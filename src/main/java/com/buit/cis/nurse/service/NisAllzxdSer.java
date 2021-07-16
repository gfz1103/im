package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisAllzxd;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisAllzxdDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 所有护理执行单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisAllzxdSer extends BaseManagerImp<NisAllzxd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisAllzxdSer.class);
    
    @Autowired
    private NisAllzxdDao nisAllzxdDao;
    
    @Override
    public NisAllzxdDao getEntityMapper(){        
        return nisAllzxdDao;
    }
    
}
