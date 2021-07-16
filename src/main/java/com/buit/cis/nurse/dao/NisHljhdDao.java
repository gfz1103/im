package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisHljhd;
import com.buit.cis.nurse.response.NisHljhdResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 护理计划单主表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljhdDao extends EntityDao<NisHljhd,Integer>{
    

    /**
     * 根据日期查询护理计划单
     * @Title: queryHljhdByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHljhdResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHljhdResp> queryHljhdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
    		@Param("jgid") Integer jgid);
    

    /**
     * 查询计划单时间树
     * @Title: queryHljhdDateTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<String>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<String> queryHljhdDateTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
}
