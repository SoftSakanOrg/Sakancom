-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2023 at 11:55 PM
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
-- Table structure for table `furniture`
--

CREATE TABLE `furniture` (
  `furniture_id` int(100) NOT NULL,
  `tenant_ID` int(100) NOT NULL,
  `price` int(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `services` varchar(500) NOT NULL,
  `participants` int(100) NOT NULL,
  `max_participants` int(100) NOT NULL,
  `owner_name` varchar(100) NOT NULL,
  `contact_num` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `houses`
--

INSERT INTO `houses` (`house_id`, `house_name`, `availability`, `price`, `location`, `services`, `participants`, `max_participants`, `owner_name`, `contact_num`) VALUES
(2, 'House1', 'available', 100, 'Nablus', 'service1', 6, 9, 'Abdul', 999),
(3, 'House2', 'unavailable', 200, 'Nablus', 'service2', 1, 1, '', 0),
(4, 'House3', 'available', 300, 'Jenin', 'service3', 4, 6, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `house_participants`
--

CREATE TABLE `house_participants` (
  `part_id` int(100) NOT NULL,
  `house_id` int(100) NOT NULL,
  `part_name` varchar(100) NOT NULL,
  `part_age` int(100) NOT NULL,
  `part_major` varchar(100) NOT NULL,
  `part_gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `house_participants`
--

INSERT INTO `house_participants` (`part_id`, `house_id`, `part_name`, `part_age`, `part_major`, `part_gender`) VALUES
(1, 4, 'Aydi', 21, 'computer', 'male'),
(3, 2, 'marwa', 25, 'dentist', 'female'),
(4, 4, 'Salah', 21, 'comp', 'm'),
(5, 4, 'Abdallah', 50, 'eng', 'm'),
(6, 4, 'Abdallah', 24, 'mobile', 'm'),
(7, 4, 'Salah', 56, 'er', 'm'),
(8, 4, 'Salah', 23, 'awe', 'm'),
(9, 4, 'Abdallah', 34, 'rty', 'm'),
(12, 2, 'sad', 22, 'vs', 'f'),
(13, 2, 'sad', 2, 'tt', 'f'),
(15, 2, 'sad', 11, 'oo', 'f'),
(16, 3, 'sad', 0, 'Unknown', 'Unknown');

-- --------------------------------------------------------

--
-- Table structure for table `house_pic`
--

CREATE TABLE `house_pic` (
  `house_pic_id` int(100) NOT NULL,
  `house_id` int(100) NOT NULL,
  `picture` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `house_pic`
--

INSERT INTO `house_pic` (`house_pic_id`, `house_id`, `picture`) VALUES
(3, 2, 'pich1'),
(4, 2, 'pich11'),
(5, 4, 'pich4');

-- --------------------------------------------------------

--
-- Table structure for table `owners`
--

CREATE TABLE `owners` (
  `ID` int(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `contact_number` int(100) NOT NULL
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
(2, 'Salah@gmail.com', 'Salah', '1234'),
(36, 'sos@ses.sas', '333', '321'),
(37, 'ser@se.se', 'sad', '123'),
(38, 's@s.s', 'sad', '123'),
(39, 'Ses@f.f', 'te', '555'),
(40, 'fa@fe.com', 'trrrr', '777'),
(41, 'F@af.com', 'abdul', '0000'),
(42, 'qwfqwf@per.com', 'fork', '765'),
(43, 'werk@grg.no', 'dod', '1111');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `furniture`
--
ALTER TABLE `furniture`
  ADD PRIMARY KEY (`furniture_id`),
  ADD KEY `tenant_ID` (`tenant_ID`);

--
-- Indexes for table `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`house_id`);

--
-- Indexes for table `house_participants`
--
ALTER TABLE `house_participants`
  ADD PRIMARY KEY (`part_id`),
  ADD KEY `house_id` (`house_id`);

--
-- Indexes for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD PRIMARY KEY (`house_pic_id`),
  ADD KEY `house_id` (`house_id`);

--
-- Indexes for table `owners`
--
ALTER TABLE `owners`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tenants`
--
ALTER TABLE `tenants`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `furniture`
--
ALTER TABLE `furniture`
  MODIFY `furniture_id` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `houses`
--
ALTER TABLE `houses`
  MODIFY `house_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `house_participants`
--
ALTER TABLE `house_participants`
  MODIFY `part_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `house_pic`
--
ALTER TABLE `house_pic`
  MODIFY `house_pic_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `owners`
--
ALTER TABLE `owners`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tenants`
--
ALTER TABLE `tenants`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `furniture`
--
ALTER TABLE `furniture`
  ADD CONSTRAINT `furniture_ibfk_1` FOREIGN KEY (`tenant_ID`) REFERENCES `tenants` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `house_participants`
--
ALTER TABLE `house_participants`
  ADD CONSTRAINT `house_participants_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD CONSTRAINT `house_pic_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `houses` (`house_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
