CREATE TABLE `nis_jbdmb`  (
  `ID` int(0) NOT NULL COMMENT '主键id',
  `HZLX` int(0) NULL COMMENT '患者类型(系统标识字典:JBD000001)',
  `SBARLX` int(0) NULL COMMENT 'SBAR类型(系统标识字典:JBD000002)',
  `BCLX` int(0) NULL COMMENT '班次类型(系统标识字典:JBD000003)',
  `MBNR` varchar(5000) NULL COMMENT '模板内容',
  `ZFPB` int(0) NULL COMMENT '作废判别(1:是0:否)',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`ID`)
) COMMENT = '护理交班单模板';

CREATE TABLE `nis_hljbd`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键id)',
  `JBSJ` datetime(0) NULL COMMENT '交班时间',
  `BCLX` int(0) NULL COMMENT '班次类型(系统标识字典:JBD000003)',
  `HLZH` int(0) NULL COMMENT '护理组号',
  `BQDM` int(0) NULL COMMENT '病区代码',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理交班单主表';

CREATE TABLE `nis_hljbdsjb`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键id)',
  `JBDJLXH` int(0) NULL COMMENT '交班单记录序号(nis_hljbd主键)',
  `CYS` int(0) NULL COMMENT '出院数',
  `RYS` int(0) NULL COMMENT '入院数',
  `YSSY` int(0) NULL COMMENT '预手术数',
  `ZCS` int(0) NULL COMMENT '转出数',
  `ZRS` int(0) NULL COMMENT '转入数',
  `BWBZS` int(0) NULL COMMENT '病危病重数',
  `SWS` int(0) NULL COMMENT '死亡数',
  `SSS` int(0) NULL COMMENT '手术数',
  `YJHLS` int(0) NULL COMMENT '一级护理数',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理交班单记录数据表';

CREATE TABLE `nis_hljbdmxb`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键id)',
  `JBDJLXH` int(0) NULL COMMENT '交班单记录序号(nis_hljbd主键)',
  `ZYH` int(0) NULL COMMENT '住院号',
  `ZDMC` varchar(100) NULL COMMENT '诊断名称(入院主诊断)',
  `HZLX` int(0) NULL COMMENT '患者类型(系统标识字典:JBD000001)',
  `SBARLX` int(0) NULL COMMENT 'SBAR类型(系统标识字典:JBD000002)',
  `JBNR` varchar(5000) NULL COMMENT '交班内容',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '护理交班单明细表';