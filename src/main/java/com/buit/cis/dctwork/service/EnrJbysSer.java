package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.EnrJbysDao;
import com.buit.cis.dctwork.model.EnrJbys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class EnrJbysSer extends BaseManagerImp<EnrJbys,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EnrJbysSer.class);
    
    @Autowired
    private EnrJbysDao enrJbysDao;
    
    @Override
    public EnrJbysDao getEntityMapper(){        
        return enrJbysDao;
    }
    
}
