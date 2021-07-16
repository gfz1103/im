package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.EnrJg02Dao;
import com.buit.cis.dctwork.model.EnrJg02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class EnrJg02Ser extends BaseManagerImp<EnrJg02,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EnrJg02Ser.class);
    
    @Autowired
    private EnrJg02Dao enrJg02Dao;
    
    @Override
    public EnrJg02Dao getEntityMapper(){        
        return enrJg02Dao;
    }
    
}
