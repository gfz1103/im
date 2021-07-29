package com.buit.cis.ims.service;


import com.buit.cis.ims.dao.ImOperateLogDao;
import com.buit.cis.ims.model.ImOperateLog;
import com.buit.commons.BaseManagerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangwei
 * @Description 患者住院操作日志
 * @Date 2021-07-26
 */
@Service
public class ImOperateLogSer extends BaseManagerImp<ImOperateLog, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImOperateLogSer.class);

    @Autowired
    private ImOperateLogDao imOperateLogDao;

    @Override
    public ImOperateLogDao getEntityMapper() {
        return imOperateLogDao;
    }

    /**
     * 查询最后一次打印状态
     *
     * @param tableName 数据库表名
     * @param dataId    单据主键
     */
    public Integer getLastPrintStatus(String tableName, Integer dataId) {
        return imOperateLogDao.getLastPrintStatus(tableName, dataId);
    }
}
