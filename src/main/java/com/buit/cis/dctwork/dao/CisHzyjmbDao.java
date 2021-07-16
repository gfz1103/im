package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.CisHzyjmb;
/**
 * 会诊意见模板维护表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisHzyjmbDao extends EntityDao<CisHzyjmb,Integer>{
    
	/**
	 * 作废或取消作废会诊意见模板
	 * @Title: invalid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zfpb
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void invalid(@Param("zfpb") Integer zfpb, @Param("jlxh") Integer jlxh);
}
