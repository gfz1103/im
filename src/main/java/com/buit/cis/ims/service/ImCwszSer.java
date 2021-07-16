package com.buit.cis.ims.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.model.*;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.utill.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 住院_床位设置<br>
 * @author zhouhaisheng
 */
@Service
public class ImCwszSer extends BaseManagerImp<ImCwsz,String> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwszSer.class);
    
    @Autowired
    private ImCwszDao imCwszDao;
    @Autowired
    private ImRcjlSer imRcjlSer;
    @Override
    public ImCwszDao getEntityMapper(){        
        return imCwszDao;
    }
    @Autowired
    public ImHzrySer imHzrySer;
    @Autowired
    public ImHcmxSer imHcmxSer;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private ImCwtjSer imCwtjSer;

    @Autowired
    private ImCwtjDao imCwtjDao;

    @Autowired
    private ImZkjlDao imZkjlDao;

    @Autowired
    private ImHcmxDao imHcmxDao;

    @Autowired
    private ImHzryDao imHzryDao;
    @Autowired
    private ImRcjlDao imRcjlDao;
    /**
     * 新增床位设置
     * @param imCwsz
     * @param user
     */
    public ReturnEntity<String> add(ImCwszAddReq imCwsz, SysUser user) {
        ImCwsz  cw=new ImCwsz();
        BeanUtils.copyProperties(imCwsz,cw);
        //先查询该床位号是否已存在
        ImCwsz  cwParama=new ImCwsz();
        cwParama.setJgid(user.getHospitalId());
        cwParama.setKsdm(imCwsz.getKsdm());
        cwParama.setBrch(cw.getBrch());
        List<ImCwsz> imCwszList=imCwszDao.findByEntity(cwParama);
        if(imCwszList.size()>0){
            throw  BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0001");
        }
        try {
            cw.setJgid(user.getHospitalId());
            imCwszDao.insert(cw);
        }catch (Exception e){
            throw  BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0003");
        }
        return ReturnEntityUtil.success();
    }

    /***
     * 修改床位设置信息
      * @param imCwsz
     * @param user
     * @return
     */
    public ReturnEntity<String> edit(ImCwszAddReq imCwsz, SysUser user) {
        ImCwsz cw=new ImCwsz();
        BeanUtils.copyProperties(imCwsz,cw);
        cw.setJgid(user.getHospitalId());
        //先查询该床位号是否已存在
//        ImCwsz  cwParama=new ImCwsz();
//        cwParama.setJgid(user.getHospitalId());
//        cwParama.setBrch(cw.getBrch());
//        cwParama.setCwks(imCwsz.getCwks());
//        cwParama.setKsdm(imCwsz.getKsdm());
//        cwParama.setFjhm(imCwsz.getFjhm());
//        List<ImCwsz> imCwszList=imCwszDao.findByEntity(cwParama);
//        if(imCwszList.size()>0){
//            throw  BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0002");
//        }
        try {
            imCwszDao.update(cw);
        } catch (Exception e) {
            throw  BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0005");
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 删除床位设置信息
     * @param
     * @return
     */
    public ReturnEntity<String> delete(String brch, Integer ksdm, SysUser user) {
        ImCwsz cw=new ImCwsz();
        cw.setJgid(user.getHospitalId());
        cw.setBrch(brch);
        cw.setKsdm(ksdm);
        //先校验床位是否有患者
        List<ImCwsz> cwszList=imCwszDao.findByEntity(cw);
        if(cwszList.size()>0){
            ImCwsz cwsz=  cwszList.get(0);
            if(cwsz.getZyh()!=null&& cwsz.getZyh().intValue()!=0){
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0006");
            }
        }
        try {
            imCwszDao.removeByEntity(cw);
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYCWSZ_0004");
        }
        return ReturnEntityUtil.success();
    }

    /**
     * 住院病人床位设置列表分页查询
      * @param zybrCwszReq
     * @return
     */
    public List<ZybrCwszResp> queryZybrCwszPage(ZybrCwszReq zybrCwszReq) {

        return imCwszDao.queryZybrCwszPage(zybrCwszReq);
    }

    /**
     * 住院病人床位管理 退床
     * @param zyh
     * @param cwhm
     * @return
     */
    public ReturnEntity<String> zyglCwszTc(Integer zyh, String cwhm, SysUser user,Integer brbq) {
        //退床之前先验证病人是否办理了出院证
        ImRcjl imRcjlParam=new ImRcjl();
        imRcjlParam.setJgid(user.getHospitalId());
        imRcjlParam.setZyh(zyh);
        imRcjlParam.setCzlx(-1);
        List<ImRcjl> imRcjlList=imRcjlSer.findByEntity(imRcjlParam);
        //病人尚未办理出院证，不能退床
        if(imRcjlList.size()==0){
            throw  BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00016",new String[]{cwhm});
        }
        ImCwsz imCwszParam=new ImCwsz();
        imCwszParam.setJgid(user.getHospitalId());
        imCwszParam.setBrch(cwhm);
        imCwszParam.setKsdm(brbq);
        imCwszParam.setZyh(zyh);
        List<ImCwsz> imCwszList=imCwszDao.findByEntity(imCwszParam);
        if(imCwszList.size()>0) {
            ImCwsz imCwsz = imCwszList.get(0);
            if (imCwsz.getZyh() == null) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00017", new String[]{cwhm});
            }

            //退床操作 处理床位信息
            imCwszDao.updateTc(imCwszParam);
            //退房操作处理入院登记信息
            ImHzry imHzryParam=new ImHzry();
            imHzryParam.setJgid(user.getHospitalId());
            imHzryParam.setZyh(zyh);
            imHzryParam.setBrch(cwhm);
            imHzrySer.updateTc(imHzryParam);
            imHzryParam.setBrch(null);
            List<ImHzry> imHzryList=imHzrySer.findByEntity(imHzryParam);
            if(imHzryList.size()>0){
                ImHzry imHzry=imHzryList.get(0);
                ImHcmx imHcmxParam=new ImHcmx();
                imHcmxParam.setZyh(imHzry.getZyh());
                imHcmxParam.setHcrq(new Timestamp(System.currentTimeMillis()));
                imHcmxParam.setHclx(5);
                imHcmxParam.setJgid(user.getHospitalId());
                imHcmxParam.setHqch(cwhm);
                imHcmxParam.setHqks(imHzry.getBrks());
                imHcmxParam.setHqbq(brbq);
                imHcmxParam.setJscs(0);
                imHcmxParam.setCzgh(String.valueOf(user.getUserId()));
                imHcmxSer.insert(imHcmxParam);

                if(imCwsz.getJcpb()<2){
                    ImCwtj imCwtjParam=new ImCwtj();
                    imCwtjParam.setCzrq(new Timestamp(System.currentTimeMillis()));
                    imCwtjParam.setCzlx(-4);
                    imCwtjParam.setZyh(imHzry.getZyh());
                    imCwtjParam.setBrks(imHcmxParam.getHqks());
                    imCwtjParam.setBqpb(0);
                    ImCwsz imCwszTj=new ImCwsz();
                    imCwszTj.setKsdm(imCwsz.getKsdm());
                    if(imCwtjParam.getBqpb().intValue()==0){
                        imCwszTj.setKsdm(null);
                        imCwszTj.setCwks(imCwsz.getKsdm());

                    }
                    int ysys=cwtj(imCwszTj)+1;
                    imCwtjParam.setYsys(ysys);
                    imCwtjParam.setXsys(ysys-1);
                    imCwtjParam.setJgid(user.getHospitalId());
                    imCwtjParam.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                    imCwtjSer.insert(imCwtjParam);

                    }

            }


        }


        return ReturnEntityUtil.success();
    }

    /**
     * 查询床位使用数
     * @param imCwsz
     * @return
     */
    public int cwtj(ImCwsz imCwsz){

        List<ImCwsz> imCwszList=imCwszDao.cwtj(imCwsz);


        return imCwszList.size();

    }



    /**
     * 床位管理-转床
     * @param doGetToBedInfoReq
     * @param user
     * @return
     */
    public ReturnEntity<String> zyglCwszZc(DoGetToBedInfoReq doGetToBedInfoReq, SysUser user) {
        Integer jgid = user.getHospitalId ();// 用户的机构ID
        Integer userId = user.getUserId();
        // 判断新床位是否已有病人
        Integer ll_zyh_New = null;
        Integer ll_bqdm_Old = null;
        Integer ll_bqks_Old = null;
        Integer ll_jcpb = null;
        Integer ll_bqdm_New = null;
        Integer ll_bqks_New = null;
        Integer ll_jcpb2 = null;
        Integer ll_cwxb_New = null;
        Integer ll_brxb_Old = null;
   /*     Map<String, Object> parameterscwszzyh = new HashMap<String, Object>();
        parameterscwszzyh.put("jgid", jgid);
        parameterscwszzyh.put("brch", doGetToBedInfoReq.getNewCwhm());*/
        ImCwsz imCwszParam=new ImCwsz();
        imCwszParam.setJgid(jgid);
        imCwszParam.setKsdm(doGetToBedInfoReq.getNewBrbq());
        imCwszParam.setBrch(doGetToBedInfoReq.getNewCwhm());
        try {
            List<ImCwsz> imCwszList = imCwszDao.findByEntity(imCwszParam);
            if (imCwszList != null && imCwszList.size() > 0) {
                ImCwsz imCwsz = imCwszList.get(0);
                if (imCwsz.getZyh() == null) {
                    ll_zyh_New = 0;
                } else {
                    ll_zyh_New = imCwsz.getZyh();
                }
            }
            // 取床位病区代码
            imCwszParam.setBrch(doGetToBedInfoReq.getOldCwhm());
            List<ImCwsz> imCwszListOld = imCwszDao.findByEntity(imCwszParam);
            if (imCwszListOld != null && imCwszListOld.size() > 0) {
                ImCwsz imCwszOld = imCwszListOld.get(0);
                ll_bqdm_Old = imCwszOld.getKsdm();
                ll_bqks_Old = imCwszOld.getCwks();
                ll_jcpb = imCwszOld.getJcpb();
                ll_brxb_Old = imCwszOld.getCwxb();
            }

            imCwszParam.setBrch(doGetToBedInfoReq.getNewCwhm());
            List<ImCwsz> imCwszListNew = imCwszDao.findByEntity(imCwszParam);
            if (imCwszListNew != null && imCwszListNew.size() > 0) {
                ImCwsz imCwszNew = imCwszListNew.get(0);
                ll_bqdm_New = imCwszNew.getKsdm();
                ll_bqks_New = imCwszNew.getCwks();
                ll_jcpb2 = imCwszNew.getJcpb();
                ll_cwxb_New = imCwszNew.getCwxb();
            }
            //病区不同，不能转床!
            if (!ll_bqks_Old.equals(ll_bqks_New)) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00032");
            }
            if (ll_cwxb_New != 3) {
                if (!ll_cwxb_New.equals(ll_brxb_Old)) {
                    throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00033");
                }
            }
            if (ll_jcpb < 2) {
                imCwszParam = new ImCwsz();
                imCwszParam.setCwks(ll_bqks_Old);
                int ll_cwsys_ks = cwtj(imCwszParam);
                imCwszParam.setCwks(null);
                imCwszParam.setKsdm(ll_bqdm_Old);
                int ll_cwsys_bq = cwtj(imCwszParam);

                ImCwtj imCwtjKsSave = new ImCwtj();
                imCwtjKsSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                imCwtjKsSave.setCzlx(2);
                imCwtjKsSave.setZyh(doGetToBedInfoReq.getZyh());
                imCwtjKsSave.setBrks(ll_bqks_Old);
                imCwtjKsSave.setYsys(ll_cwsys_ks);
                imCwtjKsSave.setXsys(ll_cwsys_ks - 1);
                imCwtjKsSave.setBqpb(0);
                imCwtjKsSave.setJgid(jgid);
                imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

                imCwtjDao.insert(imCwtjKsSave);

                ImCwtj zyCwtBqSave = new ImCwtj();
                zyCwtBqSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                zyCwtBqSave.setCzlx(2);
                zyCwtBqSave.setZyh(doGetToBedInfoReq.getZyh());
                zyCwtBqSave.setBrks(ll_bqks_Old);
                zyCwtBqSave.setYsys(ll_cwsys_bq);
                zyCwtBqSave.setXsys(ll_cwsys_bq - 1);
                zyCwtBqSave.setBqpb(1);
                zyCwtBqSave.setJgid(jgid);
                zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                imCwtjDao.insert(zyCwtBqSave);
            }
            if (ll_jcpb2 < 2) {
                imCwszParam = new ImCwsz();
                imCwszParam.setCwks(ll_bqks_New);
                int ll_cwsys_ks = cwtj(imCwszParam);
                imCwszParam.setCwks(null);
                imCwszParam.setKsdm(ll_bqdm_New);
                int ll_cwsys_bq = cwtj(imCwszParam);


                ImCwtj imCwtjKsSave = new ImCwtj();
                imCwtjKsSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                imCwtjKsSave.setCzlx(2);
                imCwtjKsSave.setZyh(doGetToBedInfoReq.getZyh());
                imCwtjKsSave.setBrks(ll_bqks_New);
                imCwtjKsSave.setYsys(ll_cwsys_ks);
                imCwtjKsSave.setXsys(ll_cwsys_ks + 1);
                imCwtjKsSave.setBqpb(0);
                imCwtjKsSave.setJgid(jgid);
                imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

                imCwtjDao.insert(imCwtjKsSave);

                ImCwtj zyCwtBqSave = new ImCwtj();
                zyCwtBqSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                zyCwtBqSave.setCzlx(2);
                zyCwtBqSave.setZyh(doGetToBedInfoReq.getZyh());
                zyCwtBqSave.setBrks(ll_bqks_New);
                zyCwtBqSave.setYsys(ll_cwsys_bq);
                zyCwtBqSave.setXsys(ll_cwsys_bq + 1);
                zyCwtBqSave.setBqpb(1);
                zyCwtBqSave.setJgid(jgid);
                zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                imCwtjDao.insert(zyCwtBqSave);
            }
            if (ll_zyh_New > 0) {
                if (ll_jcpb < 2) {
                    imCwszParam = new ImCwsz();
                    imCwszParam.setCwks(ll_bqks_Old);
                    int ll_cwsys_ks = cwtj(imCwszParam);
                    imCwszParam.setCwks(null);
                    imCwszParam.setKsdm(ll_bqdm_Old);
                    int ll_cwsys_bq = cwtj(imCwszParam);


                    ImCwtj imCwtjKsSave = new ImCwtj();
                    imCwtjKsSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                    imCwtjKsSave.setCzlx(2);
                    imCwtjKsSave.setZyh(doGetToBedInfoReq.getZyh());
                    imCwtjKsSave.setBrks(ll_bqks_Old);
                    imCwtjKsSave.setYsys(ll_cwsys_ks);
                    imCwtjKsSave.setXsys(ll_cwsys_ks + 1);
                    imCwtjKsSave.setBqpb(0);
                    imCwtjKsSave.setJgid(jgid);
                    imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

                    imCwtjDao.insert(imCwtjKsSave);

                    ImCwtj zyCwtBqSave = new ImCwtj();
                    zyCwtBqSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                    zyCwtBqSave.setCzlx(2);
                    zyCwtBqSave.setZyh(doGetToBedInfoReq.getZyh());
                    zyCwtBqSave.setBrks(ll_bqdm_Old);
                    zyCwtBqSave.setYsys(ll_cwsys_bq);
                    zyCwtBqSave.setXsys(ll_cwsys_bq + 1);
                    zyCwtBqSave.setBqpb(1);
                    zyCwtBqSave.setJgid(jgid);
                    zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                    imCwtjDao.insert(zyCwtBqSave);

                }
                if (ll_jcpb2 < 2) {
                	imCwszParam = new ImCwsz();
                    imCwszParam.setCwks(ll_bqks_New);
                    int ll_cwsys_ks = cwtj(imCwszParam);
                    imCwszParam.setCwks(null);
                    imCwszParam.setKsdm(ll_bqdm_New);
                    int ll_cwsys_bq = cwtj(imCwszParam);

                    ImCwtj imCwtjKsSave = new ImCwtj();
                    imCwtjKsSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                    imCwtjKsSave.setCzlx(2);
                    imCwtjKsSave.setZyh(doGetToBedInfoReq.getZyh());
                    imCwtjKsSave.setBrks(ll_bqks_New);
                    imCwtjKsSave.setYsys(ll_cwsys_ks);
                    imCwtjKsSave.setXsys(ll_cwsys_ks - 1);
                    imCwtjKsSave.setBqpb(0);
                    imCwtjKsSave.setJgid(jgid);
                    imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

                    imCwtjDao.insert(imCwtjKsSave);

                    ImCwtj zyCwtBqSave = new ImCwtj();
                    zyCwtBqSave.setCzrq(new Timestamp(System.currentTimeMillis()));
                    zyCwtBqSave.setCzlx(2);
                    zyCwtBqSave.setZyh(doGetToBedInfoReq.getZyh());
                    zyCwtBqSave.setBrks(ll_bqks_New);
                    zyCwtBqSave.setYsys(ll_cwsys_bq);
                    zyCwtBqSave.setXsys(ll_cwsys_bq - 1);
                    zyCwtBqSave.setBqpb(1);
                    zyCwtBqSave.setJgid(jgid);
                    zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                    imCwtjDao.insert(zyCwtBqSave);
                }
            }
            if (ll_zyh_New > 0) {
                // // 床位对调
                imCwszDao.updateCwdd(doGetToBedInfoReq.getZyh(), doGetToBedInfoReq.getNewCwhm(), ll_zyh_New, jgid);

            } else {
                // 转到空床
                imCwszDao.updateKc(doGetToBedInfoReq.getZyh(), doGetToBedInfoReq.getNewCwhm(), jgid, doGetToBedInfoReq.getNewBrbq());
            }
            //转到空床JCKS = null
            imCwszDao.updateKcJcks(doGetToBedInfoReq.getZyh(), doGetToBedInfoReq.getNewCwhm(), ll_bqdm_New);
            if (ll_zyh_New > 0) {
                //床位对调(老)
                imCwszDao.updateCwddOld(ll_zyh_New, doGetToBedInfoReq.getOldCwhm(), doGetToBedInfoReq.getZyh(), jgid);
                // 转到空床(老)
                imCwszDao.updateKcOld(ll_zyh_New, doGetToBedInfoReq.getOldCwhm(), ll_bqks_Old, ll_bqdm_Old);
            } else {
                // 转到空床
                imCwszDao.updateZckc(doGetToBedInfoReq.getOldCwhm(), doGetToBedInfoReq.getZyh(), jgid);
            }

            ImZkjl imZkjlSave = new ImZkjl();
            imZkjlSave.setZyh(doGetToBedInfoReq.getZyh());
            imZkjlSave.setHclx(2);
            imZkjlSave.setBqzxrq(new Timestamp(System.currentTimeMillis()));
            imZkjlSave.setBqzxgh(userId.toString());
            imZkjlSave.setZxbz(3);
            imZkjlSave.setHqch(doGetToBedInfoReq.getOldCwhm());
            imZkjlSave.setHqbq(ll_bqdm_Old);
            imZkjlSave.setHqks(ll_bqks_Old);
            imZkjlSave.setHhks(doGetToBedInfoReq.getBrks());
            imZkjlSave.setHhbq(ll_bqdm_New);
            imZkjlSave.setHhch(doGetToBedInfoReq.getNewCwhm());
            imZkjlSave.setJgid(jgid);
            imZkjlSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_ZKJL));
            imZkjlDao.insert(imZkjlSave);
            if (ll_zyh_New > 0) {
                ImZkjl imZkjlSaveNew=new ImZkjl();
                imZkjlSaveNew.setZyh(doGetToBedInfoReq.getZyh());
                imZkjlSaveNew.setHclx(2);
                imZkjlSaveNew.setBqzxrq(new Timestamp(System.currentTimeMillis()));
                imZkjlSaveNew.setBqzxgh(userId.toString());
                imZkjlSaveNew.setZxbz(3);
                imZkjlSaveNew.setHqch(doGetToBedInfoReq.getOldCwhm());
                imZkjlSaveNew.setHqbq(ll_bqdm_New);
                imZkjlSaveNew.setHqks(ll_bqks_New);
                imZkjlSaveNew.setHhks(doGetToBedInfoReq.getBrks());
                imZkjlSaveNew.setHhbq(ll_bqdm_Old);
                imZkjlSaveNew.setHhch(doGetToBedInfoReq.getOldCwhm());
                imZkjlSaveNew.setJgid(jgid);
                imZkjlSaveNew.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_ZKJL));
                imZkjlDao.insert(imZkjlSave);


            }

            ImHcmx imHcmxSave=new ImHcmx();
            imHcmxSave.setZyh(doGetToBedInfoReq.getZyh());
            imHcmxSave.setHcrq(new Timestamp(System.currentTimeMillis()));
            imHcmxSave.setHclx(1);
            imHcmxSave.setHqch(doGetToBedInfoReq.getOldCwhm());
            imHcmxSave.setHhch(doGetToBedInfoReq.getNewCwhm());
            imHcmxSave.setHqks(ll_bqks_Old);
            imHcmxSave.setHhks(ll_bqks_New);
            imHcmxSave.setHqbq(ll_bqdm_New);
            imHcmxSave.setHhbq(ll_bqdm_Old);
            imHcmxSave.setJscs(0);
            imHcmxSave.setCzgh(userId.toString());
            imHcmxSave.setJgid(jgid);
            imHcmxDao.insert(imHcmxSave);

            if (ll_zyh_New > 0) {
                ImHcmx imHcmxSaveNew=new ImHcmx();
                imHcmxSaveNew.setZyh(ll_zyh_New);
                imHcmxSaveNew.setHcrq(new Timestamp(System.currentTimeMillis()));
                imHcmxSaveNew.setHclx(1);
                imHcmxSaveNew.setHqch(doGetToBedInfoReq.getNewCwhm());
                imHcmxSaveNew.setHhch(doGetToBedInfoReq.getOldCwhm());
                imHcmxSaveNew.setHqks(ll_bqks_New);
                imHcmxSaveNew.setHhks(ll_bqks_Old);
                imHcmxSaveNew.setHqbq(ll_bqdm_New);
                imHcmxSaveNew.setHhbq(ll_bqdm_Old);
                imHcmxSaveNew.setJscs(0);
                imHcmxSaveNew.setCzgh(userId.toString());
                imHcmxSaveNew.setJgid(jgid);
                imHcmxDao.insert(imHcmxSaveNew);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00034");
        }


        return ReturnEntityUtil.success();

    }

 

    /**
     * 床位分配
     * @param cwfpReq
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnEntity<String> cwfp(CwfpReq cwfpReq, SysUser user){

        Map<String, Object> parameters = BUHISUtil.caseInsensitiveMap(cwfpReq);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用户的机构ID
        Integer jgid = user.getHospitalId();

        Integer userId = user.getUserId();
        // 病人科室
        Integer ll_brks = 0;
        // 床位科室
        Integer ll_cwks = 0;
        // 床位病区
        Integer ll_cwbq = 0;
        // 加床判别
        Integer ll_jcpb = 0;
        Integer zyh = 0;
            // 取病人及床位信息
            ImHzry parametersbrryinfo = new ImHzry();
            parametersbrryinfo.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
            parametersbrryinfo.setJgid(jgid);
            List<ImHzry> brryinfomap = imHzryDao.findByEntity(parametersbrryinfo);


            ImCwsz parameterscwszinfo = new ImCwsz();
            parameterscwszinfo.setBrch(MapAttributeUtils.getString("brch",parameters));
            parameterscwszinfo.setKsdm(cwfpReq.getKsdm());
            parameterscwszinfo.setJgid(jgid);
            List<ImCwsz> cwszinfomap = imCwszDao.findByEntity(parameterscwszinfo);
            if (brryinfomap != null && brryinfomap.size() > 0) {
                ll_brks = brryinfomap.get(0).getBrks();
            }
            if (cwszinfomap != null && cwszinfomap.size() > 0) {
                ll_cwks  = cwszinfomap.get(0).getCwks();
                ll_cwbq = cwszinfomap.get(0).getKsdm();
                ll_jcpb =  cwszinfomap.get(0).getJcpb();
                zyh=cwszinfomap.get(0).getZyh();
            }
            // 判断床位是否已使用
//            ImCwsz parameterscwszzyh = new ImCwsz();
//            parameterscwszzyh.setJgid(jgid);
//            parameterscwszzyh.setBrch(MapAttributeUtils.getString("brch",parameters));
//            List<ImCwsz> zyhmap = imCwszDao.findByEntity(parameterscwszzyh);
//            if(zyhmap.size()>0){
//                zyh=zyhmap.get(0).getZyh();
//            }
            if (zyh !=null && zyh.intValue()!=-1) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00035",new String[]{MapAttributeUtils.getString("brch",parameters)});
            }
            ImCwsz parameterscwsz = new ImCwsz();
            parameterscwsz.setJgid(jgid);
            parameterscwsz.setBrch(MapAttributeUtils.getString("brch",parameters));
            parameterscwsz.setKsdm(cwfpReq.getKsdm());
            parameterscwsz.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
            imCwszDao.updateCwfp(parameterscwsz);
            logger.error("imCwszDao.updateCwfp params :"+ JSONUtil.toJsonStr(parameterscwsz));

            ImHzry parametersbrry = new ImHzry();
            parametersbrry.setJgid(jgid);
            parametersbrry.setBrch(MapAttributeUtils.getString("brch",parameters));
            parametersbrry.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
            parametersbrry.setBrbq(ll_cwbq);
            parametersbrry.setKsrq(new Timestamp(System.currentTimeMillis()));
            //===============================================
            //需要重新获取科室代码

            imHzryDao.updateCwfp(parametersbrry);
            logger.error("imCwszDao.updateCwfp params :"+ JSONUtil.toJsonStr(parametersbrry));

           // parametersbrry.setBrbq(MapAttributeUtils.getInteger("brks",parameters));
            ImRcjl parametersrcjlcount = new ImRcjl();
            parametersrcjlcount.setJgid(jgid);
            parametersrcjlcount.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
            parametersrcjlcount.setCzlx(1);
            parametersrcjlcount.setBqpb(0);
            List<ImRcjl> imRcjlList=imRcjlDao.findByEntity(parametersrcjlcount);

            if (imRcjlList.size()== 0) {
                Map<String, Object> parametersryrq = new HashMap<String, Object>();
                parametersryrq.put("zyh", MapAttributeUtils.getInteger("zyh",parameters));
                parametersryrq.put("ryrq", MapAttributeUtils.getString("ryrq",parameters));
                parametersryrq.put("brks", MapAttributeUtils.getString("brks",parameters));
                uf_ryrq_set(parametersryrq,user);
            }
            Map<String, Object> parametersSaveHcmx = new HashMap<String, Object>();
            parametersSaveHcmx.put("zyh", MapAttributeUtils.getInteger("zyh",parameters));
        try {
            parametersSaveHcmx.put("hcrq", sdf.parse(sdf.format(new Date())));
        } catch (ParseException e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00040");
        }
        parametersSaveHcmx.put("hclx", 0);
            parametersSaveHcmx.put("hhch", MapAttributeUtils.getString("brch",parameters));
            parametersSaveHcmx.put("hhks", ll_brks);
            parametersSaveHcmx.put("hhbq", ll_cwbq);
            parametersSaveHcmx.put("hqch", null);
            parametersSaveHcmx.put("hqks", null);
            parametersSaveHcmx.put("hqbq", null);
            parametersSaveHcmx.put("jscs", 0);
            parametersSaveHcmx.put("czgh", userId);
            parametersSaveHcmx.put("jgid", jgid);
            imHcmxDao.insert(parametersSaveHcmx);


            ImCwsz parameterscwszcwks = new ImCwsz();
            parameterscwszcwks.setCwks(ll_cwks);
            ImCwsz parameterscwszksdm = new ImCwsz();
            parameterscwszksdm.setKsdm(ll_cwbq);
            if (ll_jcpb < 2) {
                int ll_cwsys_ks = cwtj(parameterscwszcwks);
                int ll_cwsys_bq = cwtj(parameterscwszksdm);
                ImCwtj parametersSaveCwtjks = new ImCwtj();
                parametersSaveCwtjks.setCzrq(new Timestamp(System.currentTimeMillis()));
                parametersSaveCwtjks.setCzlx(1);
                parametersSaveCwtjks.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                parametersSaveCwtjks.setBrks(ll_cwks);
                parametersSaveCwtjks.setYsys(ll_cwsys_ks);
                parametersSaveCwtjks.setXsys(ll_cwsys_ks + 1);
                parametersSaveCwtjks.setBqpb(0);
                parametersSaveCwtjks.setJgid(jgid);
                parametersSaveCwtjks.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                imCwtjDao.insert(parametersSaveCwtjks);
                ImCwtj parametersSaveCwtjbq =new ImCwtj();
                parametersSaveCwtjbq.setCzrq(new Timestamp(System.currentTimeMillis()));
                parametersSaveCwtjbq.setCzlx(1);
                parametersSaveCwtjbq.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                parametersSaveCwtjbq.setBrks(ll_cwbq);
                parametersSaveCwtjbq.setYsys(ll_cwsys_bq);
                parametersSaveCwtjbq.setXsys(ll_cwsys_bq + 1);
                parametersSaveCwtjbq.setBqpb(1);
                parametersSaveCwtjbq.setJgid(jgid);
                parametersSaveCwtjbq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
                imCwtjDao.insert(parametersSaveCwtjbq);
            }



        return ReturnEntityUtil.success();
    }

    /**
     * 设置入院日期设置入院日期
     * 入参： parameters long al_zyh 住院号,datetime adt_xryrq
     * @param parameters
     * @param user
     */
    public  void uf_ryrq_set(Map<String, Object> parameters,SysUser user){
        // 获取取病人的病人科室和病人病区
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用户的机构ID
        Integer jgid = user.getHospitalId();
        ImHzry parametersksbqlcrq = new ImHzry();
        parametersksbqlcrq.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
        parametersksbqlcrq.setJgid(jgid);
        Timestamp ldt_now = new Timestamp(System.currentTimeMillis());
        Date ldt_yryrq = null;
        Date adt_xryrq = null;
        try {
            if (parameters.get("ryrq") != null) {
                adt_xryrq = sdf.parse(MapAttributeUtils.getString("ryrq",parameters));
            }
        List<ImHzry>    ksbqmap = imHzryDao.findByEntity(parametersksbqlcrq);
            // 取原入院日期到ldt_yryrq，如果取不到则为null
           ImRcjl imRcjlParam=new ImRcjl();
            imRcjlParam.setZyh(parametersksbqlcrq.getZyh());
            imRcjlParam.setJgid(jgid);
            imRcjlParam.setCzlx(1);
            imRcjlParam.setBqpb(0);
            List<ImRcjl> imRcjlList=imRcjlDao.findByEntity(imRcjlParam);
            if (imRcjlList.size() > 1) {
                throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00036");
            }
            if (imRcjlList.size() == 1) {

                    ldt_yryrq = MapAttributeUtils.getTimestamp("lcrq",parameters);
            }
            // 日期没改变
            if (ldt_yryrq != adt_xryrq) {
                // 进行取消入院处理

                if (adt_xryrq == null) {
                    ImRcjl qxryclParam=new ImRcjl();
                    qxryclParam.setJgid(jgid);
                    qxryclParam.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                    qxryclParam.setCzlx(1);
                    imRcjlDao.qxrycl(qxryclParam);
                } else {
                    if (ldt_now.getTime() < adt_xryrq.getTime()) {
                        throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00036");
                    }
                    // 如果原入院日期为空,则新设置入院日期
                    if (ldt_yryrq == null) {
                        ImHzry parametersbrryryrq = new ImHzry();
                        parametersbrryryrq.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                        parametersbrryryrq.setJgid(jgid);
                        parametersbrryryrq.setRyrq(new Timestamp(adt_xryrq.getTime()));
                        imHzryDao.updateRyrq(parametersbrryryrq);

                        Map<String, Object> parametersrcjl = new HashMap<String, Object>();
                        parametersrcjl.put("jgid", jgid);
                        parametersrcjl.put("zyh", MapAttributeUtils.getInteger("zyh",parameters));
                        if (ksbqmap != null && ksbqmap.size() > 0) {
                            parametersrcjl.put("brks",ksbqmap.get(0).getBrks());
                        }
                        parametersrcjl.put("czlx", 1);
                        parametersrcjl.put("bqpb", 0);
                        parametersrcjl.put("cyfs", 0);
                        parametersrcjl.put("jyxx", null);
                        parametersrcjl.put("ryrq", MapAttributeUtils.getString("ryrq",parameters));
                        uf_ywcl(parametersrcjl,user);
                        Map<String, Object> parametersrcjl2 = new HashMap<String, Object>();
                        parametersrcjl2.put("jgid", jgid);
                        parametersrcjl2.put("zyh", MapAttributeUtils.getInteger("zyh",parameters));
                        if (ksbqmap != null && ksbqmap.size() > 0) {
                            parametersrcjl2.put("brks",ksbqmap.get(0).getBrbq());
                        }
                        parametersrcjl2.put("czlx", 1);
                        parametersrcjl2.put("bqpb", 1);
                        parametersrcjl2.put("cyfs", 0);
                        parametersrcjl2.put("jyxx", null);
                        parametersrcjl2.put("ryrq", MapAttributeUtils.getString("ryrq",parameters));
                        uf_ywcl(parametersrcjl2,user);
                    } else {
                        // 变动入院日期
                        ImHzry parametersupdbrryryrq = new ImHzry();
                        parametersupdbrryryrq.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                        parametersupdbrryryrq.setJgid(jgid);
                        parametersupdbrryryrq.setRyrq(new Timestamp(adt_xryrq.getTime()) );
                        imHzryDao.updateRyrq(parametersupdbrryryrq);
                        //修改住院临床记录的临床日期
                        ImRcjl parametersrcjllcrq = new ImRcjl();
                        parametersrcjllcrq.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
                        parametersrcjllcrq.setJgid(jgid);
                        parametersrcjllcrq.setCzrq(new Timestamp(ldt_now.getTime()));
                        parametersrcjllcrq.setLcrq(new Timestamp(adt_xryrq.getTime()));
                        parametersrcjllcrq.setCzlx(1);
                        imRcjlDao.updateLcrq(parametersrcjllcrq);
                    }
                }
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0006");
        }
    }
    /**
     * 检查是否已经入院或出院
     *
     * 入参：parameters integer ai_czlx 操作类型（1=入院，-1 = 出院） ,long al_zyh住院号 datetime
     * adt_lcrq 临床日期 ,long al_brks 病人科室 ,integer ai_bqpb 病区判别（0：科室 1:病区）,long
     * al_cyfs 出院方式,string as_jyxx 出院建议信息
     */
    public  void uf_ywcl(Map<String, Object> parameters, SysUser user) {
        //用户的机构ID
        Integer jgid = user.getHospitalId();
        ImRcjl parametersrcjl = new ImRcjl();
        parametersrcjl.setJgid(jgid);
        parametersrcjl.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
        parametersrcjl.setBrks(MapAttributeUtils.getInteger("brks",parameters));
        int czlx = MapAttributeUtils.getInteger("czlx",parameters);
        int bqpb = MapAttributeUtils.getInteger("bqpb",parameters);
        parametersrcjl.setBqpb(bqpb);
        int al_cyfs = MapAttributeUtils.getInteger("cyfs",parameters);
        String as_jyxx = MapAttributeUtils.getString("jyxx",parameters);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (czlx == 1) {
                parametersrcjl.setCzlx(1);
                List<ImRcjl> imRcjlList=imRcjlDao.findByEntity(parametersrcjl);

                if (imRcjlList.size() > 0) {
                    throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00037");
                }
            } else {
                parametersrcjl.setCzlx(-1);
                List<ImRcjl> imRcjlList=imRcjlDao.findByEntity(parametersrcjl);
                if (imRcjlList.size() > 0) {
                    throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00038");
                }
            }
           ImRcjl parametersSaveRcjl = new ImRcjl();
            parametersSaveRcjl.setJgid(jgid);
            parametersSaveRcjl.setCzrq(DateUtil.date().toTimestamp());
            parametersSaveRcjl.setLcrq(new Timestamp(sdf.parse(MapAttributeUtils.getString("ryrq",parameters)).getTime()) );
            parametersSaveRcjl.setCzlx(czlx);
            parametersSaveRcjl.setZyh(MapAttributeUtils.getInteger("zyh",parameters));
            parametersSaveRcjl.setBrks(MapAttributeUtils.getInteger("brks",parameters));
            parametersSaveRcjl.setYjzyrs(0);
            parametersSaveRcjl.setBqpb(bqpb);
            parametersSaveRcjl.setCyfs(al_cyfs);
            parametersSaveRcjl.setBzxx(as_jyxx);
            parametersSaveRcjl.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_RCJL));
            imRcjlDao.insert(parametersSaveRcjl);
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_ZYBRRY_00039");
        }
    }

    /**
     * 查询床位管理-床位分配-床位信息
      * @param brch
     * @param user
     * @return
     */
    public ReturnEntity<QueryCwfpInfoCwszResp> queryCwfpInfoResp(String brch,Integer brbq, SysUser user) {
        QueryCwfpInfoCwszResp queryCwfpInfoCwszResp=new QueryCwfpInfoCwszResp();
        ImCwsz param=new ImCwsz();
        param.setBrch(brch);
        param.setKsdm(brbq);
        param.setJgid(user.getHospitalId());
        List<ImCwsz> imCwszList=imCwszDao.findByEntity(param);
        if(imCwszList.size()>0){
            BeanUtils.copyProperties(imCwszList.get(0),queryCwfpInfoCwszResp);
        }

        return ReturnEntityUtil.success(queryCwfpInfoCwszResp);
    }

    /**
     * 查询没有分配床位的住院病人列表信息
     * @param cwfpQueryReq
     */
    public List<QueryCwfpInfoZybrryPageResp> doGetToBedInfo(CwfpQueryReq cwfpQueryReq, SysUser user) {
        cwfpQueryReq.setJgid(user.getHospitalId());

        return imHzryDao.queryNoBrchList(cwfpQueryReq);
    }

    /**
     * 据病人性别查询病人可分配的床位信息
     * @param brks
     * @param brxb
     * @param hospitalId
     * @return
     */
    public List<ImCwsz> findKcByBrksAndBrxbAndJgid(Integer brks, Integer brxb, Integer hospitalId) {
        return imCwszDao.findKcByBrksAndBrxbAndJgid(brks,brxb,hospitalId);
    }

    /**
     * 床位管理-转床（分页列表）
     * @param zcPatientPageInfoReq
     * @param
     * @return
     */
    public List<ZcPatientPageInfoResp> queryZcPatientPageInfo(ZcPatientPageInfoReq zcPatientPageInfoReq) {
      return   imCwszDao.queryZcPatientPageInfo(zcPatientPageInfoReq);
    }

    /**
     * 分页查询床位列表信息
     *
     * @param param
     * @return
     */
    public List<ImCwszResp> queryPage(ImCwszReq param) {
        return imCwszDao.queryPage(param);
    }

    /**
     * 床位管理 - 报表打印
     */
    public List<ReportBedListResp> queryReportBedList(ZybrCwszReq zybrCwszReq) {
        return imCwszDao.queryReportBedList(zybrCwszReq);
    }
}
