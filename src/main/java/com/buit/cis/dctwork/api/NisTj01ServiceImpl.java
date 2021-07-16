package com.buit.cis.dctwork.api;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.dao.NisTj01Dao;
import com.buit.cis.dctwork.response.QueryDispensingWardApiResp;
import com.buit.cis.dctwork.response.QuerySubmitApplyApiResp;
import com.buit.cis.dctwork.service.NisTj01Service;
import com.buit.his.pha.commons.dto.QueryConditionDto;

/**
 * 医嘱提交01外部接口实现
 * @ClassName: NisTj01ServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月12日 下午4:39:11
 *
 */
@DubboService(timeout = 10000)
public class NisTj01ServiceImpl implements NisTj01Service{
	
	@Autowired
	private NisTj01Dao nisTj01Dao;

	/**
	 * 病区待发药记录查询
	 * @Title: queryByCond
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param condApi
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<QueryDispensingWardApiResp> queryByCond(QueryConditionDto cond) {
		return nisTj01Dao.queryByCond(cond);
	}

	/**
	 * 联结NIS_TJ02表根据zyh查询tjxh
	 * @Title: queryTjxhByZyh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyhList
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<Integer> queryTjxhByZyh(List<Integer> zyhList) {
		return nisTj01Dao.queryTjxhByZyh(zyhList);
	}

	/**
	 * 查询待发药提交单
	 * @Title: queryDispensingTjd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cond
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<QuerySubmitApplyApiResp> queryDispensingTjd(QueryConditionDto cond) {
		return nisTj01Dao.queryDispensingTjd(cond);
	}

}
