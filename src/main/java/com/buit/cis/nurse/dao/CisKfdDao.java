package com.buit.cis.nurse.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.nurse.model.CisKfd;
/**
 * 口服单<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisKfdDao extends EntityDao<CisKfd,Integer>{
    
}
