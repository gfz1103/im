package com.buit.cis.ims.dao;


import com.buit.cis.ims.model.ImZyjs;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 住院_住院结算<br>
 * @author GONGFANGZHOU
 */
@Mapper
@Repository
public interface ImZyjsDao extends EntityDao<ImZyjs,Integer> {

	/**
	 * 根据住院号和结算次数查询住院结算交款合计
	 * @Title: queryTotalPaymentHospital
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param jscs
	 * @param @return    设定文件
	 * @return BigDecimal    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    BigDecimal queryTotalPaymentHospital(@Param("zyh") Integer zyh, @Param("jscs") Integer jscs);


   /**
    * 根据住院号和结算次数查询住院结算自负合计
    * @Title: queryTotalSelfHospital
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param zyh
    * @param @param jscs
    * @param @return    设定文件
    * @return BigDecimal    返回类型
    * @author 龚方舟
    * @throws
    */
    BigDecimal queryTotalSelfHospital(@Param("zyh") Integer zyh, @Param("jscs") Integer jscs);

	/**
	 * 判断当前病人是否在发票作废列表
	 * @param zyh
	 * @return
	 */
//    Integer isFbzfList(Integer zyh);

	/**
	 * 结算发票作废
	 */
	void updateFpzf(ImZyjs lastJS);

	/**
	 * 通过住院号获取费用开始时期
	 * @param zyh
	 * @return
	 */
    String findMaxZyrq(Integer zyh);

	/**
	 * 查询是否有住院结算记录
	 * @param map
	 * @return
	 */
    Integer checkCount(Map<String, Object> map);

	/**
	 *  取消日报
	 * @param parameters
	 */
    void doCancelCommit(Map<String, Object> parameters);

	/**
	 * 结算发票和缴款收据 票据序列
	 * @param map
	 * @return
	 */
    List<Map<String, Object>> queryIdsjsfp(Map<String, Object> map);

	/**
	 * 查询结账期内的住院结算记录数
	 * @param parameters
	 * @return
	 */
	Integer queryJzrqCount(Map<String, Object> parameters);

	/**
	 * 住院 住院结算修改
	 * @param parameters
	 */
	void updateJzrq(Map<String, Object> parameters);

	/**
	 * 查询结算明细
	 * @param parameters
	 * @return
	 */
    List<Map<String, Object>> queryJsmxJzrq(Map<String, Object> parameters);

	/**
	 * 查询结算作废明细
	 * @param parameters
	 * @return
	 */
	List<Map<String, Object>> queryJszfmxJzrq(Map<String, Object> parameters);

	/**
	 * 结算明细list
	 * @param parameters
	 * @return
	 */
	List<Map<String, Object>> queryJsmxJzrqList(Map<String, Object> parameters);

	/**
	 * 结算明细作废list
	 * @param parameters
	 * @return
	 */
	List<Map<String, Object>> queryJsmxJzrqListZf(Map<String, Object> parameters);

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
	 * 汇总费用合计
	 * @param parametersje
	 * @return
	 */
    Map<String, Object> queryHzFyhj(Map<String, Object> parametersje);

	/**
	 * 汇总缴款合计
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzJkhj(Map<String, Object> parametersje);

	/**
	 * 汇总费用合计作废
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzFyhjZf(Map<String, Object> parametersje);

	/**
	 * 汇总现金支票的自负合计
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzZfhjXjzp(Map<String, Object> parametersje);

	/**
	 * 汇总现金支票的额缴款合计
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzJkhjXjzp(Map<String, Object> parametersje);

	/**
	 * 汇总出院终结的费用
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzCyzj(Map<String, Object> parametersje);

	/**
	 * 汇总其他应收作废
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzQtysZf(Map<String, Object> parametersje);

	/**
	 * 汇总其他应收
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryQtys(Map<String, Object> parametersje);

	/**
	 * 汇总参保应收
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzCbys(Map<String, Object> parametersje);

	/**
	 * 汇总参保应收作废
	 * @param parametersje
	 * @return
	 */
	Map<String, Object> queryHzCbysZf(Map<String, Object> parametersje);

	/**
	 * 查询汇总本期结算
	 * @param parametersfyje
	 * @return
	 */
	List<Map<String, Object>> queryHzBqjs(Map<String, Object> parametersfyje);

	/**
	 * 查询发票打印的结算信息
	 * @param parameters
	 * @return
	 */
    List<Map<String,Object>> queryDyfpJsxx(Map<String, Object> parameters);

	/**
	 * 查询打印发票的结算日期
	 * @param parameters
	 * @return
	 */
	Map<String, Object> queryDyfpJsrq(Map<String, Object> parameters);

	/**
	 * 结算获取结算次数
	 * @param zyh
	 * @return
	 */
	List<Map<String, Object>> getJscs(Integer zyh);

	/**
	 * 查询该次住院未作废的结算记录列表(根据结算时间排序)
	 */
	List<ImZyjs> getLastRecord(Integer zyh);

	/**
	 * 住院 住院结算修改ZFFY
	 * @param
	 */
	void updateImzyjsZfhj(BigDecimal ZFHJ, int jscs, int zyh,int jgid);
	

	/**
	 * 查询中途结算的最大终止日期
	 * @Title: queryTerminationDate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return Date    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    Date queryTerminationDate(Integer zyh);
}
