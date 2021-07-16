package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisLzdngpgb;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisLzdngpgbResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 留置导尿管感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisLzdngpgbDao extends EntityDao<NisLzdngpgb,Integer>{
    
	 /**
	  * 留置导尿管感染风险因素评估表查询树结构
	  * @Title: queryLzdngTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryLzdngTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid); 
	 
	 /**
	  * 根据日期查询留置导尿管感染风险因素评估表
	  * @Title: queryLzdngByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisLzdngpgbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisLzdngpgbResp> queryLzdngByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);
	 
	 /**
	  * 根据日期查询留置导尿管感染风险因素评估表打印信息
	  * @Title: queryPrintLzdngByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<Map<String, Object>> queryPrintLzdngByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);   
}
