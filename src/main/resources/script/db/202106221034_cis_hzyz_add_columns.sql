ALTER TABLE `cis_hzyz` 
ADD COLUMN `TJSJ` datetime(0) NULL COMMENT '提交时间' AFTER `CJSJ`;

ALTER TABLE `cis_hzyz_zt` 
ADD COLUMN `TJSJ` datetime(0) NULL COMMENT '提交时间' AFTER `ZFSJ`;