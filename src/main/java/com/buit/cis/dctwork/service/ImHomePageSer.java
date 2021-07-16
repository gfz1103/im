package com.buit.cis.dctwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.CisBxsqdDao;
import com.buit.cis.dctwork.request.CisBxsqdHomeReq;
import com.buit.cis.ims.dao.ImHzryDao;
import com.buit.cis.ims.dao.ImZkjlDao;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.request.ImHzryCriticalHomeReq;
import com.buit.cis.ims.request.ImHzryHomeReq;
import com.buit.cis.ims.request.ImZkjlHomeReq;
import com.buit.cis.ims.response.ImHzryHandledResp;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.SysUser;
import com.buit.system.utill.ObjectToTypes;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class ImHomePageSer extends BaseManagerImp<ImHzry,Integer> {

static final Logger logger = LoggerFactory.getLogger(ImHomePageSer.class);
    
    @Autowired
    private ImHzryDao imHzryDao;
    
    @Override
    public ImHzryDao getEntityMapper(){        
        return imHzryDao;
    }
    
    @Autowired
    private ImZkjlDao imZkjlDao;
    
    @Autowired
    private CisBxsqdDao cisBxsqdDao;
    
    /**
     * 查询首页已处理未处理数量
     * @Title: queryHandledCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param bqdm
     * @param @param ksdm
     * @param @param user
     * @param @return    设定文件
     * @return ImHzryHandledResp    返回类型
     * @author 龚方舟
     * @throws
     */
    public ImHzryHandledResp queryHandledCount(Integer bqdm, Integer ksdm, SysUser user) {
    	ImHzryHandledResp imHzryHandledResp = new ImHzryHandledResp(); 
    	long wclCount = 0;
    	long yclCount = 0;
    	if(StrUtil.isBlankIfStr(ksdm)) {
    		//待出院未处理
        	ImHzryHomeReq imHzryHomeReq = new ImHzryHomeReq();
        	imHzryHomeReq.setJgid(user.getHospitalId());
        	imHzryHomeReq.setZt(2);
        	imHzryHomeReq.setBqdm(bqdm);
        	long cyCount = PageHelper.count(() -> imHzryDao.queryImHzryHomePage(imHzryHomeReq));
        	//待入院未出理
        	imHzryHomeReq.setZt(4);
        	long ryCount = PageHelper.count(() -> imHzryDao.queryImHzryHomePage(imHzryHomeReq));  	
        	//待转出
        	ImZkjlHomeReq imZkjlHomeReq = new ImZkjlHomeReq();
        	imZkjlHomeReq.setJgid(user.getHospitalId());
        	imZkjlHomeReq.setZt(1);
        	imZkjlHomeReq.setBqdm(bqdm);
        	long zkCount = PageHelper.count(() -> imZkjlDao.queryZkjlHomePage(imZkjlHomeReq));       	
        	wclCount = cyCount + ryCount + zkCount;	
        	//待转入
        	imZkjlHomeReq.setZt(2);
        	zkCount = PageHelper.count(() -> imZkjlDao.queryZkjlHomePage(imZkjlHomeReq));
        	
        	//危机值未通知处理
        	ImHzryCriticalHomeReq imHzryCriticalHomeReq = new ImHzryCriticalHomeReq();
        	imHzryCriticalHomeReq.setJgid(user.getHospitalId());
        	imHzryCriticalHomeReq.setBqdm(bqdm);
        	imHzryCriticalHomeReq.setZt(3);
        	long wjzCount = PageHelper.count(() -> imHzryDao.queryCriticalHomePage(imHzryCriticalHomeReq));
        	wclCount = wclCount + zkCount + wjzCount;
        	
        	//出院已处理
        	imHzryHomeReq.setZt(1);
        	cyCount = PageHelper.count(() -> imHzryDao.queryImHzryHomePage(imHzryHomeReq));
        	//入院已处理
        	imHzryHomeReq.setZt(3);
        	ryCount = PageHelper.count(() -> imHzryDao.queryImHzryHomePage(imHzryHomeReq));
        	yclCount = cyCount + ryCount;
        	//已转出
        	imZkjlHomeReq.setZt(3);
        	zkCount = PageHelper.count(() -> imZkjlDao.queryZkjlHomePage(imZkjlHomeReq));
        	yclCount = yclCount + zkCount;
        	//已转入
        	imZkjlHomeReq.setZt(4);
        	zkCount = PageHelper.count(() -> imZkjlDao.queryZkjlHomePage(imZkjlHomeReq));
        	//危机值已通知处理
        	imHzryCriticalHomeReq.setZt(4);
        	wjzCount = PageHelper.count(() -> imHzryDao.queryCriticalHomePage(imHzryCriticalHomeReq));
        	yclCount = yclCount + zkCount + wjzCount;
    	}else {
    		//备血未处理
        	CisBxsqdHomeReq cisBxsqdHomeReq = new CisBxsqdHomeReq();
        	cisBxsqdHomeReq.setYljgd(ObjectToTypes.parseString(user.getHospitalId()));
        	cisBxsqdHomeReq.setZt("0");
        	cisBxsqdHomeReq.setKsdm(ksdm);
        	long bxCount = PageHelper.count(() -> cisBxsqdDao.queryBloodHomePage(cisBxsqdHomeReq));
        	//会诊未处理
        	long hzCount = PageHelper.count(() -> imHzryDao.queryZyysConsultedHomePage(bqdm, ksdm, user.getHospitalId(), 
        			user.getUserId(), 2));
        	//问题医嘱未处理
        	long wtCount = PageHelper.count(() -> imHzryDao.queryWardAdviceHomePage(bqdm, ksdm, null));
        	//危机值未处理
        	ImHzryCriticalHomeReq imHzryCriticalHomeReq = new ImHzryCriticalHomeReq();
        	imHzryCriticalHomeReq.setJgid(user.getHospitalId());
        	imHzryCriticalHomeReq.setKsdm(ksdm);
        	imHzryCriticalHomeReq.setZt(1);
        	long wjzCount = PageHelper.count(() -> imHzryDao.queryCriticalHomePage(imHzryCriticalHomeReq));
        	wclCount = bxCount + hzCount + wtCount + wjzCount;
        	
        	//备血已处理
        	cisBxsqdHomeReq.setZt("1");
        	bxCount = PageHelper.count(() -> cisBxsqdDao.queryBloodHomePage(cisBxsqdHomeReq));
        	//会诊已处理
        	hzCount = PageHelper.count(() -> imHzryDao.queryZyysConsultedHomePage(bqdm, ksdm, user.getHospitalId(), 
        			user.getUserId(), 1));
        	//问题医嘱已处理
        	wtCount = PageHelper.count(() -> imHzryDao.queryWardAdviceHomePage(bqdm, ksdm, 1));
        	//危机值已处理
        	imHzryCriticalHomeReq.setZt(2);
        	wjzCount = PageHelper.count(() -> imHzryDao.queryCriticalHomePage(imHzryCriticalHomeReq));
        	yclCount = bxCount + hzCount + wtCount + wjzCount;
    	}
    	imHzryHandledResp.setWclCount(ObjectToTypes.parseInt(wclCount));
    	imHzryHandledResp.setYclCount(ObjectToTypes.parseInt(yclCount));
    	return imHzryHandledResp;
    }

}
