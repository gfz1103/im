package com.buit.cis.nurse.dao;

import com.buit.cis.nurse.model.NisChhld;
import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.nurse.response.NisHzmbResp;
/**
 * 产后护理记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisChhldDao extends EntityDao<NisChhld,Integer>{

    /**
     * 产后护理记录单查询树结构
     * @Title: queryChhldTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryChhldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 根据日期查询产后护理记录单
     * @Title: queryChhldByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisChhld>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisChhld> queryChhldByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate,
    		@Param("jgid") Integer jgid);
}
