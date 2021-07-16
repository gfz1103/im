package com.buit.cis.dctwork.dao;

import com.buit.commons.EntityDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.dctwork.model.NisSmtz;
import com.buit.cis.dctwork.response.NisSmtzResp;
/**
 * 生命体征<br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisSmtzDao extends EntityDao<NisSmtz,Integer>{
    
    /** 
    * 查询生命体征和体征项目序号
    * @Title: querySmtzAndBqtzInfo
    * @Description: TODO 
    * @param @param parameters
    * @param @return    设定文件
    * @return List<Map<String,Object>>    返回类型
    * @author 龚方舟
    * @throws
     */
    List<Map<String, Object>> querySmtzAndBqtzInfo(Map<String, Object> parameters);
    
    /**
    * 根据住院号查询病人生命体征
    * @Title: querySmtzInfoByZyh
    * @Description: TODO 
    * @param @param zyh
    * @param @return    设定文件
    * @return List<NisSmtzResp>    返回类型
    * @author 龚方舟
    * @throws
     */
    List<NisSmtzResp> querySmtzInfoByZyh(Integer zyh);
    

    /**
     * 根据时间和住院号查询病人生命体征
     * @Title: querySmtzInfoByZyhAndDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param tjdh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> querySmtzInfoByZyhAndDate(@Param("zyh") Integer zyh, @Param("cjsj") Date cjsj, 
    		@Param("xmh") Integer xmh, @Param("type") Integer type, @Param("list") List<Integer> list);
    

    /**
     * 根据时间和住院号查询病人生命体征心率和脉搏
     * @Title: querySmtzPulseAndRate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param cjsj
     * @param @param list
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> querySmtzPulseAndRate(@Param("zyh") Integer zyh, @Param("cjsj") Date cjsj, @Param("list") List<Integer> list);
    

    /**
     * 根据时住院号和自定义序号查询病人生命体征
     * @Title: querySmtzInfoByZyhAndZdyxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param zdyxh
     * @param @return    设定文件
     * @return List<Map<String, Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> querySmtzInfoByZyhAndZdyxh(@Param("zyh") Integer zyh, @Param("cjsj") Date cjsj, @Param("zdyxh") Integer zdyxh);
    

    /**
     * 查询自定义生命体征
     * @Title: queryZdySmtz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param cjsj
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryZdySmtz(@Param("zyh") Integer zyh, @Param("queryDate") String queryDate, 
    		 @Param("hour") Integer hour, @Param("jgid") Integer jgid);
    

    /**
     * 根据小时查询自定义生命体征
     * @Title: queryZdySmtzByHour
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param cjsj
     * @param @param hour
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryZdySmtzByHour(@Param("zyh") Integer zyh, @Param("cjsj") String cjsj, 
    		@Param("hour") Integer hour, @Param("zdyid") Integer zdyid, @Param("jgid") Integer jgid);
    

    /**
     * 查询批量最新生命体征和体征项目序号
     * @Title: queryBatchSmtzAndBqtzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<Strin,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryBatchSmtzAndBqtzInfo(Map<String, Object> parameters);
    
    /**
     * 批量删除体征
     * @Title: batchRemoveSmtz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cjhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void batchRemoveSmtz(@Param("cjhList") List<Integer> cjhList);
    
    /**
     * 查询脉搏起搏器心率
     * @Title: queryPulsePacemakerHeartRate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param cjsj
     * @param @param type
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryPulsePacemakerHeartRate(@Param("zyh") Integer zyh, @Param("cjsj") Date cjsj, 
    		@Param("type") Integer type);
    
    /**
     * 根据时间和住院号查询病人降温生命体征
     * @Title: queryJwSmtzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param cjsj
     * @param @param xmh
     * @param @param type
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryJwSmtzInfo(@Param("zyh") Integer zyh, @Param("cjsj") Timestamp cjsj, 
    		@Param("xmh") Integer xmh, @Param("type") Integer type);
    

    /**
     * 根据时间和时间段查询体征信息
     * @Title: queryNisSmtzInfoBySjdAndDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param sjd
     * @param @param queryDate
     * @param @return    设定文件
     * @return List<NisSmtz>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisSmtz> queryNisSmtzInfoBySjdAndDate(@Param("sjd") Integer sjd, @Param("queryDate") String queryDate);
}
