package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.dctwork.dao.NisTj01Dao;
import com.buit.cis.dctwork.model.NisTj01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 病区_提交记录 | 记录病区医嘱提交的记录，与提交明细表BQ_TJMX通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisTj01Ser extends BaseManagerImp<NisTj01,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisTj01Ser.class);
    
    @Autowired
    private NisTj01Dao nisTj01Dao;
    
    @Override
    public NisTj01Dao getEntityMapper(){        
        return nisTj01Dao;
    }
    
}
