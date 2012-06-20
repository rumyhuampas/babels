/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


USE `babels`;
DROP TABLE IF EXISTS `cancelations`;
CREATE TABLE `cancelations` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CancellerMoveId` int(10) NOT NULL DEFAULT '0',
  `CanceledMoveId` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `combo_products`;
CREATE TABLE `combo_products` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdCombo` int(10) NOT NULL,
  `IdProduct` int(10) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `combos`;
CREATE TABLE `combos` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `Description` text COLLATE latin1_spanish_ci,
  `Price` float NOT NULL,
  `Image` blob,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `movement_types`;
CREATE TABLE `movement_types` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `movements`;
CREATE TABLE `movements` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Type` int(3) NOT NULL DEFAULT '0',
  `Dateposted` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Amount` float NOT NULL DEFAULT '0',
  `IdUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `prints`;
CREATE TABLE `prints` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdSale` int(10) NOT NULL,
  `dateposted` datetime DEFAULT NULL,
  `status` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `dateprinted` datetime DEFAULT NULL,
  `Printer` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `prints`;
CREATE TABLE `prints` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdSale` int(10) NOT NULL,
  `dateposted` datetime DEFAULT NULL,
  `status` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `dateprinted` datetime DEFAULT NULL,
  `Printer` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
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
DROP TABLE IF EXISTS `sale_details`;
CREATE TABLE `sale_details` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `IdMove` int(10) NOT NULL DEFAULT '0',
  `IdItem` int(10) NOT NULL,
  `ItemType` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `Pass` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `IsAdmin` int(1) NOT NULL,
  `Active` int(1) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
