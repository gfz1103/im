package com.buit.cis.ims.dao;


import com.buit.cis.im.request.ImFeeFymxReq;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.cis.nurse.request.NisRefundableProjectReq;
import com.buit.commons.EntityDao;
import com.buit.drug.request.QueryGrantedDrugReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 费用明细表<br>
 *
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImFeeFymxDao extends EntityDao<ImFeeFymx, Integer> {
    /**
     * 病人管理-帐卡-费用帐卡(Vip帐卡费用列表)
     *
     * @param cardPatientInfoCostReq
     * @return
     */
    List<CardPatientInfoCostResp> queryVipCardPatientInfoCost(CardPatientInfoCostReq cardPatientInfoCostReq);

    /**
     * 病人管理-帐卡-费用帐卡(帐卡费用列表)
     *
     * @param cardPatientInfoCostReq
     * @return
     */
    List<CardPatientInfoCostResp> queryCardPatientInfoCost(CardPatientInfoCostReq cardPatientInfoCostReq);

    /**
     * 本次住院所有费用明细清单
     *
     * @param cardPatientCostReq
     * @return
     */
    List<ImFeeFymx> findAll(CardPatientCostReq cardPatientCostReq);

    /**
     * 某段时间范围的费用明细清单
     *
     * @param cardPatientCostReq
     * @return
     */
    List<ImFeeFymx> findList(CardPatientCostReq cardPatientCostReq);

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-明细格式)分页查询
     *
     * @param cardPatientCostPageReqReq
     * @return
     */
    List<ImFeeFymx> queryCardPatientCostDetail(CardPatientCostPageReq cardPatientCostPageReqReq);

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-医嘱格式)分页查询
     *
     * @param cardPatientCostPageReqReq
     * @return
     */
    List<CardPatientCostAdviceResp> queryCardPatientCostAdvice(CardPatientCostPageReq cardPatientCostPageReqReq);

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-汇总新格式)分页查询
     *
     * @param
     * @param
     * @return
     */
    List<CardPatientCostCollectNewResp> queryCardPatientCostCollectNew(CardPatientCostPageReq cardPatientCostPageReqReq);

    /**
     * 根据医嘱序号查询住院发要明细
     *
     * @param @param  jlxh
     * @param @return 设定文件
     * @return List<ImFeeFymx>    返回类型
     * @throws
     * @Title: queryZyfymxByYzxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymx> queryZyfymxByYzxh(String jlxh);

    BigDecimal queryZyfymxSumByYzxh(String jlxh);
    /**
     * 查询住院病人尚未结算的费用合计和自负合计
     *
     * @param zyh
     * @param hospitalId
     * @return
     */
    CardPatientCostBaseInfoJe queryPatientCost(@Param("zyh") Integer zyh, @Param("hospitalId") Integer hospitalId);


    /**
     * 出院结算病人是否使用高价药
     *
     * @param zyh
     * @return
     */
//    Integer findCyjsGjyByZyh(Integer zyh);

    /**
     * 中途结算病人是否使用高价药
     *
     * @param zyh
     * @return
     */
//    Integer findZtjsGjyByZyh(Integer zyh);

    /**
     * 根据住院号查询发药明细自负金额
     *
     * @param @param  zyh
     * @param @param  jscs
     * @param @return 设定文件
     * @return BigDecimal    返回类型
     * @throws
     * @Title: queryTotalSelf
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    BigDecimal queryTotalSelf(@Param("zyh") Integer zyh, @Param("jscs") Integer jscs);

    /**
     * 根据住院号查询发药明细费用自负金额
     *
     * @param @param  zyh
     * @param @param  jscs
     * @param @return 设定文件
     * @return BigDecimal    返回类型
     * @throws
     * @Title: queryTotalSelfCost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    BigDecimal queryTotalSelfCost(@Param("zyh") Integer zyh, @Param("jscs") Integer jscs);

    /**
     * 根据住院号查询发药明细不是费用自负金额
     *
     * @param @param  zyh
     * @param @param  jscs
     * @param @return 设定文件
     * @return BigDecimal    返回类型
     * @throws
     * @Title: queryTotalSelfNotCost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    BigDecimal queryTotalSelfNotCost(@Param("zyh") Integer zyh, @Param("jscs") Integer jscs);

    /**
     * 查询血透减负list
     *
     * @param @param  imFeeFymx
     * @param @return 设定文件
     * @return List<ImFeeFymxXtResp>    返回类型
     * @throws
     * @Title: queryHemodialysis
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymxXtResp> queryHemodialysis(ImFeeFymx imFeeFymx);

    /**
     * 查询费用明细自负比例
     *
     * @param @param  zyh
     * @param @param  jfjlxh
     * @param @return 设定文件
     * @return List<ImFeeFymx>    返回类型
     * @throws
     * @Title: querySelfSufficiency
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymx> querySelfSufficiency(@Param("zyh") Integer zyh, @Param("jfjlxh") List<Integer> jfjlxh);


    /**
     * 更新是大病计费标志和自负比列
     *
     * @param @param zyh
     * @param @param jfjlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateIsSeriousIllness
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateIsSeriousIllness(@Param("zyh") Integer zyh, @Param("jfjlxh") List<Integer> jfjlxh);


    /**
     * 更新不是大病计费标志和自费金额
     *
     * @param @param zyh
     * @param @param jfjlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateNotIsSeriousIllness
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateNotIsSeriousIllness(@Param("zyh") Integer zyh, @Param("jfjlxh") List<Integer> jfjlxh);

    /**
     * 清除IM_FEE_FYMX中结算次数
     */
    void updatZyjszf(ImZyjs imZyjs);

    /**
     * 结算管理-明细项目(按日期)
     *
     * @param zyh
     * @param zygb
     * @param jgid
     * @return
     */
    List<PatientBalanceAccountsDetailDateResp> queryPatientBalanceAccountsDetailDate(@Param("zyh") Integer zyh, @Param("zygb") Integer zygb, @Param("jgid") Integer jgid, @Param("ksrq") String ksrq, @Param("zzrq") String zzrq);

    /**
     * 结算管理-明细项目(按明细)
     *
     * @param zyh
     * @param zygb
     * @param jgid
     * @return
     */
    List<PatientBalanceAccountsDetailMxResp> queryPatientBalanceAccountsDetailMx(@Param("zyh") Integer zyh, @Param("zygb") Integer zygb, @Param("jgid") Integer jgid, @Param("ksrq") String ksrq, @Param("zzrq") String zzrq);

    /**
     * 查询发药明细总计金额和自负总计金额
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: querySumAndConceit
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
//    Map<String, Object> querySumAndConceit(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 更新结算次数
     */
    void updateJscs(ImZyjs imZyjs);

    /**
     * 查询费用合计
     *
     * @param imFeeFymxParameters
     * @return
     */
//    Map<String, Object> queryRealTimeFyhj(Map<String, Object> imFeeFymxParameters);

    /**
     * 更新实际结算次数
     *
     * @param imFeeFymxParameters
     */
//    void updateRealTimeJscs(Map<String, Object> imFeeFymxParameters);

    /**
     * 查询费用合计
     *
     * @return
     */
//    Map<String, Object> queryFyhj(@Param(value = "zyh") Integer zyh, @Param(value = "jgid") Integer jgid);

    /**
     * 更新高价药的费用结算明细
     *
     * @param imFeeFymxGjy
     */
//    void updateJsGjy(Map<String, Object> imFeeFymxGjy);

    /**
     * 查询住院病人费用明细列表哦
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryZyfymx(Map<String, Object> parameters);

    /**
     * 查询退药数量
     *
     * @param @param  nisTymxRetrogressiveReq
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryTysl
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryTysl(QueryGrantedDrugReq queryGrantedDrugReq);

    /**
     * 根据材料项目分组合并和住院号查询费用明细
     *
     * @param @param  zyh
     * @param @param  cldym
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryFymxByCldymAndZyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryFymxByCldymAndZyh(@Param("zyh") Integer zyh, @Param("cldym") String cldym);

    /**
     * 费用记账作废更新
     *
     * @param @param imFeeFymx    设定文件
     * @return void    返回类型
     * @throws
     * @Title: invalidExpenseBookkeeping
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void invalidExpenseBookkeeping(ImFeeFymx imFeeFymx);

    /**
     * 批量插入
     *
     * @param insertZyfymx
     * @return
     */
    void batchInsert(@Param("list") List<ImFeeFymx> insertZyfymx);

    /**
     * 病人管理-帐卡-明细项目(按明细)-修改折扣比例
     *
     * @param updateZkblReq
     */
    void updateZkbl(UpdateZkblReq updateZkblReq);

    /**
     * 查询fysl之和
     *
     * @param zyh
     * @param ypxh
     * @param ypcd
     * @param jgid
     */
    BigDecimal sumFyslByZyhAndFyxhAndYpcdAndJgid(@Param("zyh") Integer zyh, @Param("ypxh") Integer ypxh, @Param("ypcd") Integer ypcd, @Param("jgid") Integer jgid);

    /**
     * 病人管理-帐卡-费用帐卡(费用清单-汇总格式)分页查询
     *
     * @param cardPatientCostPageReqReq
     * @return
     */
    List<CardPatientCostCollectResp> queryCardPatientCostCollect(CardPatientCostPageReq cardPatientCostPageReqReq);

    /**
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsBrxz(Map<String, Object> map_par);

    /**
     * 付款方式统计
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsFkfs(Map<String, Object> map_par);

    /**
     * 结算发票统计
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJsfp(Map<String, Object> map_par);

    /**
     * 住院结算表单
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJksj(Map<String, Object> map_par);

    /**
     * 住院结算作废表单
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJszf(Map<String, Object> map_par);

    /**
     * // 住院缴款作废表单
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJkzf(Map<String, Object> map_par);

    /**
     * 住院缴款作废表单（新统计方式）收据作废
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJkzx(Map<String, Object> map_par);

    /**
     * 住院预缴金找退
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJkzt(Map<String, Object> map_par);

    /**
     * 住院付款表单
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJsfpFk(Map<String, Object> map_par);

    /**
     * 住院付款作废表单
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJszfFk(Map<String, Object> map_par);

    /**
     * 费用明细结算归并
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJsxmgb(Map<String, Object> map_par);

    /**
     * 住院收费项目
     *
     * @return
     */
    List<InHospitalPatientSfxmResp> queryIdsZyxm();

    /**
     * 取退预缴款收据张数
     *
     * @param map_par
     * @return
     */
    Map<String, Object> queryTjks(Map<String, Object> map_par);

    /**
     * 算结算票据和缴款收据 票据序列
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJsfpWithOrderBy(Map<String, Object> map_par);

    /**
     * 住院结算表单 排序
     *
     * @param map_par
     * @return
     */
    List<Map<String, Object>> queryIdsJksjWithOrderBy(Map<String, Object> map_par);

    /**
     * 住院管理-日终汇总（取消汇总）
     *
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 查询汇总数量
     *
     * @param parametersll_count
     * @return
     */
    Integer queryHzCount(Map<String, Object> parametersll_count);

    /**
     * 修改汇总日期
     *
     * @param parametersupd
     */
    void updateHzrq(Map<String, Object> parametersupd);

    /**
     * 查询汇总记录
     *
     * @param parametersll_count
     * @return
     */
    Integer queryHz(Map<String, Object> parametersll_count);

    /**
     * 查询费用明细IM_FEE_FYMX汇总到IM_SRHZ所需数据
     *
     * @param parametersll_count
     * @return
     */
    List<Map<String, Object>> queryImSrhzList1(Map<String, Object> parametersll_count);

    /**
     * 查询费用明细IM_FEE_FYMX汇总到IM_SRHZ所需数据
     *
     * @param parametersll_count
     * @return
     */
    List<Map<String, Object>> queryImSrhzList2(Map<String, Object> parametersll_count);

    /**
     * 统计汇总金额
     *
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzZjje(Map<String, Object> parametersje);

    /**
     * 查询汇总本期发生
     *
     * @param parametersfyje
     * @return
     */
    List<Map<String, Object>> queryHzBqfs(Map<String, Object> parametersfyje);

    /**
     * 查询汇总的实际结存
     *
     * @param parametersfyje
     * @return
     */
    List<Map<String, Object>> queryHzSjjc(Map<String, Object> parametersfyje);

    /**
     * 日终汇总(四) 查询总费用
     *
     * @param parameter
     * @return
     */
    List<Map<String, Object>> queryHzFour(Map<String, Object> parameter);

    /**
     * 日终汇总(四) 查询总费用列表
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzFourList(Map<String, Object> parameters);

    /**
     * 日终汇总(四) 查询总费用列表 追加西药基药/中药基药
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzFourZxyList(Map<String, Object> parameters);

    /**
     * 日终汇总(五) 查询费用列表
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzFiveList(Map<String, Object> parameters);

    /**
     * 日终汇总(五) 查询费用汇总
     *
     * @param hzparameters
     * @return
     */
    List<Map<String, Object>> queryHzFive(Map<String, Object> hzparameters);


    /**
     * 查询可退项目医嘱
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<ImFeeFymxProjectResp>    返回类型
     * @throws
     * @Title: queryRefundableProject
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymxProjectResp> queryRefundableProject(NisRefundableProjectReq req);

    /**
     * 查询执行可退项目
     *
     * @param @param  imFeeFymxQueryReq
     * @param @return 设定文件
     * @return List<ImFeeFymx>    返回类型
     * @throws
     * @Title: queryExecuteRefundableProject
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymx> queryExecuteRefundableProject(ImFeeFymxQueryReq imFeeFymxQueryReq);


    /**
     * 查询已作废列表
     * @Title: queryVoidedList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<ImFeeFymx>    返回类型
     * @author 龚方舟
     * @throws
     */
//    List<ImFeeFymx> queryVoidedList(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 费用记账查询
     *
     * @param @param  imFeeFymxAccountingReq
     * @param @return 设定文件
     * @return List<ImFeeFymxAccountingResp>    返回类型
     * @throws
     * @Title: inquiryBookkeeping
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymxAccountingResp> inquiryBookkeeping(ImFeeFymxAccountingReq imFeeFymxAccountingReq);

    /**
     * 收费项目的发票
     *
     * @param parameters2
     * @return
     */
    List<Map<String, Object>> queryqueryDyfp(Map<String, Object> parameters2);

    /**
     * 在院病人费用报表查询
     *
     * @param inHospitalPatientReportReq
     * @return
     */
    List<Map<String, Object>> queryInHospitalPatientReport(InHospitalPatientReportReq inHospitalPatientReportReq);

    /**
     * 查询出院病人的基本信息
     *
     * @param dischargedPatientReportReq
     * @return
     */
    List<Map<String, Object>> queryDischargedPatientReport(DischargedPatientReportReq dischargedPatientReportReq);

    /**
     * 查询病区患者费用
     *
     * @param bqPatientReportReq
     * @return
     */
    List<Map<String, Object>> queryBqPatientReport(BqPatientReportReq bqPatientReportReq);


    /**
     * 查询医嘱费用总计
     *
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryYzSumPrice
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryYzSumPrice(@Param("zyh") Integer zyh, @Param("fyxh") Integer fyxh, @Param("fyrq") Timestamp fyrq);


    /**
     * 病区收入核算
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryRevenueAccounting
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryRevenueAccounting(Map<String, Object> parameters);


    /**
     * 病区一日清单医嘱格式
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryListByYz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryListByYz(Map<String, Object> parameters);


    /**
     * 查询总计金额和自负金额
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryYzCost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryYzCost(Map<String, Object> parameters);

    /**
     * 批量 查询费用明细信息
     *
     * @param ids
     * @return
     */
    List<ImFeeFymxReq> queryImFeeFymxByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询未结账结算明细
     *
     * @param
     * @return
     */
    List<Map<String, Object>> queryRzjzJsmx(Map<String, Object> params);

    /**
     * 查询已结账结算明细
     *
     * @param
     * @return
     */
    List<Map<String, Object>> getRzjzJsmx(Map<String, Object> params);

    /**
     * 查询已结账付款方式
     *
     * @param
     * @return
     */
    List<Map<String, Object>> getIdsJsfpFk(Map<String, Object> params);

    /**
     * 住院未结账收预交金
     *
     * @param
     * @return
     */
    List<Map<String, Object>> queryIdsTbkk(Map<String, Object> params);

    /**
     * 住院结账收预交金
     *
     * @param
     * @return
     */
    List<Map<String, Object>> getIdsTbkk(Map<String, Object> params);


    /**
     * 更新未收费费用就诊单元号
     *
     * @param @param jzdyh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateFeeJzdyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 卑军华
     */
    void updateFeeJzdyh(String jzdyh, Integer zyh);

    /**
     * 更新未收费费用明细账单号
     *
     * @param @param jzdyh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateFeeMxzdh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author queryCostDetailInfo
     */
    void updateFeeMxzdh(String jzdyh, Integer zyh);

    /**
     * 费用清单打印查询 - 明细格式
     */
    List<ExpensesPrintDataResp> findExpensesListByTemplate1(ExpensesPrintReq req);

    /**
     * 费用清单打印查询 - 项目汇总
     */
    List<ExpensesPrintDataResp> findExpensesListByTemplate2(ExpensesPrintReq req);

    /**
     * 费用清单打印查询 - 医嘱格式
     */
    List<ExpensesPrintDataResp> findExpensesListByTemplate3(ExpensesPrintReq req);

    /**
     * 费用清单打印查询 - 按日汇总
     */
    List<ExpensesPrintDataResp> findExpensesListByTemplate4(ExpensesPrintReq req);


    /**
     * 判断出院后是否存在费用
     *
     * @param @param  jgid
     * @param @param  zyh
     * @param @param  fyrq
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryAfterCyIsExistCostCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryAfterCyIsExistCostCount(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh,
                                      @Param("fyrq") Timestamp fyrq);

    /**
     * 判断出院后是否存在费用医嘱
     *
     * @param @param  jgid
     * @param @param  zyh
     * @param @param  fyrq
     * @param @return 设定文件
     * @return List<ImFeeFymxCostResp>    返回类型
     * @throws
     * @Title: queryAfterCyIsExistCost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<ImFeeFymxCostResp> queryAfterCyIsExistCost(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh,
                                                    @Param("fyrq") Timestamp fyrq);

    /**
     * 查询医保病人结算信息（发票）
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryYbxx(Map<String, Object> parameters);

    /**
     * 查询超出出院结算时间
     *
     * @param zyh  住院号
     * @param cyrq 出院日期
     */
    List<ImFeeFymx> getOutRangeList(@Param("zyh") Integer zyh, @Param("cyrq") Timestamp cyrq);

    /**
     * 修改费用日期
     *
     * @param jlxh 记录序号
     * @param fyrq 费用日期
     */
    void updateFyrq(@Param("jlxh") Integer jlxh, @Param("fyrq") Timestamp fyrq);

    /**
     * 进入预结算界面后，是否有新的费用明细产生
     *
     * @param zyh  住院号
     * @param czsj 起始时间
     * @param zzrq 结算终止时间
     */
    boolean isOutOfRange(@Param("zyh") Integer zyh, @Param("czsj") String czsj, @Param("zzrq") String zzrq, @Param("isDischarge") boolean isDischarge);
    
    /**
     * 查询医嘱费用总大于0的医嘱序号
     * @Title: queryTotalCost
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param yzxhList
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Integer> queryTotalCost(@Param("yzxhList") List<Integer> yzxhList, @Param("jgid") Integer jgid);


    /**
     * 费用帐卡汇总对账
     * @param verifyRecordReq
     * @return
     */
    List<VerifyRecordResp> queryVerifyRecord(VerifyRecordReq verifyRecordReq);
    
    /**
     * 查询当天患者附加项目计费数量
     * @Title: queryAdditionalItemsNowDayNum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param fyxh
     * @param @param jfrq
     * @param @return    设定文件
     * @return BigDecimal    返回类型
     * @author 龚方舟
     * @throws
     */
    BigDecimal queryAdditionalItemsNowDayNum(@Param("zyh") Integer zyh, @Param("fyxh") Integer fyxh,
    		@Param("fyrq") String fyrq);

    void updateSnzy(@Param("zyh") Integer zyh);
    void updateImfymx(@Param("zyh") Integer zyh);

    /**
     * 上传明细与费用明细校验
     * @param zyh
     * @param ksrq
     * @param zzrq
     * @return
     */
    List<Map<String, Object>> getMxts(@Param("zyh") Integer zyh, @Param("ksrq") String ksrq,
                                      @Param("zzrq") String zzrq);

    /**
     * 查询费用明细
     * @Title: queryFeeDetails
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param yzxh
     * @param @return    设定文件
     * @return List<ImFeeFymx>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImFeeFymx> queryFeeDetails(Integer yzxh);
}
