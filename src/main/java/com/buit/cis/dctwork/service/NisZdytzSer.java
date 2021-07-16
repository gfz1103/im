package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.NisZdytzDao;
import com.buit.cis.dctwork.model.NisZdytz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 体温单自定义表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisZdytzSer extends BaseManagerImp<NisZdytz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisZdytzSer.class);
    
    @Autowired
    private NisZdytzDao nisZdytzDao;
    
    @Override
    public NisZdytzDao getEntityMapper(){        
        return nisZdytzDao;
    }
    
}
