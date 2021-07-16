package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisYcwxpgb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisYcwxpgbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 住院患者压疮危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisYcwxpgbSer extends BaseManagerImp<NisYcwxpgb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisYcwxpgbSer.class);
    
    @Autowired
    private NisYcwxpgbDao nisYcwxpgbDao;
    
    @Override
    public NisYcwxpgbDao getEntityMapper(){        
        return nisYcwxpgbDao;
    }
    
}
