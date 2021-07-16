package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.CisKpbzmxDao;
import com.buit.cis.nurse.model.CisKpbzmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 包装明细<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisKpbzmxSer extends BaseManagerImp<CisKpbzmx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisKpbzmxSer.class);
    
    @Autowired
    private CisKpbzmxDao cisKpbzmxDao;
    
    @Override
    public CisKpbzmxDao getEntityMapper(){        
        return cisKpbzmxDao;
    }
    
}
