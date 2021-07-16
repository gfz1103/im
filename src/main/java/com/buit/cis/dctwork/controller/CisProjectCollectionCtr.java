package com.buit.cis.dctwork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.apply.response.DrugsBody;
import com.buit.apply.response.OpZt02DrugsResp;
import com.buit.apply.response.OpZt02ProjectResp;
import com.buit.apply.response.OpZt02Resp;
import com.buit.apply.service.OpZt02Service;
import com.buit.cis.dctwork.request.CisSssqSaveReq;
import com.buit.cis.dctwork.service.CisProjectCollectionSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
* @ClassName: CisProjectCollectionCtr
* @Description: 手术、检验、检查组套等"
* @author 龚方舟
* @date 2020年10月19日14:07:53
*
 */
@Api(tags="手术、检验、检查组套等")
@Controller
@RequestMapping("/cisProjectCollection")
public class CisProjectCollectionCtr extends BaseSpringController {

	@Autowired
	private CisProjectCollectionSer cisProjectCollectionSer;
	
	@DubboReference
	private OpZt02Service opZt02Service;
	
	
	/** 保存手术申请记录(surService.saveSssq) */
//    @RequestMapping("/saveSssq")
//    @ResponseBody
//    @ApiOperation(value="保存手术申请记录" ,httpMethod="POST")
//    public ReturnEntity saveSssq(@Valid @RequestBody CisSssqSaveReq cisSssqSaveReq) {
//    	cisProjectCollectionSer.saveSssq(cisSssqSaveReq, getUser());
//        return ReturnEntityUtil.success();
//    }
    
    @RequestMapping("/queryCommonDrugs")
    @ResponseBody
    @ApiOperation(value="住院常用药查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<OpZt02DrugsResp>> queryCommonDrugs(@ApiParam(name = "ksdm", value = "科室代码", required = true) 
    @RequestParam Integer ksdm ,@ApiParam(name = "pydm", value = "拼音代码", required = false) 
    @RequestParam(value = "pydm", required = false )  String pydm, PageQuery page){
    	
    	PageInfo<OpZt02DrugsResp> opZt02DrugsRespPage = new PageInfo<>();
    	PageInfo<DrugsBody> pageInfo = opZt02Service.queryCommonDrugsByQueryPage(getUser().getUserId(), ksdm, 
    			getUser().getHospitalId(), pydm, page);

    	
    	opZt02DrugsRespPage.setTotal(pageInfo.getTotal());     
    	
        if(CollectionUtils.isNotEmpty(pageInfo.getList())) {
        	Map<Integer, List<DrugsBody>> groups = pageInfo.getList().stream().
        			collect(Collectors.groupingBy(DrugsBody::getZtlb));
        	OpZt02DrugsResp opZt02DrugsResp =  new OpZt02DrugsResp();
        	opZt02DrugsResp.setWesternMedicineList(groups.get(1));
        	opZt02DrugsResp.setChineseMedicineList(groups.get(2));
        	opZt02DrugsResp.setHerbalMedicineList(groups.get(3));
        	ArrayList<OpZt02DrugsResp> opZt02DrugsRespList = new ArrayList<OpZt02DrugsResp>();
        	opZt02DrugsRespList.add(opZt02DrugsResp);
        	opZt02DrugsRespPage.setList(opZt02DrugsRespList);
        }else {
        	opZt02DrugsRespPage.setList(new ArrayList<OpZt02DrugsResp>());
        }
    	return ReturnEntityUtil.success(opZt02DrugsRespPage);
    }
    
    
    @RequestMapping("/queryCommonProject")
    @ResponseBody
    @ApiOperation(value="住院常用项目查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<OpZt02ProjectResp>> queryCommonProject(@ApiParam(name = "ksdm", value = "科室代码", required = true) 
    @RequestParam Integer ksdm ,@ApiParam(name = "pydm", value = "拼音代码", required = false) 
    @RequestParam(value = "pydm", required = false )  String pydm, PageQuery page){
    	return ReturnEntityUtil.success(opZt02Service.queryCommonProjectByQueryPage(getUser().getUserId(), ksdm, 
    			getUser().getHospitalId(), pydm, page)
    			);
    }
    
    
    @RequestMapping("/findPageByZtbhXm")
    @ResponseBody
    @ApiOperation(value="按组套编号-返回分页(项目)" ,httpMethod="POST")
    public ReturnEntity<PageInfo<OpZt02Resp>> findPageByZtbhXm(@ApiParam(name = "ztbh", value = "组套编号", required = true) @RequestParam Integer ztbh, 
    		PageQuery page){
        return ReturnEntityUtil.success(opZt02Service.findByZtbhXmByQueryPage(ztbh, page));
    }
    
    
}
