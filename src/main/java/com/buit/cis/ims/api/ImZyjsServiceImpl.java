package com.buit.cis.ims.api;

import com.buit.cis.im.service.ImZyjsService;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.cis.ims.service.ImHzrySer;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@DubboService(timeout = 10000)
public class ImZyjsServiceImpl implements ImZyjsService {
    @Autowired
    private ZyjsPreService zyjsPreService;
    @Autowired
    private ImHzrySer imHzrySer;

    /**
     * 获取病人最后一次结算的时间点;如果病人没有结算记录则取病人入院时间
     *
     * @param zyh 住院号
     */
    @Override
    public Timestamp getLastZyjsSj(Integer zyh) {
        ImZyjs lastRecord = zyjsPreService.getLastImZyjs(zyh);
        if (lastRecord != null) {
            return lastRecord.getZzrq();
        } else {
            return imHzrySer.getById(zyh).getRyrq();
        }
    }

    /**
     * 验证费用明细的费用日期
     *
     * @param zyh  住院号
     * @param fyrq 费用日期
     */
    @Override
    public boolean validFyrq(Integer zyh, Timestamp fyrq) {
        ImHzry hzry = imHzrySer.getById(zyh);
        ImZyjs lastRecord = zyjsPreService.getLastImZyjs(zyh);
        //费用日期最小值
        Timestamp min = null;
        if (lastRecord != null) {
            min = lastRecord.getZzrq();
        } else {
            min = hzry.getRyrq();
        }
        //费用日期最大值
        Timestamp max = null;
        if (hzry.getCyrq() != null) {
            max = hzry.getCyrq();
        }
        if (fyrq.before(min)) {
            return false;
        }
        if (max != null && fyrq.after(max)) {
            return false;
        }
        return true;
    }


}
