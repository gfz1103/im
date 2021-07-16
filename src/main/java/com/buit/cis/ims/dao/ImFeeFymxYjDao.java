package com.buit.cis.ims.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.ims.model.ImFeeFymxYj;
import com.buit.cis.ims.request.ImFeeFymxYjJyReq;
import com.buit.cis.ims.request.ImFeeFymxYjPrintReq;
import com.buit.cis.ims.response.ImFeeFymxYjJyResp;
import com.buit.cis.ims.response.ImFeeFymxYjPrintResp;
import com.buit.commons.EntityDao;

/**
 * 费用明细表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface ImFeeFymxYjDao extends EntityDao<ImFeeFymxYj,Integer> {
    
	/**
	 * 根据yzxh批量删除ImFeeFymxYj
	 * @Title: deleteByYzxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jlxh    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void deleteByYzxh(String jlxh);

	/**
	 * 根据医嘱序号查询医技信息
	 * 
	 * @param sfxmdm
	 * @return
	 */
	public ImFeeFymxYj doQueryByYzxh(Integer yzxh);

	/**
	 * 根据医嘱序号更新执行状态和执行次数
	 * 
	 * @param map
	 */
	void updateZxztAndZxcs(Map<String, Object> map);
	
	/**
	 * 查询检验组套项目
	 * @Title: doQueryClinicalXm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param imFeeFymxYjJyReq
	 * @param @return    设定文件
	 * @return List<ImFeeFymxYjJyResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ImFeeFymxYjJyResp> doQueryClinicalXm(ImFeeFymxYjJyReq imFeeFymxYjJyReq);
	
	/**
	 * lis撤销更新状态
	 * @Title: cancelLisZxzt
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzxhList    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void cancelLisZxzt(@Param("yzxhList") List<Integer> yzxhList);

	/**
	 * 查询已执行项目
	 * @Title: queryXmPrint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param req
	 * @param @return    设定文件
	 * @return List<ImFeeFymxYjPrintResp>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ImFeeFymxYjPrintResp> queryXmPrint(ImFeeFymxYjPrintReq req);
}
