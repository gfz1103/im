package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.EnrJg02;
import com.buit.cis.dctwork.response.EnrJg02Resp;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface EnrJg02Dao extends EntityDao<EnrJg02,Integer>{
    
	/**
	* 根据机构编号查询Jg02
	* @Title: queryEnrJg02ByJgbh
	* @Description: TODO 
	* @param @param jgbh
	* @param @return    设定文件
	* @return List<EnrJg02Resp>    返回类型
	* @author 龚方舟
	* @throws
	 */
	List<EnrJg02Resp> queryEnrJg02ByJgbh(Integer jgbh);
}
