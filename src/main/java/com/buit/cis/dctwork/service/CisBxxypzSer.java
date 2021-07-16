package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisBxxypzDao;
import com.buit.cis.dctwork.model.CisBxxypz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class CisBxxypzSer extends BaseManagerImp<CisBxxypz,String> {
    
    static final Logger logger = LoggerFactory.getLogger(CisBxxypzSer.class);
    
    @Autowired
    private CisBxxypzDao CisBxxypzDao;
    
    @Override
    public CisBxxypzDao getEntityMapper(){        
        return CisBxxypzDao;
    }
    
}
