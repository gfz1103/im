package com.buit.cis.ims.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.ims.dao.ImCwszjlDao;
import com.buit.cis.ims.model.ImCwszjl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 床位使用记录表<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImCwszjlSer extends BaseManagerImp<ImCwszjl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwszjlSer.class);
    
    @Autowired
    private ImCwszjlDao imCwszjlDao;
    
    @Override
    public ImCwszjlDao getEntityMapper(){        
        return imCwszjlDao;
    }
    
}
