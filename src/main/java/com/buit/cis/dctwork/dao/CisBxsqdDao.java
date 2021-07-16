package com.buit.cis.dctwork.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.dto.ClinicalLeadBloodParamDto;
import com.buit.cis.dctwork.model.CisBxsqd;
import com.buit.cis.dctwork.request.CisBxsqdHomeReq;
import com.buit.cis.dctwork.request.CisBxsqdLeadBloodReq;
import com.buit.cis.dctwork.request.CisBxsqdQueryReq;
import com.buit.cis.dctwork.response.CisBxsqdHomeResp;
import com.buit.cis.dctwork.response.CisBxsqdLeadBloodResp;
import com.buit.cis.dctwork.response.CisBxsqdResp;
import com.buit.commons.EntityDao;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisBxsqdDao extends EntityDao<CisBxsqd,String>{
    
	 /**
	  * 删除备血申请单
	  * @Title: deletePrepareBloodBySqidList
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param sqidList    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void deletePrepareBloodBySqidList(@Param("sqidList") List<Integer> sqidList);
	 
	 /**
	  * 查询备血申请单
	  * @Title: queryZyBloodList
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param cisBxsqdQueryReq
	  * @param @return    设定文件
	  * @return List<CisBxsqdResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<CisBxsqdResp> queryZyBloodList(CisBxsqdQueryReq cisBxsqdQueryReq);

	 /**
	  * 查询首页备血申请单
	  * @Title: queryBloodHomePage
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param cisBxsqdHomeReq
	  * @param @return    设定文件
	  * @return List<CisBxsqdHomeResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<CisBxsqdHomeResp> queryBloodHomePage(CisBxsqdHomeReq cisBxsqdHomeReq);   
	 
	 /**
	  * 查询备血申请单打印
	  * @Title: queryBloodPrint
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param sqdh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return Map<String,Object>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 Map<String, Object> queryBloodPrint(@Param("sqdh") String sqdh, @Param("jgid") Integer jgid);
	 
	 /**
	  * 查询领血血申请单打印
	  * @Title: queryClinicalLeadBloodPrint
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param sqdh
	  * @param @param jgid
	  * @param @return    设定文件
	  * @return ClinicalLeadBloodParamDto    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 ClinicalLeadBloodParamDto queryClinicalLeadBloodPrint(@Param("sqdh") String sqdh, @Param("jgid") Integer jgid);
	 

	 /**
	  *  查询领血血申请单列表
	  * @Title: queryLeadBloodList
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param cisBxsqdLeadBloodReq
	  * @param @return    设定文件
	  * @return List<CisBxsqdLeadBloodResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<CisBxsqdLeadBloodResp> queryLeadBloodList(CisBxsqdLeadBloodReq cisBxsqdLeadBloodReq);
	 
	 /**
	  * 更新领用信息
	  * @Title: updateLeadInfo
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param sqdh
	  * @param @param lxsj
	  * @param @param lxrdm
	  * @param @param lxczrdm
	  * @param @param lxczsj    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void updateLeadInfo(@Param("sqdh") String sqdh, @Param("lxsj") Timestamp lxsj,
			  @Param("lxrdm") String lxrdm, @Param("lxczrdm") String lxczrdm,
			  @Param("lxczsj") Timestamp lxczsj);
}
