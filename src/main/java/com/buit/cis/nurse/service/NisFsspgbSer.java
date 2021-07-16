package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisFsspgb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisFsspgbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisFsspgbSer extends BaseManagerImp<NisFsspgb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisFsspgbSer.class);
    
    @Autowired
    private NisFsspgbDao nisFsspgbDao;
    
    @Override
    public NisFsspgbDao getEntityMapper(){        
        return nisFsspgbDao;
    }
    
}
