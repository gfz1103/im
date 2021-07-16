package com.buit.cis.nurse.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.buit.cis.nurse.dao.NisHljbdDao;
import com.buit.cis.nurse.dao.NisHljbdmxbDao;
import com.buit.cis.nurse.dao.NisHljbdsjbDao;
import com.buit.cis.nurse.model.NisHljbd;
import com.buit.cis.nurse.model.NisHljbdmxb;
import com.buit.cis.nurse.model.NisHljbdsjb;
import com.buit.cis.nurse.request.NisHljbdReq;
import com.buit.cis.nurse.response.NisHljbdResp;
import com.buit.cis.nurse.response.NisHljbdmxbResp;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.utill.BytesUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
/**
 * 护理交班单主表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljbdSer extends BaseManagerImp<NisHljbd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdSer.class);
    
    @Autowired
    private NisHljbdDao nisHljbdDao;
    
    @Override
    public NisHljbdDao getEntityMapper(){        
        return nisHljbdDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private NisHljbdsjbDao nisHljbdsjbDao;
    
    @Autowired
    private NisHljbdmxbDao nisHljbdmxbDao;

    /**
     * 新增或保存交班单
     * @Title: saveOrUpdateHljbd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisHljbdReq
     * @param @param hospitalId    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
	public void saveOrUpdateHljbd(NisHljbdReq nisHljbdReq, Integer hospitalId) {
		NisHljbd nisHljbd = BeanUtil.toBean(nisHljbdReq, NisHljbd.class);
		nisHljbd.setJgid(hospitalId);
		if(StrUtil.isBlankIfStr(nisHljbd.getJlxh())) {
			long count = nisHljbdDao.queryIsExistSameTimeInfo(hospitalId, nisHljbd.getHlzh(),
					nisHljbd.getJbsj(), nisHljbd.getBclx());
			if(count > 0) {
				throw BaseException.create("ERROR_NISHLJBD_HLJB_0001");
			}
			nisHljbd.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJBD));
			nisHljbdDao.insert(nisHljbd);
		}else {
			nisHljbdDao.update(nisHljbd);
		}
	}
	
	/**
	 * 根据记录序号查询交班单信息
	 * @Title: getByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @param hospitalId
	 * @param @return    设定文件
	 * @return NisHljbdResp    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public NisHljbdResp getByJlxh(Integer jlxh, Integer hospitalId) {
		NisHljbdResp resp = new NisHljbdResp();
		NisHljbdsjb nisHljbdsjb = new NisHljbdsjb();
		nisHljbdsjb.setJbdjlxh(jlxh);
		List<NisHljbdsjb> list = nisHljbdsjbDao.findByEntity(nisHljbdsjb);
		if(CollectionUtils.isNotEmpty(list)) {
			resp.setNisHljbdsjb(list.get(0));
		}
		List<NisHljbdmxbResp> mxList = nisHljbdmxbDao.queryDetailByJbdJlxh(jlxh, null);
		if(CollectionUtils.isNotEmpty(mxList)) {
			resp.setMxList(mxList);
		}
		return resp;
	}

	/**
	 * 根据记录序号删除交班单信息
	 * @Title: deleteByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteByJlxh(Integer jlxh) {
		nisHljbdDao.deleteById(jlxh);
		NisHljbdsjb nisHljbdsjb = new NisHljbdsjb();
		nisHljbdsjb.setJbdjlxh(jlxh);
		nisHljbdsjbDao.removeByEntity(nisHljbdsjb);
		NisHljbdmxb nisHljbdmxb = new NisHljbdmxb();
		nisHljbdmxb.setJbdjlxh(jlxh);
		nisHljbdmxbDao.removeByEntity(nisHljbdmxb);
	}

	/**
	 * 查询打印Parameters
	 * @Title: getParameters
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param reqStr
	 * @param @return    设定文件
	 * @return Map<String,Object>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public Map<String, Object> getParameters(String reqStr) {
		List<Integer> list = JSONArray.parseArray(reqStr, Integer.class);
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		for(int i = 0; i < list.size(); i ++) {
			Integer jlxh = list.get(i);
			NisHljbd jbd = nisHljbdDao.getById(jlxh);
			if(i == 0) {
				responseMap.put("year", DateUtil.year(jbd.getJbsj()));
				responseMap.put("month", DateUtil.month(jbd.getJbsj()) + 1);
				responseMap.put("day", DateUtil.dayOfMonth(jbd.getJbsj()));
			}
			NisHljbdsjb nisHljbdsjb = new NisHljbdsjb();
			nisHljbdsjb.setJbdjlxh(jlxh);
			List<NisHljbdsjb> sjList = nisHljbdsjbDao.findByEntity(nisHljbdsjb);
			if(CollectionUtils.isNotEmpty(sjList)) {
				NisHljbdsjb sjb = sjList.get(0);
				if(jbd.getBclx() == 1) {
					responseMap.put("bcy", sjb.getCys());
					responseMap.put("bry", sjb.getRys());
					responseMap.put("byss", sjb.getYssy());
					responseMap.put("bzc", sjb.getZcs());
					responseMap.put("bzr", sjb.getZrs());
					responseMap.put("bbw", sjb.getBwbzs());
					responseMap.put("bsw", sjb.getSws());
					responseMap.put("bss", sjb.getSss());
					responseMap.put("byj", sjb.getYjhls());
					responseMap.put("bsum", sjb.getCys() + sjb.getRys() + sjb.getYssy() + sjb.getZcs() +  sjb.getZrs() +
							sjb.getBwbzs() + sjb.getSws() + sjb.getSss() + sjb.getYjhls());
				}else if(jbd.getBclx() == 2) {
					responseMap.put("zcy", sjb.getCys());
					responseMap.put("zry", sjb.getRys());
					responseMap.put("zyss", sjb.getYssy());
					responseMap.put("zzc", sjb.getZcs());
					responseMap.put("zzr", sjb.getZrs());
					responseMap.put("zbw", sjb.getBwbzs());
					responseMap.put("zsw", sjb.getSws());
					responseMap.put("zss", sjb.getSss());
					responseMap.put("zyj", sjb.getYjhls());
					responseMap.put("zsum", sjb.getCys() + sjb.getRys() + sjb.getYssy() + sjb.getZcs() +  sjb.getZrs() +
							sjb.getBwbzs() + sjb.getSws() + sjb.getSss() + sjb.getYjhls());
				}else if(jbd.getBclx() == 3) {
					responseMap.put("ycy", sjb.getCys());
					responseMap.put("yry", sjb.getRys());
					responseMap.put("yyss", sjb.getYssy());
					responseMap.put("yzc", sjb.getZcs());
					responseMap.put("yzr", sjb.getZrs());
					responseMap.put("ybw", sjb.getBwbzs());
					responseMap.put("ysw", sjb.getSws());
					responseMap.put("yss", sjb.getSss());
					responseMap.put("yyj", sjb.getYjhls());	
					responseMap.put("ysum", sjb.getCys() + sjb.getRys() + sjb.getYssy() + sjb.getZcs() +  sjb.getZrs() +
							sjb.getBwbzs() + sjb.getSws() + sjb.getSss() + sjb.getYjhls());
				}
			}
		}
		return responseMap;
	}

	/**
	 * 查询打印Fields
	 * @Title: getFields
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param reqStr
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public List<Map<String, Object>> getFields(String reqStr) throws Exception {
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		List<Integer> list = JSONArray.parseArray(reqStr, Integer.class);
		List<Integer> zyhList = nisHljbdmxbDao.queryZyhByJlxh(list);
		for(Integer zyh : zyhList) {
			//基本信息集合
			List<String> jbList = new ArrayList<String>();
			//白班集合
			List<String> bbList = new ArrayList<String>();
			//中班集合
			List<String> zbList = new ArrayList<String>();
			//夜班集合
			List<String> ybList = new ArrayList<String>();
			List<NisHljbdmxbResp> mxList = nisHljbdmxbDao.queryDetailByJbdJlxh(null, zyh);
			if(CollectionUtils.isNotEmpty(mxList)) {
				for(int i = 0; i < mxList.size(); i++) {
					NisHljbdmxbResp mxb = mxList.get(i);
					if(i == 0) {
						jbList.add(mxb.getBrch() + " " + mxb.getBrxm());
						jbList.add(mxb.getCsny().toString());
						jbList.addAll(BytesUtil.getListStringBytes(mxb.getZdmc(), 10));
					}
					if(mxb.getBclx() == 1) {
						bbList.addAll(BytesUtil.getListStringBytes(mxb.getSbarmc() + ":" + mxb.getJbnr(), 40));
					}
					if(mxb.getBclx() == 2) {
						zbList.addAll(BytesUtil.getListStringBytes(mxb.getSbarmc() + ":" + mxb.getJbnr(), 40));
					}
					if(mxb.getBclx() == 3) {
						ybList.addAll(BytesUtil.getListStringBytes(mxb.getSbarmc() + ":" + mxb.getJbnr(), 40));
					}
				}
			}
			
			int biggest = Math.max(jbList.size(), bbList.size());
			biggest = Math.max(biggest, zbList.size());
			biggest = Math.max(biggest, ybList.size());
			for(int j = 0; j < biggest; j++) {
				Map<String, Object> map = new HashMap<String, Object>(16);
				if(jbList.size() > j) {
					map.put("jbxx", jbList.get(j).trim());
				}
				
				if(bbList.size() > j) {
					map.put("bbxx", bbList.get(j).trim());
				}
				
				if(zbList.size() > j) {
					map.put("zbxx", zbList.get(j).trim());
				}
				
				if(ybList.size() > j) {
					map.put("ybxx", ybList.get(j).trim());
				}
				resList.add(map);
			}
		}
		//补足空行
		int divNum = resList.size() % 17;
    	boolean flag = divNum == 0;
    	if(!flag) {
    		for(int i = 0; i < 17 - divNum; i++ ) {
        		Map<String, Object> map = new HashMap<String, Object>(16);
        		map.put("jbxx", "");
        		resList.add(map);
        	}
    	}
		return resList;
	}

	
}
