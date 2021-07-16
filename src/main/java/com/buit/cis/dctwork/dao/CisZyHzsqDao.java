package com.buit.cis.dctwork.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.CisZyHzsq;
import com.buit.cis.dctwork.request.CisZyHzsqReq;
import com.buit.cis.dctwork.response.CisZyHzsqResp;
import com.buit.commons.EntityDao;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisZyHzsqDao extends EntityDao<CisZyHzsq,Integer>{
	
	/**
	* 根据病区科室代码查询会诊申请
	* @Title: queryHzSqByUseId
	* @Description: TODO 
	* @param @param cisZyHzsqReq
	* @param @return    设定文件
	* @return List<CisZyHzsqResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	List<CisZyHzsqResp> queryHzSqByUseId(CisZyHzsqReq cisZyHzsqReq);

	/**   
	* 根据病区科室代码查询会诊申请
	* @Title: queryHzSqByKs
	* @Description: TODO 
	* @param @param cisZyHzsqReq
	* @param @return    设定文件
	* @return List<CisZyHzsqResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
    List<CisZyHzsqResp> queryHzSqByKs(CisZyHzsqReq cisZyHzsqReq);  
    
 
    /**
    * 根据申请序号更新会诊申请提交状态
    * @Title: updateCisZyHzsqSubmitStatus
    * @Description: TODO
    * @param @param sqxh
    * @param @param tjbz    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    void updateCisZyHzsqSubmitStatus(@Param("sqxh") Integer sqxh, @Param("tjbz") Integer tjbz, 
    		@Param("userId") Integer userId, @Param("time") Timestamp time);
    
    /**
     * 结束会诊申请修改
     * @Title: stopCisZyHzsq
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqxh
     * @param @param jssj    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void stopCisZyHzsq(@Param("sqxh") Integer sqxh, @Param("jssj") Date jssj);
    
    /**
     * 根据住院号查询已提交会诊申请记录
     * @Title: queryHzSqByZyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jzhm
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryHzSqByZyh(Integer jzhm);
    

    /**
     * 医嘱提交根据申请序号更新会诊申请提交状态
     * @Title: updateYzHzsqSubmitStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long updateYzHzsqSubmitStatus(@Param("sqxhList") List<Integer> sqxhList);
    
   	/**
   	 * 根据记录序号批量取消会诊提交状态
   	 * @Title: canceHzlSubmitStatusByJlxhList
   	 * @Description: TODO(这里用一句话描述这个方法的作用)
   	 * @param @param jlxh    设定文件
   	 * @return void    返回类型
   	 * @author 龚方舟
   	 * @throws
   	 */
   	void canceHzlSubmitStatusByJlxhList(@Param("sqxhList") List<Integer> sqxhList);
   	
    /**
     * 根据申请序号查询病人信息
     * @Title: queryImHzBySqxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqxh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryImHzBySqxh(Integer sqxh);
    
    /**
     * 根据申请序号查询申请单打印信息
     * @Title: queryHzsqPrintBySqxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqxh
     * @param @param jgid
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryHzsqPrintBySqxh(@Param("sqxh") Integer sqxh, @Param("jgid") Integer jgid);
    

    /**
     * 更新消息返回id
     * @Title: updateMessIdBySqxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param messid
     * @param @param sqxh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateMessIdBySqxh(@Param("messid") String messid, @Param("sqxh") Integer sqxh);
    

    /**
     * 查询消息id
     * @Title: queryMessIdBySqxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqxh
     * @param @return    设定文件
     * @return String    返回类型
     * @author 龚方舟
     * @throws
     */
    String queryMessIdBySqxh(Integer sqxh);
    
    CisZyHzsqResp queryBySqxh(Integer sqxh);

	/**
	 * 查询会诊申请-电子病历数据引用
	 * @param entity
	 * @return
	 */
	List<Map<String, Object>> findHzsqForBl(Object entity);

	/**
	 * @Description 批量删除会诊申请
	 * @Title  deleteConsultationBySqidList
	 * @author  gfz
	 * @Date  2021/6/6 23:49
	 * @param sqidList
	 * @Return  void
	 * @Exception
	*/
	void deleteConsultationBySqidList(@Param("sqidList") List<Integer> sqidList);

}
