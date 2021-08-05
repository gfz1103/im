package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisRyhld;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * 入院护理评估单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisRyhldDao extends EntityDao<NisRyhld,Integer>{
	
	/**
     * 入院护理评估单查询树结构
     * @Title: queryRyhldTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryRyhldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    

    /**
     * 根据日期查询入院护理评估单
     * @Title: queryRyhldByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisWzhld>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisRyhld> queryRyhldByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, @Param("jgid") Integer jgid);
    
    /**
     * 入院护理评估单查询树结构
     * @Title: queryRyhldTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryRyhldTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
     		@Param("yearMonth") String yearMonth);
}
