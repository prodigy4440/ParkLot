DROP TABLE IF EXISTS `slot`;
CREATE TABLE `slot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `occupied` bit(1) DEFAULT NULL,
  `slot_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `vehicle_entry`;
CREATE TABLE `vehicle_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(15) DEFAULT NULL,
  `cost` decimal(19,2) DEFAULT NULL,
  `entry_at` datetime DEFAULT NULL,
  `entry_id` varchar(30) DEFAULT NULL,
  `exit_at` datetime DEFAULT NULL,
  `reg_no` varchar(60) DEFAULT NULL,
  `slot_id` bigint(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `entry_id_unique_key` (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Sedan Slot 1',0,'GASOLINE_SEDAN');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Sedan Slot 2',0,'GASOLINE_SEDAN');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Sedan Slot 3',0,'GASOLINE_SEDAN');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Sedan Slot 4',0,'GASOLINE_SEDAN');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Sedan Slot 5',0,'GASOLINE_SEDAN');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 20kw Slot 1',0,'ELECTRIC_20');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 20kw Slot 2',0,'ELECTRIC_20');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 20kw Slot 3',0,'ELECTRIC_20');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 20kw Slot 4',0,'ELECTRIC_20');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 50kw Slot 1',0,'ELECTRIC_50');
INSERT INTO `slot` VALUES(null, '2020-02-19 20:45:00','Electric 50kw Slot 2',0,'ELECTRIC_50');

