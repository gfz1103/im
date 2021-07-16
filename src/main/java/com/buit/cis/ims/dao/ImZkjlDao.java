package com.buit.cis.ims.dao;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.ims.model.ImZkjl;
import com.buit.cis.ims.request.ImZkjlHomeReq;
import com.buit.cis.ims.response.ImZkjlHomeResp;
import com.buit.cis.ims.response.ImZkjlResp;
import com.buit.commons.EntityDao;

/**
 * 住院_转科记录<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface ImZkjlDao extends EntityDao<ImZkjl,Long> {
    
   /**
    * 查询是否有转科记录
    * @Title: queryisExistZkjlCount
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param zyh
    * @param @param jgid
    * @param @return    设定文件
    * @return long    返回类型
    * @author 龚方舟
    * @throws
    */
    long queryisExistZkjlCount(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
   /**
    * 查询是否有转科记录返回实体
    * @Title: queryisExistZkjl
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param zyh
    * @param @param jgid
    * @param @return    设定文件
    * @return ImZkjlResp    返回类型
    * @author 龚方舟
    * @throws
    */
    ImZkjlResp queryisExistZkjl(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 查询转科转入记录 
     * @Title: queryZkjlChangeInto
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param hhbq
     * @param @param jgid
     * @param @return    设定文件
     * @return List<ImZkjlResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImZkjlResp> queryZkjlChangeInto(@Param("type") Integer type, @Param("hhbq") Integer hhbq, @Param("jgid") Integer jgid);
    
    /**
     * 更新转科记录信息
     * @Title: updateZkjlInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param bqzxrq
     * @param @param bqzxgh
     * @param @param hhch
     * @param @param zyh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateZkjlInfo(@Param("bqzxrq") Timestamp bqzxrq, @Param("bqzxgh") Integer bqzxgh,
                        @Param("hhch") String hhch, @Param("zyh") Integer zyh);
    

    /**
     * 查询转科病区信息
     * @Title: queryZkBqjl
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryZkBqjl(Map<String, Object> parameters);
    

    /**
     * 查询首页转科记录
     * @Title: queryZkjlHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imZkjlHomeReq
     * @param @return    设定文件
     * @return List<ImZkjlHomeResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImZkjlHomeResp> queryZkjlHomePage(ImZkjlHomeReq imZkjlHomeReq);
    

   	/**
   	 * 病人转科更新通知状态
   	 * @Title: updateZkNoticeStatus
   	 * @Description: TODO(这里用一句话描述这个方法的作用)
   	 * @param @param tzzt
   	 * @param @param tzgh
   	 * @param @param zyh    设定文件
   	 * @return void    返回类型
   	 * @author 龚方舟
   	 * @throws
   	 */
   	void updateZkNoticeStatus(@Param("tzzt") Integer tzzt, @Param("tzgh") Integer tzgh,
			 @Param("jlxh") Integer jlxh);
   	

	/**
	 * 病人转出转入数
	 * @Title: queryTransferInAndOutCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jgid
	 * @param @param type
	 * @param @param kssj
	 * @param @param jssj
	 * @param @param bqdm
	 * @param @return    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	long queryTransferInAndOutCount(@Param("jgid") Integer jgid, @Param("type") Integer type,
    		@Param("kssj") Timestamp kssj, @Param("jssj") Timestamp jssj, @Param("bqdm") Integer bqdm);
}
