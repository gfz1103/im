package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisDdzcpgb;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisDdzcpgbResp;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * 住院患者跌倒、坠床危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisDdzcpgbDao extends EntityDao<NisDdzcpgb,Integer>{
    
	/**
     * 查询跌倒、坠床危险因素评估表结构树
     * @Title: queryDdzcpgbTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryDdzcpgbTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
        
    /**
     * 根据日期查询跌倒、坠床危险因素评估表
     * @Title: queryDdzcpgbByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisDdzcpgbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisDdzcpgbResp> queryDdzcpgbByDate(NisHlQueryReq nisHlQueryReq);
    
    
    /**
     * 根据日期跌倒、坠床危险因素评估表打印信息
     * @Title: queryPrintDdzcpgbByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPrintDdzcpgbByDate(NisHlQueryReq nisHlQueryReq);
    
    /**
     * 查询跌倒、坠床危险因素评估表结构树
     * @Title: queryDdzcpgbTreeDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbDetailResp> queryDdzcpgbTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
     		@Param("yearMonth") String yearMonth);
}
