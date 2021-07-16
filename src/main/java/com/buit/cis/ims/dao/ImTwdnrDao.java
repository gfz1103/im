package com.buit.cis.ims.dao;


import com.buit.cis.ims.model.ImTwdnr;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface ImTwdnrDao extends EntityDao<ImTwdnr,Integer> {

    /**
     * 更新住院体温单作废状态
     * @param imTwdnr  请求参数
     */
    void invalid(ImTwdnr imTwdnr);
    
}
