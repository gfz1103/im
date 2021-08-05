package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisWzhld;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisWzhldResp;
import com.buit.commons.EntityDao;
/**
 * 危重患者护理单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisWzhldDao extends EntityDao<NisWzhld,Integer>{
 
    /**
     * 危重患者护理单查询树结构
     * @Title: queryWzhldTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryWzhldTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    

    /**
     * 根据日期查询危重患者护理记录单
     * @Title: queryWzhldByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisWzhldResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisWzhldResp> queryWzhldByDate(NisHlQueryReq nisHlQueryReq);
    
   
    /**
     * 根据日期查询危重患者护理记录单打印信息
     * @Title: queryPrintWzhldByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisWzhldResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisWzhldResp> queryPrintWzhldByDate(NisHlQueryReq nisHlQueryReq);
    
    /**
     * 危重患者护理单查询树结构明细
     * @Title: queryWzhldTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryWzhldTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
    		@Param("yearMonth") String yearMonth);
    

    NisWzhldResp getWzhldByJlxh(Integer jlxh);
}
