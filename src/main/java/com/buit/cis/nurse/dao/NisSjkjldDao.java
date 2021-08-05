package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisSjkjld;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisSjkjldResp;
import com.buit.commons.EntityDao;
/**
 * 神经科记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisSjkjldDao extends EntityDao<NisSjkjld,Integer>{
    
	/**
	  * 神经科记录单查询树结构
	  * @Title: querySjkjldTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> querySjkjldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid); 
	 
	 /**
	  * 根据日期查询神经科记录单
	  * @Title: querySjkjldByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisSjkjldResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisSjkjldResp> querySjkjldByDate(NisHlQueryReq nisHlQueryReq);
	 
	 /**
	  * 神经科记录单查询树结构
	  * @Title: querySjkjldTreeDetail
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbDetailResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbDetailResp> querySjkjldTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
	     		@Param("yearMonth") String yearMonth); 
}
