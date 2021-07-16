package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisFsspgb;
import com.buit.cis.nurse.response.NisFsspgbResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisFsspgbDao extends EntityDao<NisFsspgb,Integer>{
    
	/**
	  * 深静脉血栓风险评估表查询树结构
	  * @Title: queryFsspgbTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryFsspgbTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
			 @Param("mblx") String mblx); 
	 
	 /**
	  * 根据日期查询深静脉血栓风险评估表
	  * @Title: queryFsspgbByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisFsspgbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisFsspgbResp> queryFsspgbByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid, @Param("mblx") String mblx);
	 
	 /**
	  * 根据日期查询深静脉血栓风险评估表打印信息
	  * @Title: queryPrintFsspgbByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @param mblx
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<Map<String, Object>> queryPrintFsspgbByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid, @Param("mblx") String mblx);
}
