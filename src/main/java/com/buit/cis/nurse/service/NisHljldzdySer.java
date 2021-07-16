package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHljldzdyDao;
import com.buit.cis.nurse.model.NisHljldzdy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理记录单自定义内容<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljldzdySer extends BaseManagerImp<NisHljldzdy,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljldzdySer.class);
    
    @Autowired
    private NisHljldzdyDao nisHljldzdyDao;
    
    @Override
    public NisHljldzdyDao getEntityMapper(){        
        return nisHljldzdyDao;
    }
    
}
