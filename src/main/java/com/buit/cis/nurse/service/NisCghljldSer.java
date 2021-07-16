package com.buit.cis.nurse.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buit.cis.nurse.model.NisCghljld;
import com.buit.cis.nurse.model.NisHljldzdy;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.nurse.dao.NisCghljldDao;
import com.buit.cis.nurse.dao.NisHljldzdyDao;
import com.buit.cis.nurse.enums.NursingTypeEnum;
import com.buit.cis.nurse.request.NisCghljldReq;
import com.buit.cis.nurse.request.NisHljldzdyReq;
import com.buit.cis.nurse.response.NisCghljldResp;
import com.buit.cis.nurse.response.NisWzhldResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 常规护理记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisCghljldSer extends BaseManagerImp<NisCghljld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisCghljldSer.class);
    
    @Autowired
    private NisCghljldDao nisCghljldDao;
    
    @Override
    public NisCghljldDao getEntityMapper(){        
        return nisCghljldDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisHljldzdyDao nisHljldzdyDao;
    
    @Autowired
    private NisHljldzdySer nisHljldzdySer;
    
    /**
     * 新增修改保存护理记录单
     * @Title: saveCgHljl
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisCghljldReq
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCgHljl(NisCghljldReq nisCghljldReq, SysUser user) {
    	nisCghljldReq.setJgid(user.getHospitalId());
    	List<NisHljldzdyReq> list = nisCghljldReq.getZdyList();
    	if(StrUtil.isBlankIfStr(nisCghljldReq.getJlxh())) {
    		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_CGHLJLD);
    		nisCghljldReq.setJlxh(jlxh);
    		nisCghljldDao.insert(BeanUtil.toBean(nisCghljldReq, NisCghljld.class));	
    		for(NisHljldzdyReq nisHljldzdyReq : list) {
    			nisHljldzdyReq.setJgid(user.getHospitalId());
    			nisHljldzdyReq.setJlxh(jlxh);
    			nisHljldzdyReq.setZyh(nisCghljldReq.getZyh());
    			nisHljldzdyReq.setMblx(nisCghljldReq.getMblx());
    			nisHljldzdyReq.setZdyid(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJLDZDY));
    			nisHljldzdyDao.insert(nisHljldzdyReq);
    		}
    	}else {
    		nisCghljldDao.update(nisCghljldReq);
    		for(NisHljldzdyReq nisHljldzdyReq : list) {
    			nisHljldzdyReq.setJgid(user.getHospitalId());
    			nisHljldzdyReq.setJlxh(nisCghljldReq.getJlxh());
    			nisHljldzdyReq.setZyh(nisCghljldReq.getZyh());
    			nisHljldzdyReq.setMblx(nisCghljldReq.getMblx());
    			if(StrUtil.isBlankIfStr(nisHljldzdyReq.getZdyid())) {
    				nisHljldzdyReq.setZdyid(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJLDZDY));
        			nisHljldzdyDao.insert(nisHljldzdyReq);
    			}else {
    				nisHljldzdyDao.update(nisHljldzdyReq);
    			}
    		}
    	}
    }
    
    public List<Map<String, Object>> queryNisCghljldByDatePrintInfo(Integer zyh, String queryDate, Integer jgid){
    	 List<NisCghljldResp> list = nisCghljldDao.queryCgHljlByDate(zyh, queryDate, jgid);
         List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
         List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
         for (Map<String, Object> map : listMap) {
             List<Map<String, Object>> zdyList = nisHljldzdySer.getEntityMapper().queryZdynrByZdyId(
                     ObjectToTypes.parseInt(map.get("jlxh")), NursingTypeEnum.nursingRecord.getCode(), jgid);
             if (CollectionUtils.isNotEmpty(zdyList)) {
                 for (int i = 0; i < zdyList.size(); i++) {
                     map.put("zdyl" + (i + 1), zdyList.get(i).get("zdynr"));
                 }
             }
             String dglx = ObjectToTypes.parseString(map.get("dglx"));
             String hljl = ObjectToTypes.parseString(map.get("hljl"));
             if(StrUtil.isNotBlank(dglx) && StrUtil.isNotBlank(hljl)) {
            	 String[] dgStr = dglx.split("@");
            	 String[] jlStr = hljl.split("@");
            	 int length = dgStr.length > jlStr.length ? dgStr.length : jlStr.length;
     			 for(int i = 0; i < length; i++) {
     				if(i == 0) {
     					map.put("dglx", dgStr[i]);
     					map.put("hljl", jlStr[i]);
     					newList.add(map);
     				}else {
     					Map<String, Object> newMap = new HashMap<String, Object>(16);
     					if(i < (dgStr.length < jlStr.length ? dgStr.length : jlStr.length)) {
     						newMap.put("dglx", dgStr[i]);
     						newMap.put("hljl", jlStr[i]);
     					}
     					if(dgStr.length == length) {
     						newMap.put("dglx", dgStr[i]);
     					}
     					if(jlStr.length == length) {
     						newMap.put("hljl", jlStr[i]);
     					}
     					newList.add(newMap);
     				}
     			 } 
             }else if(StrUtil.isNotBlank(dglx)) {
            	 String[] dgStr = dglx.split("@");
            	 for(int i = 0; i < dgStr.length; i++) {
      				if(i == 0) {
      					map.put("dglx", dgStr[i]);
      					newList.add(map);
      				}else {
      					Map<String, Object> newMap = new HashMap<String, Object>(16);
      					newMap.put("dglx", dgStr[i]);
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
     * 删除护理记录单
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
		nisCghljldDao.deleteById(jlxh);
		NisHljldzdy nisHljldzdy = new NisHljldzdy();
		nisHljldzdy.setJlxh(jlxh);
		nisHljldzdy.setMblx(NursingTypeEnum.nursingRecord.getCode());
		nisHljldzdy.setJgid(jgid);
		nisHljldzdyDao.removeByEntity(nisHljldzdy);
	}
    
    /**
     * 查询护理记录单
     * @Title: queryNisCghljldByDateInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param jgid
     * @param @return    设定文件
     * @return List<NisCghljldResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    public List<NisCghljldResp> queryNisCghljldByDateInfo(Integer zyh, String queryDate, Integer jgid){
   	 	List<NisCghljldResp> list = nisCghljldDao.queryCgHljlByDate(zyh, queryDate, jgid);
   	 	List<NisCghljldResp> newList = new ArrayList<NisCghljldResp>();
	    for (NisCghljldResp resp : list) {	
	        String dglx = resp.getDglx();
	        String hljl = resp.getHljl();
	        if(StrUtil.isNotBlank(dglx) && StrUtil.isNotBlank(hljl)) {
	       	 String[] dgStr = dglx.split("@");
	       	 String[] jlStr = hljl.split("@");
	       	 int length = dgStr.length > jlStr.length ? dgStr.length : jlStr.length;
				 for(int i = 0; i < length; i++) {
					if(i == 0) {
						resp.setDglx(dgStr[i]);
						resp.setHljl(jlStr[i]);
						newList.add(resp);
					}else {
						NisCghljldResp newResp = new NisCghljldResp();
						newResp.setJlxh(resp.getJlxh());
						if(i < (dgStr.length < jlStr.length ? dgStr.length : jlStr.length)) {
							newResp.setDglx(dgStr[i]);
							newResp.setHljl(jlStr[i]);
						}
						if(dgStr.length == length) {
							newResp.setDglx(dgStr[i]);
						}
						if(jlStr.length == length) {
							newResp.setHljl(jlStr[i]);
						}
						newList.add(newResp);
					}
				 } 
	        }else if(StrUtil.isNotBlank(dglx)) {
	       	 String[] dgStr = dglx.split("@");
	       	 for(int i = 0; i < dgStr.length; i++) {
	 				if(i == 0) {
	 					resp.setDglx(dgStr[i]);
	 					newList.add(resp);
	 				}else {
	 					NisCghljldResp newResp = new NisCghljldResp();
						newResp.setJlxh(resp.getJlxh());
						newResp.setDglx(dgStr[i]);
	 					newList.add(newResp);
	 				}
	       	 }
	        }else if(StrUtil.isNotBlank(hljl)) {
	       	 String[] jlStr = hljl.split("@");
	       	 for(int i = 0; i < jlStr.length; i++) {
	  				if(i == 0) {
	  					resp.setHljl(jlStr[i]);
	  					newList.add(resp);
	  				}else {
	  					NisCghljldResp newResp = new NisCghljldResp();
						newResp.setJlxh(resp.getJlxh());
						newResp.setHljl(jlStr[i]);
	  					newList.add(newResp);
	  				}
	        	 }
	        }else {
	       	 newList.add(resp);
	        }
	    }
	    return newList;
   }
    
}
