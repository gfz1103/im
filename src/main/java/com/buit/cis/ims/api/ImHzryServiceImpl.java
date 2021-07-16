package com.buit.cis.ims.api;

import com.buit.cis.im.request.ImHzryBaReq;
import com.buit.cis.im.request.ImHzryEmrReq;
import com.buit.cis.im.request.ImPacsPatientInfoReq;
import com.buit.cis.im.response.*;
import com.buit.cis.im.service.ImHzryService;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.response.ImHzryDctworkResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.commons.SysUser;
import com.buit.utill.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 病人入院信息
 *
 * @author liushijie
 */
@DubboService(timeout = 10000)
public class ImHzryServiceImpl implements ImHzryService {

    @Autowired
    private ImHzryDao imHzryDao;
    @Autowired
    private ImHzrySer imHzrySer;

    /**
     * 查询住院病人信息
     *
     * @param zyh 住院号
     * @return
     */
    @Override
    public List<ImHzryModel> getBrry(Integer zyh, String brxm) {
        ImHzry condition = new ImHzry();
        condition.setZyh(zyh);
        condition.setBrxm(brxm);
        List<ImHzry> existList = imHzryDao.findByEntity(condition);
        List<ImHzryModel> retList = new ArrayList<>();
        for (ImHzry imHzry : existList) {
            ImHzryModel model = BeanUtil.toBean(imHzry, ImHzryModel.class);
            retList.add(model);
        }
        return retList;
    }

    @Override
    public ImHzryResp getByZyh(Integer zyh) {
        ImHzry imHzry = imHzryDao.getById(zyh);

        return BeanUtil.toBean(imHzry, ImHzryResp.class);
    }

    @Override
    public List<ImHzryResp> queryByZyh(List<Integer> zyhList) {
        List<ImHzry> imHzryList = imHzryDao.queryByZyh(zyhList);

        return BeanUtil.toBeans(imHzryList, ImHzryResp.class);
    }

    @Override
    public ImHzryModel queryPatientBaseInfo(Integer zyh) {

        return BeanUtil.toBean(imHzrySer.doLoadBrxx(zyh), ImHzryModel.class);
    }

	@Override
	public PageInfo<ImHzryBaResp> queryImHzryBasyPage(ImHzryBaReq imHzryBaReq) {
		PageInfo<ImHzryBaResp> pageInfo = PageHelper.startPage(
				imHzryBaReq.getPageNum(), imHzryBaReq.getPageSize()).doSelectPageInfo(
	                    () -> imHzryDao.queryImHzryBasy(imHzryBaReq)
        );
		return pageInfo;
	}

	@Override
	public List<ImPacsPatientInfoResp> getHisPatientInfo(ImPacsPatientInfoReq req) {
		return imHzryDao.queryPacsPatientInfo(req);
	}

    @Override
    public PageInfo<ImHzryEmrResp> queryImHzryEmr(ImHzryEmrReq req, SysUser user) {
        req.setJgid(user.getHospitalId());
        PageInfo<ImHzryEmrResp> pageInfo = PageHelper.startPage(
                req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryImHzryEmr(req)
        );
        return pageInfo;
    }
}
