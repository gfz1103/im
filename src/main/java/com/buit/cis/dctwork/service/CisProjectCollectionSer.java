package com.buit.cis.dctwork.service;

import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.utill.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CisProjectCollectionSer {

	@Autowired
	private CisHzyzDao cisHzyzDao; 
	
	@Autowired
	private RedisFactory redisFactory;
	
	@Autowired
	private ImHzryByDctworkSer imHzryByDctworkSer;

	
//	/**
//     * 保存手术申请记录
//     * @param req
//     * @param user
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void saveSssq(CisSssqSaveReq req, SysUser user) {
//
//	    Map<String, Object> body = BeanUtil.beanToMap(req.getBody());
//	    Map<String, Object> formInfo = BeanUtil.beanToMap(req.getFormInfo());
//	    Map<String, Object> formData = BeanUtil.beanToMap(formInfo.get("formData"));
//
//	    if("2".equals(formInfo.get("sqlx").toString())) {
//	    	if(StrUtil.isNotBlank(ObjectToTypes.parseString(formData.get("sqdh")))) {
//	    		optSssqService.deleteById(ObjectToTypes.parseInt(formData.get("sqdh")));
//	    		cisHzyzDao.deleteOrdersBySqid(ObjectToTypes.parseInt(formData.get("sqdh")), 3);
//	    	}
//	    }
//
//	    // 机构ID
//	    formData.put("jgid", user.getHospitalId());
//	    // 操作工号
//	    formData.put("czgh", user.getUserId());
//
//	    formData.put("sqdh", redisFactory.getTableKey(TableName.DB_NAME, TableName.OPT_SSSQ));
//	    formData.put("jzlsh",req.getJzlsh());
//	    if(!StrUtil.isEmptyIfStr(req.getZyh())) {
//	    	formData.put("zyh", req.getZyh());
//	    }
//
//	    optSssqService.insert(formData);
////    res.put("sqdh", formData.get("sqdh"));
//
//        // 保存医嘱信息
//        Map<String, Object> brxx = new HashMap<>(16);// 病人信息
//        List<Map<String, Object>> yzxxArr = new ArrayList<Map<String, Object>>();
//        Map<String, Object> yzxx = new HashMap<>(16);// 医嘱信息
//        // 赋值病人信息
//        brxx.put("brks", req.getBrks());
//        brxx.put("brbq", req.getBrbq());
//        brxx.put("brch", req.getBrch());
//        brxx.put("zyh", req.getZyh());
//        brxx.put("brxm", req.getBrxm());
//
//        yzxx = new HashMap<>(16);// 医嘱信息
//        // 赋值一条行为医嘱信息
//        yzxx.put("opStatus", "create");
//        yzxx.put("sqid", formData.get("sqdh"));// 申请申请ID(SQID)
//        yzxx.put("zfyp", 0);// 自备药标识
//        yzxx.put("ystj", 0);// 医生提交标志
//        yzxx.put("ycsl", 1);// 一次数量
//        yzxx.put("mrcs", 1);// 每日次数
//        yzxx.put("czgh", user.getUserId());// 操作工号
//        yzxx.put("ysbz", 1);// 医生医嘱标志
//        yzxx.put("srcs", 0);// 首日次数
//        yzxx.put("yfbz", 0);
//        yzxx.put("zfpb", 0);// 自负判别
//        yzxx.put("yplx", 0);// 药品类型
//        yzxx.put("sybz", 0);// 使用标志
//        yzxx.put("mzcs", 0);// 每周次数
//        yzxx.put("yjzx", 0);// 医技主项
//        yzxx.put("xmlx", 0);// 项目类型
//        yzxx.put("jfbz", 3);// 计费标志
//        yzxx.put("zfbz", 0);// 作废标志
//        yzxx.put("sfjg", 0);// 审方结果
//        yzxx.put("fhbz", 0);// 复核标志
//        yzxx.put("tzfhbz", 0);// 停嘱复核标志
//        yzxx.put("pspb", 0);// 皮试判别
//        yzxx.put("yzpb", 0);// 医嘱判别
//        yzxx.put("lsbz", 0);// 历史标志
//        yzxx.put("yepb", 0);// 婴儿判别
//        yzxx.put("fysx", 0);// 发药属性
//        yzxx.put("tpn", 0);// tpn
//        yzxx.put("lsyz", 1);// 临时医嘱
//        yzxx.put("cfts", 0);// 贴数
//        yzxx.put("ypzs", 1);// 煎法
//        yzxx.put("jz", 1);// 脚注
//        yzxx.put("ztbz", 0);// 非组套医嘱
//        yzxx.put("ypdj", 0);// 药品单价
//        yzxx.put("yzzhShow", 2);// 医嘱组号
//        yzxx.put("ysgh", user.getUserId());// 开嘱医生
//        yzxx.put("kssj", DateUtil.parse(formData.get("sqrq")+"").toTimestamp());// 开始时间(暂时用申请单申请日期)
//        yzxx.put("yzzxsj", "23:59");//
//        yzxx.put("jgid", user.getHospitalId());// 机构ID
//        yzxx.put("zyh", req.getZyh());//
//        yzxx.put("yzmc", formData.get("ssmc"));// 医嘱名称
//        yzxx.put("bzxx", "(不计费医嘱)");// 备注信息
//        yzxx.put("yzlx", 3);// 医嘱类型
//        yzxxArr.add(yzxx);
//
//        Map<String, Object> yzxxBody = new HashMap<>(16);
//        yzxxBody.put("brxx", brxx);
//        yzxxBody.put("yzxx", yzxxArr);
//        yzxxBody.put("fjxx", new ArrayList<Map<String, Object>>());
//
//        Map<String, Object> yzxxBody2 = new HashMap<>(16);
//        yzxxBody2.put("body", yzxxBody);
//
//        CisHzyzSaveReq cisHzyzSaveReq = BeanUtil.fillBeanWithMapIgnoreCase(brxx, new CisHzyzSaveReq(), true);
//        cisHzyzSaveReq.setCisHzyzReqFjList(new ArrayList<>());
//        List<CisHzyzReq> cisHzyzReqList = BUHISUtil.ListToResultSet(yzxxArr, new CisHzyzReq());
//        cisHzyzSaveReq.setCisHzyzReqList(cisHzyzReqList);
//
//        imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, user);
//
//    }
}
