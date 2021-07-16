package com.buit.cis.dctwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.buit.cis.dctwork.dao.CisYzmessDao;
import com.buit.cis.dctwork.dto.CisHzyzMessDto;
import com.buit.cis.dctwork.model.CisYzmess;
import com.buit.enums.MessageStatusEnum;
import com.buit.mq.NotifyConsumer;

import cn.hutool.json.JSONUtil;

@Service
public class CisMessImpl implements NotifyConsumer<CisHzyzMessDto>{

	static final Logger logger = LoggerFactory.getLogger(CisMessImpl.class);
	
	@Autowired
	private CisYzmessDao cisYzmessDao;

	@Override
	public void failedCallBack(CisHzyzMessDto data, Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(CisHzyzMessDto data) {
		logger.info("CisHzyzMessDto[]{}" + JSONUtil.toJsonStr(data));
		if(data != null && MessageStatusEnum.close.equals(data.getStatus())) {
			cisYzmessDao.updateZtByJlxh(data.getJlxh(), 2, data.getUserId(), 1);
		}
	}

}
