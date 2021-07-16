package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.nurse.model.NisHzmb;
/**
 * 患者护理记录模板表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHzmbDao extends EntityDao<NisHzmb,Integer>{
    
}
