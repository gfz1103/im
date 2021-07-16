package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImRzjz;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 住院_日账终结
 * @author songyu
 */
@Mapper
public interface ImRzjzDao extends EntityDao<ImRzjz,String> {


    Integer quertCount(Map<String, Object> parameters);

    Map<String, Object> queryMx(Map<String, Object> parameters);

    void doCancelCommit(Map<String, Object> parameters);

    List<Map<String, Object>> queryMaxJzrq(Map<String, Object> parameters);

    List<Map<String, Object>>  queryCommit(Map<String, Object> parameters);

    List<Map<String, Object>> queryCancelCommit(Map<String, Object> parameters);

    ImRzjz queryJzxx(Map<String, Object> parameters);

    List<Map<String, Object>> queryHzxx(Map<String, Object> parameters);

    List<Map<String, Object>> getHzxx(Map<String, Object> parameters);

    Map<String,Object> checkCollect(Map<String,Object> parameters);

    String queryMaxHzrq(String jgid);

    String queryMinHzrq(String jgid);

    void saveHz(Map<String,Object> parameters);

    List<Map<String,Object>> getHzrq(Map<String,Object> parameters);

    void doCancelHz(Map<String,Object> parameters);

    String queryKshzrq(Map<String,Object> parameters);

}
