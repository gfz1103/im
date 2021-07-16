CREATE TABLE `cis_yzmess`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键)',
  `ZYH` int(0) NULL COMMENT '住院号',
  `FSSJ` datetime(0) NULL COMMENT '发送时间',
  `ZT` int(0) NULL COMMENT '状态(0:未处理1:处理中2:已处理)',
  `JGID` int(0) NULL COMMENT '机构id',
  `MESSID` varchar(50) NULL COMMENT '消息返回id',
  `FSRDM` int(0) NULL COMMENT '发送人代码',
  `CLRDM` int(0) NULL COMMENT '处理人代码',
  PRIMARY KEY (`JLXH`)
) COMMENT = '医嘱变动消息发送';