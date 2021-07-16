
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisHlmbwhb;
import com.buit.cis.nurse.request.NisHlmbwhbReq;
import com.buit.cis.nurse.service.NisHlmbwhbSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理单模板维护表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理单模板维护表")
@Controller
@RequestMapping("/nishlmbwhb")
public class NisHlmbwhbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHlmbwhbCtr.class);
    
    @Autowired
    private NisHlmbwhbSer nisHlmbwhbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperation(value="护理模板维护-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisHlmbwhb>> queryPage(NisHlmbwhbReq nishlmbwhb,PageQuery page){
    	nishlmbwhb.setJgid(this.getUser().getHospitalId());
        PageInfo<NisHlmbwhb> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisHlmbwhbSer.findByEntity(nishlmbwhb)
            );
        return ReturnEntityUtil.success(pageInfo);
    }

    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="查询护理模板维护信息" ,httpMethod="POST")
    public ReturnEntity<NisHlmbwhb> getById(@ApiParam(name = "id", value = "id", required = true)
	@RequestParam Integer id){
        return ReturnEntityUtil.success(nisHlmbwhbSer.getById(id));
    }
    
    /** 新增 */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    @ApiOperation(value="新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdate(NisHlmbwhbReq nisHlmbwhbReq) {
    	nisHlmbwhbReq.setJgid(this.getUser().getHospitalId());
    	nisHlmbwhbReq.setCzgh(this.getUser().getUserId());
    	nisHlmbwhbReq.setCzsj(DateUtil.date().toTimestamp());
        if(StrUtil.isBlankIfStr(nisHlmbwhbReq.getId())) {
        	if(StrUtil.isBlankIfStr(nisHlmbwhbReq.getKsdm())) {
        		nisHlmbwhbReq.setUserid(this.getUser().getUserId());
        	}
        	nisHlmbwhbReq.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLMBWHB));
        	nisHlmbwhbSer.insert(nisHlmbwhbReq);
        }else {
        	nisHlmbwhbSer.update(nisHlmbwhbReq);
        }
        return ReturnEntityUtil.success();            
    }

    /** 删除 */
    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="删除护理模板维护" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "id", value = "id", required = true)
	@RequestParam Integer id) {
        nisHlmbwhbSer.removeById(id);;        
        return ReturnEntityUtil.success();            
    }
    
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="专科护理自定义项目内容作废或取消作废" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "id", value = "id", required = true)
	@RequestParam Integer id,@ApiParam(name = "zfpb", value = "作废判别(作废:1,取消:0)", required = true)
	@RequestParam Integer zfpb)  {
    	nisHlmbwhbSer.getEntityMapper().invalid(id, zfpb);   
        return ReturnEntityUtil.success();            
    }
    
}

