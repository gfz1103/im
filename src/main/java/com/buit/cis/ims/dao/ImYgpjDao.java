package com.buit.cis.ims.dao;

import com.buit.cis.ims.model.ImYgpj;
import com.buit.cis.ims.response.ImYgpjResp;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 住院_员工票据<br>
 * @author zhouhaisheng
 */
@Repository
@Mapper
public interface ImYgpjDao extends EntityDao<ImYgpj,Integer> {
    /**
     * 验证号码段与其他号码段是否有重叠
     * @param pj
     * @return
     */
    boolean repetCheck(ImYgpj pj);

    /**
     * 校验当前号码段是否可以启用
     */
    boolean activeCheck(ImYgpj pj);

    /**
     * 修改发票号验证 当前使用号码是否合法
     * @param pj
     * @return
     */
//    List<ImYgpj> checkDqhm(ImYgpj pj);

    /**
     * 查询最小领用日期的票据信息
     * @param imYgpj
     * @return
     */
    String queryMinLyrq(ImYgpj imYgpj);

    /**
     * 修改住院员工票据当前使用号码
     * @param imYgpjUpdateParam
     */
//    void updateZyygpj(ImYgpj imYgpjUpdateParam);

    /**
     * 把员工票据置位不可用
     * @param imYgpjUpdateParam
     */
//    void updateZyygpjStatus(ImYgpj imYgpjUpdateParam);

    /**
     * 修改员工票据的当前使用号码
     * @param pj
     */
    void updateDqhm(ImYgpj pj);

    /**
     * 查询员工票据分页信息
     * @param imygpj
     * @return
     */
    List<ImYgpjResp> findYgpjPage(ImYgpj imygpj);

    /***
     *  修改票据的使用标志
     * @param imYgpj
     */
    void updateSybz(ImYgpj imYgpj);

    /**
     * 查询当前的号码的有效性
     * @param imYgpjParam
     * @return
     */
    List<ImYgpj> verifyDqhm(ImYgpj imYgpjParam);

    /**
     * 校验修改的终止号码是否合法
     * @param pj
     * @return
     */
//    List<ImYgpj> checkUpdateZzhm(ImYgpj pj);

    /**
     * 修改当前号码跟终止号码
     * @param pj
     */
//    void updateDqhmAndZzhm(ImYgpj pj);


}
