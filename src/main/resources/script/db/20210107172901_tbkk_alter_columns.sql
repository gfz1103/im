-- im_tbkk	  修改字段备注
ALTER TABLE im_tbkk
MODIFY COLUMN JKFS int(0) NULL COMMENT '缴款方式 | 1:现金,2:支票(与ZY_JKFS表中JKFS字段对应' AFTER JKJE,
MODIFY COLUMN SJHM varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '收据号码 | 病人预缴款收据号码' AFTER JKFS,
MODIFY COLUMN JSCS int(0) NULL COMMENT '结算次数 | 同ZY_BRRY,ZY_ZYJS,ZY_FYMX表中JSCS\nJSCS=0 则病人尚未办理结算.' AFTER FPHM,
MODIFY COLUMN ZFPB int(0) NULL COMMENT '作废判别 | 注销预缴款或发票作废时填写(已弃用)' AFTER ZFGH,
MODIFY COLUMN JKLX int(0) NULL DEFAULT NULL COMMENT '缴款类型 | 0：正常预缴   1：结算抵扣   2：取消结算   3：注销找退   4：出院找退' AFTER MZLB;