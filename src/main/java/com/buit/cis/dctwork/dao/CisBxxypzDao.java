package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.CisBxxypz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisBxxypzDao extends EntityDao<CisBxxypz,String>{
    
    /**
     * 删除备血申请单血液表
     * @Title: deleteBatchById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void deleteBatchById(@Param("sqidList") List<Integer> sqidList);
}
