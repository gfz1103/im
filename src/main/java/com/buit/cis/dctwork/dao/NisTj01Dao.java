package com.buit.cis.dctwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.NisTj01;
import com.buit.cis.dctwork.response.QueryDispensingWardApiResp;
import com.buit.cis.dctwork.response.QuerySubmitApplyApiResp;
import com.buit.commons.EntityDao;
import com.buit.his.pha.commons.dto.QueryConditionDto;

/**
 * 病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisTj01Dao extends EntityDao<NisTj01,Integer>{

    /**
     * 病区待发药记录查询
     *
     * @param cond
     * @return
     */
    List<QueryDispensingWardApiResp> queryByCond(@Param("cond") QueryConditionDto cond);

    /**
     * 联结NIS_TJ02表根据zyh查询tjxh
     *
     * @param zyhList
     * @return
     */
    List<Integer> queryTjxhByZyh(@Param("list") List<Integer> zyhList);

    /**
     * 查询待发药提交单
     * @param cond
     * @return
     */
    List<QuerySubmitApplyApiResp> queryDispensingTjd(@Param("cond") QueryConditionDto cond);
    

    /**
     * 根据提交序号更新tj01发药标志
     * @Title: updateFybzByTjxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param tjxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateFybzByTjxh(@Param("jgid") Integer jgid, @Param("tjxhList") List<Integer> tjxhList);
}
