package com.buit.cis.nurse.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.nurse.dao.NisWzhldDao;
import com.buit.cis.nurse.model.NisWzhld;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisWzhldResp;
import com.buit.commons.BaseManagerImp;
import com.buit.utill.BUHISUtil;

import cn.hutool.core.util.StrUtil;
/**
 * 危重患者护理单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisWzhldSer extends BaseManagerImp<NisWzhld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisWzhldSer.class);
    
    @Autowired
    private NisWzhldDao nisWzhldDao;
    
    @Override
    public NisWzhldDao getEntityMapper(){        
        return nisWzhldDao;
    }
    
    public List<Map<String, Object>> queryWzhldByDatePrintInfo(NisHlQueryReq nisHlQueryReq){
    	List<NisWzhldResp> list = nisWzhldDao.queryPrintWzhldByDate(nisHlQueryReq);
    	List<NisWzhldResp> newList = new ArrayList<NisWzhldResp>();
    	for(NisWzhldResp resp : list) {
    		String dghl = resp.getDghl();
    		String hlzd = resp.getHlzd();
    		String bqcs = resp.getBqcs();
    		if(StrUtil.isNotBlank(dghl) && StrUtil.isNotBlank(hlzd) && StrUtil.isNotBlank(bqcs)) {
    			String[] dgStr = dghl.split("@");
              	String[] hlStr = hlzd.split("@");
              	String[] bqStr = bqcs.split("@");
              	int maxLength = dgStr.length > hlStr.length ? dgStr.length : hlStr.length;
              	maxLength = maxLength > bqStr.length ? maxLength : bqStr.length;
              	int minLength = dgStr.length < hlStr.length ? dgStr.length : hlStr.length;
              	minLength = minLength < bqStr.length ? minLength : bqStr.length;
              	for(int i = 0; i < maxLength; i++) {
              		if(i == 0) {
     					resp.setDghl(dgStr[i]);
     					resp.setHlzd(hlStr[i]);
     					resp.setBqcs(bqStr[i]);
     					newList.add(resp);
     				}else {
     					NisWzhldResp newResp = new NisWzhldResp();			
     					newResp.setJlxh(resp.getJlxh());
     					if(i < dgStr.length) {
     						newResp.setDghl(dgStr[i]);
     					}
     					if(i < hlStr.length) {
     						newResp.setHlzd(hlStr[i]);
     					}
     					if(i < bqStr.length) {
     						newResp.setBqcs(bqStr[i]);
     					}
     					newList.add(newResp);
     					newResp = null;
     				}
              	}
    		}else if(StrUtil.isNotBlank(dghl) && StrUtil.isNotBlank(hlzd)) {
    			String[] dgStr = dghl.split("@");
           	 	String[] hlStr = hlzd.split("@");
            	int length = dgStr.length > hlStr.length ? dgStr.length : hlStr.length;
     			for(int i = 0; i < length; i++) {
     				if(i == 0) {
     					resp.setDghl(dgStr[i]);
     					resp.setHlzd(hlStr[i]);
     					newList.add(resp);
     				}else {
     					NisWzhldResp newResp = new NisWzhldResp();	
     					newResp.setJlxh(resp.getJlxh());
     					if(i < dgStr.length) {
     						newResp.setDghl(dgStr[i]);
     					}
     					if(i < hlStr.length) {
     						newResp.setHlzd(hlStr[i]);
     					}
     					newList.add(newResp);
     					newResp = null;
     				}
     			 } 
             }else if(StrUtil.isNotBlank(dghl) && StrUtil.isNotBlank(bqcs)) {
    			String[] dgStr = dghl.split("@");
           	 	String[] bqStr = bqcs.split("@");
            	int length = dgStr.length > bqStr.length ? dgStr.length : bqStr.length;
     			for(int i = 0; i < length; i++) {
     				if(i == 0) {
     					resp.setDghl(dgStr[i]);
     					resp.setBqcs(bqStr[i]);
     					newList.add(resp);
     				}else {
     					NisWzhldResp newResp = new NisWzhldResp();	
     					newResp.setJlxh(resp.getJlxh());
     					if(i < dgStr.length) {
     						newResp.setDghl(dgStr[i]);
     					}
     					if(i < bqStr.length) {
     						newResp.setBqcs(bqStr[i]);
     					}
     					newList.add(newResp);
     					newResp = null;
     				}
     			 } 
             }else if(StrUtil.isNotBlank(hlzd) && StrUtil.isNotBlank(bqcs)) {
    			String[] hlStr = hlzd.split("@");
           	 	String[] bqStr = bqcs.split("@");
            	int length = hlStr.length > bqStr.length ? hlStr.length : bqStr.length;
     			for(int i = 0; i < length; i++) {
     				if(i == 0) {
     					resp.setHlzd(hlStr[i]);
     					resp.setBqcs(bqStr[i]);
     					newList.add(resp);
     				}else {
     					NisWzhldResp newResp = new NisWzhldResp();	
     					newResp.setJlxh(resp.getJlxh());
     					if(i < hlStr.length) {
     						resp.setHlzd(hlStr[i]);
     					}
     					if(i < bqStr.length) {
     						resp.setBqcs(bqStr[i]);
     					}
     					newList.add(newResp);
     					newResp = null;
     				}
     			 } 
             }else if(StrUtil.isNotBlank(dghl)) {
            	 String[] str = dghl.split("@");
     			 for(int i = 0; i < str.length; i++) {
     				 if(i == 0) {
     					resp.setDghl(str[i]);
     					newList.add(resp);
     				 }else {
     					NisWzhldResp newResp = new NisWzhldResp();
     					newResp.setJlxh(resp.getJlxh());
     					newResp.setDghl(str[i]);
     					newList.add(newResp);
     					newResp = null;
     				 }
     			 }
             }else if(StrUtil.isNotBlank(hlzd)) {
            	 String[] str = hlzd.split("@");
     			 for(int i = 0; i < str.length; i++) {
     				 if(i == 0) {
     					resp.setHlzd(str[i]);
     					newList.add(resp);
     				 }else {
     					NisWzhldResp newResp = new NisWzhldResp();
     					newResp.setJlxh(resp.getJlxh());
     					newResp.setHlzd(str[i]);
     					newList.add(newResp);
     					newResp = null;
     				 }
     			 }
             }else if(StrUtil.isNotBlank(bqcs)) {
            	 String[] str = bqcs.split("@");
     			 for(int i = 0; i < str.length; i++) {
     				 if(i == 0) {
     					resp.setBqcs(str[i]);
     					newList.add(resp);
     				 }else {
     					NisWzhldResp newResp = new NisWzhldResp();
     					newResp.setJlxh(resp.getJlxh());
     					newResp.setBqcs(str[i]);
     					newList.add(newResp);
     					newResp = null;
     				 }
     			 }
             }else {
            	 newList.add(resp);
             }
    	}
    	return BUHISUtil.ListObjToMap(newList);
    }
    
}
