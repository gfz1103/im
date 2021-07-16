package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisHljl;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHljlDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 一般护理记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljlSer extends BaseManagerImp<NisHljl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljlSer.class);
    
    @Autowired
    private NisHljlDao nisHljlDao;
    
    @Override
    public NisHljlDao getEntityMapper(){        
        return nisHljlDao;
    }
    
}
