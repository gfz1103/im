package com.buit.cis.dctwork.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.NisTj02;
import com.buit.cis.dctwork.request.NisTj02HzInfoReq;
import com.buit.cis.dctwork.request.NisTj02Req;
import com.buit.cis.dctwork.response.NisTj02BackDetailResp;
import com.buit.cis.dctwork.response.NisTj02DrugInfoResp;
import com.buit.cis.dctwork.response.NisTj02HzInfoResp;
import com.buit.cis.dctwork.response.NisTj02Resp;
import com.buit.commons.EntityDao;

/**
 * 病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisTj02Dao extends EntityDao<NisTj02,Integer>{
    
	/**
	* 查询病区药品提交库存 
	* @Title: queryBqtj02Stock
	* @Description: TODO 
	* @param @param parameters
	* @param @return    设定文件
	* @return List<Map<String,Object>>    返回类型
	* @author 龚方舟
	* @throws
	 */
	List<Map<String, Object>> queryBqtj02Stock(Map<String, Object> parameters);
	
	/** 
	* 更新提交02的确认状态
	* @Title: updateSubmit02Status
	* @Description: TODO 
	* @param @param nisTj02    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	* */
    void updateSubmit02Status(NisTj02 nisTj02);

	/**
	 * 查询医嘱发药明细
	 * @param cond
	 * @return
	 * @throws
	 * */
//    List<NisTj02DetailDto> queryFyDetailByTjxh(@Param("cond")QueryConditionDto cond);

	/**
	 * 根据jlxh查询病区提交记录
	 * @param jlxhList
	 * @return
	 * @throws
	 * */
    List<NisTj02> queryByJlxh(@Param("list") List<Integer> jlxhList);

	/**
	 * 根据yzxh查询未全退的yzxh
	 *
	 * @param yzxhList
	 * @return
	 * */
    List<Integer> queryByFybzEqZeroByYzxh(@Param("list") List<Integer> yzxhList);

	/**
	 * 根据tjxh查询未全退的tjxh
	 *
	 * @param tjxhList
	 * @return
	 * */
	List<Integer> queryByFybzEqZeroByTjxh(@Param("list") List<Integer> tjxhList);

	/**
	 * 根据tjxh查询yzxh
	 *
	 * @param tjxhList
	 * @return
	 * */
    List<Integer> queryYzxhByTjxh(@Param("list") List<Integer> tjxhList);

	/**
	 * 联结NIS_TJ01根据jgid、yfsb删除NIS_TJ02
	 *
	 * @param jgid
	 * @param yfsb
	 * @return
	 * */
    void deleteByJgidAndTjyf(@Param("jgid") Integer jgid, @Param("yfsb") Integer yfsb);
    
    /**
     * 查询药品医嘱退回
     * @Title: queryDrugReturn
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisTj02Req
     * @param @return    设定文件
     * @return List<NisTj02Resp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTj02Resp> queryDrugReturn(NisTj02Req nisTj02Req);
    
    /**
     * 查询退药申请明细
     * @Title: queryTurningBackDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param yzxh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisTj02BackDetailResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTj02BackDetailResp> queryTurningBackDetail(@Param("yzxh") Integer yzxh, @Param("jgid") Integer jgid, 
    		@Param("type") Integer type, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
    
    

    /**
     * 查询病区药品提交库存
     * @Title: queryBqtj02Inventory
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryBqtj02Inventory(Map<String, Object> parameters);
    
    /**
     * 查询可退回病区药品病人信息
     * @Title: queryBqDrugReturnHzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @return    设定文件
     * @return List<NisTj02HzInfoResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTj02HzInfoResp> queryBqDrugReturnHzInfo(NisTj02HzInfoReq req);
    
    
    /**
     * 查询可退回病区药品信息
     * @Title: queryBqDrugReturnInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param zyhList
     * @param @return    设定文件
     * @return List<NisTj02DrugInfoResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisTj02DrugInfoResp> queryBqDrugReturnInfo(@Param("jgid") Integer jgid, @Param("zyhList") List<Integer> zyhList);
    

    /**
     * 根据提交序号查询退药明细数量
     * @Title: queryReturnDetailByTjxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param tjxhList
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryReturnDetailByTjxh(@Param("jgid") Integer jgid, @Param("tjxhList") List<Integer> tjxhList);
    

    /**
     * 根据记录序号更新tj02发药标志
     * @Title: updateTj02FybzByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jlxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateTj02FybzByJlxh(@Param("thgh") Integer thgh, @Param("jgid") Integer jgid, 
    		@Param("jlxhList") List<Integer> jlxhList);
    
    /**
     * 查询发药一次数量
     * @Title: queryOneTimeQuantity
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param yzxh
     * @param @param beginDate
     * @param @param endDate
     * @param @return    设定文件
     * @return BigDecimal    返回类型
     * @author 龚方舟
     * @throws
     */
    BigDecimal queryOneTimeQuantity(@Param("yzxh") Integer yzxh, @Param("beginDate") String beginDate, 
    		@Param("endDate") String endDate);
}
