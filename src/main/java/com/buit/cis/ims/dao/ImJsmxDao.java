package com.buit.cis.ims.dao;


import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.ImJsmx;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 住院_结算明细<br>
 * @author "ZHS"
 */
@Mapper
public interface ImJsmxDao extends EntityDao<ImJsmx,Integer> {
    /**
     * 新增住院结算记录
     * @param imJsmxParameters
     */
    void insertJs(Map<String, Object> imJsmxParameters);

    /**
     * 写入结算明细
     * @param imJsmxParameters
     */
    void insertJsMx(Map<String, Object> imJsmxParameters);

    /**
           * 更新状态
           * @param imJsmx
           *
           */
//        void updateStatus(ImJsmx imJsmx);

}
