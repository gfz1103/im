package com.buit.cis.dctwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/11/6 10:26
 */
@Mapper
public interface OptSssqDao {
	
	/**
	 * 批量删除手术
	 * @Title: deleteBatchBySqidList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxhList    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteBatchBySqidList(@Param("sqidList") List<Integer> sqidList);
}
