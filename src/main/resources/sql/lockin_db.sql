-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2025 at 05:19 AM
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
-- Database: `lockin_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `task_title` varchar(255) DEFAULT NULL,
  `task_category` varchar(100) DEFAULT NULL,
  `task_notification` varchar(10) DEFAULT 'None',
  `task_status` varchar(50) DEFAULT 'Pending',
  `date_created` datetime DEFAULT current_timestamp(),
  `date_updated` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`task_id`, `user_id`, `task_title`, `task_category`, `task_notification`, `task_status`, `date_created`, `date_updated`) VALUES
(1, 8, 'Water', 'Personal', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 08:53:15'),
(2, 8, 'Get Another Water', 'Personal', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 08:53:18'),
(3, 8, 'Another Water', 'Personal', 'None', 'Skipped', '2025-10-25 00:00:00', '2025-10-27 08:58:08'),
(4, 8, 'This is Getting Annoying', 'Personal', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 08:59:10'),
(5, 8, 'Schooling', 'School', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 23:34:23'),
(6, 8, 'Event', 'Event', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 23:34:23'),
(7, 8, 'Yez', 'School', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 23:34:23'),
(8, 8, 'Yez', 'School', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 23:34:23'),
(9, 8, 'Yez', 'School', 'None', 'Done', '2025-10-25 00:00:00', '2025-10-27 23:34:23'),
(10, 9, 'Assignment - Filipino', 'School', 'None', 'Pending', '2025-10-25 00:00:00', '2025-10-26 00:00:00'),
(11, 9, 'Drink Water', 'Personal', 'None', 'Pending', '2025-10-25 00:00:00', '2025-10-26 00:00:00'),
(12, 9, 'Another Task', 'Personal', 'None', 'Pending', '2025-10-25 00:00:00', '2025-10-26 00:00:00'),
(13, 8, 'Anything', 'Personal', 'None', 'Skipped', '2025-10-26 00:00:00', '2025-10-26 13:32:20'),
(14, 8, 'Yez', 'School', 'None', 'Done', '2025-10-26 00:00:00', '2025-10-26 13:29:06'),
(15, 8, 'Another Task', 'Event', 'None', 'Skipped', '2025-10-26 00:00:00', '2025-10-26 13:32:25'),
(16, 8, 'Another', 'Personal', 'None', 'Done', '2025-10-26 00:00:00', '2025-10-26 13:29:06'),
(17, 8, 'Yezzz', 'Personal', 'None', 'Done', '2025-10-27 09:00:33', '2025-10-28 00:02:28'),
(18, 8, 'Nilat', 'Personal', 'None', 'Done', '2025-10-27 09:08:00', '2025-10-28 00:01:42'),
(19, 8, 'otin', 'Personal', 'None', 'Done', '2025-10-27 16:33:46', '2025-10-28 00:01:44'),
(20, 1, 'something', 'Personal', 'None', 'Done', '2025-11-18 09:24:38', '2025-11-22 15:03:32'),
(21, 1, 'Exercise', 'School', 'None', 'Done', '2025-11-18 09:39:35', '2025-11-22 15:03:30'),
(22, 1, 'Practice Drum', 'Personal', 'None', 'Done', '2025-11-22 15:03:07', '2025-11-22 15:03:23'),
(23, 1, 'Help', 'Personal', '5 Hour', 'Done', '2025-11-25 11:05:48', '2025-11-25 18:24:34'),
(24, 1, 'aawada', 'Personal', '5 Hour', 'Done', '2025-11-25 11:34:00', '2025-11-25 17:50:33'),
(25, 1, 'asdwhdoajdioajdia', 'Personal', 'None', 'Done', '2025-11-25 12:45:32', '2025-11-25 17:50:35'),
(26, 1, 'JIASJDIAJ', 'Personal', '5 Minutes', 'Skipped', '2025-11-25 12:52:19', '2025-11-25 17:51:59'),
(27, 1, 'Someahr', 'Personal', '5 Minutes', 'Done', '2025-11-25 12:59:35', '2025-11-25 18:37:27'),
(28, 1, 'adwasd', 'Personal', '5 Minutes', 'Done', '2025-11-25 13:00:30', '2025-11-25 18:37:27'),
(29, 1, 'dDJISAJDIA', 'Personal', '5 Minutes', 'Done', '2025-11-25 13:01:39', '2025-11-25 17:52:08'),
(30, 1, 'ASjijdw', 'Personal', '5 Minutes', 'Done', '2025-11-25 13:02:42', '2025-11-25 17:52:13'),
(31, 1, 'samoka nimo bai', 'Personal', 'None', 'Done', '2025-11-25 00:00:00', '2025-11-25 18:28:54'),
(32, 1, 'This is not good bai', 'Personal', 'None', 'Done', '2025-11-26 00:00:00', '2025-11-25 18:28:46'),
(33, 1, 'Not really good bai', 'Personal', 'None', 'Done', '2025-11-26 00:00:00', '2025-11-25 18:37:26'),
(34, 1, 'fsdfdsfs', 'Personal', 'None', 'Done', '2025-11-27 00:00:00', '2025-11-26 07:23:55'),
(35, 1, 'Networking', 'Personal', '5 Minutes', 'Done', '2025-11-26 16:39:08', '2025-11-28 21:43:23'),
(36, 1, 'Her her', 'Personal', '5 Minutes', 'Pending', '2025-11-29 00:00:00', '2025-11-29 00:24:32'),
(37, 1, 'I want this finished', 'Personal', '5 Minutes', 'Done', '2025-11-28 00:00:00', '2025-11-28 23:19:29'),
(38, 1, 'AnotherOne', 'Personal', '5 Minutes', 'Done', '2025-11-28 00:00:00', '2025-11-28 23:19:33'),
(39, 1, 'YAY', 'Personal', 'None', 'Done', '2025-11-28 00:00:00', '2025-11-28 22:35:00'),
(40, 1, 'Creating a task', 'Personal', NULL, 'Done', '2025-12-06 00:00:00', '2025-11-28 23:10:41'),
(41, 1, 'HADHWAIHD', 'Personal', NULL, 'Done', '2025-11-30 00:00:00', '2025-11-28 23:10:36'),
(42, 1, 'Yez', 'Personal', NULL, 'Pending', '2025-12-01 00:00:00', '2025-11-28 23:14:13'),
(43, 1, 'jdiasjdoiwa', 'Personal', NULL, 'Pending', '2025-12-02 00:00:00', '2025-11-28 23:17:11'),
(44, 1, 'aoisdjiaoda', 'Personal', '5 Minutes', 'Pending', '2025-11-29 00:00:00', '2025-11-29 00:24:52');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`) VALUES
(1, 'Zev', 'Torrentira', 'zevtorrentira@gmail.com', 'zevtorrentira'),
(2, 'Osbev', 'Cabucos', 'osbevcabucos@gmail.com', 'osbevcabucos'),
(3, 'Virna', 'Arias', 'virnaarias@gmail.com', 'virnaarias'),
(4, 'Znelle', 'Torrentira', 'zeltorrentira@gmail.com', 'Welcome@2026'),
(5, 'Neil', 'Torrentira', 'neiltorrentira@gmail.com', 'neiltorrentira'),
(6, 'Drixyl', 'Nacu', 'drixylnacu@gmail.com', 'drixylnacu'),
(7, 'Sweet', 'Perino', 'sweetperino@gmail.com', 'sweetperino'),
(8, 'John', 'Canete', 't@gmail.com', '123456789'),
(9, 'Zeth', 'Torrentira', 'zethtorrentira@gmail.com', '123456789'),
(10, 'pussy', 'cat', 'pussycat@gmail.com', 'pussycat'),
(11, 'tai', 'verdes', 'taiverdes@gmail.com', 'taiverdes');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
