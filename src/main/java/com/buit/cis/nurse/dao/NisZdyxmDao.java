package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisZdyxm;
/**
 * 护理记录单自定义项目<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisZdyxmDao extends EntityDao<NisZdyxm,Integer>{
    
	void invalid(@Param("xmdm") Integer xmdm, @Param("zfpb") Integer zfpb);
}
