-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.3.0-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para usulibad
CREATE DATABASE IF NOT EXISTS `usulibad` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `usulibad`;

-- Volcando estructura para tabla usulibad.libros
CREATE TABLE IF NOT EXISTS `libros` (
  `idLibro` int(10) unsigned NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `anioPublicación` date NOT NULL,
  `cantidadDisponible` int(11) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla usulibad.libros: ~0 rows (aproximadamente)
REPLACE INTO `libros` (`idLibro`, `titulo`, `autor`, `anioPublicación`, `cantidadDisponible`) VALUES
	(1, 'Los Juegos del Hambre', 'Suzanne Collins', '2008-09-14', 33);

-- Volcando estructura para tabla usulibad.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla usulibad.usuario: ~4 rows (aproximadamente)
REPLACE INTO `usuario` (`idUsuario`, `usuario`, `contraseña`) VALUES
	(1, 'jamon', 'jimenez');
REPLACE INTO `usuario` (`idUsuario`, `usuario`, `contraseña`) VALUES
	(2, 'q', 'q');
REPLACE INTO `usuario` (`idUsuario`, `usuario`, `contraseña`) VALUES
	(3, 'ae', 'ae');
REPLACE INTO `usuario` (`idUsuario`, `usuario`, `contraseña`) VALUES
	(4, 'holo', '1234567891234567');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
