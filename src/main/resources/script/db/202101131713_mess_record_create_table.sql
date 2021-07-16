CREATE TABLE `mess_record`  (
  `JLXH` int(0) NULL COMMENT '主键记录序号',
  `MESSID` varchar(50) NULL COMMENT '消息返回id',
  `YWID` int(0) NULL COMMENT '消息对应业务ID(业务主键)',
  `YWLX` int(0) NULL COMMENT '业务类型(1:会诊申请2:特殊会诊申请3:危急值4:医嘱变动)',
  `JGID` int(0) NULL COMMENT '机构id'
);