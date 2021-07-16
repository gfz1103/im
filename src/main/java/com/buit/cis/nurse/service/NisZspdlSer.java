package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisZspdl;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisZspdlDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Barthel指数平定量表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisZspdlSer extends BaseManagerImp<NisZspdl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisZspdlSer.class);
    
    @Autowired
    private NisZspdlDao nisZspdlDao;
    
    @Override
    public NisZspdlDao getEntityMapper(){        
        return nisZspdlDao;
    }
    
}
