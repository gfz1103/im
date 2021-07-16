package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.buit.system.utill.ObjectToTypes;
import com.buit.aop.lock.Locked;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.dao.NisTj01Dao;
import com.buit.cis.dctwork.dao.NisTj02Dao;
import com.buit.cis.dctwork.model.NisTj02;
import com.buit.cis.dctwork.request.NisTj02ReturnConfirmReq;
import com.buit.common.drug.dto.PubPharUnfreezeDto;
import com.buit.common.drug.service.impl.PubDrugKcdjSerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTj02Ser extends BaseManagerImp<NisTj02,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTj02Ser.class);
    
    @Autowired
    private NisTj02Dao nisTj02Dao;
    
    @Override
    public NisTj02Dao getEntityMapper(){        
        return nisTj02Dao;
    }
    
    @Autowired
    private NisTj01Dao nisTj01Dao;
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @Autowired
    private PubDrugKcdjSerImpl pubDrugKcdjSerImpl;
    
    @Autowired
    private CisHzyzZtDao cisHzyzZtDao;


    /**
     * 
     * @Title: doReturnConfirm
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	@Locked(value = {"im.doReturnConfirm_#[*list.jlxh]"})
	public void doReturnConfirm(List<NisTj02ReturnConfirmReq> list, SysUser user) {
		List<Integer> tjxhList = list.stream().map(NisTj02ReturnConfirmReq::getTjxh).
				distinct().collect(Collectors.toList());
		List<Integer> yzxhList = list.stream().map(NisTj02ReturnConfirmReq::getYzxh).
				distinct().collect(Collectors.toList());
		
		List<Integer> ztjlxhList = list.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.getYplx())) 
				&& "1".equals(ObjectToTypes.parseString(o.getZtbz()))).
		map(NisTj02ReturnConfirmReq::getZtyzjlxh).distinct().collect(Collectors.toList());
		
		long count = nisTj02Dao.queryReturnDetailByTjxh(user.getHospitalId(), tjxhList);
		//退回的数量等于未发药数量,更新tj01fybz
		if(count == list.size()) {
			nisTj01Dao.updateFybzByTjxh(user.getHospitalId(), tjxhList);
		}
		//更新tj02fybz
		nisTj02Dao.updateTj02FybzByJlxh(user.getUserId(), user.getHospitalId(), list.stream().
				map(NisTj02ReturnConfirmReq::getJlxh).collect(Collectors.toList()));
		//更新医嘱使用标志
		cisHzyzDao.updateSybzByJlxh(user.getHospitalId(), yzxhList);

		//库存解冻
		List<PubPharUnfreezeDto> unFreeList = new ArrayList<PubPharUnfreezeDto>();
		List<Integer> jlxhList = list.stream().filter(o -> o.getLsyz() == 0).
				map(NisTj02ReturnConfirmReq::getJlxh).collect(Collectors.toList());
		for(Integer item : jlxhList) {
			PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
			unfreezeDto.setJgid(user.getHospitalId());
			unfreezeDto.setJlxh(item);
			unfreezeDto.setYwlb(2);
			unFreeList.add(unfreezeDto);
		}	
		pubDrugKcdjSerImpl.unfreeze(unFreeList);

		if(!ztjlxhList.isEmpty()) {
			cisHzyzZtDao.updateZtUseLogoStatus(0, ztjlxhList);
		}

	}
    
}
