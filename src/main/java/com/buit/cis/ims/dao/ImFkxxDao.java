package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImFkxx;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 住院_结算付款信息<br>
 * @author "ZHS"
 */
@Mapper
@Repository
public interface ImFkxxDao extends EntityDao<ImFkxx,Integer> {
    /**
     * 查询打印发票的付款信息
     * @param hosParam
     * @return
     */
    Map<String, Object> queryDyfp(Map<String, Object> hosParam);

    /**
     * 查询付款方式
     * @param hosParam
     * @return
     */
    Map<String, Object> queryPaymentWay(Map<String, Object> hosParam);

    /**
     * 查询打印发票的付款金额
     * @param parametersTKXJ
     * @return
     */
    Map<String, Object> queryDyfpFkje(Map<String, Object> parametersTKXJ);

    /**
           * 更新状态
           * @param imFkxx
           *
           */
//        void updateStatus(ImFkxx imFkxx);

}
