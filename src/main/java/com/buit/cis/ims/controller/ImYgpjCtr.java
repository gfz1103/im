
package com.buit.cis.ims.controller;

import com.buit.cis.ims.model.ImYgpj;
import com.buit.cis.ims.request.ImYgpjPageReq;
import com.buit.cis.ims.request.ImYgpjReq;
import com.buit.cis.ims.request.ImYgpjUpdateReq;
import com.buit.cis.ims.response.ImYgpjResp;
import com.buit.cis.ims.service.ImYgpjSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * 住院_员工票据<br>
 *
 * @author zhouhaisheng
 */
@Api(tags = "住院_员工票据")
@Controller
@RequestMapping("/imygpj")
public class ImYgpjCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(ImYgpjCtr.class);

    @Autowired
    private ImYgpjSer imYgpjSer;

    /**
     * 分页查询发票号码信息
     *
     * @param imYgpjPageReq
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "分页查询发票号码信息", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImYgpjResp>> queryPage(ImYgpjPageReq imYgpjPageReq) {
        ImYgpj imygpj = new ImYgpj();
        imygpj.setPageNum(imYgpjPageReq.getPageNum());
        imygpj.setPageSize(imYgpjPageReq.getPageSize());
        imygpj.setJgid(getUser().getHospitalId());
        imygpj.setYgdm(getUser().getUserId());
        imygpj.setPjlx(imYgpjPageReq.getPjlx());
        PageInfo<ImYgpjResp> pageInfo = PageHelper.startPage(
                imygpj.getPageNum(), imygpj.getPageSize()).doSelectPageInfo(
                () -> imYgpjSer.findYgpjPage(imygpj)
        );
        return ReturnEntityUtil.success(pageInfo);
    }


    /**
     * 发票号码维护信息详情
     *
     * @param jlxh
     * @return
     */
    @RequestMapping("/getOneByEntity")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "发票号码维护信息详情", httpMethod = "POST")
    public ReturnEntity<ImYgpjResp> getOneByEntity(Integer jlxh) {
        ImYgpj imYgpj = imYgpjSer.getById(jlxh);
        if (imYgpj == null) {
            return ReturnEntityUtil.success();
        }
        ImYgpjResp resp = new ImYgpjResp();
        BeanUtils.copyProperties(imYgpj, resp);
        return ReturnEntityUtil.success(resp);

    }

    /**
     * 新增发票号码维护信息
     *
     * @param imYgpj
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "新增发票号码维护信息", httpMethod = "POST")
    public ReturnEntity<String> add(ImYgpjReq imYgpj) throws ParseException {

        return imYgpjSer.add(imYgpj, getUser());
    }

    /**
     * 编辑发票号码维护信息
     *
     * @param imYgpjUpdateReq
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "编辑发票号码维护信息", httpMethod = "POST")
    public ReturnEntity<ImYgpjResp> edit(ImYgpjUpdateReq imYgpjUpdateReq) {

        return imYgpjSer.edit(imYgpjUpdateReq);
    }

    /**
     * 启用票据号码维护信息
     */
    @RequestMapping("/active")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "启用票据号码维护信息", httpMethod = "POST")
    public ReturnEntity<String> active(@ApiParam(name = "jlxh", value = "id", required = true) @RequestParam Integer jlxh) {
        ImYgpj imYgpj = imYgpjSer.getById(jlxh);
        return imYgpjSer.active(imYgpj);
    }

    /**
     * 停用发票号码维护信息
     */
    @RequestMapping("/disabled")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "停用票据号码维护信息", httpMethod = "POST")
    public ReturnEntity<String> disabled(@ApiParam(name = "jlxh", value = "id", required = true) @RequestParam Integer jlxh) {
        ImYgpj pj = new ImYgpj();
        pj.setJlxh(jlxh);
        pj.setSybz(1);
        pj.setJgid(getUser().getHospitalId());
        return imYgpjSer.disabled(pj);
    }

}

