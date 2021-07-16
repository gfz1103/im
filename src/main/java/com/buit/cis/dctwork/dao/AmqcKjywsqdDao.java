package com.buit.cis.dctwork.dao;

/**
 * <br>
 * @author GONGFANGZHOU
 */

import com.buit.cis.dctwork.model.AmqcKjywsqd;
import com.buit.cis.dctwork.request.AmqcMedicineReq;
import com.buit.cis.dctwork.response.AmqcMedicineResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AmqcKjywsqdDao extends EntityDao<AmqcKjywsqd,Integer> {
    
    /**
    * 查询审批通过的抗菌药物数量
    * @Title: queryApprovedInfo
    * @Description: TODO 
    * @param @param parameters
    * @param @return    设定文件
    * @return Map<String,Object>    返回类型
    * @author 龚方舟
    * @throws
     */
    Map<String, Object> queryApprovedInfo(Map<String, Object> parameters);
    
    /**
    * 抗菌药物审批列表
    * @Title: queryAmqcAndMedicineInfo
    * @Description: TODO 
    * @param @param amqcMedicineReq
    * @param @return    设定文件
    * @return List<AmqcMedicineResp>    返回类型
    * @author 龚方舟
    * @throws
     */
    List<AmqcMedicineResp> queryAmqcAndMedicineInfo(AmqcMedicineReq amqcMedicineReq);
}
