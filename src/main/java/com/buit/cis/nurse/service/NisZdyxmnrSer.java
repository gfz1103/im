package com.buit.cis.nurse.service;


import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.system.utill.ObjectToTypes;
import com.buit.cis.nurse.dao.NisHljldzdyDao;
import com.buit.cis.nurse.dao.NisZdyxmnrDao;
import com.buit.cis.nurse.model.NisHljldzdy;
import com.buit.cis.nurse.model.NisZdyxmnr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 自定义专科护理内容<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisZdyxmnrSer extends BaseManagerImp<NisZdyxmnr,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisZdyxmnrSer.class);
    
    @Autowired
    private NisZdyxmnrDao nisZdyxmnrDao;
    
    @Override
    public NisZdyxmnrDao getEntityMapper(){        
        return nisZdyxmnrDao;
    }

    @Autowired
    private NisHljldzdyDao nisHljldzdyDao;
    
	public void deleteXmnrById(Integer xmnrdm, Integer jgid) {
		NisHljldzdy nisHljldzdyNew = new NisHljldzdy();
		nisHljldzdyNew.setZdynr(ObjectToTypes.parseString(xmnrdm));
		nisHljldzdyNew.setJgid(jgid);
		long countNew = nisHljldzdyDao.findByEntityCount(nisHljldzdyNew);
		if(countNew > 0) {
			throw BaseException.create("ERROR_NURSEWORK_HLJL_0002"); 	
		}
		nisZdyxmnrDao.deleteById(xmnrdm);
	}
    
}
