-- im_zyjs	  修改字段非空属性
ALTER TABLE im_zyjs
    MODIFY COLUMN KSRQ datetime(0) NOT NULL COMMENT '开始日期 | 结算费用的开始日期' AFTER JSLX,
    MODIFY COLUMN ZZRQ datetime(0) NOT NULL COMMENT '终止日期 | 结算费用的终止日期' AFTER KSRQ,
    MODIFY COLUMN FPHM varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发票号码 | 病人结算时打印的结算发票号码' AFTER JKHJ;