package com.buit.cis.ims.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.ims.dao.ImRcjlDao;
import com.buit.cis.ims.model.ImRcjl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 住院_临床病人入出记录<br>
 * @author ZHOUHAISHENG
 */
@Service
public class ImRcjlSer extends BaseManagerImp<ImRcjl,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(ImRcjlSer.class);
    
    @Autowired
    private ImRcjlDao imRcjlDao;
    
    @Override
    public ImRcjlDao getEntityMapper(){        
        return imRcjlDao;
    }
    
}
