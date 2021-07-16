package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImDrugsOutRangeLog;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jiangwei
 * @Description 药品费用超出结算时间记录表
 * @Date 2021-05-03
 */
@Mapper
@Repository
public interface ImDrugsOutRangeLogDao extends EntityDao<ImDrugsOutRangeLog, Integer> {
    /**
     * 按属性修改非空值
     */
    void updateByEntity(ImDrugsOutRangeLog entry);
}
