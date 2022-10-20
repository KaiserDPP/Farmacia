-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2022 a las 10:38:14
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pharmacia`
--
CREATE DATABASE IF NOT EXISTS `pharmacia` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `pharmacia`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `category_name` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `created_at`, `updated_at`, `category_name`) VALUES
(1, '2022-07-28 14:02:48', '2022-07-29 08:20:56', '	 Tracto alimentario '),
(2, '2022-07-29 08:16:17', '2022-07-29 08:21:34', 'Sangre y oh.'),
(3, '2022-07-29 08:16:30', '2022-07-29 08:16:30', 'Sistema cardiovascular'),
(4, '2022-07-29 08:16:41', '2022-07-29 08:16:41', 'Dermatología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `code` varchar(225) COLLATE utf8_bin NOT NULL,
  `title` varchar(225) COLLATE utf8_bin NOT NULL,
  `cat_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5yhiu7ri51c4oigkb28skv0b2` (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id`, `created_at`, `updated_at`, `code`, `title`, `cat_id`) VALUES
(1, '2022-07-28 14:03:12', '2022-07-28 14:03:12', 'A000025', 'Enzima', 1),
(2, '2022-07-29 08:18:51', '2022-07-29 08:18:51', 'A000026', 'Laxante', 1),
(3, '2022-07-29 08:20:27', '2022-07-29 08:20:27', 'B000012', 'Anticoagulante', 2),
(4, '2022-07-29 08:23:17', '2022-07-29 08:23:17', 'C000088', 'Heparina', 3),
(5, '2022-07-29 08:24:32', '2022-07-29 08:24:32', 'C000068', 'Benazepril', 3),
(6, '2022-07-29 08:26:32', '2022-07-29 08:26:48', 'D000045', 'Minoxidil', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_details`
--

DROP TABLE IF EXISTS `product_details`;
CREATE TABLE IF NOT EXISTS `product_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expire_year` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `issue_year` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `units_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `product_details`
--

INSERT INTO `product_details` (`id`, `expire_year`, `issue_year`, `units_number`) VALUES
(1, '2000', '1980', '50'),
(2, '1988', '1982', '65'),
(3, '2002', '1997', '50'),
(4, '2001', '1996', '20'),
(5, '2012', '2000', '55'),
(6, '2011', '2006', '20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `authority` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  KEY `FK13nv3h965w27i114ylfgt9b4d` (`username`),
  KEY `IDXirsamgnera6angm0prq1kemt2` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`authority`, `username`) VALUES
('ROLE_ADMIN', 'david'),
('ROLE_OWNER', 'gorka'),
('ROLE_USER', 'Rafa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `confirm_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(225) COLLATE utf8_bin NOT NULL,
  `surname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `uk_email_unik` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`username`, `created_at`, `updated_at`, `confirm_password`, `email`, `enabled`, `name`, `password`, `surname`) VALUES
('Rafa', '2022-07-28 14:39:26', '2022-07-29 08:32:47', '$2a$10$oD/wxKWj8CXuE5iFKeVsaORkuPj7UNGpm/JDS2X8W8OlHci.wQ0/m', 'rrr@gmail.com', b'1', 'Rafa', '$2a$10$oD/wxKWj8CXuE5iFKeVsaORkuPj7UNGpm/JDS2X8W8OlHci.wQ0/m', 'RRA'),
('david', '2022-07-28 13:59:53', '2022-07-29 08:34:16', '$2a$10$3yXhFm8KRb0MnlaFlHvaru7YQrMp.0T6bMR.T79RqOcZHRszV7Udq', 'd@gmail.com', b'1', 'David', '$2a$10$3yXhFm8KRb0MnlaFlHvaru7YQrMp.0T6bMR.T79RqOcZHRszV7Udq', 'PALA'),
('gorka', '2022-07-28 14:38:39', '2022-07-29 08:34:31', '$2a$10$fc0FVoG2W3zZPJj6K8lNu.i7I3.DkIXn73pgmjUaMwjP.mUXK.IWe', 'ggg@gmail.com', b'1', 'Gorka', '$2a$10$fc0FVoG2W3zZPJj6K8lNu.i7I3.DkIXn73pgmjUaMwjP.mUXK.IWe', 'GOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_product`
--

DROP TABLE IF EXISTS `user_product`;
CREATE TABLE IF NOT EXISTS `user_product` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKnw43wab2rt35jmofmpbhkibco` (`product_id`),
  KEY `FKq5o2e33vlwpfc2k1mredtia6p` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `user_product`
--

INSERT INTO `user_product` (`user_id`, `product_id`) VALUES
('Rafa', 3),
('david', 5),
('gorka', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK5yhiu7ri51c4oigkb28skv0b2` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`);

--
-- Filtros para la tabla `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK13nv3h965w27i114ylfgt9b4d` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Filtros para la tabla `user_product`
--
ALTER TABLE `user_product`
  ADD CONSTRAINT `FKnw43wab2rt35jmofmpbhkibco` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKq5o2e33vlwpfc2k1mredtia6p` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
