package com.buit.cis.dctwork.service;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.CisJbzmdDao;
import com.buit.cis.dctwork.model.CisJbzmd;
import com.buit.cis.ims.dao.ImRyzdDao;
import com.buit.cis.ims.request.ImRyzdReq;
import com.buit.cis.ims.response.ImRyzdResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.system.utill.ObjectToTypes;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 疾病证明单<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisJbzmdSer extends BaseManagerImp<CisJbzmd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(CisJbzmdSer.class);
    
    @Autowired
    private CisJbzmdDao cisJbzmdDao;
    
    @Override
    public CisJbzmdDao getEntityMapper(){        
        return cisJbzmdDao;
    }
    
    @Autowired
    private ImRyzdDao imRyzdDao;

    /**
     * printCisbqzmd
     * @Title: printCisbqzmd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
	public Map<String, Object> printCisbqzmd(Integer jlxh, String hospitalName) {
		Map<String, Object> map = cisJbzmdDao.queryCisJbzmdPrint(jlxh);
		
		if(map != null) {
			if(StrUtil.isNotBlank(map.get("kjrq") + "")) {		
				map.put("kjrq", DateUtil.parse(map.get("kjrq") + "", DatePattern.NORM_DATETIME_PATTERN));
			}
			ImRyzdReq imRyzdReq = new ImRyzdReq(); 
			imRyzdReq.setZyh(ObjectToTypes.parseInt(map.get("zyh")));
			List<ImRyzdResp> list = imRyzdDao.getDiagnosisByEntity(imRyzdReq);
			StringBuffer str = new StringBuffer(); 
			if(CollectionUtils.isNotEmpty(list)) {
				for(ImRyzdResp resp : list) {
					str.append(resp.getJbmc()).append("\r\n");
				}
			}
			if(str.length() > 0) {
				map.put("zdmc", str.toString().substring(0, str.length() - 2));
			}
			map.put("title", hospitalName + "疾病证明单");
		}
		return map;
	}

    
}
