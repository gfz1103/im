package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.request.CisHzyzBookPrintReq;
import com.buit.cis.nurse.model.NisYzdy;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisYzdyDao extends EntityDao<NisYzdy,Integer>{
    
    /**
     * 根据打印序号更新重整护士
     * @Title: updateCzhsByDyxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param dyxh
     * @param @param czhs    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateCzhsByDyxh(@Param("dyxh") Integer dyxh, @Param("czhs") String czhs);
    

    /**
     * 根据医嘱本序号查询打印序号
     * @Title: queryDyxhByYzbxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param yzbxh
     * @param @return    设定文件
     * @return NisYzdy    返回类型
     * @author 龚方舟
     * @throws
     */
    NisYzdy queryDyxhByYzbxh(Integer yzbxh);
    
    /**
     * 查询是否有当前输入页的打印数据
     * @Title: queryIsExistPrintPage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyzBookPrintReq
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryIsExistPrintPage(CisHzyzBookPrintReq cisHzyzBookPrintReq);
    
    /**
     * 根据住院号查询打印日期
     * @Title: queryDyrqByZyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryDyrqByZyh(Integer zyh);
    
    /**
     * 更新打印操作标志
     * @Title: updatePrintCzbz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param yzlx    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updatePrintCzbz(@Param("zyh") Integer zyh, @Param("yzlx") Integer yzlx);
    
    /**
     * 根据打印序号查询打印信息
     * @Title: queryDyInfoByDyxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param dyxh
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryDyInfoByDyxh(@Param("dyxhlist") List<Integer> dyxh);
    
    /**
     * 继续打印查询
     * @Title: queryResumePrint
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryResumePrint(Map<String, Object> parameters);
    
    
    /**
     * 按页打印查询
     * @Title: queryPagePrint
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPagePrint(Map<String, Object> parameters);
    
    /**
     * 指定行打印查询
     * @Title: querySpecifyLinePrint
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> querySpecifyLinePrint(Map<String, Object> parameters);
    
    /**
     * 重整打印查询
     * @Title: queryRestructPrint
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryRestructPrint(Map<String, Object> parameters);
}
