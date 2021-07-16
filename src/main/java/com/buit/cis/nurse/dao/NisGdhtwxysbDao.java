package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisGdhtwxysb;
import com.buit.cis.nurse.response.NisGdhtwxysbResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
/**
 * 住院患者管道滑脱危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisGdhtwxysbDao extends EntityDao<NisGdhtwxysb,Integer>{
    

    /**
     * 管道滑脱危险因素表查询树结构
     * @Title: queryHljlTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryGdhtTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    

    /**
     * 根据日期查询管道滑脱危险因素表
     * @Title: queryGdhtwByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisGdhtwxysbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisGdhtwxysbResp> queryGdhtwByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
    		@Param("jgid") Integer jgid);
    
    /**
     * 根据日期查询管道滑脱危险因素表打印信息
     * @Title: queryPrintGdhtwByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPrintGdhtwByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
    		@Param("jgid") Integer jgid);
}
