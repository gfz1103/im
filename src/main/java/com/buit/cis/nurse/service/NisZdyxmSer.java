package com.buit.cis.nurse.service;


import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.nurse.dao.NisHljldzdyDao;
import com.buit.cis.nurse.dao.NisZdyxmDao;
import com.buit.cis.nurse.dao.NisZdyxmnrDao;
import com.buit.cis.nurse.model.NisHljldzdy;
import com.buit.cis.nurse.model.NisZdyxm;
import com.buit.cis.nurse.model.NisZdyxmnr;
import com.buit.cis.nurse.request.NisZdyxmReq;
import com.buit.cis.nurse.request.NisZdyxmnrReq;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.service.SysDictConfigSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.RedisFactory;

import cn.hutool.core.util.StrUtil;
/**
 * 护理记录单自定义项目<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisZdyxmSer extends BaseManagerImp<NisZdyxm,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisZdyxmSer.class);
    
    @Autowired
    private NisZdyxmDao nisZdyxmDao;
    
    @Override
    public NisZdyxmDao getEntityMapper(){        
        return nisZdyxmDao;
    }
    
    @Autowired
    private NisHljldzdyDao nisHljldzdyDao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisZdyxmnrDao nisZdyxmnrDao;
    
    @DubboReference
    private SysDictConfigSer sysDictConfigSer;

    /**
     * 删除	
     * @Title: deleteById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param xmdm    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer xmdm, Integer jgid) {
		long count = nisZdyxmnrDao.queryIsExistUse(jgid, xmdm);
		if(count > 0) {
			throw BaseException.create("ERROR_NURSEWORK_HLJL_0001"); 	
		}
		nisZdyxmDao.deleteById(xmdm);
		NisZdyxmnr nisZdyxmnr = new NisZdyxmnr();
		nisZdyxmnr.setXmdm(xmdm);
		nisZdyxmnr.setJgid(jgid);
		nisZdyxmnrDao.removeByEntity(nisZdyxmnr);
	}

	/**
	 * 作废或取消作废
	 * @Title: invalidXm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param xmdm
	 * @param @param zfpb
	 * @param @param hospitalId    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void invalidXm(Integer xmdm, Integer zfpb, Integer jgid) {
//		if(zfpb == 1) {
//			NisHljldzdy nisHljldzdy = new NisHljldzdy();
//			nisHljldzdy.setXmdm(xmdm);
//			nisHljldzdy.setJgid(jgid);
//			long count = nisHljldzdyDao.findByEntityCount(nisHljldzdy);
//			if(count > 0) {
//				throw BaseException.create("ERROR_NURSEWORK_HLJL_0001"); 	
//			}
//		}
		nisZdyxmDao.invalid(xmdm, zfpb);
	}
	

	/**
	 * 新增或修改保存
	 * @Title: saveOrUpdate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param nisZdyxmReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(NisZdyxmReq nisZdyxmReq, SysUser user) {
		nisZdyxmReq.setJgid(user.getHospitalId());
		List<NisZdyxmnrReq> list = nisZdyxmReq.getZdyxmnrReqList();
    	if(StrUtil.isBlankIfStr(nisZdyxmReq.getXmdm())) {
    		Integer xmdm = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZDYXM);
    		nisZdyxmReq.setXmdm(xmdm);
    		nisZdyxmDao.insert(nisZdyxmReq);      
    		for(NisZdyxmnrReq req : list) {
    			req.setXmdm(xmdm);
    			req.setJgid(user.getHospitalId());	
    			req.setXmnrdm(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZDYXMNR));
    			nisZdyxmnrDao.insert(req);
    		}
    	}else {
    		NisHljldzdy nisHljldzdy = new NisHljldzdy();
    		nisHljldzdy.setJgid(nisZdyxmReq.getJgid());
    		nisHljldzdy.setXmdm(nisZdyxmReq.getXmdm());
    		long count = nisHljldzdyDao.findByEntityCount(nisHljldzdy);
    		if(count > 0) {
    			throw BaseException.create("ERROR_NURSEWORK_HLJL_0001"); 	
    		}
    		nisZdyxmDao.update(nisZdyxmReq);
    		sysDictConfigSer.upDateVersion("nis_zdyxm");
    		for(NisZdyxmnrReq req : list) {
    			req.setJgid(user.getHospitalId());	
    			if(StrUtil.isBlankIfStr(req.getXmnrdm())) {
    				req.setXmdm(nisZdyxmReq.getXmdm());
        			req.setXmnrdm(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_ZDYXMNR));
        			nisZdyxmnrDao.insert(req);
    			}else {
//    				NisHljldzdy nisHljldzdyNew = new NisHljldzdy();
//    				nisHljldzdyNew.set;
//    				nisHljldzdyNew.setJgid(nisZdyxmReq.getJgid());
//    	    		long countNew = nisHljldzdyDao.findByEntityCount(nisHljldzdyNew);
//    	    		if(countNew > 0) {
//    	    			throw BaseException.create("ERROR_NURSEWORK_HLJL_0002"); 	
//    	    		}
    	    		nisZdyxmnrDao.update(req);
    			}
    		}
    	}
		
	}
    
}
