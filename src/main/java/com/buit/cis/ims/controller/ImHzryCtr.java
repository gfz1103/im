
package com.buit.cis.ims.controller;


import com.buit.cis.im.request.ImHzryEmrReq;
import com.buit.cis.im.response.ImHzryResp;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.preService.HzryPreService;
import com.buit.cis.ims.request.QueryCwListByBrksAndBrxbReq;
import com.buit.cis.ims.request.RydjSaveReq;
import com.buit.cis.ims.response.DbxxResp;
import com.buit.cis.ims.response.RydjPublicParamResp;
import com.buit.cis.ims.service.ImHzrySer;
import com.buit.commons.BaseSpringController;
import com.buit.emr.model.EmrFileIndexModel;
import com.buit.emr.request.EmrFileIndexSealReq;
import com.buit.his.shyb.source.entity.SJ31;
import com.buit.his.shyb.source.entity.SJ31Res;
import com.buit.op.response.OpZydjResp;
import com.buit.op.service.OpZydjService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

/**
 * 住院_病人入院<br>
 *
 * @author ZHOUHAISHENG
 */
@Api(tags = "住院_病人入院")
@Controller
@RequestMapping("/imhzry")
public class ImHzryCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(ImHzryCtr.class);

    @Autowired
    private ImHzrySer imHzrySer;
    @Autowired
    private HzryPreService hzryPreService;
    @DubboReference
    private OpZydjService opZydjService;

    /**
     * 获取住院号码和病案号码，收据号码
     */
    @RequestMapping("/getRydjPublicParam")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "获取住院号码和病案号码，收据号码", httpMethod = "POST")
    public ReturnEntity<RydjPublicParamResp> getRydjPublicParam() {

        return imHzrySer.getRydjPublicParam(getUser());
    }

    /**
     * 获取留观号码和病案号码，收据号码
     */
    @RequestMapping("/getLGRydjPublicParam")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "获取留观号码和病案号码，收据号码", httpMethod = "POST")
    public ReturnEntity<RydjPublicParamResp> getLGRydjPublicParam() {
        return imHzrySer.getLGRydjPublicParam(getUser());
    }

    /**
     * 病人入院登记保存
     *
     * @param rydjSaveReq
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "病人入院登记保存", httpMethod = "POST")
    public ReturnEntity<String> doSaveRrdj(@RequestBody RydjSaveReq rydjSaveReq) throws ParseException {

        return imHzrySer.doSaveRrdj(rydjSaveReq, getUser(), getIpAddress());
    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ImHzryResp> edit(ImHzryReq imHzry)  {
//        imHzrySer.update(imHzry);
//        return ReturnEntityUtil.success(imHzry);
//    }
//
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ImHzryResp> delete(ImHzryReq imHzry) {
//        imHzrySer.removeByEntity(imHzry);
//        return ReturnEntityUtil.success(imHzry);
//    }

    /**
     * 卡号查询病人住院信息
     *
     * @param cardno
     * @return
     */
    @RequestMapping("/queryBrxxByCardno")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "卡号查询病人住院信息", httpMethod = "POST")
    public ReturnEntity<ImHzryResp> queryBrxxByCardno(@ApiParam(name = "ywlx", value = "业务类型 | 1：住院    2：留观") @RequestParam String ywlx,
                                                      @ApiParam(name = "cardno", value = "就诊卡号") @RequestParam String cardno,
                                                      @ApiParam(name = "isYb", value = "是否走医保") @RequestParam String isYb) {
        ImHzryResp imHzryResp = imHzrySer.queryBrxxByCardno(cardno, isYb, getIpAddress(), getUser()).getData();
        OpZydjResp opZydjResp = opZydjService.getByJzkhAndSqlx(cardno, ywlx);
        if (opZydjResp != null) {
            imHzryResp.setBrks(opZydjResp.getNszks());
            imHzryResp.setBrbq(Integer.valueOf(opZydjResp.getNszbq()));
            imHzryResp.setMzdjid(opZydjResp.getDjid());
            imHzryResp.setMzzdCode(opZydjResp.getZzdicd10());
            imHzryResp.setMzzdText(opZydjResp.getBrzd());
            imHzryResp.setLxrm(opZydjResp.getLxrm());
            if (StringUtils.isBlank(imHzryResp.getZyys())) {
                imHzryResp.setZyys(opZydjResp.getMzys());
            }
            if (StringUtils.isBlank(imHzryResp.getZsys())) {
                imHzryResp.setZsys(opZydjResp.getMzys());
            }
            imHzryResp.setLxdz(opZydjResp.getLxdz());
            imHzryResp.setLxdh(opZydjResp.getLxdh());
        }
        return ReturnEntityUtil.success(imHzryResp);
    }

    /**
     * 根据病人性别查询病人可分配的床位信息
     *
     * @param queryCwListByBrksAndBrxbReq
     * @return
     */
    @RequestMapping("/queryCwListByBrksAndBrxb")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value = "根据病人科室和病人性别查询可分配的床位信息", httpMethod = "POST")
    public ReturnEntity<List<ImCwsz>> queryCwhmListByBrksAndBrxb(QueryCwListByBrksAndBrxbReq queryCwListByBrksAndBrxbReq) {

        return ReturnEntityUtil.success(imHzrySer.queryCwhmListByBrksAndBrxb(queryCwListByBrksAndBrxbReq, getUser()));
    }

    /**
     * 卡内数据查询医保登记信息
     *
     * @param cardno djlb
     * @return
     */
    @RequestMapping("/sj31")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value = "查询登记情况", httpMethod = "POST")
    public ReturnEntity<SJ31> queryShybRegister(String cardno, String djlb) {
        return imHzrySer.queryShybRegister(cardno, djlb, getIpAddress(), getUser());
    }

    /**
     * 卡内数据撤销医保登记信息
     *
     * @param cardno djlb
     * @return
     */
    @RequestMapping("/sj21")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value = "撤销登记情况", httpMethod = "POST")
    public ReturnEntity revokeShybRegister(String cardno, String cxtype, String dbxm) {
        imHzrySer.revokeShybRegister(cardno, cxtype, dbxm, getIpAddress(), getUser());
        return ReturnEntityUtil.success();
    }

    /**
     * 卡内数据医保重新登记信息
     *
     * @param cardno djlb
     * @return
     */
    @RequestMapping("/resj11")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="重新登记" ,httpMethod="POST")
    public ReturnEntity<SJ31Res> reShybRegister(String cardno, String cxtype , String dbxm, String dbzd, String ysgh, String ysxm, String zyhm, String dbzl, String yy) throws ParseException {
        return  imHzrySer.reShybRegister(cardno, cxtype, dbxm, zyhm, dbzd,ysgh,dbzl,yy,getIpAddress(), getUser());

    }

    /**
     *   大病回执单打印
     * @param jzdyh
     * @return
     */
    @RequestMapping("/printsj11")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="登记打印" ,httpMethod="POST")
    public ReturnEntity<DbxxResp> printShybRegister(String jzdyh) throws ParseException {
        return imHzrySer.printShybRegister(jzdyh);
    }

    @RequestMapping("/queryImHzryEmr")
    @ResponseBody
    @ApiOperation(value = "病历归档查询出院患者信息", httpMethod = "POST")
    public ReturnEntity<PageInfo<ImHzryResp>> queryImHzryEmr(ImHzryEmrReq req) {
        req.setJgid(getUser().getHospitalId());
        PageInfo<ImHzryResp> pageInfo = PageHelper.startPage(
                req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                () -> imHzrySer.queryImHzryEmr(req)
        );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryEmrByZyh")
    @ResponseBody
    @ApiOperation(value = "根据住院号查询病历信息", httpMethod = "POST")
    public ReturnEntity<PageInfo<EmrFileIndexModel>> queryEmrByZyh(EmrFileIndexSealReq emrFileIndexSealReq) {
        PageInfo<EmrFileIndexModel> pageInfo = imHzrySer.queryEmrByZyh(emrFileIndexSealReq);
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/fileSeal")
    @ResponseBody
    @ApiOperation(value = "病历归档", httpMethod = "POST")
    public ReturnEntity fileSeal(Integer zyh) {
        imHzrySer.fileSeal(zyh,getUser());
        return ReturnEntityUtil.success();
    }
}

