ALTER TABLE `im_cwzh` 
ADD COLUMN `BQDM` int(0) NULL COMMENT '病区代码' AFTER `JGID`,
COMMENT = '住院_床位组号';