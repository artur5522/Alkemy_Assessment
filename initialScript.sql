DROP DATABASE IF EXISTS  `moviesdb`;
CREATE DATABASE `moviesdb`;

USE `moviesdb`;

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` int DEFAULT NULL,
  `rate` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6ifx5x4vtwfrpu1a42l8u81w` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `charact`;
CREATE TABLE `charact` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `history` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weight` smallint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dagfdiubadqtoi7qx4lyfods5` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `character_movies`;
CREATE TABLE `characters_movies` (
  `character_id` bigint NOT NULL,
  `movie_id` bigint NOT NULL,
  KEY `FKilivlvrberl5qx84232b4f5ev` (`movie_id`),
  KEY `FKisbcc42i615640xwot0qe5n0u` (`character_id`),
  CONSTRAINT `FKilivlvrberl5qx84232b4f5ev` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FKisbcc42i615640xwot0qe5n0u` FOREIGN KEY (`character_id`) REFERENCES `charact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `rols`;
CREATE TABLE `rols` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s28ugurhecpden0cx4l3kage6` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user_rol`;
CREATE TABLE `user_rol` (
  `user_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`rol_id`),
  KEY `FKcpu1skx4ffu0smt9dmkmkl3wx` (`rol_id`),
  CONSTRAINT `FKcpu1skx4ffu0smt9dmkmkl3wx` FOREIGN KEY (`rol_id`) REFERENCES `rols` (`id`),
  CONSTRAINT `FKkijwolbkui74em8ip1i6vniu4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `moviesdb`.`rols` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `moviesdb`.`rols` (`id`, `name`) VALUES ('2', 'ROLE_USER');

INSERT INTO `moviesdb`.`user` (`id`, `email`, `name`, `password`, `user_name`) VALUES ('1', 'arturo@mail.com', 'arturo', '$2a$10$zvbcpDnuf4y2epRfkwYZcO2miL2IMEO/v8v0CbUfGPAbIaYFCXrqq', 'arturo55');
INSERT INTO `moviesdb`.`user` (`id`, `email`, `name`, `password`, `user_name`) VALUES ('2', 'mark@mail.com', 'mark', '$2a$10$VS.w/3hmVWQebMe1FWrcqe2QnPSe2ZBQNc7IJAiUqS922QPs8LNdG', 'mark22');


INSERT INTO `moviesdb`.`user_rol` (`user_id`, `rol_id`) VALUES ('1', '1');
INSERT INTO `moviesdb`.`user_rol` (`user_id`, `rol_id`) VALUES ('2', '2');


INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1968-09-25', 'Willard Carroll Smith, Jr.1​2​ (Filadelfia, Pensilvania, 25 de septiembre de 1968),3​4​ más conocido como Will Smith, es un actor, cómico, rapero, productor cinematográfico y productor discográfico estadounidense. ', 'Willard Carroll Smith, Jr.', '89');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1953-12-17', 'William Pullman es un actor de cine y televisión estadounidense. Es conocido por sus intervenciones en películas como La loca historia de las galaxias (1987), Independence Day (1996) o Lost Highway (1997) y series como The Sinner (2017).', 'William Pullman', '97');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1952-10-22', 'Jeffrey Lynn Goldblum  más conocido como Jeff Goldblum, es un actor y músico estadounidense. Su carrera comenzó con la película Death Wish (1974) donde interpretaba a un maleante y desde entonces se ha destacado en películas taquilleras ', 'Jeffrey Lynn Goldblum', '90');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1962-01-17', 'James Eugene «Jim» Carrey es un humorista, actor y cantante canadiense-estadounidense. Es conocido por sus interpretaciones de humor slapstick2​ y por su trabajo ganó dos premios Globo de Oro y fue candidato a un premio BAFTA.', 'Jim Carrey', '79');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1955-02-19', 'Jeffrey «Jeff» Warren Daniels es un actor, músico y dramaturgo estadounidense. Obtuvo el premio Emmy en 2014 al mejor actor principal en serie dramática por su papel en The Newsroom1​ y en 2018 como mejor actor secundario en la miniserie Godless.2​', 'Jeff Daniels', '92');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1963-10-28', 'Lauren Holly (Bristol, Pensilvania, 28 de octubre de 1963) es una actriz estadounidense.', 'Lauren Holly', '63');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1955-03-19', 'Se le considera como uno de los actores más rentables en un rol estelar o secundario.4​5​ Por sus interpretaciones, ha sido acreedor de varios premios, entre ellos dos premios Emmy, un Globo de Oro y cuatro Saturn.6​', 'Walter Bruce Willis', '79');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1946-02-21', 'Alan Sidney Patrick Rickman fue un actor, director de cine y teatro.​ Entre sus múltiples trabajos de gran reconocimiento, el de mayor repercusión es su interpretación de Severus Snape en la saga cinematográfica de Harry Potter', 'Alan Rickman', '83');
INSERT INTO `moviesdb`.`charact` (`date_of_birth`, `history`, `name`, `weight`) VALUES ('1948-03-25', 'Bonnie Bedelia Culkin nació en el seno de una familia judía. Estudió ballet en Nueva York y trabajó durante algunos años en Broadway.', 'Bonnie Bedelia', '61');

INSERT INTO `moviesdb`.`movie` (`creation_date`, `rate`, `title`) VALUES ('1996', '7.0', 'Independency Day');
INSERT INTO `moviesdb`.`movie` (`creation_date`, `rate`, `title`) VALUES ('1994', '7.3', 'Dumb and Dumber');
INSERT INTO `moviesdb`.`movie` (`creation_date`, `rate`, `title`) VALUES ('1988', '8.3', 'Die Hard');

