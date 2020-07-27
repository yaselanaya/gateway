CREATE TABLE IF NOT EXISTS `gateway` (
  `unique_serial_number` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  PRIMARY KEY (`unique_serial_number`)
);