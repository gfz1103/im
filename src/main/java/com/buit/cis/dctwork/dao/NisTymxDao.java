package com.buit.cis.dctwork.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.NisTymx;
import com.buit.cis.dctwork.response.NisTymxResp;
import com.buit.cis.dctwork.response.QueryBackMedicineWardApiResp;
import com.buit.cis.nurse.response.NisTymxRecordResp;
import com.buit.commons.EntityDao;
import com.buit.drug.request.QueryGrantedDrugReq;
import com.buit.his.pha.commons.dto.QueryConditionDto;
/**
 * 病区_退药明细<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisTymxDao extends EntityDao<NisTymx,Integer>{
    
	 /**
	 * 退药医嘱未提交或未确认表单记录数
     * @Title: queryTyNotSubmitOrNotSure
     * @Description: TODO 
     * @param @param zyh
     * @param @return    设定文件
     * @return List<NisTymxResp>    返回类型
     * @author 龚方舟
     * @throws
      */
	 List<NisTymxResp> queryTyNotSubmitOrNotSure(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	 
	 /**
	  * 退药申请查询退药记录
	  * @Title: queryTymxRecord
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @return    设定文件
	  * @return List<NisTymxRecordResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisTymxRecordResp> queryTymxRecord(@Param("zyh") Integer zyh, @Param("beginDate") String beginDate, 
			 @Param("endDate") String endDate);
	 
     /**
      * 查询已提交的退药数量
      * @Title: queryTyslSubmitted
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param nisTymxRetrogressiveReq
      * @param @return    设定文件
      * @return BigDecimal    返回类型
      * @author 龚方舟
      * @throws
      */
	 BigDecimal queryTyslSubmitted(QueryGrantedDrugReq queryGrantedDrugReq);
     
	 /**
	  * 根据住院号和机构id删除退药明细
	  * @Title: deleteTymxByZyhAndJgid
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void deleteTymxByZyhAndJgid(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	 

     /**
      * 根据住院号和机构id更新退药明细提交状态
      * @Title: updateTymxByZyhAndJgid
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param jgid    设定文件
      * @return void    返回类型
      * @author 龚方舟
      * @throws
      */
     void updateTymxByZyhAndJgid(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
     
     /**
      * 判断是否未提交或未确认的退药单
      * @Title: queryAntidoteNotSureOrExcuted
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param jgid
      * @param @return    设定文件
      * @return long    返回类型
      * @author 龚方舟
      * @throws
      */
     long queryAntidoteNotSureOrExcuted(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

	/**
	 * 查询待退药记录
	 *
	 * @param cond
	 * @return
	 */
    List<QueryBackMedicineWardApiResp> queryByCond(@Param("cond") QueryConditionDto cond);

    /**
	 * 查询待退药记录
	 *
	 * @param jlxhList
	 * @return
	 */
    List<NisTymx> queryByJlxh(@Param("list") List<Integer> jlxhList);
    
    /**
     * 判断是否未提交或未确认的退药单列表
     * @Title: queryAntidoteNotSureOrExcutedInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisTymxResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTymxResp> queryAntidoteNotSureOrExcutedInfo(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    

    /**
     * 查询已保存退药申请列表
     * @Title: queryDrugWithdrawalApplicationList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return List<NisTymxResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTymxResp> queryDrugWithdrawalApplicationList(@Param("zyh") Integer zyh, @Param("beginDate") String beginDate,
    		@Param("endDate") String endDate);
}
