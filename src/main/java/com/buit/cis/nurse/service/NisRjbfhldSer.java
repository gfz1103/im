package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisRjbfhldDao;
import com.buit.cis.nurse.model.NisRjbfhld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 日间病房护理记录单<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisRjbfhldSer extends BaseManagerImp<NisRjbfhld,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisRjbfhldSer.class);
    
    @Autowired
    private NisRjbfhldDao nisRjbfhldDao;
    
    @Override
    public NisRjbfhldDao getEntityMapper(){        
        return nisRjbfhldDao;
    }
    
}
