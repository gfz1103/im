package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisYzdyDao;
import com.buit.cis.nurse.model.NisYzdy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class NisYzdySer extends BaseManagerImp<NisYzdy,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisYzdySer.class);
    
    @Autowired
    private NisYzdyDao nisYzdyDao;
    
    @Override
    public NisYzdyDao getEntityMapper(){        
        return nisYzdyDao;
    }
    
}
