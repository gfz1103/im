package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisZxjmpgb;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisZxjmpgbDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 中心静脉导管相关性感染风险因素评估表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisZxjmpgbSer extends BaseManagerImp<NisZxjmpgb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisZxjmpgbSer.class);
    
    @Autowired
    private NisZxjmpgbDao nisZxjmpgbDao;
    
    @Override
    public NisZxjmpgbDao getEntityMapper(){        
        return nisZxjmpgbDao;
    }
    
}
