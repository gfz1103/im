package com.buit.cis.ims.service;


import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.cis.ims.dao.YjZdjgDao;
import com.buit.cis.ims.model.YjZdjg;
import com.buit.cis.ims.request.YjZdjgReq;
import com.buit.utill.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * @author ZHOUHAISHENG
 */
@Service
public class YjZdjgSer extends BaseManagerImp<YjZdjg,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(YjZdjgSer.class);
    
    @Autowired
    private YjZdjgDao yjZdjgDao;
    @Autowired
    private RedisFactory redisFactory;
    @Override
    public YjZdjgDao getEntityMapper(){        
        return yjZdjgDao;
    }

    public void insert(YjZdjgReq yjZdjgReq, SysUser user) {
        YjZdjg  yjZdjg=new YjZdjg();
        BeanUtils.copyProperties(yjZdjgReq,yjZdjg);
        yjZdjg.setJgid(user.getHospitalId());
        yjZdjg.setZdid(redisFactory.getTableKey(TableName.DB_NAME, TableName.YJ_ZDJG));
       // yjZdjg.setKsdm(user.getKsdm());
        yjZdjgDao.insert(yjZdjg);
    }
}
