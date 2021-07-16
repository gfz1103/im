package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.SkinPsjlDao;
import com.buit.cis.dctwork.model.SkinPsjl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@Service
public class SkinPsjlSer extends BaseManagerImp<SkinPsjl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(SkinPsjlSer.class);
    
    @Autowired
    private SkinPsjlDao skinPsjlDao;
    
    @Override
    public SkinPsjlDao getEntityMapper(){        
        return skinPsjlDao;
    }
    
}
