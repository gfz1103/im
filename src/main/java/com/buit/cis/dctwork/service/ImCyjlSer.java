package com.buit.cis.dctwork.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.ims.dao.ImCyjlDao;
import com.buit.cis.ims.model.ImCyjl;
import com.buit.commons.BaseManagerImp;
/**
 * 住院_住院记录<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImCyjlSer extends BaseManagerImp<ImCyjl,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCyjlSer.class);
    
    @Autowired
    private ImCyjlDao imCyjlDao;
    
    @Override
    public ImCyjlDao getEntityMapper(){        
        return imCyjlDao;
    }
    
}
