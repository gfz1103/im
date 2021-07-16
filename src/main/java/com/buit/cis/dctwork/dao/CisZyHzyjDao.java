package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.CisZyHzyj;
import com.buit.cis.dctwork.response.CisZyHzyjResp;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisZyHzyjDao extends EntityDao<CisZyHzyj,Integer>{
    
    /**
    * 根据申请序号查询
    * @Title: getBySqxh
    * @Description: TODO 
    * @param @param sqxh
    * @param @return    设定文件
    * @return CisZyHzyj    返回类型
    * @author 龚方舟
    * @throws
     */
    CisZyHzyj getBySqxh(Integer sqxh);
    
    /**
     * 更新会诊申请意见结束
     * @Title: updateCisZyHzyjEnd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisZyHzyj    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateCisZyHzyjEnd(CisZyHzyj cisZyHzyj);
    
    /**
     * 根据申请序号查询会诊意见和人员科室
     * @Title: getCisZyHzyjBySqxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sqxh
     * @param @return    设定文件
     * @return List<CisZyHzyjResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisZyHzyjResp> getCisZyHzyjBySqxh(Integer sqxh);

    /**
     * 查询会诊意见-电子病历数据引用
     * @param entity
     * @return
     */
    List<Map<String, Object>> findHzyjForBl(Object entity);
}
