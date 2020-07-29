INSERT INTO `gateway`(`unique_serial_number`, `name`, `ip`, `id`) VALUES ('DR763567', 'first gateway', '192.168.45.20', 1);
INSERT INTO `gateway`(`unique_serial_number`, `name`, `ip`, `id`) VALUES ('DR763523', 'second gateway', '192.168.33.52', 2);

INSERT INTO `peripheral_device`(`uid`, `vendor`, `created_date`, `status`, `gateway_id`)
VALUES (1, 'vendor one', '2020-07-28 00:00:00.000000', 1, 1);
INSERT INTO `peripheral_device`(`uid`, `vendor`, `created_date`, `status`, `gateway_id`)
VALUES (2, 'vendor two', '2020-07-28 00:00:00.000000', 1, 1);
INSERT INTO `peripheral_device`(`uid`, `vendor`, `created_date`, `status`, `gateway_id`)
VALUES (3, 'vendor three', '2020-07-28 00:00:00.000000', 1, 2);
INSERT INTO `peripheral_device`(`uid`, `vendor`, `created_date`, `status`, `gateway_id`)
VALUES (4, 'vendor four', '2020-07-28 00:00:00.000000', 1, 2);
INSERT INTO `peripheral_device`(`uid`, `vendor`, `created_date`, `status`, `gateway_id`)
VALUES (5, 'vendor five', '2020-07-28 00:00:00.000000', 1, 2);
