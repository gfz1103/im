package com.buit.cis.dctwork.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dto.PharFyjlMessDto;
import com.buit.cis.dctwork.response.CisHzyzFyjlResp;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.nurse.service.NurseworkSer;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.mq.HandlerConsumer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.RedisFactory;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;

@Service
public class CisAdditionalItemsService implements HandlerConsumer<PharFyjlMessDto>{
	
	static final Logger logger = LoggerFactory.getLogger(CisAdditionalItemsService.class);
	
	@Autowired
	private CisHzyzDao cisHzyzDao;
	
	@Autowired
	private NurseworkSer nurseworkSer;
	
	@Autowired
	private CisHzyzSer cisHzyzSer;
	
	@Autowired
	private ImFeeFymxDao imFeeFymxDao;
	
	@Autowired
	private RedisFactory redisFactory;
	

	@Override
	public void failedCallBack(PharFyjlMessDto data, Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void handler(PharFyjlMessDto data) throws Exception {
		List<CisHzyzFyjlResp> fyjlList = cisHzyzDao.queryFyjlFymx(data.getJlid());
		if(!fyjlList.isEmpty()) {
			logger.info("fyjlList[]{}" + JSONUtil.toJsonStr(fyjlList));
			List<Map<String, Object>> list = cisHzyzDao.queryAdditionalItemsFymx(fyjlList.stream().
					map(CisHzyzFyjlResp::getYzzh).collect(Collectors.toList()), fyjlList.get(0).getJgid());
			logger.info("fymxList[]{}" + JSONUtil.toJsonStr(list));
			if(!list.isEmpty()) {
				SysUser user = new SysUser();
				user.setHospitalId(fyjlList.get(0).getJgid());
				Map<String, Object> jlMap = new HashMap<String, Object>();
				nurseworkSer.calculateOrderExecution(cisHzyzSer.frequencyList(), list, user);
				for(int i = 0; i < list.size(); i++) {
					BigDecimal fysl = new BigDecimal(0);
					Map<String, Object> map = list.get(i);
					double fycs = ObjectToTypes.parseDouble(map.get("FYCS"));
					Integer jffs = ObjectToTypes.parseInt(map.get("JFFS"));
					Integer zyh = ObjectToTypes.parseInt(map.get("ZYH"));
					Integer fyxh = map.get("FYXH") == null ? ObjectToTypes.parseInt(map.get("YPXH")) : 
						ObjectToTypes.parseInt(map.get("FYXH"));
					BigDecimal jfsl = ObjectToTypes.parseBigDecimal(map.get("JFSL"));	
					String fyrq = DateUtil.format(DateUtil.tomorrow(), DatePattern.NORM_DATE_PATTERN);
					if("1".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
						fyrq = DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN);
					}
					if(fycs > 0) {
						Map<String, Object> updateMap = new HashMap<String, Object>();
						updateMap.put("JLXH", ObjectToTypes.parseInt(map.get("JLXH")));
						updateMap.put("QRSJ", DateUtil.parse(map.get("QRSJ") + "").toTimestamp());
		                cisHzyzDao.updateTimeAndLsByJlxh(updateMap);
						if(jlMap.containsKey(String.format("%s-%s-%s", zyh, fyxh, fyrq))) {
							continue;
						}
						fysl = ObjectToTypes.parseBigDecimal(map.get("YCSL")).multiply(new BigDecimal(fycs));
						if(jffs != null && (jffs == 2 || jffs == 3)) {
							jlMap.put(String.format("%s-%s-%s", zyh, fyxh, fyrq), map);
							fysl = imFeeFymxDao.queryAdditionalItemsNowDayNum(zyh, fyxh, fyrq);
							fysl = jfsl.subtract(fysl);
						}
						if(fysl.compareTo(BigDecimal.ZERO) > 0) {
							Integer fyxm = cisHzyzSer.getfygb(0, fyxh);
							Map<String, Object> fyxx = nurseworkSer.getPriceJe(0, 
									ObjectToTypes.parseInt(map.get("BRXZ")), fyxh, fyxm, 
									ObjectToTypes.parseBigDecimal(map.get("YPDJ")).doubleValue(), fysl.doubleValue());
							ImFeeFymx imFeeFymx = new ImFeeFymx();
							imFeeFymx.setJgid(user.getHospitalId());
							imFeeFymx.setZyh(zyh);
							if("1".equals(ObjectToTypes.parseString(map.get("LSYZ")))) {
								imFeeFymx.setFyrq(DateUtil.date().toTimestamp());
							}else {
								imFeeFymx.setFyrq(DateUtil.tomorrow().toTimestamp());
							}
							imFeeFymx.setFyxh(fyxh);
							imFeeFymx.setFymc(ObjectToTypes.parseString(map.get("YZMC")));
							imFeeFymx.setYpcd(0);
							imFeeFymx.setFysl(fysl);
							imFeeFymx.setFydj(ObjectToTypes.parseBigDecimal(map.get("YPDJ")));
							imFeeFymx.setZjje(ObjectToTypes.parseBigDecimal(fyxx.get("ZJJE")));
							imFeeFymx.setZfje(ObjectToTypes.parseBigDecimal(fyxx.get("ZFJE")));
							imFeeFymx.setYsgh(ObjectToTypes.parseString(map.get("YSGH")));
							imFeeFymx.setFybq(ObjectToTypes.parseInt(map.get("BRBQ")));
							imFeeFymx.setFyks(ObjectToTypes.parseInt(map.get("BRKS")));
							imFeeFymx.setZxks(ObjectToTypes.parseInt(map.get("ZXKS")));
							imFeeFymx.setJfrq(DateUtil.date().toTimestamp());
							imFeeFymx.setXmlx(1);
							imFeeFymx.setYplx(0);
							imFeeFymx.setFyxm(fyxm);
							imFeeFymx.setJscs(0);
							imFeeFymx.setZfbl(ObjectToTypes.parseBigDecimal(fyxx.get("ZFBL")));
							imFeeFymx.setYzxh(ObjectToTypes.parseInt(map.get("JLXH")));
							imFeeFymx.setZlje(ObjectToTypes.parseBigDecimal(fyxx.get("ZLJE")));
							imFeeFymx.setYepb(0);
							imFeeFymx.setDzbl(new BigDecimal(0));
							imFeeFymx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX));
							imFeeFymxDao.insert(imFeeFymx);
						}
					}
				}
			}
		}
	}

}
