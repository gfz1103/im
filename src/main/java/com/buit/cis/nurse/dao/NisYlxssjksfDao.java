package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.nurse.model.NisYlxssjksf;
import com.buit.cis.nurse.response.NisYlxssjksfResp;
import com.buit.commons.EntityDao;

/**
 * 压力性损伤预报、传报单(监控随访)<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisYlxssjksfDao extends EntityDao<NisYlxssjksf,Integer>{
    
	/**
	 * 根据传报单记录序号查询监控随访
	 * @Title: queryJksfByCbdjlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cbdjlxh
	 * @param @return    设定文件
	 * @return List<NisYlxssjksfResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<NisYlxssjksfResp> queryJksfByCbdjlxh(Integer cbdjlxh);
}
