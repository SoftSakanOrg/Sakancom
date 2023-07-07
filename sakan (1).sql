-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2023 at 06:11 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sakan`
--

-- --------------------------------------------------------

--
-- Table structure for table `houses`
--

CREATE TABLE `houses` (
  `house_id` int(100) NOT NULL,
  `house_name` varchar(100) NOT NULL,
  `availability` varchar(100) NOT NULL,
  `price` int(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `services` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `houses`
--

INSERT INTO `houses` (`house_id`, `house_name`, `availability`, `price`, `location`, `services`) VALUES
(2, 'House1', 'available', 100, 'Nablus', 'service1'),
(3, 'House2', 'unavailabe', 200, 'Nablus', 'service2'),
(4, 'Hosue3', 'available', 300, 'Jenin', 'service3');

-- --------------------------------------------------------

--
-- Table structure for table `house_pic`
--

CREATE TABLE `house_pic` (
  `house_pic_id` int(100) NOT NULL,
  `house_id` int(100) NOT NULL,
  `picture` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tenants`
--

CREATE TABLE `tenants` (
  `ID` int(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tenants`
--

INSERT INTO `tenants` (`ID`, `Email`, `username`, `password`) VALUES
(1, 'Abdallah@gmail.com', 'Abdallah', '123'),
(2, 'Salah@gmail.com', 'Salah', '1234567'),
(20, 'asff@wrgerg', 'rrher', '23523'),
(21, 'sus@gmail.com', 'asgadg', '21415'),
(22, 'asfas@sdg.com', 'ser', '05867'),
(23, 'erhrth@hdfb.as', 'st', '8980'),
(24, 'asgadg@sdgdgsb.aepojs', '12412', '2412412'),
(25, 'Salah@gmail.com', '678679', '769'),
(26, 'egwegwe@sdgg.wer', 'forl', '4567'),
(27, 'asfadvad@drheh.segg', 'grtj', '89780'),
(28, 'asgfshre@ytkyug,.dagsdg', 'wert', '675'),
(29, 'asfasf@dhtrh.asdw', 'tyu', '346');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`house_id`);

--
-- Indexes for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD PRIMARY KEY (`house_pic_id`),
  ADD KEY `house_id` (`house_id`);

--
-- Indexes for table `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `houses`
--
ALTER TABLE `houses`
  MODIFY `house_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `house_pic`
--
ALTER TABLE `house_pic`
  MODIFY `house_pic_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tenants`
--
ALTER TABLE `tenants`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD CONSTRAINT `house_pic_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
