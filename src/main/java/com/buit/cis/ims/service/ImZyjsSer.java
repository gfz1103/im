package com.buit.cis.ims.service;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.buit.aop.lock.Locked;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.enums.CypbEnum;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImJszf;
import com.buit.cis.ims.model.ImZyjs;
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
 * ??????_????????????<br>
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
     * ????????????-??????????????????
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[req.zyh]"})
    public ReturnEntity<String> patientBalanceAccountsCancel(UpdateSettleAccountsReq req, SysUser sysUser, String ip) {
        //?????????????????????
        Timestamp now = DateUtils.getNow();
        //??????????????????
        ImHzry imHzry = imHzryDao.getById(req.getZyh());
        //?????????????????????????????????
        ImZyjs imzyjs = zyjsPreService.getLastImZyjs(req.getZyh());

        //?????????????????????
        //????????????????????????????????????????????????
        if (imzyjs == null || (imzyjs != null && !imzyjs.getJscs().equals(req.getJscs()))) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00070");
        }
        //???????????????????????????????????????'????????????'??????????????????????????????????????????'????????????'
        if (!(5 == imzyjs.getJslx() && CypbEnum.NORMAL_DISCHARGE.equals(imHzry.getCypb())
                || (1 == imzyjs.getJslx() && CypbEnum.IN_HOSPITAL.equals(imHzry.getCypb())))) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00021");
        }

//        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(req);
        //????????????
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
        //????????????

        //?????????????????????
        imzyjs.setZfrq(now);
        imzyjs.setZfgh(sysUser.getUserId().toString());
        imZyjsDao.updateFpzf(imzyjs);

        //2021.1.12 ?????????????????????
        zyjsPreService.imTbkkSettleCancelHandler(imzyjs);

        //????????????????????????
        //???????????????????????????????????????
        ImZyjs lastJS = zyjsPreService.getLastImZyjs(req.getZyh());
        //????????????????????????????????????????????????????????????????????????????????????
        imHzry.setJsrq(lastJS == null ? imHzry.getRyrq() : lastJS.getZzrq());
        if (5 == imzyjs.getJslx()) {//????????????????????????????????????"????????????"???????????????????????????????????????????????????
            imHzry.setCypb(CypbEnum.CER_DISCHARGE.getValue());
        }
        imHzryDao.updateCypbAndJsrq(imHzry);

        //??????IM_FEE_FYMX???????????????
        imFeeFymxDao.updatZyjszf(imzyjs);

        //???????????????????????????
        ImJszf zyzfParam = new ImJszf();
        zyzfParam.setZyh(req.getZyh());
        zyzfParam.setJgid(sysUser.getHospitalId());
        zyzfParam.setJscs(req.getJscs());
        zyzfParam.setZfgh(sysUser.getUserId());
        zyzfParam.setZfrq(now);
        imJszfDao.insert(imzyjs);

        // ??????????????????
        /*Integer mzlb = opMzlbService.getMzlb(sysUser.getHospitalId(), ip);
        imZyjsParam.setMzlb(mzlb);*/

        // ????????????????????????????????????
        if (5 == imzyjs.getJslx() && imHzry.getBrch() != null) {
            ImCwsz cwsz = imCwszDao.getByPk(sysUser.getHospitalId(), imHzry.getBrbq(), imHzry.getBrch());
            if (cwsz.getZyh() != null) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00079");
            }
            cwsz.setZyh(imHzry.getZyh());
            imCwszDao.updateCwfp(cwsz);
        }

        //jiangwei 2020.12.31 ??????????????????????????????????????????
        // ??????????????????
        /*String lsBrch = "";
        Map<String, Object> imHcmxParameters = new HashMap<String, Object>();
        imHcmxParameters.put("zyh", zyh);
        //???????????????????????????
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
        // modify by yangl ???????????????????????????
        if (lsBrch.length() > 0 && "5".equals(body.get("jsbs") + "")) {
            Map<String, Object> imCwszParameters = new HashMap<String, Object>();
            imCwszParameters.put("brch", lsBrch);
            imCwszParameters.put("jgid", jgid);
            //??????id??????????????????????????????
            Map<String, Object> imCwszZyh = imCwszDao.queryZyhByJgidAndBrch(imCwszParameters);
            long llZyh = 0;
            if (imCwszZyh != null) {
                if (imCwszZyh.get("zyh") != null) {
                    llZyh = MapAttributeUtils.getInteger("zyh",imCwszZyh);
                }
            }
            if (llZyh == 0) {
                imHzryParameters.put("brch", lsBrch);
                //????????????id????????????????????????????????????
                imCwszDao.updateZyhByBrchAndJgid(imHzryParameters);
                //????????????????????????id????????????????????????
                 imHzryDao.updateBrchByZyhAndJgid(imHzryParameters);


                Map<String, Object> imCwszParameters1 = new HashMap<String, Object>();
                imCwszParameters1.put("brch", brch);
                imCwszParameters1.put("jgid", jgid);
                imCwszParameters1.put("zyh", zyh);
                imCwszDao.updateZyhByBrchAndJgid(imCwszParameters1);
                //???????????????????????????id?????????????????????????????????
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
        //???????????????,?????????????????????
        /*if(body.containsKey("isYb") && "1".equals(body.get("isYb").toString())){
            MedicareInterface model=new HZSMedicareModel(dao);
            Map<String,Object> map_body=new HashMap<String,Object>();
            map_body.put("zyh", zyh);
            map_body.put("jscs", jscs);
            model.updateYbzyjsxx(map_body);
        }*/
        //?????????????????????
        String fphm = imzyjs.getFphm();
        //???????????????????????????????????????
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
            //??????????????????
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
        //?????????????????????
        /*if(!(Constants.BRXZ_SYBX+"").equals(body.get("BRXZ")+"")){
            //????????????????????????????????????????????????????????????????????????Tbl_Cust_DocMaster??????????????????
            Map<String, Object> fphmParam = new HashMap<String, Object>();
            fphmParam.put("DocumentNr", body.get("ZYHM"));

            String ZZSFPDY = "";
            PublicService publicService = new PublicService();
            Map<String, Object> Pamargs  = publicService.doLoadThisComputerArgs(Param.instance().put("names", "ZZSFPDY"), res, dao, ctx);
            ZZSFPDY = StrUtil.null2Str(Pamargs.get("ZZSFPDY"));
            if(ZZSFPDY.equals("1")){ //?????????????????????
                dao.doSqlUpdate("delete from Tbl_Cust_DocMaster where DocumentNr=:DocumentNr", fphmParam);
                // ????????????????????????
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
     * ????????????-??????????????????(?????????)
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
     * ??????????????????????????????????????????????????????
     *
     * @param zyh
     * @param user
     * @return
     */
    public ReturnEntity<BalanceAccountsSettleResp> queryBalanceAccountsSettle(Integer zyh, String jslx, String jsrq, String carddata, String cardtype, String accountattr, String ip, SysUser user, String ksrq, String zzrq, String ybjsksrq, String gsrdh) throws ParseException {
        imDrugsOutRangeLogSer.outRangeDrugBalance(zyh);

        BalanceAccountsSettleResp balanceAccountsSettleResp = new BalanceAccountsSettleResp();
        ImHzry imHzry = imHzryDao.getById(zyh);
        PubBrxzOut req = new PubBrxzOut();
        req.setSjxz(1000);
        List<PubBrxzOut> brxzList = pubBrxzSer.findByEntity(req);
        List<Integer> zfxzList = brxzList.stream().map(PubBrxzOut::getBrxz).collect(Collectors.toList());
        DecimalFormat df = new DecimalFormat("#.00");
        //????????????
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
            long l = shybSi51Service.countJs(zyh, jscs);//????????????????????????????????????
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
            //??????????????????
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
            //??????
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
                BigDecimal ybjsfwfyze = new BigDecimal(xxtMap.get("ybjsfwfyze") + "");  //??????????????????????????????
                BigDecimal fybjsfwfyze = new BigDecimal(xxtMap.get("fybjsfwfyze") + "");  //?????????????????????????????????
                BigDecimal flzf = new BigDecimal(xxtMap.get("ybjsfwfyze") + "")  //????????????
                        .subtract(new BigDecimal(xxtMap.get("totalexpense") + ""));
                BigDecimal ybzf = new BigDecimal(xxtMap.get("fjdxjzfs") + "")
                        .add(new BigDecimal(xxtMap.get("tcdxjzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("qfdxjzfs") + "")); //??????
                BigDecimal grzhzf = new BigDecimal(xxtMap.get("fjdzhzfs") + "")
                        .add(new BigDecimal(xxtMap.get("qfdzhzfs") + ""))
                        .add(new BigDecimal(xxtMap.get("tcdzhzfs") + ""));  //??????????????????
                BigDecimal tczf = new BigDecimal(xxtMap.get("tczfs") + ""); //????????????
                BigDecimal fjzf = new BigDecimal(xxtMap.get("fjzfs") + ""); //????????????
                BigDecimal curaccountamt = new BigDecimal(xxtMap.get("curaccountamt") + ""); //??????????????????
                BigDecimal hisaccountamt = new BigDecimal(xxtMap.get("hisaccountamt") + ""); //??????????????????

                balanceAccountsSettleResp.setYbjsfwfyze(ybjsfwfyze);
                balanceAccountsSettleResp.setFybjsfwfyze(fybjsfwfyze);
                balanceAccountsSettleResp.setFlzf(flzf);
                balanceAccountsSettleResp.setYbzf(ybzf);
                balanceAccountsSettleResp.setGrzhzf(grzhzf);
                balanceAccountsSettleResp.setTczf(tczf);
                balanceAccountsSettleResp.setFjzf(fjzf);
                balanceAccountsSettleResp.setCuraccountamt(curaccountamt);
                balanceAccountsSettleResp.setHisaccountamt(hisaccountamt);
                //?????????????????????????????????????????????????????????
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
        //??????
        BeanUtils.copyProperties(imHzry, balanceAccountsSettleResp);

        ReturnEntity<PatientBalanceAccountsBaseInfoResp> resp = imHzrySer.queryPatientBalanceAccountsBaseInfo(zyh, user.getHospitalId(), ksrq, zzrq, personspectag, jslx, zfje + "");
        if (zfxzList.contains(imHzry.getBrxz())) {//????????????
            if (resp.getData() != null) {

                // CardPatientCostBaseInfoJe cardPatientCostBaseInfoJe= imFeeFymxDao.queryPatientCost(zyh,user.getHospitalId());
                //?????????
                balanceAccountsSettleResp.setZfy(resp.getData().getZyf().setScale(2, BigDecimal.ROUND_HALF_UP));
                //????????????
                balanceAccountsSettleResp.setZfje(resp.getData().getZfje().setScale(2, BigDecimal.ROUND_HALF_UP));
                //??????????????????
                balanceAccountsSettleResp.setYjk(imTbkkSer.queryJkje(zyh));
                //??????????????????
                balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP));
                //????????????
                balanceAccountsSettleResp.setJmje(resp.getData().getZkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        } else {
            balanceAccountsSettleResp.setZfy(resp.getData().getZyf().setScale(2, BigDecimal.ROUND_HALF_UP));
            balanceAccountsSettleResp.setZfje(zfje);
            balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            // balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP).add(qtys));
            balanceAccountsSettleResp.setYjk(imTbkkSer.queryJkje(zyh));
            //????????????
            balanceAccountsSettleResp.setJmje(resp.getData().getZkje().setScale(2, BigDecimal.ROUND_HALF_UP));
            balanceAccountsSettleResp.setJzdyh(jzdyh);
            balanceAccountsSettleResp.setJssqxh(jssqxh);
        }
        //???????????????????????????
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
     * ????????????-??????????????????
     *
     * @return
     */
    @Locked(value = {"saveAccountMoney_#[req.zyh]"})
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> saveSettleAccounts(SaveSettleAccountsReq req, SysUser sysUser, String ip) {
        //????????????????????????
        Timestamp now = DateUtils.getNow();
        logger.info("??????????????????:" + "????????????-" + DateUtils.formatToDateTime(now) + ", req-" + JSONObject.toJSONString(req));
        Integer jgid = sysUser.getHospitalId();
        Integer userId = sysUser.getUserId();
        //?????????????????????????????????
        Integer mzlb = opMzlbService.getMzlb(sysUser.getHospitalId(), ip);
        if (mzlb == null) {
            throw BaseException.create("ERROR_REG_0070", new String[]{ip});
        }
        //??????VIP
        Boolean isVIP = zyjsPreService.isVIP(jgid, String.valueOf(req.getBrxz()));
        //??????????????????
        String sjxzs = zyjsPreService.getGrandBrxz(String.valueOf(req.getBrxz()));
        //??????????????????
        ImHzry imHzry = imHzryDao.getById(req.getZyh());
        //????????????????????????
        ImZyjs lastJs = zyjsPreService.getLastImZyjs(req.getZyh());

        if (1 == req.getJslx() &&
                req.getZfhj().doubleValue() * 1 == 0 &&
                req.getJkhj().doubleValue() * 1 == 0 &&
                !"6021".equals(sjxzs) &&
                23 != req.getBrxz()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00068");
        }
        //??????????????????
        if (5 == req.getJslx() && 1 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00023");
        } else if (1 == req.getJslx() && 0 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00071");
        }
        //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        //?????????????????????????????????????????????????????????????????????
        if (req.getJscs() != imHzry.getJscs()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00070");
        }

        //????????????????????????, ???????????????????????????????????????????????????????????????????????????
        Timestamp ksrq = DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getKsrq());
        Timestamp zzrq = DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, req.getZzrq());
        if (zzrq.after(now)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00072");
        }
        boolean isOutRange = imFeeFymxDao.isOutOfRange(req.getZyh(), req.getCzsj(), req.getZzrq(), 5 == req.getJslx());
        if (isOutRange) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00081");
        }
        //????????????????????????????????????
        if (ksrq.compareTo(zzrq) > 0) {
            throw BaseException.create("ERROR_OPGD_0007");
        }
        //????????????????????????????????????????????????????????????
        if (lastJs != null && lastJs.getZzrq().compareTo(ksrq) > 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00075");
        }
        //??????????????????
        BigDecimal accountLeft = imTbkkSer.queryJkje(req.getZyh());
        if (req.getJkhj().compareTo(accountLeft) != 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00073");
        }

        //????????????????????????
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
        // 2021.1.7 jiangwei ?????????????????? ?????????????????????
            /*//????????????
            if ("4".equals(map_jsxx.get("jslx") + "")) {
                  // ???IM_TBKK??????????????????
                *//*Map<String, Object> imTbkkParameters = new HashMap<String, Object>(16);
                imTbkkParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imTbkkParameters.put("zyh", zyh);
                imTbkkParameters.put("jgid", jgid);
                imTbkkDao.updateJscs(imTbkkParameters);*//*

                // ???IM_FEE_FYMX??????????????????
                Map<String, Object> imFeeFymxParameters = new HashMap<String, Object>(16);
                imFeeFymxParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imFeeFymxParameters.put("zyh", zyh);
                imFeeFymxParameters.put("jgid", jgid);
                imFeeFymxDao.updateJscs(imFeeFymxParameters);

                // ???IM_HCMX??????????????????
                imHcmxDao.updateJscs(imFeeFymxParameters);

                // ????????????????????????IM_FEE_FYMX_JS
                Map<String, Object> imFeeFymxJsParameters = new HashMap<String, Object>(16);
                imFeeFymxJsParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imFeeFymxJsParameters.put("zyh",
                        Integer.parseInt(map_jsxx.get("zyh") + ""));
                imFeeFymxJsDao.addJs(imFeeFymxJsParameters);

                // ??????????????????
                Map<String, Object> zyCwsZparameters = new HashMap<String, Object>(16);
                zyCwsZparameters.put("jgid", jgid);
                zyCwsZparameters.put("zyh", Long.parseLong(map_jsxx.get("zyh") + ""));

                Map<String, Object> imHzryParameters = new HashMap<String, Object>(16);
                imHzryParameters.put("zzrq", imZyjs.get("zzrq"));
                imHzryParameters.put("ujscs", (Integer) map_jsxx.get("jscs") + 1);
                imHzryParameters.put("zyh", zyh);
                imHzryParameters.put("jgid", jgid);
                // ???????????????????????? ????????????????????????

                imHzryDao.updateZyjs(imHzryParameters);
                imCwszDao.tc(zyCwsZparameters);

                // ????????????????????????????????????
                Map<String, Object> imFkxx = new HashMap<String, Object>(16);
                imFkxx.put("fkfs", map_jsxx.get("fkfs"));
                imFkxx.put("fkje", map_jsxx.get("ysje"));
                imFkxx.put("jgid", jgid);
                imFkxx.put("zyh", Long.parseLong(map_jsxx.get("zyh") + ""));
                imFkxx.put("jscs", (Integer) map_jsxx.get("jscs") + 1);
                imZyjsDao.insert(imZyjs);
                // ???????????????(IM_JSMX)
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

        //????????????????????????
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
        //???????????????????????????
        imYgpjSer.setBillNumber(1, req.getFphm(), sysUser.getUserId(), sysUser.getHospitalId());

        // 2021.1.6 jiangwei ??????????????????
        zyjsPreService.imTbkkSettleHandler(imZyjs);

        // ???IM_FEE_FYMX??????????????????
        // imFeeFymxDao.updateJscs(imZyjs);  //??????????????????ybapi??????

        // ????????????????????????IM_FEE_FYMX_JS
//        imFeeFymxJsDao.addJs(imZyjs);

        // ????????????????????????
        if (5 == req.getJslx()) {
            imHzryDao.updateCyjs(imZyjs);
            //??????????????????????????????????????????????????????????????????
            if (zyjsPreService.isStayBed(imZyjs.getZyh())) {
                imCwszDao.tc(imZyjs);
                imHcmxDao.updateJscsAndJsrq(imZyjs);
            }
        } else if (1 == req.getJslx()) {
            imHzryDao.updateZtjs(imZyjs);
        }


        //??????????????????
        zyjsPreService.imFkxxSettleHandler(req, sysUser, ip, imHzry.getBrid());
        //2021.1.7 jiangwei ?????????????????? ??????????????????pos??????
            /*Map<String, Object> pos = (Map<String, Object>) map_jsxx.get("pos");
            // ???????????????POS???(?????????????????????)?????????pos???????????????
            if (("8".equals(fffs) || "17".equals(fffs) || "27".equals(fffs)) && "1".equals(pos.get("success"))) {
                Map<String, Object> posInfo = MapAttributeUtils.upcaseKeys(pos);
                // posInfo.put("ID", id);
                posInfo.put("jysj", new Date());
                posInfo.put("jylb", pos.get("TransType"));
                posInfo.put("fphm", req.getFphm());
                OpPosmx opPosmx = objectMapper.readValue(objectMapper.writeValueAsString(posInfo), OpPosmx.class);
                opPosmxService.insert(opPosmx);
            }*/

        // 2021.1.6 jiangwei ?????????????????? ?????????????????????????????????
            /*if ("1".equals(isgjy)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String cyrqString = sdf.format(cyrq);


                Map<String, Object> imFeeFymxGjy = new HashMap<String, Object>(16);
                imFeeFymxGjy.put("ujscs", jscs + 1);
                imFeeFymxGjy.put("zyh", zyh);
                imFeeFymxGjy.put("jgid", jgid);
                //IM_FEE_FYMX_GJY.put("FYRQ", CYRQ.get("CYRQ"));
                imFeeFymxGjy.put("fyrq", cyrqString);
                //????????????????????????????????????
                imFeeFymxDao.updateJsGjy(imFeeFymxGjy);
                //????????????????????????????????????
                imFeeFymxJsDao.updateJsGjy(imFeeFymxGjy);


            }*/
//            if(null!=fkxxwc){
//                res.put("jlxhwc", fkxxwc.get("JLXH"));
//            }
//            if(null!=fkxx){
//                res.put("jlxh", fkxx.get("JLXH"));
//            }

        //20211.6 jiangwei ?????????????????? ?????????????????????????????????????????????
            /*if (!("6024").equals(map_jsxx.get("brxz") + "")) {
                //*********************?????????*******************************
                String zzsFkfs = "6";
                if (null != map_jsxx.get("fkfs")) {
                    zzsFkfs = map_jsxx.get("fkfs") + "";
                } else if (!"".equals(fffs) && !"null".equals(fffs) && !"0".equals(fffs) && !"6".equals(fffs)) {
                    zzsFkfs = fffs;
                }

                //????????????????????????????????????
                List<Map<String, Object>> fkfslist = pubFkfsService.getZzsFkfs(zzsFkfs);
                //????????????????????????
                List<Map<String, Object>> sjxzList = pubBrxzSer.getSjxzList(map_jsxx.get("brxz").toString());
                String sjxz = sjxzList.get(0).get("sjxz") + "";
                if ("1000".equals(sjxz)) {

                }
                String zzsJzhm = "";
                String zzsBz2 = "";
                DecimalFormat df = new DecimalFormat("#.00");
                zzsJzhm = "?????????:" + map_jsxx.get("fphm") + "";

                //if(!"0.00".equals(OP_MZXX.get("XJJE")+"")){
                if ("1000".equals(sjxz)) {
                    zzsBz2 += "" + fkfslist.get(0).get("fkmc") + ":" + df.format(Double.parseDouble(map_jsxx.get("fyhj") + "")) + "???";
                }
                if (!"0.0".equals(map_jsxx.get("JMJE") + "") && !"0".equals(map_jsxx.get("jmje") + "")) {
                    zzsBz2 += ",??????:" + df.format(map_jsxx.get("jmje")) + "???";
                }

        *//*      Map<String,Object> invoiceInfo=new HashMap<String, Object>();
                invoiceInfo.put("jscs", (Integer) body.get("jscs")+1);
                invoiceInfo.put("zyh", zyh);
                invoiceInfo.put("invKind", 2);//???????????? 0???????????????????????????2????????????????????????
                invoiceInfo.put("documentNr", body.get("fphm"));//?????????
                invoiceInfo.put("documentDate", new Date());//????????????
                invoiceInfo.put("customerName", MapAttributeUtils.getString("brxm",body) + "     " +pubBrxz.getXzmc() + "     ??????: "+ imHzry.getYbkh());//????????????
                invoiceInfo.put("customerTaxNr", "");//????????????
                invoiceInfo.put("customerAddressTel", "");//??????????????????

                Map<String,Object> invoiceInfo=new HashMap<String, Object>(16);
                invoiceInfo.put("JSCS", (Integer) body.get("jscs")+1);
                invoiceInfo.put("ZYH", Integer.parseInt(body.get("zyh") + ""));
                invoiceInfo.put("InvKind", 2);//???????????? 0???????????????????????????2????????????????????????
                invoiceInfo.put("DocumentNr", body.get("fphm"));//?????????
                invoiceInfo.put("DocumentDate", new Date());//????????????
                invoiceInfo.put("CustomerName", StrUtil.null2Str(body.get("brxm")) + "     " +StrUtil.null2Str( map_brxz.get("XZMC")) + "     ??????: "+ StrUtil.null2Str(map_brry.get("YBKH")));//????????????
                invoiceInfo.put("CustomerTaxNr", "");//????????????
                invoiceInfo.put("CustomerAddressTel", "");//??????????????????
                //invoiceInfo.put("CustomerBankAccountNr", "");//??????????????????

                invoiceInfo.put("customerBankAccountNr",zzsJzhm);//??????????????????

                invoiceInfo.put("invoicer", "");//?????????
                invoiceInfo.put("checker", "");//?????????
                invoiceInfo.put("payee", userId);//?????????
                invoiceInfo.put("productUnit", "");//??????????????????
                invoiceInfo.put("taxRate", 0);//??????
                invoiceInfo.put("productTax", 0);//????????????
                invoiceInfo.put("discountValue", 0);//????????????
                invoiceInfo.put("discountTax", 0);//????????????
                invoiceInfo.put("machineNr", "");//????????????
                invoiceInfo.put("taxItem", "");//????????????
                invoiceInfo.put("goodsNoVer", "30.0");//???????????????
                invoiceInfo.put("goodsTaxNo", "3070202");//??????????????????
                invoiceInfo.put("taxPre", "1");//????????????????????????
                invoiceInfo.put("taxPreCon", "??????");//??????????????????????????????
                invoiceInfo.put("zeroTax", "1");//???????????????
                invoiceInfo.put("taxDeduction", 0);//?????????
                invoiceInfo.put("alternateKey", "");//???????????????

                invoiceInfo.put("zzsbzxx", zzsBz2);//???????????????2

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
        //????????????
        logger.info("?????????????????????????????????" + DateUtil.now());
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

            String accountattr = req.getAccountattr();//????????????1
            map.put("personspectag", accountattr.substring(3, 4));
            map.put("zyh", req.getZyh());//?????????2
            map.put("carddata", req.getCarddata());//??????3
            map.put("cardtype", req.getCardtype());//?????????4
                /*if(!"".equals(req.getGsrdh())){//???????????????5
                    map.put("persontype", "1"); //??????
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
            if (1 == req.getJslx()) {//????????????7
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
            map.put("jsksrq", jsksrq); //??????????????????8
            map.put("jsjsrq", jsjsrq); //??????????????????9
            map.put("zyts", req.getJsts()); //????????????10
            map.put("jzdyh", req.getJzdyh()); //???????????????11
            map.put("jssqxh", req.getJssqxh());//??????????????????12

            map.put("JSCS", req.getJscs() + 1);//????????????
            map.put("yllb", "S21");//S21??????  S24????????????
            map.put("jgdm", orgid);
            map.put("deptid", ybksdm);
            map.put("xsywlx", "1");
            map.put("ip", ip);
            map.put("jgid", sysUser.getHospitalId());
            map.put("ygdm", sysUser.getUserId());
            map.put("ygxm", sysUser.getUserName());
            logger.info("?????????????????????2???");
            SettleResultDTO sn52ResultDTO = offLineSettle.si52(map);
            logger.info("???????????????3???" + sn52ResultDTO);
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
        // ???????????????(IM_JSMX)
        Map<String, Object> imJsmxParameters = new HashMap<String, Object>(16);
        imJsmxParameters.put("UJSCS", req.getJscs() + 1);
        imJsmxParameters.put("ZYH", req.getZyh());
        if (false == isVIP) {
            imJsmxDao.insertJs(imJsmxParameters);
        } else {
            imJsmxDao.insertJsMx(imJsmxParameters);
        }
        logger.info("??????????????????:" + "????????????-" + DateUtil.now());
        //????????????
        return ReturnEntityUtil.success();
    }


    /**
     * ?????????????????????????????????
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
     * ??????????????????????????????
     *
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doQueryZyfpdy(SysUser user, String fphm) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> resp = new HashMap<>();

        int isVIP = 0;//?????????VIP??????
        SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer jgid = user.getHospitalId();
        //????????????
        Map<String, Object> JSXX = new HashMap<String, Object>();
        //????????????
        List<Map<String, Object>> SFXMS = new ArrayList<Map<String, Object>>();
        //????????????
        Map<String, Object> JKJES = new HashMap<String, Object>();
        //??????????????????
        Map<String, Object> YBJES = new HashMap<String, Object>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, Object> parameters2 = new HashMap<String, Object>();
        //??????????????????


        try {
            parameters.put("fphm", fphm);
            parameters.put("jgid", jgid);
            //????????????
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
                //??????VIP
                Boolean vipbrxz = zyjsPreService.isVIP(jgid, JSXX.get("BRXZ").toString());
                if (vipbrxz) {
                    isVIP = 1;
                }
            }
            parameters2.put("ZYH", JSXX.get("ZYH"));
            parameters2.put("JSCS", JSXX.get("JSCS"));
            parameters2.put("VIP", isVIP);
            //?????????????????????
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
            //?????????JSXX????????????????????????
            JSXX.put("HJJE", fyhj);
            //????????????
            //JKJES = dao.doLoad(jkxxHQL.toString(), parameters);
            JKJES = imTbkkDao.queryDyfpJkje(parameters);

            BigDecimal jkje = BigDecimal.ZERO;
            if (JKJES.get("JKJE") != null) {
                jkje = new BigDecimal(JKJES.get("JKJE") + "").negate();
            }
            response.put("ZYYJJ", String.format("%1$.2f", jkje));  //??????????????????1
            /*response.put("KSRQ", JSXX.get("KSRQ"));//????????????
            //?????????
            response.put("JSLX", JSXX.get("JSLX"));//????????????
            response.put("N", JSXX.get("YYYY") + "");
            response.put("Y", JSXX.get("MM") + "");
            response.put("R", JSXX.get("DD") + "");
            response.put("ZYHM", JSXX.get("XLH") + "");//????????????
            response.put("BRXM", JSXX.get("XM") + "");//??????
            response.put("BRXB", JSXX.get("BRXB") + "");//??????
            response.put("RYLB", JSXX.get("RYLB") + "");//????????????
            response.put("GZDW", JSXX.get("GZDW"));//????????????
            response.put("SYY", JSXX.get("SYY") + "");//?????????
            response.put("JSRQ", JSXX.get("JSRQ") + "");//???
            response.put("JSRQ1", JSXX.get("JSRQ1"));//??????????????????
            response.put("CSNY", JSXX.get("CSNY"));//????????????
            response.put("YBKH", JSXX.get("YBKH"));//????????????
            response.put("BRKS", JSXX.get("BRLS"));//????????????
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
                zYRQ = JSXX.get("RYRQ1") + "-" + JSXX.get("CYRQ1") + "(" + days + "???)";
            } else {
                if (JSXX.get("RYRQ") != null) {
                    days = BUHISUtil.loadDischargeDays(
                            JSXX.get("RYRQ") + "",
                            sdftime.format(new Date())
                    );
                }

                zYRQ = JSXX.get("RYRQ1") + "-" + "??????(" + days + "???)";
            }

            response.put("DAYS", days + "");
            response.put("ZYRQ", zYRQ + "");//????????????
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
                jstss = JSXX.get("RYRQ1") + "-" + JSXX.get("CYRQ1") + "(" + jsts + "???)";

            } else {
                if (JSXX.get("KSRQ") != null) {
                    jsts = BUHISUtil.loadDischargeDays(
                            JSXX.get("KSRQ") + "",
                            sdftime.format(new Date())
                    );
                }
                jstss = JSXX.get("KSRQ1") + "-" + "??????(" + jsts + "???)";
            }*/
            response.put("jsts", jsts);//????????????????????????
            response.put("zytss", jstss);//????????????????????????

            BigDecimal hjje = BigDecimal.ZERO;
            if (JSXX.get("HJJE") != null) {
                hjje = new BigDecimal(JSXX.get("HJJE") + "");
            }
            response.put("FYHJ", String.format("%1$.2f", hjje));//????????????
            //?????????????????????????????????????????????
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
            response.put("SSJE", realPayment_string + "");//?????????????????????
            //response.put("SSJE", realPayment+"");//?????????????????????
            //??????????????????
            //2021-04-28 ????????????????????????????????????
//            Map<String, Object> paymentWay = imFkxxDao.queryPaymentWay(hosParam);
//            response.put("FKMC", paymentWay.get("fkmc"));

            BigDecimal zfje = new BigDecimal(MapAttributeUtils.getDouble("ZFJE", JSXX));//????????????(????????????)
            response.put("ZFJE", String.format("%1$.2f", zfje));
            response.put("HJDX", RmbUtil.toRMBUpper(String.valueOf(hjje)));
            BigDecimal jkhj = new BigDecimal(parseDouble(JSXX.get("JKHJ")) + "");//?????????
            response.put("JKHJ", String.format("%1$.2f", jkhj));
            //????????????
            BigDecimal jmje = new BigDecimal(parseDouble(JSXX.get("JMJE")) + "");
            response.put("JMJE", String.format("%1$.2f", jmje));

            if (zfje.subtract(jmje).subtract(jkhj).compareTo(BigDecimal.ZERO) == 1) {
                response.put("CYBJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj))));//??????
                response.put("BJXJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj))));//????????????
                response.put("CYTK", "0.0");//????????????
                response.put("TKXJ", "0.00");//????????????

            } else {
                response.put("CYBJ", "0.00");
                response.put("JSMXXJ", "0,00");
                response.put("CYTK", String.format("%1$.1f", (zfje.subtract(jmje).subtract(jkhj)).negate()));//????????????
                StringBuffer hqlTKXJ = new StringBuffer(
                        "select -sum(a.FKJE) as TKXJ from IM_FKXX a,IM_JSMX b,PUB_FKFS c where a.ZYH=b.ZYH and a.JSCS=b.JSCS and a.FKFS=c.FKFS and c.HBWC=0  and a.ZYH = :ZYH and a.JSCS = :JSCS");
                Map<String, Object> parametersTKXJ = new HashMap<String, Object>();
                parametersTKXJ.put("ZYH", JSXX.get("ZYH"));
                parametersTKXJ.put("JSCS", JSXX.get("JSCS"));
                Map<String, Object> TKXJ_map = imFkxxDao.queryDyfpFkje(parametersTKXJ);
                if (TKXJ_map != null) {
                    response.put("TKXJ", String.format("%1$.2f", TKXJ_map.get("TKXJ")));//????????????
                } else {
                    response.put("TKXJ", 0);//????????????
                }

            }


            /******??????******/
            //???????????????????????????
            String BRXZ_SYBX = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.BRXZ_SYBX);
            if (StrUtil.isNotBlank(BRXZ_SYBX) && BRXZ_SYBX.equals(JSXX.get("BRXZ") + "")) {
                realPayment = realPayment.subtract(new BigDecimal(parseDouble(JSXX.get("LPJE")) + ""));
                response.put("SSJE", realPayment + "");//?????????????????????
                BigDecimal lpje = new BigDecimal(parseDouble(JSXX.get("LPJE")) + "");//?????????

                response.put("CYBJ", String.format("%1$.2f", (zfje.subtract(jmje).subtract(jkhj).subtract(lpje))));//??????
                response.put("LPJE", String.format("%1$.2f", (lpje)));//??????
            }
            //??????
            response.put("BRXZ", JSXX.get("BRXZ") + "");


            /******????????????*****/
            //bjh
            response.put("B_KPRQ", JSXX.get("YYYY") + "???" + JSXX.get("MM") + "???" + JSXX.get("DD") + "???");
            response.put("B_HYFL", JSXX.get("RYYY") + "???" + JSXX.get("RYMM") + "???" + JSXX.get("RYDD") + "??? ???" +
                    JSXX.get("CYYY") + "???" + JSXX.get("CYMM") + "???" + JSXX.get("CYDD") + "???" + "???????????? " + jsts + " ???");
            //response.put("B_TS", "???????????? "+jsts+" ???");
            response.put("B_FPHM", JSXX.get("FPHM"));
            response.put("B_BRXM", JSXX.get("XM") + "");//??????
            response.put("B_ZYHM", JSXX.get("XLH") + "");//????????????
            //response.put("B_ZYHM", "000283");//????????????
            response.put("B_RYLB", JSXX.get("RYLB") + "");//????????????
            response.put("B_YBKH", JSXX.get("YBKH"));//????????????
            response.put("B_HJDX", RmbUtil.toRMBUpper(String.valueOf(hjje)));
            response.put("B_FYHJ", String.format("%1$.2f", hjje));//????????????
            response.put("B_SKDW", user.getHospitalName());
            response.put("B_SKY", JSXX.get("RYBH") + "");

            response.put("XJZF", "0");
            response.put("GRZHZF", "0"); //??????????????????
            response.put("YBTCZF", "0"); //??????????????????
            response.put("FJZF", "0");//????????????

            response.put("XJZF_FLZF", "0"); //????????????
            response.put("XJZF_ZIFU", "0");//??????
            response.put("XJZF_ZIFEI", "0");//??????
            response.put("DNZHYE", "0");
            response.put("LNZHYE", "0");
            response.put("LSH", "");

            Map<String, Object> map_Yjj = new HashMap<String, Object>();
            map_Yjj.put("ZYH", JSXX.get("ZYH"));
            // map_Yjj.put("JSCS", Integer.parseInt(JSXX.get("JSRQ")+""));
            map_Yjj.put("jsrq", JSXX.get("JSRQ") + "");
            logger.info("??????????????????" + map_Yjj);
            List<Map<String, Object>> list_zyyjj = imTbkkDao.queryYjje(map_Yjj);
            response.put("ZYYJJ", String.format("%1$.2f", list_zyyjj.get(0).get("JKJE")));  //??????????????????

            /////


            String ybxz = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.YBXZ);
            String SJXZ = JSXX.get("SJXZ") + "";
            //???????????????????????????
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
                response.put("GRZHZF", String.format("%1$.2f", Double.valueOf(ybxx.get("GRZHZF") + ""))); //??????????????????
                response.put("YBTCZF", String.format("%1$.2f", Double.valueOf(ybxx.get("TCZF") + ""))); //??????????????????
                response.put("FJZF", String.format("%1$.2f", Double.valueOf(ybxx.get("FJZF") + "")));//????????????

                response.put("XJZF_FLZF", String.format("%1$.2f", Double.valueOf(ybxx.get("FLZF") + ""))); //????????????
                response.put("XJZF_ZIFU", String.format("%1$.2f", Double.valueOf(ybxx.get("ZF") + "")));//??????
                response.put("XJZF_ZIFEI", String.format("%1$.2f", Double.valueOf(ybxx.get("FYBZF") + "")));//??????
//				response.put("XJZF",String.format("%1$.1f",Double.valueOf(ybxx.get("XJZF")+"")));
                response.put("DNZHYE", String.format("%1$.2f", Double.valueOf(ybxx.get("DNZHYE") + "")));
                response.put("LNZHYE", String.format("%1$.2f", Double.valueOf(ybxx.get("LNZHYE") + "")));
                response.put("LSH", ybxx.get("ZXLSH") + "");
                //response.put("LSH","2001020002450270");

                response.put("SHBZK", ybxx.get("SHBZK") + "");
            }

            //???????????????????????? ?????????????????????
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
