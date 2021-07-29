
package com.buit.cis.dctwork.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.buit.cis.dctwork.model.NisQtyz;
import com.buit.cis.dctwork.request.DrugsTypkBqyzYpSrfReq;
import com.buit.cis.dctwork.response.NisQtyzSrfResp;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.DoctorTypeWriteSer;
import com.buit.cis.dctwork.service.NisQtyzSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.drug.request.QueryDrugAccountingApiReq;
import com.buit.drug.response.DrugsTypkAccountingYpApiResp;
import com.buit.drug.response.DrugsTypkBqyzYpApiResp;
import com.buit.drug.service.DrugsTypkOutSer;
import com.buit.mdi.response.HighQualityConsumablesResp;
import com.buit.mdi.service.MdiVejkfBatchViewService;
import com.buit.op.request.IOpZt01AccountingSrfReq;
import com.buit.op.response.IOpZt01AccountingSrfResp;
import com.buit.op.service.OpZt01Service;
import com.buit.system.request.FeeYlsfxmProjectSrfApiReq;
import com.buit.system.request.FeeYlsfxmProjectSrfReq;
import com.buit.system.response.FeeYlsfxmProjectSrfApiResp;
import com.buit.system.response.FeeYlsfxmProjectSrfResp;
import com.buit.system.response.HrPersonnelKsApiResp;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.FeeYlsfxmOutSer;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.BUHISUtil;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 住院医生站输入法
 * @ClassName: YpsrfCtr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月15日 上午10:32:27
 *
 */
@Api(tags = "住院医生站输入法")
@Controller
@RequestMapping("/doctorTypeWrite")
public class DoctorTypeWriteCtr extends BaseSpringController {

    static final Logger logger = LoggerFactory.getLogger(DoctorTypeWriteCtr.class);

    @DubboReference
    private FeeYlsfxmOutSer feeYlsfxmOutSer;
    
    @Autowired
    private NisQtyzSer nisQtyzSer;
    
    @Autowired
    private DoctorTypeWriteSer doctorTypeWriteSer;
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @Autowired
    private CisHzyzSer cisHzyzSer;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    
    @DubboReference
    private DrugsTypkOutSer drugsTypkOutSer;
    
    @DubboReference
    private OpZt01Service opZt01Service;
    
    @DubboReference
    private MdiVejkfBatchViewService mdiVejkfBatchViewService;
    
    @RequestMapping("/queryBqyzXm")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "病区医嘱项目输入法查询(包含商保性质)", httpMethod = "POST")
    public ReturnEntity<PageInfo<FeeYlsfxmProjectSrfApiResp>> queryBqyzXm(@Valid 
    		FeeYlsfxmProjectSrfApiReq req) {
    	PageInfo<FeeYlsfxmProjectSrfApiResp> pageInfo = new PageInfo<FeeYlsfxmProjectSrfApiResp>(new ArrayList<FeeYlsfxmProjectSrfApiResp>());
    	if(!"0".equals(req.getHssfqyxm())) {
			req.setZdlx("b.pydm");
			if (BUHISUtil.isContainChinese(req.getPydm())) {
				req.setZdlx("b.fymc");
			}
			req.setJgid(getUser().getHospitalId());
			pageInfo = feeYlsfxmOutSer.queryProjectOrderByPageInfo(req);
			
		}
//    	List<String> strList = pageInfo.getList().stream().map(o -> req.getBqdm()+ "-" + o.getXmbm()).collect(Collectors.toList());
//    	logger.error("医嘱耗材传参" + strList);
//    	if(!pageInfo.getList().isEmpty()) {
//    		List<HighQualityConsumablesResp> highResp;
//    		try {
//    			highResp = mdiVejkfBatchViewService.queryHighQualityConsumables(strList);
//    		} catch (Exception e2) {
//    			highResp = new ArrayList<HighQualityConsumablesResp>();
//    		}
//    		if(!highResp.isEmpty()) {
//    			Map<String, HighQualityConsumablesResp> map = 
//    					highResp.stream().collect(Collectors.toMap(o -> 
//    					String.format("%s-%s", o.getHisbmbm(), o.getSfdm()), e -> e, (e1, e2) -> e1));
//    			pageInfo.getList().forEach(item -> {
//        			String str = req.getBqdm()+"-"+item.getXmbm();
//        			item.setHcgg(map.get(str)!= null ? map.get(str).getGg() : null);
//        			item.setCjmc(map.get(str)!= null ? map.get(str).getSccjmc() : null);
//        			item.setSl(map.get(str)!= null ? map.get(str).getAmount() : null);
//        		});
//    		}
//    	}
    	return ReturnEntityUtil.success(pageInfo);
    }
    
    
    @RequestMapping("/queryNisQtyz")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "病区医嘱其他医嘱输入法查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<NisQtyzSrfResp>> queryNisQtyz(@ApiParam(name = "srdm", value = "拼音代码", required = true) 
    @RequestParam  String srdm, @ApiParam(name = "hssfqyzt", value = "护士是否启用行为医嘱输入", required = false)
    @RequestParam(value="hssfqyzt", required = false) String hssfqyzt, PageQuery page) {
    	PageInfo<NisQtyzSrfResp> pageInfo = new PageInfo<NisQtyzSrfResp>(new ArrayList<NisQtyzSrfResp>());
    	if(!"0".equals(hssfqyzt)) {
    		NisQtyz nisQtyz = new NisQtyz(); 
        	nisQtyz.setSrdm(srdm);
        	nisQtyz.setJgid(getUser().getHospitalId());
        	pageInfo = PageHelper.startPage(
            		page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisQtyzSer.getEntityMapper().queryQtyzSrf(nisQtyz)
            );
    	}
    	return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/queryBqyzYp")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "病区医嘱药品输入法查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<DrugsTypkBqyzYpApiResp>> queryBqyzYp(@Valid DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq,
    		PageQuery page) {  	
    	return ReturnEntityUtil.success(doctorTypeWriteSer.queryBqyzYp(drugsTypkBqyzYpSrfReq, this.getUser(), page));
    }

   
    
    @RequestMapping("/queryExpenseAccounting")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "费用记账项目输入法查询(包含商保性质)", httpMethod = "POST")
    public ReturnEntity<PageInfo<FeeYlsfxmProjectSrfResp>> queryExpenseAccounting(@Valid FeeYlsfxmProjectSrfReq
                                                                                          req) {
        req.setJgid(getUser().getHospitalId());
        PageInfo<FeeYlsfxmProjectSrfResp> pageInfo = feeYlsfxmOutSer.queryExpenseAccountingByPageInfo(req);
        List<FeeYlsfxmProjectSrfResp> list = pageInfo.getList();
        if (CollectionUtils.isEmpty(list)) {
            return ReturnEntityUtil.success();
        }
        //计算自负比列
        List<String> strList = list.stream().map(o -> {
            Integer fyxh = o.getFyxh();// 费用序号
            Integer fygb = cisHzyzSer.getfygb(0, fyxh);// 获取费用归并
            Map<String, Object> params = new HashMap<String, Object>(16);
            params.put("BRXZ", req.getBrxz());// 病人性质
            params.put("FYGB", fygb);// 费用归并
            params.put("TYPE", 0);// 药品类型 0=项目
            params.put("FYXH", fyxh);// 费用序号
            //=========================获取医保病人的支付比例支付比例的方法=============================
            Map<String, Object> zfblMap = cisHzyzSer.getPayProportion(params);
            BigDecimal zfbl = (BigDecimal) zfblMap.get("ZFBL");
            o.setZfbl(zfbl);
            return req.getBqdm() + "-" + o.getFyxh();
        }).collect(Collectors.toList());

        logger.error("耗材传参" + strList);
        //查询物资耗材库存视图
        List<HighQualityConsumablesResp> highResp = null;
        try {
            highResp = mdiVejkfBatchViewService.queryHighQualityConsumables(strList);
//            String json ="[{\"amount\":\"30\",\"gg\":\"针式：JP2-1-107\",\"hisbmbm\":\"35\",\"hisbmmc\":\"三病区\",\"price\":\"83\",\"sccjmc\":\"江苏雅凯医疗科技有限公司\",\"sfdm\":\"516\",\"unit\":\"支\",\"xh\":\"25支/盒 100支/箱\",\"zcmc\":\"一次性使用肠内营养泵管\"},{\"amount\":\"5\",\"gg\":\"针式：JP2-1-107\",\"hisbmbm\":\"35\",\"hisbmmc\":\"三病区\",\"price\":\"93\",\"sccjmc\":\"江苏雅凯医疗科技有限公司\",\"sfdm\":\"516\",\"unit\":\"支\",\"xh\":\"25支/盒 100支/箱\",\"zcmc\":\"一次性使用肠内营养泵管\"}]";
//            highResp = JSONArray.parseArray(json, HighQualityConsumablesResp.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (CollectionUtil.isEmpty(highResp)) {
            return ReturnEntityUtil.success(pageInfo);
        }

        //最终返回结果封装
        List<FeeYlsfxmProjectSrfResp> results = new ArrayList<>();
        //按项目编号分组（同一个项目有多种价格，每个价格对应一个库存）
        Map<String, List<HighQualityConsumablesResp>> highRespGroup = highResp.stream().collect(Collectors.groupingBy(HighQualityConsumablesResp::getSfdm));

        for (FeeYlsfxmProjectSrfResp resp : list) {
            //基础信息
            FeeYlsfxmProjectSrfResp result = new FeeYlsfxmProjectSrfResp();
            BeanUtils.copyProperties(resp, result);

            //库存信息
            List<HighQualityConsumablesResp> highRespListOfFyxm = highRespGroup.get(String.valueOf(resp.getFyxh()));
            if (CollectionUtils.isNotEmpty(highRespListOfFyxm)) {
                HighQualityConsumablesResp baseResp = highRespListOfFyxm.get(0);
                result.setHcgg(baseResp.getGg());
                result.setCjmc(baseResp.getSccjmc());
                BigDecimal sl = highRespListOfFyxm.stream().map(r -> new BigDecimal(r.getAmount())).reduce(BigDecimal.ZERO, BigDecimal::add);
                result.setSl(sl.toString());
            }
            results.add(result);
        }
        //将数据填充进分页对象里
        pageInfo.setList(results);

        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/queryProjectStackAccounting")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "费用记账查询项目组套", httpMethod = "POST")
    public ReturnEntity<PageInfo<IOpZt01AccountingSrfResp>> queryProjectStackAccounting(@ApiParam(name = "pydm", value = "拼音代码", required = true) 
    @RequestParam  String pydm, PageQuery page) {
    	IOpZt01AccountingSrfReq req = new IOpZt01AccountingSrfReq();
    	req.setPydm(pydm);
    	req.setJgid(this.getUser().getHospitalId());
    	req.setPageNum(page.getPageNum());
    	req.setPageSize(page.getPageSize());
    	return ReturnEntityUtil.success(opZt01Service.queryProjectStackAccounting(req));
    }
    
    @RequestMapping("/queryDrugAccounting")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "费用记账药品信息查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<DrugsTypkAccountingYpApiResp>> queryDrugAccounting(@ApiParam(name = "pydm", value = "拼音代码", required = true) 
    @RequestParam  String pydm, @ApiParam(name = "brxz", value = "病人性质", required = true) 
    @RequestParam  Integer brxz, PageQuery page) {
    	SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(this.getUser().getHospitalId(), SysXtcsCsmcCts.SFQYYPJZ);
		String sfqy = sysXtcs == null ? "0" : sysXtcs.getCsz();		
		PageInfo<DrugsTypkAccountingYpApiResp> pageInfo = new PageInfo<DrugsTypkAccountingYpApiResp>(new ArrayList<DrugsTypkAccountingYpApiResp>());
		if(!"0".equals(sfqy)) {
			QueryDrugAccountingApiReq req = new QueryDrugAccountingApiReq();
			req.setPydm(pydm);
			req.setJgid(this.getUser().getHospitalId());
			req.setPageNum(page.getPageNum());
			req.setPageSize(page.getPageSize());
	    	pageInfo = drugsTypkOutSer.queryDrugAccounting(req);
	    	for(DrugsTypkAccountingYpApiResp drugsTypkAccountingYpResp : pageInfo.getList()) {
	    		Integer fyxh = drugsTypkAccountingYpResp.getFyxh();// 费用序号
				Integer fygb = cisHzyzSer.getfygb(drugsTypkAccountingYpResp.getType(), fyxh);// 获取费用归并
				Map<String, Object> params = new HashMap<String, Object>(16);
				params.put("BRXZ", brxz);// 病人性质
				params.put("FYGB", fygb);// 费用归并
				params.put("TYPE", drugsTypkAccountingYpResp.getType());// 药品类型 0=项目
				params.put("FYXH", fyxh);// 费用序号
				//=========================获取医保病人的支付比例支付比例的方法=============================
				Map<String, Object> zfblMap = cisHzyzSer.getPayProportion(params);
				BigDecimal zfbl = (BigDecimal) zfblMap.get("ZFBL");
				drugsTypkAccountingYpResp.setZfbl(zfbl);
	    	}
		}
    	return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/queryCisyzHerbal")
    @ResponseBody
    @ApiOperationSupport(author = "龚方舟")
    @ApiOperation(value = "病区医嘱草药药品输入法查询", httpMethod = "POST")
    public ReturnEntity<PageInfo<DrugsTypkBqyzYpApiResp>> queryCisyzHerbal(@Valid DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq,
    		PageQuery page) {  	
    	return ReturnEntityUtil.success(doctorTypeWriteSer.queryCisyzHerbal(drugsTypkBqyzYpSrfReq, this.getUser(), page));
    }

    @RequestMapping("/queryAmqcRyKsInfo")
    @ResponseBody
    @ApiOperation(value="查询抗菌药物专家输入法" ,httpMethod="POST")
    public ReturnEntity<PageInfo<HrPersonnelKsApiResp>> queryAmqcRyKsInfo(
            @ApiParam(name = "pydm", value = "拼音代码") @RequestParam(required = false) String pydm,PageQuery page){
        return ReturnEntityUtil.success(hrPersonnelService.queryRyKsInfoByPageInfo(null, 
        		this.getUser().getHospitalId(), pydm, page));
    }
}

