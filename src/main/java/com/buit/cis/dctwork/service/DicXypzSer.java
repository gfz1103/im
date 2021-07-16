package com.buit.cis.dctwork.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.DicXypzDao;
import com.buit.cis.dctwork.model.DicXypz;
import com.buit.commons.BaseManagerImp;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class DicXypzSer extends BaseManagerImp<DicXypz,String> {
    
    static final Logger logger = LoggerFactory.getLogger(DicXypzSer.class);
    
    @Autowired
    private DicXypzDao dicXypzDao;
    
    @Override
    public DicXypzDao getEntityMapper(){        
        return dicXypzDao;
    }
    
}
