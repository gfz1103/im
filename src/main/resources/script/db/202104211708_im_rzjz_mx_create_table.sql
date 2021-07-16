
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `im_rzjz_mx`;
CREATE TABLE `im_rzjz_mx`  (
  `jzrq` datetime(0) NULL DEFAULT NULL,
  `czgh` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `jgid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `xmmc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `xmlx` int(0) NULL DEFAULT NULL,
  `srhj` decimal(10, 2) NULL DEFAULT NULL,
  `yhje` decimal(10, 2) NULL DEFAULT NULL,
  `sjsr` decimal(10, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
