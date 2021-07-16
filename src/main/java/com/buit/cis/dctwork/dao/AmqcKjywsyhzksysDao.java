package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.mms.antimi.model.AmqcKjywsyhzksys;
/**
 * 抗菌药物使用会诊科室医生表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface AmqcKjywsyhzksysDao extends EntityDao<AmqcKjywsyhzksys,Integer>{
    
    /**
     * 特殊级更新会诊意见
     * @Title: updateHzyjInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateHzyjInfo(Map<String, Object> parameters);
    
    /**
     * 批量删除抗菌药物会诊意见
     * @Title: deleteBatchYjBySqidList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqidList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void deleteBatchYjBySqidList(@Param("sqidList") List<Integer> sqidList);
}
