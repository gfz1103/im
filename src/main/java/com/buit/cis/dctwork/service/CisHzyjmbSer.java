package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.CisHzyjmbDao;
import com.buit.cis.dctwork.model.CisHzyjmb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 会诊意见模板维护表<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisHzyjmbSer extends BaseManagerImp<CisHzyjmb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyjmbSer.class);
    
    @Autowired
    private CisHzyjmbDao cisHzyjmbDao;
    
    @Override
    public CisHzyjmbDao getEntityMapper(){        
        return cisHzyjmbDao;
    }
    
}
