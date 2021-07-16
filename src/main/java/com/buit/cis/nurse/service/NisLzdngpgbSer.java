package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisLzdngpgb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisLzdngpgbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 留置导尿管感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisLzdngpgbSer extends BaseManagerImp<NisLzdngpgb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisLzdngpgbSer.class);
    
    @Autowired
    private NisLzdngpgbDao nisLzdngpgbDao;
    
    @Override
    public NisLzdngpgbDao getEntityMapper(){        
        return nisLzdngpgbDao;
    }
    
}
