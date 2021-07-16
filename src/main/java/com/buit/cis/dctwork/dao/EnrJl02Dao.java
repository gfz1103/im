package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.EnrJl02;
import com.buit.cis.dctwork.response.EnrJl02Resp;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface EnrJl02Dao extends EntityDao<EnrJl02,Integer>{
	
	/**
	* 根据记录编号查询护理记录明细  
	* @Title: queryEnrJl02ByJlbh
	* @Description: TODO 
	* @param @param enrJl02
	* @param @return    设定文件
	* @return List<EnrJl02Resp>    返回类型
	* @author 龚方舟
	* @throws
	 */
    List<EnrJl02Resp> queryEnrJl02ByJlbh(EnrJl02 enrJl02);
}
