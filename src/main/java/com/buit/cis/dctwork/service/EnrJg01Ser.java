package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.EnrJg01Dao;
import com.buit.cis.dctwork.model.EnrJg01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class EnrJg01Ser extends BaseManagerImp<EnrJg01,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EnrJg01Ser.class);
    
    @Autowired
    private EnrJg01Dao enrJg01Dao;
    
    @Override
    public EnrJg01Dao getEntityMapper(){        
        return enrJg01Dao;
    }
    
}
