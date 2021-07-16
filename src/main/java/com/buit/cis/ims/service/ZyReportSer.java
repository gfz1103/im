package com.buit.cis.ims.service;

import com.buit.cis.dctwork.service.ImHzryByDctworkSer;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.enums.FkfsEnum;
import com.buit.cis.ims.model.ImRzjz;
import com.buit.cis.ims.model.ImRzjzMx;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.RedisKeyHead;
import com.buit.file.IReportExportFileSer;
import com.buit.op.service.OpCzjlService;
import com.buit.system.model.DicSfxmlb;
import com.buit.system.request.PubFkfsModel;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.DicSfxmlbService;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.PubFkfsService;
import com.buit.system.utill.MedicineUtils;
import com.buit.utill.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.buit.utill.BUHISUtil.parseDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * 住院管理报表服务类<br>
 *
 * @author ZHOUHAISHENG
 */
@Service
public class ZyReportSer {
    @DubboReference
    public PubFkfsService  pubFkfsService;
    @Autowired
    public ImFeeFymxDao imFeeFymxDao;
    @Autowired
    public ImJzxxDao imJzxxDao;
    @Autowired
    public ImTbkkDao imTbkkDao;
    @Autowired
    public ImJkzfDao imJkzfDao;
    @Autowired
    public ImZyjsDao imZyjsDao;
    @Autowired
    public ImJszfDao imJszfDao;
    @Autowired
    public ImZfpjDao imzfpjDao;
    @Autowired
    public ImTbkkSer imTbkkSer;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BUHISUtil BUHISUtil;
    @Autowired
    private ImJzhzDao imJzhzDao;
    @Autowired
    private ImHzryDao imHzryDao;
    @Autowired
    private ImFeeFyhzDao imFeeFyhzDao;
    @Autowired
    private ImSrhzDao imSrhzDao;
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    @Autowired
    private ImHzrySer imHzrySer;
    @DubboReference
    private OpCzjlService opCzjlDao;
    @Autowired
    private ZyDicSer zyDicSer;
    @Autowired
    private ImFeeFymxSer imFeeFymxSer;
    @DubboReference
    private DicSfxmlbService dicSfxmlbService;
    @Autowired
    private  ImHzryByDctworkSer imHzryByDctworkSer;
    @Autowired
    private  ImRzjzDao imRzjzDao;
    @Autowired
    private ImRzjzMxDao imRzjzMxDao;
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    /**
     * 住院管理-日终结账（查询）
     *
     * @param jzrq
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doCreateJzrb(String jzrq, SysUser user) {
        Map<String, Object> ret = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> iws_pjxx1 = new HashMap<String, Object>();
        Map<String, Object> iws_pjxx2 = new HashMap<String, Object>();
        Map<String, Object> iws_pjxx3 = new HashMap<String, Object>();
        long iws_pjxx1_pjzs = 0;
        long iws_pjxx2_pjzs = 0;
        long iws_pjxx3_pjzs = 0;
        String qtysFb = "";
        String currentDay = sdf.format(new Date());
        String adt_jzrq = jzrq;
        String[] s = adt_jzrq.split(" |-|:");
        Calendar a = Calendar.getInstance();
        a.set(parseInt(s[0]), parseInt(s[1]) - 1, parseInt(s[2]),
                parseInt(s[3]), parseInt(s[4]), parseInt(s[5]));
        Date adt_jzrq_Date = a.getTime();
        Date ldt_End = a.getTime();
        if (!currentDay.equals(adt_jzrq.split(" ")[0])) {
            a.set(parseInt(s[0]), parseInt(s[1]) - 1, parseInt(s[2]), 0, 0, 0);
            ldt_End = a.getTime();
        }

        Map<String, Object> map_par = new HashMap<String, Object>();
        map_par.put("czgh", userid);
        map_par.put("jgid", jgid);
        map_par.put("adt_jzrq", adt_jzrq_Date);
        try {
            // bu为“0”时的 收费统计
            List<Map<String, Object>> ids_brxz = imFeeFymxDao.queryIdsBrxz(map_par);
            // bu为“0”时的 收费统计
            List<Map<String, Object>> ids_fkfs = imFeeFymxDao.queryIdsFkfs(map_par);

//			List<Map<String, Object>> ids_dbpb = dao.doSqlQuery(ids_dbpb_hql,map_par);// 保险判别为“0”时的 收费统计
            // 住院结算表单
            List<Map<String, Object>> ids_jsfp = imFeeFymxDao.queryIdsJsfp(map_par);


            // 住院缴款表单
            List<Map<String, Object>> ids_jksj = imFeeFymxDao.queryIdsJksj(map_par);

            // 住院结算作废表单
            List<Map<String, Object>>  ids_jszf= imFeeFymxDao.queryIdsJszf(map_par);
            // 住院缴款作废表单
            List<Map<String, Object>> ids_jkzf = imFeeFymxDao.queryIdsJkzf(map_par);
            // 住院付款表单
            List<Map<String, Object>> ids_jsfp_fk = imFeeFymxDao.queryIdsJsfpFk(map_par);
            // 住院付款作废表单
            List<Map<String, Object>> ids_jszf_fk = imFeeFymxDao.queryIdsJszfFk(map_par);
            // 住院预缴金找退
            List<Map<String, Object>> ids_jkzt = imFeeFymxDao.queryIdsJkzt(map_par);
            if (ids_jsfp.size() + ids_jksj.size() + ids_jszf.size()
                    + ids_jkzf.size() + ids_jsfp_fk.size() + ids_jszf_fk.size() == 0) {
                return null;
            }
            double lc_tyjj = 0d; // 退预缴金
            double lc_yjje = 0d; // 预缴缴金
            double lc_cysr = 0d; // 出院收入
            double ljhbwc = 0d; // 累计货币误差
            int ll_fpzs = 0; // 发票张数
            double lc_xjje = 0d; // 现金
            double lc_srje = 0d; // 货币误差
            double lc_yjxj = 0d; // 预缴金额
            double lc_qtje = 0d; // 其他金额
            double lc_zffpje=0d;// 作废发票金额
            int lc_zffpzs=0;// 作废发票张数
            double lc_zfsjje=0d;// 作废收据金额
            int lc_zfsjzs=0;// 作废收据张数
            String lc_zffp="";//作废发票
            double syb = 0.00;
            double sybzf = 0.00;

            int ll_tjks = 0;//退预交款张数
            //结算发票
            for (Map<String, Object> map_ids_jsfp : ids_jsfp) {
                String ll_zyh = map_ids_jsfp.get("ZYH") + "";
                Map<String, Object> sybPar = new HashMap<String, Object>();
                sybPar.put("ZYH", Long.parseLong(ll_zyh));
//				Map<String, Object> sybMap = dao
//						.doLoad("select sum(YLFZE-GRZFJE) as SYB from YB_ZYJS where ZYH=:ZYH",
//								sybPar);
//				if (sybMap != null && sybMap.size() > 0) {
//					if (sybMap.get("SYB") != null) {
//						syb += Double.parseDouble(sybMap.get("SYB") + "");
//					}
//				}
                String ll_jscs = map_ids_jsfp.get("JSCS") + "";
//                lc_tyjj = lc_tyjj
//                        + (((map_ids_jsfp.get("JKHJ") != null) ? Double
//                        .parseDouble(map_ids_jsfp.get("JKHJ") + "")
//                        : 0d));
                if (Double.parseDouble(map_ids_jsfp.get("ZFHJ")+"")>=
                Double.parseDouble(map_ids_jsfp.get("JKHJ")+"")){
                    lc_cysr = lc_cysr
                            + (((map_ids_jsfp.get("FYHJ") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("FYHJ") + "")
                            : 0d))
                            + (((map_ids_jsfp.get("JMJE") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("JMJE") + "")
                            : 0d))
                            - (((map_ids_jsfp.get("JKHJ") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("JKHJ") + "")
                            : 0d));
                }else{
                    lc_cysr = lc_cysr
                            + (((map_ids_jsfp.get("FYHJ") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("FYHJ") + "")
                            : 0d))
                            + (((map_ids_jsfp.get("JMJE") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("JMJE") + "")
                            : 0d))
                            - (((map_ids_jsfp.get("ZFHJ") != null) ? Double
                            .parseDouble(map_ids_jsfp.get("ZFHJ") + "")
                            : 0d));
                }

                ll_fpzs++;
                List<Map<String, Object>> ids_jsfp_fk_AfterFilter = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> map_ids_jsfp_fk : ids_jsfp_fk) {
                    if ((map_ids_jsfp_fk.get("ZYH") + "").equals(ll_zyh)
                            && (ll_jscs
                            .equals(map_ids_jsfp_fk.get("JSCS") + ""))) {
                        ids_jsfp_fk_AfterFilter.add(map_ids_jsfp_fk);
                    }
                }
                for (Map<String, Object> map_ids_jsfp_fk_AfterFilter : ids_jsfp_fk_AfterFilter) {
                    Integer fklb = wf_get_fklb(MapAttributeUtils.getInteger("FKFS", map_ids_jsfp_fk_AfterFilter));
                    if (fklb == 7) {
                        lc_xjje = lc_xjje + MapAttributeUtils.getDouble("FKJE", map_ids_jsfp_fk_AfterFilter);
                    } else if (fklb == 4) {
                        lc_srje = lc_srje + MapAttributeUtils.getDouble("FKJE", map_ids_jsfp_fk_AfterFilter);
                    } else {
                        lc_qtje = lc_qtje + MapAttributeUtils.getDouble("FKJE", map_ids_jsfp_fk_AfterFilter);
                    }
                }


            }

            //发票作废
            for (Map<String, Object> map_ids_jszf : ids_jszf) {
                String ll_zyh = map_ids_jszf.get("ZYH") + "";
                Map<String, Object> sybPar = new HashMap<String, Object>();
                sybPar.put("ZYH", Long.parseLong(ll_zyh));
//				Map<String, Object> sybMap = dao
//						.doLoad("select sum(YLFZE-GRZFJE) as SYB from YB_ZYJS where ZYH=:ZYH",
//								sybPar);
//				if (sybMap != null && sybMap.size() > 0) {
//					if (sybMap.get("SYB") != null) {
//						sybzf += Double.parseDouble(sybMap.get("SYB") + "");
//					}
//				}
                String ll_jscs = map_ids_jszf.get("JSCS") + "";
                // 累加作废发票数量，保存作废发票号码
                iws_pjxx1_pjzs++;
                //作废 发票张数
                lc_zffpzs++;
                iws_pjxx1.put(iws_pjxx1_pjzs + "", map_ids_jszf.get("FPHM")
                        + "");
                lc_zffp=lc_zffp+"" + map_ids_jszf.get("FPHM");

                // 取结算作废退预缴金并将其累加
//                lc_tyjj = lc_tyjj + MapAttributeUtils.getDouble("JKHJ", map_ids_jszf);
//                lc_zffpje=lc_zffpje+ MapAttributeUtils.getDouble("JKHJ", map_ids_jszf);


                // 累加出院收入
                if (Double.parseDouble(map_ids_jszf.get("ZFHJ")+"")<=
                        Double.parseDouble(map_ids_jszf.get("JKHJ")+"")){
                    lc_cysr = lc_cysr + MapAttributeUtils.getDouble("FYHJ", map_ids_jszf) + MapAttributeUtils.getDouble("JMJE", map_ids_jszf)-MapAttributeUtils.getDouble("JKHJ", map_ids_jszf);
                }else{
                    lc_cysr = lc_cysr + MapAttributeUtils.getDouble("FYHJ", map_ids_jszf) + MapAttributeUtils.getDouble("JMJE", map_ids_jszf)-MapAttributeUtils.getDouble("ZFHJ", map_ids_jszf);
                }

                List<Map<String, Object>> ids_jszf_fk_AfterFilter = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> map : ids_jszf_fk) {
                    if ((map.get("ZYH") + "").equals(ll_zyh)
                            && (ll_jscs.equals(map.get("JSCS") + ""))) {
                        ids_jszf_fk_AfterFilter.add(map);
                    }
                }
                for (Map<String, Object> map : ids_jszf_fk_AfterFilter) {
                    Integer fklb = wf_get_fklb(MapAttributeUtils.getInteger("FKFS", map));

                    if (fklb == 7) {
                        lc_xjje = lc_xjje - MapAttributeUtils.getDouble("FKJE", map);
                    } else if (fklb == 4) {
                        lc_srje = lc_srje - MapAttributeUtils.getDouble("FKJE", map);
                    } else {
                        lc_qtje = lc_qtje - MapAttributeUtils.getDouble("FKJE", map);
                    }
                }
            }

            Long ll_sjzs = 0L;
            double lc_qtjk = 0;
            double lc_jkhj = 0;
            //预缴金
            for (Map<String, Object> map_ids_jksj : ids_jksj) {
                Integer fklb = wf_get_fklb(MapAttributeUtils.getInteger("JKFS", map_ids_jksj));
                if (fklb != 3) {
                    ll_sjzs++;
                }
                if (fklb == 7) {
                    lc_yjxj = lc_yjxj + MapAttributeUtils.getDouble("JKJE", map_ids_jksj);
                } else {
                    lc_qtjk = lc_qtjk + MapAttributeUtils.getDouble("JKJE", map_ids_jksj);
                }
                lc_jkhj = lc_jkhj + MapAttributeUtils.getDouble("JKJE", map_ids_jksj);
                lc_yjje = lc_yjje + MapAttributeUtils.getDouble("JKJE", map_ids_jksj);
            }
            //预缴金收据作废
            for (Map<String, Object> map : ids_jkzf) {
                Integer fklb = wf_get_fklb(MapAttributeUtils.getInteger("JKFSJKFS", map));
                iws_pjxx2_pjzs++;
                String pjzs = iws_pjxx2_pjzs + "";
                iws_pjxx2.put(pjzs, map.get("SJHM") + "");
                if (fklb == 7) {
                    lc_yjxj = lc_yjxj + MapAttributeUtils.getDouble("JKJE", map);
                } else {
                    lc_qtjk = lc_qtjk + MapAttributeUtils.getDouble("JKJE", map);
                }
                lc_jkhj = lc_jkhj + MapAttributeUtils.getDouble("JKJE", map);
                lc_zfsjzs=lc_zfsjzs+1;
                lc_zfsjje=lc_zfsjje+ MapAttributeUtils.getDouble("JKJE", map);

            }
            //预缴金找退
            for (Map<String, Object> map_ids_jszt : ids_jkzt){
                Integer fklb = wf_get_fklb(MapAttributeUtils.getInteger("JKFS", map_ids_jszt));
                if (fklb == 7) {
                    lc_yjxj = lc_yjxj + MapAttributeUtils.getDouble("JKJE", map_ids_jszt);
                } else {
                    lc_qtjk = lc_qtjk + MapAttributeUtils.getDouble("JKJE", map_ids_jszt);
                }
                lc_jkhj = lc_jkhj + MapAttributeUtils.getDouble("JKJE", map_ids_jszt);
                lc_tyjj = lc_tyjj + MapAttributeUtils.getDouble("JKJE", map_ids_jszt);
                ll_tjks++;
            }

            // 算结算票据和缴款收据 票据序列
            ids_jsfp = imFeeFymxDao.queryIdsJsfpWithOrderBy(map_par);
            String ls_pjxl_fp = "";
            if (ids_jsfp.size() > 0) {
                String ls_begin_pjhm = ids_jsfp.get(0).get("FPHM") + "";
                String ls_curr_pjhm = ids_jsfp.get(0).get("FPHM") + "";
                String ls_end_pjhm = "";
                for (int i = 0; i < ids_jsfp.size(); i++) {
                    if (!(ids_jsfp.get(i).get("FPHM") + "")
                            .equals(ls_curr_pjhm)) {
                        ls_end_pjhm = (ids_jsfp.get(i - 1).get("FPHM") + "");
                        ls_pjxl_fp = wf_addtopjxl(ls_pjxl_fp, ls_begin_pjhm,
                                ls_end_pjhm);
                        ls_begin_pjhm = ids_jsfp.get(i).get("FPHM") + "";
                        ls_curr_pjhm = ids_jsfp.get(i).get("FPHM") + "";
                    }
                    ls_curr_pjhm = wf_addpjhm(ls_curr_pjhm); // 票据号码加1
                }
                ls_end_pjhm = ids_jsfp.get(ids_jsfp.size() - 1).get("FPHM")
                        + "";
                ls_pjxl_fp = wf_addtopjxl(ls_pjxl_fp, ls_begin_pjhm,
                        ls_end_pjhm);
            }

            ids_jksj = imFeeFymxDao.queryIdsJksjWithOrderBy(map_par);
            String ls_pjxl_sj = "";
            if (ids_jksj.size() > 0) {
                String ls_begin_pjhm = ids_jksj.get(0).get("SJHM") + "";
                String ls_curr_pjhm = ids_jksj.get(0).get("SJHM") + "";
                String ls_end_pjhm = "";
                for (int i = 0; i < ids_jksj.size(); i++) {
                    if (!(ids_jksj.get(i).get("SJHM") + "")
                            .equals(ls_curr_pjhm)) {
                        ls_end_pjhm = (ids_jksj.get(i - 1).get("SJHM") + "");
                        ls_pjxl_sj = wf_addtopjxl(ls_pjxl_sj, ls_begin_pjhm,
                                ls_end_pjhm);
                        ls_begin_pjhm = ids_jksj.get(i).get("SJHM") + "";
                        ls_curr_pjhm = ids_jksj.get(i).get("SJHM") + "";
                    }
                    ls_curr_pjhm = wf_addpjhm(ls_curr_pjhm); // 票据号码加1
                }
                ls_end_pjhm = ids_jksj.get(ids_jksj.size() - 1).get("SJHM")
                        + "";
                ls_pjxl_sj = wf_addtopjxl(ls_pjxl_sj, ls_begin_pjhm,
                        ls_end_pjhm);
            }
            // 取退预缴款收据张数
//            map_par.put("ldt_End", ldt_End);
//            map_par.remove("adt_jzrq");
//            //取退预缴款收据张数
//            Map<String, Object> map_ll_tjks = imFeeFymxDao.queryTjks(map_par);
//            Integer ll_tjks = MapAttributeUtils.getInteger("TJZS", map_ll_tjks);

            // 将数据写入日终结帐表单
            Map<String, Object> pjxx = new HashMap<String, Object>();
            pjxx.put("JZRQ", adt_jzrq); // 日终结帐表单JZRQ[1] = adt_jzrq // 结帐日期
            pjxx.put("CZGH", userid); // 日终结帐表单CZGH[1] = 当前操作工号 // 操作工号
//			pjxx.put("CYSR", lc_cysr); // 日终结帐表单cysr[1] = lc_cysr // 出院收入
            pjxx.put("YJJE", lc_yjje); // 日终结帐表单yjje[1] = lc_yjxj// 预缴金额
//            pjxx.put("YJJE", lc_jkhj); // 日终结帐表单yjje[1] = lc_yjxj// 预缴金额
            pjxx.put("YJXJ", lc_yjxj); // 日终结帐表单yjxj[1] = lc_yjxj // 预缴现金
            pjxx.put("YJZP", 0); // 日终结帐表单yjzp[1] = 0 // 预缴支票
            pjxx.put("YJQT", lc_qtjk); // 日终结帐表单yjqt[1] = 0 // 预缴其它
            pjxx.put("YJYHK", 0); // 日终结帐表单yjyhk[1] = 0 // 预缴银行卡
            pjxx.put("KBJE", 0); // 日终结帐表单kbje[1] = 0 // 空白支票金额
            pjxx.put("QZPJ", ls_pjxl_fp); // 日终结帐表单qzpj[1] = ls_pjxl_fp //
            // 起止发票序列
            pjxx.put("QZSJ", ls_pjxl_sj); // 日终结帐表单qzsj[1] = ls_pjxl_sj //
            // 起止收据序列
            pjxx.put("FPZS", ll_fpzs); // 日终结帐表单fpzs[1] = ll_fpzs // 发票张数
            pjxx.put("SJZS", ll_sjzs); // 日终结帐表单sjzs[1] = ll_sjzs
//			pjxx.put("YSJE",lc_cysr+lc_jkhj-lc_tyjj);
//			pjxx.put("YSJE", lc_xjje + lc_jkhj + lc_srje + lc_qtje); // 日终结帐表单ysje[1]
            // =
            // lc_xjje
            // +
            // lc_srje
            // //
            // 应收金额
            pjxx.put("YSXJ",  new BigDecimal(lc_xjje + lc_yjxj).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); // 日终结帐表单ysxj[1] = lc_xjje +
            // lc_yjxj // 应收现金
            pjxx.put("YSZP", 0); // 日终结帐表单yszp[1] = 0 // 应收支票
            pjxx.put("YSQT", new BigDecimal(lc_qtje + lc_qtjk).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); // 日终结帐表单ysqt[1] = 0 // 应收其它
            pjxx.put("YSYH", 0); // 日终结帐表单ysyh[1] = 0 // 应收优惠
            pjxx.put("YSYHK", 0); // 日终结帐表单ysyhk[1] = 0 // 应收银行卡
            pjxx.put("SRJE", lc_srje); // 日终结帐表单srje[1] = lc_srje // 舍入金额
            pjxx.put("ZPZS", 0); // 日终结帐表单zpzs[1] = ll_zpzs // 支票张数
            pjxx.put("QTZS", 0); // 日终结帐表单qtzs[1] = 0 // 其它张数
            pjxx.put("TYJJ", new BigDecimal(lc_tyjj).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); // 日终结帐表单tyjj[1] = lc_tyjj // 退预缴金
            pjxx.put("TJKS", ll_tjks); // 日终结帐表单tjks[1] = ll_tjks // 退预缴款张数
            pjxx.put("KBZP", 0); // 日终结帐表单kbzp[1] = 0 // 空白支票张数
            pjxx.put("JGID", jgid); // 日终结帐表单jgid[1] = gl_jgid
            pjxx.put("TPJE", 0); // 日终结帐表单TPJE[1] = 0
            pjxx.put("ZFSJZS", lc_zfsjzs); // 日终结帐表单 作废收据张数
            pjxx.put("ZFSJJE", lc_zfsjje); // 日终结帐表单 作废收据金额
            pjxx.put("ZFFPJE", lc_zffpje); // 日终结帐表单 作废发票
            pjxx.put("ZFFPZS", lc_zffpzs); // 日终结帐表单 作废发票张数
            pjxx.put("ZFFP", lc_zffp); // 日终结帐表单 作废发票金额


//			double xjje=0.0;//现金金额
//			double zsje=0.0;//总收金额
//			double jzje=0.0;//记账金额
//			xjje=lc_xjje + lc_yjxj;
//			qtysFb="现金:" +xjje+" ";
            String jzjeSt = "0.00";

            String POSZJ = "0.00";//POS总金额
            String YBZJ = "0.00";//医保总金额
//			//保险判别不为“0”付款方式
            if (ids_fkfs != null && ids_fkfs.size() != 0) {
                for (int i = 0; i < ids_fkfs.size(); i++) {
                    if ("8".equals(MapAttributeUtils.getInteger("FKFS", ids_fkfs.get(i))) || "17".equals(MapAttributeUtils.getInteger("FKFS", ids_fkfs.get(i))) || "22".equals(MapAttributeUtils.getInteger("FKFS", ids_fkfs.get(i)))
                            || "27".equals(ids_fkfs.get(i).get("FKFS") + "")) {
                        POSZJ = String.format("%1$.2f", new Double(0.00) + MapAttributeUtils.getDouble("FKJE", ids_fkfs.get(i)));

                    }
                }
                for (int i = 0; i < ids_fkfs.size(); i++) {
//					 if("现金".equals(ids_fkfs.get(i).get("FKMC")+"")){
//						 qtysFb = qtysFb +ids_fkfs.get(i).get("FKMC")+ ":"
//									+ String.format("%1$.2f",Double.parseDouble(ids_fkfs.get(i).get("FKJE")+"")+xjje)
//									+ " ";
//					 }else{
                    qtysFb = qtysFb + ids_fkfs.get(i).get("FKMC") + ":"
                            + String.format("%1$.2f", ids_fkfs.get(i).get("FKJE"))
                            + " ";
//					 }
                    //累加货币误差
                    if ("9".equals(ids_fkfs.get(i).get("FKFS") + "")) {
                        ljhbwc = ljhbwc + MedicineUtils.parseDouble(ids_fkfs.get(i).get("FKJE"));
                    }
                }
            }
            pjxx.put("YSJE", new BigDecimal(lc_cysr + lc_yjje + lc_tyjj - ljhbwc).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());//应收金额(由于减去了减免金额和货币误差,实际上是实收金额。 )
            pjxx.put("CYSR", new BigDecimal(lc_cysr - ljhbwc).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());  // 出院收入
//			//保险判别不为“0”
            if (ids_brxz != null && ids_brxz.size() != 0) {
                for (int i = 0; i < ids_brxz.size(); i++) {
                    if ("4001".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "4001".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "6023".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "45".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "39".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "36".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "35".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "34".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "33".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "32".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "28".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "19".equals(ids_brxz.get(i).get("BRXZ") + "")
                            || "23".equals(ids_brxz.get(i).get("BRXZ") + "")//离休
                            || "29".equals(ids_brxz.get(i).get("BRXZ") + "")) {

                        YBZJ = String.format("%1$.2f", new Double(YBZJ) + (MapAttributeUtils.getDouble("FYHJ", ids_brxz.get(i))) - MapAttributeUtils.getDouble("ZFHJ", ids_brxz.get(i)));
                    }
                }

                for (int i = 0; i < ids_brxz.size(); i++) {
                    if (Integer.parseInt(ids_brxz.get(i).get("DBPB") + "") == 0) {
                        jzjeSt = String.format("%1$.2f", new Double(jzjeSt) + (MapAttributeUtils.getDouble("FYHJ", ids_brxz.get(i))) - MapAttributeUtils.getDouble("ZFHJ", ids_brxz.get(i)));

//						 jzjeSt=String.format("%1$.2f", jzje);
                    } else {
                        qtysFb = qtysFb + ids_brxz.get(i).get("XZMC") + ":"
                                + String.format("%1$.2f", (MapAttributeUtils.getDouble("FYHJ", ids_brxz.get(i)) - MapAttributeUtils.getDouble("ZFHJ", ids_brxz.get(i))));

                    }
                }
                qtysFb = qtysFb + " " + "记账 :" + jzjeSt + " ";
                qtysFb = qtysFb + "POS总金额：" + POSZJ + ",医保总金额:" + YBZJ + " ";
            }
//			//保险判别为“0”
//			if (ids_dbpb  != null && ids_dbpb .size() != 0) {
//				 for(int i=0;i<ids_dbpb.size();i++){
//					 zsje=parseDouble(ids_dbpb.get(0).get("FKJE")+ "");
//					 jzje=zsje-xjje;
//					 jzjeSt=String.format("%1$.2f", jzje);
//				 }
//				 qtysFb = qtysFb+" " + "记账 :"
//							+ jzjeSt
//							+ " ";
//			}
            pjxx.put("qtysFb", qtysFb);


            // 写票据信息
            Map<String, Object> zfpj = wf_WriteBillInfo(iws_pjxx1_pjzs,
                    iws_pjxx1, iws_pjxx2_pjzs, iws_pjxx2, iws_pjxx3_pjzs,
                    iws_pjxx3);
            pjxx.putAll(zfpj);
            pjxx.put("SYB", (syb - sybzf));
            ret.put("JZRQ", adt_jzrq_Date);
            ret.put("jzxx", pjxx);
            ret.put("iws_pjxx1", iws_pjxx1);
            ret.put("iws_pjxx2", iws_pjxx2);
            ret.put("iws_pjxx3", iws_pjxx3);
            ret.put("iws_pjxx1_pjzs", iws_pjxx1_pjzs);
            ret.put("iws_pjxx2_pjzs", iws_pjxx2_pjzs);
            ret.put("iws_pjxx3_pjzs", iws_pjxx3_pjzs);
            ret.put("SYB", (syb - sybzf));

        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
        }

        try {
            redisTemplate.opsForValue().set(RedisKeyHead.zyCheckout + user.getHospitalId().toString() + "-" + user.getUserId(), objectMapper.writeValueAsString(ret), 24, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
        }

        return ReturnEntityUtil.success(ret);
    }

    /**
     * 获取付款类别
     *
     * @param al_fkfs
     * @return
     */
    private Integer wf_get_fklb(Integer al_fkfs) {
        Integer ll_fklb = 0;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_fkfs", al_fkfs);
        parameters.put("SYLX", 2);

        String hql = "SELECT a.FKLB as FKLB FROM PUB_FKFS a WHERE a.FKFS = :al_fkfs AND a.SYLX = 2";
        try {
            List<Map<String, Object>> ret = pubFkfsService.getPubFkfsInfo(parameters);
            if (ret.size() == 0) {
                ll_fklb = 0;
            } else {
                ll_fklb = MapAttributeUtils.getInteger("FKLB", ret.get(0));
            }
            if (ll_fklb == 0) {
                ll_fklb = 1;
            }
        } catch (Exception e) {
            //表单查询失败
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
        }
        return ll_fklb;
    }

    /**
     * 写票据信息
     *
     * @param iws_pjxx1_pjzs
     * @param iws_pjxx1
     * @param iws_pjxx2_pjzs
     * @param iws_pjxx2
     * @param iws_pjxx3_pjzs
     * @param iws_pjxx3
     * @return
     */
    private Map<String, Object> wf_WriteBillInfo(long iws_pjxx1_pjzs,
                                                 Map<String, Object> iws_pjxx1, long iws_pjxx2_pjzs,
                                                 Map<String, Object> iws_pjxx2, long iws_pjxx3_pjzs,
                                                 Map<String, Object> iws_pjxx3) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Long i;
        String ls_zffp = ""; // 作废发票号码
        String ls_zfsj = ""; // 作废收据号码
        // String ls_tphm = ""; // 退票号码
        String ls_Temp = "";

        // 作废发票(aws_pjxx[1])
        for (i = 1L; i <= iws_pjxx1_pjzs; i++) {
            if ("".equals(ls_Temp)) {
                ls_Temp = iws_pjxx1.get(i + "") + "";
                ls_zffp += iws_pjxx1.get(i + "") + "";
            } else if ((ls_Temp + " " + iws_pjxx1.get(i + "") + "").length() > 17) {
                ls_Temp = iws_pjxx1.get(i + "") + "";
                ls_zffp += " " + iws_pjxx1.get(i + "") + "";
            } else {
                ls_Temp += " " + iws_pjxx1.get(i + "") + "";
                ls_zffp += " " + iws_pjxx1.get(i + "") + "";
            }
        }

        // 作废收据(aws_pjxx[2])
        ls_Temp = "";
        for (i = 1L; i <= iws_pjxx2_pjzs; i++) {
            if ("".equals(ls_Temp)) {
                ls_Temp = iws_pjxx2.get(i + "") + "";
                ls_zfsj += iws_pjxx2.get(i + "") + "";
            } else if ((ls_Temp + " " + iws_pjxx2.get(i + "") + "").length() > 17) {
                ls_Temp = iws_pjxx2.get(i + "") + "";
                ls_zfsj += " " + iws_pjxx2.get(i + "") + "";
            } else {
                ls_Temp += " " + iws_pjxx2.get(i + "") + "";
                ls_zfsj += " " + iws_pjxx2.get(i + "") + "";
            }
        }

        String jsfp = ls_zffp; // 日终结帐表单中结算发票的内容 = ls_zffp
        String jksj = ls_zfsj; // 日终结帐表单中缴款收据的内容 = ls_zfsj
        ret.put("JSFP", jsfp);
        ret.put("JKSJ", jksj);
        return ret;
    }

    private String wf_addtopjxl(String as_pjxl, String as_begin, String as_end) {
        if (as_begin.equals(as_end)) {
            as_pjxl += " " + as_begin;
        } else {
            as_pjxl += " " + as_begin + "-" + as_end;
        }
        return as_pjxl;
    }

    private String wf_addpjhm(String as_pjhm) {
        String ls_string = "0000000000000000000000000000000";
        String ls_char = "";
        String ls_pjhm = "";
        int ls_i = 0;
        int fphm_length = 0;
        for (int i = as_pjhm.length() - 1; i >= 0; i--) {
            if (!(as_pjhm.charAt(i) >= '0' && as_pjhm.charAt(i) <= '9')) {
                ls_i = i + 1;
                break;
            }
        }
        fphm_length = as_pjhm.length() - ls_i; // 发票号码数字部分的长度
        ls_char = as_pjhm.substring(0, ls_i);
        ls_pjhm = as_pjhm.substring(ls_i);
        ls_pjhm = String.valueOf(Long.valueOf(ls_pjhm) + 1);
        ls_pjhm = ls_char
                + ls_string.substring(0, fphm_length - ls_pjhm.length())
                + ls_pjhm;
        return ls_pjhm.trim();
    }

    /**
     * @param o
     * @return
     * @author caijy
     * @createDate 2012-11-21
     * @description 数据转换成int
     * @updateInfo
     */
    public int parseInt(Object o) {
        if (o == null || "null".equals(o)) {
            return 0;
        }
        return Integer.parseInt(o + "");
    }

    /**
     * 判断本日是否已做过结帐
     *
     * @param jzrq
     * @param user
     * @return
     */
    public ReturnEntity<DoIsreckonResp> doIsreckon(String jzrq, SysUser user) {
        DoIsreckonResp doIsreckonResp = new DoIsreckonResp();
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] s = jzrq.split("-| |:");
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, parseInt(s[0]));
        a.set(Calendar.MONTH, parseInt(s[1]) - 1);
        a.set(Calendar.DATE, parseInt(s[2]));
        a.set(Calendar.HOUR_OF_DAY, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        Date begin = a.getTime();
        a.add(Calendar.DAY_OF_YEAR, 1);
        Date end = a.getTime();

        Map<String, Object> map_par = new HashMap<String, Object>();
        map_par.put("czgh", userid);
        map_par.put("jgid", jgid);
        map_par.put("begin", begin);
        map_par.put("end", end);
        try {
            Integer l = imRzjzDao.quertCount(map_par);
            doIsreckonResp.setRreckon(l > 0);
            map_par.remove("begin");
            map_par.remove("end");
            Map<String, Object> map_max = imRzjzDao.queryMx(map_par);
            if (null!=map_max && map_max.get("JZRQ") != null) {
                doIsreckonResp.setJzrq(Time(map_max.get("JZRQ").toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00048");
        }
        return ReturnEntityUtil.success(doIsreckonResp);
    }

    /**
     * 住院管理-日终结账（检验是否可以结账）
     *
     * @param ls_idt_jzrq
     * @param user
     * @return
     */
    public ReturnEntity doWfCheck(String ls_idt_jzrq, SysUser user) {
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Integer ll_Count = 0;
        String[] s = ls_idt_jzrq.split("-| |:");
        Calendar cal = Calendar.getInstance();
        cal.set(parseInt(s[0]), parseInt(s[1]) - 1, parseInt(s[2]),
                parseInt(s[3]), parseInt(s[4]), parseInt(s[5]));
        Date idt_jzrq = cal.getTime();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("is_UserId", userid);
        map.put("idt_jzrq", idt_jzrq);
        map.put("gl_jgid", jgid);
        if (ls_idt_jzrq.split(" ")[0].equals(sdf.format(new Date()))) {
            try {
                //查询是否有缴款记录
                ll_Count = imTbkkDao.checkCount(map);
                if (ll_Count > 0) {
                    return ReturnEntityUtil.error("false");
                }
                //查询是否有住院缴款作废记录
                ll_Count = imJkzfDao.checkCount(map);
                if (ll_Count > 0) {
                    return ReturnEntityUtil.error("false");
                }

                //查询是否有住院结算记录
                ll_Count = imZyjsDao.checkCount(map);
                if (ll_Count > 0) {
                    return ReturnEntityUtil.error("false");
                }
                //查询是否有结算作废记录
                ll_Count = imJszfDao.checkCount(map);
                if (ll_Count > 0) {
                    return ReturnEntityUtil.error("false");
                }
            } catch (Exception e) {

                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
            }
        }

        return ReturnEntityUtil.success();
    }

    /**
     * 取消日报查询
     *
     * @param jzrq
     * @param user
     * @return
     */
    public ReturnEntity doQueryCancelCommit(String jzrq, SysUser user) {
        // 用户的机构名称
        Integer jgid = user.getHospitalId();
        Integer userId = user.getUserId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("jgid", jgid);
        parameters.put("czgh", userId);
        try {
            List<Map<String, Object>> list = imRzjzDao.queryCancelCommit(parameters);

            if (list.size() > 0) {
                return ReturnEntityUtil.success(list);
            }
        } catch (Exception e) {
            //查询日报信息出错
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00049");

        }
        return ReturnEntityUtil.success();
    }

    /**
     * 日报查询
     *
     * @param jzrq
     * @param user
     * @return
     */
    public List<Map<String,Object>> doQueryCommit(SysUser user) {
        // 用户的机构名称
        Integer jgid = user.getHospitalId();
        Integer userId = user.getUserId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("jgid", jgid);
        parameters.put("czgh", userId);

        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = imRzjzDao.queryCommit(parameters);

            if (list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            //查询日报信息出错
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00049");
        }
        return list;
    }

    /**
     * 住院管理-日终结账（取消日报）
     *
     * @param jzrq
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity doCancelCommit(String jzrq, SysUser user) {
        // 用户的机构名称
        Integer jgid = user.getHospitalId();
        Integer userId = user.getUserId();
        Map<String, Object> parameters = new HashMap<String, Object>();

        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date jzdate = null;
        try {
            jzdate = sdfdatetime.parse(jzrq);
        } catch (ParseException e1) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00050");
        }
        parameters.put("jgid", jgid);
        parameters.put("czgh", userId);
        parameters.put("jzrq", jzdate);
        try {
//            List<Timestamp> list = imJzxxDao.quereyHz(parameters);
//            if (list.size() == 0) {
//                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00051");
//            }
            Map<String, Object> jzmaxparameters = new HashMap<String, Object>();
            jzmaxparameters.put("jgid", jgid);
            jzmaxparameters.put("czgh", userId);
            //查询最大结账日期
            List<Map<String, Object>> list1 = imRzjzDao.queryMaxJzrq(jzmaxparameters);

            if (list1.size() > 0) {
                if (!jzrq.equals(Time(list1.get(0).get("JZRQ") + ""))) {
                    //当前记录不是最后一条结账信息,不能取消!
                    throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00052");
                }
            }
            imRzjzMxDao.doCancelCommit(parameters);
            imRzjzDao.doCancelCommit(parameters);
            imTbkkDao.doCancelCommit(parameters);
            imJkzfDao.doCancelCommit(parameters);
            imZyjsDao.doCancelCommit(parameters);
            imJszfDao.doCancelCommit(parameters);


        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00053");
        }


        return ReturnEntityUtil.success();
    }

    /**
     * 住院管理-日终汇总（查询校验-产生数据前的验证）
     *
     * @param hzrq
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doGenerateVerification(String hzrq, SysUser user) {
        ReturnEntity<Map<String, Object>> returnEntity = new ReturnEntity();
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date id_dqrq = null;
        Date idt_hzrq = null;
        Date ldt_CurrentDateTime = null;
        if (StringUtils.isNotBlank(hzrq)) {
            try {
                id_dqrq = sdfdate.parse(hzrq);
                ldt_CurrentDateTime = sdfdate.parse(sdfdate.format(new Date()));
                if (id_dqrq.getTime() > ldt_CurrentDateTime.getTime()) {
                    //汇总日期不能大于当前日期
                    res.put("code", 600);
                    res.put("idt_hzrq", id_dqrq);
                    returnEntity.setData(res);
                    return returnEntity;
                }
                if (id_dqrq.getTime() == ldt_CurrentDateTime.getTime()) {
                    idt_hzrq = sdfdatetime
                            .parse(sdfdatetime.format(new Date()));
                } else {
                    idt_hzrq = sdfdatetime.parse(sdfdate.format(id_dqrq)
                            + " 23:59:59");
                }
                Map<String, Object> idt_LastDate = new HashMap<String, Object>();
                wf_IsGather(idt_hzrq, idt_LastDate, user);
                if (idt_LastDate.get("idt_LastDate") == null) {
                    res.put("idt_hzrq", sdfdate.format(idt_hzrq));

                    res.put("code", 600);
                    res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                    returnEntity.setData(res);
                    return returnEntity;


                } else {
                    int days = DateUtils.getDifferDays(idt_hzrq, sdfdatetime
                            .parse(sdfdatetime.format(idt_LastDate
                                    .get("idt_LastDate"))));
                    if (days < 0) {

                        res.put("code", 601);
                        res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                        res.put("idt_LastDate", sdfdate.format(idt_LastDate.get("idt_LastDate")));
                        returnEntity.setData(res);
                        return returnEntity;

                    } else if (days == 0) {
                        res.put("code", 602);
                        res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                        res.put("idt_LastDate", sdfdate.format(idt_LastDate
                                .get("idt_LastDate")));
                        returnEntity.setData(res);
                        return returnEntity;
                    } else if (days > 1) {
                        res.put("code", 603);
                        res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                        res.put("idt_LastDate", sdfdate.format(idt_LastDate
                                .get("idt_LastDate")));
                        returnEntity.setData(res);
                        return returnEntity;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00055");
            }
        }

        return returnEntity;
    }

    // 日终结帐表单检索数据，参数是：adt_clrq_b、adt_clrq_e、当前工号、gl_jgid
    public Map<String, Object> doQueryImJzxx(Map<String, Object> body, SysUser user) {
        Map<String, Object> jzxx = new HashMap<String, Object>();
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        String ls_adt_clrq_b = body.get("adt_clrq_b") + "";
        String ls_adt_clrq_e = body.get("adt_clrq_e") + "";
        String jzrq = body.get("jzrq") + "";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("adt_jzrq_s", adt_clrq_b);
        parameters.put("adt_jzrq_e", adt_clrq_e);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (body.get("jzrq") != null) {
            // Date jzrq = BSHISUtil.toDate(ls_jzrq);
            parameters.put("jzrq", jzrq);
            parameters.remove("adt_jzrq_s");
            parameters.remove("adt_jzrq_e");
            list = imJzxxDao.queryZyjzxxBrJzrq(parameters);
        } else {
            list = imJzxxDao.queryZyjzxx(parameters);
        }
        try {


            // 若记录数大于一(多次结帐)，将第一条以后的记录合并到第一条中
            if (list.size() > 0) {
                double CYSR = 0;
                double YJJE = 0;
                double YJXJ = 0;
                int FPZS = 0;
                int SJZS = 0;
                double SYB = 0;
                double YSJE = 0;
                double YSXJ = 0;
                double YSQT = 0;
                int ZPZS = 0;
                double TYJJ = 0;
                int TJKS = 0;
                double SRJE = 0;
                String qtysFb = "";
                StringBuffer QZPJ = new StringBuffer();
                StringBuffer QZSJ = new StringBuffer();
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < list.size(); i++) {
                    map = list.get(i);
                    SRJE = SRJE + MapAttributeUtils.getDouble("SRJE", map);
                    // 日终结帐表单.cysr[1]
                    CYSR = CYSR + MapAttributeUtils.getDouble("CYSR", map);
                    // =
                    // 日终结帐表单.cysr[1]
                    // +
                    // 日终结帐表单.cysr[ll_Row]
                    // 日终结帐表单.yjje[1]
                    YJJE = YJJE + MapAttributeUtils.getDouble("YJJE", map);
                    // =
                    // 日终结帐表单.yjje[1]
                    // +
                    // 日终结帐表单.yjje[ll_Row]
                    // 日终结帐表单.yjxj[1]
                    YJXJ = YJXJ + MapAttributeUtils.getDouble("YJXJ", map);
                    // =
                    // 日终结帐表单.yjxj[1]
                    // +
                    // 日终结帐表单.yjxj[ll_Row]
                    // 日终结帐表单.fpzs[1]
                    FPZS = FPZS + parseInt(map.get("FPZS") + "");
                    // =
                    // 日终结帐表单.fpzs[1]
                    // +
                    // 日终结帐表单.fpzs[ll_Row]
                    SJZS = SJZS + parseInt(map.get("SJZS") + ""); // 日终结帐表单.sjzs[1]
                    // =
                    // 日终结帐表单.sjzs[1]
                    // +
                    // 日终结帐表单.sjzs[ll_Row]
                    YSJE = YSJE + MapAttributeUtils.getDouble("YJXJ", map);
                    // =
                    // 日终结帐表单.ysje[1]
                    // +
                    // 日终结帐表单.ysje[ll_Row]
                    YSXJ = YSXJ + MapAttributeUtils.getDouble("YJXJ", map);
                    // =
                    // 日终结帐表单.ysxj[1]
                    // +
                    // 日终结帐表单.ysxj[ll_Row]
                    // 日终结帐表单.ysqt[1]
                    YSQT = YSQT + MapAttributeUtils.getDouble("YSQT", map);
                    // =
                    // 日终结帐表单.ysqt[1]
                    // +
                    // 日终结帐表单.ysqt[ll_Row]
                    ZPZS = ZPZS + parseInt(map.get("ZPZS") + ""); // 日终结帐表单.zpzs[1]
                    // =
                    // 日终结帐表单.zpzs[1]
                    // +
                    // 日终结帐表单.zpzs[ll_Row]
                    // 日终结帐表单.tyjj[1]
                    TYJJ = TYJJ + MapAttributeUtils.getDouble("TYJJ", map);
                    // =
                    // 日终结帐表单.tyjj[1]
                    // +
                    // 日终结帐表单.tyjj[ll_Row]
                    TJKS = TJKS + parseInt(map.get("TJKS") + ""); // 日终结帐表单.tjks[1]
                    // =
                    // 日终结帐表单.tjks[1]
                    // +
                    // 日终结帐表单.tjks[ll_Row]
                    // =
                    // 日终结帐表单.srje[1]
                    // +
                    // 日终结帐表单.srje[ll_Row]
                    SYB = SYB + MapAttributeUtils.getDouble("SYB", map);
                    qtysFb = qtysFb + map.get("QTYSFB") + " ";
                    if (map.get("QZPJ") != null) {
                        QZPJ.append(map.get("QZPJ") + " ");
                    }
                    if (map.get("QZSJ") != null) {
                        QZSJ.append(map.get("QZSJ") + " ");
                    }
                }
                //如果没有参数jzrq（表示点击打印按钮，然后进入本方法），重新整理算结算发票和缴款收据 票据序列
                if (body.get("jzrq") == null) {
                    Map<String, Object> para = new HashMap<String, Object>();
                    para.put("al_jgid", jgid);
                    para.put("as_czgh", userid);
                    para.put("adt_jzrq_s", adt_clrq_b);
                    para.put("adt_jzrq_e", adt_clrq_e);
                    //结算发票和缴款收据 票据序列
                    List<Map<String, Object>> ids_jsfp = imZyjsDao.queryIdsjsfp(para);
                    String ls_pjxl_fp = "";
                    if (ids_jsfp.size() > 0) {
                        String ls_begin_pjhm = ids_jsfp.get(0).get("FPHM") + "";
                        String ls_curr_pjhm = ids_jsfp.get(0).get("FPHM") + "";
                        String ls_end_pjhm = "";
                        for (int i = 0; i < ids_jsfp.size(); i++) {
                            if (!(ids_jsfp.get(i).get("FPHM") + "")
                                    .equals(ls_curr_pjhm)) {
                                ls_end_pjhm = (ids_jsfp.get(i - 1).get("FPHM") + "");
                                ls_pjxl_fp = wf_addtopjxl(ls_pjxl_fp, ls_begin_pjhm,
                                        ls_end_pjhm);
                                ls_begin_pjhm = ids_jsfp.get(i).get("FPHM") + "";
                                ls_curr_pjhm = ids_jsfp.get(i).get("FPHM") + "";
                            }
                            ls_curr_pjhm = wf_addpjhm(ls_curr_pjhm); // 票据号码加1
                        }
                        ls_end_pjhm = ids_jsfp.get(ids_jsfp.size() - 1).get("FPHM")
                                + "";
                        ls_pjxl_fp = wf_addtopjxl(ls_pjxl_fp, ls_begin_pjhm,
                                ls_end_pjhm);
                    }
                    QZPJ = new StringBuffer();
                    QZPJ.append(ls_pjxl_fp);

                    List<Map<String, Object>> ids_jksj = imTbkkDao.queryIdsJksj(para);
                    String ls_pjxl_sj = "";
                    if (ids_jksj.size() > 0) {
                        String ls_begin_pjhm = ids_jksj.get(0).get("SJHM") + "";
                        String ls_curr_pjhm = ids_jksj.get(0).get("SJHM") + "";
                        String ls_end_pjhm = "";
                        for (int i = 0; i < ids_jksj.size(); i++) {
                            if (!(ids_jksj.get(i).get("SJHM") + "")
                                    .equals(ls_curr_pjhm)) {
                                ls_end_pjhm = (ids_jksj.get(i - 1).get("SJHM") + "");
                                ls_pjxl_sj = wf_addtopjxl(ls_pjxl_sj, ls_begin_pjhm,
                                        ls_end_pjhm);
                                ls_begin_pjhm = ids_jksj.get(i).get("SJHM") + "";
                                ls_curr_pjhm = ids_jksj.get(i).get("SJHM") + "";
                            }
                            ls_curr_pjhm = wf_addpjhm(ls_curr_pjhm); // 票据号码加1
                        }
                        ls_end_pjhm = ids_jksj.get(ids_jksj.size() - 1).get("SJHM")
                                + "";
                        ls_pjxl_sj = wf_addtopjxl(ls_pjxl_sj, ls_begin_pjhm,
                                ls_end_pjhm);
                    }
                    QZSJ = new StringBuffer();
                    QZSJ.append(ls_pjxl_sj);
                }
                jzxx.put("JZRQ", map.get("JZRQ"));
                jzxx.put("CZGH", map.get("CZGH") + "");
                jzxx.put("CYSR", CYSR + "");
                jzxx.put("YJJE", YJJE + "");
                jzxx.put("YJXJ", YJXJ + "");
                jzxx.put("FPZS", FPZS + "");
                jzxx.put("SJZS", SJZS + "");
                jzxx.put("YSJE", YSJE + "");
                jzxx.put("YSXJ", YSXJ + "");
                jzxx.put("YSQT", YSQT + "");
                jzxx.put("ZPZS", ZPZS + "");
                jzxx.put("TYJJ", TYJJ + "");
                jzxx.put("TJKS", TJKS + "");
                jzxx.put("SRJE", SRJE + "");
                jzxx.put("QZPJ", QZPJ.toString());
                jzxx.put("QZSJ", QZSJ.toString());
                jzxx.put("CZGH", userid + "");
                jzxx.put("SYB", SYB + "");
                jzxx.put("qtysFb", qtysFb + "");
            }
            // 获取作废票据数据
            Map<String, Object> iws_pjxx1 = new HashMap<String, Object>();
            Map<String, Object> iws_pjxx2 = new HashMap<String, Object>();
            long iws_pjxx1_pjzs = 0;
            long iws_pjxx2_pjzs = 0;
            List<Map<String, Object>> ret_a = null;

            if (body.get("jzrq") != null) {
                ret_a = imzfpjDao.queryJzxxByJzrq(parameters);
            } else {
                ret_a = imzfpjDao.queryJzxx(parameters);
            }
            for (Map<String, Object> map : ret_a) {
                if ("1".equals(map.get("PJLB") + "")) {
                    iws_pjxx1_pjzs++;
                    iws_pjxx1.put(iws_pjxx1_pjzs + "", map.get("PJHM") + "");
                }
                if ("2".equals(map.get("PJLB") + "")) {
                    iws_pjxx2_pjzs++;
                    iws_pjxx2.put(iws_pjxx2_pjzs + "", map.get("PJHM") + "");
                }
            }
            Map<String, Object> zfpj = wf_WriteBillInfo(iws_pjxx1_pjzs,
                    iws_pjxx1, iws_pjxx2_pjzs, iws_pjxx2, 0, null);
            jzxx.putAll(zfpj);
            return jzxx;
        } catch (Exception e) {

            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00054");
        }
    }


    public Map<String, Object> getPatientDepartmentChargesDailyPrintParameters(Map<String, Object> request, Map<String, Object> respose, SysUser user) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.putAll(request);
        request.put("body", body);
        Integer userid = user.getUserId();
        String CZY = user.getUserName();
        String JGMC = user.getHospitalName();
        try {
            if ("1".equals(request.get("ty") + "")) {
                Map<String, Object> jzxx = doQueryImJzxx(request, user);
                respose.putAll(jzxx);
                String[] lS_JZRQ = (request.get("adt_clrq_b") + "")
                        .split("-| |:");
                String JZRQ = lS_JZRQ[0] + "年" + lS_JZRQ[1] + "月" + lS_JZRQ[2]
                        + "日";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String ZBRQ = sdf.format(new Date());

                String RMBDX = BUHISUtil.changeMoneyUpper(jzxx.get("YSJE"));
                respose.put("CYSR", MapAttributeUtils.getDouble("CYSR", jzxx));
                respose.put("YJJE", MapAttributeUtils.getDouble("YJJE", jzxx));
                respose.put("TYJJ", MapAttributeUtils.getDouble("TYJJ", jzxx));
                respose.put("YSJE", MapAttributeUtils.getDouble("YSJE", jzxx));
                respose.put("YSXJ", MapAttributeUtils.getDouble("YSXJ", jzxx));
                respose.put("SRJE", MapAttributeUtils.getDouble("SRJE", jzxx));
                respose.put("YSQT", MapAttributeUtils.getDouble("YSQT", jzxx));
                respose.put("RMBDX", RMBDX);
                respose.put("JZRQ", JZRQ);
                respose.put("ZBRQ", ZBRQ);
                respose.put("CZY", CZY);
                respose.put("JGMC", JGMC);
                respose.put("XX", "—— —— —— ——");
                respose.put("SYB", MapAttributeUtils.getDouble("SYB", jzxx));

            }
            if ("2".equals(request.get("ty") + "")) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Map<String, Object> ret = doCreateJzrb(body.get("jzrq").toString(), user).getData();
                ret = (Map<String, Object>) ret.get("jzxx");
                respose.put("qtysFb", ret.get("qtysFb") + "");
                respose.put("CYSR", MapAttributeUtils.getDouble("CYSR", ret));
                respose.put("YJJE", MapAttributeUtils.getDouble("YJJE", ret));
                respose.put("TYJJ", MapAttributeUtils.getDouble("TYJJ", ret));
                respose.put("QZPJ", ret.get("QZPJ") + "");
                respose.put("QZSJ", ret.get("QZSJ") + "");
                respose.put("FPZS", ret.get("FPZS") + "");
                respose.put("SJZS", ret.get("SJZS") + "");
                respose.put("TJKS", ret.get("TJKS") + "");
                respose.put("JSFP", ret.get("JSFP") + "");
                respose.put("JKSJ", ret.get("JKSJ") + "");
                respose.put("YSJE", MapAttributeUtils.getDouble("YSJE", ret));
                respose.put("YSXJ", MapAttributeUtils.getDouble("YSXJ", ret));
                respose.put("SRJE", MapAttributeUtils.getDouble("SRJE", ret));
                respose.put("YSQT", MapAttributeUtils.getDouble("YSQT", ret));
                respose.put("ZFSJZS", MapAttributeUtils.getInteger("ZFSJZS", ret));
                respose.put("ZFSJJE", MapAttributeUtils.getDouble("ZFSJJE", ret));

                respose.put("ZFFPJE", MapAttributeUtils.getDouble("ZFFPJE", ret)); // 日终结帐表单 作废发票
                respose.put("ZFFPZS", MapAttributeUtils.getInteger("ZFFPZS", ret)); // 日终结帐表单 作废发票张数
                respose.put("ZFFP", MapAttributeUtils.getString("ZFFP",ret)); // 日终结帐表单 作废发票金额
                String[] lS_JZRQ = (request.get("jzrq") + "").split("-| |:");
                String JZRQ = lS_JZRQ[0] + "年" + lS_JZRQ[1] + "月" + lS_JZRQ[2]
                        + "日(" + lS_JZRQ[3] + ":" + lS_JZRQ[4] + ")";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String ZBRQ = sdf.format(new Date());


                String RMBDX = BUHISUtil.changeMoneyUpper(MapAttributeUtils.getDouble("YSJE", ret));
                respose.put("RMBDX", RMBDX);
                respose.put("JZRQ", JZRQ);
                respose.put("ZBRQ", ZBRQ);
                respose.put("CZY", CZY);
                respose.put("JGMC", JGMC);
                respose.put("XX", "—— —— —— ——");
                respose.put("SYB", MapAttributeUtils.getDouble("SYB", ret));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respose;
    }

    /**
     * 住院管理-日终结账（结账）
     *
     * @param jzrq
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity doSaveJzrb(String jzrq, SysUser user, String jzrqStart, String jzrqEnd) {
        Map<String, Object> body = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            body = objectMapper.readValue(redisTemplate.opsForValue().get(RedisKeyHead.zyCheckout + user.getHospitalId().toString() + "-map-" + user.getUserId()), Map.class);
            list = objectMapper.readValue(redisTemplate.opsForValue().get(RedisKeyHead.zyCheckout + user.getHospitalId().toString() + "-list-" + user.getUserId()), List.class);
        } catch (JsonProcessingException e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00054");
        }
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idt_jzrq", jzrq);
        parameters.put("userid", userid);
        parameters.put("gl_jgid", jgid);
        try {
            // 日终结帐表单保存
            body =  BUHISUtil.transformLowerCase(body);
            body.put("ssyjje",body.get("ss_yjje"));
            body.put("tkyjje",body.get("tk_yjje"));
            body.put("czgh",userid);
            body.put("jgid",jgid);
            imRzjzDao.insert(body);
            //日终结账明细保存
            for (Map<String,Object> temp : list){
                temp = BUHISUtil.transformLowerCase(temp);
                temp.put("czgh",user.getUserId());
                temp.put("jgid",user.getHospitalId());
                temp.put("jzrq",body.get("jzrq"));
                imRzjzMxDao.insert(temp);
            }
//            Map<String, Object> jzxx = (Map<String, Object>) body.get("jzxx");
//            jzxx.put("SRJE", MapAttributeUtils.getDouble("SRJE", jzxx));
//            jzxx.put("SYB", MapAttributeUtils.getDouble("SYB", jzxx));
//            jzxx.put("JZRQ", idt_jzrq);
//            jzxx.put("QTYSFB", jzxx.get("qtysFb") + "");
//            jzxx = BUHISUtil.transformLowerCase(jzxx);
//            imJzxxDao.insert(jzxx);
            // 将作废发票1、作废收据2及退票号码3写入IM_ZFPJ
//            for (int i = 1; i < 4; i++) {
//                int ll_pjlb = i;
//                Map<String, Object> pjhms = (Map<String, Object>) body
//                        .get("iws_pjxx" + i);
//                Map<String, Object> zfpj = new HashMap<String, Object>();
//                zfpj.put("jzrq", idt_jzrq);
//                zfpj.put("czgh", userid);
//                zfpj.put("pjlb", ll_pjlb);
//                zfpj.put("jgid", jgid);
//                for (int j = 1; j <= pjhms.size(); j++) {
//                    zfpj.put("pjhm", pjhms.get(j + ""));
//                    imzfpjDao.insert(zfpj);
//                }
//            }

            // 将结帐日期写入IM_TBKK
            Integer ll_Count = 0;
            parameters.put("ldt_End", jzrq);
            parameters.remove("idt_jzrq");
            ll_Count = imTbkkDao.queeryJzrq(parameters);
            String hql_update = "";
            if (ll_Count > 0) {
                parameters.put("idt_jzrq", jzrq);
                imTbkkDao.updateJzrq(parameters);

            }

            // 将结帐日期写入IM_JKZF
            parameters.remove("idt_jzrq");
            ll_Count = 0;
            ll_Count = imJkzfDao.queryJzrqCount(parameters);

            if (ll_Count > 0) {
                parameters.put("idt_jzrq", jzrq);
                imJkzfDao.updateJzrq(parameters);
            }

            // 将结帐日期写入IM_ZYJS
            parameters.remove("idt_jzrq");
            ll_Count = 0;
            ll_Count = imZyjsDao.queryJzrqCount(parameters);
            if (ll_Count > 0) {
                parameters.put("idt_jzrq", jzrq);
                imZyjsDao.updateJzrq(parameters);
            }

            // 将结帐日期写入IM_JSZF
            parameters.remove("idt_jzrq");
            ll_Count = 0;
            ll_Count = imJszfDao.queryJzrqCoount(parameters);
            if (ll_Count > 0) {
                parameters.put("idt_jzrq", jzrq);
                imJszfDao.updateJzrq(parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00054");
        }

        return ReturnEntityUtil.success();
    }

    /**
     * 日终结账-明细(结算明细)
     *
     * @return
     */
    public Map<String, Object> getAccountsDetailParameters(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        Map<String, Object> response = new HashMap<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("hasRecordMore", hasRecordMore);
        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;
        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }
        list = imZyjsDao.queryJsmxJzrq(parameters);
        list_zf = imZyjsDao.queryJszfmxJzrq(parameters);
        double je = list.get(0).get("FYHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("FYHJ_ALL", list.get(0));
        double zf = list_zf.get(0).get("FYHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("FYHJ_ALL", list_zf.get(0));
        response.put("FYHJ_ALL", String.format("%1$.2f", je - zf));
        je = list.get(0).get("ZFHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("ZFHJ_ALL", list.get(0));
        zf = list_zf.get(0).get("ZFHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("ZFHJ_ALL", list_zf.get(0));
        response.put("ZFHJ_ALL", String.format("%1$.2f", je - zf));
        je = list.get(0).get("JKHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKHJ_ALL", list.get(0));
        zf = list_zf.get(0).get("JKHJ_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKHJ_ALL", list_zf.get(0));
        response.put("JKHJ_ALL", String.format("%1$.2f", je - zf));
        je = list.get(0).get("JSJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JSJE_ALL", list.get(0));
        zf = list_zf.get(0).get("JSJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JSJE_ALL", list_zf.get(0));
        response.put("JSJE_ALL", String.format("%1$.2f", je - zf));


        return response;
    }

    /**
     * 日终结账-明细(缴款明细)
     *
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @param user
     * @return
     */
    public Map<String, Object> getDeliveryDetailParameters(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        Map<String, Object> response = new HashMap<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        parameters.put("hasRecordMore", hasRecordMore);

        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;
        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }

        list = imTbkkDao.queryJkmxJsrq(parameters);
        list_zf = imTbkkDao.queryJkmxJsrqZf(parameters);

        double je = list.get(0).get("JKJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKJE_ALL", list.get(0));
        double zf = list_zf.get(0).get("JKJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKJE_ALL", list_zf.get(0));
        response.put("JKJE_ALL", String.format("%1$.2f", je + zf));
        return response;
    }

    /**
     * 日终结账-明细(退款明细)
     *
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @param user
     * @return
     */
    public Map<String, Object> getRefundDetailParameters(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        Map<String, Object> response = new HashMap<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        parameters.put("hasRecordMore", hasRecordMore);

        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;
        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }

        list = imTbkkDao.queryTkmxJsrq(parameters);
        list_zf = imTbkkDao.queryTkmxJsrq(parameters);

        double je = list.get(0).get("JKJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKJE_ALL", list_zf.get(0));
        double zf = list_zf.get(0).get("JKJE_ALL") == null ? 0 : MapAttributeUtils.getDouble("JKJE_ALL", list_zf.get(0));
        response.put("JKJE_ALL", String.format("%1$.2f", je));
        return response;
    }

    /**
     * 日终结账-明细(结账明细List)
     *
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @param user
     * @return
     */
    public List<Map<String, Object>> getAccountsDetailFields(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        List<Map<String, Object>> response = new ArrayList<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("hasRecordMore", hasRecordMore);
        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;

        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }
        //结算明细list
        list = imZyjsDao.queryJsmxJzrqList(parameters);
        //结算明细作废list
        list_zf = imZyjsDao.queryJsmxJzrqListZf(parameters);
        response.addAll(list);
        response.addAll(list_zf);

        return response;
    }

    /**
     * 日终结账-明细(缴款明细List)
     *
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @param user
     * @return
     */
    public List<Map<String, Object>> getDeliveryDetailFields(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        List<Map<String, Object>> response = new ArrayList<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("hasRecordMore", hasRecordMore);
        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;

        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }
        //日终汇总(缴款明细list)
        list = imTbkkDao.queryJkmxJzrqList(parameters);
        //日终汇总(缴款作废明细list)
        list_zf = imTbkkDao.queryJkmxJzrqListZf(parameters);
        response.addAll(list);
        response.addAll(list_zf);

        return response;
    }


    /**
     * 日终结账-明细(退款明细List)
     *
     * @param jzrq
     * @param jzbs
     * @param hasRecordMore
     * @param user
     * @return
     */
    public List<Map<String, Object>> getRefundDetailFields(String jzrq, String jzbs, Boolean hasRecordMore, SysUser user) {
        List<Map<String, Object>> response = new ArrayList<>();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("jzbs", jzbs);
        try {
            parameters.put("idt_jzrq", sdfdate.parse(jzrq));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parameters.put("hasRecordMore", hasRecordMore);
        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> list_zf = null;

        if (jzbs.equals("0")) {

        } else {
            if (hasRecordMore) {
                parameters.remove("idt_jzrq");
                parameters.put("adt_jzrq_s", adt_clrq_b);
                parameters.put("adt_jzrq_e", adt_clrq_e);

            }
        }
        //结算退款明细查询
        list = imTbkkDao.queryTkmxJzrqList(parameters);
        //结算退款作废明细查询
        list_zf = imTbkkDao.queryTkmxJzrqListZf(parameters);
        response.addAll(list);
        response.addAll(list_zf);

        return response;
    }

    /**
     * 日终结账明细检查
     *
     * @param jzrq
     * @param user
     * @return
     */
    public ReturnEntity<String> checkRecordCount(String jzrq, SysUser user) {
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();

        String ls_adt_clrq_b = jzrq.substring(0, 10) + " 00:00:00";
        String ls_adt_clrq_e = jzrq.substring(0, 10) + " 23:59:59";
        Date adt_clrq_b = BUHISUtil.toDate(ls_adt_clrq_b);
        Date adt_clrq_e = BUHISUtil.toDate(ls_adt_clrq_e);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        parameters.put("as_czgh", userid);
        parameters.put("adt_jzrq_s", adt_clrq_b);
        parameters.put("adt_jzrq_e", adt_clrq_e);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = imJzxxDao.checkRecordCount(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 1) {
            ReturnEntityUtil.success("true");
        }
        return ReturnEntityUtil.success("false");
    }

    /**
     * 住院管理-日终汇总（产生后校验）
     *
     * @param hzrq
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doGenerateAfterVerification(String hzrq, SysUser user) {
        ReturnEntity<Map<String, Object>> returnEntity = new ReturnEntity<>();
        Map<String, Object> rep = new HashMap<>();
        rep.put("code", 200);

        Integer jgid = user.getHospitalId();

        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date id_dqrq = null;
        Date idt_hzrq = null;
        Date ldt_CurrentDateTime = null;
        if (StringUtils.isNotBlank(hzrq)) {
            try {
                id_dqrq = sdfdate.parse(hzrq);
                ldt_CurrentDateTime = sdfdate.parse(sdfdate.format(new Date()));
                if (id_dqrq.getTime() == ldt_CurrentDateTime.getTime()) {
                    idt_hzrq = sdfdatetime
                            .parse(sdfdatetime.format(new Date()));
                } else {
                    idt_hzrq = sdfdatetime.parse(sdfdate.format(id_dqrq)
                            + " 23:59:59");
                }
                Map<String, Object> parametershzbd = new HashMap<String, Object>();
                parametershzbd.put("adt_jzrq",
                        sdfdatetime.parse(sdfdatetime.format(idt_hzrq)));
                parametershzbd.put("al_jgid", jgid);
                // 日结汇总汇总表单
                Integer l = imJzxxDao.queryRzhzCount(parametershzbd);

                if (l == 0) {
                    rep.put("code", 605);
                    returnEntity.setData(rep);
                    return returnEntity;
                }


            } catch (Exception e) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00056");
            }

        }
        return ReturnEntityUtil.success(rep);
    }

    /**
     * 查询前校验
     *
     * @param startDate
     * @param endDate
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doQueryVerification(String startDate, String endDate, SysUser user) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("code",200);
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        Integer jgid = user.getHospitalId();
        Date adt_hzrq_start = null;
        Date adt_hzrq_end = null;
        try {
            Date ldt_CurrentDateTime = sdfdate
                    .parse(sdfdate.format(new Date()));
            if (StringUtils.isNotBlank(startDate)) {
                adt_hzrq_start = sdfdate.parse(startDate);
                if (adt_hzrq_start.getTime() > ldt_CurrentDateTime.getTime()) {
                    resp.put("code", 800);
                    return ReturnEntityUtil.success(resp);

                }
            }
            if (StringUtils.isNotBlank(endDate)) {
                adt_hzrq_end = sdfdate.parse(endDate);
                if (adt_hzrq_end.getTime() > ldt_CurrentDateTime.getTime()) {
                    resp.put("code", 801);
                    return ReturnEntityUtil.success(resp);
                }
            }


            Map<String, Object> parametershzbd = new HashMap<String, Object>();
            parametershzbd.put("adt_hzrq_b", adt_hzrq_start);
            parametershzbd.put("adt_hzrq_e", adt_hzrq_end);
            parametershzbd.put("al_jgid", jgid);
            // 判断有没有数据可查询
            List<Map<String, Object>> resultList = imJzxxDao.doQueryVerification(parametershzbd);
            if (resultList.size() <= 0) {
                resp.put("code", 802);
                return ReturnEntityUtil.success(resp);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00057");
        }
        return ReturnEntityUtil.success(resp);

    }

    /**
     * 住院管理-日终汇总（汇总前验证）
     *
     * @param hzrq
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doCollectVerification(String hzrq, SysUser user) {
        Map<String, Object> res = new HashMap<>();
        res.put("code",200);
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date idt_hzrq = null;
        Date id_dqrq = null;
        Date ldt_CurrentDateTime = null;
        if (StringUtils.isNotBlank(hzrq)) {
            try {
                id_dqrq = sdfdate.parse(hzrq);
                ldt_CurrentDateTime = sdfdate.parse(sdfdate.format(new Date()));
                if (id_dqrq.getTime() == ldt_CurrentDateTime.getTime()) {
                    idt_hzrq = sdfdatetime
                            .parse(sdfdatetime.format(new Date()));
                } else {
                    idt_hzrq = sdfdatetime.parse(sdfdate.format(id_dqrq)
                            + " 23:59:59");
                }
                Map<String, Object> idt_LastDate = new HashMap<String, Object>();
                int hzsign = wf_IsGather(idt_hzrq, idt_LastDate, user);
                if (hzsign == 1) {
                    res.put("code", 700);
                    return ReturnEntityUtil.success(res);
                }
                if (idt_LastDate.get("idt_LastDate") == null) {

                    res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                    res.put("code", 701);
                    return ReturnEntityUtil.success(res);
                } else {
                    int days = DateUtils.getDifferDays(idt_hzrq, sdfdatetime
                            .parse(sdfdatetime.format(idt_LastDate
                                    .get("idt_LastDate"))));
                    if (days < 0) {

                        res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                        res.put("idt_LastDate", sdfdate.format(idt_LastDate
                                .get("idt_LastDate")));
                        res.put("code", 702);
                        return ReturnEntityUtil.success(res);
                    } else if (days == 0 || days == 1) {
                        if (sdfdate.parse(sdfdate.format(idt_hzrq)).getTime() == sdfdate
                                .parse(sdfdate.format(new Date())).getTime()) {
                            res.put("code", 703);
                            return ReturnEntityUtil.success(res);
                        } else {
                            res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                            res.put("code", 704);
                            return ReturnEntityUtil.success(res);
                        }
                    } else if (days > 1) {
                        res.put("idt_hzrq", sdfdate.format(idt_hzrq));
                        res.put("idt_LastDate", sdfdate.format(idt_LastDate
                                .get("idt_LastDate")));
                        res.put("code", 705);
                        return ReturnEntityUtil.success(res);
                    }
                }
            } catch (Exception e) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00055");
            }
        }
        return ReturnEntityUtil.success(res);
    }

    /**
     * 住院管理-日终汇总（汇总保存）
     *
     * @param hzrq
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<Map<String, Object>> doSaveCollect(String hzrq, SysUser user) {
        Map<String, Object> res = new HashMap<>();
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Integer jgid = user.getHospitalId();
        Date ldt_End = null;
        Date ldt_GatherDate = null;
        Date id_dqrq = null;
        Date idt_hzrq = null;
        Date ldt_CurrentDateTime = null;
        Map<String, Object> parametersll_count = new HashMap<String, Object>();
        Map<String, Object> parametersupd = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(hzrq)) {
                ldt_CurrentDateTime = sdfdate.parse(sdfdate.format(new Date()));
                id_dqrq = sdfdate.parse(hzrq);
                if (id_dqrq.getTime() == ldt_CurrentDateTime.getTime()) {
                    idt_hzrq = sdfdatetime
                            .parse(sdfdatetime.format(new Date()));
                } else {
                    idt_hzrq = sdfdatetime.parse(sdfdate.format(id_dqrq)
                            + " 23:59:59");
                }
            }
            if (idt_hzrq == new Date()) {
                ldt_End = idt_hzrq;
            } else {
                ldt_End = sdfdatetime.parse((sdfdate.format(BUHISUtil
                        .getDateAfter(idt_hzrq, 1)) + " 00:00:00"));
            }
            ldt_GatherDate = sdfdatetime.parse(sdfdate.format(idt_hzrq)
                    + " 00:00:00");
            parametersll_count.put("idt_End", ldt_End);
            parametersll_count.put("gl_jgid", jgid);
            parametersupd.put("ldt_GatherDate", ldt_GatherDate);
            parametersupd.put("idt_End", ldt_End);
            parametersupd.put("gl_jgid", jgid);
            Integer ll_count = 0;
            ll_count = imFeeFymxDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {
                imFeeFymxDao.updateHzrq(parametersupd);

            }
            ll_count = imTbkkDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {
                imTbkkDao.updateHzrq(parametersupd);


            }
            ll_count = imJkzfDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {

                imJkzfDao.updateHzrq(parametersupd);
            }
            ll_count = imZyjsDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {
                imZyjsDao.updateHzrq(parametersupd);

            }
            ll_count = imJszfDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {
                imJszfDao.updateHzrq(parametersupd);

            }
            ll_count = imJzxxDao.queryHzCount(parametersll_count);
            if (ll_count > 0) {
                imJzxxDao.updateHzrq(parametersupd);

            }
            wf_Create_jzhz(idt_hzrq,user);
            wf_create_fyhz(idt_hzrq,user);
            // 将表IM_HZRY打上汇总日期、结帐日期
            ll_count = imHzryDao.queryHzCount(parametersll_count);

            if (ll_count > 0) {
                imHzryDao.updateHzrq(parametersupd);

            }
            // 将费用明细IM_FEE_FYMX汇总到IM_SRHZ中:分别按费用科室(kslb=1)、执行科室(kslb=2)归并
            parametersll_count.clear();
            parametersll_count.put("ldt_GatherDate", ldt_GatherDate);
            parametersll_count.put("gl_jgid", jgid);
            ll_count = imFeeFymxDao.queryHz(parametersll_count);
            if (ll_count > 0) {
                List<Map<String, Object>> IM_SRHZ_list = imFeeFymxDao.queryImSrhzList1(parametersll_count);
                for (int i = 0; i < IM_SRHZ_list.size(); i++) {
                    if (MapAttributeUtils.getDouble("ZJJE", IM_SRHZ_list.get(i)) + MapAttributeUtils.getDouble("ZFJE", IM_SRHZ_list.get(i)) == 0.0) {
                        IM_SRHZ_list.remove(i);
                        i--;
                        continue;
                    }
                    Map<String, Object> savmap = new HashMap<String, Object>();
                    savmap.put(
                            "HZRQ",
                            sdfdatetime.parse(IM_SRHZ_list.get(i).get("HZRQ")
                                    + ""));
                    savmap.put("KSLB", IM_SRHZ_list.get(i).get("KSLB"));
                    savmap.put("KSDM", IM_SRHZ_list.get(i).get("FYKS"));
                    savmap.put("SFXM", IM_SRHZ_list.get(i).get("FYXM"));
                    savmap.put("ZJJE", IM_SRHZ_list.get(i).get("ZJJE"));
                    savmap.put("ZFJE", IM_SRHZ_list.get(i).get("ZFJE"));
                    savmap.put("JGID", IM_SRHZ_list.get(i).get("JGID"));
                    savmap = BUHISUtil.transformLowerCase(savmap);
                    imSrhzDao.insert(savmap);

                }
                IM_SRHZ_list.clear();
                IM_SRHZ_list = imFeeFymxDao.queryImSrhzList2(parametersll_count);

                for (int i = 0; i < IM_SRHZ_list.size(); i++) {
                    if (Double
                            .parseDouble(IM_SRHZ_list.get(i).get("ZJJE") + "")
                            + Double.parseDouble(IM_SRHZ_list.get(i)
                            .get("ZFJE") + "") == 0.0) {
                        IM_SRHZ_list.remove(i);
                        i--;
                        continue;
                    }
                    Map<String, Object> savmap = new HashMap<String, Object>();
                    savmap.put(
                            "HZRQ",
                            sdfdatetime.parse(IM_SRHZ_list.get(i).get("HZRQ")
                                    + ""));
                    savmap.put("KSLB", IM_SRHZ_list.get(i).get("KSLB"));
                    savmap.put("KSDM", IM_SRHZ_list.get(i).get("ZXKS"));
                    savmap.put("SFXM", IM_SRHZ_list.get(i).get("FYXM"));
                    savmap.put("ZJJE", IM_SRHZ_list.get(i).get("ZJJE"));
                    savmap.put("ZFJE", IM_SRHZ_list.get(i).get("ZFJE"));
                    savmap.put("JGID", IM_SRHZ_list.get(i).get("JGID"));
                    savmap = BUHISUtil.transformLowerCase(savmap);
                    imSrhzDao.insert(savmap);
                }
            }
            res.put("idt_hzrq", sdfdate.format(idt_hzrq));
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00061");
        }
        return ReturnEntityUtil.success(res);
    }

    /**
     * 住院管理-日终汇总（取消汇总查询）
     *
     * @param hzrq
     * @param user
     * @return
     */
    public ReturnEntity<Map<String, Object>> doQueryCancelCollectCommit(String hzrq, SysUser user) {
        Map<String, Object> res = new HashMap<>();
        res.put("code",200);
        // 用户的机构名称
        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("gl_jgid", jgid);
//			parameters.put("czgh", userId);
        try {
            //查询最大汇总日期
            Map<String, Object> map = imJzhzDao.queryMaxHzrq(parameters);
            if (map !=null &&map.size() > 0) {
                res.put("HZRQ", map.get("HZRQ"));
                return ReturnEntityUtil.success(res);
            } else {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00058");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00059");
        }

    }

    /**
     * 住院管理-日终汇总（取消汇总）
     *
     * @param hzrq
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<Map<String, Object>> doCancelCollectCommit(String hzrq, SysUser user) {

        Integer jgid = user.getHospitalId();// 用户的机构名称
        Map<String, Object> parameters = new HashMap<String, Object>();
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date hzdate = null;
        try {
            hzdate = sdfdatetime.parse(hzrq);
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00060");
        }
        parameters.put("jgid", jgid);
        parameters.put("hzrq", hzdate);
        try {
            imFeeFymxDao.doCancelCollectCommit(parameters);
            imTbkkDao.doCancelCollectCommit(parameters);
            imJkzfDao.doCancelCollectCommit(parameters);
            imZyjsDao.doCancelCollectCommit(parameters);
            imJszfDao.doCancelCollectCommit(parameters);
            imJzxxDao.doCancelCollectCommit(parameters);
            imHzryDao.doCancelCollectCommit(parameters);
            imSrhzDao.doCancelCollectCommit(parameters);
            imJzhzDao.doCancelCollectCommit(parameters);
            imFeeFyhzDao.doCancelCollectCommit(parameters);

        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00060");
        }

        return ReturnEntityUtil.success();
    }

    /**
     * 结账汇总
     *
     * @param adt_hzrq
     * @param adt_hzrq
     * @param user
     */
    public void wf_Create_jzhz(Date adt_hzrq, SysUser user) {
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        // User user = (User) ctx.get("user.instance");
        // String gl_jgid = user.get("manageUnit.id");
        Integer gl_jgid = user.getHospitalId();
        Map<String, Object> parametershzrq = new HashMap<String, Object>();
        Date ldt_sqhzrq = null; // 上期汇总日期
        parametershzrq.put("gl_jgid", gl_jgid);
        try {
            adt_hzrq = sdfdatetime
                    .parse(sdfdate.format(adt_hzrq) + " 00:00:00");
            Map<String, Object> hzrqmap = imJzhzDao.queryMaxHzrq(parametershzrq);
            if (hzrqmap != null && hzrqmap.get("HZRQ") != null) {
                ldt_sqhzrq = sdfdatetime.parse(hzrqmap.get("HZRQ") + "");
            }
            // １、取上期结存(sqjc)
            List<Map<String, Object>> lws_hzxxsqjc = new ArrayList<Map<String, Object>>();
            if (ldt_sqhzrq != null) {
                Map<String, Object> parametersbqye = new HashMap<String, Object>();
                parametersbqye.put("ldt_sqhzrq", ldt_sqhzrq);
                parametersbqye.put("gl_jgid", gl_jgid);
                parametersbqye.put("xmbh", 1);
                Map<String, Object> mapbqye1 = imJzhzDao.queryBqye(parametersbqye);
                if (mapbqye1.get("SQJC") != null) {
                    lws_hzxxsqjc.add(0, mapbqye1);
                } else {
                    mapbqye1.put("SQJC", 0);
                    lws_hzxxsqjc.add(0, mapbqye1);
                }
                parametersbqye.put("xmbh", 2);
                Map<String, Object> mapbqye2 = imJzhzDao.queryBqye(parametersbqye);

                if (mapbqye2.get("SQJC") != null) {
                    lws_hzxxsqjc.add(1, mapbqye2);
                } else {
                    mapbqye2.put("SQJC", 0);
                    lws_hzxxsqjc.add(1, mapbqye2);
                }
                parametersbqye.put("xmbh", 3);
                Map<String, Object> mapbqye3 = imJzhzDao.queryBqye(parametersbqye);

                if (mapbqye3 != null && mapbqye3.get("SQJC") != null) {
                    lws_hzxxsqjc.add(2, mapbqye3);
                } else {
                    mapbqye3 = new HashMap<String, Object>();
                    mapbqye3.put("SQJC", 0);
                    lws_hzxxsqjc.add(2, mapbqye3);
                }

            } else {
                Map<String, Object> mapbqye4 = new HashMap<String, Object>();
                mapbqye4.put("SQJC", 0);
                lws_hzxxsqjc.add(0, mapbqye4);
                lws_hzxxsqjc.add(1, mapbqye4);
                lws_hzxxsqjc.add(2, mapbqye4);
                // lws_hzxxsqjc.add(2, mapbqye4);
            }

            // ２、取本期发生(bqfs)
            List<Map<String, Object>> lws_hzxxbqfs = new ArrayList<Map<String, Object>>();
            Map<String, Object> parametersje = new HashMap<String, Object>();
            parametersje.put("adt_hzrq", adt_hzrq);
            parametersje.put("gl_jgid", gl_jgid);
            Map<String, Object> mapzjje1 = imFeeFymxDao.queryHzZjje(parametersje);

            if (mapzjje1.get("BQFS") != null) {
                lws_hzxxbqfs.add(0, mapzjje1);
            } else {
                mapzjje1.put("BQFS", 0);
                lws_hzxxbqfs.add(0, mapzjje1);
            }
            Map<String, Object> mapjkje2 = imTbkkDao.queryHzJkje(parametersje);
            if (mapjkje2.get("BQFS") != null) {
                lws_hzxxbqfs.add(1, mapjkje2);
            } else {
                mapjkje2.put("BQFS", 0);
                lws_hzxxbqfs.add(1, mapjkje2);
            }
            Map<String, Object> mapjkje3 = new HashMap<String, Object>();
            mapjkje3.put("BQFS", 0);
            lws_hzxxbqfs.add(2, mapjkje3);
            // Map<String, Object> defaultmap = new HashMap<String, Object>();
            // defaultmap.put("BQFS", 0);
            // lws_hzxxbqfs.add(2, defaultmap);

            double lws_Temp1 = 0.00;
            Map<String, Object> calculatemap = imTbkkDao.queryHzJkjeZf(parametersje);
            if (calculatemap.get("BQFS") != null) {
                lws_Temp1 = MapAttributeUtils.getDouble("BQFS",calculatemap);
            }
            lws_hzxxbqfs.get(1).put(
                    "BQFS", MapAttributeUtils.getDouble("BQFS",lws_hzxxbqfs.get(1))- lws_Temp1);


            // ３、取本期结算(bqjs)
            List<Map<String, Object>> lws_hzxxbqjs = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapfyhj = imZyjsDao.queryHzFyhj(parametersje);

            if (mapfyhj.get("BQJS") != null) {
                lws_hzxxbqjs.add(0, mapfyhj);
            } else {
                mapfyhj.put("BQJS", 0);
                lws_hzxxbqjs.add(0, mapfyhj);
            }
            Map<String, Object> mapjkhj = imZyjsDao.queryHzJkhj(parametersje);
            if (mapjkhj.get("BQJS") != null) {
                lws_hzxxbqjs.add(1, mapjkhj);
            } else {
                mapjkhj.put("BQJS", 0);
                lws_hzxxbqjs.add(1, mapjkhj);
            }
            Map<String, Object> mapbqjs3 = new HashMap<String, Object>();
            mapbqjs3.put("BQJS", 0);
            lws_hzxxbqjs.add(2, mapbqjs3);
            // Map<String, Object> defaultmap1 = new HashMap<String, Object>();
            // defaultmap1.put("BQJS", 0);
            // lws_hzxxbqjs.add(2, defaultmap1);

            double lws_Temp2 = 0.00;
            Map<String, Object> calculatemap1 = imZyjsDao.queryHzFyhjZf(parametersje);
            if (calculatemap1.get("BQJS") != null) {
                lws_Temp2 = MapAttributeUtils.getDouble("BQJS",calculatemap1);
            }
            lws_hzxxbqjs.get(0).put(
                    "BQJS", MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(0)) - lws_Temp2);

            double lws_Temp6 = 0.00;
            Map<String, Object> calculatemap6 = imJszfDao.queryHzFyhjJszf(parametersje);

            if (calculatemap6.get("BQJS") != null) {
                lws_Temp6 = MapAttributeUtils.getDouble("BQJS",calculatemap6);
            }
            lws_hzxxbqjs.get(1).put(
                    "BQJS", MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(1)) - lws_Temp6);

            // ４、取现金支票(xjzp)
            List<Map<String, Object>> lws_hzxxxjzp = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapzfhj = imZyjsDao.queryHzZfhjXjzp(parametersje);
            if (mapzfhj.get("XJZP") != null) {
                lws_hzxxxjzp.add(0, mapzfhj);
            } else {
                mapzfhj.put("XJZP", 0);
                lws_hzxxxjzp.add(0, mapzfhj);
            }
            Map<String, Object> mapjkhj1 = imZyjsDao.queryHzJkhjXjzp(parametersje);
            if (mapjkhj1.get("XJZP") != null) {
                lws_hzxxxjzp.add(1, mapjkhj1);
            } else {
                mapjkhj1.put("XJZP", 0);
                lws_hzxxxjzp.add(1, mapjkhj1);
            }
            Map<String, Object> mapxjzp3 = new HashMap<String, Object>();
            mapxjzp3.put("XJZP", 0);
            lws_hzxxxjzp.add(2, mapxjzp3);
            // Map<String, Object> defaultmap2 = new HashMap<String, Object>();
            // defaultmap2.put("XJZP", 0);
            // lws_hzxxxjzp.add(2, defaultmap2);

            double lws_Temp3 = 0.00;
            Map<String, Object> calculatemap2 = imJszfDao.queryHzZfhjXjzp(parametersje);
            if (calculatemap2.get("XJZP") != null) {
                lws_Temp3 = MapAttributeUtils.getDouble("XJZP",calculatemap2);
            }
            double lws_Temp4 = 0.00;
            Map<String, Object> calculatemap3 = imJszfDao.queryHzJkhjZfXjzp(parametersje);
            if (calculatemap3.get("XJZP") != null) {
                lws_Temp4 = MapAttributeUtils.getDouble("XJZP",calculatemap3);
            }
            double yqfje = 0.00;
            double dqfje = 0.00;
            Map<String, Object> qfjemap3 = imZyjsDao.queryHzCyzj(parametersje);
            if (qfjemap3.get("ZFHJ") != null) {
                yqfje = MapAttributeUtils.getDouble("JKHJ",qfjemap3);
                dqfje = MapAttributeUtils.getDouble("ZFHJ",qfjemap3);
                lws_hzxxxjzp.get(0).put("QFJE", 0);
                lws_hzxxxjzp.get(1).put("QFJE", yqfje);
                lws_hzxxxjzp.get(2).put("QFJE", dqfje);
            } else {
                lws_hzxxxjzp.get(0).put("QFJE", 0);
                lws_hzxxxjzp.get(1).put("QFJE", 0);
                lws_hzxxxjzp.get(2).put("QFJE", 0);
            }
            lws_hzxxxjzp.get(0).put(
                    "XJZP", MapAttributeUtils.getDouble("XJZP",lws_hzxxxjzp.get(0)) - lws_Temp3);

            lws_hzxxxjzp.get(1).put(
                    "XJZP", MapAttributeUtils.getDouble("XJZP",lws_hzxxxjzp.get(1))- lws_Temp4);

            // 5、取其它应收(qtys)
            List<Map<String, Object>> lws_hzxxqtys = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapzfhjzfhj = imZyjsDao.queryQtys(parametersje);
            if (mapzfhjzfhj.get("QTYS") != null) {
                lws_hzxxqtys.add(0, mapzfhjzfhj);
            } else {
                mapzfhjzfhj.put("QTYS", 0);
                lws_hzxxqtys.add(0, mapzfhjzfhj);
            }
            Map<String, Object> defaultmap3 = new HashMap<String, Object>();
            defaultmap3.put("QTYS", 0);
            lws_hzxxqtys.add(1, defaultmap3);
            lws_hzxxqtys.add(2, defaultmap3);
            // Map<String, Object> mapzfhjzfhj1 = dao
            // .doLoad("SELECT sum(FYHJ - ZFHJ) as QTYS FROM IM_ZYJS IM_ZYJS WHERE IM_ZYJS.HZRQ=:adt_hzrq AND (IM_ZYJS.JSLX=3) AND IM_ZYJS.BRXZ IN (SELECT PUB_BRXZ.BRXZ FROM PUB_BRXZ PUB_BRXZ WHERE PUB_BRXZ.DBPB=0) and JGID=:gl_jgid",
            // parametersje);
            // if (mapzfhjzfhj1.get("QTYS") != null) {
            // lws_hzxxqtys.add(2, mapzfhjzfhj1);
            // } else {
            // mapzfhjzfhj1.put("QTYS", 0);
            // lws_hzxxqtys.add(2, mapzfhjzfhj1);
            // }
            double lws_Temp5 = 0.00;
            Map<String, Object> calculatemap4 = imZyjsDao.queryHzQtysZf(parametersje);
            if (calculatemap4.get("QTYS") != null) {
                lws_Temp5 = MapAttributeUtils.getDouble("QTYS",calculatemap4);
            }
            lws_hzxxqtys.get(0).put(
                    "QTYS", MapAttributeUtils.getDouble("QTYS",lws_hzxxqtys.get(0))-lws_Temp5);

            // 6、取参保应收(cbys)
            List<Map<String, Object>> lws_hzxxcbys = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapfyhjfyhj = imZyjsDao.queryHzCbys(parametersje);
            if (mapfyhjfyhj.get("CBYS") != null) {
                lws_hzxxcbys.add(0, mapfyhjfyhj);
            } else {
                mapfyhjfyhj.put("CBYS", 0);
                lws_hzxxcbys.add(0, mapfyhjfyhj);
            }
            Map<String, Object> defaultmap4 = new HashMap<String, Object>();
            defaultmap4.put("CBYS", 0);
            lws_hzxxcbys.add(1, defaultmap4);
            lws_hzxxcbys.add(2, defaultmap4);
            // Map<String, Object> mapfyhjfyhj1 = dao
            // .doLoad("SELECT sum(FYHJ - ZFHJ) as CBYS FROM IM_ZYJS IM_ZYJS WHERE IM_ZYJS.HZRQ=:adt_hzrq AND (IM_ZYJS.JSLX=3) AND IM_ZYJS.BRXZ IN ( SELECT PUB_BRXZ.BRXZ FROM PUB_BRXZ PUB_BRXZ WHERE PUB_BRXZ.DBPB>0) and JGID=:gl_jgid",
            // parametersje);
            // if (mapfyhjfyhj1.get("CBYS") != null) {
            // lws_hzxxcbys.add(2, mapfyhjfyhj1);
            // } else {
            // mapfyhjfyhj1.put("CBYS", 0);
            // lws_hzxxcbys.add(2, mapfyhjfyhj1);
            // }
            double lws_Temp7 = 0.00;
            Map<String, Object> calculatemap5 = imZyjsDao.queryHzCbysZf(parametersje);
            if (calculatemap5.get("CBYS") != null) {
                lws_Temp7 = MapAttributeUtils.getDouble("CBYS",calculatemap5);
            }
            lws_hzxxcbys.get(0).put("CBYS", MapAttributeUtils.getDouble("CBYS",lws_hzxxcbys.get(0))-lws_Temp7);


            // 7、计算本期余额(bqye) = 上期结存 + 本期发生 - 本期结算
            List<Double> lws_hzxxbqye = new ArrayList<Double>();
            lws_hzxxbqye.add(
                    0, MapAttributeUtils.getDouble("SQJC",lws_hzxxsqjc.get(0))+ MapAttributeUtils.getDouble("BQFS",lws_hzxxbqfs.get(0))- MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(0)));

            lws_hzxxbqye.add(
                    1, MapAttributeUtils.getDouble("SQJC",lws_hzxxsqjc.get(1))+ MapAttributeUtils.getDouble("BQFS",lws_hzxxbqfs.get(1))- MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(1)));

            lws_hzxxbqye.add(
                    2, MapAttributeUtils.getDouble("SQJC",lws_hzxxsqjc.get(2))+ MapAttributeUtils.getDouble("BQFS",lws_hzxxbqfs.get(2))- MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(2)));


            // 7. 将汇总信息写入IM_JZHZ
            for (int ll_row = 0; ll_row < 3; ll_row++) {
                Map<String, Object> IM_JZHZmap = new HashMap<String, Object>();
                IM_JZHZmap.put("HZRQ", adt_hzrq);
                IM_JZHZmap.put("XMBH", ll_row + 1);
                IM_JZHZmap.put("SQJC", lws_hzxxsqjc.get(ll_row).get("SQJC"));
                IM_JZHZmap.put("BQFS", lws_hzxxbqfs.get(ll_row).get("BQFS"));
                IM_JZHZmap.put(
                        "BQJS", MapAttributeUtils.getDouble("BQJS",lws_hzxxbqjs.get(ll_row))+ MapAttributeUtils.getDouble("QFJE",lws_hzxxxjzp.get(ll_row)));

                IM_JZHZmap.put("XJZP", lws_hzxxxjzp.get(ll_row).get("XJZP"));
                IM_JZHZmap.put("CYDJ", 0);
                IM_JZHZmap.put("QFJE", lws_hzxxxjzp.get(ll_row).get("QFJE"));
                IM_JZHZmap.put("CBJE", lws_hzxxcbys.get(ll_row).get("CBYS"));
                IM_JZHZmap.put("QTJE", lws_hzxxqtys.get(ll_row).get("QTYS"));
                IM_JZHZmap.put(
                        "BQYE",
                        Double.parseDouble(lws_hzxxbqye.get(ll_row) + "")
                                - MapAttributeUtils.getDouble("QFJE",lws_hzxxxjzp.get(ll_row)));
                IM_JZHZmap.put("YHJE", 0);
                IM_JZHZmap.put("JGID", gl_jgid);
                IM_JZHZmap = BUHISUtil.transformLowerCase(IM_JZHZmap);
                imJzhzDao.insert(IM_JZHZmap);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00061");
        }
    }
    /**
     *汇总日期
     * @param adt_hzrq
     *
     * @return
     *
     */
    public  void wf_create_fyhz(Date adt_hzrq,SysUser user) {
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        // User user = (User) ctx.get("user.instance");
        // String gl_jgid = user.get("manageUnit.id");

        Integer gl_jgid = user.getHospitalId();// 用户的机构ID
        List<Map<String, Object>> IM_FEE_FYHZlist = new ArrayList<Map<String, Object>>();
        try {
            adt_hzrq = sdfdatetime
                    .parse(sdfdate.format(adt_hzrq) + " 00:00:00");
            Map<String, Object> parametershzrq = new HashMap<String, Object>();
            Date ldt_sqhzrq = null; // 上期汇总日期
            parametershzrq.put("gl_jgid", gl_jgid);
            Map<String, Object> hzrqmap = imFeeFyhzDao.queryMaxHzrq(parametershzrq);
            if (hzrqmap !=null && hzrqmap.get("HZRQ") != null) {
                ldt_sqhzrq = sdfdatetime.parse(hzrqmap.get("HZRQ") + "");
            }
            // １、取上期结存(sqjc)
            Map<String, Object> parameterslds_sqjc = new HashMap<String, Object>();
            parameterslds_sqjc.put("adt_hzrq", ldt_sqhzrq);
            parameterslds_sqjc.put("al_jgid", gl_jgid);
            List<Map<String, Object>> lds_sqjc = imFeeFyhzDao.queryHzSqjc(parameterslds_sqjc);

            for (int ll_Row = 0; ll_Row < lds_sqjc.size(); ll_Row++) {
                Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                IM_FEE_FYHZmap.put("FYXM", lds_sqjc.get(ll_Row).get("FYXM"));
                IM_FEE_FYHZmap.put("SQJC", lds_sqjc.get(ll_Row).get("FYJE"));
                IM_FEE_FYHZmap.put("JGID", gl_jgid);
                IM_FEE_FYHZmap.put("BQFS", 0.00);
                IM_FEE_FYHZmap.put("BQJS", 0.00);
                IM_FEE_FYHZmap.put("SJJC", 0.00);
                IM_FEE_FYHZmap.put("BQJC", 0.00);
                IM_FEE_FYHZlist.add(IM_FEE_FYHZmap);
            }
            // ２、取本期发生(bqfs)
            Map<String, Object> parametersfyje = new HashMap<String, Object>();
            parametersfyje.put("adt_hzrq", adt_hzrq);
            parametersfyje.put("al_jgid", gl_jgid);
            List<Map<String, Object>> lds_bqfs = imFeeFymxDao.queryHzBqfs(parametersfyje);

            List<Map<String, Object>> lds_bqfslist = new ArrayList<Map<String, Object>>();
            int sign1 = 0;
            for (int ll_Row = 0; ll_Row < lds_bqfs.size(); ll_Row++) {
                if (IM_FEE_FYHZlist.size() > 0) {
                    for (int j = 0; j < IM_FEE_FYHZlist.size(); j++) {
                        if (lds_bqfs
                                .get(ll_Row)
                                .get("FYXM")
                                .toString()
                                .equals(IM_FEE_FYHZlist.get(j).get("FYXM")
                                        .toString())) {
                            IM_FEE_FYHZlist.get(j).put(
                                    "BQFS",
                                    Double.parseDouble(IM_FEE_FYHZlist.get(j).get(
                                            "BQFS")
                                            + "")
                                            + Double.parseDouble(lds_bqfs.get(
                                            ll_Row).get("FYJE")
                                            + ""));
                            sign1 = 1;
                            break;
                        }
                    }
                    if (sign1 == 0) {
                        Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                        IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                        IM_FEE_FYHZmap
                                .put("FYXM", lds_bqfs.get(ll_Row).get("FYXM"));
                        IM_FEE_FYHZmap.put("SQJC", 0.00);
                        IM_FEE_FYHZmap.put("JGID", gl_jgid);
                        IM_FEE_FYHZmap.put(
                                "BQFS",
                                Double.parseDouble(lds_bqfs.get(ll_Row).get(
                                        "FYJE")
                                        + ""));
                        IM_FEE_FYHZmap.put("BQJS", 0.00);
                        IM_FEE_FYHZmap.put("SJJC", 0.00);
                        IM_FEE_FYHZmap.put("BQJC", 0.00);
                        lds_bqfslist.add(IM_FEE_FYHZmap);
                    }
                } else {
                    Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                    IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                    IM_FEE_FYHZmap.put("FYXM", lds_bqfs.get(ll_Row).get("FYXM"));
                    IM_FEE_FYHZmap.put("SQJC", 0.00);
                    IM_FEE_FYHZmap.put("JGID", gl_jgid);
                    IM_FEE_FYHZmap.put(
                            "BQFS",
                            Double.parseDouble(lds_bqfs.get(ll_Row).get("FYJE")
                                    + ""));
                    IM_FEE_FYHZmap.put("BQJS", 0.00);
                    IM_FEE_FYHZmap.put("SJJC", 0.00);
                    IM_FEE_FYHZmap.put("BQJC", 0.00);
                    lds_bqfslist.add(IM_FEE_FYHZmap);
                }
            }
            if (lds_bqfslist.size() > 0) {
                IM_FEE_FYHZlist.addAll(lds_bqfslist);
            }
            // ３、取本期结算(bqjs)
            List<Map<String, Object>> lds_bqjs = imZyjsDao.queryHzBqjs(parametersfyje);
            List<Map<String, Object>> lds_bqjslist = new ArrayList<Map<String, Object>>();
            int sign2 = 0;
            for (int ll_Row = 0; ll_Row < lds_bqjs.size(); ll_Row++) {
                if (IM_FEE_FYHZlist.size() > 0) {
                    for (int j = 0; j < IM_FEE_FYHZlist.size(); j++) {
                        if (lds_bqjs
                                .get(ll_Row)
                                .get("FYXM")
                                .toString()
                                .equals(IM_FEE_FYHZlist.get(j).get("FYXM")
                                        .toString())) {
                            IM_FEE_FYHZlist.get(j).put(
                                    "BQJS",
                                    Double.parseDouble(IM_FEE_FYHZlist.get(j).get(
                                            "BQJS")
                                            + "")
                                            + Double.parseDouble(lds_bqjs.get(
                                            ll_Row).get("FYJE")
                                            + ""));
                            sign2 = 1;
                            break;
                        }
                    }
                    if (sign2 == 0) {
                        Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                        IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                        IM_FEE_FYHZmap
                                .put("FYXM", lds_bqjs.get(ll_Row).get("FYXM"));
                        IM_FEE_FYHZmap.put("SQJC", 0.00);
                        IM_FEE_FYHZmap.put("JGID", gl_jgid);
                        IM_FEE_FYHZmap.put("BQFS", 0.00);
                        IM_FEE_FYHZmap.put(
                                "BQJS",
                                Double.parseDouble(lds_bqjs.get(ll_Row).get(
                                        "FYJE")
                                        + ""));
                        IM_FEE_FYHZmap.put("SJJC", 0.00);
                        IM_FEE_FYHZmap.put("BQJC", 0.00);
                        lds_bqjslist.add(IM_FEE_FYHZmap);
                    }

                } else {
                    Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                    IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                    IM_FEE_FYHZmap.put("FYXM", lds_bqjs.get(ll_Row).get("FYXM"));
                    IM_FEE_FYHZmap.put("SQJC", 0.00);
                    IM_FEE_FYHZmap.put("JGID", gl_jgid);
                    IM_FEE_FYHZmap.put("BQFS", 0.00);
                    IM_FEE_FYHZmap.put(
                            "BQJS",
                            Double.parseDouble(lds_bqjs.get(ll_Row).get("FYJE")
                                    + ""));
                    IM_FEE_FYHZmap.put("SJJC", 0.00);
                    IM_FEE_FYHZmap.put("BQJC", 0.00);
                    lds_bqjslist.add(IM_FEE_FYHZmap);
                }
            }
            if (lds_bqjslist.size() > 0) {
                IM_FEE_FYHZlist.addAll(lds_bqjslist);
            }
            // ４、取实际结存(sjjc)
            List<Map<String, Object>> lds_sjjc = imFeeFymxDao.queryHzSjjc(parametersfyje);

            List<Map<String, Object>> lds_sjjclist = new ArrayList<Map<String, Object>>();
            int sign3 = 0;
            for (int ll_Row = 0; ll_Row < lds_sjjc.size(); ll_Row++) {
                if (IM_FEE_FYHZlist.size() > 0) {
                    for (int j = 0; j < IM_FEE_FYHZlist.size(); j++) {
                        if (lds_sjjc
                                .get(ll_Row)
                                .get("FYXM")
                                .toString()
                                .equals(IM_FEE_FYHZlist.get(j).get("FYXM")
                                        .toString())) {
                            IM_FEE_FYHZlist.get(j).put(
                                    "SJJC", MapAttributeUtils.getDouble("SJJC",IM_FEE_FYHZlist.get(j))+ MapAttributeUtils.getDouble("FYJE",lds_sjjc.get(ll_Row)));

                            sign3 = 1;
                            break;
                        }
                    }
                    if (sign3 == 0) {
                        Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                        IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                        IM_FEE_FYHZmap
                                .put("FYXM", lds_sjjc.get(ll_Row).get("FYXM"));
                        IM_FEE_FYHZmap.put("SQJC", 0);
                        IM_FEE_FYHZmap.put("JGID", gl_jgid);
                        IM_FEE_FYHZmap.put("BQFS", 0.00);
                        IM_FEE_FYHZmap.put("BQJS", 0.00);
                        IM_FEE_FYHZmap.put(
                                "SJJC", MapAttributeUtils.getDouble("FYJE",lds_sjjc.get(ll_Row)));

                        IM_FEE_FYHZmap.put("BQJC", 0.00);
                        lds_sjjclist.add(IM_FEE_FYHZmap);
                    }

                } else {
                    Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                    IM_FEE_FYHZmap.put("HZRQ", adt_hzrq);
                    IM_FEE_FYHZmap.put("FYXM", lds_sjjc.get(ll_Row).get("FYXM"));
                    IM_FEE_FYHZmap.put("SQJC", 0);
                    IM_FEE_FYHZmap.put("JGID", gl_jgid);
                    IM_FEE_FYHZmap.put("BQFS", 0.00);
                    IM_FEE_FYHZmap.put("BQJS", 0.00);
                    IM_FEE_FYHZmap.put("SJJC", MapAttributeUtils.getDouble("FYJE",lds_sjjc.get(ll_Row)));
                    IM_FEE_FYHZmap.put("BQJC", 0.00);
                    lds_sjjclist.add(IM_FEE_FYHZmap);
                }
            }
            if (lds_sjjclist.size() > 0) {
                IM_FEE_FYHZlist.addAll(lds_sjjclist);
            }
            // ５、计算本期结存(bqjc) = 上期结存 + 本期发生 - 本期结算
            for (int ll_Row = 0; ll_Row < IM_FEE_FYHZlist.size(); ll_Row++) {
                IM_FEE_FYHZlist.get(ll_Row).put(
                        "BQJC", MapAttributeUtils.getDouble("SQJC",IM_FEE_FYHZlist.get(ll_Row))
                                + MapAttributeUtils.getDouble("BQFS",IM_FEE_FYHZlist.get(ll_Row))
                                + MapAttributeUtils.getDouble("BQJS",IM_FEE_FYHZlist.get(ll_Row)));

                // 删除全部项目为零的记录
                if ((MapAttributeUtils.getDouble("SQJC",IM_FEE_FYHZlist.get(ll_Row))
                        + MapAttributeUtils.getDouble("BQFS",IM_FEE_FYHZlist.get(ll_Row))
                        + MapAttributeUtils.getDouble("BQJS",IM_FEE_FYHZlist.get(ll_Row))
                        + MapAttributeUtils.getDouble("BQJC",IM_FEE_FYHZlist.get(ll_Row))
                        + MapAttributeUtils.getDouble("SJJC",IM_FEE_FYHZlist.get(ll_Row)))==0.0) {
                    IM_FEE_FYHZlist.remove(ll_Row);
                    ll_Row--;
                }
            }
            // ５、计算本期结存(bqjc) = 上期结存 + 本期发生 - 本期结算
            for (int ll_Row = 0; ll_Row < IM_FEE_FYHZlist.size(); ll_Row++) {
                Map<String, Object> IM_FEE_FYHZmap = new HashMap<String, Object>();
                IM_FEE_FYHZmap.put("HZRQ", IM_FEE_FYHZlist.get(ll_Row).get("HZRQ"));
                IM_FEE_FYHZmap.put("FYXM", IM_FEE_FYHZlist.get(ll_Row).get("FYXM"));
                IM_FEE_FYHZmap.put("SQJC", IM_FEE_FYHZlist.get(ll_Row).get("SQJC"));
                IM_FEE_FYHZmap.put("JGID", IM_FEE_FYHZlist.get(ll_Row).get("JGID"));
                IM_FEE_FYHZmap.put("BQFS", IM_FEE_FYHZlist.get(ll_Row).get("BQFS"));
                IM_FEE_FYHZmap.put("BQJS", IM_FEE_FYHZlist.get(ll_Row).get("BQJS"));
                IM_FEE_FYHZmap.put("SJJC", IM_FEE_FYHZlist.get(ll_Row).get("SJJC"));
                IM_FEE_FYHZmap.put("BQJC", IM_FEE_FYHZlist.get(ll_Row).get("BQJC"));
                IM_FEE_FYHZmap = BUHISUtil.transformLowerCase(IM_FEE_FYHZmap);
                imFeeFyhzDao.insert(IM_FEE_FYHZmap);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00061");

        }
    }

    /**
     * 住院病人费用结账汇总日报表Fields
     * @param save
     * @return
     */
    public List<Map<String, Object>> getCollectFieldsOne(String save,List<Map<String, Object>> list,SysUser user){
        //查询所有的员工
        List<HrPersonnelModel> ersonnelList=hrPersonnelService.findByEntity(null);
        Map<Integer, List<HrPersonnelModel>> sysUserMap=ersonnelList.stream().collect(groupingBy(HrPersonnelModel::getPersonid));
        String saveSign = "";
        if(StringUtils.isNotBlank(save)){
            saveSign=save;
        }
        if (!saveSign.equals("1")) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).put(
                        "CZGH",sysUserMap.get(MapAttributeUtils.getInteger("CZGH",list.get(i)))) ;
            }

        }
        return  list;

    }
    /**
     * 住院病人费用结账汇总日报表parameters
     * @param save
     * @return
     */
    public Map<String, Object> getCollectParametersOne(String save,String beginDate,String endDate,String hzrq,SysUser user) {
        Map<String, Object> resp=new HashMap<>();
        String saveSign = "";
        if (StringUtils.isNotBlank(save)) {
            saveSign = save;
        }
        // 查询
        if (saveSign.equals("2")) {
            resp= doCollectInquiry(beginDate,endDate,user);

        }
        // 产生
        else {
            resp= doSetCollectParameters(beginDate,endDate,hzrq,user);
        }
        return resp;
    }

    /**
     * 查询汇总产生的报表参数(汇总日报一)
     * @param beginDate
     * @param endDate
     * @param user
     * @return
     */
    private Map<String, Object> doSetCollectParameters(String beginDate, String endDate,String hzrq, SysUser user) {
        Map<String, Object> ret=new HashMap<>();
        List<Map<String, Object>> resp=new ArrayList<>();
        Map<String, Object> response=new HashMap<>();
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String jgname = user.getHospitalName();
        // 用户的机构ID
        Integer manaUnitId = user.getHospitalId();
        response.put("title", jgname);
        Date id_dqrq = null;
        Date ldt_CurrentDateTime = null;
        Date idt_hzrq=null;
//		String QTYSFB="";
        Map<String, Object> idt_LastDate = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(hzrq)) {
                ldt_CurrentDateTime = sdfdate.parse(sdfdate.format(new Date()));
                id_dqrq = sdfdate.parse(hzrq);
                if (id_dqrq.getTime() == ldt_CurrentDateTime.getTime()) {
                    idt_hzrq = sdfdatetime.parse(sdfdatetime.format(new Date()));
                } else {
                    idt_hzrq = sdfdatetime.parse(sdfdate.format(id_dqrq)
                            + " 23:59:59");
                }
            }
            int hzsign = wf_IsGather(idt_hzrq,idt_LastDate,user);
            if (hzsign == 1) {
                String[] lS_JZRQ = sdfdate.format(idt_hzrq).split("-| |:");
                String JZRQ = lS_JZRQ[0] + "年" + lS_JZRQ[1] + "月" + lS_JZRQ[2]
                        + "日(" + lS_JZRQ[3] + ":" + lS_JZRQ[4] + ")";
                response.put("HZRQ", "汇总日期:" + JZRQ);
            } else {
                String[] lS_JZRQ = sdfdate.format(idt_hzrq).split("-| ");
                String JZRQ = lS_JZRQ[0] + "年" + lS_JZRQ[1] + "月" + lS_JZRQ[2]
                        + "日";
                response.put("HZRQ", "汇总日期:" + JZRQ);
            }

            Map<String, Object> parametershzbd = new HashMap<String, Object>();
            parametershzbd.put("adt_jzrq",
                    sdfdatetime.parse(sdfdatetime.format(idt_hzrq)));
            parametershzbd.put("al_jgid", manaUnitId);
            // 日结汇总汇总表单
            resp = imJzxxDao.queryHzParamList(parametershzbd);

            for (int i = 0; i < resp.size(); i++) {
                resp.get(i).put("CYSR",
                        String.format("%1$.2f", resp.get(i).get("CYSR")));
                resp.get(i).put("YJJE",
                        String.format("%1$.2f", resp.get(i).get("YJJE")));
                resp.get(i).put("TPJE",
                        String.format("%1$.2f", resp.get(i).get("TPJE")));
                resp.get(i).put("YSJE",
                        String.format("%1$.2f", resp.get(i).get("YSJE")));
                resp.get(i).put("YSXJ",
                        String.format("%1$.2f", resp.get(i).get("YSXJ")));
                resp.get(i).put("TYJJ",
                        String.format("%1$.2f", resp.get(i).get("TYJJ")));
                resp.get(i).put("YSQT",
                        String.format("%1$.2f", resp.get(i).get("YSQT")));
                resp.get(i).put("SRJE",
                        String.format("%1$.2f", resp.get(i).get("SRJE")));
                if (resp.get(i).get("TCZC") != null) {
                    resp.get(i).put("TCZC",
                            String.format("%1$.2f", resp.get(i).get("TCZC")));
                }
                if (resp.get(i).get("DBZC") != null) {
                    resp.get(i).put("DBZC",
                            String.format("%1$.2f", resp.get(i).get("DBZC")));
                }
                if (resp.get(i).get("ZXJZFY") != null) {
                    resp.get(i).put("ZXJZFY",
                            String.format("%1$.2f", resp.get(i).get("ZXJZFY")));
                }
                if (resp.get(i).get("GRXJZF") != null) {
                    resp.get(i).put("GRXJZF",
                            String.format("%1$.2f", resp.get(i).get("GRXJZF")));
                }
                if (resp.get(i).get("BCZHZF") != null) {
                    resp.get(i).put("BCZHZF",
                            String.format("%1$.2f", resp.get(i).get("BCZHZF")));
                }
                if (resp.get(i).get("AZQGFY") != null) {
                    resp.get(i).put("AZQGFY",
                            String.format("%1$.2f", resp.get(i).get("AZQGFY")));
                }

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("adt_jzrq",
                        sdfdatetime.parse(sdfdatetime.format(idt_hzrq)));
                parameters.put("al_jgid", manaUnitId);
                parameters.put("czgh", resp.get(i).get("CZGH")+"");
                List<Map<String, Object>> ids_brxz = imJzxxDao.queryHzIdsBrxzParam(parameters);

                List<Map<String, Object>> ids_fkfs = imJzxxDao.queryHzIdsFkfsParam(parameters);
                String  qtysFb="";
                String jzjeSt="0.00";
                if (ids_fkfs  != null && ids_fkfs .size() != 0) {
                    for(int n=0;n<ids_fkfs.size();n++){
                        qtysFb = qtysFb +ids_fkfs.get(n).get("FKMC")+ ":"
                                + String.format("%1$.2f",ids_fkfs.get(n).get("FKJE"))
                                + " ";
                    }
                }
                if (ids_brxz  != null && ids_brxz .size() != 0) {
                    for(int n=0;n<ids_brxz.size();n++){
                        if(Integer.parseInt(ids_brxz.get(n).get("DBPB")+"")==0){
                            jzjeSt= String.format("%1$.2f",parseDouble(jzjeSt) +(parseDouble(ids_brxz.get(n).get("FYHJ")+ "")-parseDouble(ids_brxz.get(n).get("ZFHJ")+ "")));
                        }else{
                            qtysFb = qtysFb +ids_brxz.get(n).get("XZMC")+ ":"
                                    + String.format("%1$.2f",(parseDouble(ids_brxz.get(n).get("FYHJ")+ "")-parseDouble(ids_brxz.get(n).get("ZFHJ")+ "")))
                                    + " ";
                        }
                    }
                    qtysFb = qtysFb+" "+"记账 :"+jzjeSt+" ";
                }

//				if (li.get(i).get("QTYSFB") == null) {
                resp.get(i).put("QTYSFB", qtysFb);
//				}
//				QTYSFB=li.get(i).get("QTYSFB")+" ";
            }

            List<Map<String, Object>> ids_brxz =imJzhzDao.queryHzIdsBrxz(parametershzbd);
            List<Map<String, Object>> ids_fkfs =imJzhzDao.queryHzIdsFkfs(parametershzbd);

            String  qtysFb="";
            String jzjeSt="0.00";
            if (ids_fkfs  != null && ids_fkfs .size() != 0) {
                for(int i=0;i<ids_fkfs.size();i++){
                    qtysFb = qtysFb +ids_fkfs.get(i).get("FKMC")+ ":"
                            + String.format("%1$.2f",ids_fkfs.get(i).get("FKJE"))
                            + " ";
                }
            }
            if (ids_brxz  != null && ids_brxz .size() != 0) {
                for(int i=0;i<ids_brxz.size();i++){
                    if(Integer.parseInt(ids_brxz.get(i).get("DBPB")+"")==0){
                        jzjeSt= String.format("%1$.2f",parseDouble(jzjeSt) +(parseDouble(ids_brxz.get(i).get("FYHJ")+ "")-parseDouble(ids_brxz.get(i).get("ZFHJ")+ "")));
                    }else{
                        qtysFb = qtysFb +ids_brxz.get(i).get("XZMC")+ ":"
                                + String.format("%1$.2f",(parseDouble(ids_brxz.get(i).get("FYHJ")+ "")-parseDouble(ids_brxz.get(i).get("ZFHJ")+ "")))
                                + " ";
                    }
                }
                qtysFb = qtysFb+" "+"记账 :"+jzjeSt+" ";
            }
            response.put("qtysFb", qtysFb);
            Map<String, Object> parametershj = imJzxxDao.queryHzShj(parametershzbd);
            if (parametershj.get("ZCYSR") != null) {
                response.put("ZCYSR",
                        String.format("%1$.2f", parametershj.get("ZCYSR")));
            } else {
                response.put("ZCYSR", "");
            }
            if (parametershj.get("ZYJJE") != null) {
                response.put("ZYJJE",
                        String.format("%1$.2f", parametershj.get("ZYJJE")));
            } else {
                response.put("ZYJJE", "");
            }
            if (parametershj.get("ZTPJE") != null) {
                response.put("ZTPJE",
                        String.format("%1$.2f", parametershj.get("ZTPJE")));
            } else {
                response.put("ZTPJE", "");
            }
            if (parametershj.get("ZFPZS") != null) {
                response.put("ZFPZS", parametershj.get("ZFPZS") + "");
            } else {
                response.put("ZFPZS", "");
            }
            if (parametershj.get("ZZPZS") != null) {
                response.put("ZZPZS", parametershj.get("ZZPZS") + "");
            } else {
                response.put("ZZPZS", "");
            }
            if (parametershj.get("ZSJZS") != null) {
                response.put("ZSJZS", parametershj.get("ZSJZS") + "");
            } else {
                response.put("ZSJZS", "");
            }
            if (parametershj.get("ZTYJJ") != null) {
                response.put("ZTYJJ",
                        String.format("%1$.2f", parametershj.get("ZTYJJ")));
            } else {
                response.put("ZTYJJ", "");
            }
            if (parametershj.get("ZYSJE") != null) {
                response.put("ZYSJE",
                        String.format("%1$.2f", parametershj.get("ZYSJE")));
            } else {
                response.put("ZYSJE", "");
            }
            if (parametershj.get("ZYSXJ") != null) {
                response.put("ZYSXJ",
                        String.format("%1$.2f", parametershj.get("ZYSXJ")));
            } else {
                response.put("ZYSXJ", "");

            }
            if (parametershj.get("ZYSQT") != null) {
                response.put("ZYSQT",
                        String.format("%1$.2f", parametershj.get("ZYSQT")));
            } else {
                response.put("ZYSQT", "");
            }
            if (parametershj.get("ZQTZS") != null) {
                response.put("ZQTZS", parametershj.get("ZQTZS") + "");
            } else {
                response.put("ZQTZS", "");
            }
            if (parametershj.get("ZSRJE") != null) {
                response.put("ZSRJE",
                        String.format("%1$.2f", parametershj.get("ZSRJE")));
            } else {
                response.put("ZSRJE", "");
            }
            if (parametershj.get("TCZC") != null) {
                response.put("TCZCHJ",
                        String.format("%1$.2f", parametershj.get("TCZC")));
            } else {
                response.put("TCZCHJ", "");
            }
            if (parametershj.get("DBZC") != null) {
                response.put("DBZCHJ",
                        String.format("%1$.2f", parametershj.get("DBZC")));
            } else {
                response.put("DBZCHJ", "");
            }
            if (parametershj.get("ZXJZFY") != null) {
                response.put("ZXJZFYHJ",
                        String.format("%1$.2f", parametershj.get("ZXJZFY")));
            } else {
                response.put("ZXJZFYHJ", "");
            }
            if (parametershj.get("GRXJZF") != null) {
                response.put("GRXJZFHJ",
                        String.format("%1$.2f", parametershj.get("GRXJZF")));
            } else {
                response.put("GRXJZFHJ", "");
            }
            if (parametershj.get("BCZHZF") != null) {
                response.put("BCZHZFHJ",
                        String.format("%1$.2f", parametershj.get("BCZHZF")));
            } else {
                response.put("BCZHZFHJ", "");
            }
            if (parametershj.get("AZQGFY") != null) {
                response.put("AZQGFYHJ",
                        String.format("%1$.2f", parametershj.get("AZQGFY")));
            } else {
                response.put("AZQGFYHJ", "");
            }

            List<Map<String, Object>> zfzslist = new ArrayList<Map<String, Object>>();
            StringBuffer sbfp = new StringBuffer();
            StringBuffer sbsj = new StringBuffer();
            zfzslist = imJzxxDao.queryHzZfpjParamList(parametershzbd);
            for (int i = 0; i < zfzslist.size(); i++) {
                if (Integer.parseInt(zfzslist.get(i).get("PJLB") + "") == 1) {
                    if (zfzslist.get(i).get("PJHM") != null) {
                        sbfp.append(zfzslist.get(i).get("PJHM") + " ");
                    }
                } else {
                    if (zfzslist.get(i).get("PJHM") != null) {
                        sbsj.append(zfzslist.get(i).get("PJHM") + " ");
                    }
                }
            }
            if (sbfp.toString() != null) {
                response.put("ZFFPHM", sbfp.toString());
            } else {
                response.put("ZFFPHM", "");
            }
            if (sbsj.toString() != null) {
                response.put("ZFSJHM", sbsj.toString());
            } else {
                response.put("ZFSJHM", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.put("list",resp);
        ret.put("params",response);
        return ret;
    }

    /**
     *
     * @param beginDate 汇总开始日期
     * @param endDate 汇总结束日期
     * @param user
     * @return
     */
    public Map<String, Object> doCollectInquiry(String beginDate,String endDate,SysUser user){
        List<Map<String, Object>> resp=new ArrayList<>();
        Map<String, Object> response=new HashMap<>();
        Map<String, Object> ret=new HashMap<>();
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfdate2 = new SimpleDateFormat("yyyy-MM-dd");

        String jgname = user.getHospitalName();
        // 用户的机构ID
        Integer manaUnitId = user.getHospitalId();
        response.put("title", jgname);
        Date adt_hzrq_start = null;
        Date adt_hzrq_end = null;
        try {
            if (StringUtils.isNotBlank(beginDate)) {
                try{
                    adt_hzrq_start = sdfdate.parse(beginDate);
                }catch(Exception e){
                    adt_hzrq_start = sdfdate2.parse(beginDate);
                }

            }
            if (StringUtils.isNotBlank(endDate)) {
                try{
                    adt_hzrq_end = sdfdate.parse(endDate);
                }catch(Exception e){
                    adt_hzrq_end = sdfdate2.parse(endDate);
                }

            }
            String[] lS_ksrq = (beginDate).split("-| ");
            String ksrq = lS_ksrq[0] + "年" + lS_ksrq[1] + "月" + lS_ksrq[2]
                    + "日";
            String[] lS_jsrq = (endDate).split("-| ");
            String jsrq = lS_jsrq[0] + "年" + lS_jsrq[1] + "月" + lS_jsrq[2]
                    + "日";
            response.put("HZRQ", "汇总日期:" + ksrq);
            Map<String, Object> parametershzbd = new HashMap<String, Object>();
            parametershzbd.put("adt_hzrq_b", adt_hzrq_start);
            parametershzbd.put("adt_hzrq_e", adt_hzrq_end);
            parametershzbd.put("al_jgid", manaUnitId);
            // 日结汇总汇总表单

            List<Map<String, Object>> resultList = imJzxxDao.queryHzList(parametershzbd);

            for (int i = 0; i < resultList.size(); i++) {


                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("adt_hzrq_b", adt_hzrq_start);
                parameters.put("adt_hzrq_e", adt_hzrq_end);
                parameters.put("al_jgid", manaUnitId);
                parameters.put("czgh", resultList.get(i).get("CZGH") + "");
                List<Map<String, Object>> ids_brxz =imJzhzDao.queryHzIdsBrxz(parameters);
                List<Map<String, Object>> ids_fkfs =imJzhzDao.queryHzIdsFkfs(parameters);
                String qtysFb = "";
                String jzjeSt = "0.00";
                if (ids_fkfs != null && ids_fkfs.size() != 0) {
                    for (int n = 0; n < ids_fkfs.size(); n++) {
                        qtysFb = qtysFb
                                + ids_fkfs.get(n).get("FKMC")
                                + ":"
                                + String.format("%1$.2f",
                                ids_fkfs.get(n).get("FKJE")) + " ";
                    }
                }
                if (ids_brxz != null && ids_brxz.size() != 0) {
                    for (int n = 0; n < ids_brxz.size(); n++) {
                        if (Integer.parseInt(ids_brxz.get(n).get("DBPB") + "") == 0) {
                            jzjeSt = String
                                    .format("%1$.2f", parseDouble(jzjeSt) + (parseDouble(ids_brxz.get(n).get("FYHJ") + "") - parseDouble(ids_brxz.get(n).get("ZFHJ") + "")));
                        } else {
                            qtysFb = qtysFb
                                    + ids_brxz.get(n).get("XZMC")
                                    + ":"
                                    + String.format(
                                    "%1$.2f",
                                    (parseDouble(ids_brxz.get(n).get(
                                            "FYHJ")
                                            + "") - parseDouble(ids_brxz
                                            .get(n).get("ZFHJ") + "")))
                                    + " ";
                        }
                    }
                    qtysFb = qtysFb + " " + "记账 :" + jzjeSt + " ";
                }
                resultList.get(i).put("QTYSFB", qtysFb);
                resultList.get(i).put("CYSR",
                        String.format("%1$.2f", resultList.get(i).get("CYSR")));
                resultList.get(i).put("YJJE",
                        String.format("%1$.2f", resultList.get(i).get("YJJE")));
                resultList.get(i).put("TPJE",
                        String.format("%1$.2f", resultList.get(i).get("TPJE")));
                resultList.get(i).put("YSJE",
                        String.format("%1$.2f", resultList.get(i).get("YSJE")));
                resultList.get(i).put("YSXJ",
                        String.format("%1$.2f", resultList.get(i).get("YSXJ")));
                resultList.get(i).put("TYJJ",
                        String.format("%1$.2f", resultList.get(i).get("TYJJ")));
                resultList.get(i).put("YSQT",
                        String.format("%1$.2f", resultList.get(i).get("YSQT")));
                resultList.get(i).put("SRJE",
                        String.format("%1$.2f", resultList.get(i).get("SRJE")));

                if (resultList.get(i).get("TCZC") != null) {
                    resultList.get(i).put(
                            "TCZC",
                            String.format("%1$.2f",
                                    resultList.get(i).get("TCZC")));
                }
                if (resultList.get(i).get("DBZC") != null) {
                    resultList.get(i).put(
                            "DBZC",
                            String.format("%1$.2f",
                                    resultList.get(i).get("DBZC")));
                }
                if (resultList.get(i).get("ZXJZFY") != null) {
                    resultList.get(i).put(
                            "ZXJZFY",
                            String.format("%1$.2f",
                                    resultList.get(i).get("ZXJZFY")));
                }
                if (resultList.get(i).get("GRXJZF") != null) {
                    resultList.get(i).put(
                            "GRXJZF",
                            String.format("%1$.2f",
                                    resultList.get(i).get("GRXJZF")));
                }
                if (resultList.get(i).get("BCZHZF") != null) {
                    resultList.get(i).put(
                            "BCZHZF",
                            String.format("%1$.2f",
                                    resultList.get(i).get("BCZHZF")));
                }
                if (resultList.get(i).get("AZQGFY") != null) {
                    resultList.get(i).put(
                            "AZQGFY",
                            String.format("%1$.2f",
                                    resultList.get(i).get("AZQGFY")));
                }
            }
            Map<String, Object> parametershj = imJzxxDao.queryHzhj(parametershzbd);
            List<Map<String, Object>> ids_brxz =imJzhzDao.queryHzIdsBrxzZf(parametershzbd);
            List<Map<String, Object>> ids_fkfs = imJzhzDao.queryHzIdsFkfsZf(parametershzbd);


            String qtysFb = "";
            String jzjeSt = "0.00";
            if (ids_fkfs != null && ids_fkfs.size() != 0) {
                for (int i = 0; i < ids_fkfs.size(); i++) {
                    qtysFb = qtysFb
                            + ids_fkfs.get(i).get("FKMC")
                            + ":"
                            + String.format("%1$.2f",
                            ids_fkfs.get(i).get("FKJE")) + " ";
                }
            }
            if (ids_brxz != null && ids_brxz.size() != 0) {
                for (int i = 0; i < ids_brxz.size(); i++) {
                    if (Integer.parseInt(ids_brxz.get(i).get("DBPB") + "") == 0) {
                        jzjeSt = String.format(
                                "%1$.2f",
                                parseDouble(jzjeSt)
                                        + (parseDouble(ids_brxz.get(i).get(
                                        "FYHJ")
                                        + "") - parseDouble(ids_brxz
                                        .get(i).get("ZFHJ") + "")));
                    } else {
                        qtysFb = qtysFb
                                + ids_brxz.get(i).get("XZMC")
                                + ":"
                                + String.format(
                                "%1$.2f",
                                (parseDouble(ids_brxz.get(i)
                                        .get("FYHJ") + "") - parseDouble(ids_brxz
                                        .get(i).get("ZFHJ") + "")))
                                + " ";
                    }
                }
                qtysFb = qtysFb + " " + "记账 :" + jzjeSt + " ";
            }
            response.put("qtysFb", qtysFb);
            if (parametershj.get("ZCYSR") != null) {
                response.put("ZCYSR",
                        String.format("%1$.2f", parametershj.get("ZCYSR")));
            } else {
                response.put("ZCYSR", "");
            }
            if (parametershj.get("ZYJJE") != null) {
                response.put("ZYJJE",
                        String.format("%1$.2f", parametershj.get("ZYJJE")));
            } else {
                response.put("ZYJJE", "");
            }
            if (parametershj.get("ZTPJE") != null) {
                response.put("ZTPJE",
                        String.format("%1$.2f", parametershj.get("ZTPJE")));
            } else {
                response.put("ZTPJE", "");
            }
            if (parametershj.get("ZFPZS") != null) {
                response.put("ZFPZS", parametershj.get("ZFPZS") + "");
            } else {
                response.put("ZFPZS", "");
            }
            if (parametershj.get("ZSJZS") != null) {
                response.put("ZSJZS", parametershj.get("ZSJZS") + "");
            } else {
                response.put("ZSJZS", "");
            }
            if (parametershj.get("ZTYJJ") != null) {
                response.put("ZTYJJ",
                        String.format("%1$.2f", parametershj.get("ZTYJJ")));
            } else {
                response.put("ZTYJJ", "");
            }
            if (parametershj.get("ZYSJE") != null) {
                response.put("ZYSJE",
                        String.format("%1$.2f", parametershj.get("ZYSJE")));
            } else {
                response.put("ZYSJE", "");
            }
            if (parametershj.get("ZYSXJ") != null) {
                response.put("ZYSXJ",
                        String.format("%1$.2f", parametershj.get("ZYSXJ")));
            } else {
                response.put("ZYSXJ", "");

            }
            if (parametershj.get("ZYSQT") != null) {
                response.put("ZYSQT",
                        String.format("%1$.2f", parametershj.get("ZYSQT")));
            } else {
                response.put("ZYSQT", "");
            }
            if (parametershj.get("ZQTZS") != null) {
                response.put("ZQTZS", parametershj.get("ZQTZS") + "");
            } else {
                response.put("ZQTZS", "");
            }
            if (parametershj.get("ZSRJE") != null) {
                response.put("ZSRJE",
                        String.format("%1$.2f", parametershj.get("ZSRJE")));
            } else {
                response.put("ZSRJE", "");
            }
            if (parametershj.get("TCZC") != null) {
                response.put("TCZCHJ",
                        String.format("%1$.2f", parametershj.get("TCZC")));
            } else {
                response.put("TCZCHJ", "");
            }
            if (parametershj.get("DBZC") != null) {
                response.put("DBZCHJ",
                        String.format("%1$.2f", parametershj.get("DBZC")));
            } else {
                response.put("DBZCHJ", "");
            }
            if (parametershj.get("ZXJZFY") != null) {
                response.put("ZXJZFYHJ",
                        String.format("%1$.2f", parametershj.get("ZXJZFY")));
            } else {
                response.put("ZXJZFYHJ", "");
            }
            if (parametershj.get("GRXJZF") != null) {
                response.put("GRXJZFHJ",
                        String.format("%1$.2f", parametershj.get("GRXJZF")));
            } else {
                response.put("GRXJZFHJ", "");
            }
            if (parametershj.get("BCZHZF") != null) {
                response.put("BCZHZFHJ",
                        String.format("%1$.2f", parametershj.get("BCZHZF")));
            } else {
                response.put("BCZHZFHJ", "");
            }
            if (parametershj.get("AZQGFY") != null) {
                response.put("AZQGFYHJ",
                        String.format("%1$.2f", parametershj.get("AZQGFY")));
            } else {
                response.put("AZQGFYHJ", "");
            }

            List<Map<String, Object>> zfzslist = new ArrayList<Map<String, Object>>();
            StringBuffer sbfp = new StringBuffer();
            StringBuffer sbsj = new StringBuffer();
            zfzslist =imJzxxDao.queryHzzfzs(parametershzbd);
            for (int i = 0; i < zfzslist.size(); i++) {
                if (Integer.parseInt(zfzslist.get(i).get("PJLB") + "") == 1) {
                    if (zfzslist.get(i).get("PJHM") != null) {
                        sbfp.append(zfzslist.get(i).get("PJHM") + " ");
                    }
                } else {
                    if (zfzslist.get(i).get("PJHM") != null) {
                        sbsj.append(zfzslist.get(i).get("PJHM") + " ");
                    }
                }
            }
            if (sbfp.toString() != null && sbfp.length() > 0) {
                response.put(
                        "ZFFPHM",
                        sbfp.toString().substring(0,
                                sbfp.toString().length() - 1));
            } else {
                response.put("ZFFPHM", "");
            }
            if (sbsj.toString() != null && sbsj.length() > 0) {
                response.put(
                        "ZFSJHM",
                        sbsj.toString().substring(0,
                                sbsj.toString().length() - 1));
            } else {
                response.put("ZFSJHM", "");
            }
            List<Map<String, Object>> zfpjlist = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> parametersczgh = new HashMap<String, Object>();
                parametersczgh.put("adt_hzrq_b", adt_hzrq_start);
                parametersczgh.put("adt_hzrq_e", adt_hzrq_end);
                parametersczgh.put("as_czgh", resultList.get(i).get("CZGH"));
                parametersczgh.put("gl_jgid", manaUnitId);
                StringBuffer ls_jsfp_all = new StringBuffer();
                StringBuffer ls_jssj_all = new StringBuffer();
                zfpjlist =imJzxxDao.queryHzZfpjList(parametersczgh);
                for (int j = 0; j < zfpjlist.size(); j++) {
                    if (zfpjlist.get(j).get("QZPJ") != null) {
                        ls_jsfp_all.append(zfpjlist.get(j).get("QZPJ") + "");
                    }
                    if (zfpjlist.get(j).get("QZSJ") != null) {
                        ls_jssj_all.append(zfpjlist.get(j).get("QZSJ") + "");
                    }
                }
                if (ls_jsfp_all.toString() != null && ls_jsfp_all.length() > 0) {
                    resultList.get(i).put("QZPJ", ls_jsfp_all.toString());
                } else {
                    resultList.get(i).put("QZPJ", "");
                }
                if (ls_jssj_all.toString() != null && ls_jssj_all.length() > 0) {
                    resultList.get(i).put("QZSJ", ls_jssj_all.toString());
                } else {
                    resultList.get(i).put("QZSJ", "");
                }
            }
            resp.addAll(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.put("list",resp);
        ret.put("params",response);
        return ret;


    }

    public  int wf_IsGather(Date adt_hzrq, Map<String, Object> idt_LastDate,SysUser user) {
        SimpleDateFormat sdfdatetime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        // 用户的机构ID
        Integer gl_jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, Object> parametershzrq = new HashMap<String, Object>();
        try {
            Date ldt_Begin = sdfdate.parse(sdfdate.format(adt_hzrq));
            Date ldt_End = sdfdate.parse(sdfdate.format(BUHISUtil.getDateAfter(
                    adt_hzrq, 1)));
            parameters.put("ldt_Begin", ldt_Begin);
            parameters.put("ldt_End", ldt_End);
            parameters.put("gl_jgid", gl_jgid);
            parametershzrq.put("gl_jgid", gl_jgid);
            Integer l = imJzhzDao.queryHzCount(parameters);
            Map<String, Object> hzrqmap = imJzhzDao.queryMaxHzrq(parametershzrq);
            if (hzrqmap !=null && hzrqmap.get("HZRQ") != null) {
                idt_LastDate.put("idt_LastDate",
                        sdfdatetime.parse(hzrqmap.get("HZRQ") + ""));
            }
            if (l > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询汇总产生的报表参数(汇总日报二)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param user
     * @return
     */
    public Map<String, Object> doSetCollectParametersTwo(String save, String beginDate, String endDate, SysUser user) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> resp = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        String jgname = user.getHospitalName();
        // 用户的机构ID
        Integer manaUnitId = user.getHospitalId();
        response.put("title", jgname);

        Map<String, Object> parameters = new HashMap<String, Object>();
        Date adt_hzrq_start = null;
        Date adt_hzrq_end = null;
        try {
            if (StringUtils.isNotBlank(beginDate)) {
                adt_hzrq_start = sdfdate.parse(beginDate);
            }
            if (StringUtils.isNotBlank(endDate)) {
                adt_hzrq_end = sdfdate.parse(endDate);
            }
            String[] lS_ksrq = (beginDate).split("-| ");
            String ksrq = lS_ksrq[0] + "年" + lS_ksrq[1] + "月" + lS_ksrq[2]
                    + "日";
            String[] lS_jsrq = (endDate).split("-| ");
            String jsrq = lS_jsrq[0] + "年" + lS_jsrq[1] + "月" + lS_jsrq[2]
                    + "日";
            response.put("HZRQ", "汇总日期:" + ksrq + " 至 " + jsrq);
            parameters.put("adt_hzrq_b", adt_hzrq_start);
            parameters.put("adt_hzrq_e", adt_hzrq_end);
            parameters.put("al_jgid", manaUnitId);
            resp = imJzhzDao.queryHzListTwo(parameters);

            for (int i = 0; i < resp.size(); i++) {
//				String qtysFb="";
                if (Integer.parseInt(resp.get(i).get("XMBH") + "") == 1) {
                    resp.get(i).put("XMBH", "在院病人结算");
                    resp.get(i).put("CBJE",
                            String.format("%1$.2f", resp.get(i).get("CBJE")));
                    resp.get(i).put("QTJE",
                            String.format("%1$.2f", resp.get(i).get("QTJE")));
                    resp.get(i).put("QFJE","--");
                    resp.get(i).put("BQFS",String.format("%1$.2f", resp.get(i).get("BQFS")));

                    List<Map<String, Object>> ids_zyjs =imJzhzDao.queryHzIdsZyjsTwo(parameters);
                    List<Map<String, Object>> ids_brxz = imJzhzDao.queryHzIdsBrxzTwo(parameters);
                    String  qtysFb="";
                    String jzjeSt="0.00";
                    if (ids_zyjs  != null && ids_zyjs .size() != 0) {
                        for(int j=0;j<ids_zyjs.size();j++){
                            qtysFb = qtysFb +ids_zyjs.get(j).get("FKMC")+ ":"
                                    + String.format("%1$.2f",ids_zyjs.get(j).get("FKJE"))
                                    + " ";
                        }
                    }
                    if (ids_brxz  != null && ids_brxz .size() != 0) {
                        for(int j=0;j<ids_brxz.size();j++){
                            if(Integer.parseInt(ids_brxz.get(j).get("DBPB")+"")==0){
                                jzjeSt= String.format("%1$.2f",parseDouble(jzjeSt) +(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")));
                            }else{
                                qtysFb = qtysFb +ids_brxz.get(j).get("XZMC")+ ":"
                                        + String.format("%1$.2f",(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")))
                                        + " ";
                            }
                        }
                        qtysFb = qtysFb+" "+"记账 :"+jzjeSt+" ";
                    }
//					System.out.println("@@@"+qtysFb);
                    resp.get(i).put("qtysFb",qtysFb);
                } else if(Integer.parseInt(resp.get(i).get("XMBH") + "") == 2){
                    resp.get(i).put("XMBH", "预缴金");
                    resp.get(i).put("CBJE", "-");
                    resp.get(i).put("QTJE", "-");
                    resp.get(i).put("QFJE", resp.get(i).get("QFJE"));
                    resp.get(i).put("BQFS",String.format("%1$.2f", resp.get(i).get("BQFS")));

                    List<Map<String, Object>> ids_yjk = imJzhzDao.queryHzIdsYjkTwo(parameters);
                    String  qtysFb="";
                    if (ids_yjk  != null && ids_yjk .size() != 0) {
                        for(int j=0;j<ids_yjk.size();j++){
                            qtysFb = qtysFb +ids_yjk.get(j).get("FKMC")+ ":"
                                    + String.format("%1$.2f",ids_yjk.get(j).get("FKJE"))
                                    + " ";
                        }
                    }
                    resp.get(i).put("qtysFb",qtysFb);
                }else{
                    resp.get(i).put("XMBH", "出院终结");
                    resp.get(i).put("CBJE", "-");
                    resp.get(i).put("QTJE", "-");
                    resp.get(i).put("QFJE", resp.get(i).get("QFJE"));
                    resp.get(i).put("BQFS","--");
                    List<Map<String, Object>> ids_zyjs =imJzhzDao.queryHzIdsZyjsTwoCyzj(parameters);
                    List<Map<String, Object>> ids_brxz =imJzhzDao.queryHzIdsbrxzTwoCyzj(parameters);
                    String  qtysFb="";
                    String jzjeSt="0.00";
                    if (ids_zyjs  != null && ids_zyjs .size() != 0) {
                        for(int j=0;j<ids_zyjs.size();j++){
                            qtysFb = qtysFb +ids_zyjs.get(j).get("FKMC")+ ":"
                                    + String.format("%1$.2f",ids_zyjs.get(j).get("FKJE"))
                                    + " ";
                        }
                    }
                    if (ids_brxz  != null && ids_brxz .size() != 0) {
                        for(int j=0;j<ids_brxz.size();j++){
                            if(Integer.parseInt(ids_brxz.get(j).get("DBPB")+"")==0){
                                jzjeSt= String.format("%1$.2f",parseDouble(jzjeSt) +(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")));
                            }else{
                                qtysFb = qtysFb +ids_brxz.get(j).get("XZMC")+ ":"
                                        + String.format("%1$.2f",(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")))
                                        + " ";
                            }
                        }
                        qtysFb = qtysFb+" "+"记账 :"+jzjeSt+" ";
                    }
                    resp.get(i).put("qtysFb",qtysFb);
                }
                resp.get(i).put("SQJC",
                        String.format("%1$.2f", resp.get(i).get("SQJC")));

                resp.get(i).put("BQJS",
                        String.format("%1$.2f", resp.get(i).get("BQJS")));
                resp.get(i).put("XJZP",
                        String.format("%1$.2f", resp.get(i).get("XJZP")));
                resp.get(i).put("BQYE",
                        String.format("%1$.2f", resp.get(i).get("BQYE")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.put("params",response);
        ret.put("list",resp);
        return ret;
    }
    /**
     * 查询汇总产生的报表参数(汇总日报三)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param user
     * @return
     */
    public Map<String, Object> doSetCollectParametersThree(String save, String beginDate, String endDate, SysUser user) {
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> response = new HashMap<>();
        Integer jgid = user.getHospitalId();
        String jgname = user.getHospitalName();
        String czy = user.getUserName();
        response.put("title", jgname);
        response.put("CZY", czy);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("al_jgid", jgid);
        try {
            Date stardate = sdfdate.parse(beginDate);
            Date enddate = sdfdate.parse(endDate);
            String[] lS_ksrq = beginDate.split("-| ");
            String ksrq = lS_ksrq[0] + "年" + lS_ksrq[1] + "月" + lS_ksrq[2]
                    + "日";
            String[] lS_jsrq = endDate.split("-| ");
            String jsrq = lS_jsrq[0] + "年" + lS_jsrq[1] + "月" + lS_jsrq[2]
                    + "日";
            response.put("HZRQ",ksrq + " 至 " + jsrq);
            parameter.put("adt_hzrq", stardate);
            parameter.put("adt_hzrq_end", enddate);
            Map<String, Object> xmflMap = imSrhzDao.queryHzThree(parameter);
            //总计金额
            response.put("HJJE", String.format("%1$.2f", xmflMap.get("ZJJE")));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return  response;

    }

    /**
     * 查询汇总产生的报表参数(汇总日报三)List
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @param user
     * @return
     */
   public List<Map<String,Object>>  getCollectFieldsThree(String beginDate, String endDate,String hzrq, SysUser user){
       List<Map<String,Object>> resp=new ArrayList<>();
       SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");

       Integer jgid = user.getHospitalId();
       Map<String, Object> parameters = new HashMap<String, Object>();
       parameters.put("al_jgid", jgid);
       try {
           Date stardate = sdfdate.parse(beginDate);
           Date enddate = sdfdate.parse(endDate);
           parameters.put("adt_hzrq", stardate);
           parameters.put("adt_hzrq_end", enddate);
           List<Map<String, Object>> xmflList = imSrhzDao.qyeryHzFymxThree(parameters);
           for (int i = 0; i < xmflList.size(); i = i + 3) {
               Map<String, Object> cf = new HashMap<String, Object>();
               cf.put("XM1", xmflList.get(i).get("SFMC"));
               cf.put("JE1",
                       String.format("%1$.2f", xmflList.get(i).get("ZJJE")));
               if (i + 1 < xmflList.size()) {
                   cf.put("XM2", xmflList.get(i + 1).get("SFMC"));
                   cf.put("JE2",
                           String.format("%1$.2f",
                                   xmflList.get(i + 1).get("ZJJE")));
               }
               if (i + 2 < xmflList.size()) {
                   cf.put("XM3", xmflList.get(i + 2).get("SFMC"));
                   cf.put("JE3",
                           String.format("%1$.2f",
                                   xmflList.get(i + 2).get("ZJJE")));
               }
               resp.add(cf);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    return resp;
   }


    /**
     * 查询汇总产生的报表参数(汇总日报四)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param user
     * @return
     */
    public Map<String, Object> doSetCollectParametersFour(String save, String beginDate, String endDate, String hzrq, SysUser user) {
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> response = new HashMap<>();
        Integer jgid = user.getHospitalId();
        String jgname = user.getHospitalName();
        String czy = user.getUserName();
        response.put("title", jgname);
        response.put("CZY", czy);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("al_jgid", jgid);
        try {
            double zjje = 0.00;
            double tczf = 0.00;
            double bczhzf = 0.00;
            double srje = 0.00;
            Date stardate = sdfdate.parse(beginDate);
            Date enddate = sdfdate.parse(endDate);
            String[] lS_ksrq = beginDate.split("-| ");
            String ksrq = lS_ksrq[0] + "年" + lS_ksrq[1] + "月" + lS_ksrq[2]
                    + "日";
            String[] lS_jsrq =endDate.split("-| ");
            String jsrq = lS_jsrq[0] + "年" + lS_jsrq[1] + "月" + lS_jsrq[2]
                    + "日";
            response.put("HZRQ", ksrq + " 至 " + jsrq);
            parameter.put("adt_hzrq", stardate);
            parameter.put("adt_hzrq_end", new Date(enddate.getTime()+(1000*60*60*24)));
            List<Map<String, Object>> xmflList = imFeeFymxDao.queryHzFour(parameter);
            if (xmflList != null && xmflList.size() > 0) {
                if (xmflList.get(0).get("ZJJE") != null) {
                    response.put("HJJE", String.format("%1$.2f", xmflList
                            .get(0).get("ZJJE")));
                } else {
                    response.put("HJJE", String.format("%1$.2f", 0.00));
                }
                if (xmflList.get(0).get("ZJJE") != null) {
                    zjje = Double.parseDouble(xmflList.get(0).get("ZJJE") + "");
                }
            }

            Map<String, Object> xmflMap2 =imJzxxDao.queryHzFour(parameter);
            if (xmflMap2 != null && xmflMap2.size() > 0) {
                if (xmflMap2.get("TCZF") != null) {
                    tczf = Double.parseDouble(xmflMap2.get("TCZF") + "");
                }
                if (xmflMap2.get("ZHZF") != null) {
                    bczhzf = Double.parseDouble(xmflMap2.get("ZHZF") + "");
                }
                if (xmflMap2.get("SRJE") != null) {
                    srje = Double.parseDouble(xmflMap2.get("SRJE") + "");
                }
            }
            Map<String, Object> xmflMap3 = imHzryDao.queryHzBrCount(parameter);
            // 出院人数
            Map<String, Object> xmflMap4 =imHzryDao.queryHzBrCountWithCypb(parameter);
//			response.put("qtysFb", qtysFb);// 其中:

            List<Map<String, Object>> ids_zyjs = imJzhzDao.queryHzIdsZyjsFour(parameter);
            List<Map<String, Object>> ids_brxz =imJzhzDao.queryHzbrxzFour(parameter);
            String  qtysFb="";
            String jzjeSt="0.00";
            if (ids_zyjs  != null && ids_zyjs .size() != 0) {
                for(int j=0;j<ids_zyjs.size();j++){
                    qtysFb = qtysFb +ids_zyjs.get(j).get("FKMC")+ ":"
                            + String.format("%1$.2f",ids_zyjs.get(j).get("FKJE"))
                            + " ";
                }
            }
            if (ids_brxz  != null && ids_brxz .size() != 0) {
                for(int j=0;j<ids_brxz.size();j++){
                    if(Integer.parseInt(ids_brxz.get(j).get("DBPB")+"")==0){
                        jzjeSt= String.format("%1$.2f",parseDouble(jzjeSt) +(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")));
                    }else{
                        qtysFb = qtysFb +ids_brxz.get(j).get("XZMC")+ ":"
                                + String.format("%1$.2f",(parseDouble(ids_brxz.get(j).get("FYHJ")+ "")-parseDouble(ids_brxz.get(j).get("ZFHJ")+ "")))
                                + " ";
                    }
                }
                qtysFb = qtysFb+" "+"记账 :"+jzjeSt+" ";
            }
//			System.out.println("@@@"+qtysFb);
            response.put("qtysFb",qtysFb);
            response.put("CYRS", String.valueOf(xmflMap4.get("ZJJE")));// 出院人数:
            response.put("RYRS", String.valueOf(xmflMap3.get("ZJJE")));// 入院人数:
            response.put("XJJE", String.format("%1$.2f", zjje - tczf - bczhzf));// 现金金额:
            response.put("TCZF", String.format("%1$.2f", tczf));// 统筹支付:
            response.put("ZHZF", String.format("%1$.2f", bczhzf));// 账户支付:
            response.put("JZJE", String.format("%1$.2f", 0.00));// 记账金额:
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    /**
     * 查询汇总产生的报表参数(汇总日报四)List
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param hzrq
     * @param user
     * @return
     */
    public List<Map<String,Object>>  getCollectFieldsFour(String save, String beginDate, String endDate, String hzrq, SysUser user){
        List<Map<String,Object>> resp=new ArrayList<>();
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");

        Integer jgid = user.getHospitalId();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);
        try {
            Date stardate = sdfdate.parse(beginDate);
            Date enddate = sdfdate.parse(endDate);
            parameters.put("adt_hzrq", stardate);
            parameters.put("adt_hzrq_end", enddate);
            List<Map<String, Object>> xmflAll = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> xmflList = imFeeFymxDao.queryHzFourList(parameters);

            // 追加西药基药/中药基药
            List<Map<String, Object>> xmflList2 = imFeeFymxDao.queryHzFourZxyList(parameters);
            xmflAll.addAll(xmflList);
            xmflAll.addAll(xmflList2);
            for (int i = 0; i < xmflAll.size(); i = i + 3) {
                Map<String, Object> cf = new HashMap<String, Object>();
                cf.put("XM1", xmflAll.get(i).get("SFMC"));
                cf.put("JE1",
                        String.format("%1$.2f", xmflAll.get(i).get("ZJJE")));
                if (i + 1 < xmflAll.size()) {
                    cf.put("XM2", xmflAll.get(i + 1).get("SFMC"));
                    cf.put("JE2",
                            String.format("%1$.2f",
                                    xmflAll.get(i + 1).get("ZJJE")));
                }
                if (i + 2 < xmflAll.size()) {
                    cf.put("XM3", xmflAll.get(i + 2).get("SFMC"));
                    cf.put("JE3",
                            String.format("%1$.2f",
                                    xmflAll.get(i + 2).get("ZJJE")));
                }
                resp.add(cf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    /**
     * 查询汇总产生的报表参数(汇总日报五)
     *
     * @param save
     * @param beginDate
     * @param endDate
     * @param user
     * @return
     */
    public Map<String, Object> doSetCollectParametersFive(String save, String beginDate, String endDate, Integer bq, String bqName, SysUser user) {
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> resp = new ArrayList<>();
        Integer jgid = user.getHospitalId();
        String jgname = user.getHospitalName();
        String czy = user.getUserName();
        response.put("title", jgname);
        response.put("CZY", czy);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("al_jgid", jgid);

        try {


            Date stardate = sdfdate.parse(beginDate);
            Date enddate = sdfdate.parse(endDate);
            parameters.put("adt_hzrq", stardate);
            parameters.put("adt_hzrq_end", enddate);


                parameters.put("FYBQ", bq);

            List<Map<String, Object>> xmflList = imFeeFymxDao.queryHzFiveList(parameters);

            for (int i = 0; i < xmflList.size(); i = i + 3) {
                Map<String, Object> cf = new HashMap<String, Object>();
                cf.put("XM1", xmflList.get(i).get("SFMC"));
                cf.put("JE1",
                        String.format("%1$.2f", xmflList.get(i).get("ZJJE")));
                if (i + 1 < xmflList.size()) {
                    cf.put("XM2", xmflList.get(i + 1).get("SFMC"));
                    cf.put("JE2",
                            String.format("%1$.2f",
                                    xmflList.get(i + 1).get("ZJJE")));
                }
                if (i + 2 < xmflList.size()) {
                    cf.put("XM3", xmflList.get(i + 2).get("SFMC"));
                    cf.put("JE3",
                            String.format("%1$.2f",
                                    xmflList.get(i + 2).get("ZJJE")));
                }
                resp.add(cf);
            }
            // -------------合计--------------
            String[] lS_ksrq = beginDate.split("-| ");
            String ksrq = lS_ksrq[0] + "年" + lS_ksrq[1] + "月" + lS_ksrq[2]
                    + "日";
            String[] lS_jsrq = endDate.split("-| ");
            String jsrq = lS_jsrq[0] + "年" + lS_jsrq[1] + "月" + lS_jsrq[2]
                    + "日";
            Map<String, Object> hzparameters = new HashMap<String, Object>();
            hzparameters.put("al_jgid", jgid);
            hzparameters.put("adt_hzrq", stardate);
            hzparameters.put("adt_hzrq_end", enddate);
            hzparameters.put("bq", bq);
            StringBuffer hzhql5 = new StringBuffer();

                hzparameters.put("FYBQ", bq);

            List<Map<String, Object>> xmhjList = imFeeFymxDao.queryHzFive(hzparameters);

            double hjje = 0.00;
            if (xmhjList != null && xmhjList.size() > 0) {
                hjje = parseDouble(xmhjList.get(0).get("ZJJE"));
            }
            response.put("title", jgname);
            response.put("KSMC", bqName);
            response.put("HZRQ", ksrq + " 至 " + jsrq);
            response.put("CZY", czy);
            response.put("HJJE", String.format("%1$.2f", hjje));// 总计金额
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.put("list", resp);
        ret.put("params", response);
        return ret;
    }

    /**
     * 款管理-催款清单报表List数据
     * @param debtInventoryReportReq
     * @param user
     * @return
     */
    public List<DebtInventoryResp> doSetDebtInventoryParameters(DebtInventoryReportReq debtInventoryReportReq, SysUser user) {
        DebtInventoryReq debtInventoryReq =new DebtInventoryReq();
        debtInventoryReq.setTxje(debtInventoryReportReq.getTxje());
        debtInventoryReq.setJgid(user.getHospitalId());
        debtInventoryReq.setBrbq(debtInventoryReportReq.getBrbq());
        debtInventoryReq.setBrxm(debtInventoryReportReq.getBrxm());
        debtInventoryReq.setCkje(debtInventoryReportReq.getCkje());
        debtInventoryReq.setZyhm(debtInventoryReportReq.getZyhm());
        debtInventoryReq.setBrks(debtInventoryReportReq.getBrks());


        debtInventoryReportReq.setJgid(user.getHospitalId());
        List<DebtInventoryResp> resp= new ArrayList<>();
        if("1".equals(debtInventoryReportReq.getQueryType())){
            DebtInventoryPageResp debtInventoryPageResp= imHzrySer.queryDebtInventoryPage(debtInventoryReq);
            if(debtInventoryPageResp.getDebtInventoryRespList().size()!=0){


                resp= debtInventoryPageResp.getDebtInventoryRespList();

            }

        }
        else if("2".equals(debtInventoryReportReq.getQueryType())){
            DebtInventoryPageResp debtInventoryPageResp= imHzrySer.queryDebtInventoryPage(debtInventoryReq);
            if(debtInventoryPageResp.getDebtInventoryRespList().size()!=0){
                Map<String,List<DebtInventoryResp>> map=  debtInventoryPageResp.getDebtInventoryRespList().stream().collect(groupingBy(DebtInventoryResp::getZyhm));
                //获得前台用户选择”是“的住院号 数据集
             if(StringUtils.isNotBlank(debtInventoryReportReq.getZyhmStr())){
                 String[] zyhms = debtInventoryReportReq.getZyhmStr().split(",");

                 for(int i=0;i < zyhms.length;i++) {
                     String[] zyhmArr=(zyhms[i].split("&"));
                     BigDecimal bjje=new BigDecimal(zyhmArr[1]);
                     if(bjje.compareTo(BigDecimal.ZERO)==1){
                         map.get(zyhmArr[0]).get(0).setBjje(bjje);

                         if(StringUtils.isNotBlank(zyhmArr[2])){
                             map.get(zyhmArr[0]).get(0).setType(1);
                         }
                         if(StringUtils.isNotBlank(zyhmArr[3])){
                             map.get(zyhmArr[0]).get(0).setType(2);
                         }
                         resp.add(map.get(zyhmArr[0]).get(0));
                     }

                     }
                 }


             }


        }

        return resp;

    }

    /**
     * 款管理-催款清单报表Fields  参数
     *
     * @param debtInventoryReportReq
     * @param user
     * @return
     */
    public List<Map<String, Object>> getDebtInventoryFields(List<DebtInventoryResp> list, SysUser user) {
        List<Map<String, Object>> resp = new ArrayList<>();
        String jgmc = user.getHospitalName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (DebtInventoryResp debtInventoryResp : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("JGMC", jgmc);
            if (debtInventoryResp.getType().equals(1)) {
                map.put("BQKS", "科室:");
                // 哪个病区或者哪个科室
                map.put("KSBQ", debtInventoryResp.getKsmc());
            }
            if(debtInventoryResp.getType().equals(2)){
                map.put("BQKS","病区:");
                map.put("KSBQ",debtInventoryResp.getBqmc());
            }

            map.put("BJJE",debtInventoryResp.getBjje());
          /*  if(debtInventoryReportReq.getCkje().compareTo(MapAttributeUtils.getBigDecimal("BJJE",map))==1){
              map.put("BJJE",debtInventoryResp.getQfje().compareTo(BigDecimal.ZERO)==-1?0:debtInventoryResp.getQfje().abs());
            }*/

            map.put("ZYHM",debtInventoryResp.getZyhm());
            map.put("BRXM",debtInventoryResp.getBrxm());
            map.put("BRCH",debtInventoryResp.getBrch());
            map.put("ZFJE",debtInventoryResp.getZfje());
            map.put("ZJJE",debtInventoryResp.getZjje());
            map.put("QFJE",debtInventoryResp.getQfje());
            map.put("JKJE",debtInventoryResp.getJkje());
            map.put("CSNY",sdf.format(debtInventoryResp.getCsny()));
            map.put("BRXB",zyDicSer.getBrxbDic().get(debtInventoryResp.getBrxb().toString()));
            map.put("BRXZ",zyDicSer.getBrxzDic().get(debtInventoryResp.getBrxz()));
            // 当前时间
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            int Year = c.get(Calendar.YEAR);
            int Month = c.get(Calendar.MONTH);
            int Day = c.get(Calendar.DATE);
            map.put("Year", Year);
            map.put("Month", Month+1);
            map.put("Day", Day);

            resp.add(map);
        }





        return resp;
    }


    /**
     * 在院病人费用汇总报表Params信息
     * @param inHospitalPatientReportReq
     * @param user
     * @return
     */
    public InHospitalPatientReportAllResp InHospitalPatientReportParams(InHospitalPatientReportReq inHospitalPatientReportReq, SysUser user) {
        InHospitalPatientReportAllResp resp=new InHospitalPatientReportAllResp();
        List<InHospitalPatientReportResp> brxxList=new ArrayList<>();
        List<Map<String,Object>> zybrMap=imFeeFymxDao.queryInHospitalPatientReport(inHospitalPatientReportReq);
        DicSfxmlb dicSfxmlbParam=new DicSfxmlb();
        dicSfxmlbParam.setZysy(1);
        List<DicSfxmlb> dicSfxmlbList= dicSfxmlbService.findByEntity(dicSfxmlbParam);
        Map<String,List<Map<String,Object>>> zybrxxMap=zybrMap.stream().collect(Collectors.groupingBy(this::zyhmKey));

        for(String zyhm:zybrxxMap.keySet()){
            InHospitalPatientReportResp brxx=new InHospitalPatientReportResp();
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setZyhm(MapAttributeUtils.getString("zyhm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrch(MapAttributeUtils.getString("brch",zybrxxMap.get(zyhm).get(0)));
            brxx.setRyrq(Timestamp.valueOf(MapAttributeUtils.getString("ryrq",zybrxxMap.get(zyhm).get(0))));
            brxx.setRyrq(Timestamp.valueOf(MapAttributeUtils.getString("ryrq",zybrxxMap.get(zyhm).get(0))));
            brxx.setZyys(MapAttributeUtils.getString("zyys",zybrxxMap.get(zyhm).get(0)));



            //总费用
            brxx.setHjfy(new BigDecimal(zybrxxMap.get(zyhm).stream().mapToDouble(this::fyhjKey).sum()).setScale(2,BigDecimal.ROUND_HALF_UP));
            //计算收费项目集合
            brxx.setInHospitalPatientReportSfxmRespList(querySfxmList(zybrxxMap.get(zyhm),dicSfxmlbList));
            //住院天数
            String cyrq= MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0));
            if(StringUtils.isBlank(cyrq)){
                cyrq=new Timestamp(System.currentTimeMillis()).toString();
            }
            brxx.setZyts(imFeeFymxSer.queryZyts(brxx.getRyrq().toString(),cyrq).getData());
            //计算药占比
            brxx.setYphj(queryYzb(zybrxxMap.get(zyhm)));

            brxx.setYzb(brxx.getYphj().divide(brxx.getHjfy(),2, BigDecimal.ROUND_HALF_UP));

            brxxList.add(brxx);
        }
        resp.setInHospitalPatientReportRespList(brxxList);
        //计算总的收费项目
        resp.setInHospitalPatientReportHjRespList(queryFyhjRespList(brxxList));

        return resp;
    }

    /**
     * 计算总的出院收费项目合计
     * @param brxxList
     * @return
     */
    private List<DischargedPatientReportHjResp> queryCyFyhjRespList(List<DischargedPatientReportResp> brxxList) {
        List<DischargedPatientReportHjResp> respList=new ArrayList<>();
        List<InHospitalPatientReportSfxmResp> sfxmRespList=new ArrayList<>();
        BigDecimal zfhj=new BigDecimal(0);
        BigDecimal hjfy=new BigDecimal(0);
        BigDecimal ybhj=new BigDecimal(0);
        for(DischargedPatientReportResp dischargedPatientReportResp:brxxList){
            //费用合计求和
            sfxmRespList.addAll(dischargedPatientReportResp.getInHospitalPatientReportSfxmRespList());
            hjfy=hjfy.add(dischargedPatientReportResp.getHjfy());
            zfhj=zfhj.add(dischargedPatientReportResp.getZfhj());
            ybhj=ybhj.add(dischargedPatientReportResp.getYbhj());
        }
        //自负合计
        DischargedPatientReportHjResp zfhjBean=new DischargedPatientReportHjResp();
        zfhjBean.setSfxm(777777);
        zfhjBean.setFyhj(zfhj);
        //费用合计
        DischargedPatientReportHjResp hjfyBean=new DischargedPatientReportHjResp();
        hjfyBean.setSfxm(888888);
        hjfyBean.setFyhj(hjfy);
        //医保支付合计
        DischargedPatientReportHjResp ybzfBean=new DischargedPatientReportHjResp();
        ybzfBean.setSfxm(666666);
        ybzfBean.setFyhj(ybhj);



        //分组求和
        Map<Integer,List<InHospitalPatientReportSfxmResp>> groupmap=sfxmRespList.stream().collect(Collectors.groupingBy(InHospitalPatientReportSfxmResp::getSfxm));
        for(Integer sfxm:groupmap.keySet()){
            List<InHospitalPatientReportSfxmResp> mapList=  groupmap.get(sfxm);
            DischargedPatientReportHjResp respBean=new DischargedPatientReportHjResp();
            respBean.setSfxm(sfxm);
            double sum = 0.0;
            for (InHospitalPatientReportSfxmResp inHospitalPatientReportSfxmResp : mapList) {
                double fyhj = inHospitalPatientReportSfxmResp.getFyhj().doubleValue();
                sum += fyhj;
            }
            respBean.setFyhj(new BigDecimal(sum).setScale(2,BigDecimal.ROUND_HALF_UP) );
            respList.add(respBean);
        }
        respList.add(hjfyBean);
        respList.add(zfhjBean);
        respList.add(ybzfBean);
        return respList;

    }
    /**
     * 计算总的住院收费项目合计
     * @param brxxList
     * @return
     */
    private List<InHospitalPatientReportHjResp> queryFyhjRespList(List<InHospitalPatientReportResp> brxxList) {
        List<InHospitalPatientReportHjResp> respList=new ArrayList<>();
        List<InHospitalPatientReportSfxmResp> sfxmRespList=new ArrayList<>();
        BigDecimal ypfh=new BigDecimal(0);
        BigDecimal hjfy=new BigDecimal(0);
        for(InHospitalPatientReportResp inHospitalPatientReportResp:brxxList){
            //费用合计求和
            sfxmRespList.addAll(inHospitalPatientReportResp.getInHospitalPatientReportSfxmRespList());
            //计算药占比
            ypfh=ypfh.add(inHospitalPatientReportResp.getYphj());
            hjfy=hjfy.add(inHospitalPatientReportResp.getHjfy());
        }
        //药占比
        InHospitalPatientReportHjResp yzbBean=new InHospitalPatientReportHjResp();
        yzbBean.setSfxm(999999);
        yzbBean.setFyhj(hjfy.compareTo(BigDecimal.ZERO)==0?new BigDecimal(0):ypfh.divide(hjfy,2,BigDecimal.ROUND_HALF_UP));
        //费用合计
        InHospitalPatientReportHjResp hjfyBean=new InHospitalPatientReportHjResp();
        hjfyBean.setSfxm(888888);
        hjfyBean.setFyhj(hjfy);

        //分组求和
        Map<Integer,List<InHospitalPatientReportSfxmResp>> groupmap=sfxmRespList.stream().collect(Collectors.groupingBy(InHospitalPatientReportSfxmResp::getSfxm));
        for(Integer sfxm:groupmap.keySet()){
            List<InHospitalPatientReportSfxmResp> mapList=  groupmap.get(sfxm);
            InHospitalPatientReportHjResp respBean=new InHospitalPatientReportHjResp();
            respBean.setSfxm(sfxm);
            double sum = 0.0;
            for (InHospitalPatientReportSfxmResp inHospitalPatientReportSfxmResp : mapList) {
                double fyhj = inHospitalPatientReportSfxmResp.getFyhj().doubleValue();
                sum += fyhj;
            }
            respBean.setFyhj(new BigDecimal(sum).setScale(2,BigDecimal.ROUND_HALF_UP) );
            respList.add(respBean);
        }
        respList.add(yzbBean);
        respList.add(hjfyBean);
        return respList;

    }
    /**
     * 计算总的病区收费项目合计
     * @param brxxList
     * @return
     */
    private List<BqPatientReportHjResp> queryBqFyhjRespList(List<BqPatientReportResp> brxxList) {
        List<BqPatientReportHjResp> respList=new ArrayList<>();
        List<InHospitalPatientReportSfxmResp> sfxmRespList=new ArrayList<>();
        BigDecimal zfhj=new BigDecimal(0);
        BigDecimal hjfy=new BigDecimal(0);
        BigDecimal ybhj=new BigDecimal(0);
        BigDecimal ypfh=new BigDecimal(0);
        for(BqPatientReportResp bqPatientReportResp:brxxList){
            //费用合计求和
            sfxmRespList.addAll(bqPatientReportResp.getInHospitalPatientReportSfxmRespList());
            hjfy=hjfy.add(bqPatientReportResp.getHjfy());
            zfhj=zfhj.add(bqPatientReportResp.getZfhj());
            ybhj=ybhj.add(bqPatientReportResp.getYbhj());
            ypfh=ypfh.add(bqPatientReportResp.getYphj());

        }
        //药占比
        InHospitalPatientReportHjResp yzbBean=new InHospitalPatientReportHjResp();
        yzbBean.setSfxm(999999);
        yzbBean.setFyhj(hjfy.compareTo(BigDecimal.ZERO)==0?new BigDecimal(0): ypfh.divide(hjfy,2,BigDecimal.ROUND_HALF_UP));
        //自负合计
        BqPatientReportHjResp zfhjBean=new BqPatientReportHjResp();
        zfhjBean.setSfxm(777777);
        zfhjBean.setFyhj(zfhj);
        //费用合计
        BqPatientReportHjResp hjfyBean=new BqPatientReportHjResp();
        hjfyBean.setSfxm(888888);
        hjfyBean.setFyhj(hjfy);
        //医保支付合计
        BqPatientReportHjResp ybzfBean=new BqPatientReportHjResp();
        ybzfBean.setSfxm(666666);
        ybzfBean.setFyhj(ybhj);
        //分组求和
        Map<Integer,List<InHospitalPatientReportSfxmResp>> groupmap=sfxmRespList.stream().collect(Collectors.groupingBy(InHospitalPatientReportSfxmResp::getSfxm));
        for(Integer sfxm:groupmap.keySet()){
            List<InHospitalPatientReportSfxmResp> mapList=  groupmap.get(sfxm);
            BqPatientReportHjResp respBean=new BqPatientReportHjResp();
            respBean.setSfxm(sfxm);
            double sum = 0.0;
            for (InHospitalPatientReportSfxmResp inHospitalPatientReportSfxmResp : mapList) {
                double fyhj = inHospitalPatientReportSfxmResp.getFyhj().doubleValue();
                sum += fyhj;
            }
            respBean.setFyhj(new BigDecimal(sum).setScale(2,BigDecimal.ROUND_HALF_UP) );
            respList.add(respBean);
        }
        respList.add(hjfyBean);
        respList.add(zfhjBean);
        respList.add(ybzfBean);
        return respList;

    }

    /**
     * 出院病人费用汇总报表params信息
     * @param dischargedPatientReportReq
     * @param user
     * @return
     */
    public DischargedPatientReportAllResp queryDischargedPatientReportParams(DischargedPatientReportReq dischargedPatientReportReq, SysUser user) {
        DischargedPatientReportAllResp resp=new DischargedPatientReportAllResp();
        List<DischargedPatientReportResp> brxxList=new ArrayList<>();
        List<Map<String,Object>> cybrMap=imFeeFymxDao.queryDischargedPatientReport(dischargedPatientReportReq);
        DicSfxmlb dicSfxmlbParam=new DicSfxmlb();
        dicSfxmlbParam.setZysy(1);
        List<DicSfxmlb> dicSfxmlbList= dicSfxmlbService.findByEntity(dicSfxmlbParam);
        Map<Integer,String> brxzMap=zyDicSer.getBrxzDic();
        Map<String,List<Map<String,Object>>> zybrxxMap=cybrMap.stream().collect(Collectors.groupingBy(this::zyhmKey));
        for(String zyhm:zybrxxMap.keySet()){
            DischargedPatientReportResp brxx=new DischargedPatientReportResp();
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setZyhm(MapAttributeUtils.getString("zyhm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrch(MapAttributeUtils.getString("brch",zybrxxMap.get(zyhm).get(0)));
            brxx.setRyrq(Timestamp.valueOf(MapAttributeUtils.getString("ryrq",zybrxxMap.get(zyhm).get(0))));
            brxx.setCyrq(Timestamp.valueOf(MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0))));

            brxx.setZyys(MapAttributeUtils.getString("zyys",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxz(MapAttributeUtils.getInteger("brxz",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxzStr(brxzMap.get(brxx.getBrxz()));

            //总费用
            brxx.setHjfy(new BigDecimal(zybrxxMap.get(zyhm).stream().mapToDouble(this::fyhjKey).sum()).setScale(2,BigDecimal.ROUND_HALF_UP));
            //自负合计
            brxx.setZfhj(new BigDecimal(zybrxxMap.get(zyhm).stream().mapToDouble(this::zfhjKey).sum()).setScale(2,BigDecimal.ROUND_HALF_UP));

            //计算收费项目集合
            brxx.setInHospitalPatientReportSfxmRespList(querySfxmList(zybrxxMap.get(zyhm),dicSfxmlbList));
            //住院天数
            String cyrq= MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0));
            if(StringUtils.isBlank(cyrq)){
                cyrq=new Timestamp(System.currentTimeMillis()).toString();
            }
            brxx.setZyts(imFeeFymxSer.queryZyts(brxx.getRyrq().toString(),cyrq).getData());
            brxx.setYbhj(queryYbhj(zybrxxMap.get(zyhm)));

            brxxList.add(brxx);
        }
        resp.setDischargedPatientReportRespList(brxxList);
        //计算总的收费项目
        resp.setDischargedPatientReportHjRespList(queryCyFyhjRespList(brxxList));
        return resp;
    }



    /**
     * 缴款收据打印参数
     *
     * @param jkxh
     * @param user
     * @return
     */
    public Map<String, Object> queryJksjPrintReportParams(Integer jkxh) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("JKXH", jkxh);
        List<Map<String, Object>> IM_HZRY = imHzryDao.queryJksjPrintReportParams(parameters);
        if (IM_HZRY.size() > 0) {
            Map<String, Object> data = IM_HZRY.get(0);
            if (IM_HZRY.get(0).get("SJHM") != null) {
                response.put("PJHM", IM_HZRY.get(0).get("SJHM") + "");
            }
                response.put("ZYHM", IM_HZRY.get(0).get("ZYHM") + "");
                response.put("BRXZ", IM_HZRY.get(0).get("BRXZ") + "");
                response.put("JGID", IM_HZRY.get(0).get("JGID") + "");
                if ("1".equals(String.valueOf(data.get("BRXB")))) {
                    response.put("BRXB", "男");
                } else if ("2".equals(String.valueOf(data.get("BRXB")))) {
                    response.put("BRXB", "女");
                }
                if (data.get("CSNY") != null) {
                    response.put("CSNY", (data.get("CSNY") + "").substring(0, 10));
                    response.put("AGE", BUHISUtil.getPersonAge(DateUtils.parseToDateTime(data.get("CSNY") + ""), DateUtils.getNow()).get("age"));
                }
                if (data.get("ADDRESS") != null) {
                    response.put("ADDRESS", data.get("ADDRESS") + "");
                }
                if (data.get("TEL") != null) {
                    response.put("TEL", data.get("TEL") + "");
                }
                if (IM_HZRY.get(0).get("BRCH") != null) {
                    response.put("BRCH", IM_HZRY.get(0).get("BRCH") + "");
                }
                if (IM_HZRY.get(0).get("BRXM") != null) {
                    response.put("BRXM", IM_HZRY.get(0).get("BRXM") + "");
                }
                if (IM_HZRY.get(0).get("KSMC") != null) {
                    response.put("KSMC", IM_HZRY.get(0).get("KSMC") + "");
                }
                if (IM_HZRY.get(0).get("BQMC") != null) {
                    response.put("BQMC", IM_HZRY.get(0).get("BQMC") + "");
                }
                if (IM_HZRY.get(0).get("JKJE") != null) {
                    response.put("JKJE","￥ "+
                            String.format("%1$.2f", IM_HZRY.get(0).get("JKJE")));
                }
                double jkje = Double.parseDouble(IM_HZRY.get(0).get("JKJE")
                        + "");
                try {
                    response.put("DXJE", RmbUtil.toRMBUpper(String.valueOf(jkje)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //如果是预付卡支付
               /* if(IM_HZRY.get(0).get("JKFS").equals("20")){
                    String fphm = MapAttributeUtils.getString("SJHM",IM_HZRY.get(0));

                    List<Map<String, Object>> list = opCzjlDao.queryCzjlDyfp(fphm);
                    if (list.size() > 0) {
                        response.put("YFKXX", "预付卡余额：" + list.get(0).get("YE"));
                    }
                }*/
                PubFkfsModel fkfsParam = new PubFkfsModel();
                fkfsParam.setSylx(2);
                fkfsParam.setZfbz(0);

                List<PubFkfsModel> fkfsMapList = pubFkfsService.findByEntity(fkfsParam);
                Map<Integer, List<PubFkfsModel>> fkfsMap = fkfsMapList.stream().collect(groupingBy(PubFkfsModel::getFkfs));

                List<HrPersonnelModel> ersonnelList = hrPersonnelService.findByEntity(null);
                Map<Integer, List<HrPersonnelModel>> usermap = ersonnelList.stream().collect(groupingBy(HrPersonnelModel::getPersonid));
                response.put("JKFS", fkfsMap.get(MapAttributeUtils.getInteger("JKFS", IM_HZRY.get(0))).get(0).getFkmc());
//                response.put("JSR", usermap.get(MapAttributeUtils.getInteger("CZGH",IM_HZRY.get(0))).get(0).getPersonname());
                response.put("JSR", MapAttributeUtils.getInteger("CZGH", IM_HZRY.get(0)));

                if (IM_HZRY.get(0).get("ZPHM") != null) {
                    response.put("PKHM", IM_HZRY.get(0).get("ZPHM") + "");
                } else {
                    response.put("PKHM", "");
                }
                String[] jkDate = sdf.format(IM_HZRY.get(0).get("JKRQ")).split("-");
                response.put("JKN", jkDate[0]);
                response.put("JKY", jkDate[1]);
                response.put("JKT", jkDate[2]);
                Map<String, Object> BRXX = new HashMap<String, Object>();
                BRXX.put("JSLX", 0);
                BRXX.put("ZYH", IM_HZRY.get(0).get("ZYH"));
                BRXX = imHzryByDctworkSer.getTotalAmount(BRXX);
                double JKHJ = Double.parseDouble(BRXX.get("JKHJ") + "");
                response.put("JKHJ", String.format("%1$.2f", JKHJ));
                BRXX = imHzryByDctworkSer.getTotalAmount(BRXX);
                double ZFHJ = Double.parseDouble(BRXX.get("JKHJ") + "");
                response.put("ZFHJ", String.format("%1$.2f", ZFHJ));
                double SYHJ = BUHISUtil.getDouble(JKHJ - ZFHJ, 2);
                response.put("SYHJ", String.format("%1$.2f", SYHJ));
            }

        return response;
    }

    /**
     * 查询住院病人账卡费用清单-明细格式报表List
     * @param cardPatientCostPageReqReq
     * @param user
     * @return
     */
    public Map<String, Object> queryCardPatientCostDetailFields(CardPatientCostPageReq cardPatientCostPageReqReq, SysUser user) {
        Map<String, Object> retMap=new HashMap<>();
        List<CardPatientCostDetailResp> respList =imHzrySer.queryCardPatientCostDetail(cardPatientCostPageReqReq);
        CardPatientCostReq cardPatientCostReq = new CardPatientCostReq();
        BeanUtils.copyProperties(cardPatientCostPageReqReq,cardPatientCostReq);
        CardPatientCostBaseInfoResp cardPatientCostBaseInfoResp = imHzrySer.queryCardPatientCostBaseInfo(cardPatientCostReq, user.getHospitalId());

        Map<String, Object> response =new HashMap<>();
        List<Map<String, Object>> respListMap =new ArrayList();
        String title = user.getHospitalName();
        response.put("title", title+"病人住院费用清单");
        response.put("startDate", cardPatientCostPageReqReq.getStartDate());
        response.put("endDate",cardPatientCostPageReqReq.getEndDate());
        //计算住院天数
        String zyts= imFeeFymxSer.queryZyts(cardPatientCostPageReqReq.getStartDate(),cardPatientCostPageReqReq.getEndDate()).getData();
            // 拼装Params
            if(cardPatientCostBaseInfoResp!=null){
                response.put("BRXM",cardPatientCostBaseInfoResp.getBrxm());
                response.put("BRXZ",zyDicSer.getBrxzDic().get(cardPatientCostBaseInfoResp.getBrxz()));
                response.put("BRKS",zyDicSer.getBrksDic(user.getHospitalId()).get(cardPatientCostBaseInfoResp.getBrks()));
                response.put("ZYHM",cardPatientCostBaseInfoResp.getZyhm());
                response.put("RYRQ",cardPatientCostBaseInfoResp.getStartDate());
                response.put("CYRQ",cardPatientCostBaseInfoResp.getEndDate());
                response.put("DAYS",zyts);
                response.put("BRCH",cardPatientCostBaseInfoResp.getBrch());
                //费用合计计算
                response.put("ZFHJ", cardPatientCostBaseInfoResp.getZfhj());
                response.put("FYHJ", cardPatientCostBaseInfoResp.getFyhj());

                response.put("ZFLJ", cardPatientCostBaseInfoResp.getZflj());
                response.put("FYLJ", cardPatientCostBaseInfoResp.getFylj());

            }
            //拼装List
            for(CardPatientCostDetailResp cardPatientCostDetailResp:respList){
            Map<String,Object> map=new HashMap<>();
                map.put("FYMC",cardPatientCostDetailResp.getFymc());
                map.put("FYSL",cardPatientCostDetailResp.getFysl());
                map.put("FYDJ",cardPatientCostDetailResp.getFydj());
                map.put("FYJE",cardPatientCostDetailResp.getZjje());
                map.put("ZFBL",cardPatientCostDetailResp.getZfbl());
                map.put("FYKS",zyDicSer.getJkfsDic(cardPatientCostDetailResp.getFyks()) );
                map.put("GH",cardPatientCostDetailResp.getSrgh());
                map.put("BZ","");

                respListMap.add(map);
            }
        retMap.put("params",response);
        retMap.put("fields",respListMap);

        return retMap;
    }


    /**
     * 查询住院病人账卡费用清单-明细格式报表Params
     * @param cardPatientCostPageReqReq
     * @param user
     * @return
     */
    public Map<String, Object> queryCardPatientCostDetailParams(CardPatientCostPageReq cardPatientCostPageReqReq, SysUser user) {


        return  null;
    }

    private  String zyhmKey(Map<String,Object> map){
        return MapAttributeUtils.getString("zyhm",map);
    }
    //总计金额
    private  Double fyhjKey(Map<String,Object> map){
        return MapAttributeUtils.getDouble("zjje",map);
    }
    //自负合计
    private  Double zfhjKey(Map<String,Object> map){
        return MapAttributeUtils.getDouble("zfje",map);
    }
    private  Integer fyxmKey(Map<String,Object> map){
        return MapAttributeUtils.getInteger("fyxm",map);
    }
    //查询收费项目
    private List<InHospitalPatientReportSfxmResp> querySfxmList(List<Map<String,Object>> brxxmap,List<DicSfxmlb> dicSfxmlbs){
        List<InHospitalPatientReportSfxmResp> resps=new ArrayList<>();
        Map<Integer,List<Map<String,Object>>> map=brxxmap.stream().collect(Collectors.groupingBy(this::fyxmKey));
        for(DicSfxmlb dicSfxmlb:dicSfxmlbs){
            InHospitalPatientReportSfxmResp inHospitalPatientReportSfxmResp=new InHospitalPatientReportSfxmResp();
            inHospitalPatientReportSfxmResp.setSfxm(dicSfxmlb.getSfxm());
            inHospitalPatientReportSfxmResp.setSfmc(dicSfxmlb.getSfmc());
            inHospitalPatientReportSfxmResp.setFyhj(map.containsKey(dicSfxmlb.getSfxm())?new BigDecimal(map.get(dicSfxmlb.getSfxm()).stream().collect(summingDouble(this::fyhjKey))).setScale(2,BigDecimal.ROUND_HALF_UP):new BigDecimal(0));
            resps.add(inHospitalPatientReportSfxmResp);

        }

        return  resps;
    }
    //查询药占比
    private BigDecimal queryYzb(List<Map<String, Object>> brxxmap) {
        List<Map<String, Object>> yzbmap=brxxmap.stream().filter(s->!MapAttributeUtils.getString("yplx",s).equals("0")).collect(Collectors.toList());
        BigDecimal yzb= new BigDecimal(yzbmap.stream().collect(summingDouble(this::fyhjKey))).setScale(2,BigDecimal.ROUND_HALF_UP);
        return yzb;
    }
    //查询医保合计
    private BigDecimal queryYbhj(List<Map<String, Object>> brxxmap) {
        List<Map<String, Object>> ybmap=brxxmap.stream().filter(s-> MapAttributeUtils.getDouble("zfbl",s)<1).collect(Collectors.toList());
        BigDecimal zfhj= new BigDecimal(ybmap.stream().collect(summingDouble(this::zfhjKey))).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal fyhj= new BigDecimal(ybmap.stream().collect(summingDouble(this::fyhjKey))).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal ybhj=fyhj.subtract(zfhj);

        return  ybhj;
    }
    /**
     * 病人病人费用报表
     * @param bqPatientReportReq
     * @param user
     * @return
     */
    public BqPatientReportAllResp queryBqPatientReportParams(BqPatientReportReq bqPatientReportReq, SysUser user) {
        BqPatientReportAllResp resp=new BqPatientReportAllResp();
        List<BqPatientReportResp> brxxList=new ArrayList<>();
        List<Map<String,Object>> zybrMap=imFeeFymxDao.queryBqPatientReport(bqPatientReportReq);
        DicSfxmlb dicSfxmlbParam=new DicSfxmlb();
        dicSfxmlbParam.setZysy(1);
        List<DicSfxmlb> dicSfxmlbList= dicSfxmlbService.findByEntity(dicSfxmlbParam);
        Map<Integer,String> sfxmMap=dicSfxmlbList.stream().collect(Collectors.toMap(DicSfxmlb::getSfxm,DicSfxmlb::getSfmc));
        Map<String,List<Map<String,Object>>> zybrxxMap=zybrMap.stream().collect(Collectors.groupingBy(this::zyhmKey));
        Map<Integer,String> brxzMap=zyDicSer.getBrxzDic();
        for(String zyhm:zybrxxMap.keySet()){
            BqPatientReportResp brxx=new BqPatientReportResp();
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setZyhm(MapAttributeUtils.getString("zyhm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxm(MapAttributeUtils.getString("brxm",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrch(MapAttributeUtils.getString("brch",zybrxxMap.get(zyhm).get(0)));
            brxx.setRyrq(Timestamp.valueOf(MapAttributeUtils.getString("ryrq",zybrxxMap.get(zyhm).get(0))));
            brxx.setCyrq(MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0))==null?null:Timestamp.valueOf(MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0))));

            brxx.setZyys(MapAttributeUtils.getString("zyys",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxz(MapAttributeUtils.getInteger("brxz",zybrxxMap.get(zyhm).get(0)));
            brxx.setBrxzStr(brxzMap.get(brxx.getBrxz()));
            //总费用
            brxx.setHjfy(new BigDecimal(zybrxxMap.get(zyhm).stream().mapToDouble(this::fyhjKey).sum()).setScale(2,BigDecimal.ROUND_HALF_UP));
            brxx.setZfhj(new BigDecimal(zybrxxMap.get(zyhm).stream().mapToDouble(this::zfhjKey).sum()).setScale(2,BigDecimal.ROUND_HALF_UP));

            //计算收费项目集合
            brxx.setInHospitalPatientReportSfxmRespList(querySfxmList(zybrxxMap.get(zyhm),dicSfxmlbList));
            //住院天数
            String cyrq= MapAttributeUtils.getString("cyrq",zybrxxMap.get(zyhm).get(0));
            if(StringUtils.isBlank(cyrq)){
                cyrq=new Timestamp(System.currentTimeMillis()).toString();
            }
            brxx.setZyts(imFeeFymxSer.queryZyts(brxx.getRyrq().toString(),cyrq).getData());
            brxx.setYbhj(queryYbhj(zybrxxMap.get(zyhm)));
            //计算药占比
            brxx.setYphj(queryYzb(zybrxxMap.get(zyhm)));
            brxx.setYzb(brxx.getHjfy().compareTo(BigDecimal.ZERO)==0?new BigDecimal(0): brxx.getYphj().divide(brxx.getHjfy(),2, BigDecimal.ROUND_HALF_UP));

            brxxList.add(brxx);
        }
        resp.setBqPatientReportRespList(brxxList);
        //计算总的收费项目
        resp.setBqPatientReportHjRespList(queryBqFyhjRespList(brxxList));


        return  resp;
    }

    /**
     * 住院管理-日终结账（查询）新
     *
     * @param jzrq
     * @param user
     * @return
     */
    public Map<String, Object> doCreateJzrbNew(String jzrq,String ty,SysUser user) {
        Map<String,Object> respMap = new HashMap<>();
        //查询入参
        Integer userid = user.getUserId();
        Integer jgid = user.getHospitalId();
        Map<String, Object> map_par = new HashMap<String, Object>();
        map_par.put("czgh", userid);
        map_par.put("jgid", jgid);
        map_par.put("adt_jzrq", jzrq);
        try{
            BigDecimal yjjxj = new BigDecimal("0");//收预交金
            BigDecimal yjjzz = new BigDecimal("0");//收预交金转账
            BigDecimal yjjpos = new BigDecimal("0");//收预交金POS
            BigDecimal yjjwx = new BigDecimal("0");//收预微信
            BigDecimal yjjzfb = new BigDecimal("0");//收预交支付宝
            BigDecimal yjjewm = new BigDecimal("0");//收预交二维码
            BigDecimal zfyjjxj = new BigDecimal("0");//预交金作废退现金
            BigDecimal zfyjjzz = new BigDecimal("0");//预交金作废退转账
            BigDecimal zfyjjpos = new BigDecimal("0");//预交金作废退POS机
            BigDecimal zfyjjwx = new BigDecimal("0");//预交金作废退微信
            BigDecimal zfyjjzfb = new BigDecimal("0");//预交金作废退支付宝
            BigDecimal zfyjjewm = new BigDecimal("0");//预交金作废退支付宝

            BigDecimal yjje = new BigDecimal("0");//预缴金金额
            BigDecimal tk_yjje = new BigDecimal("0");//找退和注销退预缴金金额
            BigDecimal jsxjje = new BigDecimal("0");//结算时收现金金额
            BigDecimal jszzje = new BigDecimal("0"); //结算时收转账金额
            BigDecimal jsposje = new BigDecimal("0"); //结算时收POS金额
            BigDecimal jswxje = new BigDecimal("0");//结算时收微信金额
            BigDecimal jszfbje = new BigDecimal("0"); //结算时收支付宝金额
            BigDecimal jsgzje = new BigDecimal("0"); //结算挂账金额
            BigDecimal jshbwc = new BigDecimal("0"); //结算时货币误差(舍入金额)
            BigDecimal jsyyyh = new BigDecimal("0"); //结算时医院优惠金额
            BigDecimal ybsr = new BigDecimal("0");//医保收入
            if (ty.equals("1")||ty.equals("3")){
                ImRzjz imRzjz = imRzjzDao.queryJzxx(map_par);
                respMap.put("SFY",user.getUserId());
                respMap.put("XM",user.getUserName());
                respMap.put("JGMC",user.getHospitalName());
                respMap.put("JZRQ",jzrq);
                //结算信息
                respMap.put("YJJE",imRzjz.getYjje().setScale(2));
                respMap.put("TK_YJJE",imRzjz.getTkyjje().setScale(2));
                respMap.put("SS_YJJE",imRzjz.getSsyjje().setScale(2));
                respMap.put("JSXJJE",imRzjz.getJsxjje().setScale(2));
                respMap.put("JSZZJE",imRzjz.getJszzje().setScale(2));
                respMap.put("JSPOSJE",imRzjz.getJsposje().setScale(2));
                respMap.put("JSWXJE",imRzjz.getJswxje().setScale(2));
                respMap.put("JSZFBJE",imRzjz.getJszfbje().setScale(2));
                respMap.put("JSGZJE",imRzjz.getJsgzje().setScale(2));
                respMap.put("SRJE",imRzjz.getSrje().setScale(2));
                respMap.put("JZZE",imRzjz.getJzze().setScale(2));
                respMap.put("QTJZ",imRzjz.getQtjz().setScale(2));
                respMap.put("SBJZ",imRzjz.getSbjz().setScale(2));
                respMap.put("YBJZ",imRzjz.getYbjz().setScale(2));
                respMap.put("YHJE",imRzjz.getYhje().setScale(2));
                respMap.put("QFJE",imRzjz.getQfje().setScale(2));
                //预交金
                respMap.put("SYJJXJ",imRzjz.getSyjjxj().setScale(2));
                respMap.put("SYJJZZ",imRzjz.getSyjjzz().setScale(2));
                respMap.put("SYJJPOS",imRzjz.getSyjjpos().setScale(2));
                respMap.put("SYJJWX",imRzjz.getSyjjwx().setScale(2));
                respMap.put("SYJJZFB",imRzjz.getSyjjzfb().setScale(2));
                respMap.put("SYJJEWM",imRzjz.getSyjjewm().setScale(2));
                respMap.put("TYJJXJ",imRzjz.getTyjjxj().setScale(2));
                respMap.put("TYJJZZ",imRzjz.getTyjjzz().setScale(2));
                respMap.put("TYJJPOS",imRzjz.getTyjjpos().setScale(2));
                respMap.put("TYJJWX",imRzjz.getTyjjwx().setScale(2));
                respMap.put("TYJJZFB",imRzjz.getTyjjzfb().setScale(2));
                respMap.put("TYJJEWM",imRzjz.getTyjjewm().setScale(2));
                //结算预交金合计
                respMap.put("YJJHJXJ",imRzjz.getSyjjxj().subtract(imRzjz.getTyjjxj()).setScale(2));
                respMap.put("YJJHJZZ",imRzjz.getSyjjzz().subtract(imRzjz.getTyjjzz()).setScale(2));
                respMap.put("YJHJPOS",imRzjz.getSyjjpos().subtract(imRzjz.getTyjjpos()).setScale(2));
                respMap.put("YJHJWX",imRzjz.getSyjjwx().subtract(imRzjz.getTyjjwx()).setScale(2));
                respMap.put("YJHJZFB",imRzjz.getSyjjzfb().subtract(imRzjz.getTyjjzfb()).setScale(2));
                respMap.put("YJHJEWM",imRzjz.getSyjjewm().subtract(imRzjz.getTyjjewm()).setScale(2));

                respMap.put("XJHJ",imRzjz.getSyjjxj().subtract(imRzjz.getTyjjxj()).add(imRzjz.getTkyjje()).add(imRzjz.getJsxjje()).setScale(2));
                respMap.put("ZZHJ",imRzjz.getSyjjzz().subtract(imRzjz.getTyjjzz()).add(imRzjz.getJszzje()).setScale(2));
                respMap.put("POSHJ",imRzjz.getSyjjpos().subtract(imRzjz.getTyjjpos()).add(imRzjz.getJsposje()).setScale(2));
                respMap.put("WXHJ",imRzjz.getSyjjwx().subtract(imRzjz.getTyjjwx()).add(imRzjz.getJswxje()).setScale(2));
                respMap.put("ZFBHJ",imRzjz.getSyjjzfb().subtract(imRzjz.getTyjjzfb()).add(imRzjz.getJszfbje()).setScale(2));
                respMap.put("GZHJ",imRzjz.getJsgzje().setScale(2));
                respMap.put("EWMHJ",imRzjz.getSyjjewm().subtract(imRzjz.getTyjjewm()).setScale(2));
            }else if(ty.equals("2")){
//              医保收入
//              List<Map<String, Object>> ids_brxz = imFeeFymxDao.queryIdsBrxz(map_par);
//              付款方式收入
//              List<Map<String, Object>> ids_fkfs = imFeeFymxDao.queryIdsFkfs(map_par);
                // 住院结算表单
                List<Map<String, Object>> ids_jsfp = imFeeFymxDao.queryIdsJsfp(map_par);
                // 住院缴款表单
                List<Map<String, Object>> ids_jksj = imFeeFymxDao.queryIdsJksj(map_par);
                // 住院结算作废表单
                List<Map<String, Object>>  ids_jszf= imFeeFymxDao.queryIdsJszf(map_par);
                // 住院缴款注销表单
                List<Map<String, Object>> ids_jkzf = imFeeFymxDao.queryIdsJkzx(map_par);
                // 住院付款表单
                List<Map<String, Object>> ids_jsfp_fk = imFeeFymxDao.queryIdsJsfpFk(map_par);
//                // 住院付款作废表单
//                List<Map<String, Object>> ids_jszf_fk = imFeeFymxDao.queryIdsJszfFk(map_par);
//                // 住院预缴金找退
//                List<Map<String, Object>> ids_jkzt = imFeeFymxDao.queryIdsJkzt(map_par);
                if (ids_jsfp.size() + ids_jksj.size() + ids_jszf.size()
                        + ids_jkzf.size() + ids_jsfp_fk.size() == 0) {
                    return respMap;
                }
                //预缴金
                for (Map<String,Object> temp_map : ids_jksj){
                    //累加预缴金金额
                    if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.CASH.getValue() ||
                            MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.RECHARGE_CARD.getValue()){
                        yjjxj = yjjxj.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    } else if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.REMITTANCE.getValue()){
                        yjjzz = yjjzz.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    } else if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.POS.getValue()){
                        yjjpos = yjjpos.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    } else if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.WECHAT.getValue()){
                        yjjwx = yjjwx.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    } else if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.ALIPAY.getValue()){
                        yjjzfb = yjjzfb.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    } else if (MapAttributeUtils.getInteger("JKFS",temp_map) == FkfsEnum.QRCODE.getValue()){
                        yjjewm = yjjewm.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                    }
//                    yjje = yjje.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                }
                //预缴金作废
                for (Map<String,Object> temp_map : ids_jkzf){
                    //累加预缴金金额
                    zfyjjxj = zfyjjxj.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
//                    yjje = yjje.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
                }
                //退预缴金
//                for (Map<String,Object> temp_map : ids_jkzt){
//                    //累加退预缴金金额
//                    tk_yjje = tk_yjje.add(temp_map.get("JKJE") != null ? MapAttributeUtils.getBigDecimal("JKJE",temp_map) : new BigDecimal("0"));
//                }

                //结算金额
                for (Map<String,Object> temp_map : ids_jsfp){
                    BigDecimal zyjs_fyhj = temp_map.get("FYHJ") != null ? MapAttributeUtils.getBigDecimal("FYHJ",temp_map) : new BigDecimal("0");
                    BigDecimal zyjs_zfhj = temp_map.get("ZFHJ") != null ? MapAttributeUtils.getBigDecimal("ZFHJ",temp_map) : new BigDecimal("0");
                    BigDecimal zyjs_jkhj = temp_map.get("JKHJ") != null ? MapAttributeUtils.getBigDecimal("JKHJ",temp_map) : new BigDecimal("0");
//                    if (temp_map.get("JSLX").toString().equals("1") &&
//                            zyjs_jkhj.compareTo(zyjs_zfhj) == 1 ){//中途结算不找退预交金
//                        yjje = yjje.add(zyjs_zfhj);
//                    }else{
//                        yjje = yjje.add(zyjs_jkhj);//结算转预交金
//                    }
                    yjje = yjje.add(zyjs_jkhj);//结算转预交金

//                    if (zyjs_jkhj.compareTo(zyjs_zfhj) == 1 &&
//                            !temp_map.get("JSLX").toString().equals("1")){//如果不是中途结算,找退预交金金额
                    if (zyjs_jkhj.compareTo(zyjs_zfhj) == 1){//如果不是中途结算,找退预交金金额
                        tk_yjje = tk_yjje.add(zyjs_jkhj.subtract(zyjs_zfhj));
                    }
                    ybsr = ybsr.add(zyjs_fyhj.subtract(zyjs_zfhj));//记账总额(医保收入)
                    for (Map<String,Object> fkfs_map : ids_jsfp_fk){
                        if (fkfs_map.get("ZYH").equals(temp_map.get("ZYH")) && fkfs_map.get("JSCS").equals(temp_map.get("JSCS"))){
                            if (MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.CASH.getValue() ||
                                    MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.RECHARGE_CARD.getValue()){//付款方式为现金
                                jsxjje = jsxjje.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.REMITTANCE.getValue()){//付款方式为汇款
                                jszzje = jszzje.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.POS.getValue()){//POS统计待确定!!!!
                                jsposje = jsposje.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.CURRENCY_ERROR.getValue()){//货币误差
                                jshbwc = jshbwc.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.DISCOUNT.getValue()){
                                jsyyyh = jsyyyh.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.WECHAT.getValue()){
                                jswxje = jswxje.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }else if(MapAttributeUtils.getInteger("FKFS",fkfs_map) == FkfsEnum.ALIPAY.getValue()){
                                jszfbje = jszfbje.add(fkfs_map.get("FKJE") != null ? MapAttributeUtils.getBigDecimal("FKJE",fkfs_map) : new BigDecimal("0"));
                            }
                        }
                    }
                }

                //结算作废
                for (Map<String,Object> temp_map : ids_jszf){
                    BigDecimal zyjs_fyhj_zf = temp_map.get("FYHJ") != null ? MapAttributeUtils.getBigDecimal("FYHJ",temp_map) : new BigDecimal("0");
                    BigDecimal zyjs_zfhj_zf = temp_map.get("ZFHJ") != null ? MapAttributeUtils.getBigDecimal("ZFHJ",temp_map) : new BigDecimal("0");
                    BigDecimal zyjs_jkhj_zf = temp_map.get("JKHJ") != null ? MapAttributeUtils.getBigDecimal("JKHJ",temp_map) : new BigDecimal("0");
                    ybsr = ybsr.add(zyjs_fyhj_zf.subtract(zyjs_zfhj_zf));
                    //累加退预缴金金额
//                    if (Double.parseDouble(temp_map.get("ZFHJ").toString()) <
//                            Double.parseDouble(temp_map.get("JKHJ").toString())){
//                        jsxjje = jsxjje.add(zyjs_jkhj_zf);
//                        yjje = yjje.add(zyjs_jkhj_zf);
//                    }else if(Double.parseDouble(temp_map.get("ZFHJ").toString()) >
//                            Double.parseDouble(temp_map.get("JKHJ").toString())){
//                        yjje = yjje.add(zyjs_zfhj_zf);
//                    }
                    if (zyjs_jkhj_zf.compareTo(zyjs_zfhj_zf) == 1){
                        jsxjje = jsxjje.add(zyjs_zfhj_zf).subtract(zyjs_jkhj_zf);
                        yjje = yjje.add(zyjs_jkhj_zf);
                    }else if (zyjs_zfhj_zf.compareTo(zyjs_jkhj_zf) > -1){
                        yjje = yjje.add(zyjs_zfhj_zf);
                    }
                }
                //表单抬头
                respMap.put("SFY",user.getUserId());
                respMap.put("XM",user.getUserName());
                respMap.put("JGMC",user.getHospitalName());
                respMap.put("JZRQ",jzrq);

                //结算信息
                respMap.put("YJJE",yjje.setScale(2));
                respMap.put("TK_YJJE",tk_yjje.setScale(2).multiply(new BigDecimal("-1")));
                respMap.put("SS_YJJE",yjje.subtract(tk_yjje).setScale(2));
                respMap.put("JSXJJE",jsxjje.setScale(2));
                respMap.put("JSZZJE",jszzje.setScale(2));
                respMap.put("JSPOSJE",jsposje.setScale(2));
                respMap.put("JSWXJE",jswxje.setScale(2));
                respMap.put("JSZFBJE",jszfbje.setScale(2));
                respMap.put("JSGZJE",jsgzje.setScale(2));
                respMap.put("SRJE",jshbwc.setScale(2));
                respMap.put("JZZE",ybsr.setScale(2));
                respMap.put("QTJZ",ybsr.setScale(2));
                respMap.put("SBJZ",new BigDecimal("0").setScale(2));
                respMap.put("YBJZ",new BigDecimal("0").setScale(2));
                respMap.put("YHJE",jsyyyh.setScale(2));
                respMap.put("QFJE",new BigDecimal("0").setScale(2));
                //预交金部分
                respMap.put("SYJJXJ",yjjxj.setScale(2));
                respMap.put("SYJJZZ",yjjzz.setScale(2));
                respMap.put("SYJJPOS",yjjpos.setScale(2));
                respMap.put("SYJJWX",yjjwx.setScale(2));
                respMap.put("SYJJZFB",yjjzfb.setScale(2));
                respMap.put("SYJJEWM",yjjewm.setScale(2));
                respMap.put("TYJJXJ",zfyjjxj.setScale(2));
                respMap.put("TYJJZZ",zfyjjzz.setScale(2));
                respMap.put("TYJJPOS",zfyjjpos.setScale(2));
                respMap.put("TYJJWX",zfyjjwx.setScale(2));
                respMap.put("TYJJZFB",zfyjjzfb.setScale(2));
                respMap.put("TYJJEWM",zfyjjewm.setScale(2));
                respMap.put("YJJHJXJ",yjjxj.subtract(zfyjjxj).setScale(2));
                respMap.put("YJJHJZZ",yjjzz.subtract(zfyjjzz).setScale(2));
                respMap.put("YJHJPOS",yjjpos.subtract(zfyjjpos).setScale(2));
                respMap.put("YJHJWX",yjjwx.subtract(zfyjjwx).setScale(2));
                respMap.put("YJHJZFB",yjjzfb.subtract(zfyjjzfb).setScale(2));
                respMap.put("YJHJEWM",yjjewm.subtract(zfyjjewm).setScale(2));

                //实缴部分
                respMap.put("XJHJ",yjjxj.subtract(zfyjjxj).add(jsxjje).subtract(tk_yjje).setScale(2));
                respMap.put("ZZHJ",yjjzz.subtract(zfyjjzz).add(jszzje).setScale(2));
                respMap.put("POSHJ",yjjpos.subtract(zfyjjpos).add(jsposje).setScale(2));
                respMap.put("WXHJ",yjjwx.subtract(zfyjjwx).add(jswxje).setScale(2));
                respMap.put("ZFBHJ",yjjzfb.subtract(zfyjjzfb).add(jszfbje).setScale(2));
                respMap.put("GZHJ",jsgzje.setScale(2));
                respMap.put("EWMHJ",yjjewm.subtract(zfyjjewm).setScale(2));
                //产生的map放入redis
                redisTemplate.opsForValue().set(RedisKeyHead.zyCheckout + user.getHospitalId().toString() + "-map-" + user.getUserId(), objectMapper.writeValueAsString(respMap), 24, TimeUnit.HOURS);
            }

        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
        }

        return respMap;
    }

    public Map<String, Object> getHospitalChargesDailyPrintParameters(PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq, SysUser user){
        String jzrq = patientDepartmentChargesDailyPrintReq.getJzrq();
        String ty = patientDepartmentChargesDailyPrintReq.getTy();
        Map<String,Object> respMap = doCreateJzrbNew(jzrq,ty,user);
        return respMap;
    }

    public List<Map<String, Object>> getHospitalChargesDailyPrintFields(PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq, SysUser user){
        List<Map<String, Object>> resp = new ArrayList<>();
        String jzrq  = patientDepartmentChargesDailyPrintReq.getJzrq();
        String ty = patientDepartmentChargesDailyPrintReq.getTy();
        Map<String,Object> map_par = new HashMap<>();
        map_par.put("adt_jzrq",jzrq);
        map_par.put("czgh",user.getUserId());
        map_par.put("jgid",user.getHospitalId());
        BigDecimal xmsrhj = new BigDecimal("0");//分类项目收入合计
        BigDecimal xmyhje = new BigDecimal("0");//分类项目优惠合计
        BigDecimal smsjsr = new BigDecimal("0");//分类项目实际收入合计

        try {
            //住院在用收费项目
//            List<InHospitalPatientSfxmResp> ids_zyxm =  imFeeFymxDao.queryIdsZyxm();

            if (ty.equals("1")||ty.equals("3")){
                List<ImRzjzMx> ids_jzmx = imRzjzMxDao.queryJzxx(map_par);
                for(ImRzjzMx temp : ids_jzmx){
                    Map<String,Object> new_map = new HashMap<>();
                    new_map.put("XMMC",temp.getXmmc());
                    new_map.put("SRHJ",temp.getSrhj().setScale(2));
                    new_map.put("YHJE",temp.getYhje().setScale(2));
                    new_map.put("SJSR",temp.getSjsr().setScale(2));
                    xmsrhj = xmsrhj.add(temp.getSrhj());
                    xmyhje = xmyhje.add(temp.getYhje());
                    smsjsr = smsjsr.add(temp.getSjsr());
                    resp.add(new_map);
                }

                //添加最后一行合计金额
                Map<String,Object> hj_map = new HashMap<>();
                hj_map.put("XMMC","合 计:");
                hj_map.put("SRHJ",xmsrhj.setScale(2));
                hj_map.put("YHJE",xmyhje.setScale(2));
                hj_map.put("SJSR",smsjsr.setScale(2));
                resp.add(hj_map);
            }else if(ty.equals("2")){
                //项目收费表单
                List<Map<String, Object>> ids_jsxmgb = imFeeFymxDao.queryIdsJsxmgb(map_par);
                for(Map<String,Object> temp : ids_jsxmgb){
                    Map<String,Object> new_map = temp;
                    new_map.put("XMMC",temp.get("XMMC"));
                    new_map.put("SRHJ",MapAttributeUtils.getBigDecimal("SRHJ",temp).setScale(2));
                    new_map.put("YHJE",MapAttributeUtils.getBigDecimal("YHJE",temp).setScale(2));
                    new_map.put("SJSR",MapAttributeUtils.getBigDecimal("SJSR",temp).setScale(2));
                    xmsrhj = xmsrhj.add(MapAttributeUtils.getBigDecimal("SRHJ",temp));
                    xmyhje = xmyhje.add(MapAttributeUtils.getBigDecimal("YHJE",temp));
                    smsjsr = smsjsr.add(MapAttributeUtils.getBigDecimal("SJSR",temp));
                    resp.add(new_map);
                }

                //添加最后一行合计金额
                Map<String,Object> hj_map = new HashMap<>();
                hj_map.put("XMMC","合 计:");
                hj_map.put("SRHJ",xmsrhj.setScale(2));
                hj_map.put("YHJE",xmyhje.setScale(2));
                hj_map.put("SJSR",smsjsr.setScale(2));
                resp.add(hj_map);
                redisTemplate.opsForValue().set(RedisKeyHead.zyCheckout + user.getHospitalId().toString() + "-list-" + user.getUserId(), objectMapper.writeValueAsString(ids_jsxmgb), 24, TimeUnit.HOURS);
            }

        } catch (JsonProcessingException e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00047");
        }


        return resp;
    }

    public List<Map<String,Object>> getJsmxPrintFields(Integer ty,String jzrq,SysUser user){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("czgh",user.getUserId());
        params.put("jgid",user.getHospitalId());
        params.put("adt_jzrq",jzrq);

        List<Map<String,Object>> ids_jsmx = new ArrayList<>();
        List<Map<String,Object>> ids_fkfs = new ArrayList<>();
        if (ty == 1|| ty == 3){//查询
            ids_jsmx = imFeeFymxDao.getRzjzJsmx(params);
            ids_fkfs = imFeeFymxDao.getIdsJsfpFk(params);
        }else if(ty == 2){//产生
            ids_jsmx = imFeeFymxDao.queryRzjzJsmx(params);
            ids_fkfs = imFeeFymxDao.queryIdsJsfpFk(params);
        }
        for (Map<String,Object> temp : ids_jsmx){
            BigDecimal ssxj = new BigDecimal("0");
            BigDecimal sspos = new BigDecimal("0");
            for (Map<String,Object> temp_fkfs : ids_fkfs){
                if (temp.get("ZYH").equals(temp_fkfs.get("ZYH"))
                        && temp.get("JSCS").equals(temp_fkfs.get("JSCS"))){
                    if (MapAttributeUtils.getInteger("FKFS",temp_fkfs) == FkfsEnum.CASH.getValue() ||
                            MapAttributeUtils.getInteger("FKFS",temp_fkfs) == FkfsEnum.RECHARGE_CARD.getValue()){
                        ssxj = ssxj.add(MapAttributeUtils.getBigDecimal("FKJE",temp_fkfs));
                    }else if(MapAttributeUtils.getInteger("FKFS",temp_fkfs) == FkfsEnum.POS.getValue() ||
                                MapAttributeUtils.getInteger("FKFS",temp_fkfs) == FkfsEnum.WECHAT.getValue() ||
                                MapAttributeUtils.getInteger("FKFS",temp_fkfs) == FkfsEnum.ALIPAY.getValue()) {
                        sspos = sspos.add(MapAttributeUtils.getBigDecimal("FKJE",temp_fkfs));
                    }
                }
            }
            //判断预交金大于自负金额,有退预交金
            if (ssxj.compareTo(BigDecimal.ZERO) == 0 && sspos.compareTo(BigDecimal.ZERO) == 0 &&
                    MapAttributeUtils.getDouble("ZFHJ",temp) < MapAttributeUtils.getDouble("JKHJ",temp)){
                ssxj = ssxj.add(MapAttributeUtils.getBigDecimal("ZFHJ",temp).subtract(MapAttributeUtils.getBigDecimal("JKHJ",temp)));
            }
            Map<String,Object> new_map = new HashMap<>();
            new_map.put("ZYH",temp.get("ZYHM"));
            new_map.put("FPH",temp.get("FPHM"));
            new_map.put("XM",temp.get("BRXM"));
            new_map.put("FB",temp.get("JSLX").toString());
            new_map.put("ZJE",temp.get("FYHJ").toString());
            new_map.put("GRZF",temp.get("ZFHJ").toString());
            new_map.put("YJJ",temp.get("JKHJ").toString());
            new_map.put("SSXJ",ssxj.setScale(2).toString());
            new_map.put("SSPOS",sspos.setScale(2).toString());
            new_map.put("QFJE","");
            new_map.put("BXJZ",new BigDecimal("0").setScale(2).toString());
            new_map.put("QF",new BigDecimal("0").setScale(2).toString());
            list.add(new_map);
        }
        return list;
    }

    public List<Map<String,Object>> getTbkkPrintFields(Integer ty,String jzrq,SysUser user){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("czgh",user.getUserId());
        params.put("jgid",user.getHospitalId());
        params.put("adt_jzrq",jzrq);

        List<Map<String,Object>> ids_tbkk = new ArrayList<>();
        BigDecimal srhj = new BigDecimal("0");
        BigDecimal zchj = new BigDecimal("0");
        if (ty == 1 || ty == 3){//查询和打印
            ids_tbkk = imFeeFymxDao.getIdsTbkk(params);
        }else if(ty == 2){//产生
            ids_tbkk = imFeeFymxDao.queryIdsTbkk(params);
        }
        for (Map<String,Object> temp : ids_tbkk){
            Map<String,Object> new_map = new HashMap<>();
            new_map.put("ZYH",temp.get("ZYHM"));
            new_map.put("SJH",temp.get("SJHM"));
            new_map.put("XM",temp.get("BRXM"));
            new_map.put("SR",temp.get("JKJE").toString());
            new_map.put("ZC","0.00");
            new_map.put("SKFS",temp.get("JKFS"));
            new_map.put("BZ",temp.get("BZ"));
            list.add(new_map);
        }
        return list;
    }

    public Map<String, Object> getHospitalChargesDailyPrintAllParameters(PatientDepartmentChargesDailyPrintReq patientDepartmentChargesDailyPrintReq, SysUser user){
        String jzrq = patientDepartmentChargesDailyPrintReq.getJzrq();
        Integer ty = Integer.valueOf(patientDepartmentChargesDailyPrintReq.getTy());
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("SUBREPORT_DIR", this.getClass().getClassLoader().getResource("jrxml/"));
        respMap.put("JSMX",iReportExportFileSer.conVerDataSource(getJsmxPrintFields(ty,jzrq,user)));
        respMap.put("JKMX",iReportExportFileSer.conVerDataSource(getTbkkPrintFields(ty,jzrq,user)));
        return respMap;
    }

    public Map<String, Object> getHospitalChargesCollectPrintParameters(HospitalChargesCollectReq hospitalChargesCollectReq, SysUser user){
        String hzjsrq = hospitalChargesCollectReq.getHzjsrq();
        //获取开始时间
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("hzrq",hzjsrq);
        parameters.put("jgid",user.getHospitalId());
        String hzksrq = imRzjzDao.queryKshzrq(parameters);
        //定义返回数据
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("jgmc", user.getHospitalName());
        if (hzksrq != null){
            respMap.put("hzksrq",hzksrq);
        }else{
            respMap.put("hzksrq","2021-01-01 00:00:00");
        }
        respMap.put("hzjsrq",hzjsrq);
        respMap.put("czgh",user.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now_str = sdf.format(new Date());
        respMap.put("zbrq",now_str);
        return respMap;
    }

    public List<Map<String, Object>> getHospitalChargesCollectPrintFields(HospitalChargesCollectReq hospitalChargesCollectReq, SysUser user){
        String hzjsrq = hospitalChargesCollectReq.getHzjsrq();
        Integer ty = hospitalChargesCollectReq.getTy();
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("hzrq",hzjsrq);
        parameters.put("jgid",user.getHospitalId());
        List<Map<String,Object>> hzxx_list = new ArrayList<>();
        if (ty == 2){
            hzxx_list = imRzjzDao.queryHzxx(parameters);
        }else if(ty == 1 || ty == 3){
            hzxx_list = imRzjzDao.getHzxx(parameters);
        }
        //添加合计
        Map<String,Object> new_map = new HashMap<>();
        new_map.put("YGXM","");
        new_map.put("SFMC","  合计:");
        BigDecimal zj_srhj = BigDecimal.ZERO;
        BigDecimal zj_zkje = BigDecimal.ZERO;
        BigDecimal zj_sjsr = BigDecimal.ZERO;

        for (Map<String,Object> temp : hzxx_list){
            zj_srhj = zj_srhj.add(temp.get("ZJJE") != null? MapAttributeUtils.getBigDecimal("ZJJE",temp) : BigDecimal.ZERO);
            zj_zkje = zj_zkje.add(temp.get("ZKJE") != null? MapAttributeUtils.getBigDecimal("ZKJE",temp) : BigDecimal.ZERO);
            zj_sjsr = zj_sjsr.add(temp.get("SJJE") != null? MapAttributeUtils.getBigDecimal("SJJE",temp) : BigDecimal.ZERO);
        }
        new_map.put("ZJJE",zj_srhj.setScale(2));
        new_map.put("ZKJE",zj_zkje.setScale(2));
        new_map.put("SJJE",zj_sjsr.setScale(2));
        hzxx_list.add(new_map);
        return hzxx_list;
    }

    public ReturnEntity doCheckCollect(String hzjsrq,SysUser user){
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("hzjsrq", hzjsrq);
        parameters.put("jgid", user.getHospitalId());

        Map<String,Object> row_number = imRzjzDao.checkCollect(parameters);
        if (Integer.valueOf(row_number.get("row_count").toString()) == 0){
            throw BaseException.create("ERROR_IM_RZJZ_0005");
        }
        return ReturnEntityUtil.success();
    }

    public String getMaxHzrq(SysUser user){
        String maxHzrq = imRzjzDao.queryMaxHzrq(user.getHospitalId().toString());
        if (maxHzrq == null){//如果第一次汇总 开始日期取最早的结账日期
            maxHzrq = imRzjzDao.queryMinHzrq(user.getHospitalId().toString());
            if(maxHzrq == null ){
                throw BaseException.create("ERROR_IM_RZJZ_0005");
            }
        }
        return Time(maxHzrq);
    }

    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity doSaveHz(String hzjsrq,SysUser user){
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("hzrq",hzjsrq);
        parameters.put("jgid",user.getHospitalId());
        try{
            imRzjzDao.saveHz(parameters);
        } catch (Exception e) {
            throw BaseException.create("ERROR_IM_RZJZ_0003");
        }
        return ReturnEntityUtil.success();
    }

    public List<Map<String,Object>> getHzrq(SysUser user){
        List<Map<String,Object>> hzrq_list = new ArrayList<>();
        Map<String,Object> parameters = new HashMap<>();
//        parameters.put("hzksrq",hzksrq);
//        parameters.put("hzjsrq",hzjsrq);
        parameters.put("jgid",user.getHospitalId());
        try{
            hzrq_list = imRzjzDao.getHzrq(parameters);
        } catch (Exception e) {
            throw BaseException.create("ERROR_IM_RZJZ_0003");
        }
        return hzrq_list;
    }

    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity doCancelHz(String hzjsrq,SysUser user){
        Map<String,Object> parameters = new HashMap<>();

         String maxHzrq = imRzjzDao.queryMaxHzrq(user.getHospitalId().toString());
         if (!Time(maxHzrq).equals(hzjsrq)){
             throw BaseException.create("ERROR_IM_RZJZ_0006");
         }
         parameters.put("hzrq",hzjsrq);
         parameters.put("jgid",user.getHospitalId());
         try{
            imRzjzDao.doCancelHz(parameters);
         } catch (Exception e) {
            throw BaseException.create("ERROR_IM_RZJZ_0004");
         }
         return ReturnEntityUtil.success();
    }

    /**
     * 时间字符串去.0
     */
    public String Time(String commenttime) {
        String time1=commenttime.replace(".0","");
        return time1;
    }
}
