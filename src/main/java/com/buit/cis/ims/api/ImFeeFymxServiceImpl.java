package com.buit.cis.ims.api;


import com.buit.cis.im.request.ImFeeFymxReq;
import com.buit.cis.im.response.PatientCostResp;
import com.buit.cis.im.service.ImFeeFymxService;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.request.CardPatientInfoCostReq;
import com.buit.cis.ims.response.CardPatientInfoCostResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.utill.BeanUtil;
import com.buit.utill.ReturnEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * 住院费用明细外部接口实现服务类
 * zhouhaisheng
 */

@DubboService(timeout = 10000)
public class ImFeeFymxServiceImpl implements ImFeeFymxService {
    @Autowired
    private ImFeeFymxDao imFeeFymxDao;
    @Autowired
    private ImHzrySer imHzrySer;
    /**
     * 批量插入费用明细
     * @param insertZyfymxReq
     */
    @Override
    public void batchInsert(List<ImFeeFymxReq> insertZyfymxReq) {
        List<ImFeeFymx> imFeeFymxList= BeanUtil.toBeans(insertZyfymxReq,ImFeeFymx.class);

        imFeeFymxDao.batchInsert(imFeeFymxList);
    }

    /**
     * 查询fysl之和
     * @param zyh
     * @param ypxh
     * @param ypcd
     * @param jgid
     * @return
     */
    @Override
    public BigDecimal sumFyslByZyhAndFyxhAndYpcdAndJgid(Integer zyh, Integer ypxh, Integer ypcd, Integer jgid) {
      return   imFeeFymxDao.sumFyslByZyhAndFyxhAndYpcdAndJgid(zyh,ypxh,ypcd,jgid);
    }

    /**
     * 保存
     * @param imFeeFymxReq
     */
    @Override
    public void insert(ImFeeFymxReq imFeeFymxReq) {
        ImFeeFymx fymx = new ImFeeFymx();
        BeanUtils.copyProperties(imFeeFymxReq, fymx);
        imFeeFymxDao.insert(fymx);
    }

    @Override
    public List<PatientCostResp> queryPatientCost(Integer zyh) {
        CardPatientInfoCostReq cardPatientInfoCoseReq=new CardPatientInfoCostReq();
        cardPatientInfoCoseReq.setZyh(zyh);
        cardPatientInfoCoseReq.setJslx("5");

        ReturnEntity<List<CardPatientInfoCostResp>> returnEntity= imHzrySer.queryCardPatientCostInfoList(cardPatientInfoCoseReq);

        return BeanUtil.toBeans(returnEntity.getData(),PatientCostResp.class);
    }

    /**
     * 批量 查询费用明细信息
     * @param ids
     * @return
     */
    @Override
    public List<ImFeeFymxReq> queryImFeeFymxByIds(List<Integer> ids) {

        return imFeeFymxDao.queryImFeeFymxByIds(ids);
    }


}
