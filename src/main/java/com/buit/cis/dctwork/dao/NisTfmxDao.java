package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.NisTfmx;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisTfmxDao extends EntityDao<NisTfmx,Long>{
    
	/**
	 * 退费单未确认表单记录数
	 * @Title: queryRefundNotSure
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param jgid
	 * @param @return    设定文件
	 * @return List<NisTfmx>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<NisTfmx> queryRefundNotSure(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
}
