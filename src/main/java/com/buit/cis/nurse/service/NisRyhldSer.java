package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisRyhld;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisRyhldDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 入院护理评估单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisRyhldSer extends BaseManagerImp<NisRyhld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisRyhldSer.class);
    
    @Autowired
    private NisRyhldDao nisRyhldDao;
    
    @Override
    public NisRyhldDao getEntityMapper(){        
        return nisRyhldDao;
    }
    
}
