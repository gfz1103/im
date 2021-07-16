package com.buit.cis.nurse.service;


import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.nurse.dao.NisHljhdDao;
import com.buit.cis.nurse.dao.NisHljhdcsDao;
import com.buit.cis.nurse.model.NisHljhd;
import com.buit.cis.nurse.model.NisHljhdcs;
import com.buit.cis.nurse.request.NisHljhdReq;
import com.buit.cis.nurse.response.NisHljhdResp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 护理计划单主表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljhdSer extends BaseManagerImp<NisHljhd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljhdSer.class);
    
    @Autowired
    private NisHljhdDao nisHljhdDao;
    
    @Override
    public NisHljhdDao getEntityMapper(){        
        return nisHljhdDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired 
    private NisHljhdcsDao nisHljhdcsDao;
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @DubboReference
    private DicKszdOutSer dicKszdOutSer;
    

    /**
     * 
     * @Title: saveOrUpdateNisHljhd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @param hospitalId    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateNisHljhd(List<NisHljhdReq> list, Integer hospitalId) {
		for(NisHljhdReq req : list) {
			req.setJgid(hospitalId);
			if(StrUtil.isBlankIfStr(req.getJlxh())) {
				req.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJHD));
				nisHljhdDao.insert(BeanUtil.toBean(req, NisHljhd.class));
				List<NisHljhdcs> hlcsList = req.getHlcsList();
				for(NisHljhdcs hlcs : hlcsList) {
					hlcs.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJHDCS));
					hlcs.setZxjlxh(req.getJlxh());
					nisHljhdcsDao.insert(hlcs);
				}
			}else {
				nisHljhdDao.update(BeanUtil.toBean(req, NisHljhd.class));
				nisHljhdcsDao.deleteByZxjlxh(req.getJlxh());
				List<NisHljhdcs> hlcsList = req.getHlcsList();
				for(NisHljhdcs hlcs : hlcsList) {
					hlcs.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.NIS_HLJHDCS));
					hlcs.setZxjlxh(req.getJlxh());
					nisHljhdcsDao.insert(hlcs);
				}
			}
		}
	}
    
    /**
     * 护理计划单打印信息
     * @Title: nursingPlanPrint
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param queryDate
     * @param @param hospitalId
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
	public Map<String, Object> nursingPlanPrint(Integer zyh, String queryDate, Integer hospitalId) {
		Map<String, Object> responseMap = new HashMap<String, Object>(16);
		ImHzry hzry = imHzryDao.getById(zyh);
		Map<String, Object> hzMap = BUHISUtil.caseInsensitiveMap(hzry);	
		hzMap.put("csrq", DateUtil.format(hzry.getCsny(), DatePattern.CHINESE_DATE_PATTERN));
		hzMap.put("ksmc", dicKszdOutSer.getNameById(hzry.getBrks()));
		hzMap.put("bqmc", dicKszdOutSer.getNameById(hzry.getBrbq()));
		responseMap.put("resMap", hzMap);
		List<NisHljhdResp> list = nisHljhdDao.queryHljhdByDate(zyh,
                queryDate, hospitalId);
    	List<Map<String, Object>> listMap = BUHISUtil.ListObjToMap(list);
    	if(CollectionUtils.isNotEmpty(listMap)) {
    		for(Map<String, Object> map : listMap) {
    			StringBuffer hlcsStr = new StringBuffer(); 
    			NisHljhdcs hlcs = new NisHljhdcs();
            	hlcs.setZxjlxh(ObjectToTypes.parseInt(map.get("jlxh")));
            	List<NisHljhdcs> hlcsList = nisHljhdcsDao.findByEntity(hlcs);
            	for(NisHljhdcs jhdcs : hlcsList) {
            		hlcsStr.append(jhdcs.getHlcs()).append("\r\n");
            	}
            	if(hlcsStr.length() > 0) {
    				map.put("hlcs", hlcsStr.toString().substring(0, hlcsStr.length() - 2));
    			}
    		}
    	}
    	responseMap.put("resList", listMap);
		return responseMap;
	}
    
}
