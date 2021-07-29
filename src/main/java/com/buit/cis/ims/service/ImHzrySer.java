package com.buit.cis.ims.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.buit.aop.lock.Locked;
import com.buit.cis.bedresev.model.ImBedresev;
import com.buit.cis.bedresev.service.ImBedresevSer;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.response.CisJcsq01PrintResp;
import com.buit.cis.im.request.ImHzryEmrReq;
import com.buit.cis.im.response.ImHzryEmrResp;
import com.buit.cis.im.response.ImHzryResp;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.enums.CwxbEnum;
import com.buit.cis.ims.enums.CypbEnum;
import com.buit.cis.ims.enums.PjlxEnum;
import com.buit.cis.ims.model.*;
import com.buit.cis.ims.preService.HzryPreService;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.emr.model.EmrFileIndexModel;
import com.buit.emr.request.EmrFileIndexSealReq;
import com.buit.emr.service.EmrFileIndexService;
import com.buit.his.medinsuinterface.sh.dataitem.request.SaveDbRegisterReq;
import com.buit.his.shyb.source.entity.*;
import com.buit.his.shyb.source.service.impl.OfflineSettleService;
import com.buit.his.shyb.source.service.impl.ShybMzdbDbksService;
import com.buit.op.response.MpiBrda;
import com.buit.op.response.OpZydjResp;
import com.buit.op.service.OpMpiBrdaService;
import com.buit.op.service.OpMpiCardService;
import com.buit.op.service.OpMzlbService;
import com.buit.op.service.OpZydjService;
import com.buit.system.request.PubFkfsModel;
import com.buit.system.response.DicJbbmModel;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.*;
import com.buit.utill.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 住院_病人入院<br>
 *
 * @author ZHOUHAISHENG
 */
@Service
public class ImHzrySer extends BaseManagerImp<ImHzry, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImHzrySer.class);

    @Autowired
    private ImHzryDao imHzryDao;
    @DubboReference
    private DicJbbmService dicJbbmSer;
    @Autowired
    private ImYgpjSer imYgpjSer;
    @Autowired
    private ImZyjsDao imZyjsDao;

    @Autowired
    public ImCwszSer imCwszSer;
    @Autowired
    public ImRyzdSer imRyzdSer;

    @DubboReference
    public OpMpiBrdaService opMpiBrdaService;
    @DubboReference
    private OpZydjService opZydjService;

    @Autowired
    public ImTbkkSer imTbkkSer;
    @Autowired
    public ImFeeFymxSer imFeeFymxSer;
    @Autowired
    public ImFeeFymxDao imFeeFymxDao;
    @DubboReference
    public OpMzlbService opMzlbService;

    @DubboReference
    private SysXtcsSer sysXtcsSer;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    @Autowired
    public RedisFactory redisFactory;
    @DubboReference
    public OpMpiCardService opMpiCardService;
    @DubboReference
    public PubFkfsService pubFkfsService;
    @Autowired
    public CisHzyzDao cisHzyzDao;
    @Autowired
    public ImRcjlDao imRcjlDao;
    @Autowired
    public ImCwszDao imCwszDao;
    @Autowired
    public BUHISUtil BUHISUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @DubboReference
    private OfflineSettleService offLineSettle;
    @DubboReference
    private ShybMzdbDbksService ShybMzdbDbksService;
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    @Autowired
    public ZyjsPreService zyjsPreService;
    @Autowired
    public HzryPreService hzryPreService;
    @Autowired
    private ImBedresevSer imBedresevSer;
    @DubboReference
    private PubBrxzOutSer pubBrxzSer;
    @DubboReference
    private EmrFileIndexService emrFileIndexService;

    @Autowired
    private ImDrugsOutRangeLogSer imDrugsOutRangeLogSer;

    @Override
    public ImHzryDao getEntityMapper() {
        return imHzryDao;
    }

    /**
     * 查询病人管理列表
     *
     * @param patientListReq
     * @param user
     */
    public List<PatientListResp> findPatientList(PatientListReq patientListReq, SysUser user) {
        patientListReq.setJgid(user.getHospitalId());
        List<PatientListResp> patientListResps = imHzryDao.findPatientList(patientListReq);
        String now = DateUtils.getCurrentDateStr();
        Timestamp nowD = DateUtils.getNow();

        for (PatientListResp patientListResp : patientListResps) {
            patientListResp.setIsyb("0");
            String YBXZ = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBXZ");
            String ybsjxz = pubBrxzSer.getSjxzByBrxz(patientListResp.getBrxz()) + "";
            if(YBXZ.equals(ybsjxz)){
                patientListResp.setIsyb("1");
            }
            //计算住院天数
            String ryrq = DateUtils.toString(patientListResp.getRyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
            String cyrq = patientListResp.getCyrq() == null ? now : DateUtils.toString(patientListResp.getCyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
            patientListResp.setZyts(BUHISUtil.loadDischargeDays(ryrq, cyrq));
            //计算未结算天数
            if (!"1".equals(patientListResp.getCypb())) {
                Timestamp jsrq = patientListResp.getJsrq();
                jsrq = jsrq == null ? patientListResp.getRyrq() : jsrq;
                Timestamp jszzrq = patientListResp.getCyrq() == null ? nowD : patientListResp.getCyrq();
                patientListResp.setWjsts(DateUtils.getDaysDiff(jsrq, jszzrq));
            }
        }
        return patientListResps;


    }

    /**
     * 获取住院号码和病案号码，收据号码
     *
     * @param user
     * @return
     */
    public ReturnEntity<RydjPublicParamResp> getRydjPublicParam(SysUser user) {
        RydjPublicParamResp rydjPublicParamResp = new RydjPublicParamResp();

        rydjPublicParamResp.setBahm(hzryPreService.getBahm(user.getHospitalId()));

        //住院号病案号是否相同 系统标识 默认不启用;
        String iaAllowedTheSame = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.BAHMDYZYHM);
        if ("1".equals(iaAllowedTheSame)) {//启用病案号与住院号相同
            rydjPublicParamResp.setZyhm(hzryPreService.getZyhm(user.getHospitalId(), rydjPublicParamResp.getBahm()));
        } else if ("0".equals(iaAllowedTheSame)) {//不启用
            rydjPublicParamResp.setZyhm(hzryPreService.getZyhm(user.getHospitalId()));
        }

        //查询收据号码
        ImYgpj imYgpj = imYgpjSer.getbillnumber(PjlxEnum.code_2.getCode(), user.getHospitalId(), user.getUserId());
        if (imYgpj != null) {
            rydjPublicParamResp.setSjhm(imYgpj.getDqhm());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0003");
        }
        //查询住院默认付款方式
        PubFkfsModel pubFkfs = pubFkfsService.findZyfkfs();
        if (pubFkfs != null) {
            rydjPublicParamResp.setFkfs(pubFkfs.getFkfs());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00042");
        }
        return ReturnEntityUtil.success(rydjPublicParamResp);
    }

    /**
     * 获取留观号码和病案号码，收据号码
     *
     * @param user
     * @return
     */
    public ReturnEntity<RydjPublicParamResp> getLGRydjPublicParam(SysUser user) {

        RydjPublicParamResp rydjPublicParamResp = new RydjPublicParamResp();

        rydjPublicParamResp.setBahm(hzryPreService.getBahm(user.getHospitalId()));

        //住院号病案号是否相同 系统标识 默认不启用;
        String iaAllowedTheSame = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.BAHMDYZYHM);

        if ("1".equals(iaAllowedTheSame)) {//启用病案号与住院号相同
            rydjPublicParamResp.setZyhm(hzryPreService.getLghm(user.getHospitalId(), rydjPublicParamResp.getBahm()));
        } else if ("0".equals(iaAllowedTheSame)) {//不启用
            rydjPublicParamResp.setZyhm(hzryPreService.getLghm(user.getHospitalId()));
        }

        //查询收据号码
        ImYgpj imYgpj = imYgpjSer.getbillnumber(PjlxEnum.code_2.getCode(), user.getHospitalId(), user.getUserId());
        if (imYgpj != null) {
            rydjPublicParamResp.setSjhm(imYgpj.getDqhm());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0003");
        }
        //查询住院默认付款方式
        PubFkfsModel pubFkfs = pubFkfsService.findZyfkfs();
        if (pubFkfs != null) {
            rydjPublicParamResp.setFkfs(pubFkfs.getFkfs());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00042");
        }
        return ReturnEntityUtil.success(rydjPublicParamResp);
    }

    /**
     * 病人管理form回填
     *
     * @param zyh
     * @return
     */
    public ImHzryResp doLoadBrxx(Integer zyh) {
        ImHzry imHzry = imHzryDao.getById(zyh);
        ImHzryResp imHzryResp = new ImHzryResp();

        if (imCwszDao.queryBedNum(imHzry.getZyh()) <= 0) {
            //未找到床位
            imHzryResp.setBrch(null);
        }
        BeanUtils.copyProperties(imHzry, imHzryResp);
        if (imHzry != null) {
            DicJbbmModel dicJbbmParam = new DicJbbmModel();
            dicJbbmParam.setIcd10(imHzry.getRyzd());
            List<DicJbbmModel> dicJbbmList = dicJbbmSer.queryList(dicJbbmParam);
            if (dicJbbmList.size() > 0) {
                imHzryResp.setRyzdText(dicJbbmList.get(0).getJbmc());

            }
        }

        return imHzryResp;
    }

    /**
     * 病人性质转换
     *
     * @param zyh
     * @param brxz
     * @param cardno
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> doUpdateTransform(Integer zyh, Integer brxz, SysUser user, String cardno, String ip) {
        //查询病人住院登记信息
        try {
            ImHzry imHzry = imHzryDao.getById(zyh);
            //查询住院病人费用明细
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("zyh", zyh);
            parameters.put("jgid", user.getHospitalId());
            //查询住院病人明细列表
            List<Map<String, Object>> listImFeeFymx = imFeeFymxDao.queryZyfymx(parameters);
            listImFeeFymx = imFeeFymxSer.changeBrxz(imHzry.getBrxz(), brxz, listImFeeFymx);
            imHzry.setBrxz(brxz);
            imHzryDao.updateZybrBrxz(imHzry);
            for (int i = 0; i < listImFeeFymx.size(); i++) {
                Map<String, Object> record = listImFeeFymx.get(i);
                imFeeFymxDao.update(record);
            }

            //医保登记开始
            String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
            //上级病人性质
            String sjxzs = zyjsPreService.getGrandBrxz(String.valueOf(brxz));
            if ("0".equals(YBSYPB) && "6021".equals(sjxzs) && cardno != null) {//自费转医保登记

                Map<String, Object> map_ybksdm = dicKszdOutSer.getYbksdmzy(imHzry.getBrks());
                String ybksdm = map_ybksdm.isEmpty() ? "03" : map_ybksdm.get("ybks") + "";
                String orgid = "";

                Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
                if (map_orgid != null && !map_orgid.isEmpty()) {
                    orgid = map_orgid.get("ybjgid").toString();
                } else {
                    throw BaseException.create("ERROR_SHYB_0015");
                }

                Map<String, Object> rydj_map = new HashMap<String, Object>();
                String cardtype = "1";
                if (cardno.length() == 28) {
                    cardtype = "0";
                }
                rydj_map.put("carddata", cardno);
                rydj_map.put("cardtype", cardtype);
                rydj_map.put("zyh", zyh);
                rydj_map.put("deptid", ybksdm);
                rydj_map.put("orgid", orgid);
                rydj_map.put("ip", ip);
                rydj_map.put("jgid", user.getHospitalId());
                rydj_map.put("ygdm", user.getUserId());
                rydj_map.put("ygxm", user.getUserName());

                DicJbbmModel dicJbbmParam = new DicJbbmModel();
                dicJbbmParam.setIcd10(imHzry.getRyzd());
                dicJbbmParam.setZfbz(0);
                List<DicJbbmModel> dicJbbmList = dicJbbmSer.queryList(dicJbbmParam);
                List<DiagnosisInfo> zdnos = new ArrayList<DiagnosisInfo>();
                for (int i = 0; i < dicJbbmList.size(); i++) {
                    DiagnosisInfo zdno = new DiagnosisInfo();
                    zdno.setZdno(dicJbbmList.get(i).getIcd10() + "");
                    zdno.setZdmc(dicJbbmList.get(i).getJbmc() + "");
                    zdnos.add(zdno);
                }
                rydj_map.put("zdnos", zdnos);
                rydj_map.put("djtype", "3");
                rydj_map.put("djno", zyh);
                rydj_map.put("ryrq", imHzry.getYbjsrq().toString().replace(".0",""));

                SettleResultDTO resultDTO = offLineSettle.hospitalRegister(rydj_map);
                if (resultDTO.getCode() != 200) {
                    throw BaseException.create("ERROR_SHYB_0020", new String[]{resultDTO.getCode() + "", resultDTO.getMsg() + ""});
                } else {
                    String json = resultDTO.getData().toString();
                    Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
                    Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
                    String jzdyh = StrUtil.null2Str(xxnrMap.get("jzdyh"));
                    //imHzryDao.updateJzdyh(jzdyh, zyh);
                    cardno = cardno.length()==28?cardno.substring(0,10):cardno;
                    imHzryDao.updateZyybxx(jzdyh, cardno, zyh);
                }
            } else if ("0".equals(YBSYPB) && !"6021".equals(sjxzs) && cardno != null) {//医保转自费登记撤销
//                SJ21 sj21 = new SJ21();
                Map<String, Object> map = new HashMap<String, Object>();
//                Map<String, Object> res = new HashMap<String, Object>();
                String cardtype = "1";
                if (cardno.length() == 28) {
                    cardtype = "0";
                }
                String orgid = "";
                Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
                if (map_orgid != null && !map_orgid.isEmpty()) {
                    orgid = map_orgid.get("ybjgid").toString();
                }
                map.put("orgId", orgid);
                map.put("ip", ip);
                map.put("cardtype", cardtype);
                map.put("carddata", cardno);
                map.put("cxtype", "3");
                map.put("dbxm", "");
                map.put("ygdm", user.getUserId());
                map.put("ygxm", user.getUserName());
                map.put("jgid", user.getHospitalId());
                SettleResultDTO resultDTO = offLineSettle.hospitalRevocation(map);
                if (resultDTO.getCode() == 200) {
                    String json = resultDTO.getData().toString();
                    Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
                    Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
                    //返回字段待定  msg撤销成功
                    imHzryDao.updateZyzfxx(cardno, zyh);
                } else {
                    throw BaseException.create("ERROR_SHYB_0025");
                }
            }
            //医保结束

        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00031");
        }

        return ReturnEntityUtil.success();
    }

    /**
     * 病人性质转换
     *
     * @param oldBrxz
     * @param newBrxz
     * @return
     */
    public ReturnEntity<Boolean> checkSjxz(Integer oldBrxz, Integer newBrxz) {
        String oldSjxz = zyjsPreService.getGrandBrxz(String.valueOf(oldBrxz));
        String newSjxz = zyjsPreService.getGrandBrxz(String.valueOf(newBrxz));
        if (oldSjxz.equals(newSjxz)) {
            return ReturnEntityUtil.success(false);
        }
        return ReturnEntityUtil.success(true);
    }

    /**
     * 病人入院/留观登记保存
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"hospitalized_#[req.zyhm]"})
    public ReturnEntity<String> doSaveRrdj(RydjSaveReq req, SysUser user, String ip) {
        req.setJgid(user.getHospitalId());
        Integer mzlb = opMzlbService.getMzlb(user.getHospitalId(), ip); //门诊类别，区分医保线路

        Integer zyh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_HZRY);
        ImHzry imhzry = new ImHzry();
        BeanUtils.copyProperties(req, imhzry);
        imhzry.setJgid(user.getHospitalId());
        //重复入院（门诊号重复）
        List<ImHzry> imHzryMzhmList = imHzryDao.queryCfry(imhzry);
        if (imHzryMzhmList.size() > 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0004");
        }
        //病案号码重复
        hzryPreService.checkBahm(imhzry);
        //住院号码重复
        hzryPreService.chechZyhm(imhzry);

        //门诊调入处理
        String allowed = sysXtcsCacheSer.getCsz(user.getHospitalId(), SysXtcsCsmcCts.SFYXZJRY);
        OpZydjResp resp = null;
        if (req.getOpZydjDjid() != null) {
            resp = opZydjService.getById(req.getOpZydjDjid());
        }
        //只允许门诊调入
        if ("0".equals(allowed)) {
            //门诊未登记，或未提交
            if (resp == null || !"2".equals(resp.getSqzt())) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00078");
            } else {
                //修改门诊住院登记状态
                hzryPreService.updateSqzt(req.getOpZydjDjid());
                //拷贝门诊诊断
                hzryPreService.mzzdTransform(resp.getJzlsh(), zyh);
                imhzry.setJzlsh(resp.getJzlsh());
            }
        } else if ("1".equals(allowed)) {//允许自建档案
            if (req.getOpZydjDjid() != null && resp != null) {
                //修改门诊住院登记状态
                hzryPreService.updateSqzt(req.getOpZydjDjid());
                //拷贝门诊诊断
                hzryPreService.mzzdTransform(resp.getJzlsh(), zyh);
                imhzry.setJzlsh(resp.getJzlsh());
            }
        }
        //获取住院次数
        Integer zycs = imHzryDao.queryZycs(imhzry).size();
        //获取入院诊断
        DicJbbmModel dicJbbmParam = new DicJbbmModel();
        dicJbbmParam.setIcd10(req.getRyzd());
        dicJbbmParam.setZfbz(0);
        List<DicJbbmModel> dicJbbmList = dicJbbmSer.queryList(dicJbbmParam);
        DicJbbmModel dicJbbm = dicJbbmList.get(0);

        imhzry.setZyh(zyh);
        imhzry.setZycs(zycs + 1);
        imhzry.setMzlb(mzlb);
        imhzry.setMqzd(dicJbbm.getJbmc());
        imhzry.setIcd10bm(req.getRyzd());
        imhzry.setRyrq(DateUtils.parseToDateTime(req.getRyrq()));
        imhzry.setDjrq(DateUtils.getNow());
        imhzry.setCypb(CypbEnum.IN_HOSPITAL.getValue());
        imhzry.setJscs(0);
        if (req.getCsny() != null) {
            imhzry.setCsny(DateUtils.parseToDate(req.getCsny()));
            imhzry.setRynl((String) BUHISUtil.getPersonAge(imhzry.getCsny(), DateUtils.getNow()).get("ages"));
        }

        if ("1".equals(req.getIsGs())) {
            imhzry.setGsrdh(req.getGsrdh());
        } else {
            imhzry.setGsrdh("");
        }
        if ("1".equals(req.getIsDb())) {
            imhzry.setDbxm(req.getDbtype());
        } else {
            imhzry.setDbxm("");
        }
        imHzryDao.insert(imhzry);

        //增加入院诊断
        ImRyzd imRyzd = new ImRyzd();
        imRyzd.setZyh(zyh);
        imRyzd.setZdxh(dicJbbm.getJbxh());
        imRyzd.setZdlb(2);
        imRyzd.setJgid(user.getHospitalId());
        imRyzd.setZxlb(1);
        imRyzd.setRybqdm(1);
        imRyzd.setZgqk(6);
        imRyzd.setZdrq(new Timestamp(System.currentTimeMillis()));
        imRyzd.setPxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_RYZD_PXH));//新增时生成排序号
        imRyzdSer.insert(imRyzd);

        //修改床位预约登记id
        if (req.getCwyyid() != null) {
            ImBedresev imBedresev = new ImBedresev();
            imBedresev.setJlxh(req.getCwyyid());
            imBedresev.setRydjczr(user.getUserId());
            imBedresev.setRydjsj(new Timestamp(System.currentTimeMillis()));
            imBedresev.setDczt("3");
            imBedresevSer.updateRydj(imBedresev);
        }
        //床位分配
        if (StringUtils.isNotBlank(req.getBrch()) && req.getBrbq() != null) {
            ImCwsz imCwszParam = new ImCwsz();
            imCwszParam.setJgid(user.getHospitalId());
            imCwszParam.setBrch(req.getBrch());
            imCwszParam.setKsdm(req.getBrbq());
            List<ImCwsz> imCwszList = imCwszSer.findByEntity(imCwszParam);
            if (imCwszList.size() == 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0005");
            }
            ImCwsz cwsz = imCwszList.get(0);
            if (cwsz.getZyh() != null && cwsz.getZyh().intValue() != -1) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0006");
            }
            if (!CwxbEnum.code_3.getCode().equals(cwsz.getCwxb().toString()) && cwsz.getCwxb().intValue() != req.getBrxb()) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0008");
            }

            CwfpReq cwfp = new CwfpReq();
            cwfp.setZyh(zyh);
            cwfp.setBrch(req.getBrch());
            cwfp.setRyrq(req.getRyrq());
            cwfp.setBrks(req.getBrks());
            cwfp.setKsdm(req.getBrbq());
            imCwszSer.cwfp(cwfp, user);
        }

        //更新病人档案
        MpiBrda mpiBrda = new MpiBrda();
        BeanUtils.copyProperties(req, mpiBrda);
        mpiBrda.setBrid(req.getBrid());
        mpiBrda.setJdjg(user.getHospitalId());
        mpiBrda.setBrxz(req.getBrxz());
        mpiBrda.setCsny(Timestamp.valueOf(req.getCsny()));
        mpiBrda.setZjlx(req.getZjlx());
        mpiBrda.setAge(DateUtil.ageOfNow(mpiBrda.getCsny()));
        opMpiBrdaService.update(mpiBrda);

        //保存缴款相关信息
        // 若有缴款信息 保存缴款记录
        String jkxh = null;
        if (req.getJkfs() != null && StringUtils.isNotBlank(req.getJkje())) {
            ImTbkkSaveReq imTbkk = new ImTbkkSaveReq();
            BeanUtils.copyProperties(req, imTbkk);
            imTbkk.setZyh(zyh);
            imTbkk.setJklx(0);
            ReturnEntity rt = imTbkkSer.patientCostInsert(imTbkk, user, ip);
            jkxh = (String) rt.getData();
        }

        //操作完成后 病案号码跟住院号码/留观号码 做加一操作
        // 2021-06-11 jiangwei 病案，住院，留观 号码获取逻辑已变更，查询之后自动更新，无需保存后才更新
/*        SysXtcs gyXtcParam = new SysXtcs();
        gyXtcParam.setJgid(user.getHospitalId());
        gyXtcParam.setCsmc(SysXtcsCsmcCts.BAHM);
        gyXtcParam.setCsz(StringUtil.addOne(req.getBahm()));
        sysXtcsSer.updateZyParam(gyXtcParam);
        //住院业务
        if ("1".equals(req.getYwlx())) {
            gyXtcParam.setCsmc(SysXtcsCsmcCts.ZYHM);
        } else if ("2".equals(req.getYwlx())) {
            gyXtcParam.setCsmc(SysXtcsCsmcCts.LGHM);
        }

        gyXtcParam.setCsz(StringUtil.addOne(req.getZyhm()));
        sysXtcsSer.updateZyParam(gyXtcParam);*/

        //医保入院登记
        String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
        if ("1".equals(req.getIsYb()) && "0".equals(YBSYPB)) {
            Map<String, Object> map_ybksdm = dicKszdOutSer.getYbksdmzy(req.getBrks());
            String ybksdm = map_ybksdm.isEmpty() ? "03" : map_ybksdm.get("ybks") + "";
            String orgid = "";
            Map map_orgid = opMzlbService.getYbjgdm(req.getJgid(), ip);
            if (map_orgid != null && !map_orgid.isEmpty()) {
                orgid = map_orgid.get("ybjgid").toString();
            } else {
                throw BaseException.create("ERROR_SHYB_0015");
            }
            Map<String, Object> rydj_map = new HashMap<String, Object>();
            rydj_map.put("carddata", req.getCarddata());
            rydj_map.put("cardtype", req.getCardtype());
            rydj_map.put("zyh", zyh);
            rydj_map.put("deptid", ybksdm);
            rydj_map.put("orgid", orgid);
            rydj_map.put("ip", ip);
            rydj_map.put("jgid", user.getHospitalId());
            rydj_map.put("ygdm", user.getUserId());
            rydj_map.put("ygxm", user.getUserName());

            List<DiagnosisInfo> zdnos = new ArrayList<DiagnosisInfo>();
            for (int i = 0; i < dicJbbmList.size(); i++) {
                DiagnosisInfo zdno = new DiagnosisInfo();
                zdno.setZdno(dicJbbmList.get(i).getIcd10() + "");
                zdno.setZdmc(dicJbbmList.get(i).getJbmc() + "");
                zdnos.add(zdno);
            }
            rydj_map.put("zdnos", zdnos);
            rydj_map.put("djtype", "3");
            rydj_map.put("djno", zyh);
            rydj_map.put("ryrq", req.getRyrq());
            SettleResultDTO resultDTO = offLineSettle.hospitalRegister(rydj_map);
            if (resultDTO.getCode() != 200) {
                throw BaseException.create("ERROR_SHYB_0020", new String[]{resultDTO.getCode() + "", resultDTO.getMsg() + ""});
            } else {
                String json = resultDTO.getData().toString();
                Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
                Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
                String jzdyh = StrUtil.null2Str(xxnrMap.get("jzdyh"));
                imHzryDao.updateJzdyh(jzdyh, zyh);
            }
        }
        //医保end
        return ReturnEntityUtil.success(jkxh);

    }

    /**
     * 病人管理-病人住院信息修改
     *
     * @param imHzryReq
     * @param user
     * @return
     */
    public ReturnEntity<String> doUpdateBrry(ImHzryReq imHzryReq, SysUser user) {

        ImHzry imHzryParam = new ImHzry();
        imHzryParam.setZyh(imHzryReq.getZyh());
        List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzryParam);
        if (imHzryList.size() > 0) {
            ImHzry imHzry = imHzryList.get(0);
            //该病人已出院
            if (imHzry.getCypb().intValue() == 1) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00013");
            }
            MpiBrda mpiBrda = new MpiBrda();
            BeanUtils.copyProperties(imHzry, mpiBrda);
/*            MpiDemographicinfo mpiDemographicinfo=new MpiDemographicinfo();
            mpiDemographicinfo.setPersonname(imHzry.getBrxm());
            mpiDemographicinfo.setSexcode(imHzry.getBrxb().toString());
            mpiDemographicinfo.setBirthday(imHzry.getCsny());
            mpiDemographicinfo.setIdcard(imHzry.getSfzh());
            mpiDemographicinfo.setNationalitycode(imHzry.getJgdm().toString());
            mpiDemographicinfo.setNationcode(imHzry.getMzdm());
            mpiDemographicinfo.setMaritalstatuscode(imHzry.getHyzk().toString());
            mpiDemographicinfo.setWorkcode(imHzry.getZydm());
            mpiDemographicinfo.setWorkplace(imHzry.getGzdw());
            mpiDemographicinfo.setPhonenumber(imHzry.getJtdh());
            mpiDemographicinfo.setContact(imHzry.getLxrm());
            mpiDemographicinfo.setContactphone(imHzry.getLxdh());
            mpiDemographicinfo.setZipcode(imHzry.getHkyb());
            mpiDemographicinfo.setAddress(imHzry.getLxdz());
            mpiDemographicinfo.setEmpiid(mpiBrda.getEmpiid());
            mpiDemographicinfoDao.update(mpiDemographicinfo);*/

            ImHzry imHzryUpdate = new ImHzry();
            BeanUtils.copyProperties(imHzryReq, imHzryUpdate);
            DicJbbmModel dicJbbmParam = new DicJbbmModel();
            dicJbbmParam.setIcd10(imHzryUpdate.getRyzd());
            dicJbbmParam.setZfbz(0);
            List<DicJbbmModel> dicJbbmList = dicJbbmSer.queryList(dicJbbmParam);
            DicJbbmModel dicJbbm = dicJbbmList.get(0);
            imHzryUpdate.setMqzd(dicJbbm.getJbmc() + ",");
            imHzryDao.update(imHzryUpdate);
            //修改入院主诊断
            imRyzdSer.updateAdmittingDiagnosis(imHzryReq.getZyh(), dicJbbm.getJbxh());
            mpiBrda.setJdjg(user.getHospitalId());
            opMpiBrdaService.update(mpiBrda);

        }


        return ReturnEntityUtil.success();

    }

    /**
     * 病人管理-病人注销
     *
     * @param zyh
     * @param user
     * @return
     */
    public ReturnEntity<String> doDelBrry(Integer zyh, SysUser user, String ip ,String cardno) {
        ImHzry imHzryParam = new ImHzry();
        imHzryParam.setZyh(zyh);
        List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzryParam);
        if (imHzryList.size() > 0) {
            ImHzry imHzry = imHzryList.get(0);
            //病人已通知出院，不能注销
            if (imHzry.getCypb().intValue() == 1) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00014");
            }
            //病人已有缴款发生，不能进行注销操作
            ImTbkk imTbkkParam = new ImTbkk();
            imTbkkParam.setZyh(zyh);
            Integer jkje = imTbkkSer.valSumJkje(imTbkkParam);
            if (jkje > 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00015");
            }

            //病人有未停发医嘱，不能进行注销操作
            Integer count = cisHzyzDao.findwtyzCount(zyh);
            if (count > 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00045");
            }

            //病人已有费用发生，不能进行注销操作
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("zyh", zyh);
            paramMap.put("jgid", user.getHospitalId());
            CardPatientCostBaseInfoJe map = imFeeFymxDao.queryPatientCost(zyh, user.getHospitalId());
            if (map.getFyhj().compareTo(BigDecimal.ZERO) > 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00046");
            }
            //判断是否有临床入院记录
            ImRcjl imRcjlParam = new ImRcjl();
            imRcjlParam.setZyh(zyh);
            imRcjlParam.setCzlx(1);
            imRcjlParam.setBqpb(0);
            List<ImRcjl> imRcjlList = imRcjlDao.findByEntity(imRcjlParam);
            // 如果已有临床入院，则需要进行入院注销处理
            if (imRcjlList.size() > 0) {
                Map<String, Object> uf_ryrq_setparameters = new HashMap<String, Object>();
                uf_ryrq_setparameters.put("zyh", zyh);
                uf_ryrq_setparameters.put("ryrq", null);
                imCwszSer.uf_ryrq_set(uf_ryrq_setparameters, user);
            }

            //清空床位的住院号
            imCwszDao.delZyh(zyh);
            // 病人注销更新入院登记记录
            ImHzry doDelParam = new ImHzry();
            doDelParam.setZyh(zyh);
            doDelParam.setCypb(99);
            doDelParam.setCyrq(new Timestamp(System.currentTimeMillis()));
            imHzryDao.doDelBrry(doDelParam);

            //医保撤销登记

            String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
            //判断是否是医保病人
            Integer brxz = imHzry.getBrxz();
            String sjxzs = zyjsPreService.getGrandBrxz(String.valueOf(brxz));
            if ("0".equals(YBSYPB) && "6021".equals(sjxzs)){
//                SJ21 sj21 = new SJ21();
                Map<String, Object> yb_map = new HashMap<String, Object>();
                Map<String, Object> res = new HashMap<String, Object>();
                String cardtype = "1";
                if (cardno.length() == 28) {
                    cardtype = "0";
                }
                String orgid = "";
                Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
                if (map_orgid != null && !map_orgid.isEmpty()) {
                    orgid = map_orgid.get("ybjgid").toString();
                }
                yb_map.put("orgId", orgid);
                yb_map.put("ip", ip);
                yb_map.put("cardtype", cardtype);
                yb_map.put("carddata", cardno);
                yb_map.put("cxtype", "3");
                yb_map.put("dbxm", "");
                yb_map.put("ygdm", user.getUserId());
                yb_map.put("ygxm", user.getUserName());
                yb_map.put("jgid", user.getHospitalId());
                SettleResultDTO resultDTO = offLineSettle.hospitalRevocation(yb_map);
                if (resultDTO.getCode() == 200) {
//                    String json = resultDTO.getData().toString();
//                    Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
//                    Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
//                    if ("4".equals(cxtype)) {
//                        ShybMzdbDbksService.updateDbdjzt(StrUtil.null2Str(xxnrMap.get("cardid")), dbxm);
//                    }
                    //返回字段待定  msg撤销成功
                } else {
                    throw BaseException.create("ERROR_SHYB_0025");
                }
            }

        }
        return ReturnEntityUtil.success();
    }

    /**
     * 退床操作
     *
     * @param imHzryParam
     */
    public void updateTc(ImHzry imHzryParam) {
        imHzryDao.updateTc(imHzryParam);

    }

    /**
     * 患者管理-帐卡-费用帐卡；结算管理-结算病人帐卡信息
     *
     * @param req 患者管理调用时 jslx=null; 结算管理调用时jslx!=null
     * @return
     */
    public ReturnEntity<CardPatientInfoResp> queryCardPatientInfo(CardPatientInfoReq req) {
        CardPatientInfoResp resp = new CardPatientInfoResp();
        //病人入院信息
        ImHzry imHzry = null;
        if (StrUtil.isNotBlank(req.getFphm())) {
            ImZyjs zyjs = zyjsPreService.getImZyjsByFphm(req.getJgid(), req.getFphm());
            if (zyjs == null) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00064");
            }
            req.setZyh(zyjs.getZyh());
            req.setJslx("10");
            req.setJscs(zyjs.getJscs());
            req.setStartDate(zyjs.getKsrq().toString());
            req.setEndDate(zyjs.getZzrq().toString());
        }
        imHzry = imHzryDao.getById(req.getZyh());
        String YBXZ = sysXtcsCacheSer.getCsz(req.getJgid(), "YBXZ");
        String ybsjxz = pubBrxzSer.getSjxzByBrxz(imHzry.getBrxz().intValue()) + "";
        if(YBXZ.equals(ybsjxz)){
            resp.setIsyb("1");
        }else{
            resp.setIsyb("0");
        }
        if ("5".equals(req.getJslx())) {
            if (imHzry.getCyrq() == null) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00044");
            }
        }
        //病人状态检查
        if ("5".equals(req.getJslx()) && 1 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00023");
        } else if ("1".equals(req.getJslx()) && 0 != imHzry.getCypb()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00071");
        } else if ("10".equals(req.getJslx())) {
            Integer count = imHzryDao.queryfpzf(imHzry.getZyh());
            if (count == 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00019");
            }
        }


        resp.setZyh(imHzry.getZyh());
        resp.setJslx(req.getJslx());
        resp.setZyhm(imHzry.getZyhm());
        resp.setBrch(imHzry.getBrch());
        resp.setBrxm(imHzry.getBrxm());
        resp.setBrxz(imHzry.getBrxz());
        resp.setBrks(imHzry.getBrks());
        resp.setBrbq(imHzry.getBrbq());
        resp.setBrch(imHzry.getBrch());
        if ("".equals(imHzry.getGsrdh()) || imHzry.getGsrdh() == null) {
            resp.setGsrdh("-1");
        } else {
            resp.setGsrdh(imHzry.getGsrdh());
        }
        resp.setJscs(imHzry.getJscs() == null ? 0 : imHzry.getJscs());
        //已废弃
//        resp.setStartDate(imHzry.getRyrq().toString());
//        resp.setEndDate(imHzry.getCyrq().toString());

        //住院天数
        resp.setRyrq(imHzry.getRyrq());
        resp.setCyrq(imHzry.getCyrq() == null ? DateUtils.getNow() : imHzry.getCyrq());
        resp.setZyts(BUHISUtil.loadDischargeDays(resp.getRyrq().toString(), resp.getCyrq().toString()));
        resp.setJsrq(imHzry.getJsrq());
        //结算天数
        if (StrUtil.isNotBlank(req.getJslx())) {
            resp.setKsrq(DateUtils.parseToDateTime(req.getStartDate()));
            resp.setZzrq(DateUtils.parseToDateTime(req.getEndDate()));
            resp.setJsts(Double.valueOf(DateUtils.getDaysDiff(resp.getKsrq(), resp.getZzrq())));
        }
        //查询医保开始时间
        Map<String, Object> ybjsksrq = imHzryDao.queryYbksrq(req.getJgid(), imHzry.getZyh(), req.getEndDate());
        if (ybjsksrq != null && !ybjsksrq.isEmpty()) {
            resp.setYbjsksrq(ybjsksrq.get("YBJSKSRQ") + "");
        }
        //出院结算查询所有待结算的费用信息，不取费用日期
        ReturnEntity<PatientBalanceAccountsBaseInfoResp> re = queryPatientBalanceAccountsBaseInfo(imHzry.getZyh(), imHzry.getJgid(), req.getStartDate(), req.getEndDate(), "", req.getJslx(), "0");
        if ("SUCCESS".equals(re.getErrorCode())) {
            resp.setFyhj(re.getData().getZyf());
            resp.setZfhj(re.getData().getZfje());
            resp.setJkje(re.getData().getYjhj());
            resp.setYk(re.getData().getYkje());
            resp.setHjje(re.getData().getZyf());
            try {
                resp.setHjjedx(RmbUtil.toRMBUpper(re.getData().getZyf().toString()));
            } catch (Exception e) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00043");
            }
        }

        return ReturnEntityUtil.success(resp);

    }

    /**
     * 病人管理-帐卡-费用帐卡(帐卡费用列表)
     *
     * @param cardPatientInfoCostReq
     * @return
     */
    public ReturnEntity<List<CardPatientInfoCostResp>> queryCardPatientCostInfoList(CardPatientInfoCostReq cardPatientInfoCostReq) {
        List<CardPatientInfoCostResp> cardPatientInfoCostResps = null;
        ImHzry imHzryParam = new ImHzry();
        BeanUtils.copyProperties(cardPatientInfoCostReq, imHzryParam);

        //查询发票号码
        if (StringUtils.isNotBlank(cardPatientInfoCostReq.getFphm())) {
            ImZyjs imZyjsParam = new ImZyjs();
            imZyjsParam.setFphm(cardPatientInfoCostReq.getFphm());
            List<ImZyjs> imZyjsList = imZyjsDao.findByEntity(imZyjsParam);
            if (imZyjsList.size() == 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00064");
            } else {
                imHzryParam.setZyh(imZyjsList.get(0).getZyh());
            }
        }
        List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzryParam);
        if (imHzryList.size() == 0) {
            //住院号码
            if (StringUtils.isNotBlank(cardPatientInfoCostReq.getZyhm())) {
                return ReturnEntityUtil.error("ERROR_HIS_IM_IMS_ZYBRRY_00062");

            }
            //病人床号
            if (StringUtils.isNotBlank(cardPatientInfoCostReq.getZyhm())) {
                return ReturnEntityUtil.error("ERROR_HIS_IM_IMS_ZYBRRY_00063");
            }

        }

        if ("10".equals(cardPatientInfoCostReq.getJslx())) {
            cardPatientInfoCostReq.setJscs(cardPatientInfoCostReq.getJscs());
        } else if ("5".equals(cardPatientInfoCostReq.getJslx()) || "1".equals(cardPatientInfoCostReq.getJslx())) {
            cardPatientInfoCostReq.setJscs(0);
//            if(cardPatientInfoCostReq.getJsrq()==null){
//                cardPatientInfoCostReq.setJsrq(new Timestamp(System.currentTimeMillis()));
//            }
//
//            if ("1".equals(cardPatientInfoCostReq.getJslx()) && cardPatientInfoCostReq.getJsrq() != null) {
//                cardPatientInfoCostReq.setJsrq(DateUtils.getDailyEndTime(cardPatientInfoCostReq.getJsrq().getTime()));
//            }
        }
        Boolean isVip = zyjsPreService.isVIP(cardPatientInfoCostReq.getJgid(), cardPatientInfoCostReq.getBrxz());
        if (isVip) {
            cardPatientInfoCostResps = imFeeFymxSer.queryVipCardPatientInfoCost(cardPatientInfoCostReq);
        } else {
            cardPatientInfoCostResps = imFeeFymxSer.queryCardPatientInfoCost(cardPatientInfoCostReq);
        }


        return ReturnEntityUtil.success(cardPatientInfoCostResps);
    }

    /**
     * 病人管理-帐卡-费用帐卡(缴费记录)
     *
     * @param zyh
     * @param user
     * @return
     */
    public ReturnEntity<List<ImTbkkResp>> queryCardPatientCostRecord(Integer zyh, SysUser user) {
        ImTbkk imTbkkParam = new ImTbkk();
        imTbkkParam.setZyh(zyh);
        imTbkkParam.setJgid(user.getHospitalId());
        return ReturnEntityUtil.success(BeanUtil.toBeans(imTbkkSer.findJkjl(imTbkkParam), ImTbkkResp.class));
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-明细格式)分页查询
     *
     * @param
     * @param
     * @return
     */
    public List<CardPatientCostDetailResp> queryCardPatientCostDetail(CardPatientCostPageReq cardPatientCostPageReqReq) {

        List<ImFeeFymx> cardPatientCostDetailRespList = imFeeFymxDao.queryCardPatientCostDetail(cardPatientCostPageReqReq);

        return BeanUtil.toBeans(cardPatientCostDetailRespList, CardPatientCostDetailResp.class);
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-病人费用统计信息)
     *
     * @return
     */
    public CardPatientCostBaseInfoResp queryCardPatientCostBaseInfo(CardPatientCostReq req, Integer jgid) {
        CardPatientCostBaseInfoResp resp = new CardPatientCostBaseInfoResp();
        //入院信息
        ImHzry imHzry = imHzryDao.getById(req.getZyh());
        //是否vip
        boolean isvip = zyjsPreService.isVIP(jgid, imHzry.getBrxz().toString());

        resp.setBrxm(imHzry.getBrxm());
        resp.setBrxz(imHzry.getBrxz());
        resp.setStartDate(imHzry.getRyrq().toString());
        resp.setEndDate(imHzry.getCyrq() == null ? null : imHzry.getCyrq().toString());
        //计算住院天数
        resp.setZyts(BUHISUtil.loadDischargeDays(imHzry.getRyrq().toString(), imHzry.getCyrq() == null ? DateUtils.getCurrentDateStr() : imHzry.getCyrq().toString()));
        resp.setBrks(imHzry.getBrks());
        resp.setBrch(imHzry.getBrch());
//             cardPatientCostReq.setJscs(imHzry.getJscs());
        resp.setZyhm(imHzry.getZyhm());

        //本次费用时间范围
        Timestamp fyksrq = DateUtils.parseToDateTime(req.getStartDate());
        Timestamp fyjsrq = DateUtils.parseToDateTime(req.getEndDate());
        //本次住院所有费用明细
        List<ImFeeFymx> all = imFeeFymxDao.findAll(req);

        BigDecimal zflj = new BigDecimal(0);//自负累计
        BigDecimal fylj = new BigDecimal(0);//总费用累计
        BigDecimal zfhj = new BigDecimal(0);//自负小计（本次查询时间段）
        BigDecimal fyhj = new BigDecimal(0);//费用小计（本次查询时间段）
        for (ImFeeFymx fymx : all) {
            if (fymx.getFyrq().compareTo(fyksrq) >= 0 && fymx.getFyrq().compareTo(fyjsrq) <= 0) {
                zfhj = zfhj.add(fymx.getZfje() == null ? BigDecimal.ZERO : fymx.getZfje());
                fyhj = fyhj.add(fymx.getZjje() == null ? BigDecimal.ZERO : fymx.getZjje());
            }
            zflj = zflj.add(fymx.getZfje() == null ? BigDecimal.ZERO : fymx.getZfje());
            fylj = fylj.add(fymx.getZjje() == null ? BigDecimal.ZERO : fymx.getZjje());
        }
        resp.setFylj(fylj);
        resp.setZflj(zflj);
        resp.setFyhj(isvip ? zfhj : fyhj);
        resp.setZfhj(zfhj);
        return resp;
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-汇总格式)分页查询
     *
     * @param
     * @param
     * @return
     */
    public List<CardPatientCostCollectResp> queryCardPatientCostCollect(CardPatientCostPageReq cardPatientCostPageReqReq) {

        List<CardPatientCostCollectResp> cardPatientCostDetailRespList = imFeeFymxDao.queryCardPatientCostCollect(cardPatientCostPageReqReq);

        return cardPatientCostDetailRespList;
    }

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-医嘱格式)分页查询
     *
     * @param cardPatientCostPageReqReq
     * @return
     */
    public List<CardPatientCostAdviceResp> queryCardPatientCostAdvice(CardPatientCostPageReq cardPatientCostPageReqReq) {

        return imFeeFymxDao.queryCardPatientCostAdvice(cardPatientCostPageReqReq);
    }

    public List<CardPatientCostCollectNewResp> queryCardPatientCostCollectNew(CardPatientCostPageReq cardPatientCostPageReqReq) {

        return imFeeFymxDao.queryCardPatientCostCollectNew(cardPatientCostPageReqReq);
    }

    /**
     * 缴费管理-缴费处理-病人姓名模糊查询住院病人列表
     *
     * @param
     * @param
     * @return
     */
    public ReturnEntity queryCostPatientListByBrxm(QueryCostPatientListByBrxmReq queryCostPatientListByBrxmReq) {
        List<ImHzry> imHzryList = imHzryDao.queryCostPatientListByBrxm(queryCostPatientListByBrxmReq);


        return ReturnEntityUtil.success(imHzryList);
    }

    /**
     * 结算管理-根据结算类型查询病人结算列表
     *
     * @param queryBalanceAccountsPageReq 1:出院结算;2:中途结算;3:发票作废
     * @param user
     * @return
     */
    public ReturnEntity<PageInfo<BalanceAccountsListResp>> queryBalanceAccountsList(QueryBalanceAccountsPageReq queryBalanceAccountsPageReq, SysUser user) {
        List<BalanceAccountsListResp> balanceAccountsListRespList = new ArrayList<>();
        //出院结算出院结算
        if (queryBalanceAccountsPageReq.getJslx() == 5) {
            balanceAccountsListRespList = imHzryDao.cyjs(user.getHospitalId(), queryBalanceAccountsPageReq.getZyhm(), queryBalanceAccountsPageReq.getYwlx(),queryBalanceAccountsPageReq.getBrch());
            for (BalanceAccountsListResp balanceAccountsListResp : balanceAccountsListRespList) {
                //判断是否是高价药
//                int count = imFeeFymxDao.findCyjsGjyByZyh(balanceAccountsListResp.getZyh());
//                balanceAccountsListResp.setGjybz(count > 0 ? 1 : 0);

                if (balanceAccountsListResp.getCyrq() != null) {
                    //查询最后一次未作废的结算记录
                    ImZyjs lastRecord = zyjsPreService.getLastImZyjs(balanceAccountsListResp.getZyh());
                    Date ksrq;
                    if (lastRecord == null) {//首次结算以入院日期作为开始日期
                        ksrq = balanceAccountsListResp.getRyrq();
                    } else {//上一次的终止日期增加一秒作为下一次的开始日期
                        ksrq = DateUtils.secondAdd(lastRecord.getZzrq(), 1);
                    }
                    //计算住院天数
                    String ryrq = DateUtils.toString(balanceAccountsListResp.getRyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                    String cyrq = DateUtils.toString(balanceAccountsListResp.getCyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                    balanceAccountsListResp.setZyts(String.valueOf(BUHISUtil.loadDischargeDays(ryrq, cyrq)));
                    //计算结算天数
                    balanceAccountsListResp.setKsrq(ksrq);
                    balanceAccountsListResp.setZzrq(balanceAccountsListResp.getCyrq());
                    balanceAccountsListResp.setJsts(String.valueOf(DateUtils.getDaysDiff(balanceAccountsListResp.getRyrq(), balanceAccountsListResp.getCyrq())));
                }
            }
        }

        //中途结算
        if (queryBalanceAccountsPageReq.getJslx() == 1) {
            queryBalanceAccountsPageReq.setZzrq(DateUtils.getDailyEndTime(queryBalanceAccountsPageReq.getZzrq()));
            balanceAccountsListRespList = imHzryDao.ztjs(user.getHospitalId(), queryBalanceAccountsPageReq.getZyhm(),
                    queryBalanceAccountsPageReq.getYwlx(), queryBalanceAccountsPageReq.getZzrq(),
                    queryBalanceAccountsPageReq.getBrch());

            //查询费用明细中 高价药结算的记录
//            List<BalanceAccountsListResp> fymxZtjsList = imHzryDao.fymxZtjs(user.getHospitalId(), queryBalanceAccountsPageReq.getZyhm(), queryBalanceAccountsPageReq.getYwlx());
//            balanceAccountsListRespList.addAll(fymxZtjsList);
            for (BalanceAccountsListResp balanceAccountsListResp : balanceAccountsListRespList) {
                //判断是否是高价药
//                int count = imFeeFymxDao.findZtjsGjyByZyh(balanceAccountsListResp.getZyh());
//                balanceAccountsListResp.setGjybz(count > 0 ? 1 : 0);
                //计算住院天数
                String ryrq = DateUtils.toString(balanceAccountsListResp.getRyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                String cyrq = DateUtils.toString(DateUtils.getNow(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                balanceAccountsListResp.setZyts(String.valueOf(BUHISUtil.loadDischargeDays(ryrq, cyrq)));

                //查询最后一次未作废的结算记录
                ImZyjs lastRecord = zyjsPreService.getLastImZyjs(balanceAccountsListResp.getZyh());
                Date ksrq;
                if (lastRecord == null) {//首次结算以入院日期作为开始日期
                    ksrq = balanceAccountsListResp.getRyrq();
                } else {//上一次的终止日期增加一秒作为下一次的开始日期
                    ksrq = DateUtils.secondAdd(lastRecord.getZzrq(), 1);
                }
                balanceAccountsListResp.setKsrq(ksrq);
                if (queryBalanceAccountsPageReq.getZzrq() != null) {
                    Date zzrq = DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, queryBalanceAccountsPageReq.getZzrq());
                    balanceAccountsListResp.setZzrq(zzrq);
                }
                balanceAccountsListResp.setJsts(String.valueOf(DateUtils.getDaysDiff(balanceAccountsListResp.getKsrq(), balanceAccountsListResp.getZzrq())));
            }
            //过滤结算开始日期大于结算终止日期的数据
            balanceAccountsListRespList = balanceAccountsListRespList.stream().filter(r -> r.getKsrq().before(r.getZzrq())).collect(Collectors.toList());
        }
        //发票作废
        if (queryBalanceAccountsPageReq.getJslx() == 10) {
            balanceAccountsListRespList = imHzryDao.fpzf(user.getHospitalId(), queryBalanceAccountsPageReq.getZyhm(), queryBalanceAccountsPageReq.getYwlx(),queryBalanceAccountsPageReq.getBrch());

            //判断是否是高价药
            for (BalanceAccountsListResp balanceAccountsListResp : balanceAccountsListRespList) {
//                balanceAccountsListResp.setGjybz(0);

                //计算住院天数
                String ryrq = DateUtils.toString(balanceAccountsListResp.getRyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                String cyrq;
                if (balanceAccountsListResp.getJslx() == 5) {
                    cyrq = DateUtils.toString(balanceAccountsListResp.getCyrq(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                } else {
                    cyrq = DateUtils.toString(DateUtils.getNow(), DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
                }
                balanceAccountsListResp.setZyts(String.valueOf(BUHISUtil.loadDischargeDays(ryrq, cyrq)));
                //计算结算天数
                balanceAccountsListResp.setJsts(String.valueOf(DateUtils.getDaysDiff(balanceAccountsListResp.getKsrq(), balanceAccountsListResp.getZzrq())));
            }
        }
        PageInfo<BalanceAccountsListResp> pageInfo = PageUtil.getPageInfo(queryBalanceAccountsPageReq.getPageNum(), queryBalanceAccountsPageReq.getPageSize(), balanceAccountsListRespList);
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 结算管理-病人结算基本汇总信息
     * @param zyh
     * @param hospitalId
     * @param endDate
     * @param
     * @return
     */
    /*public ReturnEntity<PatientBalanceAccountsBaseInfoResp> queryPatientBalanceAccountsBaseInfo(Integer zyh, Integer hospitalId, String endDate) {
        PatientBalanceAccountsBaseInfoResp patientBalanceAccountsBaseInfoResp = new PatientBalanceAccountsBaseInfoResp();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ImHzry imHzry = imHzryDao.getById(zyh);
        BeanUtils.copyProperties(imHzry, patientBalanceAccountsBaseInfoResp);
        //计算总费用
        CardPatientCostReq cardPatientCostReq = new CardPatientCostReq();
        cardPatientCostReq.setQueryType(0);
        cardPatientCostReq.setZyh(zyh);
        cardPatientCostReq.setJgid(hospitalId);
        //结算日期是空
        if (patientBalanceAccountsBaseInfoResp.getJsrq() == null) {
            //结算日期为空
            if (StringUtils.isBlank(endDate)) {
                endDate = sdf.format(new Date());
            }
            cardPatientCostReq.setEndDate(endDate);
        }
        cardPatientCostReq.setJscs(imHzry.getJscs());
        CardPatientCostBaseInfoJe cardPatientCostBaseInfoJe = imFeeFymxDao.querycardPatientCostBaseInfoJe(cardPatientCostReq);
        //总费用
        patientBalanceAccountsBaseInfoResp.setZyf(cardPatientCostBaseInfoJe.getFyhj());
        boolean isVip = imZyjsSer.isVIP(hospitalId, imHzry.getBrxz().toString());
        if (isVip) {
            patientBalanceAccountsBaseInfoResp.setZyf(cardPatientCostBaseInfoJe.getZfhj());
        }
        //折扣金额
        patientBalanceAccountsBaseInfoResp.setZkje(cardPatientCostBaseInfoJe.getZkje());

        //自负合计
        patientBalanceAccountsBaseInfoResp.setZfje(cardPatientCostBaseInfoJe.getZfhj());
        //总缴款
        patientBalanceAccountsBaseInfoResp.setYjhj(imTbkkSer.queryJkje(zyh, hospitalId));
        //余款金额
        patientBalanceAccountsBaseInfoResp.setYkje(patientBalanceAccountsBaseInfoResp.getYjhj().subtract(patientBalanceAccountsBaseInfoResp.getZyf()));

        //住院天数
        ImZyjs imzyjs = imZyjsDao.getById(zyh);
        if (imzyjs != null) {
            patientBalanceAccountsBaseInfoResp.setZyts(imzyjs.getZyts());
            //结算日期
            patientBalanceAccountsBaseInfoResp.setJsrq(imzyjs.getJsrq());
        }


        return ReturnEntityUtil.success(patientBalanceAccountsBaseInfoResp);
    }*/

    /**
     * 结算管理-病人结算基本汇总信息
     *
     * @param zyh
     * @param hospitalId
     * @param endDate
     * @param accountattr 医保流程病人性质判断标志 2-伤残
     * @param zfje        进入医保统筹分类自负，但是实际未支付的部分 （eg: 1000总费用，自负比例0.1，900进医保，医保账户余额不足，或病人性质原因，只报销了600，则，zfje=300）
     * @param
     * @return
     */
    public ReturnEntity<PatientBalanceAccountsBaseInfoResp> queryPatientBalanceAccountsBaseInfo(Integer zyh, Integer hospitalId, String startDate, String endDate, String accountattr, String jslx, String zfje) {
        PatientBalanceAccountsBaseInfoResp resp = new PatientBalanceAccountsBaseInfoResp();
        ImHzry imHzry = imHzryDao.getById(zyh);
        boolean isVip = zyjsPreService.isVIP(hospitalId, imHzry.getBrxz().toString());
//        BeanUtils.copyProperties(imHzry, patientBalanceAccountsBaseInfoResp);

        //住院天数
        Timestamp ryrq = imHzry.getRyrq();
        Timestamp cyrq = imHzry.getCyrq();
        if (cyrq == null) {
            cyrq = DateUtils.getNow();
        }
        resp.setZyts(new BigDecimal(BUHISUtil.loadDischargeDays(ryrq.toString(), cyrq.toString())));
        resp.setJsrq(imHzry.getJsrq());

        //本次结算期间产生的费用明细
        CardPatientCostReq cardPatientCostReq = new CardPatientCostReq();
        cardPatientCostReq.setQueryType(0);
        cardPatientCostReq.setZyh(zyh);
        cardPatientCostReq.setStartDate(startDate);
        cardPatientCostReq.setEndDate(endDate);
        cardPatientCostReq.setJslx(jslx);

        List<ImFeeFymx> list = imFeeFymxDao.findList(cardPatientCostReq);

/*        if (CollectionUtil.isEmpty(list)) {
            return ReturnEntityUtil.success(resp);
        }*/
        BigDecimal fyhj = BigDecimal.ZERO;//总费用
        BigDecimal zfhj = BigDecimal.ZERO;//自负
        BigDecimal zkje = BigDecimal.ZERO;//折扣

        for (ImFeeFymx fymx : list) {
            if ("2".equals(accountattr)) {
                zfhj = zfhj.add(BigDecimal.ZERO);
                fyhj = fyhj.add(fymx.getZjje() == null ? BigDecimal.ZERO : fymx.getZjje());
                zkje = zkje.add(fymx.getZkje() == null ? BigDecimal.ZERO : fymx.getZkje());
            } else {
                if (!"0".equals(zfje)) {
                    zfhj = new BigDecimal(zfje);
                } else {
                    zfhj = zfhj.add(fymx.getZfje() == null ? BigDecimal.ZERO : fymx.getZfje());
                }
                fyhj = fyhj.add(fymx.getZjje() == null ? BigDecimal.ZERO : fymx.getZjje());
                zkje = zkje.add(fymx.getZkje() == null ? BigDecimal.ZERO : fymx.getZkje());
            }
        }


        resp.setZyf(isVip ? zfhj : fyhj);
        resp.setZfje(zfhj);
        resp.setZkje(zkje);

        resp.setYjhj(imTbkkSer.queryJkje(zyh));
        //余款金额 = 账户余额 - 自负金额 - 折扣金额
        BigDecimal ykje = resp.getYjhj().subtract(resp.getZfje()).subtract(resp.getZkje());
        resp.setYkje(ykje == null ? BigDecimal.ZERO : ykje);

        return ReturnEntityUtil.success(resp);
    }

    /**
     * 卡号查询病人住院信息
     *
     * @param cardno
     * @param user
     * @return
     */
    public ReturnEntity<ImHzryResp> queryBrxxByCardno(String cardno, String isYb, String ip, SysUser user) {
        String cardtype = "1";
        String xzqhdm = "";
        String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
        if ("1".equals(isYb) && "0".equals(YBSYPB)) {
            isYb = "1";
            String ecToken = cardno;
            if ("1".equals(cardtype)) {
                ecToken = "";
            }
            if (cardno.length() == 28) {
                cardtype = "0";
            } else {
                cardno = "";
            }
            String orgid = "";
            Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
            if (map_orgid != null && !map_orgid.isEmpty()) {
                orgid = map_orgid.get("ybjgid").toString();
            } else {
                throw BaseException.create("ERROR_SHYB_0015");
            }
            Map<String, Object> res = new HashMap<>();
            Map<String, Object> sm01 = new HashMap<String, Object>();
            sm01.put("xxlxm", "SM01");
            sm01.put("cardtype", cardtype);
            sm01.put("carddata", ecToken);
            sm01.put("orgId", orgid);
            sm01.put("ip", ip);
            sm01.put("jgid", user.getHospitalId());
            sm01.put("ygdm", user.getUserId());
            sm01.put("ygxm", user.getUserName());
            SettleResultDTO sm01_res = offLineSettle.send(res, sm01);
            if (sm01_res.getCode() == 200) {
                String sm01_json = sm01_res.getData().toString();
                Map<String, Object> sm01_tempMap = JackJsonUtil.parse(sm01_json, Map.class);
                Map<String, Object> ybkxxMap = (Map<String, Object>) sm01_tempMap.get("xxnr");
                System.out.println("sm01返参" + ybkxxMap);
                String accountattr = ybkxxMap.get("accountattr").toString();
                String zhfc = accountattr.substring(4, 5);
                String zhbz = accountattr.substring(11, 12);
                cardno = ybkxxMap.get("cardid").toString();
                //if("Y".equals(zhbz)){
                sm01.put("xxlxm", "S000");
                res.clear();
                System.out.println("s000入参：" + sm01);
                SettleResultDTO s000_res = offLineSettle.send(res, sm01);
                if (s000_res.getCode() == 200) {
                    String s000_json = s000_res.getData().toString();
                    Map<String, Object> s000_tempMap = JackJsonUtil.parse(s000_json, Map.class);
                    Map<String, Object> s000_Map = (Map<String, Object>) s000_tempMap.get("xxnr");
                    xzqhdm = s000_Map.get("xzqh") + "";
                }
                // }
            } else {
                Integer code = (Integer) res.get("code");
                String msg = (String) res.get("msg");
                throw BaseException.create("ERROR_SHYB_0020", new String[]{res.get("code") + "", res.get("msg") + ""});
            }
        }

        //查询病人是否有入院信息
        ImHzryResp imHzryResp = new ImHzryResp();
        //先去查询卡记录
        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("CARDNO", cardno);
        Map<String, Object> brdaInfo = opMpiCardService.getMpiCardInfoByCardNo(cardMap);

        if (null == brdaInfo || brdaInfo.size() == 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00041");
        }

        ImHzry imHzryParam = new ImHzry();
        imHzryParam.setJgid(user.getHospitalId());
        imHzryParam.setBrid(MapAttributeUtils.getInteger("BRID", brdaInfo));
        imHzryParam.setSortColumns("RYRQ desc");
        List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzryParam);
        //查询该患者是否在院
        List<ImHzry> zylist = imHzryList.stream().filter(s -> s.getCypb().intValue() < 8).collect(Collectors.toList());
        if (zylist.size() > 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00069");
        }

        MpiBrda mpiBrda = opMpiBrdaService.getByBrid((MapAttributeUtils.getInteger("BRID", brdaInfo)), user.getHospitalId());
        if (mpiBrda == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00041");
        }

//        if(imHzryResp.getBrid()==null){
//            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00041");
//        }
        cn.hutool.core.bean.BeanUtil.copyProperties(mpiBrda, imHzryResp);
        //没有工作单位 从入院登记拷贝
        if (imHzryList.size() > 0) {
            imHzryResp.setGzdw(imHzryList.get(0).getGzdw());
        }

        //修改入院年龄
        imHzryResp.setRynl(imHzryResp.getCsny() == null ? null : BUHISUtil.getPersonAge(new Date(imHzryResp.getCsny().getTime()), new Date()).get("ages").toString());
        //修改住院次数
        imHzryResp.setZycs(imHzryList.size() + 1);
        imHzryResp.setJscs(0);
        imHzryResp.setYbkh(StringUtils.isBlank(imHzryResp.getYbkh()) ? MapAttributeUtils.getString("CARDNO", brdaInfo) : imHzryResp.getYbkh());
        imHzryResp.setJzkh(StringUtils.isBlank(imHzryResp.getJzkh()) ? cardno : imHzryResp.getJzkh());
        imHzryResp.setLxdh(mpiBrda.getLxrdh());
        imHzryResp.setXzzDh(mpiBrda.getBrdh());
        imHzryResp.setBrxx(mpiBrda.getXxdm());
        imHzryResp.setBrxz(imHzryResp.getBrxz() == null ? MapAttributeUtils.getInteger("BRXZ", brdaInfo) : imHzryResp.getBrxz());
        //行政区划代码
        imHzryResp.setXzqhdm(xzqhdm);
        return ReturnEntityUtil.success(imHzryResp);
    }

    /**
     * 据病人性别查询病人可分配的床位信息
     *
     * @param queryCwListByBrksAndBrxbReq
     * @param user
     * @return
     */
    public List<ImCwsz> queryCwhmListByBrksAndBrxb(QueryCwListByBrksAndBrxbReq queryCwListByBrksAndBrxbReq, SysUser user) {
        return imCwszSer.findKcByBrksAndBrxbAndJgid(queryCwListByBrksAndBrxbReq.getBrks(), queryCwListByBrksAndBrxbReq.getBrxb(), user.getHospitalId());
    }

    /**
     * 住院管理-缴费管理(分页查询)
     */
    public DebtInventoryPageResp queryDebtInventoryPage(DebtInventoryReq req) {
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(req.getJgid(), SysXtcsCsmcCts.YBCKBL);
        String YBCKBL = sysXtcs.getCsz()==null ? "100" : sysXtcs.getCsz();
        //String YBCKBL = "".equals(sysXtcsCacheSer.getCsz(req.getJgid(), "YBCKBL"))?"100":sysXtcsCacheSer.getCsz(req.getJgid(), "YBCKBL");
        BigDecimal bxbl = new BigDecimal(YBCKBL).divide(new BigDecimal(100));
        bxbl = new BigDecimal(1).subtract(bxbl);
        DebtInventoryPageResp pageResp = new DebtInventoryPageResp();
        Timestamp now = DateUtils.getNow();
        req.setCkje(req.getCkje() == null ? BigDecimal.ZERO : req.getCkje());

        //页底统计信息
        BigDecimal zfjeSum = BigDecimal.ZERO;
        BigDecimal jkjeSum = BigDecimal.ZERO;
        BigDecimal qkjeSum = BigDecimal.ZERO;
        Integer debtCount = 0;


        List<DebtInventoryResp> list = imHzryDao.queryDebtInventory(req);
        for (DebtInventoryResp entry : list) {

            entry.setAge(BUHISUtil.getPersonAge(entry.getCsny(), now).get("ages").toString());
            entry.setZyts(BUHISUtil.loadDischargeDays(entry.getRyrq().toString(), now.toString()));
            BigDecimal ybxjzf = entry.getKbje().multiply(bxbl);
            BigDecimal zfje = entry.getZfje().add(ybxjzf);
            entry.setQfje((zfje.subtract(entry.getJkje())).setScale(2, BigDecimal.ROUND_HALF_UP));
            entry.setKbje((entry.getKbje().subtract(ybxjzf)).setScale(2, BigDecimal.ROUND_HALF_UP));
            entry.setZfje((entry.getZfje().add(ybxjzf)).setScale(2, BigDecimal.ROUND_HALF_UP));
            //补缴金额 = 催款金额 + 已欠金额, 最小为0
            BigDecimal bjje = req.getCkje().add(entry.getQfje());
            entry.setBjje(bjje.max(BigDecimal.ZERO));

            //已欠费
            if (entry.getJkje().compareTo(entry.getZfje()) < 0) {
                debtCount++;
            }
            jkjeSum = jkjeSum.add(entry.getJkje());
            zfjeSum = zfjeSum.add(entry.getZfje());
            if (entry.getQfje().compareTo(BigDecimal.ZERO) > 0) {
                qkjeSum = qkjeSum.add(entry.getQfje());
            }
        }

        pageResp.setDebtInventoryRespList(list);
        pageResp.setAllCount(list.size());
        pageResp.setDebtCount(debtCount);
        pageResp.setJkjeSum(jkjeSum);
        pageResp.setZfjeSum(zfjeSum);
        pageResp.setQkjeSum(qkjeSum);

        return pageResp;
    }

    /**
     * 住院管理-缴费管理(获取系统默认的催款金额)
     *
     * @param user
     * @return
     */
    public String queryDefaultDebtInventory(SysUser user) {
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.ZYQFTXYZ);
        return sysXtcs.getCsz();

    }

    //医保相关

    /**
     * 结算管理-病人医保读卡
     *
     * @param carddata
     * @param ip
     * @param
     * @return
     */
    public ReturnEntity<SM01Res> readPatientMedicareAccountInfo(String carddata, String ip) {
        SysUser user = new SysUser();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> res_s = new HashMap<>();
        String cardtype = "1";
        if (carddata.length() == 28) {
            cardtype = "0";
        }
        SM01Res sm01res = new SM01Res();
        map.put("cardtype", cardtype);
        map.put("carddata", carddata);
        map.put("ip", ip);
        map.put("xxlxm", "SM01");
        String orgid = "";
        Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
        if (map_orgid != null && !map_orgid.isEmpty()) {
            orgid = map_orgid.get("ybjgid").toString();
        } else {
            throw BaseException.create("ERROR_SHYB_0015");
        }
        map.put("orgId", orgid);
        SettleResultDTO sm01_res = offLineSettle.send(res, map);
        if (sm01_res.getCode() == 200) {
            Map<String, Object> a = (Map) res.get("xxt");
            sm01res = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(a, new SM01Res(), true);
            String zhbz = sm01res.getAccountattr();
            String ztqk = zhbz.substring(0, 1);
            String bjdx = zhbz.substring(1, 2);
            String gwy = zhbz.substring(2, 3);
            String tsrybz = zhbz.substring(3, 4);
            String fcbz = zhbz.substring(4, 5);
            if ("1".equals(ztqk)) {
                sm01res.setZtqk("在职");
            } else if ("2".equals(ztqk)) {
                sm01res.setZtqk("退休");
            } else if ("3".equals(ztqk)) {
                sm01res.setZtqk("离休");
            } else {
                sm01res.setZtqk(ztqk);
            }
            if ("1".equals(bjdx)) {
                sm01res.setBjdx("保健对象");
            } else {
                sm01res.setBjdx("非保健对象");
            }
            if ("1".equals(gwy)) {
                sm01res.setGwy("公务员");
            } else if ("2".equals(gwy)) {
                sm01res.setGwy("参照公务员");
            } else {
                sm01res.setGwy("非公务员");
            }
            if ("1".equals(tsrybz)) {
                sm01res.setTsrybz("离休");
            } else if ("2".equals(tsrybz)) {
                sm01res.setTsrybz("伤残");
            } else {
                sm01res.setTsrybz("普通");
            }
            if ("1".equals(fcbz)) {
                sm01res.setFcbz("封存");
            } else {
                sm01res.setFcbz("正常");
            }
        }
        if ("1".equals(cardtype)) {
            map.put("xxlxm", "S000");
            SettleResultDTO s000_res = offLineSettle.send(res_s, map);
            if (s000_res.getCode() == 200) {
                Map<String, Object> b = (Map) res_s.get("xxt");
                sm01res.setSfzh(StrUtil.null2Str(b.get("sfzh")));
                sm01res.setLxdh(StrUtil.null2Str(b.get("lxdz")));
                sm01res.setTxdz(StrUtil.null2Str(b.get("txdz")));
                sm01res.setYzbm(StrUtil.null2Str(b.get("yzbm")));
                sm01res.setXzqh(StrUtil.null2Str(b.get("xzqh")));
            }
        }
        return ReturnEntityUtil.success(sm01res);
    }

    /**
     * 电子凭证解码
     *
     * @param ecQrCode
     * @param ip
     * @param
     * @return
     */
    public ReturnEntity<SE01Res> getEctoken(String ywlx, String ecQrCode, String ip, SysUser user) {
        Map<String, Object> map = new HashMap();
        SE01Res se01res = new SE01Res();
        String orgid = "";
        Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
        if (map_orgid != null && !map_orgid.isEmpty()) {
            orgid = map_orgid.get("ybjgid").toString();
        } else {
            throw BaseException.create("ERROR_SHYB_0015");
        }
        map.put("ywlx", ywlx);
        map.put("orgId", orgid);
        map.put("ecQrCode", ecQrCode);
        map.put("operatorId", user.getUserId());
        map.put("operatorName", user.getUserName());
        map.put("officeId", "");
        map.put("officeName", "");
        SettleResultDTO resultDTO = offLineSettle.se01(map);
        if (resultDTO.getCode() == 200) {
            String json = resultDTO.getData().toString();
            Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
            Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
            se01res = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(xxnrMap, new SE01Res(), true);
        }
        return ReturnEntityUtil.success(se01res);
    }

    /**
     * 查询病人医保登记信息
     *
     * @param cardno
     * @param djlb
     * @param ip
     * @param user
     * @return
     */
    public ReturnEntity<SJ31> queryShybRegister(String cardno, String djlb, String ip, SysUser user) {
        SJ31 sj31 = new SJ31();
        Map<String, Object> zy_map = new HashMap<String, Object>();
        Map<String, Object> res = new HashMap<String, Object>();
        String cardtype = "1";
        if (cardno.length() == 28) {
            cardtype = "0";
        } else {
            cardno = "";
        }
        String orgid = "";
        Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
        if (map_orgid != null && !map_orgid.isEmpty()) {
            orgid = map_orgid.get("ybjgid").toString();
        }
        zy_map.put("xxlxm", "SJ31");
        zy_map.put("cardtype", cardtype);
        zy_map.put("carddata", cardno);
        zy_map.put("djlb", djlb);
        zy_map.put("orgId", orgid);
        zy_map.put("ip", ip);
        zy_map.put("ygdm", user.getUserId());
        zy_map.put("ygxm", user.getUserName());
        zy_map.put("jgid", user.getHospitalId());
        SettleResultDTO sj31_res = offLineSettle.send(res, zy_map);
        if (sj31_res.getCode() == 200) {
            String json = sj31_res.getData().toString();
            Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
            Map<String, Object> ybdjxxMap = (Map<String, Object>) tempMap.get("xxnr");
            sj31 = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(ybdjxxMap, new SJ31(), true);
        } else {
            throw BaseException.create("ERROR_SHYB_0020", new String[]{sj31_res.getCode() + "", sj31_res.getMsg() + ""});
        }
        return ReturnEntityUtil.success(sj31);
    }

    /**
     * 医保登记信息撤销
     *
     * @param cardno
     * @param cxtype
     * @param dbxm
     * @param ip
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void revokeShybRegister(String cardno, String cxtype, String dbxm, String ip, SysUser user) {
        SJ21 sj21 = new SJ21();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> res = new HashMap<String, Object>();
        String cardtype = "1";
        if (cardno.length() == 28) {
            cardtype = "0";
        }
        String orgid = "";
        Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
        if (map_orgid != null && !map_orgid.isEmpty()) {
            orgid = map_orgid.get("ybjgid").toString();
        }
        map.put("orgId", orgid);
        map.put("ip", ip);
        map.put("cardtype", cardtype);
        map.put("carddata", cardno);
        map.put("cxtype", cxtype);
        map.put("dbxm", dbxm);
        map.put("ygdm", user.getUserId());
        map.put("ygxm", user.getUserName());
        map.put("jgid", user.getHospitalId());
        SettleResultDTO resultDTO = offLineSettle.hospitalRevocation(map);
        if (resultDTO.getCode() == 200) {
            String json = resultDTO.getData().toString();
            Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
            Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
            if ("4".equals(cxtype)) {
                ShybMzdbDbksService.updateDbdjzt(StrUtil.null2Str(xxnrMap.get("cardid")), dbxm);
            }
            //返回字段待定  msg撤销成功
        } else if (resultDTO.getCode() != 200 && "4".equals(cxtype)) {
            throw BaseException.create("ERROR_SHYB_0044", new String[]{resultDTO.getCode() + "--" + resultDTO.getMsg() + ""});
        } else {
            throw BaseException.create("ERROR_SHYB_0025");
        }
    }

    /**
     * 医保登记信息重新登记
     *
     * @param cardno
     * @param cxtype
     * @param dbxm
     * @param ip
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<SJ31Res> reShybRegister(String cardno, String cxtype, String dbxm, String zyhm, String zd, String ysgh, String dbzl, String yy, String ip, SysUser user) throws ParseException {
        SJ31Res sj31 = new SJ31Res();
        Map<String, Object> rydj_map = new HashMap<String, Object>();
        String cardtype = "1";
        if (cardno.length() == 28) {
            cardtype = "0";
        }
        if ("".equals(cxtype)) {
            throw BaseException.create("ERROR_SHYB_0026");
        }
        if ("".equals(zyhm)) {
            throw BaseException.create("ERROR_SHYB_0027");
        }
        String orgid = "";
        Map map_orgid = opMzlbService.getYbjgdm(user.getHospitalId(), ip);
        if (map_orgid != null && !map_orgid.isEmpty()) {
            orgid = map_orgid.get("ybjgid").toString();
        }
        if ("3".equals(cxtype)) {
            List<Map<String, Object>> zyBrry = imHzryDao.getRydjxx(zyhm);
            if (zyBrry.size() > 0) {
                List<DiagnosisInfo> zdnos = new ArrayList<DiagnosisInfo>();
                for (int i = 0; i < zyBrry.size(); i++) {
                    DiagnosisInfo zdno = new DiagnosisInfo();
                    zdno.setZdno(zyBrry.get(i).get("ICD10") + "");
                    zdno.setZdmc(zyBrry.get(i).get("JBMC") + "");
                    zdnos.add(zdno);
                }
                rydj_map.put("zdnos", zdnos);
                rydj_map.put("djtype", "3");
                rydj_map.put("djno", zyBrry.get(0).get("ZYH"));
                rydj_map.put("carddata", cardno);
                rydj_map.put("cardtype", cardtype);
                rydj_map.put("ryrq", zyBrry.get(0).get("RYRQ"));
                rydj_map.put("zyh", zyBrry.get(0).get("ZYH"));
                rydj_map.put("deptid", zyBrry.get(0).get("YBKS"));
                rydj_map.put("orgid", orgid);
                rydj_map.put("ip", ip);
                rydj_map.put("ygdm", user.getUserId());
                rydj_map.put("ygxm", user.getUserName());
                rydj_map.put("jgid", user.getHospitalId());
                SettleResultDTO resultDTO = offLineSettle.hospitalRegister(rydj_map);
                if (resultDTO.getCode() == 200) {
                    String json = resultDTO.getData().toString();
                    Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
                    Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
                    String jzdyh = StrUtil.null2Str(xxnrMap.get("jzdyh"));
                    imHzryDao.updateJzdyh(jzdyh, Integer.parseInt(zyBrry.get(0).get("ZYH") + ""));
                    imFeeFymxDao.updateFeeJzdyh(jzdyh, Integer.parseInt(zyBrry.get(0).get("ZYH") + ""));
                    imFeeFymxDao.updateFeeMxzdh(jzdyh, Integer.parseInt(zyBrry.get(0).get("ZYH") + ""));
                    //返回字段待定  msg登记成功
                } else {
                    throw BaseException.create("ERROR_SHYB_0028", new String[]{resultDTO.getCode() + "--" + resultDTO.getMsg() + ""});
                }
            } else {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00018");
            }
        } else if ("4".equals(cxtype)) {  //大病登记

            List<Map<String, Object>> ZYYSXM_list = imHzryDao.getYsxm(ysgh);
            String ysxm = "";
            if (ZYYSXM_list.size() > 0) {
                ysxm = StrUtil.null2Str(ZYYSXM_list.get(0).get("YSXM"));
            }
            List<DiagnosisInfo> zdnos = new ArrayList<DiagnosisInfo>();
            DiagnosisInfo zdno = new DiagnosisInfo();
            zdno.setZdno("");
            zdno.setZdmc("");
            zdnos.add(zdno);

            rydj_map.put("zdnos", zdnos);
            rydj_map.put("djtype", "4");
            rydj_map.put("djno", "");
            rydj_map.put("carddata", cardno);
            rydj_map.put("cardtype", cardtype);
            rydj_map.put("ryrq", DateUtil.date());
            rydj_map.put("deptid", "");
            rydj_map.put("orgid", orgid);
            rydj_map.put("ip", ip);
            rydj_map.put("dbxm", dbxm);
            rydj_map.put("zd", zd);
            rydj_map.put("ysgh", ysgh);
            rydj_map.put("ysxm", ysxm);
            rydj_map.put("ygdm", user.getUserId());
            rydj_map.put("ygxm", user.getUserName());
            rydj_map.put("jgid", user.getHospitalId());
            rydj_map.put("dbzl", dbzl);
            rydj_map.put("yy", yy);

            SettleResultDTO resultDTO = offLineSettle.hospitalRegister(rydj_map);
            if (resultDTO.getCode() == 200) {
                String json = resultDTO.getData().toString();
                Map<String, Object> tempMap = JackJsonUtil.parse(json, Map.class);
                Map<String, Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
                Map<String, Object> dbdjxxMap = new HashMap();
                dbdjxxMap.put("sqdh", xxnrMap.get("lsh"));
                dbdjxxMap.put("brxm", xxnrMap.get("personname"));
                dbdjxxMap.put("sfzh", xxnrMap.get("sfzh"));
                dbdjxxMap.put("zlxm", dbxm);
                dbdjxxMap.put("jbzd", zd);
                dbdjxxMap.put("yymc", user.getHospitalId());
                dbdjxxMap.put("ysgh", ysgh);
                dbdjxxMap.put("djrq", DateUtil.date());
                dbdjxxMap.put("czgh", user.getUserId());
                dbdjxxMap.put("ztzt", "0");
                dbdjxxMap.put("ybkh", xxnrMap.get("cardid"));
                dbdjxxMap.put("qsrq", xxnrMap.get("startdate"));
                dbdjxxMap.put("jsrq", xxnrMap.get("enddate"));
                //返回字段待定  msg登记成功
                SaveDbRegisterReq req = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(dbdjxxMap, new SaveDbRegisterReq(), true);
                ShybMzdbDbksService.saveDbdjInfo(req);
            } else {
                throw BaseException.create("ERROR_SHYB_0043", new String[]{resultDTO.getCode() + "--" + resultDTO.getMsg() + ""});
            }
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00018");
        }
        return ReturnEntityUtil.success(sj31);
    }

    public ReturnEntity<DbxxResp> printShybRegister(String jzdyh) {
        DbxxResp dbxx = new DbxxResp();
        Map<String, Object> dbxxMap = imHzryDao.queryDbxx(jzdyh);
        dbxx = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(dbxxMap, new DbxxResp(), true);
        return ReturnEntityUtil.success(dbxx);
    }


    public List<VerifyRecordResp> queryVerifyRecord(VerifyRecordReq verifyRecordReq) {
        return imFeeFymxDao.queryVerifyRecord(verifyRecordReq);
    }

    /**
     * 查询出院患者信息-电子病历用
     * @param imHzryEmrReq
     * @return
     */
    public List<ImHzryEmrResp> queryImHzryEmr(ImHzryEmrReq imHzryEmrReq) {
        List<ImHzryEmrResp> imHzryEmrList = imHzryDao.queryImHzryEmr(imHzryEmrReq);
        if (CollUtil.isNotEmpty(imHzryEmrList)){
            for(ImHzryEmrResp resp : imHzryEmrList) {
                Long notSealFileCount = emrFileIndexService.queryNotSealFileCount(resp.getZyh());
                if(notSealFileCount != null && notSealFileCount > 0) {
                    resp.setEmrSealStatus(0);
                }else {
                    resp.setEmrSealStatus(1);
                }
                //计算超期时间
                SysXtcs xtcs = sysXtcsCacheSer.getByJGIdAndCsmc(imHzryEmrReq.getJgid(), SysXtcsCsmcCts.EMRSEALTIME);
                Integer csz = StringUtils.isBlank(xtcs.getCsz())?7 : Integer.valueOf(xtcs.getCsz());
                Integer overdueTime = resp.getOverdueTime();
                if(resp.getOverdueTime() != null && resp.getOverdueTime() > 7) {
                    resp.setOverdueTime(overdueTime - csz);
                }else {
                    resp.setOverdueTime(0);
                }
            }
        }
        return imHzryEmrList;
    }

    /**
     * 根据住院号查询病历信息
     * @param emrFileIndexSealReq
     * @return
     */
    public PageInfo<EmrFileIndexModel> queryEmrByZyh(EmrFileIndexSealReq emrFileIndexSealReq) {
        return emrFileIndexService.queryFileIndexList(emrFileIndexSealReq);
    }

    /**
     * 病历归档
     * @param zyh
     * @param user
     */
    public void fileSeal(Integer zyh, SysUser user) {
        String message = emrFileIndexService.fileSeal(zyh,user);
        if(StringUtils.isNotBlank(message)) {
            throw BaseException.create("ERROR_EMR_0002",new String[]{message});
        }
    }

    /**
     * 病区费用对账查询在院病人的费用信息
     * @param zyhm 住院号码
     * @param brch 床号
     * @param jgid 机构id
     */
    public CardPatientCostBaseInfoResp getInHospitalPatient(String zyhm, String brch, Integer jgid) {
        CardPatientCostBaseInfoResp resp = new CardPatientCostBaseInfoResp();
        ImHzry query = new ImHzry();
        query.setZyhm(zyhm);
        query.setBrch(brch);
        query.setJgid(jgid);
        ImHzry imHzry = imHzryDao.getInHospitalPatient(query);
        if(imHzry == null){
            return null;
        }
        //修改超出费用
        imDrugsOutRangeLogSer.outRangeDrugBalance(imHzry.getZyh());

        resp.setZyh(imHzry.getZyh());
        resp.setBrxm(imHzry.getBrxm());
        resp.setBrxz(imHzry.getBrxz());
        resp.setStartDate(DateUtils.formatToDateTime(imHzry.getRyrq()));
        resp.setEndDate(imHzry.getCyrq() == null ? null : imHzry.getCyrq().toString());
        //计算住院天数
        resp.setZyts(BUHISUtil.loadDischargeDays(imHzry.getRyrq().toString(), imHzry.getCyrq() == null ? DateUtils.getCurrentDateStr() : imHzry.getCyrq().toString()));
        resp.setBrks(imHzry.getBrks());
        resp.setBrch(imHzry.getBrch());
        resp.setZyhm(imHzry.getZyhm());

        //本次住院所有费用明细
        CardPatientCostReq req = new CardPatientCostReq();
        req.setZyh(imHzry.getZyh());
        req.setQueryType(0);
        List<ImFeeFymx> all = imFeeFymxDao.findAll(req);

        BigDecimal zflj = new BigDecimal(0);//自负累计
        BigDecimal fylj = new BigDecimal(0);//总费用累计
        for (ImFeeFymx fymx : all) {
            zflj = zflj.add(fymx.getZfje() == null ? BigDecimal.ZERO : fymx.getZfje());
            fylj = fylj.add(fymx.getZjje() == null ? BigDecimal.ZERO : fymx.getZjje());
        }
        resp.setFylj(fylj);
        resp.setZflj(zflj);
        return resp;
    }

    public List<CisJcsq01PrintResp> findPatientCheckList(Integer zyh, List<Integer> jcxhList) {
        return imHzryDao.findPatientCheckList(zyh, jcxhList);
    }
}
