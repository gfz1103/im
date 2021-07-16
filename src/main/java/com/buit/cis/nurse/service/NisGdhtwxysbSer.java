package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisGdhtwxysb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisGdhtwxysbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 住院患者管道滑脱危险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisGdhtwxysbSer extends BaseManagerImp<NisGdhtwxysb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisGdhtwxysbSer.class);
    
    @Autowired
    private NisGdhtwxysbDao nisGdhtwxysbDao;
    
    @Override
    public NisGdhtwxysbDao getEntityMapper(){        
        return nisGdhtwxysbDao;
    }
    
}
