package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.CisKfdDao;
import com.buit.cis.nurse.model.CisKfd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 口服单<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisKfdSer extends BaseManagerImp<CisKfd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisKfdSer.class);
    
    @Autowired
    private CisKfdDao cisKfdDao;
    
    @Override
    public CisKfdDao getEntityMapper(){        
        return cisKfdDao;
    }
    
}
