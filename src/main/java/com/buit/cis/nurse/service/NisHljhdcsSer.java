package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHljhdcsDao;
import com.buit.cis.nurse.model.NisHljhdcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理执行单子表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljhdcsSer extends BaseManagerImp<NisHljhdcs,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdcsSer.class);
    
    @Autowired
    private NisHljhdcsDao nisHljhdcsDao;
    
    @Override
    public NisHljhdcsDao getEntityMapper(){        
        return nisHljhdcsDao;
    }
    
}
