package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisBxsqdzdDao;
import com.buit.cis.dctwork.model.CisBxsqdzd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class CisBxsqdzdSer extends BaseManagerImp<CisBxsqdzd,String> {
    
    static final Logger logger = LoggerFactory.getLogger(CisBxsqdzdSer.class);
    
    @Autowired
    private CisBxsqdzdDao cisBxsqdzdDao;
    
    @Override
    public CisBxsqdzdDao getEntityMapper(){        
        return cisBxsqdzdDao;
    }
    
}
