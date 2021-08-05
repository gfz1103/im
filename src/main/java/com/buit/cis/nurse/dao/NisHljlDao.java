package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisHljl;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * 一般护理记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljlDao extends EntityDao<NisHljl,Integer>{

    /**
     * 一般护理记录单查询树结构
     * @Title: queryHljlTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryHljlTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 根据日期查询一般护理记录单
     * @Title: queryHljlByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHljl>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHljl> queryHljlByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
    		@Param("jgid") Integer jgid);
    
    /**
     * 一般护理记录单查询树结构明细
     * @Title: queryHljlTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryHljlTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
    		@Param("yearMonth") String yearMonth);
}
