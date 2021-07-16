package com.buit.cis.ims.dao;


import com.buit.cis.ims.model.ImCwsz;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.request.ImCwszQueryReq;
import com.buit.cis.ims.request.ImCwszReq;
import com.buit.cis.ims.request.ZcPatientPageInfoReq;
import com.buit.cis.ims.request.ZybrCwszReq;
import com.buit.cis.ims.response.*;
import com.buit.commons.EntityDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 住院_床位设置<br>
 * @author LAPTOP-6GUR25CC
 */
@Mapper
@Repository
public interface ImCwszDao extends EntityDao<ImCwsz,String> {

    /**
     * 住院病人床位设置列表查询
     * @param zybrCwszReq
     * @return
     */
    List<ZybrCwszResp> queryZybrCwszPage(ZybrCwszReq zybrCwszReq);

    /**
     * 退床操作
     * @param imCwsz
     */
    void updateTc(ImCwsz imCwsz);

    /**
     * 查询床位使用数
     * @param imCwsz
     * @return
     */
    List<ImCwsz> cwtj(ImCwsz imCwsz);

    /**
     * 查询床位是否有人
     * @Title: queryIsExistsCw
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brch
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryIsExistsCw(@Param("brch") String brch, @Param("jgid") Integer jgid, @Param("ksdm") Integer ksdm);

    /**
     * 查询床位是否有人
     * @Title: queryIsGenderFit
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imCwsz
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryIsGenderFit(ImCwsz imCwsz);

    /**
     * 住院病人结算退床操作
     */
    void tc(ImZyjs zyjs);

    /**
     * 机构id跟病人床号查询住院号
     * @param imCwszParameters
     */
    Map<String, Object> queryZyhByJgidAndBrch(Map<String, Object> imCwszParameters);

    /**
     * 通过机构id跟病人床号修改病人住院号
     * @param imHzryParameters
     */
    void updateZyhByBrchAndJgid(Map<String, Object> imHzryParameters);

    /**
     * 通过病人床号跟机构id查询床位科室跟科室代码
     * @param imCwszParameters
     * @return
     */
    Map<String, Object> queryCwksAndKsdm(Map<String, Object> imCwszParameters);

    /**
     *   床位对调
     * @param zyh
     * @param newCwhm
     * @param newZyh
     * @param jgid
     */
    void updateCwdd(@Param("zyh") Integer zyh, @Param("newCwhm") String newCwhm, @Param("newZyh") Integer newZyh, @Param("jgid") Integer jgid);

    /**
     * 转到空床
     * @param zyh
     * @param newCwhm
     * @param jgid
     */
    void updateKc(@Param("zyh") Integer zyh, @Param("newCwhm") String newCwhm, @Param("jgid") Integer jgid, @Param("ksdm") Integer ksdm);

    /**
     * 转到空床JCKS = null
     * @param zyh
     * @param brch
     * @param bqdm
     */
    void updateKcJcks(@Param("zyh") Integer zyh, @Param("brch") String brch, @Param("bqdm") Integer bqdm);

    /**
     * 床位对调(老)
     * @param zyh
     * @param oldCwhm
     * @param zyhold
     * @param jgid
     */
    void updateCwddOld(@Param("zyh") Integer zyh, @Param("oldCwhm") String oldCwhm, @Param("zyhold") Integer zyhold, @Param("jgid") Integer jgid);

    /**
     *  转到空床(老)
     * @param zyh
     * @param brch
     * @param brks
     * @param brbq
     */
    void updateKcOld(@Param("zyh") Integer zyh, @Param("brch") String brch, @Param("brks") Integer brks, @Param("brbq") Integer brbq);

    /**
     * 转到空床
     * @param oldCwhm
     */
    void updateZckc(@Param("oldCwhm") String oldCwhm, @Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 修改床位分配信息
     * @param parameterscwsz
     */
    void updateCwfp(ImCwsz parameterscwsz);

    /**
     * 锁定床号
     * @Title: updateLockedBedNo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brch
     * @param @param jgid    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateLockedBedNo(@Param("brch") String brch, @Param("jgid") Integer jgid, @Param("ksdm") Integer ksdm);

    /**
     * 据病人性别查询病人可分配的床位信息
     * @param brks
     * @param brxb
     * @param jgid
     * @return
     */
    List<ImCwsz> findKcByBrksAndBrxbAndJgid(@Param("brks") Integer brks, @Param("brxb") Integer brxb, @Param("jgid") Integer jgid);


    /**
     * 查询符合条件空的床位号
     * @Title: queryQualifiedCwhm
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imCwsz
     * @param @return    设定文件
     * @return List<ImCwsz>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImCwsz> queryQualifiedCwhm(ImCwsz imCwsz);

    /**
     * 床位管理-转床（分页列表）
     * @param zcPatientPageInfoReq
     */
    List<ZcPatientPageInfoResp> queryZcPatientPageInfo(ZcPatientPageInfoReq zcPatientPageInfoReq);

    /**
     * 清空床位的住院号操作
     * @param zyh
     */
    void delZyh(Integer zyh);

    /**
     * 查询可转床位
     * @Title: queryTransferableBeds
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imCwszQueryReq
     * @param @return    设定文件
     * @return List<ZybrCwszResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ZybrCwszResp> queryTransferableBeds(ImCwszQueryReq imCwszQueryReq);


    /**
     * 查询床位数量
     * @Title: queryBedNum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryBedNum(Integer zyh);

    /**
     * 查询床位费用信息
     * @Title: queryBedFyInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryBedFyInfo(Integer zyh);

    /**
     * 分页查询床位设置信息
     * @param param
     * @return
     */
    List<ImCwszResp> queryPage(ImCwszReq param);

    /**
     * 待床分配床位
     *
     * @param param
     */
    void waitBed(ImCwsz param);

    /**
     * 床位管理 - 报表打印
     */
    List<ReportBedListResp> queryReportBedList(ZybrCwszReq zybrCwszReq);


    /**
     * 查询床位使用或未使用使用数量
     * @Title: queryUseOrNotCwCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param ksdm
     * @param @return    设定文件
     * @return List<ImCwszUseResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImCwszUseResp> queryUseOrNotCwCount(@Param("jgid") Integer jgid, @Param("ksdm") Integer ksdm);

    /**
     * 查询床位
     *
     * @param hospitalId 机构id
     * @param brbq       床位病区
     * @param brch       床位号码
     * @return
     */
    ImCwsz getByPk(@Param("jgid") Integer hospitalId, @Param("ksdm") Integer brbq, @Param("brch") String brch);
}
