
package com.buit.cis.nurse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisJbdmb;
import com.buit.cis.nurse.request.NisJbdmbReq;
import com.buit.cis.nurse.service.NisJbdmbSer;
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
 * 护理交班单模板<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理交班单模板")
@Controller
@RequestMapping("/nisjbdmb")
public class NisJbdmbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisJbdmbCtr.class);
    
    @Autowired
    private NisJbdmbSer nisJbdmbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/queryPageJbdmb")
    @ResponseBody
    @ApiOperation(value="交班单模板-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<NisJbdmb>> queryPage(NisJbdmbReq nisjbdmb,PageQuery page){
    	nisjbdmb.setJgid(this.getUser().getHospitalId());
        PageInfo<NisJbdmb> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> nisJbdmbSer.findByEntity(nisjbdmb)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
 
    @RequestMapping("/saveOrUpdateJbdmb")
    @ResponseBody
    @ApiOperation(value="交班单模板新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateJbdmb(NisJbdmbReq nisjbdmb){
    	nisjbdmb.setJgid(this.getUser().getHospitalId());
    	NisJbdmb jbdmb = BeanUtil.toBean(nisjbdmb, NisJbdmb.class);
    	if(StrUtil.isBlankIfStr(jbdmb.getId())) {
    		jbdmb.setId(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_JBDMB));
    		nisJbdmbSer.insert(jbdmb);
    	}else {
    		nisJbdmbSer.update(jbdmb);
    	}
        return ReturnEntityUtil.success();    
    }
   
    @RequestMapping("/getById")
    @ResponseBody
    @ApiOperation(value="根据id查询交班单模板" ,httpMethod="POST")
    public ReturnEntity<NisJbdmb> getById(@ApiParam(name = "id", value = "主键id", required = true)
    @RequestParam Integer id){
        return ReturnEntityUtil.success(nisJbdmbSer.getById(id));
    }
  
    /** 编辑 */
    @RequestMapping("/invalid")
    @ResponseBody
    @ApiOperation(value="作废或取消作废交班单模板" ,httpMethod="POST")
    public ReturnEntity invalid(@ApiParam(name = "id", value = "主键id", required = true)
    @RequestParam Integer id, @ApiParam(name = "zfpb", value = "1:作废0:取消作废", required = true)
    @RequestParam Integer zfpb)  {
        nisJbdmbSer.getEntityMapper().invalid(id, zfpb);     
        return ReturnEntityUtil.success();            
    }
 
    /** 删除 */
    @RequestMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value="根据id删除交班单模板" ,httpMethod="POST")
    public ReturnEntity deleteById(@ApiParam(name = "id", value = "主键id", required = true)
    @RequestParam Integer id) {
        nisJbdmbSer.removeById(id);
        return ReturnEntityUtil.success();            
    }
    
}

