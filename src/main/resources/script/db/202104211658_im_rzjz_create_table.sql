SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `im_rzjz`  (
  `jzrq` datetime(0) NOT NULL COMMENT '结账日期',
  `czgh` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作工号',
  `jgid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构代码',
  `yjje` decimal(10, 2) NULL DEFAULT NULL COMMENT '结算收预缴金额',
  `ssyjje` decimal(10, 2) NULL DEFAULT NULL COMMENT '实收预缴金额',
  `tkyjje` decimal(10, 2) NULL DEFAULT NULL COMMENT '预缴金找退',
  `jsxjje` decimal(10, 2) NULL DEFAULT NULL COMMENT '结算收现金金额',
  `jszpje` decimal(10, 2) NULL DEFAULT NULL COMMENT '结算收支票金额',
  `jsposje` decimal(10, 2) NULL DEFAULT NULL COMMENT '结算收POS金额 ',
  `srje` decimal(10, 2) NULL DEFAULT NULL COMMENT '舍入金额(货币误差)',
  `ysje` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收金额',
  `qfje` decimal(10, 2) NULL DEFAULT NULL COMMENT '欠费金额',
  `yhje` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `jzze` decimal(10, 2) NULL DEFAULT NULL COMMENT '记账总额',
  `ybjz` decimal(10, 2) NULL DEFAULT NULL COMMENT '医保记账',
  `sbjz` decimal(10, 2) NULL DEFAULT NULL COMMENT '商保记账',
  `qtjz` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他记账',
  `syjjxj` decimal(10, 2) NULL DEFAULT NULL COMMENT '收预交金现金',
  `syjjzp` decimal(10, 2) NULL DEFAULT NULL COMMENT '收预交金支票',
  `syjjpos` decimal(10, 2) NULL DEFAULT NULL COMMENT '收预交金POS',
  `tyjjxj` decimal(10, 2) NULL DEFAULT NULL COMMENT '退预交金现金',
  `tyjjzp` decimal(10, 2) NULL DEFAULT NULL COMMENT '退预交金支票',
  `tyjjpos` decimal(10, 2) NULL DEFAULT NULL COMMENT '退预交金POS',
  `hzrq` datetime(0) NULL DEFAULT NULL COMMENT '汇总日期'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
