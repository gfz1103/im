package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.model.CisHzyzZt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 住院_病区组套医嘱<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisHzyzZtSer extends BaseManagerImp<CisHzyzZt,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyzZtSer.class);
    
    @Autowired
    private CisHzyzZtDao cisHzyzZtDao;
    
    @Override
    public CisHzyzZtDao getEntityMapper(){        
        return cisHzyzZtDao;
    }
    
}
