package com.buit.cis.ims.service;


import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImZyjsDao;
import com.buit.cis.ims.enums.ExpensesPrintTemplateEnum;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.CardPatientInfoCostReq;
import com.buit.cis.ims.request.ExpensesPrintReq;
import com.buit.cis.ims.request.UpdateZkblListReq;
import com.buit.cis.ims.request.UpdateZkblReq;
import com.buit.cis.ims.response.*;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.drug.response.DrugsTypkDetailResp;
import com.buit.drug.service.DrugsTypkOutSer;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.service.impl.OfflineSettleService;
import com.buit.op.service.OpMzlbService;
import com.buit.system.request.FeeYpxzApiReq;
import com.buit.system.request.PubFyxzApiReq;
import com.buit.system.response.FeeYlsfxmOutResp;
import com.buit.system.service.*;
import com.buit.utill.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 费用明细表<br>
 *
 * @author zhouhaisheng
 */
@Service
public class ImFeeFymxSer extends BaseManagerImp<ImFeeFymx, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImFeeFymxSer.class);

    @Autowired
    private ImFeeFymxDao imFeeFymxDao;

    @DubboReference
    private PubFyxzService pubFyxzService;

    @DubboReference
    private FeeYpxzService feeYpxzService;

    @Autowired
    private BUHISUtil BUHISUtil;

    @DubboReference
    private FeeSfdlzfblOutSer feeSfdlzfblOutSer;

    @DubboReference
    private FeeYlsfxmOutSer feeYlsfxmOutSer;
    @DubboReference
    private DrugsTypkOutSer drugsTypkOutSer;

    @Override
    public ImFeeFymxDao getEntityMapper() {
        return imFeeFymxDao;
    }

    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    @DubboReference
    private PubBrxzOutSer pubBrxzSer;
    @DubboReference
    public OpMzlbService opMzlbService;
    @Autowired
    private ImZyjsDao imZyjsDao;
    @DubboReference
    private OfflineSettleService offLineSettle;
    @Autowired
    private ImHzryDao imHzryDao;
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    @Autowired
    public ImHzrySer imHzrySer;
    @Autowired
    private ImTbkkSer imTbkkSer;
    /**
     * 病人管理-帐卡-费用帐卡(vip帐卡费用列表)
     *
     * @param cardPatientInfoCostReq
     * @return
     */
    public List<CardPatientInfoCostResp> queryVipCardPatientInfoCost(CardPatientInfoCostReq cardPatientInfoCostReq) {
        return imFeeFymxDao.queryVipCardPatientInfoCost(cardPatientInfoCostReq);
    }

    /**
     * 病人管理-帐卡-费用帐卡(帐卡费用列表)
     *
     * @param cardPatientInfoCostReq
     * @return
     */
    public List<CardPatientInfoCostResp> queryCardPatientInfoCost(CardPatientInfoCostReq cardPatientInfoCostReq) {
        return imFeeFymxDao.queryCardPatientInfoCost(cardPatientInfoCostReq);
    }

    /**
     * 结算管理-明细项目(按日期)
     *
     * @param zyh
     * @param user
     * @param zygb
     * @return
     */
    public ReturnEntity<List<PatientBalanceAccountsDetailDateResp>> patientBalanceAccountsDetailDate(Integer zyh, SysUser user, Integer zygb, String ksrq, String zzrq) {
        List<PatientBalanceAccountsDetailDateResp> patientBalanceAccountsDetailDateResps = imFeeFymxDao.queryPatientBalanceAccountsDetailDate(zyh, zygb, user.getHospitalId(), ksrq, zzrq);
        return ReturnEntityUtil.success(patientBalanceAccountsDetailDateResps);
    }

    /**
     * 结算管理-明细项目(按明细)
     *
     * @param zyh
     * @param user
     * @param zygb
     * @return
     */
    public ReturnEntity<List<PatientBalanceAccountsDetailMxResp>> patientBalanceAccountsDetailMx(Integer zyh, SysUser user, Integer zygb, String ksrq, String zzrq) {

        List<PatientBalanceAccountsDetailMxResp> patientBalanceAccountsDetailMxResps = imFeeFymxDao.queryPatientBalanceAccountsDetailMx(zyh, zygb, user.getHospitalId(), ksrq, zzrq);

        return ReturnEntityUtil.success(patientBalanceAccountsDetailMxResps);
    }


    public Map<String, Object> getFyje(Integer alYplx, Integer alBrxz, Integer alFyxh, Integer alFygb, BigDecimal adFydj, BigDecimal adFysl) {
        BigDecimal ldZfbl = new BigDecimal("1");
        boolean adcZfbl = false;
        BigDecimal ldCxbl = new BigDecimal("1");
        BigDecimal ldZfje = new BigDecimal("0");
        BigDecimal ldZlje = new BigDecimal("0");
        BigDecimal ldFyxe = new BigDecimal("0");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("BRXZ", alBrxz);

        try {
            if (alYplx == 0) {
                parameters.put("fyxh", alFyxh);
                PubFyxzApiReq pubFyxzParam = new PubFyxzApiReq();
                pubFyxzParam.setFyxh(alFyxh);
                pubFyxzParam.setBrxz(alBrxz);
                Map<String, Object> pubFyxz = pubFyxzService.getFyjyInfo(pubFyxzParam);

                if (pubFyxz != null) {
                    if (pubFyxz.get("FYXE") != null) {
                        ldFyxe = MapAttributeUtils.getBigDecimal("FYXE", pubFyxz);
                    }
                    if (pubFyxz.get("CXBL") != null) {
                        ldCxbl = MapAttributeUtils.getBigDecimal("CXBL", pubFyxz);
                    }
                    if (pubFyxz.get("ZFBL") != null) {
                        ldZfbl = MapAttributeUtils.getBigDecimal("ZFBL", pubFyxz);
                        adcZfbl = true;
                    }
                }
                if (ldFyxe.compareTo(BigDecimal.ZERO) == 1 && adFydj.compareTo(ldFyxe) == 1) {
                    ldZfje = adFysl.multiply(ldFyxe).multiply(ldZfbl);
                    ldZlje = adFysl.multiply(adFydj.subtract(ldFyxe)).multiply(ldCxbl);
                    ldZfje = ldZfje.add(ldZlje);
                } else {
                    ldZfje = adFysl.multiply(adFydj).multiply(ldZfbl);
                    ldZlje = BigDecimal.ZERO;
                }
            } else if (alYplx == 1 || alYplx == 2 || alYplx == 3) {
                FeeYpxzApiReq feeYpxz = new FeeYpxzApiReq();
                feeYpxz.setBrxz(alBrxz);
                feeYpxz.setYpxh(alFyxh);
                Map<String, Object> pubFyxz = feeYpxzService.getYpjyInfo(feeYpxz);
                if (pubFyxz != null) {
                    if (pubFyxz.get("ZFBL") != null) {
                        ldZfbl = MapAttributeUtils.getBigDecimal("ZFBL", pubFyxz);
                        adcZfbl = true;
                    }
                }
                ldZfje = adFysl.multiply(adFydj).multiply(ldZfbl);
                ldZlje = BigDecimal.ZERO;
            }
            if (!adcZfbl) {
                alFygb = getfygb(alYplx, alFyxh);
                parameters.remove("ypxh");
                parameters.remove("fyxh");
                parameters.put("SFXM", alFygb);
                List<Map<String, Object>> feeSfdlzfblList = feeSfdlzfblOutSer.getZfblMap(parameters);
                if (feeSfdlzfblList.size() != 0) {
                    Map<String, Object> feeSfdlzfbl = feeSfdlzfblList.get(0);
                    if (feeSfdlzfbl.get("ZFBL") != null) {
                        ldZfbl = MapAttributeUtils.getBigDecimal("ZFBL", feeSfdlzfbl);
                    }
                }
                ldZfje = adFysl.multiply(adFydj).multiply(ldZfbl);
                ldZlje = BigDecimal.ZERO;
            }
            BigDecimal ldZjje = adFydj.multiply(adFysl);
            Map<String, Object> retMap = new HashMap<String, Object>();
            retMap.put("zfbl", ldZfbl);
            retMap.put("zfje", ldZfje);
            retMap.put("zlje", ldZlje);
            retMap.put("zjje", ldZjje);
            retMap.put("fygb", alFygb);
            return retMap;
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00030");
        }
    }

    /**
     * 病人管理-帐卡-明细项目(按明细)-修改折扣比例
     *
     * @param updateZkblListReq
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> updateZkbl(UpdateZkblListReq updateZkblListReq) {
        for (UpdateZkblReq updateZkblReq : updateZkblListReq.getUpdateZkblReqs()) {
            imFeeFymxDao.updateZkbl(updateZkblReq);
        }


        return ReturnEntityUtil.success();
    }

    /**
     * 查询住院天数
     * @param ryrq
     * @param cyrq
     * @return
     */
    /**
     * 出院天数计算 1.当天入院，当天出院 12:00前入院,12:00点前出院,算0.5天 12:00前入院,12:00点后出院,算1.0天
     * 12:00后入院,12:00点后出院,算0.5天 2.当天入院，第二天出院： 12:00前入院,第二天12点前出院算1.5天
     * 12:00前入院,第二天12点后出院算2.0天 12:00后入院,第二天12点前出院算1.0天 12:00后入院,第二天12点后出院算1.5天
     */
    public ReturnEntity<String> queryZyts(String ryrq, String cyrq) {
        double zyts = BUHISUtil.loadDischargeDays(ryrq, cyrq);
        return ReturnEntityUtil.success(String.valueOf(zyts));
    }

    /**
     * 病人性质转换
     *
     * @param oldBrxz
     * @param newBrxz
     */
    public List<Map<String, Object>> changeBrxz(Integer oldBrxz, Integer newBrxz,
                                                List<Map<String, Object>> listImFeeFymx) {
        for (int i = 0; i < listImFeeFymx.size(); i++) {
            Map<String, Object> imFeeFymx = listImFeeFymx.get(i);
            Integer yplx = (Integer) imFeeFymx.get("yplx");
            Integer fyxh = MapAttributeUtils.getInteger("fyxh", imFeeFymx);
            Integer fygb = MapAttributeUtils.getInteger("fyxm", imFeeFymx);
            BigDecimal fydj = MapAttributeUtils.getBigDecimal("fydj", imFeeFymx);
            BigDecimal fysl = MapAttributeUtils.getBigDecimal("fysl", imFeeFymx);
            Map<String, Object> je = getFyje(yplx, newBrxz, fyxh, fygb, fydj, fysl);
            imFeeFymx.put("zfbl", je.get("zfbl"));
            imFeeFymx.put("zfje", je.get("zfje"));
            imFeeFymx.put("zlje", je.get("zlje"));
            imFeeFymx.put("zjje", je.get("zjje"));
            listImFeeFymx.set(i, imFeeFymx);
        }
        return listImFeeFymx;
    }


    /**
     * 获取费用归并
     *
     * @param al_yplx
     * @param al_fyxh
     * @return
     */
    public Integer getfygb(Integer al_yplx, Integer al_fyxh) {
        Integer al_fygb;
        if (al_yplx == 1 || al_yplx == 2 || al_yplx == 3) {// 如果不是费用,先查询有吴账簿类别,没有账簿类别则按药品类型分

            DrugsTypkDetailResp drugsTypk = drugsTypkOutSer.getDrugsTypkById(al_fyxh);
            if (drugsTypk != null && drugsTypk.getZblb() != null && drugsTypk.getZblb().intValue() > 0) {
                al_fygb = drugsTypk.getZblb();
            } else {
                throw BaseException.create("ERROR_REG_0011");
            }
        } else {
            FeeYlsfxmOutResp feeYlsfxm = feeYlsfxmOutSer.getById(al_fyxh);
            al_fygb = feeYlsfxm.getFygb();
        }
        return al_fygb;
    }

    /**
     * 费用清单打印数据查询
     */
    public List<ExpensesPrintDataResp> findExpensesListByTemplate(ExpensesPrintReq req) {
        if (ExpensesPrintTemplateEnum.DETAIL_LIST.equals(req.getTemplate())) {
            return imFeeFymxDao.findExpensesListByTemplate1(req);
        } else if (ExpensesPrintTemplateEnum.ITEM_LIST.equals(req.getTemplate())) {
            List<ExpensesPrintDataResp> results = new ArrayList<>();
            List<ExpensesPrintDataResp> data = imFeeFymxDao.findExpensesListByTemplate2(req);
            //分组统计每种类型合并数据
            Map<String, List<ExpensesPrintDataResp>> map = data.stream().collect(Collectors.groupingBy(ExpensesPrintDataResp::getFygb));
            for (Map.Entry<String, List<ExpensesPrintDataResp>> entry : map.entrySet()) {
                List<ExpensesPrintDataResp> list = entry.getValue();
                ExpensesPrintDataResp total = list.stream().reduce(new ExpensesPrintDataResp(), (x, y) -> {
                    x.setYbbm("");
                    x.setDw("");
                    x.setDj(null);
                    x.setJe(x.getJe().add(y.getJe()));
                    x.setSl(x.getSl().setScale(2));
                    if (x.getZfje() == null) {
                        x.setZfje(BigDecimal.ZERO);
                    }
                    x.setZfje(x.getZfje().add(y.getZfje()));
                    if (x.getBxje() == null) {
                        x.setBxje(BigDecimal.ZERO);
                    }
                    x.setBxje(x.getBxje().add(y.getBxje()));
                    if (x.getZfje2() == null) {
                        x.setZfje2(BigDecimal.ZERO);
                    }
                    x.setZfje2(x.getZfje2().add(y.getZfje2()));
                    return x;
                });
                total.setJe(total.getJe().setScale(2, BigDecimal.ROUND_HALF_UP));
                total.setBxje(total.getBxje().setScale(2, BigDecimal.ROUND_HALF_UP));
                total.setZfje(total.getZfje().setScale(2, BigDecimal.ROUND_HALF_UP));
                total.setZfje2(total.getZfje2().setScale(2, BigDecimal.ROUND_HALF_UP));
                total.setFymc(entry.getKey() + " 合计:");
                total.setSl(null);
                results.addAll(list);
                results.add(total);
            }
            return results;
        } else if (ExpensesPrintTemplateEnum.ADVICE_LIST.equals(req.getTemplate())) {
            return imFeeFymxDao.findExpensesListByTemplate3(req);
        } else if (ExpensesPrintTemplateEnum.DATE_LIST.equals(req.getTemplate())) {
            return imFeeFymxDao.findExpensesListByTemplate4(req);
        }
        return new ArrayList<>();
    }

    /**
     * 查询超出出院结算时间
     *
     * @param zyh  住院号
     * @param cyrq 出院日期
     */
    public List<ImFeeFymx> getOutRangeList(Integer zyh, Timestamp cyrq) {
        return imFeeFymxDao.getOutRangeList(zyh, cyrq);
    }

    /**
     * 修改费用日期
     *
     * @param jlxh 记录序号
     * @param fyrq 费用日期
     */
    public void updateFyrq(Integer jlxh, Timestamp fyrq) {
        imFeeFymxDao.updateFyrq(jlxh, fyrq);
    }

    /**
     * 住院医保提前结算
     * @param zyh
     * @param carddata
     * @param cardtype
     * @param accountattr
     * @param ip
     * @param user
     * @return
     */
        public ReturnEntity<BalanceAccountsSettleResp> checkYbfy( Integer zyh,  String carddata, String cardtype, String accountattr,
                                           int jscs,String jsksrq,int wjsts,String ip, SysUser user) {
        BalanceAccountsSettleResp balanceAccountsSettleResp = new BalanceAccountsSettleResp();
        String jyksrq = jsksrq;
        ImHzry imHzry = imHzryDao.getById(zyh);
        String personspectag = "0";
        String Cwxx = "success";
        String YBSYPB = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBSYPB");
        Map<String, Object> res = new HashMap();
        BigDecimal zfje = new BigDecimal(0);
        BigDecimal qtys = new BigDecimal(0);
        String YBXZ = sysXtcsCacheSer.getCsz(user.getHospitalId(), "YBXZ");
        String ybsjxz = pubBrxzSer.getSjxzByBrxz(imHzry.getBrxz()) + "";
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
            if (cardtype == null) {
                throw BaseException.create("ERROR_SHYB_0045");
            }
            List<Map<String, Object>> jslist = imZyjsDao.getJscs(zyh);
/*            int jscs;
            if (jslist.size() > 0) {
                jscs = Integer.parseInt(jslist.get(0).get("JSCS") + "") + 1;
            } else {
                jscs = 1;
            }*/
            Map<String, Object> map = new HashMap<>();
            map.put("ZYH", zyh);
            map.put("JSLX", "5");
            map.put("ip", ip);
            map.put("orgld", orgid);
            map.put("carddata", carddata);
            map.put("cardtype", cardtype);
            map.put("JSRQ", "9999-12-31 00:00:00");
            map.put("jgid", user.getHospitalId());
            map.put("ygdm", user.getUserId());
            map.put("ygxm", user.getUserName());
            map.put("personspectag", personspectag);
            map.put("dbxm", dbxm);
            map.put("sclx","SN00");
            //费用明细上传
            SettleResultDTO sn01ResultDTO = offLineSettle.batchUploadFeeDetails_ZY(map, user);
            if (sn01ResultDTO.getCode() != 100 && sn01ResultDTO.getCode() != ServiceCode.CODE_OK) {
                List<Map<String, Object>> CwxxList = imHzryDao.getFycwxh(zyh);
                if (CwxxList.size() == 0) {
                    throw BaseException.create("ERROR_SHYB_0022", new String[]{sn01ResultDTO.getCode() + "--" + sn01ResultDTO.getMsg() + ""});
                }
                String jysj = CwxxList.get(0).get("jysj") + "";

                for (Map<String, Object> cwxxmap : CwxxList) {
                    String jysj_new = cwxxmap.get("jysj") + "";
                    if (jysj_new.equals(jysj)) {
                        Cwxx = Cwxx + cwxxmap.get("fymxjlxh") + "#" + cwxxmap.get("fyxh") + "-" + cwxxmap.get("ypcd") + "-"
                                + cwxxmap.get("fymc") + "-" + cwxxmap.get("cdmc") + "-" + cwxxmap.get("fhxx") + "||";
                    }
                }
                throw BaseException.create("ERROR_SHYB_0022", new String[]{sn01ResultDTO.getCode() + "--" + Cwxx + ""});
            } else {
                String gsrdh = "";

                jsksrq = jsksrq.substring(0, 10).replace("-", "");
                String jsjsrq = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
                String jsjsrq1 = jsjsrq.replace("-", "");
                Map<String, Object> map_ybksdm = dicKszdOutSer.getYbksdmzy(imHzry.getBrks());
                String ybks = map_ybksdm.isEmpty() ? "03" : map_ybksdm.get("ybks") + "";
                if ("".equals(imHzry.getGsrdh()) || imHzry.getGsrdh() == null) {
                    gsrdh = "-1";
                } else {
                    gsrdh = imHzry.getGsrdh();
                }
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
                si51_map.put("cyjsbz", "0");
                si51_map.put("jsksrq", jsksrq);
                si51_map.put("jsjsrq", jsjsrq1);
                si51_map.put("zyts", wjsts);
                si51_map.put("zyh", zyh);
                si51_map.put("deptid", ybks);
                si51_map.put("jzdyh", imHzry.getJzdyh());
                si51_map.put("xsywlx", "");
                si51_map.put("JSLX", "5");
                si51_map.put("jgdm", orgid);
                si51_map.put("ip", ip);
                si51_map.put("JSCS", jscs);
                si51_map.put("jsrq", jsjsrq1.substring(0, 10).replaceAll("-", ""));
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

                }else {
                    throw BaseException.create("ERROR_SHYB_0033", new String[]{sn51ResultDTO.getCode() + "--" + sn51ResultDTO.getMsg() + ""});
                }
            }
        }
        BeanUtils.copyProperties(imHzry, balanceAccountsSettleResp);
        ReturnEntity<PatientBalanceAccountsBaseInfoResp> resp = imHzrySer.queryPatientBalanceAccountsBaseInfo(zyh, user.getHospitalId(), jyksrq, "", personspectag, "5", zfje + "");
        balanceAccountsSettleResp.setZfy(resp.getData().getZyf().setScale(2, BigDecimal.ROUND_HALF_UP));
        balanceAccountsSettleResp.setZfje(zfje);
        balanceAccountsSettleResp.setYzje(resp.getData().getYkje().setScale(2, BigDecimal.ROUND_HALF_UP));
        balanceAccountsSettleResp.setYjk(imTbkkSer.queryJkje(zyh));
        //折扣金额
        balanceAccountsSettleResp.setJmje(resp.getData().getZkje().setScale(2, BigDecimal.ROUND_HALF_UP));
        balanceAccountsSettleResp.setJzdyh(jzdyh);
        balanceAccountsSettleResp.setJssqxh(jssqxh);

        return ReturnEntityUtil.success(balanceAccountsSettleResp);
        //return ReturnEntityUtil.success(Cwxx);
    }

    /**
     * 本地明细撤销
     *
     */
    public void undoMxzd(int zyh) {
        imFeeFymxDao.updateSnzy(zyh);
        imFeeFymxDao.updateImfymx(zyh);
    }

}
