CREATE TABLE `nis_typc` (
  `id` bigint NOT NULL COMMENT '主键',
  `tyxh` int DEFAULT NULL COMMENT '退药序号，nis_tymx表主键',
  `tjxh` int DEFAULT NULL COMMENT '提交序号，nis_tj02表主键',
  `zxsj` datetime DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;