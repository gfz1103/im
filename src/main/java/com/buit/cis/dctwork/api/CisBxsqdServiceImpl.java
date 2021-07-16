package com.buit.cis.dctwork.api;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.cis.dctwork.dao.CisBxsqdDao;
import com.buit.cis.dctwork.model.CisBxsqd;
import com.buit.cis.dctwork.service.CisBxsqdService;
import com.buit.system.utill.ObjectToTypes;

@DubboService(timeout = 10000)
public class CisBxsqdServiceImpl implements CisBxsqdService{

	@Autowired
	private CisBxsqdDao cisBxsqdDao;
	
	@Override
	public List<CisBxsqd> queryBxsqdByZyh(Integer zyh, Integer jgid) {
		CisBxsqd cisBxsqd = new CisBxsqd();
		cisBxsqd.setZyh(ObjectToTypes.parseString(zyh));
		cisBxsqd.setYljgd(ObjectToTypes.parseString(jgid));
		return cisBxsqdDao.findByEntity(cisBxsqd);
	}

}
