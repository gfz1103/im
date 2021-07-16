package com.buit.cis.bedresev.dao;

import com.buit.cis.bedresev.model.ImBedresev;
import com.buit.cis.bedresev.request.BedInfoReq;
import com.buit.cis.bedresev.request.QueryImBedresevCollectReq;
import com.buit.cis.bedresev.request.QueryImBedresevCollectResp;
import com.buit.cis.bedresev.response.BedInfoResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * @author zhouhaisheng
 */
@Mapper
public interface ImBedresevDao extends EntityDao<ImBedresev,Integer>{
    /**
     * 取消待床
     * @param imBedresev
     */
    void cancelWaitBed(ImBedresev imBedresev);

    /**
     * 出院预约统计分析查询
     * @param queryImBedresevCollectReq
     * @return
     */
    List<QueryImBedresevCollectResp> queryImBedresevCollect(QueryImBedresevCollectReq queryImBedresevCollectReq);

    /**
     * 待床入院登记
     *
     * @param imBedresev
     */
    void updateRydj(ImBedresev imBedresev);

    /**
     * 查询所有床位
     *
     * @param req
     */
    List<BedInfoResp> queryAllBed(BedInfoReq req);

    /**
     * 是否已已预约或已待床的记录
     *
     * @param jgid 机构id
     * @param brid 病人id
     */
    boolean isAppointedBed(@Param("jgid") Integer jgid, @Param("brid") Integer brid);
}
