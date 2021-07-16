package com.buit.cis.nurse.service;


import com.buit.cis.nurse.model.NisFyfxpgd;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.NisFyfxpgdDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>医院获得性肺炎风险因素评估单
 * @author GONGFANGZHOU
 */
@Service
public class NisFyfxpgdSer extends BaseManagerImp<NisFyfxpgd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisFyfxpgdSer.class);
    
    @Autowired
    private NisFyfxpgdDao nisFyfxpgdDao;
    
    @Override
    public NisFyfxpgdDao getEntityMapper(){        
        return nisFyfxpgdDao;
    }
    
}
