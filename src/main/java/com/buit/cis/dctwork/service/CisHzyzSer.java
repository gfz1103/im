package com.buit.cis.dctwork.service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.apply.model.CisJcsq01Model;
import com.buit.apply.model.CisJcsq02Model;
import com.buit.apply.model.OpZt02;
import com.buit.apply.request.CisJcsq01QueryReq;
import com.buit.apply.request.CisJcsq03Req;
import com.buit.apply.request.DicZlxmSqdReqYsJx01;
import com.buit.apply.request.DicZlxmSqdReqYsJx02;
import com.buit.apply.response.CisJcsq01QueryResp;
import com.buit.apply.response.CisJcsq02ZlxmResp;
import com.buit.apply.response.CisJcsq03Resp;
import com.buit.apply.response.OpZt02Resp;
import com.buit.apply.service.Cisjcsqd01Service;
import com.buit.apply.service.Cisjcsqd03Service;
import com.buit.apply.service.OpZt02Service;
import com.buit.cis.dctwork.dao.AmqcKjywsyhzksysDao;
import com.buit.cis.dctwork.dao.AmqcKjywsysqDao;
import com.buit.cis.dctwork.dao.AmqcKjywsysqypDao;
import com.buit.cis.dctwork.dao.CisBxsqdDao;
import com.buit.cis.dctwork.dao.CisBxxypzDao;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.CisHzyzZtDao;
import com.buit.cis.dctwork.dao.CisJcsq01Dao;
import com.buit.cis.dctwork.dao.CisZyHzsqDao;
import com.buit.cis.dctwork.dao.CisZyHzyqdxDao;
import com.buit.cis.dctwork.dao.NisFyyfDao;
import com.buit.cis.dctwork.dao.NisTj01Dao;
import com.buit.cis.dctwork.dao.NisTj02Dao;
import com.buit.cis.dctwork.dto.PharFyjlMessDto;
import com.buit.cis.dctwork.dao.OpSbhyDao;
import com.buit.cis.dctwork.dao.OptSssqDao;
import com.buit.cis.dctwork.enums.OperationTypeEnum;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.CisHzyzZt;
import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.model.NisTj01;
import com.buit.cis.dctwork.model.NisTj02;
import com.buit.cis.dctwork.model.PharYflb;
import com.buit.cis.dctwork.request.CisHzyzAntibioticsReq;
import com.buit.cis.dctwork.request.CisHzyzCheckAntimicrobialReq;
import com.buit.cis.dctwork.request.CisHzyzCheckAntimicrobialReq.YzxxBody;
import com.buit.cis.dctwork.request.CisHzyzCheckKcReq;
import com.buit.cis.dctwork.request.CisHzyzChooseProjectReq;
import com.buit.cis.dctwork.request.CisHzyzCommonReq;
import com.buit.cis.dctwork.request.CisHzyzCopyReq;
import com.buit.cis.dctwork.request.CisHzyzCopyReq.CopyBody;
import com.buit.cis.dctwork.request.CisHzyzDeteleReq;
import com.buit.cis.dctwork.request.CisHzyzJcxmReq;
import com.buit.cis.dctwork.request.CisHzyzProjectReq;
import com.buit.cis.dctwork.request.CisHzyzReq;
import com.buit.cis.dctwork.request.CisHzyzSaveReq;
import com.buit.cis.dctwork.request.CisHzyzStackReq;
import com.buit.cis.dctwork.request.CisHzyzStopCheckReq;
import com.buit.cis.dctwork.request.CisHzyzXmImportReq;
import com.buit.cis.dctwork.request.CisHzyzXmImportReq.ZtBody;
import com.buit.cis.dctwork.request.CisHzyzYpSubmitReq;
import com.buit.cis.dctwork.request.DrugsTypkBqyzYpSrfReq;
import com.buit.cis.dctwork.request.NisTj02Req;
import com.buit.cis.dctwork.request.NisTjSureReq;
import com.buit.cis.dctwork.request.SkinPsjlCheckReq;
import com.buit.cis.dctwork.response.CisHzyzChooseProjectResp;
import com.buit.cis.dctwork.response.CisHzyzCommonResp;
import com.buit.cis.dctwork.response.CisHzyzProjectResp;
import com.buit.cis.dctwork.response.CisHzyzYflbResp;
import com.buit.cis.dctwork.response.CisHzyzYpSubmitResp;
import com.buit.cis.dctwork.response.CisJcsq01ZlxmResp;
import com.buit.cis.dctwork.response.NisFyyfResp;
import com.buit.cis.dctwork.response.NisTj02Resp;
import com.buit.cis.dctwork.response.NisTjMsgResp;
import com.buit.cis.dctwork.response.NisTjSureResp;
import com.buit.cis.dctwork.response.SkinPsjlCheckResp;
import com.buit.cis.ims.dao.ImFeeFymxDao;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImTbkkDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.response.CardPatientCostBaseInfoJe;
import com.buit.cis.ims.response.ImHzryYpSumbitResp;
import com.buit.cis.nurse.request.CisHzyzJySqdReq;
import com.buit.common.drug.model.PubPharKcdjNew;
import com.buit.common.drug.service.impl.PubDrugKcdjSerImpl;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.constans.TableName;
import com.buit.drug.request.PharBaseInfoReq;
import com.buit.drug.request.QueryDjYpslReq;
import com.buit.drug.request.QueryDrugInfoReq;
import com.buit.drug.response.CisHzyzYpcdDetailResp;
import com.buit.drug.response.DrugsTypkDetailResp;
import com.buit.drug.response.PharBaseInfoResp;
import com.buit.drug.service.DrugService;
import com.buit.drug.service.DrugsTypkOutSer;
import com.buit.drug.service.PharBaseConfigService;
import com.buit.his.treatment.ResultsUtil;
import com.buit.his.treatment.model.ZlHzyz;
import com.buit.his.treatment.service.ZlRwfpService;
import com.buit.mms.antimi.service.AmqcKjywsysqypService;
import com.buit.system.model.DicYjlxModel;
import com.buit.system.model.DiccZlsfdzModel;
import com.buit.system.model.FeeYlsfxmModel;
import com.buit.system.model.FeeYlsfxmdjModel;
import com.buit.system.model.ImDicYpyfModel;
import com.buit.system.request.FeeYlsfxmProjectSrfApiReq;
import com.buit.system.request.FeeYlsfxmdjApiReq;
import com.buit.system.request.FeeYpxzApiReq;
import com.buit.system.request.PubFyxzApiReq;
import com.buit.system.response.DicSypcModel;
import com.buit.system.response.DicYjlxZlxmApiResp;
import com.buit.system.response.DiccLdxmglApiResp;
import com.buit.system.response.FeeSfdlzfbl;
import com.buit.system.response.FeeYlsfxmOutResp;
import com.buit.system.response.FeeYlsfxmProjectSrfApiResp;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.DicSypcService;
import com.buit.system.service.DicYjlxService;
import com.buit.system.service.DiccLdxmglService;
import com.buit.system.service.DiccZlsfdzService;
import com.buit.system.service.FeeSfdlzfblOutSer;
import com.buit.system.service.FeeYlsfxmOutSer;
import com.buit.system.service.FeeYlsfxmdjService;
import com.buit.system.service.FeeYpxzService;
import com.buit.system.service.HrPersonnelService;
import com.buit.system.service.ImDicYpyfService;
import com.buit.system.service.PubFyxzService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 住院_病区医嘱<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisHzyzSer extends BaseManagerImp<CisHzyz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisHzyzSer.class);
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @Override
    public CisHzyzDao getEntityMapper(){        
        return cisHzyzDao;
    }
    
    @DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;
    
    @Autowired
    private NisTj02Dao nisTj02Dao;
    
    @DubboReference
    private DrugService drugService;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisTj01Dao nisTj01Dao;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private ImFeeFymxDao imFeeFymxDao;
    
    @Autowired
	private NisFyyfDao nisFyyfDao;
    
    @DubboReference
    private DrugsTypkOutSer drugsTypkOutSer;
    
    @DubboReference
    private OpZt02Service opZt02Service;
    
    @Autowired
    private ImHzryByDctworkSer imHzryByDctworkSer;
    
    @DubboReference
    private FeeYlsfxmOutSer feeYlsfxmOutSer;
    
    @DubboReference
	private DicSypcService dicSypcService;
	
	@DubboReference
	private FeeYlsfxmdjService feeYlsfxmdjService;
	
	@Autowired
	private CisHzyzZtDao cisHzyzZtDao;
	
	@DubboReference
	private DicYjlxService dicYjlxService;
	
	@DubboReference
	private Cisjcsqd01Service cisjcsqd01Service;
	
	@DubboReference
	private DiccZlsfdzService diccZlsfdzService;
	
	@DubboReference
	private PharBaseConfigService pharBaseConfigService;
	
	@DubboReference
	private DiccLdxmglService diccLdxmglService;
	
	@DubboReference
	private ImDicYpyfService imDicYpyfService;

	@DubboReference
	private HrPersonnelService hrPersonnelService;
	
	@DubboReference
	private FeeSfdlzfblOutSer feeSfdlzfblOutSer;
	
	@DubboReference
	private FeeYpxzService feeYpxzService;
	
	@DubboReference
	private PubFyxzService pubFyxzService;
	
	@Autowired
	private ImTbkkDao imTbkkDao;
	
	@DubboReference
	private AmqcKjywsysqypService amqcKjywsysqypService;
	
	@DubboReference
	private Cisjcsqd03Service cisjcsqd03Service;
	
	@Autowired

	private CisBxsqdDao cisBxsqdDao;

	@Autowired
	private CisBxxypzDao cisBxxypzDao;

	@Autowired
	private CisZyHzsqDao cisZyHzsqDao;

	@Autowired
	private CisZyHzyqdxDao cisZyHzyqdxDao;
	
	@Autowired
	private OpSbhyDao opSbhyDao;
	
	@Autowired
	private CisJcsq01Dao cisJcsq01Dao;
	
	@Autowired
	private OptSssqDao optSssqDao;
	
	@Autowired
	private AmqcKjywsysqDao amqcKjywsysqDao;
	
	@Autowired
	private AmqcKjywsysqypDao amqcKjywsysqypDao;
	
	@Autowired
	private AmqcKjywsyhzksysDao amqcKjywsyhzksysDao;
	
	@DubboReference
	private ZlRwfpService zlRwfpService;

	@Autowired
	private PubDrugKcdjSerImpl pubDrugKcdjSerImpl;

    
    /**
    * @Title: getDoctorAdviceBrQuery
    * @Description: TODO 病区医嘱提交左边病人列表数据查询
    * @param @param req
    * @param @param user
    * @param @return    设定文件
    * @return List<Map<String,Object>>    返回类型
    * @author 龚方舟
    * @throws
     */
    public List<ImHzryYpSumbitResp> getDoctorAdviceBrQuery(CisHzyzYpSubmitReq cisHzyzYpSubmitReq, SysUser user){
    	List<CisHzyzYpSubmitResp> list = doGetDoctorAdviceSubmitQuery(cisHzyzYpSubmitReq, user);
    	Map<String, List<CisHzyzYpSubmitResp>> groups = list.stream().collect(
    			Collectors.groupingBy(o -> String.format("%s-%s-%s", o.getZyh(), o.getBrch(), o.getBrxm()), 
    					LinkedHashMap::new, Collectors.toList()));
		List<ImHzryYpSumbitResp> ret = new ArrayList<ImHzryYpSumbitResp>();
		groups.entrySet().stream().forEach(item -> {
			String[] keyArray = item.getKey().split("-");
			ImHzryYpSumbitResp resp = new ImHzryYpSumbitResp();
			resp.setZyh(ObjectToTypes.parseInt(keyArray[0]));
			resp.setBrch(keyArray[1]);
			resp.setBrxm(keyArray[2]);
			ret.add(resp);
		});
		return ret;
	}
    
   /**
   * @Title: doGetDoctorAdviceSubmitQuery
   * @Description: TODO 医嘱提交查询药品信息
   * @param @param cisHzyzYpSubmitReq
   * @param @param user
   * @param @return    设定文件
   * @return List<CisHzyzYpSubmitResp>    返回类型
   * @author 龚方舟
   * @throws
    */
    @Transactional(rollbackFor = Exception.class)
	public List<CisHzyzYpSubmitResp> doGetDoctorAdviceSubmitQuery(CisHzyzYpSubmitReq cisHzyzYpSubmitReq,
			SysUser user) {
		try {
			SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
			Integer manaUnitId = user.getHospitalId();
			SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLSJ);	
			String ypylsj = sysXtcs == null ? "1" : sysXtcs.getCsz();
			Integer alhsql = 0;
			if (StrUtil.isNotBlank(ObjectToTypes.parseString(cisHzyzYpSubmitReq.getKsdm()))) {
				alhsql = cisHzyzYpSubmitReq.getKsdm();
			}
			Integer alzyh = 0;
			Integer ldtfyfs = 0;
			Integer ldtlsyz = 0;
			Integer ldtyfsb = 0;
			Date ldtserver = sdfdate.parse(sdfdate.format(new Date()));
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getZyh())) {
				alzyh = cisHzyzYpSubmitReq.getZyh();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getFyfs())) {
				ldtfyfs = cisHzyzYpSubmitReq.getFyfs();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getLsyz())) {
				ldtlsyz = cisHzyzYpSubmitReq.getLsyz();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getYfsb())) {
				ldtyfsb = cisHzyzYpSubmitReq.getYfsb();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getLyrq())) {
				ldtserver = cisHzyzYpSubmitReq.getLyrq();
			}
			
			/************************************************/
			//以前:ldt_server：1:默认当天时间，2:参数传入时变成默认第二天。3：在加上      
			//“.getDateAfter(ldt_server, 1))”   1天 的天数，变成第三天的00：00：00，
			//4:也就是到第三天的0点，实际发药发到第二天
			//5:如果时间为09:30:00，时间发药到第三天的早上
            //6:23:59:59,时间加0天。发药发到第二天23：59：59
            Date ldtserverby = ldtserver;
            sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLTS);
            String byypylts = sysXtcs == null ? "1" : sysXtcs.getCsz();
            String addylsj = " 23:59:59";
            if ("2".equals(byypylts)) {
                addylsj = " 09:30:00";
            } else if ("3".equals(byypylts)) {
                addylsj = " 12:30:00";
            }
            ldtserverby = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(ldtserverby, 0))
                    + addylsj);
            //病区药品提交预领药时间
            ldtserver = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(ldtserver, 1))
                    + ("2".equals(ypylsj) ? " 09:30:00" : " 00:00:00"));
            /*************************************************/
            List<Map<String, Object>> collardrugdetailslist = new ArrayList<Map<String, Object>>();
            Map<Long, List<Date>> qrsjMap = new HashMap<Long, List<Date>>(16);
            Map<String, Object> collardrugdetailsparameters = new HashMap<String, Object>(16);
            if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getZyhs())) {
                List<Integer> zyhs = cisHzyzYpSubmitReq.getZyhs();
                collardrugdetailsparameters.put("ZYHS", zyhs);
            } else {
                if (alzyh != 0) {
					collardrugdetailsparameters.put("ZYH", alzyh);
				}
			}
			if (ldtyfsb != 0) {
				collardrugdetailsparameters.put("YFSB", ldtyfsb);
			}
			if (ldtlsyz == 0 || ldtlsyz == 1) {
				collardrugdetailsparameters.put("LSYZ", ldtlsyz);
			} else if (ldtlsyz == 3) {
				collardrugdetailsparameters.put("LSYZ", 2);
			} else if (ldtlsyz == 4) {
				collardrugdetailsparameters.put("LSYZ", 3);
			}
			if (ldtfyfs != 0) {
				collardrugdetailsparameters.put("FYFS", ldtfyfs);
			}
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.FHYZHJF);
			String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if ("1".equals(xyf)) {
				collardrugdetailsparameters.put("FHBZ", 1);
			}
		
			collardrugdetailsparameters.put("JGID", manaUnitId);
			collardrugdetailsparameters.put("SRKS", alhsql);
			//转方药品除外
			collardrugdetailsparameters.put("ZFYP", 1);
			collardrugdetailsparameters.put("TYPE", 2);
			collardrugdetailslist = cisHzyzDao.queryBqyzSubmit(collardrugdetailsparameters);
			
			//------------临时医嘱 bid 不乘以2等
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.LSYZSYPCCS);
			String lsyzsypccs = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if("1".equals(lsyzsypccs)){
				for(int i = 0 ; i<collardrugdetailslist.size(); i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC_BAK", yz.get("SYPC"));
						yz.put("SYPC", "st");
					}	
				}			
			}
			// 处理领药明细表单数据
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				Date ldtqrsj = null;
				Date ldtkssj = null;
				if (collardrugdetailslist.get(i).get("QRSJ") != null) {
					ldtqrsj = sdfdate.parse(collardrugdetailslist.get(i).get("QRSJ")+ "");
				}
				if (collardrugdetailslist.get(i).get("KSSJ") != null) {
					ldtkssj = sdfdate.parse(collardrugdetailslist.get(i).get("KSSJ")+ "");
				}
				if (ldtqrsj == null) {
					continue;
				}
				if (ldtserver.getTime() < ldtqrsj.getTime()
						|| ldtserver.getTime() < ldtkssj.getTime()) {// 如果领药日期小于医嘱确认日期
					collardrugdetailslist.remove(i);
					i--;
				}
			}
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("firstrow", 1);
			parameters.put("lastrow", collardrugdetailslist.size());
			parameters.put("ldt_server", ldtserver);
			/*******************/
			parameters.put("ldt_server_by", ldtserverby);
			/**********************/
			yzSubmit(collardrugdetailslist, qrsjMap, parameters, user);	
			if("1".equals(lsyzsypccs)){
				for(int i = 0; i<collardrugdetailslist.size();i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC", yz.get("SYPC_BAK"));		
					}
					
				}
			}			
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
                int llfycstotal = Integer.parseInt(collardrugdetailslist.get(i).get("FYCS") + "");
                //项目类型
                int llxmlx = Integer.parseInt(collardrugdetailslist.get(i).get("XMLX") + "");
                if (llxmlx != 3) {
                    if (llfycstotal == 0) {
                        collardrugdetailslist.remove(i);
                        i--;
                    }
                }
            }
            List<CisHzyzYpSubmitResp> cisHzyzYpSubmitRespList = BUHISUtil.ListToResultSet(collardrugdetailslist,
                    new CisHzyzYpSubmitResp());
            
            List<NisTjSureResp> nisTjSureRespList = inventoryVerification(cisHzyzYpSubmitRespList.stream().filter(e -> e.getLsyz() == 0)
            		.map(o -> new NisTjSureReq(o.getYpxh(), o.getYpcd(), o.getYcsl().multiply(new BigDecimal(o.getFycs().intValue())), 
            		o.getYfsb(), o.getYzmc())).collect(Collectors.toList()));
            for(CisHzyzYpSubmitResp resp : cisHzyzYpSubmitRespList) {
            	for(NisTjSureResp sureResp : nisTjSureRespList) {
            		if(String.format("%s-%s", resp.getYpxh(), resp.getYpcd()).equals(
            				String.format("%s-%s", sureResp.getMedId(), sureResp.getMedsource()))) {
            			resp.setSfqy(true);
            		}
            	}
            }            
            return cisHzyzYpSubmitRespList;
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00020");
		} 
	}
	
	/** 
	* @Title: doDoctorAdviceQueryVerification
	* @Description: TODO 查询前天数间隔校验
	* @param @param lyrq
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void doDoctorAdviceQueryVerification(Integer brbq, java.sql.Date lyrq, SysUser user) {
		try {
			SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
			Integer manaUnitId = user.getHospitalId();
			if (!StrUtil.isBlankIfStr(brbq)) {
				Integer alhsql = brbq;
				//判断是否成立
                SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId,
                        SysXtcsCsmcCts.BQTJTS + alhsql);
                String bqtjts = sysXtcs == null ? "0" : sysXtcs.getCsz();
                sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId,
                        SysXtcsCsmcCts.YZTS + alhsql);
                String llyzts = sysXtcs == null ? "0" : sysXtcs.getCsz();
                Date ldtlyrq = sdfdate.parse(sdfdate.format(new Date()));
                if (lyrq != null) {
                    ldtlyrq = new Date(lyrq.getTime());
                }
                int litjts = BUHISUtil.getPeriod(ldtlyrq, new Date());
                if ("1".equals(bqtjts)) {
                    if (litjts > Integer.parseInt(llyzts)) {
                        throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00025");
                    }
                }
            }
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00021");
		}
	}
	
	/**
	 * 检查提交住院药房的药品的库存
	 * @Title: doCheckInventoryInpatientPharmacy
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param bqTjSureReqList
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public String doCheckInventoryInpatientPharmacy(List<NisTjSureReq> nisTjSureReqList) {
		String str = "";
        List<Map<String, Object>> body = BUHISUtil.ListObjToMap(nisTjSureReqList);
        body = body.stream().filter(e -> "0".equals(ObjectToTypes.parseString(e.get("lsyz")))).collect(Collectors.toList());
        if (body.size() > 0) {
			//根据药品id+药品产地排序
			Collections.sort(body, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1,
								   Map<String, Object> o2) {
					int i = Integer.parseInt(o1.get("medId") + "") - Integer.parseInt(o2.get("medId") + "");
					if (i == 0) {
						return Integer.parseInt(o1.get("medsource") + "")-Integer.parseInt(o2.get("medsource") + "");
					}
					return i;
				}
			});
			// 药品id+药品产地一样的记录，累加药品数量
			List<Map<String, Object>> medicine = new ArrayList<Map<String, Object>>();
			String medId = body.get(0).get("medId") + "";
			String medsource = body.get(0).get("medsource") + "";
			String ypmc = body.get(0).get("ypmc") + "";
			String yfsb = body.get(0).get("yfsb") + "";
			double ypsl = 0;
			int count = 0;
			for (Map<String, Object> data : body) {
				if (medId.equals(data.get("medId") + "")
						&& medsource.equals(data.get("medsource") + "")) {
					ypsl = ypsl + Double.parseDouble(data.get("quantity") + "");
				} else {
					Map<String, Object> temp = new HashMap<String, Object>(16);
					temp.put("medId", medId);
					temp.put("medsource", medsource);
					temp.put("ypmc", ypmc);
					temp.put("quantity", ypsl);
					medicine.add(temp);
					medId = data.get("medId") + "";
					medsource = data.get("medsource") + "";
					ypmc = data.get("ypmc") + "";
					ypsl = 0;
					ypsl = ypsl + Double.parseDouble(data.get("quantity") + "");
				}

				if (count == (body.size() - 1)) {
					Map<String, Object> temp = new HashMap<String, Object>(16);
					temp.put("medId", medId);
					temp.put("medsource", medsource);
					temp.put("ypmc", ypmc);
					temp.put("quantity", ypsl);
					medicine.add(temp);
				}
				count++;
			}
			List<String> strList = medicine.stream().map(o -> String.format("%s,%s", ObjectToTypes.parseString(o.get("medId")),
					ObjectToTypes.parseString(o.get("medsource")))).collect(Collectors.toList());
			StringBuffer reStr = new StringBuffer();
			List<Map<String, Object>> medicineInventory = drugService.queryHospitalizationStock(ObjectToTypes.parseInt(yfsb), strList);
			List<Integer> ypxhList = medicineInventory.stream().map(o ->
					ObjectToTypes.parseInt(o.get("YPXH"))).collect(Collectors.toList());
			List<Map<String, Object>> notExistList = medicine.stream().filter(
					o -> !ypxhList.contains(ObjectToTypes.parseInt(o.get("medId")))).collect(Collectors.toList());
			for(Map<String, Object> med : notExistList) {
				ReturnEntity<String> returnEntity = new ReturnEntity<String>();
				returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00053", new String[] {
						ObjectToTypes.parseString(med.get("ypmc")), "0",
						ObjectToTypes.parseString(med.get("quantity"))
				});
				reStr.append(returnEntity.getMessage() + "</br>");
			}

			for(Map<String, Object> med : medicine){
				for(Map<String, Object> medInv : medicineInventory){
					if((med.get("medId")+"").equals(medInv.get("YPXH")+"")
							&&(med.get("medsource")+"").equals(medInv.get("YPCD")+"")){
						if(ObjectToTypes.parseDouble(med.get("quantity")) > ObjectToTypes.parseDouble(medInv.get("KCZL"))){
							ReturnEntity<String> returnEntity = new ReturnEntity<String>();
							returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00053", new String[] {
									ObjectToTypes.parseString(med.get("ypmc")),
									ObjectToTypes.parseString(medInv.get("KCZL")),
									ObjectToTypes.parseString(med.get("quantity"))
							});
							reStr.append(returnEntity.getMessage() + "</br>");
						}

					}
				}
			}

			if(reStr.length() > 0) {
				str = reStr.toString().substring(0, reStr.length() - 5);
			}
		}
		return str;
	}
	
	/**
	 * 药品医嘱提交确认
	 * @Title: doSaveDoctorAdviceSubmit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzYpSubmitReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return BqTjMsgResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public NisTjMsgResp doSaveDoctorAdviceSubmit(CisHzyzYpSubmitReq cisHzyzYpSubmitReq, SysUser user){
		try {
			NisTjMsgResp bqTjMsgResp = new NisTjMsgResp();
			SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer manaUnitId = user.getHospitalId();
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLSJ);	
			String ypylsj = sysXtcs == null ? "1" : sysXtcs.getCsz();
			Integer alzyh = 0;
			Integer ldtfyfs = 0;
			Integer ldtlsyz = 0;
			Integer ldtyfsb = 0;
			Integer tjindx = 0;
			if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getZyh())) {
				alzyh = cisHzyzYpSubmitReq.getZyh();
			}
			Date ldttjsj = sdfdatetime.parse(sdfdatetime.format(new Date()));
			Date ldtserver = sdfdate.parse(sdfdate.format(new Date()));			
			Date ldtserverby = ldtserver;
			if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getLyrq())) {
				/************************************************/
				//以前:ldt_server：1:默认当天时间，2:参数传入时变成默认第二天。3：在加上       “.getDateAfter(ldt_server, 1))”   1天 的天数，变成第三天的00：00：00，4：也就是到第三天的0点，实际发药发到第二天
                //5：如果时间为09:30:00，时间发药到第三天的早上
                //6:23:59:59,时间加0天。发药发到第二天23：59：59
                sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLTS);
                String byypylts = sysXtcs == null ? "1" : sysXtcs.getCsz();
                String addylsj = " 23:59:59";
                if ("2".equals(byypylts)) {
                    addylsj = " 09:30:00";
                } else if ("3".equals(byypylts)) {
                    addylsj = " 12:30:00";
                }
                ldtserverby = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(
                        sdfdate.parse(sdfdate.format(cisHzyzYpSubmitReq.getLyrq())), 0)) + addylsj);

                ldtserver = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(
                        sdfdate.parse(sdfdate.format(cisHzyzYpSubmitReq.getLyrq())), 1))
                        + ("2".equals(ypylsj) ? " 09:30:00" : " 00:00:00"));
                /*************************************************/
            }
			ldtfyfs = cisHzyzYpSubmitReq.getFyfs();
			ldtlsyz = cisHzyzYpSubmitReq.getLsyz();
			ldtyfsb = cisHzyzYpSubmitReq.getYfsb();
			Integer uid = user.getUserId();
			Integer alhsql = 0;
			if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getKsdm())) {
				alhsql = cisHzyzYpSubmitReq.getKsdm();
				//判断是否成立
				doDoctorAdviceQueryVerification(alhsql, cisHzyzYpSubmitReq.getLyrq(), user);
			}
			List<Map<String, Object>> collardrugdetailslist = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> tj01list = new ArrayList<Map<String, Object>>();
			Map<Long,List<Date>> qrsjMap = new HashMap<Long,List<Date>>(16);
			Map<String, Object> collardrugdetailsparameters = new HashMap<String, Object>(16);
			collardrugdetailsparameters.put("JGID", manaUnitId);
			collardrugdetailsparameters.put("SRKS", alhsql);
			if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getJlxhs())) {
				List<Integer> jlxhs = cisHzyzYpSubmitReq.getJlxhs();
				collardrugdetailsparameters.put("JLXHS", jlxhs);
			}
			if (alzyh != 0) {
				collardrugdetailsparameters.put("ZYH", alzyh);
			}
			if (ldtyfsb != 0) {
				collardrugdetailsparameters.put("YFSB", ldtyfsb);
			}
			if (ldtlsyz == 0 || ldtlsyz == 1) {
				collardrugdetailsparameters.put("LSYZ", ldtlsyz);
			} else if (ldtlsyz == 3) {
				collardrugdetailsparameters.put("LSYZ", 2);
			} else if (ldtlsyz == 4) {
				collardrugdetailsparameters.put("LSYZ", 3);
			}
			if (ldtfyfs != 0) {
				collardrugdetailsparameters.put("FYFS", ldtfyfs);
			}
			
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.FHYZHJF);
			String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if ("1".equals(xyf)) {
				collardrugdetailsparameters.put("FHBZ", 1);
			}
			collardrugdetailsparameters.put("TYPE", 2);
			collardrugdetailslist = cisHzyzDao.queryBqyzSubmit(collardrugdetailsparameters);
			//------------临时医嘱 bid 不乘以2等		
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.LSYZSYPCCS);
			String lsyzsypccs = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if("1".equals(lsyzsypccs)){
				for(int i = 0;i<collardrugdetailslist.size();i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC_BAK", yz.get("SYPC"));
						yz.put("SYPC", "st");
					}	
				}		
			}

			// 处理领药明细表单数据
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				Date ldtqrsj = null;
				Date ldtkssj = null;
				if (collardrugdetailslist.get(i).get("QRSJ") != null) {
					ldtqrsj = sdfdate.parse(collardrugdetailslist.get(i).get("QRSJ") + "");
				}
				if (collardrugdetailslist.get(i).get("KSSJ") != null) {
					ldtkssj = sdfdate.parse(collardrugdetailslist.get(i).get("KSSJ") + "");
				}
				if (ldtqrsj == null) {
					continue;
				}
				//如果领药日期小于医嘱确认日期
				if (ldtserver.getTime() < ldtqrsj.getTime() || ldtserver.getTime() < ldtkssj.getTime()) {
					collardrugdetailslist.remove(i);
					i--;
				}
			}
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("firstrow", 1);
			parameters.put("lastrow", collardrugdetailslist.size());
			parameters.put("ldt_server", ldtserver);
			parameters.put("ldt_server_by", ldtserverby);
			yzSubmit(collardrugdetailslist, qrsjMap, parameters, user);
			//------------临时医嘱 bid 不乘以2等
			if("1".equals(lsyzsypccs)){					
				for(int i = 0;i<collardrugdetailslist.size();i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC", yz.get("SYPC_BAK"));	
					}
				}
			}
			
			ArrearsPatientsQuery(collardrugdetailslist, user , bqTjMsgResp);
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				int llxmlx = Integer.parseInt(collardrugdetailslist.get(i).get("XMLX") + "");// 项目类型
				int llfycstotal = Integer.parseInt(collardrugdetailslist.get(i).get("FYCS") + "");
				if (llxmlx != 2 || llxmlx != 3) {
					if (llfycstotal == 0) {
						collardrugdetailslist.remove(i);
						i--;
					}
				}
			}
			//增加抗菌药物审批判断
			List<String> warnMsg = doCheckAntibacterial(collardrugdetailslist, user);
			if (warnMsg != null && warnMsg.size() > 0) {
				bqTjMsgResp.setWarnMsg(warnMsg);
				return bqTjMsgResp;
			}
			
			List<PubPharKcdjNew> kcdjList = new ArrayList<PubPharKcdjNew>();
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				int eqorne = 0;
				//住院号
				Integer llzyh = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("ZYH")); 
				//医嘱序号
				Long llyzxh = ObjectToTypes.parseLong(collardrugdetailslist.get(i).get("JLXH")); 
				//药品产地
				Integer llypcd = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YPCD")); 
				//药品序号
				Integer llypxh = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YPXH")); 
				//药品单价
				Double ldypdj = ObjectToTypes.parseDouble(collardrugdetailslist.get(i).get("YPDJ"));
				//一次数量
				Double ldycsl = ObjectToTypes.parseDouble(collardrugdetailslist.get(i).get("YCSL"));
				//开始时间
				Date ldtkssj = sdfdatetime.parse(collardrugdetailslist.get(i).get("KSSJ") + ""); 
				//使用频次
				String lssypc = collardrugdetailslist.get(i).get("SYPC") + "";
				//项目类型
				Integer llxmlx = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("XMLX"));
				// 药房识别
				Integer llyfsb = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YFSB")); 
				// 发药方式
				Integer llfyfs = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("FYFS")); 
				//临时医嘱标志
				int lllsyz = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("LSYZ"));
				//病人科室
				Integer llbrks = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("BRKS"));
				//病人病区
				Integer llbrbq = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("BRBQ"));
				//婴儿判别
				Integer llyepb = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YEPB"));
				//药房单位
				String lsyfdw = collardrugdetailslist.get(i).get("YFDW") + "";
				//药房规格
				String lsyfgg = collardrugdetailslist.get(i).get("YFGG") + ""; 
				//药房包装
				Integer llyfbz = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YFBZ"));
				//每日次数
				Integer llmrcs = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("MRCS"));
				Integer llfycstotal = 0;
				if (llxmlx != 2 && llxmlx != 3) {
					llfycstotal = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("FYCS"));
					if (llfycstotal == 0) {
						continue;
					}
				}
				//根据医嘱需要获取医嘱对应的确认时间
				List<Date> qrsjlist = qrsjMap.get(llyzxh); 
				Map<String, Object> updatesybz = new HashMap<String, Object>(16);
				updatesybz.put("JLXH", llyzxh);
                cisHzyzDao.updateUseLogoStatus(updatesybz);
				if (tj01list.size() <= 0) {// 如果提交表里没有就插入一条
					//主表NIS_TJ01
					Map<String, Object> tj01map = new HashMap<String, Object>(16);
					tj01map.put("TJYF", llyfsb);
					tj01map.put("YZLX", llxmlx);
					tj01map.put("TJSJ", new Timestamp(ldttjsj.getTime()));
					tj01map.put("TJBQ", llbrbq);
					tj01map.put("TJGH", uid);
					tj01map.put("XMLX", 1);
					tj01map.put("FYBZ", 0);
					tj01map.put("FYFS", llfyfs);
					tj01map.put("LSYZ", lllsyz);
					tj01map.put("JGID", manaUnitId);
					
					Integer tjxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ01);
					NisTj01 nisTj01 = BeanUtil.fillBeanWithMapIgnoreCase(tj01map, new NisTj01(), true);
					nisTj01.setTjxh(tjxh);
					nisTj01Dao.insert(nisTj01);
					tj01map.put("TJXH", tjxh);// 放主键
					tj01list.add(tjindx, tj01map);
					// 明细表NIS_TJ02
					if (llxmlx == 2 || llxmlx == 3) {
						Map<String, Object> tj02map = new HashMap<String, Object>(16);
						//取主键
						tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(tjindx).get("TJXH")));
						tj02map.put("YZXH", llyzxh);
						tj02map.put("ZYH", llzyh);
						tj02map.put("YPXH", llypxh);
						tj02map.put("YPCD", llypcd);
						tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
						tj02map.put("YCSL", ldycsl);
						tj02map.put("YTCS", llmrcs);
						tj02map.put("JFRQ", new Timestamp(System.currentTimeMillis()));
						tj02map.put("SYPC", lssypc);
						tj02map.put("YPDJ", ldypdj);
						tj02map.put("YFDW", "null".equals(lsyfdw) ? ""
								: lsyfdw);
						tj02map.put("YFGG", "null".equals(lsyfgg) ? ""
								: lsyfgg);
						tj02map.put("YFBZ", llyfbz);
						tj02map.put("QRRQ", new Timestamp(System.currentTimeMillis()));
						tj02map.put("LSYZ", lllsyz);
						tj02map.put("FYJE", 0);
						tj02map.put("FYBZ", 0);
						tj02map.put("QZCL", 0);
						tj02map.put("SJFYBZ", 0);
						tj02map.put("FYSL", ldycsl);
						tj02map.put("JGID", manaUnitId);
						tj02map.put("YEPB", llyepb);
						tj02map.put("FYKS", llbrks);
						tj02map.put("TJYF", llyfsb);
						tj02map.put("FYFS", llfyfs);
						tj02map.put("YZLX", llxmlx);
						tj02map.put("BQYZ", 0L);
						tj02map.put("QRBZ", 0);
						Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
						NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
						nisTj02.setJlxh(jlxh);
						if(lllsyz == 0) {
							PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
							kcdjNew.setJgid(manaUnitId);
							kcdjNew.setYwlb(2);
							kcdjNew.setJlxh(jlxh);
							kcdjNew.setYpxh(llypxh);
							kcdjNew.setYpcd(llypcd);
							kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
							kcdjNew.setDjsj(DateUtil.date().toTimestamp());
							kcdjNew.setYfsb(llyfsb);
							kcdjList.add(kcdjNew);
						}
						nisTj02Dao.insert(nisTj02);
					} else {
						for (int lii = 0; lii < llfycstotal; lii++) {
							Map<String, Object> tj02map = new HashMap<String, Object>(16);
							//取主键
							tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(tjindx).get("TJXH")));
							tj02map.put("YZXH", llyzxh);
							tj02map.put("ZYH", llzyh);
							tj02map.put("YPXH", llypxh);
							tj02map.put("YPCD", llypcd);
							tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
							tj02map.put("YCSL", ldycsl);
							tj02map.put("YTCS", llmrcs);
							tj02map.put("JFRQ", new Timestamp(qrsjlist.get(lii).getTime()));
							tj02map.put("SYPC", lssypc);
							tj02map.put("YPDJ", ldypdj);
							tj02map.put("YFDW", "null".equals(lsyfdw) ? ""
									: lsyfdw);
							tj02map.put("YFGG", "null".equals(lsyfgg) ? ""
									: lsyfgg);
							tj02map.put("YFBZ", llyfbz);
							tj02map.put("QRRQ", new Timestamp(qrsjlist.get(lii).getTime()));
							tj02map.put("LSYZ", lllsyz);
							tj02map.put("FYJE", 0);
							tj02map.put("FYBZ", 0);
							tj02map.put("QZCL", 0);
							tj02map.put("SJFYBZ", 0);
							tj02map.put("FYSL", ldycsl);
							tj02map.put("JGID", manaUnitId);
							tj02map.put("YEPB", llyepb);
							tj02map.put("FYKS", llbrks);
							tj02map.put("TJYF", llyfsb);
							tj02map.put("FYFS", llfyfs);
							tj02map.put("YZLX", llxmlx);
							tj02map.put("BQYZ", 0L);
							tj02map.put("QRBZ", 0);
							Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
							NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
							nisTj02.setJlxh(jlxh);
							nisTj02Dao.insert(nisTj02);
							if(lllsyz == 0) {
								PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
								kcdjNew.setJgid(manaUnitId);
								kcdjNew.setYwlb(2);
								kcdjNew.setJlxh(jlxh);
								kcdjNew.setYpxh(llypxh);
								kcdjNew.setYpcd(llypcd);
								kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
								kcdjNew.setDjsj(DateUtil.date().toTimestamp());
								kcdjNew.setYfsb(llyfsb);
								kcdjList.add(kcdjNew);
							}
						}
					}
					tjindx++;
				} else {
					for (int j = 0; j < tj01list.size(); j++) {
						if (tj01list.get(j).get("TJYF").toString()
								.equals(llyfsb + "")
								&& tj01list.get(j).get("YZLX").toString()
										.equals(llxmlx + "")
								&& tj01list.get(j).get("FYFS").toString()
										.equals(llfyfs + "")
								&& tj01list.get(j).get("LSYZ").toString()
										.equals(lllsyz + "")) {
							// 明细表NIS_TJ02
							if (llxmlx == 2 || llxmlx == 3) {
								Map<String, Object> tj02map = new HashMap<String, Object>(16);
								//如果相等就直接取此条的主键
								tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(j).get("TJXH")));
								tj02map.put("YZXH", llyzxh);
								tj02map.put("ZYH", llzyh);
								tj02map.put("YPXH", llypxh);
								tj02map.put("YPCD", llypcd);
								tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
								tj02map.put("YCSL", ldycsl);
								tj02map.put("YTCS", llmrcs);
								tj02map.put("JFRQ", new Timestamp(System.currentTimeMillis()));
								tj02map.put("SYPC", lssypc);
								tj02map.put("YPDJ", ldypdj);
								tj02map.put("YFDW", lsyfdw);
								tj02map.put("YFGG", lsyfgg);
								tj02map.put("YFBZ", llyfbz);
								tj02map.put("QRRQ", new Timestamp(System.currentTimeMillis()));
								tj02map.put("LSYZ", lllsyz);
								tj02map.put("FYJE", 0);
								tj02map.put("FYBZ", 0);
								tj02map.put("QZCL", 0);
								tj02map.put("SJFYBZ", 0);
								tj02map.put("FYSL", ldycsl);
								tj02map.put("JGID", manaUnitId);
								tj02map.put("YEPB", llyepb);
								tj02map.put("FYKS", llbrks);
								tj02map.put("TJYF", llyfsb);
								tj02map.put("FYFS", llfyfs);
								tj02map.put("YZLX", llxmlx);
								tj02map.put("BQYZ", 0L);
								tj02map.put("QRBZ", 0);
								Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
								NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
								nisTj02.setJlxh(jlxh);
								nisTj02Dao.insert(nisTj02);
								if(lllsyz == 0) {
									PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
									kcdjNew.setJgid(manaUnitId);
									kcdjNew.setYwlb(2);
									kcdjNew.setJlxh(jlxh);
									kcdjNew.setYpxh(llypxh);
									kcdjNew.setYpcd(llypcd);
									kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
									kcdjNew.setDjsj(DateUtil.date().toTimestamp());
									kcdjNew.setYfsb(llyfsb);
									kcdjList.add(kcdjNew);
								}
							} else {
								for (int lii = 0; lii < llfycstotal; lii++) {
									Map<String, Object> tj02map = new HashMap<String, Object>(16);
									//如果相等就直接取此条的主键
									tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(j).get("TJXH")));
									tj02map.put("YZXH", llyzxh);
									tj02map.put("ZYH", llzyh);
									tj02map.put("YPXH", llypxh);
									tj02map.put("YPCD", llypcd);
									tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
									tj02map.put("YCSL", ldycsl);
									tj02map.put("YTCS", llmrcs);
									tj02map.put("JFRQ", new Timestamp(qrsjlist.get(lii).getTime()));
									tj02map.put("SYPC", lssypc);
									tj02map.put("YPDJ", ldypdj);
									tj02map.put("YFDW", lsyfdw);
									tj02map.put("YFGG", lsyfgg);
									tj02map.put("YFBZ", llyfbz);
									tj02map.put("QRRQ", new Timestamp(qrsjlist.get(lii).getTime()));
									tj02map.put("LSYZ", lllsyz);
									tj02map.put("FYJE", 0);
									tj02map.put("FYBZ", 0);
									tj02map.put("QZCL", 0);
									tj02map.put("SJFYBZ", 0);
									tj02map.put("FYSL", ldycsl);
									tj02map.put("JGID", manaUnitId);
									tj02map.put("YEPB", llyepb);
									tj02map.put("FYKS", llbrks);
									tj02map.put("TJYF", llyfsb);
									tj02map.put("FYFS", llfyfs);
									tj02map.put("YZLX", llxmlx);
									tj02map.put("BQYZ", 0L);
									tj02map.put("QRBZ", 0);
									Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
									NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
									nisTj02.setJlxh(jlxh);
									nisTj02Dao.insert(nisTj02);	
									if(lllsyz == 0) {
										PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
										kcdjNew.setJgid(manaUnitId);
										kcdjNew.setYwlb(2);
										kcdjNew.setJlxh(jlxh);
										kcdjNew.setYpxh(llypxh);
										kcdjNew.setYpcd(llypcd);
										kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
										kcdjNew.setDjsj(DateUtil.date().toTimestamp());
										kcdjNew.setYfsb(llyfsb);
										kcdjList.add(kcdjNew);
									}}
							}
							eqorne = 1;
						}
					}
					if (eqorne == 0) {
						// 如果不想等 继续插入主表明细表
						// 主表NIS_TJ01
						Map<String, Object> tj01map = new HashMap<String, Object>(16);
						tj01map.put("TJYF", llyfsb);
						tj01map.put("YZLX", llxmlx);
						tj01map.put("TJSJ", new Timestamp(ldttjsj.getTime()));
						tj01map.put("TJBQ", llbrbq);
						tj01map.put("TJGH", uid);
						tj01map.put("XMLX", 1);
						tj01map.put("FYBZ", 0);
						tj01map.put("FYFS", llfyfs);
						tj01map.put("LSYZ", lllsyz);
						tj01map.put("JGID", manaUnitId);
						Integer tjxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ01);
						NisTj01 nisTj01 = BeanUtil.fillBeanWithMapIgnoreCase(tj01map, new NisTj01(), true);
						nisTj01.setTjxh(tjxh);
						nisTj01Dao.insert(nisTj01);
						tj01map.put("TJXH", tjxh);
						tj01list.add(tjindx, tj01map);
						// 明细表NIS_TJ02
						if ( llxmlx == 2|| llxmlx == 3) {
							Map<String, Object> tj02map = new HashMap<String, Object>(16);
							tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(tjindx).get("TJXH")));
							tj02map.put("YZXH", llyzxh);
							tj02map.put("ZYH", llzyh);
							tj02map.put("YPXH", llypxh);
							tj02map.put("YPCD", llypcd);
							tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
							tj02map.put("YCSL", ldycsl);
							tj02map.put("YTCS", llmrcs);
							tj02map.put("JFRQ", new Timestamp(System.currentTimeMillis()));
							tj02map.put("SYPC", lssypc);
							tj02map.put("YPDJ", ldypdj);
							tj02map.put("YFDW", lsyfdw);
							tj02map.put("YFGG", lsyfgg);
							tj02map.put("YFBZ", llyfbz);
							tj02map.put("QRRQ", new Timestamp(System.currentTimeMillis()));
							tj02map.put("LSYZ", lllsyz);
							tj02map.put("FYJE", 0);
							tj02map.put("FYBZ", 0);
							tj02map.put("QZCL", 0);
							tj02map.put("SJFYBZ", 0);
							tj02map.put("FYSL", ldycsl);
							tj02map.put("JGID", manaUnitId);
							tj02map.put("YEPB", llyepb);
							tj02map.put("FYKS", llbrks);
							tj02map.put("TJYF", llyfsb);
							tj02map.put("FYFS", llfyfs);
							tj02map.put("YZLX", llxmlx);
							tj02map.put("BQYZ", 0L);
							tj02map.put("QRBZ", 0);
							Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
							NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
							nisTj02.setJlxh(jlxh);
							nisTj02Dao.insert(nisTj02);
							if(lllsyz == 0) {
								PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
								kcdjNew.setJgid(manaUnitId);
								kcdjNew.setYwlb(2);
								kcdjNew.setJlxh(jlxh);
								kcdjNew.setYpxh(llypxh);
								kcdjNew.setYpcd(llypcd);
								kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
								kcdjNew.setDjsj(DateUtil.date().toTimestamp());
								kcdjNew.setYfsb(llyfsb);
								kcdjList.add(kcdjNew);
							}
						} else {
							for (int lii = 0; lii < llfycstotal; lii++) {
								Map<String, Object> tj02map = new HashMap<String, Object>(16);
								tj02map.put("TJXH", ObjectToTypes.parseInt(tj01list.get(tjindx).get("TJXH")));
								tj02map.put("YZXH", llyzxh);
								tj02map.put("ZYH", llzyh);
								tj02map.put("YPXH", llypxh);
								tj02map.put("YPCD", llypcd);
								tj02map.put("KSSJ", new Timestamp(ldtkssj.getTime()));
								tj02map.put("YCSL", ldycsl);
								tj02map.put("YTCS", llmrcs);
								tj02map.put("JFRQ", new Timestamp(qrsjlist.get(lii).getTime()));
								tj02map.put("SYPC", lssypc);
								tj02map.put("YPDJ", ldypdj);
								tj02map.put("YFDW", lsyfdw);
								tj02map.put("YFGG", lsyfgg);
								tj02map.put("YFBZ", llyfbz);
								tj02map.put("QRRQ", new Timestamp(qrsjlist.get(lii).getTime()));
								tj02map.put("LSYZ", lllsyz);
								tj02map.put("FYJE", 0);
								tj02map.put("FYBZ", 0);
								tj02map.put("QZCL", 0);
								tj02map.put("SJFYBZ", 0);
								tj02map.put("FYSL", ldycsl);
								tj02map.put("JGID", manaUnitId);
								tj02map.put("YEPB", llyepb);
								tj02map.put("FYKS", llbrks);
								tj02map.put("TJYF", llyfsb);
								tj02map.put("FYFS", llfyfs);
								tj02map.put("YZLX", llxmlx);
								tj02map.put("BQYZ", 0L);
								tj02map.put("QRBZ", 0);
								Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_TJ02);
								NisTj02 nisTj02 = BeanUtil.fillBeanWithMapIgnoreCase(tj02map, new NisTj02(), true);
								nisTj02.setJlxh(jlxh);
								nisTj02Dao.insert(nisTj02);
								if(lllsyz == 0) {
									PubPharKcdjNew kcdjNew = new PubPharKcdjNew();
									kcdjNew.setJgid(manaUnitId);
									kcdjNew.setYwlb(2);
									kcdjNew.setJlxh(jlxh);
									kcdjNew.setYpxh(llypxh);
									kcdjNew.setYpcd(llypcd);
									kcdjNew.setYpsl(ObjectToTypes.parseBigDecimal(ldycsl));
									kcdjNew.setDjsj(DateUtil.date().toTimestamp());
									kcdjNew.setYfsb(llyfsb);
									kcdjList.add(kcdjNew);
								}
							}
						}
						tjindx++;
					}
				}
			}
			
			List<Integer> ztJlxhList = collardrugdetailslist.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.get("YPLX"))) 
					&& "1".equals(ObjectToTypes.parseString(o.get("ZTBZ")))).map(o -> ObjectToTypes.parseInt(o.get("ZTYZJLXH")))
					.distinct().collect(Collectors.toList());
			if(!ztJlxhList.isEmpty()) {
				cisHzyzZtDao.updateZtUseLogoStatus(1, ztJlxhList);
			}
			
			if(!kcdjList.isEmpty()) {
				pubDrugKcdjSerImpl.frozen(kcdjList);

			}
			
			return  bqTjMsgResp;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00026");
		}
	}
	
	/**
	 * 校验抗菌药物是否审批
	 *
	 */
	public List<String> doCheckAntibacterial(List<Map<String, Object>> list, SysUser user){
		List<String> errMsg = new ArrayList<String>();
		for(Map<String, Object> map : list) {
			String kjywsp = ObjectToTypes.parseString(map.get("KJYWSP"));
			Integer sqid = ObjectToTypes.parseInt(map.get("SQID"));
			if("1".equals(kjywsp)) {
				Map<String, Object> parameters = new HashMap<String, Object>(16);
				parameters.put("sqdh", sqid);
				parameters.put("jgid", user.getHospitalId());
				parameters.put("ypxh", ObjectToTypes.parseInt(map.get("YPXH")));
				parameters.put("ypcd", ObjectToTypes.parseInt(map.get("YPCD")));
				parameters.put("kssj", DateUtil.parse(map.get("KSSJ") + "", DatePattern.NORM_DATE_PATTERN).toSqlDate());
				long count = amqcKjywsysqypService.checkDaysKjyw(parameters);
				if(count > 0) {
					ReturnEntity<String> returnEntity = new ReturnEntity<String>();		  
					returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00031", 
							new String[]{ObjectToTypes.parseString(map.get("BRXM")),
									ObjectToTypes.parseString(map.get("YZMC"))});
					errMsg.add(returnEntity.getMessage());
				}
			}
		}
		return errMsg;
	}
	
	/**
	* @Title: doSssThqr
	* @Description: TODO 药品医嘱退回确认
	* @param @param jlxh
	* @param @param fymxjlxh
	* @param @param user
	* @param @return    设定文件
	* @return     返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doSssThqr(Integer jlxh, Integer fymxjlxh, SysUser user)  {
		Date date = new Date();
		Integer uid = user.getUserId();
			//判断是不是出院状态
		ImHzry imHzry = imHzryDao.queryDischargediscrimination(jlxh);
		if(imHzry != null && !"0".equals(ObjectToTypes.parseString(imHzry.getCypb()))){
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00029");
		}else{
			NisTj02 nisTj02 = new NisTj02();
			nisTj02.setQrsj(new Timestamp(date.getTime()));
			nisTj02.setQrgh(ObjectToTypes.parseString(uid));
			nisTj02.setJlxh(jlxh);
			nisTj02Dao.updateSubmit02Status(nisTj02);
			imFeeFymxDao.deleteById(fymxjlxh);
		}
	}
	
	/**
	 * 药品项目助手调入常用药或全部药品
	 * @Title: doLoadMedicineInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCommonReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return CisHzyzCommonResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public CisHzyzCommonResp doLoadMedicineInfo(CisHzyzCommonReq cisHzyzCommonReq,
			SysUser user) {
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(cisHzyzCommonReq);
        Integer jgid = user.getHospitalId();
        Integer ypxh = ObjectToTypes.parseInt(body.get("ypxh"));
        // 发药药房
        String tabId = ObjectToTypes.parseString(body.get("tabId"));
        Integer wardId = ObjectToTypes.parseInt(body.get("wardId"));
        String adviceType = ObjectToTypes.parseString(body.get("adviceType"));
        Integer type = 1;
        //急诊用药tab
        if ("jz".equals(adviceType)) {
            type = 2;
		//出院用药tab
		} else if ("cy".equals(adviceType)) {
			type = 3;
		}
		//药品产地选择方式,库存序号
		Map<String, Object> med = null;
		if ("medicineCommon".equals(tabId)) {
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("jlbh", ObjectToTypes.parseInt(body.get("jlbh")));
			parameters.put("orderBy", "ZTBH");
			List<Map<String, Object>> list = opZt02Service.queryCommonDrugsInfoByJlbh(parameters);
			if(CollectionUtils.isNotEmpty(list)) {
				med = list.get(0);
			}
		}

		DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq = new DrugsTypkBqyzYpSrfReq();
		drugsTypkBqyzYpSrfReq.setBqdm(wardId);
		drugsTypkBqyzYpSrfReq.setJgid(jgid);
		drugsTypkBqyzYpSrfReq.setYfsz(type);
		List<Map<String, Object>> yfsbs = nisFyyfDao.queryYfsb(drugsTypkBqyzYpSrfReq);
		for (int i = 0; i < yfsbs.size(); i++) {
			Integer pharmacyId = ObjectToTypes.parseInt(yfsbs.get(i).get("YFSB"));
			QueryDrugInfoReq queryDrugInfoReq = new QueryDrugInfoReq();
			queryDrugInfoReq.setYfsb(pharmacyId);
			queryDrugInfoReq.setYpxh(ypxh);
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLR_BZLX);
			String bzlx = sysXtcs == null ? "1" : sysXtcs.getCsz();
			queryDrugInfoReq.setBzlx(ObjectToTypes.parseInt(bzlx));
			List<Map<String, Object>> meds = drugsTypkOutSer.queryDrugInformation(queryDrugInfoReq);
			if (meds.size() > 0) {//多产地取第一条记录
				Map<String, Object> ztMed = meds.get(0);
				if ("medicineCommon".equals(tabId)) {
					ztMed.putAll(med);
				}
				Integer fygb = getfygb(ObjectToTypes.parseInt(ztMed.get("TYPE")), ypxh);
				ztMed.put("FYGB", fygb);
				ztMed.put("YFSB", pharmacyId);
				if ("medicineCommon".equals(tabId) && StrUtil.isEmpty(ztMed.get("SYPC")+"")) {
					throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00039", 
							new String[] {ObjectToTypes.parseString(ztMed.get("YPMC"))});

				}
				return BeanUtil.fillBeanWithMapIgnoreCase(ztMed, new CisHzyzCommonResp(), true);
			} else {
				if (i == yfsbs.size() - 1) {
					throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00038");
				}
			}
		}	
		return null;
	}
	
	/**
	 * 药品医嘱组套调入
	 * @Title: doLoadAdviceSet
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzStackReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<CisHzyzCommonResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyzCommonResp> doLoadAdviceSet(CisHzyzStackReq cisHzyzStackReq,
			SysUser user) {
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(cisHzyzStackReq);
        Integer manageUnit = user.getHospitalId();
        List<CisHzyzCommonResp> listCommon = new ArrayList<CisHzyzCommonResp>();
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        Integer ztbh = ObjectToTypes.parseInt(body.get("ZTBH"));
        Integer brxz = ObjectToTypes.parseInt(body.get("BRXZ"));
        Integer zyh = ObjectToTypes.parseInt(body.get("ZYH"));
        Integer wardId = ObjectToTypes.parseInt(body.get("wardId"));
        Integer ztlb = ObjectToTypes.parseInt(body.get("ZTLB"));
        String adviceType = ObjectToTypes.parseString(body.get("adviceType"));
        Integer type = 1;
		if ("jz".equals(adviceType)) {
			type = 2;
		} else if ("cy".equals(adviceType)) {
			type = 3;
		}

		// 发药药房 和 药品类别
		if (ztlb < 4) {
            Map<String, Object> parameters = new HashMap<String, Object>(16);
            parameters.put("ztbh", ztbh);
            parameters.put("orderBy", "YPZH,JLBH");
            List<Map<String, Object>> list = opZt02Service.queryCommonDrugsInfoByJlbh(parameters);
            listCommon = BUHISUtil.ListToResultSet(list, new CisHzyzCommonResp());
            for (CisHzyzCommonResp obj : listCommon) {
                Integer ypxh = obj.getYpxh();
                // 根据包装类型区分查询的对象
                SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLR_BZLX);
                String bzlx = sysXtcs == null ? "1" : sysXtcs.getCsz();
                QueryDrugInfoReq queryDrugInfoReq = new QueryDrugInfoReq();
                queryDrugInfoReq.setJgid(manageUnit);
                queryDrugInfoReq.setYpxh(ypxh);
                queryDrugInfoReq.setBzlx(ObjectToTypes.parseInt(bzlx));
				
				if (!"2".equals(bzlx)) {
					queryDrugInfoReq.setType(type);
				}
				
				List<CisHzyzYpcdDetailResp> meds = drugsTypkOutSer.queryZyYpcdInfo(queryDrugInfoReq);
				List<CisHzyzYpcdDetailResp> ypcdList = new ArrayList<CisHzyzYpcdDetailResp>();
				if (CollectionUtils.isNotEmpty(meds)) {// 取第一条记录
					for(CisHzyzYpcdDetailResp zyyp : meds) {
						// 获取组套中药品自负比例
						SkinPsjlCheckReq skinPsjlCheckReq = new SkinPsjlCheckReq();
						skinPsjlCheckReq.setYplx(zyyp.getYplx());
						skinPsjlCheckReq.setYpxh(zyyp.getYpxh());
						skinPsjlCheckReq.setBrxz(brxz);
						skinPsjlCheckReq.setZyh(zyh);
						skinPsjlCheckReq.setBrid(ObjectToTypes.parseInt(body.get("BRID")));
						SkinPsjlCheckResp skinPsjlCheckResp = imHzryByDctworkSer.doLoadDetailsInfo(skinPsjlCheckReq);
						zyyp.setZfbl(skinPsjlCheckResp.getZfbl());
						zyyp.setAllergy(skinPsjlCheckResp.isAllergy());
						zyyp.setBlfy(skinPsjlCheckResp.getBlfy());
						if(ypxh.intValue() == zyyp.getYpxh().intValue()) {
							ypcdList.add(zyyp);
						}
					}
				}
				obj.setYpcdList(ypcdList);
			}
		} 
		return listCommon;
	}
	
	
	/**
	 * 项目常用项和全部项目调入
	 * @Title: doLoadClinicInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzProjectReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return CisHzyzProjectResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public CisHzyzProjectResp doLoadClinicInfo(CisHzyzProjectReq cisHzyzProjectReq,
			SysUser user) {
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(cisHzyzProjectReq);
        Integer jgid = user.getHospitalId();
        String tabId = ObjectToTypes.parseString(body.get("tabId"));
        Integer brxz = ObjectToTypes.parseInt(body.get("BRXZ"));
        Map<String, Object> clinic = null;
        if ("clinicCommon".equals(tabId)) {
            OpZt02 opZt02 = opZt02Service.getById(ObjectToTypes.parseInt(body.get("JLBH")));
            clinic = BUHISUtil.caseInsensitiveMap(opZt02);
        }
        FeeYlsfxmProjectSrfApiReq feeYlsfxmProjectSrfApiReq = new FeeYlsfxmProjectSrfApiReq();
        feeYlsfxmProjectSrfApiReq.setJgid(jgid);
		feeYlsfxmProjectSrfApiReq.setFyxh(ObjectToTypes.parseInt(body.get("FYXH")));
		feeYlsfxmProjectSrfApiReq.setBrxz(brxz);
		List<FeeYlsfxmProjectSrfApiResp> feeYlsfxmProjectSrfRespList = feeYlsfxmOutSer.queryProjectOrder(feeYlsfxmProjectSrfApiReq);
		Map<String, Object> ztclinic = null;
		if(CollectionUtils.isNotEmpty(feeYlsfxmProjectSrfRespList)) {
            ztclinic = BUHISUtil.caseInsensitiveMap(feeYlsfxmProjectSrfRespList.get(0));
        }
		if ("clinicCommon".equals(tabId)) {
			if (clinic != null && ztclinic!= null) {
				ztclinic.putAll(clinic);
			}
		}
		return BeanUtil.fillBeanWithMapIgnoreCase(ztclinic, new CisHzyzProjectResp(), true);
	}
	
	/**
	 * 项目组套调入保存
	 * @Title: doSaveWarDisposalSet
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzXmImportReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer doSaveWarDisposalSet(CisHzyzXmImportReq cisHzyzXmImportReq, SysUser user) {

        ZtBody ztBody = cisHzyzXmImportReq.getZtBody();
        Map<String, Object> record = BUHISUtil.caseInsensitiveMap(ztBody);
        Integer ztbh = ObjectToTypes.parseInt(record.get("ZTBH")); //组套编号
        Integer brxz = cisHzyzXmImportReq.getBrxz();

        List<Map<String, Object>> details = null;
        Map<String, Object> detailsParameter = new HashMap<String, Object>(16);
        detailsParameter.put("ztbh", ztbh);
        detailsParameter.put("brxz", brxz);
        //包含商保性质
        details = opZt02Service.queryPackageDetailsByZtbh(detailsParameter);
		
		//判断项目是否作废，若作废不能保存并给出提示
		String zfxmMc = "";
		for (Map<String, Object> xm : details) {
			FeeYlsfxmdjApiReq feeYlsfxmdj = new FeeYlsfxmdjApiReq();
			feeYlsfxmdj.setFyxh(ObjectToTypes.parseInt(xm.get("XMBH")));
			feeYlsfxmdj.setZfpb(1);
			List<FeeYlsfxmdjModel> feeYlsfxmdjList= feeYlsfxmdjService.findByEntity(feeYlsfxmdj);
			if (CollectionUtils.isNotEmpty(feeYlsfxmdjList)) {
				zfxmMc += ObjectToTypes.parseString(xm.get("YZMC")) + ", ";
				xm.put("FYKS", feeYlsfxmdjList.get(0).getFyks());
			}

		}
		if (zfxmMc.length() > 0) {
			zfxmMc = zfxmMc.substring(0, zfxmMc.lastIndexOf(","));
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_00023", new String[] {
					zfxmMc
			});
		}
		// 保存医嘱信息
		List<Map<String, Object>> yzxxList = new ArrayList<Map<String, Object>>();
		Map<String, Object> yzxx = new HashMap<String, Object>(16);// 医嘱信息
		
		// 检验组套 如果该组套项目全部选中，就向CIS_HZYZ_ZT表中存储一条数据
		Map<String, Object> ztMap = new HashMap<String, Object>(16);
		
		// 组号 同一组套的项目全选时，医嘱存为同一组
		int yzzhShow = 1;
		
		// 查询st对应的执行时间
		DicSypcModel dicSypc  = dicSypcService.getById("st");
		String zxsj = "";
		if (dicSypc != null && StrUtil.isNotBlank(dicSypc.getZxsj())) {
			zxsj = dicSypc.getZxsj();
		}
		
		int yzpx = 1;// 医嘱排序
		
		// 该组套的项目全部选中
		double ypdj = 0;
		for (Map<String, Object> xm : details) {
			ypdj += ObjectToTypes.parseDouble(xm.get("FYDJ")) * ObjectToTypes.parseDouble(xm.get("XMSL"));
		}
		//组套明细名称一起保存
		StringBuffer yzmcStr = new StringBuffer();
		List<OpZt02Resp> opZt02RespList = opZt02Service.findByZtbhXm(ztbh);
		for(OpZt02Resp opZt02Resp : opZt02RespList) {
			yzmcStr.append(opZt02Resp.getXmmc()).append("+");
		}
		yzmcStr.deleteCharAt(yzmcStr.length() - 1);
		// 向CIS_HZYZ_ZT表中存储一条数据
		Map<String, Object> ztxxMap = new HashMap<String, Object>(16);// 组套信息
		// 赋值医嘱信息
//		ztxxMap.put("YZLX", 0); // 组套医嘱类型 0检查医嘱，1检验医嘱
		ztxxMap.put("JGID", user.getHospitalId());// 机构ID
		ztxxMap.put("ZYH", cisHzyzXmImportReq.getZyh());// 住院号
		ztxxMap.put("YZMC", ObjectToTypes.parseString(record.get("ZTMC")) + "(" + yzmcStr.toString() + ")");// 药嘱名称
		ztxxMap.put("YPXH", ztbh);// 药品序号
		ztxxMap.put("YPCD", 0);// 药品产地
		ztxxMap.put("XMLX", 5);// 项目类型 4检验,5检查,6手术,7治疗,8护理,9饮食,10卫材,99其他
		ztxxMap.put("YPLX", 0);// 药品类型
		ztxxMap.put("MRCS", 1);// 每日次数
		ztxxMap.put("YCJL", 0.000);// 一次剂量
		ztxxMap.put("YCSL", 1.0000);// 一次数量
		ztxxMap.put("MZCS", 0);// 每周次数

		ztxxMap.put("KSSJ", DateUtil.date().toTimestamp());// 开始时间
		ztxxMap.put("TZSJ", DateUtil.date().toTimestamp());// 停嘱时间
		// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
		ztxxMap.put("SYPC", "st");
		ztxxMap.put("YZZXSJ", zxsj);

		ztxxMap.put("YPDJ", ObjectToTypes.parseBigDecimal(ypdj));// 药品单价
		ztxxMap.put("YPYF", 0L);// 药品用法
		ztxxMap.put("YSGH", user.getUserId());// 开嘱医生
		ztxxMap.put("TZYS", user.getUserId());// 停嘱医生
		ztxxMap.put("CZGH", user.getUserId());// 操作工号
		ztxxMap.put("SYBZ", 0);// 使用标志
		// 输入科室
		ztxxMap.put("SRKS", cisHzyzXmImportReq.getBrks() == null ? 0 : cisHzyzXmImportReq.getBrks());
		ztxxMap.put("ZFPB", 1);// 自负判别------------
		ztxxMap.put("YJZX", 0);// 医技主项
		ztxxMap.put("YJXH", 0);// 医技序号
		// 执行科室
		ztxxMap.put("ZXKS", cisHzyzXmImportReq.getBrks());
		ztxxMap.put("YZZH", yzzhShow++);// 医嘱组号---------
		ztxxMap.put("FYSX", 0);// 发药属性
		ztxxMap.put("YEPB", 0);// 婴儿判别
		ztxxMap.put("YFSB", 0L);// 药房识别
		ztxxMap.put("LSYZ", 1);// 临时医嘱
		ztxxMap.put("LSBZ", 0);// 历史标志
		ztxxMap.put("YZPB", 0);// 医嘱判别
		ztxxMap.put("JFBZ", 2);// 计费标志--------------
		ztxxMap.put("TPN", 0);// TPN
		ztxxMap.put("YSBZ", 1);// 医生医嘱标志
		ztxxMap.put("YSTJ", 0);// 医生提交标志

		ztxxMap.put("ZFBZ", 0);// 作废标志
		ztxxMap.put("SRCS", 0);// 首日次数
		ztxxMap.put("SFJG", 0);// 审方结果
		ztxxMap.put("BRKS", cisHzyzXmImportReq.getBrks());// 病人科室
		ztxxMap.put("BRBQ", cisHzyzXmImportReq.getBrbq());// 病人病区
		ztxxMap.put("BRCH", cisHzyzXmImportReq.getBrch());// 病人床号
		ztxxMap.put("FHBZ", 0);// 复核标志
		ztxxMap.put("TZFHBZ", 0);// 停嘱复核标志
		ztxxMap.put("PSPB", 0);// 皮试判别
		ztxxMap.put("CFTS", 0);// 贴数
		ztxxMap.put("YPZS", 1);// 煎法
		ztxxMap.put("JZ", 1);// 脚注
		ztxxMap.put("ZFYP", 0);// 自备药标识
		ztxxMap.put("ZTBZ", 1);// 组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志
		ztxxMap.put("YZPX", yzpx++); // 医嘱排序
		
		Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
		ztxxMap.put("JLXH", jlxh);
		ztxxMap.put("YZZH", redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ_ZT +".yzzh"));
		CisHzyzZt cisHzyzZt = BeanUtil.fillBeanWithMapIgnoreCase(ztxxMap, new CisHzyzZt(), true);
		cisHzyzZtDao.insert(cisHzyzZt);
		
		for (Map<String, Object> xm : details) {
			ztMap.put(xm.get("XMBH").toString(), jlxh);
		}
		
		for (int i = 0; i < details.size(); i++) {
			yzxx = new HashMap<String, Object>(16);// 医嘱信息
			if (!ztMap.containsKey(details.get(i).get("XMBH").toString())) {
				yzxx.put("ZTBZ", 0);// 组套标志 非组套医嘱
				yzzhShow += 1;
			} else {
				yzxx.put("ZTBZ", 1);// 组套标志 组套医嘱
				yzxx.put("ZTYZJLXH", ztMap.get(details.get(i).get("XMBH").toString()));
			}
			// 赋值医嘱信息
			yzxx.put("opStatus", "create");
			yzxx.put("ZFYP", 0);// 自备药标识
			yzxx.put("YSTJ", 0);// 医生提交标志
			yzxx.put("YCSL", details.get(i).get("XMSL"));// 一次数量
			yzxx.put("MRCS", 1);// 每日次数
			yzxx.put("CZGH", user.getUserId());// 操作工号
			yzxx.put("YSBZ", 1);// 医生医嘱标志
			yzxx.put("SRCS", 0);// 首日次数

			// 获取病人自负判别
			Integer ypxh = ObjectToTypes.parseInt(details.get(i).get("XMBH"));// 费用序号
			Integer fygb = getfygb(0, ypxh);// 获取费用归并
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("BRXZ", brxz);// 病人性质
			params.put("FYGB", fygb);// 费用归并
			params.put("TYPE", 0);// 药品类型 0=项目
			params.put("FYXH", ypxh);// 费用序号
			Map<String, Object> zfblMap = getPayProportion(params);
			if (ObjectToTypes.parseDouble(zfblMap.get("ZFBL")) > 0) {
				yzxx.put("ZFPB", 1);
			} else {
				yzxx.put("ZFPB", 0);
			}

			yzxx.put("YPLX", 0);// 药品类型
			yzxx.put("SYBZ", 0);// 使用标志
			yzxx.put("MZCS", 0);// 每周次数
			yzxx.put("XMLX", 5);// 项目类型 4检验,5检查,6手术,7治疗,8护理,9饮食,10卫材,99其他

			// 项目类型
			yzxx.put("JFBZ", 2);// 计费标志---------------------
			yzxx.put("ZFBZ", 0);// 作废标志
			yzxx.put("SFJG", 0);// 审方结果
			yzxx.put("FHBZ", 0);// 复核标志
			yzxx.put("TZFHBZ", 0);// 停嘱复核标志
			yzxx.put("PSPB", 0);// 皮试判别
			yzxx.put("YZPB", 0);// 医嘱判别
			yzxx.put("LSBZ", 0);// 历史标志
			yzxx.put("YEPB", 0);// 婴儿判别
			yzxx.put("FYSX", 0);// 发药属性
			yzxx.put("TPN", 0);// TPN
			yzxx.put("LSYZ", 1);// 临时医嘱
			yzxx.put("CFTS", 0);// 贴数
			yzxx.put("YPZS", 1);// 煎法
			yzxx.put("JZ", 1);// 脚注
			yzxx.put("YPDJ", ObjectToTypes.parseBigDecimal(details.get(i).get("FYDJ")));// 药品单价
			// 医嘱组号
			yzxx.put("yzzhShow", yzzhShow);
			yzxx.put("YSGH", user.getUserId());// 开嘱医生
			yzxx.put("KSSJ", DateUtil.date().toTimestamp());// 开始时间
			yzxx.put("TZSJ", DateUtil.date().toTimestamp());// 停嘱时间
			// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
			yzxx.put("SYPC", "st");
			yzxx.put("YZZXSJ", zxsj);
			yzxx.put("JGID", user.getHospitalId());// 机构ID
			yzxx.put("ZYH", cisHzyzXmImportReq.getZyh());// 住院号
			yzxx.put("YPJL", 0);
			yzxx.put("YCJL", 0);// 一次剂量
            yzxx.put("YPXH", ObjectToTypes.parseInt(details.get(i).get("XMBH")));// 药品序号
            yzxx.put("YJXH", ObjectToTypes.parseInt(details.get(i).get("XMBH")));// 医技序号
            yzxx.put("YZMC", ObjectToTypes.parseString(details.get(i).get("YZMC")));// 药嘱名称
//            yzxx.put("YZLX", 0);
            // 执行科室
            Integer zxks = ObjectToTypes.parseInt(details.get(i).get("FYKS"));
            yzxx.put("ZXKS", zxks == null ? cisHzyzXmImportReq.getBrks() : zxks);
            yzxx.put("uniqueId", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            yzxxList.add(yzxx);
        }
        List<CisHzyzReq> cisHzyzList = BUHISUtil.ListToResultSet(yzxxList, new CisHzyzReq());
        List<CisHzyzReq> cisHzyzFjList = new ArrayList<CisHzyzReq>();
        CisHzyzSaveReq cisHzyzSaveReq = new CisHzyzSaveReq();
        cisHzyzSaveReq.setCisHzyzReqList(cisHzyzList);
        cisHzyzSaveReq.setZyh(cisHzyzXmImportReq.getZyh());
        cisHzyzSaveReq.setBrbq(cisHzyzXmImportReq.getBrbq());
        cisHzyzSaveReq.setBrch(cisHzyzXmImportReq.getBrch());
        cisHzyzSaveReq.setBrks(cisHzyzXmImportReq.getBrks());
        cisHzyzSaveReq.setBrxm(cisHzyzXmImportReq.getBrxm());
        cisHzyzSaveReq.setCisHzyzReqFjList(cisHzyzFjList);
        imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, user);
		return jlxh;
	}
	
	/**
	 * 项目组套多条调入后查询
	 * @Title: doLoadFromUsltPersonalSet
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzChooseProjectReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<CisHzyzChooseProjectResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyzChooseProjectResp> doLoadFromUsltPersonalSet(CisHzyzChooseProjectReq cisHzyzChooseProjectReq
			, SysUser user) {
		Integer manageUnit = user.getHospitalId();
		Integer brxz = cisHzyzChooseProjectReq.getBrxz();
		Integer zyh = cisHzyzChooseProjectReq.getZyh();
		List<Integer> yzztIds = cisHzyzChooseProjectReq.getJlbhs();
		String inSql = "";
		for (int i = 0; i <yzztIds.size(); i++) {
			inSql += "'" + yzztIds.get(i) + "',";
		}
		inSql = inSql.substring(0, inSql.lastIndexOf(","));
		
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("BRXZ", brxz);
		parameters.put("JGID", manageUnit);
		parameters.put("JLBHS", inSql);
		List<Map<String, Object>> clinics = opZt02Service.queryProjectPackageInfoByZtbh(parameters);
		for (Map<String, Object> clinic : clinics) {
			if (!"1".equals(clinic.get("ZYSY") + "")) {
				ReturnEntity returnEntity = new ReturnEntity();	
				returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00043", new String[] {
					ObjectToTypes.parseString(clinic.get("XMMC")),
                });
                clinic.put("errorMsg", returnEntity.getMessage());
                continue;
            }

            SkinPsjlCheckReq skinPsjlCheckReq = new SkinPsjlCheckReq();
            skinPsjlCheckReq.setYplx(0);
            skinPsjlCheckReq.setYpxh(ObjectToTypes.parseInt(clinic.get("FYXH")));
            skinPsjlCheckReq.setBrxz(brxz);
            skinPsjlCheckReq.setZyh(zyh);
            Map<String, Object> psMap = BUHISUtil.caseInsensitiveMap(imHzryByDctworkSer.doLoadDetailsInfo(skinPsjlCheckReq));
            clinic.putAll(psMap);
        }
        return BUHISUtil.ListToResultSet(clinics, new CisHzyzChooseProjectResp());
    }
	
	/**
	 * 保存申请单
	 * @Title: saveZySqd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzJcxmReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return Integer    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer saveZySqd(CisHzyzJcxmReq cisHzyzJcxmReq, SysUser user) {
        // 检查主表
        Map<String, Object> zb = BUHISUtil.caseInsensitiveMap(cisHzyzJcxmReq.getYsJx01());
        DicZlxmSqdReqYsJx01 ysjx01 = cisHzyzJcxmReq.getYsJx01();
        // 检查诊疗项目列表
//        List<Map<String, Object>> details = BUHISUtil.ListObjToMap(cisHzyzJcxmReq.getYsJx02List());
           
        List<DicZlxmSqdReqYsJx02> ysjx02List = cisHzyzJcxmReq.getYsJx02List();
        
        List<CisJcsq03Req> jcsq03List = ysjx01.getCisJcsq03();
        Integer brxz = cisHzyzJcxmReq.getBrxz();
        String op = cisHzyzJcxmReq.getOp();
        if ("".equals(op)) {
            op = OperationTypeEnum.create.getCode();
        }

        if (StrUtil.isNotBlank(ObjectToTypes.parseString(cisHzyzJcxmReq.getSqid()))) {
        	opSbhyDao.updateNumSourceBySqid(cisHzyzJcxmReq.getSqid(), 0);
            cisjcsqd01Service.jc01DeleteById(cisHzyzJcxmReq.getSqid());
            cisjcsqd01Service.jc02DeleteById(cisHzyzJcxmReq.getSqid());
            cisjcsqd01Service.jc03DeleteByJcxh(cisHzyzJcxmReq.getSqid(), user.getHospitalId());
			cisHzyzDao.deleteOrdersBySqid(cisHzyzJcxmReq.getSqid(), 0);
			cisHzyzZtDao.deleteOrderSetBySqid(cisHzyzJcxmReq.getSqid(), 0);
		}
		
		// 新增
		Map<String, Object> parameters = null;
		// 判断项目是否作废，若作废不能保存并给出提示
		String zfxmMc = "";
		String ybzfMc = "";
		for (DicZlxmSqdReqYsJx02 ysJx02 : ysjx02List) {	
			parameters = new HashMap<String, Object>(16);
			parameters.put("jgid", user.getHospitalId());
			parameters.put("zlxmid", ysJx02.getZlxmid());
			List<Map<String, Object>> xmList = feeYlsfxmOutSer.getFyxhFymcZfpbYbbm(parameters);
			for (Map<String, Object> xm : xmList) {
				if ("1".equals(ObjectToTypes.parseString(xm.get("ZFPB")))) {
					zfxmMc += "【" + ObjectToTypes.parseString(xm.get("FYMC")) + "】" + ", ";
				}
				if (StrUtil.isBlank(ObjectToTypes.parseString(xm.get("YBBM")))) {
					ybzfMc += "【" + ObjectToTypes.parseString(xm.get("FYMC")) + "】" + ", ";
				}
			}
			
			boolean brxzFlag = ("4001".equals(brxz+"") ||"4001".equals(brxz+"") ||"6023".equals(brxz+"")
					||"45".equals(brxz+"") ||"39".equals(brxz+"") ||"36".equals(brxz+"") ||"35".equals(brxz+"")
					||"34".equals(brxz+"") ||"33".equals(brxz+"") ||"32".equals(brxz+"") ||"28".equals(brxz+"")
					||"19".equals(brxz+"")||"29".equals(brxz+"")) && ybzfMc.length() > 0;
			if(brxzFlag){
				ybzfMc = ybzfMc.substring(0, ybzfMc.lastIndexOf(","));
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00044", new String[] {
						ysJx02.getFymc(),
						ybzfMc
				});
			}
							
			if (zfxmMc.length() > 0) {
				zfxmMc = zfxmMc.substring(0, zfxmMc.lastIndexOf(","));
				throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00046", new String[] {
						ysJx02.getFymc(),
						zfxmMc
				});						
			}
		}

		// 保存申请单数据
		zb.put("SQSJ", cisHzyzJcxmReq.getJcsj());// 申请时间
		if(StrUtil.isNotBlank(ysjx01.getYyrq()) && StrUtil.isNotBlank(ysjx01.getYysj())) {
			zb.put("YYZT", 1);
			zb.put("JCZT", 2);
		
		}else {
			zb.put("YYZT", 0);
			zb.put("JCZT", 1);
		}
		
		zb.put("ZYH", cisHzyzJcxmReq.getZyh());
		zb.put("ZXKS", ysjx02List.get(0).getZxks());// 执行科室
		// 检查类型
		DicYjlxModel dicYjlx = dicYjlxService.getById(ObjectToTypes.parseInt(zb.get("YJLX")));
		zb.put("JCLX", dicYjlx.getJclx());
		CisJcsq01Model cisJcsq01 = BeanUtil.fillBeanWithMapIgnoreCase(zb, new CisJcsq01Model(), true);	
		Integer jcxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_JCSQ01);
		cisJcsq01.setJcxh(jcxh);
		cisjcsqd01Service.jc01Insert(cisJcsq01);
		for (int i = 0; i < ysjx02List.size(); i++) {
			CisJcsq02Model jc02model = new CisJcsq02Model();
			jc02model.setJcxh(jcxh);
			jc02model.setFyxh(ysjx02List.get(i).getZlxmid());
			jc02model.setFydj(ysjx02List.get(i).getFydj());
			cisjcsqd01Service.jc02Insert(jc02model);
		}
		
		for(CisJcsq03Req jcsq03 : jcsq03List) {
			jcsq03.setJcxh(jcxh);
			jcsq03.setJgid(user.getHospitalId());
			jcsq03.setJcsqsjid(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_JCSQ03));
			cisjcsqd01Service.jc03Insert(jcsq03);
		}
		
		// 更新设备号源使用状态
		opSbhyDao.updateSfyy(ObjectToTypes.parseString(zb.get("HYID")));

		// 存储CIS_HZYZ数据
		List<Map<String, Object>> yzxxArray = new ArrayList<Map<String, Object>>();
		Map<String, Object> yzxxMap = null;// 医嘱信息
		Map<String, Object> yzxxZtMap = null;// 组套医嘱信息
		List<Map<String, Object>> fymxList = null;
		int yzzhShow = 1;// 医嘱组号

		// 查询st对应的执行时间
		DicSypcModel dicSypc  = dicSypcService.getById("st");
		String zxsj = "";
		if (dicSypc != null && StrUtil.isNotBlank(dicSypc.getZxsj())) {
			zxsj = dicSypc.getZxsj();
		}

		int yzpx = 1;// 医嘱排序
		Integer ztjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ_ZT +".yzzh");
		for (DicZlxmSqdReqYsJx02 ysJx02 : ysjx02List) {
			// 保存CIS_HZYZ_ZT信息
			yzxxZtMap = new HashMap<String, Object>(16);
			// 赋值医嘱信息
			yzxxZtMap.put("SQID", jcxh);// 申请申请ID(SQID)
			yzxxZtMap.put("YZLX", 0); // 组套医嘱类型 0检查医嘱，1检验医嘱
			yzxxZtMap.put("JGID", user.getHospitalId()); // 机构ID
			yzxxZtMap.put("ZYH", cisHzyzJcxmReq.getZyh()); // 住院号
			yzxxZtMap.put("YZMC", ysJx02.getFymc()); // 药嘱名称
			yzxxZtMap.put("YPXH", ysJx02.getZlxmid()); // 药品序号=诊疗项目ID
			yzxxZtMap.put("YPCD", 0); // 药品产地
			// 4检验,5检查,6手术,7治疗,8护理,9饮食,10卫材,99其他
			yzxxZtMap.put("XMLX", 5);// 项目类型 5=检查
			yzxxZtMap.put("YPLX", 0); // 药品类型
			yzxxZtMap.put("MRCS", 1); // 每日次数
			yzxxZtMap.put("YCJL", 0.000); // 一次剂量
			yzxxZtMap.put("YCSL", 1.0000); // 一次数量
			yzxxZtMap.put("MZCS", 0); // 每周次数

			yzxxZtMap.put("KSSJ", cisHzyzJcxmReq.getJcsj());// 开始时间
			yzxxZtMap.put("TZSJ", cisHzyzJcxmReq.getJcsj());// 停嘱时间
			// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
			yzxxZtMap.put("SYPC", "st");
			yzxxZtMap.put("YZZXSJ", zxsj);

			yzxxZtMap.put("YPDJ", ysJx02.getFydj()); // 药品单价
			yzxxZtMap.put("YPYF", 0L); // 药品用法
			yzxxZtMap.put("YSGH", user.getUserId()); // 开嘱医生
			yzxxZtMap.put("TZYS", user.getUserId()); // 停嘱医生
			yzxxZtMap.put("CZGH", user.getUserId()); // 操作工号
			yzxxZtMap.put("SYBZ", 0); // 使用标志
			// 输入科室
			yzxxZtMap.put("SRKS", cisHzyzJcxmReq.getBrks() == null ? 0 : cisHzyzJcxmReq.getBrks());
			yzxxZtMap.put("ZFPB", 1); // 自负判别
			yzxxZtMap.put("YJZX", 0); // 医技主项
			yzxxZtMap.put("YJXH", 0L); // 医技序号
			yzxxZtMap.put("ZXKS", ysJx02.getZxks());// 执行科室=诊疗项目执行科室
			yzxxZtMap.put("YZZH", ztjlxh); // 医嘱组号
			yzxxZtMap.put("FYSX", 0); // 发药属性
			yzxxZtMap.put("YEPB", 0); // 婴儿判别
			yzxxZtMap.put("YFSB", 0); // 药房识别
			yzxxZtMap.put("LSYZ", 1); // 临时医嘱
			yzxxZtMap.put("LSBZ", 0); // 历史标志
			yzxxZtMap.put("YZPB", 0); // 医嘱判别
			yzxxZtMap.put("JFBZ", 2); // 计费标志
			yzxxZtMap.put("TPN", 0); // TPN
			yzxxZtMap.put("YSBZ", 1); // 医生医嘱标志
			yzxxZtMap.put("YSTJ", 0); // 医生提交标志
			yzxxZtMap.put("ZFBZ", 0); // 作废标志
			yzxxZtMap.put("SRCS", 0); // 首日次数
			yzxxZtMap.put("SFJG", 0); // 审方结果
			yzxxZtMap.put("BRKS", cisHzyzJcxmReq.getBrks()); // 病人科室
			yzxxZtMap.put("BRBQ", cisHzyzJcxmReq.getBrbq()); // 病人病区
			yzxxZtMap.put("BRCH", cisHzyzJcxmReq.getBrch()); // 病人床号
			yzxxZtMap.put("FHBZ", 0); // 复核标志
			yzxxZtMap.put("TZFHBZ", 0); // 停嘱复核标志
			yzxxZtMap.put("PSPB", 0); // 皮试判别
			yzxxZtMap.put("CFTS", 0); // 贴数
			yzxxZtMap.put("YPZS", 1); // 煎法
			yzxxZtMap.put("JZ", 1); // 脚注
			yzxxZtMap.put("ZFYP", 0); // 自备药标识
			yzxxZtMap.put("ZTBZ", 1); // 组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志
			yzxxZtMap.put("YZPX", yzpx++); // 医嘱排序
			Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
			yzxxZtMap.put("JLXH", jlxh);
			CisHzyzZt cisHzyzZt = BeanUtil.fillBeanWithMapIgnoreCase(yzxxZtMap, new CisHzyzZt(), true);
			cisHzyzZtDao.insert(cisHzyzZt);

			// 查询该诊疗项目对应的收费项目明细
			parameters = new HashMap<String, Object>(16);
			parameters.put("zlxmid", ysJx02.getZlxmid());
			//判断如果是商保病人，获取商报价格
			if("6024".equals(brxz + "")){
				parameters.put("types", 1);
			}else{
				parameters.put("types", 2);
			}
			fymxList = feeYlsfxmOutSer.getBxfy(parameters);
			DiccZlsfdzModel diccZlsfdz = new DiccZlsfdzModel();
			diccZlsfdz.setZlxmid(ysJx02.getZlxmid());
			List<DiccZlsfdzModel> diccZlsfdzList = diccZlsfdzService.findByEntity(diccZlsfdz);
			for (Map<String, Object> fymx : fymxList) {
				yzxxMap = new HashMap<String, Object>(16);// 医嘱信息
				// 赋值医嘱信息
				yzxxMap.put("opStatus", OperationTypeEnum.create.getCode());
				yzxxMap.put("ZTYZJLXH",jlxh);// 组套医嘱记录序号
				yzxxMap.put("SQID", jcxh);// 申请申请ID(SQID)
				yzxxMap.put("ZTBZ", 1);// 组套标志
				yzxxMap.put("ZFYP", 0);// 自备药标识
				yzxxMap.put("YSTJ", 0);// 医生提交标志
				
				for(DiccZlsfdzModel xmfy : diccZlsfdzList){
					if(xmfy.getFyxh() != null && 
							ObjectToTypes.parseString(xmfy.getFyxh()).equals(fymx.get("FYXH").toString())){
						yzxxMap.put("YCSL", xmfy.getFysl() == null ? 1 : xmfy.getFysl());// 一次数量
						break;
					}
				}
				yzxxMap.put("MRCS", 1);// 每日次数
				yzxxMap.put("CZGH", user.getUserId());// 操作工号
				yzxxMap.put("YSBZ", 1);// 医生医嘱标志
				yzxxMap.put("SRCS", 0);// 首日次数
				yzxxMap.put("YFBZ", "");// 药房包装

				// 获取病人自负判别
				Integer ypxh = ObjectToTypes.parseInt(fymx.get("FYXH"));// 费用序号
				Integer fygb = getfygb(0, ypxh);// 获取费用归并
				Map<String, Object> params = new HashMap<String, Object>(16);
				params.put("BRXZ", brxz);// 病人性质
				params.put("FYGB", fygb);// 费用归并
				params.put("TYPE", 0);// 药品类型 0=项目
				params.put("FYXH", ypxh);// 费用序号
				Map<String, Object> zfblMap = getPayProportion(params);
				// 自负判别
				if (ObjectToTypes.parseDouble(zfblMap.get("ZFBL")) > 0) {
					yzxxMap.put("ZFPB", 1);
				} else {
					yzxxMap.put("ZFPB", 0);
				}

				yzxxMap.put("YPLX", 0);// 药品类型
				yzxxMap.put("SYBZ", 0);// 使用标志
				yzxxMap.put("MZCS", 0);// 每周次数
				yzxxMap.put("XMLX", 5);// 项目类型 5=检查
				yzxxMap.put("JFBZ", 2);// 计费标志
				yzxxMap.put("ZFBZ", 0);// 作废标志
				yzxxMap.put("SFJG", 0);// 审方结果
				yzxxMap.put("FHBZ", 0);// 复核标志
				yzxxMap.put("TZFHBZ", 0);// 停嘱复核标志
				yzxxMap.put("PSPB", 0);// 皮试判别
				yzxxMap.put("YZPB", 0);// 医嘱判别
				yzxxMap.put("LSBZ", 0);// 历史标志
				yzxxMap.put("YEPB", 0);// 婴儿判别
				yzxxMap.put("FYSX", 0);// 发药属性
				yzxxMap.put("TPN", 0);// TPN
				yzxxMap.put("LSYZ", 1);// 临时医嘱
				yzxxMap.put("CFTS", 0);// 贴数
				yzxxMap.put("YPZS", 1);// 煎法
				yzxxMap.put("JZ", 1);// 脚注
				yzxxMap.put("YPDJ", ObjectToTypes.parseDouble(fymx.get("FYDJ")));// 药品单价
				yzxxMap.put("yzzhShow", yzzhShow);// 医嘱组号
				yzxxMap.put("YSGH", user.getUserId());// 开嘱医生

				yzxxMap.put("KSSJ", cisHzyzJcxmReq.getJcsj());// 开始时间
				yzxxMap.put("TZSJ", cisHzyzJcxmReq.getJcsj());// 停嘱时间
				// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
				yzxxMap.put("SYPC", "st");
				yzxxMap.put("YZZXSJ", zxsj);

				yzxxMap.put("JGID", user.getHospitalId());// 机构ID
				yzxxMap.put("ZYH", cisHzyzJcxmReq.getZyh());// 住院号
				yzxxMap.put("YPJL", 0);// =======================================================
				yzxxMap.put("YCJL", 0);// 一次剂量
				yzxxMap.put("YPXH", ypxh);// 药品序号
				yzxxMap.put("YJXH", ypxh);// 医技序号
                yzxxMap.put("YZMC", ObjectToTypes.parseString(fymx.get("FYMC")));// 药嘱名称
                yzxxMap.put("ZXKS", ysJx02.getZxks());// 执行科室=诊疗项目执行科室
                yzxxMap.put("YZLX", 0);// 执行科室=诊疗项目执行科室
//					brxx.put("SYPC", "");// 使用频次
                yzxxMap.put("uniqueId", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                yzxxArray.add(yzxxMap);
            }
        }

        //医嘱信息body
        List<CisHzyzReq> cisHzyzList = BUHISUtil.ListToResultSet(yzxxArray, new CisHzyzReq());
        List<CisHzyzReq> cisHzyzFjList = new ArrayList<CisHzyzReq>();
        CisHzyzSaveReq cisHzyzSaveReq = new CisHzyzSaveReq();
        cisHzyzSaveReq.setCisHzyzReqList(cisHzyzList);
        cisHzyzSaveReq.setZyh(cisHzyzJcxmReq.getZyh());
        cisHzyzSaveReq.setBrbq(cisHzyzJcxmReq.getBrbq());
        cisHzyzSaveReq.setBrch(cisHzyzJcxmReq.getBrch());
        cisHzyzSaveReq.setBrks(cisHzyzJcxmReq.getBrks());
        cisHzyzSaveReq.setBrxm(cisHzyzJcxmReq.getBrxm());
        cisHzyzSaveReq.setCisHzyzReqFjList(cisHzyzFjList);
        imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, user);
		return jcxh;

	}
	
	/**
	 * 查询当天使用药品数量总和
	 * @Title: doLoadAmqcCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param ypxh
	 * @param @param kssj
	 * @param @return    设定文件
	 * @return BigDecimal    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public BigDecimal doLoadAmqcCount(Integer zyh, Integer ypxh, String kssj) {
		Map<String, Object> parameters = new HashMap<String, Object>(16);
		parameters.put("zyh", zyh);
		parameters.put("ypxh", ypxh);
		parameters.put("kssj", kssj);
		Map<String, Object> map = cisHzyzDao.queryDrugSumSameDay(parameters);
		if (map == null || map.size() == 0) {
			return new BigDecimal(0);
		} else {
			return ObjectToTypes.parseBigDecimal(map.get("SYSL"));
		}
	}
	
	
	/**
	 * 获取当前机构所有的药房和病区的发药药房
	 * @Title: doGetAllYfs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param bqdm
	 * @param @param user
	 * @param @return    设定文件
	 * @return CisHzyzYflbResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public CisHzyzYflbResp doGetAllYfs(Integer bqdm, SysUser user) {
		PharBaseInfoReq req = new PharBaseInfoReq();
		req.setJgid(user.getHospitalId());
		List<PharBaseInfoResp> resList = pharBaseConfigService.findByEntity(req);
		
		List<PharYflb> pharYflbList = new ArrayList<PharYflb>();
		for(PharBaseInfoResp pharBaseInfoResp : resList) {
			PharYflb yflb = BeanUtil.toBean(pharBaseInfoResp, PharYflb.class);
			pharYflbList.add(yflb);
		}
		
		NisFyyf nisFyyf = new NisFyyf();
		nisFyyf.setBqdm(ObjectToTypes.parseString(bqdm));
		nisFyyf.setJgid(user.getHospitalId());
		List<NisFyyfResp> nisFyyfRespList = nisFyyfDao.queryAllYfsbCurrent(nisFyyf);
		CisHzyzYflbResp bqyzYflbResp = new CisHzyzYflbResp();
		bqyzYflbResp.setPharYflbList(pharYflbList);
		bqyzYflbResp.setNisFyyfRespList(nisFyyfRespList);
		return bqyzYflbResp;
	}
	
	/**
	 * 药品提交退回-分页
	 * @Title: getDrugReturn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param nisTj02Req
	 * @param @return    设定文件
	 * @return List<NisTj02Resp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<NisTj02Resp> getDrugReturn(NisTj02Req nisTj02Req) {
		return nisTj02Dao.queryDrugReturn(nisTj02Req);
	}
	
	/**
	 * 住院保存检验申请单
	 * @Title: saveZyJySqd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzJySqdReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveZyJySqd(CisHzyzJySqdReq cisHzyzJySqdReq, SysUser user) {
        List<Map<String, Object>> details = BUHISUtil.ListObjToMap(cisHzyzJySqdReq.getXmDetails());

        if (StrUtil.isNotBlank(ObjectToTypes.parseString(cisHzyzJySqdReq.getSqid()))) {
            cisHzyzDao.deleteOrdersBySqid(cisHzyzJySqdReq.getSqid(), 1);
            cisHzyzZtDao.deleteOrderSetBySqid(cisHzyzJySqdReq.getSqid(), 1);
        }

        // 判断项目是否作废，若作废不能保存并给出提示
        String zfxmMc = "";
        for (Map<String, Object> xm : details) {
            FeeYlsfxmModel feeYlsfxm = new FeeYlsfxmModel();
			feeYlsfxm.setFyxh(ObjectToTypes.parseInt(xm.get("XMBH")));
			feeYlsfxm.setZfpb(0);
			List<FeeYlsfxmModel> feeYlsfxmList = feeYlsfxmOutSer.findByEntity(feeYlsfxm);		
			if (CollectionUtils.isEmpty(feeYlsfxmList)) {
				zfxmMc += ObjectToTypes.parseString(xm.get("YZMC")) + ", ";
			}
		}
		if (zfxmMc.length() > 0) {
			zfxmMc = zfxmMc.substring(0, zfxmMc.lastIndexOf(","));
            throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00046", new String[]{
                    zfxmMc
            });
        }

        // 保存医嘱信息
        List<Map<String, Object>> yzxxArray = new ArrayList<Map<String, Object>>();
        Map<String, Object> yzxxMap = new HashMap<String, Object>(16);// 医嘱信息

        // 检验组套 如果该组套项目全部选中，就向CIS_HZYZ_ZT表中存储一条数据
        List<Map<String, Object>> jyzt = BUHISUtil.ListObjToMap(cisHzyzJySqdReq.getJyzts());
        Map<String, Object> zt01Map = new HashMap<String, Object>(16);
        Map<String, Object> yzzhMap = new HashMap<String, Object>(16);
        // 组号 同一组套的项目全选时，医嘱存为同一组
        int yzzhShow = 1;

        // 查询st对应的执行时间
        DicSypcModel dicSypc = dicSypcService.getById("st");
        String zxsj = "";
        if (dicSypc != null && StrUtil.isNotBlank(dicSypc.getZxsj())) {
            zxsj = dicSypc.getZxsj();
		}

		Integer yzpx = 1;// 医嘱排序
		for (Map<String, Object> ztMap : jyzt) {
			Integer ztbh = ObjectToTypes.parseInt(ztMap.get("ZTBH"));
			Integer brxz = cisHzyzJySqdReq.getBrxz();
			List<Map<String, Object>> xmDetails =new ArrayList<Map<String,Object>>();
			// 查询该组套下面的项目明细
			//判断病人性质是否是医保病人
			Map<String, Object> param = new HashMap<String, Object>(16);
			param.put("ztbh", ztbh);
			if(brxz!=null && "6024".equals(ObjectToTypes.parseString(brxz))){
				//如果是商保病人，获取商保价格
				xmDetails = opZt02Service.getJlbhXmbhXmslFydjSb(param);
			}else{
				//非商保病人
				xmDetails = opZt02Service.getJlbhXmbhXmslFydjFSb(param);
			}
			boolean hasZt = true;// 该组套项目明细全选
			//检验项目按组套形式开都生成CIS_HZYZ_ZT记录
			List<Map<String, Object>> newxmDetails =new ArrayList<Map<String,Object>>();
			for (Map<String, Object> xm : xmDetails) {
				for (Map<String, Object> yz : details) {
					if (xm.get("jlbh").toString().equals(yz.get("JLBH").toString())) {
						newxmDetails.add(xm);
						break;
					}
				}
			}

			// 该组套的项目全部选中
			double ypdj = 0;
			Integer fyks = null;
			StringBuffer yzmcStr = new StringBuffer();
			if (hasZt) {
				for (Map<String, Object> xm : newxmDetails) {
					ypdj += ObjectToTypes.parseDouble(xm.get("fydj")) * 
							ObjectToTypes.parseDouble(xm.get("fysl"));
					yzmcStr.append(xm.get("xmmc")).append("+"); 
				}
				for (Map<String, Object> xm : newxmDetails) {
					if(StrUtil.isNotBlank(ObjectToTypes.parseString(xm.get("fyks")))) {
						fyks = ObjectToTypes.parseInt(xm.get("fyks"));
						break;
					}
				}
				// 向CIS_HZYZ_ZT表中存储一条数据
				Map<String, Object> ztxxMap = new HashMap<String, Object>(16);// 组套信息
				// 赋值医嘱信息
				ztxxMap.put("YZLX", 1); // 组套医嘱类型 0检查医嘱，1检验医嘱
				ztxxMap.put("JGID", user.getHospitalId());// 机构ID
				ztxxMap.put("ZYH", cisHzyzJySqdReq.getZyh());// 住院号
				ztxxMap.put("YZMC", ObjectToTypes.parseString(ztMap.get("ZTMC")) 
						+ (yzmcStr.length() > 0 ? "(" + yzmcStr.deleteCharAt(yzmcStr.length() - 1).toString() + ")" : ""));// 药嘱名称
				ztxxMap.put("YPXH", ztbh);// 药品序号
				ztxxMap.put("YPCD", 0);// 药品产地
				ztxxMap.put("XMLX", 4);// 项目类型
									// 4检验,5检查,6手术,7治疗,8护理,9饮食,10卫材,99其他
				ztxxMap.put("YPLX", 0);// 药品类型
				ztxxMap.put("MRCS", 1);// 每日次数
				ztxxMap.put("YCJL", 0.000);// 一次剂量
				ztxxMap.put("YCSL", 1.0000);// 一次数量
				ztxxMap.put("MZCS", 0);// 每周次数

				ztxxMap.put("KSSJ", cisHzyzJySqdReq.getSqsj());// 开始时间
				ztxxMap.put("TZSJ", cisHzyzJySqdReq.getSqsj());// 停嘱时间
				// ztxxMap.put("QRSJ", "");// 确认时间
				// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
				ztxxMap.put("SYPC", "st");
				ztxxMap.put("YZZXSJ", zxsj);

				ztxxMap.put("YPDJ", ObjectToTypes.parseDouble(ypdj));// 药品单价
				ztxxMap.put("YPYF", 0L);// 药品用法
				ztxxMap.put("YSGH", user.getUserId());// 开嘱医生
				ztxxMap.put("TZYS", user.getUserId());// 停嘱医生
				ztxxMap.put("CZGH", user.getUserId());// 操作工号
				ztxxMap.put("SYBZ", 0);// 使用标志
				// 输入科室
				ztxxMap.put("SRKS", cisHzyzJySqdReq.getBrks() == null ? 0 : cisHzyzJySqdReq.getBrks());
				ztxxMap.put("ZFPB", 1);// 自负判别------------
				ztxxMap.put("YJZX", 0);// 医技主项
				ztxxMap.put("YJXH", 0L);// 医技序号
				// 执行科室
				ztxxMap.put("ZXKS", fyks == null ? cisHzyzJySqdReq.getBrks() : fyks);
				ztxxMap.put("YZZH", redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ_ZT +".yzzh"));
				ztxxMap.put("FYSX", 0);// 发药属性
				ztxxMap.put("YEPB", 0);// 婴儿判别
				ztxxMap.put("YFSB", 0);// 药房识别
				ztxxMap.put("LSYZ", 1);// 临时医嘱
				ztxxMap.put("LSBZ", 0);// 历史标志
				ztxxMap.put("YZPB", 0);// 医嘱判别
				ztxxMap.put("JFBZ", 2);// 计费标志--------------
				ztxxMap.put("TPN", 0);// TPN
				ztxxMap.put("YSBZ", 1);// 医生医嘱标志
				ztxxMap.put("YSTJ", 0);// 医生提交标志
				ztxxMap.put("ZFBZ", 0);// 作废标志
				ztxxMap.put("SRCS", 0);// 首日次数
				ztxxMap.put("SFJG", 0);// 审方结果
				ztxxMap.put("BRKS", cisHzyzJySqdReq.getBrks());// 病人科室
				ztxxMap.put("BRBQ", cisHzyzJySqdReq.getBrbq());// 病人病区
				ztxxMap.put("BRCH", cisHzyzJySqdReq.getBrch());// 病人床号
				ztxxMap.put("FHBZ", 0);// 复核标志
				ztxxMap.put("TZFHBZ", 0);// 停嘱复核标志
				ztxxMap.put("PSPB", 0);// 皮试判别
				ztxxMap.put("CFTS", 0);// 贴数
				ztxxMap.put("YPZS", 1);// 煎法
				ztxxMap.put("JZ", 1);// 脚注
				ztxxMap.put("ZFYP", 0);// 自备药标识
				ztxxMap.put("ZTBZ", 1);// 组套标志(该条医嘱存是组套信息) 0非组套标志，1组套标志
				ztxxMap.put("HYXM",cisHzyzJySqdReq.getJjbz() == null ? "0":String.valueOf(cisHzyzJySqdReq.getJjbz()));// 化验项目加急
				if(cisHzyzJySqdReq.getJjbz() != null){
					ztxxMap.put("BZXX",cisHzyzJySqdReq.getJjbz() == 1 ?"加急":cisHzyzJySqdReq.getBzxx());
				}
				ztxxMap.put("YZPX", yzpx++); // 医嘱排序
				Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
				ztxxMap.put("JLXH", jlxh);
				CisHzyzZt cisHzyzZt = BeanUtil.fillBeanWithMapIgnoreCase(ztxxMap, new CisHzyzZt(), true);
				cisHzyzZtDao.insert(cisHzyzZt);
				yzzhShow++;
				for (Map<String, Object> xm : newxmDetails) {
					zt01Map.put(xm.get("jlbh").toString(), jlxh);
					yzzhMap.put(xm.get("jlbh").toString(), yzzhShow);
				}
			}
		}

		for (int i = 0; i < details.size(); i++) {
			yzxxMap = new HashMap<String, Object>(16);// 医嘱信息
			yzxxMap.put("ZTBZ", 1);// 组套标志 组套医嘱
			yzxxMap.put("ZTYZJLXH", zt01Map.get(details.get(i).get("JLBH").toString()));
			// 赋值医嘱信息
			yzxxMap.put("opStatus", OperationTypeEnum.create.getCode());
			yzxxMap.put("ZFYP", 0);// 自备药标识
			yzxxMap.put("YSTJ", 0);// 医生提交标志
			yzxxMap.put("YCSL", details.get(i).get("FYSL"));// 一次数量
			yzxxMap.put("MRCS", 1);// 每日次数
			yzxxMap.put("CZGH", user.getUserId());// 操作工号
			yzxxMap.put("YSBZ", 1);// 医生医嘱标志
			yzxxMap.put("SRCS", 0);// 首日次数
			// brxx.put("YFBZ", "");// 药房包装

			// 获取病人自负判别
			Integer ypxh = ObjectToTypes.parseInt(details.get(i).get("XMBH"));// 费用序号
			Integer fygb = getfygb(0, ypxh);// 获取费用归并
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("BRXZ", cisHzyzJySqdReq.getBrxz());// 病人性质
			params.put("FYGB", fygb);// 费用归并
			params.put("TYPE", 0);// 药品类型 0=项目
			params.put("FYXH", ypxh);// 费用序号
			Map<String, Object> zfblMap = getPayProportion(params);
			if (ObjectToTypes.parseDouble(zfblMap.get("ZFBL")) > 0) {
				yzxxMap.put("ZFPB", 1);
			} else {
				yzxxMap.put("ZFPB", 0);
			}

			yzxxMap.put("YPLX", 0);// 药品类型
			yzxxMap.put("SYBZ", 0);// 使用标志
			yzxxMap.put("MZCS", 0);// 每周次数
			yzxxMap.put("XMLX", 4);// 项目类型 4检验,5检查,6手术,7治疗,8护理,9饮食,10卫材,99其他
			// 项目类型
			yzxxMap.put("JFBZ", 2);// 计费标志---------------------
			yzxxMap.put("ZFBZ", 0);// 作废标志
			yzxxMap.put("SFJG", 0);// 审方结果
			yzxxMap.put("FHBZ", 0);// 复核标志
			yzxxMap.put("TZFHBZ", 0);// 停嘱复核标志
			yzxxMap.put("PSPB", 0);// 皮试判别
			yzxxMap.put("YZPB", 0);// 医嘱判别
			yzxxMap.put("LSBZ", 0);// 历史标志
			yzxxMap.put("YEPB", 0);// 婴儿判别
			yzxxMap.put("FYSX", 0);// 发药属性
			yzxxMap.put("TPN", 0);// TPN
			yzxxMap.put("LSYZ", 1);// 临时医嘱
			yzxxMap.put("CFTS", 0);// 贴数
			yzxxMap.put("YPZS", 1);// 煎法
			yzxxMap.put("JZ", 1);// 脚注
			yzxxMap.put("YPDJ", ObjectToTypes.parseDouble(details.get(i).get("FYDJ")));// 药品单价
			// 医嘱组号
			yzxxMap.put("yzzhShow", yzzhMap.get(details.get(i).get("JLBH").toString()));
			yzxxMap.put("YSGH", user.getUserId());// 开嘱医生
			yzxxMap.put("KSSJ", cisHzyzJySqdReq.getSqsj());// 开始时间
			yzxxMap.put("TZSJ", cisHzyzJySqdReq.getSqsj());// 停嘱时间
			// 检验，检查临时医嘱 开组套的时候， CIS_HZYZ表 的频次 默认为st， 时间取st对应的 23:59
			yzxxMap.put("SYPC", "st");
			yzxxMap.put("YZZXSJ", zxsj);
			yzxxMap.put("JGID", user.getHospitalId());// 机构ID
			yzxxMap.put("ZYH", cisHzyzJySqdReq.getZyh());// 住院号
			yzxxMap.put("YPJL", 0);
			yzxxMap.put("YCJL", 0);// 一次剂量
			yzxxMap.put("YPXH", ObjectToTypes.parseInt(details.get(i).get("XMBH")));// 药品序号
            yzxxMap.put("YJXH", ObjectToTypes.parseInt(details.get(i).get("XMBH")));// 医技序号
            yzxxMap.put("YZMC", ObjectToTypes.parseString(details.get(i).get("YZMC")));// 药嘱名称
            // 执行科室
            yzxxMap.put("ZXKS", cisHzyzJySqdReq.getBrks());
            yzxxMap.put("uniqueId", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            yzxxArray.add(yzxxMap);
        }

        //医嘱信息body
        List<CisHzyzReq> cisHzyzList = BUHISUtil.ListToResultSet(yzxxArray, new CisHzyzReq());
        List<CisHzyzReq> cisHzyzFjList = new ArrayList<CisHzyzReq>();
        CisHzyzSaveReq cisHzyzSaveReq = new CisHzyzSaveReq();
        cisHzyzSaveReq.setCisHzyzReqList(cisHzyzList);
        cisHzyzSaveReq.setZyh(cisHzyzJySqdReq.getZyh());
        cisHzyzSaveReq.setBrbq(cisHzyzJySqdReq.getBrbq());
        cisHzyzSaveReq.setBrch(cisHzyzJySqdReq.getBrch());
        cisHzyzSaveReq.setBrks(cisHzyzJySqdReq.getBrks());
        cisHzyzSaveReq.setBrxm(cisHzyzJySqdReq.getBrxm());
        cisHzyzSaveReq.setCisHzyzReqFjList(cisHzyzFjList);
        imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, user);
	}
	
	/**
	 * 查询附加项目信息
	 * @Title: doLoadAppendAdvice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzFjReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<DiccLdxmglResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<DiccLdxmglApiResp> doLoadAppendAdvice(Integer fyxh, Integer ksdm, String mzsy, SysUser user) {
		Integer manageUnit = user.getHospitalId();
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("JGID", manageUnit);
		params.put("XMXH", fyxh);
		params.put("KSDM", ksdm);
		String sylb = "b.ZYSY";
		if (!"".equals(mzsy) && "1".equals(mzsy)) {
			sylb = "b.MZSY";
		}
		params.put("SYLB", sylb);
		return diccLdxmglService.queryProjectAssociationInfo(params);
	}
	
	/**
	 * 根据申请id查询检查项目信息
	 * @Title: queryDicZlxmInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqid
	 * @param @param user
	 * @param @return    设定文件
	 * @return CisJcsq01ZlxmResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public CisJcsq01ZlxmResp queryDicZlxmInfo(Integer sqid, SysUser user) {
		CisJcsq01Model cisJcsq01 = cisjcsqd01Service.jc01getById(sqid);
		CisJcsq01ZlxmResp cisJcsq01ZlxmResp = BeanUtil.toBean(cisJcsq01, CisJcsq01ZlxmResp.class);
		List<CisJcsq03Resp> cisJcsq03RespList = cisjcsqd03Service.queryCisJcsq03ByJcxh(user.getHospitalId(), sqid);
		cisJcsq01ZlxmResp.setCisJcsq03RespList(cisJcsq03RespList);
		List<CisJcsq02ZlxmResp> cisJcsq02ZlxmRespList = cisjcsqd01Service.queryYsJcDicZlxmInfo(sqid, user.getHospitalId());
		Integer yjlx = null; 
		if(CollectionUtils.isNotEmpty(cisJcsq02ZlxmRespList)) {
			yjlx = cisJcsq02ZlxmRespList.get(0).getYjlx();
			cisJcsq01ZlxmResp.setCisJcsq02ZlxmRespList(cisJcsq02ZlxmRespList);
		}	
		DicYjlxZlxmApiResp dicYjlxZlxmResp = new DicYjlxZlxmApiResp();
		List<DicYjlxZlxmApiResp> list = new ArrayList<DicYjlxZlxmApiResp>();
		queryYjlxZlxm(yjlx, null, dicYjlxZlxmResp, list);
		cisJcsq01ZlxmResp.setChildren(list);
		return cisJcsq01ZlxmResp;
	}
	
	/**
	 * 查询检查医技树
	 * @Title: queryYjlxZlxm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return    设定文件
	 * @return List<DicYjlxZlxmResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void queryYjlxZlxm(Integer id, DicYjlxZlxmApiResp yjlxZlxm, DicYjlxZlxmApiResp dicYjlxZlxmResp, List<DicYjlxZlxmApiResp> yjlxZlxmlist){
		if(id == null || id == 0 ) {
			yjlxZlxmlist.add(dicYjlxZlxmResp);
			return;
		}
		DicYjlxZlxmApiResp dicYjlxZlxm = dicYjlxService.queryDicYjlxById(id);		
		if(dicYjlxZlxm != null) {
			if(yjlxZlxm == null) {
				yjlxZlxm = dicYjlxZlxm;
			}else {
				List<DicYjlxZlxmApiResp> list = new ArrayList<DicYjlxZlxmApiResp>();
				list.add(dicYjlxZlxmResp);
				dicYjlxZlxm.setChildren(list);
			}
			dicYjlxZlxmResp = dicYjlxZlxm;			
			queryYjlxZlxm(dicYjlxZlxm.getPid(), dicYjlxZlxm, dicYjlxZlxmResp, yjlxZlxmlist);
		}
	}
	
	/**
	 * 药品组套调入校验库存
	 * @Title: checkYpZtKc
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCheckKcReq
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<String>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<String> checkYpZtKc(CisHzyzCheckKcReq cisHzyzCheckKcReq, SysUser user) {
		List<String> listStr = new ArrayList<String>();
		List<CisHzyzYpcdDetailResp> list = cisHzyzCheckKcReq.getYpmxList();
		String adviceType = cisHzyzCheckKcReq.getAdviceType();
		Integer type = 1;
		if ("jz".equals(adviceType)) {
			type = 2;
		} else if ("cy".equals(adviceType)) {
			type = 3;
		}
		Map<String, Object> prarms = new HashMap<String, Object>(16);
		DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq = new DrugsTypkBqyzYpSrfReq();
		drugsTypkBqyzYpSrfReq.setBqdm(cisHzyzCheckKcReq.getKsdm());
		drugsTypkBqyzYpSrfReq.setJgid(user.getHospitalId());
		drugsTypkBqyzYpSrfReq.setYfsz(type);
		List<Map<String, Object>> yfsbs = nisFyyfDao.queryYfsb(drugsTypkBqyzYpSrfReq);
		for(CisHzyzYpcdDetailResp cisHzyzYpcdDetailResp : list) {
			for (int i = 0; i < yfsbs.size(); i++) {
				prarms.put("pharmId",Long.parseLong(yfsbs.get(i).get("YFSB")+ ""));
				prarms.put("medId", cisHzyzYpcdDetailResp.getYpxh());
				prarms.put("medsource", cisHzyzYpcdDetailResp.getYpcd());
				Double ycjl = cisHzyzYpcdDetailResp.getYcjl().doubleValue();
				Double ypjl = cisHzyzYpcdDetailResp.getYpjl().doubleValue();
				prarms.put("quantity",(ycjl / ypjl)
								* (cisHzyzYpcdDetailResp.getMrcs()));
				prarms.put("lsjg", cisHzyzYpcdDetailResp.getLsjg());
				Map<String, Object> ret = checkInventory(prarms, user.getHospitalId());
				if ("-1".equals(ret.get("sign")+"") && i == (yfsbs.size() - 1)) {
					ReturnEntity returnEntity = new ReturnEntity();	
					returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00040", new String[] {
							cisHzyzYpcdDetailResp.getYpmc(),
						ObjectToTypes.parseString(ret.get("KCZL")),
						ObjectToTypes.parseString(prarms.get("quantity")),
					});
					listStr.add(returnEntity.getMessage());
//					ztMed.put("warnMsg", returnEntity.getMessage());
				} else if ("-1".equals(ret.get("sign")+"")) {
					continue;
				} 
			}
		}
		return listStr;
	}
	
	/**
	 * 药品医嘱提交打印返回
	 * @Title: getAdviceSubmitParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param lsyz
	 * @param @param user
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getAdviceSubmitParameters(Integer lsyz, SysUser user) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		if (lsyz == 3) {
			responseMap.put("title", user.getHospitalName() + "-病区急诊用药领药单(明细)");
		} else if (lsyz == 4) {
			responseMap.put("title", user.getHospitalName() + "-病区出院带药领药单(明细)");
		} else {
			responseMap.put("title", user.getHospitalName() + "-病区领药单(明细)");
		}
		return responseMap;
	}

	public List<Map<String, Object>> getAdviceSubmitFields(CisHzyzYpSubmitReq cisHzyzYpSubmitReq, SysUser user) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		String brch = null;
		String brxm = null;
		try {
			SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
			Integer manaUnitId = user.getHospitalId();
			SimpleDateFormat sdfdatetime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLSJ);	
			String ypylsj = sysXtcs == null ? "1" : sysXtcs.getCsz();
			Integer alhsql = 0;
			if (StrUtil.isNotBlank(ObjectToTypes.parseString(cisHzyzYpSubmitReq.getKsdm()))) {
				alhsql = cisHzyzYpSubmitReq.getKsdm();
			}
			Integer alzyh = 0;
			Integer ldtfyfs = 0;
			Integer ldtlsyz = 0;
			Integer ldtyfsb = 0;
			Date ldtserver = sdfdate.parse(sdfdate.format(new Date()));
		
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getZyh())) {
				alzyh = cisHzyzYpSubmitReq.getZyh();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getFyfs())) {
				ldtfyfs = cisHzyzYpSubmitReq.getFyfs();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getLsyz())) {
				ldtlsyz = cisHzyzYpSubmitReq.getLsyz();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getYfsb())) {
				ldtyfsb = cisHzyzYpSubmitReq.getYfsb();
			}
			if(!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getLyrq())) {
				ldtserver = cisHzyzYpSubmitReq.getLyrq();
			}
	
			/************************************************/
			//以前:ldt_server：1:默认当天时间，2:参数传入时变成默认第二天。3：在加上       “.getDateAfter(ldt_server, 1))”   1天 的天数，变成第三天的00：00：00，4：也就是到第三天的0点，实际发药发到第二天
			//5：如果时间为09:30:00，时间发药到第三天的早上
            //6:23:59:59,时间加0天。发药发到第二天23：59：59
            Date ldtserverby = ldtserver;
            sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.BQYPYLTS);
            String byypylts = sysXtcs == null ? "1" : sysXtcs.getCsz();
            String addylsj = " 23:59:59";
            if ("2".equals(byypylts)) {
                addylsj = " 09:30:00";
            } else if ("3".equals(byypylts)) {
                addylsj = " 12:30:00";
            }
            ldtserverby = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(ldtserverby, 0))
                    + addylsj);
            //病区药品提交预领药时间
            ldtserver = sdfdatetime.parse(sdfdate.format(BUHISUtil.getDateAfter(ldtserver, 1))
                    + ("2".equals(ypylsj) ? " 09:30:00" : " 00:00:00"));

            List<Map<String, Object>> collardrugdetailslist = new ArrayList<Map<String, Object>>();
            Map<Long, List<Date>> qrsjMap = new HashMap<Long, List<Date>>(16);
            Map<String, Object> collardrugdetailsparameters = new HashMap<String, Object>(16);
            collardrugdetailsparameters.put("JGID", manaUnitId);
            collardrugdetailsparameters.put("SRKS", alhsql);
            if (!StrUtil.isBlankIfStr(cisHzyzYpSubmitReq.getJlxhs())) {
                List<Integer> jlxhs = cisHzyzYpSubmitReq.getJlxhs();
                collardrugdetailsparameters.put("JLXHS", jlxhs);
			}
			if (alzyh != 0) {
				collardrugdetailsparameters.put("ZYH", alzyh);
			}
			if (ldtyfsb != 0) {
				collardrugdetailsparameters.put("YFSB", ldtyfsb);
			}
			if (ldtlsyz == 0 || ldtlsyz == 1) {
				collardrugdetailsparameters.put("LSYZ", ldtlsyz);
			} else if (ldtlsyz == 3) {
				collardrugdetailsparameters.put("LSYZ", 2);
			} else if (ldtlsyz == 4) {
				collardrugdetailsparameters.put("LSYZ", 3);
			}
			if (ldtfyfs != 0) {
				collardrugdetailsparameters.put("FYFS", ldtfyfs);
			}
			
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.FHYZHJF);
			String xyf = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if ("1".equals(xyf)) {
				collardrugdetailsparameters.put("FHBZ", 1);
			}
			collardrugdetailsparameters.put("TYPE", 2);
			collardrugdetailslist = cisHzyzDao.queryBqyzSubmit(collardrugdetailsparameters);
			//------------临时医嘱 bid 不乘以2等
			sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(manaUnitId, SysXtcsCsmcCts.LSYZSYPCCS);
			String lsyzsypccs = sysXtcs == null ? "0" : sysXtcs.getCsz();
			if("1".equals(lsyzsypccs)){
				for(int i = 0 ; i<collardrugdetailslist.size(); i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC_BAK", yz.get("SYPC"));
						yz.put("SYPC", "st");
					}	
				}			
			}
			
			// 处理领药明细表单数据
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				Date ldtqrsj = null;
				Date ldtkssj = null;
				if (collardrugdetailslist.get(i).get("QRSJ") != null) {
					ldtqrsj = sdfdate.parse(collardrugdetailslist.get(i).get("QRSJ") + "");
				}
				if (collardrugdetailslist.get(i).get("KSSJ") != null) {
					ldtkssj = sdfdate.parse(collardrugdetailslist.get(i).get("KSSJ") + "");
				}
				if (ldtqrsj == null) {
					continue;
				}
				//如果领药日期小于医嘱确认日期
				if (ldtserver.getTime() < ldtqrsj.getTime()
						|| ldtserver.getTime() < ldtkssj.getTime()) {
					collardrugdetailslist.remove(i);
					i--;
				}
			}
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			parameters.put("firstrow", 1);
			parameters.put("lastrow", collardrugdetailslist.size());
			parameters.put("ldt_server", ldtserver);
			/*******************/
			parameters.put("ldt_server_by", ldtserverby);
			/**********************/
			yzSubmit(collardrugdetailslist, qrsjMap, parameters, user);
			//------------临时医嘱 bid 不乘以2等
			if("1".equals(lsyzsypccs)){					
				for(int i = 0;i<collardrugdetailslist.size();i++){
					Map<String, Object> yz = collardrugdetailslist.get(i);
					if("1".equals(yz.get("LSYZ")+"")){
						yz.put("SYPC", yz.get("SYPC_BAK"));	
					}
				}
			}
			
			//-------------
			for (int i = 0; i < collardrugdetailslist.size(); i++) {
				int fycsTotal = ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("FYCS"));
				if (fycsTotal == 0) {
					collardrugdetailslist.remove(i);
					i--;
					continue;
				}
				if(!StrUtil.isBlankIfStr(collardrugdetailslist.get(i).get("KSSJ"))) {
					collardrugdetailslist.get(i).put("KSSJ", 
							DateUtil.parse(collardrugdetailslist.get(i).get("KSSJ").toString()));
				}
				if(!StrUtil.isBlankIfStr(collardrugdetailslist.get(i).get("TZSJ"))) {
					collardrugdetailslist.get(i).put("TZSJ", 
							DateUtil.parse(collardrugdetailslist.get(i).get("TZSJ").toString()));
				}
				
				collardrugdetailslist.get(i).put("YCSL", String.format("%1$.3f", collardrugdetailslist.get(i).get("YCSL")));
				ImDicYpyfModel imDicYpyf = imDicYpyfService.getById(ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YPYF")));
				collardrugdetailslist.get(i).put("YPYF", imDicYpyf.getXmmc());
	
				DicSypcModel dicSypc = dicSypcService.getById(ObjectToTypes.parseString(collardrugdetailslist.get(i).get("SYPC")));
				collardrugdetailslist.get(i).put("SYPC", dicSypc.getPcmc());
				
				PharYflb pharYflb = new PharYflb();
				pharYflb.setYfsb(ObjectToTypes.parseInt(collardrugdetailslist.get(i).get("YFSB")));
				pharYflb.setZxbz(0);
				pharYflb.setJgid(manaUnitId);
				
				PharBaseInfoReq req = BeanUtil.toBean(pharYflb, PharBaseInfoReq.class);
				List<PharBaseInfoResp> pharYflbList = pharBaseConfigService.findByEntity(req);
				
				if(CollectionUtils.isNotEmpty(pharYflbList)) {				
					collardrugdetailslist.get(i).put("YFSB", pharYflbList.get(0).getYfmc());
				}
				
				if (i == 0) {
					brch = collardrugdetailslist.get(i).get("BRCH") + "";
					brxm = collardrugdetailslist.get(i).get("BRXM") + "";
				} else {
					if (collardrugdetailslist.get(i).get("BRCH") != null
							&& collardrugdetailslist.get(i).get("BRCH")
									.toString().equals(brch)
							&& collardrugdetailslist.get(i).get("BRXM")
									.toString().equals(brxm)) {
						collardrugdetailslist.get(i).put("BRCH", "");
						collardrugdetailslist.get(i).put("BRXM", "");
					} else {
						brch = collardrugdetailslist.get(i).get("BRCH") + "";
						brxm = collardrugdetailslist.get(i).get("BRXM") + "";
					}
				}
				listMap.add(collardrugdetailslist.get(i));
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00047");			
		}
		return listMap;
	}

	/**
	 * 更新医嘱抗菌药物审批状态
	 * @Title: updateAntibioticsStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @param sqlx
	 * @param @param syly    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void updateAntibioticsStatus(List<CisHzyzAntibioticsReq> list) {
		for(CisHzyzAntibioticsReq req : list) {
			Integer kjywsp = null;
			Integer sqlx = req.getSqlx();
			String syly = req.getSyly();
			Integer jlxh = req.getJlxh();
			if((sqlx == 1 && !"".equals(syly)) || sqlx == 2) {
				kjywsp = 1;
			}else if(sqlx == 3) {
				kjywsp = 0;
			}
			cisHzyzDao.updateAntibioticsStatus(kjywsp, syly, jlxh);		
		}
		
	}
	
	/**
	 * 抗菌药物上级授权登录和权限校验
	 * @Title: checkAntimicrobialRights
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param loginName
	 * @param @param passwd
	 * @param @param kssdj
	 * @param @return    设定文件
	 * @return Integer    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public String checkAntimicrobialRights(CisHzyzCheckAntimicrobialReq req) {
		Integer userId = hrPersonnelService.getPersonId(req.getLoginName());
		boolean flag = hrPersonnelService.checkPassWd(userId, req.getPasswd());
		if(flag) {
			List<YzxxBody> list = req.getYzxxList();
			String[] strLevel = null;
			int level = 0;
			HrPersonnelModel hrPersonnel = hrPersonnelService.getPersonnelById(userId);
			if(StrUtil.isNotBlank(hrPersonnel.getAntibioticlevel())) {
				strLevel =  hrPersonnel.getAntibioticlevel().split(",");
				int[] array = Arrays.asList(strLevel).stream().mapToInt(Integer::parseInt).toArray();
				level = Arrays.stream(array).max().getAsInt();
			}
			StringBuffer resMsg = new StringBuffer();
			for(YzxxBody body : list) {
				Integer kssdj = body.getKssdj();
				if(level < kssdj.intValue() || kssdj.intValue() == 3) {
					CisHzyz cisHzyz = cisHzyzDao.getById(body.getJlxh());
					resMsg.append(cisHzyz.getYzmc()).append(",");
				}else {
					cisHzyzDao.updateAntibioticsStatus(1, null, body.getJlxh());		
				}
			}
			if(resMsg.length() > 0) {
				resMsg.deleteCharAt(resMsg.length() - 1);
				ReturnEntity<String> msg = new ReturnEntity<String>();
				msg.setError("ERROR_DCTWORK_ZYBQYZ_00050", new String[] {
						resMsg.toString()	
				});
				return msg.getMessage();
			}
			
		}else {
			ReturnEntity<String> msg = new ReturnEntity<String>();
			msg.setError("ERROR_DCTWORK_ZYBQYZ_00051", null);
			return msg.getMessage();
		}
		return null;
	}
	
	

	/**
	 * 医嘱提交 collardrugdetailslist里是用于提交的药品数据集合,领药日期 医嘱执行时间根据医嘱序号返回到Map对象中
	 * @Title: yzSubmit(原uf_yztj)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param collardrugdetailslist
	 * @param @param qrsjMap
	 * @param @param parameters
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void yzSubmit(List<Map<String, Object>> collardrugdetailslist, Map<Long, List<Date>> qrsjMap,
			Map<String, Object> parameters, SysUser user) {
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int ll_fycs_total = 0;
		try {
			Date adt_ylrq = sdfdate.parse(sdfdate.format(parameters.get("ldt_server")));
			/*********************************************/
			Date adt_ylrq_by = adt_ylrq;// 医嘱摆药发药时间提前到第二天12点，医嘱发药的发药时间不变，到第二天晚上
			if (null != parameters.get("ldt_server_by")) {
				adt_ylrq_by = sdfdate.parse(sdfdate.format(parameters.get("ldt_server_by")));
			}
			/*********************************************/
			List<Map<String, Object>> dicsypcList = frequencyList();
			for (int ll_row = 0; ll_row < collardrugdetailslist.size(); ll_row++) {
				List<Date> ldt_qrsj_list = new ArrayList<Date>();
				// 医嘱序号
				Long ll_yzxh = Long.parseLong(collardrugdetailslist.get(ll_row).get("JLXH") + "");
				String ldt_kssj = null;
				// 开始时间
				if (collardrugdetailslist.get(ll_row).get("KSSJ") != null) {
					ldt_kssj = collardrugdetailslist.get(ll_row).get("KSSJ") + "";
				}
				String ldt_tzsj = null;
				// 停止时间
				if (collardrugdetailslist.get(ll_row).get("TZSJ") != null) {
					ldt_tzsj = collardrugdetailslist.get(ll_row).get("TZSJ") + "";
				}
				String ldt_qrsj = null;
				// 确认时间
				if (collardrugdetailslist.get(ll_row).get("QRSJ") != null) {
					ldt_qrsj = collardrugdetailslist.get(ll_row).get("QRSJ") + "";
				}
				// 使用频次
				String ls_sypc = collardrugdetailslist.get(ll_row).get("SYPC") + "";

				int ll_lsyz = Integer.parseInt(collardrugdetailslist.get(ll_row).get("LSYZ") + "");// 临时医嘱标志
				// 医嘱执行时间字符串
				String ls_yzzxsj_str = collardrugdetailslist.get(ll_row).get("YZZXSJ") + "";
				// 药房包装
				int ll_yfbz = Integer.parseInt(collardrugdetailslist.get(ll_row).get("YFBZ") + "");
				// 首日次数
				int ll_srcs = Integer.parseInt(collardrugdetailslist.get(ll_row).get("SRCS") + "");
				// 项目类型
				int ll_xmlx = Integer.parseInt(collardrugdetailslist.get(ll_row).get("XMLX") + "");
				// 首日次数
				Double ycsl = ObjectToTypes.parseDouble(collardrugdetailslist.get(ll_row).get("YCSL"));
				Map<String, Object> sjparameters = new HashMap<String, Object>(16);
                sjparameters.put("ldt_kssj", ldt_kssj);
                sjparameters.put("ldt_qrsj", ldt_qrsj);
                sjparameters.put("ldt_tzsj", ldt_tzsj);
                sjparameters.put("ls_sypc", ls_sypc);
                sjparameters.put("ls_yzzxsj_str", ls_yzzxsj_str);
                sjparameters.put("ll_lsyz", ll_lsyz);
                sjparameters.put("al_ypbz", 1);
                if (ll_yfbz + "" == "" || ll_yfbz == 0) {
                    ll_yfbz = 1;
                }
                int ll_lsbz = BUHISUtil.uf_cacl_lsbz(dicsypcList, sjparameters);
                if (ll_lsbz == 1) {
                    parameters.put("ll_yzxh", ll_yzxh);
                    yzUpdateLsbz(parameters, user);
                    collardrugdetailslist.get(ll_row).put("FYCS", 0);
                    continue;
                }
                parameters.put("ldt_kssj", ldt_kssj);
                parameters.put("ldt_qrsj", ldt_qrsj);
                parameters.put("ldt_tzsj", ldt_tzsj);
                parameters.put("adt_ylrq", adt_ylrq);
				parameters.put("ll_srcs", ll_srcs);
				parameters.put("ls_sypc", ls_sypc);
				parameters.put("ls_yzzxsj_str", ls_yzzxsj_str);
				parameters.put("al_fybz", 1);
				// 得到发药次数
				// 长期医嘱
				if (ll_lsyz == 0) {
					/*********************************************/
					String ldt_fyfs = collardrugdetailslist.get(ll_row).get("FYFS") + "";
					// 发药方式，2：摆药
					if ("2".equals(ldt_fyfs)) {
                        // 摆药
                        // 摆药发药预领时间
                        parameters.put("adt_ylrq", adt_ylrq_by);
                        ll_fycs_total = BUHISUtil.uf_cacl_fycs_cq(dicsypcList, parameters, ldt_qrsj_list);
                    } else {
                        parameters.put("adt_ylrq", adt_ylrq);
                        ll_fycs_total = BUHISUtil.uf_cacl_fycs_cq(dicsypcList, parameters, ldt_qrsj_list);
                    }
				} else {
					// 临时医嘱
					if (ll_xmlx != 3) {
                        ll_fycs_total = BUHISUtil.uf_cacl_fycs_ls(dicsypcList, parameters, ldt_qrsj_list);
                    }
				}
				if (ll_xmlx == 3) {
					collardrugdetailslist.get(ll_row).put("FYCS", 1);
					collardrugdetailslist.get(ll_row).put("JE", String.format("%1$.2f",
							ycsl * 1 * ObjectToTypes.parseDouble(collardrugdetailslist.get(ll_row).get("YPDJ"))));
				} else {
					collardrugdetailslist.get(ll_row).put("FYCS", ll_fycs_total);
					collardrugdetailslist.get(ll_row).put("JE", String.format("%1$.2f", ycsl * ll_fycs_total
							* ObjectToTypes.parseDouble(collardrugdetailslist.get(ll_row).get("YPDJ"))));
				}
				qrsjMap.put(ll_yzxh, ldt_qrsj_list);
			}

		} catch (ParseException e) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00021");
		}
	}
	
	/**
	 * 使用频次数据集
	 * @Title: frequencyList(原u_his_share_yzzxsj)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> frequencyList() {
		List<Map<String, Object>> sypcList = null;
		try {
            List<DicSypcModel> dicSypcList = dicSypcService.findByEntity(null);
            sypcList = BUHISUtil.ListObjToMap(dicSypcList);
        } catch (Exception e) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0001");
		}
		return sypcList;
	}
	
	
	/**
	 * 将指定医嘱转为历史医嘱 
	 * @Title: yzUpdateLsbz(原uf_update_lsbz)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void yzUpdateLsbz(Map<String, Object> parameters, SysUser user) {
		Integer manageUnit = user.getHospitalId();
		try {
			Map<String, Object> parametersjlxh = new HashMap<String, Object>(16);
			if (parameters.get("ll_yzxh") != null) {
				parametersjlxh.put("JLXH", ObjectToTypes.parseInt(parameters.get("ll_yzxh")));
			} else {
				parametersjlxh.put("JLXH", 0);
			}
			parametersjlxh.put("JGID", manageUnit);
			cisHzyzDao.modifyToHistoricalAppointOrders(parametersjlxh);
		} catch (Exception e) {
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00022");
		}
	}
	
	/**
	 * 病区医嘱（欠费控制）
	 * @Title: ArrearsPatientsQuery
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param collardrugdetailslist
	 * @param @param user
	 * @param @param nisTjMsgResp
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public boolean ArrearsPatientsQuery(List<Map<String, Object>> collardrugdetailslist, SysUser user,
			NisTjMsgResp nisTjMsgResp) {
		Integer jgid = user.getHospitalId();
		// 私有参数 是否管理收费（0不管，1管）
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZYQFKZ);
		String zyqfkz = sysXtcs == null ? "0" : sysXtcs.getCsz();
		int ll_qfkz = Integer.parseInt(zyqfkz);
		Map<String, Object> Ls_brxm = new HashMap<String, Object>(16);
		long ll_zyh = 0;
		String brxString = " ";
		String zyhString = " ";
		boolean flag = false;// 定义返回值，如果有超过余额的返回true 否则返回false
		for (int ll_row = 0; ll_row < collardrugdetailslist.size(); ll_row++) {
			Map<String, Object> rowMap = collardrugdetailslist.get(ll_row);
			Map<String, Object> body = new HashMap<String, Object>(16);
			body.put("TYPE", rowMap.get("YPLX"));
			body.put("BRXZ", rowMap.get("BRXZ"));
			body.put("FYXH", rowMap.get("YPXH"));
			if (rowMap.get("FYGB") != null) {
				body.put("FYGB", rowMap.get("FYGB"));
			} else {
				body.put("FYGB", 0);
			}
			Map<String, Object> zfblMap = getzfbl(body);
			double zfbl = Double.parseDouble(zfblMap.get("ZFBL") + "");
			// 欠费管理 且不是市医保
			boolean qfkzFlag = ll_qfkz > 0
					&& (rowMap.get("BRXZ") != null && !"6089".equals(rowMap.get("BRXZ").toString()));
			if (qfkzFlag) {
				long ZYH = Long.parseLong(collardrugdetailslist.get(ll_row).get("ZYH") + "");
				if ((ll_row == 0) || (ZYH != ll_zyh)) {
					ll_zyh = ZYH;
					double ld_hj = 0;
					for (int ll_row1 = ll_row; ll_row1 < collardrugdetailslist.size(); ll_row1++) {
                        long ZYH1 = Long.parseLong(collardrugdetailslist.get(ll_row1).get("ZYH") + "");
                        double je = BUHISUtil.parseDouble(collardrugdetailslist.get(ll_row1).get("JE"));
                        if (ZYH1 == ll_zyh) {
                            ld_hj += je;
                        }
                    }
					if (arrearageControl(ll_zyh, ld_hj * zfbl, jgid, Ls_brxm) == 1) {
						for (int ll_row1 = ll_row; ll_row1 < collardrugdetailslist.size(); ll_row1++) {
							long ZYH1 = Long.parseLong(collardrugdetailslist.get(ll_row1).get("ZYH") + "");
							if (collardrugdetailslist.get(ll_row).get("ok") != null) {
								String ok = collardrugdetailslist.get(ll_row).get("ok") + "";
								if (ZYH1 == ll_zyh && ok.equals("1")) {
									collardrugdetailslist.get(ll_row1).put("SFTJ", 1);
								}
							} else {
								if (ZYH1 == ll_zyh) {
									collardrugdetailslist.get(ll_row1).put("SFTJ", 1);
								}
							}
						}
					} else {
						for (int ll_row1 = ll_row; ll_row1 < collardrugdetailslist.size(); ll_row1++) {
							long ZYH1 = Long.parseLong(collardrugdetailslist.get(ll_row1).get("ZYH") + "");
							if (collardrugdetailslist.get(ll_row).get("ok") != null) {
								String ok = collardrugdetailslist.get(ll_row).get("ok") + "";
								if (ZYH1 == ll_zyh && ok.equals("1")) {
									collardrugdetailslist.get(ll_row1).put("SFTJ", 0);
								}
							} else {
								if (ZYH1 == ll_zyh) {
									collardrugdetailslist.get(ll_row1).put("SFTJ", 0);
								}
							}
						}
					}
				}
				if (Ls_brxm.get("Ls_brxm") != null) {
					// int num = 0;
					try {
						for (int ll_row1 = ll_row + 1; ll_row1 < collardrugdetailslist.size(); ll_row1++) {
							long ZYH1 = Long.parseLong(collardrugdetailslist.get(ll_row1).get("ZYH") + "");
							if (ZYH1 == Long.parseLong(Ls_brxm.get("Ls_brxm") + "")) {
								collardrugdetailslist.remove(ll_row1);
								ll_row1--;
							}

						}
						ImHzry imHzry = imHzryDao.getById(ObjectToTypes.parseInt(Ls_brxm.get("Ls_brxm")));
						brxString += "'" + imHzry.getBrxm() + "'";
						zyhString += "'" + Ls_brxm.get("Ls_brxm") + "'";
						nisTjMsgResp.setResBrxmMsg(brxString);
						nisTjMsgResp.setResZyhMsg(zyhString);
						collardrugdetailslist.remove(ll_row);
						ll_row--;
						Ls_brxm.clear();
						if (brxString.length() > 0) {
							ReturnEntity<String> returnEntity = new ReturnEntity<>();
							returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00037", new String[] { brxString });
							nisTjMsgResp.setResErrorMsg(returnEntity.getMessage());
							flag = true;
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00028");
					}
				}
			}
		}
		return flag;
	}
	
	
	/**
	 * 项目执行获取自付比例
	 * @Title: getzfbl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param body
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getzfbl(Map<String, Object> body) {
		Integer al_yplx = ObjectToTypes.parseInt(body.get("TYPE"));// 药品传药品类型1,2,3,费用传0
		Integer al_brxz = ObjectToTypes.parseInt(body.get("BRXZ"));// 病人性质
		Integer al_fyxh = ObjectToTypes.parseInt(body.get("FYXH"));// 费用序号
		Integer al_fygb = ObjectToTypes.parseInt(body.get("FYGB"));// 费用归并
		Map<String, Object> reMap = new HashMap<String, Object>(16);
		reMap.put("FYGB", al_fygb);
		reMap.put("ZFBL", 1);
		Map<String, Object> map = new HashMap<String, Object>(16);
		if (al_yplx == 0) {
			PubFyxzApiReq pubFyxz = new PubFyxzApiReq();
			pubFyxz.setBrxz(al_brxz);
			pubFyxz.setFyxh(al_fyxh);
			map = pubFyxzService.getFyjyInfo(pubFyxz);
		} else {
			FeeYpxzApiReq feeYpxz = new FeeYpxzApiReq();
			feeYpxz.setBrxz(al_fyxh);
			feeYpxz.setYpxh(al_fyxh);
			// 查询药品禁用信息
			map = feeYpxzService.getYpjyInfo(feeYpxz);
		}

		if (map == null) {
			if (al_fygb == 0) {
				al_fygb = getfygb(al_yplx, al_fyxh);
				reMap.put("FYGB", al_fygb);
			}
			FeeSfdlzfbl feeSfdlzfbl = new FeeSfdlzfbl();
			feeSfdlzfbl.setBrxz(al_brxz);
			feeSfdlzfbl.setSfxm(al_fygb);
			Map<String, Object> zfblMap = feeSfdlzfblOutSer.getZfblInfo(feeSfdlzfbl);
			if (zfblMap != null) {
				reMap.put("ZFBL", zfblMap.get("ZFBL"));
			}
		} else {
			reMap.put("ZFBL", map.get("ZFBL"));
		}
		return reMap;
	}
	
	/**
	 * 获取费用归并
	 * @Title: getfygb
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param al_yplx
	 * @param @param al_fyxh
	 * @param @return    设定文件
	 * @return Integer    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Integer getfygb(Integer al_yplx, Integer al_fyxh) {
		Integer al_fygb;
		if (al_yplx == 1 || al_yplx == 2 || al_yplx == 3) {// 如果不是费用,先查询有吴账簿类别,没有账簿类别则按药品类型分
			DrugsTypkDetailResp drugsTypk = drugsTypkOutSer.getDrugsTypkById(al_fyxh);
			if (drugsTypk != null && drugsTypk.getZblb() != null && drugsTypk.getZblb().intValue() > 0) {
				al_fygb = drugsTypk.getZblb();
			} else {
				throw BaseException.create("ERROR_REG_0011");
			}
		} else {
			FeeYlsfxmOutResp feeYlsfxm = feeYlsfxmOutSer.getById(al_fyxh);
			al_fygb = feeYlsfxm.getFygb();
		}
		return al_fygb;
	}
	
	
	/**
	 * 获取费用自负比例
	 * @Title: getPayProportion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param body
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getPayProportion(Map<String, Object> body) {
		Integer al_yplx = Integer.parseInt(body.get("TYPE") + "");// 药品传药品类型1,2,3,费用传0
		Object al_brxz = body.get("BRXZ");// 病人性质
		Object al_fyxh = body.get("FYXH");// 费用序号
		Object al_fygb = body.get("FYGB");// 费用归并
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("BRXZ", al_brxz);
		params.put("FYXH", al_fyxh);
		Map<String, Object> map = new HashMap<String, Object>(16);
		if (al_yplx == 0) {
			PubFyxzApiReq pubFyxz = new PubFyxzApiReq();
			pubFyxz.setBrxz(Integer.valueOf(params.get("BRXZ").toString()));
			pubFyxz.setFyxh(Integer.valueOf(params.get("FYXH").toString()));
			// 查询费用禁用信息
			map = pubFyxzService.getFyjyInfo(pubFyxz);
		} else {
			FeeYpxzApiReq feeYpxz = new FeeYpxzApiReq();
			feeYpxz.setBrxz(Integer.valueOf(params.get("BRXZ").toString()));
			feeYpxz.setYpxh(Integer.valueOf(params.get("FYXH").toString()));
			// 查询药品禁用信息
			map = feeYpxzService.getYpjyInfo(feeYpxz);
		}
		if (map != null) {
			if (map.get("FYXE") == null || map.get("FYXE") == "") {
				map.put("FYXE", 0.0);
			}
			if (map.get("CXBL") == null || map.get("CXBL") == "") {
				map.put("CXBL", 0.0);
			}
			map.put("CXBL", ObjectToTypes.parseBigDecimal(map.get("CXBL")).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP));
			return map;
		}
		params.clear();
		params.put("BRXZ", al_brxz);
		params.put("FYGB", al_fygb);

		FeeSfdlzfbl feeSfdlzfbl = new FeeSfdlzfbl();
		feeSfdlzfbl.setBrxz(Integer.valueOf(params.get("BRXZ").toString()));
		feeSfdlzfbl.setSfxm(Integer.valueOf(params.get("FYGB").toString()));
		Map<String, Object> zfblMap = feeSfdlzfblOutSer.getZfblInfo(feeSfdlzfbl);
		if (zfblMap == null) {
			zfblMap = new HashMap<String, Object>(16);
			zfblMap.put("ZFBL", 1);
		}
		return zfblMap;
	}
	
	
	/**
	 * 根据传入的药品数量判断库存是否满足 还要根据零售价格判断
	 * @Title: checkInventory
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param body
	 * @param @param jgid
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> checkInventory(Map<String, Object> body, Integer jgid) {
		Object pharmId = body.get("pharmId");// 药房编号
		Object medId = body.get("medId");// 药品编号
		Object medsource = body.get("medsource");// 药品产地
		Object quantity = body.get("quantity");// 药品总量
		QueryDjYpslReq queryDjYpslReq = new QueryDjYpslReq();
		queryDjYpslReq.setYfsb(ObjectToTypes.parseInt(pharmId));
		queryDjYpslReq.setYpxh(ObjectToTypes.parseInt(medId));
		queryDjYpslReq.setYpcd(ObjectToTypes.parseInt(medsource));

		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("YFSB", Long.parseLong(pharmId.toString()));
		params.put("YPXH", Long.parseLong(medId.toString()));
		params.put("YPCD", Long.parseLong(medsource.toString()));
		List<Map<String, Object>> list = drugService.getKczlAndLsjg(ObjectToTypes.parseInt(pharmId),
				ObjectToTypes.parseInt(medId), ObjectToTypes.parseInt(medsource));

		HashMap<String, Object> map = null;
		if (list == null || list.size() == 0) {
			map = new HashMap<String, Object>(16);
			map.put("sign", -1);
			map.put("KCZL", 0);
			return map;
		}
		map = (HashMap<String, Object>) list.get(0);
		Double kczl = Double.valueOf(map.get("kczl").toString());
		// 是否启用库存冻结
		int sfqyyfyfy = Integer.parseInt(sysXtcsCacheSer.getCsz(jgid, "SFQYYFYFY"));
		Integer kcdjts = ObjectToTypes.parseInt(sysXtcsCacheSer.getCsz(jgid, "KCDJTS"));
		if (sfqyyfyfy == 1) {
			Integer jlxh = 0;
			if (body.containsKey("jlxh")) {
				jlxh = ObjectToTypes.parseInt(body.get("jlxh"));
			}
			queryDjYpslReq.setJlxh(jlxh);
			queryDjYpslReq.setKcdjts(kcdjts);
			params.put("jlxh", jlxh);
			params.put("kcdjts", kcdjts);
			// params.put("LSJG", map.get("LSJG"));

			BigDecimal djsl = drugService.sumYpsl(queryDjYpslReq);

			if (djsl != null ) {
				kczl -= djsl.doubleValue();
			}
		}
		// 库存冻结代码结束
		if (kczl == null || kczl < Double.parseDouble(quantity.toString())) {
			if (kczl == null) {
				map.put("KCZL", 0);
			} else {
				DecimalFormat df = new DecimalFormat("#.00");
				map.put("KCZL", df.format(kczl));
			}
			map.put("sign", -1);
		} else {
			map.put("sign", 1);
		}
		return map;
	}
	
	 
	/**
	 * 欠费控制判断
	 * @Title: arrearageControl(原Wf_qfkz)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param ad_fsje
	 * @param @param jgid
	 * @param @param Ls_brxm
	 * @param @return    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public long arrearageControl(long zyh, double ad_fsje, Integer jgid, Map<String, Object> Ls_brxm) {
		try {
			BigDecimal jkhj = imTbkkDao.queryTotalPayment(ObjectToTypes.parseInt(zyh), jgid);

			CardPatientCostBaseInfoJe map = imFeeFymxDao.queryPatientCost(ObjectToTypes.parseInt(zyh), jgid);

			double ld_jkhj = 0;
			double ld_fyhj = 0;
			double ld_zfhj = 0;
			if (jkhj != null) {
				ld_jkhj = jkhj.doubleValue();
			}
			ld_fyhj = ObjectToTypes.parseDouble(map.getFyhj());
			ld_zfhj = ObjectToTypes.parseDouble(map.getZfhj());
			double ld_qfje = ld_zfhj - ld_jkhj;
			String Ls_brxm_s = "";
			if ((ld_qfje >= 0) || (ld_qfje + ad_fsje > 0)) {
				long ls_brxm_new = zyh;
				if (Ls_brxm_s.equals("")) {
					Ls_brxm_s = ls_brxm_new + "";
				} else {
					Ls_brxm_s += "," + ls_brxm_new;
				}
				Ls_brxm.put("Ls_brxm", Ls_brxm_s);
				return 0;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBQYZ_00027");
		}
		return 1;
	}
	
	/**
	 * 查询皮试结果类别代码
	 * @Title: queryAllergicDrugsCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return Integer    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Integer queryAllergicDrugsCode(Integer zyh) {
		//默认为无
		Integer pslb = null;
		//取医嘱最近一条
		List<Map<String, Object>> list = cisHzyzDao.queryPslb(zyh);
		if(CollectionUtils.isNotEmpty(list)) {
			pslb = ObjectToTypes.parseInt(list.get(0).get("pslb"));
		}
		return pslb;
	}
	
	/**
	 * 查询患者历史医嘱信息
	 * @Title: queryMedicalAdviceInfoByZyh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<CisHzyz>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisHzyz> queryMedicalAdviceInfoByZyh(Integer zyh, SysUser user) {
		return cisHzyzDao.queryMedicalAdviceInfoByZyh(user.getHospitalId(), zyh);
	}
	
	
	/**
	 * 医嘱复制
	 * @Title: copyMedicalAdviceInfoHistoryToNew
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzCopyReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void copyMedicalAdviceInfoHistoryToNew(CisHzyzCopyReq cisHzyzCopyReq, SysUser user) {
		//先分组获取组号,map存上新的组号
		List<CopyBody> list = cisHzyzCopyReq.getCopyBodyList();
		
		List<CopyBody> cyList = list.stream().filter(o -> "3".equals(ObjectToTypes.parseString(o.getYplx())) || 
				"5".equals(ObjectToTypes.parseString(o.getYzlx()))).collect(Collectors.toList());
		
		List<CopyBody> notCyList = list.stream().filter(o -> !"3".equals(ObjectToTypes.parseString(o.getYplx())))
				.collect(Collectors.toList());
		
		Map<Integer, List<CopyBody>> cyGroups = cyList.stream().
    			collect(Collectors.groupingBy(CopyBody::getYzzh));
		for(Map.Entry<Integer, List<CopyBody>> entry : cyGroups.entrySet()) {
			Integer ztjlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ);
			Integer yzzh = redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh");
			StringBuffer strBuffer = new StringBuffer();
			BigDecimal ztdj = BigDecimal.ZERO;
			for(CopyBody body : entry.getValue()) {
				strBuffer.append(body.getYzmc()).append("+");
				ztdj = ztdj.add(body.getYpdj().multiply(body.getYcsl()));
				body.setKssj(DateUtil.date().toTimestamp());
				body.setQrsj(null);
				body.setYsgh(ObjectToTypes.parseString(user.getUserId()));
				body.setCzgh(ObjectToTypes.parseString(user.getUserId()));
				body.setFhgh(null);
				body.setSybz(0);
				body.setSrks(cisHzyzCopyReq.getBrks());
				body.setYsbz(1);
				body.setYstj(0);
				body.setZfbz(0);
				body.setLsbz(0);
				body.setBrks(cisHzyzCopyReq.getBrks());
				body.setBrbq(cisHzyzCopyReq.getBrbq());
				body.setBrch(cisHzyzCopyReq.getBrch());
				body.setFhbz(0);
				body.setFhsj(null);
				body.setTzfhbz(0);
				body.setTzfhr(null);
				body.setTzfhsj(null);
				body.setPsjg(null);
				body.setYwid(null);
				body.setKjywsp(null);
				body.setTzzt(null);
				body.setTztime(null);
				body.setTzgh(null);
				body.setYzzf(null);
				body.setZyh(cisHzyzCopyReq.getZyh());
				body.setTzsj(DateUtil.date().toTimestamp());
				body.setTzys(ObjectToTypes.parseString(user.getUserId()));
				body.setSypc("st");
				body.setYzzxsj("23:59");
				body.setYzzh(yzzh);
				body.setZtyzjlxh(ztjlxh);
				body.setZtbz(1);
				body.setYzlx(5);
				body.setJfbz(1);
				body.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
				cisHzyzDao.insert(body);
			}
			CisHzyzZt cisHzyzZt = new CisHzyzZt();
			cisHzyzZt.setJgid(user.getHospitalId());
			cisHzyzZt.setZyh(cisHzyzCopyReq.getZyh());
			cisHzyzZt.setYzmc(strBuffer.deleteCharAt(strBuffer.length() - 1).toString());
			cisHzyzZt.setYpxh(0);
			cisHzyzZt.setYpcd(0);
			cisHzyzZt.setXmlx(1);
			cisHzyzZt.setYplx(3);
			cisHzyzZt.setMrcs(1);
			cisHzyzZt.setYcjl(entry.getValue().get(0).getYcjl());
			cisHzyzZt.setYcsl(entry.getValue().get(0).getYcsl());
			cisHzyzZt.setMzcs(entry.getValue().get(0).getMrcs());
			cisHzyzZt.setKssj(DateUtil.date().toTimestamp());
			cisHzyzZt.setTzsj(DateUtil.date().toTimestamp());
			cisHzyzZt.setYpdj(ztdj);
			cisHzyzZt.setYpyf(entry.getValue().get(0).getYpyf());
			cisHzyzZt.setYsgh(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setTzys(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setCzgh(ObjectToTypes.parseString(user.getUserId()));
			cisHzyzZt.setSybz(0);
			cisHzyzZt.setSrks(cisHzyzCopyReq.getBrks());
			cisHzyzZt.setZfpb(0);
			cisHzyzZt.setYjzx(0);
			cisHzyzZt.setYjxh(0);
			cisHzyzZt.setZxks(cisHzyzCopyReq.getBrks());
			cisHzyzZt.setYzzh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ_ZT +".yzzh"));
			cisHzyzZt.setSypc("st");
			cisHzyzZt.setFysx(0);
			cisHzyzZt.setYepb(0);
			cisHzyzZt.setYfsb(entry.getValue().get(0).getYfsb());
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
			cisHzyzZt.setBrks(cisHzyzCopyReq.getBrks());
			cisHzyzZt.setBrbq(cisHzyzCopyReq.getBrbq());
			cisHzyzZt.setBrch(cisHzyzCopyReq.getBrch());
			cisHzyzZt.setYzzxsj("23:59");
			cisHzyzZt.setFhbz(0);
			cisHzyzZt.setTzfhbz(0);
			cisHzyzZt.setPspb(0);
			cisHzyzZt.setCfts(entry.getValue().get(0).getCfts());
			cisHzyzZt.setYpzs(1);
			cisHzyzZt.setJz(entry.getValue().get(0).getJz());
			cisHzyzZt.setZfyp(0);
			cisHzyzZt.setZtbz(1);
			cisHzyzZt.setYzlx(5);
			cisHzyzZt.setBzxx(entry.getValue().get(0).getBzxx());
			cisHzyzZt.setJlxh(ztjlxh);
			cisHzyzZtDao.insert(cisHzyzZt);
		}
		
		
		Map<Integer, List<CopyBody>> groups = notCyList.stream().
    			collect(Collectors.groupingBy(CopyBody::getYzzh));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, List<CopyBody>> entry : groups.entrySet()){
	        map.put(entry.getKey(), redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));
	    }
		for(CopyBody body : notCyList) {
//			if(body.getYzlx() != null) {
//				if(body.getYzlx() == 0) {
//					//检查
//					
//				}else if(body.getYzlx() == 1) {
//					//检验
//				}else if(body.getYzlx() == 2) {
//					//备血
//				}else if(body.getYzlx() == 3) {
//					//手术
//				}else if(body.getYzlx() == 4) {
//					//会诊
//				}else if(body.getYzlx() == 6) {
//					//理疗
//				}
//					
//			}
			for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if(body.getYzzh().equals(entry.getKey())) {
					body.setYzzh(entry.getValue());
				}
			}
			body.setKssj(DateUtil.date().toTimestamp());
			body.setQrsj(null);
			body.setTzsj(null);
			body.setYsgh(ObjectToTypes.parseString(user.getUserId()));
			body.setTzys(null);
			body.setCzgh(ObjectToTypes.parseString(user.getUserId()));
			body.setFhgh(null);
			body.setSybz(0);
			body.setSrks(cisHzyzCopyReq.getBrks());
			body.setYsbz(1);
			body.setYstj(0);
			body.setZfbz(0);
			body.setLsbz(0);
			body.setBrks(cisHzyzCopyReq.getBrks());
			body.setBrbq(cisHzyzCopyReq.getBrbq());
			body.setBrch(cisHzyzCopyReq.getBrch());
			body.setFhbz(0);
			body.setFhsj(null);
			body.setTzfhbz(0);
			body.setTzfhr(null);
			body.setTzfhsj(null);
			body.setPsjg(null);
			body.setYwid(null);
			body.setKjywsp(null);
			body.setTzzt(null);
			body.setTztime(null);
			body.setTzgh(null);
			body.setYzzf(null);
			body.setZyh(cisHzyzCopyReq.getZyh());
			if(!StrUtil.isBlankIfStr(cisHzyzCopyReq.getYzlx())) {
				if(cisHzyzCopyReq.getYzlx() == 0) {
					body.setLsyz(0);
				}else if(cisHzyzCopyReq.getYzlx() == 1) {
					body.setLsyz(1);
					body.setMrcs(1);
					body.setMzcs(0);
					body.setTzsj(DateUtil.date().toTimestamp());
					body.setTzys(ObjectToTypes.parseString(user.getUserId()));
					body.setSypc("st");
					body.setYzzxsj("23:59");
				}else if(cisHzyzCopyReq.getYzlx() == 2) {
					body.setLsyz(1);
					body.setXmlx(2);
				}else if(cisHzyzCopyReq.getYzlx() == 3) {
					body.setLsyz(1);
					body.setXmlx(3);
				}
			}
			
			body.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
			cisHzyzDao.insert(body);
		}
	}

	/**
	 * 根据住院批量查询检查申请单序号
	 * @Title: queryBatchJcxhByZyh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<CisJcsq01QueryResp> queryBatchJcxhByZyh(Integer zyh) {
		CisJcsq01QueryReq req = new CisJcsq01QueryReq();
		req.setZyh(zyh);
		return cisjcsqd01Service.queryZyJcSqdListJcxh(req);
	}
	
	/**
	 * 药品提交校验库存
	 * @Title: inventoryVerification
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<NisTjSureResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<NisTjSureResp> inventoryVerification(List<NisTjSureReq> nisTjSureReqList) {
        List<NisTjSureResp> resList = new ArrayList<NisTjSureResp>();
        if (!nisTjSureReqList.isEmpty()) {
        	Map<String, DoubleSummaryStatistics> groups = nisTjSureReqList.stream().
        			collect(Collectors.groupingBy(o -> o.getMedId() + ","+ o.getMedsource(), 
            		LinkedHashMap:: new, Collectors.summarizingDouble(e -> e.getQuantity().doubleValue())));
        	System.out.println(groups);
	        List<String> strList =  new ArrayList<String>();
	        strList.addAll(groups.keySet());
	        Integer yfsb = nisTjSureReqList.get(0).getYfsb();
			List<Map<String, Object>> medicineInventory= drugService.queryHospitalizationStock(ObjectToTypes.parseInt(yfsb), strList);
			
			List<Integer> ypxhList = medicineInventory.stream().map(o ->
			ObjectToTypes.parseInt(o.get("YPXH"))).collect(Collectors.toList());
			
			List<NisTjSureReq> notExistList = nisTjSureReqList.stream().filter(
			o -> !ypxhList.contains(o.getMedId())).collect(Collectors.toList());
			for(NisTjSureReq med : notExistList) {
				NisTjSureResp resp = new NisTjSureResp();
				resp.setMedId(med.getMedId());
				resp.setMedsource(med.getMedsource());
				resList.add(resp);
			}
			
			for(Map.Entry<String, DoubleSummaryStatistics> entry : groups.entrySet()) {
				for(Map<String, Object> medInv : medicineInventory) {
					if(entry.getKey().equals(ObjectToTypes.parseString(medInv.get("YPXH")) + "," + 
							ObjectToTypes.parseString(medInv.get("YPCD")))){
						if(entry.getValue().getSum() > ObjectToTypes.parseDouble(medInv.get("KCZL"))){
							NisTjSureResp resp = new NisTjSureResp();
							resp.setMedId(ObjectToTypes.parseInt(medInv.get("YPXH")));
							resp.setMedsource(ObjectToTypes.parseInt(medInv.get("YPCD")));
							resList.add(resp);
						}
						
					}
				}
			}
        }
		return resList.stream().distinct().collect(Collectors.toList());
	}


	/**
	 * @Description
	 * @author  gfz
	 * @Date  2021/5/31 20:52
	 * @param list
	 * @param sysUser
	 * @Return  String
	 * @Exception
	*/
	public String stopOrdersAndCheckAuthority(List<CisHzyzStopCheckReq> list, SysUser sysUser) {
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(sysUser.getHospitalId(), SysXtcsCsmcCts.SFJYYSJB);	
		String jyjb = sysXtcs.getCsz();
		Integer userId = sysUser.getUserId();
		String warnMsg = "";
		List<CisHzyzStopCheckReq> newList = list;
		//参数控制是否校验医生权限
		if("1".equals(jyjb)) {
			List<String> ysghList = list.stream().map(CisHzyzStopCheckReq::getYsgh).collect(Collectors.toList());
			HrPersonnelModel model = hrPersonnelService.getPersonnelById(userId);
			if(model != null) {
				Integer officeCode = model.getOfficecode();
				Integer medicalRoles = model.getMedicalroles();
				List<HrPersonnelModel> accrodList = hrPersonnelService.querySuperiorDoctor(ysghList, 
						officeCode, medicalRoles);
				List<String> doctList = accrodList.stream().map(o -> 
				ObjectToTypes.parseString(o.getPersonid())).collect(Collectors.toList());
				newList = list.stream().filter(o -> 
				doctList.contains(o.getYsgh())).collect(Collectors.toList());
				if(list.size() != newList.size() && !list.isEmpty() && !newList.isEmpty()) {
					ReturnEntity<String> returnEntity = new ReturnEntity<>();
					returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00055", null);
					warnMsg = returnEntity.getMessage();
				}
			}
		}		
		sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(sysUser.getHospitalId(), SysXtcsCsmcCts.SFTZKFXM);
        String sftzkfxm = sysXtcs == null ? "1" : sysXtcs.getCsz();
        if("1".equals(sftzkfxm)) {
        	List<ZlHzyz> zlList = com.buit.utill.BeanUtil.toBeans(newList, ZlHzyz.class);
        	zlList = zlList.stream().filter(o -> o.getTzsj() != null && "6".equals(
    					ObjectToTypes.parseString(o.getYzlx()))).collect(Collectors.toList());
			if(!zlList.isEmpty()) {
				ResultsUtil res = zlRwfpService.stopByYzJlxh(zlList, sysUser.getHospitalId());
				if(!res.isOk()) {
					Map<Integer, String> errorMap = res.getErrorData();
					Set<Integer> errorList = errorMap.keySet();
					newList = newList.stream().filter(o -> !errorList.contains(o.getJlxh())).collect(Collectors.toList());
					if(StrUtil.isNotBlank(warnMsg)) {
						warnMsg = warnMsg + "</br>" + res.getString();
					}else {
						warnMsg = res.getString();
					}
				}	
			}
        }	
		if(!newList.isEmpty()) {
			cisHzyzDao.updateStopOrdersByJlxh(ObjectToTypes.parseString(userId), newList.get(0).getTzsj(), 
					newList.stream().map(CisHzyzStopCheckReq::getJlxh).collect(Collectors.toList()));
		}
		
		return warnMsg;
	}

	/**
	 * @Description 取消停医嘱同时校验医生权限
	 * @Title  cancelStopOrdersAndCheckAuthority
	 * @author  gfz
	 * @Date  2021/6/4 22:13
	 * @param list
	 * @param user
	 * @Return  java.lang.String
	 * @Exception
	*/
	public String cancelStopOrdersAndCheckAuthority(List<CisHzyzStopCheckReq> list, SysUser user) {
		SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.SFJYYSJB);	
		String jyjb = sysXtcs.getCsz();
		Integer userId = user.getUserId();
		if("1".equals(jyjb)) {
			List<String> ysghList = list.stream().map(CisHzyzStopCheckReq::getYsgh).collect(Collectors.toList());
			HrPersonnelModel model = hrPersonnelService.getPersonnelById(userId);
			if(model != null) {
				Integer officeCode = model.getOfficecode();
				Integer medicalRoles = model.getMedicalroles();
				List<HrPersonnelModel> accrodList = hrPersonnelService.querySuperiorDoctor(ysghList,
						officeCode, medicalRoles);
				List<String> doctList = accrodList.stream().map(o ->
						ObjectToTypes.parseString(o.getPersonid())).collect(Collectors.toList());
				List<CisHzyzStopCheckReq> newList = list.stream().filter(o ->
						(doctList.contains(o.getYsgh()) || ObjectToTypes.parseString(userId).equals(o.getYsgh()))).collect(Collectors.toList());
				if(!newList.isEmpty()) {
					cisHzyzDao.cancelStopOrdersByJlxh(newList.stream().map(CisHzyzStopCheckReq::getJlxh).collect(Collectors.toList()));
				}
				if(list.size() != newList.size()) {
					ReturnEntity<String> returnEntity = new ReturnEntity<>();
					returnEntity.setError("ERROR_DCTWORK_ZYBQYZ_00056", null);
					return returnEntity.getMessage();
				}
			}
		}else {
			cisHzyzDao.cancelStopOrdersByJlxh(list.stream().map(CisHzyzStopCheckReq::getJlxh).collect(Collectors.toList()));
		}	
		return null;
	}

	/**
	 * @Description 批量删除医嘱
	 * @Title  deleteOrdersByJlxhList
	 * @author  gfz
	 * @Date  2021/6/6 23:16
	 * @param list
	 * @Return  void
	 * @Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteOrdersByJlxhList(List<CisHzyzDeteleReq> list){
		List<CisHzyzDeteleReq> ztList = list.stream().filter(o -> (o.getZtbz() != null && o.getZtbz() == 1))
				.collect(Collectors.toList());
		//组套集合
		if(!ztList.isEmpty()) {
			cisHzyzZtDao.deleteZtBatchByJlxhList(ztList.stream().map(CisHzyzDeteleReq::getJlxh).collect(Collectors.toList()));
			cisHzyzDao.deleteBatchByJlxhList(ztList.stream().map(CisHzyzDeteleReq::getJlxh).collect(Collectors.toList()));
			//检查
			List<Integer> jcList = ztList.stream().filter(e -> "0".equals(ObjectToTypes.parseString(e.getYzlx())) && e.getSqid() != null)
					.map(o -> o.getSqid()).collect(Collectors.toList());
			if(!jcList.isEmpty()) {
				opSbhyDao.updateNumSourceByJlxhList(jcList);
				cisJcsq01Dao.deleteInspectByJlxhList(jcList);
			}
		}
		//非组套集合
		List<CisHzyzDeteleReq> ptList = list.stream().filter(o -> (o.getZtbz() == null || o.getZtbz() == 0))
				.collect(Collectors.toList());
		if(!ptList.isEmpty()) {
			cisHzyzDao.deleteBatchById(ptList.stream().map(CisHzyzDeteleReq::getJlxh).collect(Collectors.toList()));
			//备血
			List<Integer> bxList = ptList.stream().filter(e -> "2".equals(ObjectToTypes.parseString(e.getYzlx())) && e.getSqid() != null)
					.map(o -> o.getSqid()).collect(Collectors.toList());
			if(!bxList.isEmpty()) {
				cisBxsqdDao.deletePrepareBloodBySqidList(bxList);
				cisBxxypzDao.deleteBatchById(bxList);
			}
			//手术
			List<Integer> ssList = ptList.stream().filter(e -> "3".equals(ObjectToTypes.parseString(e.getYzlx())) && e.getSqid() != null)
					.map(o -> o.getSqid()).collect(Collectors.toList());
			if(!ssList.isEmpty()) {
				optSssqDao.deleteBatchBySqidList(ssList);
			}
			//会诊
			List<Integer> hzList = ptList.stream().filter(e -> "4".equals(ObjectToTypes.parseString(e.getYzlx())) && e.getSqid() != null)
					.map(o -> o.getSqid()).collect(Collectors.toList());
			if(!hzList.isEmpty()) {
				cisZyHzsqDao.deleteConsultationBySqidList(hzList);
				cisZyHzyqdxDao.deleteConsultationYqBySqidList(hzList);
			}
			//抗菌药物
			List<Integer> ywList = ptList.stream().filter(e -> "0".equals(ObjectToTypes.parseString(e.getKjywsp())) && e.getSqid() != null)
					.map(o -> o.getSqid()).collect(Collectors.toList());
			if(!ywList.isEmpty()) {
				amqcKjywsysqDao.deleteBatchKjywsysqBySqidList(ywList);
				amqcKjywsysqypDao.deleteBatchYpBySqidList(ywList);	
				amqcKjywsyhzksysDao.deleteBatchYjBySqidList(ywList);
			}
		}
	}

}
