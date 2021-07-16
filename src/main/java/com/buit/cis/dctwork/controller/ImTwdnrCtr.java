
package com.buit.cis.dctwork.controller;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.request.NisSmtzBatchQueryReq;
import com.buit.cis.dctwork.request.NisSmtzBatchSaveReq;
import com.buit.cis.dctwork.response.NisSmtzBatchListResp;
import com.buit.cis.dctwork.response.NisSmtzListResp;
import com.buit.cis.dctwork.response.TemperatureChartResp;
import com.buit.cis.dctwork.service.ImTwdnrSer;
import com.buit.cis.ims.enums.ZFPBEnum;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImTwdnr;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.request.ImTwdReq;
import com.buit.cis.ims.request.ImTwdnrReq;
import com.buit.cis.ims.response.ImTwdnrResp;
import com.buit.cis.ims.service.ImZyjsSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.BeanUtil;
import com.buit.utill.PageUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 住院体温单内容<br>
 * @author GONGFANGZHOU
 */
@Api(tags="住院体温单内容")
@Controller
@RequestMapping("/imtwdnr")
public class ImTwdnrCtr extends BaseSpringController {
    
    static final Logger logger = LoggerFactory.getLogger(ImTwdnrCtr.class);
    
    @Autowired
    private ImTwdnrSer imTwdnrSer;
    
    @Autowired
    private ImZyjsSer imZyjsSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ImTwdnrResp>> queryPage(ImTwdnrReq imtwdnr, PageQuery page){
        PageInfo<ImTwdnr> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> imTwdnrSer.findByEntity(imtwdnr)
            );
        PageInfo<ImTwdnrResp> ret = BeanUtil.toPage(pageInfo, ImTwdnrResp.class);
        return ReturnEntityUtil.success(ret);
    }
  
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增住院体温单内容" ,httpMethod="POST")
    public ReturnEntity add(ImTwdnrReq imTwdnr) {
        imTwdnrSer.add(imTwdnr);
        return ReturnEntityUtil.success();
    }

    /**
     * 作废
     */
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value = "作废住院体温单内容", httpMethod = "POST")
    public ReturnEntity<String> invalid(@ApiParam(name = "id", value = "主键id", required = true) @RequestParam Integer id) {
        imTwdnrSer.invalid(id);
        return ReturnEntityUtil.success(String.valueOf(ZFPBEnum.code_1.getCode()));
    }

    /** 修改*/
    @RequestMapping("/update")
    @ResponseBody
    @ApiOperation(value="住院体温单内容修改" ,httpMethod="POST")
    public ReturnEntity<ImTwdnrResp> update(ImTwdnrReq imTwdnr)  {
        imTwdnrSer.update(imTwdnr);        
        return ReturnEntityUtil.success();            
    }

    @RequestMapping("/getSelectksrq")
    @ResponseBody
    @ApiOperation(value="住院体温单获取开始日期" ,httpMethod="POST")
    public ReturnEntity<ImZyjs> getSelectksrq(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh)  {
    	ImZyjs imZyjs = new ImZyjs();
    	imZyjs.setZyh(zyh);
    	imZyjs.setSortColumns("JSRQ desc");
    	List<ImZyjs> imZyjsList = imZyjsSer.findByEntity(imZyjs);
    	if(CollectionUtils.isNotEmpty(imZyjsList)) {
    		imZyjs = imZyjsList.get(0);
    	}
        return ReturnEntityUtil.success(imZyjs);            
    }
    
    @RequestMapping("/queryTwdPatientInfo")
    @ResponseBody
    @ApiOperation(value="住院体温单查询未出院病人列表" ,httpMethod="POST")
    public ReturnEntity<List<ImHzry>> queryTwdPatientInfo(@ApiParam(name = "brbq", value = "病人病区", required = true) 
	@RequestParam Integer brbq)  {
        return ReturnEntityUtil.success(imTwdnrSer.queryTwdPatientInfo(brbq));            
    }
    
    @RequestMapping("/saveTwdSmtzInfo")
    @ResponseBody
    @ApiOperation(value="住院体温单生命体征保存" ,httpMethod="POST")
    public ReturnEntity saveTwdSmtzInfo(@RequestBody ImTwdReq zyTwdReq)  {
    	imTwdnrSer.saveSmtz(zyTwdReq, this.getUser());
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/deleteTwdSmtz")
    @ResponseBody
    @ApiOperation(value="住院体温单生命体征删除" ,httpMethod="POST")
    public ReturnEntity deleteTwdSmtz(@RequestBody List<Integer> cjhList)  {
    	imTwdnrSer.deleteSmtz(cjhList);
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/querySmtzInfoByZyh")
    @ResponseBody
    @ApiOperation(value="住院体温单根据住院号查询病人生命体征列表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisSmtzListResp>> querySmtzInfoByZyh(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, PageQuery page)  {
        return ReturnEntityUtil.success(PageUtil.getPageInfo(page.getPageNum(), page.getPageSize(), imTwdnrSer.querySmtzInfoByZyh(zyh)));            
    }
    
    @RequestMapping("/getZyh")
    @ResponseBody
    @ApiOperation(value="通过住院号码查询住院号" ,httpMethod="POST")
    public ReturnEntity<ImHzry> getZyh(@ApiParam(name = "zyhm", value = "住院号码", required = true) 
	@RequestParam String zyhm)  {
        return ReturnEntityUtil.success(imTwdnrSer.doGetZyh(zyhm));            
    } 
    
    @RequestMapping("/loadTemperatureChart")
    @ResponseBody
    @ApiOperation(value="查询体温单表格数据" ,httpMethod="POST")
    public ReturnEntity<TemperatureChartResp> loadTemperatureChart(@ApiParam(name = "zyh", value = "住院号", required = true) 
	@RequestParam Integer zyh, @ApiParam(name = "currentWeek", value = "周数", required = true) 
	@RequestParam Integer currentWeek)  {
        return ReturnEntityUtil.success(imTwdnrSer.loadTemperatureChart(zyh, currentWeek, this.getUser()));            
    }  
    
    @RequestMapping("/saveBatchTwdnr")
    @ResponseBody
    @ApiOperation(value="批量保存新增或修改住院体温单内容" ,httpMethod="POST")
    public ReturnEntity saveBatchTwdnr(@RequestBody List<ImTwdnrReq> imTwdnrReqList) {
    	imTwdnrSer.saveBatchTwdnr(imTwdnrReqList);
        return ReturnEntityUtil.success();            
    }  
    
    @RequestMapping("/saveBatchTwdSmtzInfo")
    @ResponseBody
    @ApiOperation(value="批量保存体温单生命体征" ,httpMethod="POST")
    public ReturnEntity saveBatchTwdSmtzInfo(@RequestBody NisSmtzBatchSaveReq nisSmtzBatchSaveReq)  {
    	imTwdnrSer.saveBatchTwdSmtzInfo(nisSmtzBatchSaveReq, this.getUser());
        return ReturnEntityUtil.success();            
    }

    @RequestMapping("/queryTemperatureHzInfo")
    @ResponseBody
    @ApiOperation(value="批量查询体温单生命体征" ,httpMethod="POST")
    public ReturnEntity<List<NisSmtzBatchListResp>> queryTemperatureHzInfo(NisSmtzBatchQueryReq req)  {
        return ReturnEntityUtil.success(imTwdnrSer.queryTemperatureHzInfo(req, this.getUser()));            
    }
}

