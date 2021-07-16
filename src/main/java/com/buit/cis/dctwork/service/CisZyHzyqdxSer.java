package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisZyHzyqdxDao;
import com.buit.cis.dctwork.model.CisZyHzyqdx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class CisZyHzyqdxSer extends BaseManagerImp<CisZyHzyqdx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisZyHzyqdxSer.class);
    
    @Autowired
    private CisZyHzyqdxDao cisZyHzyqdxDao;
    
    @Override
    public CisZyHzyqdxDao getEntityMapper(){        
        return cisZyHzyqdxDao;
    }
    
}
