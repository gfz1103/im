package com.buit.cis.ims.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.ims.dao.ImFeeFymxYjDao;
import com.buit.cis.ims.model.ImFeeFymxYj;
import com.buit.commons.BaseManagerImp;

/**
 * 费用明细表<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImFeeFymxYjSer extends BaseManagerImp<ImFeeFymxYj,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImFeeFymxYjSer.class);
    
    @Autowired
    private ImFeeFymxYjDao imFeeFymxYjDao;
    
    @Override
    public ImFeeFymxYjDao getEntityMapper(){        
        return imFeeFymxYjDao;
    }
    
}
