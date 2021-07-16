package com.buit.cis.dctwork.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.cis.dctwork.dao.NisFyyfDao;
import com.buit.cis.dctwork.model.NisFyyf;
import com.buit.cis.dctwork.request.DrugsTypkBqyzYpSrfReq;
import com.buit.commons.BaseManagerImp;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.constans.SysXtcsCsmcCts;
import com.buit.drug.request.DrugsTypkBqyzYpSrfApiReq;
import com.buit.drug.request.DrugsTypkBqyzYpSrfPackageReq;
import com.buit.drug.response.DrugsTypkBqyzYpApiResp;
import com.buit.drug.service.DrugsTypkOutSer;
import com.buit.system.response.SysXtcs;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BUHISUtil;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 住院医生站输入法
 * @ClassName: DoctorTypeWriteSer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年10月15日 下午2:33:54
 *
 */
@Service
public class DoctorTypeWriteSer extends BaseManagerImp<NisFyyf,Long> {

    static final Logger logger = LoggerFactory.getLogger(DoctorTypeWriteSer.class);

    @Autowired
    private NisFyyfDao nisFyyfDao;
    
    @Override
	public NisFyyfDao getEntityMapper() {
		return nisFyyfDao;
	}
 
    @DubboReference
    private DrugsTypkOutSer drugsTypkOutSer;
    
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;

    
    
    /**
     * 病区医嘱药品输入法查询
     * @Title: queryBqyzYp
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param drugsTypkBqyzYpSrfReq
     * @param @param user
     * @param @return    设定文件
     * @return List<DrugsTypkBqyzYpResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    public PageInfo<DrugsTypkBqyzYpApiResp> queryBqyzYp(DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq, SysUser user,
    		PageQuery page) {
    	PageInfo<DrugsTypkBqyzYpApiResp> pageInfo = new PageInfo<DrugsTypkBqyzYpApiResp>(new ArrayList<DrugsTypkBqyzYpApiResp>());
    	DrugsTypkBqyzYpSrfApiReq ypsrfReq = new DrugsTypkBqyzYpSrfApiReq();
    	DrugsTypkBqyzYpSrfPackageReq packageReq = new DrugsTypkBqyzYpSrfPackageReq();
    	if(!"0".equals(drugsTypkBqyzYpSrfReq.getHssfqyyp())) {
            ypsrfReq.setZdlx("c.pydm");
            packageReq.setZdlx("c.pydm");
            ypsrfReq.setPydm(drugsTypkBqyzYpSrfReq.getPydm());
            packageReq.setPydm(drugsTypkBqyzYpSrfReq.getPydm());
            ypsrfReq.setPageSize(page.getPageSize());
            ypsrfReq.setPageNum(page.getPageNum());
            packageReq.setPageSize(page.getPageSize());
            packageReq.setPageNum(page.getPageNum());
            if (BUHISUtil.isContainChinese(drugsTypkBqyzYpSrfReq.getPydm())) {
                ypsrfReq.setZdlx("c.ypmc");
                packageReq.setZdlx("c.ypmc");
            }
            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLR_BZLX);
            String bzlx = sysXtcs == null ? "1" : sysXtcs.getCsz();

            ypsrfReq.setJgid(user.getHospitalId());
            drugsTypkBqyzYpSrfReq.setJgid(user.getHospitalId());
            if ("2".equals(bzlx)) {
                pageInfo = drugsTypkOutSer.queryBqyzYpInpatientWard(ypsrfReq);
			}else {
				if(StrUtil.isEmpty(ObjectToTypes.parseString(drugsTypkBqyzYpSrfReq.getYfsz()))) {
					drugsTypkBqyzYpSrfReq.setYfsz(1);
				}
				
				List<Map<String, Object>> list = nisFyyfDao.queryYfsb(drugsTypkBqyzYpSrfReq);
				List<Integer> yfsbList = new ArrayList<Integer>();
				for(Map<String, Object> map : list) {
					yfsbList.add(ObjectToTypes.parseInt(map.get("YFSB")));
				}
				
				packageReq.setYfsbList(yfsbList);
				packageReq.setJgid(user.getHospitalId());
				if(CollectionUtil.isNotEmpty(yfsbList)) {
					pageInfo = drugsTypkOutSer.queryBqyzYpDrugPackaging(packageReq);
				}
				
			}
    	}
		return pageInfo;
    }
    
    /**
     * 病区医嘱草药药品输入法查询
     * @Title: queryCisyzHerbal
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param drugsTypkBqyzYpSrfReq
     * @param @param user
     * @param @param page
     * @param @return    设定文件
     * @return PageInfo<DrugsTypkBqyzYpResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    public PageInfo<DrugsTypkBqyzYpApiResp> queryCisyzHerbal(DrugsTypkBqyzYpSrfReq drugsTypkBqyzYpSrfReq, SysUser user, PageQuery page) {
    	PageInfo<DrugsTypkBqyzYpApiResp> pageInfo = new PageInfo<DrugsTypkBqyzYpApiResp>(new ArrayList<DrugsTypkBqyzYpApiResp>());
    	
    	DrugsTypkBqyzYpSrfApiReq ypsrfReq = new DrugsTypkBqyzYpSrfApiReq();
    	DrugsTypkBqyzYpSrfPackageReq packageReq = new DrugsTypkBqyzYpSrfPackageReq();
    	if(!"0".equals(drugsTypkBqyzYpSrfReq.getHssfqyyp())) {
            ypsrfReq.setZdlx("c.pydm");
            packageReq.setZdlx("c.pydm");
            ypsrfReq.setPydm(drugsTypkBqyzYpSrfReq.getPydm());
            packageReq.setPydm(drugsTypkBqyzYpSrfReq.getPydm());
            ypsrfReq.setPageSize(page.getPageSize());
            ypsrfReq.setPageNum(page.getPageNum());
            packageReq.setPageSize(page.getPageSize());
            packageReq.setPageNum(page.getPageNum());
            if (BUHISUtil.isContainChinese(drugsTypkBqyzYpSrfReq.getPydm())) {
                ypsrfReq.setZdlx("c.ypmc");
                packageReq.setZdlx("c.ypmc");
            }
            SysXtcs sysXtcs = sysXtcsCacheSer.getByJGIdAndCsmc(user.getHospitalId(), SysXtcsCsmcCts.YZLR_BZLX);
            String bzlx = sysXtcs == null ? "1" : sysXtcs.getCsz();

            ypsrfReq.setJgid(user.getHospitalId());
            drugsTypkBqyzYpSrfReq.setJgid(user.getHospitalId());
            if ("2".equals(bzlx)) {
                pageInfo = drugsTypkOutSer.queryCisHerbalInpatientWard(ypsrfReq);
				
			}else {
				if(StrUtil.isEmpty(ObjectToTypes.parseString(drugsTypkBqyzYpSrfReq.getYfsz()))) {
					drugsTypkBqyzYpSrfReq.setYfsz(1);
				}
				drugsTypkBqyzYpSrfReq.setDmsb(3);
				List<Map<String, Object>> list = nisFyyfDao.queryYfsb(drugsTypkBqyzYpSrfReq);
				List<Integer> yfsbList = new ArrayList<Integer>();
				for(Map<String, Object> map : list) {
					yfsbList.add(ObjectToTypes.parseInt(map.get("YFSB")));
				}
				packageReq.setYfsbList(yfsbList);
				packageReq.setJgid(user.getHospitalId());
				if(CollectionUtil.isNotEmpty(yfsbList)) {
					pageInfo = drugsTypkOutSer.queryCisHerbalDrugPackaging(packageReq);
				}
				
			}
    	}
		return pageInfo;
    }

	
   

}
