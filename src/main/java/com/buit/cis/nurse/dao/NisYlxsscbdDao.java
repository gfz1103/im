package com.buit.cis.nurse.dao;

import com.buit.cis.nurse.model.NisYlxsscbd;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.response.NisHzmbResp;

/**
 * 压力性损伤预报、传报单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisYlxsscbdDao extends EntityDao<NisYlxsscbd,Integer>{
    
	/**
     * 压力性损伤预报、传报单查询树结构
     * @Title: queryYlxsscbdTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzmbResp> queryYlxsscbdTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 根据日期查询压力性损伤预报、传报单
     * @Title: queryYlxsscbdByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisWzhld>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisYlxsscbd> queryYlxsscbdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, @Param("jgid") Integer jgid);
    

    /**
     * 根据日期查询压力性损伤预报、传报单打印信息
     * @Title: queryPrintYlxsscbdByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<Strin,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPrintYlxsscbdByDate(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, @Param("jgid") Integer jgid);
}
