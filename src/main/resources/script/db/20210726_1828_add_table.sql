CREATE TABLE `im_operate_log`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号',
  `CATEGORY` int(6) NOT NULL COMMENT '操作业务类型 | 0：患者状态变更，1：医嘱状态变更，2：医技申请状态变更，3：单据打印状态变更',
  `TYPE` int(6) NULL COMMENT '操作对象类别：\r\n医技申请 | 0：会诊申请，1：手术申请，2：备血申请，3：检查申请，4：检验申请，5：抗菌药物使用申请\r\n\r\n单据打印 | 0：口服卡，1：注射卡，2：静滴卡，3：营养卡，4：治疗单，5：汇总治疗单，6：输液巡视卡，7：给药记录单，8：饮食单，9：其他，10：检查/检验申请单',
  `STATUS` int(6) NOT NULL COMMENT '操作类型：\r\n患者信息变更 | 0：入院登记，1：床位分配，2：转床，3：性质转换，4：出院通知，5：结算出院，6：转科\r\n医嘱信息变更 | 0：新开/编辑，1：提交，2：复核，3：取消复核，4：执行，5：取消执行，6：停嘱，7：停嘱复核，8：停嘱取消复核，9：作废（医生）/退回（护士退回到医生），10：删除\r\n医技申请 | 0：新开/修改，1：审批通过 2：审批不通过 3：预约 4：执行（打印报告） 5：取消执行（撤销报告） 6：删除\r\n单据打印 | 0：打印,1：取消打印,2：重打',
  `ZYH` int(0) NULL COMMENT '住院号',
  `DATA_TABLE` varchar(255) NOT NULL COMMENT '关联数据表名',
  `DATA_ID` text NOT NULL COMMENT '关联数据ID（逗号分隔）',
  `OPERATE_USER` int(0) NOT NULL COMMENT '操作人',
  `OPERATE_TIME` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`JLXH`)
) COMMENT = '患者住院操作日志';