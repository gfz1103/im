package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImTbkk;
import com.buit.cis.ims.request.PatientImTbkkReq;
import com.buit.cis.ims.request.PatientPayPageReq;
import com.buit.cis.ims.request.QueryPatientCostReq;
import com.buit.cis.ims.response.CostImTbkkResp;
import com.buit.cis.ims.response.PatientImTbkkResp;
import com.buit.cis.ims.response.PatientPayPageResp;
import com.buit.cis.ims.response.QueryPatientCostResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 住院_退补缴款<br>
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImTbkkDao extends EntityDao<ImTbkk,Integer> {
    /**
     * 缴费管理-缴费处理分页查询
     * @param patientPayPageReq
     * @return
     */
    List<PatientPayPageResp> queryPatientPayPage(PatientPayPageReq patientPayPageReq);

    /**
     * 缴费管理-缴费处理-注销
     * @param imTbkk
     */
    void patientPayCancel(ImTbkk imTbkk);

    /**
     * 缴费管理-缴费查询
     * @param queryPatientCostReq
     * @return
     */
    List<QueryPatientCostResp> queryPatientCost(QueryPatientCostReq queryPatientCostReq);

    /**
     * 根据住院号获取病人的缴款总计金额
     * @return
     */
    BigDecimal queryJkje(Integer zyh);

    /**
     * 根据住院号查询交款合金额
     * @Title: queryTotalPayment
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return BigDecimal    返回类型
     * @author 龚方舟
     * @throws
     */
    BigDecimal queryTotalPayment(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 在院病人预缴款统计分页查询
     * @param patientImTbkkReq
     * @return
     */
    List<PatientImTbkkResp> queryPatientImTbkkPage(PatientImTbkkReq patientImTbkkReq);

    /**
     * 在院病人预缴款统计总和
     * @param patientImTbkkReq
     * @return
     */
    BigDecimal queryPatientImTbkkSum(PatientImTbkkReq patientImTbkkReq);

    /**
     * 修改结算次数
     * @param IM_TBKKparameters
     */
//    void updateJscs(Map<String, Object> IM_TBKKparameters);

    /**
     *查询缴款记录列表
     * @param imTbkkParam
     */
    List<CostImTbkkResp> findJkjl(ImTbkk imTbkkParam);

    /**
     * 统计缴款金额
     * @param imTbkkParam
     * @return
     */
    Integer valSumJkje(ImTbkk imTbkkParam);

    /**
     * 住院号分组查询缴款金额
     * @param debtInventoryReq
     * @return
     */
//    List<ImTbkkJkjeResp> findZytbkkjkje(DebtInventoryReq debtInventoryReq);

    /**
     * 检查是否有缴款记录
     * @param map
     * @return
     */
    Integer checkCount(Map<String, Object> map);

    /**
     * 取消日报
     * @param parameters
     */
    void doCancelCommit(Map<String, Object> parameters);

    /**
     * 按缴款时间查询结账信息
     * @param para
     * @return
     */
    List<Map<String, Object>> queryIdsJksj(Map<String, Object> para);

    /**
     * 查询结账日期
     * @param parameters
     * @return
     */
    Integer  queeryJzrq(Map<String, Object> parameters);

    /**
     * 修改结账日期
     * @param parameters
     */
    void updateJzrq(Map<String, Object> parameters);

    /**
     *日终结账-明细( 缴款明细查询)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJkmxJsrq(Map<String, Object> parameters);

    /**
     * 日终结账-明细( 缴款作废明细查询)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJkmxJsrqZf(Map<String, Object> parameters);

    /**
     * 日终结账-明细(退款明细List)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryTkmxJzrqList(Map<String, Object> parameters);

    /**
     * 日终结账-明细(退款明细作废List)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryTkmxJzrqListZf(Map<String, Object> parameters);

    /**
     * 日终结账-明细(退款明细)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryTkmxJsrq(Map<String, Object> parameters);

    /**
     * 日终结账-明细(作废退款明细)
     * @param parameters
     * @return
     */
//    List<Map<String, Object>> queryTkmxJsrqZf(Map<String, Object> parameters);

    /**
     * 日终汇总(缴款明细list)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJkmxJzrqList(Map<String, Object> parameters);

    /**
     * 日终汇总(缴款作废明细list)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJkmxJzrqListZf(Map<String, Object> parameters);

    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 查询汇总次数
     * @param parametersll_count
     * @return
     */
    Integer queryHzCount(Map<String, Object> parametersll_count);

    /**
     * 修改汇总日期
     * @param parametersupd
     */
    void updateHzrq(Map<String, Object> parametersupd);

    /**
     * 统计汇总金额
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzJkje(Map<String, Object> parametersje);

    /**
     * 查询汇总缴款作废金额
     * @param parametersje
     * @return
     */
    Map<String, Object> queryHzJkjeZf(Map<String, Object> parametersje);

    /**
     * 查询打印发票的缴款金额
     * @param parameters
     * @return
     */
    Map<String, Object> queryDyfpJkje(Map<String, Object> parameters);

    /**
     * 获取预缴金
     * @param map_Yjj
     * @return
     */
    List<Map<String, Object>> queryYjje(Map<String, Object> map_Yjj );

}
