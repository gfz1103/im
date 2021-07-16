package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisHljldzdy;
import com.buit.cis.nurse.model.NisSjkjld;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import com.buit.cis.nurse.dao.NisHljldzdyDao;
import com.buit.cis.nurse.dao.NisSjkjldDao;
import com.buit.cis.nurse.enums.NursingTypeEnum;
import com.buit.cis.nurse.request.NisHljldzdyReq;
import com.buit.cis.nurse.request.NisSjkjldReq;
import com.buit.cis.nurse.response.NisSjkjldResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 神经科记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisSjkjldSer extends BaseManagerImp<NisSjkjld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisSjkjldSer.class);
    
    @Autowired
    private NisSjkjldDao nisSjkjldDao;
    
    @Override
    public NisSjkjldDao getEntityMapper(){        
        return nisSjkjldDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisHljldzdyDao nisHljldzdyDao;
    
    @Autowired
    private NisHljldzdySer nisHljldzdySer;
    
    /**
     * 新增修改保存神经科记录单
     * @Title: saveSjkjld
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisSjkjldReq    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSjkjld(NisSjkjldReq nisSjkjldReq, SysUser user) {
    	nisSjkjldReq.setJgid(user.getHospitalId());
    	List<NisHljldzdyReq> list = nisSjkjldReq.getZdyList();
    	if(StrUtil.isBlankIfStr(nisSjkjldReq.getJlxh())) {
    		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_SJKJLD);
    		nisSjkjldReq.setJlxh(jlxh);
    		nisSjkjldDao.insert(BeanUtil.toBean(nisSjkjldReq, NisSjkjld.class));	
    		for(NisHljldzdyReq nisHljldzdyReq : list) {
    			nisHljldzdyReq.setJgid(user.getHospitalId());
    			nisHljldzdyReq.setJlxh(jlxh);
    			nisHljldzdyReq.setZyh(nisSjkjldReq.getZyh());
    			nisHljldzdyReq.setMblx(nisSjkjldReq.getMblx());
    			nisHljldzdyReq.setZdyid(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJLDZDY));
    			nisHljldzdyDao.insert(nisHljldzdyReq);
    		}
    	}else {
    		nisSjkjldDao.update(BeanUtil.toBean(nisSjkjldReq, NisSjkjld.class));
    		for(NisHljldzdyReq nisHljldzdyReq : list) {
    			nisHljldzdyReq.setJgid(user.getHospitalId());
    			nisHljldzdyReq.setJlxh(nisSjkjldReq.getJlxh());
    			nisHljldzdyReq.setZyh(nisSjkjldReq.getZyh());
    			nisHljldzdyReq.setMblx(nisSjkjldReq.getMblx());
    			if(StrUtil.isBlankIfStr(nisHljldzdyReq.getZdyid())) {
    				nisHljldzdyReq.setZdyid(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJLDZDY));
    				nisHljldzdyDao.insert(nisHljldzdyReq);
    			}else {
    				nisHljldzdyDao.update(nisHljldzdyReq);
    			}
    		}
    	}
    }
    
    public List<Map<String, Object>> querySjkjldByDatePrintInfo(Integer zyh, String queryDate, Integer jgid){
    	 List<NisSjkjldResp> list = nisSjkjldDao.querySjkjldByDate(zyh, queryDate, jgid);
         List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
         List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
        
         for (Map<String, Object> map : listMap) {
             List<Map<String, Object>> zdyList = nisHljldzdySer.getEntityMapper().queryZdynrByZdyId(
                     ObjectToTypes.parseInt(map.get("jlxh")), NursingTypeEnum.neurologyRecords.getCode(), jgid);
             if (CollectionUtils.isNotEmpty(zdyList)) {
                 for (int i = 0; i < zdyList.size(); i++) {
                     map.put("zdyl" + (i + 1), zdyList.get(i).get("zdynr"));
                 }
             }
             String hlxj = ObjectToTypes.parseString(map.get("hlxj"));
             String hljl = ObjectToTypes.parseString(map.get("hljl"));
             if(StrUtil.isNotBlank(hlxj) && StrUtil.isNotBlank(hljl)) {
            	 String[] xjStr = hlxj.split("@");
            	 String[] jlStr = hljl.split("@");
            	 int length = xjStr.length > jlStr.length ? xjStr.length : jlStr.length;
     			 for(int i = 0; i < length; i++) {
     				if(i == 0) {
     					map.put("hlxj", xjStr[i]);
     					map.put("hljl", jlStr[i]);
     					newList.add(map);
     				}else {
     					Map<String, Object> newMap = new HashMap<String, Object>(16);
     					if(i < (xjStr.length < jlStr.length ? xjStr.length : jlStr.length) ) {
     						newMap.put("hlxj", xjStr[i]);
     						newMap.put("hljl", jlStr[i]);
     					}
     					if(xjStr.length == length) {
     						newMap.put("hlxj", xjStr[i]);
     					}
     					if(jlStr.length == length) {
     						newMap.put("hljl", jlStr[i]);
     					}
     					newList.add(newMap);
     				}
     			 } 
             }else if(StrUtil.isNotBlank(hlxj)) {
            	 String[] xjStr = hlxj.split("@");
            	 for(int i = 0; i < xjStr.length; i++) {
      				if(i == 0) {
      					map.put("hlxj", xjStr[i]);
      					newList.add(map);
      				}else {
      					Map<String, Object> newMap = new HashMap<String, Object>(16);
      					newMap.put("hlxj", xjStr[i]);
      					newList.add(newMap);
      				}
            	 }
             }else if(StrUtil.isNotBlank(hljl)) {
            	 String[] jlStr = hljl.split("@");
            	 for(int i = 0; i < jlStr.length; i++) {
       				if(i == 0) {
       					map.put("hljl", jlStr[i]);
       					newList.add(map);
       				}else {
       					Map<String, Object> newMap = new HashMap<String, Object>(16);
       					newMap.put("hljl", jlStr[i]);
       					newList.add(newMap);
       				}
             	 }
             }else {
            	 newList.add(map);
             }
        }
    	return newList;
    }

    /**
     * 删除神经科记录单
     * @Title: removeByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @param jgid    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	public void removeByJlxh(Integer jlxh, Integer jgid) {
		nisSjkjldDao.deleteById(jlxh);
		NisHljldzdy nisHljldzdy = new NisHljldzdy();
		nisHljldzdy.setJlxh(jlxh);
		nisHljldzdy.setMblx(NursingTypeEnum.neurologyRecords.getCode());
		nisHljldzdy.setJgid(jgid);
		nisHljldzdyDao.removeByEntity(nisHljldzdy);
	}
}
