package com.buit.cis.dctwork.api;

import cn.hutool.core.bean.BeanUtil;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.enums.OperationTypeEnum;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.request.CisHzyzReq;
import com.buit.cis.dctwork.request.CisHzyzSaveReq;
import com.buit.cis.dctwork.request.CisHzyzSssqApiReq;
import com.buit.cis.dctwork.response.CisHzyzModel;
import com.buit.cis.dctwork.service.CisHzyzSer;
import com.buit.cis.dctwork.service.CisHzyzService;
import com.buit.cis.dctwork.service.ImHzryByDctworkSer;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.commons.SysUser;
import com.buit.utill.BUHISUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者医嘱接口实现
 * @ClassName: CisHzyzServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月12日 上午10:29:47
 *
 */
@DubboService(timeout = 10000)
public class CisHzyzServiceImpl implements CisHzyzService{
	
	@Autowired
	private CisHzyzDao cisHzyzDao;
	@Autowired
	private CisHzyzSer cisHzyzSer;
	
	@Autowired
	private ImHzryByDctworkSer imHzryByDctworkSer;
	
	@Autowired
	private ImHzryDao imHzryDao;
	
	/**
	 * 根据yzxh查询病区医嘱
	 * @Title: queryByYzxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzxhList
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<CisHzyzModel> queryByYzxh(List<Integer> yzxhList) {
		List<CisHzyz> cisHzyzList = cisHzyzDao.queryByYzxh(yzxhList);
		List<CisHzyzModel> modelList = new ArrayList<CisHzyzModel>();
		for (CisHzyz cisHzyz : cisHzyzList) {
			CisHzyzModel cisHzyzModel = BeanUtil.toBean(cisHzyz, CisHzyzModel.class);
			modelList.add(cisHzyzModel);
		}
		return modelList;
	}

	/**
	 * 根据记录序号查询病区医嘱
	 *
	 * @param jlxh 记录序号
	 */
	public CisHzyzModel getById(Integer jlxh) {
		CisHzyz hzyz = cisHzyzDao.getById(jlxh);
		return BeanUtil.toBean(hzyz, CisHzyzModel.class);
	}


	/**
	 * 根据记录序号或者组套记录序号查询
	 * @Title: doQueryByJlxhOrZtjlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sbxh
	 * @param @param ztbz
	 * @param @return    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public List<CisHzyzModel> doQueryByJlxhOrZtjlxh(Integer sbxh, Integer ztbz) {
		List<CisHzyz> cisHzyzList =  cisHzyzDao.doQueryByJlxhOrZtjlxh(sbxh, ztbz);
		List<CisHzyzModel> modelList = new ArrayList<CisHzyzModel>();
		for(CisHzyz cisHzyz : cisHzyzList) {
			CisHzyzModel cisHzyzModel = BeanUtil.toBean(cisHzyz,CisHzyzModel.class);
			modelList.add(cisHzyzModel);
		}
		return modelList;
	}


	/**
	 * 根据申请id更新抗菌药物医嘱
	 * @Title: updateYzAntibiotics
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param kjywsp
	 * @param @param sqid    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void updateYzAntibiotics(Integer kjywsp, Integer sqid) {
		cisHzyzDao.updateYzAntibiotics(kjywsp, sqid);
	}

	/**
	 * 抗菌药物申请单更新到医嘱
	 * @Title: updateAntibioticsSqidByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqid
	 * @param @param jlxh    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void updateAntibioticsSqidByJlxh(Integer sqid, Integer jlxh) {
		cisHzyzDao.updateAntibioticsSqidByJlxh(sqid, jlxh);
	}

	/**
	 * 更新医嘱的抗菌药物状态和申请id
	 * @Title: removeAntibioticsStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqid    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void removeAntibioticsStatus(Integer sqid) {
		cisHzyzDao.removeAntibioticsStatus(sqid);
	
	}
	
	@Override
	public void insert(CisHzyzModel cisHzyz) {
		cisHzyzDao.insert(cisHzyz);
	}

	@Override
	public Map<String, Object> getzfbl(Map<String, Object> zfblParamMap) {
		return cisHzyzSer.getzfbl(zfblParamMap);
	}

	/**
	 * 保存住院手术申请单医嘱信息
	 * @Title: saveZySssqYzInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyzSssqApiReq
	 * @param @param user    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void saveZySssqYzInfo(CisHzyzSssqApiReq cisHzyzSssqApiReq, SysUser user) {
		if(OperationTypeEnum.update.getCode().equals(cisHzyzSssqApiReq.getOpStatus())) {
			cisHzyzDao.deleteOrdersBySqid(cisHzyzSssqApiReq.getSqdh(), 3);
		}
		 // 保存医嘱信息
        Map<String, Object> brxx = new HashMap<>(16);// 病人信息
        List<Map<String, Object>> yzxxArr = new ArrayList<Map<String, Object>>();
        Map<String, Object> yzxx = new HashMap<>(16);// 医嘱信息
        ImHzry imHzry = imHzryDao.getById(cisHzyzSssqApiReq.getZyh());
        // 赋值病人信息
        brxx.put("brks", imHzry.getBrks());
        brxx.put("brbq", imHzry.getBrbq());
        brxx.put("brch", imHzry.getBrch());
        brxx.put("zyh", cisHzyzSssqApiReq.getZyh());
        brxx.put("brxm", imHzry.getBrxm());

        yzxx = new HashMap<>(16);// 医嘱信息
        // 赋值一条行为医嘱信息
        yzxx.put("opStatus", OperationTypeEnum.create.getCode());
        yzxx.put("sqid", cisHzyzSssqApiReq.getSqdh());// 申请申请ID(SQID)
        yzxx.put("zfyp", 0);// 自备药标识
        yzxx.put("ystj", 0);// 医生提交标志
        yzxx.put("ycsl", 1);// 一次数量
        yzxx.put("mrcs", 1);// 每日次数
        yzxx.put("czgh", user.getUserId());// 操作工号
        yzxx.put("ysbz", 1);// 医生医嘱标志
        yzxx.put("srcs", 0);// 首日次数
        yzxx.put("yfbz", 0);
        yzxx.put("zfpb", 0);// 自负判别
        yzxx.put("yplx", 0);// 药品类型
        yzxx.put("sybz", 0);// 使用标志
        yzxx.put("mzcs", 0);// 每周次数
        yzxx.put("yjzx", 0);// 医技主项
        yzxx.put("xmlx", 0);// 项目类型
        yzxx.put("jfbz", 3);// 计费标志
        yzxx.put("zfbz", 0);// 作废标志
        yzxx.put("sfjg", 0);// 审方结果
        yzxx.put("fhbz", 0);// 复核标志
        yzxx.put("tzfhbz", 0);// 停嘱复核标志
        yzxx.put("pspb", 0);// 皮试判别
        yzxx.put("yzpb", 0);// 医嘱判别
        yzxx.put("lsbz", 0);// 历史标志
        yzxx.put("yepb", 0);// 婴儿判别
        yzxx.put("fysx", 0);// 发药属性
        yzxx.put("tpn", 0);// tpn
        yzxx.put("lsyz", 1);// 临时医嘱
        yzxx.put("cfts", 0);// 贴数
        yzxx.put("ypzs", 1);// 煎法
        yzxx.put("jz", 1);// 脚注
        yzxx.put("ztbz", 0);// 非组套医嘱
        yzxx.put("ypdj", 0);// 药品单价
        yzxx.put("yzzhShow", 2);// 医嘱组号
        yzxx.put("ysgh", user.getUserId());// 开嘱医生
        yzxx.put("kssj", cisHzyzSssqApiReq.getSqrq());// 开始时间(暂时用申请单申请日期)
        yzxx.put("yzzxsj", "23:59");//
        yzxx.put("jgid", user.getHospitalId());// 机构ID
        yzxx.put("zyh", cisHzyzSssqApiReq.getZyh());//
        yzxx.put("yzmc", cisHzyzSssqApiReq.getSsmc());// 医嘱名称
        yzxx.put("yzlx", 3);// 医嘱类型
        yzxxArr.add(yzxx);

        Map<String, Object> yzxxBody = new HashMap<>(16);
        yzxxBody.put("brxx", brxx);
        yzxxBody.put("yzxx", yzxxArr);
        yzxxBody.put("fjxx", new ArrayList<Map<String, Object>>());

        Map<String, Object> yzxxBody2 = new HashMap<>(16);
        yzxxBody2.put("body", yzxxBody);

        CisHzyzSaveReq cisHzyzSaveReq = BeanUtil.fillBeanWithMapIgnoreCase(brxx, new CisHzyzSaveReq(), true);
        cisHzyzSaveReq.setCisHzyzReqFjList(new ArrayList<>());
		List<CisHzyzReq> cisHzyzReqList = BUHISUtil.ListToResultSet(yzxxArr, new CisHzyzReq());
        cisHzyzSaveReq.setCisHzyzReqList(cisHzyzReqList);

        imHzryByDctworkSer.saveWardPatientInfo(cisHzyzSaveReq, user);
		
	}

	/**
	 * 更新抗菌药物医嘱提交状态
	 * @Title: updateAntibioticsTjByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jlxh    设定文件
	 * @author 龚方舟
	 * @throws
	 */
	@Override
	public void updateAntibioticsTjByJlxh(Integer jlxh) {
		cisHzyzDao.updateAntibioticsTjByJlxh(jlxh);
	}


}
