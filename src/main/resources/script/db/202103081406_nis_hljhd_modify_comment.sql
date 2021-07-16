ALTER TABLE `nis_hljhd` COMMENT = '护理计划单主表';
ALTER TABLE `nis_hljhdcs` COMMENT = '护理计划单子表';
ALTER TABLE `nis_hljhdmb` COMMENT = '护理计划单模板';

ALTER TABLE `nis_hljhd` 
ADD COLUMN `XGPJ` varchar(500) NULL COMMENT '效果评价' AFTER `JGID`,
ADD COLUMN `HSQM` varchar(50) NULL COMMENT '护士签名' AFTER `XGPJ`;
