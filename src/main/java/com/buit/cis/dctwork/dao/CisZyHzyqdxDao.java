package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.CisZyHzyqdx;

import java.util.List;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface CisZyHzyqdxDao extends EntityDao<CisZyHzyqdx,Integer>{
    
	/**
	 * updateYqdxBySqxh
	 * @Title: updateYqdxBySqxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sqxh
	 * @param @param yqdx    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    void updateYqdxBySqxh(@Param("sqxh") Integer sqxh, @Param("yqdx") String yqdx);

	/**
	 * @Description 批量删除会诊邀请对象
	 * @Title  deleteConsultationBySqidList
	 * @author  gfz
	 * @Date  2021/6/6 23:49
	 * @param sqidList
	 * @Return  void
	 * @Exception
	 */
	void deleteConsultationYqBySqidList(@Param("sqidList") List<Integer> sqidList);
}
