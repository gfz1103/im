package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisBqyjpgd;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisBqyjpgdDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>患者病情预警评估单
 * @author GONGFANGZHOU
 */
@Service
public class NisBqyjpgdSer extends BaseManagerImp<NisBqyjpgd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisBqyjpgdSer.class);
    
    @Autowired
    private NisBqyjpgdDao nisBqyjpgdDao;
    
    @Override
    public NisBqyjpgdDao getEntityMapper(){        
        return nisBqyjpgdDao;
    }
    
}
