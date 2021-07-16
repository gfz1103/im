package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisHlmbwhb;
/**
 * 护理单模板维护表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHlmbwhbDao extends EntityDao<NisHlmbwhb,Integer>{
    
	void invalid(@Param("id") Integer id, @Param("zfpb") Integer zfpb);
}
