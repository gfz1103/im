package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.YjZy01;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface YjZy01Dao extends EntityDao<YjZy01,Integer>{
    
    /**
     * 查询是否有项目医嘱未停或未执行YJ_ZY01
     * @Title: queryYjNotExcuted
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryYjNotExcuted(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
}
