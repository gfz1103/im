package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImOperateLog;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangwei
 * @Description 患者住院操作日志
 * @Date 2021-07-26
 */
@Mapper
@Repository
public interface ImOperateLogDao extends EntityDao<ImOperateLog, Integer> {
    /**
     * 按属性修改非空值
     */
    void updateByEntity(ImOperateLog entry);

    /**
     * 查询最后一次打印状态
     *
     * @param tableName 数据库表名
     * @param dataId    单据主键
     */
    Integer getLastPrintStatus(@Param("tableName") String tableName, @Param("dataId") Integer dataId);
}
