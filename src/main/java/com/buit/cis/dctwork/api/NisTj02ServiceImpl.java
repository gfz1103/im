package com.buit.cis.dctwork.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.dao.NisTj02Dao;
import com.buit.cis.dctwork.model.NisTj02;
import com.buit.cis.dctwork.response.NisTj02Model;
import com.buit.cis.dctwork.service.NisTj02Service;
import com.buit.utill.BeanUtil;

/**
 * 医嘱提交02外部接口实现
 * @ClassName: NisTj02ServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月12日 下午4:39:38
 *
 */
@DubboService(timeout = 10000)
public class NisTj02ServiceImpl implements NisTj02Service{

	@Autowired
	private NisTj02Dao nisTj02Dao;
	
	/**
	 * 根据jlxh查询病区提交记录
	 * @Title: queryByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxhList
	 * @param @return    设定文件
	 * @return List<NisTj02Model>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<NisTj02Model> queryByJlxh(List<Integer> jlxhList) {
		List<NisTj02Model> resList = new ArrayList<NisTj02Model>();
		List<NisTj02> list = nisTj02Dao.queryByJlxh(jlxhList);
		for(NisTj02 nisTj02 : list) {
			NisTj02Model model = BeanUtil.toBean(nisTj02, NisTj02Model.class);
			resList.add(model);
		}
		return resList;
	}

	/**
	 * 根据yzxh查询未全退的yzxh
	 * @Title: queryByFybzEqZeroByYzxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzxhList
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<Integer> queryByFybzEqZeroByYzxh(List<Integer> yzxhList) {
		return nisTj02Dao.queryByFybzEqZeroByYzxh(yzxhList);
	}

	
	/**
	 * 根据tjxh查询未全退的tjxh
	 * @Title: queryByFybzEqZeroByTjxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tjxhList
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<Integer> queryByFybzEqZeroByTjxh(List<Integer> tjxhList) {
		return nisTj02Dao.queryByFybzEqZeroByTjxh(tjxhList);
	}

	
	/**
	 * 根据tjxh查询yzxh
	 * @Title: queryYzxhByTjxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tjxhList
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<Integer> queryYzxhByTjxh(List<Integer> tjxhList) {
		return nisTj02Dao.queryYzxhByTjxh(tjxhList);
	}

}
