package com.buit.cis.nurse.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.nurse.dao.NisHljbdmxbDao;
import com.buit.cis.nurse.model.NisHljbdmxb;
import com.buit.cis.nurse.request.NisHljbdmxbReq;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 护理交班单明细表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljbdmxbSer extends BaseManagerImp<NisHljbdmxb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdmxbSer.class);
    
    @Autowired
    private NisHljbdmxbDao nisHljbdmxbDao;
    
    @Override
    public NisHljbdmxbDao getEntityMapper(){        
        return nisHljbdmxbDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;

    /**
     * 批量保存或新增交班单明细表
     * @Title: saveOrUpdateNisHljbdmxb
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @param hospitalId    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateNisHljbdmxb(List<NisHljbdmxbReq> list, Integer hospitalId) {
		for(NisHljbdmxbReq req : list) {
			NisHljbdmxb mx = BeanUtil.toBean(req, NisHljbdmxb.class);
			mx.setJgid(hospitalId);
			if(StrUtil.isBlankIfStr(mx.getJlxh())) {
				mx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJBDMXB));
				nisHljbdmxbDao.insert(mx);
			}else {
				nisHljbdmxbDao.update(mx);
			}
		}
	}
    
}
