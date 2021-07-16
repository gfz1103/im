package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.CisYzmess;
/**
 * 医嘱变动消息发送<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisYzmessDao extends EntityDao<CisYzmess,Integer>{
    
	/**
	 * 更新医嘱消息状态
	 * @Title: updateZtByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh
	 * @param @param newzt
	 * @param @param oldzt    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateZtByJlxh(@Param("jlxh") Integer jlxh, @Param("newzt") Integer newzt,
			@Param("clrdm") Integer clrdm, @Param("oldzt") Integer oldzt);

	/**
	 * 查询是否存在最新发送时间已完成的消息
	 * @Title: queryLatestTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return CisYzmess    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    CisYzmess queryLatestTime(Integer zyh);
}
