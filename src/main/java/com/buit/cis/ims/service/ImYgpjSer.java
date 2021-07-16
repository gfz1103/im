package com.buit.cis.ims.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.cis.ims.dao.ImYgpjDao;
import com.buit.cis.ims.enums.SybzEnum;
import com.buit.cis.ims.model.ImYgpj;
import com.buit.cis.ims.request.ImYgpjReq;
import com.buit.cis.ims.request.ImYgpjUpdateReq;
import com.buit.cis.ims.response.ImYgpjResp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * 住院_员工票据<br>
 *
 * @author zhouhaisheng
 */
@Service
public class ImYgpjSer extends BaseManagerImp<ImYgpj, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImYgpjSer.class);

    @Autowired
    private ImYgpjDao imYgpjDao;

    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;


    @Override
    public ImYgpjDao getEntityMapper() {
        return imYgpjDao;
    }

    @Autowired
    private RedisFactory redisFactory;

    /**
     * 新增发票号码维护信息
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> add(ImYgpjReq imYgpj, SysUser user) {
        ImYgpj pj = new ImYgpj();
        //验证发票的当前号码段是否合法
        BigInteger qshm = new BigInteger(imYgpj.getQshm());
        BigInteger dqhm = new BigInteger(imYgpj.getDqhm());
        BigInteger zzhm = new BigInteger(imYgpj.getZzhm());

        if (qshm.compareTo(dqhm) > 0 || zzhm.compareTo(dqhm) < 0 || imYgpj.getQshm().trim().length() != imYgpj.getZzhm().trim().length()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0008");
        }

        BeanUtils.copyProperties(imYgpj, pj);
        pj.setLyrq(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, imYgpj.getLyrq()));
        pj.setJgid(user.getHospitalId());
        pj.setYgdm(user.getUserId());
        //默认不可用
        pj.setSybz(SybzEnum.code_1.getCode());
        pj.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_YGPJ));

        //验证号码段与别的号码段是否有重叠
        if (imYgpjDao.repetCheck(pj)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0007");
        }
        imYgpjDao.insert(pj);
        return ReturnEntityUtil.success();
    }

    /**
     * 修改发票号码维护信息
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<ImYgpjResp> edit(ImYgpjUpdateReq imYgpjUpdateReq) {
        ImYgpj pj = imYgpjDao.getById(imYgpjUpdateReq.getJlxh());

        //验证发票的当前号码段是否合法
        BigInteger qshm = new BigInteger(pj.getQshm());
        BigInteger dqhm = new BigInteger(imYgpjUpdateReq.getDqhm());
        BigInteger zzhm = new BigInteger(imYgpjUpdateReq.getZzhm());

        if (qshm.compareTo(dqhm) > 0 || zzhm.compareTo(dqhm) < 0 || pj.getQshm().trim().length() != pj.getZzhm().trim().length()) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0008");
        }

        BeanUtils.copyProperties(imYgpjUpdateReq, pj);
        //验证号码段与别的号码段是否有重叠
        if (imYgpjDao.repetCheck(pj)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0007");
        }

        imYgpjDao.update(pj);
        return ReturnEntityUtil.success();
    }

    /**
     * 启用号码段
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> active(ImYgpj imYgpj) {
        //校验该医院该员工有没有其他已经启用的号码段
        if (imYgpjDao.activeCheck(imYgpj)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0011");
        } else {
            imYgpj.setSybz(0);
            imYgpjDao.updateSybz(imYgpj);
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 停用号码段
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> disabled(ImYgpj imYgpj) {
        try {
            imYgpjDao.updateSybz(imYgpj);
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0003");
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 获取当前可使用的票据号对象
     */
    public ImYgpj getbillnumber(Integer pjlx, Integer jgid, Integer ygdm) {
        ImYgpj imYgpj = new ImYgpj();
        imYgpj.setPjlx(pjlx);
        imYgpj.setJgid(jgid);
        imYgpj.setYgdm(ygdm);
        imYgpj.setSybz(SybzEnum.code_0.getCode());
        String queryMinLyrq = imYgpjDao.queryMinLyrq(imYgpj);
        if (StrUtil.isBlank(queryMinLyrq)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0009");
        }
        imYgpj.setLyrq(DateUtils.parseToDate(queryMinLyrq));
        List<ImYgpj> imYgpjList = imYgpjDao.findByEntity(imYgpj);
        if (CollectionUtil.isEmpty(imYgpjList)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYYGPJ_0009");
        } else {
            //获取当前使用号码
            return imYgpjList.get(0);
        }

    }

    //更新发票 收据
    @Transactional(rollbackFor = Exception.class)
    public boolean setBillNumber(Integer pjlx, String fphm, Integer userId, Integer jgid) {

        // 定位唯一记录 （当前使用的记录),使用该条件来控制单条记录更新
        ImYgpj imYgpjParam = new ImYgpj();
        imYgpjParam.setJgid(jgid);

        imYgpjParam.setPjlx(pjlx);
        imYgpjParam.setSybz(SybzEnum.code_0.getCode());
        imYgpjParam.setDqhm(fphm);
        Timestamp ldt_Lyrq = new Timestamp(System.currentTimeMillis());
        List<ImYgpj> imYgpjList = imYgpjDao.findByEntity(imYgpjParam);
        if (imYgpjList != null && imYgpjList.size() > 0) {
            ImYgpj imYgpj = imYgpjList.get(0);
            ldt_Lyrq = imYgpj.getLyrq();

            //当前票据已用完
            imYgpjParam.setYgdm(userId);
            SysXtcs zyfpsfzcgy = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZYFPSFZCGY);
            SysXtcs jksjsfzcgy = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.JKSJSFZCGY);
            if (("1".equals(zyfpsfzcgy.getCsz()) && pjlx == 1) || ("1".equals(jksjsfzcgy.getCsz()) && pjlx == 2)) {
                imYgpjParam.setYgdm(null);
            }
            imYgpjParam.setDqhm(null);
            imYgpjParam.setLyrq(ldt_Lyrq);
            List<ImYgpj> liList = imYgpjDao.verifyDqhm(imYgpjParam);
            if (liList.size() == 0) {
                ImYgpj update_imYgpj = new ImYgpj();
                update_imYgpj.setDqhm(StringUtil.addOne(imYgpj.getDqhm()));
                update_imYgpj.setYgdm(userId);
                update_imYgpj.setPjlx(pjlx);
                update_imYgpj.setLyrq(imYgpj.getLyrq());
                if (("1".equals(zyfpsfzcgy.getCsz()) && pjlx == 1) || ("1".equals(jksjsfzcgy.getCsz()) && pjlx == 2)) {
                    imYgpjParam.setYgdm(null);

                }
                imYgpjDao.updateDqhm(update_imYgpj);
                return true;
            } else {
                ImYgpj imYgpjUpdateParam = new ImYgpj();
                imYgpjUpdateParam.setYgdm(userId);
                imYgpjUpdateParam.setPjlx(pjlx);
                imYgpjUpdateParam.setLyrq(ldt_Lyrq);
                if (("1".equals(zyfpsfzcgy.getCsz()) && pjlx == 1) || ("1".equals(jksjsfzcgy.getCsz()) && pjlx == 2)) {
                    imYgpjUpdateParam.setYgdm(null);

                }
                imYgpjUpdateParam.setJlxh(imYgpj.getJlxh());
                imYgpjUpdateParam.setSybz(SybzEnum.code_1.getCode());
                imYgpjDao.updateSybz(imYgpjUpdateParam);
            }


        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00028");

        }
        return true;
    }

    /**
     * 查询员工票据分页信息
     */
    public List<ImYgpjResp> findYgpjPage(ImYgpj imygpj) {
        return imYgpjDao.findYgpjPage(imygpj);
    }
}
