package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisCqhld;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisCqhldDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 产前护理记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisCqhldSer extends BaseManagerImp<NisCqhld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisCqhldSer.class);
    
    @Autowired
    private NisCqhldDao nisCqhldDao;
    
    @Override
    public NisCqhldDao getEntityMapper(){        
        return nisCqhldDao;
    }
    
}
