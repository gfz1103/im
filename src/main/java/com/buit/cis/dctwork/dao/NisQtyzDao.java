package com.buit.cis.dctwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.buit.cis.dctwork.model.NisQtyz;
import com.buit.cis.dctwork.response.NisQtyzSrfResp;
import com.buit.commons.EntityDao;

/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface NisQtyzDao extends EntityDao<NisQtyz,Integer>{
    
	 /**
	  * 其他医嘱输入发查询
	  * @Title: queryQtyzSrf
	  * @Description: TODO(这里用一句话描述这个方法的作用)
	  * @param @param nisQtyz
	  * @param @return    设定文件
	  * @return List<NisQtyzSrfResp>    返回类型
	  * @author 龚方舟
	  * @throws
	  */
	 List<NisQtyzSrfResp> queryQtyzSrf(NisQtyz nisQtyz);
}
