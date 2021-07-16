CREATE TABLE `im_cwzh`  (
  `CWZH` int(0) NOT NULL COMMENT '床位组号(主键)',
  `CWZHMC` varchar(50) NULL COMMENT '床位组号名称',
  `ZFPB` int(0) NULL COMMENT '作废判别(1:是0:否)',
  `PYDM` varchar(50) NULL COMMENT '拼音代码',
  `JGID` int(0) NULL COMMENT '机构id',
  PRIMARY KEY (`CWZH`)
);

ALTER TABLE `mess_record` 
MODIFY COLUMN `JLXH` int(0) NOT NULL COMMENT '主键记录序号' FIRST,
ADD PRIMARY KEY (`JLXH`);