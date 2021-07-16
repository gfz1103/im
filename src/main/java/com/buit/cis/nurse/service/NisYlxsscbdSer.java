package com.buit.cis.nurse.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buit.cis.nurse.model.NisYlxsscbd;
import com.buit.cis.nurse.model.NisYlxssjksf;
import com.buit.cis.nurse.model.NisYlxsszg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.nurse.dao.NisYlxsscbdDao;
import com.buit.cis.nurse.dao.NisYlxssjksfDao;
import com.buit.cis.nurse.dao.NisYlxsszgDao;
import com.buit.cis.nurse.request.NisYlxsscbdReq;
import com.buit.cis.nurse.request.NisYlxssjksfReq;
import com.buit.cis.nurse.request.NisYlxsszgReq;
import com.buit.cis.nurse.response.NisYlxssjksfResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.BeanUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.util.StrUtil;
/**
 * 压力性损伤预报、传报单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisYlxsscbdSer extends BaseManagerImp<NisYlxsscbd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxsscbdSer.class);
    
    @Autowired
    private NisYlxsscbdDao nisYlxsscbdDao;
    
    @Override
    public NisYlxsscbdDao getEntityMapper(){        
        return nisYlxsscbdDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisYlxssjksfDao nisYlxssjksfDao;
    
    @Autowired
    private NisYlxsszgDao nisYlxsszgDao;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    /**
     * 新增修改保存压力性损伤传报单
     * @Title: saveYlxsscbd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisYlxsscbdReq
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveYlxsscbd(NisYlxsscbdReq nisYlxsscbdReq, SysUser user) {
    	nisYlxsscbdReq.setJgid(user.getHospitalId());
    	List<NisYlxssjksfReq> jksfList = nisYlxsscbdReq.getNisYlxssjksfList();
    	List<NisYlxsszgReq> zgList = nisYlxsscbdReq.getNisYlxsszgList();
    	if(StrUtil.isBlankIfStr(nisYlxsscbdReq.getJlxh())) {
    		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YLXSSCBD);
    		nisYlxsscbdReq.setJlxh(jlxh);
    		NisYlxsscbd nisYlxsscbd = BeanUtil.toBean(nisYlxsscbdReq, NisYlxsscbd.class);
    		nisYlxsscbdDao.insert(nisYlxsscbd);
    		for(NisYlxssjksfReq nisYlxssjksf : jksfList) {
    			nisYlxssjksf.setJgid(user.getHospitalId());
    			if(StrUtil.isBlankIfStr(nisYlxssjksf.getJlxh())) {
    				nisYlxssjksf.setCbdjlxh(jlxh);
    				nisYlxssjksf.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YLXSSJKSF));
    				nisYlxssjksfDao.insert(nisYlxssjksf);
    			}
    		}
    		
    		for(NisYlxsszgReq nisYlxsszg : zgList) {
    			nisYlxsszg.setJgid(user.getHospitalId());
    			if(StrUtil.isBlankIfStr(nisYlxsszg.getJlxh())) {
    				nisYlxsszg.setCbdjlxh(nisYlxsscbd.getJlxh());
    				nisYlxsszg.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YLXSSZG));
    				nisYlxsszgDao.insert(nisYlxsszg);
    			}
    		}
    	}else {
    		NisYlxsscbd nisYlxsscbd = BeanUtil.toBean(nisYlxsscbdReq, NisYlxsscbd.class);
    		nisYlxsscbdDao.update(nisYlxsscbd);
    		for(NisYlxssjksfReq nisYlxssjksf : jksfList) {
    			nisYlxssjksf.setJgid(user.getHospitalId());
    			if(StrUtil.isBlankIfStr(nisYlxssjksf.getJlxh())) {
    				nisYlxssjksf.setCbdjlxh(nisYlxsscbd.getJlxh());
    				nisYlxssjksf.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YLXSSJKSF));
    				nisYlxssjksfDao.insert(nisYlxssjksf);
    			}else {
    				if("del".equals(nisYlxssjksf.getOpStatus())) {
    					nisYlxssjksfDao.deleteById(nisYlxssjksf.getJlxh());
    				}else {   					
    					nisYlxssjksfDao.update(nisYlxssjksf);
    				}
    			}
    		}
    		for(NisYlxsszgReq nisYlxsszg : zgList) {
    			nisYlxsszg.setJgid(user.getHospitalId());
    			if(StrUtil.isBlankIfStr(nisYlxsszg.getJlxh())) {
    				nisYlxsszg.setCbdjlxh(nisYlxsscbd.getJlxh());
    				nisYlxsszg.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_YLXSSZG));
    				nisYlxsszgDao.insert(nisYlxsszg);
    			}else {
    				if("del".equals(nisYlxsszg.getOpStatus())) {
    					nisYlxsszgDao.deleteById(nisYlxsszg.getJlxh());
    				}else {
    	   				nisYlxsszgDao.update(nisYlxsszg);
    				}
    			}
    		}
    	}
    }
    
    /**
     * 根据日期删除压力性损伤传报单
     * @Title: deleteYlxsscbdByDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteYlxsscbdByDate(Integer zyh, String queryDate, SysUser user) {
    	List<NisYlxsscbd> list = nisYlxsscbdDao.queryYlxsscbdByDate(zyh, 
        		queryDate, user.getHospitalId());
    	for(NisYlxsscbd nisYlxsscbd : list) {
    		NisYlxssjksf jksf = new NisYlxssjksf();
    		jksf.setCbdjlxh(nisYlxsscbd.getJlxh());
    		nisYlxssjksfDao.removeByEntity(jksf);
    		NisYlxsszg nisYlxsszg = new NisYlxsszg();
    		nisYlxsszg.setCbdjlxh(nisYlxsscbd.getJlxh());
    		nisYlxsszgDao.removeByEntity(nisYlxsszg);
    		nisYlxsscbdDao.deleteById(nisYlxsscbd.getJlxh());
    	}
    }
    
    public List<Map<String, Object>> queryPrintInfo(List<Map<String, Object>> list){
		Integer jlxh = ObjectToTypes.parseInt(list.get(0).get("jlxh"));
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<NisYlxssjksfResp> jksfList = nisYlxssjksfDao.queryJksfByCbdjlxh(jlxh);
        List<NisYlxssjksfResp> newjksfList = new ArrayList<NisYlxssjksfResp>();
        for(NisYlxssjksfResp resp : jksfList) {
        	String bw = resp.getBw();
        	if(StrUtil.isNotBlank(bw)) {
        		String[] str = bw.split(",");
        		for(int i = 0; i < str.length; i++) {
        			if(i == 0) {
        				resp.setBw(str[i]);
        				newjksfList.add(resp);
        			}else {
        				NisYlxssjksfResp newResp = new NisYlxssjksfResp();
        				newResp.setBw(str[i]);
        				newjksfList.add(newResp);
        			}
        		}
        	}else {
        		newjksfList.add(resp);
        	}
        }
		List<Map<String, Object>> zgList = nisYlxsszgDao.queryPrintZgByCbdjlxh(jlxh);
        map.put("StressInjuryOne", iReportExportFileSer.conVerDataSource(BUHISUtil.ListObjToMap(newjksfList)));
        map.put("StressInjuryTwo", iReportExportFileSer.conVerDataSource(zgList));
        listMap.add(map);
    	return listMap;
    }
}
