
package com.buit.cis.ims.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.ims.model.ImCwzh;
import com.buit.cis.ims.request.ImCwzhReq;
import com.buit.cis.ims.service.ImCwzhSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 床位组号 <br> 
 * @author GONGFANGZHOU
 */
@Api(tags="床位组号")
@Controller
@RequestMapping("/imcwzh")
public class ImCwzhCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ImCwzhCtr.class);
    
    @Autowired
    private ImCwzhSer imCwzhSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="床位组号-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ImCwzh>> queryPage(ImCwzhReq imcwzh,PageQuery page){
    	imcwzh.setJgid(this.getUser().getHospitalId());
        PageInfo<ImCwzh> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> imCwzhSer.findByEntity(imcwzh)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/saveOrUpdateCwzh")
    @ResponseBody
    @ApiOperation(value="床位组号新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateCwzh(ImCwzhReq imcwzh){
    	imcwzh.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(imcwzh.getCwzh())) {
    		imcwzh.setCwzh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWZH));
    		imCwzhSer.insert(imcwzh);
    	}else {
    		imCwzhSer.updateCwzh(imcwzh);
    	}
        return ReturnEntityUtil.success();    
    }
    
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="根据床位组号查询床位组信息" ,httpMethod="POST")
    public ReturnEntity<ImCwzh> getById(@ApiParam(name = "cwzh", value = "床位组号", required = true)
	@RequestParam Integer cwzh){
        return ReturnEntityUtil.success(imCwzhSer.getById(cwzh));
    }
   
    @RequestMapping("/invalidByCwzh")
    @ResponseBody
    @ApiOperation(value="作废或取消作废床位组号" ,httpMethod="POST")
    public ReturnEntity invalidByCwzh(@ApiParam(name = "cwzh", value = "床位组号", required = true)
	@RequestParam Integer cwzh, @ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb) {
        imCwzhSer.invalidByCwzh(cwzh, zfpb, this.getUser().getHospitalId());        
        return ReturnEntityUtil.success();            
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="删除床位组号" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "cwzh", value = "床位组号", required = true)
	@RequestParam Integer cwzh)  {
        imCwzhSer.deleteByCwzh(cwzh, this.getUser().getHospitalId());
        return ReturnEntityUtil.success();            
    }

    @RequestMapping("/queryCwzhDic")
    @ResponseBody
    @ApiOperation(value="床位组号字典查询" ,httpMethod="POST")
    public ReturnEntity<List<ImCwzh>> queryCwzhDic(@ApiParam(name = "bqdm", value = "病区代码", required = true)
	@RequestParam Integer bqdm){
    	ImCwzh imCwzh = new ImCwzh();
    	imCwzh.setBqdm(bqdm);
    	imCwzh.setJgid(this.getUser().getHospitalId());
    	imCwzh.setZfpb(0);
        return ReturnEntityUtil.success(imCwzhSer.findByEntity(imCwzh));
    }
    
}

