
package com.buit.cis.dctwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.model.CisHzyjmb;
import com.buit.cis.dctwork.request.CisHzyjmbReq;
import com.buit.cis.dctwork.service.CisHzyjmbSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 会诊意见模板维护表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="会诊意见模板维护表")
@Controller
@RequestMapping("/cishzyjmb")
public class CisHzyjmbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyjmbCtr.class);
    
    @Autowired
    private CisHzyjmbSer cisHzyjmbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="查询会诊意见模板-分页" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisHzyjmb>> queryPage(CisHzyjmbReq cishzyjmb,PageQuery page){
    	cishzyjmb.setJgid(this.getUser().getHospitalId());
    	PageInfo<CisHzyjmb> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> cisHzyjmbSer.findByEntity(cishzyjmb)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
 
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="查询会诊意见模板信息" ,httpMethod="POST")
    public ReturnEntity<CisHzyjmb> getById(@ApiParam(name = "jlxh", value = "jlxh", required = true)
	@RequestParam Integer jlxh){
        return ReturnEntityUtil.success(cisHzyjmbSer.getById(jlxh));
    }

    /** 新增 */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="会诊意见模板新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(CisHzyjmbReq cisHzyjmbReq) {
    	CisHzyjmb cisHzyjmb = BeanUtil.toBean(cisHzyjmbReq, CisHzyjmb.class);
    	cisHzyjmb.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(cisHzyjmb.getJlxh())) {
    		cisHzyjmb.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYJMB));
    		cisHzyjmbSer.insert(cisHzyjmb);    
    	}else {
    		cisHzyjmbSer.update(cisHzyjmb);    
    	}     
        return ReturnEntityUtil.success();            
    }

    /** 删除 */
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="删除会诊意见模板" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
        cisHzyjmbSer.removeById(jlxh);
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="会诊意见模板作废或取消作废" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "jlxh", value = "jlxh", required = true)
	@RequestParam Integer jlxh,@ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb)  {
    	cisHzyjmbSer.getEntityMapper().invalid(zfpb, jlxh);   
        return ReturnEntityUtil.success();            
    }
    
}

