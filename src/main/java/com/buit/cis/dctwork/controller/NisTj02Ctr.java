
package com.buit.cis.dctwork.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.NisTj02HzInfoReq;
import com.buit.cis.dctwork.request.NisTj02ReturnConfirmReq;
import com.buit.cis.dctwork.response.NisTj02DrugInfoResp;
import com.buit.cis.dctwork.response.NisTj02HzInfoResp;
import com.buit.cis.dctwork.service.NisTj02Ser;
import com.buit.commons.BaseSpringController;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联<br>
 * @author GONGFANGZHOU
 */
@Api(tags="病区_提交明细 | 记录病区医嘱提交明细的记录，与提交记录表通过TJXH关联")
@Controller
@RequestMapping("/nistj02")
public class NisTj02Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisTj02Ctr.class);
    
    @Autowired
    private NisTj02Ser nisTj02Ser;
    
    @RequestMapping("/queryBqDrugReturnHzInfo")
    @ResponseBody
    @ApiOperation(value = "查询可退回病区药品病人信息", httpMethod = "POST")
    public ReturnEntity<List<NisTj02HzInfoResp>> queryBqDrugReturnHzInfo(NisTj02HzInfoReq req) {
        req.setJgid(this.getUser().getHospitalId());
        return ReturnEntityUtil.success(nisTj02Ser.getEntityMapper().queryBqDrugReturnHzInfo(req));
    }
    
    @RequestMapping("/queryBqDrugReturnInfo")
    @ResponseBody
    @ApiOperation(value = "查询可退回病区药品信息", httpMethod = "POST")
    public ReturnEntity<List<NisTj02DrugInfoResp>> queryBqDrugReturnInfo(@RequestBody List<Integer> zyhList) {
        return ReturnEntityUtil.success(nisTj02Ser.getEntityMapper().
        		queryBqDrugReturnInfo(this.getUser().getHospitalId(), zyhList));
    }
    
    @RequestMapping("/doReturnConfirm")
    @ResponseBody
    @ApiOperation(value = "药品退回病区确认", httpMethod = "POST")
    public ReturnEntity doReturnConfirm(@RequestBody List<NisTj02ReturnConfirmReq> list) {     
    	nisTj02Ser.doReturnConfirm(list, this.getUser());
    	return ReturnEntityUtil.success();
    }
    
}

