package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.CisYzkpdyjlDao;
import com.buit.cis.nurse.model.CisYzkpdyjl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 住院医嘱医嘱卡片打印记录<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisYzkpdyjlSer extends BaseManagerImp<CisYzkpdyjl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisYzkpdyjlSer.class);
    
    @Autowired
    private CisYzkpdyjlDao cisYzkpdyjlDao;
    
    @Override
    public CisYzkpdyjlDao getEntityMapper(){        
        return cisYzkpdyjlDao;
    }
    
}
