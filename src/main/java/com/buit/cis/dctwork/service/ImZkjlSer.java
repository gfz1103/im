package com.buit.cis.dctwork.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.util.StringUtils;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.dao.NisTfmxDao;
import com.buit.cis.dctwork.dao.NisTymxDao;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.model.NisTfmx;
import com.buit.cis.dctwork.response.NisTymxResp;
import com.buit.cis.ims.dao.*;
import com.buit.cis.ims.model.*;
import com.buit.cis.ims.request.ImZkjlBedReq;
import com.buit.cis.ims.request.ImZkjlReq;
import com.buit.cis.ims.request.ImZkjlVerReq;
import com.buit.cis.ims.response.ImZkZqHcjlResp;
import com.buit.cis.ims.response.ImZkjlListResp;
import com.buit.cis.ims.response.ImZkjlResp;
import com.buit.cis.ims.service.ImCwszSer;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.ReturnEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 住院_转科记录<br>
 * @author GONGFANGZHOU
 */
@Service
public class ImZkjlSer extends BaseManagerImp<ImZkjl,Long> {
    
    static final Logger logger = LoggerFactory.getLogger(ImZkjlSer.class);
    
    @Autowired
    private ImZkjlDao imZkjlDao;
    
    @Override
    public ImZkjlDao getEntityMapper(){        
        return imZkjlDao;
    }
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private ImCwszDao imCwszDao;
    
    @Autowired
    private ImRcjlDao imRcjlDao;
    
    @Autowired
    private CisHzyzDao cisHzyzDao;

    @Autowired
    private BUHISUtil buhisutil;
    
    @Autowired
    private NisTfmxDao nisTfmxDao;
    
    @Autowired
    private NisTymxDao nisTymxDao;
    
    @Autowired
	private RedisFactory redisFactory;
    
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    
    @Autowired
    private ImCwtjDao imCwtjDao;
    
    @Autowired
    private ImCwszSer imCwszSer;
    
    @Autowired
    private ImHcmxDao imHcmxDao;
    
    @Autowired
    private CisHzyzSer cisHzyzSer;
    
    /**
     * 初始化查询
     * @Title: doQueryInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imZkjlVerReq
     * @param @param user
     * @param @return    设定文件
     * @return Long    返回类型
     * @author 龚方舟
     * @throws
     */
	public Long doQueryInfo(ImZkjlVerReq imZkjlVerReq, SysUser user) {
		Integer type = imZkjlVerReq.getType();
		Integer jgid = user.getHospitalId();
		Long count = 0L;
		switch (type) {
		case 1:
			//查询是否有转科记录
			count = queryisExistZkjl(imZkjlVerReq, jgid);
			break;
		case 2:
			//查询是否有未退包床
			count = queryIsExistBc(imZkjlVerReq, jgid);
			break;
		case 3:
			//查询是否有出院证
			count = queryIsExistCyz(imZkjlVerReq, jgid);
			break;

		case 4:
			//有会诊申请转科给予提示
			count = queryIsExistHz(imZkjlVerReq, jgid);
			break;
		case 5:
			//查看是否有皮试药品未做
			count = queryIsExistPs(imZkjlVerReq, jgid);
			break;
		case 6:
			//查看IM_HZRY中转科状态是否为1
			count = queryisExistBrinfo(imZkjlVerReq, jgid);
			break;
		default:
			break;
		}
		return count;
	}
	
	/**
	* @Title: queryisExistZkjl
	* @Description: TODO 查询是否有转科记录
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryisExistZkjl(ImZkjlVerReq imZkjlVerReq, Integer jgid) {
		long count = 0L, countZk = 0L;
		try {
			countZk = imZkjlDao.queryisExistZkjlCount(imZkjlVerReq.getZyh(), jgid);
			if (countZk > 0) {
				count = imHzryDao.queryIsZkjlCount(imZkjlVerReq.getZyh(), jgid);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0006");
		}
		return count;
	}
	
	/** 
	* @Title: queryIsExistBc
	* @Description: TODO 查询是否有未退包床
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryIsExistBc(ImZkjlVerReq imZkjlVerReq, Integer jgid){
		Long count = 0L;
		try {
			ImCwsz imCwsz = new ImCwsz();
			imCwsz.setZyh(imZkjlVerReq.getZyh());
			imCwsz.setJgid(jgid);
			count = imCwszDao.findByEntityCount(imCwsz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0007");
		}
		return count;
	}
	
	/**
	* @Title: queryIsExistCyz
	* @Description: TODO 查询是否有出院证
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryIsExistCyz(ImZkjlVerReq imZkjlVerReq, Integer jgid) {
		Long count = 0L;
		try {

			Map<String, Object> map = new HashMap<String, Object>(16);
			map.put("JGID",jgid);// 机构ID
			map.put("ZYH", imZkjlVerReq.getZyh());
			map.put("BRKS", imZkjlVerReq.getBrks());
			map.put("CZLX", -1);
			map.put("BQPB", 1);
			count = imRcjlDao.queryZyrcjl(map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0008");
		}
		return count;
	}
	
	
	/**
	* @Title: queryIsExistHz
	* @Description: TODO 有会诊申请转科给予提示
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryIsExistHz(ImZkjlVerReq imZkjlVerReq, Integer jgid){
		Long count = 0L;
		try {
			count = imHzryDao.queryIsExistHzCount(imZkjlVerReq.getZyh(), jgid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0009");
		}
		return count;
	}
	
	/**
	* @Title: queryIsExistPs
	* @Description: TODO 查看是否有皮试药品
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryIsExistPs(ImZkjlVerReq imZkjlVerReq, Integer jgid) {
		Long count = 0L;
		try {
			count = cisHzyzDao.queryIsExistPs(imZkjlVerReq.getZyh(),jgid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00010");
		}
		return count;
	}
	
	/** 
	* @Title: queryisExistBrinfo
	* @Description: TODO 查看病人入院表中转科状态
	* @param @param imZkjlVerReq
	* @param @param jgid
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryisExistBrinfo(ImZkjlVerReq imZkjlVerReq, Integer jgid) {
		long count = 0L;
		try {
			ImHzry imHzry = new ImHzry();
			imHzry.setZyh(imZkjlVerReq.getZyh());
			imHzry.setJgid(jgid);
			imHzry.setBrbq(imZkjlVerReq.getBrks());
			count = imHzryDao.findByEntityCount(imHzry);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00011");
		}
		return count;
	}
	
	/**
	* @Title: doQuerySFZK
	* @Description: TODO 查询是否转科以及转科内容
	* @param @param zyh
	* @param @param user
	* @param @return    设定文件
	* @return ImZkjlResp    返回类型
	* @author 龚方舟
	* @throws
	 */
	public ImZkjlResp doQuerySfzk(Integer zyh, SysUser user) {
		ImZkjlVerReq imZkjlVerReq = new ImZkjlVerReq();
		ImZkjlResp imZkjlResp = new ImZkjlResp();
		imZkjlVerReq.setZyh(zyh);
		long count = queryisExistZkjl(imZkjlVerReq, user.getHospitalId());
		if(count > 0){
			long countZk = imZkjlDao.queryisExistZkjlCount(zyh, user.getHospitalId());
			if(countZk == 1){
				imZkjlResp = imZkjlDao.queryisExistZkjl(zyh, user.getHospitalId());
			}
		}
		imZkjlResp.setCountZk(count);
		return imZkjlResp;
	}
	
	
	public String doQueryUnvalidRecord(Integer zyh, SysUser user) {
		StringBuffer expiredMsg = new StringBuffer();
		StringBuffer codeMsg = new StringBuffer();
		ReturnEntity<String> returnEntity = new ReturnEntity<>();
		ImZkjlListResp imZkjlListResp = doCheckUnvalidRecord(zyh, 7, user);
		if(CollectionUtils.isNotEmpty(imZkjlListResp.getCisHzyzlist())) {
			returnEntity.setError("ERROR_DCTWORK_ZYZKJL_00016", null);
			expiredMsg.append(returnEntity.getMessage());
			codeMsg.append("ypyz|");
		}
		
		imZkjlListResp = doCheckUnvalidRecord(zyh, 2, user);
		if(CollectionUtils.isNotEmpty(imZkjlListResp.getNisTymxRespList())) {
			returnEntity.setError("ERROR_DCTWORK_ZYZKJL_00017", null);
			expiredMsg.append(returnEntity.getMessage());
			codeMsg.append("tyyz|");
		}
		
		imZkjlListResp = doCheckUnvalidRecord(zyh, 3, user);
		if(CollectionUtils.isNotEmpty(imZkjlListResp.getNisTfmxList())) {
			returnEntity.setError("ERROR_DCTWORK_ZYZKJL_00018", null);
			expiredMsg.append(returnEntity.getMessage());
			codeMsg.append("tfd|");
		}
		
		imZkjlListResp = doCheckUnvalidRecord(zyh, 8, user);
		if(CollectionUtils.isNotEmpty(imZkjlListResp.getCisHzyzlist())) {
			returnEntity.setError("ERROR_DCTWORK_ZYZKJL_00019", null);
			expiredMsg.append(returnEntity.getMessage());
			codeMsg.append("xmyz|");
		}
		if(expiredMsg.length() > 0 ) {
			expiredMsg.append(",").append(codeMsg.substring(0,codeMsg.length()- 1));
		}
		return expiredMsg.toString();
	}
	
	
	/**
	* @Title: doQueryUnvalidRecord
	* @Description: TODO 取出不符合该病人的转科条件的记录
	* @param @param zyh
	* @param @param type    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public ImZkjlListResp doCheckUnvalidRecord(Integer zyh, Integer type, SysUser user){
		ImZkjlListResp imZkjlListResp = new ImZkjlListResp();
		List<CisHzyz> cisHzyzlist = null;
		switch (type) {
		case 1:
			//未停未提交医嘱表单检索数据
			List<Map<String, Object>> dicsypcList = cisHzyzSer.frequencyList();
			medicalAdviceToLs(dicsypcList, zyh);
			cisHzyzlist = queryIsExistyzbd(zyh, user.getHospitalId());
			imZkjlListResp.setCisHzyzlist(cisHzyzlist);
			break;
		case 2:
			//退药医嘱未提交或未确认表单记录数
			List<NisTymxResp> nisTymxRespList = queryIsExisttyzy(zyh, user.getHospitalId());
			imZkjlListResp.setNisTymxRespList(nisTymxRespList);
			break;
		case 3:
			//退费单未确认表单记录数
			List<NisTfmx> nisTfmxList = queryIsExistf(zyh, user.getHospitalId());
			imZkjlListResp.setNisTfmxList(nisTfmxList);
			break;
		case 7:
			//药品医嘱未提交未发药
			List<Map<String, Object>> gypcList = cisHzyzSer.frequencyList();
			medicalAdviceToLs(gypcList, zyh);
			cisHzyzlist = queryIsExistyp(zyh, user.getHospitalId());
			imZkjlListResp.setCisHzyzlist(cisHzyzlist);
			break;
		case 8:
			//项目医嘱未提交或未执行
			List<Map<String, Object>> pcList = cisHzyzSer.frequencyList();
			medicalAdviceToLs(pcList, zyh);
			cisHzyzlist = queryIsExistxm(zyh, user.getHospitalId());
			imZkjlListResp.setCisHzyzlist(cisHzyzlist);
			break;
		default:
			break;
		}
		return imZkjlListResp;
	}
	
	/**
	* @Title: queryIsExistyzbd
	* @Description: TODO 未停未提交医嘱表单检索数据
	* @param @param zyh
	* @param @return    设定文件
	* @return List<CisHzyz>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<CisHzyz> queryIsExistyzbd(Integer zyh, Integer jgid) {
		List<CisHzyz> cisHzyzList = cisHzyzDao.queryNotStopOrNotSubmitYz(zyh, jgid);
		return cisHzyzList;
	}
	
	/**
	* @Title: queryIsExisttyzy
	* @Description: TODO 退药医嘱未提交或未确认表单记录数
	* @param @param zyh
	* @param @return    设定文件
	* @return List<NisTymxResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<NisTymxResp> queryIsExisttyzy(Integer zyh, Integer jgid)  {
		List<NisTymxResp> nisTymxRespList = nisTymxDao.queryTyNotSubmitOrNotSure(zyh, jgid);
		return nisTymxRespList;
	}
	
	
	/**
	* @Title: queryIsExistf
	* @Description: TODO 退费单未确认表单记录数
	* @param @param zyh
	* @param @return    设定文件
	* @return List<NisTfmx>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<NisTfmx> queryIsExistf(Integer zyh, Integer jgid) {
		List<NisTfmx> nisTfmxList = nisTfmxDao.queryRefundNotSure(zyh, jgid);
		return nisTfmxList;
	}
	
	/**
	* @Title: queryIsExistyp
	* @Description: TODO 药品医嘱未提交未发药
	* @param @param zyh
	* @param @return    设定文件
	* @return List<CisHzyz>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<CisHzyz> queryIsExistyp(Integer zyh, Integer jgid) {
		List<CisHzyz> cisHzyzList = cisHzyzDao.queryNotSubmitOrNotSendYz(zyh, jgid);
		return cisHzyzList;
	}
	
	/**
	* @Title: queryIsExistxm
	* @Description: TODO 项目医嘱未提交或未执行
	* @param @param zyh
	* @param @return    设定文件
	* @return List<CisHzyz>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<CisHzyz> queryIsExistxm(Integer zyh, Integer jgid) {
		List<CisHzyz> cisHzyzList = cisHzyzDao.queryNotSubmitOrNotExcuteXm(zyh, jgid);
		return cisHzyzList;
	}
	
	/**
	 * 病人管理保存转科记录
	 * @Title: doSaveorupdate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imZkjlReq
	 * @param @param user    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doSaveorupdate(ImZkjlReq imZkjlReq, SysUser user) {
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(imZkjlReq);
        //更新
        try {
            updateBrry(body, user);
            //保存
            saveZkjl(body, user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0005");
        }
		
	}
	
	/**
	* @Title: updateBrry
	* @Description: TODO 更新病人入院表转科状态和主任医生
	* @param @param body
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void updateBrry(Map<String, Object> body, SysUser user) {
		//更改转科状态值和同步病人主治医师		
		ImHzry imHzry = new ImHzry();
		imHzry.setZyh(ObjectToTypes.parseInt(body.get("zyh")));
		imHzry.setZyys(ObjectToTypes.parseString(body.get("hhys")));
		imHzryDao.updateZkStatus(imHzry);
	}
	
	/**
	* @Title: saveZkjl
	* @Description: TODO 保存转科记录
	* @param @param body
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void saveZkjl(Map<String, Object> body, SysUser user) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("HCLX", "1");// 换床类型
		map.put("ZXBZ", "2");// 2为病区确认
		map.put("YSSQRQ", DateUtil.beginOfDay(new Date()).toTimestamp());// 医生申请时间
		map.put("HQCH", body.get("brch").toString());// 换前床号
		map.put("HQKS", body.get("brks").toString());// 换前科室
		map.put("HQBQ", body.get("brbq").toString());// 换前病区
		map.put("HQYS", body.get("zyys") != null ? body.get("zyys").toString() : null);// 换前医生
		map.put("ZYH", body.get("zyh").toString());// 住院号
		map.put("ZYHM", body.get("zyhm").toString());// 住院号码
		map.put("HHYS", body.get("hhys").toString());// 换后医生
		map.put("HHKS", body.get("hhks").toString());// 换后科室
		map.put("HHBQ", body.get("hhbq").toString());// 换后科室
		map.put("BQSQGH", user.getUserId());// 病区申请工号
		map.put("BQSQRQ", DateUtil.beginOfDay(new Date()).toTimestamp());// 病区申请时间
		map.put("JGID", user.getHospitalId());// 机构
		try {
			//保存转科记录
			ImZkjl imZkjl = BeanUtil.fillBeanWithMapIgnoreCase(map, new ImZkjl(), true);
			Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_ZKJL);
			imZkjl.setJlxh(jlxh);
			imZkjlDao.insert(imZkjl);
			body.put("JLXH", jlxh);
			saveZkYz(body, user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0005");
		}
	}
	
	
	/**
	* @Title: saveZkYz
	* @Description: TODO 生成转科医嘱
	* @param @param body
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void saveZkYz(Map<String, Object> body, SysUser user) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		Integer jgid = user.getHospitalId();
		String officeName = dicKszdOutSer.getVauleByKey(jgid, 
				ObjectToTypes.parseInt(body.get("hhks")));
		Integer zyh = ObjectToTypes.parseInt(body.get("zyh"));
		Map<String, Object> zyyspar = new HashMap<String, Object>(16);
		zyyspar.put("ZYH", Long.parseLong(body.get("zyh").toString()));
		try {
			map.put("ZYH", zyh);// 住院号
			map.put("BRKS", ObjectToTypes.parseInt(body.get("brks")));// 病人科室
			map.put("BRBQ", ObjectToTypes.parseInt(body.get("brbq")));// 病人病区
			map.put("BRCH", body.get("brch"));// 病人床号
			map.put("YPXH", Long.parseLong("0"));//
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
			map.put("YZMC","转往"	 + officeName + "科室");// 药嘱名称
			map.put("CZGH", user.getUserId());// 转抄
			ImHzry imHzry = imHzryDao.getById(zyh);
			if (imHzry != null) {
				if (StrUtil.isNotBlank(imHzry.getZyys())) {
					map.put("YSGH", imHzry.getZyys());// 开嘱医生
				}
			}
//			List<Map<String, Object>> yzzhlist = cisHzyzDao.queryMaxYzzh(zyh);
//			if (yzzhlist.size() > 0) {
//				if (yzzhlist.get(0) != null && yzzhlist.get(0).get("YZZH") != null) {
//					map.put("YZZH",Long.parseLong(yzzhlist.get(0).get("YZZH") + "") + 1);
//				} else {
//					map.put("YZZH", 1L);
//				}
//			}
			map.put("YZZH", redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));
			map.put("YWID", Long.parseLong(body.get("JLXH").toString()));// 申请序号
			map.put("KSSJ", DateUtil.date().toTimestamp());// 开嘱时间
			map.put("YDLB", "302");// 约定类别
			map.put("SRKS", Long.parseLong(body.get("brks").toString()));// 输入科室
			map.put("ZXKS", Long.parseLong(body.get("brks").toString()));// 执行科室
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
//			SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(jgid, SysXtcsCsmcCts.ZYYSQY);
//			int zyysqy = sysXtcs == null ? 0 : ObjectToTypes.parseInt(sysXtcs.getCsz());	
//			if (zyysqy == 1) {				
//			} else {
//				map.put("YSBZ", 0);
//			}
			map.put("YSBZ", 1);
			map.put("YSTJ", 1);
			map.put("PSPB", 0);
			map.put("SYPC", "ST");
			CisHzyz cisHzyz = BeanUtil.fillBeanWithMapIgnoreCase(map, new CisHzyz(), true);
			cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
			cisHzyzDao.insert(cisHzyz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0005");
		}
	}
	
	/**
	* @Title: updateundoZk
	* @Description: TODO 取消转科
	* @param @param imZkjlReq
	* @param @param user
	* @param @return    设定文件
	* @return Integer    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer updateundoZk(ImZkjlReq imZkjlReq, SysUser user) {
		Long count = 0L;
		Integer status = 2;
		Integer jgid = user.getHospitalId();
		Integer zyh = imZkjlReq.getZyh();
		ImHzry imHzry = new ImHzry();
		imHzry.setZyh(zyh);
		imHzry.setJgid(jgid);
		imHzry.setZkzt(0);
		count = imHzryDao.findByEntityCount(imHzry);
		if (count > 1) {
			status = 1;
		} else {
			ImZkjlResp imZkjlResp = imZkjlDao.queryisExistZkjl(zyh, jgid);
			if (imZkjlResp != null) {
				// 删除转科记录
				CisHzyz cisHzyz = new CisHzyz();
				cisHzyz.setYwid(imZkjlResp.getJlxh());
				cisHzyz.setJgid(jgid);
				cisHzyz.setYdlb("302");
				imZkjlDao.deleteById(ObjectToTypes.parseLong(imZkjlResp.getJlxh()));
				cisHzyzDao.removeByEntity(cisHzyz);
				Integer hqys = imZkjlResp.getHqys(); // 换前医生
				ImHzry imHzryNew = new ImHzry();
				imHzryNew.setZyh(zyh);
				imHzryNew.setJgid(jgid);
				imHzryNew.setZyys(ObjectToTypes.parseString(hqys));
				imHzryDao.updateCancelZkStatus(imHzryNew);
			}
		}
		return status;
	}
	
	/**
	* @Title: doQueryInList
	* @Description: TODO 查询转科转入记录 
	* @param @param hhbq
	* @param @param user
	* @param @return    设定文件
	* @return List<ImZkjlResp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<ImZkjlResp> doQueryInList(Integer type, Integer hhbq, SysUser user) {
		Integer jgid = user.getHospitalId();
		return imZkjlDao.queryZkjlChangeInto(type, hhbq, jgid);	
	}
	
	/**
	* @Title: queryIsExistPatient
	* @Description: TODO 判断新床位是否有病人
	* @param @param fpcw
	* @param @param user
	* @param @return    设定文件
	* @return Long    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Long queryIsExistPatient(String fpcw, SysUser user, Integer ksdm) {
		Integer jgid = user.getHospitalId();
		return imCwszDao.queryIsExistsCw(fpcw, jgid, ksdm);
	}
	
	/**
	* @Title: checkBedSexLimit
	* @Description: TODO 判断病人是否满足新床位性别限制
	* @param @param zyh
	* @param @param brch
	* @param @param wardId
	* @param @param ksdm
	* @param @param user
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Boolean checkBedSexLimit(Integer zyh, String brch, Integer wardId, Integer ksdm, SysUser user) {
		Boolean illegal = true;
		Integer jgid = user.getHospitalId();
		ImCwsz imCwsz = new ImCwsz();
		imCwsz.setZyh(zyh);
		imCwsz.setBrch(brch);
		imCwsz.setKsdm(wardId);
		imCwsz.setCwks(ksdm);
		imCwsz.setJgid(jgid);
		Map<String, Object> map = imCwszDao.queryIsGenderFit(imCwsz);
		if(map!=null && map.size() > 0 && map.get("BRCH") != null) {
			illegal = false;
		}
		return illegal;
	}
	
	/**
	 * 提醒查询转入确定
	 * @Title: savecwglZccl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imZkjlBedReq
	 * @param @param user
	 * @param @param res    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void savecwglZccl(ImZkjlBedReq imZkjlBedReq, SysUser user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer manaUnitId = user.getHospitalId();
		Integer llbqdmOld = 0;
		Integer llbqdmNew = 0;
		Integer lljcpb = 0;
		Integer lljcpb2 = 0;
		Integer llcwxbNew = 0;
		Integer llbrxbOld = 0;
		Integer llbqksOld = 0;
		Integer llbqksNew = 0;
		Integer llzyhOld = 0;

		long count = queryIsExistPatient(imZkjlBedReq.getCwhmNew(), user, imZkjlBedReq.getHhbq());
		if(count > 0 ) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00020");
		}
		
		boolean flag = checkBedSexLimit(imZkjlBedReq.getZyh(), imZkjlBedReq.getCwhmNew(), 
				imZkjlBedReq.getHhbq(), imZkjlBedReq.getHhks(), user);
		if(flag) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00021");
		}
		// 取床位病区代码
		ImCwsz imCwsz = new ImCwsz();
		imCwsz.setBrch(imZkjlBedReq.getCwhmOld());
		imCwsz.setJgid(manaUnitId);

		List<ImCwsz> imCwszListOld = imCwszDao.findByEntity(imCwsz);
		if (CollectionUtils.isNotEmpty(imCwszListOld)) {
			ImCwsz imCwszOld = imCwszListOld.get(0);
			llbqdmOld = imCwszOld.getKsdm();
			llbqksOld = imCwszOld.getCwks();
			lljcpb = imCwszOld.getJcpb();
			llbrxbOld = imCwszOld.getCwxb();
		}
		
		imCwsz.setBrch(imZkjlBedReq.getCwhmNew());
		List<ImCwsz> imCwszListNew = imCwszDao.findByEntity(imCwsz);
		if (CollectionUtils.isNotEmpty(imCwszListNew)) {
			ImCwsz imCwszNew = imCwszListNew.get(0);
			llbqdmNew = imCwszNew.getKsdm();
			llbqksNew = imCwszNew.getCwks();
			lljcpb2 = imCwszNew.getJcpb();
			llcwxbNew = imCwszNew.getCwxb();
		}

		if (!llbqksNew.equals(imZkjlBedReq.getHhks())) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00012");
		}
		if (llcwxbNew != 3 && llbrxbOld != 3) {
			if (llcwxbNew.equals(llbrxbOld)) {
				throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00013");
			}
		}
		if (lljcpb < 2) {				
			imCwsz = new ImCwsz();
			imCwsz.setCwks(llbqksOld);
			int llcwsysks = imCwszSer.cwtj(imCwsz);
			imCwsz.setCwks(null);
            imCwsz.setKsdm(llbqdmOld);
            int llcwsysbq = imCwszSer.cwtj(imCwsz);

            ImCwtj imCwtjKsSave = new ImCwtj();
            imCwtjKsSave.setCzrq(DateUtil.date().toTimestamp());
            imCwtjKsSave.setCzlx(2);
            imCwtjKsSave.setZyh(imZkjlBedReq.getZyh());
            imCwtjKsSave.setBrks(llbqksOld);
            imCwtjKsSave.setYsys(llcwsysks);
            imCwtjKsSave.setXsys(llcwsysks - 1);
            imCwtjKsSave.setBqpb(0);
            imCwtjKsSave.setJgid(manaUnitId);
            imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

            imCwtjDao.insert(imCwtjKsSave);

            ImCwtj zyCwtBqSave = new ImCwtj();
            zyCwtBqSave.setCzrq(DateUtil.date().toTimestamp());
            zyCwtBqSave.setCzlx(2);
            zyCwtBqSave.setZyh(imZkjlBedReq.getZyh());
            zyCwtBqSave.setBrks(llbqksOld);
            zyCwtBqSave.setYsys(llcwsysbq);
            zyCwtBqSave.setXsys(llcwsysbq - 1);
            zyCwtBqSave.setBqpb(1);
            zyCwtBqSave.setJgid(manaUnitId);
            zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
            imCwtjDao.insert(zyCwtBqSave);
		}
		if (lljcpb2 < 2) {
		    imCwsz = new ImCwsz();
		    imCwsz.setCwks(llbqksNew);
            int llcwsysks = imCwszSer.cwtj(imCwsz);
            imCwsz.setCwks(null);
            imCwsz.setKsdm(llbqdmNew);
            int llcwsysbq = imCwszSer.cwtj(imCwsz);     
			
			ImCwtj imCwtjKsSave = new ImCwtj();
            imCwtjKsSave.setCzrq(DateUtil.date().toTimestamp());
            imCwtjKsSave.setCzlx(2);
            imCwtjKsSave.setZyh(imZkjlBedReq.getZyh());
            imCwtjKsSave.setBrks(llbqksNew);
            imCwtjKsSave.setYsys(llcwsysks);
            imCwtjKsSave.setXsys(llcwsysks + 1);
            imCwtjKsSave.setBqpb(0);
            imCwtjKsSave.setJgid(manaUnitId);
            imCwtjKsSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));

            imCwtjDao.insert(imCwtjKsSave);
            
            ImCwtj zyCwtBqSave = new ImCwtj();
            zyCwtBqSave.setCzrq(DateUtil.date().toTimestamp());
            zyCwtBqSave.setCzlx(2);
            zyCwtBqSave.setZyh(imZkjlBedReq.getZyh());
            zyCwtBqSave.setBrks(llbqksNew);
            zyCwtBqSave.setYsys(llcwsysbq);
            zyCwtBqSave.setXsys(llcwsysbq + 1);
            zyCwtBqSave.setBqpb(1);
            zyCwtBqSave.setJgid(manaUnitId);
            zyCwtBqSave.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_CWTJ));
            imCwtjDao.insert(zyCwtBqSave);
			
		}

		//锁定原床位、新床位
		imCwszDao.updateLockedBedNo(imZkjlBedReq.getCwhmOld(), manaUnitId, imZkjlBedReq.getHqbq());
		imCwszDao.updateLockedBedNo(imZkjlBedReq.getCwhmNew(), manaUnitId, imZkjlBedReq.getHhbq());
		//判断原床位是否已发生变化
		imCwsz = new ImCwsz();
		imCwsz.setJgid(manaUnitId);
		imCwsz.setBrch(imZkjlBedReq.getCwhmOld());
		List<ImCwsz> imCwszList = imCwszDao.findByEntity(imCwsz); 
		if (CollectionUtils.isNotEmpty(imCwszList)) {
			ImCwsz zyhCwsz = imCwszList.get(0);
			if (zyhCwsz.getZyh() == null) {
				llzyhOld = 0;
			} else {
				llzyhOld = zyhCwsz.getZyh();
			}
		}
		if (llzyhOld != imZkjlBedReq.getZyh().intValue()) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_00014");
		}
		//3个update语句
		imCwsz = new ImCwsz();
		imCwsz.setZyh(imZkjlBedReq.getZyh());
		imCwsz.setBrch(imZkjlBedReq.getCwhmNew());
		imCwsz.setJgid(manaUnitId);
		imCwszDao.updateCwfp(imCwsz);

		imCwszDao.updateKcOld(imZkjlBedReq.getZyh(), imZkjlBedReq.getCwhmNew(), 
				imZkjlBedReq.getHhks(), imZkjlBedReq.getHhbq());

		imCwszDao.updateZckc(imZkjlBedReq.getCwhmOld(), imZkjlBedReq.getZyh(), manaUnitId);
		
		ImHcmx imHcmxSave = new ImHcmx();
		imHcmxSave.setZyh(imZkjlBedReq.getZyh());
		imHcmxSave.setHcrq(DateUtil.date().toTimestamp());
		imHcmxSave.setHclx(2);
		imHcmxSave.setHqch(imZkjlBedReq.getCwhmOld());
		imHcmxSave.setHhch(imZkjlBedReq.getCwhmNew());
		imHcmxSave.setHqks(llbqksOld);
		imHcmxSave.setHhks(llbqksNew);
		imHcmxSave.setHqbq(llbqdmOld);
		imHcmxSave.setHhbq(llbqdmNew);
		imHcmxSave.setJgid(manaUnitId);
		imHcmxDao.insert(imHcmxSave);

		Map<String, Object> ywcl = new HashMap<String, Object>(16);
		ywcl.put("CZLX", -2);
		ywcl.put("ZYH", imZkjlBedReq.getZyh());
		ywcl.put("RYRQ", sdf.format(new Date()));
		ywcl.put("BRKS", imZkjlBedReq.getHqks());
		ywcl.put("BQPB", 0);
		ywcl.put("CYFS", 0);
		ywcl.put("JGID", manaUnitId);
		checkAdmissionOrDischarge(ywcl, manaUnitId);

		// 新科室转入记录
		Map<String, Object> ywc2 = new HashMap<String, Object>(16);
		ywc2.put("CZLX", 2);
		ywc2.put("ZYH", imZkjlBedReq.getZyh());
		ywc2.put("RYRQ", sdf.format(new Date()));
		ywc2.put("BRKS", imZkjlBedReq.getHhks());
		ywc2.put("BQPB", 0);
		ywc2.put("CYFS", 0);
		ywc2.put("JGID", manaUnitId);

		checkAdmissionOrDischarge(ywc2, manaUnitId);
		if (!StringUtils.equals(ObjectToTypes.parseString(imZkjlBedReq.getHqbq()), 
				ObjectToTypes.parseString(imZkjlBedReq.getHhbq()))) {
			//// 原病区转出记录
			Map<String, Object> ywc3 = new HashMap<String, Object>(16);
			ywc3.put("CZLX", -2);
			ywc3.put("ZYH", imZkjlBedReq.getZyh());
			ywc3.put("RYRQ", sdf.format(new Date()));
			ywc3.put("BRKS", imZkjlBedReq.getHqbq());
			ywc3.put("BQPB", 1);
			ywc3.put("CYFS", 0);
			ywc3.put("JGID", manaUnitId);

			checkAdmissionOrDischarge(ywc3, manaUnitId);

			// 新病区转入记录
			Map<String, Object> ywc4 = new HashMap<String, Object>(16);
			ywc4.put("CZLX", 2);
			ywc4.put("ZYH", imZkjlBedReq.getZyh());
			ywc4.put("RYRQ", sdf.format(new Date()));
			ywc4.put("BRKS", imZkjlBedReq.getHhbq());
			ywc4.put("BQPB", 1);
			ywc4.put("CYFS", 0);
				ywc4.put("JGID", manaUnitId);
				checkAdmissionOrDischarge(ywc4, manaUnitId);
			}
			Integer hhys = null;
			ImZkjlResp imZkjlResp = imZkjlDao.queryisExistZkjl(imZkjlBedReq.getZyh(), manaUnitId);
			
			if (imZkjlResp != null && imZkjlResp.getHhys() != null) {
				hhys = imZkjlResp.getHhys();
			}
			ImHzry imHzry = new ImHzry();
			imHzry.setZyys(ObjectToTypes.parseString(hhys));
			imHzry.setZyh(imZkjlBedReq.getZyh());
			imHzry.setJgid(manaUnitId);
			imHzryDao.updateCancelZkStatus(imHzry);


			imZkjlDao.updateZkjlInfo(DateUtil.date().toTimestamp(), 
					user.getUserId(), imZkjlBedReq.getCwhmNew(), imZkjlBedReq.getZyh());
	
	}
	
	/**
	 * 查询符合条件空的床位号
	 * @Title: queryCwInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param wardId
	 * @param @param ksdm
	 * @param @param zyh
	 * @param @param user
	 * @param @return    设定文件
	 * @return List<ImCwsz>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<ImCwsz> queryCwInfo(Integer wardId, Integer ksdm, Integer zyh, SysUser user) {
		Integer jgid = user.getHospitalId();// 用户的机构ID
		ImCwsz imCwsz = new ImCwsz();
		imCwsz.setJgid(jgid);
		imCwsz.setKsdm(wardId);
		imCwsz.setCwks(ksdm);
		imCwsz.setZyh(zyh);
		return imCwszDao.queryQualifiedCwhm(imCwsz);
	}
	
	
	/**
	 * 将医嘱转为历史
	 * @Title: medicalAdviceToLs(原uf_lsyz)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param listMap
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public int medicalAdviceToLs(List<Map<String, Object>> listMap, Integer zyh) {
		try {
			// 历史医嘱转化时过滤YZPB<>4
			List<Map<String, Object>> bryzs = cisHzyzDao.queryHistoricalOrders(zyh);
			for (Map<String, Object> bryz : bryzs) {
                int lsbz = BUHISUtil.uf_cacl_lsbz(listMap, bryz);
                if (lsbz == 1) {
                    cisHzyzDao.updateToHistoricalOrders(ObjectToTypes.parseInt(bryz.get("JLXH")));
                }
            }
		} catch (Exception e) {
			throw BaseException.create("ERROR_DCTWORK_ZYZKJL_0002");
		}
		return 1;
	}
	

	/**
	 * 检查是否已经入院或出院
	 * @Title: checkAdmissionOrDischarge(原uf_ywcl)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @param manaUnitId    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void checkAdmissionOrDischarge(Map<String, Object> parameters, Integer manaUnitId) {
		Map<String, Object> parametersrcjl = new HashMap<String, Object>(16);
		parametersrcjl.put("JGID", manaUnitId);
		parametersrcjl.put("ZYH", Long.parseLong(parameters.get("ZYH") + ""));
		parametersrcjl.put("BRKS", Long.parseLong(parameters.get("BRKS") + ""));
		int czlx = Integer.parseInt(parameters.get("CZLX") + "");
		int bqpb = Integer.parseInt(parameters.get("BQPB") + "");
		parametersrcjl.put("BQPB", bqpb);
		int cyfs = Integer.parseInt(parameters.get("CYFS") + "");
		String jyxx = parameters.get("JYXX") + "";
		String sfxx = parameters.get("SFXX") + "";
		try {
			if (czlx == 1) {
				parametersrcjl.put("CZLX", czlx);
				Long l = imRcjlDao.queryZyrcjl(parametersrcjl);
				if (l > 0) {
					throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0007");
				}
			} else {
				parametersrcjl.put("CZLX", -1);
				Long l = imRcjlDao.queryZyrcjl(parametersrcjl);
				if (l > 0) {
					throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0008");
				}
			}
			Map<String, Object> parametersSaveRcjl = new HashMap<String, Object>(16);
			parametersSaveRcjl.put("JGID", parameters.get("JGID"));
			parametersSaveRcjl.put("CZRQ", DateUtil.date().toTimestamp());
			parametersSaveRcjl.put("LCRQ", cn.hutool.core.date.DateUtil
					.date(cn.hutool.core.date.DateUtil.parse(parameters.get("RYRQ") + "")).toTimestamp());

			parametersSaveRcjl.put("CZLX", czlx);
			parametersSaveRcjl.put("ZYH", Long.parseLong(parameters.get("ZYH") + ""));
			parametersSaveRcjl.put("BRKS", Long.parseLong(parametersrcjl.get("BRKS") + ""));
			parametersSaveRcjl.put("YJZYRS", 0);
			parametersSaveRcjl.put("BQPB", bqpb);
			parametersSaveRcjl.put("CYFS", cyfs);
			parametersSaveRcjl.put("BZXX", jyxx);
			parametersSaveRcjl.put("SF", sfxx);
			Integer jlxh = redisFactory.getTableKey(TableName.DB_NAME, TableName.IM_RCJL);
			ImRcjl imRcjl = BeanUtil.fillBeanWithMapIgnoreCase(parametersSaveRcjl, new ImRcjl(), true);
			imRcjl.setJlxh(jlxh);
			imRcjlDao.insert(imRcjl);
			parameters.put("JLXH", jlxh);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ZYBRRY_0009");
		}
	}
	
	/**
	 * 查询转科转病区换床信息
	 * @Title: queryZkZqHcInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param currentWeek
	 * @param @param user
	 * @param @return    设定文件
	 * @return ImZkZqHcjlResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public ImZkZqHcjlResp queryZkZqHcInfo(Integer zyh, Integer currentWeek, SysUser user) {
        ImZkZqHcjlResp resp = new ImZkZqHcjlResp();
        Map<String, Object> map = imHzryDao.queryBasicInfo(zyh);
        Date ryrq = (Date) map.get("RYRQ");
        int dateStart = currentWeek * 7;
        //当前星期第一天和最后一天
        java.sql.Date minDate = DateUtil.date(BUHISUtil.getDateAfter(ryrq, dateStart)).toSqlDate();
        java.sql.Date maxDate = DateUtil.date(BUHISUtil.getDateAfter(ryrq, dateStart + 6)).toSqlDate();
        List<String> ksList = new ArrayList<String>();
        List<String> bqList = new ArrayList<String>();
        List<String> cwList = new ArrayList<String>();
        Map<String, Object> parameters = new HashMap<String, Object>(16);
        parameters.put("zyh", zyh);
        parameters.put("minDate", minDate);
        parameters.put("maxDate", maxDate);
        parameters.put("jgid", user.getHospitalId());
        List<Map<String, Object>> listMap = imZkjlDao.queryZkBqjl(parameters);
        if (CollectionUtils.isNotEmpty(listMap)) {
			for(int i = 0; i < listMap.size(); i++) {
				Integer hqbq = ObjectToTypes.parseInt(listMap.get(i).get("hqbq"));
				Integer hhbq = ObjectToTypes.parseInt(listMap.get(i).get("hhbq"));
				if(i == 0) {
					ksList.add(ObjectToTypes.parseString(listMap.get(i).get("hqksmc")));
					if(hqbq.intValue() != hhbq.intValue()) {
						bqList.add(ObjectToTypes.parseString(listMap.get(i).get("hqbqmc")));
						bqList.add(ObjectToTypes.parseString(listMap.get(i).get("hhbqmc")));
					}
				}else {
					if(hqbq.intValue() != hhbq.intValue()) {
						bqList.add(ObjectToTypes.parseString(listMap.get(i).get("hhbqmc")));
					}
				}
				ksList.add(ObjectToTypes.parseString(listMap.get(i).get("hhksmc")));
				
			}
		}
		
		List<Map<String, Object>> hclistMap = imHcmxDao.queryHcInfo(parameters);
		if(CollectionUtils.isNotEmpty(hclistMap)) {
			for(int i = 0; i < hclistMap.size(); i++) {
				if(i == 0) {
					cwList.add(ObjectToTypes.parseString(hclistMap.get(i).get("hqch")));
				}
				cwList.add(ObjectToTypes.parseString(hclistMap.get(i).get("hhch")));
			}
		}
		resp.setKsList(ksList);
		resp.setBqList(bqList);
		resp.setCwList(cwList);
		return resp;
	}
}
