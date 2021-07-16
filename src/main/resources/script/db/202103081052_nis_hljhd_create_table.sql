CREATE TABLE `nis_hljhd`  (
  `JLXH` int(0) NOT NULL COMMENT '主键记录序号',
  `ZYH` int(0) NULL COMMENT '住院号',
  `RQ` datetime(0) NULL COMMENT '日期',
  `HLWT` varchar(255) NULL COMMENT '护理问题',
  `HLMB` varchar(255) NULL COMMENT '护理目标',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理执行单主表';

CREATE TABLE `nis_hljhdcs`  (
  `JLXH` int(0) NOT NULL COMMENT '主键记录序号',
  `ZXJLXH` int(0) NULL COMMENT '执行单主表记录序号',
  `HLCS` varchar(500) NULL COMMENT '护理措施',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理执行单子表';


CREATE TABLE `nis_hljhdmb`  (
  `JLXH` int(0) NOT NULL COMMENT '主键记录序号',
  `HLWT` varchar(255) NULL COMMENT '护理问题',
  `HLMB` varchar(255) NULL COMMENT '护理目标',
  `HLCS` varchar(5000) NULL COMMENT '护理措施',
  `JGID` int(0) NULL COMMENT '机构id',
  `KSDM` int(0) NULL COMMENT '科室代码',
  `BQDM` int(0) NULL COMMENT '病区代码',
  `BZXX` varchar(255) NULL COMMENT '备注信息',
  `ZFPB` int(0) NULL COMMENT '作废判别',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理执行单模板';