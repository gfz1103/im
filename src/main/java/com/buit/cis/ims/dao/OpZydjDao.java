package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;
import com.buit.cis.ims.model.OpZydj;
import com.buit.cis.ims.response.OpZydjPageResp;
import com.buit.op.response.OpZydjResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 住院病人入院登记
 * <br>
 * @author zhouhaisheng
 */
@Mapper
public interface OpZydjDao extends EntityDao<OpZydj,Integer> {
    /**
     * 门诊住院申请列表查询
     * @param opZydj
     * @return
     */
    List<OpZydjResp> findMzzydj(String opZydj);

    /**
     * 查询住院登记单列表
     * @param par
     * @return
     */
    List<OpZydjPageResp> getHospitalizedRegisterList(Map<String, Object> par);

    /**
     * 修改住院登记单
     * @param req
     */
    void updateSqzt(OpZydj req);

    /**
     * @name: getPrintInfo
     * @description: 获取打印信息
     * @param map_par
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @date: 2020/8/27 15:56
     * @auther: 老花生
     *
     */
    Map<String, Object> getPrintInfo(Map<String, Object> map_par);
}
