package com.buit.cis.dctwork.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.aop.lock.Locked;
import com.buit.cis.dctwork.dao.AmqcKjywsqdDao;
import com.buit.cis.dctwork.dao.AmqcKjywsyhzksysDao;
import com.buit.cis.dctwork.dao.AmqcKjywsysqDao;
import com.buit.cis.dctwork.dao.AmqcKjywsysqypDao;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.dao.CisYzmessDao;
import com.buit.cis.dctwork.dao.CisZyHzsqDao;
import com.buit.cis.dctwork.dao.CisZyHzyqdxDao;
import com.buit.cis.dctwork.dao.NisFyyfDao;
import com.buit.cis.dctwork.dao.SkinPsjlDao;
import com.buit.cis.dctwork.dto.CisHzyzMessDto;
import com.buit.cis.dctwork.dto.CisZyHzsqMessDto;
import com.buit.cis.dctwork.enums.CareLevelEnum;
import com.buit.cis.dctwork.enums.DischargeDiscriminationTypeEnum;
import com.buit.cis.dctwork.enums.OpenTypeEnum;
import com.buit.cis.dctwork.enums.OperationTypeEnum;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.model.CisYzmess;
import com.buit.cis.dctwork.model.CisZyHzsq;
import com.buit.cis.dctwork.model.CisZyHzyqdx;
import com.buit.cis.dctwork.model.SkinPsjl;
import com.buit.cis.dctwork.request.CisHzyzCancelReq;
import com.buit.cis.dctwork.request.CisHzyzCancelReq.CisHzyzCancelBody;
import com.buit.cis.dctwork.request.CisHzyzDataReq;
import com.buit.cis.dctwork.request.CisHzyzHerbalReq;
import com.buit.cis.dctwork.request.CisHzyzHerbalReq.CisHzyzHerbalBody;
import com.buit.cis.dctwork.request.CisHzyzInvalidReq;
import com.buit.cis.dctwork.request.CisHzyzInvalidReq.CisHzyzInvalidBody;
import com.buit.cis.dctwork.request.CisHzyzQueryApiReq;
import com.buit.cis.dctwork.request.CisHzyzSaveReq;
import com.buit.cis.dctwork.request.CisHzyzSubmitReq;
import com.buit.cis.dctwork.request.CisHzyzXhListReq;
import com.buit.cis.dctwork.request.SkinPsjlCheckReq;
import com.buit.cis.dctwork.request.SkinPsjlReq;
import com.buit.cis.dctwork.response.CisHzyzDocSubmitResp;
import com.buit.cis.dctwork.response.CisHzyzDocSubmitResp.DocSubmitBody;
import com.buit.cis.dctwork.response.CisHzyzKcdjResp;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.dctwork.response.CisHzyzResp;
import com.buit.cis.dctwork.response.NisFyyfQxResp;
import com.buit.cis.dctwork.response.NisFyyfResp;
import com.buit.cis.dctwork.response.SkinPsjlCheckResp;
import com.buit.cis.dctwork.response.SkinPsjlQueryResp;
import com.buit.cis.dctwork.response.SkinPsjlResp;
import com.buit.cis.dctwork.response.YsZyHzBgResp;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImCyjlDao;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImFeeFymxYjDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImRcjlDao;
import com.buit.cis.ims.dao.ImRyzdDao;
import com.buit.cis.ims.dao.ImTbkkDao;
import com.buit.cis.ims.dao.ImZyjsDao;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImCyjl;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImRcjl;
import com.buit.cis.ims.model.ImRyzd;
import com.buit.cis.ims.request.ImFeeFymxDbReq;
import com.buit.cis.ims.request.ImHzryDctworkReq;
import com.buit.cis.ims.request.ImHzryInfoReq;
import com.buit.cis.ims.request.ImHzryQueryReq;
import com.buit.cis.ims.request.ImHzryReq;
import com.buit.cis.ims.request.ImRcjlCyReq;
import com.buit.cis.ims.request.ImRyzdReq;
import com.buit.cis.ims.response.ImFeeFymxXtResp;
import com.buit.cis.ims.response.ImHzryDctworkResp;
import com.buit.cis.ims.response.ImHzryLeaveHospitalResp;
import com.buit.cis.ims.response.ImRyzdResp;
import com.buit.cis.ims.service.ZyDicSer;
import com.buit.common.drug.dto.PubPharUnfreezeDto;
import com.buit.common.drug.model.PubPharKcdjNew;
import com.buit.common.drug.service.impl.PubDrugKcdjSerImpl;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.drug.service.DrugService;
import com.buit.dto.LineContentDto;
import com.buit.dto.TargetConfig;
import com.buit.dto.WaitProcessMessage;
import com.buit.enums.CoverageTypeEnum;
import com.buit.enums.MessageLevelType;
import com.buit.enums.SystemTypeEnum;
import com.buit.his.treatment.model.ZlRwfpUpdateReq;
import com.buit.his.treatment.service.ZlRwfpService;
import com.buit.mms.antimi.model.AmqcKjywsycs;
import com.buit.mms.antimi.service.AmqcKjywsycsService;
import com.buit.mms.antimi.service.AmqcKjywsysqService;
import com.buit.mq.RabbitMqProducer;
import com.buit.mrms.response.BasySfgdResp;
import com.buit.mrms.service.BasyService;
import com.buit.system.model.SysYwqxkz;
import com.buit.system.response.DicJbbmInfoResp;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicJbbmService;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.SysMessSer;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.service.SysYwqxkzService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 
 * @ClassName: ImHzryByDctworkSer
 * @Description: 住院医生站病人管理
 * @author 龚方舟
 * @date 2020年5月25日 下午2:31:53
 *
 */
@Service
public class ImHzryByDctworkSer extends BaseManagerImp<ImHzry, Integer>{

	static final Logger logger = LoggerFactory.getLogger(ImHzryByDctworkSer.class);

    private static Pattern p = Pattern.compile("[0-9]*");

	@Autowired
	private ImHzryDao imHzryDao;

	@Override
	public ImHzryDao getEntityMapper() {
		return imHzryDao;
	}

	@DubboReference
	private HrPersonnelService hrPersonnelService;

	@Autowired
	private ImRyzdDao imRyzdDao;

	@Autowired
	private SkinPsjlDao skinPsjlDao;

	@Autowired
	private CisHzyzDao cisHzyzDao;

	@DubboReference
	private DicJbbmService dicJbbmService;

	@Autowired
	private RedisFactory redisFactory;
	
	@DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;
	
	@Autowired
	private CisHzyzZtDao cisHzyzZtDao;
	
	@Autowired
	private AmqcKjywsqdDao amqcKjywsqdDao;
	
	@Autowired
	private NisFyyfDao nisFyyfDao;
	
	@Autowired
	private ImFeeFymxDao imFeeFymxDao;
	
	@Autowired
	private ImFeeFymxYjDao imFeeFymxYjDao;
	
	@Autowired
	private ImRcjlDao imRcjlDao;
	
	@Autowired
	private ImCwszDao imCwszDao;
    
    @Autowired
    private CisZyHzsqDao cisZyHzsqDao;
    
    @Autowired
    private CisZyHzyqdxDao cisZyHzyqdxDao;
    
    @DubboReference
    private AmqcKjywsycsService amqcKjywsycsService;
    
    @Autowired
    private CisHzyzSer cisHzyzSer;
    
    @Autowired
    private ImTbkkDao imTbkkDao;
    
    @Autowired
    private ImZyjsDao imZyjsDao;
    
    @Autowired
    private ImZkjlSer imZkjlSer;  
    
    @Autowired
    private ImCyjlDao imCyjlDao;
    
    @DubboReference
    private SysMessSer sysMessSer;
    
    @DubboReference
    private AmqcKjywsysqService amqcKjywsysqService;
    
    @Autowired
    private ZyDicSer zyDicSer;
    
    @DubboReference
    private SysYwqxkzService sysYwqxkzService;
    
    @DubboReference
    private BasyService basyService;
    
    @Autowired
    private RabbitMqProducer rabbit;
    
    @Autowired
    private CisYzmessDao cisYzmessDao;
    
    @DubboReference
    private ZlRwfpService zlRwfpService;
    
    @Autowired
    private PubDrugKcdjSerImpl pubDrugKcdjSerImpl;
    
    @DubboReference
    private DrugService drugService;

    @Autowired
	private AmqcKjywsysqDao amqcKjywsysqDao;

	@Autowired
	private AmqcKjywsysqypDao amqcKjywsysqypDao;

	@Autowired
	private AmqcKjywsyhzksysDao amqcKjywsyhzksysDao;

	/**
	 * @Title: queryBedPatientInfo 
	 * @Description: TODO 医生工作站病人管理查询不同的病人列表信息
	 * @param imHzryDctworkReq
	 * @return List<ImHzryDctworkResp>
	 * @author 龚方舟
	 * @throws
	 */
	public PageInfo<ImHzryDctworkResp> queryBedPatientInfo(ImHzryDctworkReq imHzryDctworkReq, SysUser user, PageQuery page) {
		Integer ksdm = imHzryDctworkReq.getKsdm();
		Integer userId = user.getUserId();
		Integer manageUnit = user.getHospitalId();
		imHzryDctworkReq.setJgid(manageUnit);
		String openBy = imHzryDctworkReq.getOpenBy();
		Object brqk = imHzryDctworkReq.getBrqk();
		Object hljb = imHzryDctworkReq.getHljb();
		Integer zyzt = imHzryDctworkReq.getZyzt();
		Integer brbq = imHzryDctworkReq.getBrbq();
		if (ksdm != null) {
			if (Long.parseLong(ksdm.toString()) == -1) {
				// 我的病人
				imHzryDctworkReq.setZyys(userId);
			} else if (Long.parseLong(ksdm.toString()) == 2) {
				// 会诊病人标志
				HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(userId);	
				imHzryDctworkReq.setNyqhzks(hrPersonnel.getOfficecode());
				imHzryDctworkReq.setZyys(userId);
			}
		}

		if (!"".equals(openBy) && OpenTypeEnum.doctor.getCode().equals(openBy)) {

		} else {
//			imHzryDctworkReq.setKsdm(brbq);
		}

		PageInfo<ImHzryDctworkResp> pageInfo = PageHelper.startPage(
	            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
	                    () -> imHzryDao.queryBedPatientInfo(imHzryDctworkReq)
        );

		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.BQCHXSWS);

		String brchxsws = sysXtcs == null ? "0" : sysXtcs.getCsz();

		if (brchxsws == null || brchxsws.trim().length() == 0 || 
				!p.matcher(brchxsws).matches()) {
			brchxsws = "0";
		}

		for (ImHzryDctworkResp imHzryDctworkResp : pageInfo.getList()) {
			Map<String, Object> res = new HashMap<String, Object>(16);		
			String brch = imHzryDctworkResp.getBrch();
			if(imHzryDctworkResp.getRyrq() != null) {
				String ryrq = DateUtil.format(imHzryDctworkResp.getRyrq(), DatePattern.NORM_DATETIME_PATTERN);
				BigDecimal zyts = BigDecimal.valueOf(BUHISUtil.loadDischargeDays(ryrq, 
						DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN)));
				imHzryDctworkResp.setZyts(zyts);
			}
			if (Integer.parseInt(brchxsws) > 0 && Integer.parseInt(brchxsws) < brch.length()) {
				imHzryDctworkResp.setBrchShow(brch.substring(brch.length() - Integer.parseInt(brchxsws)));
			} else {
				imHzryDctworkResp.setBrchShow(brch);
			}
			if (imHzryDctworkResp.getZyh() != null && imHzryDctworkResp.getZyh().toString().trim().length() > 0) {
                res = BUHISUtil.getPersonAge(imHzryDctworkResp.getCsny(), null);
                imHzryDctworkResp.setAge(String.valueOf(res.get("age")));
            } else {
				continue;
			}
		}
		return pageInfo;
	}

	/**
	 * @Title: getDiagnosisByZyh 
	 * @Description: TODO 根据住院号查询病人入院诊断
	 * @param imRyzdReq @param 
	 * @return 设定文件 
	 * @return List<ImRyzdResp> 返回类型
	 * @author 龚方舟 
	 * @throws
	 */
	public List<ImRyzdResp> getDiagnosisByEntity(ImRyzdReq imRyzdReq) {
		List<ImRyzdResp> imRyzdRespList = imRyzdDao.getDiagnosisByEntity(imRyzdReq);
		return imRyzdRespList;
	}

	/**
	 * @Title: getAllergicDrugsByEntity 
	 * @Description: TODO 根据brid,jgid查询病人过敏药物 
	 * @param brid 
	 * @param user
	 * @return 设定文件
	 * @return List<SkinPsjlResp> 返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<SkinPsjlResp> getAllergicDrugsByEntity(Integer brid, SysUser user) {
		SkinPsjlReq skinPsjlReq = new SkinPsjlReq();
		skinPsjlReq.setBrid(brid);
		skinPsjlReq.setJgid(user.getHospitalId());
		List<SkinPsjlResp> skinPsjlRespList = skinPsjlDao.getAllergicDrugsByEntity(skinPsjlReq);
		return skinPsjlRespList;
	}

	/**
	 * @Title: deleteByCompositeKeys 
	 * @Description: TODO 根据联合主键删除入院诊断
	 * @param zyh 
	 * @param zdxh 
	 * @param zdlb 设定文件 
	 * @return void 返回类型
	 * @author 龚方舟 
	 * @throws
	 */
	public void deleteByCompositeKeys(Integer zyh, Integer zdxh, Integer zdlb) {
		imRyzdDao.deleteByCompositeKeys(zyh, zdxh, zdlb);
	}

	/**
	 * @Title: deleteByCompositeKeys 
	 * @Description: TODO 根据主键删除过敏药物记录
	 * @param jlxh 
	 * @return void 返回类型
	 * @author 龚方舟 
	 * @throws
	 */
	public void deleteAllergicDrugsById(Integer jlxh) {
		skinPsjlDao.deleteById(jlxh);
	}

	/**
	 * 
	 * @Title: savePatientInfo 
	 * @Description: TODO 病人管理病人信息保存(包含诊断,过敏药物更新删除) 
	 * @param imHzryInfoReq 
	 * @param user  
	 * @return 设定文件 
	 * @return DicJbbmInfoResp 返回类型 
	 * @author 龚方舟 @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public DicJbbmInfoResp savePatientInfo(ImHzryInfoReq imHzryInfoReq, SysUser user) {
		String openBy = imHzryInfoReq.getOpenBy();
		// 可能医生站和护士站同都用到需要区分
//    	if(OpenTypeEnum.doctor.getCode().equals(openBy)) {
//    		
//    	}
		DicJbbmInfoResp dicJbbmInfoResp = new DicJbbmInfoResp();
		Integer manageUnit = user.getHospitalId();
		Integer zyh = imHzryInfoReq.getZyh();
		// 基础信息 判断是否已经有处方信息，若已经存在，不允许删除ZYYS
		if (StringUtils.isBlank(imHzryInfoReq.getZyys())) {
			CisHzyz cisHzyz = new CisHzyz();
			cisHzyz.setZyh(imHzryInfoReq.getZyh());
			cisHzyz.setJgid(manageUnit);
			long count = cisHzyzDao.findByEntityCount(cisHzyz);
			if (count > 0) {
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_0001");
			}
		}
		
		// 诊断信息
		String opStatus = OperationTypeEnum.update.getCode();
		String mqzd = "";
		String ryzd = "";
		String ryzdIcd10 = "";
		String icd10Str = "";
		List<ImRyzdReq> imRyzdReqList = imHzryInfoReq.getImRyzdReqList();
		ImHzry imhzry = imHzryDao.getById(zyh);
		if (imRyzdReqList != null && imRyzdReqList.size() > 0) {
			StringBuffer zdxhStr = new StringBuffer();
			for (ImRyzdReq imRyzdReq : imRyzdReqList) {
				icd10Str += imRyzdReq.getIcd10() + ",";
				opStatus = imRyzdReq.getOpStatus();
				imRyzdReq.setZyh(zyh);
				imRyzdReq.setJgid(manageUnit);
				Integer zdxh = imRyzdReq.getZdxh();
				Integer zdlb = imRyzdReq.getZdlb();
				if (OperationTypeEnum.remove.getCode().equals(opStatus)) {
					imRyzdDao.deleteByCompositeKeys(zyh, zdxh, zdlb);
				} else {
					if (zdlb == 2) {
						ryzd += ryzd = imRyzdReq.getJbmc() + ",";
						// 修改入院诊断，修改插入ICD10编码
						if (StringUtils.isNotBlank(imRyzdReq.getIcd10())) {
							ryzdIcd10 = imRyzdReq.getIcd10();
						}

					}
					mqzd += imRyzdReq.getJbmc() + ",";
					if (OperationTypeEnum.update.getCode().equals(opStatus)) {
						imRyzdDao.updateDiagnosis(imRyzdReq);
					} else {
						imRyzdReq.setZdrq(DateUtil.date().toTimestamp());
						imRyzdReq.setPxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_RYZD_PXH));//新增时生成排序号
						imRyzdDao.insert(imRyzdReq);
					}
					zdxhStr.append(zdxh).append(",");
				}
			}
			if(!"".equals(mqzd)) {
				mqzd = mqzd.substring(0, mqzd.length() - 1);
			}
			if(!"".equals(icd10Str)) {
				icd10Str = icd10Str.substring(0, icd10Str.length() - 1);
			}
			if (zdxhStr.length() == 0) {
				imHzryInfoReq.setRyzd(ryzd);
				imHzryInfoReq.setMqzd(mqzd);
			} else {
				List<Map<String, Object>> jbbmList = dicJbbmService
						.getJbbmInfo(zdxhStr.substring(0, zdxhStr.lastIndexOf(",")));
				if (jbbmList != null) {
					StringBuffer jbpbStr = new StringBuffer();
					StringBuffer jbbgkStr = new StringBuffer();
					for (int i = 0; i < jbbmList.size(); i++) {
						Map<String, Object> map = jbbmList.get(i);
						if(map != null) {
							if (map.get("JBPB") != null && (map.get("JBPB") + "").length() > 0) {
								jbpbStr.append(map.get("JBPB"));
							}
							if (map.get("JBBGK") != null && (map.get("JBBGK") + "").length() > 0) {
								jbbgkStr.append(map.get("JBBGK"));
							}
						}
					}
					if (jbpbStr.length() > 0) {
						dicJbbmInfoResp.setJbpbStr(jbpbStr.toString());
					}

					if (jbbgkStr.length() > 0) {
						dicJbbmInfoResp.setJbbgkStr(jbbgkStr.toString());
					}

				}
				if (!"".equals(ryzdIcd10) && !"null".equals(ryzdIcd10)) {
					imHzryInfoReq.setRyzd(ryzdIcd10);

				}
				imHzryInfoReq.setMqzd(mqzd);
			}
		}
		BeanUtil.copyProperties(imHzryInfoReq, imhzry, 
				CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		imhzry.setIcd10bm(icd10Str);
		imHzryDao.update(imhzry);

		// 过敏药物
		List<SkinPsjlReq> skinPsjlReqList = imHzryInfoReq.getSkinPsjlReqList();
		if (skinPsjlReqList != null && skinPsjlReqList.size() > 0) {
			for (SkinPsjlReq skinPsjlReq : skinPsjlReqList) {
				opStatus = skinPsjlReq.getOpStatus();
				skinPsjlReq.setJgid(manageUnit);
				if (OperationTypeEnum.remove.getCode().equals(opStatus)) {
					skinPsjlDao.deleteByEntity(skinPsjlReq);
				} else {
					if (OperationTypeEnum.update.getCode().equals(opStatus)) {
						skinPsjlDao.updateAllergicDrugs(skinPsjlReq);
					} else {
						skinPsjlReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.SKIN_PSJL));
						skinPsjlDao.insert(skinPsjlReq);
					}
				}
			}
		}
		return dicJbbmInfoResp;
	}

	/**
	 * 病区病人管理医嘱保存
	 * @Title: saveWardPatientInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzSaveReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	@Locked(value = "im_save_hzyz_#[cisHzyzSaveReq.zyh]",block = 1)
	public void saveWardPatientInfo(CisHzyzSaveReq cisHzyzSaveReq, SysUser user) {
		boolean remaidSign = false;
		Integer manageUnit = user.getHospitalId();
		ImHzry imHzry = new ImHzry();
		try {
			imHzry = imHzryDao.getById(cisHzyzSaveReq.getZyh());
			if(DischargeDiscriminationTypeEnum.ONE.getCode() == imHzry.getCypb()) {
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_0002");
			}else if(imHzry.getCypb() >= DischargeDiscriminationTypeEnum.EIGHT.getCode()) {
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_0003");
			}
		} catch (NullPointerException e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_0004");
        }
        SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.WPJFBZ);

        Integer wpjfbz = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
        sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.WZSFXMJGZY);
        Integer wzsfxmjgzy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());
        Map<String, Object> prarms = new HashMap<String, Object>(16);
        List<Map<String, Object>> yzxxList = BUHISUtil.ListObjToMap(cisHzyzSaveReq.getCisHzyzReqList());

        //附加项目
        List<Map<String, Object>> fjxxList = BUHISUtil.ListObjToMap(cisHzyzSaveReq.getCisHzyzReqFjList());
		
		Integer lastYzzh = -1;
		Date lastDate = null;
		Long yzzh = 0L;

		// 删除附加项目
		int yzpx = 0;
		for (Map<String, Object> fjxx : fjxxList) {
			if (OperationTypeEnum.remove.getCode().equals(fjxx.get("opStatus").toString())) {
				cisHzyzDao.deleteById(Integer.valueOf(fjxx.get("jlxh").toString()));
			}
		}
		
		// *************************begin******************************
		// 数据保存前校验数据有效性,防止在医嘱被提交后由于页面缓存导致数据状态更新错误的问题(!!!)
		// 思路: 更新前获取数据库数据,并校验关键状态是否已经变更,若变更,不允许保存并给出提示信息
		// 重要标志位  FHBZ(复核标志),LSBZ(历史医嘱),SYBZ(使用标志)  expired
		List<Long> jlxh = new ArrayList<Long>();
		for (int i = 0 ; i < yzxxList.size(); i ++) {
			Map<String, Object> r = yzxxList.get(i);
			if(r.containsKey("jlxh") && r.get("jlxh") != null // 有效医嘱
					&& r.get("jlxh").toString().trim().length() > 0) {
				jlxh.add(Long.parseLong(r.get("jlxh").toString()));
			}
			
		}

		if (jlxh.size() > 0) {
			StringBuffer expiredMsg = new StringBuffer();
			List<Map<String, Object>> dbRecord = cisHzyzDao.queryBqyzInfo(jlxh);		
			ReturnEntity<String> returnEntity=new ReturnEntity<>();
		
			for (Map<String, Object> dr : dbRecord) {
				for (Map<String, Object> r : yzxxList) {
					if (r.get("jlxh") != null && dr.get("JLXH").toString()
							.equals(r.get("jlxh").toString())) { // 转字符串匹配
						if (!isEq(dr.get("FHBZ"), r.get("fhbz"))
								|| !isEq(dr.get("SYBZ"), r.get("sybz"))
								|| !isEq(dr.get("LSBZ"), r.get("lsbz"))) {	
							// 标记过期数据
							r.put("opStatus", OperationTypeEnum.expired.getCode()); 		
							returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00034", new String[] {
									ObjectToTypes.parseString(r.get("yzmc"))});						
							expiredMsg.append(returnEntity.getMessage());
						}
						continue;
					}
				}
			}
			if(expiredMsg.length() > 0) {
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00033", new String[] {expiredMsg.toString()});
			}
		}
		//************************end***************************

		for (int i = 0; i < yzxxList.size(); i++) {
			Map<String, Object> r = yzxxList.get(i);
			String op = String.valueOf(r.get("opStatus"));
			//过期数据不保存
			if(OperationTypeEnum.expired.getCode().equals(op)) {
				continue;
			}
			// 默认值设置

			if (ObjectToTypes.parseInt(r.get("yzzhShow")) == lastYzzh) {	
				r.put("kssj", DateUtil.date(lastDate).toTimestamp());
			} else {
//				boolean yzzhNotExist = OperationTypeEnum.create.getCode().equals(op) 
//						&& (r.get("yzzh") == null
//						|| r.get("yzzh").toString().length() == 0 || "0".equals(r
//								.get("yzzh").toString()));
                if (OperationTypeEnum.create.getCode().equals(op) && ObjectToTypes.parseInt(r.get("yzzhShow")) != lastYzzh) {
                    yzzh = ObjectToTypes.parseLong(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));

                    yzpx = 0;
                    // 查询医嘱表YZZH最大值
                }
                lastYzzh = ObjectToTypes.parseInt(r.get("yzzhShow"));
                lastDate = BUHISUtil.toDate(r.get("kssj").toString());
                r.put("kssj", DateUtil.date(lastDate).toTimestamp());
            }
			// 新增的记录且没有YZZH
//			boolean yzzhNotExist = OperationTypeEnum.create.getCode().equals(op) && 
//					(r.get("yzzh") == null || r.get("yzzh").toString().length() == 0 || 
//					"0".equals(r.get("yzzh").toString()));
			if (OperationTypeEnum.create.getCode().equals(op) && yzzh != 0) {
				r.put("yzzh", yzzh);
			}
			if (r.get("tzsj") != null && r.get("tzsj").toString().length() > 0) {
                r.put("tzsj", r.get("tzsj").toString());
                if (BUHISUtil.dateCompare((DateUtil.parse(r.get("tzsj").toString()).toJdkDate()),
                        DateUtil.parse(r.get("kssj").toString()).toJdkDate()) < 0) {
                    throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00019");
                }
            }
			if (("1").equals(r.get("lsyz") + "")) {
				r.put("tzsj", r.get("kssj"));
				r.put("tzys", r.get("ysgh") );
			}
			// add by YangL 判断是否有医生站医嘱变动
			if (r.get("needRemaid") != null && "1".equals(r.get("needRemaid").toString())) {
				remaidSign = true;
			}
			r.put("brks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
					: cisHzyzSaveReq.getBrks().toString()));
			r.put("brbq", Long.parseLong(cisHzyzSaveReq.getBrbq() == null ? "0"
					: cisHzyzSaveReq.getBrbq() .toString()));
			r.put("brch", cisHzyzSaveReq.getBrch());
			r.put("srks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
					: cisHzyzSaveReq.getBrks().toString()));
			r.put("ysgh", StrUtil.isBlankIfStr(r.get("ysgh")) ? user.getUserId() : r.get("ysgh"));
			r.put("czgh", StrUtil.isBlankIfStr(r.get("czgh")) ? user.getUserId() : r.get("czgh"));
			Integer yplx = ObjectToTypes.parseInt(r.get("YPLX"));
			if (yplx >= 1) {
				// 药品
				r.put("jfbz", 1);
				boolean zfypFlag = "1".equals(r.get("zfyp") + "")
						&& (r.get("yfsb") == null || "".equals(r.get("yfsb")));
				if (zfypFlag) {
					r.put("yfsb", 0);
				}
			} else if (yplx == 0) {// 项目
				if (Integer.parseInt(r.get("jfbz").toString()) != 3) {
					if (r.get("midifyYjzx") == null) {
						if (lastYzzh != ObjectToTypes.parseInt(r.get("yzzhShow"))) {// 同组的第一项
							r.put("yjzx", 1);
							r.put("yzpx", 1);
							yzpx = 0;
						} else {
							r.put("yjzx", 0);
						}
					}
				} else {
					if (!r.containsKey("ypxh") || StrUtil.isEmptyIfStr(r.get("ypxh"))) {
						r.put("ypxh", 0);
					}
					r.put("yjxh", 0);
					r.put("xmlx", 1);
				}
				r.put("yjxh", 0);
				r.put("ypyf", StrUtil.isBlankIfStr(r.get("ypyf")) ? 0 : r.get("ypyf"));
				r.put("ypcd", 0);
				r.put("ycjl", StrUtil.isBlankIfStr(r.get("ycjl")) ? 0 : r.get("ycjl"));
				r.put("yfsb", 0);
			}
			Map<String, Object> where = new HashMap<String, Object>(16);
			where.put("lsbz", 0);
			// 不修改确认时间，防止时间格式错误
			r.remove("qrsj");

//			// 项目 计费标志处理
//			// add by liyunt
//			if (r.get("yplx") != null && "0".equals(r.get("yplx").toString())
//					&& !"3".equals(r.get("jfbz").toString())) {
//
//			}
			r.put("yzpx", ++yzpx);
			String uniqueIdStr = r.get("uniqueId") + "";
			r.remove("uniqueId");
			r.put("zfpb", r.get("zfpb") == null ? 0 : r.get("zfpb"));
			String fygb = ObjectToTypes.parseString(r.get("fygb"));
			//高质耗材或手术耗材数量为0时不保存
			Integer ycsl = r.get("ycsl").toString().contains(".") ? Integer.parseInt(r.get("ycsl").toString().substring(0, 1)) : 0;
			boolean saveFlag = StrUtil.isNotBlank(fygb) && ("4".equals(fygb)
					|| "17".equals(fygb)) && ycsl == 0;
			if(!saveFlag) {	
				if (OperationTypeEnum.create.getCode().equals(op)) {	
						CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(r, new CisHzyz(), true);
						cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
						cisHzyzDao.insert(cisHzyz);
				} else if (OperationTypeEnum.update.getCode().equals(op)) {
					CisHzyz cisHzyzUpdate = cisHzyzDao.getById(ObjectToTypes.parseInt(r.get("jlxh")));
					CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(r, new CisHzyz(), true);
					BeanUtil.copyProperties(cisHzyz, cisHzyzUpdate, 
							CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
					cisHzyzDao.update(cisHzyzUpdate);
				}
			}

			r.put("uniqueId", uniqueIdStr);
			if (yplx == 0) {// 项目
				if (wpjfbz == 1 && wzsfxmjgzy == 1) {
					if (("1").equals(r.get("lsyz") + "")) {
//						setSuppliesZy(manageUnit, ObjectToTypes.parseLong(r.get("jlxh")));
					}
				}
			}
			// lastYzzh = (Integer.parseInt(r.get("YZZH").toString()));
			// 更换组的时候判断下是否有附加项目需要保存
			if (fjxxList != null && fjxxList.size() > 0) {
				if ((i + 1 >= yzxxList.size()) || yzxxList.get(i + 1).get("yzzhShow") != lastYzzh) {
					String uniqueId = (r.get("jlxh") != null && r
							.get("jlxh").toString().length() > 0) ? "yzzh"
							: "uniqueId";
					for (Map<String, Object> fjxx : fjxxList) {
						if (!OperationTypeEnum.remove.getCode().equals(fjxx.get("opStatus"))
								&& !OperationTypeEnum.deal.getCode().equals(fjxx.get("opStatus"))) {
							if ((fjxx.get(uniqueId) + "").equals(r
									.get(uniqueId) + "")) {
								String ops = OperationTypeEnum.create.getCode();
								if (fjxx.get("jlxh") != null
										&& fjxx.get("jlxh").toString()
												.trim().length() > 0) {
									ops = OperationTypeEnum.update.getCode();
								}
								if (ops.equals(OperationTypeEnum.create.getCode()) && 
										ObjectToTypes.parseString(r.get("yzzhShow")).equals(ObjectToTypes.parseString(fjxx.get("yzzhShow")))) {
									fjxx.put("yzzh", r.get("yzzh"));
								}
								fjxx.put("tpn", 0);
								fjxx.put("yfsb", 0);
								fjxx.put("fyfs", 0);
								fjxx.put("kssj", DateUtil.date(lastDate).toTimestamp());
								fjxx.put("brks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
										: cisHzyzSaveReq.getBrks().toString()));
								fjxx.put("brbq", Long.parseLong(cisHzyzSaveReq.getBrbq() == null ? "0"
										: cisHzyzSaveReq.getBrbq() .toString()));
								fjxx.put("brch", cisHzyzSaveReq.getBrch());
								fjxx.put("srks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
										: cisHzyzSaveReq.getBrks().toString()));
								fjxx.put("ysgh", StrUtil.isBlankIfStr(r.get("ysgh")) ? user.getUserId() : r.get("ysgh"));
								fjxx.put("czgh", StrUtil.isBlankIfStr(r.get("czgh")) ? user.getUserId() : r.get("czgh"));
								fjxx.remove("qrsj");
								if (("1").equals(fjxx.get("lsyz") + "")) {
									fjxx.put("tzsj", fjxx.get("kssj"));
									fjxx.put("tzys", fjxx.get("ysgh"));
								}
								uniqueIdStr = fjxx.get("uniqueId") + "";
								fjxx.remove("uniqueId");
								if(OperationTypeEnum.create.getCode().equals(ops) &&
										ObjectToTypes.parseString(r.get("yzzhShow")).equals(ObjectToTypes.parseString(fjxx.get("yzzhShow")))) {
									CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(fjxx, new CisHzyz(), true);
									cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
									cisHzyzDao.insert(cisHzyz);
									fjxx.put("uniqueId", uniqueIdStr);
									fjxx.put("opStatus", OperationTypeEnum.deal.getCode());
								}else if(OperationTypeEnum.update.getCode().equals(ops)) {
									cisHzyzDao.update(fjxx);
									fjxx.put("uniqueId", uniqueIdStr);
									fjxx.put("opStatus", OperationTypeEnum.deal.getCode());

								}
							}
						}
					}
				}
			}
		}
		for (Map<String, Object> fjxx : fjxxList) {
			if (!OperationTypeEnum.remove.getCode().equals(fjxx.get("opStatus"))
					&& !OperationTypeEnum.deal.getCode().equals(fjxx.get("opStatus"))) {
				String ops = OperationTypeEnum.create.getCode();
				if (fjxx.get("jlxh") != null && fjxx.get("jlxh").toString().trim().length() > 0) {
					ops = OperationTypeEnum.update.getCode();
				}
				fjxx.put("yfsb", 0);
				fjxx.put("fyfs", 0);
				fjxx.put("kssj", fjxx.get("kssj").toString());
				fjxx.put("brks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
						: cisHzyzSaveReq.getBrks().toString()));
				fjxx.put("brbq", Long.parseLong(cisHzyzSaveReq.getBrbq() == null ? "0"
						: cisHzyzSaveReq.getBrbq() .toString()));
				fjxx.put("brch", cisHzyzSaveReq.getBrch());
				fjxx.put("srks", Long.parseLong(cisHzyzSaveReq.getBrks() == null ? "0"
						: cisHzyzSaveReq.getBrks().toString()));
				fjxx.remove("qrsj");
				if (("1").equals(fjxx.get("lsyz") + "")) {
					fjxx.put("tzsj", fjxx.get("kssj"));
					fjxx.put("tzys", fjxx.get("ysgh"));
				}
				String uniqueIdStr = fjxx.get("uniqueId") + "";
				fjxx.remove("uniqueId");
				if(OperationTypeEnum.create.getCode().equals(ops)) {
					CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(fjxx, new CisHzyz(), true);
					cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
					cisHzyzDao.insert(cisHzyz);
				}else if(OperationTypeEnum.update.getCode().equals(ops)) {
					CisHzyz cisHzyzUpdate = cisHzyzDao.getById(ObjectToTypes.parseInt(fjxx.get("jlxh")));
					CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(fjxx, new CisHzyz(), true);
					BeanUtil.copyProperties(cisHzyz, cisHzyzUpdate, 
							CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
					cisHzyzDao.update(cisHzyzUpdate);
				}
				fjxx.put("uniqueId", uniqueIdStr);
				fjxx.put("opStatus", OperationTypeEnum.deal.getCode());
			}
		}

		//变动医嘱提醒
//		sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.QYXXXT);	
//		String qyxxxt = sysXtcs == null ? "0" : sysXtcs.getCsz();
//		if ("1".equals(qyxxxt) && remaidSign) {
//			Map<String, Object> map = new HashMap<String, Object>(16);
//			map.put("ZYH", cisHzyzSaveReq.getZyh());
//			map.put("BRCH", cisHzyzSaveReq.getBrch());
//			map.put("OFFICENAME", zyDicSer.getBrksDic(manageUnit).get(cisHzyzSaveReq.getBrks()));
//			map.put("BRBQ", cisHzyzSaveReq.getBrbq());
//			map.put("BRXM", cisHzyzSaveReq.getBrxm());
//			map.put("XB", imHzry.getBrxb() == 1 ? "男" : "女");
//			sendAdviceChangeMessage(map, user);
//		}	  
		
	}

	/**
	 * 判断对象是否相等
	 * @Title: isEq
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param o1
	 * @param @param o2
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	private boolean isEq(Object o1, Object o2) {
		if (o1 == null || o1.toString() == "" || o1.toString() == "null") {
			o1 = "";
		}
		if (o2 == null || o2.toString() == "" || o2.toString() == "null") {
			o2 = "";
		}

		return o1.toString().equals(o2.toString());
	}
	
	/**
	 * 
	* @Title: saveDocSubmit
	* @Description: TODO 病区医嘱提交
	* @param @param cisHzyzSubmitReq
	* @param @param user
	* @param @return    设定文件
	* @return Integer    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	@Locked(value = "im_submit_hzyz_#[cisHzyzSubmitReq.zyh]")
	public CisHzyzDocSubmitResp saveDocSubmit(CisHzyzSubmitReq cisHzyzSubmitReq, SysUser user) {
		long beginTime = System.currentTimeMillis();
		CisHzyzDocSubmitResp cisHzyzDocSubmitResp = new CisHzyzDocSubmitResp();
		Integer manageUnit = user.getHospitalId();
		Integer userId = user.getUserId();

		Map<String, Object> body = BeanUtil.beanToMap(cisHzyzSubmitReq);
		
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("ZYH", body.get("zyh"));
		params.put("YSGH", userId);
		params.put("JGID", manageUnit);
		params.put("LSYZ", body.get("lsyz"));
		
		//取当前用户的最大抗生素等级
		String[] strLevel = null;
		int level = 0;
		HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(userId);
		if(hrPersonnel !=null && StrUtil.isNotBlank(hrPersonnel.getAntibioticlevel())) {
			strLevel =  hrPersonnel.getAntibioticlevel().split(",");
			int[] array = Arrays.asList(strLevel).stream().mapToInt(Integer::parseInt).toArray();
			level = Arrays.stream(array).max().getAsInt();
		}

		List<Integer> yzzhList = new ArrayList<Integer>();
		//增加抗菌药物判断	
		List<Map<String, Object>> list = cisHzyzDao.queryIsExistAntimicrobial(params);
		long kangjunyaoBeginTime = System.currentTimeMillis();
		if(CollectionUtils.isNotEmpty(list)) {
			StringBuffer str = new StringBuffer();
			ReturnEntity<String> returnEntity = new ReturnEntity<String>();	
			List<DocSubmitBody> bodyList = new ArrayList<CisHzyzDocSubmitResp.DocSubmitBody>();
			for(Map<String, Object> map : list) {
				Integer kssdj = ObjectToTypes.parseInt(map.get("KSSDJ"));
				//抗菌药等级和审批结果必须分开判断,为了提交重复的申请单
				if(level < kssdj.intValue() || kssdj.intValue() == 3) {
					yzzhList.add(ObjectToTypes.parseInt(map.get("YZZH")));
					str.append(ObjectToTypes.parseString(map.get("YZMC"))).append(",");
					if(StrUtil.isBlankIfStr(map.get("KJYWSP"))) {
						DocSubmitBody docSubmitBody = new DocSubmitBody();
						docSubmitBody.setJlxh(ObjectToTypes.parseInt(map.get("JLXH")));
						docSubmitBody.setYzzh(ObjectToTypes.parseInt(map.get("YZZH")));
						docSubmitBody.setKssdj(kssdj);
						bodyList.add(docSubmitBody);
					}		
				}
			}
			if(str.length() > 0) {
				str.deleteCharAt(str.length() - 1);
				returnEntity.setError("ERROR_DCTWORK_ZYBRRY_00024", new String[] {
						str.toString()
				});
			}	
			cisHzyzDocSubmitResp.setMessage(returnEntity.getMessage());
			cisHzyzDocSubmitResp.setYzxxList(bodyList);
			//查询抗菌药物越级参数
			AmqcKjywsycs amqcKjywsycs = amqcKjywsycsService.getSycsById(manageUnit);
			if(amqcKjywsycs == null) {
				throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00025");
			}
			cisHzyzDocSubmitResp.setSqlx(amqcKjywsycs.getYjsykjywgkfs());
			if(StrUtil.isBlankIfStr(amqcKjywsycs.getMzyxsyzdts())) {
				cisHzyzDocSubmitResp.setKjywzdts(0);
			}else {
				cisHzyzDocSubmitResp.setKjywzdts(amqcKjywsycs.getMzyxsyzdts());
			}			
		}
		
		if("1".equals(ObjectToTypes.parseString(body.get("lsyz")))) {
		//更新会诊申请医嘱状态
			List<Integer> sqxhList = cisHzyzDao.queryYzHzsq(params);
			if(!sqxhList.isEmpty()) {
				long hzCount = cisZyHzsqDao.updateYzHzsqSubmitStatus(sqxhList);
				if(hzCount > 0) {
					List<Map<String, Object>> listMap = cisHzyzDao.queryHzSqId(params);
					for(Map<String, Object> resMap : listMap) {
						Integer sqxh = ObjectToTypes.parseInt(resMap.get("SQID"));
						Map<String, Object> map = cisZyHzsqDao.queryImHzBySqxh(sqxh);
						WaitProcessMessage<CisZyHzsqMessDto> wait = new WaitProcessMessage<CisZyHzsqMessDto>();
						wait.setTitle("待会诊患者");
						List<LineContentDto> dtoList = new ArrayList<LineContentDto>();
						LineContentDto dto = new LineContentDto();
						dto.setLabel("床号");
						dto.setContent(ObjectToTypes.parseString(map.get("BRCH")));
						dtoList.add(dto);
						dto = new LineContentDto();
						dto.setLabel("患者");
						dto.setContent(ObjectToTypes.parseString(map.get("BRXM")));
						dtoList.add(dto);
						dto = new LineContentDto();
						dto.setContent(ObjectToTypes.parseString(map.get("XB")));
						dtoList.add(dto);
						dto = new LineContentDto();
						dto.setContent(ObjectToTypes.parseString(map.get("OFFICENAME")));
						dtoList.add(dto);
						wait.setContents(dtoList);
						wait.setData(new CisZyHzsqMessDto(sqxh));
						wait.setRelativePath("1");
						List<Integer> userIdList = new ArrayList<Integer>();
						CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx(); 
						cisZyHzyqdx.setSqxh(sqxh);
						List<CisZyHzyqdx> yqdxList = cisZyHzyqdxDao.findByEntity(cisZyHzyqdx);
						for(CisZyHzyqdx yqdx : yqdxList) {
							String yqdxStr = yqdx.getYqdx();
							if("-1".equals(yqdxStr)) {
								SysYwqxkz sysYwqxkz = new SysYwqxkz();
								sysYwqxkz.setKsdm(ObjectToTypes.parseInt(yqdx.getNyqhzks()));
								sysYwqxkz.setYwlb(7);
								sysYwqxkz.setJgid(user.getHospitalId());
								List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
								for(SysYwqxkz qxkz : qxkzList) {
									if(!user.getUserId().equals(qxkz.getYgdm())) {
										userIdList.add(qxkz.getYgdm());
									}
								}
							}else {
								userIdList.add(ObjectToTypes.parseInt(yqdxStr));
							}
							
						}
						String uuid = rabbit.sendAlertMsg("imsConsultation", wait, MessageLevelType.low, 
						TargetConfig.toSystemUser(SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline), false);
						cisZyHzsqDao.updateMessIdBySqxh(uuid, sqxh);
					}
				}
			}
		}
		
		params.put("yzzhList", yzzhList);
		List<Integer> ztJlxhList = new ArrayList<Integer>();
		List<PubPharKcdjNew> kcdjList = new ArrayList<PubPharKcdjNew>();
		if("1".equals(ObjectToTypes.parseString(body.get("lsyz")))) {
			List<CisHzyzKcdjResp> respList = cisHzyzDao.queryYpKcdjInfo(params);
			System.out.println(JSONUtil.toJsonStr(respList));
			Map<Integer, List<CisHzyzKcdjResp>> groups = respList.stream().
					collect(Collectors.groupingBy(o -> o.getYfsb()));
			StringJoiner stringJoiner = new StringJoiner(",");
			for(Map.Entry<Integer, List<CisHzyzKcdjResp>> entry : groups.entrySet()) {
				Map<String, DoubleSummaryStatistics> sumGroups = entry.getValue().stream().
						collect(Collectors.groupingBy(o -> o.getYpxh() + ","+ o.getYpcd(), 
			    		LinkedHashMap:: new, Collectors.summarizingDouble(e -> e.getYpsl().doubleValue())));
				List<String> strList =  new ArrayList<String>();
				strList.addAll(sumGroups.keySet());

				List<Map<String, Object>> medicineInventory = drugService.queryHospitalizationStock(entry.getKey(), strList);
				List<Integer> ypxhList = medicineInventory.stream().map(o ->
				ObjectToTypes.parseInt(o.get("YPXH"))).collect(Collectors.toList());
				
				
				List<CisHzyzKcdjResp> notExistList = respList.stream().filter(
				o -> !ypxhList.contains(o.getYpxh()) && entry.getKey().equals(o.getYfsb())).collect(Collectors.toList());
				yzzhList.addAll(notExistList.stream().map(CisHzyzKcdjResp::getYzzh).distinct().collect(Collectors.toList()));
				notExistList.stream().map(CisHzyzKcdjResp::getYzmc).distinct().collect(Collectors.toList()).forEach(item -> {
					stringJoiner.add(item.toString());
				});
				ztJlxhList.addAll(notExistList.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.getYplx()))
						&& "1".equals(ObjectToTypes.parseString(o.getZtbz())))
						.map(o -> o.getZtyzjlxh()).distinct().collect(Collectors.toList()));

				
				for(Map.Entry<String, DoubleSummaryStatistics> sumEntry : sumGroups.entrySet()) {
					for(Map<String, Object> medInv : medicineInventory) {
						if(sumEntry.getKey().equals(ObjectToTypes.parseString(medInv.get("YPXH")) + "," + 
								ObjectToTypes.parseString(medInv.get("YPCD")))){
							if(sumEntry.getValue().getSum() > ObjectToTypes.parseDouble(medInv.get("KCZL"))){
								List<CisHzyzKcdjResp> filterList = respList.stream().filter(e -> 
								sumEntry.getKey().equals(String.format("%s,%s", e.getYpxh(),e.getYpcd()))).collect(Collectors.toList());
								yzzhList.addAll(filterList.stream().map(CisHzyzKcdjResp::getYzzh).distinct().collect(Collectors.toList()));
								filterList.stream().map(CisHzyzKcdjResp::getYzmc).distinct().collect(Collectors.toList()).forEach(item -> {
									stringJoiner.add(item.toString());
								});
								ztJlxhList.addAll(filterList.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.getYplx()))
										&& "1".equals(ObjectToTypes.parseString(o.getZtbz())))
										.map(o -> o.getZtyzjlxh()).collect(Collectors.toList()));
							}
							
						}
					}
				}
			}
			kcdjList = com.buit.utill.BeanUtil.toBeans(respList.stream().filter(e -> !yzzhList.contains(e.getYzzh()))
					.collect(Collectors.toList()), PubPharKcdjNew.class);

			if(stringJoiner.length() > 0) {
				ReturnEntity<String> returnEntity = new ReturnEntity<String>();	
				returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00057", new String[] {
						stringJoiner.toString()
				});
				cisHzyzDocSubmitResp.setKcMessage(returnEntity.getMessage());
			}
			
		}
		
		long updateYzBeginTime = System.currentTimeMillis();

		//更新医嘱提交状态
		int l = cisHzyzDao.updateBqyzSubmitStatus(params);

		long updateNotZtEndTime = System.currentTimeMillis();
		logger.info("更新非组套医嘱提交状态={}", updateNotZtEndTime - updateYzBeginTime);

		params.remove("yzzhList");

		//更新组套医嘱提交状态
		params.put("ztJlxhList", ztJlxhList.stream().distinct().collect(Collectors.toList()));
		cisHzyzZtDao.updateBqyzZtSubmitStatus(params);

		long updateYzztEndTime = System.currentTimeMillis();
		logger.info("更新组套提交状态={}", updateYzztEndTime - updateNotZtEndTime);

		if(!kcdjList.isEmpty()) {
			pubDrugKcdjSerImpl.frozen(kcdjList);
		}


		if (l > 0) {
			String qyxxxt = "";
			SysXtcs sysXtcs= sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.QYXXXT);
	        if(sysXtcs != null ){
	        	qyxxxt = sysXtcs.getCsz();	        	
	        }
	        CisYzmess cisYzmess = cisYzmessDao.queryLatestTime(ObjectToTypes.parseInt(body.get("zyh")));
	        boolean flag = "1".equals(qyxxxt) && 
	        		(cisYzmess == null || (cisYzmess !=null && cisYzmess.getZt() == 2));
			if (flag) {
				Map<String, Object> map = new HashMap<String, Object>(16);
				ImHzry imHzry = imHzryDao.getById(ObjectToTypes.parseInt(body.get("zyh")));
				map.put("ZYH", body.get("zyh"));
				map.put("BRCH", imHzry.getBrch());
				map.put("OFFICENAME", zyDicSer.getBrksDic(manageUnit).get(imHzry.getBrks()));
				map.put("BRBQ", imHzry.getBrbq());
				map.put("BRXM", imHzry.getBrxm());
				map.put("XB", imHzry.getBrxb() == 1 ? "男" : "女");
				map.put("ZYHM", imHzry.getZyhm());
				sendAdviceChangeMessage(map, user);
			} 

		}
		long updateYzEndTime = System.currentTimeMillis();
		logger.info("全部执行时间={},更新组套医嘱提交状态={}", updateYzEndTime - beginTime, updateYzEndTime - updateYzBeginTime);
		return cisHzyzDocSubmitResp;
	}
	
	
	/**
	 * 
	* @Title: doLoadHospitalParams
	* @Description: TODO 查询住院发药药房是否设置
	* @param @param brbq
	* @param @param user
	* @param @return    设定文件
	* @return NisFyyfQxResp    返回类型
	* @author 龚方舟
	* @throws
	 */
	public NisFyyfQxResp doLoadHospitalParams(Integer brbq, SysUser user){
		NisFyyfQxResp nisFyyfQxResp = new NisFyyfQxResp();	
		Integer manageUnit = user.getHospitalId();
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("BQDM", brbq);
		params.put("JGID", manageUnit);
		List<Map<String, Object>> ret = nisFyyfDao.queryInpatientPharmacyInfo(params);
		if(CollectionUtils.isEmpty(ret)) {
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00020");
		}
		
		if (ret != null && ret.size() > 0) {
            nisFyyfQxResp.setNisFyyfRespList(BUHISUtil.ListToResultSet(ret, new NisFyyfResp()));
        }
		//门诊处方权限信息获取
		HrPersonnelModel sys = hrPersonnelService.getPersonnelById(user.getUserId());
		nisFyyfQxResp.setMzysqx(sys);
		return nisFyyfQxResp;
	}
	
	
	/**
	 * @Description 医嘱取消
	 * @Title  doCancelYz
	 * @author  gfz
	 * @Date  2021/6/22 0:05
	 * @param req
	 * @param user
	 * @Return  java.lang.String
	 * @Exception
	*/
	@Transactional(rollbackFor = Exception.class)
	public String doCancelYz(CisHzyzCancelReq req, SysUser user) {
		boolean returnFlag = false;
		List<CisHzyzCancelBody> list = req.getCancelBodyList();
		//组套
		List<CisHzyzCancelBody> ztList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
				.collect(Collectors.toList());
		if(!ztList.isEmpty()) {
			List<Integer> notReviewList = cisHzyzZtDao.queryNotReview(ztList.stream().
					map(CisHzyzCancelBody::getJlxh).collect(Collectors.toList()));
			if(!notReviewList.isEmpty() && ztList.size() != notReviewList.size()) {
				returnFlag = true;
			}
			cisHzyzDao.cancelSubmitStatusByZtJlxhList(notReviewList);
			cisHzyzZtDao.cancelZtSubmitStatusByJlxhList(notReviewList);
		}
		//非组套
		List<CisHzyzCancelBody> ptList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.collect(Collectors.toList());
		if(!ptList.isEmpty()) {
			List<Integer> notReviewList = cisHzyzDao.queryPtNotReview(ptList.stream().
					map(CisHzyzCancelBody::getJlxh).collect(Collectors.toList()));
			if(!notReviewList.isEmpty() && ptList.size() != notReviewList.size()) {
				returnFlag = true;
			}
			cisHzyzDao.cancelSubmitStatusByJlxhList(notReviewList);
		}
		
		//库存解冻
		if(req.getLsyz() == 1) {
			List<PubPharUnfreezeDto> unFreeList = new ArrayList<PubPharUnfreezeDto>();
			List<CisHzyzCancelBody> ypList = list.stream().filter(o -> o.getYplx() > 0)
					.collect(Collectors.toList());
			if(!ypList.isEmpty()) {
				List<Integer> jlxhList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0) 
						&& o.getYplx() > 0).map(CisHzyzCancelBody::getJlxh).collect(Collectors.toList());
				for(Integer item : jlxhList) {
					PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
					unfreezeDto.setJgid(user.getHospitalId());
					unfreezeDto.setJlxh(item);
					unfreezeDto.setYwlb(3);
					unFreeList.add(unfreezeDto);
				}
				
				List<Integer> ztjlxhList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
						.map(CisHzyzCancelBody::getJlxh).collect(Collectors.toList());
				for(Integer ztjlxh : ztjlxhList) {
					CisHzyz cisHzyz = new CisHzyz();    
					cisHzyz.setZtyzjlxh(ztjlxh);
					cisHzyz.setJgid(user.getHospitalId());
					List<CisHzyz> hzyzList =cisHzyzDao.findByEntity(cisHzyz);
					for(CisHzyz resp : hzyzList) {
						PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
						unfreezeDto.setJgid(user.getHospitalId());
						unfreezeDto.setJlxh(resp.getJlxh());
						unfreezeDto.setYwlb(3);
						unFreeList.add(unfreezeDto);
					}
				}
				pubDrugKcdjSerImpl.unfreeze(unFreeList);
			}
		}
		
		//取消会诊申请表
		List<Integer> hzList = list.stream().filter(o -> (o.getYzlx() != null && o.getYzlx() == 4 && o.getSqid() != null))
				.map(CisHzyzCancelBody::getSqid).collect(Collectors.toList());
		if(!hzList.isEmpty()) {
			cisZyHzsqDao.canceHzlSubmitStatusByJlxhList(hzList);
			for(Integer sqxh : hzList) {
				CisZyHzsq cisZyHzsq = cisZyHzsqDao.getById(sqxh);
				if(cisZyHzsq != null && StrUtil.isNotBlank(cisZyHzsq.getMessid())) {
					List<Integer> userIdList = new ArrayList<Integer>();
					CisZyHzyqdx cisZyHzyqdx = new CisZyHzyqdx();
					cisZyHzyqdx.setSqxh(sqxh);
					List<CisZyHzyqdx> yqdxList = cisZyHzyqdxDao.findByEntity(cisZyHzyqdx);
					for(CisZyHzyqdx yqdx : yqdxList) {
						String yqdxStr = yqdx.getYqdx();
						if("-1".equals(yqdxStr)) {
							SysYwqxkz sysYwqxkz = new SysYwqxkz();
							sysYwqxkz.setKsdm(ObjectToTypes.parseInt(yqdx.getNyqhzks()));
							sysYwqxkz.setYwlb(7);
							sysYwqxkz.setJgid(user.getHospitalId());
							List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
							for(SysYwqxkz qxkz : qxkzList) {
								userIdList.add(qxkz.getYgdm());
							}
						}else {
							userIdList.add(ObjectToTypes.parseInt(yqdxStr));
						}

					}
					rabbit.cancelAlertMsg(cisZyHzsq.getMessid(), TargetConfig.toSystemUser(
							SystemTypeEnum.zyysz, userIdList, CoverageTypeEnum.onlineAndOffline));
				}
			}
		}

		List<Integer> ywList = ptList.stream().filter(e -> "0".equals(ObjectToTypes.parseString(e.getKjywsp())) && e.getSqid() != null)
				.map(o -> o.getSqid()).collect(Collectors.toList());
		if(!ywList.isEmpty()) {
			amqcKjywsysqDao.deleteBatchKjywsysqBySqidList(ywList);
			amqcKjywsysqypDao.deleteBatchYpBySqidList(ywList);
			amqcKjywsyhzksysDao.deleteBatchYjBySqidList(ywList);
		}
		modifyLevelOfCare(req.getZyh(), req.getLsyz(), user.getHospitalId());
		if(returnFlag) {
			ReturnEntity<String> returnEntity = new ReturnEntity<>();
			returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00042", null);
			return returnEntity.getMessage();
		}
		return null;
	}
	
	/**
	* @Title: modifyLevelOfCare
	* @Description: TODO 修改护理级别
	* @param @param body    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void modifyLevelOfCare(Integer zyh, Integer lsyz, Integer jgid) {
		//只针对长期医嘱
		try {
			if ("0".equals(ObjectToTypes.parseString(lsyz))) {
				
				SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.YJHLJBMC);
				String yjmc = sysXtcs.getCsz(); 
				sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.EJHLJBMC);
				String ejmc = sysXtcs.getCsz(); 
				sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.SJHLJBMC);
				String sjmc = sysXtcs.getCsz(); 
				// 修改护理级别
				Map<String, Object> zyhparam = new HashMap<String, Object>(16);
				zyhparam.put("ZYH", zyh);			
				Map<String, Object> hljbMap = cisHzyzDao.queryLevelOfCare(zyhparam);
				if (hljbMap == null) {
					imHzryDao.modifyLevelOfCare(zyhparam);
				}
				if (hljbMap != null && sjmc.equals(
						ObjectToTypes.parseString(hljbMap.get("YZMC")))) {
					zyhparam.put("HLJB", 3);
					imHzryDao.modifyLevelOfCare(zyhparam);
				} else if (hljbMap != null && ejmc.equals(
						ObjectToTypes.parseString(hljbMap.get("YZMC")))) {
					zyhparam.put("HLJB", 2);
					imHzryDao.modifyLevelOfCare(zyhparam);
				} else if (hljbMap != null && yjmc.equals(
						ObjectToTypes.parseString(hljbMap.get("YZMC")))) {
					zyhparam.put("HLJB", 1);
					imHzryDao.modifyLevelOfCare(zyhparam);
				}else if (hljbMap != null && CareLevelEnum.levelZero.getCode().equals(
						ObjectToTypes.parseString(hljbMap.get("YZMC")))) {
					zyhparam.put("HLJB", 0);
					imHzryDao.modifyLevelOfCare(zyhparam);
				}
			}
		} catch (MyBatisSystemException e) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00048");
		}
		
	}
	
	/**
	 * 更新医嘱信息（历史停嘱信息取消停嘱）
	 * @Title: doUpdateAdviceStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param isGroup
	 * @param @param zyh
	 * @param @param yzzh
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void doUpdateAdviceStatus(Boolean isGroup, Integer zyh, Integer yzzh, Integer jlxh){
		if (!isGroup) {
			cisHzyzDao.cancelStopOrderM(zyh, yzzh, jlxh);
		}else {
			cisHzyzDao.cancelStopOrder(zyh, yzzh);
		}
	}
	
	
	/**
	 * @Description 病区病人管理医嘱作废或取消作废
	 * @author  gfz
	 * @Date  2021/5/31 22:00
	 * @param req
	 * @param user
	 * @Return  void
	 * @Exception
	*/
	@SuppressWarnings("unlikely-arg-type")
	@Transactional(rollbackFor = Exception.class)
	public String saveYzzf(CisHzyzInvalidReq req, SysUser user) {
		Integer yzzf = req.getYzzf();
		String zfgh = yzzf == 1 ? ObjectToTypes.parseString(user.getUserId()) : "";
		Timestamp zfsj = yzzf == 1 ? DateUtil.date().toTimestamp() : null;
		List<CisHzyzInvalidBody> list =  req.getInvalidBodyList();
		List<Integer> intList = new ArrayList<Integer>();
		if(yzzf == 1) {
			modifyLevelOfCare(req.getZyh(), req.getLsyz(), user.getHospitalId());
			//判断临时药品医嘱是否退药才能作废
			if(req.getLsyz() == 1) {
				intList = imFeeFymxDao.queryTotalCost(list.stream().
						map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList()), user.getHospitalId());
				if(!intList.isEmpty()) {
					list.removeAll(intList);
				}
			}

            List<ZlRwfpUpdateReq> zlList = cisHzyzDao.queryTreatmentProjectByJlxh(user.getHospitalId(), list.stream().
            		map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList()), 6, null);
            if (CollectionUtils.isNotEmpty(zlList)) {
                zlRwfpService.deleteByYzJlxh(zlList.stream().map(ZlRwfpUpdateReq::getYzJlxh).collect(Collectors.toList()));
            }
            
            List<PubPharUnfreezeDto> unFreeList = new ArrayList<PubPharUnfreezeDto>();
			List<CisHzyzInvalidBody> ypList = list.stream().filter(o -> o.getYplx() > 0)
					.collect(Collectors.toList());
			if(!ypList.isEmpty()) {
				List<Integer> jlxhList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0) 
						&& o.getYplx() > 0).map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList());
				for(Integer item : jlxhList) {
					PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
					unfreezeDto.setJgid(user.getHospitalId());
					unfreezeDto.setJlxh(item);
					unfreezeDto.setYwlb(3);
					unFreeList.add(unfreezeDto);
				}
				
				List<Integer> ztjlxhList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
						.map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList());
				for(Integer ztjlxh : ztjlxhList) {
					CisHzyz cisHzyz = new CisHzyz();    
					cisHzyz.setZtyzjlxh(ztjlxh);
					cisHzyz.setJgid(user.getHospitalId());
					List<CisHzyz> hzyzList =cisHzyzDao.findByEntity(cisHzyz);
					for(CisHzyz resp : hzyzList) {
						PubPharUnfreezeDto unfreezeDto = new PubPharUnfreezeDto();
						unfreezeDto.setJgid(user.getHospitalId());
						unfreezeDto.setJlxh(resp.getJlxh());
						unfreezeDto.setYwlb(3);
						unFreeList.add(unfreezeDto);
					}
				}
				pubDrugKcdjSerImpl.unfreeze(unFreeList);
			}

		}

		List<Integer> ztjlxhList = list.stream().filter(o -> o.getZtbz() != null && o.getZtbz() == 1)
				.map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList());
		if(!ztjlxhList.isEmpty()) {
			cisHzyzZtDao.invalidZtByJlxh(yzzf, zfgh, zfsj, ztjlxhList);
			cisHzyzDao.invalidByZtJlxh(yzzf, zfgh, zfsj, ztjlxhList);
		}

		List<Integer> jlxhList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.map(CisHzyzInvalidBody::getJlxh).collect(Collectors.toList());
		if(!jlxhList.isEmpty()) {
			cisHzyzDao.invalidByJlxh(yzzf, zfgh, zfsj, jlxhList);
		}
		
		if(!intList.isEmpty()) {
			ReturnEntity<String> returnEntity = new ReturnEntity<>();
			returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00054", null);
			return returnEntity.getMessage();
		}
		return null;
	}
	
	/**
	* @Title: doQueryZTMX2
	* @Description: TODO 查询已执行医嘱和组套
	* @param @param jlxh
	* @param @return    设定文件
	* @return List<CisHzyzResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<CisHzyzResp> doQueryZtmx2(Integer jlxh){
		List<CisHzyzResp> cisHzyzRespList = cisHzyzDao.queryBqyzExecuted(jlxh);
		return cisHzyzRespList;
	}
	
	/**
	* @Title: doYjIsPay
	* @Description: TODO 判断是否有收费项目,取消已执行医嘱
	* @param @param cisHzyzXhListReq    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doYjIsPay(CisHzyzXhListReq cisHzyzXhListReq) {
		List<Integer> jlxhList = cisHzyzXhListReq.getJlxhList();
		List<Integer> ztyzjlxhList = cisHzyzXhListReq.getZtyzjlxhList();
		StringBuffer jlxhStr = new StringBuffer();
		StringBuffer ztyzjlxhStr = new StringBuffer();
	    Integer ztbz = cisHzyzXhListReq.getZtbz();
	 
    	for (int i = 0; i < jlxhList.size(); i++) {
		      if (i == 0) {
		    	  jlxhStr.append(ObjectToTypes.parseString(jlxhList.get(i)));
		      } else {
		    	  jlxhStr = jlxhStr.append(",").append(ObjectToTypes.parseString(jlxhList.get(i)));
		      }
	    }    
	    for (int i = 0; i < ztyzjlxhList.size(); i++) {
		      if (i == 0) {
		    	  ztyzjlxhStr.append(ObjectToTypes.parseString(ztyzjlxhList.get(i)));
		      }else{
		    	  ztyzjlxhStr = ztyzjlxhStr.append(",").append(ObjectToTypes.parseString(ztyzjlxhList.get(i)));
		      }
	    }
	    List<ImFeeFymx> imFeeFymxList = imFeeFymxDao.queryZyfymxByYzxh(jlxhStr.toString());
		BigDecimal zjje = imFeeFymxDao.queryZyfymxSumByYzxh(jlxhStr.toString());
	    //医技项目没有收费 或 已退费
	    if(imFeeFymxList.size() == 0 || zjje.compareTo(BigDecimal.ZERO) == 0) {
	    	if(ztbz != null) {
	    		if (ztbz == 1){
		    		cisHzyzZtDao.updateBqyzZtExecuted(ztyzjlxhStr.toString());
	    		}
	    		cisHzyzDao.updateBqyzExecuted(jlxhStr.toString());
	    		imFeeFymxYjDao.deleteByYzxh(jlxhStr.toString());
	    	}
	    }else{
	    	throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00010");
	    }
		 
	}
	
	/**
	* @Title: doZbyZx
	* @Description: TODO 自备药停嘱执行
	* @param @param zyh
	* @param @param user
	* @param @return    设定文件
	* @return 返回类型
	* @author 龚方舟
	* @throws
	 */
	public void doZbyZx(Integer zyh, SysUser user) {
		Integer manageUnit = user.getHospitalId();
		CisHzyz cisHzyz = new CisHzyz();
		cisHzyz.setZyh(zyh);
		cisHzyz.setJgid(manageUnit);
		int rs = 0;		
		long count = cisHzyzDao.queryPreparedMedicineCount(cisHzyz);
		if (count > 0) {
			rs = cisHzyzDao.updatePreparedMedicine(cisHzyz);
		}
		if(rs == 0) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00012");
		}	
	}
	
	/**
	* @Title: queryPatientVisitInfo
	* @Description: TODO 获取病人基本信息和交款信息
	* @param @param req
	* @param @param sys    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public ImHzryDctworkResp queryPatientVisitInfo(Integer zyh, String zyhm, 
			String brch, String type) {
		ImHzry imHzry = null;
		if(zyh != null) {
			imHzry = imHzryDao.getById(zyh);
		}else if(StrUtil.isNotBlank(zyhm)) {
			ImHzry imHzryQuery = new ImHzry();
			imHzryQuery.setZyhm(zyhm);
			List<ImHzry> imHzryList = imHzryDao.findByEntity(imHzryQuery);
			if(CollectionUtils.isNotEmpty(imHzryList)) {
				imHzry = imHzryList.get(0);
				if(imHzry.getCypb() >= 8) {
					throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00026");
				}
			}	
		}else if(StrUtil.isNotBlank(brch)) {
			ImCwsz imCwsz = new ImCwsz();
			imCwsz.setBrch(brch);
			List<ImCwsz> imCwszList = imCwszDao.findByEntity(imCwsz);
			if(CollectionUtils.isNotEmpty(imCwszList)) {
				imHzry = imHzryDao.getById(imCwszList.get(0).getZyh());
				if(imHzry.getCypb() >= 8) {
					throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00026");
				}
			}
		}

		if(Objects.isNull(imHzry)){
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00027");
		}
		
		if(StrUtil.isNotBlank(type)) {
			if(StrUtil.isBlankIfStr(imHzry.getZyys())) {
				throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00021");
			}
			
			if(imHzry.getZkzt() != null && imHzry.getZkzt() > 0) {
				throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00022");
            }
        }


        ImHzryDctworkResp imHzryDctworkResp = new ImHzryDctworkResp();
        Map<String, Object> params = new HashMap<String, Object>(16);
        params.put("ZYH", imHzry.getZyh());
        params.put("JSLX", 0);
        Map<String, Object> jkhj = getTotalAmount(params);
        Map<String, Object> zfhj = getSelfFinancingTotal(params);
        Map<String, Object> ret = BUHISUtil.getPersonAge(DateUtil.date(imHzry.getCsny()), null);
        imHzryDctworkResp.setZyh(imHzry.getZyh());
        imHzryDctworkResp.setZyhm(imHzry.getZyhm());
        imHzryDctworkResp.setBrch(imHzry.getBrch());
        imHzryDctworkResp.setBrxm(imHzry.getBrxm());
        imHzryDctworkResp.setBrxb(imHzry.getBrxb());
        imHzryDctworkResp.setBrks(imHzry.getBrks());
        imHzryDctworkResp.setBrxz(imHzry.getBrxz());
        imHzryDctworkResp.setRynl(imHzry.getRynl());
        imHzryDctworkResp.setMqzd(imHzry.getMqzd());
        imHzryDctworkResp.setAge(ObjectToTypes.parseString(ret.get("age")));
		BigDecimal jkje = ObjectToTypes.parseBigDecimal(jkhj.get("JKHJ"));
		BigDecimal zfje = ObjectToTypes.parseBigDecimal(zfhj.get("ZFHJ"));
		BigDecimal syje = jkje.subtract(zfje);
		imHzryDctworkResp.setYjk(jkje);
		imHzryDctworkResp.setZfje(zfje);
		imHzryDctworkResp.setSyje(syje);
		return imHzryDctworkResp;
	}
	
	/**
	* @Title: doQuerydbjfFymx
	* @Description: TODO 查询血透减负list
	* @param @param zyh
	* @param @param user
	* @param @return    设定文件
	* @return List<ImFeeFymxXtResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<ImFeeFymxXtResp> doQuerydbjfFymx(Integer zyh, SysUser user){
		ImFeeFymx imFeeFymx = new ImFeeFymx();
		Integer manageUnit = user.getHospitalId();
		Integer userId = user.getUserId();
		imFeeFymx.setZyh(zyh);
		imFeeFymx.setJgid(manageUnit);
		imFeeFymx.setYsgh(ObjectToTypes.parseString(userId));
		return imFeeFymxDao.queryHemodialysis(imFeeFymx);
	}
	
	/** 
	* @Title: doSaveDbjfFymx
	* @Description: TODO 保存大病血透减负
	* @param @param imFeeFymxDbReq    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doSaveDbjfFymx(ImFeeFymxDbReq imFeeFymxDbReq){
  		Integer zyh = imFeeFymxDbReq.getZyh();
  		List<ImFeeFymx> imFeeFymxList = imFeeFymxDbReq.getImFeeFymxList();
  		List<Integer> dbBjf = new ArrayList<Integer>();
  		List<Integer> dbjf = new ArrayList<Integer>();
  		//大病减负
  		for(int i = 0; i < imFeeFymxList.size(); i++){
  			if("1".equals(ObjectToTypes.parseString(imFeeFymxList.get(i).getDbjfbz()))){
  				dbjf.add(imFeeFymxList.get(i).getJlxh());
  			}else{
  				//不减负
  				dbBjf.add(imFeeFymxList.get(i).getJlxh());
  			}
  		}
  		ImHzry imHzry = imHzryDao.getById(zyh);

  		if(imHzry != null && "1".equals(ObjectToTypes.parseString(imHzry.getDbjfbz()))) {
  			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00015");
  		}
  		if(dbjf.size()>0){
  			List<ImFeeFymx> imFeeFymxZfList = imFeeFymxDao.querySelfSufficiency(zyh, dbBjf);
			for(int i = 0 ; i < imFeeFymxZfList.size(); i++){
				if(ObjectToTypes.parseDouble(imFeeFymxZfList.get(i).getZfbl())>=1){
				 	throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00016");
				}
			}
  		}
  		if(dbBjf.size()>0){
  			imFeeFymxDao.updateNotIsSeriousIllness(zyh, dbBjf);
		}
  		if(dbjf.size()>0){
  			imFeeFymxDao.updateIsSeriousIllness(zyh, dbjf);
  		}
  	  
	}
	
	/**
	* @Title: doLoadDischargeDays
	* @Description: TODO 计算住院天数
	* @param @param ryrq_s
	* @param @param cyrq_s
	* @param @return    设定文件
	* @return Double    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Double doLoadDischargeDays(String ryrqs, String cyrqs) {
        if (StrUtil.isBlank(ryrqs) || StrUtil.isBlank(cyrqs)) {
            throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00018");
        }
        return BUHISUtil.loadDischargeDays(ryrqs, cyrqs);
    }
	
	/**
	* @Title: doSaveLeaveHospitalProve
	* @Description: TODO 出院证保存
	* @param @param body
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doSaveLeaveHospitalProve(ImRcjlCyReq imRcjlCyReq, SysUser user) {
		Map<String, Object> body = BeanUtil.beanToMap(imRcjlCyReq);
		Integer jgid = user.getHospitalId();
		Integer userId = user.getUserId();
		dischargeDateSetting(body, jgid, userId);
		if (body.containsKey("JLXH")) {
			if (body.get("JLXH") != null && body.get("JLXH") != "") {
				body.put("JLXH", body.get("JLXH") + "");
				saveCyYz(body, jgid, userId);
			}
		}
	}
	
	
	/**
	* @Title: saveCyYz
	* @Description: TODO 出院证保存
	* @param @param body
	* @param @param jgid
	* @param @param userId
	* @param @return    设定文件
	* @return Map<String,Object>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void saveCyYz(Map<String, Object> body, Integer jgid, Integer userId) {
		int cyfs = 0;
		if (body.get("cyfs") != null && body.get("cyfs") != "") {
			cyfs = Integer.parseInt(body.get("cyfs") + "");
		}
		Map<String, Object> map = new HashMap<String, Object>(16);

		String cyrq = DateUtil.format((Date)body.get("xcyrq"), DatePattern.NORM_DATE_PATTERN);
		//住院号
		Integer zyh = ObjectToTypes.parseInt(body.get("zyh"));
		ImHzryReq imHzryReq = new ImHzryReq();
		imHzryReq.setZyh(zyh);
		imHzryReq.setJgid(jgid);

		List<ImRyzd> imRyzdList =imRyzdDao.findByEntity(BeanUtil.toBean(imHzryReq, ImRyzd.class));
		if(imRyzdList==null || imRyzdList.size()==0 ){
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0001");
		}else if(imRyzdList.size() == 1 ){
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0002");
		}
		ImHzry imHzry = imHzryDao.findByEntity(BeanUtil.toBean(imHzryReq, ImHzry.class)).get(0);
		Map<String, Object> brry = BeanUtil.beanToMap(imHzry);
		map.put("ZYH", zyh);// 住院号
		if (brry != null) {
			if (brry.get("brks") != null) {
				map.put("brks", Long.parseLong(brry.get("brks").toString()));// 病人科室
				map.put("srks", Long.parseLong(brry.get("brks").toString()));// 输入科室
				map.put("zxks", Long.parseLong(brry.get("brks").toString()));// 执行科室

			}
			if (brry.get("brbq") != null) {
				map.put("brbq", Long.parseLong(brry.get("brbq").toString()));// 病人病区
			}
			if (brry.get("brch") != null) {
				map.put("brch", brry.get("brch") + "");// 病人床号
			}
			if(brry.get("ryzd")== null || "".equals(brry.get("ryzd"))){
				throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00001");
			}
		}
		map.put("YPXH", 0);
		map.put("YPCD", (long) 0);
		map.put("XMLX", 10);
		map.put("YPLX", 0);// 药片类型
		map.put("TZFHBZ", 0);// 停嘱复核标志
		map.put("FHBZ", 0);
		map.put("YCJL", (double) 1);// 一次剂量
		map.put("YCSL", (double) 1);// 一次数量
		map.put("LSYZ", 1);// 临时医嘱
		map.put("MRCS", 1);
		map.put("MZCS", 0);
		map.put("JFBZ", 3);// 计费标志
		map.put("YPYF", (long) 0);
		map.put("SRCS", 1);// 首日次数
		if (cyfs != 5) {
			if(DateUtil.parse(cyrq, DatePattern.NORM_DATE_PATTERN).
					compareTo(DateUtil.parse(DateUtil.date().toString(), DatePattern.NORM_DATE_PATTERN)) == 0) {
				map.put("YZMC", "今日出院");// 药嘱名称
			}else {
				map.put("YZMC", "明日出院");
			}
			map.put("YDLB", "303");// 约定类别
		} else {
			map.put("YZMC","临床死亡");// 药嘱名称
			map.put("YDLB", "304");// 约定类别
		}
		map.put("YWID", Long.parseLong(body.get("JLXH").toString()));// 申请序号
		map.put("CZGH", userId);
		Map<String, Object> zyysmap = BeanUtil.beanToMap(imHzryDao.getById(zyh));
		if (zyysmap != null) {
			if (zyysmap.containsKey("zyys")) {
				if (zyysmap.get("zyys") != null) {
					map.put("YSGH", zyysmap.get("zyys") + "");// 开嘱医生
				}
			}
		}
//		List<Map<String, Object>> yzzhlist = cisHzyzDao.queryMaxYzzh(zyh);
//		if (yzzhlist.size() > 0) {
//			if (yzzhlist.get(0) !=null && yzzhlist.get(0).get("YZZH") != null) {
//				map.put("YZZH",
//						Long.parseLong(yzzhlist.get(0).get("YZZH") + "") + 1);// 开嘱医生
//			} else {
//				map.put("YZZH", 1L);
//			}
//		}
		map.put("YZZH", redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));
		map.put("KSSJ", DateUtil.parse(body.get("xcyrq") + "", 
				DatePattern.NORM_DATETIME_PATTERN).toTimestamp());// 开嘱时间
		map.put("JGID", jgid);// 机构ID
		map.put("SFJG", "0");
		map.put("YPDJ", (double) 0);
		map.put("SYBZ", 0);
		map.put("ZFPB", 0);
		map.put("YJZX", 0);
		map.put("YJXH", (long) 0);
		map.put("ZFBZ", 0);// 作废标志
		map.put("FYSX", 0);
		map.put("YEPB", 0);
		map.put("YFSB", (long) 0);
		map.put("LSYZ", 1);
		map.put("LSBZ", 0);
		map.put("YZPB", 0);
		map.put("JFBZ", 3);
		map.put("TPN", 0);
		map.put("ZFYP", 0);
//		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZYYSQY);
//		int zyysqy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());	
//		if (zyysqy == 1) {		
//		} else {
//			map.put("YSBZ", 0);
//		}
		map.put("YSBZ", 1);
		map.put("YSTJ", 1);
		map.put("PSPB", 0);
		map.put("SYPC", "st");
		CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(map, new CisHzyz(), true);
		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
		cisHzyz.setJlxh(jlxh);
		cisHzyzDao.insert(cisHzyz);
//			return null;
		
	}
	
	
	/**
	 * 获取病人过敏信息及入院诊断等信息
	 * @Title: doLoadPatientExInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param brid
	 * @param @param user
	 * @param @return    设定文件
	 * @return SkinPsjlQueryResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public SkinPsjlQueryResp doLoadPatientExInfo(Integer zyh, Integer brid, SysUser user) {
		Integer manageUnit = user.getHospitalId();
		SkinPsjlQueryResp skinPsjlQueryResp = new SkinPsjlQueryResp();
		try {
			List<SkinPsjlResp> skinPsjlRespList = getAllergicDrugsByEntity(brid, user);
			skinPsjlQueryResp.setGmywList(skinPsjlRespList);
			ImRcjl imRcjl = new ImRcjl();
			imRcjl.setZyh(zyh);
			imRcjl.setCzlx(-1);
			long l = imRcjlDao.findByEntityCount(imRcjl);
			skinPsjlQueryResp.setCyz(l);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00017");
		}
		return skinPsjlQueryResp;
	}
	
	/**
	 * 数据盒会诊报告查询
	 * @Title: doGetHzbgList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyhm
	 * @param @return    设定文件
	 * @return List<YsZyHzBgResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<YsZyHzBgResp> doGetHzbgList(String zyhm){
		List<YsZyHzBgResp> vreportinfoList = null;
		try {
			vreportinfoList = imHzryDao.queryHzbgList(zyhm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vreportinfoList;
	}
	
	/**
	 * 数据盒查询临时长期变更医嘱
	 * @Title: queryBqyzByDataBox
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzDataReq
	 * @param @return    设定文件
	 * @return List<CisHzyz>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyz> queryBqyzByDataBox(CisHzyzDataReq cisHzyzDataReq){
		return cisHzyzDao.queryBqyzByDataBox(cisHzyzDataReq);
	}
	
	
	/**
	 * 住院医生护士病人医嘱列表查询
	 * @Title: execute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzQueryReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<CisHzyzQueryResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyzQueryResp> execute(CisHzyzQueryApiReq cisHzyzQueryReq, SysUser user) {
		Integer zyh = cisHzyzQueryReq.getZyh();
		Integer manageUnit = user.getHospitalId();
		try {
			cisHzyzQueryReq.setJgid(manageUnit);
			// 将可以转成历史的医嘱转成历史医嘱
			if (zyh != null) {
				List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
				imZkjlSer.medicalAdviceToLs(dicsypcList, zyh);
			}
		
			String jzbz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getJzbz())) {
				jzbz = cisHzyzQueryReq.getJzbz();
			}
			String dybz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getDybz())) {
				dybz = cisHzyzQueryReq.getDybz();
			}
			String cqbz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getCqbz())) {
				cqbz = cisHzyzQueryReq.getCqbz();
			}
			String lsbz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getLsbz())) {			
				lsbz = cisHzyzQueryReq.getLsbz();
			}
			String ypbz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getYpbz())) {		
				ypbz = cisHzyzQueryReq.getYpbz();	
			}
			String xmbz = null;
			if (StrUtil.isNotBlank(cisHzyzQueryReq.getXmbz())) {	
				xmbz = cisHzyzQueryReq.getXmbz();
			}
			if(StrUtil.isBlankIfStr(cisHzyzQueryReq.getXmlx())) {
				boolean flagOne = "true".equals(jzbz) && "false".equals(dybz)
						&& ("true".equals(cqbz) || "true".equals(lsbz) || "true".equals(ypbz) 
								|| "true".equals(xmbz));
				boolean flagTwo = "true".equals(dybz) && "false".equals(jzbz)
						&& ("true".equals(cqbz) || "true".equals(lsbz) || "true".equals(ypbz) 
								|| "true".equals(xmbz));
				boolean flagThree = "true".equals(dybz) && "true".equals(jzbz) 
						&& ("true".equals(cqbz) || "true".equals(lsbz) || "true".equals(ypbz) 
								|| "true".equals(xmbz));
				if (flagOne) {
					cisHzyzQueryReq.setType(1);
				} else if ("true".equals(jzbz) && "false".equals(dybz)
						&& "false".equals(cqbz) && "false".equals(lsbz)
						&& "false".equals(ypbz) && "false".equals(xmbz)) {
					cisHzyzQueryReq.setType(2);
				} else if (flagTwo) {
					cisHzyzQueryReq.setType(3);
				} else if ("true".equals(dybz) && "false".equals(jzbz)
						&& "false".equals(cqbz) && "false".equals(lsbz)
						&& "false".equals(ypbz) && "false".equals(xmbz)) {
					cisHzyzQueryReq.setType(4);
				} else if (flagThree) {
					cisHzyzQueryReq.setType(5);
				} else if ("true".equals(dybz) && "true".equals(jzbz)
						&& "false".equals(cqbz) && "false".equals(lsbz)
						&& "false".equals(ypbz) && "false".equals(xmbz)) {
					cisHzyzQueryReq.setType(6);
				} else if ("false".equals(dybz) && "false".equals(jzbz)
						&& "false".equals(cqbz) && "false".equals(lsbz)
						&& "false".equals(ypbz) && "false".equals(xmbz)) {
					cisHzyzQueryReq.setType(5);
				} else {
					cisHzyzQueryReq.setType(7);
				}
			}
			
			boolean ypFlag = ("true".equals(ypbz) && "true".equals(xmbz)) || ("false".equals(ypbz) && "false".equals(xmbz));
			if(ypFlag) {
				cisHzyzQueryReq.setYplx(1);
			}else if("true".equals(xmbz)) {
				cisHzyzQueryReq.setYplx(2);
			}else if("true".equals(ypbz)) {
				cisHzyzQueryReq.setYplx(3);
			}
			
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manageUnit, SysXtcsCsmcCts.CXYZTS);
			if("true".equals(cisHzyzQueryReq.getIsTime())) {
				cisHzyzQueryReq.setDays(ObjectToTypes.parseInt(sysXtcs.getCsz()));
			}
			
			List<CisHzyzQueryResp> cisHzyzQueryRespList = cisHzyzDao.queryWardOrderList(cisHzyzQueryReq);
			List<CisHzyzQueryResp> cisHzyzList = new ArrayList<CisHzyzQueryResp>();
			//显示组套名称
			Map<String, Object> ztyzjlxhMap = new HashMap<String, Object>(16);
			for (CisHzyzQueryResp cisHzyzQuery : cisHzyzQueryRespList) {
				if (cisHzyzQuery.getZtbz() != null && "1".equals(ObjectToTypes.parseString(cisHzyzQuery.getZtbz()))) {
                    if (ztyzjlxhMap.containsKey(ObjectToTypes.parseString(cisHzyzQuery.getZtyzjlxh()))) {
                        continue;
                    }
                    CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(cisHzyzQuery.getZtyzjlxh());
                    Map<String, Object> record = BUHISUtil.caseInsensitiveMap(cisHzyzZt);
                    cisHzyzQuery = BeanUtil.fillBeanWithMapIgnoreCase(record, new CisHzyzQueryResp(), true);
                    ztyzjlxhMap.put(ObjectToTypes.parseString(cisHzyzQuery.getJlxh()), cisHzyzQuery);
                }
				
				if (cisHzyzQuery.getXmlx().intValue() >= 4) {
					cisHzyzQuery.setJldw("");
				}
				cisHzyzList.add(cisHzyzQuery);
			}
			return cisHzyzList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00018");
		}

	}
	
	/**
	 * 是否为过敏药物校验
	 * @Title: doLoadDetailsInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param skinPsjlCheckReq
	 * @param @return    设定文件
	 * @return SkinPsjlCheckResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public SkinPsjlCheckResp doLoadDetailsInfo(SkinPsjlCheckReq skinPsjlCheckReq) {
		SkinPsjlCheckResp skinPsjlCheckResp = new SkinPsjlCheckResp();
		Integer ypxh = skinPsjlCheckReq.getYpxh();
		Integer yplx = skinPsjlCheckReq.getYplx();
		Integer fygb = cisHzyzSer.getfygb(yplx, ypxh);
		Integer brxz = skinPsjlCheckReq.getBrxz();
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("BRXZ", brxz);
		params.put("FYGB", fygb);
		params.put("TYPE", yplx);
		params.put("FYXH", ypxh);
		Map<String, Object> zfblMap = cisHzyzSer.getPayProportion(params);	
		skinPsjlCheckResp.setZfbl((BigDecimal) zfblMap.get("ZFBL"));
		if (yplx > 0) {
			try {
				SkinPsjl skinPsjl = new SkinPsjl();
				skinPsjl.setPsjg(1);
				skinPsjl.setBrid(skinPsjlCheckReq.getBrid());
				skinPsjl.setYpxh(ypxh);
				List<SkinPsjl> skinPsjlList = skinPsjlDao.findByEntity(skinPsjl);
				if (skinPsjlList.size() == 0) {
					skinPsjlCheckResp.setAllergy(false);
				} else {
					skinPsjlCheckResp.setAllergy(true);
					skinPsjlCheckResp.setBlfy(skinPsjlList.get(0).getBlfy());
				}
			} catch (Exception e) {
				throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00019");
			}
		}
		return skinPsjlCheckResp;
	}
	
	/**
	 * 查询出院病人列表
	 * @Title: queryDischargedPatients
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imHzryQueryReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<ImHzryDctworkResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<ImHzryDctworkResp> queryDischargedPatients(ImHzryQueryReq imHzryQueryReq, SysUser user){
		Integer jgid = user.getHospitalId();
		imHzryQueryReq.setJgid(jgid);
		List<ImHzryDctworkResp> list = imHzryDao.queryDischargedPatients(imHzryQueryReq);
		List<Integer> zyhList = new ArrayList<Integer>();
		for (ImHzryDctworkResp imHzryDctworkResp : list) {
            zyhList.add(imHzryDctworkResp.getZyh());
            Map<String, Object> ret = BUHISUtil.getPersonAge(
                    imHzryDctworkResp.getCsny(), null);
            imHzryDctworkResp.setAge(ObjectToTypes.parseString(ret.get("age")));
            Integer cypb = imHzryDctworkResp.getCypb();
            Integer zyh = imHzryDctworkResp.getCypb();
            if (cypb.intValue() == 1) {
                continue;
            }
            if (cypb.intValue() == 8) {
                continue;
            }
			ImRcjl imRcjl = new ImRcjl();
			imRcjl.setJgid(jgid);
			imRcjl.setZyh(zyh);
			imRcjl.setCzlx(-1);
			long count = imRcjlDao.findByEntityCount(imRcjl);
			if (count > 0) {
				imHzryDctworkResp.setCypb(102);
			}
		}
		List<BasySfgdResp> respList = basyService.basySfgd(jgid, zyhList);
		if(CollectionUtils.isNotEmpty(respList)) {
			for (ImHzryDctworkResp imHzryDctworkResp : list) {
				for(BasySfgdResp basy : respList) {
					if(imHzryDctworkResp.getZyh().equals(basy.getZyh()) && "1".equals(basy.getSfgd()) ) {
						imHzryDctworkResp.setCypb(10);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据处置开的项目去取对应的价格并插入到物资库存表(住院)
	 * @Title: setSuppliesZY
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param manaUnitId
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
//	public void setSuppliesZy(Integer manaUnitId, Long jlxh) {
//		Map<String, Object> parameters = new HashMap<String, Object>(16);
//		Map<String, Object> parameterskfxh = new HashMap<String, Object>(16);
//		Map<String, Object> parametersksdm = new HashMap<String, Object>(16);
//		parameters.put("JLXH", jlxh);
//		parameters.put("JGID", manaUnitId);
//		Map<String, Object> lisFYXX = cisHzyzDao.queryWardMaterialsInfo(parameters);
//
//		parametersksdm.put("JGID", manaUnitId);
//		if (lisFYXX != null) {
//			parametersksdm.put("ZYH", Long.parseLong(lisFYXX.get("ZYH") + ""));
//		}
//		Integer wardId = 0;
//		String wardName = null;
//		String zyhm = null;
//		Map<String, Object> ksdmMap = imHzryDao.queryOfficeInfo(parametersksdm);
//		if (ksdmMap != null) {
//			if (ksdmMap.get("BRBQ") != null) {
//				wardId = ObjectToTypes.parseInt(ksdmMap.get("BRBQ"));
//			}
//			if (ksdmMap.get("KSMC") != null) {
//				wardName = ksdmMap.get("KSMC") + "";
//			}
//			if (ksdmMap.get("ZYHM") != null) {
//				zyhm = ksdmMap.get("ZYHM") + "";
//			}
//		}
//		parameterskfxh.put("ejkf", wardId);
//		Map<String, Object> kfxhMap = wlKfdzDao.findKfxh(parameterskfxh);
//		int kfxh = 0;
//		if (kfxhMap != null) {
//			if (kfxhMap.get("KFXH") != null) {
//				kfxh = Integer.parseInt(kfxhMap.get("KFXH") + "");
//			}
//		} else {
//			throw new RuntimeException("当前科室或病区没有对应的物资库房！");
//		}
//		if (lisFYXX != null) {
//			double czsl = Double.parseDouble(lisFYXX.get("CZSL") + "");
//			Long sbxh = Long.parseLong(lisFYXX.get("SBXH") + "");
//			Long fyxh = Long.parseLong(lisFYXX.get("FYXH") + "");
//			Long brid = Long.parseLong(lisFYXX.get("BRID") + "");
//			String brxm = lisFYXX.get("BRXM") + "";
//			String jgid = lisFYXX.get("JGID") + "";
//			int jgbz = 0;
//			if (lisFYXX.get("JGBZ") != null) {
//				jgbz = Integer.parseInt(lisFYXX.get("JGBZ") + "");
//			}
//			double bzjg = 0;
//			if (lisFYXX.get("BZJG") != null) {
//				bzjg = Double.parseDouble(lisFYXX.get("BZJG") + "");
//			}
//			setSuppliesJg(sbxh, fyxh, kfxh, czsl, brid, "2", wardId, brxm, wardName, -2, jgid, jgbz, bzjg, zyhm);
//		}
//	}
	
	/**
	 * 根据处置开的项目去取对应的价格本计算金额
	 * @Title: setSuppliesJg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sbxh
	 * @param @param fyxh
	 * @param @param kfxh
	 * @param @param czsl
	 * @param @param brid
	 * @param @param brly
	 * @param @param ksdm
	 * @param @param brxm
	 * @param @param ksmc
	 * @param @param ztbz
	 * @param @param jgid
	 * @param @param jgbz
	 * @param @param bzjg
	 * @param @param zyhm    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
//	public void setSuppliesJg(Long sbxh, Long fyxh, int kfxh, double czsl, Long brid, String brly, Integer ksdm,
//			String brxm, String ksmc, int ztbz, String jgid, int jgbz, double bzjg, String zyhm) {
//		Map<String, Object> parameterswzxh = new HashMap<String, Object>(16);
//		Map<String, Object> parameterswzjg = new HashMap<String, Object>(16);
//		Map<String, Object> parametersupd = new HashMap<String, Object>(16);
//		Map<String, Object> parametersykslupd = new HashMap<String, Object>(16);
//		parameterswzxh.put("FYXH", fyxh);
//		parameterswzxh.put("JGID", jgid);
//		parametersupd.put("SBXH", sbxh);
//
//		// 根据fyxh取对应的wzxh
//		List<Map<String, Object>> lisWZXH = feeXmzxksDao.findSuppliesInfo(parameterswzxh);
//
//		double wzjg = 0.00;
//		double czje = 0.00;
//		for (int i = 0; i < lisWZXH.size(); i++) {
//			double wzslall = czsl * Double.parseDouble(lisWZXH.get(i).get("WZSL") + "");// 取到第一个物资的实际数量
//			parameterswzjg.put("WZXH", Long.parseLong(lisWZXH.get(i).get("WZXH") + ""));
//			parameterswzjg.put("JGID", jgid);
//			parameterswzjg.put("KFXH", kfxh);
//
//			List<Map<String, Object>> lisWZKC = wlWzkcDao.findSuppliesAmount(parameterswzjg);
//
//			for (int j = 0; j < lisWZKC.size(); j++) {// 第一个去做的金额
//				double wzsl = Double.parseDouble(lisWZKC.get(j).get("WZSL") + "");
//				double yksl = Double.parseDouble(lisWZKC.get(j).get("YKSL") + "");
//				long kcxh = Long.parseLong(lisWZKC.get(j).get("KCXH") + "");
//				parametersykslupd.put("KCXH", kcxh);
//				parametersykslupd.put("KFXH", kfxh);
//				parametersykslupd.put("JGID", jgid);
//				long wzxhs = Long.parseLong(lisWZKC.get(j).get("WZXH") + "");
//				if (wzslall <= (wzsl - yksl)) {
//					if (jgbz == 1) {
//						wzjg = Double.parseDouble(lisWZKC.get(j).get("WZJG") + "");
//					} else {
//						wzjg = bzjg;
//					}
//					czje += wzslall * wzjg;
//					parametersykslupd.put("YKSL", yksl + wzslall);
//
//					wlWzkcDao.updateyksl(parametersykslupd);
//
//					Map<String, Object> parametersyxhmxsave = new HashMap<String, Object>(16);
//					parametersyxhmxsave.put("BRHM", zyhm);
//					parametersyxhmxsave.put("BRXM", brxm);
//					parametersyxhmxsave.put("WZSL", wzslall);
//					parametersyxhmxsave.put("KSMC", ksmc);
//					parametersyxhmxsave.put("BRID", brid);
//					parametersyxhmxsave.put("BRLY", brly);
//					parametersyxhmxsave.put("XHRQ", new Date());
//					parametersyxhmxsave.put("KSDM", ksdm);
//					parametersyxhmxsave.put("JGID", jgid);
//					parametersyxhmxsave.put("KFXH", kfxh);
//					parametersyxhmxsave.put("WZXH", wzxhs);
//					parametersyxhmxsave.put("ZTBZ", ztbz);
//					parametersyxhmxsave.put("MZXH", sbxh);
//					parametersyxhmxsave.put("KCXH", kcxh);
//
//					WlXhmx wlxhmx = BeanUtil.fillBeanWithMapIgnoreCase(parametersyxhmxsave, new WlXhmx(), true);
//					wlxhmx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.WL_WZKC));
//
//					wlXhmxDao.insert(wlxhmx);
//					break;
//				} else if (wzslall > (wzsl - yksl)) {
//					wzslall = wzslall - (wzsl - yksl);
//					parametersykslupd.put("YKSL", yksl + (wzsl - yksl));
//					wlWzkcDao.updateyksl(parametersykslupd);
//					if ((wzsl - yksl) > 0) {
//						Map<String, Object> parametersyxhmxsave = new HashMap<String, Object>(16);
//						parametersyxhmxsave.put("BRHM", zyhm);
//						parametersyxhmxsave.put("BRXM", brxm);
//						parametersyxhmxsave.put("WZSL", (wzsl - yksl));
//						parametersyxhmxsave.put("KSMC", ksmc);
//						parametersyxhmxsave.put("BRID", brid);
//						parametersyxhmxsave.put("BRLY", brly);
//						parametersyxhmxsave.put("XHRQ", new Date());
//						parametersyxhmxsave.put("KSDM", ksdm);
//						parametersyxhmxsave.put("JGID", jgid);
//						parametersyxhmxsave.put("KFXH", kfxh);
//						parametersyxhmxsave.put("WZXH", wzxhs);
//						parametersyxhmxsave.put("ZTBZ", ztbz);
//						parametersyxhmxsave.put("MZXH", sbxh);
//						parametersyxhmxsave.put("KCXH", kcxh);
//
//						WlXhmx wlxhmx = BeanUtil.fillBeanWithMapIgnoreCase(parametersyxhmxsave, new WlXhmx(), true);
//						wlxhmx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.WL_WZKC));
//
//						wlXhmxDao.insert(wlxhmx);
//					}
//					if (jgbz == 1) {
//						wzjg = Double.parseDouble(lisWZKC.get(j).get("WZJG") + "");
//					} else {
//						wzjg = bzjg;
//					}
//					czje += (wzsl - yksl) * wzjg;
//				}
//			}
//		}
//		double wzdj = czje / czsl;
//		if (wzdj != 0.00 && czje != 0.00) {
//			if ("1".equals(brly)) {
//				parametersupd.put("YLDJ", wzdj);
//				parametersupd.put("HJJE", czje);
//				// update OP_YJCF02 set YLDJ=:YLDJ,HJJE=:HJJE where SBXH=:SBXH
//				opYjcf02Dao.updateYldjAndHjje(parametersupd);
//			} else if ("2".equals(brly)) {
//				parametersupd.put("YPDJ", wzdj);
//				// update CIS_HZYZ set YPDJ=:YPDJ where JLXH=:SBXH
//				opYjcf02Dao.updateYpdj(parametersupd);
//			}
//		}
//	}
	
	/**
	 * getTotalAmount(原gf_zy_gxmk_getjkhj)
	 * @Title: getTotalAmount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param brxxMap
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getTotalAmount(Map<String, Object> brxxMap) {
		brxxMap.put("JKHJ", 0);
		try {
			Integer zyh = ObjectToTypes.parseInt(brxxMap.get("ZYH"));
			if (Integer.parseInt(brxxMap.get("JSLX") + "") == 0 || Integer.parseInt(brxxMap.get("JSLX") + "") == 5) {
				BigDecimal jkhj = imTbkkDao.queryTotalPayment(zyh, null);
				if (jkhj != null) {
					brxxMap.put("JKHJ", jkhj);
				}
			} else if (Integer.parseInt(brxxMap.get("JSLX") + "") < 0 || Integer.parseInt(brxxMap.get("JSLX") + "") == 10) {
				BigDecimal zyjkhj = imZyjsDao.queryTotalPaymentHospital(zyh, ObjectToTypes.parseInt(brxxMap.get("JSCS")));
				if (zyjkhj != null) {
					brxxMap.put("JKHJ", zyjkhj);
				}
			}
			return brxxMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00013");
		}
	}
	
	/**
	 * 获取自负金额
	 * @Title: getSelfFinancingTotal(原gf_zy_gxmk_getzfhj)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param BRXX
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getSelfFinancingTotal(Map<String, Object> brxxMap) {
		brxxMap.put("ZFHJ", 0);
		brxxMap.put("YL_ZFHJ", 0);
		brxxMap.put("YP_ZFHJ", 0);
		try {
			Integer zyh = ObjectToTypes.parseInt(brxxMap.get("ZYH"));
			if (Integer.parseInt(brxxMap.get("JSLX") + "") == 0 || Integer.parseInt(brxxMap.get("JSLX") + "") == 5) {
				BigDecimal zfhj = imFeeFymxDao.queryTotalSelf(zyh, null);
				if (zfhj != null) {
					brxxMap.put("ZFHJ", zfhj);
				}
				BigDecimal zfhjCost = imFeeFymxDao.queryTotalSelfCost(zyh, null);
				if (zfhjCost != null) {
					brxxMap.put("YL_ZFHJ", zfhjCost);
				}
				BigDecimal zfhjNotCost = imFeeFymxDao.queryTotalSelfNotCost(zyh, null);
				if (zfhjNotCost != null) {
					brxxMap.put("YP_ZFHJ", zfhjNotCost);
				}
			} else if (Integer.parseInt(brxxMap.get("JSLX") + "") < 0 || Integer.parseInt(brxxMap.get("JSLX") + "") == 10) {
				Integer jscs = ObjectToTypes.parseInt(brxxMap.get("JSCS"));
				BigDecimal zfhj = imZyjsDao.queryTotalSelfHospital(zyh, jscs);
				if (zfhj != null) {
					brxxMap.put("ZFHJ", zfhj);
				}
				BigDecimal zfhjCost = imFeeFymxDao.queryTotalSelfCost(zyh, jscs);
				if (zfhjCost != null) {
					brxxMap.put("YL_ZFHJ", zfhjCost);
				}
				BigDecimal zfhjNotCost = imFeeFymxDao.queryTotalSelfNotCost(zyh, jscs);
				if (zfhjNotCost != null) {
					brxxMap.put("YP_ZFHJ", zfhjNotCost);
				}
			}
			return brxxMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00014");
		}
	}
	
	
	/**
	 * 设置出院日期
	 * @Title: dischargeDateSetting(原来uf_cyrq_set)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @param manaUnitId
	 * @param @param userId    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void dischargeDateSetting(Map<String, Object> parameters, Integer manaUnitId, Integer userId) {
		// 获取取病人的病人科室和病人病区
		Object xcyrq = parameters.get("xcyrq");// 当前出院时间
		Integer zyh = ObjectToTypes.parseInt(parameters.get("zyh"));// 住院号
		Object cyfsObj = parameters.get("cyfs");// 出院方式
		String bzxx = ObjectToTypes.parseString(parameters.get("bzxx"));// 备注信息
		String sfxx = ObjectToTypes.parseString(parameters.get("sf"));// 备注信息
		Integer cyfs = Integer.parseInt(cyfsObj == null ? "0" : cyfsObj.toString());
		if (xcyrq != null) {
            xcyrq = BUHISUtil.toDate(xcyrq.toString());
        }

		ImRcjl imRcjl = new ImRcjl();
		imRcjl.setZyh(zyh);
		imRcjl.setJgid(manaUnitId);
		imRcjl.setCzlx(1);

		Long l = imRcjlDao.findByEntityCount(imRcjl);
		if (l == 0) {
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00010");
		}
		imRcjl.setBqpb(0);
		ImRcjl rcjl = imRcjlDao.queryClinicalDate(imRcjl);

        if (rcjl != null && xcyrq != null &&
                BUHISUtil.dateCompare(new Date(rcjl.getLcrq().getTime()), (Date) xcyrq) > 0) {
            throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00011");
        }

		ImHzry imHzry = new ImHzry();
		imHzry.setJgid(manaUnitId);
		imHzry.setZyh(zyh);
		ImHzry brry = imHzryDao.queryZyKsInfo(imHzry);

		imRcjl.setCzlx(-1);
		ImRcjl yrcjl = imRcjlDao.queryClinicalDate(imRcjl);
		if (brry.getCypb() != 0) {
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00012");
		}
		boolean cyrqFlag = xcyrq == null && (yrcjl == null || yrcjl.getLcrq() == null);
		if (cyrqFlag) {
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00013");
		}
		if (xcyrq == null) {
			imRcjl.setBqpb(1);
			ImRcjl jlxhMap = imRcjlDao.queryClinicalDate(imRcjl);
			CisHzyz cisHzyz = new CisHzyz();
			if (jlxhMap != null) {
				if (jlxhMap.getJlxh() != null) {
					cisHzyz.setYwid(jlxhMap.getJlxh());
					cisHzyz.setJgid(manaUnitId);
					cisHzyzDao.removeByEntity(cisHzyz);
				}
			}
			ImRcjl imRcjlNew = new ImRcjl();
			imRcjlNew.setZyh(zyh);
			imRcjlNew.setJgid(manaUnitId);
			imRcjlNew.setCzlx(-1);
			imRcjlDao.removeByEntity(imRcjlNew);

			ImCyjl imCyjl = new ImCyjl();
			imCyjl.setJgid(manaUnitId);
			imCyjl.setCzrq(DateUtil.beginOfDay(new Date()).toTimestamp());
			imCyjl.setCysj(null);
			imCyjl.setCzlx(2);
			imCyjl.setZyh(zyh);
			imCyjl.setCzr(userId);
			imCyjl.setCyfs(0);
			imCyjl.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CYJL));
			imCyjlDao.insert(imCyjl);
		} else {
            if (yrcjl != null && (BUHISUtil.toString(new Date(rcjl.getLcrq().getTime()), DatePattern.NORM_DATETIME_PATTERN))
                    .equals(BUHISUtil.toString((Date) xcyrq, DatePattern.NORM_DATETIME_PATTERN))) {
                imRcjl.setCzlx(-1);
                imRcjl.setBqpb(1);
                Map<String, Object> zyinfo = new HashMap<String, Object>(16);
                zyinfo.put("CYFS", cyfs);
                zyinfo.put("BZXX", bzxx);
                zyinfo.put("JGID", manaUnitId);
                zyinfo.put("ZYH", zyh);
                ImRcjl jlxhMap = imRcjlDao.queryClinicalDate(imRcjl);
                CisHzyz cisHzyz = new CisHzyz();
                if (jlxhMap != null) {
					if (jlxhMap.getJlxh() != null) {
						cisHzyz.setYwid(jlxhMap.getJlxh());
						cisHzyz.setJgid(manaUnitId);
						cisHzyzDao.removeByEntity(cisHzyz);
					}
				}
				imRcjlDao.updateCyfsStatus(zyinfo);
				return;
			}

			if (yrcjl == null || yrcjl.getLcrq() == null) {
				ImHzry imHzryCy = new ImHzry();
				imHzryCy.setCyrq(new Timestamp(((Date) xcyrq).getTime()));
                imHzryCy.setCyfs(cyfs);
                imHzryCy.setZyh(zyh);
                imHzryCy.setJgid(manaUnitId);
                imHzryDao.updateZyCyfsStatus(imHzry);

                Map<String, Object> ywcl = new HashMap<String, Object>(16);
                ywcl.put("ZYH", zyh);
                ywcl.put("JGID", manaUnitId);
                ywcl.put("CZLX", -1);
                ywcl.put("BQPB", 0);
                ywcl.put("RYRQ", BUHISUtil.toString((Date) xcyrq, DatePattern.NORM_DATETIME_PATTERN));
                ywcl.put("BRKS", brry.getBrks());
                ywcl.put("JYXX", bzxx);
                ywcl.put("CYFS", cyfs);
                ywcl.put("SFXX", sfxx);
                imZkjlSer.checkAdmissionOrDischarge(ywcl, manaUnitId);
                ywcl.put("BQPB", 1);
                ywcl.put("BRKS", brry.getBrbq());
                imZkjlSer.checkAdmissionOrDischarge(ywcl, manaUnitId);
                parameters.put("JLXH", ywcl.get("JLXH") + "");
            } else {
				ImRcjl imRcjlCy = new ImRcjl();
				imRcjlCy.setLcrq(new Timestamp(((Date) xcyrq).getTime()));
				imRcjlCy.setCyfs(cyfs);
				imRcjlCy.setZyh(zyh);
				imRcjlCy.setJgid(manaUnitId);
				imRcjlCy.setCzrq(DateUtil.date().toTimestamp());
				imRcjlCy.setBzxx(ObjectToTypes.parseString(bzxx));
				imRcjlCy.setSf(ObjectToTypes.parseString(sfxx));
				imRcjlDao.updateImRcjlCyfsStatus(imRcjlCy);
				imRcjl.setCzlx(-1);
				imRcjl.setBqpb(1);
				ImRcjl jlxhMap = imRcjlDao.queryClinicalDate(imRcjl);
				CisHzyz cisHzyz = new CisHzyz();
				if (jlxhMap != null) {
					if (jlxhMap.getJlxh() != null) {
						cisHzyz.setYwid(jlxhMap.getJlxh());
						cisHzyz.setJgid(manaUnitId);
						cisHzyzDao.removeByEntity(cisHzyz);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * 医嘱变动发送消息
	 * @Title: sendAdviceChangeMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void sendAdviceChangeMessage(Map<String, Object> map, SysUser user) {
		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_YZMESS);
		WaitProcessMessage<CisHzyzMessDto> wait = new WaitProcessMessage<CisHzyzMessDto>();
		wait.setTitle("医嘱变动");
		List<LineContentDto> dtoList = new ArrayList<LineContentDto>();
		LineContentDto dto = new LineContentDto();
		dto.setLabel("床号");
		dto.setContent(ObjectToTypes.parseString(map.get("BRCH")));
		dtoList.add(dto);
		dto = new LineContentDto();
		dto.setLabel("患者");
		dto.setContent(ObjectToTypes.parseString(map.get("BRXM")));
		dtoList.add(dto);
		dto = new LineContentDto();
		dto.setContent(ObjectToTypes.parseString(map.get("XB")));
		dtoList.add(dto);
		dto = new LineContentDto();
		dto.setContent(ObjectToTypes.parseString(map.get("OFFICENAME")));
		dtoList.add(dto);
		wait.setContents(dtoList);
		wait.setData(new CisHzyzMessDto(ObjectToTypes.parseInt(map.get("ZYH")), 
				ObjectToTypes.parseString(map.get("ZYHM")), jlxh));
		wait.setRelativePath("im/nursePatientHomePage");
		List<Integer> userIdList = new ArrayList<Integer>();
		SysYwqxkz sysYwqxkz = new SysYwqxkz();
		sysYwqxkz.setKsdm(ObjectToTypes.parseInt(map.get("BRBQ")));
		sysYwqxkz.setYwlb(1);
		sysYwqxkz.setJgid(user.getHospitalId());
		List<SysYwqxkz> qxkzList = sysYwqxkzService.findByEntity(sysYwqxkz);
		if(CollectionUtils.isNotEmpty(qxkzList)) {
			for(SysYwqxkz qxkz : qxkzList) {
				if(!user.getUserId().equals(qxkz.getYgdm())) {			
					userIdList.add(qxkz.getYgdm());
				}
			}
			String uuid = rabbit.sendAlertMsg("imsMedicalChange", wait, MessageLevelType.low, 
			TargetConfig.toSystemUser(SystemTypeEnum.zyhsz, userIdList, CoverageTypeEnum.onlineAndOffline), true);	
			
			CisYzmess mess = new CisYzmess();
			mess.setZyh(ObjectToTypes.parseInt(map.get("ZYH")));
			mess.setFssj(DateUtil.date().toTimestamp());
			mess.setZt(0);
			mess.setJgid(user.getHospitalId());
			mess.setMessid(uuid);
			mess.setFsrdm(user.getUserId());
			mess.setJlxh(jlxh);
			cisYzmessDao.insert(mess);
		}
	}
	
	/**
	 * 获取出院证打印数据
	 * @Title: getDischargeCertificatePrintParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param user
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getDischargeCertificatePrintParameters(Integer zyh, SysUser user) {
        ImHzry imHzry = new ImHzry();
        imHzry.setZyh(zyh);
        imHzry.setJgid(user.getHospitalId());
        ImHzryLeaveHospitalResp resp = imHzryDao.queryDischargeCertificateInfo(imHzry);
        Map<String, Object> map = BUHISUtil.caseInsensitiveMap(resp);
        map.put("TITLE", user.getHospitalName() + "出院证");
        map.put("KSMC", zyDicSer.getBrksDic(user.getHospitalId()).get(resp.getBrks()));
        map.put("BQMC", zyDicSer.getBrbqDic(user.getHospitalId()).get(resp.getBrbq()));
        map.put("BRXB", resp.getBrxb() == 1 ? "男" : "女");
        map.put("YEAR", DateUtil.year(resp.getCsrq()));
        map.put("MONTH", DateUtil.month(resp.getCsrq()) + 1);
        map.put("DAY", DateUtil.dayOfMonth(resp.getCsrq()));
        if (resp.getCyfs() != null) {
            if (resp.getRyrq() != null && resp.getCyrq() != null) {
                String ryrq = DateUtil.format(resp.getRyrq(), DatePattern.NORM_DATETIME_PATTERN);
                String cyrq = DateUtil.format(resp.getCyrq(), DatePattern.NORM_DATETIME_PATTERN);
                map.put("DAYS", BUHISUtil.loadDischargeDays(ryrq, cyrq));
                map.put("RYRQ", DateUtil.format(resp.getRyrq(), "yyyy年MM月dd日HH时mm分"));
                map.put("CYRQ", DateUtil.format(resp.getCyrq(), "yyyy年MM月dd日HH时mm分"));
            }
		}	
		List<ImRyzdResp> imRyzdRespList = imRyzdDao.getDischargeDiagnosis(zyh);
		if(CollectionUtils.isNotEmpty(imRyzdRespList)) {
			for(int i = 0; i < imRyzdRespList.size(); i++) {
				map.put("CYZD" + i, imRyzdRespList.get(i).getJbmc());
			}
		}
		return map;
	}


	/**
	 * 中草药新增或修改
	 * @Title: saveOrModifyHerbalMedicine
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzSaveReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	@Locked(value = "im_save_HerbalMedicine_#[cisHzyzHerbalReq.zyh]",block = 1)
	public Integer saveOrModifyHerbalMedicine(CisHzyzHerbalReq cisHzyzHerbalReq, SysUser user) {
		List<CisHzyzHerbalBody> reqList = cisHzyzHerbalReq.getCisHzyzReqList();
		List<CisHzyzHerbalBody> fjReqList = cisHzyzHerbalReq.getCisHzyzReqFjList();
		List<Integer> yzzhList = reqList.stream().filter(o -> OperationTypeEnum.remove.getCode().equals(o.getOpStatus()) || 
				OperationTypeEnum.update.getCode().equals(o.getOpStatus())).map(e -> e.getYzzh()).collect(Collectors.toList());
		StringBuffer str = new StringBuffer();
		BigDecimal ztdj = BigDecimal.ZERO;
		for(CisHzyzHerbalBody req : reqList) {
			if(!OperationTypeEnum.remove.getCode().equals(req.getOpStatus())) {
				str.append(req.getYzmc()).append("+");
				ztdj = ztdj.add(req.getYpdj().multiply(req.getYcsl()));
			}
		}
		Integer ztjlxh = null;
		if(StrUtil.isBlankIfStr(cisHzyzHerbalReq.getZtjlxh())) {
			CisHzyzZt cisHzyzZt = BeanUtil.toBean(cisHzyzHerbalReq, CisHzyzZt.class);
			cisHzyzZt.setJgid(user.getHospitalId());
			cisHzyzZt.setYzmc(str.deleteCharAt(str.length() - 1).toString());
			cisHzyzZt.setYpxh(0);
			cisHzyzZt.setYpcd(0);
			cisHzyzZt.setXmlx(1);
			cisHzyzZt.setYplx(3);
			cisHzyzZt.setMzcs(0);
			cisHzyzZt.setTzsj(cisHzyzHerbalReq.getKssj());
			cisHzyzZt.setYpdj(ztdj);
			cisHzyzZt.setYsgh(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setTzys(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setCzgh(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setSybz(0);
			cisHzyzZt.setSrks(cisHzyzHerbalReq.getBrks());
			cisHzyzZt.setZfpb(0);
			cisHzyzZt.setYjzx(0);
			cisHzyzZt.setYjxh(0);
			cisHzyzZt.setZxks(cisHzyzHerbalReq.getBrks());
			cisHzyzZt.setYzzh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ_ZT +".yzzh"));
			cisHzyzZt.setFysx(0);
			cisHzyzZt.setYepb(0);
			cisHzyzZt.setYfsb(reqList.get(0).getYfsb());
			cisHzyzZt.setLsyz(1);
			cisHzyzZt.setLsbz(0);
			cisHzyzZt.setYzpb(0);
			cisHzyzZt.setJfbz(2);
			cisHzyzZt.setTpn(0);
			cisHzyzZt.setYsbz(1);
			cisHzyzZt.setYstj(0);
			cisHzyzZt.setYzpx(1);
			cisHzyzZt.setZfbz(0);
			cisHzyzZt.setSrcs(0);
			cisHzyzZt.setSfjg(BigDecimal.ZERO);
			cisHzyzZt.setFhbz(0);
			cisHzyzZt.setTzfhbz(0);
			cisHzyzZt.setPspb(0);
			cisHzyzZt.setYpzs(1);
			cisHzyzZt.setJz(reqList.get(0).getJz());
			cisHzyzZt.setZfyp(0);
			cisHzyzZt.setZtbz(1);
			cisHzyzZt.setYzlx(5);
			ztjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
			cisHzyzZt.setJlxh(ztjlxh);
			cisHzyzZtDao.insert(cisHzyzZt);
		}else {
			ztjlxh = cisHzyzHerbalReq.getZtjlxh();
			CisHzyzZt cisHzyzZt = cisHzyzZtDao.getById(ztjlxh);
			BeanUtil.copyProperties(cisHzyzHerbalReq, cisHzyzZt,
					CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
			cisHzyzZt.setTzsj(cisHzyzHerbalReq.getKssj());
			cisHzyzZt.setYzmc(str.deleteCharAt(str.length() - 1).toString());
			cisHzyzZt.setYpdj(ztdj);
			cisHzyzZtDao.update(cisHzyzZt);
		}
		
		
		Integer yzzh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh");
		for(CisHzyzHerbalBody req : reqList) {
			String opStatus = req.getOpStatus();
			CisHzyz cisHzyz = BeanUtil.toBean(req, CisHzyz.class);
			cisHzyz.setJgid(user.getHospitalId());
			cisHzyz.setSypc(cisHzyzHerbalReq.getSypc());
			cisHzyz.setYzzxsj(cisHzyzHerbalReq.getYzzxsj());
			cisHzyz.setYpyf(cisHzyzHerbalReq.getYpyf());
			cisHzyz.setCfts(cisHzyzHerbalReq.getCfts());
			cisHzyz.setKssj(cisHzyzHerbalReq.getKssj());
			cisHzyz.setTzsj(cisHzyzHerbalReq.getKssj());
			cisHzyz.setZyh(cisHzyzHerbalReq.getZyh());
			cisHzyz.setBrks(cisHzyzHerbalReq.getBrks());
			cisHzyz.setBrbq(cisHzyzHerbalReq.getBrbq());
			cisHzyz.setBrch(cisHzyzHerbalReq.getBrch());
			cisHzyz.setBzxx(cisHzyzHerbalReq.getBzxx());
			cisHzyz.setYfdw(req.getJldw());
			cisHzyz.setYzlx(5);
			cisHzyz.setZtycjl(cisHzyzHerbalReq.getYcjl());
			cisHzyz.setZtmrcs(cisHzyzHerbalReq.getMrcs());
			cisHzyz.setZtycsl(cisHzyzHerbalReq.getYcsl());
			if(OperationTypeEnum.remove.getCode().equals(opStatus)) {
				cisHzyzDao.deleteById(req.getJlxh());
			}else if(OperationTypeEnum.update.getCode().equals(opStatus)) {
				cisHzyzDao.update(cisHzyz);
			}else if(OperationTypeEnum.create.getCode().equals(opStatus)) {
				cisHzyz.setYzzh(yzzhList.isEmpty() ? yzzh : yzzhList.get(0));
				cisHzyz.setZtbz(1);
				cisHzyz.setZtyzjlxh(ztjlxh);
				cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
				cisHzyzDao.insert(cisHzyz);
			}
		}

		for(CisHzyzHerbalBody fjReq : fjReqList) {
			CisHzyz cisHzyz = BeanUtil.toBean(fjReq, CisHzyz.class);
			cisHzyz.setJgid(user.getHospitalId());
			cisHzyz.setSypc(cisHzyzHerbalReq.getSypc());
			cisHzyz.setYzzxsj(cisHzyzHerbalReq.getYzzxsj());
			cisHzyz.setZyh(cisHzyzHerbalReq.getZyh());
			cisHzyz.setYpyf(cisHzyzHerbalReq.getYpyf());
			cisHzyz.setKssj(cisHzyzHerbalReq.getKssj());
			cisHzyz.setTzsj(cisHzyzHerbalReq.getKssj());
			cisHzyz.setBrks(cisHzyzHerbalReq.getBrks());
			cisHzyz.setBrbq(cisHzyzHerbalReq.getBrbq());
			cisHzyz.setBrch(cisHzyzHerbalReq.getBrch());
			if(OperationTypeEnum.remove.getCode().equals(fjReq.getOpStatus())) {
				cisHzyzDao.deleteById(fjReq.getJlxh());
			}else if(OperationTypeEnum.create.getCode().equals(fjReq.getOpStatus())) {
				cisHzyz.setYzzh(yzzhList.isEmpty() ? yzzh : yzzhList.get(0));
				cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
				cisHzyzDao.insert(cisHzyz);
			}
		}
		return ztjlxh;
	}

}
