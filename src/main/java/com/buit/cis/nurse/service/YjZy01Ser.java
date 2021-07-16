package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.YjZy01Dao;
import com.buit.cis.nurse.model.YjZy01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class YjZy01Ser extends BaseManagerImp<YjZy01,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(YjZy01Ser.class);
    
    @Autowired
    private YjZy01Dao yjZy01Dao;
    
    @Override
    public YjZy01Dao getEntityMapper(){        
        return yjZy01Dao;
    }
    
}
