package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisYlxsszg;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisYlxsszgDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 压力性损伤预报、传报单(转归情况)<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisYlxsszgSer extends BaseManagerImp<NisYlxsszg,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxsszgSer.class);
    
    @Autowired
    private NisYlxsszgDao nisYlxsszgDao;
    
    @Override
    public NisYlxsszgDao getEntityMapper(){        
        return nisYlxsszgDao;
    }
    
}
