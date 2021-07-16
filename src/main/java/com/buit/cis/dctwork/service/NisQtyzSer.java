package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.commons.SysUser;
import com.buit.utill.RedisFactory;

import cn.hutool.core.util.StrUtil;

import com.buit.cis.dctwork.dao.NisQtyzDao;
import com.buit.cis.dctwork.model.NisQtyz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class NisQtyzSer extends BaseManagerImp<NisQtyz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisQtyzSer.class);
    
    @Autowired
    private NisQtyzDao nisQtyzDao;
    
    @Override
    public NisQtyzDao getEntityMapper(){        
        return nisQtyzDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    /**
    * @Title: addQtyz
    * @Description: TODO 其他医嘱新增或修改
    * @param @param yzxh
    * @param @param yzmc
    * @param @param srdm
    * @param @param user    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    public void addQtyz(Integer yzxh, String yzmc, String srdm, SysUser user) {
    	NisQtyz nisQtyz = new NisQtyz();
    	nisQtyz.setYzmc(yzmc);
    	nisQtyz.setYzlb(0);
    	nisQtyz.setZfbz(0);
    	nisQtyz.setYzpx(0);
    	nisQtyz.setJgid(user.getHospitalId());
    	if(StrUtil.isNotBlank(srdm)) {
    		nisQtyz.setSrdm(srdm);
    	}
    	if(yzxh != null) {
    		nisQtyz.setYzxh(yzxh);
    		nisQtyzDao.update(nisQtyz);
    	}else {
    		nisQtyz.setYzxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_QTYZ));
    		nisQtyzDao.insert(nisQtyz);
    	}
    }
}
