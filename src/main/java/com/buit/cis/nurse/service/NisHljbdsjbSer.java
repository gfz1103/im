package com.buit.cis.nurse.service;


import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImZkjlDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.nurse.dao.NisHljbdsjbDao;
import com.buit.cis.nurse.model.NisHljbdsjb;
import com.buit.cis.nurse.response.NisHljbdsjbResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.system.utill.ObjectToTypes;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
/**
 * 护理交班单记录数据表<br>
 * @author GONGFANGZHOU
 */
@Service
public class NisHljbdsjbSer extends BaseManagerImp<NisHljbdsjb,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(NisHljbdsjbSer.class);
    
    @Autowired
    private NisHljbdsjbDao nisHljbdsjbDao;
    
    @Override
    public NisHljbdsjbDao getEntityMapper(){        
        return nisHljbdsjbDao;
    }
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Autowired
    private ImZkjlDao imZkjlDao;

    /**
     * @param  
     * 查询初始交班单数据
     * @Title: queryNisHljbdData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param bclx
     * @param @return    设定文件
     * @return Object    返回类型
     * @author 龚方舟
     * @throws
     */
	public NisHljbdsjbResp queryNisHljbdData(Timestamp jbsj, Integer bclx, Integer bqdm, Integer jgid) {
		NisHljbdsjbResp resp = new NisHljbdsjbResp();
		String nowtime = DateUtil.format(jbsj, DatePattern.NORM_DATE_PATTERN);
		String kssj = nowtime + " 08:00:00";
		String jssj = nowtime + " 16:00:00";
		if(bclx == 2) {
			kssj = nowtime + " 16:00:00" ;
			jssj = DateUtil.format(DateUtil.offsetDay(jbsj, 1),  DatePattern.NORM_DATE_PATTERN) + " 00:00:00";
		}else if(bclx == 3) {
			kssj = DateUtil.format(DateUtil.offsetDay(jbsj, 1),  DatePattern.NORM_DATE_PATTERN) + " 00:00:00";
			jssj = DateUtil.format(DateUtil.offsetDay(jbsj, 1),  DatePattern.NORM_DATE_PATTERN) + " 08:00:00";
		}
		//出院
		long cyCount = imHzryDao.queryInAndOutCount(jgid, 1, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setCys(ObjectToTypes.parseInt(cyCount));
		//入院
		long ryCount = imHzryDao.queryInAndOutCount(jgid, 2, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setRys(ObjectToTypes.parseInt(ryCount));
		//病危、病重
		long bwCount = imHzryDao.queryInAndOutCount(jgid, 3, null, null, bqdm);
		resp.setBwbzs(ObjectToTypes.parseInt(bwCount));
		//一级护理
		long hlCount = imHzryDao.queryInAndOutCount(jgid, 4, null, null, bqdm);
		resp.setYjhls(ObjectToTypes.parseInt(hlCount));
		//转出
		long zcCount = imZkjlDao.queryTransferInAndOutCount(jgid, 1, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setZcs(ObjectToTypes.parseInt(zcCount));
		//转入
		long zrCount = imZkjlDao.queryTransferInAndOutCount(jgid, 2, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setZrs(ObjectToTypes.parseInt(zrCount));
		//预手术
		long yssCount = imHzryDao.queryPreOperationCount(jgid, 1, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setYssy(ObjectToTypes.parseInt(yssCount));
		//手术
		long ssCount = imHzryDao.queryPreOperationCount(jgid, 2, DateUtil.parse(kssj).toTimestamp(), 
				DateUtil.parse(jssj).toTimestamp(), bqdm);
		resp.setSss(ObjectToTypes.parseInt(ssCount));
		long swCount = 0L;
		resp.setSws(ObjectToTypes.parseInt(swCount));
		return resp;
	}
    
}
