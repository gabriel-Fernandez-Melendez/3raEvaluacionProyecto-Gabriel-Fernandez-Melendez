-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-05-2022 a las 18:16:32
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agencia_conciertos`
--
CREATE DATABASE IF NOT EXISTS `agencia_conciertos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `agencia_conciertos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concierto`
--

DROP TABLE IF EXISTS `concierto`;
CREATE TABLE `concierto` (
  `id` int(2) NOT NULL,
  `fecha_concierto` date NOT NULL,
  `id_reportero` int(2) DEFAULT NULL,
  `id_gira` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `concierto`
--

INSERT INTO `concierto` (`id`, `fecha_concierto`, `id_reportero`, `id_gira`) VALUES
(1, '1995-02-10', NULL, NULL),
(2, '1999-03-22', NULL, NULL),
(3, '1992-08-20', NULL, NULL),
(4, '1993-05-30', NULL, NULL),
(5, '1985-10-17', NULL, NULL),
(6, '1993-11-24', NULL, NULL),
(7, '1993-02-12', NULL, NULL),
(8, '2000-07-14', NULL, NULL),
(9, '1991-06-01', NULL, NULL),
(10, '1985-06-09', NULL, NULL),
(11, '2016-08-19', 5, 5),
(12, '2016-08-19', 6, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gira`
--

DROP TABLE IF EXISTS `gira`;
CREATE TABLE `gira` (
  `id` int(2) NOT NULL,
  `nombre_gira` varchar(50) NOT NULL,
  `fecha_ini` date NOT NULL,
  `fecha_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gira`
--

INSERT INTO `gira` (`id`, `nombre_gira`, `fecha_ini`, `fecha_fin`) VALUES
(1, 'ac/dc comeback', '2001-06-10', '2001-08-10'),
(2, 'ac/dc return', '2001-02-10', '2001-04-10'),
(3, 'ac/dc el regreso', '2001-06-02', '2001-09-10'),
(4, 'ac/dc bagette', '2002-01-04', '2002-06-10'),
(5, 'foyone en la casa', '2001-04-10', '2001-09-20'),
(6, 'fernando costa v34', '2001-06-10', '2001-06-10'),
(7, 'ayaxyprok', '2001-06-05', '2001-09-10'),
(8, 'delaossa', '2001-06-10', '2002-06-10'),
(9, 'luisker', '2002-08-10', '2003-06-10'),
(10, 'ac/dc comeback2', '2001-06-18', '2001-12-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportero`
--

DROP TABLE IF EXISTS `reportero`;
CREATE TABLE `reportero` (
  `id` int(11) NOT NULL,
  `nombre_apellido` varchar(50) NOT NULL,
  `nif_nie` varchar(10) NOT NULL,
  `telefono` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reportero`
--

INSERT INTO `reportero` (`id`, `nombre_apellido`, `nif_nie`, `telefono`) VALUES
(1, 'vwfv', 'efve', 'svw'),
(2, 'Bolson Vilvo', '51498665Q', '99885432'),
(3, 'Miles Morales', '51498665L', '998674445'),
(4, 'Maria Domingez', '548446651U', '648442569'),
(5, 'Derek mark', '587452165Q', '259887654'),
(6, 'Estefania Domingez', '51498665X', '698569246'),
(7, 'Jessie Domingez', '984657421P', '336659471'),
(8, 'Willian Mendez', '51498665O', '259887654'),
(9, 'Pedro Domingez', '51498665N', '325686447'),
(10, 'Antonio Rodrigez', '365489754E', '452165558'),
(11, 'prueba', 'prueba', 'prueba');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `concierto`
--
ALTER TABLE `concierto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_reportero` (`id_reportero`),
  ADD KEY `id_gira` (`id_gira`);

--
-- Indices de la tabla `gira`
--
ALTER TABLE `gira`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reportero`
--
ALTER TABLE `reportero`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `concierto`
--
ALTER TABLE `concierto`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `gira`
--
ALTER TABLE `gira`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `reportero`
--
ALTER TABLE `reportero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `concierto`
--
ALTER TABLE `concierto`
  ADD CONSTRAINT `concierto_ibfk_1` FOREIGN KEY (`id_reportero`) REFERENCES `reportero` (`id`),
  ADD CONSTRAINT `concierto_ibfk_2` FOREIGN KEY (`id_gira`) REFERENCES `gira` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
