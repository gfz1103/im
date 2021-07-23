package com.buit.cis.dctwork.api;


import com.buit.cis.dctwork.request.CisHzyzQueryApiReq;
import com.buit.cis.dctwork.request.SysUserModel;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.dctwork.service.ImHzryByDctworkSer;
import com.buit.cis.dctwork.service.ImHzryByDctworkService;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.utill.BeanUtil;
import com.buit.utill.PageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liushijie
 */
@DubboService(timeout = 10000)
public class ImHzryByDctworkServiceImpl implements ImHzryByDctworkService {

    @Autowired
    ImHzryByDctworkSer imHzryByDctworkSer;


    /**
     * 住院医生护士病人医嘱列表查询
     *
     * @param cisHzyzQueryReqApi
     * @param userModel
     * @return
     */
    @Override
    public PageInfo<CisHzyzQueryResp> execute(CisHzyzQueryApiReq cisHzyzQueryReqApi, SysUserModel userModel, PageQuery page) {
        CisHzyzQueryApiReq req = BeanUtil.toBean(cisHzyzQueryReqApi, CisHzyzQueryApiReq.class);
        SysUser user = BeanUtil.toBean(userModel, SysUser.class);

        List<CisHzyzQueryResp> list = imHzryByDctworkSer.execute(req, user);

        PageInfo<CisHzyzQueryResp> pageInfo = PageUtil.getPageInfo(page.getPageNum(), page.getPageSize(),list.stream().sorted(Comparator.comparing(CisHzyzQueryResp :: getKssj).reversed()).collect(Collectors.toList()));

        return pageInfo;
    }
}
