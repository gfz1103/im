package com.buit.cis.dctwork.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.request.DrugsTypkBqyzYpSrfReq;
import com.buit.cis.dctwork.response.NisFyyfResp;
import com.buit.commons.EntityDao;

/**
 * 病区_发药药房<br>
 * @author zhouhaisheng
 */
@Mapper
public interface NisFyyfDao extends EntityDao<NisFyyf,Long> {
    /**
     *病区_发药药房注销
     * @param yf
     */
    void updateZxpb(NisFyyf yf);
    
    /**
     * 查询病区发药药房信息
     * @Title: queryInpatientPharmacyInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryInpatientPharmacyInfo(Map<String, Object> parameters);
    
    /**
     * 查询药房识别
     * @Title: queryYfsb
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param drugsTypkBqyzYpSrfReq
     * @param @return    设定文件
     * @return List<Map<String, Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryYfsb(DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq);
    
    /**
     * 查询机构病区所有发药药房
     * @Title: queryAllYfsbCurrent
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisFyyf
     * @param @return    设定文件
     * @return List<NisFyyfResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisFyyfResp> queryAllYfsbCurrent(NisFyyf nisFyyf);
}
