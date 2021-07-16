package com.buit.cis.nurse.dao;

import java.util.List;

import com.buit.cis.nurse.model.NisAllzxd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;

/**
 * 所有护理执行单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisAllzxdDao extends EntityDao<NisAllzxd,Integer>{
    
	/**
	  * 对应执行单查询树结构
	  * @Title: queryZxdTree
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisHzmbResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisHzmbResp> queryZxdTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
			 @Param("mblx") String mblx); 
	 
	 /**
	  * 根据日期查询模板类型对应执行单
	  * @Title: queryZxdByDate
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param queryDate
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return List<NisBqyjpgd>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisAllzxd> queryZxdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
			 @Param("jgid") Integer jgid, @Param("mblx") String mblx);
	 

	 /**
	  * 根据组号删除
	  * @Title: deleteByZh
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zh    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void deleteByZh(Integer zh);
}
