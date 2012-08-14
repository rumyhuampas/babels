-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.24-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-08-13 21:00:32
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for babels
DROP DATABASE IF EXISTS `babels`;
CREATE DATABASE IF NOT EXISTS `babels` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `babels`;


-- Dumping structure for table babels.babelsprinterlog
DROP TABLE IF EXISTS `babelsprinterlog`;
CREATE TABLE IF NOT EXISTS `babelsprinterlog` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `ServiceName` varchar(50) NOT NULL,
  `MessageType` varchar(50) NOT NULL,
  `Message` text NOT NULL,
  `DatePosted` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table babels.cancelations
DROP TABLE IF EXISTS `cancelations`;
CREATE TABLE IF NOT EXISTS `cancelations` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CancellerMoveId` int(10) NOT NULL DEFAULT '0',
  `CanceledMoveId` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.clients
DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `DocNum` varchar(15) NOT NULL,
  `DocType` char(1) NOT NULL,
  `Resp` char(1) NOT NULL,
  `Address` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table babels.combos
DROP TABLE IF EXISTS `combos`;
CREATE TABLE IF NOT EXISTS `combos` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `Description` text COLLATE latin1_spanish_ci,
  `Price` float NOT NULL,
  `Image` longblob,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.combo_products
DROP TABLE IF EXISTS `combo_products`;
CREATE TABLE IF NOT EXISTS `combo_products` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdCombo` int(10) NOT NULL,
  `IdProduct` int(10) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.movements
DROP TABLE IF EXISTS `movements`;
CREATE TABLE IF NOT EXISTS `movements` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Type` int(3) NOT NULL DEFAULT '0',
  `Dateposted` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Amount` float NOT NULL DEFAULT '0',
  `IdUser` int(11) DEFAULT NULL,
  `IdClient` int(11) DEFAULT NULL,
  `Description` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.movement_types
DROP TABLE IF EXISTS `movement_types`;
CREATE TABLE IF NOT EXISTS `movement_types` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.prints
DROP TABLE IF EXISTS `prints`;
CREATE TABLE IF NOT EXISTS `prints` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdMove` int(10) DEFAULT NULL,
  `dateposted` datetime DEFAULT NULL,
  `status` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `dateprinted` datetime DEFAULT NULL,
  `Printer` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Data` text COLLATE latin1_spanish_ci,
  `Retries` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `Description` text COLLATE latin1_spanish_ci,
  `Price` float NOT NULL,
  `Image` longblob,
  `CostPrice` float DEFAULT NULL,
  `Iva` float DEFAULT NULL,
  `PricePackaging` float DEFAULT NULL,
  `IdKitchen` int(11) DEFAULT NULL,
  `IdCategories` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.sale_details
DROP TABLE IF EXISTS `sale_details`;
CREATE TABLE IF NOT EXISTS `sale_details` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdMove` int(10) NOT NULL DEFAULT '0',
  `IdItem` int(10) NOT NULL,
  `ItemType` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `Pass` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `IsAdmin` int(1) NOT NULL,
  `Active` int(1) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
