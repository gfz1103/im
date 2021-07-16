package com.buit.cis.ims.dao;

import com.buit.cis.im.response.ImRyzdMedicalRecord;
import com.buit.cis.im.response.ImRyzdModel;
import com.buit.cis.ims.model.ImRyzd;
import com.buit.cis.ims.request.ImRyzdReq;
import com.buit.cis.ims.response.ImRyzdResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 住院_入院诊断<br>
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImRyzdDao extends EntityDao<ImRyzd,Long> {
    
	/**
	 * 根据住院查询入院诊断和诊断名称等
	 * @Title: getDiagnosisByEntity
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imRyzdReq
	 * @param @return    设定文件
	 * @return List<ImRyzdResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ImRyzdResp> getDiagnosisByEntity(ImRyzdReq imRyzdReq);
	
	/**
	 * 根据联合主键删除入院诊断
	 * @Title: deleteByCompositeKeys
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @param zdxh
	 * @param @param zdlb    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteByCompositeKeys(@Param("zyh") Integer zyh, @Param("zdxh") Integer zdxh, @Param("zdlb") Integer zdlb);
	
	/**
	 * 病人信息诊断更新
	 * @Title: updateDiagnosis
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imRyzdReq    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateDiagnosis(ImRyzdReq imRyzdReq);

	/**
	 * @name: queryZdmcByZyh
	 * @description: 根据住院号查询诊断名称
	 * @param zyh
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @date: 2020/8/27 19:40
	 * @auther: 老花生
	 *
	 */
    List<String> queryZdmcByZyh(@Param("zyh") String zyh);
    
    /**
     * 查询病案首页诊断信息
     * @Title: queryZdInfoMedicalRecord
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return List<ImRyzdMedicalRecord>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImRyzdMedicalRecord> queryZdInfoMedicalRecord(Integer zyh);
    

    /**
	 * 出院证打印获取出院诊断名称
	 * @Title: getDischargeDiagnosis
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param zyh
	 * @param @return    设定文件
	 * @return List<ImRyzdResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ImRyzdResp> getDischargeDiagnosis(Integer zyh);

	/**
	 * 查询对应类别的所有诊断（包含中医和西医）
	 *
	 * @param zyh  住院号
	 * @param zdlb 诊断类别
	 */
	List<ImRyzdModel> findList(@Param("zyh") Integer zyh, @Param("zdlb") Integer zdlb);

	/**
	 * 根据住院号修改入院主诊断
	 *
	 * @param zyh  住院号
	 * @param zdxh 诊断序号
	 */
	void updateAdmittingDiagnosis(Integer zyh, Integer zdxh);
}
