package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.NisTymxDao;
import com.buit.cis.dctwork.model.NisTymx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 病区_退药明细<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTymxSer extends BaseManagerImp<NisTymx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTymxSer.class);
    
    @Autowired
    private NisTymxDao nisTymxDao;
    
    @Override
    public NisTymxDao getEntityMapper(){        
        return nisTymxDao;
    }
    
}
