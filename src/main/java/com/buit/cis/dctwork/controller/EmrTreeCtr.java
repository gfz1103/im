package com.buit.cis.dctwork.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buit.cis.dctwork.request.ImHzryEmrTreeReq;
import com.buit.emr.request.EmrFileIndexTreeReq;
import com.buit.utill.BeanUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.commons.BaseSpringController;
import com.buit.emr.model.EmrFileIndexTreeModel;
import com.buit.emr.service.EmrCatalogService;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.HrPersonnelService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Auther   : liushijie
 **/
@Api(tags = "病历树")
@Controller
@RequestMapping("/emrTree")
public class EmrTreeCtr extends BaseSpringController {

    @DubboReference
    private EmrCatalogService emrCatalogService;

//    @DubboReference
//    private OpBcjlService opBcjlService;

    @DubboReference
    private HrPersonnelService hrPersonnelService;

    @RequestMapping("/getTree")
    @ResponseBody
    @ApiOperation(value = "获取病历树", httpMethod = "POST")
    public ReturnEntity<Map> getTree(ImHzryEmrTreeReq imHzryEmrTreeReq) {
        Map result = new HashMap(2);
        HrPersonnelModel personnel = hrPersonnelService.getPersonnelById(getUser().getUserId());
        String mode = imHzryEmrTreeReq.getMode();
        String hm = imHzryEmrTreeReq.getHm();
        // 门诊医生站
        if ("1".equals(mode)) {
            List<EmrFileIndexTreeModel> outpatDoctor = emrCatalogService.doctorTree(BeanUtil.toBean(imHzryEmrTreeReq, EmrFileIndexTreeReq.class), personnel.getMedicalroles());
            result.put("outpatDoctor", outpatDoctor);
        }
        // 住院医生站、住院护士站
        if ("2".equals(mode) || "3".equals(mode)) {
            List<EmrFileIndexTreeModel> inpatDoctor = emrCatalogService.doctorTree(BeanUtil.toBean(imHzryEmrTreeReq, EmrFileIndexTreeReq.class), personnel.getMedicalroles());
            List<EmrFileIndexTreeModel> nurse = emrCatalogService.nurseTree(BeanUtil.toBean(imHzryEmrTreeReq, EmrFileIndexTreeReq.class), personnel.getMedicalroles());
            result.put("inpatDoctor", inpatDoctor);
            result.put("nurse", nurse);
        }
        return ReturnEntityUtil.success(result);
    }

    // TEST
//    @RequestMapping("/queryBcjl")
//    @ResponseBody
//    @ApiOperation(value = "查询门诊病程记录", httpMethod = "POST")
//    public ReturnEntity<OpBcjlModel> queryBcjl(@ApiParam(name = "jzlsh", value = "就这流水号", required = true) @RequestParam String jzlsh) {
//        return ReturnEntityUtil.success(opBcjlService.queryBcjl(jzlsh));
//    }



}
