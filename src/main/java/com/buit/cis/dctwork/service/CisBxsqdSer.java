package com.buit.cis.dctwork.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buit.system.utill.ObjectToTypes;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.cis.dctwork.dao.CisBxsqdDao;
import com.buit.cis.dctwork.dao.CisBxxypzDao;
import com.buit.cis.dctwork.dao.CisHzyzDao;
import com.buit.cis.dctwork.model.CisBxsqd;
import com.buit.cis.dctwork.model.CisBxxypz;
import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.request.CisBxsqdReq;
import com.buit.cis.dctwork.request.CisBxxypzReq;
import com.buit.cis.dctwork.response.CisBxsqdQueryResp;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.system.response.DicSypcModel;
import com.buit.system.service.DicSypcService;
import com.buit.commons.SysUser;
import com.buit.utill.RedisFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 备血申请单<br>
 * @author GONGFANGZHOU
 */
@Service
public class CisBxsqdSer extends BaseManagerImp<CisBxsqd,String> {
    
    static final Logger logger = LoggerFactory.getLogger(CisBxsqdSer.class);
    
    @Autowired
    private CisBxsqdDao cisBxsqdDao;
    
    @Override
    public CisBxsqdDao getEntityMapper(){        
        return cisBxsqdDao;
    }
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Autowired
    private CisBxxypzDao cisBxxypzDao;
    
    @Autowired
    private CisHzyzDao cisHzyzDao;
    
    @DubboReference
    private DicSypcService dicSypcService;
    
    /**
     * 保存备血申请单
     * @Title: saveZySxSqd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisBxsqdReq
     * @param @param user    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveZySxSqd(CisBxsqdReq cisBxsqdReq, SysUser user) {
    	List<CisBxxypzReq> CisBxxypzReqList = cisBxsqdReq.getCisBxxypzReqList();
    	String jgid = ObjectToTypes.parseString(user.getHospitalId());
    	if(StrUtil.isNotBlank(cisBxsqdReq.getSqdh())) {
    		cisBxsqdDao.deleteById(cisBxsqdReq.getSqdh());
    		CisBxxypz cisBxxypz = new CisBxxypz();
    		cisBxxypz.setSqdh(cisBxsqdReq.getSqdh());
    		cisBxxypzDao.removeByEntity(cisBxxypz);
    		cisHzyzDao.deleteOrdersBySqid(ObjectToTypes.parseInt(cisBxsqdReq.getSqdh()), 2);
    	}
    	cisBxsqdReq.setBrlx("2");
    	cisBxsqdReq.setYljgd(jgid);
    	String sqdh = ObjectToTypes.parseString(redisFactory.getTableKey(TableName.DB_NAME, TableName.CISBXSQD));
    	cisBxsqdReq.setSqdh(sqdh);
    	cisBxsqdDao.insert(cisBxsqdReq);
    	// 查询st对应的执行时间
		DicSypcModel dicSypc  = dicSypcService.getById("st");
		String zxsj = "";
		if (dicSypc != null && StrUtil.isNotBlank(dicSypc.getZxsj())) {
			zxsj = dicSypc.getZxsj();
		}
		int yzpx = 0; 
    	for(CisBxxypzReq cisBxxypzReq : CisBxxypzReqList) {
    		cisBxxypzReq.setYljgd(jgid);
    		cisBxxypzReq.setSqdh(sqdh);
    		cisBxxypzReq.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CISBXXYPZ));
    		cisBxxypzDao.insert(cisBxxypzReq);
    		
    		CisHzyz cisHzyz = new CisHzyz();
    		cisHzyz.setBrks(ObjectToTypes.parseInt(cisBxsqdReq.getKsdm()));
    		cisHzyz.setBrbq(ObjectToTypes.parseInt(cisBxsqdReq.getBqdm()));
    		cisHzyz.setBrch(cisBxsqdReq.getBrch());
    		cisHzyz.setZfyp(0);
    		cisHzyz.setYpcd(0);
    		cisHzyz.setXmlx(4);
    		cisHzyz.setYplx(0);
    		cisHzyz.setYcsl(cisBxxypzReq.getSl());
    		cisHzyz.setMrcs(1);
    		cisHzyz.setMzcs(0);
    		cisHzyz.setJfbz(3);
    		cisHzyz.setYpyf(0);
    		cisHzyz.setSrcs(1);
    		cisHzyz.setYfdw(cisBxxypzReq.getDw());
    		cisHzyz.setCzgh(ObjectToTypes.parseString(user.getUserId()));
    		cisHzyz.setYzzh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ + ".yzzh"));
    		cisHzyz.setKssj(DateUtil.date().toTimestamp());
    		cisHzyz.setSrks(ObjectToTypes.parseInt(cisBxsqdReq.getKsdm()));
    		cisHzyz.setSqid(ObjectToTypes.parseInt(sqdh));
    		cisHzyz.setJgid(user.getHospitalId());
    		cisHzyz.setSybz(0);
    		cisHzyz.setZfpb(0);
    		cisHzyz.setYjzx(0);
    		cisHzyz.setYfsb(0);
    		cisHzyz.setYsbz(1);
    		cisHzyz.setYstj(1);
    		cisHzyz.setZfbz(0);
    		cisHzyz.setSfjg(0);
    		cisHzyz.setFhbz(0);
    		cisHzyz.setTzfhbz(0);
    		cisHzyz.setPspb(0);
    		cisHzyz.setYzpb(0);
    		cisHzyz.setLsbz(0);
    		cisHzyz.setYepb(0);
    		cisHzyz.setFysx(0);
    		cisHzyz.setTpn(0);
    		cisHzyz.setLsyz(1);
    		cisHzyz.setCfts(0);
    		cisHzyz.setYpzs(1);
    		cisHzyz.setJz(1);
    		cisHzyz.setYpdj(cisBxxypzReq.getDj());
    		cisHzyz.setYsgh(ObjectToTypes.parseString(user.getUserId()));
    		cisHzyz.setTzys(ObjectToTypes.parseString(user.getUserId()));
    		cisHzyz.setTzsj(DateUtil.date().toTimestamp());
    		cisHzyz.setSypc("st");
    		cisHzyz.setYzzxsj(zxsj);
    		cisHzyz.setZyh(ObjectToTypes.parseInt(cisBxsqdReq.getZyh()));
    		cisHzyz.setYcjl(new BigDecimal(0));
    		cisHzyz.setYpxh(ObjectToTypes.parseInt(cisBxxypzReq.getSxpzdm()));
    		cisHzyz.setYjxh(ObjectToTypes.parseInt(cisBxxypzReq.getSxpzdm()));
    		cisHzyz.setYzmc(cisBxxypzReq.getSxpzmc());
    		cisHzyz.setYzlx(2);
    		cisHzyz.setYzpx(++yzpx);
    		cisHzyz.setZxks(ObjectToTypes.parseInt(cisBxsqdReq.getKsdm()));
			cisHzyz.setJlxh(redisFactory.getTableKey(TableName.DB_NAME, TableName.CIS_HZYZ));
			cisHzyzDao.insert(cisHzyz);
    	}
		return sqdh;
    	
    }
    
    /**
     * 备血申请单跳转查询
     * @Title: queryZySxSqdInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param sqdh
     * @param @param user
     * @param @return    设定文件
     * @return CisBxsqdQueryResp    返回类型
     * @author 龚方舟
     * @throws
     */
    public CisBxsqdQueryResp queryZySxSqdInfo(Integer zyh, Integer sqdh, SysUser user) {
    	CisBxsqdQueryResp cisBxsqdQueryResp = new CisBxsqdQueryResp();
    	String jgid = ObjectToTypes.parseString(user.getHospitalId());
    	//备血申请单
    	CisBxsqd cisBxsqd = new CisBxsqd();
    	cisBxsqd.setZyh(ObjectToTypes.parseString(zyh));
    	cisBxsqd.setSqdh(ObjectToTypes.parseString(sqdh));
    	cisBxsqd.setYljgd(jgid);
    	List<CisBxsqd> cisBxsqdList = cisBxsqdDao.findByEntity(cisBxsqd);
    	if(CollectionUtil.isNotEmpty(cisBxsqdList)) {
    		cisBxsqd = cisBxsqdList.get(0);
    		cisBxsqdQueryResp = BeanUtil.toBean(cisBxsqd, CisBxsqdQueryResp.class);
    	}
    	//血液品种
    	CisBxxypz cisBxxypz = new CisBxxypz();
    	cisBxxypz.setSqdh(ObjectToTypes.parseString(sqdh));
    	cisBxxypz.setYljgd(jgid);
    	List<CisBxxypz> cisBxxypzList = cisBxxypzDao.findByEntity(cisBxxypz);
    	if(CollectionUtil.isNotEmpty(cisBxxypzList)) {
    		cisBxsqdQueryResp.setCisBxxypzList(cisBxxypzList);
    	}
    	return cisBxsqdQueryResp;	
    }
    
}
