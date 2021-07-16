package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisYlxssjksf;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisYlxssjksfDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 压力性损伤预报、传报单(监控随访)<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisYlxssjksfSer extends BaseManagerImp<NisYlxssjksf,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisYlxssjksfSer.class);
    
    @Autowired
    private NisYlxssjksfDao nisYlxssjksfDao;
    
    @Override
    public NisYlxssjksfDao getEntityMapper(){        
        return nisYlxssjksfDao;
    }
    
}
