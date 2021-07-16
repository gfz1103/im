CREATE TABLE `nis_zdyxmnr`  (
  `XMNRDM` int(0) NOT NULL COMMENT '自定义项目内容代码(主键)',
  `XMNRMC` varchar(50) NULL COMMENT '项目内容名称',
  `PYDM` varchar(50) NULL COMMENT '拼音代码',
  `JGID` int(0) NULL COMMENT '机构id',
  `XMDM` varchar(0) NULL COMMENT 'nis_zdyxm(主键关联)',
  `BZXX` varchar(255) NULL COMMENT '备注信息',
  `ZFPB` int(0) NULL COMMENT '作废判别(1:是0:否)',
  PRIMARY KEY (`XMNRDM`)
) COMMENT = '自定义专科护理内容';