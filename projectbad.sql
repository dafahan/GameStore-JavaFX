-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 21, 2023 at 05:53 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectbad`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `UserID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `GameID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`UserID`, `GameID`, `Quantity`) VALUES
('AC002', 'GA001', 3),
('AC002', 'GA005', 2),
('AC003', 'GA001', 2),
('AC003', 'GA004', 1),
('AC004', 'GA002', 2),
('AC004', 'GA003', 2),
('AC005', 'GA002', 1),
('AC005', 'GA003', 3),
('AC006', 'GA003', 2),
('AC006', 'GA005', 1),
('AC007', 'GA005', 0);

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `GameID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `GameName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `GameDescription` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `Price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`GameID`, `GameName`, `GameDescription`, `Price`) VALUES
('GA001', 'Sekiro: Lights Live Thrice', 'So you\'re awake. Looks like death is not your fate just yet. Your master still lives but they\'ll soon make use of his bloodline. The limb you have lost will give way to something more... useful.  Your death won\'t come easily.', 499000),
('GA002', 'Elden Bracelet', 'Tarnished of no renown. Cross the fog, to the Lands Between. To stand before the Elden Bracelet. And become the Elden Lord.', 599000),
('GA003', 'DARK SPIRITS III', 'Only, in truth... the Lords will abandon their thrones... And the Unkindled will rise. Nameless, accursed Undead, unfit even to be cinder. And so it is, that ash seeketh embers.', 399000),
('GA004', 'Counter-Block : Global Defensive', 'BANG!!!!!!!!!! PEW PEW PEW PEW PEW BOOOOOOOOOOOOMMMMMM!!!', 129000),
('GA005', 'Honkai: Star Track', 'Kafka....Kafka....Kafka....Kafka....Kafka....Kafka....Kafka...Kafka....Kafka....Kafka.... \r\nWelcome to Gacha Hell (⊙Д⊙)', 69000);

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `TransactionID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `GameID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`TransactionID`, `GameID`, `Quantity`) VALUES
('TR001', 'GA002', 2),
('TR002', 'GA005', 3),
('TR003', 'GA004', 1),
('TR004', 'GA001', 2),
('TR005', 'GA002', 10),
('TR006', 'GA004', 6),
('TR007', 'GA005', 6),
('TR008', 'GA003', 2),
('TR009', 'GA002', 1),
('TR009', 'GA004', 1),
('TR010', 'GA002', 2),
('TR11', 'GA002', 4),
('TR11', 'GA003', 1),
('TR11', 'GA004', 1),
('TR11', 'GA005', 1),
('TR12', 'GA001', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `TransactionID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `UserID` char(5) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionheader`
--

INSERT INTO `transactionheader` (`TransactionID`, `UserID`) VALUES
('TR009', 'AC002'),
('TR010', 'AC002'),
('TR007', 'AC003'),
('TR008', 'AC003'),
('TR005', 'AC004'),
('TR006', 'AC004'),
('TR003', 'AC005'),
('TR004', 'AC005'),
('TR001', 'AC006'),
('TR002', 'AC006'),
('TR11', 'AC007'),
('TR12', 'AC007'),
('TR13', 'AC007');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `PhoneNumber` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Role` varchar(10) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `PhoneNumber`, `Email`, `Role`) VALUES
('AC001', 'admin', 'admin123', '97100109105110', 'admin@admin.com', 'admin'),
('AC002', 'Whillington', 'Whillington101', '0816942069', 'Whillington@gmail.com', 'customer'),
('AC003', 'PomPom', '3xpr3ss', '089804503856', 'conductor@space.station', 'customer'),
('AC004', 'Malenia', 'M1qu3ll4', '4139999999', 'prepare@die.edition', 'customer'),
('AC005', 'Peppy', 'N3ur0C00k1ng', '0899833964', 'pe@ppy.sh', 'customer'),
('AC006', 'customer', 'customer123', '123456789', 'customer@customer.com', 'customer'),
('AC007', 'dafahan', '123123', '123123', 'daf@ha.n', 'customer'),
('AC008', 'admins', '123123', '123123123', 'admin@ad.min', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`UserID`,`GameID`),
  ADD KEY `FK_cart_GameID` (`GameID`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`GameID`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`TransactionID`,`GameID`),
  ADD KEY `FK_GameID` (`GameID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`TransactionID`,`UserID`),
  ADD KEY `FK_UserID` (`UserID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_cart_GameID` FOREIGN KEY (`GameID`) REFERENCES `game` (`GameID`),
  ADD CONSTRAINT `FK_cart_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD CONSTRAINT `FK_GameID_New` FOREIGN KEY (`GameID`) REFERENCES `game` (`GameID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_TransactionID` FOREIGN KEY (`TransactionID`) REFERENCES `transactionheader` (`TransactionID`);

--
-- Constraints for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD CONSTRAINT `FK_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
