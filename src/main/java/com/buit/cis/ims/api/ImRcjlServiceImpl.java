package com.buit.cis.ims.api;

import com.buit.cis.im.response.ImRcjlModel;
import com.buit.cis.im.service.ImRcjlService;
import com.buit.cis.ims.dao.ImRcjlDao;
import com.buit.cis.ims.model.ImRcjl;
import com.buit.utill.BeanUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 入出记录查询
 * zhouhaisheng
 */
@DubboService(timeout = 10000)
public class ImRcjlServiceImpl implements ImRcjlService {
    @Autowired
    private ImRcjlDao imRcjlDao;

    @Override
    public List<ImRcjlModel> queryRcjl(Integer zyh) {
        ImRcjl imRcjl=new ImRcjl();
        imRcjl.setZyh(zyh);
        List<ImRcjl> imRcjlList=imRcjlDao.findByEntity(imRcjl);

        return BeanUtil.toBeans(imRcjlList,ImRcjlModel.class);
    }
}
