package com.buit.cis.dctwork.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.aop.lock.Locked;
import com.buit.cis.dctwork.dao.CisYzmessDao;
import com.buit.cis.dctwork.model.CisYzmess;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.dto.TargetConfig;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.SystemTypeEnum;
import com.buit.mq.RabbitMqProducer;
import com.buit.system.model.SysYwqxkz;
import com.buit.system.service.SysYwqxkzService;
/**
 * 医嘱变动消息发送<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisYzmessSer extends BaseManagerImp<CisYzmess,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisYzmessSer.class);
    
    @Autowired
    private CisYzmessDao cisYzmessDao;
    
    @Override
    public CisYzmessDao getEntityMapper(){        
        return cisYzmessDao;
    }
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private RabbitMqProducer rabbit;
    
    @DubboReference
    private SysYwqxkzService sysYwqxkzService;
    
    /**
     * 更新医嘱消息状态
     * @Title: updateZt
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Locked(value = {"updateZt_#[jlxh]"})
    @Transactional(rollbackFor = Exception.class)
	public void updateZt(Integer jlxh, SysUser user) {
    	CisYzmess cisYzmess = cisYzmessDao.getById(jlxh);
    	if(cisYzmess != null && cisYzmess.getZt() != 0) {
    		throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00049");
    	}
    	cisYzmessDao.updateZtByJlxh(jlxh, 1, user.getUserId(), 0);
    	ImHzry imHzry = imHzryDao.getById(cisYzmess.getZyh());
    	List<Integer> userIdList = new ArrayList<Integer>();
		SysYwqxkz sysYwqxkz = new SysYwqxkz();
		sysYwqxkz.setKsdm(imHzry.getBrbq());
		sysYwqxkz.setYwlb(1);
		sysYwqxkz.setJgid(user.getHospitalId());
		List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
		for(SysYwqxkz qxkz : qxkzList) {
			if(!user.getUserId().equals(qxkz.getYgdm())) {			
				userIdList.add(qxkz.getYgdm());
			}
		}
		rabbit.cancelAlertMsg(cisYzmess.getMessid(), TargetConfig.toSystemUser(SystemTypeEnum.zyhsz, 
				userIdList, CoverageTypeEnum.onlineAndOffline));
	}
    
}
