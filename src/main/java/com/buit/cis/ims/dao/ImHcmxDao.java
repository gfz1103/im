package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImHcmx;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 住院_换床明细<br>
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImHcmxDao extends EntityDao<ImHcmx,Long> {
    /**
     * 修改结算次数
     * @param imFeeFymxParameters
     *//*
    void updateJscs(Map<String, Object> imFeeFymxParameters);*/

    /**
     * 修改结算次数（fyrq）
     */
    void updateJscsAndJsrq(ImZyjs imZyjs);

    /**
     * 住院号查询换床信息
     * @param zyh 住院号
     * @return
     */
    List<ImHcmx> queryHqchAndhch(Integer zyh);
    
    /**
     * 查询换床信息
     * @Title: queryHcInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryHcInfo(Map<String, Object> parameters);
}
