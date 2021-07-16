package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.EnrJl02Dao;
import com.buit.cis.dctwork.model.EnrJl02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class EnrJl02Ser extends BaseManagerImp<EnrJl02,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EnrJl02Ser.class);
    
    @Autowired
    private EnrJl02Dao enrJl02Dao;
    
    @Override
    public EnrJl02Dao getEntityMapper(){        
        return enrJl02Dao;
    }
    
}
