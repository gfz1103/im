-- 修改 im_zyjs 字段备注
ALTER TABLE im_zyjs
MODIFY COLUMN JSLX int(0) NOT NULL COMMENT '结算类型 | 1：中途结算\n2：预结（不写IM_ZYJS）\n3：预结后出院结算\n4：终结处理(异常出院)\n5：正常出院结算\n6：合并结算\n9：退费\n' AFTER JGID,
MODIFY COLUMN JKHJ decimal(10, 2) NOT NULL COMMENT '预缴款合计 | 从IM_TBKK表中统计' AFTER ZFHJ,
MODIFY COLUMN HZRQ datetime NULL DEFAULT NULL COMMENT '汇总日期 | 同IM_TBKK表中HZRQ住院处做汇总日期时填写' AFTER JZRQ;

-- im_tbkk	  增加字段
ALTER TABLE im_tbkk
ADD COLUMN FPHM varchar(20) NULL COMMENT '发票号码 | 病人结算发票号码' AFTER ZPHM,
ADD COLUMN JKLX int(0) NULL COMMENT '缴款类型 | 0：缴款   1：扣款    2：出院找退' AFTER MZLB;