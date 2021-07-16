-- 修改记账作废判别注释
ALTER TABLE im_fee_fymx
    MODIFY COLUMN ZFPB int(0) NULL DEFAULT NULL COMMENT '作废判别 | 0：正常记账    1：已作废    2：作废抵账' AFTER ZFRQ;