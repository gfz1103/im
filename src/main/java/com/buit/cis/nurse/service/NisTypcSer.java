package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisTypcDao;
import com.buit.cis.nurse.model.NisTypc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTypcSer extends BaseManagerImp<NisTypc,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTypcSer.class);
    
    @Autowired
    private NisTypcDao nisTypcDao;
    
    @Override
    public NisTypcDao getEntityMapper(){        
        return nisTypcDao;
    }
    
}
