package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.nurse.model.NisHljldzdy;
/**
 * 护理记录单自定义内容<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljldzdyDao extends EntityDao<NisHljldzdy,Integer>{
    
    /**
     * 查询自定义内容
     * @Title: queryZdynrByZdyId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryZdynrByZdyId(@Param("jlxh") Integer jlxh, @Param("mblx") String mblx, @Param("jgid") Integer jgid);
    

    /**
     * 查询自定义内容列标题
     * @Title: queryZdyTitle
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisHljldzdy>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHljldzdy> queryZdyTitle(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid, @Param("mblx") String mblx);
    
    /**
     * 更新护理记录病人自定义名称
     * @Title: updateZdymc
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param xmdm
     * @param @param zdymc
     * @param @param jgid    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateZdymc(@Param("xmdm") Integer xmdm, @Param("zdymc") String zdymc, @Param("jgid") Integer jgid);
}
