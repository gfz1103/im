package com.buit.cis.dctwork.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.buit.cis.dctwork.model.CisHzyz;
import com.buit.cis.dctwork.request.CisHzyzDataReq;
import com.buit.cis.dctwork.request.CisHzyzOrderSetReq;
import com.buit.cis.dctwork.request.CisHzyzQueryApiReq;
import com.buit.cis.dctwork.response.CisHzyzFyjlResp;
import com.buit.cis.dctwork.response.CisHzyzHerbResp;
import com.buit.cis.dctwork.response.CisHzyzKcdjResp;
import com.buit.cis.dctwork.response.CisHzyzOperationResp;
import com.buit.cis.dctwork.response.CisHzyzOrderBookResp;
import com.buit.cis.dctwork.response.CisHzyzQueryResp;
import com.buit.cis.dctwork.response.CisHzyzResp;
import com.buit.cis.nurse.request.CisHzyzChangeReq;
import com.buit.cis.nurse.request.CisHzyzExcuteReq;
import com.buit.cis.nurse.request.CisHzyzReviewBatchReq;
import com.buit.cis.nurse.request.CisHzyzReviewUpdateReq;
import com.buit.cis.nurse.request.CisHzyzStopUpdateReq;
import com.buit.cis.nurse.request.CisHzyzorztExcuteReq;
import com.buit.cis.nurse.response.CisHzyzCancelPrintResp;
import com.buit.cis.nurse.response.CisHzyzChangeResp;
import com.buit.cis.nurse.response.CisHzyzOrztResp;
import com.buit.cis.nurse.response.CisHzyzReviewInfoResp;
import com.buit.commons.EntityDao;
import com.buit.his.treatment.model.ZlRwfpUpdateReq;

/**
 * 住院_病区医嘱<br>
 *
 * @author GONGFANGZHOU
 */

@Mapper
@Repository
public interface CisHzyzDao extends EntityDao<CisHzyz, Integer> {

    /**
     * 根据记录序号集合查询病区医嘱
     *
     * @param @param  jlxh
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryBqyzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryBqyzInfo(@Param("jlxhlist") List<Long> jlxh);


    /**
     * 医嘱删除
     *
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: deleteOrdersByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void deleteOrdersByJlxh(@Param("jlxh") Long jlxh, @Param("yzlx") Integer yzlx);


    /**
     * 查询病区医嘱有关物质信息
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryWardMaterialsInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryWardMaterialsInfo(Map<String, Object> parameters);

    /**
     * 查询护理级别名称
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryLevelOfCare
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryLevelOfCare(Map<String, Object> parameters);

    /**
     * 医生提交更新提交状态
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: updateBqyzSubmitStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Integer updateBqyzSubmitStatus(Map<String, Object> parameters);

    /**
     * 查询医嘱中所有需要审批的抗菌药物的数量
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryAntibacterialsCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryAntibacterialsCount(Map<String, Object> parameters);

    /**
     * 根据组套号取消医生提交状态
     *
     * @param @param  jlxh
     * @param @param  ystj
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: cancelSubmitStatusByZtxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long cancelSubmitStatusByZtxh(@Param("ystjnew") Integer ystjnew, @Param("yzzhList") List<Integer> yzzhList,
                                  @Param("bzxx") String bzxx);

    /**
     * 根据记录序号取消医生提交状态
     *
     * @param @param  jlxh
     * @param @param  ystj
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: cancelSubmitStatusByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long cancelSubmitStatusByJlxh(@Param("ystjnew") Integer ystjnew, @Param("jlxh") Integer jlxh,
                                  @Param("ystj") Integer ystj, @Param("bzxx") String bzxx);

    /**
     * 取消医嘱停嘱
     *
     * @param @param zyh
     * @param @param yzzh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: cancelStopOrder
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void cancelStopOrder(@Param("zyh") Integer zyh, @Param("yzzh") Integer yzzh);

    /**
     * 取消医嘱停嘱(包含记录序号)
     *
     * @param @param zyh
     * @param @param yzzh
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: cancelStopOrderM
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void cancelStopOrderM(@Param("zyh") Integer zyh, @Param("yzzh") Integer yzzh, @Param("jlxh") Integer jlxh);

    /**
     * 医嘱作废或取消作废根据组套记录序号
     *
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @throws
     * @Title: invalidByZtJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void invalidByZtJlxh(@Param("yzzf") Integer yzzf, @Param("zfgh") String zfgh,
                         @Param("zfsj") Timestamp zfsj, @Param("jlxhList") List<Integer> jlxhList);

    /**
     * 医嘱作废或取消作废根据记录序号
     *
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @throws
     * @Title: invalidByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void invalidByJlxh(@Param("yzzf") Integer yzzf, @Param("zfgh") String zfgh,
                       @Param("zfsj") Timestamp zfsj, @Param("jlxhList") List<Integer> jlxhList);

    /**
     * 查询执行过的医嘱和组套
     *
     * @param @param  jlxh
     * @param @return 设定文件
     * @return List<CisHzyzResp>    返回类型
     * @throws
     * @Title: queryBqyzExecuted
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzResp> queryBqyzExecuted(Integer jlxh);

    /**
     * 已执行医嘱取消更新
     *
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateBqyzExecuted
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateBqyzExecuted(String jlxh);

    /**
     * 查询自备药条数
     *
     * @param @param  cisHzyz
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryPreparedMedicineCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryPreparedMedicineCount(CisHzyz cisHzyz);

    /**
     * 更新自备药医嘱状态
     *
     * @param @param  cisHzyz
     * @param @return 设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: updatePreparedMedicine
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Integer updatePreparedMedicine(CisHzyz cisHzyz);

    /**
     * 查询最大的医嘱组号
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryMaxYzzh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryMaxYzzh(Integer zyh);

    /**
     * 更新医嘱名称和开始时间
     *
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateOrdersAndTime
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateOrdersAndTime(Map<String, Object> parameters);

    /**
     * 查询是否存在皮试记录
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryIsExistPs
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryIsExistPs(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 查询历史标志不等于1的医嘱
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryHistoricalOrders
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryHistoricalOrders(Integer zyh);

    /**
     * 更新医嘱为历史医嘱
     *
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateToHistoricalOrders
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateToHistoricalOrders(Integer jlxh);

    /**
     * 未停未提交医嘱表单检索数据
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryNotStopOrNotSubmitYz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryNotStopOrNotSubmitYz(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 药品医嘱未提交未发药
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryNotSubmitOrNotSendYz
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryNotSubmitOrNotSendYz(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 项目医嘱未提交或未执行
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryNotSubmitOrNotExcuteXm
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryNotSubmitOrNotExcuteXm(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);

    /**
     * 查询已提交医嘱
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryBqyzSubmit
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryBqyzSubmit(Map<String, Object> parameters);

    /**
     * 将指定医嘱修改为历史医嘱
     *
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @throws
     * @Title: modifyToHistoricalAppointOrders
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void modifyToHistoricalAppointOrders(Map<String, Object> parameters);

    /**
     * 更新医嘱使用标志
     *
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateUseLogoStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateUseLogoStatus(Map<String, Object> parameters);

    /**
     * 查询医嘱总的使用数量
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryBqUseSum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryBqUseSum(Map<String, Object> parameters);

    /**
     * 数据盒查询临时长期变更医嘱
     *
     * @param @param  cisHzyzDataReq
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryBqyzByDataBox
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryBqyzByDataBox(CisHzyzDataReq cisHzyzDataReq);

    /**
     * 查询其他医生需要复核的医嘱
     *
     * @param @param  cisHzyz
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryReviewByOther
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryReviewByOther(CisHzyz cisHzyz);

    /**
     * 更新复核状态(非同一个工号)
     *
     * @param @param  cisHzyz
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: updateReviewStatusByOther
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long updateReviewStatusByOther(CisHzyz cisHzyz);

    /**
     * 批量取消医嘱复核
     * @Title: cancelYzReviewStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jlxhList
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long cancelYzReviewStatus(@Param("jgid") Integer jgid, @Param("jlxhList") List<Integer> jlxhList);


    /**
     * 查询复核需执行医嘱记录条数
     *
     * @param @param  cisHzyzExcuteReq
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryBqyzExcutedAndReviewCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryBqyzExcutedAndReviewCount(CisHzyzExcuteReq cisHzyzExcuteReq);


    /**
     * 查询附加医嘱信息
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryFjBqyzInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryFjBqyzInfo(Map<String, Object> parameters);


    /**
     * 查询复核需执行医嘱
     *
     * @param @param  cisHzyzorztExcuteReq
     * @param @return 设定文件
     * @return List<CisHzyzOrztResp>    返回类型
     * @throws
     * @Title: queryBqyzExcutedAndReview
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzOrztResp> queryBqyzExcutedAndReview(CisHzyzorztExcuteReq cisHzyzorztExcuteReq);

    /**
     * 查询费用医嘱附加计费单列表
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryBqyzBillingSheet
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryBqyzBillingSheet(Map<String, Object> parameters);


    /**
     * 更新确认时间和历史标志
     *
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateTimeAndLsByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateTimeAndLsByJlxh(Map<String, Object> parameters);

    /**
     * 查询已确认的医嘱
     *
     * @param @param  ztyzjlxh
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryCountByZtjlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryCountByZtjlxh(Integer ztyzjlxh);

    /**
     * 判断是否有药品医嘱未停或未发药或是否有项目医嘱未停或未执行
     *
     * @param @param  zyh
     * @param @param  jgid
     * @param @param  yplx
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: queryYzXmNotSureOrExcuted
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long queryYzXmNotSureOrExcuted(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid, @Param("yplx") Integer yplx);

    /**
     * 查询是否存在费用明细
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryCostDetail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryCostDetail(Integer zyh);

    long queryCostDetailCnt(Integer zyh);

    List<CisHzyz> queryCostDetailInfo(Integer zyh);
    /**
     * 项目执行按病人列表查询
     *
     * @param @param  cisHzyzorztExcuteReq
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryPatientInfoByProject
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryPatientInfoByProject(CisHzyzorztExcuteReq cisHzyzorztExcuteReq);


    /**
     * 附加计价医嘱明细列表
     *
     * @param @param  cisHzyzExcuteReq
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryAdditionalPriced
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryAdditionalPriced(CisHzyzExcuteReq cisHzyzExcuteReq);

    /**
     * 医嘱批量复核开医嘱列表
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @param  zyhm
     * @param @return 设定文件
     * @return List<CisHzyzReviewInfoResp>    返回类型
     * @throws
     * @Title: queryBatchReviewOpen
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzReviewInfoResp> queryBatchReviewOpen(CisHzyzReviewBatchReq req);

    /**
     * 医嘱批量复核停医嘱列表
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @param  zyhm
     * @param @return 设定文件
     * @return List<CisHzyzReviewInfoResp>    返回类型
     * @throws
     * @Title: queryBatchReviewStop
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzReviewInfoResp> queryBatchReviewStop(CisHzyzReviewBatchReq req);

    /**
     * 医嘱复核前开医嘱查询
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryBeforeBatchReviewOpen
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryBeforeBatchReviewOpen(@Param("jgid") Integer jgid, @Param("srks") Integer srks,
    		 @Param("lsyz") Integer lsyz, @Param("zyhm") String zyhm);

    /**
     * 医嘱复核前停医嘱查询
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryBeforeBatchReviewStop
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryBeforeBatchReviewStop(@Param("jgid") Integer jgid, @Param("srks") Integer srks,
    		@Param("lsyz") Integer lsyz, @Param("zyhm") String zyhm);


    /**
     * 更新医嘱停嘱复核状态
     *
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateBqyzStopOrderReview
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateBqyzStopOrderReview(CisHzyz cisHzyz);


    /**
     * 病区医嘱列表查询
     *
     * @param @param  type
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryWardOrderList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzQueryResp> queryWardOrderList(CisHzyzQueryApiReq cisHzyzQueryReq);


    /**
     * 根据yzxh查询病区医嘱
     *
     * @param yzxhList
     * @return
     * @throws
     */
    List<CisHzyz> queryByYzxh(@Param("list") List<Integer> yzxhList);

    /**
     * 查询药品用法序号
     *
     * @param @param  jlxh
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryDrugUsage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryDrugUsage(Integer jlxh);

    /**
     * 查询病人未停医嘱的数量
     *
     * @param zyh
     * @return
     */
    Integer findwtyzCount(Integer zyh);


    /**
     * 查询药品当天使用数量总和
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryDrugSumSameDay
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryDrugSumSameDay(Map<String, Object> parameters);

    /**
     * 判断是否有药品医嘱未停或未发药或是否有项目医嘱未停或未执行列表
     *
     * @param @param  zyh
     * @param @param  @ApiParam(name = "zyh", value = "住院号", required = true)
     * @param @param  yplx
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryYzXmNotSureOrExcutedInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryYzXmNotSureOrExcutedInfo(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid, @Param("yplx") Integer yplx);


    /**
     * 根据医嘱组号查询对应附加项目
     *
     * @param @param  cisHzyz
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryAdditionalAdvice
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryAdditionalAdvice(CisHzyz cisHzyz);


    /**
     * 更新皮试结果
     *
     * @param @param  psjg
     * @param @param  jlxh
     * @param @param  jgid
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: updateSkinTestByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long updateSkinTestByJlxh(@Param("psjg") Integer psjg, @Param("jlxh") Integer jlxh, @Param("jgid") Integer jgid);

    /**
     * 检查项目医嘱删除
     *
     * @param @param sqid
     * @param @param yzlx    设定文件
     * @return void    返回类型
     * @throws
     * @Title: deleteOrdersBySqid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void deleteOrdersBySqid(@Param("sqid") Integer sqid, @Param("yzlx") Integer yzlx);

    /**
     * 备血申请医嘱删除
     *
     * @param @param jlxh
     * @param @param yzlx    设定文件
     * @return void    返回类型
     * @throws
     * @Title: deleteSxsqByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void deleteSxsqByJlxh(@Param("jlxh") Long jlxh, @Param("yzlx") Integer yzlx);

    /**
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: deleteJyYzByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void deleteBatchByJlxhList(@Param("jlxhList") List<Integer> jlxhList);

    /**
     * 查询需要皮试医嘱
     *
     * @param @param  jgid
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryIsNeedSkinTest
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryIsNeedSkinTest(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh, @Param("lsyz") Integer lsyz);


    /**
     * <!-- 更新复核状态(除去未做皮试结果医嘱) -->
     *
     * @param @param  cisHzyz
     * @param @param  yzzhList
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: updateReviewNotExtisSkinTest
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    long updateReviewNotExtisSkinTest(@Param("req") CisHzyz cisHzyz, @Param("yzzhList") List<Integer> yzzhList);

    /**
     * 批量查询查询需要皮试医嘱
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryIsNeedSkinTestBatch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryIsNeedSkinTestBatch(@Param("jgid") Integer jgid, @Param("srks") Integer srks,
    		@Param("jlxhList") List<Integer> jlxhList);

    /**
     * 查询草药方医嘱信息
     *
     * @param @param  zyh
     * @param @param  yzzh
     * @param @return 设定文件
     * @return List<CisHzyzHerbResp>    返回类型
     * @throws
     * @Title: queryHerbInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzHerbResp> queryHerbInfo(@Param("zyh") Integer zyh, @Param("yzzh") Integer yzzh);

    /**
     * 查询医嘱本打印医嘱信息
     *
     * @param @param  yzlx
     * @param @param  zyh
     * @param @param  yzzt
     * @param @param  type
     * @param @return 设定文件
     * @return List<CisHzyzOrderBookResp>    返回类型
     * @throws
     * @Title: queryMedicalOrderInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzOrderBookResp> queryMedicalOrderInfo(@Param("yzlx") Integer yzlx, @Param("zyh") Integer zyh,
                                                     @Param("yzzt") Integer yzzt, @Param("type") Integer type, @Param("jgid") Integer jgid,
                                                     @Param("sftj") Integer sftj);


    /**
     * 更新重复医嘱打印状态
     *
     * @param @param dyzt
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateDyzt
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateDyztFilter(@Param("dyzt") Integer dyzt, @Param("jlxhlist") Set<Integer> jlxh);

    /**
     * 更新医嘱打印状态
     *
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateDyzt
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateDyzt(@Param("jlxhlist") List<Integer> jlxh);

    /**
     * 根据记录序号更新医嘱打印状态
     *
     * @param @param dyzt
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateDyztByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateDyztByJlxh(@Param("dyzt") Integer dyzt, @Param("jlxh") Integer jlxh);

    /**
     * 查询需打印的重整医嘱信息
     *
     * @param @param  yzlx
     * @param @param  zyh
     * @param @param  jgid
     * @param @param  sftj
     * @param @return 设定文件
     * @return List<CisHzyzOrderBookResp>    返回类型
     * @throws
     * @Title: queryRenewOrderInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzOrderBookResp> queryRenewOrderInfo(@Param("yzlx") Integer yzlx, @Param("zyh") Integer zyh,
                                                   @Param("jgid") Integer jgid, @Param("sftj") Integer sftj);


    /**
     * 查询需要打印的组套数据
     *
     * @param @param  ztyzjlxh
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryZtOrderPrintInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    Map<String, Object> queryZtOrderPrintInfo(Integer ztyzjlxh);


    /**
     * 根据记录序号或者组套记录序号查询
     *
     * @param sbxh
     * @param ztbz
     * @return
     */
    List<CisHzyz> doQueryByJlxhOrZtjlxh(Integer sbxh, Integer ztbz);


    /**
     * 会诊申请更新医嘱提交状态
     *
     * @param @param parameters    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateHzsqSubmitStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateHzsqSubmitStatus(Map<String, Object> parameters);


    /**
     * 根据申请id取消会诊医嘱提交状态
     *
     * @param @param sqid
     * @param @param yzlx    设定文件
     * @return void    返回类型
     * @throws
     * @Title: cancelHzSubmitStatusBySqid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void cancelHzSubmitStatusBySqid(@Param("sqid") Integer sqid, @Param("yzlx") Integer yzlx);


    /**
     * 根据记录序号更新抗菌药物状态
     *
     * @param @param kjywsp
     * @param @param syly
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateAntibioticsStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateAntibioticsStatus(@Param("kjywsp") Integer kjywsp, @Param("syly") String syly,
                                 @Param("jlxh") Integer jlxh);


    /**
     * 提交查询是否存在未审批或刚开的抗菌药物
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryIsExistAntimicrobial
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryIsExistAntimicrobial(Map<String, Object> parameters);


    /**
     * 根据申请id更新抗菌药物医嘱
     *
     * @param @param kjywsp
     * @param @param sqid    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateYzAntibiotics
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateYzAntibiotics(@Param("kjywsp") Integer kjywsp, @Param("sqid") Integer sqid);


    /**
     * 抗菌药物申请单更新到医嘱
     *
     * @param @param sqid
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateAntibioticsSqidByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateAntibioticsSqidByJlxh(@Param("sqid") Integer sqid, @Param("jlxh") Integer jlxh);


    /**
     * 更新医嘱的抗菌药物状态和申请id
     *
     * @param @param sqid    设定文件
     * @return void    返回类型
     * @throws
     * @Title: removeAntibioticsStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void removeAntibioticsStatus(Integer sqid);


    /**
     * 查询医嘱口服固定卡片打印信息
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryMedicalCardInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryMedicalCardInfo(Map<String, Object> parameters);


    /**
     * 查询医嘱口服执行单打印信息
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryOralCardZxInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryOralCardZxInfo(Map<String, Object> parameters);


    /**
     * 查询取消打印数据
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<CisHzyzCancelPrintResp>    返回类型
     * @throws
     * @Title: queryCancelPrintList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzCancelPrintResp> queryCancelPrintList(Map<String, Object> parameters);


    /**
     * 查询医嘱会诊申请单号
     *
     * @param @param  parameters
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryHzSqId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryHzSqId(Map<String, Object> parameters);


    /**
     * 查询皮试类别
     *
     * @param @param  zyh
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryPslb
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryPslb(Integer zyh);


    /**
     * 问题医嘱通知更新
     *
     * @param @param tzgh
     * @param @param zyh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateYzProblemStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateYzProblemStatus(@Param("tzgh") Integer tzgh, @Param("zyh") Integer zyh);


    /**
     * 查询变动医嘱
     *
     * @param @param  cisHzyzChangeReq
     * @param @return 设定文件
     * @return List<CisHzyzChangeResp>    返回类型
     * @throws
     * @Title: queryChangeOrders
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyzChangeResp> queryChangeOrders(CisHzyzChangeReq cisHzyzChangeReq);


    /**
     * 查询未审批或审批不通过的抗菌药物
     *
     * @param @param  jgid
     * @param @param  zyh
     * @param @param  lsyz
     * @param @return 设定文件
     * @return List<CisHzyz>    返回类型
     * @throws
     * @Title: queryNotApprovedKjyw
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<CisHzyz> queryNotApprovedKjyw(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh, @Param("lsyz") Integer lsyz);


    /**
     * 批量查询抗菌药物未审批或审批不通过
     *
     * @param @param  jgid
     * @param @param  srks
     * @param @return 设定文件
     * @return List<Map < String, Object>>    返回类型
     * @throws
     * @Title: queryNotApprovedKjywBatch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    List<Map<String, Object>> queryNotApprovedKjywBatch(@Param("jgid") Integer jgid, @Param("srks") Integer srks,
    		@Param("jlxhList") List<Integer> jlxhList);


    /**
     * 更新抗菌药物医嘱提交状态
     *
     * @param @param jlxh    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateAntibioticsTjByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 龚方舟
     */
    void updateAntibioticsTjByJlxh(Integer jlxh);
    
    /**
     * 根据住院号查询药品用法
     * @Title: queryYpyfByZyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param lsyz
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryYpyfByZyh(@Param("zyh") Integer zyh, @Param("lsyz") Integer lsyz,
    		@Param("jgid") Integer jgid);
    

    /**
     * 查询给药记录单药品信息
     * @Title: queryAdministrationRecord
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param lsyz
     * @param @param ypyf
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryAdministrationRecord(@Param("zyh") Integer zyh, @Param("lsyz") Integer lsyz,
    		@Param("ypyf") Integer ypyf, @Param("jgid") Integer jgid);
    

    /**
     * 根据住院号查询历史医嘱信息
     * @Title: queryMedicalAdviceInfoByZyh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param zyh
     * @param @return    设定文件
     * @return List<CisHzyz>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyz> queryMedicalAdviceInfoByZyh(@Param("jgid") Integer jgid, @Param("zyh") Integer zyh);
    

    /**
     * 查询饮食医嘱信息
     * @Title: queryDietList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param zyhList
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryDietList(@Param("jgid") Integer jgid, @Param("zyhList") List<Integer> zyhList,
    		@Param("lsyz") Integer lsyz, @Param("zyh") Integer zyh);
    

    /**
     * 判断是否有手术未安排
     * @Title: queryOperationNotArrangeCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryOperationNotArrangeCount(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 查询未安排手术信息
     * @Title: queryOperationNotArrange
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return List<CisHzyzOperationResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzOperationResp> queryOperationNotArrange(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);
    
    /**
     * 查询医嘱治疗单打印信息
     * @Title: queryTreatmentZxInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryTreatmentZxInfo(Map<String, Object> parameters);
    

	/**
	 * 查询治疗项目医嘱
	 * @Title: queryTreatmentProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyz
	 * @param @return    设定文件
	 * @return List<Integer>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ZlRwfpUpdateReq> queryTreatmentProject(CisHzyz cisHzyz);
	

	/**
	 * 更新治疗项目医嘱类型
	 * @Title: updateTreatmentProjectByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yzjlxhList    设定文件
	 * @return void    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	void updateTreatmentProjectByJlxh(@Param("yzjlxhList") List<Integer> yzjlxhList);
	
	/**
	 * g
	 * @Title: queryCareInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<Map<String, Object>> queryCareInfo(Map<String, Object> parameters);
	
    /**
     * 查询文字医嘱最大序号
     * @Title: queryWrittenOrdersMaxXh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long queryWrittenOrdersMaxXh(Integer jgid);
    
    /**
     * 病区组套医嘱列表查询
     * @Title: queryOrderSetList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyzOrderSetReq
     * @param @return    设定文件
     * @return List<CisHzyzQueryResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzQueryResp> queryOrderSetList(CisHzyzOrderSetReq cisHzyzOrderSetReq);
    

    /**
     * 作废附加项目
     * @Title: invalidAdditionalByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void invalidAdditionalByJlxh(CisHzyz cisHzyz);
    
    /**
     * 作废组套附加项目
     * @Title: invalidAdditionalByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param cisHzyz    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void invalidAdditionalZtByJlxh(CisHzyz cisHzyz);
    

	/**
	 * 根据记录序号查询治疗项目医嘱
	 * @Title: queryTreatmentProjectByJlxh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jgid
	 * @param @param jlxhList
	 * @param @return    设定文件
	 * @return List<ZlRwfpUpdateReq>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<ZlRwfpUpdateReq> queryTreatmentProjectByJlxh(@Param("jgid") Integer jgid, @Param("jlxhList") List<Integer> jlxhList,
			 @Param("yzlx") Integer yzlx, @Param("fhbz") Integer fhbz);
	

	/**
	 * 查询医嘱口服固定卡片打印信息
	 * @Title: queryOralCardInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param parameters
	 * @param @return    设定文件
	 * @return List<Map<Strin,Object>>    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	List<Map<String, Object>> queryOralCardInfo(Map<String, Object> parameters);
	

	/**
	 * 更新复停嘱核状态(非同一个工号)
	 * @Title: updateStopOrderReview
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param cisHzyz    设定文件
	 * @return long    返回类型
	 * @author 龚方舟
	 * @throws
	 */
    long updateStopOrderReview(CisHzyzStopUpdateReq cisHzyzStopUpdateReq);
    

    /**
     * 根据记录序号更新药品使用标志
     * @Title: updateSybzByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param yzxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateSybzByJlxh(@Param("jgid") Integer jgid, @Param("yzxhList") List<Integer> yzxhList);
    

    /**
     * 根据记录序号更新停嘱医生和时间
     * @Title: updateStopOrdersByJlxh
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param tzys
     * @param @param tzsj
     * @param @param jlxhList    设定文件
     * @return void    返回类型
     * @author 龚方舟
     * @throws
     */
    void updateStopOrdersByJlxh(@Param("tzys") String tzys, @Param("tzsj") Timestamp tzsj, 
    		@Param("jlxhList") List<Integer> jlxhList);

    /**
     * @Description 根据记录序号取消停嘱医生和时间
     * @Title  cancelStopOrdersByJlxh
     * @author  gfz
     * @Date  2021/6/4 22:14
     * @param jlxhList
     * @Return  void
     * @Exception
    */
    void cancelStopOrdersByJlxh(@Param("jlxhList") List<Integer> jlxhList);

    /**
     * @Description 根据主键批量删除
     * @Title  deleteBatchById
     * @author  gfz
     * @Date  2021/6/6 23:41
     * @param jlxhList
     * @Return  void
     * @Exception
    */
    void deleteBatchById(@Param("jlxhList") List<Integer> jlxhList);

     /** 查询存在的会诊申请
     * @Title: queryYzHzsq
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<Integer>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Integer> queryYzHzsq(Map<String, Object> parameters);

    
    /**
     * 附加项目医嘱费用明细列表
     * @Title: queryAdditionalItemsFymx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jlxhList
     * @param @param jgid
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<Map<String, Object>> queryAdditionalItemsFymx(@Param("yzzhList") List<Integer> yzzhList,
    		@Param("jgid") Integer jgid);
    

    /**
     * 查询发药明细记录
     * @Title: queryFyjlFymx
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    设定文件
     * @return List<CisHzyzFyjlResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzFyjlResp> queryFyjlFymx(Integer jlid);


    /**
     * @Description 批量取消已提交医嘱
     * @Title  cancelSubmitStatusByZtJlxhList
     * @author  gfz
     * @Date  2021/6/21 22:45
     * @param jlxhList
     * @Return  void
     * @Exception
    */
    void cancelSubmitStatusByZtJlxhList(@Param("jlxhList") List<Integer> jlxhList);

    /**
     * @Description 查询非组套未复核医嘱
     * @Title  queryPtNotReview
     * @author  gfz
     * @Date  2021/6/21 23:02
     * @param jlxhList
     * @Return  java.util.List<java.lang.Integer>
     * @Exception
    */
    List<Integer> queryPtNotReview(@Param("jlxhList") List<Integer> jlxhList);

    /**
     * @Description 非组套批量取消已提交医嘱
     * @Title  cancelSubmitStatusByJlxhList
     * @author  gfz
     * @Date  2021/6/21 23:11
     * @param jlxhList
     * @Return  void
     * @Exception
    */
    void cancelSubmitStatusByJlxhList(@Param("jlxhList") List<Integer> jlxhList);
    
    /**
     * 批量取消组套医嘱复核
     * @Title: cancelYzReviewStatus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param jgid
     * @param @param jlxhList
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long cancelYzZtReviewStatus(@Param("jgid") Integer jgid, @Param("jlxhList") List<Integer> jlxhList);
    

    /**
     * 批量更新复核状态
     * @Title: updateReviewStatusBatch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long updateReviewStatusBatch(CisHzyzReviewUpdateReq req);
    
    /**
     * 批量更新复核状态(除去未做皮试结果医嘱)
     * @Title: updateNotExtisSkinTestBatch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param req
     * @param @param yzzhList
     * @param @return    设定文件
     * @return long    返回类型
     * @author 龚方舟
     * @throws
     */
    long updateNotExtisSkinTestBatch(@Param("req") CisHzyzReviewUpdateReq req, 
    		@Param("yzzhList") List<Integer> yzzhList);
    
    /**
     * 查询药品库存冻结信息
     * @Title: queryYpKcdjInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param parameters
     * @param @return    设定文件
     * @return List<CisHzyzKcdjResp>    返回类型
     * @author 龚方舟
     * @throws
     */
    List<CisHzyzKcdjResp> queryYpKcdjInfo(Map<String, Object> parameters);
    

    /**
     * 查询未复核出院医嘱
     * @Title: queryNotReviewedDischargeOrder
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param zyh
     * @param @param jgid
     * @param @return    设定文件
     * @return String    返回类型
     * @author 龚方舟
     * @throws
     */
    String queryNotReviewedDischargeOrder(@Param("zyh") Integer zyh, @Param("jgid") Integer jgid);


    List<Map<String, Object>> querySpecialMedicalDiet(@Param("jgid") Integer jgid, 
    		@Param("zyhList") List<Integer> zyhList);
}
