package com.buit.cis.nurse.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisHljbd;
import com.buit.cis.nurse.request.NisHljbdQueryReq;
import com.buit.commons.EntityDao;
/**
 * 护理交班单主表<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisHljbdDao extends EntityDao<NisHljbd,Integer>{
    
	/**
	 * 分页查询交班单
	 * @Title: queryPageHljbd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param nisHljbdReq
	 * @param @return    设定文件
	 * @return List<NisHljbd>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<NisHljbd> queryPageHljbd(NisHljbdQueryReq nisHljbdQueryReq);
	

    /**
     * 查询是否存在同时间班次护理组交班单
     * @Title: queryIsExistSameTimeInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param hlzh
     * @param @param jbsj
     * @param @param bclx
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryIsExistSameTimeInfo(@Param("jgid") Integer jgid, @Param("hlzh") Integer hlzh,
    		@Param("jbsj") Timestamp jbsj, @Param("bclx") Integer bclx);
}
