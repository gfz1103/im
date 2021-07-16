package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisChhld;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisChhldDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 产后护理记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisChhldSer extends BaseManagerImp<NisChhld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisChhldSer.class);
    
    @Autowired
    private NisChhldDao nisChhldDao;
    
    @Override
    public NisChhldDao getEntityMapper(){        
        return nisChhldDao;
    }
    
}
