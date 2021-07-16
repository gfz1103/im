package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHljhdmbDao;
import com.buit.cis.nurse.model.NisHljhdmb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理执行单模板<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljhdmbSer extends BaseManagerImp<NisHljhdmb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdmbSer.class);
    
    @Autowired
    private NisHljhdmbDao nisHljhdmbDao;
    
    @Override
    public NisHljhdmbDao getEntityMapper(){        
        return nisHljhdmbDao;
    }
    
}
