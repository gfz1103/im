package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.CisYzkpdyjl;
/**
 * 住院医嘱医嘱卡片打印记录<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisYzkpdyjlDao extends EntityDao<CisYzkpdyjl,Integer>{
 

	 /**
	  * 取消打印
	  * @Title: cancelKpdy
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param yzzh
	  * @param @param yysj    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void cancelKpdy(@Param("yzzh") Integer yzzh, @Param("yysj") Timestamp yysj);
}
