package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImRcjl;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 住院_临床病人入出记录<br>
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImRcjlDao extends EntityDao<ImRcjl,Long> {
    
	/**
	 * 查询出入院记录条数
	 * @Title: queryZyrcjl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @return    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    long queryZyrcjl(Map<String, Object> parameters); 
    
    /**
     * 查询临床日期
     * @Title: queryClinicalDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imRcjl
     * @param @return    设定文件
     * @return ImRcjl    返回类型
     * @author 龚方舟
     * @throws
     */
    ImRcjl queryClinicalDate(ImRcjl imRcjl);

    /**
     * 更新出院方式状态
     * @Title: updateCyfsStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateCyfsStatus(Map<String, Object> parameters);

    /**
     * 更新入出记录出入方式状态
     * @Title: updateImRcjlCyfsStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imRcjl    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateImRcjlCyfsStatus(ImRcjl imRcjl);

	/**
	 * 进行取消入院处理
	 * @param qxryclParam
	 */
	void qxrycl(ImRcjl qxryclParam);

	/**
	 * 修改住院临床记录的临床日期
	 * @param parametersrcjllcrq
	 */
	void updateLcrq(ImRcjl parametersrcjllcrq);
}
