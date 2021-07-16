package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.NisTzxmDao;
import com.buit.cis.dctwork.model.NisTzxm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTzxmSer extends BaseManagerImp<NisTzxm,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTzxmSer.class);
    
    @Autowired
    private NisTzxmDao nisTzxmDao;
    
    @Override
    public NisTzxmDao getEntityMapper(){        
        return nisTzxmDao;
    }
    
}
