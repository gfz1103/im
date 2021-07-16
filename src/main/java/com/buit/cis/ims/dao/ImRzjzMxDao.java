package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImRzjz;
import com.buit.cis.ims.model.ImRzjzMx;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 住院_日账终结
 * @author songyu
 */
@Mapper
public interface ImRzjzMxDao extends EntityDao<ImRzjz,String> {

    void doCancelCommit(Map<String,Object> parameters);

    List<ImRzjzMx> queryJzxx(Map<String,Object> parameters);

}
