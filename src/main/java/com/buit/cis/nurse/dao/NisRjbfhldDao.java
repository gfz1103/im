package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisRjbfhld;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 日间病房护理记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisRjbfhldDao extends EntityDao<NisRjbfhld,Integer>{

	/**
     * 日间病房护理单查询树结构
     * @Title: queryRjbfhldTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryRjbfhldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	
	/**
	 * 根据日期查询日间病房护理单
	 * @Title: queryRjbfhldByDate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param queryDate
	 * @param @param hospitalId
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 龚方舟
	 * @throws
	*/
	List<NisRjbfhld> queryRjbfhldByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, @Param("jgid") Integer jgid);
    
}
