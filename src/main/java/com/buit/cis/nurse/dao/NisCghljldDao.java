package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisCghljld;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisCghljldResp;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * 常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisCghljldDao extends EntityDao<NisCghljld,Integer>{
    
    /**
     * 查询常规护理记录单结构树
     * @Title: queryHljlTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryCghljlTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    

    /**
     * 根据日期查询护理记录单
     * @Title: queryCgHljlByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisCghljldResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisCghljldResp> queryCgHljlByDate(NisHlQueryReq nisHlQueryReq);
    
    /**
     * 根据主键查询护理记录单
     * @Title: getCgHljlByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @return    设定文件
     * @return NisCghljldResp    返回类型
     * @author 龚方舟
     * @throws
     */
    NisCghljldResp getCgHljlByJlxh(@Param("jlxh") Integer jlxh);
    
    /**
     * 查询常规护理记录单结构树
     * @Title: queryHljlTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryCghljlTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
     		@Param("yearMonth") String yearMonth);
}
