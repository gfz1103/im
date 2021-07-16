package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisHlmbwhbDao;
import com.buit.cis.nurse.model.NisHlmbwhb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 护理单模板维护表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHlmbwhbSer extends BaseManagerImp<NisHlmbwhb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHlmbwhbSer.class);
    
    @Autowired
    private NisHlmbwhbDao nisHlmbwhbDao;
    
    @Override
    public NisHlmbwhbDao getEntityMapper(){        
        return nisHlmbwhbDao;
    }
    
}
