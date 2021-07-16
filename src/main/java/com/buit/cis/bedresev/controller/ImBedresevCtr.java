
package com.buit.cis.bedresev.controller;


import com.buit.cis.bedresev.model.ImBedresev;
import com.buit.cis.bedresev.request.BedInfoReq;
import com.buit.cis.bedresev.request.ImBedresevReq;
import com.buit.cis.bedresev.request.QueryWaitBedPageReq;
import com.buit.cis.bedresev.request.WaitBedReq;
import com.buit.cis.bedresev.response.BedInfoResp;
import com.buit.cis.bedresev.response.ImBedresevResp;
import com.buit.cis.bedresev.service.ImBedresevSer;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * <br>
 * 床位预约
 *
 * @author zhouhaishenng
 */
@Api(tags = "床位预约")
@Controller
@RequestMapping("/imbedresev")
public class ImBedresevCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(ImBedresevCtr.class);

    @Autowired
    private ImBedresevSer imBedresevSer;

    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "分页查询住院预约信息", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImBedresevResp>> queryPage(QueryWaitBedPageReq queryWaitBedPageReq) {
        ImBedresev imBedresev = new ImBedresev();
        BeanUtils.copyProperties(queryWaitBedPageReq, imBedresev);
        imBedresev.setJgid(getUser().getHospitalId());
        PageInfo<ImBedresevResp> pageInfo = PageHelper.startPage(
                queryWaitBedPageReq.getPageNum(), queryWaitBedPageReq.getPageSize()).doSelectPageInfo(
                () -> imBedresevSer.findByEntity(imBedresev)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/checkSfyb")
    @ResponseBody
    @ApiOperationSupport(author = "宋宇")
    @ApiOperation(value = "床位预约判断性质是否是医保", httpMethod = "POST")
    public ReturnEntity<Boolean> checkSfyb(Integer brxz) {

        return ReturnEntityUtil.success(imBedresevSer.checkSfyb(brxz));
    }

    @RequestMapping("/queryAllBed")
    @ResponseBody
    @ApiOperationSupport(author = "jiangwei")
    @ApiOperation(value = "查询所有床位", httpMethod = "POST")
    public ReturnEntity<PageInfo<BedInfoResp>> queryAllBed(BedInfoReq req) {
        req.setJgid(getUser().getHospitalId());
        PageInfo<BedInfoResp> pageInfo = PageHelper.startPage(
                req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                () -> imBedresevSer.queryAllBed(req)
        );
        return ReturnEntityUtil.success(pageInfo);
    }


    /**
     * 新增住院预约信息
     *
     * @param imBedresev
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "新增住院预约信息", httpMethod = "POST")
    public ReturnEntity<String> add(ImBedresevReq imBedresev) throws ParseException {
        imBedresev.setJgid(getUser().getHospitalId());
        imBedresevSer.add(imBedresev, getUser());
        return ReturnEntityUtil.success();
    }

    /**
     * 修改住院预约信息
     *
     * @param imBedresev
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "修改住院预约信息", httpMethod = "POST")
    public ReturnEntity<String> edit(ImBedresevReq imBedresev) {
        ImBedresev imBed = new ImBedresev();
        BeanUtils.copyProperties(imBedresev, imBed);
        imBedresevSer.update(imBed);
        return ReturnEntityUtil.success();
    }

    /**
     * 取消预约
     *
     * @param jlxh
     * @return
     */
    @RequestMapping("/cancel")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "取消预约", httpMethod = "POST")
    public ReturnEntity<String> cancel(Integer jlxh) {

        return imBedresevSer.cancel(jlxh);
    }

    @RequestMapping("/queryWaitBedPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "分页查询预约待床信息", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImBedresevResp>> queryWaitBedPage(QueryWaitBedPageReq req) {
        ImBedresev imBedresev = new ImBedresev();
        BeanUtils.copyProperties(req, imBedresev);
        if (req.getCwxb() != null && (1 == req.getCwxb() || 2 == req.getCwxb())) {
            imBedresev.setBrxb(req.getCwxb());
        }
        imBedresev.setJgid(getUser().getHospitalId());
        imBedresev.setSortColumns("yyzyrq desc");
        PageInfo<ImBedresevResp> pageInfo = PageHelper.startPage(
                req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                () -> imBedresevSer.findByEntity(imBedresev)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 待床
     *
     * @param waitBedReq
     * @return
     */
    @RequestMapping("/waitBed")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "待床", httpMethod = "POST")
    public ReturnEntity<String> waitBed(WaitBedReq waitBedReq) {

        return imBedresevSer.waitBed(waitBedReq, getUser());
    }

    /**
     * 取消待床
     *
     * @param jlxh
     * @return
     */
    @RequestMapping("/cancelWaitBed")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "取消待床", httpMethod = "POST")
    public ReturnEntity<String> cancelWaitBed(Integer jlxh) {

        return imBedresevSer.cancelWaitBed(jlxh);
    }

    /**
     * 住院预约读卡操作
     *
     * @param cardNo
     * @return
     */
    @RequestMapping("/readCardInfo")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "读卡", httpMethod = "POST")
    public ReturnEntity<ImBedresevResp> readCardInfo(String cardNo) {

        return imBedresevSer.readCardInfo(cardNo, getUser());
    }


}

