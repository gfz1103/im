package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.NisTfmxDao;
import com.buit.cis.dctwork.model.NisTfmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTfmxSer extends BaseManagerImp<NisTfmx,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTfmxSer.class);
    
    @Autowired
    private NisTfmxDao nisTfmxDao;
    
    @Override
    public NisTfmxDao getEntityMapper(){        
        return nisTfmxDao;
    }
    
}
