package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.CisKpkfbzDao;
import com.buit.cis.nurse.model.CisKpkfbz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 口服包装<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisKpkfbzSer extends BaseManagerImp<CisKpkfbz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisKpkfbzSer.class);
    
    @Autowired
    private CisKpkfbzDao cisKpkfbzDao;
    
    @Override
    public CisKpkfbzDao getEntityMapper(){        
        return cisKpkfbzDao;
    }
    
}
