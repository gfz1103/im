package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisZspdl;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisZspdlResp;
import com.buit.commons.EntityDao;
/**
 * Barthel指数平定量表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisZspdlDao extends EntityDao<NisZspdl,Integer>{
    
	 /**
	  * Barthel指数平定量表查询树结构
	  * @Title: queryZspdlTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryZspdlTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	 
	 /**
	  * 根据日期查询Barthel指数平定量表
	  * @Title: queryZspdlByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisZspdlResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisZspdlResp> queryZspdlByDate(NisHlQueryReq nisHlQueryReq);
	 

	 /**
	  * 根据日期查询Barthel指数平定量表打印信息
	  * @Title: queryPrintZspdlByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
     List<Map<String, Object>> queryPrintZspdlByDate(NisHlQueryReq nisHlQueryReq);
     
     /**
	  * Barthel指数平定量表查询树结构明细
	  * @Title: queryZspdlTreeDetail
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbDetailResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbDetailResp> queryZspdlTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
	    		@Param("yearMonth") String yearMonth);
}
