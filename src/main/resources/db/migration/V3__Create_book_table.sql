CREATE TABLE IF NOT EXISTS `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(80),
  `launch_date` DATE NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `title` varchar(200),
  PRIMARY KEY (`id`)
);