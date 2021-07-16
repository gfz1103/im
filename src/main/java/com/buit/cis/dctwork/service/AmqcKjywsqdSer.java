package com.buit.cis.dctwork.service;


import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.commons.SysUser;
import com.buit.utill.RedisFactory;
import com.buit.cis.dctwork.dao.AmqcKjywsqdDao;
import com.buit.cis.dctwork.enums.OperationTypeEnum;
import com.buit.cis.dctwork.model.AmqcKjywsqd;
import com.buit.cis.dctwork.request.AmqcMedicineReq;
import com.buit.cis.dctwork.request.AmqcKjywsqdReq;
import com.buit.cis.dctwork.response.AmqcMedicineResp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@SuppressWarnings("ALL")
@Service
public class AmqcKjywsqdSer extends BaseManagerImp<AmqcKjywsqd,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(AmqcKjywsqdSer.class);
    
    @Autowired
    private AmqcKjywsqdDao amqcKjywsqdDao;
    
    @Override
    public AmqcKjywsqdDao getEntityMapper(){        
        return amqcKjywsqdDao;
    }
    
    @Autowired
	private RedisFactory redisFactory;
    
    /**
    * @Title: saveOrupdateAntibacterials
    * @Description: TODO 保存修改(可以直接提交)
    * @param @param amqcKjywsqdReq    设定文件
    * @return void    返回类型
    * @author 龚方舟
    * @throws
     */
    public void saveOrupdateAntibacterials(AmqcKjywsqdReq amqcKjywsqdReq) {
    	String opStatus = amqcKjywsqdReq.getOpStatus();
    	if(OperationTypeEnum.create.getCode().equals(opStatus)) {
    		amqcKjywsqdReq.setSqdh(redisFactory.getTableKey(TableName.DB_NAME, TableName.AMQC_KJYWSQD));
    		amqcKjywsqdDao.insert(amqcKjywsqdReq);
    	}else{
    		amqcKjywsqdDao.update(amqcKjywsqdReq);
    	}
    }
    
    /**
    * @Title: queryAmqcAndMedicine
    * @Description: TODO 抗菌药物审批列表
    * @param @param amqcMedicineReq
    * @param @param user
    * @param @return    设定文件
    * @return List<AmqcMedicineResp>    返回类型
    * @author 龚方舟
    * @throws
     */
    public List<AmqcMedicineResp> queryAmqcAndMedicine(AmqcMedicineReq amqcMedicineReq, SysUser user) {
		 amqcMedicineReq.setJgid(user.getHospitalId());
		 amqcMedicineReq.setJzlx(1);
		 amqcMedicineReq.setZfbz(0);
		 return amqcKjywsqdDao.queryAmqcAndMedicineInfo(amqcMedicineReq);
    }
    
}
