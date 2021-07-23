package com.buit.cis.ims.service;


import com.buit.cis.im.response.ImRyzdModel;
import com.buit.cis.ims.dao.ImRyzdDao;
import com.buit.cis.ims.model.ImRyzd;
import com.buit.commons.BaseManagerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 住院_入院诊断<br>
 *
 * @author ZHOUHAISHENG
 */
@Service
public class ImRyzdSer extends BaseManagerImp<ImRyzd, Long> {

    static final Logger logger = LoggerFactory.getLogger(ImRyzdSer.class);

    @Autowired
    private ImRyzdDao imRyzdDao;

    @Override
    public ImRyzdDao getEntityMapper() {
        return imRyzdDao;
    }

    public void updateAdmittingDiagnosis(Integer zyh, Integer zdxh) {
        imRyzdDao.updateAdmittingDiagnosis(zyh, zdxh);
    }

    /**
     * 查询病人所有诊断
     *
     * @param zyh  住院号
     * @param zxlb 中西类别 1：西医 2：中医
     * @return
     */
    public List<ImRyzdModel> findListByZyhAndZxlb(Integer zyh, int zxlb) {
        return imRyzdDao.findListByZyhAndZxlb(zyh, zxlb);
    }
}
