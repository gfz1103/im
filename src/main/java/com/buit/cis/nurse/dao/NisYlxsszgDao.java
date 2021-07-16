package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisYlxsszg;
import com.buit.cis.nurse.response.NisYlxsszgResp;
import com.buit.commons.EntityDao;

/**
 * 压力性损伤预报、传报单(转归情况)<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisYlxsszgDao extends EntityDao<NisYlxsszg,Integer>{
    
	/**
	 * 根据传报单记录序号查询转归情况
	 * @Title: queryZgByCbdjlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cbdjlxh
	 * @param @return    设定文件
	 * @return List<NisYlxsszgResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<NisYlxsszgResp> queryZgByCbdjlxh(Integer cbdjlxh);
	
	/**
	 * 根据传报单记录序号查询转归情况
	 * @Title: queryPrintZgByCbdjlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cbdjlxh
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<Map<String, Object>> queryPrintZgByCbdjlxh(Integer cbdjlxh);
}
