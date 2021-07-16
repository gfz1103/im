package com.buit.cis.nurse.preSevice;

import cn.hutool.core.collection.CollectionUtil;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.nurse.enums.FymxZfpbEnum;
import com.buit.cis.nurse.enums.JzlxEnum;
import com.buit.constans.TableName;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 费用记账预处理
 */
@Service
public class NurseWorkPreService {

    @Autowired
    ImFeeFymxDao imFeeFymxDao;
    @Autowired
    RedisFactory redisFactory;

    /**
     * 查找患者某个费用项目尚未结算的所有数量(用于补记帐负数量退帐判断)
     *
     * @param zyh  住院号
     * @param yplx 药品类型
     * @param fyxh 收費項目序号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Double getFyslCount(Integer zyh, Integer yplx, Integer fyxh) {
        BigDecimal result = BigDecimal.ZERO;

        ImFeeFymx condition = new ImFeeFymx();
        condition.setZyh(zyh);
        condition.setYplx(yplx);
        condition.setFyxh(fyxh);
        condition.setXmlx(JzlxEnum.IN_HOSPITAL.getValue());
        condition.setJscs(0);

        List<ImFeeFymx> list = imFeeFymxDao.findByEntity(condition);
        if (CollectionUtil.isNotEmpty(list)) {
            for (ImFeeFymx fymx : list) {
                result = result.add(fymx.getFysl());
            }
        }
        return result.doubleValue();
    }

    /**
     * 根据需要作废的记账信息 ，新增一条抵扣的记账信息
     *
     * @param entry 该次作废的明细
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addNewNegateRecord(ImFeeFymx entry, Integer userId) {
        //增加一条抵扣的新记录
        ImFeeFymx newRecord = entry;
        newRecord.setZfpb(FymxZfpbEnum.REFUND.getValue());
        newRecord.setJfrq(entry.getZfrq());
        newRecord.setZfrq(null);
        newRecord.setZfgh(null);
        newRecord.setZfglxh(String.valueOf(entry.getJlxh()));
        Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX);
        newRecord.setJlxh(jlxh);
        newRecord.setSrgh(ObjectToTypes.parseString(userId));
        newRecord.setFysl(entry.getFysl().negate());
        newRecord.setZfje(entry.getZfje().negate());
        newRecord.setZjje(entry.getZjje().negate());
        if (entry.getZlje() != null) {
            newRecord.setZlje(entry.getZlje().negate());
        }
        if (entry.getZkje() != null) {
            newRecord.setZkje(entry.getZkje().negate());
        }
        if (entry.getZhje() != null) {
            newRecord.setZhje(entry.getZhje().negate());
        }
        imFeeFymxDao.insert(newRecord);
        return jlxh;
    }
}
