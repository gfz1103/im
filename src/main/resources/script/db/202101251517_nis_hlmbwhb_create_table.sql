CREATE TABLE `nis_hlmbwhb`  (
  `ID` int(0) NOT NULL COMMENT '主键id',
  `HLMBMC` varchar(50) NULL COMMENT '护理模板名称',
  `HLNR` varchar(2000) NULL COMMENT '护理内容',
  `ZFPB` int(0) NULL COMMENT '作废判别(1:是0:否)',
  `JGID` int(0) NULL COMMENT '机构id',
  `KSDM` int(0) NULL COMMENT '科室代码(0:全院)',
  `USERID` int(0) NULL COMMENT '个人',
  `CZGH` int(0) NULL COMMENT '操作工号',
  `CZSJ` datetime(0) NULL COMMENT '操作时间',
  `BZXX` varchar(255) NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) COMMENT = '护理单模板维护表';

ALTER TABLE `cis_zy_hzsq` 
ADD COLUMN `MESSID` varchar(50) NULL COMMENT '消息返回id' AFTER `KZRYS`;

ALTER TABLE `cis_wjzjl` 
ADD COLUMN `MESSID` varchar(50) NULL COMMENT '消息返回id' AFTER `YLJGD`;

ALTER TABLE `amqc_kjywsysq` 
ADD COLUMN `MESSID` varchar(50) NULL COMMENT '消息返回id' AFTER `QT`;