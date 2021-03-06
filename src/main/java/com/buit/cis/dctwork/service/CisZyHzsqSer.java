package com.buit.cis.dctwork.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisZyHzsqDao;
import com.buit.cis.dctwork.dao.CisZyHzyjDao;
import com.buit.cis.dctwork.dao.CisZyHzyqdxDao;
import com.buit.cis.dctwork.dto.CisZyHzsqMessDto;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisZyHzsq;
import com.buit.cis.dctwork.model.CisZyHzyj;
import com.buit.cis.dctwork.model.CisZyHzyqdx;
import com.buit.cis.dctwork.request.CisZyHzsqReq;
import com.buit.cis.dctwork.response.CisZyHzsqAndYjResp;
import com.buit.cis.dctwork.response.CisZyHzsqResp;
import com.buit.cis.dctwork.response.CisZyHzyjResp;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImRyzdDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.ImRyzdReq;
import com.buit.cis.ims.response.ImRyzdResp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.dto.LineContentDto;
import com.buit.dto.TargetConfig;
import com.buit.dto.WaitProcessMessage;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.MessageLevelType;
import com.buit.enums.SystemTypeEnum;
import com.buit.mq.RabbitMqProducer;
import com.buit.system.model.SysYwqxkz;
import com.buit.system.response.DicKszdModel;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.SysMessSer;
import com.buit.system.service.SysYwqxkzService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class CisZyHzsqSer extends BaseManagerImp<CisZyHzsq,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzsqSer.class);
    
    @Autowired
    private CisZyHzsqDao cisZyHzsqDao;
    
    @Override
    public CisZyHzsqDao getEntityMapper(){        
        return cisZyHzsqDao;
    }
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    
    @Autowired
	private RedisFactory redisFactory;
    
    @Autowired
    private CisZyHzyqdxDao cisZyHzyqdxDao;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    @Autowired
    private CisZyHzyjDao cisZyHzyjDao;
    
    @Autowired
    private ImRyzdDao imRyzdDao;
    
    @DubboReference
    private SysMessSer sysMessSer;
    
    @DubboReference
    private SysYwqxkzService sysYwqxkzService;
    
    @Autowired
    private RabbitMqProducer rabbit; 

    
    /**
    * @Title: doGetHzList
    * @Description: TODO ??????????????????????????????
    * @param @param zyh
    * @param @param hzbr
    * @param @param alias
    * @param @param user
    * @param @return    ????????????
    * @return List<CisZyHzsqResp>    ????????????
    * @author ?????????
    * @throws
     */
    public PageInfo<CisZyHzsqResp> doGetHzList(Integer zyh, String alias, Integer ksdm, SysUser user, PageQuery page) {
		Integer userId = user.getUserId();
		CisZyHzsqReq cisZyHzsqReq = new CisZyHzsqReq();
		cisZyHzsqReq.setJzhm(ObjectToTypes.parseString(zyh));
		PageInfo<CisZyHzsqResp> pageInfo = PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> cisZyHzsqDao.queryHzSqByUseId(cisZyHzsqReq));
		if ("-1".equals(alias)) {
			cisZyHzsqReq.setTxry(ObjectToTypes.parseString(userId));
			pageInfo = PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> cisZyHzsqDao.queryHzSqByUseId(cisZyHzsqReq));
			
		} else if ("2".equals(alias)) {
			//?????????????????????????????????????????????????????? ??????????????????????????????????????????????????????
			HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(userId);
			cisZyHzsqReq.setYqdx(ObjectToTypes.parseString(userId));
			cisZyHzsqReq.setNyqhzks(ObjectToTypes.parseString(hrPersonnel.getOfficecode()));
			pageInfo = PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                () -> cisZyHzsqDao.queryHzSqByKs(cisZyHzsqReq));
		}
		if(CollectionUtils.isNotEmpty(pageInfo.getList())){
			for (int i = 0; i < pageInfo.getList().size(); i++) {				
				 CisZyHzsqResp cisZyHzsqResp = pageInfo.getList().get(i);
				 if("1".equals(cisZyHzsqResp.getNhzyy())){
					 boolean sftxFlag = false;				 
					 if(StrUtil.isNotBlank(cisZyHzsqResp.getNyqhzys3())){
						 String[] strArray = cisZyHzsqResp.getNyqhzys3().split(",");
						 List<String> listString = new ArrayList<String>(Arrays.asList(cisZyHzsqResp.getNyqhzks3().split(",")));
						 StringBuffer personNameBuffer = new StringBuffer(); 
						 for(String str : strArray) {
							 HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(ObjectToTypes.parseInt(str));
							 if(hrPersonnel != null){
								 personNameBuffer.append(hrPersonnel.getPersonname()).append(",");	
								 listString.remove(ObjectToTypes.parseString(hrPersonnel.getOfficecode()));
							 }
							 
							 if(str.equals(ObjectToTypes.parseString(userId))) {
								 sftxFlag = true;
							 }
						 }
						 
						 for(String strKs : listString) {
							 if(strKs.equals(ObjectToTypes.parseString(ksdm))) {			 
								 sftxFlag = true;
							 } 
						 }
						 if(personNameBuffer.length() > 0) {							 
							 personNameBuffer.deleteCharAt(personNameBuffer.length() - 1);
							 cisZyHzsqResp.setNyqhzys3(personNameBuffer.toString());				  
						 }
					 }else {
						 List<String> listString = new ArrayList<String>(Arrays.asList(cisZyHzsqResp.getNyqhzks3().split(",")));
						 for(String strKs : listString) {
							 if(strKs.equals(ObjectToTypes.parseString(ksdm))) {			 
								 sftxFlag = true;
							 } 
						 }
					 }	
					 
					 if(ObjectToTypes.parseString(userId).equals(cisZyHzsqResp.getSqys())) {
						 sftxFlag = false;
					 }
					 if(sftxFlag) {
						 cisZyHzsqResp.setSftx(1);
					 }else {
						 cisZyHzsqResp.setSftx(0);
					 }
					 
					 if(StrUtil.isNotBlank(cisZyHzsqResp.getNyqhzks3())){
						 String[] strArray = cisZyHzsqResp.getNyqhzks3().split(",");
						 StringBuffer officeNameBuffer = new StringBuffer();
						 for(String str : strArray) {
							 String officeName = dicKszdOutSer.getNameById(ObjectToTypes.parseInt(str));			
							 if(StrUtil.isNotBlank(officeName)){
								 officeNameBuffer.append(officeName).append(",");							 
							 }
						 }
	
						 officeNameBuffer.deleteCharAt(officeNameBuffer.length() - 1);
						 cisZyHzsqResp.setNyqhzks3(officeNameBuffer.toString());
						 
					 }
					 
				 }		
				 
				 cisZyHzsqResp.setJgName(user.getHospitalName());
			}			
		}	
		return pageInfo;
	}
    
    
   /**
   * @Title: doSaveorupdate
   * @Description: TODO ????????????????????????
   * @param @param cisZyHzsqReq
   * @param @param user
   * @param @return    ????????????
   * @return Integer    ????????????
   * @author ?????????
   * @throws
    */
	@Transactional(rollbackFor = Exception.class)
	public void doSaveorupdate(CisZyHzsqReq cisZyHzsqReq, SysUser user) {
        Map<String, Object> body = BeanUtil.beanToMap(cisZyHzsqReq);
        //???????????????????????????????????????
        Date hzsj = BUHISUtil.toDate(body.get("hzsj").toString());
        Date ryrq = BUHISUtil.toDate(body.get("ryrq").toString());
        int flag = BUHISUtil.dateCompare(hzsj, ryrq);
        if (flag < 0) {
            throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_00012");
        } else {
            //??????
            Map<String, Object> map = saveCisZyHzsq(body, user);
            //?????????????????????????????????????????????????????????????????????
            Map<String, Object> map2 = new HashMap<String, Object>(16);
            map2.put("SQXH", map.get("SQXH").toString());
            if (StrUtil.isNotBlank(ObjectToTypes.parseString(body.get("yqdx")))) {
                map2.put("YQDX", body.get("yqdx") + "");
			}else{
				map2.put("YQDX", -1);
			}
			map2.put("NYQHZKS", body.get("nyqhzks")+"");
			map2.put("NHZYY", body.get("nhzyy"));
			saveCisZyHzyqdx(map2, user);
			//????????????????????????
			body.put("SQXH", map.get("SQXH").toString());
			saveHzYz(body, user);
		}
	}
	
	/**
	* @Title: saveCisZyHzsq
	* @Description: TODO ?????????????????????
	* @param @param body
	* @param @param user
	* @param @return    ????????????
	* @return Map<String,Object>    ????????????
	* @author ?????????
	* @throws
	 */
	public Map<String, Object> saveCisZyHzsq(Map<String, Object> body, SysUser user){
		//???????????????ID
		Map<String, Object> cisZyHzsqMap = new HashMap<String, Object>(16);
		Integer jgid = user.getHospitalId();
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("JZHM", body.get("zyh"));// ?????????
		map.put("SQKS", body.get("sqks"));// ????????????
		map.put("SQYS", body.get("sqys"));// ????????????
		map.put("SQSJ", body.get("sqsj"));// ????????????
		map.put("HZMD", body.get("hzmd"));
		map.put("HZSJ", DateUtil.date(DateUtil.parse(body.get("hzsj")+"")).toTimestamp());	
		map.put("YQDX", body.get("yqdx"));
		map.put("NYQHZKS", body.get("nyqhzks"));
		map.put("NYQHZYS2", body.get("nyqhzys2"));
		map.put("NYQHZKS2", body.get("nyqhzks2"));
		map.put("NYQHZYY", body.get("nyqhzyy"));
		map.put("KZRYS", body.get("kzrys"));
		map.put("NHZYY", body.get("nhzyy"));
		//????????????
		if (StrUtil.isBlank(body.get("jjbz") + "")){
			map.put("JJBZ", 0);
		} else {
			map.put("JJBZ", body.get("jjbz"));
		}
		map.put("JSBZ", 0);// ????????????
		map.put("TJBZ", 0);// ????????????
		map.put("ZFBZ", 0);// ????????????
		map.put("TXRY", user.getUserId());// ????????????
		map.put("BQZL", body.get("bqzl"));// ????????????
		map.put("JGID", jgid);// ??????ID
		CisZyHzsq cisZyHzsq = BeanUtil.fillBeanWithMapIgnoreCase(map, new CisZyHzsq(), true);
		try {
			Integer sqxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_ZY_HZSQ);
			cisZyHzsqMap.put("SQXH", sqxh);
			cisZyHzsq.setSqxh(sqxh);
			cisZyHzsqDao.insert(cisZyHzsq);
			return cisZyHzsqMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0001");
		}
	}
	
	/**
	* @Title: saveCisZyHzyqdx
	* @Description: TODO ??????????????????
	* @param @param body
	* @param @param user    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	public void saveCisZyHzyqdx(Map<String, Object> body, SysUser user){
		Integer jgid = user.getHospitalId();
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("SQXH", Long.parseLong(body.get("SQXH").toString()));//????????????
		map.put("DXLX", 2);//????????????
		map.put("QRBZ",0);//????????????
		map.put("JGID",jgid);//??????ID
		CisZyHzyqdx cisZyHzyqdx = BeanUtil.fillBeanWithMapIgnoreCase(map, new CisZyHzyqdx(), true);	
		if("1".equals(body.get("NHZYY") + "")) {
			String[] strArray = ObjectToTypes.parseString(body.get("YQDX")).split(",");
			List<String> listString = Arrays.asList(ObjectToTypes.parseString(body.get("NYQHZKS")).split(","));
			List<String> list = new ArrayList<String>(listString);
			//?????????????????????????????????
			if(!"-1".equals(body.get("YQDX") + "")) {
				for(String yqdx : strArray) {		
					HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(ObjectToTypes.parseInt(yqdx));
					cisZyHzyqdx.setNyqhzks(ObjectToTypes.parseString(hrPersonnel.getOfficecode()));
					//?????????????????????????????????
					list.remove(ObjectToTypes.parseString(hrPersonnel.getOfficecode()));
					cisZyHzyqdx.setYqdx(yqdx);
					cisZyHzyqdx.setYqxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_ZY_HZYQDX));
					cisZyHzyqdxDao.insert(cisZyHzyqdx);
				}
			}
			
			//?????????????????????????????????????????????
			for(String nyqhzks : list) {
				cisZyHzyqdx.setYqdx("-1");
				cisZyHzyqdx.setNyqhzks(nyqhzks);
				cisZyHzyqdx.setYqxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_ZY_HZYQDX));
				cisZyHzyqdxDao.insert(cisZyHzyqdx);
			}
		}else {
			cisZyHzyqdx.setYqdx(ObjectToTypes.parseString(body.get("YQDX")));
			cisZyHzyqdx.setYqxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_ZY_HZYQDX));
			cisZyHzyqdxDao.insert(cisZyHzyqdx);
		}
		

	}
	
	/**
	* @Title: saveHzYz
	* @Description: TODO ??????????????????
	* @param @param body
	* @param @param user    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	public void saveHzYz(Map<String, Object> body, SysUser user) {
		//???????????????ID
		Integer jgid = user.getHospitalId();	
		String nyqhzksYz = "";
		if("2".equals(body.get("nhzyy") + "")) {
			nyqhzksYz = ObjectToTypes.parseString(body.get("nyqhzyy")) + ObjectToTypes.parseString(body.get("nyqhzks2"));
		}else {
			List<DicKszdModel> dicKszdList = dicKszdOutSer.getOfficeNameByJgidAndStrId(jgid, 
					ObjectToTypes.parseString(body.get("nyqhzks")));
			for(DicKszdModel office : dicKszdList) {
				nyqhzksYz = nyqhzksYz + office.getOfficename() + ",";
			}
			if(!"".equals(nyqhzksYz)) {
				nyqhzksYz = nyqhzksYz.substring(0,nyqhzksYz.length()- 1);
			}
		}
		try {
			CisHzyz cishzyz = new CisHzyz();
			cishzyz.setZyh(ObjectToTypes.parseInt(body.get("zyh")));
			cishzyz.setBrks(ObjectToTypes.parseInt(body.get("sqks")));
			cishzyz.setBrbq(ObjectToTypes.parseInt(body.get("brbq")));
			cishzyz.setBrch(ObjectToTypes.parseString(body.get("brch")));
			cishzyz.setYpxh(0);
			cishzyz.setYpcd(0);
			cishzyz.setXmlx(10);
			cishzyz.setYplx(0);
			cishzyz.setTzfhbz(0);
			cishzyz.setFhbz(0);
			cishzyz.setYcjl(new BigDecimal(1));
			cishzyz.setYcsl(new BigDecimal(1));
			cishzyz.setLsyz(1);
			cishzyz.setMrcs(1);
			cishzyz.setMzcs(0);
			cishzyz.setJfbz(3);
			cishzyz.setYpyf(0);
			cishzyz.setSrcs(1);
			cishzyz.setYzmc("??????"+ nyqhzksYz + "??????");
			cishzyz.setCzgh(ObjectToTypes.parseString(user.getUserId()));
			cishzyz.setYsgh(ObjectToTypes.parseString(body.get("sqys")));
			cishzyz.setYzzh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));
			cishzyz.setKssj(DateUtil.parse(body.get("sqsj") + "").toTimestamp());
			cishzyz.setYdlb("301");
			cishzyz.setSrks(ObjectToTypes.parseInt(body.get("sqks")));
			cishzyz.setSqid(ObjectToTypes.parseInt(body.get("SQXH")));
			cishzyz.setYwid(ObjectToTypes.parseInt(body.get("SQXH")));
			cishzyz.setZxks(ObjectToTypes.parseInt(body.get("sqks")));
			cishzyz.setJgid(jgid);
			cishzyz.setSfjg(0);
			cishzyz.setYpdj(new BigDecimal(0));
			cishzyz.setSybz(0);
			cishzyz.setYjzx(0);
			cishzyz.setYjxh(0);
			cishzyz.setZfbz(0);
			cishzyz.setZfpb(0);
			cishzyz.setFysx(0);
			cishzyz.setYepb(0);
			cishzyz.setYfsb(0);
			cishzyz.setLsbz(0);
			cishzyz.setYzpb(0);
			cishzyz.setTpn(0);
			cishzyz.setZfyp(0);
			cishzyz.setYsbz(1);
			cishzyz.setYstj(0);
			cishzyz.setPspb(0);
			cishzyz.setSypc("st");
			cishzyz.setYzlx(4);
			cishzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
			cisHzyzDao.insert(cishzyz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0001");
		}
	}
	
	
	/**
	* @Title: updateCisZyHzsq
	* @Description: TODO ????????????????????????
	* @param @param body
	* @param @param user    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCisZyHzsq(CisZyHzsqReq cisZyHzsqReq, SysUser user){
		Integer jgid = user.getHospitalId();
//		CisZyHzsq cisHzsq = BeanUtil.toBean(cisZyHzsqReq, CisZyHzsq.class);
//		cisHzsq.setJzhm(ObjectToTypes.parseString(cisZyHzsqReq.getZyh()));
//		if(StrUtil.isBlankIfStr(cisHzsq.getJjbz())) {
//			cisHzsq.setJjbz(0);
//		}
//		cisHzsq.setJsbz(0);
//		cisHzsq.setTjbz(0);
//		cisHzsq.setZfbz(0);
//		cisHzsq.setTxry(ObjectToTypes.parseString(user.getUserId()));
//		cisHzsq.setJgid(jgid);
		Map<String, Object> body = BeanUtil.beanToMap(cisZyHzsqReq);
		
		Map<String, Object> map = new HashMap<String, Object>(16);
		Map<String, Object> updmap = new HashMap<String, Object>(16);
		map.put("JZHM", body.get("zyh"));// ?????????
		map.put("SQKS", body.get("sqks"));// ????????????
		map.put("SQYS", body.get("sqys"));// ????????????
		map.put("SQSJ", body.get("sqsj"));// ????????????
		map.put("HZMD", body.get("hzmd"));//
		map.put("HZSJ", DateUtil.date(DateUtil.parse(body.get("hzsj")+"")).toTimestamp());
		map.put("YQDX", body.get("yqdx"));
		map.put("NYQHZKS", body.get("nyqhzks"));
		map.put("NYQHZYS2", body.get("nyqhzys2"));
		map.put("NYQHZKS2", body.get("nyqhzks2"));
		map.put("NYQHZYY", body.get("nyqhzyy"));
		map.put("KZRYS", body.get("kzrys"));
		map.put("NHZYY", body.get("nhzyy"));
		//????????????
		if (StrUtil.isBlank(body.get("jjbz") + "")) {
			map.put("JJBZ", 0);
		} else {
			map.put("JJBZ", body.get("jjbz"));

		}
		map.put("JSBZ", 0);// ????????????
		map.put("TJBZ", 0);// ????????????
		map.put("ZFBZ", 0);// ????????????
		map.put("TXRY", user.getUserId());// ????????????
		map.put("BQZL", body.get("bqzl"));// ????????????
		map.put("JGID", jgid);// ??????ID
		map.put("SQXH", body.get("sqxh"));
		String nyqhzksYz = "";
		if("1".equals(ObjectToTypes.parseString(body.get("nhzyy")))) {
			List<DicKszdModel> dicKszdList = dicKszdOutSer.getOfficeNameByJgidAndStrId(jgid, 
					ObjectToTypes.parseString(body.get("nyqhzks")));
			for(DicKszdModel office : dicKszdList) {
				nyqhzksYz = nyqhzksYz + office.getOfficename() + ",";
			}
			if(!"".equals(nyqhzksYz)) {
				nyqhzksYz = nyqhzksYz.substring(0,nyqhzksYz.length()- 1);
			}
		}else {
			nyqhzksYz = ObjectToTypes.parseString(body.get("nyqhzyy")) + ObjectToTypes.parseString(body.get("nyqhzks2"));
		}
		
		updmap.put("YZMC","??????" + nyqhzksYz + "??????");
		updmap.put("KSSJ", DateUtil.parse(body.get("sqsj") + "").toTimestamp());// ????????????
		updmap.put("YWID", Long.parseLong(body.get("sqxh") + ""));//
		updmap.put("ZYH", Long.parseLong(body.get("zyh") + ""));
		try {
			CisZyHzsq cisZyHzsq = BeanUtil.fillBeanWithMapIgnoreCase(map, new CisZyHzsq(), true);
			cisZyHzsqDao.update(cisZyHzsq);
			cisHzyzDao.updateOrdersAndTime(updmap);
			//????????????????????????????????????????????????
			CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx();
			cisZyHzyqdx.setSqxh(ObjectToTypes.parseInt(body.get("sqxh")));
			cisZyHzyqdxDao.removeByEntity(cisZyHzyqdx);
			Map<String, Object> map2 = new HashMap<String, Object>(16);
			map2.put("SQXH", body.get("sqxh"));
			if(StrUtil.isNotBlank(ObjectToTypes.parseString(body.get("yqdx")))){
				map2.put("YQDX", body.get("yqdx")+"");
			}else{
				map2.put("YQDX", -1);
			}
			map2.put("NYQHZKS", body.get("nyqhzks")+"");
			map2.put("NHZYY", body.get("nhzyy"));
			saveCisZyHzyqdx(map2, user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0002");
		}
	}
	
	/**
	* @Title: submitCisZyHzsq
	* @Description: TODO ??????????????????
	* @param @param sqxh    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	public void submitCisZyHzsq(Integer sqxh, SysUser user) {
		try {
			cisZyHzsqDao.updateCisZyHzsqSubmitStatus(sqxh, 1, user.getUserId(), DateUtil.date().toTimestamp());
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("lsyz", 1);
			parameters.put("jgid", user.getHospitalId());
			parameters.put("sqid", sqxh);
			parameters.put("yzlx", 4);
			cisHzyzDao.updateHzsqSubmitStatus(parameters);			
			Map<String, Object> map = cisZyHzsqDao.queryImHzBySqxh(sqxh);
			WaitProcessMessage<CisZyHzsqMessDto> wait = new WaitProcessMessage<CisZyHzsqMessDto>();
			wait.setTitle("???????????????");
			List<LineContentDto> dtoList = new ArrayList<LineContentDto>();
			LineContentDto dto = new LineContentDto();
			dto.setLabel("??????");
			dto.setContent(ObjectToTypes.parseString(map.get("BRCH")));
			dtoList.add(dto);
			dto = new LineContentDto();
			dto.setLabel("??????");
			dto.setContent(ObjectToTypes.parseString(map.get("BRXM")));
			dtoList.add(dto);
			dto = new LineContentDto();
			dto.setContent(ObjectToTypes.parseString(map.get("XB")));
			dtoList.add(dto);
			dto = new LineContentDto();
			dto.setContent(ObjectToTypes.parseString(map.get("OFFICENAME")));
			dtoList.add(dto);
			wait.setContents(dtoList);
			wait.setData(new CisZyHzsqMessDto(sqxh));
			wait.setRelativePath("1");
			List<Integer> userIdList = new ArrayList<Integer>();
			CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx(); 
			cisZyHzyqdx.setSqxh(sqxh);
			List<CisZyHzyqdx> yqdxList = cisZyHzyqdxDao.findByEntity(cisZyHzyqdx);
			for(CisZyHzyqdx yqdx : yqdxList) {
				String yqdxStr = yqdx.getYqdx();
				if("-1".equals(yqdxStr)) {
					SysYwqxkz sysYwqxkz = new SysYwqxkz();
					sysYwqxkz.setKsdm(ObjectToTypes.parseInt(yqdx.getNyqhzks()));
					sysYwqxkz.setYwlb(6);
					sysYwqxkz.setJgid(user.getHospitalId());
					List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
					for(SysYwqxkz qxkz : qxkzList) {
						if(!user.getUserId().equals(qxkz.getYgdm())) {
							userIdList.add(qxkz.getYgdm());
						}
					}
				}else {
					userIdList.add(ObjectToTypes.parseInt(yqdxStr));
				}
				
			}
			String uuid = rabbit.sendAlertMsg("imConsultation", wait, MessageLevelType.low, 
			TargetConfig.toSystemUser(SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline), false);	
			cisZyHzsqDao.updateMessIdBySqxh(uuid, sqxh);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0003");
		}
	}
	
	
	/**
	* @Title: stopCisZyHzsq
	* @Description: TODO ??????????????????
	* @param @param sqxh    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	public void stopCisZyHzsq(Integer sqxh) {
		CisZyHzsq cisZyHzsq = cisZyHzsqDao.getById(sqxh);
		StringBuffer offStr = new StringBuffer();
		if(StrUtil.isNotBlank(cisZyHzsq.getNyqhzks())) {
			String[] strArray = cisZyHzsq.getNyqhzks().split(",");
			for(String str : strArray) {
				CisZyHzyj cisZyHzyj = new CisZyHzyj();
				cisZyHzyj.setSqxh(sqxh);
				cisZyHzyj.setKsdm(ObjectToTypes.parseInt(str));
				long count = cisZyHzyjDao.findByEntityCount(cisZyHzyj);
				if(count == 0) {
					String officeName = dicKszdOutSer.getNameById(ObjectToTypes.parseInt(str));
					offStr.append(officeName).append(",");
				}	
			}
		}
		if(offStr.length() > 0) {
			offStr.deleteCharAt(offStr.length() - 1);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_00013", new String[] {
					offStr.toString()
			});
		}
		
		cisZyHzsqDao.stopCisZyHzsq(sqxh, new Date());
		
//		sysMessSer.updateFinishByCallId(sqxh, 2);
	}
	
	/**
	 * ????????????????????????
	 * @Title: queryCisZyHzyj
	 * @Description: TODO(?????????????????????????????????????????????)
	 * @param @param sqxh
	 * @param @return    ????????????
	 * @return List<CisZyHzyjResp>    ????????????
	 * @author ?????????
	 * @throws
	 */
	public List<CisZyHzyjResp> queryCisZyHzyj(Integer sqxh) {
		return cisZyHzyjDao.getCisZyHzyjBySqxh(sqxh);
	}
	
	/** 
	* @Title: backCisZyHzsq
	* @Description: TODO ??????????????????
	* @param @param sqxh    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void backCisZyHzsq(Integer sqxh, SysUser user) {
		CisZyHzyj cisZyHzyj = new CisZyHzyj();
		cisZyHzyj.setSqxh(sqxh);
		long count = cisZyHzyjDao.findByEntityCount(cisZyHzyj);
		if(count > 0) {
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_00014");
		}
		cisZyHzsqDao.updateCisZyHzsqSubmitStatus(sqxh, 0, null, null);
		cisHzyzDao.cancelHzSubmitStatusBySqid(sqxh, 4);
		String messId = cisZyHzsqDao.queryMessIdBySqxh(sqxh);
		if(StrUtil.isNotBlank(messId)) {
			List<Integer> userIdList = new ArrayList<Integer>();
			CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx(); 
			cisZyHzyqdx.setSqxh(sqxh);
			List<CisZyHzyqdx> yqdxList = cisZyHzyqdxDao.findByEntity(cisZyHzyqdx);
			for(CisZyHzyqdx yqdx : yqdxList) {
				String yqdxStr = yqdx.getYqdx();
				if("-1".equals(yqdxStr)) {
					SysYwqxkz sysYwqxkz = new SysYwqxkz();
					sysYwqxkz.setKsdm(ObjectToTypes.parseInt(yqdx.getNyqhzks()));
					sysYwqxkz.setYwlb(6);
					sysYwqxkz.setJgid(user.getHospitalId());
					List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
					for(SysYwqxkz qxkz : qxkzList) {
						if(!user.getUserId().equals(qxkz.getYgdm())) {
							userIdList.add(qxkz.getYgdm());
						}
					}
				}else {
					userIdList.add(ObjectToTypes.parseInt(yqdxStr));
				}
				
			}
			rabbit.cancelAlertMsg(messId, TargetConfig.toSystemUser(SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline));
		}
	}
	
	
	/**
	* @Title: removeCisZyHzsq
	* @Description: TODO ??????????????????
	* @param @param sqxh
	* @param @param user    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void removeCisZyHzsq(Integer sqxh, SysUser user) {
		//???????????????ID
		Integer jgid = user.getHospitalId();
		try {
			cisZyHzsqDao.deleteById(sqxh);
			// add CIS_ZY_HZYQDX ?????????????????????????
			CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx();
			cisZyHzyqdx.setSqxh(sqxh);
			cisZyHzyqdxDao.removeByEntity(cisZyHzyqdx);
			// end
			CisHzyz cisHzyz = new CisHzyz();
			cisHzyz.setJgid(jgid);
			cisHzyz.setYwid(sqxh);
			cisHzyz.setYdlb("301");
			cisHzyzDao.removeByEntity(cisHzyz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0007");
		}
	}
	
	/**
	* @Title: queryHzxx
	* @Description: TODO ????????????????????????
	* @param @param sqxh
	* @param @return    ????????????
	* @return CisZyHzsqAndYjResp    ????????????
	* @author ?????????
	* @throws
	 */
	public CisZyHzsqAndYjResp queryHzxx(Integer sqxh) {
		CisZyHzsq cisZyHzsq = cisZyHzsqDao.getById(sqxh);
		CisZyHzsqAndYjResp cisZyHzsqAndYjResp = BeanUtil.toBean(cisZyHzsq, CisZyHzsqAndYjResp.class);
		List<CisZyHzyjResp> cisZyHzyjRespList = cisZyHzyjDao.getCisZyHzyjBySqxh(sqxh);
		cisZyHzsqAndYjResp.setCisZyHzyjRespList(cisZyHzyjRespList);
		return cisZyHzsqAndYjResp;
	}
	
	/**
	* @Title: doLoadInfo
	* @Description: TODO ??????????????????????????????
	* @param @param sqxh
	* @param @param user
	* @param @return    ????????????
	* @return CisZyHzsqResp    ????????????
	* @author ?????????
	* @throws
	 */
	public CisZyHzsqResp doLoadInfo(Integer sqxh, SysUser user) {
		try {
			Integer jgid = user.getHospitalId();
			CisZyHzsqResp cisZyHzsqResp = cisZyHzsqDao.queryBySqxh(sqxh);
			cisZyHzsqResp.setJgName(user.getHospitalName());
			// ????????????
			String currentZd = queryCurrentBq(ObjectToTypes.parseInt(cisZyHzsqResp.getJzhm()),
					jgid);
			if (StrUtil.isNotBlank(currentZd)) {
				cisZyHzsqResp.setZdmc(currentZd);
			}
			return cisZyHzsqResp;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_0009");
		}
	}
	
	/**
	* @Title: queryCurrentBq
	* @Description: TODO ????????????????????????
	* @param @param zyh
	* @param @param jgid
	* @param @return    ????????????
	* @return String    ????????????
	* @author ?????????
	* @throws
	 */
	public String queryCurrentBq(Integer zyh, Integer jgid) {
		String jbmc = "";
		try {
			ImRyzdReq imRyzdReq = new ImRyzdReq();
			imRyzdReq.setZyh(zyh);
			imRyzdReq.setZdlb(2);
			imRyzdReq.setJgid(jgid);
			List<ImRyzdResp> imRyzdRespList = imRyzdDao.getDiagnosisByEntity(imRyzdReq);
			if (imRyzdRespList.size() > 0) {
				jbmc = imRyzdRespList.get(0).getJbmc();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_00010");
		}
		return jbmc;
	}
	
	/** 
	* @Title: commitCisZyHzyj
	* @Description: TODO ????????????????????????
	* @param @param sqxh
	* @param @param ssys
	* @param @param dbks
	* @param @param hzyj
	* @param @param user    ????????????
	* @return void    ????????????
	* @author ?????????
	* @throws
	 */
	public void commitCisZyHzyj(Integer sqxh, String ssys, Integer dbks,
			String hzyj, SysUser user) {
		Integer jgid = user.getHospitalId();
		try {
			CisZyHzyj cisZyHzyj = new CisZyHzyj();
			cisZyHzyj.setSqxh(sqxh);
			cisZyHzyj.setSsys(ssys);
			cisZyHzyj.setKsdm(dbks);
			List<CisZyHzyj> cisZyHzyjList = cisZyHzyjDao.findByEntity(cisZyHzyj);
			if (CollectionUtils.isNotEmpty(cisZyHzyjList)) {
				cisZyHzyj = cisZyHzyjList.get(0);
				cisZyHzyj.setHzyj(hzyj);
				cisZyHzyjDao.updateCisZyHzyjEnd(cisZyHzyj);
			} else {
				CisZyHzyj hzyjSave = new CisZyHzyj();
				hzyjSave.setSqxh(sqxh);
				hzyjSave.setSsys(ssys);
				hzyjSave.setKsdm(dbks);
				hzyjSave.setSxys(ObjectToTypes.parseString(user.getUserId()));
				hzyjSave.setSxsj(DateUtil.date().toTimestamp());
				hzyjSave.setHzyj(hzyj);
				hzyjSave.setJgid(jgid);
				hzyjSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_ZY_HZYJ));
				cisZyHzyjDao.insert(hzyjSave);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_YSZYHZSQ_00011");
		}
	}
	
	public Map<String, Object> getConsultationPrintParameters(Integer sqxh, SysUser user){
		Map<String, Object> map = cisZyHzsqDao.queryHzsqPrintBySqxh(sqxh, user.getHospitalId());
		StringBuffer yqdxStr = new StringBuffer(); 
		StringBuffer nyqhzksStr = new StringBuffer(); 
		String lx = ObjectToTypes.parseString(map.get("NHZYY"));
		if("1".equals(lx)) {	
			map.put("JGMC", user.getHospitalName());
			if(!"".equals(ObjectToTypes.parseString(map.get("YQDX")))) {
				String[] yqdxArray = ObjectToTypes.parseString(map.get("YQDX")).split(",");
				for(String yqdx : yqdxArray) {
					HrPersonnelModel model = hrPersonnelService.getPersonnelById(ObjectToTypes.parseInt(yqdx));
					if(StrUtil.isNotBlank(model.getPersonname())) {
						yqdxStr.append(model.getPersonname()).append(",");
					}
				}
			}
			if(yqdxStr.length() > 0 ) {
				map.put("HZYSMC", yqdxStr.deleteCharAt(yqdxStr.length() - 1));
			}
			
			if(!"".equals(ObjectToTypes.parseString(map.get("NYQHZKS")))) {
				String[] nyqhzksArray = ObjectToTypes.parseString(map.get("NYQHZKS")).split(",");
				for(String nyqhzks : nyqhzksArray) {
					nyqhzksStr.append(dicKszdOutSer.getNameById(ObjectToTypes.parseInt(nyqhzks))).append(",");
				}
			}
			if(nyqhzksStr.length() > 0 ) {
				map.put("HZKSMC", nyqhzksStr.deleteCharAt(nyqhzksStr.length() - 1));
			}
			
			CisZyHzyj cisZyHzyj = new CisZyHzyj();
			cisZyHzyj.setSqxh(sqxh);
			List<CisZyHzyj> list = cisZyHzyjDao.findByEntity(cisZyHzyj);
			StringBuffer hzyjStr = new StringBuffer(); 
			if(CollectionUtils.isNotEmpty(list)) {
				for(CisZyHzyj resp : list) {
					hzyjStr.append(resp.getHzyj()).append("\r\n");
				}
			}
			if(hzyjStr.length() > 0) {
				map.put("HZYJ", hzyjStr.toString().substring(0, hzyjStr.length() - 2));
			}
		}
		return map;
	}
	
	

}
