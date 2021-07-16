package com.buit.cis.dctwork.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.buit.cis.dctwork.dao.CisZyHzyjDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.model.CisZyHzyj;
import com.buit.cis.dctwork.response.CisZyHzyjModel;
import com.buit.cis.dctwork.service.CisZyHzyjSer;
import com.buit.cis.dctwork.service.CisZyHzyjService;
import com.buit.utill.BeanUtil;

/**
 * 类名：CisZyHzyjServiceimpl
 * 描述：会诊意见接口实现
 *
 * @author : liushijie
 * 2020/9/14
 **/
@DubboService(timeout = 10000)
public class CisZyHzyjServiceImpl implements CisZyHzyjService {
    @Autowired
    CisZyHzyjSer cisZyHzyjSer;
    @Autowired
    CisZyHzyjDao cisZyHzyjDao;

    /**
     * 查询会诊意见
     * @param sqxh
     * @return
     */
    @Override
    public List<CisZyHzyjModel> queryHzyj(Integer sqxh) {
        CisZyHzyj condition = new CisZyHzyj();
        condition.setSqxh(sqxh);
        List<CisZyHzyj> dataList = cisZyHzyjSer.findByEntity(condition);
        List<CisZyHzyjModel> retList = new ArrayList<>();
        for (CisZyHzyj cisZyHzyj : dataList) {
            CisZyHzyjModel model = BeanUtil.toBean(cisZyHzyj, CisZyHzyjModel.class);
            retList.add(model);
        }
        return retList;
    }

    /**
     * 查询会诊意见 - 电子病历数据引用
     * @param sqxh
     * @return
     */
    @Override
    public List<Map<String, Object>> queryHzyjForBl(Integer sqxh) {
        CisZyHzyj condition = new CisZyHzyj();
        condition.setSqxh(sqxh);
        List<Map<String, Object>> dataList = cisZyHzyjDao.findHzyjForBl(condition);
        return dataList;
    }
}
