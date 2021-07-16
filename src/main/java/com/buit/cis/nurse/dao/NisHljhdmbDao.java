package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisHljhdmb;
/**
 * 护理执行单模板<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljhdmbDao extends EntityDao<NisHljhdmb,Integer>{
    
	/**
	 * 作废或取消作废
	 * @Title: invalid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @param zfpb    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void invalid(@Param("jlxh") Integer jlxh, @Param("zfpb") Integer zfpb);
}
