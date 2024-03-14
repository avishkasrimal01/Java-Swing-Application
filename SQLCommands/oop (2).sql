-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 16, 2024 at 01:07 PM
-- Server version: 8.0.31
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop`
--

-- --------------------------------------------------------

--
-- Table structure for table `allocate`
--

DROP TABLE IF EXISTS `allocate`;
CREATE TABLE IF NOT EXISTS `allocate` (
  `orderno` varchar(200) NOT NULL,
  `empid` varchar(200) NOT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employeetbl`
--

DROP TABLE IF EXISTS `employeetbl`;
CREATE TABLE IF NOT EXISTS `employeetbl` (
  `eId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `fullName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `NIC` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Age` int NOT NULL,
  `Telephone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Gender` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Salary` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`eId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employeetbl`
--

INSERT INTO `employeetbl` (`eId`, `firstName`, `fullName`, `Email`, `NIC`, `Age`, `Telephone`, `Gender`, `Salary`, `count`) VALUES
(5, 'Nirmal', 'Koswatta', 'EMail', 'ISuru', 12, '1231312', 'MAleasd', 12313, 3);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
  `id` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `qty` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `name`, `price`, `category`, `qty`) VALUES
('1', 'sdcsd', 'csdc', 'dsccs', '4'),
('01', 'avishka', '50000', 'asd', '02');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `orderno` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `price` varchar(200) NOT NULL,
  `vno` varchar(200) NOT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderno`, `name`, `category`, `price`, `vno`) VALUES
('30', 'uthwarinpn@gmail.com', 'Car', '20000', '12'),
('3', 'fwrfw', 'wfew', 'wef', 'wfe'),
('1', 'yfyes', 'esse', '2001', '12'),
('123', 'cfhc', 'hjjh', '200', 'mbwj');

-- --------------------------------------------------------

--
-- Table structure for table `registry`
--

DROP TABLE IF EXISTS `registry`;
CREATE TABLE IF NOT EXISTS `registry` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Transation Name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Price` int NOT NULL,
  `Date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registry`
--

INSERT INTO `registry` (`ID`, `Transation Name`, `Price`, `Date`) VALUES
(1, 'IT1204', 1500, 'JANUARY'),
(2, 'IT1223', 1250, 'MARCH'),
(3, 'Nipun', 2000, 'March'),
(5, 'Pasindi', 4000, 'December');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `products` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supplies`
--

DROP TABLE IF EXISTS `supplies`;
CREATE TABLE IF NOT EXISTS `supplies` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `Product Name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Price` int NOT NULL,
  `Date` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplies`
--

INSERT INTO `supplies` (`productID`, `Product Name`, `Price`, `Date`) VALUES
(1, 'Bandages', 12000, 'January'),
(3, 'Chamika', 200, 'April');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
