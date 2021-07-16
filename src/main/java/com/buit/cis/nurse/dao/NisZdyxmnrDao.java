package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisZdyxmnr;
/**
 * 自定义专科护理内容<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisZdyxmnrDao extends EntityDao<NisZdyxmnr,Integer>{
    
	void invalid(@Param("xmnrdm") Integer xmnrdm, @Param("zfpb") Integer zfpb);

	/**
	 * 查询是专科项目是否被使用
	 * @Title: queryIsExistUse
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jgid
	 * @param @param xmdm
	 * @param @return    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	long queryIsExistUse(@Param("jgid") Integer jgid, @Param("xmdm") Integer xmdm);
}
