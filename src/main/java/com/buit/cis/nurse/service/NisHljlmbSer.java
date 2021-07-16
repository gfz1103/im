package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisHljlmb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHljlmbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理记录模板<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljlmbSer extends BaseManagerImp<NisHljlmb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljlmbSer.class);
    
    @Autowired
    private NisHljlmbDao nisHljlmbDao;
    
    @Override
    public NisHljlmbDao getEntityMapper(){        
        return nisHljlmbDao;
    }
    
}
