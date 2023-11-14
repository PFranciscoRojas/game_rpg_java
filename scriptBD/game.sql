-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2023 a las 13:32:27
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `game`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name_category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name_category`) VALUES
(1, 'armas'),
(2, 'armaduras'),
(3, 'pociones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipment`
--

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL,
  `inventory_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventory`
--

CREATE TABLE `inventory` (
  `id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `personage_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inventory`
--

INSERT INTO `inventory` (`id`, `store_id`, `personage_id`) VALUES
(77, 5, 1),
(78, 21, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mission`
--

CREATE TABLE `mission` (
  `id` int(11) NOT NULL,
  `name_mission` varchar(50) NOT NULL,
  `description_mission` varchar(50) NOT NULL,
  `gold` int(11) NOT NULL,
  `experience` double NOT NULL,
  `personage_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mission`
--

INSERT INTO `mission` (`id`, `name_mission`, `description_mission`, `gold`, `experience`, `personage_id`) VALUES
(1, 'Bosque perdido', 'Elimina dos esqueletos para reclamar recompensas', 3, 3, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monster`
--

CREATE TABLE `monster` (
  `id` int(11) NOT NULL,
  `name_monster` varchar(50) NOT NULL,
  `life` int(11) NOT NULL,
  `force_monster` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `monster`
--

INSERT INTO `monster` (`id`, `name_monster`, `life`, `force_monster`) VALUES
(1, 'skeleton', 30, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monster_mission`
--

CREATE TABLE `monster_mission` (
  `id` int(11) NOT NULL,
  `monster_id` int(11) DEFAULT NULL,
  `mission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `monster_mission`
--

INSERT INTO `monster_mission` (`id`, `monster_id`, `mission_id`) VALUES
(1, 1, 1),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personage`
--

CREATE TABLE `personage` (
  `id` int(11) NOT NULL,
  `name_personage` varchar(50) NOT NULL,
  `breed` varchar(50) NOT NULL,
  `level_personage` int(11) NOT NULL,
  `experience` double NOT NULL,
  `force_personage` int(11) NOT NULL,
  `gold` int(11) NOT NULL,
  `life` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personage`
--

INSERT INTO `personage` (`id`, `name_personage`, `breed`, `level_personage`, `experience`, `force_personage`, `gold`, `life`) VALUES
(1, 'daniel', 'Guerrero', 1, 0, 10, 78, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `store`
--

CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `name_item` varchar(50) DEFAULT NULL,
  `description_item` varchar(250) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `category` char(1) DEFAULT NULL,
  `gold` int(11) DEFAULT NULL,
  `graphics` varchar(50) NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `store`
--

INSERT INTO `store` (`id`, `name_item`, `description_item`, `score`, `category`, `gold`, `graphics`, `category_id`) VALUES
(1, 'CASCO', 'CASCO BRONCE', 4, 'H', 10, 'img/cascoI.png', 2),
(2, 'PECHO', 'PECHO BRONCE', 5, 'C', 10, 'img/chalecoI.png', 2),
(3, 'PANTALON', 'PANTALON BRONCE', 5, 'P', 10, 'img/pantalonI.png', 2),
(4, 'CASCO', 'CASCO PLATA', 5, 'H', 3, 'img/cascoII.png', 2),
(5, 'PECHO', 'PECHO PLATA', 8, 'C', 10, 'img/chalecoII.png', 2),
(6, 'ESPADA', 'ESPADA', 5, 'A', 20, 'img/espada.png', 1),
(7, 'POCIMA', 'REGENERA 100% TU VIDA', 8, 'A', 20, 'img/posion.png', 3),
(8, 'ARCO', 'ARCO DOMIANTE', 10, 'A', 20, 'img/Arco.png', 1),
(9, 'GRANADA', 'GRANADQA METRALLA', 15, 'A', 13, 'img/granada.png', 1),
(13, 'MARTILLO', 'MARTILLO INFINITO', 17, 'A', 19, 'img/thor.png', 1),
(14, 'RESORTERA', 'RESORTERA EXPLOSIVA', 8, 'A', 8, 'img/catapulta.png', 1),
(16, 'PISTOLA', 'PISTOLA DE LLAMAS', 22, 'A', 18, 'img/pistola.png', 1),
(18, 'FATALITY', 'DERRIVA CON UN K.O.', 8, 'P', 45, 'img/box.png', 3),
(19, 'ADRENALINE', 'AUMENTA TU FUERZA', 15, 'P', 20, 'img/adrenaline.png', 3),
(20, 'PANTALON', 'PANTALON PLATA', 8, 'P', 10, 'img/pantalonII.png', 2),
(21, 'CASCO', 'CASCO ORO', 8, 'H', 18, 'img/cascoIII.png', 2),
(22, 'PECHO', 'PECHO ORO', 15, 'C', 18, 'img/chalecoIII.png', 2),
(23, 'PANTALON ', 'PANTALON ORO', 15, 'P', 18, 'img/pantalonIII.png', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_equipments_inventorys` (`inventory_id`);

--
-- Indices de la tabla `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_inventorys_stores_idx` (`store_id`),
  ADD KEY `fk_inventorys_personages` (`personage_id`);

--
-- Indices de la tabla `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_missions_personages` (`personage_id`);

--
-- Indices de la tabla `monster`
--
ALTER TABLE `monster`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `monster_mission`
--
ALTER TABLE `monster_mission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_monsters_missions_monsters` (`monster_id`),
  ADD KEY `fk_monsters_missions_missions` (`mission_id`);

--
-- Indices de la tabla `personage`
--
ALTER TABLE `personage`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_stores_categorys` (`category_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `equipment`
--
ALTER TABLE `equipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT de la tabla `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
--
-- AUTO_INCREMENT de la tabla `mission`
--
ALTER TABLE `mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `monster`
--
ALTER TABLE `monster`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `monster_mission`
--
ALTER TABLE `monster_mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `personage`
--
ALTER TABLE `personage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `store`
--
ALTER TABLE `store`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `fk_equipments_inventorys` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`);

--
-- Filtros para la tabla `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `fk_inventorys_personages` FOREIGN KEY (`personage_id`) REFERENCES `personage` (`id`),
  ADD CONSTRAINT `fk_inventorys_stores` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`);

--
-- Filtros para la tabla `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `fk_missions_personages` FOREIGN KEY (`personage_id`) REFERENCES `personage` (`id`);

--
-- Filtros para la tabla `monster_mission`
--
ALTER TABLE `monster_mission`
  ADD CONSTRAINT `fk_monsters_missions_missions` FOREIGN KEY (`mission_id`) REFERENCES `mission` (`id`),
  ADD CONSTRAINT `fk_monsters_missions_monsters` FOREIGN KEY (`monster_id`) REFERENCES `monster` (`id`);

--
-- Filtros para la tabla `store`
--
ALTER TABLE `store`
  ADD CONSTRAINT `fk_stores_categorys` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
