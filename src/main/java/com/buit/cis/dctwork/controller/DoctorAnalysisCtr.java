package com.buit.cis.dctwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buit.commons.BaseSpringController;

import io.swagger.annotations.Api;

/**
 * 医生站统计分析
 * @ClassName: DoctorAnalysisCtr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年8月3日 下午4:14:07
 *
 */
@Api(tags="医生站统计分析")
@Controller
@RequestMapping("/doctorAnalysis")
public class DoctorAnalysisCtr extends BaseSpringController{

}
