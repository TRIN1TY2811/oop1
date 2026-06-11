-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2026 at 04:02 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `schoolsystemdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cpattendance`
--

CREATE TABLE `cpattendance` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Week1` varchar(50) DEFAULT NULL,
  `Week2` varchar(50) DEFAULT NULL,
  `Week3` varchar(50) DEFAULT NULL,
  `Week4` varchar(50) DEFAULT NULL,
  `Week5` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cpgrades`
--

CREATE TABLE `cpgrades` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quiz` int(11) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `final_grade` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `integattendance`
--

CREATE TABLE `integattendance` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Week1` varchar(50) DEFAULT NULL,
  `Week2` varchar(50) DEFAULT NULL,
  `Week3` varchar(50) DEFAULT NULL,
  `Week4` varchar(50) DEFAULT NULL,
  `Week5` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `integgrades`
--

CREATE TABLE `integgrades` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quiz` int(11) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `final_grade` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `integgrades`
--

INSERT INTO `integgrades` (`student_id`, `name`, `quiz`, `exam`, `project`, `final_grade`) VALUES
(1, 'James', 86, 87, 96, 89.67),
(2, 'Edward', 87, 86, 95, 89.33),
(3, 'Thomas', 86, 86, 96, 89.33);

-- --------------------------------------------------------

--
-- Table structure for table `netattendance`
--

CREATE TABLE `netattendance` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Week1` varchar(50) DEFAULT NULL,
  `Week2` varchar(50) DEFAULT NULL,
  `Week3` varchar(50) DEFAULT NULL,
  `Week4` varchar(50) DEFAULT NULL,
  `Week5` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `netgrades`
--

CREATE TABLE `netgrades` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quiz` int(11) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `final_grade` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oopattendance`
--

CREATE TABLE `oopattendance` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Week1` varchar(10) DEFAULT NULL,
  `Week2` varchar(10) DEFAULT NULL,
  `Week3` varchar(10) DEFAULT NULL,
  `Week4` varchar(10) DEFAULT NULL,
  `Week5` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oopattendance`
--

INSERT INTO `oopattendance` (`ID`, `Name`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`) VALUES
(1, 'James', 'Present', 'Present', 'Present', 'Present', 'Present'),
(2, 'Edward', 'Present', 'Present', 'Present', 'Absent', 'Absent'),
(3, 'Maxine', 'Present', 'Present', 'Present', 'Absent', 'Absent');

-- --------------------------------------------------------

--
-- Table structure for table `oopgrades`
--

CREATE TABLE `oopgrades` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quiz` int(11) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `final_grade` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `oopgrades`
--

INSERT INTO `oopgrades` (`student_id`, `name`, `quiz`, `exam`, `project`, `final_grade`) VALUES
(1, 'Edward', 85, 87, 96, 89.33),
(2, 'James', 85, 87, 96, 89.33),
(3, 'Thomas', 85, 87, 96, 89.33);

-- --------------------------------------------------------

--
-- Table structure for table `osattendance`
--

CREATE TABLE `osattendance` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Week1` varchar(50) DEFAULT NULL,
  `Week2` varchar(50) DEFAULT NULL,
  `Week3` varchar(50) DEFAULT NULL,
  `Week4` varchar(50) DEFAULT NULL,
  `Week5` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `osgrades`
--

CREATE TABLE `osgrades` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quiz` int(11) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `final_grade` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `studentinfo`
--

CREATE TABLE `studentinfo` (
  `student_id` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `studentinfo`
--

INSERT INTO `studentinfo` (`student_id`, `name`, `age`, `gender`, `birthday`) VALUES
('1', 'James Earl', 21, 'Male', '2005-11-28'),
('2', 'Edward', 21, 'Male', '2005-11-23');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Username`, `Password`) VALUES
(1, 'admin', 'admin'),
(2, 'student1', 'pass123'),
(3, 'teacher1', 'teach123'),
(4, 'aaa', 'aaa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cpattendance`
--
ALTER TABLE `cpattendance`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cpgrades`
--
ALTER TABLE `cpgrades`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `integattendance`
--
ALTER TABLE `integattendance`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `integgrades`
--
ALTER TABLE `integgrades`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `netattendance`
--
ALTER TABLE `netattendance`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `netgrades`
--
ALTER TABLE `netgrades`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `oopattendance`
--
ALTER TABLE `oopattendance`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `oopgrades`
--
ALTER TABLE `oopgrades`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `osattendance`
--
ALTER TABLE `osattendance`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `osgrades`
--
ALTER TABLE `osgrades`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `studentinfo`
--
ALTER TABLE `studentinfo`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cpattendance`
--
ALTER TABLE `cpattendance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `integattendance`
--
ALTER TABLE `integattendance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `netattendance`
--
ALTER TABLE `netattendance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `oopattendance`
--
ALTER TABLE `oopattendance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2025;

--
-- AUTO_INCREMENT for table `osattendance`
--
ALTER TABLE `osattendance`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
