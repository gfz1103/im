package com.buit.cis.ims.service;


import cn.hutool.core.collection.CollectionUtil;
import com.buit.aop.lock.Locked;
import com.buit.cis.ims.dao.ImDrugsOutRangeLogDao;
import com.buit.cis.ims.enums.CypbEnum;
import com.buit.cis.ims.model.ImDrugsOutRangeLog;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.utill.DateUtils;
import com.buit.utill.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jiangwei
 * @Description 药品费用超出结算时间记录表
 * @Date 2021-05-03
 */
@Service
public class ImDrugsOutRangeLogSer extends BaseManagerImp<ImDrugsOutRangeLog, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImDrugsOutRangeLogSer.class);

    @Autowired
    private ImDrugsOutRangeLogDao imDrugsOutRangeLogDao;
    @Autowired
    private ImFeeFymxSer feeFymxSer;
    @Autowired
    private ImHzrySer imHzrySer;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private ZyjsPreService zyjsPreService;

    @Override
    public ImDrugsOutRangeLogDao getEntityMapper() {
        return imDrugsOutRangeLogDao;
    }

    /**
     * 将超出出院时间的药品的费用时间保存到本记录表，然后将该费用明细费用日期改为出院时间前一秒,保证其能在结算时间段内查询到
     */
    @Locked(value = {"saveAccountMoney_[zyh]"})
    @Transactional(rollbackFor = Exception.class)
    public void outRangeDrugBalance(Integer zyh) {
        ImHzry hzry = imHzrySer.getById(zyh);
        //已开出院证
        if (CypbEnum.CER_DISCHARGE.equals(hzry.getCypb())) {
            List<ImFeeFymx> fymxList = feeFymxSer.getOutRangeList(zyh, hzry.getCyrq());
            if (CollectionUtil.isNotEmpty(fymxList)) {//有超出的费用
                for (ImFeeFymx fymx : fymxList) {
                    ImDrugsOutRangeLog query = new ImDrugsOutRangeLog();
                    query.setFymxJlxh(fymx.getJlxh());

                    List<ImDrugsOutRangeLog> logList = imDrugsOutRangeLogDao.findByEntity(query);
                    if (CollectionUtil.isEmpty(logList)) {//尚未记录该条费用明细的费用日期，已记录的不再重复记录
                        ImDrugsOutRangeLog log = new ImDrugsOutRangeLog();
                        log.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_DRUGS_OUT_RANGE_LOG));
                        log.setZyh(fymx.getZyh());
                        log.setFymxJlxh(fymx.getJlxh());
                        log.setSjfyrq(fymx.getFyrq());
                        //保存费用明细的原费用日期
                        imDrugsOutRangeLogDao.insert(log);
                    }
                    //将费用日期提前到出院日期前一秒
                    Timestamp fyrq = DateUtils.secondAdd(hzry.getCyrq(), -1);
                    //修改超出的费用明细的费用日期
                    feeFymxSer.updateFyrq(fymx.getJlxh(), fyrq);
                }
            }
        }
    }

    /**
     * 发票作废后，将费用日期从记录表中再重新找回
     * 发票作废时已有业务锁
     *
     * @param zyh 住院号
     */
    @Transactional(rollbackFor = Exception.class)
    public void outRangeDrugReturn(Integer zyh) {
        ImDrugsOutRangeLog query = new ImDrugsOutRangeLog();
        query.setZyh(zyh);
        //找回保存的费用明细及其原始费用时间
        List<ImDrugsOutRangeLog> logList = imDrugsOutRangeLogDao.findByEntity(query);
        if (CollectionUtil.isNotEmpty(logList)) {
            for (ImDrugsOutRangeLog log : logList) {
                //恢复费用明细的费用日期
                feeFymxSer.updateFyrq(log.getFymxJlxh(), log.getSjfyrq());
                imDrugsOutRangeLogDao.deleteById(log.getJlxh());
            }
        }
    }
}
