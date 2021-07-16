
package com.buit.cis.ims.controller;

import com.buit.op.request.OpZydjQueryCardnoPageReq;
import com.buit.op.response.OpZydjResp;
import com.buit.commons.BaseSpringController;
import com.buit.op.service.OpZydjService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 *     门诊住院登记
 * @author zhouhaisheng
 */
@Api(tags="门诊住院登记")
@Controller
@RequestMapping("/opzydj")
public class OpZydjCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(OpZydjCtr.class);
    
    @DubboReference
    private OpZydjService opZydjService;

    /**
     * 门诊住院申请列表分页查询
     * @param opzydj
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="门诊住院申请列表分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<OpZydjResp>> queryPage(OpZydjQueryCardnoPageReq opzydj){
        opzydj.setSortColumns("kdrq desc");
        return ReturnEntityUtil.success(opZydjService.findMzzydj(opzydj));
    }


    /**
     * 获取单条门诊住院申请信息
     * @param djid
     * @return
     */
    @RequestMapping("/getOneByEntity")
    @ResponseBody
    @ApiOperation(value="获取单条门诊住院申请信息" ,httpMethod="POST")
    public ReturnEntity<OpZydjResp> getOneByEntity(Integer djid){
        OpZydjResp opZydjResp=opZydjService.getById(djid);
        return ReturnEntityUtil.success(opZydjResp);
    }




}

