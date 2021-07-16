package com.buit.cis.nurse.service;


import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.utill.RedisFactory;
import com.buit.aop.lock.Locked;
import com.buit.cis.nurse.dao.NisAllzxdDao;
import com.buit.cis.nurse.dao.NisBqyjpgdDao;
import com.buit.cis.nurse.dao.NisCghljldDao;
import com.buit.cis.nurse.dao.NisChhldDao;
import com.buit.cis.nurse.dao.NisCqhldDao;
import com.buit.cis.nurse.dao.NisDdzcpgbDao;
import com.buit.cis.nurse.dao.NisFsspgbDao;
import com.buit.cis.nurse.dao.NisFssriskDao;
import com.buit.cis.nurse.dao.NisFyfxpgdDao;
import com.buit.cis.nurse.dao.NisGdhtwxysbDao;
import com.buit.cis.nurse.dao.NisHljlDao;
import com.buit.cis.nurse.dao.NisHljlmbDao;
import com.buit.cis.nurse.dao.NisHzmbDao;
import com.buit.cis.nurse.dao.NisLzdngpgbDao;
import com.buit.cis.nurse.dao.NisRjbfhldDao;
import com.buit.cis.nurse.dao.NisRyhldDao;
import com.buit.cis.nurse.dao.NisSjkjldDao;
import com.buit.cis.nurse.dao.NisWzhldDao;
import com.buit.cis.nurse.dao.NisYcwxpgbDao;
import com.buit.cis.nurse.dao.NisYlxsscbdDao;
import com.buit.cis.nurse.dao.NisZspdlDao;
import com.buit.cis.nurse.dao.NisZxjmpgbDao;
import com.buit.cis.nurse.enums.NursingTypeEnum;
import com.buit.cis.nurse.model.NisAllzxd;
import com.buit.cis.nurse.model.NisBqyjpgd;
import com.buit.cis.nurse.model.NisCghljld;
import com.buit.cis.nurse.model.NisChhld;
import com.buit.cis.nurse.model.NisCqhld;
import com.buit.cis.nurse.model.NisDdzcpgb;
import com.buit.cis.nurse.model.NisFsspgb;
import com.buit.cis.nurse.model.NisFssrisk;
import com.buit.cis.nurse.model.NisFyfxpgd;
import com.buit.cis.nurse.model.NisGdhtwxysb;
import com.buit.cis.nurse.model.NisHljlmb;
import com.buit.cis.nurse.model.NisHzmb;
import com.buit.cis.nurse.model.NisLzdngpgb;
import com.buit.cis.nurse.model.NisRjbfhld;
import com.buit.cis.nurse.model.NisRyhld;
import com.buit.cis.nurse.model.NisSjkjld;
import com.buit.cis.nurse.model.NisWzhld;
import com.buit.cis.nurse.model.NisYcwxpgb;
import com.buit.cis.nurse.model.NisYlxsscbd;
import com.buit.cis.nurse.model.NisZspdl;
import com.buit.cis.nurse.model.NisZxjmpgb;
import com.buit.cis.nurse.request.NisHzmbReq;
import com.buit.cis.nurse.response.NisHzmbResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 患者护理记录模板表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHzmbSer extends BaseManagerImp<NisHzmb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHzmbSer.class);
    
    @Autowired
    private NisHzmbDao nisHzmbDao;
    
    @Override
    public NisHzmbDao getEntityMapper(){        
        return nisHzmbDao;
    }
    
    @Autowired
    private NisHljlDao nisHljlDao;
    
    @Autowired
    private NisWzhldDao nisWzhldDao;
    
    @Autowired
    private NisBqyjpgdDao nisBqyjpgdDao;
    
    @Autowired
    private NisZspdlDao nisZspdlDao;
    
    @Autowired
    private NisCqhldDao nisCqhldDao;
    
    @Autowired
    private NisChhldDao nisChhldDao;
    
    @Autowired
    private NisYcwxpgbDao nisYcwxpgbDao;
    
    @Autowired
    private NisFyfxpgdDao nisFyfxpgdDao;
    
    @Autowired
    private NisGdhtwxysbDao nisGdhtwxysbDao;
    
    @Autowired
    private NisCghljldDao nisCghljldDao;
    
    @Autowired
    private NisDdzcpgbDao nisDdzcpgbDao;
    
    @Autowired
    private NisLzdngpgbDao nisLzdngpgbDao;
    
    @Autowired
    private NisZxjmpgbDao nisZxjmpgbDao;
    
    @Autowired
    private NisFsspgbDao nisFsspgbDao;
    
    @Autowired
    private NisSjkjldDao nisSjkjldDao;
    
    @Autowired
    private NisAllzxdDao nisAllzxdDao;
    
    @Autowired
    private NisRyhldDao nisRyhldDao;
    
    @Autowired
    private NisYlxsscbdDao nisYlxsscbdDao;
    
    @Autowired
    private NisFssriskDao nisFssriskDao;
    
    @Autowired
    private NisRjbfhldDao nisRjbfhldDao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisHljlmbDao nisHljlmbDao;
    
    /**
     * 查询患者模板树形结构
     * @Title: queryHzmbTree
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param user
     * @param @return    设定文件
     * @return List<NisHzmbResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    public List<NisHzmbResp> queryHzmbTree(Integer zyh, SysUser user){
    	List<NisHzmbResp> nisHzmbRespList = new ArrayList<NisHzmbResp>();
    	Integer jgid = user.getHospitalId();
    	NisHzmb nisHzmb = new NisHzmb();
    	nisHzmb.setZyh(zyh);
    	nisHzmb.setJgid(jgid);
    	List<NisHzmb> hzmbList = nisHzmbDao.findByEntity(nisHzmb);
    	for(NisHzmb hzmb : hzmbList) {
    		NisHzmbResp nisHzmbResp = new NisHzmbResp();
    		nisHzmbResp.setLabel(hzmb.getMbmc());
    		nisHzmbResp.setName(hzmb.getMblx());
    		List<NisHzmbResp> respList = new ArrayList<NisHzmbResp>();;
    		if(NursingTypeEnum.commonly.getCode().equals(hzmb.getMblx())) {
    			respList = nisHljlDao.queryHljlTree(zyh, jgid);
    		}else if(NursingTypeEnum.commonlyGrave.getCode().equals(hzmb.getMblx())) {
    			respList = nisWzhldDao.queryWzhldTree(zyh, jgid);
    		}else if(NursingTypeEnum.diseaseWarning.getCode().equals(hzmb.getMblx())) {
    			respList = nisBqyjpgdDao.queryBqyjpgdTree(zyh, jgid);
    		}else if(NursingTypeEnum.barthelRecord.getCode().equals(hzmb.getMblx())) {
    			respList = nisZspdlDao.queryZspdlTree(zyh, jgid);
    		}else if(NursingTypeEnum.prenatalCare.getCode().equals(hzmb.getMblx())) {
    			respList = nisCqhldDao.queryCqhldTree(zyh, jgid);
    		}else if(NursingTypeEnum.postpartumCare.getCode().equals(hzmb.getMblx())) {
    			respList = nisChhldDao.queryChhldTree(zyh, jgid);
    		}else if(NursingTypeEnum.pressureSore.getCode().equals(hzmb.getMblx())) {
    			respList = nisYcwxpgbDao.queryYcwxpgbTree(zyh, jgid);
    		}else if(NursingTypeEnum.acquiredPneumonia.getCode().equals(hzmb.getMblx())) {
    			respList = nisFyfxpgdDao.queryFyfxpgdTree(zyh, jgid);
    		}else if(NursingTypeEnum.pipeSlippage.getCode().equals(hzmb.getMblx())) {
    			respList = nisGdhtwxysbDao.queryGdhtTree(zyh, jgid);
    		}else if(NursingTypeEnum.nursingRecord.getCode().equals(hzmb.getMblx())) {
    			respList = nisCghljldDao.queryCghljlTree(zyh, jgid);
    		}else if(NursingTypeEnum.fallingBed.getCode().equals(hzmb.getMblx())) {
    			respList = nisDdzcpgbDao.queryDdzcpgbTree(zyh, jgid);
    		}else if(NursingTypeEnum.catheterInfection.getCode().equals(hzmb.getMblx())) {
    			respList = nisLzdngpgbDao.queryLzdngTree(zyh, jgid);
    		}else if(NursingTypeEnum.stressInjury.getCode().equals(hzmb.getMblx())) {
    			respList = nisYlxsscbdDao.queryYlxsscbdTree(zyh, jgid);
    		}else if(NursingTypeEnum.centralVenousCatheter.getCode().equals(hzmb.getMblx())) {
    			respList = nisZxjmpgbDao.queryZxjmpgbTree(zyh, jgid);
    		}else if(NursingTypeEnum.emotionalDisorder.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.patientCare.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.youthCare.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.abuseNeglect.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.frailOldMan.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.infectiousDiseases.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.terminalPregnancy.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.stageDisease.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.chemotherapyPatient.getCode().equals(hzmb.getMblx())
    				|| NursingTypeEnum.chronicPain.getCode().equals(hzmb.getMblx())
					|| NursingTypeEnum.hypoimmunityPatient.getCode().equals(hzmb.getMblx())) {
    			respList = nisAllzxdDao.queryZxdTree(zyh, jgid, hzmb.getMblx());
    		}else if(NursingTypeEnum.pulmonaryEmbolism.getCode().equals(hzmb.getMblx())) {
    			respList = nisFssriskDao.queryFssriskTree(zyh, jgid);
    		}else if(NursingTypeEnum.deepVeinThrombosis.getCode().equals(hzmb.getMblx())) {
    			respList = nisFsspgbDao.queryFsspgbTree(zyh, jgid, hzmb.getMblx());
    		}else if(NursingTypeEnum.admissionNursing.getCode().equals(hzmb.getMblx())) {
    			respList = nisRyhldDao.queryRyhldTree(zyh, jgid);
    		}else if(NursingTypeEnum.neurologyRecords.getCode().equals(hzmb.getMblx())) {
    			respList = nisSjkjldDao.querySjkjldTree(zyh, jgid);
    		}else if(NursingTypeEnum.dayWard.getCode().equals(hzmb.getMblx())) {
    			respList = nisRjbfhldDao.queryRjbfhldTree(zyh, jgid);
    		}
    		nisHzmbResp.setChildren(respList);
    		nisHzmbRespList.add(nisHzmbResp);
    	}
    	return nisHzmbRespList;
    }
    
    /**
     * 护理表单补足空行
     * @Title: ComplementEmptyline
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @param pageRows    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    public void ComplementEmptyline(List<Map<String, Object>> list, Integer pageRows) {
    	int divNum = list.size() % pageRows;
    	boolean flag = divNum == 0;
    	if(!flag) {
    		for(int i = 0; i < pageRows - divNum; i++ ) {
        		Map<String, Object> map = new HashMap<String, Object>(16);
        		map.put("rq", "");
        		list.add(map);
        	}
    	}
    }

    /**
     * 患者护理记录模板保存
     * @Title: saveHzmb
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisHzmbReq
     * @param @param hospitalId    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Locked(value = {"saveHzmb_#[nisHzmbReq.zyh]_#[nisHzmbReq.mblx]"})
	public void saveHzmb(NisHzmbReq nisHzmbReq, Integer hospitalId) {
		nisHzmbReq.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HZMB));
    	nisHzmbReq.setJgid(hospitalId);
    	NisHzmb hzmb = new NisHzmb();
    	hzmb.setMblx(nisHzmbReq.getMblx());
    	hzmb.setZyh(nisHzmbReq.getZyh());
    	long count = nisHzmbDao.findByEntityCount(hzmb);
    	if(count > 0) {
    		throw BaseException.create("ERROR_NURSEWORK_HLJL_0003");
    	}
    	NisHljlmb nisHljlmb = new NisHljlmb();
    	nisHljlmb.setMblx(nisHzmbReq.getMblx());
    	nisHljlmb.setJgid(hospitalId);
    	nisHljlmb.setZfpb(0);
    	List<NisHljlmb> list = nisHljlmbDao.findByEntity(nisHljlmb);
    	if(CollectionUtils.isNotEmpty(list)) {
    		nisHzmbReq.setMbmc(list.get(0).getMbmc());
    	}
	    nisHzmbDao.insert(nisHzmbReq);   
	}

    /**
     * 根据住院号和模板类型删除模板
     * @Title: deleteByZyhAndMblx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param mblx
     * @param @param hospitalId    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
	public void deleteByZyhAndMblx(Integer zyh, String mblx, Integer hospitalId) {
		long count = 0;
		if(NursingTypeEnum.commonlyGrave.getCode().equals(mblx)) {
			NisWzhld nisWzhld = new NisWzhld();
			nisWzhld.setZyh(zyh);
			nisWzhld.setJgid(hospitalId);
			count = nisWzhldDao.findByEntityCount(nisWzhld);
		}else if(NursingTypeEnum.diseaseWarning.getCode().equals(mblx)) {
			NisBqyjpgd nisBqyjpgd = new NisBqyjpgd();
			nisBqyjpgd.setZyh(zyh);
			nisBqyjpgd.setJgid(hospitalId);
			count = nisBqyjpgdDao.findByEntityCount(nisBqyjpgd);
		}else if(NursingTypeEnum.barthelRecord.getCode().equals(mblx)) {
			NisZspdl nisZspdl = new NisZspdl();
			nisZspdl.setZyh(zyh);
			nisZspdl.setJgid(hospitalId);
			count = nisZspdlDao.findByEntityCount(nisZspdl);
		}else if(NursingTypeEnum.prenatalCare.getCode().equals(mblx)) {
			NisCqhld nisCqhld = new NisCqhld();
			nisCqhld.setZyh(zyh);
			nisCqhld.setJgid(hospitalId);
			count = nisCqhldDao.findByEntityCount(nisCqhld);
		}else if(NursingTypeEnum.postpartumCare.getCode().equals(mblx)) {
			NisChhld nisChhld = new NisChhld();
			nisChhld.setZyh(zyh);
			nisChhld.setJgid(hospitalId);
			count = nisChhldDao.findByEntityCount(nisChhld);
		}else if(NursingTypeEnum.pressureSore.getCode().equals(mblx)) {
			NisYcwxpgb nisYcwxpgb = new NisYcwxpgb();
			nisYcwxpgb.setZyh(zyh);
			nisYcwxpgb.setJgid(hospitalId);
			count = nisYcwxpgbDao.findByEntityCount(nisYcwxpgb);
		}else if(NursingTypeEnum.acquiredPneumonia.getCode().equals(mblx)) {
			NisFyfxpgd nisFyfxpgd = new NisFyfxpgd();
			nisFyfxpgd.setZyh(zyh);
			nisFyfxpgd.setJgid(hospitalId);
			count = nisFyfxpgdDao.findByEntityCount(nisFyfxpgd);
		}else if(NursingTypeEnum.pipeSlippage.getCode().equals(mblx)) {
			NisGdhtwxysb nisGdhtwxysb = new NisGdhtwxysb();
			nisGdhtwxysb.setZyh(zyh);
			nisGdhtwxysb.setJgid(hospitalId);
			count = nisGdhtwxysbDao.findByEntityCount(nisGdhtwxysb);
		}else if(NursingTypeEnum.nursingRecord.getCode().equals(mblx)) {
			NisCghljld nisCghljld = new NisCghljld();
			nisCghljld.setZyh(zyh);
			nisCghljld.setJgid(hospitalId);
			count = nisCghljldDao.findByEntityCount(nisCghljld);
		}else if(NursingTypeEnum.fallingBed.getCode().equals(mblx)) {
			NisDdzcpgb nisDdzcpgb = new NisDdzcpgb();
			nisDdzcpgb.setZyh(zyh);
			nisDdzcpgb.setJgid(hospitalId);
			count = nisDdzcpgbDao.findByEntityCount(nisDdzcpgb);
		}else if(NursingTypeEnum.catheterInfection.getCode().equals(mblx)) {
			NisLzdngpgb nisLzdngpgb = new NisLzdngpgb();
			nisLzdngpgb.setZyh(zyh);
			nisLzdngpgb.setJgid(hospitalId);
			count = nisLzdngpgbDao.findByEntityCount(nisLzdngpgb);
		}else if(NursingTypeEnum.stressInjury.getCode().equals(mblx)) {
			NisYlxsscbd nisYlxsscbd = new NisYlxsscbd();
			nisYlxsscbd.setZyh(zyh);
			nisYlxsscbd.setJgid(hospitalId);
			count = nisYlxsscbdDao.findByEntityCount(nisYlxsscbd);
		}else if(NursingTypeEnum.centralVenousCatheter.getCode().equals(mblx)) {
			NisZxjmpgb nisZxjmpgb = new NisZxjmpgb();
			nisZxjmpgb.setZyh(zyh);
			nisZxjmpgb.setJgid(hospitalId);
			count = nisZxjmpgbDao.findByEntityCount(nisZxjmpgb);
		}else if(NursingTypeEnum.emotionalDisorder.getCode().equals(mblx)
				|| NursingTypeEnum.patientCare.getCode().equals(mblx)
				|| NursingTypeEnum.youthCare.getCode().equals(mblx)
				|| NursingTypeEnum.abuseNeglect.getCode().equals(mblx)
				|| NursingTypeEnum.frailOldMan.getCode().equals(mblx)
				|| NursingTypeEnum.infectiousDiseases.getCode().equals(mblx)
				|| NursingTypeEnum.terminalPregnancy.getCode().equals(mblx)
				|| NursingTypeEnum.stageDisease.getCode().equals(mblx)
				|| NursingTypeEnum.chemotherapyPatient.getCode().equals(mblx)
				|| NursingTypeEnum.chronicPain.getCode().equals(mblx)
				|| NursingTypeEnum.hypoimmunityPatient.getCode().equals(mblx)) {
			NisAllzxd nisAllzxd = new NisAllzxd();
			nisAllzxd.setZyh(zyh);
			nisAllzxd.setMblx(mblx);
			nisAllzxd.setJgid(hospitalId);
			count = nisAllzxdDao.findByEntityCount(nisAllzxd);
		}else if(NursingTypeEnum.pulmonaryEmbolism.getCode().equals(mblx)) {
			NisFssrisk nisFssrisk = new NisFssrisk();
			nisFssrisk.setZyh(zyh);
			nisFssrisk.setJgid(hospitalId);
			count = nisFssriskDao.findByEntityCount(nisFssrisk);
		}else if(NursingTypeEnum.deepVeinThrombosis.getCode().equals(mblx)) {
			NisFsspgb nisFsspgb = new NisFsspgb();
			nisFsspgb.setZyh(zyh);
			nisFsspgb.setJgid(hospitalId);
			count = nisFsspgbDao.findByEntityCount(nisFsspgb);
		}else if(NursingTypeEnum.admissionNursing.getCode().equals(mblx)) {
			NisRyhld nisRyhld = new NisRyhld();
			nisRyhld.setZyh(zyh);
			nisRyhld.setJgid(hospitalId);
			count = nisRyhldDao.findByEntityCount(nisRyhld);
		}else if(NursingTypeEnum.neurologyRecords.getCode().equals(mblx)) {
			NisSjkjld nisSjkjld = new NisSjkjld();
			nisSjkjld.setZyh(zyh);
			nisSjkjld.setJgid(hospitalId);
			count = nisSjkjldDao.findByEntityCount(nisSjkjld);
		}else if(NursingTypeEnum.dayWard.getCode().equals(mblx)) {
			NisRjbfhld nisRjbfhld = new NisRjbfhld();
			nisRjbfhld.setZyh(zyh);
			nisRjbfhld.setJgid(hospitalId);
			count = nisRjbfhldDao.findByEntityCount(nisRjbfhld);
		}
		NisHzmb nisHzmb = new NisHzmb();
		nisHzmb.setZyh(zyh);
		nisHzmb.setMblx(mblx);
		nisHzmb.setJgid(hospitalId);
		if(count > 0) {
			List<NisHzmb> respList = nisHzmbDao.findByEntity(nisHzmb);
			if(CollectionUtils.isNotEmpty(respList)) {
				throw BaseException.create("ERROR_NURSEWORK_HLJL_0004", new String[] {
						respList.get(0).getMbmc()
				});
			}
		}
		nisHzmbDao.removeByEntity(nisHzmb);
	}

}
