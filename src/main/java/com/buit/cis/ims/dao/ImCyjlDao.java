package com.buit.cis.ims.dao;


import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.ims.model.ImCyjl;
import com.buit.commons.EntityDao;

/**
 * 住院_住院记录<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface ImCyjlDao extends EntityDao<ImCyjl,Long> {
    
}
