DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `roles` (`id`, `name`)
VALUES
	(1,'ROLE_ADMIN'),
	(3,'ROLE_CLIENT'),
	(2,'ROLE_PROVIDER');


DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `defualt_duration` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` bigint(20) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `catagory_id` bigint(20) DEFAULT NULL,
  `provider_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5c417kxqrosy2rvgdl4ns6oeg` (`catagory_id`),
  KEY `FK6vss4irsk5nky3rndiw5fw471` (`provider_id`),
  CONSTRAINT `FK5c417kxqrosy2rvgdl4ns6oeg` FOREIGN KEY (`catagory_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK6vss4irsk5nky3rndiw5fw471` FOREIGN KEY (`provider_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `reservations`;

CREATE TABLE `reservations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_date_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `appointment_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7etkhts3kqwtt41g9bea4et4m` (`appointment_id`),
  KEY `FKb5g9io5h54iwl2inkno50ppln` (`user_id`),
  CONSTRAINT `FK7etkhts3kqwtt41g9bea4et4m` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `FKb5g9io5h54iwl2inkno50ppln` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

