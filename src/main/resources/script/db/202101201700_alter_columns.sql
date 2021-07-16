-- 修改备注乱码, 预约状态增加已入院
ALTER TABLE im_bedresev
    MODIFY COLUMN csny datetime NULL DEFAULT NULL COMMENT '出生年月' AFTER id_card,
    MODIFY COLUMN yyzyrq datetime NULL DEFAULT NULL COMMENT '预约住院日期' AFTER yyks,
    MODIFY COLUMN rytzsj datetime NULL DEFAULT NULL COMMENT '通知入院时间' AFTER brch,
    MODIFY COLUMN djsj datetime NULL DEFAULT NULL COMMENT '登记时间' AFTER dcczr,
    MODIFY COLUMN rydjsj datetime NULL DEFAULT NULL COMMENT '入院登记时间' AFTER djsj,
    MODIFY COLUMN dczt varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '待床状态 0:未待床 1:已待床 2:逾期 3:已入院' AFTER notice;

ALTER TABLE im_zyjs
    MODIFY COLUMN KSRQ datetime(0) NOT NULL COMMENT '开始日期 | 结算费用的开始日期' AFTER JSLX,
    MODIFY COLUMN ZZRQ datetime(0) NOT NULL COMMENT '终止日期 | 结算费用的终止日期' AFTER KSRQ,
    MODIFY COLUMN FPHM varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发票号码 | 病人结算时打印的结算发票号码' AFTER JKHJ;