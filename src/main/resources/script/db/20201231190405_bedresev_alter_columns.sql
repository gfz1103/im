ALTER TABLE im_bedresev
    MODIFY COLUMN csny datetime NULL DEFAULT NULL COMMENT '出生年月' AFTER id_card,
    MODIFY COLUMN yyzyrq datetime NULL DEFAULT NULL COMMENT '预约住院日期' AFTER yyks,
    MODIFY COLUMN rytzsj datetime NULL DEFAULT NULL COMMENT '通知入院时间' AFTER brch,
    MODIFY COLUMN djsj datetime NULL DEFAULT NULL COMMENT '登记时间' AFTER dcczr,
    MODIFY COLUMN rydjsj datetime NULL DEFAULT NULL COMMENT '入院登记时间' AFTER djsj;