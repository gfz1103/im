package com.buit.cis.dctwork.service;



import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.NisFyyfDao;
import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.request.NisFyyfReq;
import com.buit.cis.dctwork.response.NisFyyfResp;
import com.buit.cis.ims.enums.ZxpbEnum;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.service.SysDictConfigSer;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

/**
 * 病区_发药药房<br>
 * @author zhouhaisheng
 */
@Service
public class NisFyyfSer extends BaseManagerImp<NisFyyf,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(NisFyyfSer.class);
    
    @Autowired
    private NisFyyfDao nisFyyfDao;
    
    @DubboReference
    private SysDictConfigSer sysDictConfigSer;
    
    @Override
    public NisFyyfDao getEntityMapper(){
        return nisFyyfDao;
    }
    @Autowired
    private RedisFactory redisFactory;
    /**
     * 新增病区_发药药房设置
     * @param nisFyyf
     * @return
     */
    public ReturnEntity<NisFyyfResp> add(NisFyyfReq nisFyyf, SysUser user) {
        NisFyyf yf=new NisFyyf();
        BeanUtils.copyProperties(nisFyyf,yf);
        yf.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_FYYF));
        yf.setJgid(user.getHospitalId());
        //默认注销类别是正常状态
        yf.setZxpb(ZxpbEnum.code_0.getCode());
        try {
            nisFyyfDao.insert(yf);
            sysDictConfigSer.upDateVersion("nis_fyyf");
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_BQFYYF_0001");
        }
        return ReturnEntityUtil.success();

    }

    /**
     * 修改病区_发药药房设置
     * @param nisFyyf
     * @param user
     * @return
     */
    public ReturnEntity<NisFyyfResp> edit(NisFyyfReq nisFyyf, SysUser user) {
        NisFyyf yf=new NisFyyf();
        BeanUtils.copyProperties(nisFyyf,yf);
        yf.setJgid(user.getHospitalId());
        try {
            nisFyyfDao.update(yf);
            sysDictConfigSer.upDateVersion("nis_fyyf");
        } catch (Exception e) {
            throw BaseException.create("ERROR_HIS_IM_IMS_BQFYYF_0002");
        }

        return ReturnEntityUtil.success();
    }

    /**
     * 注销病区_发药药房设置
     * @param jlxh
     * @return
     */
    public ReturnEntity<String> delete(Integer jlxh, SysUser user) {
        NisFyyf yf=new NisFyyf();
        yf.setJgid(user.getHospitalId());
        yf.setJlxh(jlxh);
        yf.setZxpb(ZxpbEnum.code_1.getCode());
        try {
            nisFyyfDao.updateZxpb(yf);
            sysDictConfigSer.upDateVersion("nis_fyyf");
        } catch (Exception e) {

            throw BaseException.create("ERROR_HIS_IM_IMS_BQFYYF_0003");
        }

        return ReturnEntityUtil.success();
    }
}
