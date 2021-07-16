package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.PubCsrzDao;
import com.buit.cis.dctwork.model.PubCsrz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class PubCsrzSer extends BaseManagerImp<PubCsrz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(PubCsrzSer.class);
    
    @Autowired
    private PubCsrzDao gyCsrzDao;
    
    @Override
    public PubCsrzDao getEntityMapper(){        
        return gyCsrzDao;
    }
    
}
