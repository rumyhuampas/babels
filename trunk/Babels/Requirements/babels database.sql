-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.20-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-06-19 01:52:35
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for babels
DROP DATABASE IF EXISTS `babels`;
CREATE DATABASE IF NOT EXISTS `babels` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `babels`;


-- Dumping structure for table babels.cancelations
DROP TABLE IF EXISTS `cancelations`;
CREATE TABLE IF NOT EXISTS `cancelations` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CancelerSaleId` int(10) NOT NULL,
  `CanceledSaleId` int(10) NOT NULL,
  `DatePosted` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.cashmovement
DROP TABLE IF EXISTS `cashmovement`;
CREATE TABLE IF NOT EXISTS `cashmovement` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdMovementType` int(10) NOT NULL,
  `Dateposted` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.cashregister
DROP TABLE IF EXISTS `cashregister`;
CREATE TABLE IF NOT EXISTS `cashregister` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `ActionType` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `DatePosted` date NOT NULL,
  `IdUser` int(10) NOT NULL,
  `IdPos` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.combos
DROP TABLE IF EXISTS `combos`;
CREATE TABLE IF NOT EXISTS `combos` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `Description` text COLLATE latin1_spanish_ci,
  `Price` float NOT NULL,
  `Image` blob,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.combosproducts
DROP TABLE IF EXISTS `combosproducts`;
CREATE TABLE IF NOT EXISTS `combosproducts` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdCombo` int(10) NOT NULL,
  `IdProduct` int(10) NOT NULL,
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
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.sales
DROP TABLE IF EXISTS `sales`;
CREATE TABLE IF NOT EXISTS `sales` (
  `Id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Total` float NOT NULL,
  `Type` varchar(1) COLLATE latin1_spanish_ci NOT NULL,
  `DatePosted` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.salesitems
DROP TABLE IF EXISTS `salesitems`;
CREATE TABLE IF NOT EXISTS `salesitems` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdSale` int(10) NOT NULL,
  `IdItem` int(10) NOT NULL,
  `ItemType` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Data exporting was unselected.


-- Dumping structure for table babels.salesprints
DROP TABLE IF EXISTS `salesprints`;
CREATE TABLE IF NOT EXISTS `salesprints` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdSale` int(10) NOT NULL,
  `dateposted` datetime DEFAULT NULL,
  `status` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `dateprinted` datetime DEFAULT NULL,
  `Printer` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
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
