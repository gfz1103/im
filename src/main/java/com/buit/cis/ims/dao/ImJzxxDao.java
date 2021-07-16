package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImJzxx;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 住院_收款结帐信息<br>
 * @author ZHOUHAISHENG
 */
@Mapper
public interface ImJzxxDao extends EntityDao<ImJzxx, Timestamp> {
    /**
     * 查询有没结账过
     * @param map_par
     * @return
     */
    Integer quertCount(Map<String, Object> map_par);

    /**
     *
     * @param map_par
     * @return
     */
    Map<String, Object> queryMx(Map<String, Object> map_par);

    /**
     * 取消日报查询
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryCancelCommit(Map<String, Object> parameters);

    /**
     * 查询结账汇总记录信息
     * @param parameters
     * @return
     */
    List<Timestamp> quereyHz(Map<String, Object> parameters);

    /**
     * 查询最大结账日期
     * @param jzmaxparameters
     * @return
     */
    List<Map<String, Object>> queryMaxJzrq(Map<String, Object> jzmaxparameters);

    /**
     * 取消日报
     * @param parameters
     */
    void doCancelCommit(Map<String, Object> parameters);

    /**
     * 查询住院结账信息
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryZyjzxx(Map<String, Object> parameters);

    /**
     * 查询住院结账信息(结账日期不为空)
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryZyjzxxBrJzrq(Map<String, Object> parameters);

    /**
     * 校验结账信息
     * @param parameters
     * @return
     */
    List<Map<String, Object>> checkRecordCount(Map<String, Object> parameters);

    /**
     * 查询日终汇总的次数
     * @param parametershzbd
     * @return
     */
    Integer queryRzhzCount(Map<String, Object> parametershzbd);

    /**
     * 判断日终汇总有没有数据可查询
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> doQueryVerification(Map<String, Object> parametershzbd);

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
     * 日结汇总汇总表单
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzList(Map<String, Object> parametershzbd);

    /**
     * 查询汇总合计
     * @param parametershzbd
     * @return
     */
    Map<String, Object> queryHzhj(Map<String, Object> parametershzbd);

    /**
     * 汇总查询发票的作废张数
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzzfzs(Map<String, Object> parametershzbd);

    /**
     * 汇总查询作废票据list
     * @param parametersczgh
     * @return
     */
    List<Map<String, Object>> queryHzZfpjList(Map<String, Object> parametersczgh);

    /**
     * 查询日结汇总表单
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzParamList(Map<String, Object> parametershzbd);

    /**
     * 汇总查询作废票据list报表参数
     * @param parametershzbd
     * @return
     */
    List<Map<String, Object>> queryHzZfpjParamList(Map<String, Object> parametershzbd);

    Map<String, Object> queryHzShj(Map<String, Object> parametershzbd);

    /**
     * 查询汇总报表参数 按病人性质
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsBrxzParam(Map<String, Object> parameters);

    /**
     * 查询汇总报表参数 按付款方式
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryHzIdsFkfsParam(Map<String, Object> parameters);

    /**
     * 查询日终汇总（四）收入金额
     * @param parameter
     * @return
     */
    Map<String, Object> queryHzFour(Map<String, Object> parameter);
}
