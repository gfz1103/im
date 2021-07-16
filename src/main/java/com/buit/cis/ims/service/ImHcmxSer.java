package com.buit.cis.ims.service;


import com.buit.cis.ims.dao.ImHcmxDao;
import com.buit.cis.ims.model.ImHcmx;
import com.buit.commons.BaseManagerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 住院_换床明细<br>
 * @author ZHOUHAISHENG
 */
@Service
public class ImHcmxSer extends BaseManagerImp<ImHcmx,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(ImHcmxSer.class);
    
    @Autowired
    private ImHcmxDao imHcmxDao;
    
    @Override
    public ImHcmxDao getEntityMapper(){        
        return imHcmxDao;
    }
    
}
