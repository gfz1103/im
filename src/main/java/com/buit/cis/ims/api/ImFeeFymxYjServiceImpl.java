package com.buit.cis.ims.api;

import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.im.response.ImFeeFymxYjModel;
import com.buit.cis.im.service.ImFeeFymxYjService;
import com.buit.cis.ims.dao.ImFeeFymxYjDao;
import com.buit.cis.ims.model.ImFeeFymxYj;
import com.buit.utill.BeanUtil;

/**
 * 费用明细医技外部接口实现
 * @ClassName: ImFeeFymxYjServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月13日 下午2:21:36
 *
 */

@DubboService(timeout = 10000)
public class ImFeeFymxYjServiceImpl implements ImFeeFymxYjService{
	
	@Autowired
	private ImFeeFymxYjDao imFeeFymxYjDao;
	
	/**
	 * 根据医嘱序号查询医技信息
	 * @Title: doQueryByYzxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzxh
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public ImFeeFymxYjModel doQueryByYzxh(Integer yzxh) {
		ImFeeFymxYj imFeeFymxYj = imFeeFymxYjDao.doQueryByYzxh(yzxh);
		return BeanUtil.toBean(imFeeFymxYj, ImFeeFymxYjModel.class);
	}

	/**
	 * 根据医嘱序号更新执行状态和执行次数
	 * @Title: updateZxztAndZxcs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void updateZxztAndZxcs(Map<String, Object> map) {
		imFeeFymxYjDao.updateZxztAndZxcs(map);
	}


}
