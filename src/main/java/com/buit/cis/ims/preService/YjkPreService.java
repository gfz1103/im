package com.buit.cis.ims.preService;

import cn.hutool.core.collection.CollectionUtil;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImTbkkDao;
import com.buit.cis.ims.enums.JklxEnum;
import com.buit.cis.ims.model.ImTbkk;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.utill.DateUtils;
import com.buit.utill.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YjkPreService {

    @Autowired
    ImTbkkDao imTbkkDao;
    @Autowired
    ImFeeFymxDao imFeeFymxDao;
    @Autowired
    private RedisFactory redisFactory;

    /**
     * 根据收据号码查询 预缴款记录
     * @param sjhm 收据号码
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ImTbkk queryBySjhm(String sjhm, SysUser sysUser){
        ImTbkk tbkk = new ImTbkk();
        tbkk.setSjhm(sjhm);
        tbkk.setJgid(sysUser.getHospitalId());
        List<ImTbkk> list = imTbkkDao.findByEntity(tbkk);
        if(CollectionUtil.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 缴款注销
     * @param imTbkk 需要注销的记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancel(ImTbkk imTbkk, SysUser sysUser){
        //原记录增加注销操作员工号
        imTbkk.setZfrq(DateUtils.getNow());
        imTbkk.setZfgh(sysUser.getUserId());
        imTbkk.setJgid(sysUser.getHospitalId());
        imTbkkDao.patientPayCancel(imTbkk);

        //增加一条抵扣的注销记录
        ImTbkk refund = new ImTbkk();
        refund.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
        refund.setJgid(sysUser.getHospitalId());
        refund.setZyh(imTbkk.getZyh());
        refund.setJkje(imTbkk.getJkje().negate());
        refund.setJkfs(imTbkk.getJkfs());
        refund.setJkrq(imTbkk.getZfrq());
        refund.setSjhm(imTbkk.getSjhm());
        refund.setCzgh(imTbkk.getCzgh());
        refund.setJscs(0);
        refund.setZcpb(0);
        refund.setJklx(JklxEnum.REFUND_UNDO.getValue());
        imTbkkDao.insert(refund);
    }
}
