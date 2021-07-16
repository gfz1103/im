package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisZyHzyjDao;
import com.buit.cis.dctwork.model.CisZyHzyj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class CisZyHzyjSer extends BaseManagerImp<CisZyHzyj,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzyjSer.class);
    
    @Autowired
    private CisZyHzyjDao cisZyHzyjDao;
    
    @Override
    public CisZyHzyjDao getEntityMapper(){        
        return cisZyHzyjDao;
    }
    
}
