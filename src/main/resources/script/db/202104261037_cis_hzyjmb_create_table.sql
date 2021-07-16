CREATE TABLE `cis_hzyjmb`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键)',
  `MBMC` varchar(50) NULL COMMENT '模板名称',
  `HZYJ` varchar(2000) NULL COMMENT '会诊意见',
  `YSGH` int(0) NULL COMMENT '个人工号(个人必填)',
  `KSDM` int(0) NULL COMMENT '科室代码(全院为空)',
  `ZFPB` int(0) NULL COMMENT '作废判别(1是0否)',
  `PYDM` varchar(50) NULL COMMENT '名称拼音代码',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '会诊意见模板维护表';