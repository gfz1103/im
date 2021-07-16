package com.buit.cis.nurse.dao;

import com.buit.cis.nurse.model.NisBqyjpgd;
import com.buit.commons.EntityDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.response.NisBqyjpgdResp;
import com.buit.cis.nurse.response.NisHzmbResp;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisBqyjpgdDao extends EntityDao<NisBqyjpgd,Integer>{

	 /**
	  * 患者病情预警评估单查询树结构
	  * @Title: queryBqyjpgdTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryBqyjpgdTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid); 
	 
	 /**
	  * 根据日期查询患者病情预警评估单
	  * @Title: queryBqyjpgdByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisBqyjpgdResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisBqyjpgdResp> queryBqyjpgdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);
	 
	 /**
	  * 根据日期查询患者病情预警评估单打印信息
	  * @Title: queryPrintBqyjpgdByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<Map<String, Object>> queryPrintBqyjpgdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid);
}
