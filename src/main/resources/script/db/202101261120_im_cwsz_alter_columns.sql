ALTER TABLE `im_cwsz` 
MODIFY COLUMN `VIPCWFY` decimal(6, 2) NULL DEFAULT NULL COMMENT 'VIP床位费用' AFTER `SCBZ`,
ADD COLUMN `CWFYXH` int(0) NULL COMMENT '普通床位费对应项目费用序号' AFTER `CWZH`,
ADD COLUMN `ICUFYXH` int(0) NULL COMMENT 'ICU费用序号' AFTER `CWFYXH`,
ADD COLUMN `VIPFYXH` int(0) NULL COMMENT 'VIP费用序号' AFTER `ICUFYXH`,
ADD COLUMN `SBFYXH` int(0) NULL COMMENT '商保费用序号' AFTER `VIPFYXH`;