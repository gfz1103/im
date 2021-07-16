package com.buit.cis.ims.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.buit.cis.ims.model.ImCwzh;
/**
 * <br>
 * @author GONGFANGZHOU
 */
@Mapper
public interface ImCwzhDao extends EntityDao<ImCwzh,Integer>{
    
	/**
	 * 作废或取消作废床位组号信息
	 * @Title: invalidByCwzh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cwzh
	 * @param @param zfpb
	 * @param @param jgid    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    void invalidByCwzh(@Param("cwzh") Integer cwzh, @Param("zfpb") Integer zfpb,
            @Param("jgid") Integer jgid);
}
