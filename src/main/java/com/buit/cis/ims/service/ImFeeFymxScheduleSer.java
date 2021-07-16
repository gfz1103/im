package com.buit.cis.ims.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.cis.dctwork.dao.PubCsrzDao;
import com.buit.cis.dctwork.model.PubCsrz;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImFeeFymx;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.nurse.service.NurseworkSer;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.system.model.FeeYlsfxmdjModel;
import com.buit.system.request.FeeYlsfxmdjApiReq;
import com.buit.system.response.FeeYlsfxmOutResp;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.response.PubBrxzOut;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.*;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 费用明细定时任务<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImFeeFymxScheduleSer extends BaseManagerImp<ImFeeFymx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImFeeFymxScheduleSer.class);
    
    static Map<String, Object> istr_cwfy = new HashMap<String, Object>(16);
	static Map<String, Object> istr_fymx = new HashMap<String, Object>(16);
	static Map<String, Object> istr_brxx = new HashMap<String, Object>(16);
	static Map<String, Object> istr_cwfyxx = new HashMap<String, Object>(16);
	static boolean tsfyFlag = false;
	static boolean getCwfxxFlag = false;
    
    @Autowired
    private ImFeeFymxDao imFeeFymxDao;
    
    @Override
    public ImFeeFymxDao getEntityMapper(){        
        return imFeeFymxDao;
    }
    
    @DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;
    
    @Autowired
    private PubCsrzDao pubCsrzDao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @DubboReference
    private HrPersonnelService hrPersonnelService;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @DubboReference
    private FeeYlsfxmOutSer feeYlsfxmOutSer;
    
    @DubboReference
    private DicSfxmlbService dicSfxmlbService;
    
    @Autowired
    private ImCwszDao imCwszDao;
    
    @DubboReference
    private PubBrxzOutSer pubBrxzOutSer;
    
    @Autowired
    private NurseworkSer nurseworkSer;
    
    @DubboReference
    private FeeYlsfxmdjService feeYlsfxmdjService;
    
    
//    @Scheduled(cron = "0 1 0 * * ?")
//    public void imFeeFymxScheduleTask() {
//		logger.error("定时执行了!!!!!!!!!!!!!!!==============");
//    	HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(0);
//    	Integer jgid = hrPersonnel.getOrganizcode();
//    	ReturnEntity<String> returnEntity = new ReturnEntity<>();
//    	returnEntity.setSuccess("SUCCESS_IM_FEE_FYMX_0001", null);
//		savePubCsrz(jgid, returnEntity.getMessage(), 1);
//		logger.error("定时任务保存日志第一次!!!!!!!!!!!!!!!==============");
//		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.CWFZDLJ);
//		String cwfzdlj = sysXtcs == null ? "1" : sysXtcs.getCsz();
//		if("1".equals(cwfzdlj)){	
//			logger.error("定时任务保存日志第二次!!!!!!!!!!!!!!!==============");
//			returnEntity.setSuccess("SUCCESS_IM_FEE_FYMX_0002", null);
//			savePubCsrz(jgid, returnEntity.getMessage(), 1);		
//			costAccumulation(jgid);
//		}
//    }
    
    /**
     * 定时任务保存记录日志
     * @Title: savePubCsrz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param rzxx
     * @param @param rzlx    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    public void savePubCsrz(Integer jgid, String rzxx, Integer rzlx) {
		PubCsrz pubCsrz = new PubCsrz();
		pubCsrz.setJgid(jgid);
		pubCsrz.setRzxx(rzxx);
		pubCsrz.setRzlx(rzlx);
		pubCsrz.setRzsj(DateUtil.date().toTimestamp());
		pubCsrzDao.insert(pubCsrz);
	}
    
    /**
     * 费用累加
     * @Title: costAccumulation
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @return    设定文件
     * @return boolean    返回类型
     * @author 龚方舟
     * @throws
     */
    public boolean costAccumulation(Integer jgid) {
		istr_cwfy = new HashMap<String, Object>(16);
		istr_fymx = new HashMap<String, Object>(16);
		istr_brxx = new HashMap<String, Object>(16);
		istr_cwfyxx = new HashMap<String, Object>(16);
		tsfyFlag = false;
		getCwfxxFlag = false;
		ReturnEntity<String> returnEntity = new ReturnEntity<>();
//		if (!costVerify(jgid)) {	
//	    	returnEntity.setError("ERROR_IM_FEE_FYMX_0001", null);
//			savePubCsrz(jgid, returnEntity.getMessage(), 2);
//			return false;
//		}
		
		returnEntity.setSuccess("SUCCESS_IM_FEE_FYMX_0003", null);
		savePubCsrz(jgid, returnEntity.getMessage(), 1);

		//累加病人表单检索数据，参数：ldt_ljzzrq;
		List<ImHzry> imHzryList = imHzryDao.queryZyHzInfo(DateUtil.date().toTimestamp());

		int zyrsCount = imHzryList.size();
		if (zyrsCount == 0) {
			return false;
		}
		
		returnEntity.setSuccess("SUCCESS_IM_FEE_FYMX_0004", new String[] {
				ObjectToTypes.parseString(zyrsCount)
		});
		savePubCsrz(jgid, returnEntity.getMessage(), 1);
		
		for (int i = 0; i < imHzryList.size(); i++) {
			ImHzry imHzry = imHzryList.get(i);
			Integer zyh = imHzry.getZyh();
			if (fylj(zyh, DateUtil.date().toJdkDate(), false, jgid)) {
				
			} else {
				returnEntity.setError("ERROR_IM_FEE_FYMX_0001", null);
				savePubCsrz(imHzry.getJgid(), returnEntity.getMessage(), 2);
				return false;
			}
		}
		
		returnEntity.setSuccess("SUCCESS_IM_FEE_FYMX_0005", null);
		savePubCsrz(jgid, returnEntity.getMessage() , 1);
		return true;
	}
    
    
	public boolean fylj(Integer zyh, Date endDate, boolean adjustFlag, Integer jgid) {
		String czgh = null;
		return fylj(zyh, endDate, adjustFlag, czgh, jgid);
	}
	
	/**
	 * 费用累加
	 * @Title: fylj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param alzyh
	 * @param @param alEndDate
	 * @param @param adjustFlag
	 * @param @param alczgh
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean fylj(Integer alzyh, Date alEndDate, boolean adjustFlag, String alczgh, Integer jgid) {
			queryHzxx(alzyh);
		if (istr_brxx.get("brch") == null) {
			return true;
		}
		if (istr_brxx.get("brbq") == null) {
			return true;
		}
		if (istr_brxx.get("ljrq") == null) {//病人第一天入院，没有产生累积日期
			istr_brxx.put("ljrq", istr_brxx.get("ryrq"));
		}
		// //DaysAfter是后面参数-前面参数 的天数
		int adjustValue = 0;
		int computeDays = BUHISUtil.getPeriod(
				DateUtil.parse(istr_brxx.get("ljrq") + "").toJdkDate(), alEndDate);
		if (computeDays == 0) {
			return true;
		} else if (computeDays > 0) {
			adjustValue = 1;
		}
		if (!saveBedCharge(istr_brxx, alEndDate, alczgh, jgid)) {
			logger.info("saveBedCharge error");
			return false;
		}
		istr_fymx.put("zyh", istr_brxx.get("zyh")); // 住院号
		istr_fymx.put("jgid", istr_brxx.get("jgid")); // 住院号
		istr_fymx.put("brxz", istr_brxx.get("brxz")); // 病人性质
		istr_fymx.put("fyks", istr_brxx.get("brks")); // 费用科室(病人科室)
		istr_fymx.put("fybq", istr_brxx.get("brbq")); // 费用病区(病人病区)
		istr_fymx.put("zxks", istr_brxx.get("brks")); // 执行科室(病人科室)
		istr_fymx.put("xmlx", 9); // 项目类型(自动累计)
		istr_fymx.put("yplx", 0); // 药品类型
		istr_fymx.put("ypcd", 0); // 药品产地
		istr_fymx.put("srgh", alczgh); // 输入工号
		istr_fymx.put("qrgh", alczgh); // 确认工号
		istr_fymx.put("ysgh", istr_brxx.get("ysgh")); // 医生工号
		istr_fymx.put("yzxh", 0); // 医嘱序号
		istr_fymx.put("zlxz", istr_brxx.get("zlxz")); // 诊疗小组
		istr_fymx.put("jfrq", DateUtil.date().toTimestamp()); // 记费日期
		long cwCount = imCwszDao.queryBedNum(alzyh);
		imHzryDao.updateKsrq(DateUtil.date(alEndDate).toTimestamp(), alzyh);
		return true;
	}
	
	/**
	 * 查询患者信息
	 * @Title: queryHzxx
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param al_zyh
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean queryHzxx(Integer alzyh) {
		ImHzry imHzry = imHzryDao.getById(alzyh);
		istr_brxx = BUHISUtil.caseInsensitiveMap(imHzry);
		istr_brxx.put("ljrq", istr_brxx.get("ksrq"));
		if (istr_brxx.get("zsys") != null) {
			istr_brxx.put("ysgh", istr_brxx.get("zsys"));
		} else {
			istr_brxx.put("ysgh", istr_brxx.get("zyys"));
		}
		if (istr_brxx.get("gzdw") == null) {
			istr_brxx.put("gzdw", "");
		}
		if (alzyh > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 保存床位费用
	 * @Title: saveBedCharge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param hzxxMap
	 * @param @param adtEndDate
	 * @param @param czgh
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean saveBedCharge(Map<String, Object> hzxxMap,
			Date adtEndDate, String czgh, Integer jgid) {
		int betweenDays = BUHISUtil.getPeriod(DateUtil.parse(hzxxMap.get("ljrq") + "").toJdkDate(),
				adtEndDate); // 取床位费天数
		int adjustValue = 0;
		Date betweenDate = null;
		if (betweenDays == 0) {
			return true;
		} else if (betweenDays > 0) {
			adjustValue = 1;
			betweenDate = DateUtil.parse(hzxxMap.get("ljrq") + "").toJdkDate();
		} else {
			adjustValue = -1;
			betweenDays = -1 * betweenDays;
			betweenDate = adtEndDate;
		}

		queryBedCharge(ObjectToTypes.parseInt(hzxxMap.get("zyh")), jgid);

		// // 保存床位费公共部分信息
		istr_fymx.put("zyh", hzxxMap.get("zyh")); // 住院号
		istr_fymx.put("brxz", hzxxMap.get("brxz")); // 病人性质
		istr_fymx.put("fyks", hzxxMap.get("brks")); // 费用科室(病人科室)
		istr_fymx.put("fybq", hzxxMap.get("brbq")); // 费用病区(病人病区)
		istr_fymx.put("zxks", hzxxMap.get("brks")); // 执行科室(病人科室)
		istr_fymx.put("xmlx", 9); // 项目类型(自动累计)
		istr_fymx.put("yplx", 0); // 药品类型
		istr_fymx.put("ypcd", 0); // 药品产地
		istr_fymx.put("srgh", czgh); // 输入工号
		istr_fymx.put("qrgh", czgh); // 确认工号
		istr_fymx.put("ysgh", hzxxMap.get("ysgh")); // 医生工号
		istr_fymx.put("yzxh", 0); // 医嘱序号
		istr_fymx.put("zlxz", hzxxMap.get("zlxz")); // 诊疗小组

		//*****************************************************************************
		//定时任务执行多次
		//先要判断是否是计算第一天的费用
		//现在是要判断，入院时间是上午，还是下午
		//获得入院日期
		Date date1 = DateUtil.parse(hzxxMap.get("ryrq") +"").toJdkDate();
		//获得开始日期
		Date ljrq = DateUtil.parse(hzxxMap.get("ljrq") +"").toJdkDate();
		//分配床位的时候，会给ksrq赋值，但是计费是以入院时间为准
		//如果ljrq不是启动时间，就是第一天入院
		//需要比较小时和分钟
	
		if (betweenDays > 0){
				if(ljrq != null && DateUtil.hour(ljrq, true) != 0 && DateUtil.minute(ljrq) != 1){//判断是否是计算第一天的费用
					if(ljrq != null && DateUtil.hour(ljrq, true) < 12 ){//如果是上午入院,按一天算
						istr_fymx.put("fysl", adjustValue); // 费用数量
					}else if(ljrq != null && DateUtil.hour(ljrq, true) >= 12){
						istr_fymx.put("fysl", adjustValue * 0.5); // 如果是下午入院,按半天算
					}
				}else{//第一天的费用已经计算过
					istr_fymx.put("fysl", adjustValue); // 费用数量
				}
		}
		
		//*****************************************************************************
		istr_fymx.put("jfrq", DateUtil.date().toTimestamp());
		//
		for (int i = 0; i < betweenDays; i++) {
			//如果直接改数据库的数据，定时任务只执行一次，一次性把多天的费用进行计算
			//***********************************只对第一天进行判断*******************************************
			if(i == 0){//第一天进行费用结算
				//计费是以开始日期为准，ksrq
				//ljrq的第一个值是临床入院时间，后边的值是定时任务的启动时间
				if (betweenDays > 0){
						if( ljrq != null && DateUtil.hour(ljrq, true) < 12){//如果是上午入院,按一天算
							istr_fymx.put("fysl", adjustValue); // 费用数量
						}else if(ljrq != null && DateUtil.hour(ljrq, true) >= 12){
							istr_fymx.put("fysl", adjustValue * 0.5); // 如果是下午入院,按半天算
						}
				}
			}else{
				//第一天的费用已经计算过
				//费用数量
				istr_fymx.put("fysl", adjustValue); 
			}
			//***********************************只对第一天进行判断*******************************************
			istr_fymx.put("fyrq", DateUtil.date(betweenDate).toTimestamp());
			// 插入床位费
			logger.error("床位费判断序号"+istr_cwfy.get("cwfxh") + "金额"+istr_cwfy.get("cwfje"));
			if (ObjectToTypes.parseInt(istr_cwfy.get("cwfxh")) > 0
					&& ObjectToTypes.parseDouble(istr_cwfy.get("cwfje")) > 0) {
				if (adjustValue > 0) {
					istr_fymx.put("fydj", istr_cwfy.get("cwfje"));
				} else {
					Map<String, Object> fyMap = imFeeFymxDao.queryYzSumPrice(ObjectToTypes.parseInt(hzxxMap.get("zyh")), 
							ObjectToTypes.parseInt(istr_cwfy.get("cwfxh")), DateUtil.date(betweenDate).toTimestamp());			
					istr_fymx.put("fydj", fyMap.get("FYDJ"));
				}
				istr_fymx.put("fyxh", istr_cwfy.get("cwfxh"));
				istr_fymx.put("jgid", istr_brxx.get("jgid"));
				istr_fymx.put("fyxm", istr_cwfy.get("cwfxm"));
				istr_fymx.put("fymc", istr_cwfy.get("cwfmc"));
				Map<String,Object> fyjeMap = nurseworkSer.getPriceJe(0, ObjectToTypes.parseInt(istr_brxx.get("brxz")), 
						ObjectToTypes.parseInt(istr_fymx.get("fyxh")), ObjectToTypes.parseInt(istr_fymx.get("fyxm")),
						ObjectToTypes.parseDouble(istr_fymx.get("fydj")), ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
				istr_fymx.put("zfbl", fyjeMap.get("ZFBL"));
				istr_fymx.put("zjje", fyjeMap.get("ZJJE"));
				istr_fymx.put("zfje", fyjeMap.get("ZFJE"));
				istr_fymx.put("zlje", fyjeMap.get("ZLJE"));
				logger.error("插入床位费");
				if (!saveFeeFymx(istr_fymx)) {
					return false;
				}
			}
			//插入自负床位费
			if (ObjectToTypes.parseInt(istr_cwfy.get("zfcwfxh")) > 0
					&& ObjectToTypes.parseDouble(istr_cwfy.get("zfcwfje")) > 0) {
				istr_fymx.put("fydj", istr_cwfy.get("zfcwfje"));
				istr_fymx.put("fyxh", istr_cwfy.get("zfcwfxh"));
				istr_fymx.put("fyxm", istr_cwfy.get("zfcwfxm"));
				istr_fymx.put("fymc", istr_cwfy.get("zfcwfmc"));
				istr_fymx.put("zfbl", 1);
				istr_fymx.put("zjje", ObjectToTypes.parseDouble(istr_cwfy.get("zfcwfje"))*ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
				istr_fymx.put("zfje", ObjectToTypes.parseDouble(istr_cwfy.get("zfcwfje"))*ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
				istr_fymx.put("zlje", ObjectToTypes.parseDouble(istr_cwfy.get("zfcwfje"))*ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
				logger.error("插入自负床位费");
				if (!saveFeeFymx(istr_fymx)) {
					return false;
				}
			}
			
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, "ZLFXH");
			if(sysXtcs != null && StrUtil.isNotBlank(sysXtcs.getCsz())) {
				String[] zlfStr =  sysXtcs.getCsz().split(",");
				//插入诊疗费
				for(String zlf : zlfStr) {
					FeeYlsfxmOutResp ylsfxm = feeYlsfxmOutSer.getById(ObjectToTypes.parseInt(zlf));
					if(ylsfxm != null) {
						FeeYlsfxmdjApiReq model = new FeeYlsfxmdjApiReq();
						model.setFyxh(ObjectToTypes.parseInt(zlf));
						model.setZfpb(0);
						List<FeeYlsfxmdjModel> list = feeYlsfxmdjService.findByEntity(model);
						if(CollectionUtils.isNotEmpty(list)) {
							istr_fymx.put("fydj", list.get(0).getFydj());
							if(ObjectToTypes.parseInt(istr_brxx.get("brxz")) == 6024) {
								istr_fymx.put("fydj", list.get(0).getSbjg() == null ? 0 : list.get(0).getSbjg());
							}
						}else {
							istr_fymx.put("fydj", 0);
						}
						istr_fymx.put("fyxh", zlf);
						istr_fymx.put("fyxm", ylsfxm.getFygb());
						istr_fymx.put("fymc", ylsfxm.getFymc());
						Map<String,Object> fyjeMap = nurseworkSer.getPriceJe(0, ObjectToTypes.parseInt(istr_brxx.get("brxz")), 
								ObjectToTypes.parseInt(zlf), ObjectToTypes.parseInt(istr_fymx.get("fyxm")),
								ObjectToTypes.parseDouble(istr_fymx.get("fydj")+""), ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
						istr_fymx.put("zfbl", fyjeMap.get("ZFBL"));
						istr_fymx.put("zjje", fyjeMap.get("ZJJE"));
						istr_fymx.put("zfje", fyjeMap.get("ZFJE"));
						istr_fymx.put("zlje", fyjeMap.get("ZLJE"));
						logger.error("插入诊疗费");
						if (!saveFeeFymx(istr_fymx)) {
							return false;
						}	
					}		
				}
			}

			if (istr_cwfy.get("icuxh") != null && ObjectToTypes.parseInt(istr_cwfy.get("icuxh")) > 0
					&& ObjectToTypes.parseDouble(istr_cwfy.get("icuje")) > 0) {
				istr_fymx.put("fydj", istr_cwfy.get("icuje"));
				istr_fymx.put("fyxh", istr_cwfy.get("icuxh"));
				istr_fymx.put("fyxm", istr_cwfy.get("icuxm"));
				istr_fymx.put("fymc", istr_cwfy.get("icumc"));
				Map<String,Object> fyjeMap = nurseworkSer.getPriceJe(0, ObjectToTypes.parseInt(istr_brxx.get("brxz")), 
						ObjectToTypes.parseInt(istr_fymx.get("fyxh")), ObjectToTypes.parseInt(istr_fymx.get("fyxm")),
						ObjectToTypes.parseDouble(istr_fymx.get("fydj")), ObjectToTypes.parseDouble(istr_fymx.get("fysl")));
				istr_fymx.put("zfbl", fyjeMap.get("ZFBL"));
				istr_fymx.put("zjje", fyjeMap.get("ZJJE"));
				istr_fymx.put("zfje", fyjeMap.get("ZFJE"));
				istr_fymx.put("zlje", fyjeMap.get("ZLJE"));
				if (!saveFeeFymx(istr_fymx)) {
					return false;
				}
			}
			betweenDate = DateUtil.offsetDay(betweenDate, 1);// 下一天
		}
		return true;
	}
	
	/**
	 * 保存费用明细
	 * @Title: saveFeeFymx
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param fymxMap
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean saveFeeFymx(Map<String, Object> fymxMap) {
		fymxMap.put("fygg", (fymxMap.get("fygg") + "").trim()); // 规格
		if (fymxMap.get("fygg") == null || "null".equals(fymxMap.get("fygg"))) {
			fymxMap.put("fygg", "");
		} else if ((fymxMap.get("fygg") + "").length() > 0) {
			fymxMap.put("fygg", "/" + fymxMap.get("fygg"));
		}
		fymxMap.put("fydw", (fymxMap.get("fydw") + "").trim()); // 单位
		if (fymxMap.get("fydw") == null || "null".equals(fymxMap.get("fydw"))) {
			fymxMap.put("fydw", "");
		} else if ((fymxMap.get("fydw") + "").length() > 0) {
			fymxMap.put("fydw", "/" + fymxMap.get("fydw"));
		}
		fymxMap.put(
				"fymc",
				"" + fymxMap.get("fymc") + fymxMap.get("fygg")
				+ fymxMap.get("fydw"));
		Map<String, Object> imFeeFymxMap = new HashMap<String, Object>(16);
		imFeeFymxMap.put("ZYH", fymxMap.get("zyh"));
		imFeeFymxMap.put("FYRQ", fymxMap.get("fyrq"));
		imFeeFymxMap.put("JFRQ", fymxMap.get("jfrq"));
		imFeeFymxMap.put("FYKS", fymxMap.get("fyks"));
		imFeeFymxMap.put("FYBQ", fymxMap.get("fybq"));
		imFeeFymxMap.put("ZXKS", fymxMap.get("zxks"));
		imFeeFymxMap.put("FYXH", fymxMap.get("fyxh"));
		imFeeFymxMap.put("FYMC", fymxMap.get("fymc"));
		imFeeFymxMap.put("FYXM", fymxMap.get("fyxm"));
		imFeeFymxMap.put("XMLX", fymxMap.get("xmlx"));
		imFeeFymxMap.put("YPLX", fymxMap.get("yplx"));
		imFeeFymxMap.put("YPCD", fymxMap.get("ypcd"));
		imFeeFymxMap.put("FYSL", fymxMap.get("fysl"));
		imFeeFymxMap.put("FYDJ", fymxMap.get("fydj"));
		imFeeFymxMap.put("ZFBL", fymxMap.get("zfbl"));
		imFeeFymxMap.put("ZJJE", fymxMap.get("zjje"));
		imFeeFymxMap.put("ZFJE", fymxMap.get("zfje"));
		imFeeFymxMap.put("ZLJE", fymxMap.get("zlje"));
		imFeeFymxMap.put("SRGH", fymxMap.get("srgh"));
		imFeeFymxMap.put("QRGH", fymxMap.get("qrgh"));
		imFeeFymxMap.put("YSGH", fymxMap.get("ysgh"));
		imFeeFymxMap.put("YZXH", fymxMap.get("yzxh"));
		imFeeFymxMap.put("JSCS", 0);
		imFeeFymxMap.put("ZLXZ", fymxMap.get("zlxz"));
		imFeeFymxMap.put("YEPB", 0);
		imFeeFymxMap.put("JGID", istr_brxx.get("jgid"));
		imFeeFymxMap.put("DZBL", new BigDecimal(1));
		ImFeeFymx imFeeFymx = BeanUtil.fillBeanWithMapIgnoreCase(imFeeFymxMap, new ImFeeFymx(), true);
		imFeeFymx.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_FEE_FYMX));
		imFeeFymxDao.insert(imFeeFymx);
		return true;
	}
	
	/**
	 * 查询床位费
	 * @Title: queryBedCharge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param al_zyh
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean queryBedCharge(Integer alzyh, Integer jgid) {
		ImHzry hzry = imHzryDao.getById(alzyh);
		Integer brxz = hzry.getBrxz();
		PubBrxzOut pubBrxz = pubBrxzOutSer.getById(brxz);
		double cfxj = pubBrxz.getCfxj().doubleValue();
		Integer cwfyxh = 0;
		istr_cwfy.put("cwfje", 0);
		istr_cwfy.put("icuje", 0);
		Map<String, Object> cwMap = imCwszDao.queryBedFyInfo(alzyh);
		/**************商保病人床位费用**************/
		if (cwMap != null) {
			//判断病人性质，医保和普通自费，外地自费的取CWFY(25)， 商报和VIP的取VIPCWFY(300,500,3000)
			if("0".equals(pubBrxz.getVipcwfy())){
				istr_cwfy.put("cwfje", cwMap.get("CWFJE") == null ? 0 : cwMap.get("CWFJE"));
				cwfyxh = ObjectToTypes.parseInt(cwMap.get("CWFYXH"));
			}else{
				istr_cwfy.put("cwfje", cwMap.get("VIPCWFJE") == null ? 0 : cwMap.get("VIPCWFJE"));
				cwfyxh = ObjectToTypes.parseInt(cwMap.get("VIPFYXH"));
			}
			/**************商保病人床位费用**************/
			if(6024 == brxz){//天佑商业保险 6024
				if(cwMap.get("SBBRCWFY") != null && ObjectToTypes.parseDouble(cwMap.get("SBBRCWFY")) != 0){
					istr_cwfy.put("cwfje", cwMap.get("SBBRCWFY"));
					cwfyxh = ObjectToTypes.parseInt(cwMap.get("SBFYXH"));
				}	
			}	
			
			if(cwMap.get("ICUJE") != null && ObjectToTypes.parseDouble(cwMap.get("ICUJE")) != 0) {
				istr_cwfy.put("icuje", cwMap.get("ICUJE"));	
				cwfyxh = ObjectToTypes.parseInt(cwMap.get("ICUFYXH"));
			}
		} 
		if(cwfyxh > 0) {
			FeeYlsfxmOutResp feeYlsfxm = feeYlsfxmOutSer.getById(cwfyxh);
			//床位费 
			istr_cwfy.put("cwfxh", cwfyxh);
			istr_cwfy.put("cwfxm", feeYlsfxm.getFygb());
			istr_cwfy.put("cwfmc", feeYlsfxm.getFymc());
		}
		return true;
	}
	

	
	
}
