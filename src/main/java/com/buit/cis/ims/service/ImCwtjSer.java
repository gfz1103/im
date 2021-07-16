package com.buit.cis.ims.service;


import com.buit.cis.ims.dao.ImCwtjDao;
import com.buit.cis.ims.model.ImCwtj;
import com.buit.commons.BaseManagerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 住院_床位统计<br>
 * @author ZHOUHAISHENG
 */
@Service
public class ImCwtjSer extends BaseManagerImp<ImCwtj,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwtjSer.class);
    
    @Autowired
    private ImCwtjDao imCwtjDao;
    
    @Override
    public ImCwtjDao getEntityMapper(){        
        return imCwtjDao;
    }
    
}
