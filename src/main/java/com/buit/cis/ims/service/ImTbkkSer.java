package com.buit.cis.ims.service;

import cn.hutool.core.exceptions.ValidateException;
import com.buit.aop.lock.Locked;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImTbkkDao;
import com.buit.cis.ims.enums.JklxEnum;
import com.buit.cis.ims.enums.PjlxEnum;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImTbkk;
import com.buit.cis.ims.model.ImYgpj;
import com.buit.cis.ims.preService.YjkPreService;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.cis.ims.request.ImTbkkSaveReq;
import com.buit.cis.ims.request.PatientImTbkkReq;
import com.buit.cis.ims.request.PatientPayPageReq;
import com.buit.cis.ims.request.QueryPatientCostReq;
import com.buit.cis.ims.response.*;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.utill.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 住院_退补缴款<br>
 *
 * @author ZHOUHAISHENG
 */
@Service
public class ImTbkkSer extends BaseManagerImp<ImTbkk, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ImTbkkSer.class);

    @Autowired
    private ImTbkkDao imTbkkDao;
    @Autowired
    private ImFeeFymxDao imFeeFymxDao;
    @Autowired
    private ImHzryDao imHzryDao;

    @Autowired
    private YjkPreService preService;

    @Autowired
    private ImCwszDao imCwszDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private ImYgpjSer imYgpjSer;
    @Autowired
    private ImZyjsSer imZyjsSer;
    @Autowired
    private ZyjsPreService zyjsPreService;
   /* @Autowired
    private OpPosmxDao opPosmxDao;*/

    @Override
    public ImTbkkDao getEntityMapper() {
        return imTbkkDao;
    }



    /**
     * 缴费管理-缴费处理分页查询
     *
     * @param patientPayPageReq
     * @return
     */
    public List<PatientPayPageResp> queryPatientPayPage(PatientPayPageReq patientPayPageReq) {

        return imTbkkDao.queryPatientPayPage(patientPayPageReq);

    }

    /**
     * 缴费管理-缴费处理-注销
     *
     * @param sjhm
     * @param sysUser
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[tbkk.zyh]"})
    public ReturnEntity<String> patientPayCancel(ImTbkk tbkk, SysUser sysUser) {
        //缴款状态检查
        if (!JklxEnum.RECHARGE.equals(tbkk.getJklx())) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00076");
        }
        //账户费用检查
        BigDecimal rechargeTotal = imTbkkDao.queryJkje(tbkk.getZyh());//预缴款总额
        BigDecimal chargeTotalLeft = rechargeTotal.subtract(tbkk.getJkje());//注销退款之后的账户剩余金额
        CardPatientCostBaseInfoJe statistics = imFeeFymxDao.queryPatientCost(tbkk.getZyh(), sysUser.getHospitalId());//未结算的费用统计
        if (chargeTotalLeft.compareTo(statistics.getZfhj()) < 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00077");
        }
        preService.cancel(tbkk, sysUser);
        return ReturnEntityUtil.success();
    }

    /**
     * 缴费管理-缴费查询
     *
     * @param queryPatientCostReq
     * @return
     */
    public List<QueryPatientCostResp> queryPatientCost(QueryPatientCostReq queryPatientCostReq) {
       return imTbkkDao.queryPatientCost(queryPatientCostReq);
    }


    /**
     * 缴费管理-病人预缴费保存
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Locked(value = {"saveAccountMoney_#[req.zyh]"})
    public ReturnEntity<String> patientCostInsert(ImTbkkSaveReq req, SysUser sysUser, String ip) {
        Map<String, Object> posMap = req.getPos();
        //收据号码验证
        ImYgpj imYgpj = imYgpjSer.getbillnumber(2, sysUser.getHospitalId(), sysUser.getUserId());
        if(StrUtil.isBlank(imYgpj.getDqhm()) || StrUtil.isBlank(req.getSjhm()) || !imYgpj.getDqhm().equals(req.getSjhm())){
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00027");
        }

        //缴款记录插入
        ImTbkk imTbkk = new ImTbkk();
        imTbkk.setJkxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_TBKK));
        imTbkk.setJgid(sysUser.getHospitalId());
        imTbkk.setZyh(req.getZyh());
        imTbkk.setJkje(new BigDecimal(req.getJkje()));
        imTbkk.setJkfs(req.getJkfs());
        imTbkk.setJkrq(req.getJkrq() == null ? DateUtils.getNow() : DateUtils.parseToDateTime(req.getJkrq()));//入院登记时缴款日期为空
        imTbkk.setSjhm(req.getSjhm());
        imTbkk.setCzgh(String.valueOf(sysUser.getUserId()));
        imTbkk.setJscs(0);
        imTbkk.setZcpb(0);
        imTbkk.setJklx(0);
        imTbkk.setZphm(req.getZphm());

        try {
            if (imYgpjSer.setBillNumber(2, imYgpj.getDqhm(), sysUser.getUserId(), sysUser.getHospitalId())) {
                imTbkkDao.insert(imTbkk);
            } else {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00028");
            }
            //是否是用预付卡充值
            if ("20".equals(req.getJkfs())) {
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("zyh", req.getZyh());
                ImHzry imHzry = imHzryDao.getById(req.getZyh());
                zyjsPreService.doSaveYykjkInfo(imHzry.getBrid().toString(), req.getJkje(), "09", req.getSjhm(), sysUser.getUserId(), ip);
            }
            // 付费方式：POS机(新添微信支付宝)，写入pos机交易信息
            /*if (("8".equals(MapAttributeUtils.getString("jkfs", imTbkk)) || "17".equals(MapAttributeUtils.getString("jkfs", imTbkk)) || "27".equals(MapAttributeUtils.getString("jkfs", imTbkk))) && "1".equals(posMap.get("success"))) {
                Map<String, Object> posInfo = MapAttributeUtils.upcaseKeys(posMap);
                // posInfo.put("ID", id);
                posInfo.put("jysj", new Date());
                posInfo.put("jylb", posMap.get("TransType"));
                posInfo.put("fphm", sjhm);
                posInfo.put("id", redisFactory.getTableKey(TableName.DB_NAME, TableName.OP_POSMX));
                opPosmxDao.insert(posInfo);
            }*/

        } catch (ValidateException e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00029");
        }
        return ReturnEntityUtil.success(imTbkk.getJkxh().toString());
    }

    /**
     * 缴费管理-缴费处理-住院号查询预缴医药收据
     *
     * @param zyhm
     * @param sysUser
     * @return
     */
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByZyhm(String zyhm, SysUser sysUser) {
        CostPatientCollectResp costPatientCollectResp = new CostPatientCollectResp();

        ImHzry imHzry = imHzryDao.getByZyhm(sysUser.getHospitalId(), zyhm);
        if (imHzry == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00018");
        }
        costPatientCollectResp.setZyh(imHzry.getZyh());
        costPatientCollectResp.setZyhm(imHzry.getZyhm());
//        costPatientCollectResp.setJzkh(imHzry.getYbkh());
        costPatientCollectResp.setJzkh(imHzry.getJzkh());
        costPatientCollectResp.setBrch(imHzry.getBrch());
        costPatientCollectResp.setBrxm(imHzry.getBrxm());
        costPatientCollectResp.setSrgh(sysUser.getUserId().toString());
        costPatientCollectResp = sumCost(costPatientCollectResp, sysUser.getHospitalId());
        return ReturnEntityUtil.success(costPatientCollectResp);

    }

    /**
     * 缴费管理-缴费处理-就诊卡号查询预缴医药收据
     *
     * @param jzkh
     * @param sysUser
     * @return
     */
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByJzkh(String jzkh, SysUser sysUser) {
        CostPatientCollectResp costPatientCollectResp = new CostPatientCollectResp();

        ImHzry imHzry = imHzryDao.getByJzkh(jzkh);
        if (imHzry == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00067");
        }
        costPatientCollectResp.setZyh(imHzry.getZyh());
        costPatientCollectResp.setZyhm(imHzry.getZyhm());
        costPatientCollectResp.setJzkh(imHzry.getYbkh());
        costPatientCollectResp.setBrch(imHzry.getBrch());
        costPatientCollectResp.setBrxm(imHzry.getBrxm());
        costPatientCollectResp.setSrgh(sysUser.getUserId().toString());
        costPatientCollectResp = sumCost(costPatientCollectResp, sysUser.getHospitalId());
        return ReturnEntityUtil.success(costPatientCollectResp);

    }

    /**
     * 住院号查询
     * 缴费管理-缴费处理-住院号查询预缴医药收据
     *
     * @param zyh
     * @param sysUser
     * @return
     */
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByZyh(Integer zyh, SysUser sysUser) {
        CostPatientCollectResp costPatientCollectResp = new CostPatientCollectResp();

        ImHzry imHzry = imHzryDao.getById(zyh);
        if (imHzry == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00018");
        }
        costPatientCollectResp.setZyh(imHzry.getZyh());
        costPatientCollectResp.setZyhm(imHzry.getZyhm());
        costPatientCollectResp.setJzkh(imHzry.getYbkh());
        costPatientCollectResp.setBrch(imHzry.getBrch());
        costPatientCollectResp.setBrxm(imHzry.getBrxm());
        costPatientCollectResp.setSrgh(sysUser.getUserId().toString());
        costPatientCollectResp = sumCost(costPatientCollectResp, sysUser.getHospitalId());
        return ReturnEntityUtil.success(costPatientCollectResp);

    }

    /**
     * 查询住院病人未结算的费用合计和自负合计
     *
     */
    public CostPatientCollectResp sumCost(CostPatientCollectResp response, Integer hospitalId) {

        CardPatientCostBaseInfoJe cardPatientCostBaseInfoJe = imFeeFymxDao.queryPatientCost(response.getZyh(), hospitalId);

//        costPatientCollectResp.setSyhj(cardPatientCostBaseInfoJe.getFyhj());
        response.setZfhj(cardPatientCostBaseInfoJe.getZfhj());
        //查询缴费金额
        response.setYjhj(queryJkje(response.getZyh()));
        //计算剩余金额
        response.setSyhj(response.getYjhj().subtract(response.getSyhj() == null ? BigDecimal.ZERO: response.getSyhj()));

        return response;

    }

    /**
     * 根据住院号获取病人的缴款总计金额
     *
     * @param zyh
     * @return
     */
    public BigDecimal queryJkje(Integer zyh) {
        return imTbkkDao.queryJkje(zyh);
    }

    /**
     * 缴费管理-缴费处理-病人床号查询预缴医药收据
     *
     * @param brch
     * @param user
     * @return
     */
    public ReturnEntity<CostPatientCollectResp> queryCostPatientCollectByBrch(String brch, SysUser user) {
        ImCwsz imCwszParam = new ImCwsz();
        imCwszParam.setBrch(brch);
        imCwszParam.setJgid(user.getHospitalId());
        List<ImCwsz> imCwszList = imCwszDao.findByEntity(imCwszParam);
        //该床位号不存在
        if (imCwszList.size() == 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00019");
        }
        ImCwsz imCwsz = imCwszList.get(0);
        //该床位暂未分配病人
        if (imCwsz.getZyh() == null) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00020");
        }
        return queryCostPatientCollectByZyh(imCwsz.getZyh(), user);
    }

    /**
     * 在院病人预缴款统计分页查询
     *
     * @param patientImTbkkReq
     * @return
     */
    public List<PatientImTbkkResp> queryPatientImTbkkPage(PatientImTbkkReq patientImTbkkReq) {
        return imTbkkDao.queryPatientImTbkkPage(patientImTbkkReq);
    }

    /**
     * 在院病人预缴款统计总和
     *
     * @param patientImTbkkReq
     * @return
     */
    public BigDecimal queryPatientImTbkkSum(PatientImTbkkReq patientImTbkkReq) {
        return imTbkkDao.queryPatientImTbkkSum(patientImTbkkReq);
    }

    /**
     * 查询缴款记录列表
     *
     * @param imTbkkParam
     * @return
     */
    public List<CostImTbkkResp> findJkjl(ImTbkk imTbkkParam) {

        return imTbkkDao.findJkjl(imTbkkParam);
    }

    /**
     * 统计缴款金额
     *
     * @param imTbkkParam
     * @return
     */
    public Integer valSumJkje(ImTbkk imTbkkParam) {
        return imTbkkDao.valSumJkje(imTbkkParam);
    }

    /**
     * 缴费管理-获取当前的收据号码
     *
     * @param user
     * @return
     */
    public ReturnEntity<String> getCurrentBill(SysUser user) {
        //查询收据号码
        ImYgpj imYgpj = imYgpjSer.getbillnumber(PjlxEnum.code_2.getCode(), user.getHospitalId(), user.getUserId());
        if (imYgpj != null) {
            return ReturnEntityUtil.success(imYgpj.getDqhm());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0003");
        }

    }

    /**
     * 缴费管理-获取当前的发票号码
     *
     * @param user
     * @return
     */
    public ReturnEntity<String> getCurrentInvoice(SysUser user) {
        //查询收据号码
        ImYgpj imYgpj = imYgpjSer.getbillnumber(PjlxEnum.code_1.getCode(), user.getHospitalId(), user.getUserId());
        if (imYgpj != null) {
            return ReturnEntityUtil.success(imYgpj.getDqhm());
        } else {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_0003");
        }

    }

    /**
     * 查询交款记录
     *
     * @param debtInventoryReq
     * @return
     */
//    public List<ImTbkkJkjeResp> findImTbkkJkje(DebtInventoryReq debtInventoryReq) {
//        return imTbkkDao.findZytbkkjkje(debtInventoryReq);
//    }

    /**
     * 住院号统计病人缴款总金额
     *
     * @param jdid
     * @param zyh
     * @return
     */
    public BigDecimal queryPatientImTbkkSumByZyh(Integer zyh, Integer jdid) {
        return imTbkkDao.queryTotalPayment(zyh, jdid);
    }


}
