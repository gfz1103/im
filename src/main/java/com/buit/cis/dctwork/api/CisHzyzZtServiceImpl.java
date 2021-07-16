package com.buit.cis.dctwork.api;

import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.response.CisHzyzZtModel;
import com.buit.cis.dctwork.service.CisHzyzZtService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 医嘱组套对外相关接口实现
 */
@DubboService(timeout = 10000)
public class CisHzyzZtServiceImpl implements CisHzyzZtService {
    @Autowired
    private CisHzyzZtDao cisHzyzZtDao;




    @Override
    public void insert(CisHzyzZtModel cisHzyzZt) {
        cisHzyzZtDao.insert(cisHzyzZt);
    }
}
