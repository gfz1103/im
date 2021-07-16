package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisJbdmb;
/**
 * 护理交班单模板<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisJbdmbDao extends EntityDao<NisJbdmb,Integer>{
    
	/**
	 * 护理交班单作废或取消作废
	 * @Title: invalid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @param zfpb    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void invalid(@Param("id") Integer id, @Param("zfpb") Integer zfpb);
}
