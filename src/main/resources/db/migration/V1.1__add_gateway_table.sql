CREATE TABLE IF NOT EXISTS `gateway` (
  `unique_serial_number` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
);