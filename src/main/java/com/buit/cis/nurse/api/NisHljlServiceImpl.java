package com.buit.cis.nurse.api;

import com.buit.cis.nurse.model.*;
import com.buit.cis.nurse.service.*;
import com.buit.commons.PageQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类名：NisHljlServiceImpl
 * 描述：护理记录接口实现
 *
 * @author : liushijie
 * 2020/9/16
 **/
@DubboService(timeout = 10000)
public class NisHljlServiceImpl implements NisHljlService {
    @Autowired
    NisHljlmbSer nisHljlmbSer;
    @Autowired
    NisHljlSer nisHljlSer;
    @Autowired
    NisWzhldSer nisWzhldSer;
    @Autowired
    NisBqyjpgdSer nisBqyjpgdSer;
    @Autowired
    NisZspdlSer nisZspdlSer;
    @Autowired
    NisCqhldSer nisCqhldSer;
    @Autowired
    NisChhldSer nisChhldSer;
    @Autowired
    NisYcwxpgbSer nisYcwxpgbSer;
    @Autowired
    NisFyfxpgdSer nisFyfxpgdSer;
    @Autowired
    NisGdhtwxysbSer nisGdhtwxysbSer;
    @Autowired
    NisDdzcpgbSer nisDdzcpgbSer;
    @Autowired
    NisLzdngpgbSer nisLzdngpgbSer;
    @Autowired
    NisZxjmpgbSer nisZxjmpgbSer;
    @Autowired
    NisFssriskSer nisFssriskSer;
    @Autowired
    NisRyhldSer nisRyhldSer;
    @Autowired
    NisFsspgbSer nisFsspgbSer;
    @Autowired
    NisSjkjldSer nisSjkjldSer;
    @Autowired
    NisAllzxdSer nisAllzxdSer;

    /**
     * 查询护理记录模板
     *
     * @return
     */
    @Override
    public PageInfo<NisHljlmb> queryHljlmbList(PageQuery page) {
        NisHljlmb condition = new NisHljlmb();
        condition.setZfpb(0);
        PageInfo<NisHljlmb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisHljlmbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询一般护理记录单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisHljl> queryHljl(Integer zyh, PageQuery page) {
        NisHljl condition = new NisHljl();
        condition.setZyh(zyh);
        PageInfo<NisHljl> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisHljlSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询危重患者护理单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisWzhld> queryWzhld(Integer zyh, PageQuery page) {
        NisWzhld condition = new NisWzhld();
        condition.setZyh(zyh);
        PageInfo<NisWzhld> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisWzhldSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询患者病情预警评估单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisBqyjpgd> queryBqyjpgd(Integer zyh, PageQuery page) {
        NisBqyjpgd condition = new NisBqyjpgd();
        condition.setZyh(zyh);
        PageInfo<NisBqyjpgd> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisBqyjpgdSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询Barthel指数平定量表
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisZspdl> queryZspdl(Integer zyh, PageQuery page) {
        NisZspdl condition = new NisZspdl();
        condition.setZyh(zyh);
        PageInfo<NisZspdl> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisZspdlSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询产前护理记录单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisCqhld> queryCqhld(Integer zyh, PageQuery page) {
        NisCqhld condition = new NisCqhld();
        condition.setZyh(zyh);
        PageInfo<NisCqhld> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisCqhldSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询产后护理记录单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisChhld> queryChhld(Integer zyh, PageQuery page) {
        NisChhld condition = new NisChhld();
        condition.setZyh(zyh);
        PageInfo<NisChhld> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisChhldSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询住院患者压疮危险因素评估表
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisYcwxpgb> queryYcwxpgb(Integer zyh, PageQuery page) {
        NisYcwxpgb condition = new NisYcwxpgb();
        condition.setZyh(zyh);
        PageInfo<NisYcwxpgb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisYcwxpgbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 查询医院获得性肺炎风险因素评估单
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public PageInfo<NisFyfxpgd> queryFyfxpgd(Integer zyh, PageQuery page) {
        NisFyfxpgd condition = new NisFyfxpgd();
        condition.setZyh(zyh);
        PageInfo<NisFyfxpgd> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisFyfxpgdSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 住院患者管道滑脱危险因素评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisGdhtwxysb> queryGdhtwxysb(Integer zyh, PageQuery page) {
        NisGdhtwxysb condition = new NisGdhtwxysb();
        condition.setZyh(zyh);
        PageInfo<NisGdhtwxysb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisGdhtwxysbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 住院患者跌倒、坠床危险因素评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisDdzcpgb> queryDdzcpgb(Integer zyh, PageQuery page) {
        NisDdzcpgb condition = new NisDdzcpgb();
        condition.setZyh(zyh);
        PageInfo<NisDdzcpgb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisDdzcpgbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 留置导尿管感染风险因素评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisLzdngpgb> queryLzdngpgb(Integer zyh, PageQuery page) {
        NisLzdngpgb condition = new NisLzdngpgb();
        condition.setZyh(zyh);
        PageInfo<NisLzdngpgb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisLzdngpgbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 中心静脉导管相关性感染风险因素评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisZxjmpgb> queryZxjmpgb(Integer zyh, PageQuery page) {
        NisZxjmpgb condition = new NisZxjmpgb();
        condition.setZyh(zyh);
        PageInfo<NisZxjmpgb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisZxjmpgbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 肺栓塞风险因素评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisFssrisk> queryFssrisk(Integer zyh, PageQuery page) {
        NisFssrisk condition = new NisFssrisk();
        condition.setZyh(zyh);
        PageInfo<NisFssrisk> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisFssriskSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 入院护理评估单
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisRyhld> queryRyhld(Integer zyh, PageQuery page) {
        NisRyhld condition = new NisRyhld();
        condition.setZyh(zyh);
        PageInfo<NisRyhld> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisRyhldSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 深静脉血栓风险评估表
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisFsspgb> queryFsspgb(Integer zyh, PageQuery page) {
        NisFsspgb condition = new NisFsspgb();
        condition.setZyh(zyh);
        PageInfo<NisFsspgb> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisFsspgbSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 神经科记录单
     *
     * @param zyh
     * @return
     */
    @Override
    public PageInfo<NisSjkjld> querySjkjld(Integer zyh, PageQuery page) {
        NisSjkjld condition = new NisSjkjld();
        PageInfo<NisSjkjld> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisSjkjldSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }

    /**
     * 所有护理执行单
     *
     * @param zyh
     * @param mblx
     * @return
     */
    @Override
    public PageInfo<NisAllzxd> queryAllzxd(Integer zyh, String mblx, PageQuery page) {
        NisAllzxd condition = new NisAllzxd();
        condition.setZyh(zyh);
        condition.setMblx(mblx);
        PageInfo<NisAllzxd> pageInfo = PageHelper.startPage(
                page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                () -> nisAllzxdSer.getEntityMapper().findByEntity(condition)
        );
        return pageInfo;
    }


}
