CREATE TABLE `im_cwszjl`  (
  `JLXH` int(0) NOT NULL COMMENT '记录序号(主键)',
  `JLRQ` datetime(0) NULL COMMENT '记录日期',
  `BQDM` int(0) NULL COMMENT '病区代码',
  `CWSYS` int(0) NULL COMMENT '床位使用数',
  `CWWSY` int(0) NULL COMMENT '床位未使用数',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`JLXH`)
) COMMENT = '床位使用记录表';