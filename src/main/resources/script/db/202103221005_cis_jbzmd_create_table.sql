CREATE TABLE `cis_jbzmd`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键)',
  `BRLX` int(0) NULL COMMENT '病人类型(1:门诊2:住院)',
  `ZYH` int(0) NULL COMMENT '住院号',
  `MZHM` varchar(50) NULL COMMENT '门诊号码',
  `JTDZ` varchar(255) NULL COMMENT '工作单位/家庭地址',
  `JY` varchar(1000) NULL COMMENT '建议',
  `LXDH` varchar(20) NULL COMMENT '联系电话',
  `ZZYS` int(0) NULL COMMENT '诊治医生',
  `KJRQ` datetime(0) NULL COMMENT '开具日期',
  `LRZDM` int(0) NULL COMMENT '录入者代码',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '疾病证明单';