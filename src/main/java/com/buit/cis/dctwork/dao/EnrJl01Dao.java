package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.EnrJl01;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface EnrJl01Dao extends EntityDao<EnrJl01,Integer>{
    
    /**
     * 根据记录编号更新记录时间
     * @Title: updateJlTimeByJlbh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateJlTimeByJlbh(Map<String, Object> parameters);
    
    /**
     * 查询有效的护理记录条数
     * @Title: queryEffectiveCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlbh
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryEffectiveCount(Integer jlbh);
    
    /**
     * 根据记录编号更新记录状态为失效
     * @Title: updateRecordStatusByJlbh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlbh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateRecordStatusByJlbh(Integer jlbh);
}
