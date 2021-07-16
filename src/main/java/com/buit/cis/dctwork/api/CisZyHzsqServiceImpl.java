package com.buit.cis.dctwork.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.buit.cis.dctwork.dao.CisZyHzsqDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.model.CisZyHzsq;
import com.buit.cis.dctwork.response.CisZyHzsqModel;
import com.buit.cis.dctwork.service.CisZyHzsqSer;
import com.buit.cis.dctwork.service.CisZyHzsqService;
import com.buit.utill.BeanUtil;

/**
 * 类名：CisZyHzsqServiceImpl
 * 描述：会诊申请接口实现
 *
 * @author : liushijie
 * 2020/9/14
 **/
@DubboService(timeout = 10000)
public class CisZyHzsqServiceImpl implements CisZyHzsqService {
    @Autowired
    CisZyHzsqSer cisZyHzsqSer;
    @Autowired
    CisZyHzsqDao cisZyHzsqDao;

    /**
     * 查询会诊申请
     * @param zyh
     * @return
     */
    @Override
    public List<CisZyHzsqModel> queryHzsq(String zyh) {
        CisZyHzsq condition = new CisZyHzsq();
        condition.setJzhm(zyh);
        condition.setZfbz(0);
        List<CisZyHzsq> dataList = cisZyHzsqSer.findByEntity(condition);
        List<CisZyHzsqModel> retList = new ArrayList<>();
        if (dataList.size() > 0) {
            for (CisZyHzsq cisZyHzsq : dataList) {
                CisZyHzsqModel model = BeanUtil.toBean(cisZyHzsq, CisZyHzsqModel.class);
                retList.add(model);
            }
        }
        return retList;
    }

    /**
     * 查询会诊申请 - 电子病历数据引用
     * @param zyh
     * @return
     */
    @Override
    public List<Map<String,Object>> queryHzsqForBl(String zyh) {
        CisZyHzsq condition = new CisZyHzsq();
        condition.setJzhm(zyh);
        condition.setZfbz(0);
        List<Map<String,Object>> dataList = cisZyHzsqDao.findHzsqForBl(condition);
        return dataList;
    }
}
