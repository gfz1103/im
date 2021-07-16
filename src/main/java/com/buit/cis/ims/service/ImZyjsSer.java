package com.buit.cis.ims.service;


import com.buit.aop.lock.Locked;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.enums.CypbEnum;
import com.buit.cis.ims.model.*;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.cis.ims.request.SaveSettleAccountsReq;
import com.buit.cis.ims.request.UpdateSettleAccountsReq;
import com.buit.cis.ims.response.BalanceAccountsSettleResp;
import com.buit.cis.ims.response.PatientBalanceAccountsBaseInfoResp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.service.impl.MedicalInsuranceService;
import com.buit.his.shyb.source.service.impl.OfflineSettleService;
import com.buit.his.shyb.source.service.impl.ShybSi51Service;
import com.buit.op.model.MpiKpxx;
import com.buit.op.service.OpCzjlService;
import com.buit.op.service.OpMpiKpxxService;
import com.buit.op.service.OpMzlbService;
import com.buit.system.BUHISSystemArgument;
import com.buit.system.response.PubBrxzOut;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.PubBrxzOutSer;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.buit.utill.BUHISUtil.parseDouble;

/**
 * 住院_住院结算<br>
 *
 * @author GONGFANGZHOU
 */
@Service
public class ImZyjsSer extends BaseManagerImp<ImZyjs, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImZyjsSer.class);

    @Autowired
    private ImZyjsDao imZyjsDao;

    @Autowired
    private ImHzryDao imHzryDao;

    @DubboReference
    public OpMzlbService opMzlbService;

    @Autowired
    public ImJszfDao imJszfDao;

    @Autowired
    private ImFeeFymxDao imFeeFymxDao;

    @Autowired
    private ImTbkkSer imTbkkSer;

    @Autowired
    private BUHISUtil BUHISUtil;

    @Autowired
    private ImTbkkDao imTbkkDao;

    @Autowired
    private ImHcmxDao imHcmxDao;

    @Autowired
    private ImCwszDao imCwszDao;
    @Autowired
    private ImJsmxDao imJsmxDao;
    @Autowired
    private ImFkxxDao imFkxxDao;

    //    @DubboReference
//    private OpPosmxService opPosmxService;

    @Autowired
    private ImYgpjSer imYgpjSer;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ImDrugsOutRangeLogSer imDrugsOutRangeLogSer;


    @Override
    public ImZyjsDao getEntityMapper() {
        return imZyjsDao;
    }

    @Autowired
    public ImHzrySer imHzrySer;
    @Autowired
    public ZyjsPreService zyjsPreService;
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    @DubboReference
    private OfflineSettleService offLineSettle;
    @DubboReference
    private ShybSi51Service shybSi51Service;
    @DubboReference
    private OpCzjlService opCzjlService;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    @DubboReference
    private OpMpiKpxxService opMpiKpxxService;
    @DubboReference
    private MedicalInsuranceService medicalInsuranceService;
    @DubboReference
    private PubBrxzOutSer pubBrxzSer;

    /**
     * 结算管理-病人结算作废
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[req.zyh]"})
    public ReturnEntity<String> patientBalanceAccountsCancel(UpdateSettleAccountsReq req, SysUser sysUser, String ip) {
        //作废操作时间点
        Timestamp now = DateUtils.getNow();
        //病人入院信息
        ImHzry imHzry = imHzryDao.getById(req.getZyh());
        //本次将要作废的结算记录
        ImZyjs imzyjs = zyjsPreService.getLastImZyjs(req.getZyh());

        //数据实时性检查
        //结算已作废或者有新的结算记录产生
        if (imzyjs == null || (imzyjs != null && !imzyjs.getJscs().equals(req.getJscs()))) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00070");
        }
        //出院结算作废：患者状态不为'正常出院'，中途结算作废：患者状态不为'在院病人'
        if (!(5 == imzyjs.getJslx() && CypbEnum.NORMAL_DISCHARGE.equals(imHzry.getCypb())
                || (1 == imzyjs.getJslx() && CypbEnum.IN_HOSPITAL.equals(imHzry.getCypb())))) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00021");
        }

//        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(req);
        //医保开始
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(req);
        String YBXZ = sysXtcsCacheSer.getCsz(sysUser.getHospitalId(), "YBXZ");
        String sjxzs = zyjsPreService.getGrandBrxz(String.valueOf(req.getBrxz()));
        String YBSYPB = sysXtcsCacheSer.getCsz(sysUser.getHospitalId(), "YBSYPB");
        if (YBXZ.equals(sjxzs) && "0".equals(YBSYPB)) {
            if ("".equals(body.get("carddata")) || "".equals(body.get("cardtype"))) {
                throw BaseException.create("ERROR_SHYB_0019");
            }
            String sbxh = imzyjs.getFphm();
            if ("".equals(sbxh)) {
                throw BaseException.create("ERROR_SHYB_0047");
            }
            Map<String, Object> fee = new HashMap();
            fee.put("sbxh", sbxh);
            fee.put("ywlx", "3");
            Map<String, Object> feeMap = medicalInsuranceService.queryMedicalInsuranceTranInfo(fee);
            String jgdm = "";
            Map map_orgid = opMzlbService.getYbjgdm(sysUser.getHospitalId(), ip);
            if (map_orgid != null && !map_orgid.isEmpty()) {
                jgdm = map_orgid.get("ybjgid").toString();
            }
            if (feeMap != null) {
                feeMap.put("jgdm", jgdm);
                feeMap.put("cardtype", body.get("cardtype"));
                feeMap.put("carddata", body.get("carddata"));
                feeMap.put("zyh", req.getZyh());
                feeMap.put("ip", ip);
                feeMap.put("jgid", sysUser.getHospitalId());
                feeMap.put("ygdm", sysUser.getUserId());
                feeMap.put("ygxm", sysUser.getUserName());

                feeMap.put("orgid", jgdm);
                feeMap.put("sbxh", req.getZyh());
                Map<String, Object> tranMap = new HashMap<>();
                tranMap.put("MZBS","3");
                tranMap.put("FPHM", sbxh);
                tranMap.put("YBJSFWFYZE", feeMap.get("totfy"));
                tranMap.put("TOTALEXPENSE", feeMap.get("totalexpense"));
                tranMap.put("FYBJSFWFYZE", feeMap.get("zf"));

                SettleResultDTO sn52ResultDTO = offLineSettle.hospitalRefund(feeMap);
                if (sn52ResultDTO.getCode() == 200 && sn52ResultDTO.getData() != null) {
                    String jsonData = sn52ResultDTO.getData().toString();
                    Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                    Map<String, Object> xxtMap = (Map<String, Object>) tmpMap.get("xxnr");
                    medicalInsuranceService.saveSk01RequireNew(tmpMap, feeMap, tranMap);
                    //shybSi51Service.updateYbsi51Zf(req.getZyh() + "", feeMap.get("lsh") + "");
                } else {
                    throw BaseException.create("ERROR_SHYB_0048", new String[]{sn52ResultDTO.getCode() + "--" + sn52ResultDTO.getMsg() + ""});
                }
            }
        }
        //医保结束

        //将结算发票作废
        imzyjs.setZfrq(now);
        imzyjs.setZfgh(sysUser.getUserId().toString());
        imZyjsDao.updateFpzf(imzyjs);

        //2021.1.12 预缴款记录处理
        zyjsPreService.imTbkkSettleCancelHandler(imzyjs);

        //更新患者入院信息
        //获取上一次未作废的结算记录
        ImZyjs lastJS = zyjsPreService.getLastImZyjs(req.getZyh());
        //结算日期改为上一次结算的终止日期，首次结算则改为入院日期
        imHzry.setJsrq(lastJS == null ? imHzry.getRyrq() : lastJS.getZzrq());
        if (5 == imzyjs.getJslx()) {//出院结算作废将病人回退为"出院证明"状态，中途结算作废病人状态不做改变
            imHzry.setCypb(CypbEnum.CER_DISCHARGE.getValue());
        }
        imHzryDao.updateCypbAndJsrq(imHzry);

        //清除IM_FEE_FYMX中结算次数
        imFeeFymxDao.updatZyjszf(imzyjs);

        //住院结算作废表保存
        ImJszf zyzfParam = new ImJszf();
        zyzfParam.setZyh(req.getZyh());
        zyzfParam.setJgid(sysUser.getHospitalId());
        zyzfParam.setJscs(req.getJscs());
        zyzfParam.setZfgh(sysUser.getUserId());
        zyzfParam.setZfrq(now);
        imJszfDao.insert(imzyjs);

        // 获取门诊类别
        /*Integer mzlb = opMzlbService.getMzlb(sysUser.getHospitalId(), ip);
        imZyjsParam.setMzlb(mzlb);*/

        // 出院结算作废恢复病人床位
        if (5 == imzyjs.getJslx() && imHzry.getBrch() != null) {
            ImCwsz cwsz = imCwszDao.getByPk(sysUser.getHospitalId(), imHzry.getBrbq(), imHzry.getBrch());
            if (cwsz.getZyh() != null) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00079");
            }
            cwsz.setZyh(imHzry.getZyh());
            imCwszDao.updateCwfp(cwsz);
        }

        //jiangwei 2020.12.31 床位操作由护士在护士站内执行
        // 恢复病人床位
        /*String lsBrch = "";
        Map<String, Object> imHcmxParameters = new HashMap<String, Object>();
        imHcmxParameters.put("zyh", zyh);
        //住院号查询换床信息
        List<Map<String, Object>> imHcmx=imHcmxDao.queryHqchAndhch(imHcmxParameters);

        if (imHcmx != null && imHcmx.size() > 0) {
            if (imHcmx.get(0) != null) {
                if (imHcmx.get(0).get("hhch") != null) {
                    lsBrch = imHcmx.get(0).get("hhch") + "";
                } else {
                    lsBrch = imHcmx.get(0).get("hqch") + "";
                }
            }
        }
        // modify by yangl 中途结算不恢复床位
        if (lsBrch.length() > 0 && "5".equals(body.get("jsbs") + "")) {
            Map<String, Object> imCwszParameters = new HashMap<String, Object>();
            imCwszParameters.put("brch", lsBrch);
            imCwszParameters.put("jgid", jgid);
            //机构id跟病人床号查询住院号
            Map<String, Object> imCwszZyh = imCwszDao.queryZyhByJgidAndBrch(imCwszParameters);
            long llZyh = 0;
            if (imCwszZyh != null) {
                if (imCwszZyh.get("zyh") != null) {
                    llZyh = MapAttributeUtils.getInteger("zyh",imCwszZyh);
                }
            }
            if (llZyh == 0) {
                imHzryParameters.put("brch", lsBrch);
                //通过机构id跟病人床号修改病人住院号
                imCwszDao.updateZyhByBrchAndJgid(imHzryParameters);
                //通过住院号跟机构id修改住院病人床号
                 imHzryDao.updateBrchByZyhAndJgid(imHzryParameters);


                Map<String, Object> imCwszParameters1 = new HashMap<String, Object>();
                imCwszParameters1.put("brch", brch);
                imCwszParameters1.put("jgid", jgid);
                imCwszParameters1.put("zyh", zyh);
                imCwszDao.updateZyhByBrchAndJgid(imCwszParameters1);
                //通过病人床号跟机构id查询床位科室跟科室代码
                Map<String, Object> imCwsz=imCwszDao.queryCwksAndKsdm(imCwszParameters);

                if (imCwsz != null) {
                    Integer ksdm = MapAttributeUtils.getInteger("ksdm",imCwsz);
                    Map<String, Object> cwtjparameters = new HashMap<String, Object>();
                    cwtjparameters.put("ksdm", ksdm);
                    cwtjparameters.put("bqpb", 0);
                    ImCwsz cwtjParam=new ImCwsz();
                    cwtjParam.setCwks(ksdm);
                    int liYsys= imCwszSer.cwtj(cwtjParam);
                    liYsys = liYsys - 1;
                    int liXsys = liYsys + 1;
                    Map<String, Object> imCwtj1 = new HashMap<String, Object>();
                    imCwtj1.put("jgid", jgid);
                    imCwtj1.put("czrq", new Date());
                    imCwtj1.put("czlx", 1);
                    imCwtj1.put("zyh", zyh);
                    imCwtj1.put("brks", ksdm);
                    imCwtj1.put("ysys", liYsys);
                    imCwtj1.put("xsys", liXsys);
                    imCwtj1.put("bqpb", 0);
                    imCwtj1.put("jlxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                    imCwtjDao.insert(imCwtj1);
                    cwtjparameters.put("bqpb", 1);
                    cwtjParam.setCwks(null);
                    cwtjParam.setKsdm(ksdm);
                    liYsys = imCwszSer.cwtj(cwtjParam);
                    liYsys = liYsys - 1;
                    liXsys = liYsys + 1;
                    Map<String, Object> imCwtj2 = new HashMap<String, Object>();
                    imCwtj2.put("jgid", jgid);
                    imCwtj2.put("czrq", new Date());
                    imCwtj2.put("czlx", 1);
                    imCwtj2.put("zyh", zyh);
                    imCwtj2.put("brks", ksdm);
                    imCwtj2.put("ysys", liYsys);
                    imCwtj2.put("xsys", liXsys);
                    imCwtj2.put("bqpb", 1);
                    imCwtj2.put("jlxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                    imCwtjDao.insert(imCwtj2);
                }
            } else {
                imHzryParameters.put("brch",null);
                imHzryDao.updateBrchByZyhAndJgid(imHzryParameters);
            }
        }*/
        //如果是医保,更新医保结算表
        /*if(body.containsKey("isYb") && "1".equals(body.get("isYb").toString())){
            MedicareInterface model=new HZSMedicareModel(dao);
            Map<String,Object> map_body=new HashMap<String,Object>();
            map_body.put("zyh", zyh);
            map_body.put("jscs", jscs);
            model.updateYbzyjsxx(map_body);
        }*/
        //预付卡支付退费
        String fphm = imzyjs.getFphm();
        //通过发票号码查询预付卡信息
        List<Map<String, Object>> list = opMpiKpxxService.queryCardInfoByFphm(fphm);
        if (list.size() == 0) {
        } else {
            int brid = MapAttributeUtils.getInteger("brid", list.get(0));
            Map<String, Object> knyeMap = new HashMap<>();
            knyeMap.put("knye", MapAttributeUtils.getDouble("tfje", list.get(0)));
            knyeMap.put("brid", MapAttributeUtils.getInteger("brid", list.get(0)));
            knyeMap.put("status", 0);

            Integer czgh = sysUser.getUserId();
            String czip = ip;
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("cardno", list.get(0).get("cardno"));//carno
            data.put("cztype", "10");
            data.put("amount", list.get(0).get("tfje"));//amount
            data.put("czgh", czgh);
            data.put("czip", czip);
            data.put("czsj", new Date());
            data.put("brid", brid);
            data.put("fphm", fphm);
            //lastknye
            data.put("lastknye", MapAttributeUtils.getString("knye", list.get(0)));
            //更新卡内余额
            opMpiKpxxService.updateKnye(knyeMap);
            data.put("czxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.OP_CZJL));
            MpiKpxx mpiKpxxParam = null;
            try {
                mpiKpxxParam = objectMapper.readValue(objectMapper.writeValueAsString(data), MpiKpxx.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            opMpiKpxxService.insert(mpiKpxxParam);
        }
        //如果非商保病人
        /*if(!(Constants.BRXZ_SYBX+"").equals(body.get("BRXZ")+"")){
            //根据发票号码检查是否已经开了发票，没开发票就删除Tbl_Cust_DocMaster中对应的记录
            Map<String, Object> fphmParam = new HashMap<String, Object>();
            fphmParam.put("DocumentNr", body.get("ZYHM"));

            String ZZSFPDY = "";
            PublicService publicService = new PublicService();
            Map<String, Object> Pamargs  = publicService.doLoadThisComputerArgs(Param.instance().put("names", "ZZSFPDY"), res, dao, ctx);
            ZZSFPDY = StrUtil.null2Str(Pamargs.get("ZZSFPDY"));
            if(ZZSFPDY.equals("1")){ //启用增值税发票
                dao.doSqlUpdate("delete from Tbl_Cust_DocMaster where DocumentNr=:DocumentNr", fphmParam);
                // 去作废增值税发票
                Map<String, Object> request = new HashMap<String, Object>();
                request.put("FPHM", body.get("ZYHM"));
                request.put("FPLX", "ZY");
                PHISCommonModel phis = new PHISCommonModel(dao);
                //   phis.doCancelinv(request);
                phis.doCancelinv_jk(request,res, ctx);
            }else{
                List<Map<String, Object>> countList=dao.doSqlQuery("select count(*) as TOTAL from Tbl_Result where DocumentNr=:DocumentNr", fphmParam);
                if(countList!=null&&0==Long.parseLong(countList.get(0).get("TOTAL")+"")){
                    dao.doSqlUpdate("delete from Tbl_Cust_DocMaster where DocumentNr=:DocumentNr", fphmParam);
                }
            }*/

        imDrugsOutRangeLogSer.outRangeDrugReturn(req.getZyh());
        return ReturnEntityUtil.success();
    }

    /**
     * 结算管理-病人结算作废(高价药)
     *
     * @param zyh
     * @param user
     * @param ipAddress
     * @return
     */
    public ReturnEntity<String> patientBalanceAccountsCancelGjy(Integer zyh, SysUser user, String ipAddress) {

        return ReturnEntityUtil.success();
    }

    /**
     * 结算处理页面的结算金额和补退金额汇总
     *
     * @param zyh
     * @param user
     * @return
     */
    public ReturnEntity<BalanceAccountsSettleResp> queryBalanceAccountsSettle(Integer zyh, String jslx, String jsrq, String carddata, String cardtype, String accountattr, String ip, SysUser user, String ksrq, String zzrq, String ybjsksrq, String gsrdh) throws ParseException {
        BalanceAccountsSettleResp balanceAccountsSettleResp = new BalanceAccountsSettleResp();
        ImHzry imHzry = imHzryDao.getById(zyh);
        PubBrxzOut req = new PubBrxzOut();
        req.setSjxz(1000);
        List<PubBrxzOut> brxzList = pubBrxzSer.findByEntity(req);
        List<Integer> zfxzList = brxzList.stream().map(PubBrxzOut::getBrxz).collect(Collectors.toList());
        DecimalFormat df = new DecimalFormat("#.00");
        //五期医保
        String personspectag = "0";
        String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
        Map<String, Object> res = new HashMap();
        BigDecimal zfje = new BigDecimal(0);
        BigDecimal qtys = new BigDecimal(0);
        String YBXZ = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBXZ");
        String ybsjxz = pubBrxzSer.getSjxzByBrxz(imHzry.getBrxz().intValue()) + "";
        String jzdyh = "";
        String jssqxh = "";
        String dbxm = imHzry.getDbxm();
        if (YBXZ.equals(ybsjxz) && "0".equals(YBSYPB)) {
            String orgid = "";
            personspectag = accountattr.substring(3, 4);
            Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
            if (map_orgid != null && !map_orgid.isEmpty()) {
                orgid = map_orgid.get("ybjgid").toString();
            } else {
                throw BaseException.create("ERROR_SHYB_0015");
            }
            if ("1".equals(jslx) && "45".equals(imHzry.getBrxz().intValue())) {
                throw BaseException.create("ERROR_SHYB_0029");
            }
            if (jslx == null || cardtype == null) {
                throw BaseException.create("ERROR_SHYB_0045");
            }
            if ("1".equals(jslx) && "".equals(jsrq)) {
                throw BaseException.create("ERROR_SHYB_0030");
            }
            List<Map<String, Object>> jslist = imZyjsDao.getJscs(zyh);
            int jscs;
            if (jslist.size() > 0) {
                jscs = Integer.parseInt(jslist.get(0).get("JSCS") + "") + 1;
            } else {
                jscs = 1;
            }
            long l = shybSi51Service.countJs(zyh, jscs);//校验医保是否已经结算成功
            if (l > 0) {
                throw BaseException.create("ERROR_SHYB_0031");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("ZYH", zyh);
            map.put("JSLX", jslx);
            map.put("ip", ip);
            map.put("orgld", orgid);
            map.put("carddata", carddata);
            map.put("cardtype", cardtype);
            map.put("JSRQ", zzrq);
            map.put("jgid", user.getHospitalId());
            map.put("ygdm", user.getUserId());
            map.put("ygxm", user.getUserName());
            map.put("personspectag", personspectag);
            map.put("dbxm", dbxm);
            map.put("sclx","SN01");
            //费用明细上传
            SettleResultDTO sn01ResultDTO = offLineSettle.batchUploadFeeDetails_ZY(map, user);
            if (sn01ResultDTO.getCode() != 100 && sn01ResultDTO.getCode() != ServiceCode.CODE_OK) {
                List<Map<String,Object>> CwxxList = imHzryDao.getFycwxh(zyh);
                if(CwxxList.size()==0){
                    throw BaseException.create("ERROR_SHYB_0022", new String[]{sn01ResultDTO.getCode() + "--" + sn01ResultDTO.getMsg() + ""});
                }
                String jysj = CwxxList.get(0).get("jysj")+"";
                String Cwxx = "";
                for(Map<String, Object> cwxxmap : CwxxList){
                    String jysj_new  = cwxxmap.get("jysj")+"";
                    if(jysj_new.equals(jysj)){
                        Cwxx =Cwxx + cwxxmap.get("fymxjlxh")+"#"+cwxxmap.get("fyxh")+"-"+ cwxxmap.get("ypcd")+"-"
                                + cwxxmap.get("fymc")+"-"+ cwxxmap.get("cdmc")+"-"+ cwxxmap.get("fhxx")+"||";
                    }
                }
                throw BaseException.create("ERROR_SHYB_0022", new String[]{sn01ResultDTO.getCode() + "--" + Cwxx + ""});
            }
            //试算
            String cyjsbz = "0";
            String ryrq1 = "";
            String ryrq = imHzry.getRyrq() + "";
            if ("".equals(ryrq)) {
                throw BaseException.create("ERROR_SHYB_0033");
            }
            if ("1".equals(jslx)) {
                cyjsbz = "1";
                // ryrq1 = ryrq.substring(0, 10).replace("-", "");
                ryrq1 = ybjsksrq.substring(0, 10).replace("-", "");
            } else if ("5".equals(jslx)) {
                cyjsbz = "0";
                ryrq1 = ybjsksrq.substring(0, 10).replace("-", "");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Date date = sdf.parse(s);
            // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mi:ss");
            Date endDate = new Date();
            String jsrq1 = "";
            String zyts = "0";
            if (!"".equals(jsrq) && jsrq != null) {
                jsrq1 = jsrq.substring(0, 10).replace("-", "");
                try {
                    endDate = format.parse(jsrq.substring(0, 10) + " 23:59:59");
                    Date beginDate = format.parse(ybjsksrq);
                    long a = endDate.getTime() - beginDate.getTime();
                    zyts = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000) + "";
                    //zyts = BUHISUtil.loadDischargeDays(ryrq, cyrq);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                jsrq = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
                jsrq1 = jsrq.replace("-", "");
                try {
                    endDate = format.parse(jsrq + " 23:59:59");
                    Date beginDate = format.parse(ryrq);
                    zyts = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000) + "";
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Map<String, Object> map_ybksdm = dicKszdOutSer.getYbksdmzy(imHzry.getBrks());
            String ybks = map_ybksdm.isEmpty() ? "03" : map_ybksdm.get("ybks") + "";
            Map<String, Object> si51_map = new HashMap<>();
            si51_map.put("carddata", carddata);
            si51_map.put("cardtype", cardtype);
            si51_map.put("personspectag", personspectag);
            si51_map.put("yllb", "S21");
            si51_map.put("persontype", "0");
            if ("-1".equals(gsrdh)) {
                si51_map.put("gsrdh", "");
            } else {
                si51_map.put("gsrdh", gsrdh);
                si51_map.put("persontype", "1");
            }
            si51_map.put("cyjsbz", cyjsbz);
            si51_map.put("jsksrq", ryrq1);
            si51_map.put("jsjsrq", jsrq1);
            si51_map.put("zyts", zyts);
            si51_map.put("zyh", zyh);
            si51_map.put("deptid", ybks);
            si51_map.put("jzdyh", imHzry.getJzdyh());
            si51_map.put("xsywlx", "");
            si51_map.put("JSLX", jslx);
            si51_map.put("jgdm", orgid);
            si51_map.put("ip", ip);
            si51_map.put("JSCS", jscs);
            si51_map.put("jsrq", jsrq.substring(0, 10).replaceAll("-", ""));
            si51_map.put("jgid", user.getHospitalId());
            si51_map.put("ygdm", user.getUserId());
            si51_map.put("ygxm", user.getUserName());
            SettleResultDTO sn51ResultDTO = offLineSettle.si51(si51_map, res);

            if (sn51ResultDTO.getCode() == 200 && sn51ResultDTO.getData() != null) {
                String jsonData = sn51ResultDTO.getData().toString();
                Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                Map<String, Object> xxtMap = (Map<String, Object>) tmpMap.get("xxnr");
                zfje = new BigDecimal(xxtMap.get("ybjsfwfyze") + "").subtract(new BigDecimal(xxtMap.get("totalexpense") + ""))
                        .add(new BigDecimal(xxtMap.get("fjdxjzfs") + "")).add(new BigDecimal(xxtMap.get("tcdxjzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("qfdxjzfs") + "")).add(new BigDecimal(xxtMap.get("fybjsfwfyze") + ""));
                qtys = new BigDecimal(xxtMap.get("totalexpense") + "");
                jssqxh = StrUtil.null2Str(xxtMap.get("jssqxh"));
                jzdyh = imHzry.getJzdyh();
                BigDecimal ybjsfwfyze = new BigDecimal(xxtMap.get("ybjsfwfyze") + "");  //医保结算范围费用总额
                BigDecimal fybjsfwfyze = new BigDecimal(xxtMap.get("fybjsfwfyze") + "");  //非医保结算范围费用总额
                BigDecimal flzf = new BigDecimal(xxtMap.get("ybjsfwfyze") + "")  //分类自负
                        .subtract(new BigDecimal(xxtMap.get("totalexpense") + ""));
                BigDecimal ybzf = new BigDecimal(xxtMap.get("fjdxjzfs") + "")
                        .add(new BigDecimal(xxtMap.get("tcdxjzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("qfdxjzfs") + "")); //自负
                BigDecimal grzhzf = new BigDecimal(xxtMap.get("fjdzhzfs") + "")
                        .add(new BigDecimal(xxtMap.get("qfdzhzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("tcdzhzfs") + ""));  //个人账户支付
                BigDecimal tczf = new BigDecimal(xxtMap.get("tczfs") + ""); //统筹支付
                BigDecimal fjzf = new BigDecimal(xxtMap.get("fjzfs") + ""); //附加支付
                BigDecimal curaccountamt = new BigDecimal(xxtMap.get("curaccountamt") + ""); //当年账户余额
                BigDecimal hisaccountamt = new BigDecimal(xxtMap.get("hisaccountamt") + ""); //当年账户余额

                balanceAccountsSettleResp.setYbjsfwfyze(ybjsfwfyze);
                balanceAccountsSettleResp.setFybjsfwfyze(fybjsfwfyze);
                balanceAccountsSettleResp.setFlzf(flzf);
                balanceAccountsSettleResp.setYbzf(ybzf);
                balanceAccountsSettleResp.setGrzhzf(grzhzf);
                balanceAccountsSettleResp.setTczf(tczf);
                balanceAccountsSettleResp.setFjzf(fjzf);
                balanceAccountsSettleResp.setCuraccountamt(curaccountamt);
                balanceAccountsSettleResp.setHisaccountamt(hisaccountamt);
                //校验上传明细与本地费用明细条数是否相等
                List<Map<String,Object>> fymxlist =  imFeeFymxDao.getMxts(imHzry.getZyh(),ksrq,zzrq);
                if( fymxlist.size()>0 ){
                    String mxcwxx = "";
                    for(Map<String, Object> fymxmap : fymxlist){
                        mxcwxx =mxcwxx +  fymxmap.get("fymc")+"("+fymxmap.get("jlxh")+"#"+fymxmap.get("fyxh")+"-"+ fymxmap.get("ypcd")+")"
                                    +"||";
                    }
                    throw BaseException.create("ERROR_SHYB_0049", new String[]{sn01ResultDTO.getCode() + "--" + mxcwxx + ""});
                }
//                zfje = zfje.add(new BigDecimal(xxtMap.get("qfdxjzfs")+"").add
//                        (new BigDecimal(xxtMap.get("tcdxjzfs")+"")).add
//                        (new BigDecimal(xxtMap.get("fjdxjzfs")+"")));
            } else {
                throw BaseException.create("ERROR_SHYB_0033", new String[]{sn51ResultDTO.getCode() + "--" + sn51ResultDTO.getMsg() + ""});
            }
        }
        //结束
        BeanUtils.copyProperties(imHzry, balanceAccountsSettleResp);

        ReturnEntity<PatientBalanceAccountsBaseInfoResp> resp = imHzrySer.queryPatientBalanceAccountsBaseInfo(zyh, user.getHospitalId(), ksrq, zzrq, personspectag, jslx, zfje + "");
        if (zfxzList.contains(imHzry.getBrxz())) {//普通自费
            if (resp.getData() != null) {

                // CardPatientCostBaseInfoJe cardPatientCostBaseInfoJe= imFeeFymxDao.queryPatientCost(zyh,user.getHospitalId());
                //总费用
                balanceAccountsSettleResp.setZfy(resp.getData().getZyf().setScale(2, BigDecimal.ROUND_HALF_UP));
                //自负金额
                balanceAccountsSettleResp.setZfje(resp.getData().getZfje().setScale(2, BigDecimal.ROUND_HALF_UP));
                //查询缴费金额
                balanceAccountsSettleResp.setYjk(imTbkkSer.queryJkje(zyh));
                //计算剩余金额
                balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP));
                //折扣金额
                balanceAccountsSettleResp.setJmje(resp.getData().getZkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        } else {
            balanceAccountsSettleResp.setZfy(resp.getData().getZyf().setScale(2, BigDecimal.ROUND_HALF_UP));
            balanceAccountsSettleResp.setZfje(zfje);
            balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            // balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP).add(qtys));
            balanceAccountsSettleResp.setYjk(imTbkkSer.queryJkje(zyh));
            //折扣金额
            balanceAccountsSettleResp.setJmje(resp.getData().getZkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            balanceAccountsSettleResp.setJzdyh(jzdyh);
            balanceAccountsSettleResp.setJssqxh(jssqxh);
        }
        //查询患者的卡内余额
        if (imHzry.getBrid() == null) {
            balanceAccountsSettleResp.setKnye(new BigDecimal("0"));
            return ReturnEntityUtil.success(balanceAccountsSettleResp);
        }
        List<Map<String, Object>> list = opMpiKpxxService.queryCardInfoByBrid(String.valueOf(imHzry.getBrid()));
        if (list.size() == 0) {
            balanceAccountsSettleResp.setKnye(new BigDecimal("0"));
            return ReturnEntityUtil.success(balanceAccountsSettleResp);
        }
        balanceAccountsSettleResp.setKnye(new BigDecimal(MapAttributeUtils.getString("knye", list.get(0))));
        return ReturnEntityUtil.success(balanceAccountsSettleResp);
    }

    /**
     * 结算管理-住院结算保存
     *
     * @return
     */
    @Locked(value = {"saveAccountMoney_#[req.zyh]"})
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> saveSettleAccounts(SaveSettleAccountsReq req, SysUser sysUser, String ip) {
        //结算时操作时间点
        Timestamp now = DateUtils.getNow();
        Integer jgid = sysUser.getHospitalId();
        Integer userId = sysUser.getUserId();
        //门诊类别，区分医保线路
        Integer mzlb = opMzlbService.getMzlb(sysUser.getHospitalId(), ip);
        //判断VIP
        Boolean isVIP = zyjsPreService.isVIP(jgid, String.valueOf(req.getBrxz()));
        //上级病人性质
        String sjxzs = zyjsPreService.getGrandBrxz(String.valueOf(req.getBrxz()));
        //病人入院信息
        ImHzry imHzry = imHzryDao.getById(req.getZyh());
        //最后一次结算记录
        ImZyjs lastJs = zyjsPreService.getLastImZyjs(req.getZyh());

        if (1 == req.getJslx() &&
                req.getZfhj().doubleValue() * 1 == 0 &&
                req.getJkhj().doubleValue() * 1 == 0 &&
                !"6021".equals(sjxzs) &&
                23 != req.getBrxz()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00068");
        }
        //病人状态检查
        if (5 == req.getJslx() && 1 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00023");
        } else if (1 == req.getJslx() && 0 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00071");
        }
        //首次结算时病人结算记录不为空，或者病人当前结算次数与最终记录的结算次数不一致
        //代表结算预处理界面停留过程中，病人已进行过结算
        if (req.getJscs() != imHzry.getJscs()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00070");
        }

        //时间检查, 防止选择时间段内有新的费用明细或者缴款记录产生产生
        Timestamp ksrq = DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getKsrq());
        Timestamp zzrq = DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getZzrq());
        if (zzrq.after(now)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00072");
        }
        //开始日期不能大于终止日期
        if (ksrq.compareTo(zzrq) > 0) {
            throw BaseException.create("ERROR_OPGD_0007");
        }
        //已结算的日期与本次结算的时间段不能有重叠
        if (lastJs != null && lastJs.getZzrq().compareTo(ksrq) > 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00075");
        }
        //缴款记录检查
        BigDecimal accountLeft = imTbkkSer.queryJkje(req.getZyh());
        if (req.getJkhj().compareTo(accountLeft) != 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00073");
        }

        //是否重复申请医保
        if (StrUtil.isNotBlank(req.getJssqxh())) {
//                Map<String, Object> ybSiParameters = new HashMap<String, Object>(16);
//                ybSiParameters.put("jssqxh", map_jsxx.get("jssqxh").toString());
//                ybSiParameters.put("jscs", (Integer) map_jsxx.get("jscs") + 1);
            String totalexpense = req.getTotalexpense();
            long l = shybSi51Service.countisJs(totalexpense, req.getZyh());
            if (l > 0) {
                throw BaseException.create("ERROR_SHYB_0034");
            }
        }
        // 2021.1.7 jiangwei 无用代码梳理 出院终结不可用
            /*//出院终结
            if ("4".equals(map_jsxx.get("jslx") + "")) {
                  // 将IM_TBKK打上结算次数
                *//*Map<String, Object> imTbkkParameters = new HashMap<String, Object>(16);
                imTbkkParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imTbkkParameters.put("zyh", zyh);
                imTbkkParameters.put("jgid", jgid);
                imTbkkDao.updateJscs(imTbkkParameters);*//*

                // 将IM_FEE_FYMX打上结算次数
                Map<String, Object> imFeeFymxParameters = new HashMap<String, Object>(16);
                imFeeFymxParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imFeeFymxParameters.put("zyh", zyh);
                imFeeFymxParameters.put("jgid", jgid);
                imFeeFymxDao.updateJscs(imFeeFymxParameters);

                // 将IM_HCMX打上结算次数
                imHcmxDao.updateJscs(imFeeFymxParameters);

                // 写结算费用明细表IM_FEE_FYMX_JS
                Map<String, Object> imFeeFymxJsParameters = new HashMap<String, Object>(16);
                imFeeFymxJsParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imFeeFymxJsParameters.put("zyh",
                        Integer.parseInt(map_jsxx.get("zyh") + ""));
                imFeeFymxJsDao.addJs(imFeeFymxJsParameters);

                // 清空病人床位
                Map<String, Object> zyCwsZparameters = new HashMap<String, Object>(16);
                zyCwsZparameters.put("jgid", jgid);
                zyCwsZparameters.put("zyh", Long.parseLong(map_jsxx.get("zyh") + ""));

                Map<String, Object> imHzryParameters = new HashMap<String, Object>(16);
                imHzryParameters.put("zzrq", imZyjs.get("zzrq"));
                imHzryParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imHzryParameters.put("zyh", zyh);
                imHzryParameters.put("jgid", jgid);
                // 根据不同结算类型 清空床位方式不同

                imHzryDao.updateZyjs(imHzryParameters);
                imCwszDao.tc(zyCwsZparameters);

                // 准备住院付款信息表单数据
                Map<String, Object> imFkxx = new HashMap<String, Object>(16);
                imFkxx.put("fkfs", map_jsxx.get("fkfs"));
                imFkxx.put("fkje", map_jsxx.get("ysje"));
                imFkxx.put("jgid", jgid);
                imFkxx.put("zyh", Long.parseLong(map_jsxx.get("zyh") + ""));
                imFkxx.put("jscs", (Integer) map_jsxx.get("jscs") + 1);
                imZyjsDao.insert(imZyjs);
                // 写结算明细(IM_JSMX)
                Map<String, Object> imJsmxParameters = new HashMap<String, Object>(16);
                imJsmxParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imJsmxParameters.put("zyh", Integer.parseInt(map_jsxx.get("zyh") + ""));
                imJsmxDao.insertJs(imJsmxParameters);
                imFkxxDao.insert(imFkxx);
                if (Double.parseDouble(map_jsxx.get("wcje") + "") != 0) {
                    Map<String, Object> zyHbwc = new HashMap<String, Object>(16);
                    zyHbwc.put("fkfs", map_jsxx.get("wcfs"));
                    zyHbwc.put("fkje", map_jsxx.get("wcje"));
                    zyHbwc.put("jgid", jgid);
                    zyHbwc.put("zyh", zyh);
                    zyHbwc.put("jscs", MapAttributeUtils.getInteger("jscs", map_jsxx) + 1);
                    imFkxxDao.insert(zyHbwc);
                }
            }*/

        //保存结算主表信息
        ImZyjs imZyjs = new ImZyjs();
        BeanUtils.copyProperties(req, imZyjs);
        imZyjs.setJscs(req.getJscs() + 1);
        imZyjs.setCzgh(String.valueOf(userId));
        imZyjs.setZfpb(0);
            /*if ("1".equals(isgjy)) {
                imZyjs.put("fyhj", map_jsxx.get("fyhjBak"));
                imZyjs.put("zfhj", map_jsxx.get("zfhjBak"));
                imZyjs.put("fyzj", map_jsxx.get("fyhjBak"));
                imZyjs.put("zfzj", map_jsxx.get("zfhjBak"));
            }*/
//            imZyjs.put("jszj", map_jsxx.get("jsje"));

        imZyjs.setJgid(jgid);
        imZyjs.setKsrq(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getKsrq()));
        imZyjs.setZzrq(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getZzrq()));
        imZyjs.setZyts(BigDecimal.valueOf(BUHISUtil.loadDischargeDays(req.getKsrq(),req.getZzrq())));
        imZyjs.setJsrq(now);
        imZyjs.setMzlb(mzlb);
        imZyjsDao.insert(imZyjs);
        //更新当前的发票号码
        imYgpjSer.setBillNumber(1, req.getFphm(), sysUser.getUserId(), sysUser.getHospitalId());

        // 2021.1.6 jiangwei 新增扣费记录
        zyjsPreService.imTbkkSettleHandler(imZyjs);

        // 将IM_FEE_FYMX打上结算次数
        // imFeeFymxDao.updateJscs(imZyjs);  //若是医保放入ybapi更新

        // 写结算费用明细表IM_FEE_FYMX_JS
//        imFeeFymxJsDao.addJs(imZyjs);

        // 更新病人入院信息
        if (5 == req.getJslx()) {
            imHzryDao.updateCyjs(imZyjs);
            //护士没有执行退床操作时，出院结算需要清空床位
            if (zyjsPreService.isStayBed(imZyjs.getZyh())) {
                imCwszDao.tc(imZyjs);
                imHcmxDao.updateJscsAndJsrq(imZyjs);
            }
        } else if (1 == req.getJslx()) {
            imHzryDao.updateZtjs(imZyjs);
        }


        //保存付款信息
        zyjsPreService.imFkxxSettleHandler(req, sysUser, ip, imHzry.getBrid());

        //费用明细检查
        boolean isOutRange = imFeeFymxDao.isOutOfRange(req.getZyh(), req.getCzsj(), req.getZzrq());
        if (isOutRange) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00081");
        }
        //2021.1.7 jiangwei 无用代码梳理 传入参数中无pos信息
            /*Map<String, Object> pos = (Map<String, Object>) map_jsxx.get("pos");
            // 付费方式：POS机(新添微信支付宝)，写入pos机交易信息
            if (("8".equals(fffs) || "17".equals(fffs) || "27".equals(fffs)) && "1".equals(pos.get("success"))) {
                Map<String, Object> posInfo = MapAttributeUtils.upcaseKeys(pos);
                // posInfo.put("ID", id);
                posInfo.put("jysj", new Date());
                posInfo.put("jylb", pos.get("TransType"));
                posInfo.put("fphm", req.getFphm());
                OpPosmx opPosmx = objectMapper.readValue(objectMapper.writeValueAsString(posInfo), OpPosmx.class);
                opPosmxService.insert(opPosmx);
            }*/

        // 2021.1.6 jiangwei 无用代码梳理 传入参数中无高价药标识
            /*if ("1".equals(isgjy)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String cyrqString = sdf.format(cyrq);


                Map<String, Object> imFeeFymxGjy = new HashMap<String, Object>(16);
                imFeeFymxGjy.put("ujscs", jscs + 1);
                imFeeFymxGjy.put("zyh", zyh);
                imFeeFymxGjy.put("jgid", jgid);
                //IM_FEE_FYMX_GJY.put("FYRQ", CYRQ.get("CYRQ"));
                imFeeFymxGjy.put("fyrq", cyrqString);
                //更新高价药的费用结算明细
                imFeeFymxDao.updateJsGjy(imFeeFymxGjy);
                //更新高价药的费用结算明细
                imFeeFymxJsDao.updateJsGjy(imFeeFymxGjy);


            }*/
//            if(null!=fkxxwc){
//                res.put("jlxhwc", fkxxwc.get("JLXH"));
//            }
//            if(null!=fkxx){
//                res.put("jlxh", fkxx.get("JLXH"));
//            }

        //20211.6 jiangwei 无用代码梳理 只有查询操作，不对流程造成影响
            /*if (!("6024").equals(map_jsxx.get("brxz") + "")) {
                //*********************增值税*******************************
                String zzsFkfs = "6";
                if (null != map_jsxx.get("fkfs")) {
                    zzsFkfs = map_jsxx.get("fkfs") + "";
                } else if (!"".equals(fffs) && !"null".equals(fffs) && !"0".equals(fffs) && !"6".equals(fffs)) {
                    zzsFkfs = fffs;
                }

                //获取增值税的付款方式信息
                List<Map<String, Object>> fkfslist = pubFkfsService.getZzsFkfs(zzsFkfs);
                //获取病人性质信息
                List<Map<String, Object>> sjxzList = pubBrxzSer.getSjxzList(map_jsxx.get("brxz").toString());
                String sjxz = sjxzList.get(0).get("sjxz") + "";
                if ("1000".equals(sjxz)) {

                }
                String zzsJzhm = "";
                String zzsBz2 = "";
                DecimalFormat df = new DecimalFormat("#.00");
                zzsJzhm = "单据号:" + map_jsxx.get("fphm") + "";

                //if(!"0.00".equals(OP_MZXX.get("XJJE")+"")){
                if ("1000".equals(sjxz)) {
                    zzsBz2 += "" + fkfslist.get(0).get("fkmc") + ":" + df.format(Double.parseDouble(map_jsxx.get("fyhj") + "")) + "元";
                }
                if (!"0.0".equals(map_jsxx.get("JMJE") + "") && !"0".equals(map_jsxx.get("jmje") + "")) {
                    zzsBz2 += ",减免:" + df.format(map_jsxx.get("jmje")) + "元";
                }

        *//*      Map<String,Object> invoiceInfo=new HashMap<String, Object>();
                invoiceInfo.put("jscs", (Integer) body.get("jscs")+1);
                invoiceInfo.put("zyh", zyh);
                invoiceInfo.put("invKind", 2);//发票类型 0：增值税专用发票，2：增值税普通发票
                invoiceInfo.put("documentNr", body.get("fphm"));//单据号
                invoiceInfo.put("documentDate", new Date());//单据日期
                invoiceInfo.put("customerName", MapAttributeUtils.getString("brxm",body) + "     " +pubBrxz.getXzmc() + "     卡号: "+ imHzry.getYbkh());//客户名称
                invoiceInfo.put("customerTaxNr", "");//客户税号
                invoiceInfo.put("customerAddressTel", "");//客户地址电话

                Map<String,Object> invoiceInfo=new HashMap<String, Object>(16);
                invoiceInfo.put("JSCS", (Integer) body.get("jscs")+1);
                invoiceInfo.put("ZYH", Integer.parseInt(body.get("zyh") + ""));
                invoiceInfo.put("InvKind", 2);//发票类型 0：增值税专用发票，2：增值税普通发票
                invoiceInfo.put("DocumentNr", body.get("fphm"));//单据号
                invoiceInfo.put("DocumentDate", new Date());//单据日期
                invoiceInfo.put("CustomerName", StrUtil.null2Str(body.get("brxm")) + "     " +StrUtil.null2Str( map_brxz.get("XZMC")) + "     卡号: "+ StrUtil.null2Str(map_brry.get("YBKH")));//客户名称
                invoiceInfo.put("CustomerTaxNr", "");//客户税号
                invoiceInfo.put("CustomerAddressTel", "");//客户地址电话
                //invoiceInfo.put("CustomerBankAccountNr", "");//客户银行帐号

                invoiceInfo.put("customerBankAccountNr",zzsJzhm);//客户银行帐号

                invoiceInfo.put("invoicer", "");//开票人
                invoiceInfo.put("checker", "");//复核人
                invoiceInfo.put("payee", userId);//收款人
                invoiceInfo.put("productUnit", "");//商品计量单位
                invoiceInfo.put("taxRate", 0);//税率
                invoiceInfo.put("productTax", 0);//商品税额
                invoiceInfo.put("discountValue", 0);//折扣金额
                invoiceInfo.put("discountTax", 0);//折扣税额
                invoiceInfo.put("machineNr", "");//开票机号
                invoiceInfo.put("taxItem", "");//明细税目
                invoiceInfo.put("goodsNoVer", "30.0");//编码版本号
                invoiceInfo.put("goodsTaxNo", "3070202");//税收分类编码
                invoiceInfo.put("taxPre", "1");//是否享受优惠政策
                invoiceInfo.put("taxPreCon", "免税");//享受税收优惠政策内容
                invoiceInfo.put("zeroTax", "1");//零税率标识
                invoiceInfo.put("taxDeduction", 0);//扣除额
                invoiceInfo.put("alternateKey", "");//备用关键字

                invoiceInfo.put("zzsbzxx", zzsBz2);//备用关键字2

                StringBuffer invoiceSql=new StringBuffer();

                invoiceSql.append("insert into Tbl_Cust_DocMaster (invKind,documentNr,documentDate,customerName,customerTaxNr,customerAddressTel,customerBankAccountNr,invoicer,checker,payee,productName,productUnit,");
                invoiceSql.append("productQuantity,productValue, productAmount, taxRate,productTax,discountValue,discountTax,machineNr,taxItem,goodsNoVer,goodsTaxNo,taxPre,taxPreCon,zeroTax,taxDeduction,alternateKey");
                invoiceSql.append(",zzsbzxx");
                invoiceSql.append(")");
                invoiceSql.append("select :invKind,:documentNr,:documentDate,:customerName,:customerTaxNr,:customerAddressTel,:customerBankAccountNr,:invoicer,:checker,:payee,t.productName,:productUnit,");
                invoiceSql.append(" 1,t.productValue,t.productValue,:taxRate,:productTax,:discountValue,:discountTax,:machineNr,:taxItem,:goodsNoVer,:goodsTaxNo,:taxPre,:taxPreCon,:zeroTax,:taxDeduction,:alternateKey ");
                invoiceSql.append(",:ZZSBZXX ");//---------------
                invoiceSql.append("from(");
                invoiceSql.append("select sf.SFMC as productName, sum(mx.ZJJE) as productValue from IM_JSMX mx,DIC_SFXMLB sf ");
                invoiceSql.append("where mx.fyxm=sf.sfxm and mx.zyh = :ZYH and mx.jscs = :JSCS ");
                invoiceSql.append("group by mx.FYXM ,sf.SFMC having sum(mx.ZJJE) <>0 ");
                invoiceSql.append(")t");
                dao.doSqlUpdate(invoiceSql.toString(), invoiceInfo);*//*
            }*/
        //医保开始
        logger.info("住院实结算开始1：");
        String YBSYPB = sysXtcsCacheSer.getCsz(jgid, "YBSYPB");
        String YBXZ = sysXtcsCacheSer.getCsz(jgid, "YBXZ");
        if (sjxzs.equals(YBXZ) && "0".equals(YBSYPB)) {
            String orgid = "";
            Map map_orgid = opMzlbService.getYbjgdm(jgid, ip);
            if (map_orgid != null && !map_orgid.isEmpty()) {
                orgid = map_orgid.get("ybjgid").toString();
            } else {
                throw BaseException.create("ERROR_SHYB_0015");
            }
            Map<String, Object> map_ybksdm = dicKszdOutSer.getYbksdmzy(req.getBrks());
            String ybksdm = map_ybksdm.isEmpty() ? "03" : map_ybksdm.get("ybks") + "";
            Map<String, Object> map = new HashMap();

            String accountattr = req.getAccountattr();//账户标志1
            map.put("personspectag", accountattr.substring(3, 4));
            map.put("zyh", req.getZyh());//住院号2
            map.put("carddata", req.getCarddata());//卡号3
            map.put("cardtype", req.getCardtype());//卡类型4
                /*if(!"".equals(req.getGsrdh())){//工伤认定号5
                    map.put("persontype", "1"); //工伤
                    map.put("gsrdh", req.getGsrdh());
                }else{
                    map.put("persontype", "0");
                    map.put("gsrdh", "");
                }*/
            map.put("persontype", "0");
            if ("-1".equals(req.getGsrdh())) {
                map.put("gsrdh", "");
            } else {
                map.put("gsrdh", req.getGsrdh());
            }
            if (1 == req.getJslx()) {//结算类型7
                map.put("cyjsbz", "1");
                map.put("JSLX", "1");
            } else if (5 == req.getJslx()) {
                map.put("cyjsbz", "0");
                map.put("JSLX", "5");
            }
            String jsksrq = "";
            String jsjsrq = "";
            if (!"".equals(req.getYbjsksrq()) && !"".equals(req.getYbjsjsrq())) {
                jsksrq = req.getYbjsksrq().substring(0, 10).replaceAll("-", "");
                jsjsrq = req.getYbjsjsrq().replaceAll("-", "");
            }
            map.put("jsksrq", jsksrq); //结算开始时间8
            map.put("jsjsrq", jsjsrq); //结算结束时间9
            map.put("zyts", req.getJsts()); //住院天数10
            map.put("jzdyh", req.getJzdyh()); //就诊单元号11
            map.put("jssqxh", req.getJssqxh());//计算申请序号12

            map.put("JSCS", req.getJscs() + 1);//结算次数
            map.put("yllb", "S21");//S21住院  S24急诊观察
            map.put("jgdm", orgid);
            map.put("deptid", ybksdm);
            map.put("xsywlx", "1");
            map.put("ip", ip);
            map.put("jgid", sysUser.getHospitalId());
            map.put("ygdm", sysUser.getUserId());
            map.put("ygxm", sysUser.getUserName());
            logger.info("住院实结算开始2：");
            SettleResultDTO sn52ResultDTO = offLineSettle.si52(map);
            logger.info("住院实结算3：" + sn52ResultDTO);
            if (sn52ResultDTO.getCode().intValue() == ServiceCode.CODE_OK && !"".equals(sn52ResultDTO.getData().toString())) {
                String jsonData = sn52ResultDTO.getData().toString();
                Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                Map<String, Object> xxtMap = (Map<String, Object>) tmpMap.get("xxnr");
                BigDecimal ZFHJ = new BigDecimal(xxtMap.get("ybjsfwfyze") + "")
                        .subtract(new BigDecimal(xxtMap.get("totalexpense") + ""))
                        .add(new BigDecimal(xxtMap.get("qfdxjzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("tcdxjzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("fjdxjzfs") + ""));
                ;
                // imZyjsDao.updateImzyjsZfhj(ZFHJ, req.getJscs() + 1, req.getZyh(), sysUser.getHospitalId());
                //shybSi51Service.updateJscs52(xxtMap.get("jssqxh") + "", String.valueOf(req.getJscs()));
            } else if (sn52ResultDTO.getCode() == 1119) {

            } else {
                throw BaseException.create("ERROR_SHYB_0046", new String[]{sn52ResultDTO.getCode() + "--" + sn52ResultDTO.getMsg() + ""});
            }
        }
        imFeeFymxDao.updateJscs(imZyjs);
        // 写结算明细(IM_JSMX)
        Map<String, Object> imJsmxParameters = new HashMap<String, Object>(16);
        imJsmxParameters.put("UJSCS", req.getJscs() + 1);
        imJsmxParameters.put("ZYH", req.getZyh());
        if (false == isVIP) {
            imJsmxDao.insertJs(imJsmxParameters);
        } else {
            imJsmxDao.insertJsMx(imJsmxParameters);
        }

        //医保结束
        return ReturnEntityUtil.success();
    }


    /**
     * 查询病人的实际病人性质
     *
     * @param brxz
     * @param
     * @return
     */
    public ReturnEntity<Integer> querySjBrxz(Integer brxz) {
        PubBrxzOut pubBrxz = pubBrxzSer.querySjBrxz(brxz);
        if (pubBrxz != null) {
            return ReturnEntityUtil.success(pubBrxz.getSjxz());
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 获取住院发票打印数据
     *
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doQueryZyfpdy(SysUser user, String fphm) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> resp = new HashMap<>();

        int isVIP = 0;//是否是VIP病人
        SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer jgid = user.getHospitalId();
        //结算信息
        Map<String, Object> JSXX = new HashMap<String, Object>();
        //收费明细
        List<Map<String, Object>> SFXMS = new ArrayList<Map<String, Object>>();
        //交款信息
        Map<String, Object> JKJES = new HashMap<String, Object>();
        //医保结算信息
        Map<String, Object> YBJES = new HashMap<String, Object>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, Object> parameters2 = new HashMap<String, Object>();
        //查询结算信息


        try {
            parameters.put("fphm", fphm);
            parameters.put("jgid", jgid);
            //结算信息
            //JSXX = dao.doLoad(jsxxHQL.toString(), parameters);
            List<Map<String, Object>> jsxxList = imZyjsDao.queryDyfpJsxx(parameters);
            if (jsxxList.size() > 0) {
                JSXX = jsxxList.get(0);
            }

            Map<String, Object> dateMap = imZyjsDao.queryDyfpJsrq(parameters);
            if (dateMap != null) {
                response.putAll(dateMap);
            }
            parameters.clear();
            parameters2.clear();
            if (JSXX.size() > 0) {
                parameters.put("ZYH", JSXX.get("ZYH"));
                parameters.put("JSCS", JSXX.get("JSCS"));
                //=================
                //获取VIP
                Boolean vipbrxz = zyjsPreService.isVIP(jgid, JSXX.get("BRXZ").toString());
                if (vipbrxz) {
                    isVIP = 1;
                }
            }
            parameters2.put("ZYH", JSXX.get("ZYH"));
            parameters2.put("JSCS", JSXX.get("JSCS"));
            parameters2.put("VIP", isVIP);
            //收费项目的发票
            SFXMS = imFeeFymxDao.queryqueryDyfp(parameters2);
            //double fyhj = 0.00;
            BigDecimal fyhj = BigDecimal.ZERO;
            List<Map<String, Object>> sfxmList = new ArrayList();
            for (int i = 0; i < SFXMS.size(); i++) {
                Map<String, Object> sfxm = SFXMS.get(i);
                Map<String, Object> map = new HashMap<>();
                map.put("SFXM", sfxm.get("SFMC"));
                map.put("XMJE", String.format("%1$.2f", sfxm.get("ZJJE")));
                sfxmList.add(map);
                //response.put("SFXM" + sfxm.get("ZYPL"), sfxm.get("SFMC"));
                //response.put("XMJE" + sfxm.get("ZYPL"), String.format("%1$.2f", sfxm.get("ZJJE")));
                fyhj = fyhj.add(new BigDecimal(sfxm.get("ZJJE") + ""));
            }
            response.put("sfxm", sfxmList);
            //重新给JSXX中的合计金额赋值
            JSXX.put("HJJE", fyhj);
            //缴款信息
            //JKJES = dao.doLoad(jkxxHQL.toString(), parameters);
            JKJES = imTbkkDao.queryDyfpJkje(parameters);

            BigDecimal jkje = BigDecimal.ZERO;
            if (JKJES.get("JKJE") != null) {
                jkje = new BigDecimal(JKJES.get("JKJE") + "").negate();
            }
            response.put("ZYYJJ", String.format("%1$.2f", jkje));  //住院预缴金额1
            /*response.put("KSRQ", JSXX.get("KSRQ"));//开始日期
            //年月日
            response.put("JSLX", JSXX.get("JSLX"));//结算类型
            response.put("N", JSXX.get("YYYY") + "");
            response.put("Y", JSXX.get("MM") + "");
            response.put("R", JSXX.get("DD") + "");
            response.put("ZYHM", JSXX.get("XLH") + "");//住院号码
            response.put("BRXM", JSXX.get("XM") + "");//姓名
            response.put("BRXB", JSXX.get("BRXB") + "");//性别
            response.put("RYLB", JSXX.get("RYLB") + "");//人员类别
            response.put("GZDW", JSXX.get("GZDW"));//工作单位
            response.put("SYY", JSXX.get("SYY") + "");//收银员
            response.put("JSRQ", JSXX.get("JSRQ") + "");//收
            response.put("JSRQ1", JSXX.get("JSRQ1"));//中途结算日期
            response.put("CSNY", JSXX.get("CSNY"));//出生年月
            response.put("YBKH", JSXX.get("YBKH"));//就诊卡号
            response.put("BRKS", JSXX.get("BRLS"));//病人科室
            if (JSXX.get("BRCH") != null) {
                response.put("CWH", JSXX.get("BRCH") + "");
            }
            if (JSXX.get("RYRQ1") != null) {
                response.put("FY", (JSXX.get("RYRQ1") + "").substring(0, 4));
            }
            if (JSXX.get("RYMM") != null) {
                response.put("FM", JSXX.get("RYMM") + "");
            }
            if (JSXX.get("RYDD") != null) {
                response.put("FD", JSXX.get("RYDD") + "");
            }
            if (JSXX.get("CYRQ1") != null) {
                response.put("TY", (JSXX.get("CYRQ1") + "").substring(0, 4));
            }
            if (JSXX.get("CYMM") != null) {
                response.put("TM", JSXX.get("CYMM") + "");
            }
            if (JSXX.get("CYDD") != null) {
                response.put("TD", JSXX.get("CYDD") + "");
            }
            double days = 0;
            if (JSXX.get("RYRQ") != null && JSXX.get("CYRQ") != null) {
                days = BUHISUtil.loadDischargeDays(
                        JSXX.get("RYRQ") + "",
                        JSXX.get("CYRQ") + "");
            }

            String zYRQ = "";
            if (JSXX.get("CYRQ1") != null) {
                zYRQ = JSXX.get("RYRQ1") + "-" + JSXX.get("CYRQ1") + "(" + days + "天)";
            } else {
                if (JSXX.get("RYRQ") != null) {
                    days = BUHISUtil.loadDischargeDays(
                            JSXX.get("RYRQ") + "",
                            sdftime.format(new Date())
                    );
                }

                zYRQ = JSXX.get("RYRQ1") + "-" + "至今(" + days + "天)";
            }

            response.put("DAYS", days + "");
            response.put("ZYRQ", zYRQ + "");//住院日期
            if (JSXX.get("JSRQ") != null && JSXX.get("KSRQ") != null) {
                jsts = BUHISUtil.loadDischargeDays(
                        JSXX.get("KSRQ") + "",
                        JSXX.get("ZZRQ") + ""
                );
            }*/
            String jstss = "";
            String jsts = "0";
            jsts = JSXX.get("ZYTS") + "";
           /* if (JSXX.get("JSRQ1") != null) {
                jstss = JSXX.get("RYRQ1") + "-" + JSXX.get("CYRQ1") + "(" + jsts + "天)";

            } else {
                if (JSXX.get("KSRQ") != null) {
                    jsts = BUHISUtil.loadDischargeDays(
                            JSXX.get("KSRQ") + "",
                            sdftime.format(new Date())
                    );
                }
                jstss = JSXX.get("KSRQ1") + "-" + "至今(" + jsts + "天)";
            }*/
            response.put("jsts", jsts);//中途结算住院天数
            response.put("zytss", jstss);//中途结算住院天数

            BigDecimal hjje = BigDecimal.ZERO;
            if (JSXX.get("HJJE") != null) {
                hjje = new BigDecimal(JSXX.get("HJJE") + "");
            }
            response.put("FYHJ", String.format("%1$.2f", hjje));//费用合计
            //查询出货币误差，计算出实收金额
            Map<String, Object> hosParam = new HashMap<String, Object>();
            hosParam.put("ZYH", JSXX.get("ZYH"));
            hosParam.put("JSCS", JSXX.get("JSCS"));
            Map<String, Object> HBWCInfo = imFkxxDao.queryDyfp(hosParam);
            BigDecimal realPayment = new BigDecimal("0.00");
            realPayment = realPayment.add(new BigDecimal(hjje + ""));
            if (null != HBWCInfo) {
                realPayment = realPayment.subtract(new BigDecimal(HBWCInfo.get("HBWC") + ""));
            }
            realPayment = realPayment.subtract(new BigDecimal(JSXX.get("JMJE").toString()));

            String realPayment_string = String.format("%1$.2f", realPayment);
            response.put("SSJE", realPayment_string + "");//发票上实收金额
            //response.put("SSJE", realPayment+"");//发票上实收金额
            //返回付款方式
            //2021-04-28 发票内无付款方式名称字段
//            Map<String, Object> paymentWay = imFkxxDao.queryPaymentWay(hosParam);
//            response.put("FKMC", paymentWay.get("fkmc"));

            BigDecimal zfje = new BigDecimal(MapAttributeUtils.getDouble("ZFJE", JSXX));//自费金额(自理自费)
            response.put("ZFJE", String.format("%1$.2f", zfje));
            response.put("HJDX", RmbUtil.toRMBUpper(String.valueOf(hjje)));
            BigDecimal jkhj = new BigDecimal(parseDouble(JSXX.get("JKHJ")) + "");//预缴款
            response.put("JKHJ", String.format("%1$.2f", jkhj));
            //减免金额
            BigDecimal jmje = new BigDecimal(parseDouble(JSXX.get("JMJE")) + "");
            response.put("JMJE", String.format("%1$.2f", jmje));

            if (zfje.subtract(jmje).subtract(jkhj).compareTo(BigDecimal.ZERO) == 1) {
                response.put("CYBJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj))));//补缴
                response.put("BJXJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj))));//补缴现金
                response.put("CYTK", "0.0");//出院退款
                response.put("TKXJ", "0.00");//退款现金

            } else {
                response.put("CYBJ", "0.00");
                response.put("JSMXXJ", "0,00");
                response.put("CYTK", String.format("%1$.1f", (zfje.subtract(jmje).subtract(jkhj)).negate()));//出院退款
                StringBuffer hqlTKXJ = new StringBuffer(
                        "select -sum(a.FKJE) as TKXJ from IM_FKXX a,IM_JSMX b,PUB_FKFS c where a.ZYH=b.ZYH and a.JSCS=b.JSCS and a.FKFS=c.FKFS and c.HBWC=0  and a.ZYH = :ZYH and a.JSCS = :JSCS");
                Map<String, Object> parametersTKXJ = new HashMap<String, Object>();
                parametersTKXJ.put("ZYH", JSXX.get("ZYH"));
                parametersTKXJ.put("JSCS", JSXX.get("JSCS"));
                Map<String, Object> TKXJ_map = imFkxxDao.queryDyfpFkje(parametersTKXJ);
                if (TKXJ_map != null) {
                    response.put("TKXJ", String.format("%1$.2f", TKXJ_map.get("TKXJ")));//退款现金
                } else {
                    response.put("TKXJ", 0);//退款现金
                }

            }


            /******商保******/
            //查询商保的病人性质
            String BRXZ_SYBX = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.BRXZ_SYBX);
            if (StrUtil.isNotBlank(BRXZ_SYBX) && BRXZ_SYBX.equals(JSXX.get("BRXZ") + "")) {
                realPayment = realPayment.subtract(new BigDecimal(parseDouble(JSXX.get("LPJE")) + ""));
                response.put("SSJE", realPayment + "");//发票上实收金额
                BigDecimal lpje = new BigDecimal(parseDouble(JSXX.get("LPJE")) + "");//预缴款

                response.put("CYBJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj).subtract(lpje))));//补缴
                response.put("LPJE", String.format("%1$.2f", (lpje)));//补缴
            }
            //补缴
            response.put("BRXZ", JSXX.get("BRXZ") + "");


            /******医保性质*****/
            //bjh
            response.put("B_KPRQ", JSXX.get("YYYY") + "年" + JSXX.get("MM") + "月" + JSXX.get("DD") + "日");
            response.put("B_HYFL", JSXX.get("RYYY") + "年" + JSXX.get("RYMM") + "月" + JSXX.get("RYDD") + "日 到" +
                    JSXX.get("CYYY") + "年" + JSXX.get("CYMM") + "月" + JSXX.get("CYDD") + "日" + "住院天数 " + jsts + " 天");
            //response.put("B_TS", "住院天数 "+jsts+" 天");
            response.put("B_FPHM", JSXX.get("FPHM"));
            response.put("B_BRXM", JSXX.get("XM") + "");//姓名
            response.put("B_ZYHM", JSXX.get("XLH") + "");//住院号码
            //response.put("B_ZYHM", "000283");//住院号码
            response.put("B_RYLB", JSXX.get("RYLB") + "");//人员类别
            response.put("B_YBKH", JSXX.get("YBKH"));//就诊卡号
            response.put("B_HJDX", RmbUtil.toRMBUpper(String.valueOf(hjje)));
            response.put("B_FYHJ", String.format("%1$.2f", hjje));//费用合计
            response.put("B_SKDW", user.getHospitalName());
            response.put("B_SKY", JSXX.get("RYBH") + "");

            response.put("XJZF", "0");
            response.put("GRZHZF", "0"); //个人账户支付
            response.put("YBTCZF", "0"); //医保统筹支付
            response.put("FJZF", "0");//附加支付

            response.put("XJZF_FLZF", "0"); //分类自负
            response.put("XJZF_ZIFU", "0");//自负
            response.put("XJZF_ZIFEI", "0");//自费
            response.put("DNZHYE", "0");
            response.put("LNZHYE", "0");
            response.put("LSH", "");

            Map<String, Object> map_Yjj = new HashMap<String, Object>();
            map_Yjj.put("ZYH", JSXX.get("ZYH"));
            // map_Yjj.put("JSCS", Integer.parseInt(JSXX.get("JSRQ")+""));
            map_Yjj.put("jsrq", JSXX.get("JSRQ") + "");
            logger.info("住院发票打印" + map_Yjj);
            List<Map<String, Object>> list_zyyjj = imTbkkDao.queryYjje(map_Yjj);
            response.put("ZYYJJ", String.format("%1$.2f", list_zyyjj.get(0).get("JKJE")));  //住院预缴金额

            /////


            String ybxz = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.YBXZ);
            String SJXZ = JSXX.get("SJXZ") + "";
            //查询病人的实际性质
            // Integer sjxz=pubBrxzDao.getSjxzByBrxz(MapAttributeUtils.getInteger("BRXZ",JSXX));

            if (SJXZ.equals(ybxz)) {
                parameters.clear();
                parameters.put("ZYH", JSXX.get("ZYH") + "");
                parameters.put("JSRQ", "" + JSXX.get("YYYY") + JSXX.get("MM") + JSXX.get("DD") + "%");
                List<Map<String, Object>> ybxxList = imFeeFymxDao.queryYbxx(parameters);

                Map<String, Object> ybxx = ybxxList.get(0);
                BigDecimal bigd = new BigDecimal(Double.valueOf(ybxx.get("XJZF") + ""));
                response.put("XJZF", bigd.setScale(1, BigDecimal.ROUND_HALF_DOWN) + "");
                bigd = bigd.setScale(1, BigDecimal.ROUND_HALF_DOWN);
                response.put("XJZF", String.format("%1$.2f", bigd));
                if (jkhj.compareTo(bigd) == 1) {
                    response.put("CYTK", jkhj.subtract(bigd));
                }
                response.put("GRZHZF", String.format("%1$.2f", Double.valueOf(ybxx.get("GRZHZF") + ""))); //个人账户支付
                response.put("YBTCZF", String.format("%1$.2f", Double.valueOf(ybxx.get("TCZF") + ""))); //医保统筹支付
                response.put("FJZF", String.format("%1$.2f", Double.valueOf(ybxx.get("FJZF") + "")));//附加支付

                response.put("XJZF_FLZF", String.format("%1$.2f", Double.valueOf(ybxx.get("FLZF") + ""))); //分类自负
                response.put("XJZF_ZIFU", String.format("%1$.2f", Double.valueOf(ybxx.get("ZF") + "")));//自负
                response.put("XJZF_ZIFEI", String.format("%1$.2f", Double.valueOf(ybxx.get("FYBZF") + "")));//自费
//				response.put("XJZF",String.format("%1$.1f",Double.valueOf(ybxx.get("XJZF")+"")));
                response.put("DNZHYE", String.format("%1$.2f", Double.valueOf(ybxx.get("DNZHYE") + "")));
                response.put("LNZHYE", String.format("%1$.2f", Double.valueOf(ybxx.get("LNZHYE") + "")));
                response.put("LSH", ybxx.get("ZXLSH") + "");
                //response.put("LSH","2001020002450270");

                response.put("SHBZK", ybxx.get("SHBZK") + "");
            }

            //如果是预付卡支付 返回预付卡信息
            List<Map<String, Object>> list = opCzjlService.queryCzjlDyfp(fphm);
            if (list.size() > 0) {
                response.put("YFKYE", list.get(0).get("YE"));
                response.put("YFKZF", list.get(0).get("AMOUNT"));
            }
            String zyjsdyjmc = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.ZYJSDYJMC);
            if (StrUtil.isNotBlank(zyjsdyjmc)) {
                response.put(BUHISSystemArgument.ZYJSDYJMC, zyjsdyjmc);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return ReturnEntityUtil.success(response);
    }


}
