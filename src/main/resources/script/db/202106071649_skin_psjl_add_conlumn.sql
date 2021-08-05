ALTER TABLE `skin_psjl` 
ADD COLUMN `CZSJ` timestamp(0) NULL COMMENT '操作时间' AFTER `BLFY`,
ADD COLUMN `GMLB` int(0) NULL COMMENT '过敏类别(对应字典sys_flag_data:FD000030)' AFTER `CZSJ`;