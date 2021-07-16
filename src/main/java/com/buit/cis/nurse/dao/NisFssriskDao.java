package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisFssrisk;
import com.buit.cis.nurse.response.NisFssriskResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisFssriskDao extends EntityDao<NisFssrisk,Integer>{
    
	/**
	  * 肺栓塞风险因素评估表查询树结构
	  * @Title: queryFsspgbTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryFssriskTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid); 
	 
	 /**
	  * 根据日期查询肺栓塞风险因素评估表
	  * @Title: queryFssriskByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisFssriskResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisFssriskResp> queryFssriskByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);
	
	 /**
	  * 根据日期查询肺栓塞风险因素评估表打印信息
	  * @Title: queryPrintFssriskByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<Map<String, Object>> queryPrintFssriskByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);
	 
	 
}
