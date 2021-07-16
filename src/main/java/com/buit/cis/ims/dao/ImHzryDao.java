package com.buit.cis.ims.dao;


import com.buit.cis.dctwork.response.YsZyHzBgResp;
import com.buit.cis.im.request.ImHzryBaReq;
import com.buit.cis.im.request.ImHzryEmrReq;
import com.buit.cis.im.request.ImPacsPatientInfoReq;
import com.buit.cis.im.response.ImHzryBaResp;
import com.buit.cis.im.response.ImHzryEmrResp;
import com.buit.cis.im.response.ImPacsPatientInfoResp;
import com.buit.cis.ims.model.ImHzry;
import com.buit.cis.ims.model.ImZyjs;
import com.buit.cis.ims.request.*;
import com.buit.cis.ims.response.*;
import com.buit.cis.nurse.request.CisHzyzCardQueryReq;
import com.buit.cis.nurse.request.NisDispensingDetailReq;
import com.buit.cis.nurse.request.NisDispensingReq;
import com.buit.cis.nurse.request.NisHzDispensingReq;
import com.buit.cis.nurse.response.*;
import com.buit.commons.EntityDao;
import com.buit.op.request.OpZydjUpdateStatusReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 住院_病人入院<br>
 * @author ZHOUHAISHENG
 */
@Mapper
@Repository
public interface ImHzryDao extends EntityDao<ImHzry,Integer> {
    /**
     * 校验该病人是否重复入院
     * @param cfBraryParama
     * @return
     */
    List<ImHzry> queryCfry(ImHzry cfBraryParama);

    /**
     * 效验该病人入院的住院号码
     * @param imHzryParam
     * @return
     */
    List<ImHzry> queryCfZyhm(ImHzry imHzryParam);

    /**
     * 效验该病人入院的病案号码
     * @param imHzryParam
     * @return
     */
    List<ImHzry> queryCfBahm(ImHzry imHzryParam);

    /**
     * 查询住院病人的住院次数
     * @param zycsParam
     * @return
     */
    List<ImHzry> queryZycs(ImHzry zycsParam);

    /**
     * 更新ImHzry mzzd信息
     * @param updateImHzry
     */
//    void updateMzzd(ImHzry updateImHzry);

    /**
     * 查询病人管理列表
     * @param patientListReq
     * @return
     */
    List<PatientListResp> findPatientList(PatientListReq patientListReq);


    /**
     * 医生工作站病人管理查询不同的病人列表信息
     * @Title: queryBedPatientInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzryDctworkReq
     * @param @return    设定文件
     * @return List<ImHzryDctworkResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryDctworkResp> queryBedPatientInfo(ImHzryDctworkReq imHzryDctworkReq);

    /**
     * 退床操作
     * @param imHzryParam
     */
    void updateTc(ImHzry imHzryParam);

    /**
     * 通过住院号查询该病人是否在发票作废病人列表
     * @param zyh
     * @return
     */
    Integer queryfpzf(Integer zyh);

    /**
     * 通过住院号查询该病人是否在中途结算病人列表
     * @param zyh
     * @return
     */
//    Integer queryztjs(Integer zyh);

    /**
     * 住院号查询帐卡病人基本信息
     * @param zyh
     * @return
     */
//    Map<String, Object> queryCardBrxxMap(Integer zyh);

    /**
     * jgid和zyh查询科室代码
     * @Title: queryOfficeInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parametersksdm
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryOfficeInfo(Map<String, Object> parametersksdm);

    /**
     * imhzry修改护理级别
     * @Title: modifyLevelOfCare
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void modifyLevelOfCare(Map<String, Object> parameters);

    /**
     * 缴费管理-缴费处理-病人姓名模糊查询住院病人列表
     * @param queryCostPatientListByBrxmReq
     * @return
     */
    List<ImHzry> queryCostPatientListByBrxm(QueryCostPatientListByBrxmReq queryCostPatientListByBrxmReq);

    /**
     * 查询住院科室信息
     * @Title: queryZyKsInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzry
     * @param @return    设定文件
     * @return ImHzry    返回类型
     * @author 龚方舟
     * @throws
     */
    ImHzry queryZyKsInfo(ImHzry imHzry);

 	/**
 	 * 查询住院科室信息
 	 * @Title: updateZyCyfsStatus
 	 * @Description: TODO(这里用一句话描述这个方法的作用)
 	 * @param @param imHzry    设定文件
 	 * @return void    返回类型
 	 * @author 龚方舟
 	 * @throws
 	 */
 	void updateZyCyfsStatus(ImHzry imHzry);

    /**
     * 查询出院结算病人列表
     * @param jgid
     * @param zyhm
     * @return
     */
    List<BalanceAccountsListResp> cyjs(@Param("jgid") Integer jgid, @Param("zyhm") String zyhm,@Param("ywlx") String ywlx, @Param("brch") String brch);

    /**
     * 查询中途结算病人列表
     * @param jgid
     * @param zyhm
     * @return
     */
    List<BalanceAccountsListResp> ztjs(@Param("jgid") Integer jgid, @Param("zyhm") String zyhm,@Param("ywlx") String ywlx, @Param("zzrq") String zzrq,@Param("brch") String brch);

    /**
     * 查询发票作废病人列表
     *
     * @param jgid
     * @return
     */
    List<BalanceAccountsListResp> fpzf(@Param("jgid") Integer jgid, @Param("zyhm") String zyhm, @Param("ywlx") String ywlx,
                                       @Param("brch") String brch);

    /**
     * 判断当前病人是否在院
     * @param zyh
     * @param hospitalId
     * @return
     */
//    Integer isZybr(@Param("zyh") Integer zyh, @Param("jgid") Integer hospitalId);

    /**
     * 病人入院查询是否存在转科记录
     * @Title: queryIsZkjlCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryIsZkjlCount(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);


    /**
     * 病人入院查询是否存在会诊记录
     * @Title: queryIsExistHzCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryIsExistHzCount(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 更新住院病人入院转科出院状态
     * @Title: updateZkStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzry    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
 	void updateZkStatus(ImHzry imHzry);

 	/**
 	 * 取消更新住院病人入院转科状态
 	 * @Title: updateCancelZkStatus
 	 * @Description: TODO(这里用一句话描述这个方法的作用)
 	 * @param @param imHzry    设定文件
 	 * @return void    返回类型
 	 * @author 龚方舟
 	 * @throws
 	 */
 	void updateCancelZkStatus(ImHzry imHzry);

 	/**
 	 * 查询体温单未出院病人列表
 	 * @Title: queryTwdPatientInfo
 	 * @Description: TODO(这里用一句话描述这个方法的作用)
 	 * @param @param brbq
 	 * @param @return    设定文件
 	 * @return List<ImHzry>    返回类型
 	 * @author 龚方舟
 	 * @throws
 	 */
 	List<ImHzry> queryTwdPatientInfo(Integer brbq);

    /**
     * 查询医嘱提交病人出院判别
     * @Title: queryDischargediscrimination
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxh
     * @param @return    设定文件
     * @return ImHzry    返回类型
     * @author 龚方舟
     * @throws
     */
    ImHzry queryDischargediscrimination(Integer jlxh);


    /**
     * 住院号查询病人是否还在院
     * @param zyh
     * @return
     */
    Integer queryCountByZyh(Integer zyh);

    /**
     * 住院病人出院结算
     */
    void updateCyjs(ImZyjs imZyjs);

    /**
     * 住院病人中途结算
     */
    void updateZtjs(ImZyjs imZyjs);
/*    *//**
     * 住院自费病人结算
     * @param imHzryParameters
     *//*
    void updateZyjs(Map<String, Object> imHzryParameters);*/

    /**
     * 住院号查询病人id
     * @return
     */
    String queryCardBridMap(Integer zyh);


    /**
     * 数据盒会诊报告列表
     * @Title: queryHzbgList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyhm
     * @param @return    设定文件
     * @return List<YsZyHzBgResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<YsZyHzBgResp> queryHzbgList(String zyhm);

    /**
     * 结算作废-更新住院病人的出院判别跟结算日期
     */
    void updateCypbAndJsrq(ImHzry imHzry);

    /**
     * 通过住院号跟机构id修改住院病人床号
     * @param imHzryParameters
     */
    void updateBrchByZyhAndJgid(Map<String, Object> imHzryParameters);


    /**
     * 修改住院病人性质
     * @param imHzry
     */
    void updateZybrBrxz(ImHzry imHzry);

    /**
     * 床位分配信息修改
     * @param parametersbrry
     */
    void updateCwfp(ImHzry parametersbrry);

    /**
     * 设置入院日期
     * @param parametersbrryryrq
     */
    void updateRyrq(ImHzry parametersbrryryrq);

    /**
     * 查询没有分配床位的住院病人列表信息
     * @param imHzry
     * @return
     */
    List<ImHzry> queryToBedInfo(ImHzry imHzry);


    /**
     * 查询已出院记录
     * @Title: queryDischarged
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
	long queryDischarged(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

	/**
	 * 通知出院确认更新
	 * @Title: updateInformDischarge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateInformDischarge(Map<String, Object> parameters);

    /**
     * 根据病人住院号码或床号查询在院病人
     * @Title: queryPatientInfoByZyhmOrBrch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzry
     * @param @return    设定文件
     * @return ImHzry    返回类型
     * @author 龚方舟
     * @throws
     */
    ImHzry queryPatientInfoByZyhmOrBrch(ImHzry imHzry);

    /**
     * 根据zyh查询病人住院信息
     * @param zyhList
     * @return
     */
    List<ImHzry> queryByZyh(@Param("list") List<Integer> zyhList);

/*
    */
/**
     * 查询待发药病人信息
     *
     * @param cond
     * @return
     *//*

    List<QueryDispensingBrResp> queryDispensingBr(@Param("cond") QueryConditionDto cond);

    */
/**
     * 查询待退药病人信息
     *
     * @param jgid
     * @param yfsb
     * @param tybq
     * @return
     *//*

    List<QueryBackMedicineResp> queryRefundDrugBr(@Param("yfsb") Integer yfsb, @Param("tybq") Integer tybq, @Param("jgid") Integer jgid);
*/

    /**
     * 病人注销更新入院登记记录
     * @param doDelParam
     */
    void doDelBrry(ImHzry doDelParam);

/*    *//**
     * 查询病人信息
     *
     * @param cond
     * @return
     *//*
    ImHzry getByCond(@Param("cond") QueryConditionDto cond);*/

    /**
     * 住院管理-缴费管理(分页查询)
     * @param debtInventoryReq
     * @return
     */
    List<DebtInventoryResp> queryDebtInventory(DebtInventoryReq debtInventoryReq);

    /**
     * 查询未分配床位的病人列表
     * @param cwfpQueryReq
     * @return
     */
    List<QueryCwfpInfoZybrryPageResp> queryNoBrchList(CwfpQueryReq cwfpQueryReq);

    /**
     * 查询病人出院证表单信息
     * @Title: queryDischargeCertificateInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzry
     * @param @return    设定文件
     * @return ImHzryLeaveHospitalResp    返回类型
     * @author 龚方舟
     * @throws
     */
    ImHzryLeaveHospitalResp queryDischargeCertificateInfo(ImHzry imHzry);

    /**
     * 查询费用明细中 中途结算的记录
     * @return
     */
    List<BalanceAccountsListResp> fymxZtjs(@Param("jgid") Integer jgid, @Param("zyhm") String zyhm,@Param("ywlx") String ywlx);

    /**
     * 查询医保结算的开始时间
     * @param jgid
     * @param zyh
     * @return
     */
    Map<String, Object> queryYbksrq(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh, @Param("endDate") String endDate);

    /**
     * 住院号码查询住院信息
     * @param zyhm
     * @return
     */
    ImHzry getByZyhm(@Param("jgid")Integer jgid, @Param("zyhm") String zyhm);

    /**
     * 住院管理-日终汇总（取消汇总）
     * @param parameters
     */
    void doCancelCollectCommit(Map<String, Object> parameters);

    /**
     * 查询汇总次数
     * @param parametersll_count
     * @return
     */
    Integer queryHzCount(Map<String, Object> parametersll_count);

    /**
     * 修改汇总日期
     * @param parametersupd
     */
    void updateHzrq(Map<String, Object> parametersupd);

    /**
     * 汇总入院病人数量
     * @param parameter
     * @return
     */
    Map<String, Object> queryHzBrCount(Map<String, Object> parameter);

    /**
     * 汇总出院人数
     * @param parameter
     * @return
     */
    Map<String, Object> queryHzBrCountWithCypb(Map<String, Object> parameter);


    /**
     * 查询出院病人列表
     * @Title: queryDischargedPatients
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzryQueryReq
     * @param @return    设定文件
     * @return List<ImHzryDctworkResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryDctworkResp> queryDischargedPatients(ImHzryQueryReq imHzryQueryReq);

/*    *//**
     * 查询已审医嘱病人信息
     * @param jgid
     * @param req
     * *//*
    List<ImHzry> queryApprovedBryz(@Param("jgid") Integer jgid, @Param("req") QueryApprovedYzReq req);

    *//**
     * 查询待印签病人信息
     * @param jgid
     * @param req
     * *//*
    List<ImHzry> queryJpPrintLabelBr(@Param("req") QueryPrintLabelReq req, @Param("jgid") Integer jgid);

    *//**
     * 查询打包核对病人列表
     * @param req
     * *//*
    List<ImHzry> queryPackingCheckBr(QueryPackingCheckReq req);*/


    /**
     * 体温单查询病人基本信息
     * @Title: queryBasicInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryBasicInfo(Integer zyh);


    /**
     * 查询标签重打病人列表
     * */
/*    List<ImHzry> queryRepeatPrintLabelBr(QueryRepeatPrintLabelBrReq req);*/

    /**
     * 查询住院病人缴款收据打印参数
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryJksjPrintReportParams(Map<String, Object> parameters);

    /**
     * 就诊卡号查询在院患者信息
     * @param jzkh
     * @return
     */
    ImHzry getByJzkh(String jzkh);

    /**
     * 更新入院登记就诊单元号
     * @Title: updateJzdyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jzdyh    设定文件
     * @return void    返回类型
     * @author 卑军华
     * @throws
     */
    void updateJzdyh(String jzdyh, Integer zyh);

    /**
     * 自费转医保更新医保信息
     * @Title: updateZyybxx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jzdyh    设定文件
     * @return void    返回类型
     * @author 宋宇
     * @throws
     */
    void updateZyybxx(String jzdyh, String cardno, Integer zyh);

    /**
     * 医保转自费更新自费信息
     * @Title: updateZyzfxx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jzdyh    设定文件
     * @return void    返回类型
     * @author 宋宇
     * @throws
     */
    void updateZyzfxx(String cardno, Integer zyh);

    /**
     * 查询医保入院登记信息
     * @param zyhm
     * @return
     */
    List<Map<String, Object>> getRydjxx(String zyhm);

    /**
     * 定时任务查询在院病人
     * @Title: queryZyHzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cyrq
     * @param @return    设定文件
     * @return List<ImHzry>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzry> queryZyHzInfo(Timestamp cyrq);

    /**
     * 更新病人开始日期
     * @Title: updateKsrq
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param ksrq
     * @param @param zyh    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateKsrq(@Param("ksrq") Timestamp ksrq, @Param("zyh") Integer zyh);

    /**
     * 病区医嘱本打印病人信息
     * @Title: queryMedicalBookInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzryMedicalBookReq
     * @param @return    设定文件
     * @return List<ImHzry>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzry> queryMedicalBookInfo(ImHzryMedicalBookReq imHzryMedicalBookReq);


    /**
     * 查询医嘱卡片病人列表
     * @Title: queryMedicalCardInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<ImHzry>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzry> queryMedicalCardInfo(CisHzyzCardQueryReq req);
    

    /**
     * 查询首页今日手术
     * @Title: queryOperationHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<ImHzryOperaResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryOperaResp> queryOperationHomePage(@Param("bqdm") Integer bqdm, @Param("ksdm") Integer ksdm);
    

    /**
     * 查询首页待会诊申请
     * @Title: queryConsultedHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @return    设定文件
     * @return List<ImHzryConsultedResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryConsultedResp> queryConsultedHomePage(@Param("bqdm") Integer bqdm, @Param("ksdm") Integer ksdm,
    		@Param("jgid") Integer jgid);
    

    /**
     * 查询首页病人出入院信息
     * @Title: queryImHzryHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zt
     * @param @param jgid
     * @param @return    设定文件
     * @return List<ImHzryHomeResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryHomeResp> queryImHzryHomePage(ImHzryHomeReq imHzryHomeReq);
    

    /**
     * 查询首页病人问题医嘱
     * @Title: queryWardAdviceHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<ImHzryWardHomeResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryWardHomeResp> queryWardAdviceHomePage(@Param("bqdm") Integer bqdm, @Param("ksdm") Integer ksdm,
    		 @Param("clzt") Integer clzt);
    
    /**
     * 查询首页危机值信息
     * @Title: queryCriticalHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzryCriticalHomeReq
     * @param @return    设定文件
     * @return List<ImHzryCriticalHomeResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryCriticalHomeResp> queryCriticalHomePage(ImHzryCriticalHomeReq imHzryCriticalHomeReq);
    

    /**
     * 查询首页住院医生站待会诊申请
     * @Title: queryZyysConsultedHomePage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<ImHzryConsultedResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryConsultedResp> queryZyysConsultedHomePage(@Param("bqdm") Integer bqdm, @Param("ksdm") Integer ksdm,
    		@Param("jgid") Integer jgid, @Param("userId") Integer userId, @Param("clzt") Integer clzt);
    

    /**
     * 查询批量体温单录入患者信息
     * @Title: queryTemperatureHzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cjsj
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryTemperatureHzInfo(@Param("hour") Integer hour, @Param("queryDate") String queryDate, 
    		@Param("jgid") Integer jgid, @Param("bqdm") Integer bqdm);
    

	/**
	 * 病人转出转入等更新通知状态
	 * @Title: updateNoticeStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param hour
	 * @param @param queryDate    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateNoticeStatus(@Param("tzzt") Integer tzzt, @Param("tzgh") Integer tzgh,
			 @Param("zyh") Integer zyh);

    /**
     * 查询病区发药汇总
     * @Title: querySummaryDispensing
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisDispensingReq
     * @param @return    设定文件
     * @return List<NisDispensingResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisDispensingResp> querySummaryDispensing(NisDispensingReq nisDispensingReq);


    /**
     * 查询病区已发药的患者信息
     * @Title: queryHzDispensing
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisHzDispensingReq
     * @param @return    设定文件
     * @return List<NisHzDispensingResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzDispensingResp> queryHzDispensing(NisHzDispensingReq nisHzDispensingReq);


    /**
     * 病区发药按病人查询病人信息
     * @Title: queryPatientByDispensing
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param nisDispensingReq
     * @param @return    设定文件
     * @return List<ImHzry>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzry> queryPatientByDispensing(NisDispensingReq nisDispensingReq);


    /**
     * 查询一日清单病人信息
     * @Title: queryPatientByList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param bqdm
     * @param @param ksdm
     * @param @param zyhm
     * @param @return    设定文件
     * @return List<NisHzListResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<NisHzListResp> queryPatientByList(@Param("jgid") Integer jgid, @Param("bqdm") Integer bqdm,
    		@Param("ksdm") Integer ksdm, @Param("zyhm") String zyhm);

    /**
     * 大病医生信息
     * @Title: getYsxm
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param ysgh
     * @param @return    设定文件
     * @return List<>    返回类型
     * @author 卑军华
     * @throws
     */
    List<Map<String,Object>> getYsxm(String ysgh);
    

    /**
     * 查询检查申请单打印
     * @Title: queryPatientJcsqd
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jcxh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 龚方舟
     * @throws
     */
    Map<String, Object> queryPatientJcsqd(@Param("jgid") Integer jgid, @Param("jcxh") Integer jcxh);
    
    /**
     * 查询3天内新入院患者
     * @Title: queryNewAdmissionPatient
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brbq
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Integer> queryNewAdmissionPatient(@Param("brbq") Integer brbq, @Param("jgid") Integer jgid,
    		@Param("type") Integer type, @Param("queryDate") String queryDate);
    
    /**
     * 查询手术患者
     * @Title: querySsPatient
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brbq
     * @param @param jgid
     * @param @param type
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Integer> querySsPatient(@Param("brbq") Integer brbq, @Param("jgid") Integer jgid,
    		@Param("type") Integer type, @Param("queryDate") String queryDate);
    

    /**
     * 查询发热患者
     * @Title: queryFeverPatient
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brbq
     * @param @param jgid
     * @param @param type
     * @param @param datetime
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Integer> queryFeverPatient(@Param("brbq") Integer brbq, @Param("jgid") Integer jgid,
    		@Param("type") Integer type, @Param("datetime") Timestamp datetime);
    

    /**
     * 根据身份证查询患者历史信息
     * @Title: queryPatientByIdCard
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param sfzh
     * @param @return    设定文件
     * @return List<ImHzry>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzry> queryPatientByIdCard(@Param("jgid") Integer jgid, @Param("sfzh") String sfzh,
    		 @Param("beginDate") String beginDate, @Param("endDate") String endDate);

    /**
     * 修改门诊住院登记单 状态为 已入院
     * 分布式事务问题 无法回滚替代方案
     */
    void updateSqzt(OpZydjUpdateStatusReq req);

    /**
     * 大病打印
     * @Title: queryDbxx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jcxh
     * @param @return    设定文件
     * @return Map<String,Object>    返回类型
     * @author 卑军华
     * @throws
     */
    Map<String, Object> queryDbxx(String jzdyh);


    /**
     * 查询给药记录单病人信息
     * @Title: queryAdministrationPatientInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param brbq
     * @param @param zyhList
     * @param @param lsyz
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryAdministrationPatientInfo(@Param("jgid") Integer jgid, @Param("brbq") Integer brbq,
    		@Param("zyhList") List<Integer> zyhList, @Param("lsyz") Integer lsyz);
    

    /**
     * 查询出入院数量
     * @Title: queryInAndOutCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param type
     * @param @param kssj
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryInAndOutCount(@Param("jgid") Integer jgid, @Param("type") Integer type,
    		@Param("kssj") Timestamp kssj, @Param("jssj") Timestamp jssj, @Param("bqdm") Integer bqdm);
    

    /**
     * 查询预手术、手术数
     * @Title: queryPreOperationCount
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
    long queryPreOperationCount(@Param("jgid") Integer jgid, @Param("type") Integer type,
    		@Param("kssj") Timestamp kssj, @Param("jssj") Timestamp jssj, @Param("bqdm") Integer bqdm);
    

    /**
     * 根据病区查询科室
     * @Title: queryCardBrksByBrbq
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param brbq
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzCardKsResp> queryCardBrksByBrbq(@Param("brbq") Integer brbq, @Param("jgid") Integer jgid);
    

    /**
     * 病案首页查询出院病人信息
     * @Title: queryImHzryBasy
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param imHzryBaReq
     * @param @return    设定文件
     * @return List<ImHzryBaResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImHzryBaResp> queryImHzryBasy(ImHzryBaReq imHzryBaReq);
    

    /**
     * pacs查询病人信息
     * @Title: queryPacsPatientInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @return    设定文件
     * @return List<ImPacsPatientInfoResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<ImPacsPatientInfoResp> queryPacsPatientInfo(ImPacsPatientInfoReq req);

    /**
     * 费用明细上传获取错误信息
     *
     * @param zyh
     * @return
     */
    List<Map<String, Object>> getFycwxh(@Param("zyh") Integer zyh);

    /**
     * 查询病区发药明细
     */
    List<NisDispensingDetailResp> querySummaryDispensingDetail(NisDispensingDetailReq req);

    /**
     * 查询出院患者-电子病历用
     * @param imHzryEmrReq
     * @return
     */
    List<ImHzryEmrResp> queryImHzryEmr(ImHzryEmrReq imHzryEmrReq);

    /**
     * 查询在院病人
     */
    ImHzry getInHospitalPatient(ImHzry query);
}
