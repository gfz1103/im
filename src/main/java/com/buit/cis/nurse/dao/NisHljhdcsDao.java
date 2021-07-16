package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.nurse.model.NisHljhdcs;
/**
 * 护理执行单子表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljhdcsDao extends EntityDao<NisHljhdcs,Integer>{
   
	void deleteByZxjlxh(Integer zxjlxh);
}
