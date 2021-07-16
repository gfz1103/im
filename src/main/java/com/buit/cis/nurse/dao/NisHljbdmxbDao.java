package com.buit.cis.nurse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisHljbdmxb;
import com.buit.cis.nurse.response.NisHljbdmxbResp;
import com.buit.commons.EntityDao;
/**
 * 护理交班单明细表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljbdmxbDao extends EntityDao<NisHljbdmxb,Integer>{
    

	/**
	 * 根据交班单主键查询明细
	 * @Title: queryDetailByJbdJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jbdjlxh
	 * @param @return    设定文件
	 * @return List<NisHljbdmxbResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    List<NisHljbdmxbResp> queryDetailByJbdJlxh(@Param("jbdjlxh") Integer jbdjlxh, @Param("zyh") Integer zyh);
    
    
    List<Integer> queryZyhByJlxh(@Param("jlxhlist") List<Integer> jlxhlist);
}
