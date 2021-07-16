
package com.buit.cis.nurse.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.nurse.model.NisHljbdsjb;
import com.buit.cis.nurse.request.NisHljbdsjbReq;
import com.buit.cis.nurse.response.NisHljbdsjbResp;
import com.buit.cis.nurse.service.NisHljbdsjbSer;
import com.buit.commons.BaseSpringController;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 护理交班单记录数据表<br>
 * @author GONGFANGZHOU
 */
@Api(tags="护理交班单记录数据表")
@Controller
@RequestMapping("/nishljbdsjb")
public class NisHljbdsjbCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdsjbCtr.class);
    
    @Autowired
    private NisHljbdsjbSer nisHljbdsjbSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @RequestMapping("/saveOrUpdateNisHljbdsjb")
    @ResponseBody
    @ApiOperation(value="新增或修改护理交班单记录数据表" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateNisHljbdsjb(NisHljbdsjbReq nishljbdsjb){
    	nishljbdsjb.setJgid(this.getUser().getHospitalId());
    	NisHljbdsjb sjb = BeanUtil.toBean(nishljbdsjb, NisHljbdsjb.class);
    	if(StrUtil.isBlankIfStr(sjb.getJlxh())) {
    		sjb.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJBDSJB));
    		nisHljbdsjbSer.insert(sjb);
    	}else {
    		nisHljbdsjbSer.update(sjb);
    	}
        return ReturnEntityUtil.success();
    }

    @RequestMapping("/queryNisHljbdData")
    @ResponseBody
    @ApiOperation(value="查询初始交班单数据" ,httpMethod="POST")
    public ReturnEntity<NisHljbdsjbResp> queryNisHljbdData(@ApiParam(name = "jbsj", value = "交班时间", required = true)
    @RequestParam Timestamp jbsj, @ApiParam(name = "bclx", value = "班次类型", required = true)
    @RequestParam Integer bclx, @ApiParam(name = "bqdm", value = "病区代码", required = true)
    @RequestParam Integer bqdm){
        return ReturnEntityUtil.success(nisHljbdsjbSer.queryNisHljbdData(jbsj, bclx, bqdm, this.getUser().getHospitalId()));
    }
    
}

