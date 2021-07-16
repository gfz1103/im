CREATE TABLE `his`.`IM_Drugs_Out_Range_Log`
(
    `JLXH`      int(0)      NOT NULL COMMENT '记录序号',
    `ZYH`       int(0)      NOT NULL COMMENT '住院号',
    `FYMX_JLXH` int(0)      NOT NULL COMMENT '原费用明细的主键 | 关联IM_FEE_FYMX.JLXH,',
    `SJFYRQ`    datetime(0) NOT NULL COMMENT '实际费用日期 | 调换之前原费用明细的费用日期',
    PRIMARY KEY (`JLXH`)
) COMMENT = '药品费用超出结算时间记录表';

