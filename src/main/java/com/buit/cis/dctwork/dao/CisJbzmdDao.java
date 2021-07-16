package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.CisJbzmd;
import com.buit.cis.dctwork.request.CisJbzmdQueryReq;
import com.buit.cis.dctwork.response.CisJbzmdResp;
/**
 * 疾病证明单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisJbzmdDao extends EntityDao<CisJbzmd,Integer>{
    
	/**
	 * 疾病证明单分页查询
	 * @Title: queryPageCisJbzmd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return List<CisJbzmdResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<CisJbzmdResp> queryPageCisJbzmd(CisJbzmdQueryReq cisjbzmd);
	
	
	/**
	 * 查询疾病证明单打印
	 * @Title: queryCisJbzmdPrint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	Map<String, Object> queryCisJbzmdPrint(Integer jlxh);
}
