package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisCqhld;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * 产前护理记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisCqhldDao extends EntityDao<NisCqhld,Integer>{

	 /**
	  * 产前护理记录单查询树结构
	  * @Title: queryCqhldTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryCqhldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	 
	 /**
	  * 根据日期查询产前护理记录单
	  * @Title: queryCqhldByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisCqhld>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisCqhld> queryCqhldByDate(NisHlQueryReq nisHlQueryReq);
	 
	 /**
	  * 产前护理记录单查询树结构明细
	  * @Title: queryCqhldTreeDetail
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbDetailResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbDetailResp> queryCqhldTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
	    		@Param("yearMonth") String yearMonth);
}
