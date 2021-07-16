package com.buit.cis.dctwork.service;


import cn.hutool.core.bean.BeanUtil;
import com.buit.cis.dctwork.dao.EnrJg01Dao;
import com.buit.cis.dctwork.dao.EnrJg02Dao;
import com.buit.cis.dctwork.dao.EnrJl01Dao;
import com.buit.cis.dctwork.dao.EnrJl02Dao;
import com.buit.cis.dctwork.model.EnrJg01;
import com.buit.cis.dctwork.model.EnrJl01;
import com.buit.cis.dctwork.model.EnrJl02;
import com.buit.cis.dctwork.request.EnrJbysSaveReq;
import com.buit.cis.dctwork.request.EnrJlSaveReq;
import com.buit.cis.dctwork.response.EnrJg02Resp;
import com.buit.cis.dctwork.response.EnrJl02Resp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class EnrJl01Ser extends BaseManagerImp<EnrJl01,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(EnrJl01Ser.class);
    
    @Autowired
    private EnrJl01Dao enrJl01Dao;
    
    @Override
    public EnrJl01Dao getEntityMapper(){        
        return enrJl01Dao;
    }
    
    @Autowired
    private EnrJg02Dao enrJg02Dao;
    
    @Autowired
    private EnrJg01Dao enrJg01Dao;
    
    @Autowired
    private EnrJl02Dao enrJl02Dao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    
    /** 
    * @Title: queryRecordMete
    * @Description: TODO 查询护理结构表单
    * @param @return    设定文件
    * @return List<EnrJg02Resp>    返回类型
    * @author 龚方舟
    * @throws
     */
	public List<EnrJg02Resp> queryRecordMete() {
		try {
			Integer jgbh = getJgbh();
			return enrJg02Dao.queryEnrJg02ByJgbh(jgbh);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ENRJL01_0001");
		}
	}
	
	/**
	* @Title: getJGBH
	* @Description: TODO 获取 ENR_JG01结构编号
	* @param @return    设定文件
	* @return Integer    返回类型
	* @author 龚方舟
	* @throws
	 */
	public Integer getJgbh(){
		Integer jgbh = 0;
		try {
			EnrJg01 enrJg01 = new EnrJg01();
			enrJg01.setJgmc("一般护理记录单");
			List<EnrJg01> enrJg01List = enrJg01Dao.findByEntity(enrJg01);
			if (enrJg01List.size() > 0) {
				jgbh = enrJg01List.get(0).getJgbh();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BaseException.create("ERROR_DCTWORK_ENRJL01_0001");
		}
		return jgbh;
	}
	
	/**
	* @Title: queryEnrJl02ByJlbh
	* @Description: TODO 根据记录编号获取护理明细表单
	* @param @param jlbh
	* @param @param user
	* @param @return    设定文件
	* @return List<EnrJl02Resp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	public List<EnrJl02Resp> queryEnrJl02ByJlbh(Integer jlbh, SysUser user) {
		Integer jgid = user.getHospitalId();
		try {
			EnrJl02 enrJl02 = new EnrJl02();
			enrJl02.setJgid(jgid);
			enrJl02.setJlbh(jlbh);
			return enrJl02Dao.queryEnrJl02ByJlbh(enrJl02);
		} catch (Exception e) {
			logger.error("护理记录模块，根据记录编号获取护理明细表单失败", e);
			throw BaseException.create("ERROR_DCTWORK_ENRJL01_0002");
		}
	}
	
	/**
	 * 
	* @Title: doSaveNursingRecords
	* @Description: TODO 新增或修改保存护理记录
	* @param @param req
	* @param @param res
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void doSaveNursingRecords(EnrJlSaveReq enrJlSaveReq, SysUser user) {
        List<EnrJbysSaveReq> enrJbysSaveReqList = enrJlSaveReq.getEnrJbysSaveReqList();
        List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(enrJbysSaveReqList);
        Map<String, Object> body = BUHISUtil.caseInsensitiveMap(enrJlSaveReq);
        if (body.get("JLBH") != null) {// 记录编号不为空时，表示更新动作
            updateNursingRecords(body, listMap, user);
        } else {
            //记录编号为空时表示新增动作
            saveNursingRecords(body, listMap, user);
        }

    }
	
	
	/**
	* @Title: updateNursingRecords
	* @Description: TODO 更新护理记录录入
	* @param @param req
	* @param @param res
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void updateNursingRecords(Map<String, Object> req, List<Map<String, Object>> list, SysUser user) {
		Integer userId = user.getUserId();
		Integer jgid = user.getHospitalId();
		Integer jlbh = ObjectToTypes.parseInt(req.get("JLBH"));
		try {
            EnrJl02 enrJl02 = new EnrJl02();
            enrJl02.setJlbh(jlbh);
            enrJl02.setJgid(jgid);
            List<EnrJl02> enrJl02List = enrJl02Dao.findByEntity(enrJl02);
            List<Map<String, Object>> enrjl02 = BUHISUtil.ListObjToMap(enrJl02List);
            Map<String, Object> map = null, hlmxbd = null, tmpMap = null;
            boolean updateFlag = false;
            String lsxmqz, lsxsmc;
            Integer mxbh = 0;
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    map = list.get(i);
                    updateFlag = false;
                    lsxmqz = String.valueOf(map.get("XMQZ"));
                    lsxsmc = String.valueOf(map.get("XSMC"));
					hlmxbd = new HashMap<String, Object>(16);
					if ("".equals(lsxmqz) || "null".equals(lsxmqz)) {// 项目取值为空则不保存
						lsxmqz = " ";
					}
					for (int j = 0; j < enrjl02.size(); j++) {
						tmpMap = enrjl02.get(j);
						// 如果该明细编号已经在数据库中存在则表示更新
						if (String.valueOf(tmpMap.get("XMBH")).equals(String.valueOf(map.get("XMBH")))) {
							mxbh = ObjectToTypes.parseInt(tmpMap.get("MXBH"));
							updateFlag = true;
							// 如果这次更新的项目取值与数据库中的数据不一致，则修改标志为1否则使用本身保存的修改标志
							if (lsxmqz.equals(String.valueOf(tmpMap.get("XMQZ")))) {
								hlmxbd.put("XGBZ", tmpMap.get("XGBZ"));
							} else {
								hlmxbd.put("XGBZ", 1);// 修改标志为1
							}
							break;
						}
					}

					if (updateFlag) {// 更新时需要设计院有的明细编号
						hlmxbd.put("MXBH", mxbh);
					} else {// 对于新增加的修改标志为0
						hlmxbd.put("XGBZ", 0);
					}
					hlmxbd.put("JLBH", jlbh);
					hlmxbd.put("XMBH", ObjectToTypes.parseInt(map.get("XMBH")));
					hlmxbd.put("XMMC", String.valueOf(map.get("XMMC")));
					hlmxbd.put("XSMC", String.valueOf(map.get("XSMC")));
					hlmxbd.put("KSLH", ObjectToTypes.parseInt(map.get("KSLH")));
					hlmxbd.put("JSLH", ObjectToTypes.parseInt(map.get("JSLH")));
					hlmxbd.put("HDBZ", ObjectToTypes.parseInt(map.get("HDBZ")));
					hlmxbd.put("YMCLFS", ObjectToTypes.parseInt(map.get("YMCLFS")));
					hlmxbd.put("HHJG", ObjectToTypes.parseInt(map.get("HHJG")));
					hlmxbd.put("JGID", jgid);
					hlmxbd.put("XSMC", lsxsmc);
					hlmxbd.put("XMQZ", lsxmqz);
					EnrJl02 enrJl02New = BeanUtil.fillBeanWithMapIgnoreCase(hlmxbd, 
							new EnrJl02(), true);	
					if (updateFlag) {
						enrJl02Dao.update(enrJl02New);
						//上海天佑医院特殊需求，要求护理记录的时间可以修改。
						String tsStr = String.valueOf(req.get("TIME"));
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						ts = Timestamp.valueOf(tsStr);
						Map<String, Object> jlsjMap = new HashMap<String, Object>(16);
						jlsjMap.put("JLSJ", ts);
						jlsjMap.put("JLBH", jlbh);
						enrJl01Dao.updateJlTimeByJlbh(jlsjMap);
					} else {
						enrJl02New.setMxbh(redisFactory.getTableKey(TableName.DB_NAME, TableName.ENR_JL02));
						enrJl02Dao.insert(enrJl02New);
					}
				}
			}
		} catch (Exception e) {
			logger.error("护理记录模块，更新护理明细表单失败", e);
			throw BaseException.create("ERROR_DCTWORK_ENRJL01_0003");
		}
	}
	
	/**
	* @Title: saveNursingRecords
	* @Description: TODO 保存新增护理记录
	* @param @param req
	* @param @param res
	* @param @param user    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	public void saveNursingRecords(Map<String, Object> req, List<Map<String, Object>> list, SysUser user) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>(16);
			Integer userid = user.getUserId();
			Integer jgid = user.getHospitalId();
			String tsStr = String.valueOf(req.get("TIME"));
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			ts = Timestamp.valueOf(tsStr);
			Integer jgbh = getJgbh();
			parameters.put("JGID", jgid);
			parameters.put("ZYH", ObjectToTypes.parseInt(req.get("ZYH")));
			parameters.put("JGBH", jgbh);
			parameters.put("JLMC", "护理记录");
			parameters.put("BLLX", 2);
			parameters.put("DLLB", 0);
			parameters.put("DLJ", "");
			parameters.put("JLHS", 0);
			parameters.put("HYBZ", 0);
			parameters.put("JLSJ", ts);
			parameters.put("SXSJ", ts);
			parameters.put("XTSJ", ts);
			parameters.put("SXBQ", ObjectToTypes.parseInt(req.get("BRBQDM")));
			parameters.put("SXHS", userid);// 当前操作工号
			parameters.put("WCQM", 0);
			parameters.put("SYBZ", 0);
			parameters.put("SYHS", "");
			parameters.put("SYQM", 0);
			parameters.put("DYBZ", 0);
			parameters.put("JLZT", 0);
			parameters.put("ZJLX", 0);
			parameters.put("BLLB", 0);
			parameters.put("MBLB", 0);
			parameters.put("MBBH", 0);
			parameters.put("DLHHBZ", 0);
			EnrJl01 enrJl01 = BeanUtil.fillBeanWithMapIgnoreCase(parameters, 
					new EnrJl01(), true);	
			Integer jlbh = redisFactory.getTableKey(TableName.DB_NAME, TableName.ENR_JL01);
			enrJl01.setJlbh(jlbh);
			enrJl01Dao.insert(enrJl01);
		
			Map<String, Object> hlmxbd = null, map = null;
			String lsxsmc, lsxmqz;
			if(CollectionUtils.isNotEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					map = list.get(i);
					String llxmbh = String.valueOf(map.get("XMBH"));
					if ("".equals(llxmbh) || "null".equals(llxmbh)) {
						continue;
					}
					lsxsmc = String.valueOf(map.get("XSMC"));
					lsxmqz = String.valueOf(map.get("XMQZ"));
					//项目取值为空则不保存
					if ("".equals(lsxmqz) || "null".equals(lsxmqz)) {
						continue;
					}
					hlmxbd = new HashMap<String, Object>(16);
					hlmxbd.put("JLBH", jlbh);
					hlmxbd.put("XMBH",ObjectToTypes.parseInt(map.get("XMBH")));
					hlmxbd.put("XMMC", String.valueOf(map.get("XMMC")));
					hlmxbd.put("XSMC", String.valueOf(map.get("XSMC")));
					hlmxbd.put("XMQZ", "");
					hlmxbd.put("KSLH", ObjectToTypes.parseInt(map.get("KSLH")));
					hlmxbd.put("JSLH", ObjectToTypes.parseInt(map.get("JSLH")));
					hlmxbd.put("HDBZ", ObjectToTypes.parseInt(map.get("HDBZ")));
					hlmxbd.put("YMCLFS", ObjectToTypes.parseInt(map.get("YMCLFS")));
					hlmxbd.put("HHJG", ObjectToTypes.parseInt(map.get("HHJG")));
					hlmxbd.put("XGBZ", 0);
					hlmxbd.put("GXBZ", 0);
					hlmxbd.put("JGID", jgid);
					hlmxbd.put("XSMC", lsxsmc);
					hlmxbd.put("XMQZ", lsxmqz);
					EnrJl02 enrJl02New = BeanUtil.fillBeanWithMapIgnoreCase(hlmxbd, 
							new EnrJl02(), true);	
					enrJl02New.setMxbh(redisFactory.getTableKey(TableName.DB_NAME, TableName.ENR_JL02));
					enrJl02Dao.insert(enrJl02New);
				}
			}
		} catch (Exception e) {
			logger.error("护理记录模块，进行保存操作失败", e);
			throw BaseException.create("ERROR_DCTWORK_ENRJL01_0004");
		}

	}
	
	/**
	 * 护理记录单删除
	 * @Title: deleteEnrjl01
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlbh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void deleteEnrjl01(Integer jlbh){
		long count = enrJl01Dao.queryEffectiveCount(jlbh);
		if (count < 1) {
			throw BaseException.create("errorCode.ERROR_DCTWORK_ENRJL01_0006");
		} else {
			enrJl01Dao.updateRecordStatusByJlbh(jlbh);
		}	
	}
	

}
