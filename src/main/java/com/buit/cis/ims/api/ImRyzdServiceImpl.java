package com.buit.cis.ims.api;


import com.buit.cis.im.response.ImRyzdMedicalRecord;
import com.buit.cis.im.response.ImRyzdModel;
import com.buit.cis.im.service.ImRyzdService;
import com.buit.cis.ims.dao.ImRyzdDao;
import com.buit.cis.ims.model.ImRyzd;
import com.buit.cis.ims.service.ImRyzdSer;
import com.buit.utill.BeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类名：ImRyzdServiceImpl
 * 描述：住院入院诊断接口实现
 *
 * @author : liushijie
 * 2020/9/15
 **/
@DubboService(timeout = 10000)
public class ImRyzdServiceImpl implements ImRyzdService {
    @Autowired
    private ImRyzdSer imRyzdSer;
    @Autowired
    private ImRyzdDao imRyzdDao;

    /**
     * 查询入院诊断
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public ImRyzdModel queryRyzd(Integer zyh) {
        ImRyzd condition = new ImRyzd();
        condition.setZyh(zyh);
        // 查询主诊断
        condition.setZdlb(3);
        List<ImRyzd> dataList = imRyzdSer.findByEntity(condition);
        ImRyzdModel retModel = new ImRyzdModel();
        if (dataList.size() > 0) {
            ImRyzd ryzd = dataList.get(0);
            retModel = BeanUtil.toBean(ryzd, ImRyzdModel.class);
        }
        return retModel;
    }

    @Override
    public List<String> queryZdmcByZyh(String zyh) {
        return imRyzdSer.getEntityMapper().queryZdmcByZyh(zyh);
    }

    /**
     * 查询病案首页诊断信息
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @throws
     * @Title: queryZdInfoMedicalRecord
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    @Override
    public List<ImRyzdMedicalRecord> queryZdInfoMedicalRecord(Integer zyh) {
        return imRyzdSer.getEntityMapper().queryZdInfoMedicalRecord(zyh);
    }

    /**
     * 查询对应类别的所有诊断（包含中医和西医）
     *
     * @param zyh  住院号
     * @param zdlb 诊断类别
     */
    @Override
    public List<ImRyzdModel> findList(Integer zyh, Integer zdlb) {
        return imRyzdDao.findList(zyh, zdlb);
    }
}
