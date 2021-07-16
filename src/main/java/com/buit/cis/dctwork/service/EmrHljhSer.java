package com.buit.cis.dctwork.service;


import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.RedisFactory;

import cn.hutool.core.util.StrUtil;

import com.buit.cis.dctwork.dao.EmrHljhDao;
import com.buit.cis.dctwork.model.EmrHljh;
import com.buit.cis.dctwork.request.EmrHljhReq;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理计划<br>
 * @author GONGFANGZHOU
 */
@Service
public class EmrHljhSer extends BaseManagerImp<EmrHljh,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EmrHljhSer.class);
    
    @Autowired
    private EmrHljhDao emrHljhDao;
    
    @Override
    public EmrHljhDao getEntityMapper(){        
        return emrHljhDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    /**
    * @Title: add
    * @Description: TODO 护理计划新增或修改
    * @param @param emrHljh    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    public void add(EmrHljhReq emrHljh) {
    	try {
    		Date ksrq = emrHljh.getKsrq();
    		Date tzrq = emrHljh.getTzrq();
    		if (ksrq != null && tzrq != null && ksrq.compareTo(tzrq) == 1) {
    			throw BaseException.create("ERROR_DCTWORK_EMRHLJH_0001");
    		}
        	if(StrUtil.isNotBlank(ObjectToTypes.parseString(emrHljh.getJlbh()))) {
        		emrHljhDao.update(emrHljh);
        	}else {
        		emrHljh.setJlbh(redisFactory.getTableKey(TableName.DB_NAME, TableName.EMR_HLJH));
        		emrHljhDao.insert(emrHljh);
        	}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_EMRHLJH_0002");
		}
    	
    }
    
}
