package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.cis.nurse.dao.ZyYbxmfzClDao;
import com.buit.cis.nurse.model.ZyYbxmfzCl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 医保项目分组(材料)<br>
 * @author GONGFANGZHOU
 */
@Service
public class ZyYbxmfzClSer extends BaseManagerImp<ZyYbxmfzCl,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ZyYbxmfzClSer.class);
    
    @Autowired
    private ZyYbxmfzClDao zyYbxmfzClDao;
    
    @Override
    public ZyYbxmfzClDao getEntityMapper(){        
        return zyYbxmfzClDao;
    }
    
}
