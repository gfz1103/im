package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisFssrisk;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisFssriskDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 肺栓塞风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisFssriskSer extends BaseManagerImp<NisFssrisk,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisFssriskSer.class);
    
    @Autowired
    private NisFssriskDao nisFssriskDao;
    
    @Override
    public NisFssriskDao getEntityMapper(){        
        return nisFssriskDao;
    }
    
}
