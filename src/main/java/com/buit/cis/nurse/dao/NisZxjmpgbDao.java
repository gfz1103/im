package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisZxjmpgb;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisZxjmpgbResp;
import com.buit.commons.EntityDao;
/**
 * 中心静脉导管相关性感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisZxjmpgbDao extends EntityDao<NisZxjmpgb,Integer>{
    
	/**
	  * 中心静脉导管相关性感染风险因素评估表查询树结构
	  * @Title: queryZxjmpgbTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryZxjmpgbTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid); 
	 
	 /**
	  * 根据日期查询中心静脉导管相关性感染风险因素评估表
	  * @Title: queryZxjmpgbByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisZxjmpgbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisZxjmpgbResp> queryZxjmpgbByDate(NisHlQueryReq nisHlQueryReq);
	 

	 /**
	  * 根据日期查询中心静脉导管相关性感染风险因素评估表打印信息
	  * @Title: queryPrintZxjmpgbByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<Map<String, Object>> queryPrintZxjmpgbByDate(NisHlQueryReq nisHlQueryReq);
	 
	 /**
	  * 中心静脉导管相关性感染风险因素评估表查询树结构
	  * @Title: queryZxjmpgbTreeDetail
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbDetailResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbDetailResp> queryZxjmpgbTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
	     		@Param("yearMonth") String yearMonth); 
}
