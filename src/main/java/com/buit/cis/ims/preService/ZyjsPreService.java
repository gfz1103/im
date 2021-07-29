package com.buit.cis.ims.preService;

import cn.hutool.core.collection.CollectionUtil;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImFkxxDao;
import com.buit.cis.ims.dao.ImTbkkDao;
import com.buit.cis.ims.dao.ImZyjsDao;
import com.buit.cis.ims.enums.FkfsEnum;
import com.buit.cis.ims.enums.JklxEnum;
import com.buit.cis.ims.enums.YwlxEnum;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImFkxx;
import com.buit.cis.ims.model.ImTbkk;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.request.ImFkxxAddReq;
import com.buit.cis.ims.request.SaveSettleAccountsReq;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.op.model.OpCzjl;
import com.buit.op.service.OpCzjlService;
import com.buit.op.service.OpMpiKpxxService;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.PubBrxzOutSer;
import com.buit.system.service.PubFkfsService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.MapAttributeUtils;
import com.buit.utill.RedisFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZyjsPreService {

    @Autowired
    ImZyjsDao imZyjsDao;
    @Autowired
    ImTbkkDao imTbkkDao;
    @Autowired
    ImCwszDao imCwszDao;
    @Autowired
    private RedisFactory redisFactory;
    @DubboReference
    private PubBrxzOutSer pubBrxzSer;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    @DubboReference
    private PubFkfsService pubFkfsService;
    @Autowired
    private ImFkxxDao imFkxxDao;
    @Autowired
    private ObjectMapper objectMapper;
    @DubboReference
    private OpMpiKpxxService opMpiKpxxService;
    @DubboReference
    private OpCzjlService opCzjlService;

    /**
     * 获取最后一次结算记录
     *
     * @param zyh 住院号
     */
    @Transactional(rollbackFor = Exception.class)
    public ImZyjs getLastImZyjs(Integer zyh) {
        List<ImZyjs> list = imZyjsDao.getLastRecord(zyh);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取上级病人性质
     */
    @Transactional(rollbackFor = Exception.class)
    public String getGrandBrxz(String brxz) {
        List<Map<String, Object>> sjbrxzList = pubBrxzSer.getSjxzList(brxz);
        if (CollectionUtil.isNotEmpty(sjbrxzList)) {
            return String.valueOf(sjbrxzList.get(0).get("sjxz"));
        }
        return null;
    }

    /**
     * 判断病人是否是vip
     *
     * @param jgid
     * @param brxz
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean isVIP(Integer jgid, String brxz) {
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.VIPBRXZ);
        return sysXtcs != null && sysXtcs.getCsz().split(",")[0].equals(brxz);
    }

    /**
     * 结算时付款信息、货币误差处理（IM_FKXX）
     */
    @Transactional(rollbackFor = Exception.class)
    public void imFkxxSettleHandler(SaveSettleAccountsReq req, SysUser sysUser, String ip, Integer brid) {
        List<ImFkxxAddReq> list = req.getFkxxList();
        if (CollectionUtil.isEmpty(list)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00080");
        }
        //多种付款方式时判断总金额是否等于自负合计
        if (list.size() > 1) {
            BigDecimal total = list.stream().map(ImFkxxAddReq::getFkje).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
            if (req.getJkhj() != null) {
                total = total.add(req.getJkhj());
            }
            if (total.compareTo(req.getZfhj()) != 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00080");
            }
        }

        for (ImFkxxAddReq fkxx : list) {
            //付款方式及对应金额验证
            if (fkxx.getFkfs() == null || fkxx.getFkje() == null || fkxx.getFkje().compareTo(BigDecimal.ZERO) < 0) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00080");
            }
            // 保存付款方式及对应金额
            ImFkxx query = new ImFkxx();
            query.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FKXX));
            query.setFkfs(fkxx.getFkfs());
            query.setFkje(fkxx.getFkje());
            query.setJgid(sysUser.getHospitalId());
            query.setZyh(req.getZyh());
            query.setJscs(req.getJscs() + 1);
            imFkxxDao.insert(query);

            //充值卡支付
            if (FkfsEnum.RECHARGE_CARD.equals(fkxx.getFkfs())) {
                doSaveYykjkInfo(String.valueOf(brid), String.valueOf(fkxx.getFkje()), "09", req.getFphm(), sysUser.getUserId(), ip);
            }
        }

        if (req.getWcje() != null && !req.getWcje().equals(BigDecimal.ZERO)) {
            // 保存货币误差及误差金额
            ImFkxx hbwc = new ImFkxx();
            hbwc.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FKXX));
            hbwc.setFkfs(pubFkfsService.getHbwc(YwlxEnum.HOSPITALIZATION.getValue()).getFkfs());
            hbwc.setFkje(req.getWcje());
            hbwc.setJgid(sysUser.getHospitalId());
            hbwc.setZyh(req.getZyh());
            hbwc.setJscs(req.getJscs() + 1);
            imFkxxDao.insert(hbwc);
        }
        /*Map<String, Object> imFkxx = new HashMap<String, Object>(16);
        imFkxx.put("fkfs", req.getFkfs());
        imFkxx.put("fkje", req.getYsje());
        imFkxx.put("jgid", jgid);
        imFkxx.put("zyh", req.getZyh());
        imFkxx.put("jscs", req.getJscs() + 1);
            *//*if ("1".equals(isgjy)) {
                imZyjs.put("fyhj", map_jsxx.get("fyhjBak"));
                imZyjs.put("zfhj", map_jsxx.get("zfhjBak"));
                imZyjs.put("fyzj", map_jsxx.get("fyhjBak"));
                imZyjs.put("zfzj", map_jsxx.get("zfhjBak"));
                logger.info("住院结算：前台传入的费用合计与后台计算的不一致，将取后台计算的值  存在高价药时 " + imZyjs);
            }*//*
        imFkxx.put("jlxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FKXX));
        imFkxxDao.insert(imFkxx);*/


        /*if (req.getWcje().doubleValue() != 0) {
            Map<String, Object> zyHbwc = new HashMap<String, Object>(16);
            zyHbwc.put("fkfs", req.getWcfs());
            zyHbwc.put("fkje", req.getWcje());
            zyHbwc.put("jgid", jgid);
            zyHbwc.put("zyh", req.getZyh());
            zyHbwc.put("jscs", req.getJscs() + 1);
            zyHbwc.put("jlxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FKXX));
            imFkxxDao.insert(zyHbwc);
        }*/
    }

    /**
     * 结算时扣款记录处理（IM_TBKK）
     *
     * @param imZyjs 结算信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void imTbkkSettleHandler(ImZyjs imZyjs) {
        //实际付款 = 自负金额 - 减免金额 - 理赔金额 - 免赔金额
        BigDecimal realCost = imZyjs.getZfhj().subtract(imZyjs.getMpje()).subtract(imZyjs.getLpje()).subtract(imZyjs.getJmje());

        //账户余额不足，增加一条正的补缴记录，数值为 实际付款 - 账户余额
        if (imZyjs.getJkhj().compareTo(realCost) < 0) {
            ImTbkk costRecord = new ImTbkk();
            costRecord.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
            costRecord.setJgid(imZyjs.getJgid());
            costRecord.setZyh(imZyjs.getZyh());
            costRecord.setJkje(realCost.subtract(imZyjs.getJkhj()));
            costRecord.setJklx(JklxEnum.RECHARGE.getValue());
            costRecord.setJkrq(imZyjs.getJsrq());
            costRecord.setFphm(imZyjs.getFphm());
            costRecord.setJscs(imZyjs.getJscs());
            costRecord.setCzgh(imZyjs.getCzgh());
            costRecord.setZcpb(0);

            imTbkkDao.insert(costRecord);

            //更新账户余额
            imZyjs.setJkhj(imZyjs.getJkhj().add(costRecord.getJkje()));
        }
        //账户余额比实际付款多， 增加一条负的抵扣记录，数值为 实际付款
        if (imZyjs.getJkhj().compareTo(realCost) >= 0) {
            ImTbkk costRecord = new ImTbkk();
            costRecord.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
            costRecord.setJgid(imZyjs.getJgid());
            costRecord.setZyh(imZyjs.getZyh());
            costRecord.setJkje(realCost.negate());
            costRecord.setJklx(JklxEnum.Deduction.getValue());
            costRecord.setJkrq(imZyjs.getJsrq());
            costRecord.setFphm(imZyjs.getFphm());
            costRecord.setJscs(imZyjs.getJscs());
            costRecord.setCzgh(imZyjs.getCzgh());
            costRecord.setZcpb(0);

            imTbkkDao.insert(costRecord);

            //更新账户余额
            imZyjs.setJkhj(imZyjs.getJkhj().add(costRecord.getJkje()));

            //出院结算，抵扣后账户余额不为空, 需要找退，增加一条找退的记录, 数值为账户余额
            if ((5 == imZyjs.getJslx() ||1 == imZyjs.getJslx() ) &&  imZyjs.getJkhj().compareTo(BigDecimal.ZERO) != 0) {
                ImTbkk refound = new ImTbkk();
                refound.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
                refound.setJgid(imZyjs.getJgid());
                refound.setZyh(imZyjs.getZyh());
                refound.setJkje(imZyjs.getJkhj().negate());
                refound.setFphm(imZyjs.getFphm());
                refound.setJkrq(imZyjs.getJsrq());
                refound.setJscs(imZyjs.getJscs());
                refound.setCzgh(imZyjs.getCzgh());
                refound.setZcpb(0);
                refound.setJklx(JklxEnum.REFUND_DISCHARGE.getValue());
                imTbkkDao.insert(refound);
            }
        }
    }

    /**
     * 结算发票作废时扣款记录处理（IM_TBKK）
     *
     * @param imZyjs 需要作废的结算信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void imTbkkSettleCancelHandler(ImZyjs imZyjs) {
        //该次结算实际抵扣的部分
        ImTbkk payment = findByImZyjsAndJklx(imZyjs, JklxEnum.Deduction.getValue());
        if (payment != null) {
            payment.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
            payment.setJkje(payment.getJkje().multiply(new BigDecimal(-1)));
            payment.setCzgh(imZyjs.getZfgh());
            payment.setJkrq(imZyjs.getZfrq());
            payment.setJklx(JklxEnum.SETTLEMENT_CANCEL.getValue());
            imTbkkDao.insert(payment);
        }
    }

    /**
     * 查询病人是否在床
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean isStayBed(Integer zyh) {
        ImCwsz condition = new ImCwsz();
        condition.setZyh(zyh);
        List<ImCwsz> list = imCwszDao.findByEntity(condition);
        return CollectionUtil.isNotEmpty(list);
    }

    /**
     * 查询某个结算记录产生的缴费记录（抵扣）
     *
     * @param imZyjs 结算记录
     * @param jklx   缴款类型
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ImTbkk findByImZyjsAndJklx(ImZyjs imZyjs, Integer jklx) {
        ImTbkk condition = new ImTbkk();
        condition.setZyh(imZyjs.getZyh());
        condition.setJgid(imZyjs.getJgid());
        condition.setFphm(imZyjs.getFphm());
        condition.setJscs(imZyjs.getJscs());
        condition.setJklx(jklx);

        List<ImTbkk> list = imTbkkDao.findByEntity(condition);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据发票号码查找结算记录
     */
    @Transactional(rollbackFor = Exception.class)
    public ImZyjs getImZyjsByFphm(Integer jgid, String fphm) {
        ImZyjs imZyjs = new ImZyjs();
        imZyjs.setJgid(jgid);
        imZyjs.setFphm(fphm);
        List<ImZyjs> list = imZyjsDao.findByEntity(imZyjs);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    //预付卡支付保存
    public Map<String, Object> doSaveYykjkInfo(String brid, String ysk, String cztype, String fphm, Integer userId, String ip) {
        List<Map<String, Object>> list = null;
        Map<String, Object> res = new HashMap<String, Object>();
        list = opMpiKpxxService.queryCardInfoByBrid(brid);
        if (CollectionUtil.isEmpty(list)) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00065");
        }
        String czgh = userId.toString();
        String czip = ip;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("cardno", list.get(0).get("cardno"));//carno
        data.put("cztype", cztype);//cztype
        data.put("amount", Double.parseDouble(ysk));//amount
        data.put("czgh", czgh);//czgh
        data.put("czip", czip);//czip
        data.put("czsj", new Date());//czsj
        data.put("brid", brid);//BRID
        data.put("fphm", fphm);//fphm
        data.put("lastknye", list.get(0).get("knye"));//lastknye

        try {
            ysk = String.valueOf(MapAttributeUtils.getDouble("knye", list.get(0)) - Double.parseDouble(ysk));
            if (Double.parseDouble(ysk) < 0) {
                //卡内余额不足
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00066");
            }
            data.put("ysk", ysk);
            //通过病人id更新卡内余额
            opMpiKpxxService.updateKnyeByBrid(data);
            data.put("czxh", redisFactory.getTableKey(TableName.DB_NAME, TableName.OP_CZJL));
            OpCzjl opCzjlParam = objectMapper.readValue(objectMapper.writeValueAsString(data), OpCzjl.class);
            opCzjlService.insert(opCzjlParam);

        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00026");
        }
        res.put("code", 200);
        res.put("msg", "预付卡操作成功");
        return res;
    }
}
