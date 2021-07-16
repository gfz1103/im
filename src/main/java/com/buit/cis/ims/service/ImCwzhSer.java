package com.buit.cis.ims.service;


import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.cis.ims.dao.ImCwszDao;
import com.buit.cis.ims.dao.ImCwzhDao;
import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImCwzh;
import com.buit.cis.ims.request.ImCwzhReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Service
public class ImCwzhSer extends BaseManagerImp<ImCwzh,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ImCwzhSer.class);
    
    @Autowired
    private ImCwzhDao imCwzhDao;
    
    @Override
    public ImCwzhDao getEntityMapper(){        
        return imCwzhDao;
    }
    
    @Autowired
    private ImCwszDao imCwszDao;

    /**
     * 
     * @Title: invalidByCwzh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cwzh
     * @param @param zfpb
     * @param @param jgid    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
	public void invalidByCwzh(Integer cwzh, Integer zfpb, Integer jgid) {
//		if(zfpb == 1) {
//			ImCwsz imCwsz = new ImCwsz();
//			imCwsz.setJgid(jgid);
//			imCwsz.setCwzh(cwzh);
//			long count = imCwszDao.findByEntityCount(imCwsz);
//			if(count > 0) {
//				throw BaseException.create("ERROR_NURSEWORK_CWZH_0001");
//			}
//		}
		imCwzhDao.invalidByCwzh(cwzh, zfpb, jgid);
	}

	/**
	 * 删除床位组号
	 * @Title: deleteByCwzh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cwzh
	 * @param @param jgid    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void deleteByCwzh(Integer cwzh, Integer jgid) {
		ImCwsz imCwsz = new ImCwsz();
		imCwsz.setJgid(jgid);
		imCwsz.setCwzh(cwzh);
		long count = imCwszDao.findByEntityCount(imCwsz);
		if(count > 0) {
			throw BaseException.create("ERROR_NURSEWORK_CWZH_0001");
		}
		imCwzhDao.deleteById(cwzh);
	}

	/**
	 * 修改
	 * @Title: updateCwzh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imcwzh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public void updateCwzh(ImCwzhReq imcwzh) {
		ImCwsz imCwsz = new ImCwsz();
		imCwsz.setJgid(imcwzh.getJgid());
		imCwsz.setCwzh(imcwzh.getCwzh());
		long count = imCwszDao.findByEntityCount(imCwsz);
		if(count > 0) {
			throw BaseException.create("ERROR_NURSEWORK_CWZH_0001");
		}
		imCwzhDao.update(imcwzh);
	}
    
}
