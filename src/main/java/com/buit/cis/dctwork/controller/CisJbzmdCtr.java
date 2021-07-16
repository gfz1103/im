
package com.buit.cis.dctwork.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buit.cis.dctwork.model.CisJbzmd;
import com.buit.cis.dctwork.request.CisJbzmdQueryReq;
import com.buit.cis.dctwork.request.CisJbzmdReq;
import com.buit.cis.dctwork.response.CisJbzmdResp;
import com.buit.cis.dctwork.service.CisJbzmdSer;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.constans.TableName;
import com.buit.file.IReportExportFileSer;
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
 * 疾病证明单<br>
 * @author GONGFANGZHOU
 */
@Api(tags="疾病证明单")
@Controller
@RequestMapping("/cisjbzmd")
public class CisJbzmdCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(CisJbzmdCtr.class);
    
    @Autowired
    private CisJbzmdSer cisJbzmdSer;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private IReportExportFileSer iReportExportFileSer;
    
    @RequestMapping("/queryPageCisJbzmd")
    @ResponseBody
    @ApiOperation(value="疾病证明单-分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CisJbzmdResp>> queryPage(CisJbzmdQueryReq cisjbzmd,PageQuery page){
    	cisjbzmd.setJgid(this.getUser().getHospitalId());
    	PageInfo<CisJbzmdResp> pageInfo = PageHelper.startPage(
            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
                    () -> cisJbzmdSer.getEntityMapper().queryPageCisJbzmd(cisjbzmd)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
 
    @RequestMapping("/saveOrUpdateCisJbzmd")
    @ResponseBody
    @ApiOperation(value="疾病证明单新增或修改" ,httpMethod="POST")
    public ReturnEntity saveOrUpdateCisJbzmd(CisJbzmdReq cisjbzmd){
    	cisjbzmd.setLrzdm(this.getUser().getUserId());
    	cisjbzmd.setJgid(this.getUser().getHospitalId());
    	if(StrUtil.isBlankIfStr(cisjbzmd.getJlxh())) {
    		cisjbzmd.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_JBZMD));
    		cisJbzmdSer.insert(BeanUtil.toBean(cisjbzmd, CisJbzmd.class));
    	}else {
    		cisJbzmdSer.update(BeanUtil.toBean(cisjbzmd, CisJbzmd.class));
    	}
        return ReturnEntityUtil.success();    
    }

    /** 删除 */
    @RequestMapping("/deleteByJlxh")
    @ResponseBody
    @ApiOperation(value="删除疾病证明单" ,httpMethod="POST")
    public ReturnEntity deleteByJlxh(@ApiParam(name = "jlxh", value = "记录序号", required = true)
    @RequestParam Integer jlxh) {
        cisJbzmdSer.removeById(jlxh);
        return ReturnEntityUtil.success();            
    }
    
    @GetMapping("/printCisbqzmd")
    @ApiOperation(value="疾病证明单打印")
    public void printCisbqzmd(@RequestParam("jlxh") Integer jlxh, HttpServletResponse response) {
        Map<String, Object> map = cisJbzmdSer.printCisbqzmd(jlxh, this.getUser().getHospitalName());
        String jasperName = "jrxml/DiseaseCertificate.jasper";
        if(map != null) {
        	iReportExportFileSer.reportHtmlForStream(map, jasperName, response);
        }
    }
    
}

