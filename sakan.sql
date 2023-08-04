-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2023 at 09:10 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

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
-- Table structure for table `advertisment_requests`
--

CREATE TABLE `advertisment_requests` (
  `req_id` int(100) NOT NULL,
  `Building_name` varchar(100) NOT NULL,
  `Owner_name` varchar(100) NOT NULL,
  `contact_number` int(100) NOT NULL,
  `Price` int(100) NOT NULL,
  `floor_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `advertisment_requests`
--

INSERT INTO `advertisment_requests` (`req_id`, `Building_name`, `Owner_name`, `contact_number`, `Price`, `floor_id`) VALUES
(8, 'Whitehouse', 'Salah', 59793, 500, 25);

-- --------------------------------------------------------

--
-- Table structure for table `booking_info`
--

CREATE TABLE `booking_info` (
  `ID` int(100) NOT NULL,
  `Tenant_Name` varchar(100) NOT NULL,
  `Owner_Name` varchar(100) NOT NULL,
  `Contact_info` int(100) NOT NULL,
  `Rent_Date` text NOT NULL,
  `Tenant_ID` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE `building` (
  `building_id` int(100) NOT NULL,
  `owner_id` int(100) NOT NULL,
  `building_name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `floors_num` int(100) NOT NULL,
  `owner_name` varchar(100) NOT NULL,
  `contact_num` int(100) NOT NULL,
  `TotalParticipants` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `building`
--

INSERT INTO `building` (`building_id`, `owner_id`, `building_name`, `location`, `floors_num`, `owner_name`, `contact_num`, `TotalParticipants`) VALUES
(9, 33, 'Whitehouse', 'nablus', 1, 'Salah', 59792, 0);

-- --------------------------------------------------------

--
-- Table structure for table `floors`
--

CREATE TABLE `floors` (
  `floor_id` int(100) NOT NULL,
  `building_id` int(100) NOT NULL,
  `availability` varchar(100) NOT NULL,
  `price` int(100) NOT NULL,
  `services` varchar(500) NOT NULL,
  `participants` int(100) NOT NULL,
  `max_participants` int(100) NOT NULL,
  `Bedrooms` int(100) NOT NULL,
  `Bathrooms` int(100) NOT NULL,
  `Balcony` int(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `floors`
--

INSERT INTO `floors` (`floor_id`, `building_id`, `availability`, `price`, `services`, `participants`, `max_participants`, `Bedrooms`, `Bathrooms`, `Balcony`, `Status`) VALUES
(25, 9, 'available', 400, 'goodservice', 0, 5, 2, 2, 1, 'Not_Advertised');

-- --------------------------------------------------------

--
-- Table structure for table `furniture`
--

CREATE TABLE `furniture` (
  `furniture_id` int(100) NOT NULL,
  `user_ID` int(100) NOT NULL,
  `price` int(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `furniture`
--

INSERT INTO `furniture` (`furniture_id`, `user_ID`, `price`, `description`, `Status`) VALUES
(33, 32, 99, 'dag', 'forsale');

-- --------------------------------------------------------

--
-- Table structure for table `furniture_pic`
--

CREATE TABLE `furniture_pic` (
  `furniture_pic_id` int(100) NOT NULL,
  `furniture_id` int(100) NOT NULL,
  `picture` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `house_participants`
--

CREATE TABLE `house_participants` (
  `part_id` int(100) NOT NULL,
  `floor_id` int(100) NOT NULL,
  `part_name` varchar(100) NOT NULL,
  `part_age` int(100) NOT NULL,
  `part_major` varchar(100) NOT NULL,
  `part_gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `house_pic`
--

CREATE TABLE `house_pic` (
  `house_pic_id` int(100) NOT NULL,
  `floor_id` int(100) NOT NULL,
  `picture` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `system_observation`
--

CREATE TABLE `system_observation` (
  `ID` int(100) NOT NULL,
  `description` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `system_observation`
--

INSERT INTO `system_observation` (`ID`, `description`) VALUES
(33, 'Owner@o.com has added a building (Whitehouse) to the system'),
(34, 'Owner@o.com has added an apartment  (Whitehouse) to his building');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` int(100) NOT NULL,
  `contact_num` int(100) NOT NULL,
  `user_type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `username`, `password`, `contact_num`, `user_type`) VALUES
(30, 'a@a.a', 'Admin', 999, 789, 'ADMIN'),
(32, 'abd@sal.com', 'Abdul', 123, 59945, 'TENANTS'),
(33, 'Owner@o.com', 'Salah', 123, 59792, 'OWNERS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advertisment_requests`
--
ALTER TABLE `advertisment_requests`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `floor_id` (`floor_id`);

--
-- Indexes for table `booking_info`
--
ALTER TABLE `booking_info`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Tenant_ID` (`Tenant_ID`);

--
-- Indexes for table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`building_id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `floors`
--
ALTER TABLE `floors`
  ADD PRIMARY KEY (`floor_id`),
  ADD KEY `building_id` (`building_id`);

--
-- Indexes for table `furniture`
--
ALTER TABLE `furniture`
  ADD PRIMARY KEY (`furniture_id`),
  ADD KEY `tenant_ID` (`user_ID`);

--
-- Indexes for table `furniture_pic`
--
ALTER TABLE `furniture_pic`
  ADD PRIMARY KEY (`furniture_pic_id`),
  ADD KEY `furniture_id` (`furniture_id`);

--
-- Indexes for table `house_participants`
--
ALTER TABLE `house_participants`
  ADD PRIMARY KEY (`part_id`),
  ADD KEY `house_id` (`floor_id`);

--
-- Indexes for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD PRIMARY KEY (`house_pic_id`),
  ADD KEY `house_id` (`floor_id`);

--
-- Indexes for table `system_observation`
--
ALTER TABLE `system_observation`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advertisment_requests`
--
ALTER TABLE `advertisment_requests`
  MODIFY `req_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `booking_info`
--
ALTER TABLE `booking_info`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `building`
--
ALTER TABLE `building`
  MODIFY `building_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `floors`
--
ALTER TABLE `floors`
  MODIFY `floor_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `furniture`
--
ALTER TABLE `furniture`
  MODIFY `furniture_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `furniture_pic`
--
ALTER TABLE `furniture_pic`
  MODIFY `furniture_pic_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `house_participants`
--
ALTER TABLE `house_participants`
  MODIFY `part_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `house_pic`
--
ALTER TABLE `house_pic`
  MODIFY `house_pic_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `system_observation`
--
ALTER TABLE `system_observation`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advertisment_requests`
--
ALTER TABLE `advertisment_requests`
  ADD CONSTRAINT `advertisment_requests_ibfk_1` FOREIGN KEY (`floor_id`) REFERENCES `floors` (`floor_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `booking_info`
--
ALTER TABLE `booking_info`
  ADD CONSTRAINT `booking_info_ibfk_1` FOREIGN KEY (`Tenant_ID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `building_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `floors`
--
ALTER TABLE `floors`
  ADD CONSTRAINT `floors_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`building_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `furniture`
--
ALTER TABLE `furniture`
  ADD CONSTRAINT `furniture_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `furniture_pic`
--
ALTER TABLE `furniture_pic`
  ADD CONSTRAINT `furniture_pic_ibfk_1` FOREIGN KEY (`furniture_id`) REFERENCES `furniture` (`furniture_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `house_participants`
--
ALTER TABLE `house_participants`
  ADD CONSTRAINT `house_participants_ibfk_1` FOREIGN KEY (`floor_id`) REFERENCES `floors` (`floor_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `house_pic`
--
ALTER TABLE `house_pic`
  ADD CONSTRAINT `house_pic_ibfk_1` FOREIGN KEY (`floor_id`) REFERENCES `floors` (`floor_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
