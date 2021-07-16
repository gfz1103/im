ALTER TABLE im_fee_fymx
    ADD COLUMN ZFPB int(0)      NULL COMMENT '作废判别 | 0：正常记账    1：作废记账' AFTER JZDYH,
    ADD COLUMN ZFRQ datetime(0) NULL COMMENT '作废日期' AFTER JZDYH;

ALTER TABLE im_fee_fymx
    MODIFY COLUMN XMLX int(0) NOT NULL COMMENT '项目类型 | 1：病区系统记帐    2：药房系统记帐    3：医技系统记帐    4：住院系统记帐(补记账)     5：手术麻醉记帐    9：自动累加费用' AFTER JFRQ,
    MODIFY COLUMN ZFGH varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补记账 作废工号' AFTER GJYBZ;
