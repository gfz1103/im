package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisTypc;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisTypcDao extends EntityDao<NisTypc,Long>{
    
	 /**
	  * 根据住院号删除存在未提交退药明细的退药批次
	  * @Title: deleteTypcByZyh
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param zyh
	  * @param @param jgid    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void deleteTypcByZyh(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
	 
	 /**
	  * 根据退药序号删除存在未提交退药明细的退药批次
	  * @Title: deleteTypcByTyxh
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param tyxh    设定文件
	  * @return void    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 void deleteTypcByTyxh(Integer tyxh);
}
