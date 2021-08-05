package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.buit.cis.dctwork.model.SkinPsjl;
import com.buit.cis.dctwork.request.SkinPsjlReq;
import com.buit.cis.dctwork.response.SkinPsjlDrugsResp;
import com.buit.cis.dctwork.response.SkinPsjlResp;
import org.apache.ibatis.annotations.Param;

/**
 * 公用_病人皮试记录<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface SkinPsjlDao extends EntityDao<SkinPsjl,Integer>{
    
	/**
	 * 根据brid,jgid查询病人过敏药物
	 * @Title: getAllergicDrugsByEntity
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param skinPsjlReq
	 * @param @return    设定文件
	 * @return List<SkinPsjlResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<SkinPsjlResp> getAllergicDrugsByEntity(SkinPsjlReq skinPsjlReq);
	
	/**
	 * 根据brid,ypxh,jgid删除过敏药物
	 * @Title: deleteByEntity
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param skinPsjlReq    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteByEntity(SkinPsjlReq skinPsjlReq);
	
	/**
	* 过敏药物更新 
	* @Title: updateAllergicDrugs
	* @Description: TODO 
	* @param @param skinPsjlReq    设定文件
	* @return void    返回类型
	* @author 龚方舟
	* @throws
	 */
	void updateAllergicDrugs(SkinPsjlReq skinPsjlReq);


    /**
     * @Description 查询是否存在过敏药物类别最大时间
     * @Title  queryIsExtisMaxDate
     * @author  gfz
     * @Date  2021/6/7 23:13
     * @param brid
     * @param ypxh
     * @param gmlb
     * @param jgid
     * @Return  SkinPsjl
     * @Exception
    */
	SkinPsjl queryIsExtisMaxDate(@Param("brid") Integer brid, @Param("ypxh") Integer ypxh,
			@Param("gmlb") Integer gmlb, @Param("jgid") Integer jgid);
	

	/**
	 * 查询患者过敏药物大类信息
	 * @Title: queryAllergyDrugsInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param brid
	 * @param @param jgid
	 * @param @return    设定文件
	 * @return List<SkinPsjlDrugsResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<SkinPsjlDrugsResp> queryAllergyDrugsInfo(@Param("brid") Integer brid, @Param("jgid") Integer jgid);
	

	void updatePssj(@Param("pssj") Timestamp pssj, @Param("brid") Integer brid,
			@Param("gmlbList") List<Integer> gmlbList);

}
