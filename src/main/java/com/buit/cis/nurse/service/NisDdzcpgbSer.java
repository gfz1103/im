package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisDdzcpgb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisDdzcpgbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 住院患者跌倒、坠床危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisDdzcpgbSer extends BaseManagerImp<NisDdzcpgb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisDdzcpgbSer.class);
    
    @Autowired
    private NisDdzcpgbDao nisDdzcpgbDao;
    
    @Override
    public NisDdzcpgbDao getEntityMapper(){        
        return nisDdzcpgbDao;
    }
    
}
