package com.buit.cis.bedresev.service;


import com.buit.cis.bedresev.dao.ImBedresevDao;
import com.buit.cis.bedresev.model.ImBedresev;
import com.buit.cis.bedresev.request.*;
import com.buit.cis.bedresev.response.BedInfoResp;
import com.buit.cis.bedresev.response.ImBedresevResp;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.preService.ZyjsPreService;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.op.response.MpiBrda;
import com.buit.op.service.OpMpiBrdaService;
import com.buit.op.service.OpMpiCardService;
import com.buit.utill.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * @author zhouhaisheng
 */
@Service
public class ImBedresevSer extends BaseManagerImp<ImBedresev,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImBedresevSer.class);
    
    @Autowired
    private ImBedresevDao imBedresevDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private ImCwszDao imCwszDao;
    @Autowired
    public ZyjsPreService zyjsPreService;
    @DubboReference
    public OpMpiCardService opMpiCardService;
    @DubboReference
    public OpMpiBrdaService opMpiBrdaService;

    @Override
    public ImBedresevDao getEntityMapper(){        
        return imBedresevDao;
    }

    /**
     * 新增住院预约信息
     * @param imBedresev
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(ImBedresevReq imBedresev, SysUser user) throws ParseException {
        imBedresev.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.BEDRESEV));
        //默认未待床
        imBedresev.setDczt("0");
        ImBedresev imbed = new ImBedresev();
        BeanUtils.copyProperties(imBedresev, imbed);
        //卡号为空
        if (StringUtils.isBlank(imbed.getCardNo())) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00041");
        }

        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("CARDNO", imbed.getCardNo());
        Map<String, Object> brdaInfo = opMpiCardService.getMpiCardInfoByCardNo(cardMap);

        if (brdaInfo != null) {
            if (isAppointedBed(user.getHospitalId(), MapAttributeUtils.getInteger("BRID", brdaInfo))) {
                throw BaseException.create("ERROR_HIS_IM_BEDRESEV_0002");
            }

            MpiBrda mpiBrda = opMpiBrdaService.getByBrid((MapAttributeUtils.getInteger("BRID", brdaInfo)), user.getHospitalId());
            imbed.setBrid(mpiBrda.getBrid());
        }

        imbed.setDjr(user.getUserId());
        imbed.setDjsj(new Timestamp(System.currentTimeMillis()));
        if (StrUtil.isBlank(imBedresev.getCsny())) {
            imbed.setYyzyrq(null);
        } else {
            imbed.setCsny(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY, imBedresev.getCsny()));
        }
        if (StrUtil.isBlank(imBedresev.getYyzyrq())) {
            imbed.setYyzyrq(null);
        } else {
            imbed.setYyzyrq(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, imBedresev.getYyzyrq()));
        }
        if(StrUtil.isBlank(imBedresev.getRytzsj())){
            imbed.setRytzsj(null);
        }else{
            imbed.setRytzsj(DateUtils.convertTimestamp(DateUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND,imBedresev.getRytzsj()));
        }

        imBedresevDao.insert(imbed);
    }

    /**
     * 取消预约
     * @param jlxh
     * @return
     */
    public ReturnEntity<String> cancel(Integer jlxh) {
        ImBedresev imBedresev=imBedresevDao.getById(jlxh);
        if(StringUtils.isNotBlank(imBedresev.getBrch())){
            throw BaseException.create("ERROR_HIS_IM_BEDRESEV_0001");
        }
        imBedresevDao.deleteById(jlxh);
        return ReturnEntityUtil.success();


    }

    /**
     * 待床
     * @param waitBedReq
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> waitBed(WaitBedReq waitBedReq,SysUser sysUser) {

        ImBedresev imBedresev=new ImBedresev();
        BeanUtils.copyProperties(waitBedReq,imBedresev);
        imBedresev.setDczt("1");
        imBedresev.setDcczr(sysUser.getUserId());
        imBedresevDao.update(imBedresev);
        ImCwsz param=new ImCwsz();
        param.setJgid(sysUser.getHospitalId());
        param.setBrch(waitBedReq.getBrch());
        //待床的床位号码统一设置为-1
        param.setZyh(-1);
        param.setKsdm(waitBedReq.getBrbq());
        imCwszDao.waitBed(param);
        return ReturnEntityUtil.success();
    }

    /**
     * 取消待床
     * @param jlxh
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> cancelWaitBed(Integer jlxh) {
        ImBedresev imBedresev=new ImBedresev();
        imBedresev.setJlxh(jlxh);
        imBedresev.setBrch(null);
        imBedresev.setBrbq(null);
        imBedresev.setRytzsj(null);
        imBedresev.setDczt("0");
        ImBedresev imBedresev1=imBedresevDao.getById(jlxh);
        imBedresevDao.cancelWaitBed(imBedresev);
        ImCwsz param=new ImCwsz();
        param.setJgid(imBedresev1.getJgid());
        param.setBrch(imBedresev1.getBrch());
        param.setKsdm(imBedresev1.getBrbq());
        param.setZyh(null);
        imCwszDao.waitBed(param);
        return ReturnEntityUtil.success();

    }

    /**
     * 出院预约统计分析查询
      * @param queryImBedresevCollectReq
     * @return
     */
    public List<QueryImBedresevCollectResp> queryImBedresevCollect(QueryImBedresevCollectReq queryImBedresevCollectReq) {

        return imBedresevDao.queryImBedresevCollect(queryImBedresevCollectReq);
    }

    /**
     * 待床入院登记
     * @param imBedresev
     */
    public void updateRydj(ImBedresev imBedresev) {
        imBedresevDao.updateRydj(imBedresev);
    }

    /**
     * 读卡操作
     * @param cardNo
     * @return
     */
    public ReturnEntity<ImBedresevResp> readCardInfo(String cardNo,SysUser user) {
        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("CARDNO", cardNo);
        Map<String, Object> brdaInfo = opMpiCardService.getMpiCardInfoByCardNo(cardMap);


        if (null == brdaInfo || brdaInfo.size() == 0) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00041");
        }
        if (isAppointedBed(user.getHospitalId(), MapAttributeUtils.getInteger("BRID", brdaInfo))) {
            throw BaseException.create("ERROR_HIS_IM_BEDRESEV_0002");
        }

        MpiBrda mpiBrda = opMpiBrdaService.getByBrid((MapAttributeUtils.getInteger("BRID", brdaInfo)), user.getHospitalId());
        ImBedresevResp imBedresevResp = new ImBedresevResp();
        BeanUtils.copyProperties(mpiBrda, imBedresevResp);
        imBedresevResp.setCardNo(cardNo);
        imBedresevResp.setIdCard(mpiBrda.getSfzh());
        imBedresevResp.setLxdh(mpiBrda.getBrdh());

        return ReturnEntityUtil.success(imBedresevResp);
    }

    /**
     * 查询所有床位
     *
     * @param req
     */
    public List<BedInfoResp> queryAllBed(BedInfoReq req) {
        return imBedresevDao.queryAllBed(req);
    }

    /**
     * 是否已经预约或者已经待床
     *
     * @param jgid 机构id
     * @param brid 病人id
     */
    public boolean isAppointedBed(Integer jgid, Integer brid) {
        return imBedresevDao.isAppointedBed(jgid, brid);
    }

    /**
     * 床位预约判断性质是否是医保
     *
     * @param brxz 病人性质
     */
    public Boolean checkSfyb(Integer brxz) {
        String sjxz = zyjsPreService.getGrandBrxz(String.valueOf(brxz));
        if ("6021".equals(sjxz)){
            return true;
        }else{
            return false;
        }
    }
}
