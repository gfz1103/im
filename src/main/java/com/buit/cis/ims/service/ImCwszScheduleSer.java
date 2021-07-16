package com.buit.cis.ims.service;

import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImCwszjlDao;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImCwszjl;
import com.buit.cis.ims.response.ImCwszUseResp;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.RedisFactory;

import cn.hutool.core.date.DateUtil;
/**
 * 计算床位数定时任务<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImCwszScheduleSer extends BaseManagerImp<ImCwsz,String> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwszScheduleSer.class);
    
    @Autowired
    private ImCwszDao imCwszDao;

    @Override
    public ImCwszDao getEntityMapper(){        
        return imCwszDao;
    }
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @Autowired
    private ImCwszjlDao imCwszjlDao;
    
//    @Scheduled(cron = "0 1 0 * * ?")
//    @Transactional(rollbackFor = Exception.class)
//    public void imCwszScheduleTask() {
//    	HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(0);
//    	Integer jgid = hrPersonnel.getOrganizcode();
//    	List<ImCwszUseResp> list = imCwszDao.queryUseOrNotCwCount(jgid, null);
//    	for(ImCwszUseResp resp : list) {
//    		ImCwszjl imCwszjl = new ImCwszjl();
//    		imCwszjl.setJlrq(DateUtil.date().toTimestamp());
//    		imCwszjl.setBqdm(resp.getKsdm());
//    		imCwszjl.setCwsys(resp.getYsy());
//    		imCwszjl.setCwwsy(resp.getWsy());
//    		imCwszjl.setJgid(jgid);
//    		imCwszjlDao.insert(imCwszjl);
//    	}
//    } 
	
}
