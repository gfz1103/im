package com.buit.cis.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.nurse.model.NisFyfxpgd;
import com.buit.cis.nurse.request.NisHlQueryReq;
import com.buit.cis.nurse.response.NisFyfxpgdResp;
import com.buit.cis.nurse.response.NisHzmbDetailResp;
import com.buit.cis.nurse.response.NisHzmbResp;
import com.buit.commons.EntityDao;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisFyfxpgdDao extends EntityDao<NisFyfxpgd,Integer>{

     /**
      * 医院获得性肺炎风险因素评估单查询树结构
      * @Title: queryFyfxpgdTree
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param jgid
      * @param @return    设定文件
      * @return List<NisHzmbResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<NisHzmbResp> queryFyfxpgdTree(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
     
     /**
      * 根据日期查询医院获得性肺炎风险因素评估单
      * @Title: queryFyfxpgdByDate
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param queryDate
      * @param @param jgid
      * @param @return    设定文件
      * @return List<NisFyfxpgdResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<NisFyfxpgdResp> queryFyfxpgdByDate(NisHlQueryReq nisHlQueryReq);
     
     /**
      * 根据日期查询医院获得性肺炎风险因素评估单打印信息
      * @Title: queryPrintFyfxpgdByDate
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param queryDate
      * @param @param jgid
      * @param @return    设定文件
      * @return List<Map<String,Object>>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<Map<String, Object>> queryPrintFyfxpgdByDate(NisHlQueryReq nisHlQueryReq);
     
     /**
      * 医院获得性肺炎风险因素评估单查询树结构
      * @Title: queryFyfxpgdTreeDetail
      * @Description: TODO(这里用一句话描述这个方法的作用)
      * @param @param zyh
      * @param @param jgid
      * @param @return    设定文件
      * @return List<NisHzmbResp>    返回类型
      * @author 龚方舟
      * @throws
      */
     List<NisHzmbDetailResp> queryFyfxpgdTreeDetail(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid,
     		@Param("yearMonth") String yearMonth);
}
