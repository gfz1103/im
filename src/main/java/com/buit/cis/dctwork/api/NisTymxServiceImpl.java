package com.buit.cis.dctwork.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.dao.NisTymxDao;
import com.buit.cis.dctwork.model.NisTymx;
import com.buit.cis.dctwork.response.NisTymxModel;
import com.buit.cis.dctwork.response.QueryBackMedicineWardApiResp;
import com.buit.cis.dctwork.service.NisTymxService;
import com.buit.his.pha.commons.dto.QueryConditionDto;
import com.buit.utill.BeanUtil;

/**
 * 退药明细外部接口实现
 * @ClassName: NisTymxServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月12日 下午8:23:46
 *
 */
@DubboService(timeout = 10000)
public class NisTymxServiceImpl implements NisTymxService{

	@Autowired
	private NisTymxDao nisTymxDao;
	
	/**
	 * 查询待退药记录
	 * @Title: queryByCond
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cond
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<QueryBackMedicineWardApiResp> queryByCond(QueryConditionDto cond) {
		return nisTymxDao.queryByCond(cond);
	}

	/**
	 * 查询待退药记录
	 * @Title: queryByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxhList
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<NisTymxModel> queryByJlxh(List<Integer> jlxhList) {
		List<NisTymx> list = nisTymxDao.queryByJlxh(jlxhList);
		List<NisTymxModel> resList = new ArrayList<NisTymxModel>();
		for(NisTymx nisTymx : list) {
			NisTymxModel model = BeanUtil.toBean(nisTymx, NisTymxModel.class);
			resList.add(model);
		}
		return resList;
	}


	

}
