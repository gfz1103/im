package com.buit.cis.dctwork.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.request.CisHzyzZtQueryReq;
import com.buit.cis.dctwork.response.CisHzyzZtQueryResp;
import com.buit.cis.nurse.request.CisHzyzReviewUpdateReq;
import com.buit.cis.nurse.request.CisHzyzStopUpdateReq;
import com.buit.cis.nurse.request.CisHzyzorztExcuteReq;
import com.buit.cis.nurse.response.CisHzyzOrztResp;
import com.buit.commons.EntityDao;
@Mapper
public interface CisHzyzZtDao extends EntityDao<CisHzyzZt,Integer>{
    
	/**
	 * 更新组套医嘱提交状态
	 * @Title: updateBqyzZtSubmitStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param paramertes    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateBqyzZtSubmitStatus(Map<String, Object> paramertes); 
	
	/**
	 * 根据记录序号取消修改医嘱组套提交状态
	 * @Title: cancelZtSubmitStatusByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @param ystj    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    void cancelZtSubmitStatusByJlxh(@Param("ystjnew") Integer ystjnew, @Param("jlxh") Integer jlxh, 
    		@Param("ystj") Integer ystj, @Param("bzxx") String bzxx);
    
    /**
     * 医嘱组套作废或取消作废根据记录序号
     * @Title: invalidZtByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
	void invalidZtByJlxh(@Param("yzzf") Integer yzzf, @Param("zfgh") String zfgh,
						 @Param("zfsj") Timestamp zfsj, @Param("jlxhList") List<Integer> jlxhList);
	
	/**
	 * 已执行医嘱组套取消更新
	 * @Title: updateBqyzZtExecuted
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    void updateBqyzZtExecuted(String jlxh);
    
    /**
     * 更新组套复核状态(非同一个工号)
     * @Title: updateZtReviewStatusByOther
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateZtReviewStatusByOther(CisHzyz cisHzyz);
    
    /**
     * 批量取消医嘱组套复核
     * @Title: cancelZtReviewStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jlxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void cancelZtReviewStatus(@Param("jgid") Integer jgid, @Param("jlxhList") List<Integer> jlxhList);
    
    /**
     * 查询复核需执行医嘱组套
     * @Title: queryBqyzZtExcutedAndReview
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyzorztExcuteReq
     * @param @return    设定文件
     * @return List<CisHzyzOrztResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzOrztResp> queryBqyzZtExcutedAndReview(CisHzyzorztExcuteReq cisHzyzorztExcuteReq);
    
    
    /**
     * 更新组套确认时间和历史标志
     * @Title: updateZtTimeAndLsByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateZtTimeAndLsByJlxh(Map<String, Object> parameters);
    
    /**
	 * 医嘱组套删除
	 * @Title: deleteOrderSetByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteOrderSetByJlxh(@Param("jlxh") Long jlxh, @Param("yzlx") Integer yzlx);
	
	/**
	 * 更新医嘱组套停嘱复核状态
	 * @Title: updateBqyzZtStopOrderReview
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyz    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateBqyzZtStopOrderReview(CisHzyz cisHzyz);
	
	/**
	 * 住院检验申请查询
	 * @Title: queryZyJySqdList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzZtQueryReq
	 * @param @return    设定文件
	 * @return List<CisHzyzZtQueryResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<CisHzyzZtQueryResp> queryZyJySqdList(CisHzyzZtQueryReq cisHzyzZtQueryReq);
	

	/**
	 * 检查医嘱组套删除
	 * @Title: deleteOrderSetBySqid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqid
	 * @param @param yzlx    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteOrderSetBySqid(@Param("sqid") Integer sqid, @Param("yzlx") Integer yzlx);
	
	/**
	 * 医嘱组套删除
	 * @Title: deleteZtBatchByJlxhList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxhList    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteZtBatchByJlxhList(@Param("jlxhList") List<Integer> jlxhList);
	

	/**
	 * 组套问题医嘱通知更新
	 * @Title: updateZtProblemStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tzgh
	 * @param @param zyh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateZtProblemStatus(@Param("tzgh") Integer tzgh, @Param("zyh") Integer zyh);


	String getZt01Name(@Param("ztbh") Integer ztbh);
	
	/**
	 * 更新组套停嘱复核状态(非同一个工号)
	 * @Title: updateZtStopOrderReview
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyz    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	long updateZtStopOrderReview(CisHzyzStopUpdateReq cisHzyzStopUpdateReq);
	
	/**
	 * 查询未复核过医嘱的记录序号
	 * @Title: queryNotReview
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxhList
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<Integer> queryNotReview(@Param("jlxhList") List<Integer> jlxhList);

    /**
     * @Description 批量取消修改医嘱组套提交状态
     * @Title  cancelZtSubmitStatusByJlxhList
     * @author  gfz
     * @Date  2021/6/21 22:58
     * @param jlxhList
     * @Return  void
     * @Exception
    */
	void cancelZtSubmitStatusByJlxhList(@Param("jlxhList") List<Integer> jlxhList);
	

	/**
	 * 批量更新组套复核状态(非同一个工号)
	 * @Title: updateZtReviewStatusBatch
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @return    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	long updateZtReviewStatusBatch(CisHzyzReviewUpdateReq req);
	
    /**
     * 更新医嘱草药组套使用标志
     * @Title: updateZtUseLogoStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateZtUseLogoStatus(@Param("sybz") Integer sybz, @Param("jlxhList") List<Integer> jlxhList);
}
