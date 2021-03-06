package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisYcwxpgb;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.cis.nurse.response.NisYcwxpgbResp;
import com.buit.commons.EntityDao;
/**
 * 住院患者压疮危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisYcwxpgbDao extends EntityDao<NisYcwxpgb,Integer>{

    /**
     * 住院患者压疮危险因素评估表查询树结构
     * @Title: queryYcwxpgbTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryYcwxpgbTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 根据日期查询住院患者压疮危险因素评估表
     * @Title: queryYcwxpgbByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisYcwxpgbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisYcwxpgbResp> queryYcwxpgbByDate(NisHlQueryReq nisHlQueryReq);
    
    /**
     * 根据日期查询住院患者压疮危险因素评估表打印信息
     * @Title: queryPrintYcwxpgbByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPrintYcwxpgbByDate(NisHlQueryReq nisHlQueryReq);
    
    /**
     * 住院患者压疮危险因素评估表查询树结构
     * @Title: queryYcwxpgbTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryYcwxpgbTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
    		@Param("yearMonth") String yearMonth);
}
