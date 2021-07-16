package com.buit.cis.ims.preService;

import com.buit.aop.lock.Locked;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImRyzd;
import com.buit.cis.ims.service.ImRyzdSer;
import com.buit.commons.BaseException;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.op.request.OpZydjUpdateStatusReq;
import com.buit.op.response.BrzdResp;
import com.buit.op.service.OpBrzdService;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.service.SysXtcsSer;
import com.buit.utill.StringUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HzryPreService {
    @Autowired
    ImHzryDao imHzryDao;
    @DubboReference
    private SysXtcsSer sysXtcsSer;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;

    @DubboReference
    private OpBrzdService opBrzdService;
    @Autowired
    private ImRyzdSer imRyzdService;

    /**
     * 获取病案号码
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"getBahm_#[jgid]"})
    public String getBahm(Integer jgid) {
        SysXtcs bahm = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.BAHM);
        if (bahm == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0001");
        }
        bahm.setCsz(StringUtil.addOne(bahm.getCsz()));
        sysXtcsSer.updateZyParam(bahm);
        return bahm.getCsz();
    }

    /**
     * 获取留观号码（病案号住院号不相同）
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"getLghm_#[jgid]"})
    public String getLghm(Integer jgid) {
        SysXtcs zyhm = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.LGHM);
        if (zyhm == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0002");
        }
        zyhm.setCsz(StringUtil.addOne(zyhm.getCsz()));
        sysXtcsSer.updateZyParam(zyhm);
        return zyhm.getCsz();
    }

    /**
     * 获取留观号码（病案号住院号相同）
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"getLghm_#[jgid]"})
    public String getLghm(Integer jgid, String bahm) {
        //先将住院号码与病案号同步，然后返回病案号
        //如果不同步直接返回病案号，住院号将在自增后追上已使用过的病案号造成住院号重复问题
        SysXtcs zyhm = new SysXtcs();
        zyhm.setJgid(jgid);
        zyhm.setCsmc(SysXtcsCsmcCts.LGHM);
        zyhm.setCsz(StringUtil.addOne(bahm));
        sysXtcsSer.updateZyParam(zyhm);
        return bahm;
    }

    /**
     * 获取住院号码（病案号住院号不相同）
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"getZyhm_#[jgid]"})
    public String getZyhm(Integer jgid) {
        SysXtcs zyhm = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZYHM);
        if (zyhm == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0002");
        }
        zyhm.setCsz(StringUtil.addOne(zyhm.getCsz()));
        sysXtcsSer.updateZyParam(zyhm);
        return zyhm.getCsz();
    }

    /**
     * 获取住院号码（病案号住院号相同）
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"getZyhm_#[jgid]"})
    public String getZyhm(Integer jgid, String bahm) {
        //先将住院号码与病案号同步，然后返回病案号
        //如果不同步直接返回病案号，住院号将在自增后追上已使用过的病案号造成住院号重复问题
        SysXtcs zyhm = new SysXtcs();
        zyhm.setJgid(jgid);
        zyhm.setCsmc(SysXtcsCsmcCts.ZYHM);
        zyhm.setCsz(StringUtil.addOne(bahm));
        sysXtcsSer.updateZyParam(zyhm);
        return bahm;
    }

    /**
     * 病案号码重复验证
     *
     * @param checkParam
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void checkBahm(ImHzry checkParam) {
        List<ImHzry> list = imHzryDao.queryCfBahm(checkParam);
        if (list.size() > 0) {
            SysXtcs gyXtcParam = new SysXtcs();
            gyXtcParam.setJgid(checkParam.getJgid());
            gyXtcParam.setCsmc(SysXtcsCsmcCts.BAHM);
            gyXtcParam.setCsz(StringUtil.addOne(checkParam.getBahm()));
            sysXtcsSer.updateZyParam(gyXtcParam);
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00010");
        }
    }

    /**
     * 住院号码重复验证
     *
     * @param checkParam
     */
    @Transactional(rollbackFor = Exception.class)
    public void chechZyhm(ImHzry checkParam) {
        List<ImHzry> imHzryZyhmList = imHzryDao.queryCfZyhm(checkParam);
        if (imHzryZyhmList.size() > 0) {
            //住院号病案号是否相同 系统标识 默认不启用;
            String iaAllowedTheSame = sysXtcsCacheSer.getCsz(checkParam.getJgid(), SysXtcsCsmcCts.BAHMDYZYHM);

            SysXtcs current = null;//当前号码值
            if ("1".equals(checkParam.getYwlx())) {// 住院业务
                current = sysXtcsCacheSer.getByJGIdAndCsmc(checkParam.getJgid(), SysXtcsCsmcCts.ZYHM);
            } else if ("2".equals(checkParam.getYwlx())) {// 留观业务
                current = sysXtcsCacheSer.getByJGIdAndCsmc(checkParam.getJgid(), SysXtcsCsmcCts.LGHM);
            }
            if (checkParam.getZyhm().equals(current.getCsz())) {
                if ("1".equals(iaAllowedTheSame)) {//启用病案号与住院号相同
                    current.setCsz(StringUtil.addOne(checkParam.getBahm()));
                } else if ("0".equals(iaAllowedTheSame)) {//不启用
                    current.setCsz(StringUtil.addOne(checkParam.getZyhm()));
                }
                sysXtcsSer.updateZyParam(current);
            }
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0009");
        }
    }

    /**
     * 入院登记 门诊病人诊断复制到入院诊断表
     *
     * @param jzlsh 就诊流水号
     * @param zyh   住院号
     */
    @Transactional(rollbackFor = Exception.class)
    public void mzzdTransform(String jzlsh, Integer zyh) {
        List<BrzdResp> list = opBrzdService.getBrzdByJzlsh(jzlsh);
        for (BrzdResp mzzd : list) {
            ImRyzd imRyzd = new ImRyzd();
            imRyzd.setZyh(zyh);
            imRyzd.setZdxh(mzzd.getZdxh());
            imRyzd.setZdlb(mzzd.getZdlb());
            imRyzd.setJgid(mzzd.getJgid());
            imRyzd.setZgqk(mzzd.getZgqk());
            imRyzd.setZdrq(mzzd.getZdsj());
            imRyzd.setZxlb(mzzd.getCflx());
            imRyzdService.insert(imRyzd);
        }
    }

    /**
     * 修改门诊住院登记单 状态为 已入院
     * 分布式事务问题 无法回滚替代方案
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSqzt(Integer mzdjid) {
        OpZydjUpdateStatusReq opZydj = new OpZydjUpdateStatusReq();
        opZydj.setDjid(mzdjid);
        opZydj.setSqzt("3");
        imHzryDao.updateSqzt(opZydj);
    }

}
