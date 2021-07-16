
package com.buit.cis.bedresev.controller;


import com.buit.cis.bedresev.model.ImBedresev;
import com.buit.cis.bedresev.request.BedresevPatientQueryPageReq;
import com.buit.cis.bedresev.request.BedresevQueryReq;
import com.buit.cis.bedresev.request.ImBedresevReq;
import com.buit.cis.bedresev.request.QueryImBedresevCollectReq;
import com.buit.cis.bedresev.request.QueryImBedresevCollectResp;
import com.buit.cis.bedresev.request.QueryWaitBedPageReq;
import com.buit.cis.bedresev.request.WaitBedReq;
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

import java.util.List;

/**
 * <br>
 *     床位预约查询统计
 * @author zhouhaishenng
 */
@Api(tags="床位预约查询统计")
@Controller
@RequestMapping("/imbedresevReport")
public class ImBedresevReportCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImBedresevReportCtr.class);
    
    @Autowired
    private ImBedresevSer imBedresevSer;

    /**
     * 分页查询住院预约信息
     * @param bedresevPatientQueryPageReq
     * @param
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="分页查询住院预约信息" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ImBedresevResp>> queryPage(BedresevPatientQueryPageReq bedresevPatientQueryPageReq){
        ImBedresev imBedresev=new ImBedresev();
        BeanUtils.copyProperties(bedresevPatientQueryPageReq,imBedresev);
        imBedresev.setJgid(getUser().getHospitalId());
        PageInfo<ImBedresevResp> pageInfo = PageHelper.startPage(
                bedresevPatientQueryPageReq.getPageNum(), bedresevPatientQueryPageReq.getPageSize()).doSelectPageInfo(
                    () -> imBedresevSer.findByEntity(imBedresev)
            );
        return ReturnEntityUtil.success(pageInfo);
    }

    /**
     * 出院预约统计分析查询
     * @param queryImBedresevCollectReq
     * @return
     */
    @RequestMapping("/queryImBedresevCollect")
    @ResponseBody
    @ApiOperationSupport(author = "zhouhaisheng")
    @ApiOperation(value="出院预约统计分析查询" ,httpMethod="POST")
   public  ReturnEntity<PageInfo<QueryImBedresevCollectResp>> queryImBedresevCollect(QueryImBedresevCollectReq queryImBedresevCollectReq){


        PageInfo<QueryImBedresevCollectResp> pageInfo = PageHelper.startPage(
                queryImBedresevCollectReq.getPageNum(), queryImBedresevCollectReq.getPageSize()).doSelectPageInfo(
                () -> imBedresevSer.queryImBedresevCollect(queryImBedresevCollectReq)
        );
        return ReturnEntityUtil.success(pageInfo);


    }


}

