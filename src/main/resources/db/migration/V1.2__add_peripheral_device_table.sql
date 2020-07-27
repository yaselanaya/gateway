CREATE TABLE IF NOT EXISTS `peripheral_device` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `vendor` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `gateway_id` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `gateway_id` (`gateway_id`),
  CONSTRAINT `gateway_id` FOREIGN KEY (`gateway_id`) REFERENCES `gateway` (`unique_serial_number`)
)