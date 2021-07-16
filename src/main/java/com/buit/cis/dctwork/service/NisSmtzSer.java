package com.buit.cis.dctwork.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.NisSmtzDao;
import com.buit.cis.dctwork.model.NisSmtz;
import com.buit.commons.BaseManagerImp;
/**
 * 生命体征<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisSmtzSer extends BaseManagerImp<NisSmtz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisSmtzSer.class);
    
    @Autowired
    private NisSmtzDao nisSmtzDao;
    
    @Override
    public NisSmtzDao getEntityMapper(){        
        return nisSmtzDao;
    }
    
}
