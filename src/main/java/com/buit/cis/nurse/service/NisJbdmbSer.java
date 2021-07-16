package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisJbdmbDao;
import com.buit.cis.nurse.model.NisJbdmb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理交班单模板<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisJbdmbSer extends BaseManagerImp<NisJbdmb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisJbdmbSer.class);
    
    @Autowired
    private NisJbdmbDao nisJbdmbDao;
    
    @Override
    public NisJbdmbDao getEntityMapper(){        
        return nisJbdmbDao;
    }
    
}
