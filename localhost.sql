-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 10, 2022 at 04:21 AM
-- Server version: 5.7.39-cll-lve
-- PHP Version: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `baadbaan_db`
--
CREATE DATABASE IF NOT EXISTS `baadbaan_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `baadbaan_db`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `email` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `address2` text COLLATE utf8_persian_ci NOT NULL,
  `province` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `city` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `code` bigint(20) NOT NULL,
  `image` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `username`, `password`, `email`, `address`, `address2`, `province`, `city`, `code`, `image`) VALUES
(1, 'admin2022_68', 'ec28a05c7ccf4fd920026334831adb55', '', 'تهران', 'جمهوری', 'تهران', 'کرج', 52, '24.png'),
(2, 'baadbaan_admin', 'ec28a05c7ccf4fd920026334831adb55', 'm@gmail.com', 'بوعلی', 'آپارتمان رز', 'همدان', 'همدان', 55255, '30.png');

-- --------------------------------------------------------

--
-- Table structure for table `advertising`
--

CREATE TABLE `advertising` (
  `adv_id` int(11) NOT NULL,
  `adv_title` text COLLATE utf8_persian_ci NOT NULL,
  `adv_img` text COLLATE utf8_persian_ci NOT NULL,
  `adv_url` text COLLATE utf8_persian_ci NOT NULL,
  `adv_dir` text COLLATE utf8_persian_ci NOT NULL,
  `adv_company` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `adv_phone` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `advertising`
--

INSERT INTO `advertising` (`adv_id`, `adv_title`, `adv_img`, `adv_url`, `adv_dir`, `adv_company`, `adv_phone`) VALUES
(1, 'advertising 1', 'https://baadbaanapp.ir/public/img/advertising/adv1.png', 'www', 'public/img/advertising/adv1.png', 'company name', '08112225556'),
(2, 'adv 2', 'https://baadbaanapp.ir/public/img/advertising/adv2.png', 'www', 'public/img/advertising/adv2.png', 'company name', '9188881111');

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE `agency` (
  `id` int(11) NOT NULL,
  `fname` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `lastname` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `father_name` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `national_code` int(11) NOT NULL,
  `phone` int(11) NOT NULL,
  `shop_phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL,
  `city` text COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `email` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `serial` text COLLATE utf8_persian_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `agency`
--

INSERT INTO `agency` (`id`, `fname`, `lastname`, `father_name`, `national_code`, `phone`, `shop_phone`, `job_category`, `province`, `city`, `address`, `email`, `serial`, `status`) VALUES
(9, 'علی', 'احمدی', 'father_name', 0, 2147483647, '0214445566', 'تعویض روغنی', '1', '32', 'همدان - شهرک مدنی 2', 'email', '8655c7fb7bd5374b1203c8b29494958f', 0),
(10, 'مهدی', 'مهدوی', 'father_name', 0, 2147483647, '0214445566', 'c', '12', '534', 'اذربایجان', 'email', 'a0f2c710fbe2d3802d9122473e0d238c', 0),
(11, 'حسین', 'حسینی', 'father_name', 0, 2147483647, '0214445566', 'تعویض روغنی', '30', '1256', 'همدان شهرک مدنی', 'email', 'f1403503e8dc301e390d4acaa1b3b0c0', 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `all_users`
-- (See below for the actual view)
--
CREATE TABLE `all_users` (
`user_id` int(255)
,`name` text
,`d_id` int(11)
,`lastname` text
,`sex` text
,`phone` varchar(255)
,`birth_date` date
,`register_date` date
,`user_is_change` bit(1)
,`is_delete` bit(1)
,`status` int(11)
,`car_name` text
,`car_tag` varchar(9)
,`car_model` text
,`car_type` text
,`fuel_type` text
,`avg_function` bigint(20)
,`chassis_num` text
,`engine_cap` int(255)
,`last_date_of_cheak_service_timing` date
);

-- --------------------------------------------------------

--
-- Table structure for table `amazing_sales`
--

CREATE TABLE `amazing_sales` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `percentage` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `send_notification` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `amazing_sales`
--

INSERT INTO `amazing_sales` (`id`, `product_id`, `percentage`, `status`, `send_notification`, `start_date`, `end_date`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 50, 0, 0, '2022-07-10 21:12:09', '2022-07-08 21:12:09', '2022-07-07 06:15:05', '2022-07-10 05:23:19', NULL),
(2, 1, 10, 1, 0, '2022-06-26 09:42:22', '2022-07-07 09:42:22', '2022-07-07 09:42:33', '2022-07-07 09:42:33', NULL),
(3, 1, 20, 1, 0, '2022-07-17 09:44:25', '2022-07-20 09:44:25', '2022-07-07 09:44:34', '2022-07-07 10:15:38', NULL),
(4, 1, 70, 0, 0, '2022-07-07 09:45:53', '2022-07-07 09:45:53', '2022-07-07 09:45:58', '2022-07-07 09:45:58', NULL),
(5, 1, 20, 0, 0, '2022-07-07 09:46:24', '2022-07-07 09:46:24', '2022-07-07 09:46:28', '2022-07-07 09:46:28', NULL),
(6, 1, 20, 0, 0, '2022-07-07 09:46:57', '2022-07-07 09:46:57', '2022-07-07 09:47:01', '2022-07-07 09:47:01', NULL),
(7, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:03', '2022-07-07 09:48:03', NULL),
(8, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:13', '2022-07-07 09:48:13', NULL),
(9, 1, 70, 0, 0, '2022-07-07 09:48:49', '2022-07-07 09:48:49', '2022-07-07 09:48:57', '2022-07-07 09:48:57', NULL),
(10, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:38', '2022-07-07 09:52:38', NULL),
(11, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:59', '2022-07-07 09:52:59', NULL),
(12, 1, 20, 0, 0, '2022-07-07 10:07:31', '2022-07-07 10:07:31', '2022-07-07 10:07:42', '2022-07-07 10:07:42', NULL),
(13, 1, 20, 1, 0, '2022-07-07 10:07:57', '2022-07-07 10:07:57', '2022-07-07 10:08:02', '2022-07-07 10:08:02', NULL),
(14, 1, 10, 1, 0, '2022-07-07 10:09:59', '2022-07-07 10:09:59', '2022-07-07 10:10:06', '2022-07-07 10:10:06', NULL),
(15, 1, 10, 1, 0, '2022-07-07 10:18:26', '2022-07-07 10:18:26', '2022-07-07 10:18:30', '2022-07-07 10:18:30', NULL),
(16, 1, 20, 1, 0, '2022-07-07 18:04:03', '2022-07-07 18:04:04', '2022-07-07 18:04:13', '2022-07-07 18:04:13', NULL),
(17, 1, 20, 1, 0, '2022-07-07 19:09:38', '2022-07-07 19:09:38', '2022-07-07 19:09:45', '2022-07-07 19:09:45', NULL),
(18, 1, 10, 1, 1, '2022-07-07 19:11:13', '2022-07-07 19:11:13', '2022-07-07 19:11:21', '2022-07-07 19:11:21', NULL),
(19, 1, 10, 1, 1, '2022-07-07 21:21:04', '2022-07-07 21:21:04', '2022-07-07 21:21:10', '2022-07-07 21:21:10', NULL),
(20, 1, 20, 0, 1, '2022-07-07 21:21:55', '2022-07-07 21:21:55', '2022-07-07 21:22:01', '2022-07-07 21:22:01', NULL),
(21, 1, 10, 0, 1, '2022-07-08 19:58:05', '2022-07-08 19:58:05', '2022-07-08 19:58:12', '2022-07-08 19:58:12', NULL),
(22, 1, 10, 1, 1, '2022-07-10 05:18:22', '2022-07-10 05:18:22', '2022-07-10 05:18:43', '2022-07-10 05:18:43', NULL),
(1, 1, 50, 0, 0, '2022-07-10 21:12:09', '2022-07-08 21:12:09', '2022-07-07 06:15:05', '2022-07-10 05:23:19', NULL),
(2, 1, 10, 1, 0, '2022-06-26 09:42:22', '2022-07-07 09:42:22', '2022-07-07 09:42:33', '2022-07-07 09:42:33', NULL),
(3, 1, 20, 1, 0, '2022-07-17 09:44:25', '2022-07-20 09:44:25', '2022-07-07 09:44:34', '2022-07-07 10:15:38', NULL),
(4, 1, 70, 0, 0, '2022-07-07 09:45:53', '2022-07-07 09:45:53', '2022-07-07 09:45:58', '2022-07-07 09:45:58', NULL),
(5, 1, 20, 0, 0, '2022-07-07 09:46:24', '2022-07-07 09:46:24', '2022-07-07 09:46:28', '2022-07-07 09:46:28', NULL),
(6, 1, 20, 0, 0, '2022-07-07 09:46:57', '2022-07-07 09:46:57', '2022-07-07 09:47:01', '2022-07-07 09:47:01', NULL),
(7, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:03', '2022-07-07 09:48:03', NULL),
(8, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:13', '2022-07-07 09:48:13', NULL),
(9, 1, 70, 0, 0, '2022-07-07 09:48:49', '2022-07-07 09:48:49', '2022-07-07 09:48:57', '2022-07-07 09:48:57', NULL),
(10, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:38', '2022-07-07 09:52:38', NULL),
(11, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:59', '2022-07-07 09:52:59', NULL),
(12, 1, 20, 0, 0, '2022-07-07 10:07:31', '2022-07-07 10:07:31', '2022-07-07 10:07:42', '2022-07-07 10:07:42', NULL),
(13, 1, 20, 1, 0, '2022-07-07 10:07:57', '2022-07-07 10:07:57', '2022-07-07 10:08:02', '2022-07-07 10:08:02', NULL),
(14, 1, 10, 1, 0, '2022-07-07 10:09:59', '2022-07-07 10:09:59', '2022-07-07 10:10:06', '2022-07-07 10:10:06', NULL),
(15, 1, 10, 1, 0, '2022-07-07 10:18:26', '2022-07-07 10:18:26', '2022-07-07 10:18:30', '2022-07-07 10:18:30', NULL),
(16, 1, 20, 1, 0, '2022-07-07 18:04:03', '2022-07-07 18:04:04', '2022-07-07 18:04:13', '2022-07-07 18:04:13', NULL),
(17, 1, 20, 1, 0, '2022-07-07 19:09:38', '2022-07-07 19:09:38', '2022-07-07 19:09:45', '2022-07-07 19:09:45', NULL),
(18, 1, 10, 1, 1, '2022-07-07 19:11:13', '2022-07-07 19:11:13', '2022-07-07 19:11:21', '2022-07-07 19:11:21', NULL),
(19, 1, 10, 1, 1, '2022-07-07 21:21:04', '2022-07-07 21:21:04', '2022-07-07 21:21:10', '2022-07-07 21:21:10', NULL),
(20, 1, 20, 0, 1, '2022-07-07 21:21:55', '2022-07-07 21:21:55', '2022-07-07 21:22:01', '2022-07-07 21:22:01', NULL),
(21, 1, 10, 0, 1, '2022-07-08 19:58:05', '2022-07-08 19:58:05', '2022-07-08 19:58:12', '2022-07-08 19:58:12', NULL),
(22, 1, 10, 1, 1, '2022-07-10 05:18:22', '2022-07-10 05:18:22', '2022-07-10 05:18:43', '2022-07-10 05:18:43', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `auto_service`
--

CREATE TABLE `auto_service` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` text COLLATE utf8_persian_ci NOT NULL,
  `b_date` date NOT NULL,
  `sex` int(1) NOT NULL,
  `email` text COLLATE utf8_persian_ci NOT NULL,
  `username` text COLLATE utf8_persian_ci NOT NULL,
  `password` text COLLATE utf8_persian_ci NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `back_up`
--

CREATE TABLE `back_up` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `department_id` bigint(20) UNSIGNED NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `file` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `banners`
--

CREATE TABLE `banners` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'developer explain 0 or 1 or ... in admin\\content\\banner model',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `category` int(11) NOT NULL,
  `display_group` int(11) NOT NULL,
  `picture` text COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `author` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `post_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `blog`
--

INSERT INTO `blog` (`id`, `title`, `category`, `display_group`, `picture`, `description`, `author`, `post_date`) VALUES
(7, 'رشد هوش مصنوعی', 1, 0, 'blog1.png', '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\nلورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.\r\n\r\nلورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.', 'admin', '2022-04-01'),
(8, 'مشاوره تجاری رشد محصول', 1, 0, 'blog2.png', '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', 'users', '2022-04-05'),
(50, '8882', 2, 0, 'contactus.png', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.\r\n\r\nلورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.', 'author', '2022-04-18');

-- --------------------------------------------------------

--
-- Table structure for table `buys`
--

CREATE TABLE `buys` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `service_center` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `tariffe_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `buys`
--

INSERT INTO `buys` (`id`, `name`, `email`, `mobile`, `type`, `service_center`, `address`, `tariffe_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'hffgfg', 'dasdasd@dadsa', '09997777656', 2, 'gjghjh', 'fdgfdgdgf', 3, '2022-07-16 10:52:57', '2022-07-16 10:52:57', NULL),
(2, 'hffgfg', 'salam@salam.com', '09398777656', 2, 'gjghjh', 'dfgdfdgfdfgfdg', 5, '2022-07-16 10:53:56', '2022-07-16 10:53:56', NULL),
(1, 'hffgfg', 'dasdasd@dadsa', '09997777656', 2, 'gjghjh', 'fdgfdgdgf', 3, '2022-07-16 10:52:57', '2022-07-16 10:52:57', NULL),
(2, 'hffgfg', 'salam@salam.com', '09398777656', 2, 'gjghjh', 'dfgdfdgfdfgfdg', 5, '2022-07-16 10:53:56', '2022-07-16 10:53:56', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `car_id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `car_tag` varchar(9) COLLATE utf8_persian_ci NOT NULL,
  `car_name` text COLLATE utf8_persian_ci NOT NULL,
  `car_model` text COLLATE utf8_persian_ci,
  `car_type` text COLLATE utf8_persian_ci NOT NULL,
  `fuel_type` text COLLATE utf8_persian_ci NOT NULL,
  `avg_function` bigint(20) DEFAULT NULL,
  `chassis_num` text COLLATE utf8_persian_ci,
  `engine_cap` int(255) DEFAULT NULL,
  `register_date` date NOT NULL,
  `is_delete` int(11) NOT NULL,
  `is_change` int(11) NOT NULL DEFAULT '0',
  `last_date_of_cheak_service_timing` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`car_id`, `user_id`, `car_tag`, `car_name`, `car_model`, `car_type`, `fuel_type`, `avg_function`, `chassis_num`, `engine_cap`, `register_date`, `is_delete`, `is_change`, `last_date_of_cheak_service_timing`) VALUES
(15, 28, '13ب41221', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, '2022-05-04'),
(18, 28, '13ق41518', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-14', 0, 0, '2022-05-01'),
(19, 28, '13ق41518', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, '2022-02-07'),
(20, 28, '18س4518', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2021-02-03', 0, 0, '2022-05-01'),
(21, 28, '18ج52632', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, '2021-09-07'),
(26, 28, '11ب25632', 'برلیانس', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, '2021-05-12'),
(28, 28, '99ر5582', 'پژو 405', '', '', 'بنزینی', 0, '', 0, '2022-02-01', 0, 0, '2022-05-02'),
(29, 28, '13ق43518', 'پژو 405', '', '', 'بنزینی', 0, '', 0, '2022-02-01', 0, 0, '2022-05-02'),
(30, 28, '13ع2218', 'پژو 405', '88', '', 'بنزینی', 500, '600', 700, '2022-02-01', 0, 0, '2022-05-03'),
(31, 28, '18ق62182', ' پیکان', '98', '', 'بنزینی', 500, '600', 700, '2022-02-01', 0, 0, '2022-05-02'),
(32, 28, ' 66س55223', ' بنز', '88', '', 'بنزینی', 500, '600', 700, '2021-02-01', 0, 0, '2014-02-01'),
(33, 28, '28ط25632', 'نیسان', '88', '', 'بنزینی', 500, '600', 700, '2020-04-20', 0, 0, '1400-02-01'),
(34, 28, '25ل65233', ' وانت', '98', '', 'بنزینی', 500, '600', 700, '2001-04-21', 0, 0, '2022-04-21'),
(35, 28, '13ذ8218 ', ' پیکان', '88', '', 'بنزینی', 500, '600', 700, '2019-04-21', 0, 0, '2019-04-21'),
(36, 28, '1341518ق', 'پژو', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-05-29', 0, 0, NULL),
(37, 52, '1355555ق', 'ژیان', 'ژیان', 'سواری', 'دیزلی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(42, 1, '4652118ج', 'پیکان', 'پیکان', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(43, 50, '4652118ج', 'پیکان', 'پیکان', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(44, 46, '1221410ل', 'پراید', 'پراید', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(45, 45, '1012822د', '206 SD', '206 SD', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(46, 53, '4778718ط', '۲۰۶', '۲۰۶', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(47, 54, '1425215ب', 'زانتیا', 'زانتیا', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(48, 55, '2525318ف', 'ژستا', 'ژستا', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(49, 56, '5252382غ', 'طیتا', 'طیتا', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(50, 57, '5258012ب', 'سیانا', 'سیانا', 'سواری', 'بنزینی', 0, '', 0, '2022-06-02', 0, 0, NULL),
(51, 58, '5589818س', 'آریو', 'آریو', 'سواری', 'بنزینی', 0, '', 0, '2022-06-06', 0, 0, NULL),
(52, 59, '5578419د', 'سمند', 'سمند', 'سواری', 'دوگانه', 0, '', 0, '2022-09-13', 0, 0, NULL),
(53, 60, '1252389ا', '405 GLX', '405 GLX', 'سواری', 'بنزینی', 0, '', 0, '2022-09-22', 0, 0, NULL),
(54, 61, '1255555ل', 'سورن', 'سورن', 'سواری', 'بنزینی', 0, '', 0, '2022-09-22', 0, 0, NULL),
(55, 62, '1255555ل', 'سورن', 'سورن', 'سواری', 'دیزلی', 0, '', 0, '2022-09-22', 0, 0, NULL),
(56, 63, '1255555ل', 'سورن', 'سورن', 'سواری', 'دیزلی', 0, '', 0, '2022-09-22', 0, 0, NULL),
(57, 64, '1255555ل', 'سورن', 'سورن', 'سواری', 'دیزلی', 0, '', 0, '2022-09-22', 0, 0, NULL),
(58, 65, '4778718ی', '۲۰۷', '۲۰۷', 'سواری', 'بنزینی', 0, '', 0, '2022-10-16', 0, 0, NULL),
(59, 66, '2522230ق', 'ذارترتتر', 'ذارترتتر', 'سواری', 'دیزلی', 0, '', 0, '2022-10-18', 0, 0, NULL),
(60, 67, '6463199م', '۲۰۶', '۲۰۶', 'سواری', 'بنزینی', 0, '', 0, '2022-10-19', 0, 0, NULL),
(61, 68, '4354415ه', 'پراید', 'پراید', 'سواری', 'بنزینی', 0, '', 0, '2022-11-02', 0, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_company`
--

CREATE TABLE `cars_company` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_company`
--

INSERT INTO `cars_company` (`id`, `name`, `status`, `deleted_at`) VALUES
(1, 'پژو', 1, NULL),
(2, 'رنو', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_model`
--

CREATE TABLE `cars_model` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_model`
--

INSERT INTO `cars_model` (`id`, `year`) VALUES
(1, 1399),
(2, 1400);

-- --------------------------------------------------------

--
-- Table structure for table `cars_name`
--

CREATE TABLE `cars_name` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_company_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_name`
--

INSERT INTO `cars_name` (`id`, `car_company_id`, `name`, `status`, `deleted_at`) VALUES
(1, 1, '206', 1, NULL),
(2, 2, 'ساندرو', 1, NULL),
(3, 1, '405 GLX', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_tip`
--

CREATE TABLE `cars_tip` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tip` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_tip`
--

INSERT INTO `cars_tip` (`id`, `tip`, `car_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'ef7', 1, '2022-07-11 10:49:00', '2022-07-11 10:49:00', NULL),
(1, 'ef7', 1, '2022-07-11 10:49:00', '2022-07-11 10:49:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `car_galleries`
--

CREATE TABLE `car_galleries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `car_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chassei_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avg_function` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_tag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fuel_type` tinyint(4) NOT NULL DEFAULT '0',
  `car_type` tinyint(4) NOT NULL DEFAULT '0',
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `car_galleries`
--

INSERT INTO `car_galleries` (`id`, `car_name_id`, `car_create`, `chassei_number`, `avg_function`, `car_tag`, `fuel_type`, `car_type`, `car_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(22, 1, '2022-07-24 08:06:38', '777', '333', '09776543', 0, 1, 24, '2022-07-24 08:06:44', '2022-07-24 08:06:44', NULL),
(22, 1, '2022-07-24 08:06:38', '777', '333', '09776543', 0, 1, 24, '2022-07-24 08:06:44', '2022-07-24 08:06:44', NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `car_info`
-- (See below for the actual view)
--
CREATE TABLE `car_info` (
`id` int(255)
,`title` text
,`job_category` text
,`description` text
,`image` varchar(10000)
,`job_id` int(11)
,`name` text
);

-- --------------------------------------------------------

--
-- Table structure for table `car_info_detail`
--

CREATE TABLE `car_info_detail` (
  `id` int(255) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `image` varchar(10000) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `car_info_detail`
--

INSERT INTO `car_info_detail` (`id`, `title`, `job_category`, `description`, `image`) VALUES
(19, 'oil filter', '1', '4000 km need for change', '2.png'),
(20, 'lent tormoz', '2', 'pars khodro', '1.png'),
(21, 'polos', '2', 'polos description', '2.png'),
(22, 'oil tormz', '1', '5000 km need for change', '2.png'),
(23, '22', '1', '<p>هچ</p>', '128_orig.jpg'),
(24, '22', '1', '<p>هچ</p>', '128_orig.jpg'),
(25, 'نیس', '2', '<p>تست</p>', '128_orig.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `car_list`
--

CREATE TABLE `car_list` (
  `user_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `car_name`
--

CREATE TABLE `car_name` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `car_tec_info`
--

CREATE TABLE `car_tec_info` (
  `id` int(255) NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `car_name` text COLLATE utf8_persian_ci NOT NULL,
  `car_model` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `car_tec_info`
--

INSERT INTO `car_tec_info` (`id`, `job_category`, `car_name`, `car_model`) VALUES
(19, '1', '206', '95'),
(20, '1', 'پیکان', '95'),
(21, '1', 'پیکان', '95'),
(22, '2', 'نیسان', '95');

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `city`, `status`, `created_at`, `updated_at`, `deleted_at`, `province_id`) VALUES
(1, 'تهران', 1, '2022-06-30 02:57:32', '2022-06-30 05:04:54', NULL, 1),
(2, 'همدان', 1, '2022-06-30 04:51:06', '2022-06-30 07:01:39', NULL, 2),
(1, 'تهران', 1, '2022-06-30 02:57:32', '2022-06-30 05:04:54', NULL, 1),
(2, 'همدان', 1, '2022-06-30 04:51:06', '2022-06-30 07:01:39', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `name` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `job` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `rank` int(10) NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `name`, `job`, `description`, `rank`, `img`) VALUES
(1, 'اکبر احمدی', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi1.png'),
(2, 'مرتضی آقاجانی', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi2.png'),
(3, 'مهرداد رنجدار', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi1.png'),
(4, 'جمال پیوده', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi2.png'),
(5, 'مجتبی منصوری', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi1.png'),
(6, 'احمد دست پیمان', 'اتوسرویس', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است', 5, 'testi2.png');

-- --------------------------------------------------------

--
-- Table structure for table `comment_post`
--

CREATE TABLE `comment_post` (
  `id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_persian_ci NOT NULL,
  `email` varchar(250) COLLATE utf8_persian_ci NOT NULL,
  `message` text COLLATE utf8_persian_ci NOT NULL,
  `post_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `comment_post`
--

INSERT INTO `comment_post` (`id`, `name`, `email`, `message`, `post_id`) VALUES
(1, 'mehdi', 'm@yahoo.com', 'hi\r\nلورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.', 7),
(2, 'mehdi', 'm@yahoo.com', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم ', 7),
(3, 'test4', 'test@test.com', 'hi', 7),
(4, 'Louis C. Charmis', 'm@yahoo.com', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.', 8),
(5, 'Louis C', 'test@test.com', 'بسیار عالی ', 50),
(6, 'احمد دست پیمان', 'm@gmail.com', 'hi', 8);

-- --------------------------------------------------------

--
-- Table structure for table `common_discount`
--

CREATE TABLE `common_discount` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `percentage` int(11) NOT NULL,
  `discount_celling` bigint(20) UNSIGNED DEFAULT NULL,
  `minimal_order_amount` bigint(20) UNSIGNED DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `copans`
--

CREATE TABLE `copans` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => percentage , 1 => price unit',
  `discount_celling` bigint(20) UNSIGNED DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => common (each user can use one time) , 1 => private (one user can use one time)',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `co_worker`
--

CREATE TABLE `co_worker` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `text_id` int(11) NOT NULL,
  `num` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `co_worker`
--

INSERT INTO `co_worker` (`id`, `d_id`, `text_id`, `num`) VALUES
(1, 10, 1, 9181112233),
(2, 10, 1, 9181112233);

-- --------------------------------------------------------

--
-- Table structure for table `customer_list`
--

CREATE TABLE `customer_list` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `c_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `customer_list`
--

INSERT INTO `customer_list` (`id`, `d_id`, `user_id`, `c_date`) VALUES
(3, 1, 25, '2022-02-01'),
(4, 1, 26, '2022-02-01'),
(5, 1, 27, '2022-02-01'),
(6, 10, 34, '2022-02-01'),
(7, 10, 36, '2022-02-01'),
(9, 10, 40, '2022-02-01'),
(16, 10, 46, '2022-02-01'),
(17, 9, 19, '2014-02-22');

-- --------------------------------------------------------

--
-- Table structure for table `downloads`
--

CREATE TABLE `downloads` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `service_center` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `downloads`
--

INSERT INTO `downloads` (`id`, `name`, `service_center`, `address`, `email`, `mobile`, `type`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'پرداخت', 'xccxvxcv', 'vbffcxcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-25 08:31:58', NULL),
(2, 'پرداخت', 'vcxvcvxcv', 'cxvcvxcvcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-20 11:28:44', NULL),
(1, 'پرداخت', 'xccxvxcv', 'vbffcxcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-25 08:31:58', NULL),
(2, 'پرداخت', 'vcxvcvxcv', 'cxvcvxcvcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-20 11:28:44', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `draft_msg`
--

CREATE TABLE `draft_msg` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_change` bit(1) NOT NULL,
  `show_in_public_sms` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emails`
--

CREATE TABLE `emails` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `subject` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `emails`
--

INSERT INTO `emails` (`id`, `subject`, `body`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'fdgfdgfdg', '<p>fghgfhfghfggfh</p>', 1, '2022-07-06 05:24:56', '2022-07-06 05:24:56', NULL),
(2, 'dvcxvc', '<p>sdfsassadsf</p>', 1, '2022-07-06 05:44:43', '2022-07-06 05:44:43', NULL),
(3, 'dvcxvc', '<p>sdfsdfsfds</p>', 0, '2022-07-06 05:55:07', '2022-07-06 05:55:07', NULL),
(4, 'daada', '<p>ASSADASD</p>', 1, '2022-07-06 05:59:08', '2022-07-06 05:59:08', NULL),
(5, 'fdgfdgfdg', '<p>DRFSSDFSF</p>', 1, '2022-07-06 06:00:12', '2022-07-06 06:35:52', NULL),
(6, 'daada', '<p>sadewqsadadsa</p>', 1, '2022-07-06 06:03:55', '2022-07-06 06:29:37', NULL),
(7, 'fdgfdgfdg', '<p>asdsadasdsad</p>', 1, '2022-07-06 06:04:14', '2022-07-06 06:04:14', NULL),
(8, 'dvcxvc', '<p>sfsdfsdfdsassadfsd</p>', 1, '2022-07-06 06:11:06', '2022-07-06 06:11:06', NULL),
(9, 'dvcxvc', '<p>sdfsdsdfsdfdsf</p>', 1, '2022-07-06 06:14:53', '2022-07-06 06:14:53', NULL),
(10, 'daada', '<p>سلام خوب هستین</p>', 1, '2022-07-06 06:17:39', '2022-07-06 06:17:39', NULL),
(11, 'dvcxvc', '<p>zcxczxzxczxzxczxc</p>', 1, '2022-07-06 06:20:46', '2022-07-06 06:20:46', NULL),
(12, 'daada', '<p>sadsasadsadassadsadsadsa</p>', 1, '2022-07-06 06:23:03', '2022-07-06 06:23:03', NULL),
(13, 'fdgfdgfdg', '<p>zcxzxzxzxccxzvfghbfgb</p>', 1, '2022-07-06 06:24:57', '2022-07-06 06:24:57', NULL),
(14, 'fdgfdgfdg', '<p>gdhdffdfd</p>', 1, '2022-07-06 06:27:15', '2022-07-06 06:27:15', NULL),
(15, 'fdgfdgfdg', '<p>dsfdfdsfsddfsdfd</p>', 1, '2022-07-06 06:28:56', '2022-07-06 06:44:06', NULL),
(16, 'fdgfdgfdg', '<p>srtwerwewer</p>', 1, '2022-07-06 06:32:07', '2022-07-06 06:32:07', NULL),
(17, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:34:27', '2022-07-06 06:34:27', NULL),
(18, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:35:42', '2022-07-06 06:35:42', NULL),
(19, 'dvcxvc', '<p>fdsfddsfdsf</p>', 1, '2022-07-06 06:37:06', '2022-07-07 09:45:11', NULL),
(20, 'dfgfd', '<p>drdrgddfggfdgfdg</p>', 1, '2022-07-06 06:47:23', '2022-07-07 15:33:02', NULL),
(21, 'fhdhsfhf', '<p>ghjghjgjghj</p>', 1, '2022-07-10 05:05:18', '2022-07-10 05:05:18', NULL),
(1, 'fdgfdgfdg', '<p>fghgfhfghfggfh</p>', 1, '2022-07-06 05:24:56', '2022-07-06 05:24:56', NULL),
(2, 'dvcxvc', '<p>sdfsassadsf</p>', 1, '2022-07-06 05:44:43', '2022-07-06 05:44:43', NULL),
(3, 'dvcxvc', '<p>sdfsdfsfds</p>', 0, '2022-07-06 05:55:07', '2022-07-06 05:55:07', NULL),
(4, 'daada', '<p>ASSADASD</p>', 1, '2022-07-06 05:59:08', '2022-07-06 05:59:08', NULL),
(5, 'fdgfdgfdg', '<p>DRFSSDFSF</p>', 1, '2022-07-06 06:00:12', '2022-07-06 06:35:52', NULL),
(6, 'daada', '<p>sadewqsadadsa</p>', 1, '2022-07-06 06:03:55', '2022-07-06 06:29:37', NULL),
(7, 'fdgfdgfdg', '<p>asdsadasdsad</p>', 1, '2022-07-06 06:04:14', '2022-07-06 06:04:14', NULL),
(8, 'dvcxvc', '<p>sfsdfsdfdsassadfsd</p>', 1, '2022-07-06 06:11:06', '2022-07-06 06:11:06', NULL),
(9, 'dvcxvc', '<p>sdfsdsdfsdfdsf</p>', 1, '2022-07-06 06:14:53', '2022-07-06 06:14:53', NULL),
(10, 'daada', '<p>سلام خوب هستین</p>', 1, '2022-07-06 06:17:39', '2022-07-06 06:17:39', NULL),
(11, 'dvcxvc', '<p>zcxczxzxczxzxczxc</p>', 1, '2022-07-06 06:20:46', '2022-07-06 06:20:46', NULL),
(12, 'daada', '<p>sadsasadsadassadsadsadsa</p>', 1, '2022-07-06 06:23:03', '2022-07-06 06:23:03', NULL),
(13, 'fdgfdgfdg', '<p>zcxzxzxzxccxzvfghbfgb</p>', 1, '2022-07-06 06:24:57', '2022-07-06 06:24:57', NULL),
(14, 'fdgfdgfdg', '<p>gdhdffdfd</p>', 1, '2022-07-06 06:27:15', '2022-07-06 06:27:15', NULL),
(15, 'fdgfdgfdg', '<p>dsfdfdsfsddfsdfd</p>', 1, '2022-07-06 06:28:56', '2022-07-06 06:44:06', NULL),
(16, 'fdgfdgfdg', '<p>srtwerwewer</p>', 1, '2022-07-06 06:32:07', '2022-07-06 06:32:07', NULL),
(17, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:34:27', '2022-07-06 06:34:27', NULL),
(18, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:35:42', '2022-07-06 06:35:42', NULL),
(19, 'dvcxvc', '<p>fdsfddsfdsf</p>', 1, '2022-07-06 06:37:06', '2022-07-07 09:45:11', NULL),
(20, 'dfgfd', '<p>drdrgddfggfdgfdg</p>', 1, '2022-07-06 06:47:23', '2022-07-07 15:33:02', NULL),
(21, 'fhdhsfhf', '<p>ghjghjgjghj</p>', 1, '2022-07-10 05:05:18', '2022-07-10 05:05:18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `email_files`
--

CREATE TABLE `email_files` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `public_mail_id` bigint(20) UNSIGNED NOT NULL,
  `file_path` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `publish_type` tinyint(4) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `failed_jobs`
--

INSERT INTO `failed_jobs` (`id`, `uuid`, `connection`, `queue`, `payload`, `exception`, `failed_at`) VALUES
(54, 'bbae80e3-6fd2-42ab-a712-4e0343a87433', 'database', 'default', '{\"uuid\":\"bbae80e3-6fd2-42ab-a712-4e0343a87433\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:39:33'),
(55, '855da78c-52bf-4ec2-8add-8480c44fbac3', 'database', 'default', '{\"uuid\":\"855da78c-52bf-4ec2-8add-8480c44fbac3\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:43:42'),
(56, '426d81a6-a2d5-4073-b761-8950ff83b695', 'database', 'default', '{\"uuid\":\"426d81a6-a2d5-4073-b761-8950ff83b695\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:45:00'),
(54, 'bbae80e3-6fd2-42ab-a712-4e0343a87433', 'database', 'default', '{\"uuid\":\"bbae80e3-6fd2-42ab-a712-4e0343a87433\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:39:33'),
(55, '855da78c-52bf-4ec2-8add-8480c44fbac3', 'database', 'default', '{\"uuid\":\"855da78c-52bf-4ec2-8add-8480c44fbac3\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:43:42'),
(56, '426d81a6-a2d5-4073-b761-8950ff83b695', 'database', 'default', '{\"uuid\":\"426d81a6-a2d5-4073-b761-8950ff83b695\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:45:00');

-- --------------------------------------------------------

--
-- Table structure for table `fileup`
--

CREATE TABLE `fileup` (
  `id` int(11) NOT NULL,
  `name` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `fileup`
--

INSERT INTO `fileup` (`id`, `name`) VALUES
(16, 'upload/ChromeSetup_2.exe'),
(19, 'upload/WhatsApp-Messenger-2.18.146-(www.Patoghu.com).apk');

-- --------------------------------------------------------

--
-- Table structure for table `footer_about`
--

CREATE TABLE `footer_about` (
  `id` int(11) NOT NULL,
  `logo` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `facebook` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `twitter` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `instagram` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `pinterest` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `footer_about`
--

INSERT INTO `footer_about` (`id`, `logo`, `title`, `description`, `facebook`, `twitter`, `instagram`, `pinterest`) VALUES
(1, 'servicea.png', 'درباره ما', 'مفتخریم با تجربه بیش از 12 سال فعالیت در حوزه طراحی و تولید نرم‌افزار، سرویسا را برای ارائه ارزش به شما و مشتریانتان پیاده سازی نموده‌ایم.', 'fid', 'tid', 'www.instagram.com/servicea', 'id');

-- --------------------------------------------------------

--
-- Table structure for table `footer_newsletter`
--

CREATE TABLE `footer_newsletter` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `footer_newsletter`
--

INSERT INTO `footer_newsletter` (`id`, `title`, `description`) VALUES
(1, 'خبرنامه', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ است');

-- --------------------------------------------------------

--
-- Table structure for table `footer_support`
--

CREATE TABLE `footer_support` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `link` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `footer_support`
--

INSERT INTO `footer_support` (`id`, `title`, `description`, `link`) VALUES
(1, 'برند', 'توضیحات', '#'),
(2, 'تبلیغات', 'توضیحات', '#'),
(3, 'سیاست حریم شخصی', 'توضیحات', '#'),
(4, 'دستگاه', 'توضیحات', '#'),
(5, 'بلاگ', 'توضیحات', 'blog.php');

-- --------------------------------------------------------

--
-- Table structure for table `group_products`
--

CREATE TABLE `group_products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `heroes`
--

CREATE TABLE `heroes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image2` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `heroes`
--

INSERT INTO `heroes` (`id`, `image`, `alt`, `image2`, `description`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>dsfdsfdsdsfdsfd</p>', '2022-07-16 05:11:43', '2022-07-16 06:59:28', NULL),
(2, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '<p>dfsfddsfd</p>', '2022-07-16 07:00:21', '2022-07-16 07:01:35', '2022-07-16 07:01:35'),
(3, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>ddgsffsdgdfsgdfsgdf</p>', '2022-07-16 11:19:20', '2022-07-16 11:19:20', NULL),
(1, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>dsfdsfdsdsfdsfd</p>', '2022-07-16 05:11:43', '2022-07-16 06:59:28', NULL),
(2, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '<p>dfsfddsfd</p>', '2022-07-16 07:00:21', '2022-07-16 07:01:35', '2022-07-16 07:01:35'),
(3, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>ddgsffsdgdfsgdfsgdf</p>', '2022-07-16 11:19:20', '2022-07-16 11:19:20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `image` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `image`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `infoes`
--

CREATE TABLE `infoes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `instagram` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `twitter` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `facebook` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pintress` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `infoes`
--

INSERT INTO `infoes` (`id`, `title`, `image`, `alt`, `description`, `instagram`, `twitter`, `facebook`, `pintress`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'hrerhrth', '{\"indexArray\":{\"large\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.large.jpg\",\"medium\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.medium.jpg\",\"small\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.small.jpg\"},\"directory\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\",\"currentImage\":\"medium\"}', '', '<p>rthertrhthrth</p>', 'htrrtrth', 'rtyrtyeyrtey', 'rterthrhrt', 'rtthtrhrthrt', 1, '2022-07-16 05:13:43', '2022-07-16 05:13:43', NULL),
(1, 'hrerhrth', '{\"indexArray\":{\"large\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.large.jpg\",\"medium\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.medium.jpg\",\"small\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.small.jpg\"},\"directory\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\",\"currentImage\":\"medium\"}', '', '<p>rthertrhthrth</p>', 'htrrtrth', 'rtyrtyeyrtey', 'rterthrhrt', 'rtthtrhrthrt', 1, '2022-07-16 05:13:43', '2022-07-16 05:13:43', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `invite`
--

CREATE TABLE `invite` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `reciver` text COLLATE utf8_persian_ci NOT NULL,
  `invite_code` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `queue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `attempts` tinyint(3) UNSIGNED NOT NULL,
  `reserved_at` int(10) UNSIGNED DEFAULT NULL,
  `available_at` int(10) UNSIGNED NOT NULL,
  `created_at` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_categories`
--

CREATE TABLE `job_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_categories`
--

INSERT INTO `job_categories` (`id`, `title`, `status`) VALUES
(1, 'برق خودرو', 1),
(2, 'تعویض روغنی', 1),
(3, 'جلوبندی', 1),
(4, 'کارواش', 1),
(5, 'صافکاری', 1),
(6, 'مکانیکی', 1),
(7, 'نقاشی', 1),
(8, 'یدک کش', 1);

-- --------------------------------------------------------

--
-- Table structure for table `job_category`
--

CREATE TABLE `job_category` (
  `job_id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `job_category`
--

INSERT INTO `job_category` (`job_id`, `name`) VALUES
(1, 'تعویض روغنی'),
(2, 'مکانیکی'),
(3, 'جلوبندی سازی'),
(4, 'باتری سازی'),
(5, 'صافکاری نقاشی');

-- --------------------------------------------------------

--
-- Table structure for table `job_services`
--

CREATE TABLE `job_services` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_services`
--

INSERT INTO `job_services` (`id`, `title`, `job_category_id`, `status`, `deleted_at`) VALUES
(1, 'تعویض روغن گیربکس اتوماتیک با دستگاه', 2, 1, NULL),
(2, 'تعویض روغن ترمز با دستگاه', 2, 1, NULL),
(3, 'تنظیم تایم سوپاپ', 6, 1, NULL),
(4, 'تنظیم باد لاستیک خودرو', 2, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `keywords`
--

CREATE TABLE `keywords` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keywords` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `keywords`
--

INSERT INTO `keywords` (`id`, `name`, `keywords`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'keyword', 'نرم افزار مدیریت اتوسرویس,حسابداری,تعویض روغنی,سرویسا,پلاکخوان,پلاک خوان,اتوسرویسا,مکانیکی,برق خودرو', '2022-07-23 08:28:51', '2022-07-31 06:46:19', NULL),
(1, 'keyword', 'نرم افزار مدیریت اتوسرویس,حسابداری,تعویض روغنی,سرویسا,پلاکخوان,پلاک خوان,اتوسرویسا,مکانیکی,برق خودرو', '2022-07-23 08:28:51', '2022-07-31 06:46:19', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `land_app`
--

CREATE TABLE `land_app` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_app`
--

INSERT INTO `land_app` (`id`, `title`, `img`) VALUES
(1, '1', 'screen1.png'),
(5, '2', 'screen2.png'),
(6, '3', 'screen5.png'),
(7, '4', 'screen1.png');

-- --------------------------------------------------------

--
-- Table structure for table `land_comment`
--

CREATE TABLE `land_comment` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_comment`
--

INSERT INTO `land_comment` (`id`, `title`, `description`) VALUES
(1, 'نظر مشتریان', 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است');

-- --------------------------------------------------------

--
-- Table structure for table `land_counter`
--

CREATE TABLE `land_counter` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_counter`
--

INSERT INTO `land_counter` (`id`, `title`, `description`) VALUES
(1, 'تعداد مراکز سرویس و صاحبان خودرویی که از سرویسا استفاده می‌کنند.', 'شما هم می‌توانید با عضویت در خانواده بزرگ سرویسا، دیگر نگران سلامت خودروی خود نباشید.');

-- --------------------------------------------------------

--
-- Table structure for table `land_dl`
--

CREATE TABLE `land_dl` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `link1` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `link2` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_dl`
--

INSERT INTO `land_dl` (`id`, `title`, `description`, `link1`, `link2`, `img`) VALUES
(1, 'دانلود آخرین نسخه نرم‌افزار', 'برای استفاده از نرم‌افزار مدیریت اتوسرویسا و استفاده از امکانات جذاب آن، روی دکمه دانلود کلیک نمایید و اطلاعات خود را وارد کنید که تیم پشتیبانی ما در تمام مراحل در کنار شما باشند', 'admin/upload/WhatsApp-Messenger-2.18.146-(www.Patoghu.com).apk', 'admin/upload/ChromeSetup_2.exe', 'img3.png');

-- --------------------------------------------------------

--
-- Table structure for table `land_header`
--

CREATE TABLE `land_header` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `img` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_header`
--

INSERT INTO `land_header` (`id`, `title`, `description`, `img`) VALUES
(3, 'نرم‌افزار مدیریت اتوسرویسـا', '<p>نرم‌افزار مدیریت اتوسرویسا امکاناتی در اختیار صاحبان مشاغل خدمات خودرو، مثل تعویض روغنی‌ها، مکانیکی‌ها، برق خودرو و ... قرار می‌دهد که هر نیازی که برای ثبت اطلاعات مشتری، ثبت سرویس خودرو، یادآوری سرویس و افزایش درآمد خود دارند با قدردت برطرف بشه</p>', 'hero1.png'),
(5, 'سرویسا برای صاحبان خودرو', '<p>صاحبان خودرو می‌توانند با نصب اپلیکیشن سرویسا، تمامی سرویس‌های انجام شده روی خودروهای خود را مشاهده کنند و همچنین این اپ در زمان مناسب موعد سرویس‌های خودرو را یادآوری می‌کند </p>', 'hero2img.png'),
(6, 'سرویسا، گنجینه‌ای از بهترین‌ها', '<p>در این اپلیکیشن امکانات متفاوتی برای صاحبان مشاغل و همچنین صاحبان خودرو پیاده سازی شده که شامل لیست مشاغل به همراه نمایش امتیاز آن‌ها، درخواست امداد خودرو، نمایش سرویس‌های انجام شده، یادآوری سرویس و ...</p>', 'h2screen1.png');

-- --------------------------------------------------------

--
-- Table structure for table `land_option`
--

CREATE TABLE `land_option` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `more` text COLLATE utf8_persian_ci NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_option`
--

INSERT INTO `land_option` (`id`, `title`, `description`, `more`, `img`) VALUES
(1, 'امکانات کاربردی', 'ما با تکیه بر تجربه چند ساله خود که از نیازهای شما عزیزان بدست آمده امکاناتی را در سرویسا قرار دادیم که نه تنها نیازهای روزانه شما را برطرف می‌کند، بلکه درآمد شما را به شدت افزایش می‌دهد.', 'بیشتر بدانید2', 'icon1.png'),
(2, 'استفاده آسان', '<p>ما برای اینکه بهترین نرم‌افزار را در اختیار شما قرار بدهیم به مدت 3 سال در بیش از 20 مرکز خدماتی تجربه کسب کردیم و الان مدعی هستیم که بهترین نرم‌افزار کشور را در اختیار شما عزیزان می‌گذاریم</p>', 'بیشتر بدانید', 'icon2.png'),
(4, 'پلاک‌خوان', '<p>ما برای نرم‌افزار دسکتاپ سرویسا، قدرتمندترین سیستم پلاک‌خوان کشور را قرار دادیم که بتوانید با کمترین خطا و بیشترین سرعت، پلاک خودرو مشتریان را برای ثبت خودرو در این نرم‌افزار ذخیره کنید.</p>', 'توضیحات بیشتر', 'icon3.png');

-- --------------------------------------------------------

--
-- Table structure for table `land_price`
--

CREATE TABLE `land_price` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_price`
--

INSERT INTO `land_price` (`id`, `title`, `description`) VALUES
(1, 'تعرفه اپلیکیشن و نرم‌افزاردسکتاپ سرویسا', 'اپلیکیشن سرویسا و همچنین نسخه پایه نرم‌افزار دسکتاپ آن به صورت رایگان در اختیار شما عزیزان قرار گرفت.');

-- --------------------------------------------------------

--
-- Table structure for table `land_price_tbl`
--

CREATE TABLE `land_price_tbl` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `price` bigint(200) NOT NULL,
  `unit` varchar(250) COLLATE utf8_persian_ci NOT NULL,
  `type_x` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property1` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property2` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property3` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property4` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property5` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `Property6` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_price_tbl`
--

INSERT INTO `land_price_tbl` (`id`, `title`, `price`, `unit`, `type_x`, `Property1`, `Property2`, `Property3`, `Property4`, `Property5`, `Property6`) VALUES
(1, 'نسخه پایه', 0, 'رایگان', 'دائمی', 'پلاک‌خوان', 'ثبت کارت سرویس', 'ارسال پیامک سرویس', 'ارسال پیامک یادآوری', 'افزودن گروه کالا و خدمات', 'امکان چاپ کارت سرویس'),
(5, 'نسخه پیشرفته', 1500000, 'تومان', 'دائمی', 'امکانات نسخه پایه', 'ثبت فاکتور فروش و خرید', 'انبارگردانی و کاردکس کالا', 'امکان فروش اعتباری', 'اتصال به دستگاه کارت‌خوان', 'اتصال به بارکدخوان'),
(6, 'نسخه حرفه‌ای', 2500000, 'تومان', 'دائمی', 'امکانات نسخه پیشرفته', 'باشگاه مشتریان', 'تعریف جشنواره فروش', 'تعریف آفر برای مشتریان', 'ارائه هدیه و تخفیف', 'ارائه گزارشات حرفه‌ای'),
(7, 'برنامه شروع', 1200, 'تومان', 'سالانه', 'ارتباط با دو سرور', 'ویرایش بی نهایت', 'ویرایش زمان واقعی', 'مانیتور زمان واقعی', 'آلترناتیو بی نهایت', '30 روز امتحان رایگان'),
(8, 'استاندارد', 1500, 'تومان', 'سالانه', 'ارتباط با دو سرور', 'ویرایش بی نهایت', 'ویرایش زمان واقعی', 'مانیتور زمان واقعی', 'آلترناتیو بی نهایت', '30 روز امتحان رایگان'),
(9, 'ویژه', 1900, 'تومان', 'سالانه', 'ارتباط با دو سرور', 'ویرایش بی نهایت', 'ویرایش زمان واقعی', 'مانیتور زمان واقعی', 'آلترناتیو بی نهایت', '30 روز امتحان رایگان');

-- --------------------------------------------------------

--
-- Table structure for table `land_services`
--

CREATE TABLE `land_services` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `more` text COLLATE utf8_persian_ci NOT NULL,
  `serv1` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `serv2` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `serv3` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `serv4` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_services`
--

INSERT INTO `land_services` (`id`, `title`, `description`, `more`, `serv1`, `serv2`, `serv3`, `serv4`, `img`) VALUES
(2, 'امکانات و ویژگی‌های سرویسا', 'با استفاده از اپلیکیشن سرویسا، در هر جای کشور عزیزمان که باشید می‌توانید بهترین خدمات را انتخاب و استفاده کنید. در ضمن همیشه یادتان می‌ماند که در چه تاریخی چه سرویسی انجام داده‌اید و این باعث سلامت خودروی شما می‌شود.', 'بیشتر بدانید2', 'نمایش سرویس‌های انجام شده', 'نمایش مراکز خدماتی با جزئیات', 'یادآوری سرویس‌های بعدی', 'درخواست خدمات در محل', 'img2.png');

-- --------------------------------------------------------

--
-- Table structure for table `land_team`
--

CREATE TABLE `land_team` (
  `id` int(11) NOT NULL,
  `name` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `skill` varchar(500) COLLATE utf8_persian_ci NOT NULL,
  `facebook` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `twitter` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `instagram` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `img` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `land_team`
--

INSERT INTO `land_team` (`id`, `name`, `skill`, `facebook`, `twitter`, `instagram`, `img`) VALUES
(3, 'عرفان سقاباشی', 'برنامه نویس اندروید', '#id', '#id', '#id', 'team3.png'),
(4, 'مهدی ساعتی', 'برنامه نویس و طراح سایت', '#id', '#id', '#id', 'team4.png'),
(7, 'محمود همدانی', 'تحلیل‌گر نرم‌افزار', 'Mahmoud.Hamedani', 'Mahmoud.Hamedani', 'Mahmoud.Hamedani', 'team1.png'),
(8, 'مرتضی بهرامی', 'برنامه نویس #C', 'facebook', 'twitter', 'instagram', 'team2.png');

-- --------------------------------------------------------

--
-- Table structure for table `list_service`
--

CREATE TABLE `list_service` (
  `id` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `master_auto`
--

CREATE TABLE `master_auto` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `master_id` bigint(20) UNSIGNED NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `master_auto_list`
--

CREATE TABLE `master_auto_list` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `master_of_car_id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(15) COLLATE utf8_persian_ci NOT NULL,
  `price` int(11) NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `picture` text COLLATE utf8_persian_ci NOT NULL,
  `serial_code` varchar(6) COLLATE utf8_persian_ci NOT NULL,
  `is_counting` int(1) DEFAULT NULL,
  `dn_link` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `name`, `price`, `description`, `picture`, `serial_code`, `is_counting`, `dn_link`) VALUES
(1, '1', 1, '<p>11</p>', 'public/img/module/Sketchpad.png', '1', 0, 'https://baadbaanapp.ir/public/img/module/Sketchpad.png');

-- --------------------------------------------------------

--
-- Table structure for table `module_factor`
--

CREATE TABLE `module_factor` (
  `id` int(11) NOT NULL,
  `factor_num` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `status` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `total_price` float NOT NULL,
  `discount` float NOT NULL,
  `remaining` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `msg_log`
--

CREATE TABLE `msg_log` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` datetime NOT NULL,
  `send_at` datetime NOT NULL,
  `char_count` int(11) NOT NULL,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_log`
--

INSERT INTO `msg_log` (`id`, `service_center_id`, `user_id`, `content`, `create_at`, `send_at`, `char_count`, `total_price`) VALUES
(2, 10, 64, 'تست', '2022-10-15 13:34:20', '2022-10-15 13:34:20', 6, 60),
(3, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(4, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(5, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(6, 42, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(7, 42, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(8, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(9, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(10, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(11, 42, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(12, 10, 64, 'تست', '2022-10-15 13:36:48', '2022-10-15 13:36:48', 6, 60),
(13, 42, 64, 'تست', '2022-10-15 13:37:14', '2022-10-15 13:37:14', 6, 60),
(14, 10, 64, 'تست', '2022-10-15 13:37:22', '2022-10-15 13:37:23', 6, 60);

-- --------------------------------------------------------

--
-- Stand-in structure for view `msg_log_details`
-- (See below for the actual view)
--
CREATE TABLE `msg_log_details` (
`id` bigint(20) unsigned
,`service_center_id` bigint(20) unsigned
,`user_id` bigint(20) unsigned
,`content` text
,`create_at` datetime
,`send_at` datetime
,`char_count` int(11)
,`total_price` int(11)
,`user_fullname` mediumtext
,`user_phone` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `msg_prov`
--

CREATE TABLE `msg_prov` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `msg_title_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `char_count` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_prov`
--

INSERT INTO `msg_prov` (`id`, `msg_title_id`, `name`, `text`, `char_count`, `status`, `deleted_at`) VALUES
(1, 1, 'خوش آمد گویی 1', 'سلام از این که به ما پیوستید بسیار خوش خالیم', 44, 1, NULL),
(2, 1, 'خوش آمد گویی 2', 'سلام از این که به ما پیوستید بسیار خوش حالیم', 44, 1, NULL),
(3, 2, 'پیامک جزئیات سرویس 1', 'متن پیام جزئیات سرویس', 21, 1, NULL),
(4, 3, 'متن تبریک تولد', 'متن تبریک تولد', 14, 1, NULL),
(5, 4, 'متن یادآوری سرویس', 'متن یادآوری سرویس', 14, 1, NULL),
(6, 4, '2متن یادآوری سرویس', 'متن یادآوری سرویس', 14, 1, NULL),
(7, 3, 'متن تبریک تولد2', 'متن تبریک تولد', 14, 1, NULL),
(8, 2, 'پیامک جزئیات سرویس 2', 'متن پیام جزئیات سرویس', 21, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msg_title`
--

CREATE TABLE `msg_title` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_title`
--

INSERT INTO `msg_title` (`id`, `name`, `status`) VALUES
(1, 'خوش‌آمدگویی', 1),
(2, 'جزئیات سرویس', 1),
(3, 'تبریک تولد', 1),
(4, 'یادآوری سرویس', 1);

-- --------------------------------------------------------

--
-- Table structure for table `newsletter`
--

CREATE TABLE `newsletter` (
  `id` int(11) NOT NULL,
  `email` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `newsletter`
--

INSERT INTO `newsletter` (`id`, `email`) VALUES
(26, 'test@test.com'),
(27, 'mj.hamedani@yahoo.com'),
(28, 'test@test.com');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `category` int(11) NOT NULL,
  `text` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `title`, `category`, `text`) VALUES
(1, 'TEST', 1, '<p>TEST</p>'),
(2, 't', 2, '<p>tt</p>');

-- --------------------------------------------------------

--
-- Table structure for table `operators`
--

CREATE TABLE `operators` (
  `d_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `b_date` date NOT NULL,
  `shop_name` text COLLATE utf8_persian_ci NOT NULL,
  `shop_phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL,
  `city` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `opentime` int(255) DEFAULT '8',
  `closetime` int(255) DEFAULT '21',
  `opentime2` int(11) NOT NULL DEFAULT '16',
  `closetime2` int(11) NOT NULL DEFAULT '20',
  `num_branch` int(255) DEFAULT '1',
  `waiting_space` int(255) NOT NULL DEFAULT '0',
  `catering_service` int(255) NOT NULL DEFAULT '0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `serial` text COLLATE utf8_persian_ci,
  `username` text COLLATE utf8_persian_ci,
  `password` text COLLATE utf8_persian_ci,
  `email` text COLLATE utf8_persian_ci,
  `charge_total` int(11) NOT NULL DEFAULT '0',
  `charge_remain` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `operators`
--

INSERT INTO `operators` (`d_id`, `status`, `name`, `lastname`, `phone`, `sex`, `b_date`, `shop_name`, `shop_phone`, `address`, `province`, `city`, `job_category`, `opentime`, `closetime`, `opentime2`, `closetime2`, `num_branch`, `waiting_space`, `catering_service`, `is_delete`, `serial`, `username`, `password`, `email`, `charge_total`, `charge_remain`) VALUES
(1, 0, 'ehsan', 'rst', '9185081379', 0, '2010-01-11', 'co', '0215544555', 'hmd', '1', '1', '1', 8, 16, 16, 20, 1, 0, 1, b'0', '45ssw', '', '', '', 0, 0),
(8, 0, 'morteza', 'bahrami', '09181112233', 0, '2000-01-11', 'bahramishop', '08134225566', 'hamedannn', '1', '1', '1', 8, 21, 16, 20, 1, 1, 1, b'0', '45', '', '', '', 0, 0),
(9, 1, 'ali', 'abedi', '0912223366', 1, '2022-01-27', 'shop', '08132223366', 'اذربایجان', '1', '3', '1', 2, 22, 16, 20, 1, 1, 1, b'0', NULL, NULL, NULL, '', 0, 0),
(10, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا تیم', '09398116292', 'همدان', '28', '7', '2', 8, 13, 16, 20, 2, 1, 0, b'0', '', '', '', 'example@gmail.com', 500000, 499728),
(38, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '09398116292', 'همدان', '28', '7', '0', 0, 0, 16, 20, 0, 0, 0, b'0', '', '', '', 'example@gmail.com', 0, 0),
(39, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '', 'همدان', '28', '7', '0', 0, 0, 16, 20, 0, 0, 0, b'0', '', '', '', 'example@gmail.com', 0, 0),
(40, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '', 'همدان', '28', '7', '0', 0, 0, 16, 20, 0, 0, 0, b'0', '', '', '', 'example@gmail.com', 0, 0),
(41, 1, 'احمد ', 'توکلی', '09017736292', 0, '2020-10-20', 'سیارو', '', 'همدان', '28', '7', '0', 12, 17, 16, 20, 2, 1, 1, b'0', '', '', '', 'example@gmail.com', 0, 0),
(42, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '09184455585', 'همدان قلیانی', '28', '7', '2', 8, 13, 16, 20, 1, 1, 0, b'0', '', '', '', 'example@gmail.com', 500000, 499943),
(43, 1, 'علی', 'رصایی', '09019938199', 0, '2020-10-20', 'ارتین', '09019938199', 'تست', '6', '10', '1', 8, 13, 16, 20, 2, 1, 0, b'0', '', '', '', 'example@gmail.com', 0, 0),
(44, 1, 'آرش ', 'پورقاسمیان ثانی', '09372960208', 0, '2020-10-20', 'دمو', '09372960208', 'تبریز', '0', '5', '2', 0, 0, 0, 0, 0, 0, 0, b'0', '', '', '', 'example@gmail.com', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `operators2`
--

CREATE TABLE `operators2` (
  `d_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `b_date` date NOT NULL,
  `shop_name` text COLLATE utf8_persian_ci NOT NULL,
  `shop_phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL,
  `city` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `opentime` int(255) DEFAULT '8',
  `closetime` int(255) DEFAULT '21',
  `num_branch` int(255) DEFAULT '1',
  `waiting_space` int(255) NOT NULL DEFAULT '0',
  `catering_service` int(255) NOT NULL DEFAULT '0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `serial` text COLLATE utf8_persian_ci,
  `username` text COLLATE utf8_persian_ci,
  `password` text COLLATE utf8_persian_ci,
  `email` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `operators2`
--

INSERT INTO `operators2` (`d_id`, `status`, `name`, `lastname`, `phone`, `sex`, `b_date`, `shop_name`, `shop_phone`, `address`, `province`, `city`, `job_category`, `opentime`, `closetime`, `num_branch`, `waiting_space`, `catering_service`, `is_delete`, `serial`, `username`, `password`, `email`) VALUES
(1, 0, 'ehsan', 'rst', '9185081379', 0, '0000-00-00', 'co', '426', 'hmd', '31', '2', '1', 8, 16, 1, 0, 1, b'0', '45ssw', '', '', ''),
(8, 0, 'morteza', 'bahrami', '0918', 0, '0000-00-00', 'bahramishop', '081', 'hamedannn', '1', '1', '1', 8, 21, 1, 1, 1, b'0', '45', '', '', ''),
(9, 1, 'ali', 'abedi', '0254', 1, '2022-01-27', 'shop', '0813', 'h', '31', '38', '1', 2, 22, 1, 1, 1, b'0', NULL, NULL, NULL, ''),
(10, 1, 'عرفان', 'سقاباشی', '09198116292', 0, '2017-12-20', 'آرنا', '09398116292', 'همدان', 'همدان', '7', '0', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(14, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '09398116292', 'همدان', 'همدان', '7', '3', 8, 20, 1, 0, 0, b'1', '', '', '', 'example@gmail.com'),
(15, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '09398116292', 'همدان', 'همدان', '7', '4', 8, 20, 1, 0, 0, b'1', '', '', '', 'example@gmail.com'),
(16, 1, 'علی', 'احمدی', '09398116293', 1, '2020-10-20', 'اتو علی', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(17, 1, 'محمد', 'احمدی', '09398116294', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(18, 1, 'محمد', 'احمدی', '09398116295', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(19, 1, 'حسین', 'احمدی', '09398116296', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(20, 1, 'حسن', 'احمدی', '09398116297', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(21, 1, 'محمد', 'احمدی', '09398116298', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(22, 1, 'محمد', 'احمدی', '09298116298', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(0, 1, 'mehdi', 'sz', '09181112253', 1, '1368-09-19', 'xx', '0212223366', 'همدان - شهرک مدنی ', '30', '1256', '1', 9, 21, 1, 1, 1, b'0', 'a9c9edf95361b4fb733561b10967765a', 'm2@yahoo.com', '123', 'm2@yahoo.com'),
(0, 1, 'Louis C. Charmis', 'C. Charmis', '09181112233', 1, '1368-09-19', 'autoo', '0212223366', 'همدان - شهرک مدنی ', '19', '838', '2', 20, 20, 1, 1, 1, b'0', 'df28514cf5c14e4ce22cc52bde40661f', 'mmm@yahoo.com', '1', 'mmm@yahoo.com'),
(0, 1, 'test4', 'C. Charmis', '09181112255', 1, '1368-09-19', 'xx', '0212223366', 'همدان - شهرک مدنی ', '14', '633', '1', 20, 20, 1, 1, 1, b'0', 'ffd37cf41b463a3adee648bd46b81a65', 'm2@yahoo.com', '123', 'm2@yahoo.com'),
(0, 1, 'احمد دست پیمان', 'sz', '09181112232', 1, '1368-09-19', 'xx', '0212223366', 'همدان - شهرک مدنی ', '14', '633', '1', 20, 20, 1, 1, 1, b'0', '39481e53281de99af4620d115d46a59e', 'mmm@yahoo.com', '1', 'mmm@yahoo.com'),
(0, 1, 'test4', 'sz', '09181112252', 1, '1368-09-19', 'autoo', '0212223366', 'همدان - شهرک مدنی ', '11', '466', '1', 23, 21, 1, 1, 1, b'0', '0a5dc3e8d3faddcf430307855dc14d0f', 'm@yahoo.com', '123', 'm@yahoo.com'),
(0, 1, 'علی', 'احمدی', '09182223366', 1, '1368-09-19', 'اتوعلی', '0212223366', 'همدان - شهرک مدنی ', '30', '1256', '1', 8, 21, 1, 1, 1, b'0', '5b088eedcdc40cdaaf6ffd03f58ed9ca', 'mmm@yahoo.com', '124', 'mmm@yahoo.com'),
(0, 1, 'test5', 'sz', '09181112231', 1, '1368-09-19', 'autoo', '0212223366', 'همدان - شهرک مدنی ', '31', '1257', '1', 21, 21, 1, 1, 1, b'0', '01a8d7d44e160a2783dd76a650dc3f82', 'mmm@yahoo.com', '1', 'mmm@yahoo.com'),
(0, 1, 'test6', 'C. Charmis', '09181112251', 1, '1368-09-19', 'autoo', '0212223366', 'همدان - شهرک مدنی ', '14', '633', '1', 15, 21, 1, 1, 1, b'0', '44d25490328ba418790cc9dc61479701', 'm@gmail.com', '123', 'm@gmail.com'),
(0, 1, 'test8', 'احمدی', '09181112257', 1, '1368-09-19', 'autoo', '0212223366', 'همدان - شهرک مدنی ', '', '', '1', 22, 22, 1, 1, 1, b'0', '873aa756e70ebfe3a33b35f3438cf508', 'mmm@yahoo.com', '2555', 'mmm@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `operator_module`
--

CREATE TABLE `operator_module` (
  `id` int(11) NOT NULL,
  `factor_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `tracking_code` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `operator_option`
--

CREATE TABLE `operator_option` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `o_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `operator_option`
--

INSERT INTO `operator_option` (`id`, `d_id`, `o_id`) VALUES
(1, 9, 1),
(2, 9, 2),
(3, 9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `pre_operators`
--

CREATE TABLE `pre_operators` (
  `pre_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `shop_phone` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL,
  `city` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `serial` text COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `pre_operators`
--

INSERT INTO `pre_operators` (`pre_id`, `status`, `name`, `lastname`, `phone`, `shop_phone`, `address`, `province`, `city`, `job_category`, `serial`, `description`) VALUES
(1, 1, 'ali', 'ahmadi', '09182223300', '0214445566', 'اذربایجان', '1', '32', '1', '54d40c66b2efbf1e2e3c8595c80c7cbd', 'x'),
(2, 1, 'نوید', 'خدمتی', '09173217757', '07142217020', 'چهارراه انقلاب روبروی حسینیه آهنگران', '17', '779', '1', '8d949d2136a5e690da698823be23ae90', 'با اینستاگرام');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `job_cat` text COLLATE utf8_persian_ci NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `km_usage` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `job_cat`, `name`, `km_usage`) VALUES
(6, '1', '22', '322222222'),
(0, '2', 'تست', '50000');

-- --------------------------------------------------------

--
-- Table structure for table `products_name`
--

CREATE TABLE `products_name` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_group_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products_name`
--

INSERT INTO `products_name` (`id`, `product_group_id`, `name`, `image`, `status`, `deleted_at`) VALUES
(1, 1, 'ایرانول', NULL, 1, NULL),
(2, 1, 'کاسترول', NULL, 1, NULL),
(3, 1, 'پارس', NULL, 1, NULL),
(4, 1, 'بهران', NULL, 1, NULL),
(5, 1, 'اسپیدی', NULL, 1, NULL),
(6, 1, 'الف', NULL, 1, NULL),
(7, 3, 'ترن', NULL, 1, NULL),
(8, 2, 'ترن', NULL, 1, NULL),
(9, 3, 'کاسپین', NULL, 1, NULL),
(10, 2, 'کاسپین', NULL, 1, NULL),
(11, 3, 'لوبریفنت', NULL, 1, NULL),
(12, 2, 'لوبریفنت', NULL, 1, NULL),
(13, 1, 'سوپر پیشتاز', NULL, 1, NULL),
(14, 1, 'کاسپین', NULL, 1, NULL),
(15, 4, 'ترن', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_groups`
--

CREATE TABLE `product_groups` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_usage` int(11) DEFAULT NULL,
  `send_msg` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_groups`
--

INSERT INTO `product_groups` (`id`, `job_category_id`, `title`, `km_usage`, `send_msg`, `status`, `deleted_at`) VALUES
(1, 2, 'روغن موتور', 5000, 1, 1, NULL),
(2, 2, 'روغن دنده اتوماتیک', 50000, 1, 1, NULL),
(3, 2, 'روغن دنده دستی', 30000, 1, 1, NULL),
(4, 2, 'روغن هیدرولیک', 60000, 1, 1, NULL),
(5, 1, 'سیم کشی خودرو', 60000, 1, 1, NULL),
(6, 1, 'کولر خودرو', 60000, 1, 1, NULL),
(7, 5, 'تعویض گلگیر', 60000, 1, 1, NULL),
(8, 4, 'شتشو کامل', 60000, 1, 1, NULL),
(9, 6, 'تعویض تسمه', 60000, 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` int(11) NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `province`) VALUES
(1, 'آذربایجان شرقی'),
(2, 'آذربایجان غربی'),
(3, 'اردبیل'),
(4, 'اصفهان'),
(5, 'البرز'),
(6, 'ایلام'),
(7, 'بوشهر'),
(8, 'تهران'),
(9, 'چهارمحال و بختیاری'),
(10, 'خراسان جنوبی'),
(11, 'خراسان رضوی'),
(12, 'خراسان شمالی'),
(13, 'خوزستان'),
(14, 'زنجان'),
(15, 'سمنان'),
(16, 'سیتان و بلوچستان'),
(17, 'فارس'),
(18, 'قزوین'),
(19, 'قم'),
(20, 'کردستان'),
(21, 'کرمان'),
(22, 'کرمانشاه'),
(23, 'کهگیلویه و بویراحمد'),
(24, 'گلستان'),
(25, 'گیلان'),
(26, 'لرستان'),
(27, 'مازندران'),
(28, 'مرکزی'),
(29, 'هرمزگان'),
(30, 'همدان'),
(31, 'یزد');

-- --------------------------------------------------------

--
-- Table structure for table `province_cities`
--

CREATE TABLE `province_cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `parent` int(11) NOT NULL DEFAULT '0',
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `province_cities`
--

INSERT INTO `province_cities` (`id`, `parent`, `title`) VALUES
(1, 0, 'آذربایجان شرقی'),
(2, 0, 'آذربایجان غربی'),
(3, 0, 'اردبیل'),
(4, 0, 'اصفهان'),
(5, 0, 'البرز'),
(6, 0, 'ایلام'),
(7, 0, 'بوشهر'),
(8, 0, 'تهران'),
(9, 0, 'چهارمحال بختیاری'),
(10, 0, 'خراسان جنوبی'),
(11, 0, 'خراسان رضوی'),
(12, 0, 'خراسان شمالی'),
(13, 0, 'خوزستان'),
(14, 0, 'زنجان'),
(15, 0, 'سمنان'),
(16, 0, 'سیستان و بلوچستان'),
(17, 0, 'فارس'),
(18, 0, 'قزوین'),
(19, 0, 'قم'),
(20, 0, 'کردستان'),
(21, 0, 'کرمان'),
(22, 0, 'کرمانشاه'),
(23, 0, 'کهکیلویه و بویراحمد'),
(24, 0, 'گلستان'),
(25, 0, 'گیلان'),
(26, 0, 'لرستان'),
(27, 0, 'مازندران'),
(28, 0, 'مرکزی'),
(29, 0, 'هرمزگان'),
(30, 0, 'همدان'),
(31, 0, 'یزد'),
(32, 1, 'آذرشهر'),
(33, 1, 'تیمورلو'),
(34, 1, 'گوگان'),
(35, 1, 'ممقان'),
(36, 1, 'اسکو'),
(37, 1, 'ایلخچی'),
(38, 1, 'سهند'),
(39, 1, 'اهر'),
(40, 1, 'هوراند'),
(41, 1, 'بستان آباد'),
(42, 1, 'تیکمه داش'),
(43, 1, 'بناب'),
(44, 1, 'باسمنج'),
(45, 1, 'تبریز'),
(46, 1, 'خسروشاه'),
(47, 1, 'سردرود'),
(48, 1, 'جلفا'),
(49, 1, 'سیه رود'),
(50, 1, 'هادیشهر'),
(51, 1, 'قره آغاج'),
(52, 1, 'خمارلو'),
(53, 1, 'دوزدوزان'),
(54, 1, 'سراب'),
(55, 1, 'شربیان'),
(56, 1, 'مهربان'),
(57, 1, 'تسوج'),
(58, 1, 'خامنه'),
(59, 1, 'سیس'),
(60, 1, 'شبستر'),
(61, 1, 'شرفخانه'),
(62, 1, 'شندآباد'),
(63, 1, 'صوفیان'),
(64, 1, 'کوزه کنان'),
(65, 1, 'وایقان'),
(66, 1, 'جوان قلعه'),
(67, 1, 'عجب شیر'),
(68, 1, 'آبش احمد'),
(69, 1, 'کلیبر'),
(70, 1, 'خداجو(خراجو)'),
(71, 1, 'مراغه'),
(72, 1, 'بناب مرند'),
(73, 1, 'زنوز'),
(74, 1, 'کشکسرای'),
(75, 1, 'مرند'),
(76, 1, 'یامچی'),
(77, 1, 'لیلان'),
(78, 1, 'مبارک شهر'),
(79, 1, 'ملکان'),
(80, 1, 'آقکند'),
(81, 1, 'اچاچی'),
(82, 1, 'ترک'),
(83, 1, 'ترکمانچای'),
(84, 1, 'میانه'),
(85, 1, 'خاروانا'),
(86, 1, 'ورزقان'),
(87, 1, 'بخشایش'),
(88, 1, 'خواجه'),
(89, 1, 'زرنق'),
(90, 1, 'کلوانق'),
(91, 1, 'هریس'),
(92, 1, 'نظرکهریزی'),
(93, 1, 'هشترود'),
(94, 2, 'ارومیه'),
(95, 2, 'سرو'),
(96, 2, 'سیلوانه'),
(97, 2, 'قوشچی'),
(98, 2, 'نوشین'),
(99, 2, 'اشنویه'),
(100, 2, 'نالوس'),
(101, 2, 'بوکان'),
(102, 2, 'سیمینه'),
(103, 2, 'پلدشت'),
(104, 2, 'نازک علیا'),
(105, 2, 'پیرانشهر'),
(106, 2, 'گردکشانه'),
(107, 2, 'تکاب'),
(108, 2, 'آواجیق'),
(109, 2, 'سیه چشمه'),
(110, 2, 'قره ضیاءالدین'),
(111, 2, 'ایواوغلی'),
(112, 2, 'خوی'),
(113, 2, 'دیزج دیز'),
(114, 2, 'زرآباد'),
(115, 2, 'فیرورق'),
(116, 2, 'قطور'),
(117, 2, 'ربط'),
(118, 2, 'سردشت'),
(119, 2, 'میرآباد'),
(120, 2, 'تازه شهر'),
(121, 2, 'سلماس'),
(122, 2, 'شاهین دژ'),
(123, 2, 'کشاورز'),
(124, 2, 'محمودآباد'),
(125, 2, 'شوط'),
(126, 2, 'مرگنلر'),
(127, 2, 'بازرگان'),
(128, 2, 'ماکو'),
(129, 2, 'خلیفان'),
(130, 2, 'مهاباد'),
(131, 2, 'باروق'),
(132, 2, 'چهاربرج'),
(133, 2, 'میاندوآب'),
(134, 2, 'محمدیار'),
(135, 2, 'نقده'),
(136, 3, 'اردبیل'),
(137, 3, 'هیر'),
(138, 3, 'بیله سوار'),
(139, 3, 'جعفرآباد'),
(140, 3, 'اسلام اباد'),
(141, 3, 'اصلاندوز'),
(142, 3, 'پارس آباد'),
(143, 3, 'تازه کند'),
(144, 3, 'خلخال'),
(145, 3, 'کلور'),
(146, 3, 'هشتجین'),
(147, 3, 'سرعین'),
(148, 3, 'گیوی'),
(149, 3, 'تازه کندانگوت'),
(150, 3, 'گرمی'),
(151, 3, 'رضی'),
(152, 3, 'فخراباد'),
(153, 3, 'قصابه'),
(154, 3, 'لاهرود'),
(155, 3, 'مرادلو'),
(156, 3, 'مشگین شهر'),
(157, 3, 'آبی بیگلو'),
(158, 3, 'عنبران'),
(159, 3, 'نمین'),
(160, 3, 'کوراییم'),
(161, 3, 'نیر'),
(162, 4, 'آران وبیدگل'),
(163, 4, 'ابوزیدآباد'),
(164, 4, 'سفیدشهر'),
(165, 4, 'نوش آباد'),
(166, 4, 'اردستان'),
(167, 4, 'زواره'),
(168, 4, 'مهاباد'),
(169, 4, 'اژیه'),
(170, 4, 'اصفهان'),
(171, 4, 'بهارستان'),
(172, 4, 'تودشک'),
(173, 4, 'حسن اباد'),
(174, 4, 'زیار'),
(175, 4, 'سجزی'),
(176, 4, 'قهجاورستان'),
(177, 4, 'کوهپایه'),
(178, 4, 'محمدآباد'),
(179, 4, 'نصرآباد'),
(180, 4, 'نیک آباد'),
(181, 4, 'ورزنه'),
(182, 4, 'هرند'),
(183, 4, 'حبیب آباد'),
(184, 4, 'خورزوق'),
(185, 4, 'دستگرد'),
(186, 4, 'دولت آباد'),
(187, 4, 'سین'),
(188, 4, 'شاپورآباد'),
(189, 4, 'کمشچه'),
(190, 4, 'افوس'),
(191, 4, 'بویین ومیاندشت'),
(192, 4, 'تیران'),
(193, 4, 'رضوانشهر'),
(194, 4, 'عسگران'),
(195, 4, 'چادگان'),
(196, 4, 'رزوه'),
(197, 4, 'اصغرآباد'),
(198, 4, 'خمینی شهر'),
(199, 4, 'درچه'),
(200, 4, 'کوشک'),
(201, 4, 'خوانسار'),
(202, 4, 'جندق'),
(203, 4, 'خور'),
(204, 4, 'فرخی'),
(205, 4, 'دهاقان'),
(206, 4, 'گلشن'),
(207, 4, 'حنا'),
(208, 4, 'سمیرم'),
(209, 4, 'کمه'),
(210, 4, 'ونک'),
(211, 4, 'شاهین شهر'),
(212, 4, 'گرگاب'),
(213, 4, 'گزبرخوار'),
(214, 4, 'لای بید'),
(215, 4, 'میمه'),
(216, 4, 'وزوان'),
(217, 4, 'شهرضا'),
(218, 4, 'منظریه'),
(219, 4, 'داران'),
(220, 4, 'دامنه'),
(221, 4, 'برف انبار'),
(222, 4, 'فریدونشهر'),
(223, 4, 'ابریشم'),
(224, 4, 'ایمانشهر'),
(225, 4, 'بهاران شهر'),
(226, 4, 'پیربکران'),
(227, 4, 'زازران'),
(228, 4, 'فلاورجان'),
(229, 4, 'قهدریجان'),
(230, 4, 'کلیشادوسودرجان'),
(231, 4, 'برزک'),
(232, 4, 'جوشقان قالی'),
(233, 4, 'قمصر'),
(234, 4, 'کاشان'),
(235, 4, 'کامو و چوگان'),
(236, 4, 'مشکات'),
(237, 4, 'نیاسر'),
(238, 4, 'گلپایگان'),
(239, 4, 'گلشهر'),
(240, 4, 'گوگد'),
(241, 4, 'باغ بهادران'),
(242, 4, 'باغشاد'),
(243, 4, 'چرمهین'),
(244, 4, 'چمگردان'),
(245, 4, 'زاینده رود'),
(246, 4, 'زرین شهر'),
(247, 4, 'سده لنجان'),
(248, 4, 'فولادشهر'),
(249, 4, 'ورنامخواست'),
(250, 4, 'دیزیچه'),
(251, 4, 'زیباشهر'),
(252, 4, 'طالخونچه'),
(253, 4, 'کرکوند'),
(254, 4, 'مبارکه'),
(255, 4, 'مجلسی'),
(256, 4, 'انارک'),
(257, 4, 'بافران'),
(258, 4, 'نایین'),
(259, 4, 'جوزدان'),
(260, 4, 'دهق'),
(261, 4, 'علویجه'),
(262, 4, 'کهریزسنگ'),
(263, 4, 'گلدشت'),
(264, 4, 'نجف آباد'),
(265, 4, 'بادرود'),
(266, 4, 'خالدآباد'),
(267, 4, 'طرق رود'),
(268, 4, 'نطنز'),
(269, 5, 'اشتهارد'),
(270, 5, 'چهارباغ'),
(271, 5, 'شهرجدیدهشتگرد'),
(272, 5, 'کوهسار'),
(273, 5, 'گلسار'),
(274, 5, 'هشتگرد'),
(275, 5, 'طالقان'),
(276, 5, 'فردیس'),
(277, 5, 'مشکین دشت'),
(278, 5, 'آسارا'),
(279, 5, 'کرج'),
(280, 5, 'کمال شهر'),
(281, 5, 'گرمدره'),
(282, 5, 'ماهدشت'),
(283, 5, 'محمدشهر'),
(284, 5, 'تنکمان'),
(285, 5, 'نظرآباد'),
(286, 6, 'آبدانان'),
(287, 6, 'سراب باغ'),
(288, 6, 'مورموری'),
(289, 6, 'ایلام'),
(290, 6, 'چوار'),
(291, 6, 'ایوان'),
(292, 6, 'زرنه'),
(293, 6, 'بدره'),
(294, 6, 'آسمان آباد'),
(295, 6, 'بلاوه'),
(296, 6, 'توحید'),
(297, 6, 'سرابله'),
(298, 6, 'شباب'),
(299, 6, 'دره شهر'),
(300, 6, 'ماژین'),
(301, 6, 'پهله'),
(302, 6, 'دهلران'),
(303, 6, 'موسیان'),
(304, 6, 'میمه'),
(305, 6, 'لومار'),
(306, 6, 'ارکواز'),
(307, 6, 'دلگشا'),
(308, 6, 'مهر'),
(309, 6, 'صالح آباد'),
(310, 6, 'مهران'),
(311, 7, 'بوشهر'),
(312, 7, 'چغادک'),
(313, 7, 'خارک'),
(314, 7, 'عالی شهر'),
(315, 7, 'آباد'),
(316, 7, 'اهرم'),
(317, 7, 'دلوار'),
(318, 7, 'انارستان'),
(319, 7, 'جم'),
(320, 7, 'ریز'),
(321, 7, 'آب پخش'),
(322, 7, 'برازجان'),
(323, 7, 'بوشکان'),
(324, 7, 'تنگ ارم'),
(325, 7, 'دالکی'),
(326, 7, 'سعد آباد'),
(327, 7, 'شبانکاره'),
(328, 7, 'کلمه'),
(329, 7, 'وحدتیه'),
(330, 7, 'بادوله'),
(331, 7, 'خورموج'),
(332, 7, 'شنبه'),
(333, 7, 'کاکی'),
(334, 7, 'آبدان'),
(335, 7, 'بردخون'),
(336, 7, 'بردستان'),
(337, 7, 'بندردیر'),
(338, 7, 'دوراهک'),
(339, 7, 'امام حسن'),
(340, 7, 'بندردیلم'),
(341, 7, 'عسلویه'),
(342, 7, 'نخل تقی'),
(343, 7, 'بندرکنگان'),
(344, 7, 'بنک'),
(345, 7, 'سیراف'),
(346, 7, 'بندرریگ'),
(347, 7, 'بندرگناوه'),
(348, 8, 'احمد آباد مستوفی'),
(349, 8, 'اسلامشهر'),
(350, 8, 'چهاردانگه'),
(351, 8, 'صالحیه'),
(352, 8, 'گلستان'),
(353, 8, 'نسیم شهر'),
(354, 8, 'پاکدشت'),
(355, 8, 'شریف آباد'),
(356, 8, 'فرون اباد'),
(357, 8, 'بومهن'),
(358, 8, 'پردیس'),
(359, 8, 'پیشوا'),
(360, 8, 'تهران'),
(361, 8, 'آبسرد'),
(362, 8, 'آبعلی'),
(363, 8, 'دماوند'),
(364, 8, 'رودهن'),
(365, 8, 'کیلان'),
(366, 8, 'پرند'),
(367, 8, 'رباطکریم'),
(368, 8, 'نصیرشهر'),
(369, 8, 'باقرشهر'),
(370, 8, 'حسن آباد'),
(371, 8, 'ری'),
(372, 8, 'کهریزک'),
(373, 8, 'تجریش'),
(374, 8, 'شمشک'),
(375, 8, 'فشم'),
(376, 8, 'لواسان'),
(377, 8, 'اندیشه'),
(378, 8, 'باغستان'),
(379, 8, 'شاهدشهر'),
(380, 8, 'شهریار'),
(381, 8, 'صباشهر'),
(382, 8, 'فردوسیه'),
(383, 8, 'وحیدیه'),
(384, 8, 'ارجمند'),
(385, 8, 'فیروزکوه'),
(386, 8, 'قدس'),
(387, 8, 'قرچک'),
(388, 8, 'صفادشت'),
(389, 8, 'ملارد'),
(390, 8, 'جوادآباد'),
(391, 8, 'ورامین'),
(392, 9, 'اردل'),
(393, 9, 'دشتک'),
(394, 9, 'سرخون'),
(395, 9, 'کاج'),
(396, 9, 'بروجن'),
(397, 9, 'بلداجی'),
(398, 9, 'سفیددشت'),
(399, 9, 'فرادبنه'),
(400, 9, 'گندمان'),
(401, 9, 'نقنه'),
(402, 9, 'بن'),
(403, 9, 'وردنجان'),
(404, 9, 'سامان'),
(405, 9, 'سودجان'),
(406, 9, 'سورشجان'),
(407, 9, 'شهرکرد'),
(408, 9, 'طاقانک'),
(409, 9, 'فرخ شهر'),
(410, 9, 'کیان'),
(411, 9, 'نافچ'),
(412, 9, 'هارونی'),
(413, 9, 'هفشجان'),
(414, 9, 'باباحیدر'),
(415, 9, 'پردنجان'),
(416, 9, 'جونقان'),
(417, 9, 'چلیچه'),
(418, 9, 'فارسان'),
(419, 9, 'گوجان'),
(420, 9, 'بازفت'),
(421, 9, 'چلگرد'),
(422, 9, 'صمصامی'),
(423, 9, 'دستنا'),
(424, 9, 'شلمزار'),
(425, 9, 'گهرو'),
(426, 9, 'ناغان'),
(427, 9, 'آلونی'),
(428, 9, 'سردشت'),
(429, 9, 'لردگان'),
(430, 9, 'مال خلیفه'),
(431, 9, 'منج'),
(432, 10, 'ارسک'),
(433, 10, 'بشرویه'),
(434, 10, 'بیرجند'),
(435, 10, 'خوسف'),
(436, 10, 'محمدشهر'),
(437, 10, 'اسدیه'),
(438, 10, 'طبس مسینا'),
(439, 10, 'قهستان'),
(440, 10, 'گزیک'),
(441, 10, 'حاجی آباد'),
(442, 10, 'زهان'),
(443, 10, 'آیسک'),
(444, 10, 'سرایان'),
(445, 10, 'سه قلعه'),
(446, 10, 'سربیشه'),
(447, 10, 'مود'),
(448, 10, 'دیهوک'),
(449, 10, 'طبس'),
(450, 10, 'عشق آباد'),
(451, 10, 'اسلامیه'),
(452, 10, 'فردوس'),
(453, 10, 'آرین شهر'),
(454, 10, 'اسفدن'),
(455, 10, 'خضری دشت بیاض'),
(456, 10, 'قاین'),
(457, 10, 'نیمبلوک'),
(458, 10, 'شوسف'),
(459, 10, 'نهبندان'),
(460, 11, 'باخرز'),
(461, 11, 'بجستان'),
(462, 11, 'یونسی'),
(463, 11, 'انابد'),
(464, 11, 'بردسکن'),
(465, 11, 'شهراباد'),
(466, 11, 'شاندیز'),
(467, 11, 'طرقبه'),
(468, 11, 'تایباد'),
(469, 11, 'کاریز'),
(470, 11, 'مشهدریزه'),
(471, 11, 'احمدابادصولت'),
(472, 11, 'تربت جام'),
(473, 11, 'صالح آباد'),
(474, 11, 'نصرآباد'),
(475, 11, 'نیل شهر'),
(476, 11, 'بایک'),
(477, 11, 'تربت حیدریه'),
(478, 11, 'رباط سنگ'),
(479, 11, 'کدکن'),
(480, 11, 'جغتای'),
(481, 11, 'نقاب'),
(482, 11, 'چناران'),
(483, 11, 'گلبهار'),
(484, 11, 'گلمکان'),
(485, 11, 'خلیل آباد'),
(486, 11, 'کندر'),
(487, 11, 'خواف'),
(488, 11, 'سلامی'),
(489, 11, 'سنگان'),
(490, 11, 'قاسم آباد'),
(491, 11, 'نشتیفان'),
(492, 11, 'سلطان آباد'),
(493, 11, 'داورزن'),
(494, 11, 'چاپشلو'),
(495, 11, 'درگز'),
(496, 11, 'لطف آباد'),
(497, 11, 'نوخندان'),
(498, 11, 'جنگل'),
(499, 11, 'رشتخوار'),
(500, 11, 'دولت آباد'),
(501, 11, 'روداب'),
(502, 11, 'سبزوار'),
(503, 11, 'ششتمد'),
(504, 11, 'سرخس'),
(505, 11, 'مزدآوند'),
(506, 11, 'سفیدسنگ'),
(507, 11, 'فرهادگرد'),
(508, 11, 'فریمان'),
(509, 11, 'قلندرآباد'),
(510, 11, 'فیروزه'),
(511, 11, 'همت آباد'),
(512, 11, 'باجگیران'),
(513, 11, 'قوچان'),
(514, 11, 'ریوش'),
(515, 11, 'کاشمر'),
(516, 11, 'شهرزو'),
(517, 11, 'کلات'),
(518, 11, 'بیدخت'),
(519, 11, 'کاخک'),
(520, 11, 'گناباد'),
(521, 11, 'رضویه'),
(522, 11, 'مشهد'),
(523, 11, 'مشهد ثامن'),
(524, 11, 'ملک آباد'),
(525, 11, 'شادمهر'),
(526, 11, 'فیض آباد'),
(527, 11, 'بار'),
(528, 11, 'چکنه'),
(529, 11, 'خرو'),
(530, 11, 'درود'),
(531, 11, 'عشق آباد'),
(532, 11, 'قدمگاه'),
(533, 11, 'نیشابور'),
(534, 12, 'اسفراین'),
(535, 12, 'صفی آباد'),
(536, 12, 'بجنورد'),
(537, 12, 'چناران شهر'),
(538, 12, 'حصارگرمخان'),
(539, 12, 'جاجرم'),
(540, 12, 'سنخواست'),
(541, 12, 'شوقان'),
(542, 12, 'راز'),
(543, 12, 'زیارت'),
(544, 12, 'شیروان'),
(545, 12, 'قوشخانه'),
(546, 12, 'لوجلی'),
(547, 12, 'تیتکانلو'),
(548, 12, 'فاروج'),
(549, 12, 'ایور'),
(550, 12, 'درق'),
(551, 12, 'گرمه'),
(552, 12, 'آشخانه'),
(553, 12, 'آوا'),
(554, 12, 'پیش قلعه'),
(555, 12, 'قاضی'),
(556, 13, 'آبادان'),
(557, 13, 'اروندکنار'),
(558, 13, 'چویبده'),
(559, 13, 'آغاجاری'),
(560, 13, 'امیدیه'),
(561, 13, 'جایزان'),
(562, 13, 'آبژدان'),
(563, 13, 'قلعه خواجه'),
(564, 13, 'آزادی'),
(565, 13, 'اندیمشک'),
(566, 13, 'بیدروبه'),
(567, 13, 'چم گلک'),
(568, 13, 'حسینیه'),
(569, 13, 'الهایی'),
(570, 13, 'اهواز'),
(571, 13, 'ایذه'),
(572, 13, 'دهدز'),
(573, 13, 'باغ ملک'),
(574, 13, 'صیدون'),
(575, 13, 'قلعه تل'),
(576, 13, 'میداود'),
(577, 13, 'شیبان'),
(578, 13, 'ملاثانی'),
(579, 13, 'ویس'),
(580, 13, 'بندرامام خمینی'),
(581, 13, 'بندرماهشهر'),
(582, 13, 'چمران'),
(583, 13, 'بهبهان'),
(584, 13, 'تشان'),
(585, 13, 'سردشت'),
(586, 13, 'منصوریه'),
(587, 13, 'حمیدیه'),
(588, 13, 'خرمشهر'),
(589, 13, 'مقاومت'),
(590, 13, 'مینوشهر'),
(591, 13, 'چغامیش'),
(592, 13, 'حمزه'),
(593, 13, 'دزفول'),
(594, 13, 'سالند'),
(595, 13, 'سیاه منصور'),
(596, 13, 'شمس آباد'),
(597, 13, 'شهر امام'),
(598, 13, 'صفی آباد'),
(599, 13, 'میانرود'),
(600, 13, 'ابوحمیظه'),
(601, 13, 'بستان'),
(602, 13, 'سوسنگرد'),
(603, 13, 'کوت سیدنعیم'),
(604, 13, 'رامشیر'),
(605, 13, 'مشراگه'),
(606, 13, 'رامهرمز'),
(607, 13, 'خنافره'),
(608, 13, 'دارخوین'),
(609, 13, 'شادگان'),
(610, 13, 'الوان'),
(611, 13, 'حر'),
(612, 13, 'شاوور'),
(613, 13, 'شوش'),
(614, 13, 'فتح المبین'),
(615, 13, 'سرداران'),
(616, 13, 'شرافت'),
(617, 13, 'شوشتر'),
(618, 13, 'گوریه'),
(619, 13, 'کوت عبداله'),
(620, 13, 'ترکالکی'),
(621, 13, 'جنت مکان'),
(622, 13, 'سماله'),
(623, 13, 'صالح شهر'),
(624, 13, 'گتوند'),
(625, 13, 'لالی'),
(626, 13, 'گلگیر'),
(627, 13, 'مسجدسلیمان'),
(628, 13, 'هفتگل'),
(629, 13, 'زهره'),
(630, 13, 'هندیجان'),
(631, 13, 'رفیع'),
(632, 13, 'هویزه'),
(633, 14, 'ابهر'),
(634, 14, 'صایین قلعه'),
(635, 14, 'هیدج'),
(636, 14, 'حلب'),
(637, 14, 'زرین آباد'),
(638, 14, 'زرین رود'),
(639, 14, 'سجاس'),
(640, 14, 'سهرورد'),
(641, 14, 'قیدار'),
(642, 14, 'کرسف'),
(643, 14, 'گرماب'),
(644, 14, 'نوربهار'),
(645, 14, 'خرمدره'),
(646, 14, 'ارمغانخانه'),
(647, 14, 'زنجان'),
(648, 14, 'نیک پی'),
(649, 14, 'سلطانیه'),
(650, 14, 'آب بر'),
(651, 14, 'چورزق'),
(652, 14, 'دندی'),
(653, 14, 'ماه نشان'),
(654, 15, 'آرادان'),
(655, 15, 'کهن آباد'),
(656, 15, 'امیریه'),
(657, 15, 'دامغان'),
(658, 15, 'دیباج'),
(659, 15, 'کلاته'),
(660, 15, 'سرخه'),
(661, 15, 'سمنان'),
(662, 15, 'بسطام'),
(663, 15, 'بیارجمند'),
(664, 15, 'رودیان'),
(665, 15, 'شاهرود'),
(666, 15, 'کلاته خیج'),
(667, 15, 'مجن'),
(668, 15, 'ایوانکی'),
(669, 15, 'گرمسار'),
(670, 15, 'درجزین'),
(671, 15, 'شهمیرزاد'),
(672, 15, 'مهدی شهر'),
(673, 15, 'میامی'),
(674, 16, 'ایرانشهر'),
(675, 16, 'بزمان'),
(676, 16, 'بمپور'),
(677, 16, 'محمدان'),
(678, 16, 'چابهار'),
(679, 16, 'نگور'),
(680, 16, 'خاش'),
(681, 16, 'نوک آباد'),
(682, 16, 'گلمورتی'),
(683, 16, 'بنجار'),
(684, 16, 'زابل'),
(685, 16, 'زاهدان'),
(686, 16, 'نصرت آباد'),
(687, 16, 'زهک'),
(688, 16, 'جالق'),
(689, 16, 'سراوان'),
(690, 16, 'سیرکان'),
(691, 16, 'گشت'),
(692, 16, 'محمدی'),
(693, 16, 'پیشین'),
(694, 16, 'راسک'),
(695, 16, 'سرباز'),
(696, 16, 'سوران'),
(697, 16, 'هیدوچ'),
(698, 16, 'فنوج'),
(699, 16, 'قصرقند'),
(700, 16, 'زرآباد'),
(701, 16, 'کنارک'),
(702, 16, 'مهرستان'),
(703, 16, 'میرجاوه'),
(704, 16, 'اسپکه'),
(705, 16, 'بنت'),
(706, 16, 'نیک شهر'),
(707, 16, 'ادیمی'),
(708, 16, 'شهرک علی اکبر'),
(709, 16, 'محمدآباد'),
(710, 16, 'دوست محمد'),
(711, 17, 'آباده'),
(712, 17, 'ایزدخواست'),
(713, 17, 'بهمن'),
(714, 17, 'سورمق'),
(715, 17, 'صغاد'),
(716, 17, 'ارسنجان'),
(717, 17, 'استهبان'),
(718, 17, 'ایج'),
(719, 17, 'رونیز'),
(720, 17, 'اقلید'),
(721, 17, 'حسن اباد'),
(722, 17, 'دژکرد'),
(723, 17, 'سده'),
(724, 17, 'بوانات'),
(725, 17, 'حسامی'),
(726, 17, 'کره ای'),
(727, 17, 'مزایجان'),
(728, 17, 'سعادت شهر'),
(729, 17, 'مادرسلیمان'),
(730, 17, 'باب انار'),
(731, 17, 'جهرم'),
(732, 17, 'خاوران'),
(733, 17, 'دوزه'),
(734, 17, 'قطب آباد'),
(735, 17, 'خرامه'),
(736, 17, 'سلطان شهر'),
(737, 17, 'صفاشهر'),
(738, 17, 'قادراباد'),
(739, 17, 'خنج'),
(740, 17, 'جنت شهر'),
(741, 17, 'داراب'),
(742, 17, 'دوبرجی'),
(743, 17, 'فدامی'),
(744, 17, 'کوپن'),
(745, 17, 'مصیری'),
(746, 17, 'حاجی آباد'),
(747, 17, 'دبیران'),
(748, 17, 'شهرپیر'),
(749, 17, 'اردکان'),
(750, 17, 'بیضا'),
(751, 17, 'هماشهر'),
(752, 17, 'سروستان'),
(753, 17, 'کوهنجان'),
(754, 17, 'خانه زنیان'),
(755, 17, 'داریان'),
(756, 17, 'زرقان'),
(757, 17, 'شهرصدرا'),
(758, 17, 'شیراز'),
(759, 17, 'لپویی'),
(760, 17, 'دهرم'),
(761, 17, 'فراشبند'),
(762, 17, 'نوجین'),
(763, 17, 'زاهدشهر'),
(764, 17, 'ششده'),
(765, 17, 'فسا'),
(766, 17, 'قره بلاغ'),
(767, 17, 'میانشهر'),
(768, 17, 'نوبندگان'),
(769, 17, 'فیروزآباد'),
(770, 17, 'میمند'),
(771, 17, 'افزر'),
(772, 17, 'امام شهر'),
(773, 17, 'قیر'),
(774, 17, 'کارزین (فتح آباد)'),
(775, 17, 'مبارک آباددیز'),
(776, 17, 'بالاده'),
(777, 17, 'خشت'),
(778, 17, 'قایمیه'),
(779, 17, 'کازرون'),
(780, 17, 'کنارتخته'),
(781, 17, 'نودان'),
(782, 17, 'کوار'),
(783, 17, 'گراش'),
(784, 17, 'اوز'),
(785, 17, 'بنارویه'),
(786, 17, 'بیرم'),
(787, 17, 'جویم'),
(788, 17, 'خور'),
(789, 17, 'عمادده'),
(790, 17, 'لار'),
(791, 17, 'لطیفی'),
(792, 17, 'اشکنان'),
(793, 17, 'اهل'),
(794, 17, 'علامرودشت'),
(795, 17, 'لامرد'),
(796, 17, 'خانیمن'),
(797, 17, 'رامجرد'),
(798, 17, 'سیدان'),
(799, 17, 'کامفیروز'),
(800, 17, 'مرودشت'),
(801, 17, 'بابامنیر'),
(802, 17, 'خومه زار'),
(803, 17, 'نورآباد'),
(804, 17, 'اسیر'),
(805, 17, 'خوزی'),
(806, 17, 'گله دار'),
(807, 17, 'مهر'),
(808, 17, 'وراوی'),
(809, 17, 'آباده طشک'),
(810, 17, 'قطرویه'),
(811, 17, 'مشکان'),
(812, 17, 'نی ریز'),
(813, 18, 'آبیک'),
(814, 18, 'خاکعلی'),
(815, 18, 'آبگرم'),
(816, 18, 'آوج'),
(817, 18, 'الوند'),
(818, 18, 'بیدستان'),
(819, 18, 'شریفیه'),
(820, 18, 'محمدیه'),
(821, 18, 'ارداق'),
(822, 18, 'بویین زهرا'),
(823, 18, 'دانسفهان'),
(824, 18, 'سگزآباد'),
(825, 18, 'شال'),
(826, 18, 'اسفرورین'),
(827, 18, 'تاکستان'),
(828, 18, 'خرمدشت'),
(829, 18, 'ضیاڈآباد'),
(830, 18, 'نرجه'),
(831, 18, 'اقبالیه'),
(832, 18, 'رازمیان'),
(833, 18, 'سیردان'),
(834, 18, 'قزوین'),
(835, 18, 'کوهین'),
(836, 18, 'محمودآبادنمونه'),
(837, 18, 'معلم کلایه'),
(838, 19, 'جعفریه'),
(839, 19, 'دستجرد'),
(840, 19, 'سلفچگان'),
(841, 19, 'قم'),
(842, 19, 'قنوات'),
(843, 19, 'کهک'),
(844, 20, 'آرمرده'),
(845, 20, 'بانه'),
(846, 20, 'بویین سفلی'),
(847, 20, 'کانی سور'),
(848, 20, 'بابارشانی'),
(849, 20, 'بیجار'),
(850, 20, 'پیرتاج'),
(851, 20, 'توپ آغاج'),
(852, 20, 'یاسوکند'),
(853, 20, 'بلبان آباد'),
(854, 20, 'دهگلان'),
(855, 20, 'دیواندره'),
(856, 20, 'زرینه'),
(857, 20, 'اورامان تخت'),
(858, 20, 'سروآباد'),
(859, 20, 'سقز'),
(860, 20, 'صاحب'),
(861, 20, 'سنندج'),
(862, 20, 'شویشه'),
(863, 20, 'دزج'),
(864, 20, 'دلبران'),
(865, 20, 'سریش آباد'),
(866, 20, 'قروه'),
(867, 20, 'کامیاران'),
(868, 20, 'موچش'),
(869, 20, 'برده رشه'),
(870, 20, 'چناره'),
(871, 20, 'کانی دینار'),
(872, 20, 'مریوان'),
(873, 21, 'ارزوییه'),
(874, 21, 'امین شهر'),
(875, 21, 'انار'),
(876, 21, 'بافت'),
(877, 21, 'بزنجان'),
(878, 21, 'بردسیر'),
(879, 21, 'دشتکار'),
(880, 21, 'گلزار'),
(881, 21, 'لاله زار'),
(882, 21, 'نگار'),
(883, 21, 'بروات'),
(884, 21, 'بم'),
(885, 21, 'بلوک'),
(886, 21, 'جبالبارز'),
(887, 21, 'جیرفت'),
(888, 21, 'درب بهشت'),
(889, 21, 'رابر'),
(890, 21, 'هنزا'),
(891, 21, 'راور'),
(892, 21, 'هجدک'),
(893, 21, 'بهرمان'),
(894, 21, 'رفسنجان'),
(895, 21, 'صفاییه'),
(896, 21, 'کشکوییه'),
(897, 21, 'مس سرچشمه'),
(898, 21, 'رودبار'),
(899, 21, 'زهکلوت'),
(900, 21, 'گنبکی'),
(901, 21, 'محمدآباد'),
(902, 21, 'خانوک'),
(903, 21, 'ریحان'),
(904, 21, 'زرند'),
(905, 21, 'یزدان شهر'),
(906, 21, 'بلورد'),
(907, 21, 'پاریز'),
(908, 21, 'خواجو شهر'),
(909, 21, 'زیدآباد'),
(910, 21, 'سیرجان'),
(911, 21, 'نجف شهر'),
(912, 21, 'هماشهر'),
(913, 21, 'جوزم'),
(914, 21, 'خاتون اباد'),
(915, 21, 'خورسند'),
(916, 21, 'دهج'),
(917, 21, 'شهربابک'),
(918, 21, 'دوساری'),
(919, 21, 'عنبرآباد'),
(920, 21, 'مردهک'),
(921, 21, 'فاریاب'),
(922, 21, 'فهرج'),
(923, 21, 'قلعه گنج'),
(924, 21, 'اختیارآباد'),
(925, 21, 'اندوهجرد'),
(926, 21, 'باغین'),
(927, 21, 'جوپار'),
(928, 21, 'چترود'),
(929, 21, 'راین'),
(930, 21, 'زنگی آباد'),
(931, 21, 'شهداد'),
(932, 21, 'کاظم آباد'),
(933, 21, 'کرمان'),
(934, 21, 'گلباف'),
(935, 21, 'ماهان'),
(936, 21, 'محی آباد'),
(937, 21, 'کوهبنان'),
(938, 21, 'کیانشهر'),
(939, 21, 'کهنوج'),
(940, 21, 'منوجان'),
(941, 21, 'نودژ'),
(942, 21, 'نرماشیر'),
(943, 21, 'نظام شهر'),
(944, 22, 'اسلام آبادغرب'),
(945, 22, 'حمیل'),
(946, 22, 'بانوره'),
(947, 22, 'باینگان'),
(948, 22, 'پاوه'),
(949, 22, 'نودشه'),
(950, 22, 'نوسود'),
(951, 22, 'ازگله'),
(952, 22, 'تازه آباد'),
(953, 22, 'جوانرود'),
(954, 22, 'ریجاب'),
(955, 22, 'کرند'),
(956, 22, 'گهواره'),
(957, 22, 'روانسر'),
(958, 22, 'شاهو'),
(959, 22, 'سرپل ذهاب'),
(960, 22, 'سطر'),
(961, 22, 'سنقر'),
(962, 22, 'صحنه'),
(963, 22, 'میان راهان'),
(964, 22, 'سومار'),
(965, 22, 'قصرشیرین'),
(966, 22, 'رباط'),
(967, 22, 'کرمانشاه'),
(968, 22, 'کوزران'),
(969, 22, 'هلشی'),
(970, 22, 'کنگاور'),
(971, 22, 'گودین'),
(972, 22, 'سرمست'),
(973, 22, 'گیلانغرب'),
(974, 22, 'بیستون'),
(975, 22, 'هرسین'),
(976, 23, 'باشت'),
(977, 23, 'چیتاب'),
(978, 23, 'گراب سفلی'),
(979, 23, 'مادوان'),
(980, 23, 'مارگون'),
(981, 23, 'یاسوج'),
(982, 23, 'لیکک'),
(983, 23, 'چرام'),
(984, 23, 'سرفاریاب'),
(985, 23, 'پاتاوه'),
(986, 23, 'سی سخت'),
(987, 23, 'دهدشت'),
(988, 23, 'دیشموک'),
(989, 23, 'سوق'),
(990, 23, 'قلعه رییسی'),
(991, 23, 'دوگنبدان'),
(992, 23, 'لنده'),
(993, 24, 'آزادشهر'),
(994, 24, 'نگین شهر'),
(995, 24, 'نوده خاندوز'),
(996, 24, 'آق قلا'),
(997, 24, 'انبارآلوم'),
(998, 24, 'بندرگز'),
(999, 24, 'نوکنده'),
(1000, 24, 'بندرترکمن'),
(1001, 24, 'تاتارعلیا'),
(1002, 24, 'خان ببین'),
(1003, 24, 'دلند'),
(1004, 24, 'رامیان'),
(1005, 24, 'سنگدوین'),
(1006, 24, 'علی اباد'),
(1007, 24, 'فاضل آباد'),
(1008, 24, 'مزرعه'),
(1009, 24, 'کردکوی'),
(1010, 24, 'فراغی'),
(1011, 24, 'کلاله'),
(1012, 24, 'گالیکش'),
(1013, 24, 'جلین'),
(1014, 24, 'سرخنکلاته'),
(1015, 24, 'گرگان'),
(1016, 24, 'سیمین شهر'),
(1017, 24, 'گمیش تپه'),
(1018, 24, 'اینچه برون'),
(1019, 24, 'گنبدکاووس'),
(1020, 24, 'مراوه'),
(1021, 24, 'مینودشت'),
(1022, 25, 'آستارا'),
(1023, 25, 'لوندویل'),
(1024, 25, 'آستانه اشرفیه'),
(1025, 25, 'کیاشهر'),
(1026, 25, 'املش'),
(1027, 25, 'رانکوه'),
(1028, 25, 'بندرانزلی'),
(1029, 25, 'خشکبیجار'),
(1030, 25, 'خمام'),
(1031, 25, 'رشت'),
(1032, 25, 'سنگر'),
(1033, 25, 'کوچصفهان'),
(1034, 25, 'لشت نشاء'),
(1035, 25, 'لولمان'),
(1036, 25, 'پره سر'),
(1037, 25, 'رضوانشهر'),
(1038, 25, 'بره سر'),
(1039, 25, 'توتکابن'),
(1040, 25, 'جیرنده'),
(1041, 25, 'رستم آباد'),
(1042, 25, 'رودبار'),
(1043, 25, 'لوشان'),
(1044, 25, 'منجیل'),
(1045, 25, 'چابکسر'),
(1046, 25, 'رحیم آباد'),
(1047, 25, 'رودسر'),
(1048, 25, 'کلاچای'),
(1049, 25, 'واجارگاه'),
(1050, 25, 'دیلمان'),
(1051, 25, 'سیاهکل'),
(1052, 25, 'احمدسرگوراب'),
(1053, 25, 'شفت'),
(1054, 25, 'صومعه سرا'),
(1055, 25, 'گوراب زرمیخ'),
(1056, 25, 'مرجقل'),
(1057, 25, 'اسالم'),
(1058, 25, 'چوبر'),
(1059, 25, 'حویق'),
(1060, 25, 'لیسار'),
(1061, 25, 'هشتپر (تالش)'),
(1062, 25, 'فومن'),
(1063, 25, 'ماسوله'),
(1064, 25, 'ماکلوان'),
(1065, 25, 'رودبنه'),
(1066, 25, 'لاهیجان'),
(1067, 25, 'اطاقور'),
(1068, 25, 'چاف و چمخاله'),
(1069, 25, 'شلمان'),
(1070, 25, 'کومله'),
(1071, 25, 'لنگرود'),
(1072, 25, 'بازار جمعه'),
(1073, 25, 'ماسال'),
(1074, 26, 'ازنا'),
(1075, 26, 'مومن آباد'),
(1076, 26, 'الیگودرز'),
(1077, 26, 'شول آباد'),
(1078, 26, 'اشترینان'),
(1079, 26, 'بروجرد'),
(1080, 26, 'پلدختر'),
(1081, 26, 'معمولان'),
(1082, 26, 'بیران شهر'),
(1083, 26, 'خرم آباد'),
(1084, 26, 'زاغه'),
(1085, 26, 'سپیددشت'),
(1086, 26, 'نورآباد'),
(1087, 26, 'هفت چشمه'),
(1088, 26, 'چالانچولان'),
(1089, 26, 'دورود'),
(1090, 26, 'سراب دوره'),
(1091, 26, 'ویسیان'),
(1092, 26, 'چقابل'),
(1093, 26, 'الشتر'),
(1094, 26, 'فیروزآباد'),
(1095, 26, 'درب گنبد'),
(1096, 26, 'کوهدشت'),
(1097, 26, 'کوهنانی'),
(1098, 26, 'گراب'),
(1099, 27, 'آمل'),
(1100, 27, 'امامزاده عبدالله'),
(1101, 27, 'دابودشت'),
(1102, 27, 'رینه'),
(1103, 27, 'گزنک'),
(1104, 27, 'امیرکلا'),
(1105, 27, 'بابل'),
(1106, 27, 'خوش رودپی'),
(1107, 27, 'زرگرمحله'),
(1108, 27, 'گتاب'),
(1109, 27, 'گلوگاه'),
(1110, 27, 'مرزیکلا'),
(1111, 27, 'بابلسر'),
(1112, 27, 'بهنمیر'),
(1113, 27, 'هادی شهر'),
(1114, 27, 'بهشهر'),
(1115, 27, 'خلیل شهر'),
(1116, 27, 'رستمکلا'),
(1117, 27, 'تنکابن'),
(1118, 27, 'خرم آباد'),
(1119, 27, 'شیرود'),
(1120, 27, 'نشتارود'),
(1121, 27, 'جویبار'),
(1122, 27, 'کوهی خیل'),
(1123, 27, 'چالوس'),
(1124, 27, 'مرزن آباد'),
(1125, 27, 'هچیرود'),
(1126, 27, 'رامسر'),
(1127, 27, 'کتالم وسادات شهر'),
(1128, 27, 'پایین هولار'),
(1129, 27, 'ساری'),
(1130, 27, 'فریم'),
(1131, 27, 'کیاسر'),
(1132, 27, 'آلاشت'),
(1133, 27, 'پل سفید'),
(1134, 27, 'زیرآب'),
(1135, 27, 'شیرگاه'),
(1136, 27, 'کیاکلا'),
(1137, 27, 'سلمان شهر'),
(1138, 27, 'عباس اباد'),
(1139, 27, 'کلارآباد'),
(1140, 27, 'فریدونکنار'),
(1141, 27, 'ارطه'),
(1142, 27, 'قایم شهر'),
(1143, 27, 'کلاردشت'),
(1144, 27, 'گلوگاه'),
(1145, 27, 'سرخرود'),
(1146, 27, 'محمودآباد'),
(1147, 27, 'سورک'),
(1148, 27, 'نکا'),
(1149, 27, 'ایزدشهر'),
(1150, 27, 'بلده'),
(1151, 27, 'چمستان'),
(1152, 27, 'رویان'),
(1153, 27, 'نور'),
(1154, 27, 'پول'),
(1155, 27, 'کجور'),
(1156, 27, 'نوشهر'),
(1157, 28, 'آشتیان'),
(1158, 28, 'اراک'),
(1159, 28, 'داودآباد'),
(1160, 28, 'ساروق'),
(1161, 28, 'کارچان'),
(1162, 28, 'تفرش'),
(1163, 28, 'خمین'),
(1164, 28, 'قورچی باشی'),
(1165, 28, 'جاورسیان'),
(1166, 28, 'خنداب'),
(1167, 28, 'دلیجان'),
(1168, 28, 'نراق'),
(1169, 28, 'پرندک'),
(1170, 28, 'خشکرود'),
(1171, 28, 'رازقان'),
(1172, 28, 'زاویه'),
(1173, 28, 'مامونیه'),
(1174, 28, 'آوه'),
(1175, 28, 'ساوه'),
(1176, 28, 'غرق آباد'),
(1177, 28, 'نوبران'),
(1178, 28, 'آستانه'),
(1179, 28, 'توره'),
(1180, 28, 'شازند'),
(1181, 28, 'شهباز'),
(1182, 28, 'مهاجران'),
(1183, 28, 'هندودر'),
(1184, 28, 'خنجین'),
(1185, 28, 'فرمهین'),
(1186, 28, 'کمیجان'),
(1187, 28, 'میلاجرد'),
(1188, 28, 'محلات'),
(1189, 28, 'نیمور'),
(1190, 29, 'ابوموسی'),
(1191, 29, 'بستک'),
(1192, 29, 'جناح'),
(1193, 29, 'سردشت'),
(1194, 29, 'گوهران'),
(1195, 29, 'بندرعباس'),
(1196, 29, 'تازیان پایین'),
(1197, 29, 'تخت'),
(1198, 29, 'فین'),
(1199, 29, 'قلعه قاضی'),
(1200, 29, 'بندرلنگه'),
(1201, 29, 'چارک'),
(1202, 29, 'کنگ'),
(1203, 29, 'کیش'),
(1204, 29, 'لمزان'),
(1205, 29, 'پارسیان'),
(1206, 29, 'دشتی'),
(1207, 29, 'کوشکنار'),
(1208, 29, 'بندرجاسک'),
(1209, 29, 'حاجی اباد'),
(1210, 29, 'سرگز'),
(1211, 29, 'فارغان'),
(1212, 29, 'خمیر'),
(1213, 29, 'رویدر'),
(1214, 29, 'بیکاء'),
(1215, 29, 'دهبارز'),
(1216, 29, 'زیارتعلی'),
(1217, 29, 'سیریک'),
(1218, 29, 'کوهستک'),
(1219, 29, 'گروک'),
(1220, 29, 'درگهان'),
(1221, 29, 'سوزا'),
(1222, 29, 'قشم'),
(1223, 29, 'هرمز'),
(1224, 29, 'تیرور'),
(1225, 29, 'سندرک'),
(1226, 29, 'میناب'),
(1227, 29, 'هشتبندی'),
(1228, 30, 'آجین'),
(1229, 30, 'اسدآباد'),
(1230, 30, 'بهار'),
(1231, 30, 'صالح آباد'),
(1232, 30, 'لالجین'),
(1233, 30, 'مهاجران'),
(1234, 30, 'تویسرکان'),
(1235, 30, 'سرکان'),
(1236, 30, 'فرسفج'),
(1237, 30, 'دمق'),
(1238, 30, 'رزن'),
(1239, 30, 'قروه درجزین'),
(1240, 30, 'فامنین'),
(1241, 30, 'شیرین سو'),
(1242, 30, 'کبودرآهنگ'),
(1243, 30, 'گل تپه'),
(1244, 30, 'ازندریان'),
(1245, 30, 'جوکار'),
(1246, 30, 'زنگنه'),
(1247, 30, 'سامن'),
(1248, 30, 'ملایر'),
(1249, 30, 'برزول'),
(1250, 30, 'فیروزان'),
(1251, 30, 'گیان'),
(1252, 30, 'نهاوند'),
(1253, 30, 'جورقان'),
(1254, 30, 'قهاوند'),
(1255, 30, 'مریانج'),
(1256, 30, 'همدان'),
(1257, 31, 'ابرکوه'),
(1258, 31, 'مهردشت'),
(1259, 31, 'احمدآباد'),
(1260, 31, 'اردکان'),
(1261, 31, 'عقدا'),
(1262, 31, 'اشکذر'),
(1263, 31, 'خضرآباد'),
(1264, 31, 'بافق'),
(1265, 31, 'بهاباد'),
(1266, 31, 'تفت'),
(1267, 31, 'نیر'),
(1268, 31, 'مروست'),
(1269, 31, 'هرات'),
(1270, 31, 'مهریز'),
(1271, 31, 'بفروییه'),
(1272, 31, 'میبد'),
(1273, 31, 'ندوشن'),
(1274, 31, 'حمیدیا'),
(1275, 31, 'زارچ'),
(1276, 31, 'شاهدیه'),
(1277, 31, 'یزد');

-- --------------------------------------------------------

--
-- Stand-in structure for view `services`
-- (See below for the actual view)
--
CREATE TABLE `services` (
`service_id` int(11)
,`d_id` int(11)
,`car_id` int(255)
,`car_name` text
,`car_tag` varchar(9)
,`car_model` text
,`car_type` text
,`fuel_type` text
,`user_id` int(255)
,`name` text
,`lastname` text
,`fullname` mediumtext
,`sex` text
,`phone` varchar(255)
,`birth_date` date
,`register_date` date
,`km_next` bigint(255)
,`km_now` bigint(255)
,`date_time` datetime
,`avg_function` bigint(255)
,`description` text
,`price` bigint(20)
,`detail_service` text
,`is_delete` bit(1)
,`is_change` bit(1)
,`is_sent_msg` bit(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `service_all_detail`
-- (See below for the actual view)
--
CREATE TABLE `service_all_detail` (
`name` text
,`lastname` text
,`sex` text
,`birth_date` date
,`phone` varchar(255)
,`user_is_change` bit(1)
,`user_id` int(255)
,`car_tag` varchar(9)
,`car_name` text
,`car_model` text
,`car_type` text
,`fuel_type` text
,`avg_function` bigint(20)
,`chassis_num` text
,`engine_cap` int(255)
,`register_date` date
,`is_delete` int(11)
,`last_date_of_cheak_service_timing` date
,`b_date` date
,`shop_name` text
,`shop_phone` varchar(255)
,`address` text
,`province` text
,`city` text
,`job_category` text
,`opentime` int(255)
,`closetime` int(255)
,`num_branch` int(255)
,`waiting_space` int(255)
,`catering_service` int(255)
,`serial` text
,`username` text
,`password` text
,`email` text
,`service_id` int(11)
,`d_id` int(11)
,`car_id` int(11)
,`date_time` datetime
,`discount_percent` int(11)
,`discount_quantity` int(11)
,`remaining` int(11)
,`is_change` bit(1)
,`km_now` bigint(255)
,`km_next` bigint(255)
,`is_sent_msg` bit(1)
,`description` text
,`price` bigint(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `service_available`
--

CREATE TABLE `service_available` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `job_service_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `service_available`
--

INSERT INTO `service_available` (`id`, `service_center_id`, `job_service_id`, `status`) VALUES
(89, 10, 1, 1),
(90, 10, 2, 1),
(91, 10, 4, 1),
(101, 42, 1, 1),
(102, 42, 2, 0),
(103, 42, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `service_detail`
--

CREATE TABLE `service_detail` (
  `id` int(11) NOT NULL,
  `id_service` int(11) NOT NULL,
  `sub_group_id` int(11) NOT NULL,
  `date_time_detail` datetime NOT NULL,
  `service_name` text COLLATE utf8_persian_ci NOT NULL,
  `have_visit` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `have_change` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `service_detail`
--

INSERT INTO `service_detail` (`id`, `id_service`, `sub_group_id`, `date_time_detail`, `service_name`, `have_visit`, `have_change`) VALUES
(1, 1, 25, '2021-12-07 00:00:00', 'name', '0', '1'),
(2, 1, 46, '2021-12-22 00:00:00', 'oil', '0', '1'),
(3, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(4, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(5, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(6, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(7, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(8, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(9, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(10, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(11, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(12, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(13, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(14, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(15, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(16, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(17, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(18, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(19, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(20, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(21, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(22, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(23, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(24, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(25, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(26, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(27, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(28, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(29, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(30, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(31, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(32, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(33, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(34, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(35, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(36, 23, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(37, 23, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(38, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(39, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(40, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(41, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(42, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(43, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(44, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(45, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(46, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(47, 24, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(48, 24, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(49, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(50, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(51, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(52, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(53, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(54, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(55, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(56, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(57, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(58, 25, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(59, 25, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(60, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(61, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(62, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(63, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(64, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(65, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(66, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(67, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(68, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(69, 26, 0, '1400-11-12 00:00:00', 'روغن ترمز', '1', '0'),
(70, 26, 0, '1400-11-12 00:00:00', 'فیلتر روغن', '1', '0'),
(71, 26, 0, '1400-11-12 00:00:00', 'فیلتر هوا', '1', '0'),
(72, 26, 0, '1400-11-12 00:00:00', 'فیلتر کابین', '1', '0'),
(73, 26, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(74, 26, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(75, 26, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(76, 26, 0, '1400-11-12 00:00:00', 'فیلتر بنزین', '1', '0'),
(77, 27, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(78, 27, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(79, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(80, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(81, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(82, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(83, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(84, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(85, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(86, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(87, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(88, 28, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(89, 28, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(90, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(91, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(92, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(93, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(94, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(95, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(96, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(97, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(98, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(99, 29, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(100, 29, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(101, 29, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(102, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(103, 30, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(104, 30, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(105, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(106, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(107, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(108, 30, 0, '1400-11-13 00:00:00', 'روغن دنده', '1', '0'),
(109, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(110, 31, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(111, 31, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(112, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(113, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(114, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(115, 31, 0, '1400-11-13 00:00:00', 'روغن دنده', '1', '0');

-- --------------------------------------------------------

--
-- Stand-in structure for view `service_info_detail`
-- (See below for the actual view)
--
CREATE TABLE `service_info_detail` (
`id` int(11)
,`id_service` int(11)
,`sub_group_id` int(11)
,`date_time_detail` datetime
,`service_name` text
,`have_visit` varchar(255)
,`have_change` varchar(255)
,`user_id` int(255)
,`name` text
,`lastname` text
,`sex` text
,`birth_date` date
,`phone` varchar(255)
,`register_date` date
,`user_is_change` bit(1)
,`status` int(11)
,`username` varchar(50)
,`password` varchar(50)
,`car_name` text
,`car_tag` varchar(9)
,`service_id` int(11)
,`d_id` int(11)
,`car_id` int(11)
,`date_time` datetime
,`discount_percent` int(11)
,`discount_quantity` int(11)
,`remaining` int(11)
,`is_change` bit(1)
,`is_delete` bit(1)
,`avg_function` bigint(255)
,`km_now` bigint(255)
,`km_next` bigint(255)
,`is_sent_msg` bit(1)
,`description` text
,`price` bigint(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `service_records`
--

CREATE TABLE `service_records` (
  `service_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `discount_percent` int(11) NOT NULL DEFAULT '0',
  `discount_quantity` int(11) NOT NULL DEFAULT '0',
  `remaining` int(11) NOT NULL DEFAULT '0',
  `is_change` bit(1) NOT NULL DEFAULT b'0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `avg_function` bigint(255) NOT NULL DEFAULT '0',
  `km_now` bigint(255) NOT NULL DEFAULT '0',
  `km_next` bigint(255) NOT NULL DEFAULT '0',
  `is_sent_msg` bit(1) NOT NULL DEFAULT b'0',
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `price` bigint(20) NOT NULL,
  `detail_service` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `service_records`
--

INSERT INTO `service_records` (`service_id`, `d_id`, `car_id`, `date_time`, `discount_percent`, `discount_quantity`, `remaining`, `is_change`, `is_delete`, `avg_function`, `km_now`, `km_next`, `is_sent_msg`, `description`, `price`, `detail_service`) VALUES
(2, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'1', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(3, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(4, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(5, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'1', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(6, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'1', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(7, 10, 37, '2022-09-29 00:00:00', 0, 0, 0, b'0', b'0', 5000, 300004, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(8, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(9, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(10, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(11, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(12, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(13, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(14, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(15, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(16, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(17, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(18, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(19, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(20, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(21, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(22, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(23, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(24, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(25, 10, 37, '2022-12-01 03:01:04', 0, 0, 0, b'0', b'0', 50000, 290000, 320000, b'0', 'تست', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(27, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(28, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(29, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'1', 5000, 250000, 265000, b'0', '', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(30, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'1', 5000, 265000, 280000, b'0', '', 254000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(31, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'0', 5000, 25, 1000, b'0', '', 256000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(32, 10, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'0', 5000, 250000, 260000, b'0', '', 250000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(33, 5, 37, '2020-04-01 03:05:00', 0, 0, 0, b'1', b'1', 32323, 290000, 320000, b'1', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(34, 6, 37, '2022-04-13 22:53:01', 0, 0, 0, b'1', b'1', 32323, 290000, 320000, b'1', 'test', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(35, 8, 37, '2022-04-13 23:24:48', 1, 1, 0, b'1', b'1', 32323, 290000, 320000, b'1', 'test 35', 265000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(36, 16, 37, '2020-04-01 03:05:00', 0, 0, 0, b'0', b'1', 0, 0, 0, b'0', '', 500000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(37, 16, 37, '2022-04-30 19:44:02', 0, 0, 0, b'0', b'1', 0, 5000, 7600, b'0', '', 300000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(38, 10, 37, '2022-09-24 00:00:00', 0, 0, 0, b'1', b'1', 550000, 250000, 520000, b'0', '', 2500000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(39, 10, 45, '2022-09-24 00:00:00', 0, 0, 0, b'0', b'0', 25000, 25000, 35000, b'0', '', 250000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(40, 10, 45, '2022-09-25 00:00:00', 0, 0, 0, b'1', b'1', 25000, 23000, 35000, b'0', '', 250000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(41, 10, 45, '2022-09-25 00:00:00', 0, 0, 0, b'0', b'0', 25000, 22000, 35000, b'0', '', 250000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(42, 10, 49, '2022-09-25 00:00:00', 0, 0, 0, b'0', b'0', 5000, 25000, 28000, b'0', '', 5000000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(43, 10, 49, '2022-09-26 00:00:00', 0, 0, 0, b'0', b'0', 5000, 25000, 28000, b'0', '', 822000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(44, 10, 50, '2022-09-26 00:00:00', 0, 0, 0, b'0', b'0', 5000, 25000, 30000, b'0', '', 652000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(45, 10, 46, '2022-09-27 00:00:00', 0, 0, 0, b'0', b'0', 65, 245000, 250000, b'0', '', 4650000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(46, 10, 46, '2022-09-28 00:00:00', 0, 0, 0, b'0', b'0', 67, 250000, 255000, b'0', '', 3250000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(47, 10, 51, '2022-09-28 00:00:00', 0, 0, 0, b'0', b'0', 55, 75000, 80000, b'0', '', 3650000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(48, 10, 46, '2022-09-29 00:00:00', 0, 0, 0, b'0', b'0', 55, 248000, 253000, b'0', '', 3400000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(49, 10, 57, '2022-09-30 00:00:00', 0, 0, 0, b'0', b'0', 100, 25000000, 35000000, b'0', '', 5000000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(50, 10, 57, '2022-10-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 250000, 258000, b'0', '', 25000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(51, 10, 57, '2022-10-01 00:00:00', 0, 0, 0, b'0', b'0', 500, 500000, 508000, b'0', '', 65000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(52, 42, 52, '2022-10-02 00:00:00', 0, 0, 0, b'0', b'0', 50000, 520000, 528000, b'0', '', 850000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(53, 42, 46, '2022-09-30 00:00:00', 0, 0, 0, b'0', b'0', 50, 2500, 2580, b'0', '', 590000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(54, 42, 52, '2022-10-06 00:00:00', 0, 0, 0, b'0', b'0', 58, 25000, 29000, b'0', '', 53000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(55, 10, 57, '2022-10-14 00:00:00', 0, 0, 0, b'0', b'0', 300000, 25000, 325000, b'0', '', 530000, '[{\"id\":1,\"name\":\"تعویض روغن ترمز با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(56, 10, 57, '2022-10-14 00:00:00', 0, 0, 0, b'0', b'0', 4000, 35000, 39000, b'0', '', 653000, '[{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"}]'),
(57, 10, 56, '2022-10-14 00:00:00', 0, 0, 0, b'0', b'0', 6000, 32000, 36000, b'0', '', 253000, '[{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(58, 10, 57, '2022-10-14 00:00:00', 0, 0, 0, b'0', b'0', 100, 50000, 55000, b'0', '', 532000, '[{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":4,\"name\":\"تنظیم باد لاستیک خودرو\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(59, 42, 46, '2022-10-14 00:00:00', 0, 0, 0, b'0', b'0', 45, 10000, 15000, b'0', '', 12700000, '[{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"},{\"id\":4,\"name\":\"تنظیم باد لاستیک خودرو\",\"selb\":\"true\",\"selt\":\"false\"}]'),
(60, 42, 46, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 48, 5500, 10500, b'0', '', 4150000, '[{\"id\":1,\"name\":\"تعویض روغن گیربکس اتوماتیک با دستگاه\",\"selb\":\"true\",\"selt\":\"false\"},{\"id\":4,\"name\":\"تنظیم باد لاستیک خودرو\",\"selb\":\"false\",\"selt\":\"true\"}]'),
(61, 42, 58, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 5000, 50000, 55000, b'0', '', 2580, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":2,\"name\":\"روغن دنده اتوماتیک\",\"selb\":\"true\",\"selt\":\"true\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":4,\"name\":\"روغن هیدرولیک\",\"selb\":\"false\",\"selt\":\"true\"}]'),
(62, 42, 52, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 100, 52000, 57000, b'0', '', 250000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"true\",\"selt\":\"false\"},{\"id\":2,\"name\":\"روغن دنده اتوماتیک\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"false\",\"selt\":\"true\"},{\"id\":4,\"name\":\"روغن هیدرولیک\",\"selb\":\"false\",\"selt\":\"true\"}]'),
(63, 42, 58, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 100, 52000, 57000, b'0', '', 530000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"true\",\"selt\":\"true\",\"type\":\"کاسپین\"},{\"id\":2,\"name\":\"روغن دنده اتوماتیک\",\"selb\":\"true\",\"selt\":\"true\",\"type\":\"\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"true\",\"selt\":\"true\",\"type\":\"\"},{\"id\":4,\"name\":\"روغن هیدرولیک\",\"selb\":\"true\",\"selt\":\"true\",\"type\":\"\"}]'),
(64, 42, 58, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 100, 520000, 525000, b'0', '', 250000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"false\",\"selt\":\"true\",\"type\":\"\"},{\"id\":2,\"name\":\"روغن دنده اتوماتیک\",\"selb\":\"true\",\"selt\":\"false\",\"type\":\"ترن\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"false\",\"selt\":\"true\",\"type\":\"\"},{\"id\":4,\"name\":\"روغن هیدرولیک\",\"selb\":\"true\",\"selt\":\"false\",\"type\":\"ترن\"}]'),
(65, 42, 52, '2022-10-18 00:00:00', 0, 0, 0, b'0', b'0', 200, 520000, 525000, b'0', '', 520000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"false\",\"selt\":\"true\",\"type\":\"\",\"value\":\"\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"false\",\"selt\":\"true\",\"type\":\"\",\"value\":\"\"},{\"id\":4,\"name\":\"روغن هیدرولیک\",\"selb\":\"true\",\"selt\":\"false\",\"type\":\"ترن\",\"value\":\"2.5 لیتر\"}]'),
(66, 42, 60, '2022-10-19 00:00:00', 0, 0, 0, b'0', b'0', 55, 195000, 200000, b'0', '', 5350000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"true\",\"selt\":\"false\",\"type\":\"اسکار ۱۰ ۴۰\",\"value\":\"۴\\n\"},{\"id\":3,\"name\":\"روغن دنده دستی\",\"selb\":\"false\",\"selt\":\"true\",\"type\":\"\",\"value\":\"\"}]'),
(67, 44, 61, '2022-11-02 00:00:00', 0, 0, 0, b'0', b'0', 30, 240000, 245000, b'0', 'پروتک', 5000000, '[{\"id\":1,\"name\":\"روغن موتور\",\"selb\":\"true\",\"selt\":\"true\",\"type\":\"پروتک\",\"value\":\"۴\"}]');

-- --------------------------------------------------------

--
-- Stand-in structure for view `service_records_cars`
-- (See below for the actual view)
--
CREATE TABLE `service_records_cars` (
`user_id` int(255)
,`name` text
,`lastname` text
,`phone` varchar(255)
,`car_name` text
,`car_tag` varchar(9)
,`km_now` bigint(255)
,`service_id` int(11)
,`d_id` int(11)
,`km_next` bigint(255)
,`date_time` datetime
);

-- --------------------------------------------------------

--
-- Table structure for table `service_timing`
--

CREATE TABLE `service_timing` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `sub_group_id` int(11) NOT NULL,
  `service_date` date NOT NULL,
  `due_date` date NOT NULL,
  `defenitive_date` date NOT NULL,
  `km_next` bigint(255) NOT NULL,
  `avg_function` bigint(20) NOT NULL,
  `num_reminder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

CREATE TABLE `slider` (
  `id` int(11) UNSIGNED NOT NULL,
  `title` text COLLATE utf8_persian_ci,
  `image` text COLLATE utf8_persian_ci,
  `url` text COLLATE utf8_persian_ci NOT NULL,
  `dir` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`id`, `title`, `image`, `url`, `dir`) VALUES
(5, '1', 'https://baadbaanapp.ir/public/img/slider/slider1.png', '1', 'public/img/slider/slider1.png'),
(6, '2', 'https://baadbaanapp.ir/public/img/slider/slider2.png', '2', 'public/img/slider/slider2.png'),
(7, '3', 'https://baadbaanapp.ir/public/img/slider/slider3.png', '3', 'public/img/slider/slider3.png'),
(8, '4', 'https://baadbaanapp.ir/public/img/slider/slider4.png', '4', 'public/img/slider/slider4.png');

-- --------------------------------------------------------

--
-- Table structure for table `sms_draft`
--

CREATE TABLE `sms_draft` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `sms_type_id` int(10) NOT NULL,
  `message` text COLLATE utf8_persian_ci NOT NULL,
  `count` int(10) NOT NULL,
  `type` int(10) NOT NULL,
  `d_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_draft`
--

INSERT INTO `sms_draft` (`id`, `title`, `sms_type_id`, `message`, `count`, `type`, `d_id`) VALUES
(6, 'wellcome', 120, 'wellcome my shop', 16, 1, 8),
(9, 'wellcome', 120, 'wellcome my shop mr', 19, 0, 10),
(10, 'تست', 120, 'پیام تستی', 9, 1, 10),
(14, 'wellcome سلام', 120, 'wellcome my shop mr', 19, 1, 10),
(15, 'تست', 120, 'پیام تستی', 9, 1, 10),
(16, 'تبریک روز تولد', 120, 'روزتون مبارک', 12, 1, 41),
(17, 'تخفیف', 120, 'تخفیف 50 درصد', 13, 1, 41),
(19, 'تست تست', 120, 'این متن تستی است', 16, 1, 10),
(20, 'للراوو', 120, '۸ع خبع۸ره۹رحعف۷ حل', 18, 1, 42),
(21, 'تست جدید ', 120, 'تست جدید', 8, 1, 10),
(22, 'دغرفرفرفرفغل', 120, 'تست تست تستا ارغررغرغررغررغغر', 29, 1, 10),
(23, 'تست جدید جدیدتر', 120, 'تست جدید در این حالت', 20, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `sms_draft_primary`
--

CREATE TABLE `sms_draft_primary` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `lastname` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `car` varchar(250) COLLATE utf8_persian_ci NOT NULL,
  `plate` int(11) NOT NULL,
  `kilometer` bigint(20) NOT NULL,
  `next_kilometer` bigint(20) NOT NULL,
  `product_group` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `factor_price` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_draft_primary`
--

INSERT INTO `sms_draft_primary` (`id`, `name`, `lastname`, `car`, `plate`, `kilometer`, `next_kilometer`, `product_group`, `gender`, `factor_price`) VALUES
(1, 'علی', 'احمدی', 'بنز', 1, 2500, 3000, 'oil', 0, 250000),
(4, 'xx', 'x', 'نیسان', 200, 1250, 2550, '6', 0, 300000);

-- --------------------------------------------------------

--
-- Table structure for table `sms_package`
--

CREATE TABLE `sms_package` (
  `sms_id` int(11) UNSIGNED NOT NULL,
  `name` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `price` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `discount` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_package`
--

INSERT INTO `sms_package` (`sms_id`, `name`, `price`, `count`, `discount`) VALUES
(1, 'پکیج پیامکی طلایی', 100000, 500, 5000),
(2, 'پکیج پیامکی اولیه', 10000, 50, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `sms_prices`
--

CREATE TABLE `sms_prices` (
  `id` int(11) NOT NULL,
  `en_price` bigint(200) NOT NULL,
  `fa_price` bigint(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_prices`
--

INSERT INTO `sms_prices` (`id`, `en_price`, `fa_price`) VALUES
(1, 210, 120);

-- --------------------------------------------------------

--
-- Table structure for table `sms_purchase_history`
--

CREATE TABLE `sms_purchase_history` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `purchase_date` datetime NOT NULL,
  `price` bigint(20) NOT NULL,
  `status` bit(1) NOT NULL,
  `sms_id` int(11) NOT NULL,
  `code` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_purchase_history`
--

INSERT INTO `sms_purchase_history` (`id`, `d_id`, `purchase_date`, `price`, `status`, `sms_id`, `code`) VALUES
(1, 10, '2022-04-05 00:00:00', 10000, b'1', 1, 'x2ds3dc44'),
(2, 8, '2022-04-05 00:00:00', 10000, b'1', 1, 'e3r43d4'),
(16, 10, '2022-04-05 22:24:03', 10000, b'1', 1, '33333'),
(17, 10, '2022-04-05 22:26:03', 20000, b'1', 1, '1655635842'),
(18, 10, '2022-04-05 22:26:21', 10000, b'1', 1, '1606148088'),
(19, 8, '2022-04-05 22:29:55', 10000, b'1', 1, 'f8f779fa4b4238a51e281f415bdf9524145e2e12'),
(20, 10, '2022-04-13 19:03:23', 265000, b'0', 0, '24a5963812d2018a53015fbb7f44e999205c5e77'),
(21, 10, '2022-04-13 19:07:02', 500, b'1', 1, '4c765284d21491de2ba45ce2a5da2acce25a3575'),
(22, 7, '2022-04-13 19:08:48', 500, b'1', 1, '6cc69ac9d80c0930e675c7a4d4ee0247e7c4de2b');

-- --------------------------------------------------------

--
-- Stand-in structure for view `sms_purchase_history_info`
-- (See below for the actual view)
--
CREATE TABLE `sms_purchase_history_info` (
`id` int(11)
,`d_id` int(11)
,`purchase_date` datetime
,`price` bigint(20)
,`status` bit(1)
,`sms_id` int(11)
,`code` varchar(300)
,`name` text
,`lastname` text
,`phone` varchar(255)
,`package_name` varchar(300)
);

-- --------------------------------------------------------

--
-- Table structure for table `sms_sending_logs`
--

CREATE TABLE `sms_sending_logs` (
  `id` int(11) NOT NULL,
  `msg_id` int(11) NOT NULL,
  `msg_text` text COLLATE utf8_persian_ci NOT NULL,
  `d_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `recive_num` int(20) NOT NULL,
  `user_id` int(255) NOT NULL,
  `car_id` int(255) NOT NULL,
  `log_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_timing`
--

CREATE TABLE `sms_timing` (
  `id` int(11) NOT NULL,
  `reciver_num` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `draft_id` int(11) NOT NULL,
  `create_date` date NOT NULL,
  `send_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_type`
--

CREATE TABLE `sms_type` (
  `id` int(11) NOT NULL,
  `title` varchar(300) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `sms_type`
--

INSERT INTO `sms_type` (`id`, `title`) VALUES
(1, 'خوش آمد گویی'),
(2, 'سرویس'),
(3, 'یادآوری'),
(4, 'تولد'),
(5, 'مناسبت'),
(6, 'جشنواره'),
(7, 'آفر'),
(8, 'قرعه کشی');

-- --------------------------------------------------------

--
-- Table structure for table `software`
--

CREATE TABLE `software` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` date NOT NULL,
  `dn_link` text COLLATE utf8_persian_ci NOT NULL,
  `dir` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `software`
--

INSERT INTO `software` (`id`, `version`, `date`, `dn_link`, `dir`) VALUES
(27, 1, '2022-04-01', 'www/ver1.com', 'dir'),
(28, 2, '2022-04-05', 'hhhhhhhhhhhhh', ''),
(29, 3, '2022-04-13', 'http://', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `sex` text COLLATE utf8_persian_ci NOT NULL,
  `birth_date` date NOT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `register_date` date NOT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `user_is_change` bit(1) NOT NULL DEFAULT b'0',
  `status` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_persian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `d_id`, `name`, `lastname`, `sex`, `birth_date`, `phone`, `register_date`, `is_delete`, `user_is_change`, `status`, `username`, `password`) VALUES
(1, 1, 'ehsan', 'rst', 'مرد', '2022-02-16', '09185081379', '2022-01-18', b'0', b'0', 0, 'user', '123'),
(2, 8, 'بارمان', 'شکوهی', 'مرد', '2021-11-09', '09304297421', '2022-01-11', b'0', b'0', 0, '', ''),
(28, 9, 'عرفان', 'سقاباشی', 'مرد', '1375-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(30, 1, 'عرفان دو', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(31, 8, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(33, 9, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(35, 1, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(37, 8, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(39, 9, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(40, 10, 'عرفان', 'سقاباشی', 'مرد', '1375-04-01', '09398116292', '2022-09-28', b'0', b'0', 0, NULL, NULL),
(41, 0, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'1', 1, NULL, NULL),
(42, 1, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(44, 8, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(45, 10, 'رضا', 'حامدی', 'مرد', '1360-01-01', '09017736292', '2022-09-25', b'0', b'0', 0, '', ''),
(46, 10, 'فراز', 'فریدنیا', 'زن', '1378-01-01', '09014225332', '2022-09-25', b'0', b'0', 0, '', ''),
(47, 9, 'حسین', 'حسینی', 'مرد', '1385-04-01', '09398112222', '2022-01-04', b'0', b'0', 0, NULL, NULL),
(48, 9, 'جعفر', 'جعفری', 'مرد', '1385-06-01', '09395512222', '2022-04-15', b'0', b'0', 0, NULL, NULL),
(49, 0, 'عرفان', 'سقاباشی', 'مرد', '1375-04-01', '09398116292', '2022-05-29', b'0', b'0', 0, NULL, NULL),
(50, 10, 'رضا', 'حسینی', 'زن', '1345-01-01', '09017736292', '2022-09-26', b'0', b'0', 0, '', ''),
(52, 10, 'رضا', 'صیادی', 'زن', '1360-01-01', '09398116292', '2022-09-29', b'0', b'0', 0, '', ''),
(53, 42, 'محمود', 'همدانی', 'مرد', '1370-01-16', '09035223062', '2022-06-02', b'0', b'0', 0, '', ''),
(54, 41, 'عرفان', 'مرادی', 'مرد', '1370-01-01', '09398116292', '2022-06-02', b'0', b'0', 0, '', ''),
(55, 41, 'رضا', 'سیاهی', 'مرد', '1370-01-01', '09398112152', '2022-06-02', b'0', b'0', 0, '', ''),
(56, 41, 'مینا', 'احمدی', 'مرد', '1370-01-01', '09025523669', '2022-06-02', b'0', b'0', 0, '', ''),
(57, 41, 'محمد ', 'جفت', 'مرد', '1370-01-01', '09014421212', '2022-06-02', b'0', b'0', 0, '', ''),
(58, 42, 'وحید', 'خدارحمی', 'مرد', '1373-03-24', '09363555936', '2022-06-06', b'0', b'0', 0, '', ''),
(59, 42, 'حاتم', 'سمایی', 'مرد', '1366-01-04', '09189189189', '2022-09-13', b'0', b'0', 0, '', ''),
(60, 10, 'عرفان ', 'سقف ', 'مرد', '1355-01-01', '09398116292', '2022-10-01', b'0', b'0', 0, '', ''),
(61, 10, 'عرفان ', 'رحیمی ', 'مرد', '1360-01-01', '09398116292', '2022-10-01', b'0', b'0', 0, '', ''),
(62, 10, 'عرفان ', 'رحیمی ', 'مرد', '1384-01-01', '09398116292', '2022-09-30', b'0', b'0', 0, '', ''),
(63, 10, 'عرفان ', 'رحیمی ', 'مرد', '1383-01-01', '09398116292', '2022-09-27', b'0', b'0', 0, '', ''),
(64, 10, 'عرفان ', 'رحیمی ', 'مرد', '1380-01-01', '09398116292', '2022-09-28', b'0', b'0', 0, '', ''),
(65, 42, 'محمود', 'محمدی', 'مرد', '1370-01-01', '09184455525', '2022-10-16', b'0', b'0', 0, '', ''),
(66, 42, 'هاهذه', 'ذهذهر', 'زن', '1370-01-01', '8683883868538838383', '2022-10-18', b'1', b'1', 0, '', ''),
(67, 42, 'رضا', 'آریان ', 'مرد', '1369-10-28', '09125033793', '2022-10-19', b'0', b'0', 0, '', ''),
(68, 44, 'آرش ', 'پورقاسمیان', 'مرد', '1364-11-20', '09372960208', '2022-11-02', b'0', b'0', 0, '', '');

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_cars`
-- (See below for the actual view)
--
CREATE TABLE `users_cars` (
`user_id` int(255)
,`name` text
,`d_id` int(11)
,`lastname` text
,`sex` text
,`phone` varchar(255)
,`birth_date` date
,`register_date` date
,`user_is_change` bit(1)
,`is_delete` bit(1)
,`status` int(11)
,`car_name` text
,`car_tag` varchar(9)
,`car_model` text
,`car_type` text
,`fuel_type` text
,`avg_function` bigint(20)
,`chassis_num` text
,`engine_cap` int(255)
,`last_date_of_cheak_service_timing` date
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_get_service`
-- (See below for the actual view)
--
CREATE TABLE `users_get_service` (
`user_id` int(255)
,`name` text
,`lastname` text
,`phone` varchar(255)
,`sex` text
,`birth_date` date
,`car_name` text
,`car_tag` varchar(9)
,`service_id` int(11)
,`d_id` int(11)
,`car_id` int(11)
,`date_time` datetime
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_sex`
-- (See below for the actual view)
--
CREATE TABLE `users_sex` (
`COUNT(sex)` bigint(21)
,`sex` text
,`d_id` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `user_info`
-- (See below for the actual view)
--
CREATE TABLE `user_info` (
`user_id` int(255)
,`name` text
,`d_id` int(11)
,`lastname` text
,`sex` text
,`phone` varchar(255)
,`birth_date` date
,`register_date` date
,`user_is_change` bit(1)
,`is_delete` bit(1)
,`status` int(11)
,`car_name` text
,`car_tag` varchar(9)
,`car_model` text
,`car_type` text
,`fuel_type` text
,`avg_function` bigint(20)
,`chassis_num` text
,`engine_cap` int(255)
,`last_date_of_cheak_service_timing` date
);

-- --------------------------------------------------------

--
-- Table structure for table `validate_phone`
--

CREATE TABLE `validate_phone` (
  `id` int(11) UNSIGNED NOT NULL,
  `phone` text COLLATE utf8_persian_ci NOT NULL,
  `code` int(6) NOT NULL,
  `status` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `validate_phone`
--

INSERT INTO `validate_phone` (`id`, `phone`, `code`, `status`) VALUES
(1, '09398116292', 418315, '352695003'),
(2, '09398116292', 172281, '352695215');

-- --------------------------------------------------------

--
-- Structure for view `all_users`
--
DROP TABLE IF EXISTS `all_users`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `all_users`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`d_id` AS `d_id`, `users`.`lastname` AS `lastname`, `users`.`sex` AS `sex`, `users`.`phone` AS `phone`, `users`.`birth_date` AS `birth_date`, `users`.`register_date` AS `register_date`, `users`.`user_is_change` AS `user_is_change`, `users`.`is_delete` AS `is_delete`, `users`.`status` AS `status`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `cars`.`car_model` AS `car_model`, `cars`.`car_type` AS `car_type`, `cars`.`fuel_type` AS `fuel_type`, `cars`.`avg_function` AS `avg_function`, `cars`.`chassis_num` AS `chassis_num`, `cars`.`engine_cap` AS `engine_cap`, `cars`.`last_date_of_cheak_service_timing` AS `last_date_of_cheak_service_timing` FROM (`users` join `cars` on((`users`.`user_id` = `cars`.`user_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `car_info`
--
DROP TABLE IF EXISTS `car_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `car_info`  AS SELECT `car_info_detail`.`id` AS `id`, `car_info_detail`.`title` AS `title`, `car_info_detail`.`job_category` AS `job_category`, `car_info_detail`.`description` AS `description`, `car_info_detail`.`image` AS `image`, `job_category`.`job_id` AS `job_id`, `job_category`.`name` AS `name` FROM (`car_info_detail` join `job_category` on((`car_info_detail`.`job_category` = `job_category`.`job_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `msg_log_details`
--
DROP TABLE IF EXISTS `msg_log_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `msg_log_details`  AS SELECT `msg_log`.`id` AS `id`, `msg_log`.`service_center_id` AS `service_center_id`, `msg_log`.`user_id` AS `user_id`, `msg_log`.`content` AS `content`, `msg_log`.`create_at` AS `create_at`, `msg_log`.`send_at` AS `send_at`, `msg_log`.`char_count` AS `char_count`, `msg_log`.`total_price` AS `total_price`, (select concat(`users`.`name`,' ',`users`.`lastname`) from `users` where (`users`.`user_id` = `msg_log`.`user_id`) limit 1) AS `user_fullname`, (select concat(`users`.`phone`) from `users` where (`users`.`user_id` = `msg_log`.`user_id`) limit 1) AS `user_phone` FROM `msg_log` ;

-- --------------------------------------------------------

--
-- Structure for view `services`
--
DROP TABLE IF EXISTS `services`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `services`  AS SELECT `service_records`.`service_id` AS `service_id`, `service_records`.`d_id` AS `d_id`, `cars`.`car_id` AS `car_id`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `cars`.`car_model` AS `car_model`, `cars`.`car_type` AS `car_type`, `cars`.`fuel_type` AS `fuel_type`, `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`lastname` AS `lastname`, concat(`users`.`name`,' ',`users`.`lastname`) AS `fullname`, `users`.`sex` AS `sex`, `users`.`phone` AS `phone`, `users`.`birth_date` AS `birth_date`, `users`.`register_date` AS `register_date`, `service_records`.`km_next` AS `km_next`, `service_records`.`km_now` AS `km_now`, `service_records`.`date_time` AS `date_time`, `service_records`.`avg_function` AS `avg_function`, `service_records`.`description` AS `description`, `service_records`.`price` AS `price`, `service_records`.`detail_service` AS `detail_service`, `service_records`.`is_delete` AS `is_delete`, `service_records`.`is_change` AS `is_change`, `service_records`.`is_sent_msg` AS `is_sent_msg` FROM ((`service_records` join `cars` on((`service_records`.`car_id` = `cars`.`car_id`))) join `users` on((`users`.`user_id` = (select `cars`.`user_id` from `cars` where (`cars`.`car_id` = `service_records`.`car_id`) order by `cars`.`car_id` desc limit 1)))) ;

-- --------------------------------------------------------

--
-- Structure for view `service_all_detail`
--
DROP TABLE IF EXISTS `service_all_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `service_all_detail`  AS SELECT `users`.`name` AS `name`, `users`.`lastname` AS `lastname`, `users`.`sex` AS `sex`, `users`.`birth_date` AS `birth_date`, `users`.`phone` AS `phone`, `users`.`user_is_change` AS `user_is_change`, `cars`.`user_id` AS `user_id`, `cars`.`car_tag` AS `car_tag`, `cars`.`car_name` AS `car_name`, `cars`.`car_model` AS `car_model`, `cars`.`car_type` AS `car_type`, `cars`.`fuel_type` AS `fuel_type`, `cars`.`avg_function` AS `avg_function`, `cars`.`chassis_num` AS `chassis_num`, `cars`.`engine_cap` AS `engine_cap`, `cars`.`register_date` AS `register_date`, `cars`.`is_delete` AS `is_delete`, `cars`.`last_date_of_cheak_service_timing` AS `last_date_of_cheak_service_timing`, `operators`.`b_date` AS `b_date`, `operators`.`shop_name` AS `shop_name`, `operators`.`shop_phone` AS `shop_phone`, `operators`.`address` AS `address`, `operators`.`province` AS `province`, `operators`.`city` AS `city`, `operators`.`job_category` AS `job_category`, `operators`.`opentime` AS `opentime`, `operators`.`closetime` AS `closetime`, `operators`.`num_branch` AS `num_branch`, `operators`.`waiting_space` AS `waiting_space`, `operators`.`catering_service` AS `catering_service`, `operators`.`serial` AS `serial`, `operators`.`username` AS `username`, `operators`.`password` AS `password`, `operators`.`email` AS `email`, `service_records`.`service_id` AS `service_id`, `service_records`.`d_id` AS `d_id`, `service_records`.`car_id` AS `car_id`, `service_records`.`date_time` AS `date_time`, `service_records`.`discount_percent` AS `discount_percent`, `service_records`.`discount_quantity` AS `discount_quantity`, `service_records`.`remaining` AS `remaining`, `service_records`.`is_change` AS `is_change`, `service_records`.`km_now` AS `km_now`, `service_records`.`km_next` AS `km_next`, `service_records`.`is_sent_msg` AS `is_sent_msg`, `service_records`.`description` AS `description`, `service_records`.`price` AS `price` FROM (((`service_records` join `cars` on((`service_records`.`car_id` = `cars`.`car_id`))) join `users` on((`cars`.`user_id` = `users`.`user_id`))) join `operators` on((`service_records`.`d_id` = `operators`.`d_id`))) WHERE ((`service_records`.`is_delete` = 0) AND (`cars`.`is_delete` = 0)) ;

-- --------------------------------------------------------

--
-- Structure for view `service_info_detail`
--
DROP TABLE IF EXISTS `service_info_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `service_info_detail`  AS SELECT `service_detail`.`id` AS `id`, `service_detail`.`id_service` AS `id_service`, `service_detail`.`sub_group_id` AS `sub_group_id`, `service_detail`.`date_time_detail` AS `date_time_detail`, `service_detail`.`service_name` AS `service_name`, `service_detail`.`have_visit` AS `have_visit`, `service_detail`.`have_change` AS `have_change`, `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`lastname` AS `lastname`, `users`.`sex` AS `sex`, `users`.`birth_date` AS `birth_date`, `users`.`phone` AS `phone`, `users`.`register_date` AS `register_date`, `users`.`user_is_change` AS `user_is_change`, `users`.`status` AS `status`, `users`.`username` AS `username`, `users`.`password` AS `password`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `service_records`.`service_id` AS `service_id`, `service_records`.`d_id` AS `d_id`, `service_records`.`car_id` AS `car_id`, `service_records`.`date_time` AS `date_time`, `service_records`.`discount_percent` AS `discount_percent`, `service_records`.`discount_quantity` AS `discount_quantity`, `service_records`.`remaining` AS `remaining`, `service_records`.`is_change` AS `is_change`, `service_records`.`is_delete` AS `is_delete`, `service_records`.`avg_function` AS `avg_function`, `service_records`.`km_now` AS `km_now`, `service_records`.`km_next` AS `km_next`, `service_records`.`is_sent_msg` AS `is_sent_msg`, `service_records`.`description` AS `description`, `service_records`.`price` AS `price` FROM (((`service_detail` join `service_records` on(((`service_records`.`service_id` = `service_detail`.`id_service`) and (`service_records`.`is_delete` = 0)))) join `cars` on((`cars`.`car_id` = `service_records`.`car_id`))) join `users` on((`cars`.`user_id` = `users`.`user_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `service_records_cars`
--
DROP TABLE IF EXISTS `service_records_cars`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `service_records_cars`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`lastname` AS `lastname`, `users`.`phone` AS `phone`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `service_records`.`km_now` AS `km_now`, `service_records`.`service_id` AS `service_id`, `service_records`.`d_id` AS `d_id`, `service_records`.`km_next` AS `km_next`, `service_records`.`date_time` AS `date_time` FROM ((`service_records` join `cars` on(((`service_records`.`is_delete` = 0) and (`service_records`.`car_id` = `cars`.`car_id`) and (`cars`.`is_delete` = 0)))) join `users` on(((`cars`.`user_id` = `users`.`user_id`) and (`users`.`is_delete` = 0)))) ;

-- --------------------------------------------------------

--
-- Structure for view `sms_purchase_history_info`
--
DROP TABLE IF EXISTS `sms_purchase_history_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `sms_purchase_history_info`  AS SELECT `sms_purchase_history`.`id` AS `id`, `sms_purchase_history`.`d_id` AS `d_id`, `sms_purchase_history`.`purchase_date` AS `purchase_date`, `sms_purchase_history`.`price` AS `price`, `sms_purchase_history`.`status` AS `status`, `sms_purchase_history`.`sms_id` AS `sms_id`, `sms_purchase_history`.`code` AS `code`, `operators`.`name` AS `name`, `operators`.`lastname` AS `lastname`, `operators`.`phone` AS `phone`, `sms_package`.`name` AS `package_name` FROM ((`sms_purchase_history` join `operators` on((`operators`.`d_id` = `sms_purchase_history`.`d_id`))) join `sms_package` on((`sms_package`.`sms_id` = `sms_purchase_history`.`sms_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `users_cars`
--
DROP TABLE IF EXISTS `users_cars`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `users_cars`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`d_id` AS `d_id`, `users`.`lastname` AS `lastname`, `users`.`sex` AS `sex`, `users`.`phone` AS `phone`, `users`.`birth_date` AS `birth_date`, `users`.`register_date` AS `register_date`, `users`.`user_is_change` AS `user_is_change`, `users`.`is_delete` AS `is_delete`, `users`.`status` AS `status`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `cars`.`car_model` AS `car_model`, `cars`.`car_type` AS `car_type`, `cars`.`fuel_type` AS `fuel_type`, `cars`.`avg_function` AS `avg_function`, `cars`.`chassis_num` AS `chassis_num`, `cars`.`engine_cap` AS `engine_cap`, `cars`.`last_date_of_cheak_service_timing` AS `last_date_of_cheak_service_timing` FROM (`users` join `cars` on((`users`.`user_id` = `cars`.`user_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `users_get_service`
--
DROP TABLE IF EXISTS `users_get_service`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `users_get_service`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`lastname` AS `lastname`, `users`.`phone` AS `phone`, `users`.`sex` AS `sex`, `users`.`birth_date` AS `birth_date`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `service_records`.`service_id` AS `service_id`, `service_records`.`d_id` AS `d_id`, `service_records`.`car_id` AS `car_id`, `service_records`.`date_time` AS `date_time` FROM ((`service_records` join `cars` on(((`service_records`.`is_delete` = 0) and (`service_records`.`car_id` = `cars`.`car_id`) and (`cars`.`is_delete` = 0)))) join `users` on(((`cars`.`user_id` = `users`.`user_id`) and (`users`.`is_delete` = 0)))) GROUP BY `users`.`user_id` ;

-- --------------------------------------------------------

--
-- Structure for view `users_sex`
--
DROP TABLE IF EXISTS `users_sex`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `users_sex`  AS SELECT count(`users`.`sex`) AS `COUNT(sex)`, `users`.`sex` AS `sex`, `users`.`d_id` AS `d_id` FROM `users` GROUP BY `users`.`sex` ;

-- --------------------------------------------------------

--
-- Structure for view `user_info`
--
DROP TABLE IF EXISTS `user_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `user_info`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`name` AS `name`, `users`.`d_id` AS `d_id`, `users`.`lastname` AS `lastname`, `users`.`sex` AS `sex`, `users`.`phone` AS `phone`, `users`.`birth_date` AS `birth_date`, `users`.`register_date` AS `register_date`, `users`.`user_is_change` AS `user_is_change`, `users`.`is_delete` AS `is_delete`, `users`.`status` AS `status`, `cars`.`car_name` AS `car_name`, `cars`.`car_tag` AS `car_tag`, `cars`.`car_model` AS `car_model`, `cars`.`car_type` AS `car_type`, `cars`.`fuel_type` AS `fuel_type`, `cars`.`avg_function` AS `avg_function`, `cars`.`chassis_num` AS `chassis_num`, `cars`.`engine_cap` AS `engine_cap`, `cars`.`last_date_of_cheak_service_timing` AS `last_date_of_cheak_service_timing` FROM (`users` join `cars` on(((`users`.`user_id` = `cars`.`user_id`) and (`users`.`is_delete` = 0) and (`users`.`status` = 1) and (`cars`.`is_delete` = 0)))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `agency`
--
ALTER TABLE `agency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`car_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `cars_company`
--
ALTER TABLE `cars_company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars_model`
--
ALTER TABLE `cars_model`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars_name`
--
ALTER TABLE `cars_name`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_name_car_company_id_foreign` (`car_company_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment_post`
--
ALTER TABLE `comment_post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fileup`
--
ALTER TABLE `fileup`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `footer_about`
--
ALTER TABLE `footer_about`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `footer_newsletter`
--
ALTER TABLE `footer_newsletter`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `footer_support`
--
ALTER TABLE `footer_support`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_categories`
--
ALTER TABLE `job_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_services`
--
ALTER TABLE `job_services`
  ADD PRIMARY KEY (`id`),
  ADD KEY `job_services_job_category_id_foreign` (`job_category_id`);

--
-- Indexes for table `land_comment`
--
ALTER TABLE `land_comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_dl`
--
ALTER TABLE `land_dl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_header`
--
ALTER TABLE `land_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_option`
--
ALTER TABLE `land_option`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_price`
--
ALTER TABLE `land_price`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_price_tbl`
--
ALTER TABLE `land_price_tbl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `land_team`
--
ALTER TABLE `land_team`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `msg_log`
--
ALTER TABLE `msg_log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `msg_prov`
--
ALTER TABLE `msg_prov`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_prov_msg_title_id_foreign` (`msg_title_id`);

--
-- Indexes for table `msg_title`
--
ALTER TABLE `msg_title`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `newsletter`
--
ALTER TABLE `newsletter`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operators`
--
ALTER TABLE `operators`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `pre_operators`
--
ALTER TABLE `pre_operators`
  ADD PRIMARY KEY (`pre_id`);

--
-- Indexes for table `products_name`
--
ALTER TABLE `products_name`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_name_product_group_id_foreign` (`product_group_id`);

--
-- Indexes for table `product_groups`
--
ALTER TABLE `product_groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_groups_job_category_id_foreign` (`job_category_id`);

--
-- Indexes for table `service_available`
--
ALTER TABLE `service_available`
  ADD PRIMARY KEY (`id`),
  ADD KEY `service_available_service_center_id_foreign` (`service_center_id`),
  ADD KEY `service_available_job_service_id_foreign` (`job_service_id`);

--
-- Indexes for table `service_records`
--
ALTER TABLE `service_records`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `car_id` (`car_id`);

--
-- Indexes for table `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_draft`
--
ALTER TABLE `sms_draft`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_draft_primary`
--
ALTER TABLE `sms_draft_primary`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_package`
--
ALTER TABLE `sms_package`
  ADD PRIMARY KEY (`sms_id`);

--
-- Indexes for table `sms_prices`
--
ALTER TABLE `sms_prices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_type`
--
ALTER TABLE `sms_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `validate_phone`
--
ALTER TABLE `validate_phone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `car_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `cars_company`
--
ALTER TABLE `cars_company`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_model`
--
ALTER TABLE `cars_model`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_name`
--
ALTER TABLE `cars_name`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `comment_post`
--
ALTER TABLE `comment_post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `fileup`
--
ALTER TABLE `fileup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `footer_about`
--
ALTER TABLE `footer_about`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `footer_newsletter`
--
ALTER TABLE `footer_newsletter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `footer_support`
--
ALTER TABLE `footer_support`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `job_categories`
--
ALTER TABLE `job_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `job_services`
--
ALTER TABLE `job_services`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `land_comment`
--
ALTER TABLE `land_comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `land_dl`
--
ALTER TABLE `land_dl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `land_header`
--
ALTER TABLE `land_header`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `land_option`
--
ALTER TABLE `land_option`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `land_price`
--
ALTER TABLE `land_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `land_price_tbl`
--
ALTER TABLE `land_price_tbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `land_team`
--
ALTER TABLE `land_team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `msg_log`
--
ALTER TABLE `msg_log`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `msg_prov`
--
ALTER TABLE `msg_prov`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `msg_title`
--
ALTER TABLE `msg_title`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `newsletter`
--
ALTER TABLE `newsletter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `operators`
--
ALTER TABLE `operators`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `pre_operators`
--
ALTER TABLE `pre_operators`
  MODIFY `pre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `products_name`
--
ALTER TABLE `products_name`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `product_groups`
--
ALTER TABLE `product_groups`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `service_available`
--
ALTER TABLE `service_available`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `service_records`
--
ALTER TABLE `service_records`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `slider`
--
ALTER TABLE `slider`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `sms_draft`
--
ALTER TABLE `sms_draft`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `sms_draft_primary`
--
ALTER TABLE `sms_draft_primary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sms_package`
--
ALTER TABLE `sms_package`
  MODIFY `sms_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sms_prices`
--
ALTER TABLE `sms_prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sms_type`
--
ALTER TABLE `sms_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `validate_phone`
--
ALTER TABLE `validate_phone`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `cars_name`
--
ALTER TABLE `cars_name`
  ADD CONSTRAINT `cars_name_car_company_id_foreign` FOREIGN KEY (`car_company_id`) REFERENCES `cars_company` (`id`);

--
-- Constraints for table `job_services`
--
ALTER TABLE `job_services`
  ADD CONSTRAINT `job_services_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`);

--
-- Constraints for table `msg_prov`
--
ALTER TABLE `msg_prov`
  ADD CONSTRAINT `msg_prov_msg_title_id_foreign` FOREIGN KEY (`msg_title_id`) REFERENCES `msg_title` (`id`);

--
-- Constraints for table `products_name`
--
ALTER TABLE `products_name`
  ADD CONSTRAINT `products_name_product_group_id_foreign` FOREIGN KEY (`product_group_id`) REFERENCES `product_groups` (`id`);

--
-- Constraints for table `product_groups`
--
ALTER TABLE `product_groups`
  ADD CONSTRAINT `product_groups_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`);

--
-- Constraints for table `service_records`
--
ALTER TABLE `service_records`
  ADD CONSTRAINT `service_records_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`);
--
-- Database: `baadbaan_db2`
--
CREATE DATABASE IF NOT EXISTS `baadbaan_db2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `baadbaan_db2`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `password`) VALUES
('ehsan', 'ehsan');

-- --------------------------------------------------------

--
-- Table structure for table `auto_service`
--

CREATE TABLE `auto_service` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` text COLLATE utf8_persian_ci NOT NULL,
  `b_date` date NOT NULL,
  `sex` int(1) NOT NULL,
  `email` text COLLATE utf8_persian_ci NOT NULL,
  `username` text COLLATE utf8_persian_ci NOT NULL,
  `password` text COLLATE utf8_persian_ci NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `category` int(11) NOT NULL,
  `display_group` int(11) NOT NULL,
  `picture` text COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `car_id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `car_tag` varchar(9) COLLATE utf8_persian_ci NOT NULL,
  `car_name` text COLLATE utf8_persian_ci NOT NULL,
  `car_model` text COLLATE utf8_persian_ci,
  `car_type` text COLLATE utf8_persian_ci NOT NULL,
  `fuel_type` text COLLATE utf8_persian_ci NOT NULL,
  `avg_function` bigint(20) DEFAULT NULL,
  `chassis_num` text COLLATE utf8_persian_ci,
  `engine_cap` int(255) DEFAULT NULL,
  `register_date` date NOT NULL,
  `is_delete` int(11) NOT NULL,
  `is_change` int(11) NOT NULL DEFAULT '0',
  `last_date_of_cheak_service_timing` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`car_id`, `user_id`, `car_tag`, `car_name`, `car_model`, `car_type`, `fuel_type`, `avg_function`, `chassis_num`, `engine_cap`, `register_date`, `is_delete`, `is_change`, `last_date_of_cheak_service_timing`) VALUES
(15, 25, '1341518ق', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(18, 34, '1341518ق', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(19, 38, '1341518ق', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(20, 36, '1341518ق', 'پژو 405', NULL, '', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(21, 41, '1352118ج', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(22, 42, '1225310ق', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(23, 43, '1225310ق', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(24, 44, '1225310ق', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(25, 45, '1341512ق', '۲۰۶ SD', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(26, 46, '1245236ق', 'برلیانس', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-02-01', 0, 0, NULL),
(27, 47, '4778718ط', '۲۰۶', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-03-08', 0, 0, NULL),
(28, 48, '1111111ص', 'پورشه', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-03-19', 0, 0, NULL),
(29, 49, '3333333ه', 'نیسان', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-03-24', 0, 0, NULL),
(30, 50, '4444444س', 'پژو', NULL, 'سواری', 'بنزینی', NULL, NULL, NULL, '2022-03-25', 0, 0, NULL),
(31, 51, '5555555ل', 'ذذ', NULL, 'سواری', 'دیزلی', NULL, NULL, NULL, '2022-03-25', 0, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `car_info_detail`
--

CREATE TABLE `car_info_detail` (
  `id` int(255) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `image` varchar(10000) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `car_info_detail`
--

INSERT INTO `car_info_detail` (`id`, `title`, `job_category`, `description`, `image`) VALUES
(19, 'oil filter', '1', '<p>text</p>', '2.png');

-- --------------------------------------------------------

--
-- Table structure for table `car_list`
--

CREATE TABLE `car_list` (
  `user_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `car_name`
--

CREATE TABLE `car_name` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `car_tec_info`
--

CREATE TABLE `car_tec_info` (
  `id` int(255) NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `car_name` text COLLATE utf8_persian_ci NOT NULL,
  `car_model` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `car_tec_info`
--

INSERT INTO `car_tec_info` (`id`, `job_category`, `car_name`, `car_model`) VALUES
(19, '1', '206', '95');

-- --------------------------------------------------------

--
-- Table structure for table `customer_list`
--

CREATE TABLE `customer_list` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `customer_list`
--

INSERT INTO `customer_list` (`id`, `d_id`, `user_id`, `date`) VALUES
(3, 1, 25, '2022-02-01'),
(4, 1, 26, '2022-02-01'),
(5, 1, 27, '2022-02-01'),
(6, 10, 34, '2022-02-01'),
(7, 10, 36, '2022-02-01'),
(9, 10, 40, '2022-02-01'),
(16, 10, 46, '2022-02-01'),
(17, 16, 47, '2022-03-08'),
(18, 25, 48, '2022-03-19'),
(19, 25, 49, '2022-03-24'),
(20, 25, 50, '2022-03-25'),
(21, 25, 51, '2022-03-25');

-- --------------------------------------------------------

--
-- Table structure for table `draft_msg`
--

CREATE TABLE `draft_msg` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_change` bit(1) NOT NULL,
  `show_in_public_sms` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `image` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `image`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `invite`
--

CREATE TABLE `invite` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `reciver` text COLLATE utf8_persian_ci NOT NULL,
  `invite_code` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_category`
--

CREATE TABLE `job_category` (
  `job_id` int(11) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `job_category`
--

INSERT INTO `job_category` (`job_id`, `name`) VALUES
(1, 'تعویض روغنی'),
(2, 'مکانیکی'),
(3, 'جلوبندی سازی'),
(4, 'باتری سازی'),
(5, 'صافکاری نقاشی');

-- --------------------------------------------------------

--
-- Table structure for table `list_service`
--

CREATE TABLE `list_service` (
  `id` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(15) COLLATE utf8_persian_ci NOT NULL,
  `price` int(11) NOT NULL,
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `picture` text COLLATE utf8_persian_ci NOT NULL,
  `serial_code` varchar(6) COLLATE utf8_persian_ci NOT NULL,
  `is_counting` int(1) DEFAULT NULL,
  `dn_link` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `name`, `price`, `description`, `picture`, `serial_code`, `is_counting`, `dn_link`) VALUES
(1, '1', 1, '<p>11</p>', 'public/img/module/Sketchpad.png', '1', 0, 'https://baadbaanapp.ir/public/img/module/Sketchpad.png');

-- --------------------------------------------------------

--
-- Table structure for table `module_factor`
--

CREATE TABLE `module_factor` (
  `id` int(11) NOT NULL,
  `factor_num` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `status` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `total_price` float NOT NULL,
  `discount` float NOT NULL,
  `remaining` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci NOT NULL,
  `category` int(11) NOT NULL,
  `text` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `title`, `category`, `text`) VALUES
(1, 'TEST', 1, '<p>TEST</p>');

-- --------------------------------------------------------

--
-- Table structure for table `operators`
--

CREATE TABLE `operators` (
  `d_id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `b_date` date NOT NULL,
  `shop_name` text COLLATE utf8_persian_ci NOT NULL,
  `shop_phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `address` text COLLATE utf8_persian_ci NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL,
  `city` text COLLATE utf8_persian_ci NOT NULL,
  `job_category` text COLLATE utf8_persian_ci NOT NULL,
  `opentime` int(255) DEFAULT '8',
  `closetime` int(255) DEFAULT '21',
  `num_branch` int(255) DEFAULT '1',
  `waiting_space` int(255) NOT NULL DEFAULT '0',
  `catering_service` int(255) NOT NULL DEFAULT '0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `serial` text COLLATE utf8_persian_ci,
  `username` text COLLATE utf8_persian_ci,
  `password` text COLLATE utf8_persian_ci,
  `email` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `operators`
--

INSERT INTO `operators` (`d_id`, `status`, `name`, `lastname`, `phone`, `sex`, `b_date`, `shop_name`, `shop_phone`, `address`, `province`, `city`, `job_category`, `opentime`, `closetime`, `num_branch`, `waiting_space`, `catering_service`, `is_delete`, `serial`, `username`, `password`, `email`) VALUES
(1, 0, 'ehsan', 'rst', '9185081379', 0, '0000-00-00', 'co', '426', 'hmd', '31', '2', '1', 8, 16, 1, 0, 1, b'0', '45ssw', '', '', ''),
(8, 0, 'morteza', 'bahrami', '0918', 0, '0000-00-00', 'bahramishop', '081', 'hamedannn', '1', '1', '1', 8, 21, 1, 1, 1, b'0', '45', '', '', ''),
(9, 1, 'ali', 'abedi', '0254', 1, '2022-01-27', 'shop', '0813', 'h', '31', '38', '1', 2, 22, 1, 1, 1, b'0', NULL, NULL, NULL, ''),
(10, 1, 'عرفان', 'سقاباشی', '09398116292', 0, '2020-10-20', 'آرنا', '09398116292', 'همدان', '28', '7', '0', 8, 20, 1, 0, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(14, 1, 'عرفان ', 'سقاباشی', '09017736292', 0, '2020-10-20', 'ارنا', '', 'همدان', '28', '7', '0', 8, 17, 2, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(15, 1, 'عرفان ', 'سقاباشی', '09017736292', 0, '2020-10-20', 'ارنا', '', 'همدان', '28', '7', '0', 8, 17, 2, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(16, 1, 'علی', 'احمدی', '09398116293', 1, '2020-10-20', 'اتو علی', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(17, 1, 'عرفان ', 'سقاباشی', '09017736292', 0, '2020-10-20', 'ارنا', '', 'همدان', '28', '7', '0', 8, 17, 2, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(18, 1, 'عرفان ', 'سقاباشی', '09017736292', 0, '2020-10-20', 'ارنا', '', 'همدان', '28', '7', '0', 8, 17, 2, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(19, 1, 'عرفان ', 'سقاباشی', '09017736292', 0, '2020-10-20', 'ارنا', '', 'همدان', '28', '7', '0', 8, 17, 1, 0, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(20, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '', 'همدان گلزار', '28', '7', '0', 8, 21, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(21, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '', 'همدان گلزار', '28', '7', '0', 8, 21, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(22, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '', 'همدان گلزار', '28', '7', '0', 8, 21, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(23, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '', 'همدان گلزار', '28', '7', '0', 8, 21, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(24, 1, 'محمود', 'همدانی', '09184455585', 0, '2020-10-20', 'اتوسرویس گاندو', '', 'همدان گلزار', '28', '7', '0', 8, 21, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(25, 1, 'mehdi', 'saatie', '9921315713', 0, '2020-10-20', 'اتومهدی', '', 'همدان قلیانی', '28', '7', '0', 8, 18, 1, 0, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(26, 1, 'mehdi', 'saatie', '9921315713', 0, '2020-10-20', 'اتومهدی', '', 'همدان قلیانی', '28', '7', '0', 8, 18, 1, 0, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(27, 1, 'mehdi', 'saatie', '9921315713', 0, '2020-10-20', 'اتومهدی', '', 'همدان قلیانی', '28', '7', '0', 8, 20, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(28, 1, 'mehdi', 'saatie', '9921315713', 0, '2020-10-20', 'اتومهدی', '', 'همدان قلیانی', '28', '7', '0', 8, 20, 1, 1, 0, b'0', NULL, NULL, NULL, 'example@gmail.com'),
(29, 1, 'محمد', 'احمدی', '09298116298', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(30, 1, 'محمد', 'احمدی', '09298116292', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(31, 1, 'محمد', 'احمدی', '09298116293', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com'),
(32, 1, 'محمد', 'احمدی', '09298116294', 1, '2020-10-20', 'اتو محمد', '09398116292', 'همدان', 'همدان', '7', '1', 8, 20, 1, 0, 0, b'0', '', '', '', 'example@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `operator_module`
--

CREATE TABLE `operator_module` (
  `id` int(11) NOT NULL,
  `factor_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `tracking_code` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `operator_option`
--

CREATE TABLE `operator_option` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `o_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `operator_option`
--

INSERT INTO `operator_option` (`id`, `d_id`, `o_id`) VALUES
(1, 9, 1),
(2, 9, 2),
(3, 9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `job_cat` text COLLATE utf8_persian_ci NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `km_usage` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` int(11) NOT NULL,
  `province` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `province`) VALUES
(1, 'آذربایجان شرقی'),
(2, 'آذربایجان غربی'),
(3, 'اردبیل'),
(4, 'اصفهان'),
(5, 'البرز'),
(6, 'ایلام'),
(7, 'بوشهر'),
(8, 'تهران'),
(9, 'چهارمحال و بختیاری'),
(10, 'خراسان جنوبی'),
(11, 'خراسان رضوی'),
(12, 'خراسان شمالی'),
(13, 'خوزستان'),
(14, 'زنجان'),
(15, 'سمنان'),
(16, 'سیتان و بلوچستان'),
(17, 'فارس'),
(18, 'قزوین'),
(19, 'قم'),
(20, 'کردستان'),
(21, 'کرمان'),
(22, 'کرمانشاه'),
(23, 'کهگیلویه و بویراحمد'),
(24, 'گلستان'),
(25, 'گیلان'),
(26, 'لرستان'),
(27, 'مازندران'),
(28, 'مرکزی'),
(29, 'هرمزگان'),
(30, 'همدان'),
(31, 'یزد');

-- --------------------------------------------------------

--
-- Table structure for table `province_cities`
--

CREATE TABLE `province_cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `parent` int(11) NOT NULL DEFAULT '0',
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `province_cities`
--

INSERT INTO `province_cities` (`id`, `parent`, `title`) VALUES
(1, 0, 'آذربایجان شرقی'),
(2, 0, 'آذربایجان غربی'),
(3, 0, 'اردبیل'),
(4, 0, 'اصفهان'),
(5, 0, 'البرز'),
(6, 0, 'ایلام'),
(7, 0, 'بوشهر'),
(8, 0, 'تهران'),
(9, 0, 'چهارمحال بختیاری'),
(10, 0, 'خراسان جنوبی'),
(11, 0, 'خراسان رضوی'),
(12, 0, 'خراسان شمالی'),
(13, 0, 'خوزستان'),
(14, 0, 'زنجان'),
(15, 0, 'سمنان'),
(16, 0, 'سیستان و بلوچستان'),
(17, 0, 'فارس'),
(18, 0, 'قزوین'),
(19, 0, 'قم'),
(20, 0, 'کردستان'),
(21, 0, 'کرمان'),
(22, 0, 'کرمانشاه'),
(23, 0, 'کهکیلویه و بویراحمد'),
(24, 0, 'گلستان'),
(25, 0, 'گیلان'),
(26, 0, 'لرستان'),
(27, 0, 'مازندران'),
(28, 0, 'مرکزی'),
(29, 0, 'هرمزگان'),
(30, 0, 'همدان'),
(31, 0, 'یزد'),
(32, 1, 'آذرشهر'),
(33, 1, 'تیمورلو'),
(34, 1, 'گوگان'),
(35, 1, 'ممقان'),
(36, 1, 'اسکو'),
(37, 1, 'ایلخچی'),
(38, 1, 'سهند'),
(39, 1, 'اهر'),
(40, 1, 'هوراند'),
(41, 1, 'بستان آباد'),
(42, 1, 'تیکمه داش'),
(43, 1, 'بناب'),
(44, 1, 'باسمنج'),
(45, 1, 'تبریز'),
(46, 1, 'خسروشاه'),
(47, 1, 'سردرود'),
(48, 1, 'جلفا'),
(49, 1, 'سیه رود'),
(50, 1, 'هادیشهر'),
(51, 1, 'قره آغاج'),
(52, 1, 'خمارلو'),
(53, 1, 'دوزدوزان'),
(54, 1, 'سراب'),
(55, 1, 'شربیان'),
(56, 1, 'مهربان'),
(57, 1, 'تسوج'),
(58, 1, 'خامنه'),
(59, 1, 'سیس'),
(60, 1, 'شبستر'),
(61, 1, 'شرفخانه'),
(62, 1, 'شندآباد'),
(63, 1, 'صوفیان'),
(64, 1, 'کوزه کنان'),
(65, 1, 'وایقان'),
(66, 1, 'جوان قلعه'),
(67, 1, 'عجب شیر'),
(68, 1, 'آبش احمد'),
(69, 1, 'کلیبر'),
(70, 1, 'خداجو(خراجو)'),
(71, 1, 'مراغه'),
(72, 1, 'بناب مرند'),
(73, 1, 'زنوز'),
(74, 1, 'کشکسرای'),
(75, 1, 'مرند'),
(76, 1, 'یامچی'),
(77, 1, 'لیلان'),
(78, 1, 'مبارک شهر'),
(79, 1, 'ملکان'),
(80, 1, 'آقکند'),
(81, 1, 'اچاچی'),
(82, 1, 'ترک'),
(83, 1, 'ترکمانچای'),
(84, 1, 'میانه'),
(85, 1, 'خاروانا'),
(86, 1, 'ورزقان'),
(87, 1, 'بخشایش'),
(88, 1, 'خواجه'),
(89, 1, 'زرنق'),
(90, 1, 'کلوانق'),
(91, 1, 'هریس'),
(92, 1, 'نظرکهریزی'),
(93, 1, 'هشترود'),
(94, 2, 'ارومیه'),
(95, 2, 'سرو'),
(96, 2, 'سیلوانه'),
(97, 2, 'قوشچی'),
(98, 2, 'نوشین'),
(99, 2, 'اشنویه'),
(100, 2, 'نالوس'),
(101, 2, 'بوکان'),
(102, 2, 'سیمینه'),
(103, 2, 'پلدشت'),
(104, 2, 'نازک علیا'),
(105, 2, 'پیرانشهر'),
(106, 2, 'گردکشانه'),
(107, 2, 'تکاب'),
(108, 2, 'آواجیق'),
(109, 2, 'سیه چشمه'),
(110, 2, 'قره ضیاءالدین'),
(111, 2, 'ایواوغلی'),
(112, 2, 'خوی'),
(113, 2, 'دیزج دیز'),
(114, 2, 'زرآباد'),
(115, 2, 'فیرورق'),
(116, 2, 'قطور'),
(117, 2, 'ربط'),
(118, 2, 'سردشت'),
(119, 2, 'میرآباد'),
(120, 2, 'تازه شهر'),
(121, 2, 'سلماس'),
(122, 2, 'شاهین دژ'),
(123, 2, 'کشاورز'),
(124, 2, 'محمودآباد'),
(125, 2, 'شوط'),
(126, 2, 'مرگنلر'),
(127, 2, 'بازرگان'),
(128, 2, 'ماکو'),
(129, 2, 'خلیفان'),
(130, 2, 'مهاباد'),
(131, 2, 'باروق'),
(132, 2, 'چهاربرج'),
(133, 2, 'میاندوآب'),
(134, 2, 'محمدیار'),
(135, 2, 'نقده'),
(136, 3, 'اردبیل'),
(137, 3, 'هیر'),
(138, 3, 'بیله سوار'),
(139, 3, 'جعفرآباد'),
(140, 3, 'اسلام اباد'),
(141, 3, 'اصلاندوز'),
(142, 3, 'پارس آباد'),
(143, 3, 'تازه کند'),
(144, 3, 'خلخال'),
(145, 3, 'کلور'),
(146, 3, 'هشتجین'),
(147, 3, 'سرعین'),
(148, 3, 'گیوی'),
(149, 3, 'تازه کندانگوت'),
(150, 3, 'گرمی'),
(151, 3, 'رضی'),
(152, 3, 'فخراباد'),
(153, 3, 'قصابه'),
(154, 3, 'لاهرود'),
(155, 3, 'مرادلو'),
(156, 3, 'مشگین شهر'),
(157, 3, 'آبی بیگلو'),
(158, 3, 'عنبران'),
(159, 3, 'نمین'),
(160, 3, 'کوراییم'),
(161, 3, 'نیر'),
(162, 4, 'آران وبیدگل'),
(163, 4, 'ابوزیدآباد'),
(164, 4, 'سفیدشهر'),
(165, 4, 'نوش آباد'),
(166, 4, 'اردستان'),
(167, 4, 'زواره'),
(168, 4, 'مهاباد'),
(169, 4, 'اژیه'),
(170, 4, 'اصفهان'),
(171, 4, 'بهارستان'),
(172, 4, 'تودشک'),
(173, 4, 'حسن اباد'),
(174, 4, 'زیار'),
(175, 4, 'سجزی'),
(176, 4, 'قهجاورستان'),
(177, 4, 'کوهپایه'),
(178, 4, 'محمدآباد'),
(179, 4, 'نصرآباد'),
(180, 4, 'نیک آباد'),
(181, 4, 'ورزنه'),
(182, 4, 'هرند'),
(183, 4, 'حبیب آباد'),
(184, 4, 'خورزوق'),
(185, 4, 'دستگرد'),
(186, 4, 'دولت آباد'),
(187, 4, 'سین'),
(188, 4, 'شاپورآباد'),
(189, 4, 'کمشچه'),
(190, 4, 'افوس'),
(191, 4, 'بویین ومیاندشت'),
(192, 4, 'تیران'),
(193, 4, 'رضوانشهر'),
(194, 4, 'عسگران'),
(195, 4, 'چادگان'),
(196, 4, 'رزوه'),
(197, 4, 'اصغرآباد'),
(198, 4, 'خمینی شهر'),
(199, 4, 'درچه'),
(200, 4, 'کوشک'),
(201, 4, 'خوانسار'),
(202, 4, 'جندق'),
(203, 4, 'خور'),
(204, 4, 'فرخی'),
(205, 4, 'دهاقان'),
(206, 4, 'گلشن'),
(207, 4, 'حنا'),
(208, 4, 'سمیرم'),
(209, 4, 'کمه'),
(210, 4, 'ونک'),
(211, 4, 'شاهین شهر'),
(212, 4, 'گرگاب'),
(213, 4, 'گزبرخوار'),
(214, 4, 'لای بید'),
(215, 4, 'میمه'),
(216, 4, 'وزوان'),
(217, 4, 'شهرضا'),
(218, 4, 'منظریه'),
(219, 4, 'داران'),
(220, 4, 'دامنه'),
(221, 4, 'برف انبار'),
(222, 4, 'فریدونشهر'),
(223, 4, 'ابریشم'),
(224, 4, 'ایمانشهر'),
(225, 4, 'بهاران شهر'),
(226, 4, 'پیربکران'),
(227, 4, 'زازران'),
(228, 4, 'فلاورجان'),
(229, 4, 'قهدریجان'),
(230, 4, 'کلیشادوسودرجان'),
(231, 4, 'برزک'),
(232, 4, 'جوشقان قالی'),
(233, 4, 'قمصر'),
(234, 4, 'کاشان'),
(235, 4, 'کامو و چوگان'),
(236, 4, 'مشکات'),
(237, 4, 'نیاسر'),
(238, 4, 'گلپایگان'),
(239, 4, 'گلشهر'),
(240, 4, 'گوگد'),
(241, 4, 'باغ بهادران'),
(242, 4, 'باغشاد'),
(243, 4, 'چرمهین'),
(244, 4, 'چمگردان'),
(245, 4, 'زاینده رود'),
(246, 4, 'زرین شهر'),
(247, 4, 'سده لنجان'),
(248, 4, 'فولادشهر'),
(249, 4, 'ورنامخواست'),
(250, 4, 'دیزیچه'),
(251, 4, 'زیباشهر'),
(252, 4, 'طالخونچه'),
(253, 4, 'کرکوند'),
(254, 4, 'مبارکه'),
(255, 4, 'مجلسی'),
(256, 4, 'انارک'),
(257, 4, 'بافران'),
(258, 4, 'نایین'),
(259, 4, 'جوزدان'),
(260, 4, 'دهق'),
(261, 4, 'علویجه'),
(262, 4, 'کهریزسنگ'),
(263, 4, 'گلدشت'),
(264, 4, 'نجف آباد'),
(265, 4, 'بادرود'),
(266, 4, 'خالدآباد'),
(267, 4, 'طرق رود'),
(268, 4, 'نطنز'),
(269, 5, 'اشتهارد'),
(270, 5, 'چهارباغ'),
(271, 5, 'شهرجدیدهشتگرد'),
(272, 5, 'کوهسار'),
(273, 5, 'گلسار'),
(274, 5, 'هشتگرد'),
(275, 5, 'طالقان'),
(276, 5, 'فردیس'),
(277, 5, 'مشکین دشت'),
(278, 5, 'آسارا'),
(279, 5, 'کرج'),
(280, 5, 'کمال شهر'),
(281, 5, 'گرمدره'),
(282, 5, 'ماهدشت'),
(283, 5, 'محمدشهر'),
(284, 5, 'تنکمان'),
(285, 5, 'نظرآباد'),
(286, 6, 'آبدانان'),
(287, 6, 'سراب باغ'),
(288, 6, 'مورموری'),
(289, 6, 'ایلام'),
(290, 6, 'چوار'),
(291, 6, 'ایوان'),
(292, 6, 'زرنه'),
(293, 6, 'بدره'),
(294, 6, 'آسمان آباد'),
(295, 6, 'بلاوه'),
(296, 6, 'توحید'),
(297, 6, 'سرابله'),
(298, 6, 'شباب'),
(299, 6, 'دره شهر'),
(300, 6, 'ماژین'),
(301, 6, 'پهله'),
(302, 6, 'دهلران'),
(303, 6, 'موسیان'),
(304, 6, 'میمه'),
(305, 6, 'لومار'),
(306, 6, 'ارکواز'),
(307, 6, 'دلگشا'),
(308, 6, 'مهر'),
(309, 6, 'صالح آباد'),
(310, 6, 'مهران'),
(311, 7, 'بوشهر'),
(312, 7, 'چغادک'),
(313, 7, 'خارک'),
(314, 7, 'عالی شهر'),
(315, 7, 'آباد'),
(316, 7, 'اهرم'),
(317, 7, 'دلوار'),
(318, 7, 'انارستان'),
(319, 7, 'جم'),
(320, 7, 'ریز'),
(321, 7, 'آب پخش'),
(322, 7, 'برازجان'),
(323, 7, 'بوشکان'),
(324, 7, 'تنگ ارم'),
(325, 7, 'دالکی'),
(326, 7, 'سعد آباد'),
(327, 7, 'شبانکاره'),
(328, 7, 'کلمه'),
(329, 7, 'وحدتیه'),
(330, 7, 'بادوله'),
(331, 7, 'خورموج'),
(332, 7, 'شنبه'),
(333, 7, 'کاکی'),
(334, 7, 'آبدان'),
(335, 7, 'بردخون'),
(336, 7, 'بردستان'),
(337, 7, 'بندردیر'),
(338, 7, 'دوراهک'),
(339, 7, 'امام حسن'),
(340, 7, 'بندردیلم'),
(341, 7, 'عسلویه'),
(342, 7, 'نخل تقی'),
(343, 7, 'بندرکنگان'),
(344, 7, 'بنک'),
(345, 7, 'سیراف'),
(346, 7, 'بندرریگ'),
(347, 7, 'بندرگناوه'),
(348, 8, 'احمد آباد مستوفی'),
(349, 8, 'اسلامشهر'),
(350, 8, 'چهاردانگه'),
(351, 8, 'صالحیه'),
(352, 8, 'گلستان'),
(353, 8, 'نسیم شهر'),
(354, 8, 'پاکدشت'),
(355, 8, 'شریف آباد'),
(356, 8, 'فرون اباد'),
(357, 8, 'بومهن'),
(358, 8, 'پردیس'),
(359, 8, 'پیشوا'),
(360, 8, 'تهران'),
(361, 8, 'آبسرد'),
(362, 8, 'آبعلی'),
(363, 8, 'دماوند'),
(364, 8, 'رودهن'),
(365, 8, 'کیلان'),
(366, 8, 'پرند'),
(367, 8, 'رباطکریم'),
(368, 8, 'نصیرشهر'),
(369, 8, 'باقرشهر'),
(370, 8, 'حسن آباد'),
(371, 8, 'ری'),
(372, 8, 'کهریزک'),
(373, 8, 'تجریش'),
(374, 8, 'شمشک'),
(375, 8, 'فشم'),
(376, 8, 'لواسان'),
(377, 8, 'اندیشه'),
(378, 8, 'باغستان'),
(379, 8, 'شاهدشهر'),
(380, 8, 'شهریار'),
(381, 8, 'صباشهر'),
(382, 8, 'فردوسیه'),
(383, 8, 'وحیدیه'),
(384, 8, 'ارجمند'),
(385, 8, 'فیروزکوه'),
(386, 8, 'قدس'),
(387, 8, 'قرچک'),
(388, 8, 'صفادشت'),
(389, 8, 'ملارد'),
(390, 8, 'جوادآباد'),
(391, 8, 'ورامین'),
(392, 9, 'اردل'),
(393, 9, 'دشتک'),
(394, 9, 'سرخون'),
(395, 9, 'کاج'),
(396, 9, 'بروجن'),
(397, 9, 'بلداجی'),
(398, 9, 'سفیددشت'),
(399, 9, 'فرادبنه'),
(400, 9, 'گندمان'),
(401, 9, 'نقنه'),
(402, 9, 'بن'),
(403, 9, 'وردنجان'),
(404, 9, 'سامان'),
(405, 9, 'سودجان'),
(406, 9, 'سورشجان'),
(407, 9, 'شهرکرد'),
(408, 9, 'طاقانک'),
(409, 9, 'فرخ شهر'),
(410, 9, 'کیان'),
(411, 9, 'نافچ'),
(412, 9, 'هارونی'),
(413, 9, 'هفشجان'),
(414, 9, 'باباحیدر'),
(415, 9, 'پردنجان'),
(416, 9, 'جونقان'),
(417, 9, 'چلیچه'),
(418, 9, 'فارسان'),
(419, 9, 'گوجان'),
(420, 9, 'بازفت'),
(421, 9, 'چلگرد'),
(422, 9, 'صمصامی'),
(423, 9, 'دستنا'),
(424, 9, 'شلمزار'),
(425, 9, 'گهرو'),
(426, 9, 'ناغان'),
(427, 9, 'آلونی'),
(428, 9, 'سردشت'),
(429, 9, 'لردگان'),
(430, 9, 'مال خلیفه'),
(431, 9, 'منج'),
(432, 10, 'ارسک'),
(433, 10, 'بشرویه'),
(434, 10, 'بیرجند'),
(435, 10, 'خوسف'),
(436, 10, 'محمدشهر'),
(437, 10, 'اسدیه'),
(438, 10, 'طبس مسینا'),
(439, 10, 'قهستان'),
(440, 10, 'گزیک'),
(441, 10, 'حاجی آباد'),
(442, 10, 'زهان'),
(443, 10, 'آیسک'),
(444, 10, 'سرایان'),
(445, 10, 'سه قلعه'),
(446, 10, 'سربیشه'),
(447, 10, 'مود'),
(448, 10, 'دیهوک'),
(449, 10, 'طبس'),
(450, 10, 'عشق آباد'),
(451, 10, 'اسلامیه'),
(452, 10, 'فردوس'),
(453, 10, 'آرین شهر'),
(454, 10, 'اسفدن'),
(455, 10, 'خضری دشت بیاض'),
(456, 10, 'قاین'),
(457, 10, 'نیمبلوک'),
(458, 10, 'شوسف'),
(459, 10, 'نهبندان'),
(460, 11, 'باخرز'),
(461, 11, 'بجستان'),
(462, 11, 'یونسی'),
(463, 11, 'انابد'),
(464, 11, 'بردسکن'),
(465, 11, 'شهراباد'),
(466, 11, 'شاندیز'),
(467, 11, 'طرقبه'),
(468, 11, 'تایباد'),
(469, 11, 'کاریز'),
(470, 11, 'مشهدریزه'),
(471, 11, 'احمدابادصولت'),
(472, 11, 'تربت جام'),
(473, 11, 'صالح آباد'),
(474, 11, 'نصرآباد'),
(475, 11, 'نیل شهر'),
(476, 11, 'بایک'),
(477, 11, 'تربت حیدریه'),
(478, 11, 'رباط سنگ'),
(479, 11, 'کدکن'),
(480, 11, 'جغتای'),
(481, 11, 'نقاب'),
(482, 11, 'چناران'),
(483, 11, 'گلبهار'),
(484, 11, 'گلمکان'),
(485, 11, 'خلیل آباد'),
(486, 11, 'کندر'),
(487, 11, 'خواف'),
(488, 11, 'سلامی'),
(489, 11, 'سنگان'),
(490, 11, 'قاسم آباد'),
(491, 11, 'نشتیفان'),
(492, 11, 'سلطان آباد'),
(493, 11, 'داورزن'),
(494, 11, 'چاپشلو'),
(495, 11, 'درگز'),
(496, 11, 'لطف آباد'),
(497, 11, 'نوخندان'),
(498, 11, 'جنگل'),
(499, 11, 'رشتخوار'),
(500, 11, 'دولت آباد'),
(501, 11, 'روداب'),
(502, 11, 'سبزوار'),
(503, 11, 'ششتمد'),
(504, 11, 'سرخس'),
(505, 11, 'مزدآوند'),
(506, 11, 'سفیدسنگ'),
(507, 11, 'فرهادگرد'),
(508, 11, 'فریمان'),
(509, 11, 'قلندرآباد'),
(510, 11, 'فیروزه'),
(511, 11, 'همت آباد'),
(512, 11, 'باجگیران'),
(513, 11, 'قوچان'),
(514, 11, 'ریوش'),
(515, 11, 'کاشمر'),
(516, 11, 'شهرزو'),
(517, 11, 'کلات'),
(518, 11, 'بیدخت'),
(519, 11, 'کاخک'),
(520, 11, 'گناباد'),
(521, 11, 'رضویه'),
(522, 11, 'مشهد'),
(523, 11, 'مشهد ثامن'),
(524, 11, 'ملک آباد'),
(525, 11, 'شادمهر'),
(526, 11, 'فیض آباد'),
(527, 11, 'بار'),
(528, 11, 'چکنه'),
(529, 11, 'خرو'),
(530, 11, 'درود'),
(531, 11, 'عشق آباد'),
(532, 11, 'قدمگاه'),
(533, 11, 'نیشابور'),
(534, 12, 'اسفراین'),
(535, 12, 'صفی آباد'),
(536, 12, 'بجنورد'),
(537, 12, 'چناران شهر'),
(538, 12, 'حصارگرمخان'),
(539, 12, 'جاجرم'),
(540, 12, 'سنخواست'),
(541, 12, 'شوقان'),
(542, 12, 'راز'),
(543, 12, 'زیارت'),
(544, 12, 'شیروان'),
(545, 12, 'قوشخانه'),
(546, 12, 'لوجلی'),
(547, 12, 'تیتکانلو'),
(548, 12, 'فاروج'),
(549, 12, 'ایور'),
(550, 12, 'درق'),
(551, 12, 'گرمه'),
(552, 12, 'آشخانه'),
(553, 12, 'آوا'),
(554, 12, 'پیش قلعه'),
(555, 12, 'قاضی'),
(556, 13, 'آبادان'),
(557, 13, 'اروندکنار'),
(558, 13, 'چویبده'),
(559, 13, 'آغاجاری'),
(560, 13, 'امیدیه'),
(561, 13, 'جایزان'),
(562, 13, 'آبژدان'),
(563, 13, 'قلعه خواجه'),
(564, 13, 'آزادی'),
(565, 13, 'اندیمشک'),
(566, 13, 'بیدروبه'),
(567, 13, 'چم گلک'),
(568, 13, 'حسینیه'),
(569, 13, 'الهایی'),
(570, 13, 'اهواز'),
(571, 13, 'ایذه'),
(572, 13, 'دهدز'),
(573, 13, 'باغ ملک'),
(574, 13, 'صیدون'),
(575, 13, 'قلعه تل'),
(576, 13, 'میداود'),
(577, 13, 'شیبان'),
(578, 13, 'ملاثانی'),
(579, 13, 'ویس'),
(580, 13, 'بندرامام خمینی'),
(581, 13, 'بندرماهشهر'),
(582, 13, 'چمران'),
(583, 13, 'بهبهان'),
(584, 13, 'تشان'),
(585, 13, 'سردشت'),
(586, 13, 'منصوریه'),
(587, 13, 'حمیدیه'),
(588, 13, 'خرمشهر'),
(589, 13, 'مقاومت'),
(590, 13, 'مینوشهر'),
(591, 13, 'چغامیش'),
(592, 13, 'حمزه'),
(593, 13, 'دزفول'),
(594, 13, 'سالند'),
(595, 13, 'سیاه منصور'),
(596, 13, 'شمس آباد'),
(597, 13, 'شهر امام'),
(598, 13, 'صفی آباد'),
(599, 13, 'میانرود'),
(600, 13, 'ابوحمیظه'),
(601, 13, 'بستان'),
(602, 13, 'سوسنگرد'),
(603, 13, 'کوت سیدنعیم'),
(604, 13, 'رامشیر'),
(605, 13, 'مشراگه'),
(606, 13, 'رامهرمز'),
(607, 13, 'خنافره'),
(608, 13, 'دارخوین'),
(609, 13, 'شادگان'),
(610, 13, 'الوان'),
(611, 13, 'حر'),
(612, 13, 'شاوور'),
(613, 13, 'شوش'),
(614, 13, 'فتح المبین'),
(615, 13, 'سرداران'),
(616, 13, 'شرافت'),
(617, 13, 'شوشتر'),
(618, 13, 'گوریه'),
(619, 13, 'کوت عبداله'),
(620, 13, 'ترکالکی'),
(621, 13, 'جنت مکان'),
(622, 13, 'سماله'),
(623, 13, 'صالح شهر'),
(624, 13, 'گتوند'),
(625, 13, 'لالی'),
(626, 13, 'گلگیر'),
(627, 13, 'مسجدسلیمان'),
(628, 13, 'هفتگل'),
(629, 13, 'زهره'),
(630, 13, 'هندیجان'),
(631, 13, 'رفیع'),
(632, 13, 'هویزه'),
(633, 14, 'ابهر'),
(634, 14, 'صایین قلعه'),
(635, 14, 'هیدج'),
(636, 14, 'حلب'),
(637, 14, 'زرین آباد'),
(638, 14, 'زرین رود'),
(639, 14, 'سجاس'),
(640, 14, 'سهرورد'),
(641, 14, 'قیدار'),
(642, 14, 'کرسف'),
(643, 14, 'گرماب'),
(644, 14, 'نوربهار'),
(645, 14, 'خرمدره'),
(646, 14, 'ارمغانخانه'),
(647, 14, 'زنجان'),
(648, 14, 'نیک پی'),
(649, 14, 'سلطانیه'),
(650, 14, 'آب بر'),
(651, 14, 'چورزق'),
(652, 14, 'دندی'),
(653, 14, 'ماه نشان'),
(654, 15, 'آرادان'),
(655, 15, 'کهن آباد'),
(656, 15, 'امیریه'),
(657, 15, 'دامغان'),
(658, 15, 'دیباج'),
(659, 15, 'کلاته'),
(660, 15, 'سرخه'),
(661, 15, 'سمنان'),
(662, 15, 'بسطام'),
(663, 15, 'بیارجمند'),
(664, 15, 'رودیان'),
(665, 15, 'شاهرود'),
(666, 15, 'کلاته خیج'),
(667, 15, 'مجن'),
(668, 15, 'ایوانکی'),
(669, 15, 'گرمسار'),
(670, 15, 'درجزین'),
(671, 15, 'شهمیرزاد'),
(672, 15, 'مهدی شهر'),
(673, 15, 'میامی'),
(674, 16, 'ایرانشهر'),
(675, 16, 'بزمان'),
(676, 16, 'بمپور'),
(677, 16, 'محمدان'),
(678, 16, 'چابهار'),
(679, 16, 'نگور'),
(680, 16, 'خاش'),
(681, 16, 'نوک آباد'),
(682, 16, 'گلمورتی'),
(683, 16, 'بنجار'),
(684, 16, 'زابل'),
(685, 16, 'زاهدان'),
(686, 16, 'نصرت آباد'),
(687, 16, 'زهک'),
(688, 16, 'جالق'),
(689, 16, 'سراوان'),
(690, 16, 'سیرکان'),
(691, 16, 'گشت'),
(692, 16, 'محمدی'),
(693, 16, 'پیشین'),
(694, 16, 'راسک'),
(695, 16, 'سرباز'),
(696, 16, 'سوران'),
(697, 16, 'هیدوچ'),
(698, 16, 'فنوج'),
(699, 16, 'قصرقند'),
(700, 16, 'زرآباد'),
(701, 16, 'کنارک'),
(702, 16, 'مهرستان'),
(703, 16, 'میرجاوه'),
(704, 16, 'اسپکه'),
(705, 16, 'بنت'),
(706, 16, 'نیک شهر'),
(707, 16, 'ادیمی'),
(708, 16, 'شهرک علی اکبر'),
(709, 16, 'محمدآباد'),
(710, 16, 'دوست محمد'),
(711, 17, 'آباده'),
(712, 17, 'ایزدخواست'),
(713, 17, 'بهمن'),
(714, 17, 'سورمق'),
(715, 17, 'صغاد'),
(716, 17, 'ارسنجان'),
(717, 17, 'استهبان'),
(718, 17, 'ایج'),
(719, 17, 'رونیز'),
(720, 17, 'اقلید'),
(721, 17, 'حسن اباد'),
(722, 17, 'دژکرد'),
(723, 17, 'سده'),
(724, 17, 'بوانات'),
(725, 17, 'حسامی'),
(726, 17, 'کره ای'),
(727, 17, 'مزایجان'),
(728, 17, 'سعادت شهر'),
(729, 17, 'مادرسلیمان'),
(730, 17, 'باب انار'),
(731, 17, 'جهرم'),
(732, 17, 'خاوران'),
(733, 17, 'دوزه'),
(734, 17, 'قطب آباد'),
(735, 17, 'خرامه'),
(736, 17, 'سلطان شهر'),
(737, 17, 'صفاشهر'),
(738, 17, 'قادراباد'),
(739, 17, 'خنج'),
(740, 17, 'جنت شهر'),
(741, 17, 'داراب'),
(742, 17, 'دوبرجی'),
(743, 17, 'فدامی'),
(744, 17, 'کوپن'),
(745, 17, 'مصیری'),
(746, 17, 'حاجی آباد'),
(747, 17, 'دبیران'),
(748, 17, 'شهرپیر'),
(749, 17, 'اردکان'),
(750, 17, 'بیضا'),
(751, 17, 'هماشهر'),
(752, 17, 'سروستان'),
(753, 17, 'کوهنجان'),
(754, 17, 'خانه زنیان'),
(755, 17, 'داریان'),
(756, 17, 'زرقان'),
(757, 17, 'شهرصدرا'),
(758, 17, 'شیراز'),
(759, 17, 'لپویی'),
(760, 17, 'دهرم'),
(761, 17, 'فراشبند'),
(762, 17, 'نوجین'),
(763, 17, 'زاهدشهر'),
(764, 17, 'ششده'),
(765, 17, 'فسا'),
(766, 17, 'قره بلاغ'),
(767, 17, 'میانشهر'),
(768, 17, 'نوبندگان'),
(769, 17, 'فیروزآباد'),
(770, 17, 'میمند'),
(771, 17, 'افزر'),
(772, 17, 'امام شهر'),
(773, 17, 'قیر'),
(774, 17, 'کارزین (فتح آباد)'),
(775, 17, 'مبارک آباددیز'),
(776, 17, 'بالاده'),
(777, 17, 'خشت'),
(778, 17, 'قایمیه'),
(779, 17, 'کازرون'),
(780, 17, 'کنارتخته'),
(781, 17, 'نودان'),
(782, 17, 'کوار'),
(783, 17, 'گراش'),
(784, 17, 'اوز'),
(785, 17, 'بنارویه'),
(786, 17, 'بیرم'),
(787, 17, 'جویم'),
(788, 17, 'خور'),
(789, 17, 'عمادده'),
(790, 17, 'لار'),
(791, 17, 'لطیفی'),
(792, 17, 'اشکنان'),
(793, 17, 'اهل'),
(794, 17, 'علامرودشت'),
(795, 17, 'لامرد'),
(796, 17, 'خانیمن'),
(797, 17, 'رامجرد'),
(798, 17, 'سیدان'),
(799, 17, 'کامفیروز'),
(800, 17, 'مرودشت'),
(801, 17, 'بابامنیر'),
(802, 17, 'خومه زار'),
(803, 17, 'نورآباد'),
(804, 17, 'اسیر'),
(805, 17, 'خوزی'),
(806, 17, 'گله دار'),
(807, 17, 'مهر'),
(808, 17, 'وراوی'),
(809, 17, 'آباده طشک'),
(810, 17, 'قطرویه'),
(811, 17, 'مشکان'),
(812, 17, 'نی ریز'),
(813, 18, 'آبیک'),
(814, 18, 'خاکعلی'),
(815, 18, 'آبگرم'),
(816, 18, 'آوج'),
(817, 18, 'الوند'),
(818, 18, 'بیدستان'),
(819, 18, 'شریفیه'),
(820, 18, 'محمدیه'),
(821, 18, 'ارداق'),
(822, 18, 'بویین زهرا'),
(823, 18, 'دانسفهان'),
(824, 18, 'سگزآباد'),
(825, 18, 'شال'),
(826, 18, 'اسفرورین'),
(827, 18, 'تاکستان'),
(828, 18, 'خرمدشت'),
(829, 18, 'ضیاڈآباد'),
(830, 18, 'نرجه'),
(831, 18, 'اقبالیه'),
(832, 18, 'رازمیان'),
(833, 18, 'سیردان'),
(834, 18, 'قزوین'),
(835, 18, 'کوهین'),
(836, 18, 'محمودآبادنمونه'),
(837, 18, 'معلم کلایه'),
(838, 19, 'جعفریه'),
(839, 19, 'دستجرد'),
(840, 19, 'سلفچگان'),
(841, 19, 'قم'),
(842, 19, 'قنوات'),
(843, 19, 'کهک'),
(844, 20, 'آرمرده'),
(845, 20, 'بانه'),
(846, 20, 'بویین سفلی'),
(847, 20, 'کانی سور'),
(848, 20, 'بابارشانی'),
(849, 20, 'بیجار'),
(850, 20, 'پیرتاج'),
(851, 20, 'توپ آغاج'),
(852, 20, 'یاسوکند'),
(853, 20, 'بلبان آباد'),
(854, 20, 'دهگلان'),
(855, 20, 'دیواندره'),
(856, 20, 'زرینه'),
(857, 20, 'اورامان تخت'),
(858, 20, 'سروآباد'),
(859, 20, 'سقز'),
(860, 20, 'صاحب'),
(861, 20, 'سنندج'),
(862, 20, 'شویشه'),
(863, 20, 'دزج'),
(864, 20, 'دلبران'),
(865, 20, 'سریش آباد'),
(866, 20, 'قروه'),
(867, 20, 'کامیاران'),
(868, 20, 'موچش'),
(869, 20, 'برده رشه'),
(870, 20, 'چناره'),
(871, 20, 'کانی دینار'),
(872, 20, 'مریوان'),
(873, 21, 'ارزوییه'),
(874, 21, 'امین شهر'),
(875, 21, 'انار'),
(876, 21, 'بافت'),
(877, 21, 'بزنجان'),
(878, 21, 'بردسیر'),
(879, 21, 'دشتکار'),
(880, 21, 'گلزار'),
(881, 21, 'لاله زار'),
(882, 21, 'نگار'),
(883, 21, 'بروات'),
(884, 21, 'بم'),
(885, 21, 'بلوک'),
(886, 21, 'جبالبارز'),
(887, 21, 'جیرفت'),
(888, 21, 'درب بهشت'),
(889, 21, 'رابر'),
(890, 21, 'هنزا'),
(891, 21, 'راور'),
(892, 21, 'هجدک'),
(893, 21, 'بهرمان'),
(894, 21, 'رفسنجان'),
(895, 21, 'صفاییه'),
(896, 21, 'کشکوییه'),
(897, 21, 'مس سرچشمه'),
(898, 21, 'رودبار'),
(899, 21, 'زهکلوت'),
(900, 21, 'گنبکی'),
(901, 21, 'محمدآباد'),
(902, 21, 'خانوک'),
(903, 21, 'ریحان'),
(904, 21, 'زرند'),
(905, 21, 'یزدان شهر'),
(906, 21, 'بلورد'),
(907, 21, 'پاریز'),
(908, 21, 'خواجو شهر'),
(909, 21, 'زیدآباد'),
(910, 21, 'سیرجان'),
(911, 21, 'نجف شهر'),
(912, 21, 'هماشهر'),
(913, 21, 'جوزم'),
(914, 21, 'خاتون اباد'),
(915, 21, 'خورسند'),
(916, 21, 'دهج'),
(917, 21, 'شهربابک'),
(918, 21, 'دوساری'),
(919, 21, 'عنبرآباد'),
(920, 21, 'مردهک'),
(921, 21, 'فاریاب'),
(922, 21, 'فهرج'),
(923, 21, 'قلعه گنج'),
(924, 21, 'اختیارآباد'),
(925, 21, 'اندوهجرد'),
(926, 21, 'باغین'),
(927, 21, 'جوپار'),
(928, 21, 'چترود'),
(929, 21, 'راین'),
(930, 21, 'زنگی آباد'),
(931, 21, 'شهداد'),
(932, 21, 'کاظم آباد'),
(933, 21, 'کرمان'),
(934, 21, 'گلباف'),
(935, 21, 'ماهان'),
(936, 21, 'محی آباد'),
(937, 21, 'کوهبنان'),
(938, 21, 'کیانشهر'),
(939, 21, 'کهنوج'),
(940, 21, 'منوجان'),
(941, 21, 'نودژ'),
(942, 21, 'نرماشیر'),
(943, 21, 'نظام شهر'),
(944, 22, 'اسلام آبادغرب'),
(945, 22, 'حمیل'),
(946, 22, 'بانوره'),
(947, 22, 'باینگان'),
(948, 22, 'پاوه'),
(949, 22, 'نودشه'),
(950, 22, 'نوسود'),
(951, 22, 'ازگله'),
(952, 22, 'تازه آباد'),
(953, 22, 'جوانرود'),
(954, 22, 'ریجاب'),
(955, 22, 'کرند'),
(956, 22, 'گهواره'),
(957, 22, 'روانسر'),
(958, 22, 'شاهو'),
(959, 22, 'سرپل ذهاب'),
(960, 22, 'سطر'),
(961, 22, 'سنقر'),
(962, 22, 'صحنه'),
(963, 22, 'میان راهان'),
(964, 22, 'سومار'),
(965, 22, 'قصرشیرین'),
(966, 22, 'رباط'),
(967, 22, 'کرمانشاه'),
(968, 22, 'کوزران'),
(969, 22, 'هلشی'),
(970, 22, 'کنگاور'),
(971, 22, 'گودین'),
(972, 22, 'سرمست'),
(973, 22, 'گیلانغرب'),
(974, 22, 'بیستون'),
(975, 22, 'هرسین'),
(976, 23, 'باشت'),
(977, 23, 'چیتاب'),
(978, 23, 'گراب سفلی'),
(979, 23, 'مادوان'),
(980, 23, 'مارگون'),
(981, 23, 'یاسوج'),
(982, 23, 'لیکک'),
(983, 23, 'چرام'),
(984, 23, 'سرفاریاب'),
(985, 23, 'پاتاوه'),
(986, 23, 'سی سخت'),
(987, 23, 'دهدشت'),
(988, 23, 'دیشموک'),
(989, 23, 'سوق'),
(990, 23, 'قلعه رییسی'),
(991, 23, 'دوگنبدان'),
(992, 23, 'لنده'),
(993, 24, 'آزادشهر'),
(994, 24, 'نگین شهر'),
(995, 24, 'نوده خاندوز'),
(996, 24, 'آق قلا'),
(997, 24, 'انبارآلوم'),
(998, 24, 'بندرگز'),
(999, 24, 'نوکنده'),
(1000, 24, 'بندرترکمن'),
(1001, 24, 'تاتارعلیا'),
(1002, 24, 'خان ببین'),
(1003, 24, 'دلند'),
(1004, 24, 'رامیان'),
(1005, 24, 'سنگدوین'),
(1006, 24, 'علی اباد'),
(1007, 24, 'فاضل آباد'),
(1008, 24, 'مزرعه'),
(1009, 24, 'کردکوی'),
(1010, 24, 'فراغی'),
(1011, 24, 'کلاله'),
(1012, 24, 'گالیکش'),
(1013, 24, 'جلین'),
(1014, 24, 'سرخنکلاته'),
(1015, 24, 'گرگان'),
(1016, 24, 'سیمین شهر'),
(1017, 24, 'گمیش تپه'),
(1018, 24, 'اینچه برون'),
(1019, 24, 'گنبدکاووس'),
(1020, 24, 'مراوه'),
(1021, 24, 'مینودشت'),
(1022, 25, 'آستارا'),
(1023, 25, 'لوندویل'),
(1024, 25, 'آستانه اشرفیه'),
(1025, 25, 'کیاشهر'),
(1026, 25, 'املش'),
(1027, 25, 'رانکوه'),
(1028, 25, 'بندرانزلی'),
(1029, 25, 'خشکبیجار'),
(1030, 25, 'خمام'),
(1031, 25, 'رشت'),
(1032, 25, 'سنگر'),
(1033, 25, 'کوچصفهان'),
(1034, 25, 'لشت نشاء'),
(1035, 25, 'لولمان'),
(1036, 25, 'پره سر'),
(1037, 25, 'رضوانشهر'),
(1038, 25, 'بره سر'),
(1039, 25, 'توتکابن'),
(1040, 25, 'جیرنده'),
(1041, 25, 'رستم آباد'),
(1042, 25, 'رودبار'),
(1043, 25, 'لوشان'),
(1044, 25, 'منجیل'),
(1045, 25, 'چابکسر'),
(1046, 25, 'رحیم آباد'),
(1047, 25, 'رودسر'),
(1048, 25, 'کلاچای'),
(1049, 25, 'واجارگاه'),
(1050, 25, 'دیلمان'),
(1051, 25, 'سیاهکل'),
(1052, 25, 'احمدسرگوراب'),
(1053, 25, 'شفت'),
(1054, 25, 'صومعه سرا'),
(1055, 25, 'گوراب زرمیخ'),
(1056, 25, 'مرجقل'),
(1057, 25, 'اسالم'),
(1058, 25, 'چوبر'),
(1059, 25, 'حویق'),
(1060, 25, 'لیسار'),
(1061, 25, 'هشتپر (تالش)'),
(1062, 25, 'فومن'),
(1063, 25, 'ماسوله'),
(1064, 25, 'ماکلوان'),
(1065, 25, 'رودبنه'),
(1066, 25, 'لاهیجان'),
(1067, 25, 'اطاقور'),
(1068, 25, 'چاف و چمخاله'),
(1069, 25, 'شلمان'),
(1070, 25, 'کومله'),
(1071, 25, 'لنگرود'),
(1072, 25, 'بازار جمعه'),
(1073, 25, 'ماسال'),
(1074, 26, 'ازنا'),
(1075, 26, 'مومن آباد'),
(1076, 26, 'الیگودرز'),
(1077, 26, 'شول آباد'),
(1078, 26, 'اشترینان'),
(1079, 26, 'بروجرد'),
(1080, 26, 'پلدختر'),
(1081, 26, 'معمولان'),
(1082, 26, 'بیران شهر'),
(1083, 26, 'خرم آباد'),
(1084, 26, 'زاغه'),
(1085, 26, 'سپیددشت'),
(1086, 26, 'نورآباد'),
(1087, 26, 'هفت چشمه'),
(1088, 26, 'چالانچولان'),
(1089, 26, 'دورود'),
(1090, 26, 'سراب دوره'),
(1091, 26, 'ویسیان'),
(1092, 26, 'چقابل'),
(1093, 26, 'الشتر'),
(1094, 26, 'فیروزآباد'),
(1095, 26, 'درب گنبد'),
(1096, 26, 'کوهدشت'),
(1097, 26, 'کوهنانی'),
(1098, 26, 'گراب'),
(1099, 27, 'آمل'),
(1100, 27, 'امامزاده عبدالله'),
(1101, 27, 'دابودشت'),
(1102, 27, 'رینه'),
(1103, 27, 'گزنک'),
(1104, 27, 'امیرکلا'),
(1105, 27, 'بابل'),
(1106, 27, 'خوش رودپی'),
(1107, 27, 'زرگرمحله'),
(1108, 27, 'گتاب'),
(1109, 27, 'گلوگاه'),
(1110, 27, 'مرزیکلا'),
(1111, 27, 'بابلسر'),
(1112, 27, 'بهنمیر'),
(1113, 27, 'هادی شهر'),
(1114, 27, 'بهشهر'),
(1115, 27, 'خلیل شهر'),
(1116, 27, 'رستمکلا'),
(1117, 27, 'تنکابن'),
(1118, 27, 'خرم آباد'),
(1119, 27, 'شیرود'),
(1120, 27, 'نشتارود'),
(1121, 27, 'جویبار'),
(1122, 27, 'کوهی خیل'),
(1123, 27, 'چالوس'),
(1124, 27, 'مرزن آباد'),
(1125, 27, 'هچیرود'),
(1126, 27, 'رامسر'),
(1127, 27, 'کتالم وسادات شهر'),
(1128, 27, 'پایین هولار'),
(1129, 27, 'ساری'),
(1130, 27, 'فریم'),
(1131, 27, 'کیاسر'),
(1132, 27, 'آلاشت'),
(1133, 27, 'پل سفید'),
(1134, 27, 'زیرآب'),
(1135, 27, 'شیرگاه'),
(1136, 27, 'کیاکلا'),
(1137, 27, 'سلمان شهر'),
(1138, 27, 'عباس اباد'),
(1139, 27, 'کلارآباد'),
(1140, 27, 'فریدونکنار'),
(1141, 27, 'ارطه'),
(1142, 27, 'قایم شهر'),
(1143, 27, 'کلاردشت'),
(1144, 27, 'گلوگاه'),
(1145, 27, 'سرخرود'),
(1146, 27, 'محمودآباد'),
(1147, 27, 'سورک'),
(1148, 27, 'نکا'),
(1149, 27, 'ایزدشهر'),
(1150, 27, 'بلده'),
(1151, 27, 'چمستان'),
(1152, 27, 'رویان'),
(1153, 27, 'نور'),
(1154, 27, 'پول'),
(1155, 27, 'کجور'),
(1156, 27, 'نوشهر'),
(1157, 28, 'آشتیان'),
(1158, 28, 'اراک'),
(1159, 28, 'داودآباد'),
(1160, 28, 'ساروق'),
(1161, 28, 'کارچان'),
(1162, 28, 'تفرش'),
(1163, 28, 'خمین'),
(1164, 28, 'قورچی باشی'),
(1165, 28, 'جاورسیان'),
(1166, 28, 'خنداب'),
(1167, 28, 'دلیجان'),
(1168, 28, 'نراق'),
(1169, 28, 'پرندک'),
(1170, 28, 'خشکرود'),
(1171, 28, 'رازقان'),
(1172, 28, 'زاویه'),
(1173, 28, 'مامونیه'),
(1174, 28, 'آوه'),
(1175, 28, 'ساوه'),
(1176, 28, 'غرق آباد'),
(1177, 28, 'نوبران'),
(1178, 28, 'آستانه'),
(1179, 28, 'توره'),
(1180, 28, 'شازند'),
(1181, 28, 'شهباز'),
(1182, 28, 'مهاجران'),
(1183, 28, 'هندودر'),
(1184, 28, 'خنجین'),
(1185, 28, 'فرمهین'),
(1186, 28, 'کمیجان'),
(1187, 28, 'میلاجرد'),
(1188, 28, 'محلات'),
(1189, 28, 'نیمور'),
(1190, 29, 'ابوموسی'),
(1191, 29, 'بستک'),
(1192, 29, 'جناح'),
(1193, 29, 'سردشت'),
(1194, 29, 'گوهران'),
(1195, 29, 'بندرعباس'),
(1196, 29, 'تازیان پایین'),
(1197, 29, 'تخت'),
(1198, 29, 'فین'),
(1199, 29, 'قلعه قاضی'),
(1200, 29, 'بندرلنگه'),
(1201, 29, 'چارک'),
(1202, 29, 'کنگ'),
(1203, 29, 'کیش'),
(1204, 29, 'لمزان'),
(1205, 29, 'پارسیان'),
(1206, 29, 'دشتی'),
(1207, 29, 'کوشکنار'),
(1208, 29, 'بندرجاسک'),
(1209, 29, 'حاجی اباد'),
(1210, 29, 'سرگز'),
(1211, 29, 'فارغان'),
(1212, 29, 'خمیر'),
(1213, 29, 'رویدر'),
(1214, 29, 'بیکاء'),
(1215, 29, 'دهبارز'),
(1216, 29, 'زیارتعلی'),
(1217, 29, 'سیریک'),
(1218, 29, 'کوهستک'),
(1219, 29, 'گروک'),
(1220, 29, 'درگهان'),
(1221, 29, 'سوزا'),
(1222, 29, 'قشم'),
(1223, 29, 'هرمز'),
(1224, 29, 'تیرور'),
(1225, 29, 'سندرک'),
(1226, 29, 'میناب'),
(1227, 29, 'هشتبندی'),
(1228, 30, 'آجین'),
(1229, 30, 'اسدآباد'),
(1230, 30, 'بهار'),
(1231, 30, 'صالح آباد'),
(1232, 30, 'لالجین'),
(1233, 30, 'مهاجران'),
(1234, 30, 'تویسرکان'),
(1235, 30, 'سرکان'),
(1236, 30, 'فرسفج'),
(1237, 30, 'دمق'),
(1238, 30, 'رزن'),
(1239, 30, 'قروه درجزین'),
(1240, 30, 'فامنین'),
(1241, 30, 'شیرین سو'),
(1242, 30, 'کبودرآهنگ'),
(1243, 30, 'گل تپه'),
(1244, 30, 'ازندریان'),
(1245, 30, 'جوکار'),
(1246, 30, 'زنگنه'),
(1247, 30, 'سامن'),
(1248, 30, 'ملایر'),
(1249, 30, 'برزول'),
(1250, 30, 'فیروزان'),
(1251, 30, 'گیان'),
(1252, 30, 'نهاوند'),
(1253, 30, 'جورقان'),
(1254, 30, 'قهاوند'),
(1255, 30, 'مریانج'),
(1256, 30, 'همدان'),
(1257, 31, 'ابرکوه'),
(1258, 31, 'مهردشت'),
(1259, 31, 'احمدآباد'),
(1260, 31, 'اردکان'),
(1261, 31, 'عقدا'),
(1262, 31, 'اشکذر'),
(1263, 31, 'خضرآباد'),
(1264, 31, 'بافق'),
(1265, 31, 'بهاباد'),
(1266, 31, 'تفت'),
(1267, 31, 'نیر'),
(1268, 31, 'مروست'),
(1269, 31, 'هرات'),
(1270, 31, 'مهریز'),
(1271, 31, 'بفروییه'),
(1272, 31, 'میبد'),
(1273, 31, 'ندوشن'),
(1274, 31, 'حمیدیا'),
(1275, 31, 'زارچ'),
(1276, 31, 'شاهدیه'),
(1277, 31, 'یزد');

-- --------------------------------------------------------

--
-- Table structure for table `service_detail`
--

CREATE TABLE `service_detail` (
  `id` int(11) NOT NULL,
  `id_service` int(11) NOT NULL,
  `sub_group_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `have_visit` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `have_change` varchar(255) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `service_detail`
--

INSERT INTO `service_detail` (`id`, `id_service`, `sub_group_id`, `date_time`, `name`, `have_visit`, `have_change`) VALUES
(1, 1, 25, '2021-12-07 00:00:00', 'name', '0', '1'),
(2, 1, 46, '2021-12-22 00:00:00', 'oil', '0', '1'),
(3, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(4, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(5, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(6, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(7, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(8, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(9, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(10, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(11, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(12, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(13, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(14, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(15, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(16, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(17, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(18, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(19, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(20, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(21, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(22, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(23, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(24, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(25, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(26, 1, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(27, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(28, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(29, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(30, 1, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(31, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(32, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(33, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(34, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(35, 1, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(36, 23, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(37, 23, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(38, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(39, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(40, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(41, 23, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(42, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(43, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(44, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(45, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(46, 23, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(47, 24, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(48, 24, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(49, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(50, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(51, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(52, 24, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(53, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(54, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(55, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(56, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(57, 24, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(58, 25, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(59, 25, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(60, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(61, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(62, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(63, 25, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(64, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(65, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(66, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(67, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(68, 25, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(69, 26, 0, '1400-11-12 00:00:00', 'روغن ترمز', '1', '0'),
(70, 26, 0, '1400-11-12 00:00:00', 'فیلتر روغن', '1', '0'),
(71, 26, 0, '1400-11-12 00:00:00', 'فیلتر هوا', '1', '0'),
(72, 26, 0, '1400-11-12 00:00:00', 'فیلتر کابین', '1', '0'),
(73, 26, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(74, 26, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(75, 26, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(76, 26, 0, '1400-11-12 00:00:00', 'فیلتر بنزین', '1', '0'),
(77, 27, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(78, 27, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(79, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(80, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(81, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(82, 27, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(83, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(84, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(85, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(86, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(87, 27, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(88, 28, 0, '1400-11-12 00:00:00', 'روغن دنده', '1', '0'),
(89, 28, 0, '1400-11-12 00:00:00', 'روغن دنده', '0', '1'),
(90, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(91, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(92, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '0', '1'),
(93, 28, 0, '1400-11-12 00:00:00', 'روغن هیدرولیک', '1', '0'),
(94, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(95, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(96, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(97, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '0', '1'),
(98, 28, 0, '1400-11-12 00:00:00', 'روغن موتور', '1', '0'),
(99, 29, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(100, 29, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(101, 29, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(102, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(103, 30, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(104, 30, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(105, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(106, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(107, 30, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(108, 30, 0, '1400-11-13 00:00:00', 'روغن دنده', '1', '0'),
(109, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(110, 31, 0, '1400-11-13 00:00:00', 'روغن دنده', '0', '1'),
(111, 31, 0, '1400-11-13 00:00:00', 'روغن هیدرولیک', '1', '0'),
(112, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(113, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '0', '1'),
(114, 31, 0, '1400-11-13 00:00:00', 'روغن موتور', '1', '0'),
(115, 31, 0, '1400-11-13 00:00:00', 'روغن دنده', '1', '0'),
(116, 33, 0, '1400-12-17 00:00:00', 'فیلتر روغن', '0', '1'),
(117, 33, 0, '1400-12-17 00:00:00', 'فیلتر هوا', '0', '1'),
(118, 33, 0, '1400-12-17 00:00:00', 'فیلتر کابین', '0', '1'),
(119, 33, 0, '1400-12-17 00:00:00', 'فیلتر بنزین', '0', '1'),
(120, 33, 0, '1400-12-17 00:00:00', 'روغن موتور', '0', '1'),
(121, 34, 0, '1400-12-28 00:00:00', 'روغن موتور', '0', '1'),
(122, 34, 0, '1400-12-28 00:00:00', 'فیلتر روغن', '0', '1'),
(123, 34, 0, '1400-12-28 00:00:00', 'فیلتر هوا', '0', '1'),
(124, 34, 0, '1400-12-28 00:00:00', 'فیلتر کابین', '0', '1'),
(125, 35, 0, '1400-12-28 00:00:00', 'روغن موتور', '1', '0'),
(126, 36, 0, '1400-12-28 00:00:00', 'روغن موتور', '1', '0'),
(127, 36, 0, '1400-12-28 00:00:00', 'روغن ترمز', '1', '0'),
(128, 37, 0, '1400-12-28 00:00:00', 'روغن موتور', '1', '0'),
(129, 37, 0, '1400-12-28 00:00:00', 'روغن ترمز', '1', '0'),
(130, 37, 0, '1400-12-28 00:00:00', 'روغن هیدرولیک', '1', '0'),
(131, 37, 0, '1400-12-28 00:00:00', 'روغن موتور', '1', '0'),
(132, 38, 0, '1401-01-04 00:00:00', 'روغن موتور', '1', '0'),
(133, 38, 0, '1401-01-04 00:00:00', 'روغن ترمز', '1', '0'),
(134, 38, 0, '1401-01-04 00:00:00', 'روغن هیدرولیک', '1', '0'),
(135, 38, 0, '1401-01-04 00:00:00', 'روغن موتور', '1', '0'),
(136, 39, 0, '1401-01-05 00:00:00', 'روغن موتور', '1', '0'),
(137, 39, 0, '1401-01-05 00:00:00', 'روغن ترمز', '1', '0'),
(138, 39, 0, '1401-01-05 00:00:00', 'روغن هیدرولیک', '1', '0'),
(139, 39, 0, '1401-01-05 00:00:00', 'روغن موتور', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `service_records`
--

CREATE TABLE `service_records` (
  `service_id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `discount_percent` int(11) NOT NULL DEFAULT '0',
  `discount_quantity` int(11) NOT NULL DEFAULT '0',
  `remaining` int(11) NOT NULL DEFAULT '0',
  `is_change` bit(1) NOT NULL DEFAULT b'0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `avg_function` bigint(255) NOT NULL DEFAULT '0',
  `km_now` bigint(255) NOT NULL DEFAULT '0',
  `km_next` bigint(255) NOT NULL DEFAULT '0',
  `is_sent_msg` bit(1) NOT NULL DEFAULT b'0',
  `description` text COLLATE utf8_persian_ci NOT NULL,
  `price` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `service_records`
--

INSERT INTO `service_records` (`service_id`, `d_id`, `car_id`, `date_time`, `discount_percent`, `discount_quantity`, `remaining`, `is_change`, `is_delete`, `avg_function`, `km_now`, `km_next`, `is_sent_msg`, `description`, `price`) VALUES
(1, 1, 1, '0000-00-00 00:00:00', 0, 0, 0, b'0', b'0', 0, 0, 0, b'0', '', 0),
(2, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(3, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(4, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(5, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(6, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(7, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(8, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(9, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(10, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(11, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(12, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(13, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(14, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(15, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(16, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(17, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(18, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(19, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(20, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(21, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(22, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(23, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(24, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'0', 5000, 290000, 320000, b'0', 'test', 265000),
(25, 10, 15, '1400-11-12 00:00:00', 0, 0, 0, b'0', b'0', 50000, 290000, 320000, b'0', 'تست', 265000),
(26, 10, 15, '1400-11-12 00:00:00', 0, 0, 0, b'0', b'1', 50000, 290000, 320000, b'0', '', 256000),
(27, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(28, 10, 15, '1400-04-01 00:00:00', 0, 0, 0, b'0', b'1', 5000, 290000, 320000, b'0', 'test', 265000),
(29, 10, 25, '1400-11-13 00:00:00', 0, 0, 0, b'0', b'1', 5000, 250000, 265000, b'0', '', 265000),
(30, 10, 25, '1400-11-13 00:00:00', 0, 0, 0, b'0', b'1', 5000, 265000, 280000, b'0', '', 254000),
(31, 10, 26, '1400-11-13 00:00:00', 0, 0, 0, b'0', b'0', 5000, 25, 1000, b'0', '', 256000),
(32, 10, 15, '1400-11-13 00:00:00', 0, 0, 0, b'0', b'0', 5000, 250000, 260000, b'0', '', 250000),
(33, 16, 27, '1400-12-17 00:00:00', 0, 0, 0, b'0', b'0', 80, 239000, 244000, b'0', '', 3750000),
(34, 20, 27, '1400-12-28 00:00:00', 0, 0, 0, b'0', b'0', 55, 240000, 245000, b'0', '', 4200000),
(35, 25, 28, '1400-12-28 00:00:00', 0, 0, 0, b'0', b'0', 4500, 0, 5000, b'0', 'تست اول', 250000),
(36, 25, 28, '1400-12-28 00:00:00', 0, 0, 0, b'0', b'0', 58, 88, 888, b'0', '', 888),
(37, 25, 28, '1400-12-28 00:00:00', 0, 0, 0, b'0', b'0', 58, 5000, 75000, b'0', '', 558),
(38, 25, 29, '1401-01-04 00:00:00', 0, 0, 0, b'0', b'0', 22, 5, 55, b'0', 'اااا', 555),
(39, 25, 31, '1401-01-05 00:00:00', 0, 0, 0, b'0', b'0', 88, 255, 888, b'0', '', 8888);

-- --------------------------------------------------------

--
-- Table structure for table `service_timing`
--

CREATE TABLE `service_timing` (
  `id` int(255) NOT NULL,
  `d_id` int(11) NOT NULL,
  `sub_group_id` int(11) NOT NULL,
  `service_date` date NOT NULL,
  `due_date` date NOT NULL,
  `defenitive_date` date NOT NULL,
  `km_next` bigint(255) NOT NULL,
  `avg_function` bigint(20) NOT NULL,
  `num_reminder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

CREATE TABLE `slider` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_persian_ci,
  `image` text COLLATE utf8_persian_ci,
  `url` text COLLATE utf8_persian_ci NOT NULL,
  `dir` text COLLATE utf8_persian_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`id`, `title`, `image`, `url`, `dir`) VALUES
(5, '1', 'https://baadbaanapp.ir/public/img/slider/slider1.png', '1', 'public/img/slider/slider1.png'),
(6, '2', 'https://baadbaanapp.ir/public/img/slider/slider2.png', '2', 'public/img/slider/slider2.png'),
(7, '3', 'https://baadbaanapp.ir/public/img/slider/slider3.png', '3', 'public/img/slider/slider3.png'),
(8, '4', 'https://baadbaanapp.ir/public/img/slider/slider4.png', '4', 'public/img/slider/slider4.png');

-- --------------------------------------------------------

--
-- Table structure for table `sms_purchase_history`
--

CREATE TABLE `sms_purchase_history` (
  `id` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `price` bigint(20) NOT NULL,
  `status` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_timing`
--

CREATE TABLE `sms_timing` (
  `id` int(11) NOT NULL,
  `reciver_num` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `draft_id` int(11) NOT NULL,
  `create_date` date NOT NULL,
  `send_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `software`
--

CREATE TABLE `software` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` date NOT NULL,
  `dn_link` text COLLATE utf8_persian_ci NOT NULL,
  `dir` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(255) NOT NULL,
  `name` text COLLATE utf8_persian_ci NOT NULL,
  `lastname` text COLLATE utf8_persian_ci NOT NULL,
  `sex` text COLLATE utf8_persian_ci NOT NULL,
  `birth_date` date NOT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci NOT NULL,
  `register_date` date NOT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `is_change` bit(1) NOT NULL DEFAULT b'0',
  `status` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_persian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `lastname`, `sex`, `birth_date`, `phone`, `register_date`, `is_delete`, `is_change`, `status`, `username`, `password`) VALUES
(1, 'ehsan', 'rst', 'مرد', '2022-02-16', '09185081379', '2022-01-18', b'0', b'0', 0, '', ''),
(2, 'بارمان', 'شکوهی', 'مرد', '2021-11-09', '09304297421', '2022-01-11', b'0', b'0', 0, '', ''),
(25, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(28, 'عرفان', 'سقاباشی', 'مرد', '1375-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(29, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(30, 'عرفان دو', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(31, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(32, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(33, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(34, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(35, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(36, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(37, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(38, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116269', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(39, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(40, 'عرفان', 'سقاباشی', 'مرد', '2020-04-01', '09398116292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(41, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(42, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(43, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(44, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(45, 'رضا', 'حامدی', 'مرد', '1370-01-01', '09017736292', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(46, 'فراز', 'فریدنیا', 'مرد', '1370-01-01', '09014225332', '2022-02-01', b'0', b'0', 0, NULL, NULL),
(47, 'محمود', 'همدانی', 'مرد', '1370-01-16', '09184455585', '2022-03-08', b'0', b'0', 0, NULL, NULL),
(48, 'مهدی', 'ساعتی', 'مرد', '1368-09-19', '9921315713', '2022-03-19', b'0', b'0', 0, NULL, NULL),
(49, 'تست', 'تست', 'زن', '1370-12-01', '9181112233', '2022-03-24', b'0', b'0', 0, NULL, NULL),
(50, 'س', 'س', 'مرد', '1370-01-01', '22222', '2022-03-25', b'0', b'0', 0, NULL, NULL),
(51, 'ع', 'تت', 'مرد', '1370-01-01', '998', '2022-03-25', b'0', b'0', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `validate_phone`
--

CREATE TABLE `validate_phone` (
  `id` int(11) NOT NULL,
  `phone` text COLLATE utf8_persian_ci NOT NULL,
  `code` int(6) NOT NULL,
  `status` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `validate_phone`
--

INSERT INTO `validate_phone` (`id`, `phone`, `code`, `status`) VALUES
(1, '09185081379', 660751, 'finish'),
(2, '09398116292', 150759, 'active'),
(3, '09185081379', 118353, 'active'),
(4, '09398116292', 638876, 'active'),
(5, '09185081379', 736900, 'finish'),
(6, '09398116292', 916251, 'active'),
(7, '09398116292', 789396, 'active'),
(8, '09185081379', 401137, 'active'),
(9, '09398116292', 262099, 'active'),
(10, '09398116292', 323387, 'active'),
(11, '09398116292', 943882, 'active'),
(12, '09185081379', 418936, 'active'),
(13, '09398116292', 221624, 'active'),
(14, '09398116292', 208971, 'active'),
(15, '09398116292', 469895, 'active'),
(16, '09398116292', 949180, 'active'),
(17, '09398116292', 981662, 'active'),
(18, '09398116292', 187369, 'active'),
(19, '09398116292', 365841, 'active'),
(20, '09398116292', 220165, 'active'),
(21, '09398116292', 711176, 'finish'),
(22, '09398116292', 905721, 'active'),
(23, '09398116292', 694128, 'active'),
(24, '09398116292', 620485, 'active'),
(25, '09398116292', 726296, 'finish'),
(26, '09398116292', 775013, 'finish'),
(27, '09398116292', 732380, 'active'),
(28, '09398116292', 614615, 'active'),
(29, '09398116292', 606363, 'finish'),
(30, '09398116292', 797339, 'finish'),
(31, '09398116292', 376261, 'active'),
(32, '09398116292', 862076, 'active'),
(33, '09184455585', 670126, 'active'),
(34, '09184455585', 601875, 'active'),
(35, '09184455585', 263119, 'active'),
(36, '9921315713', 214822, 'active'),
(37, '09184455585', 985435, 'active'),
(38, '09398116292', 760403, 'active'),
(39, '9921315713', 450036, 'active'),
(40, '9921315713', 910175, 'active'),
(41, '9921315713', 698021, 'active'),
(42, '09184455585', 520466, 'active'),
(43, '09184455585', 840149, 'active'),
(44, '09035223062', 391171, 'active'),
(45, '09184455585', 226404, 'active'),
(46, '9921315713', 432466, 'active'),
(47, '09017736292', 141425, 'active'),
(48, '09017736292', 623097, 'active'),
(49, '09017736292', 757063, 'active'),
(50, '09017736292', 356798, 'active'),
(51, '09184455585', 161997, 'active'),
(52, '9921315713', 453841, 'active'),
(53, '9921315713', 936477, 'active'),
(54, '9921315713', 799164, 'active'),
(55, '9921315713', 663994, 'active'),
(56, '9921315713', 878971, 'active'),
(57, '9921315713', 779340, 'active'),
(58, '9921315713', 688528, 'active'),
(59, '09184455585', 856000, 'active'),
(60, '9921315713', 344925, 'active'),
(61, '09189000944', 552219, 'active'),
(62, '9921315713', 785115, 'active'),
(63, '9921315713', 875536, 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auto_service`
--
ALTER TABLE `auto_service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`car_id`);

--
-- Indexes for table `car_info_detail`
--
ALTER TABLE `car_info_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car_name`
--
ALTER TABLE `car_name`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car_tec_info`
--
ALTER TABLE `car_tec_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_list`
--
ALTER TABLE `customer_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_category`
--
ALTER TABLE `job_category`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `list_service`
--
ALTER TABLE `list_service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module_factor`
--
ALTER TABLE `module_factor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operators`
--
ALTER TABLE `operators`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `operator_module`
--
ALTER TABLE `operator_module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operator_option`
--
ALTER TABLE `operator_option`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provinces`
--
ALTER TABLE `provinces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_detail`
--
ALTER TABLE `service_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_records`
--
ALTER TABLE `service_records`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `service_timing`
--
ALTER TABLE `service_timing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_purchase_history`
--
ALTER TABLE `sms_purchase_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_timing`
--
ALTER TABLE `sms_timing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `software`
--
ALTER TABLE `software`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `validate_phone`
--
ALTER TABLE `validate_phone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auto_service`
--
ALTER TABLE `auto_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `car_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `car_info_detail`
--
ALTER TABLE `car_info_detail`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `car_name`
--
ALTER TABLE `car_name`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `car_tec_info`
--
ALTER TABLE `car_tec_info`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `customer_list`
--
ALTER TABLE `customer_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `job_category`
--
ALTER TABLE `job_category`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `list_service`
--
ALTER TABLE `list_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `operators`
--
ALTER TABLE `operators`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `operator_module`
--
ALTER TABLE `operator_module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `operator_option`
--
ALTER TABLE `operator_option`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `service_detail`
--
ALTER TABLE `service_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;

--
-- AUTO_INCREMENT for table `service_records`
--
ALTER TABLE `service_records`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `service_timing`
--
ALTER TABLE `service_timing`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `slider`
--
ALTER TABLE `slider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `sms_purchase_history`
--
ALTER TABLE `sms_purchase_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_timing`
--
ALTER TABLE `sms_timing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `software`
--
ALTER TABLE `software`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `validate_phone`
--
ALTER TABLE `validate_phone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;
--
-- Database: `baadbaan_servicea_am`
--
CREATE DATABASE IF NOT EXISTS `baadbaan_servicea_am` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `baadbaan_servicea_am`;

-- --------------------------------------------------------

--
-- Table structure for table `about_us`
--

CREATE TABLE `about_us` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `excerpt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `access_routes`
--

CREATE TABLE `access_routes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `role_id` bigint(20) UNSIGNED NOT NULL,
  `route_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `access_routes`
--

INSERT INTO `access_routes` (`id`, `role_id`, `route_name`, `created_at`, `updated_at`) VALUES
(1, 1, 'admin/', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `average_function`
--

CREATE TABLE `average_function` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `customer_car_id` bigint(20) UNSIGNED NOT NULL,
  `last_km_now` int(11) NOT NULL,
  `service_date_time` datetime NOT NULL,
  `average` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `backup`
--

CREATE TABLE `backup` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `date_time` datetime NOT NULL,
  `file_dir` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `blogs`
--

CREATE TABLE `blogs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `summary` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tags` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `blog_category_id` bigint(20) UNSIGNED NOT NULL,
  `published_at` datetime NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `view` bigint(20) NOT NULL DEFAULT '0',
  `commentable` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `blogs`
--

INSERT INTO `blogs` (`id`, `title`, `summary`, `content`, `image`, `alt`, `tags`, `type`, `user_id`, `blog_category_id`, `published_at`, `slug`, `view`, `commentable`, `created_at`, `updated_at`) VALUES
(1, 'بلاگ اول', 'خلاصه متن بلاگ اول', 'موتور، قلب و یکی از اصلی‌ترین قطعات ماشین است. موتور از اجزا کوچک و بزرگ تشکیل‌شده که در کنار هم حرکت ماشین را ممکن می‌سازند. قطعات ماشین از فلزات سخت، قطعات فولادی و سنگین ساخته می‌شوند و با یکدیگر اصطحکاک فراوانی دارند. سالم بودن هر یک از این قطعات و درست‌ کار کردن آن‌ها از اهمیت بسیار زیادی برخوردار است. عیب و نقص در هر یک از آن‌ها حرکت ماشین را با مشکل روبه‌رو می‌کند و ممکن است هزینه‌های گزافی برای راننده و سرنشینان ایجاد کند.', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 'متن جایگزین', 'تگ اول', 0, 1, 1, '2022-10-30 13:49:21', 'بلاگ-اول', 0, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `blog_categories`
--

CREATE TABLE `blog_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `keywords` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `blog_categories`
--

INSERT INTO `blog_categories` (`id`, `title`, `description`, `keywords`, `slug`, `status`, `deleted_at`) VALUES
(1, 'عنوان اول دسته بندی', 'توضیحات اول', 'کلمه کلیدی', 'عنوان-اول-دسته-بندی', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `branch_request`
--

CREATE TABLE `branch_request` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `job_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `view` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `cars`
-- (See below for the actual view)
--
CREATE TABLE `cars` (
`id` bigint(20) unsigned
,`car_name_id` bigint(20) unsigned
,`car_name` varchar(255)
,`car_tip_id` bigint(20) unsigned
,`car_tip` varchar(255)
,`car_model_id` bigint(20) unsigned
,`car_model` bigint(11)
,`fuel_type_id` bigint(20) unsigned
,`fuel_type` varchar(255)
,`service_center_id` bigint(20) unsigned
,`user_id` bigint(20) unsigned
,`car_plate` varchar(255)
,`chassis_num` varchar(255)
,`created_at` date
);

-- --------------------------------------------------------

--
-- Table structure for table `cars_company`
--

CREATE TABLE `cars_company` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_company`
--

INSERT INTO `cars_company` (`id`, `name`, `status`, `deleted_at`) VALUES
(1, 'ایران خودرو', 0, NULL),
(2, 'سایپا', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_model`
--

CREATE TABLE `cars_model` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_model`
--

INSERT INTO `cars_model` (`id`, `year`) VALUES
(1, 1390),
(2, 1400);

-- --------------------------------------------------------

--
-- Table structure for table `cars_name`
--

CREATE TABLE `cars_name` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_company_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_name`
--

INSERT INTO `cars_name` (`id`, `car_company_id`, `name`, `status`, `deleted_at`) VALUES
(1, 1, '206', 0, NULL),
(2, 1, '405', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_tip`
--

CREATE TABLE `cars_tip` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `tip` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_tip`
--

INSERT INTO `cars_tip` (`id`, `car_name_id`, `tip`, `status`, `deleted_at`) VALUES
(1, 1, 'تیپ 2', 0, NULL),
(2, 1, 'تیپ 5', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `car_info`
--

CREATE TABLE `car_info` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `car_tip_id` bigint(20) UNSIGNED NOT NULL,
  `car_model_id` bigint(20) UNSIGNED NOT NULL,
  `fuel_type_id` bigint(20) UNSIGNED NOT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `product_group_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `video_dir` text COLLATE utf8mb4_unicode_ci,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `car_info`
--

INSERT INTO `car_info` (`id`, `car_name_id`, `car_tip_id`, `car_model_id`, `fuel_type_id`, `job_category_id`, `product_group_id`, `title`, `video_dir`, `text`, `status`, `deleted_at`) VALUES
(1, 1, 1, 1, 1, 1, 1, 'اموزش تعویض روغن خودرو', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 'روغن موتور خودرو بهران مدل Super Pishtaz SAE حجم 4 لیتر', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `charging_package`
--

CREATE TABLE `charging_package` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `msg_count` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `festival_id` bigint(20) UNSIGNED DEFAULT NULL,
  `total_price` int(11) NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `charging_package`
--

INSERT INTO `charging_package` (`id`, `name`, `msg_count`, `price`, `discount_percent`, `festival_id`, `total_price`, `description`, `status`, `deleted_at`) VALUES
(1, 'بسته نقره‌ای', 10, 199000, 16, NULL, 167160, NULL, 0, NULL),
(2, 'بسته طلایی', 100, 299000, 30, NULL, 209300, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `charging_package_log`
--

CREATE TABLE `charging_package_log` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `charging_package_id` bigint(20) UNSIGNED NOT NULL,
  `date_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `province_id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 'آذر شهر', NULL, NULL, NULL),
(2, 1, 'اسكو', NULL, NULL, NULL),
(3, 1, 'اهر', NULL, NULL, NULL),
(4, 1, 'بستان آباد', NULL, NULL, NULL),
(5, 1, 'بناب', NULL, NULL, NULL),
(6, 1, 'بندر شرفخانه', NULL, NULL, NULL),
(7, 1, 'تبريز', NULL, NULL, NULL),
(8, 1, 'تسوج', NULL, NULL, NULL),
(9, 1, 'جلفا', NULL, NULL, NULL),
(10, 1, 'سراب', NULL, NULL, NULL),
(11, 1, 'شبستر', NULL, NULL, NULL),
(12, 1, 'صوفیان', NULL, NULL, NULL),
(13, 1, 'عجبشير', NULL, NULL, NULL),
(14, 1, 'قره آغاج', NULL, NULL, NULL),
(15, 1, 'كليبر', NULL, NULL, NULL),
(16, 1, 'كندوان', NULL, NULL, NULL),
(17, 1, 'مراغه', NULL, NULL, NULL),
(18, 1, 'مرند', NULL, NULL, NULL),
(19, 1, 'ملكان', NULL, NULL, NULL),
(20, 1, 'ممقان', NULL, NULL, NULL),
(21, 1, 'ميانه', NULL, NULL, NULL),
(22, 1, 'هاديشهر', NULL, NULL, NULL),
(23, 1, 'هريس', NULL, NULL, NULL),
(24, 1, 'هشترود', NULL, NULL, NULL),
(25, 1, 'ورزقان', NULL, NULL, NULL),
(26, 2, 'اروميه', NULL, NULL, NULL),
(27, 2, 'اشنويه', NULL, NULL, NULL),
(28, 2, 'بازرگان', NULL, NULL, NULL),
(29, 2, 'بوكان', NULL, NULL, NULL),
(30, 2, 'پلدشت', NULL, NULL, NULL),
(31, 2, 'پيرانشهر', NULL, NULL, NULL),
(32, 2, 'تكاب', NULL, NULL, NULL),
(33, 2, 'خوي', NULL, NULL, NULL),
(34, 2, 'سردشت', NULL, NULL, NULL),
(35, 2, 'سلماس', NULL, NULL, NULL),
(36, 2, 'سيه چشمه- چالدران', NULL, NULL, NULL),
(37, 2, 'سیمینه', NULL, NULL, NULL),
(38, 2, 'شاهين دژ', NULL, NULL, NULL),
(39, 2, 'شوط', NULL, NULL, NULL),
(40, 2, 'ماكو', NULL, NULL, NULL),
(41, 2, 'مهاباد', NULL, NULL, NULL),
(42, 2, 'مياندوآب', NULL, NULL, NULL),
(43, 2, 'نقده', NULL, NULL, NULL),
(44, 3, 'اردبيل', NULL, NULL, NULL),
(45, 3, 'بيله سوار', NULL, NULL, NULL),
(46, 3, 'پارس آباد', NULL, NULL, NULL),
(47, 3, 'خلخال', NULL, NULL, NULL),
(48, 3, 'سرعين', NULL, NULL, NULL),
(49, 3, 'كيوي (كوثر)', NULL, NULL, NULL),
(50, 3, 'گرمي (مغان)', NULL, NULL, NULL),
(51, 3, 'مشگين شهر', NULL, NULL, NULL),
(52, 3, 'مغان (سمنان)', NULL, NULL, NULL),
(53, 3, 'نمين', NULL, NULL, NULL),
(54, 3, 'نير', NULL, NULL, NULL),
(55, 4, 'آران و بيدگل', NULL, NULL, NULL),
(56, 4, 'اردستان', NULL, NULL, NULL),
(57, 4, 'اصفهان', NULL, NULL, NULL),
(58, 4, 'باغ بهادران', NULL, NULL, NULL),
(59, 4, 'تيران', NULL, NULL, NULL),
(60, 4, 'خميني شهر', NULL, NULL, NULL),
(61, 4, 'خوانسار', NULL, NULL, NULL),
(62, 4, 'دهاقان', NULL, NULL, NULL),
(63, 4, 'دولت آباد-اصفهان', NULL, NULL, NULL),
(64, 4, 'زرين شهر', NULL, NULL, NULL),
(65, 4, 'زيباشهر (محمديه)', NULL, NULL, NULL),
(66, 4, 'سميرم', NULL, NULL, NULL),
(67, 4, 'شاهين شهر', NULL, NULL, NULL),
(68, 4, 'شهرضا', NULL, NULL, NULL),
(69, 4, 'فريدن', NULL, NULL, NULL),
(70, 4, 'فريدون شهر', NULL, NULL, NULL),
(71, 4, 'فلاورجان', NULL, NULL, NULL),
(72, 4, 'فولاد شهر', NULL, NULL, NULL),
(73, 4, 'قهدریجان', NULL, NULL, NULL),
(74, 4, 'كاشان', NULL, NULL, NULL),
(75, 4, 'گلپايگان', NULL, NULL, NULL),
(76, 4, 'گلدشت اصفهان', NULL, NULL, NULL),
(77, 4, 'گلدشت مركزی', NULL, NULL, NULL),
(78, 4, 'مباركه اصفهان', NULL, NULL, NULL),
(79, 4, 'مهاباد-اصفهان', NULL, NULL, NULL),
(80, 4, 'نايين', NULL, NULL, NULL),
(81, 4, 'نجف آباد', NULL, NULL, NULL),
(82, 4, 'نطنز', NULL, NULL, NULL),
(83, 4, 'هرند', NULL, NULL, NULL),
(84, 5, 'آسارا', NULL, NULL, NULL),
(85, 5, 'اشتهارد', NULL, NULL, NULL),
(86, 5, 'شهر جديد هشتگرد', NULL, NULL, NULL),
(87, 5, 'طالقان', NULL, NULL, NULL),
(88, 5, 'كرج', NULL, NULL, NULL),
(89, 5, 'گلستان تهران', NULL, NULL, NULL),
(90, 5, 'نظرآباد', NULL, NULL, NULL),
(91, 5, 'هشتگرد', NULL, NULL, NULL),
(92, 6, 'آبدانان', NULL, NULL, NULL),
(93, 6, 'ايلام', NULL, NULL, NULL),
(94, 6, 'ايوان', NULL, NULL, NULL),
(95, 6, 'دره شهر', NULL, NULL, NULL),
(96, 6, 'دهلران', NULL, NULL, NULL),
(97, 6, 'سرابله', NULL, NULL, NULL),
(98, 6, 'شيروان چرداول', NULL, NULL, NULL),
(99, 6, 'مهران', NULL, NULL, NULL),
(100, 7, 'آبپخش', NULL, NULL, NULL),
(101, 7, 'اهرم', NULL, NULL, NULL),
(102, 7, 'برازجان', NULL, NULL, NULL),
(103, 7, 'بندر دير', NULL, NULL, NULL),
(104, 7, 'بندر ديلم', NULL, NULL, NULL),
(105, 7, 'بندر كنگان', NULL, NULL, NULL),
(106, 7, 'بندر گناوه', NULL, NULL, NULL),
(107, 7, 'بوشهر', NULL, NULL, NULL),
(108, 7, 'تنگستان', NULL, NULL, NULL),
(109, 7, 'جزيره خارك', NULL, NULL, NULL),
(110, 7, 'جم (ولايت)', NULL, NULL, NULL),
(111, 7, 'خورموج', NULL, NULL, NULL),
(112, 7, 'دشتستان - شبانکاره', NULL, NULL, NULL),
(113, 7, 'دلوار', NULL, NULL, NULL),
(114, 7, 'عسلویه', NULL, NULL, NULL),
(115, 8, 'اسلامشهر', NULL, NULL, NULL),
(116, 8, 'بومهن', NULL, NULL, NULL),
(117, 8, 'پاكدشت', NULL, NULL, NULL),
(118, 8, 'تهران', NULL, NULL, NULL),
(119, 8, 'چهاردانگه', NULL, NULL, NULL),
(120, 8, 'دماوند', NULL, NULL, NULL),
(121, 8, 'رودهن', NULL, NULL, NULL),
(122, 8, 'ري', NULL, NULL, NULL),
(123, 8, 'شريف آباد', NULL, NULL, NULL),
(124, 8, 'شهر رباط كريم', NULL, NULL, NULL),
(125, 8, 'شهر شهريار', NULL, NULL, NULL),
(126, 8, 'فشم', NULL, NULL, NULL),
(127, 8, 'فيروزكوه', NULL, NULL, NULL),
(128, 8, 'قدس', NULL, NULL, NULL),
(129, 8, 'كهريزك', NULL, NULL, NULL),
(130, 8, 'لواسان بزرگ', NULL, NULL, NULL),
(131, 8, 'ملارد', NULL, NULL, NULL),
(132, 8, 'ورامين', NULL, NULL, NULL),
(133, 9, 'اردل', NULL, NULL, NULL),
(134, 9, 'بروجن', NULL, NULL, NULL),
(135, 9, 'چلگرد (كوهرنگ)', NULL, NULL, NULL),
(136, 9, 'سامان', NULL, NULL, NULL),
(137, 9, 'شهركرد', NULL, NULL, NULL),
(138, 9, 'فارسان', NULL, NULL, NULL),
(139, 9, 'لردگان', NULL, NULL, NULL),
(140, 10, 'بشرویه', NULL, NULL, NULL),
(141, 10, 'بيرجند', NULL, NULL, NULL),
(142, 10, 'خضری', NULL, NULL, NULL),
(143, 10, 'خوسف', NULL, NULL, NULL),
(144, 10, 'سرایان', NULL, NULL, NULL),
(145, 10, 'سربيشه', NULL, NULL, NULL),
(146, 10, 'طبس', NULL, NULL, NULL),
(147, 10, 'فردوس', NULL, NULL, NULL),
(148, 10, 'قائن', NULL, NULL, NULL),
(149, 10, 'نهبندان', NULL, NULL, NULL),
(150, 11, 'بجستان', NULL, NULL, NULL),
(151, 11, 'بردسكن', NULL, NULL, NULL),
(152, 11, 'تايباد', NULL, NULL, NULL),
(153, 11, 'تربت جام', NULL, NULL, NULL),
(154, 11, 'تربت حيدريه', NULL, NULL, NULL),
(155, 11, 'جغتای', NULL, NULL, NULL),
(156, 11, 'جوین', NULL, NULL, NULL),
(157, 11, 'چناران', NULL, NULL, NULL),
(158, 11, 'خلیل آباد', NULL, NULL, NULL),
(159, 11, 'خواف', NULL, NULL, NULL),
(160, 11, 'درگز', NULL, NULL, NULL),
(161, 11, 'رشتخوار', NULL, NULL, NULL),
(162, 11, 'سبزوار', NULL, NULL, NULL),
(163, 11, 'سرخس', NULL, NULL, NULL),
(164, 11, 'طبس', NULL, NULL, NULL),
(165, 11, 'طرقبه', NULL, NULL, NULL),
(166, 11, 'فريمان', NULL, NULL, NULL),
(167, 11, 'قوچان', NULL, NULL, NULL),
(168, 11, 'كاشمر', NULL, NULL, NULL),
(169, 11, 'كلات', NULL, NULL, NULL),
(170, 11, 'گناباد', NULL, NULL, NULL),
(171, 11, 'مشهد', NULL, NULL, NULL),
(172, 11, 'نيشابور', NULL, NULL, NULL),
(173, 12, 'آشخانه، مانه و سمرقان', NULL, NULL, NULL),
(174, 12, 'اسفراين', NULL, NULL, NULL),
(175, 12, 'بجنورد', NULL, NULL, NULL),
(176, 12, 'جاجرم', NULL, NULL, NULL),
(177, 12, 'شيروان', NULL, NULL, NULL),
(178, 12, 'فاروج', NULL, NULL, NULL),
(179, 13, 'آبادان', NULL, NULL, NULL),
(180, 13, 'اميديه', NULL, NULL, NULL),
(181, 13, 'انديمشك', NULL, NULL, NULL),
(182, 13, 'اهواز', NULL, NULL, NULL),
(183, 13, 'ايذه', NULL, NULL, NULL),
(184, 13, 'باغ ملك', NULL, NULL, NULL),
(185, 13, 'بستان', NULL, NULL, NULL),
(186, 13, 'بندر ماهشهر', NULL, NULL, NULL),
(187, 13, 'بندرامام خميني', NULL, NULL, NULL),
(188, 13, 'بهبهان', NULL, NULL, NULL),
(189, 13, 'خرمشهر', NULL, NULL, NULL),
(190, 13, 'دزفول', NULL, NULL, NULL),
(191, 13, 'رامشیر', NULL, NULL, NULL),
(192, 13, 'رامهرمز', NULL, NULL, NULL),
(193, 13, 'سوسنگرد', NULL, NULL, NULL),
(194, 13, 'شادگان', NULL, NULL, NULL),
(195, 13, 'شوش', NULL, NULL, NULL),
(196, 13, 'شوشتر', NULL, NULL, NULL),
(197, 13, 'لالي', NULL, NULL, NULL),
(198, 13, 'مسجد سليمان', NULL, NULL, NULL),
(199, 13, 'هنديجان', NULL, NULL, NULL),
(200, 13, 'هويزه', NULL, NULL, NULL),
(201, 14, 'آب بر (طارم)', NULL, NULL, NULL),
(202, 14, 'ابهر', NULL, NULL, NULL),
(203, 14, 'خرمدره', NULL, NULL, NULL),
(204, 14, 'زرین آباد (ایجرود)', NULL, NULL, NULL),
(205, 14, 'زنجان', NULL, NULL, NULL),
(206, 14, 'قیدار (خدا بنده)', NULL, NULL, NULL),
(207, 14, 'ماهنشان', NULL, NULL, NULL),
(208, 15, 'ايوانكي', NULL, NULL, NULL),
(209, 15, 'بسطام', NULL, NULL, NULL),
(210, 15, 'دامغان', NULL, NULL, NULL),
(211, 15, 'سرخه', NULL, NULL, NULL),
(212, 15, 'سمنان', NULL, NULL, NULL),
(213, 15, 'شاهرود', NULL, NULL, NULL),
(214, 15, 'شهمیرزاد', NULL, NULL, NULL),
(215, 15, 'گرمسار', NULL, NULL, NULL),
(216, 15, 'مهدیشهر', NULL, NULL, NULL),
(217, 16, 'ايرانشهر', NULL, NULL, NULL),
(218, 16, 'چابهار', NULL, NULL, NULL),
(219, 16, 'خاش', NULL, NULL, NULL),
(220, 16, 'راسك', NULL, NULL, NULL),
(221, 16, 'زابل', NULL, NULL, NULL),
(222, 16, 'زاهدان', NULL, NULL, NULL),
(223, 16, 'سراوان', NULL, NULL, NULL),
(224, 16, 'سرباز', NULL, NULL, NULL),
(225, 16, 'ميرجاوه', NULL, NULL, NULL),
(226, 16, 'نيكشهر', NULL, NULL, NULL),
(227, 17, 'آباده', NULL, NULL, NULL),
(228, 17, 'آباده طشك', NULL, NULL, NULL),
(229, 17, 'اردكان', NULL, NULL, NULL),
(230, 17, 'ارسنجان', NULL, NULL, NULL),
(231, 17, 'استهبان', NULL, NULL, NULL),
(232, 17, 'اشكنان', NULL, NULL, NULL),
(233, 17, 'اقليد', NULL, NULL, NULL),
(234, 17, 'اوز', NULL, NULL, NULL),
(235, 17, 'ایج', NULL, NULL, NULL),
(236, 17, 'ایزد خواست', NULL, NULL, NULL),
(237, 17, 'باب انار', NULL, NULL, NULL),
(238, 17, 'بالاده', NULL, NULL, NULL),
(239, 17, 'بنارويه', NULL, NULL, NULL),
(240, 17, 'بهمن', NULL, NULL, NULL),
(241, 17, 'بوانات', NULL, NULL, NULL),
(242, 17, 'بيرم', NULL, NULL, NULL),
(243, 17, 'بیضا', NULL, NULL, NULL),
(244, 17, 'جنت شهر', NULL, NULL, NULL),
(245, 17, 'جهرم', NULL, NULL, NULL),
(246, 17, 'حاجي آباد-زرین دشت', NULL, NULL, NULL),
(247, 17, 'خاوران', NULL, NULL, NULL),
(248, 17, 'خرامه', NULL, NULL, NULL),
(249, 17, 'خشت', NULL, NULL, NULL),
(250, 17, 'خفر', NULL, NULL, NULL),
(251, 17, 'خنج', NULL, NULL, NULL),
(252, 17, 'خور', NULL, NULL, NULL),
(253, 17, 'داراب', NULL, NULL, NULL),
(254, 17, 'رونيز عليا', NULL, NULL, NULL),
(255, 17, 'زاهدشهر', NULL, NULL, NULL),
(256, 17, 'زرقان', NULL, NULL, NULL),
(257, 17, 'سده', NULL, NULL, NULL),
(258, 17, 'سروستان', NULL, NULL, NULL),
(259, 17, 'سعادت شهر', NULL, NULL, NULL),
(260, 17, 'سورمق', NULL, NULL, NULL),
(261, 17, 'ششده', NULL, NULL, NULL),
(262, 17, 'شيراز', NULL, NULL, NULL),
(263, 17, 'صغاد', NULL, NULL, NULL),
(264, 17, 'صفاشهر', NULL, NULL, NULL),
(265, 17, 'علاء مرودشت', NULL, NULL, NULL),
(266, 17, 'عنبر', NULL, NULL, NULL),
(267, 17, 'فراشبند', NULL, NULL, NULL),
(268, 17, 'فسا', NULL, NULL, NULL),
(269, 17, 'فيروز آباد', NULL, NULL, NULL),
(270, 17, 'قائميه', NULL, NULL, NULL),
(271, 17, 'قادر آباد', NULL, NULL, NULL),
(272, 17, 'قطب آباد', NULL, NULL, NULL),
(273, 17, 'قير', NULL, NULL, NULL),
(274, 17, 'كازرون', NULL, NULL, NULL),
(275, 17, 'كنار تخته', NULL, NULL, NULL),
(276, 17, 'گراش', NULL, NULL, NULL),
(277, 17, 'لار', NULL, NULL, NULL),
(278, 17, 'لامرد', NULL, NULL, NULL),
(279, 17, 'لپوئی', NULL, NULL, NULL),
(280, 17, 'لطيفي', NULL, NULL, NULL),
(281, 17, 'مبارك آباد ديز', NULL, NULL, NULL),
(282, 17, 'مرودشت', NULL, NULL, NULL),
(283, 17, 'مشكان', NULL, NULL, NULL),
(284, 17, 'مصير', NULL, NULL, NULL),
(285, 17, 'مهر فارس(گله دار)', NULL, NULL, NULL),
(286, 17, 'ميمند', NULL, NULL, NULL),
(287, 17, 'نوبندگان', NULL, NULL, NULL),
(288, 17, 'نودان', NULL, NULL, NULL),
(289, 17, 'نورآباد', NULL, NULL, NULL),
(290, 17, 'ني ريز', NULL, NULL, NULL),
(291, 17, 'کوار', NULL, NULL, NULL),
(292, 18, 'آبيك', NULL, NULL, NULL),
(293, 18, 'البرز', NULL, NULL, NULL),
(294, 18, 'بوئين زهرا', NULL, NULL, NULL),
(295, 18, 'تاكستان', NULL, NULL, NULL),
(296, 18, 'قزوين', NULL, NULL, NULL),
(297, 18, 'محمود آباد نمونه', NULL, NULL, NULL),
(298, 19, 'قم', NULL, NULL, NULL),
(299, 20, 'بانه', NULL, NULL, NULL),
(300, 20, 'بيجار', NULL, NULL, NULL),
(301, 20, 'دهگلان', NULL, NULL, NULL),
(302, 20, 'ديواندره', NULL, NULL, NULL),
(303, 20, 'سقز', NULL, NULL, NULL),
(304, 20, 'سنندج', NULL, NULL, NULL),
(305, 20, 'قروه', NULL, NULL, NULL),
(306, 20, 'كامياران', NULL, NULL, NULL),
(307, 20, 'مريوان', NULL, NULL, NULL),
(308, 21, 'بابك', NULL, NULL, NULL),
(309, 21, 'بافت', NULL, NULL, NULL),
(310, 21, 'بردسير', NULL, NULL, NULL),
(311, 21, 'بم', NULL, NULL, NULL),
(312, 21, 'جيرفت', NULL, NULL, NULL),
(313, 21, 'راور', NULL, NULL, NULL),
(314, 21, 'رفسنجان', NULL, NULL, NULL),
(315, 21, 'زرند', NULL, NULL, NULL),
(316, 21, 'سيرجان', NULL, NULL, NULL),
(317, 21, 'كرمان', NULL, NULL, NULL),
(318, 21, 'كهنوج', NULL, NULL, NULL),
(319, 21, 'منوجان', NULL, NULL, NULL),
(320, 22, 'اسلام آباد غرب', NULL, NULL, NULL),
(321, 22, 'پاوه', NULL, NULL, NULL),
(322, 22, 'تازه آباد- ثلاث باباجانی', NULL, NULL, NULL),
(323, 22, 'جوانرود', NULL, NULL, NULL),
(324, 22, 'سر پل ذهاب', NULL, NULL, NULL),
(325, 22, 'سنقر كليائي', NULL, NULL, NULL),
(326, 22, 'صحنه', NULL, NULL, NULL),
(327, 22, 'قصر شيرين', NULL, NULL, NULL),
(328, 22, 'كرمانشاه', NULL, NULL, NULL),
(329, 22, 'كنگاور', NULL, NULL, NULL),
(330, 22, 'گيلان غرب', NULL, NULL, NULL),
(331, 22, 'هرسين', NULL, NULL, NULL),
(332, 23, 'دهدشت', NULL, NULL, NULL),
(333, 23, 'دوگنبدان', NULL, NULL, NULL),
(334, 23, 'سي سخت- دنا', NULL, NULL, NULL),
(335, 23, 'گچساران', NULL, NULL, NULL),
(336, 23, 'ياسوج', NULL, NULL, NULL),
(337, 24, 'آزاد شهر', NULL, NULL, NULL),
(338, 24, 'آق قلا', NULL, NULL, NULL),
(339, 24, 'انبارآلوم', NULL, NULL, NULL),
(340, 24, 'اینچه برون', NULL, NULL, NULL),
(341, 24, 'بندر گز', NULL, NULL, NULL),
(342, 24, 'تركمن', NULL, NULL, NULL),
(343, 24, 'جلین', NULL, NULL, NULL),
(344, 24, 'خان ببین', NULL, NULL, NULL),
(345, 24, 'راميان', NULL, NULL, NULL),
(346, 24, 'سرخس کلاته', NULL, NULL, NULL),
(347, 24, 'سیمین شهر', NULL, NULL, NULL),
(348, 24, 'علي آباد كتول', NULL, NULL, NULL),
(349, 24, 'فاضل آباد', NULL, NULL, NULL),
(350, 24, 'كردكوي', NULL, NULL, NULL),
(351, 24, 'كلاله', NULL, NULL, NULL),
(352, 24, 'گالیکش', NULL, NULL, NULL),
(353, 24, 'گرگان', NULL, NULL, NULL),
(354, 24, 'گمیش تپه', NULL, NULL, NULL),
(355, 24, 'گنبد كاووس', NULL, NULL, NULL),
(356, 24, 'مراوه تپه', NULL, NULL, NULL),
(357, 24, 'مينو دشت', NULL, NULL, NULL),
(358, 24, 'نگین شهر', NULL, NULL, NULL),
(359, 24, 'نوده خاندوز', NULL, NULL, NULL),
(360, 24, 'نوکنده', NULL, NULL, NULL),
(361, 25, 'آستارا', NULL, NULL, NULL),
(362, 25, 'آستانه اشرفيه', NULL, NULL, NULL),
(363, 25, 'املش', NULL, NULL, NULL),
(364, 25, 'بندرانزلي', NULL, NULL, NULL),
(365, 25, 'خمام', NULL, NULL, NULL),
(366, 25, 'رشت', NULL, NULL, NULL),
(367, 25, 'رضوان شهر', NULL, NULL, NULL),
(368, 25, 'رود سر', NULL, NULL, NULL),
(369, 25, 'رودبار', NULL, NULL, NULL),
(370, 25, 'سياهكل', NULL, NULL, NULL),
(371, 25, 'شفت', NULL, NULL, NULL),
(372, 25, 'صومعه سرا', NULL, NULL, NULL),
(373, 25, 'فومن', NULL, NULL, NULL),
(374, 25, 'كلاچاي', NULL, NULL, NULL),
(375, 25, 'لاهيجان', NULL, NULL, NULL),
(376, 25, 'لنگرود', NULL, NULL, NULL),
(377, 25, 'لوشان', NULL, NULL, NULL),
(378, 25, 'ماسال', NULL, NULL, NULL),
(379, 25, 'ماسوله', NULL, NULL, NULL),
(380, 25, 'منجيل', NULL, NULL, NULL),
(381, 25, 'هشتپر', NULL, NULL, NULL),
(382, 26, 'ازنا', NULL, NULL, NULL),
(383, 26, 'الشتر', NULL, NULL, NULL),
(384, 26, 'اليگودرز', NULL, NULL, NULL),
(385, 26, 'بروجرد', NULL, NULL, NULL),
(386, 26, 'پلدختر', NULL, NULL, NULL),
(387, 26, 'خرم آباد', NULL, NULL, NULL),
(388, 26, 'دورود', NULL, NULL, NULL),
(389, 26, 'سپید دشت', NULL, NULL, NULL),
(390, 26, 'كوهدشت', NULL, NULL, NULL),
(391, 26, 'نورآباد (خوزستان)', NULL, NULL, NULL),
(392, 27, 'آمل', NULL, NULL, NULL),
(393, 27, 'بابل', NULL, NULL, NULL),
(394, 27, 'بابلسر', NULL, NULL, NULL),
(395, 27, 'بلده', NULL, NULL, NULL),
(396, 27, 'بهشهر', NULL, NULL, NULL),
(397, 27, 'پل سفيد', NULL, NULL, NULL),
(398, 27, 'تنكابن', NULL, NULL, NULL),
(399, 27, 'جويبار', NULL, NULL, NULL),
(400, 27, 'چالوس', NULL, NULL, NULL),
(401, 27, 'خرم آباد', NULL, NULL, NULL),
(402, 27, 'رامسر', NULL, NULL, NULL),
(403, 27, 'رستم کلا', NULL, NULL, NULL),
(404, 27, 'ساري', NULL, NULL, NULL),
(405, 27, 'سلمانشهر', NULL, NULL, NULL),
(406, 27, 'سواد كوه', NULL, NULL, NULL),
(407, 27, 'فريدون كنار', NULL, NULL, NULL),
(408, 27, 'قائم شهر', NULL, NULL, NULL),
(409, 27, 'گلوگاه', NULL, NULL, NULL),
(410, 27, 'محمودآباد', NULL, NULL, NULL),
(411, 27, 'مرزن آباد', NULL, NULL, NULL),
(412, 27, 'نكا', NULL, NULL, NULL),
(413, 27, 'نور', NULL, NULL, NULL),
(414, 27, 'نوشهر', NULL, NULL, NULL),
(415, 28, 'آشتيان', NULL, NULL, NULL),
(416, 28, 'اراك', NULL, NULL, NULL),
(417, 28, 'تفرش', NULL, NULL, NULL),
(418, 28, 'خمين', NULL, NULL, NULL),
(419, 28, 'دليجان', NULL, NULL, NULL),
(420, 28, 'ساوه', NULL, NULL, NULL),
(421, 28, 'شازند', NULL, NULL, NULL),
(422, 28, 'محلات', NULL, NULL, NULL),
(423, 28, 'کمیجان', NULL, NULL, NULL),
(424, 29, 'ابوموسي', NULL, NULL, NULL),
(425, 29, 'انگهران', NULL, NULL, NULL),
(426, 29, 'بستك', NULL, NULL, NULL),
(427, 29, 'بندر جاسك', NULL, NULL, NULL),
(428, 29, 'بندر لنگه', NULL, NULL, NULL),
(429, 29, 'بندرعباس', NULL, NULL, NULL),
(430, 29, 'پارسیان', NULL, NULL, NULL),
(431, 29, 'حاجي آباد', NULL, NULL, NULL),
(432, 29, 'دشتی', NULL, NULL, NULL),
(433, 29, 'دهبارز (رودان)', NULL, NULL, NULL),
(434, 29, 'قشم', NULL, NULL, NULL),
(435, 29, 'كيش', NULL, NULL, NULL),
(436, 29, 'ميناب', NULL, NULL, NULL),
(437, 30, 'اسدآباد', NULL, NULL, NULL),
(438, 30, 'بهار', NULL, NULL, NULL),
(439, 30, 'تويسركان', NULL, NULL, NULL),
(440, 30, 'رزن', NULL, NULL, NULL),
(441, 30, 'كبودر اهنگ', NULL, NULL, NULL),
(442, 30, 'ملاير', NULL, NULL, NULL),
(443, 30, 'نهاوند', NULL, NULL, NULL),
(444, 30, 'همدان', NULL, NULL, NULL),
(445, 31, 'ابركوه', NULL, NULL, NULL),
(446, 31, 'اردكان', NULL, NULL, NULL),
(447, 31, 'اشكذر', NULL, NULL, NULL),
(448, 31, 'بافق', NULL, NULL, NULL),
(449, 31, 'تفت', NULL, NULL, NULL),
(450, 31, 'مهريز', NULL, NULL, NULL),
(451, 31, 'ميبد', NULL, NULL, NULL),
(452, 31, 'هرات', NULL, NULL, NULL),
(453, 31, 'يزد', NULL, NULL, NULL),
(454, 30, 'جورقان', '2019-11-27 05:37:13', '2019-11-27 05:37:13', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comments_blog`
--

CREATE TABLE `comments_blog` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `blog_id` bigint(20) UNSIGNED NOT NULL,
  `parent_id` bigint(20) UNSIGNED DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comments_product`
--

CREATE TABLE `comments_product` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `product_name_id` bigint(20) UNSIGNED NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comments_service_center`
--

CREATE TABLE `comments_service_center` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `parent_id` bigint(20) UNSIGNED DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contact_us`
--

CREATE TABLE `contact_us` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `copon`
--

CREATE TABLE `copon` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `discount_celing` int(11) NOT NULL COMMENT 'max of the discount',
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `contact_type` int(11) NOT NULL,
  `sms` tinyint(1) NOT NULL DEFAULT '0',
  `push_notification` tinyint(1) NOT NULL DEFAULT '0',
  `msg_prov_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `customers`
-- (See below for the actual view)
--
CREATE TABLE `customers` (
`cust_id` bigint(20) unsigned
,`f_name` varchar(255)
,`l_name` varchar(255)
,`mobile` varchar(255)
,`sex` varchar(255)
,`birthdate` date
,`national_code` varchar(255)
,`profile_photo` text
,`province_id` bigint(20) unsigned
,`city_id` bigint(20) unsigned
,`cust_created_at` datetime
,`cust_deleted_at` timestamp
,`car_id` bigint(20) unsigned
,`car_name_id` bigint(20) unsigned
,`car_name` varchar(255)
,`car_tip_id` bigint(20) unsigned
,`car_tip` varchar(255)
,`car_plate` varchar(255)
,`car_model_id` bigint(20) unsigned
,`car_model` bigint(11)
,`fuel_type_id` bigint(20) unsigned
,`fuel_type` varchar(255)
,`chassis_num` varchar(255)
,`car_service_center_id` bigint(20) unsigned
,`car_created_at` date
,`user_id` bigint(20) unsigned
,`center_id` bigint(20) unsigned
);

-- --------------------------------------------------------

--
-- Table structure for table `customers_car`
--

CREATE TABLE `customers_car` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `car_tip_id` bigint(20) UNSIGNED NOT NULL,
  `car_model_id` bigint(20) UNSIGNED NOT NULL,
  `fuel_type_id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `chassis_num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `car_plate` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` date NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `customers_car`
--

INSERT INTO `customers_car` (`id`, `car_name_id`, `car_tip_id`, `car_model_id`, `fuel_type_id`, `service_center_id`, `user_id`, `chassis_num`, `car_plate`, `created_at`, `deleted_at`) VALUES
(1, 1, 1, 1, 1, 2, 3, '67556565', '1241518ق', '2022-11-09', NULL),
(2, 1, 1, 1, 1, 2, 4, '232332323', '1341518ق', '2022-11-09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `downloads`
--

CREATE TABLE `downloads` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `f_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `l_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `service_center_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `app_type` tinyint(4) NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_dir` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `festival`
--

CREATE TABLE `festival` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `discount_percent` int(11) DEFAULT NULL,
  `discount_amount` int(11) DEFAULT NULL,
  `sms` tinyint(1) NOT NULL DEFAULT '0',
  `push_notification` tinyint(1) NOT NULL DEFAULT '0',
  `msg_prov_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fuel_type`
--

CREATE TABLE `fuel_type` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `fuel_type`
--

INSERT INTO `fuel_type` (`id`, `type`) VALUES
(1, 'gas');

-- --------------------------------------------------------

--
-- Table structure for table `hero_sliders`
--

CREATE TABLE `hero_sliders` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_dir` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `alt` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `introduce`
--

CREATE TABLE `introduce` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `num_reciver` int(11) NOT NULL,
  `install_app` tinyint(1) NOT NULL,
  `install_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_categories`
--

CREATE TABLE `job_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_categories`
--

INSERT INTO `job_categories` (`id`, `title`, `status`) VALUES
(1, 'تعویض روغنی', 0),
(2, 'مکانیک خودرو', 0),
(3, 'برق خودرو', 0);

-- --------------------------------------------------------

--
-- Table structure for table `job_services`
--

CREATE TABLE `job_services` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_services`
--

INSERT INTO `job_services` (`id`, `title`, `job_category_id`, `status`, `deleted_at`) VALUES
(1, 'تنظیم باد لاستیک', 1, 0, NULL),
(2, 'سرویس روغن گیربکس اوتومات', 1, 0, NULL),
(3, 'سرویس روغن ترمز با دستگاه', 1, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `login_log`
--

CREATE TABLE `login_log` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `date_time` datetime NOT NULL,
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mac` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `browser` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `device` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `meta_keyword`
--

CREATE TABLE `meta_keyword` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `keywords` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2013_06_20_064416_create_users_table', 1),
(2, '2016_06_01_000001_create_oauth_auth_codes_table', 1),
(3, '2016_06_01_000002_create_oauth_access_tokens_table', 1),
(4, '2016_06_01_000003_create_oauth_refresh_tokens_table', 1),
(5, '2016_06_01_000004_create_oauth_clients_table', 1),
(6, '2016_06_01_000005_create_oauth_personal_access_clients_table', 1),
(7, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(8, '2022_09_28_100413_create_services_center_table', 1),
(9, '2022_09_28_100454_create_job_categories_table', 1),
(10, '2022_09_28_100641_create_job_services_table', 1),
(11, '2022_09_28_100936_create_service_available_table', 1),
(12, '2022_09_28_101206_create_product_groups_table', 1),
(13, '2022_09_28_101726_create_cars_company_table', 1),
(14, '2022_09_28_101751_create_cars_name_table', 1),
(15, '2022_09_28_102201_create_cars_model_table', 1),
(16, '2022_09_28_102310_create_cars_tip_table', 1),
(17, '2022_09_28_102524_create_fuel_type_table', 1),
(18, '2022_09_28_102629_create_customers_car_table', 1),
(19, '2022_09_28_103345_create_services_table', 1),
(20, '2022_09_28_104020_create_services_detail_table', 1),
(21, '2022_09_28_105225_create_services_pay_detail_table', 1),
(22, '2022_09_28_105934_create_average_function_table', 1),
(23, '2022_09_28_110441_create_products_name_table', 1),
(24, '2022_09_28_110653_create_services_timing_table', 1),
(25, '2022_09_28_111710_create_msg_num_reminder_table', 1),
(26, '2022_09_28_112302_create_blog_categories_table', 1),
(27, '2022_09_28_112543_create_blogs_table', 1),
(28, '2022_09_28_120602_create_introduce_table', 1),
(29, '2022_09_28_120907_create_slider_table', 1),
(30, '2022_09_28_121114_create_update_table', 1),
(31, '2022_09_28_121518_create_backup_table', 1),
(32, '2022_09_28_121851_create_msg_title_table', 1),
(33, '2022_09_28_121955_create_msg_prov_table', 1),
(34, '2022_09_28_122307_create_msg_draft_table', 1),
(35, '2022_09_28_122611_create_charging_package_table', 1),
(36, '2022_09_28_123357_create_mobile_operation_table', 1),
(37, '2022_09_28_123637_create_msg_pricing_table', 1),
(38, '2022_09_28_123936_create_msg_timing_table', 1),
(39, '2022_09_28_124227_create_msg_log_table', 1),
(40, '2022_09_28_124454_create_charging_package_log_table', 1),
(41, '2022_09_28_124642_create_notification_table', 1),
(42, '2022_09_28_125509_create_notification_log_table', 1),
(43, '2022_09_28_125656_create_ticket_table', 1),
(44, '2022_09_28_125946_create_car_info_table', 1),
(45, '2022_09_28_130538_create_comments_product_table', 1),
(46, '2022_09_28_130812_create_comments_service_center_table', 1),
(47, '2022_09_28_131940_create_comments_blog_table', 1),
(48, '2022_09_28_132323_create_score_service_center_table', 1),
(49, '2022_09_28_132517_create_festival_table', 1),
(50, '2022_09_28_133158_create_copon_table', 1),
(51, '2022_09_28_133559_create_login_log_table', 1),
(52, '2022_09_29_100358_create_quet_table', 1),
(53, '2022_09_29_110922_create_provinces_table', 1),
(54, '2022_09_29_110946_create_cities_table', 1),
(55, '2022_10_01_130640_create_hero_sliders_table', 1),
(56, '2022_10_01_131646_create_tariff_table', 1),
(57, '2022_10_01_132826_create_features_table', 1),
(58, '2022_10_01_133032_create_downloads_table', 1),
(59, '2022_10_02_092015_create_contact_us_table', 1),
(60, '2022_10_02_092131_create_teams_table', 1),
(61, '2022_10_02_092338_create_branch_request_table', 1),
(62, '2022_10_02_092519_create_special_sale_request_table', 1),
(63, '2022_10_02_092610_create_meta_keyword_table', 1),
(64, '2022_10_02_092750_create_property_table', 1),
(65, '2022_10_02_093244_create_about_us_table', 1),
(66, '2022_10_11_100024_add_username_column_to_users_table', 1),
(67, '2022_10_30_130208_create_roles_table', 1),
(68, '2022_10_30_130342_create_access_routes_table', 1),
(69, '2022_10_30_130910_create_tables_relations_migration', 1);

-- --------------------------------------------------------

--
-- Table structure for table `mobile_operation`
--

CREATE TABLE `mobile_operation` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pre_number` int(11) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `mobile_operation`
--

INSERT INTO `mobile_operation` (`id`, `name`, `pre_number`, `deleted_at`) VALUES
(1, 'همراه اول', 918, NULL),
(2, 'همراه اول', 912, NULL),
(3, 'ایرانسل', 930, NULL),
(4, 'ایرانسل', 935, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msg_draft`
--

CREATE TABLE `msg_draft` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_draft`
--

INSERT INTO `msg_draft` (`id`, `service_center_id`, `name`, `text`, `deleted_at`) VALUES
(1, 2, 'تست', 'متن تست', NULL),
(2, 2, 'تست ۲', 'متن تست ۲', NULL),
(3, 2, 'تست ۳', 'تست ۳', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msg_log`
--

CREATE TABLE `msg_log` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` datetime NOT NULL,
  `send_at` datetime NOT NULL,
  `char_count` int(11) NOT NULL,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_log`
--

INSERT INTO `msg_log` (`id`, `service_center_id`, `user_id`, `content`, `create_at`, `send_at`, `char_count`, `total_price`) VALUES
(1, 2, 4, 'متن تستیارسال شد', '2022-11-10 01:26:25', '2022-11-10 01:26:25', 40, 2500),
(2, 2, 6, 'تست ۳', '2022-11-10 04:19:16', '2022-11-10 04:19:17', 9, 46);

-- --------------------------------------------------------

--
-- Stand-in structure for view `msg_log_details`
-- (See below for the actual view)
--
CREATE TABLE `msg_log_details` (
`id` bigint(20) unsigned
,`service_center_id` bigint(20) unsigned
,`user_id` bigint(20) unsigned
,`content` text
,`create_at` datetime
,`send_at` datetime
,`char_count` int(11)
,`total_price` int(11)
,`user_fullname` varchar(511)
,`user_mobile` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `msg_num_reminder`
--

CREATE TABLE `msg_num_reminder` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_timing_id` bigint(20) UNSIGNED NOT NULL,
  `first_msg_date` date NOT NULL,
  `msg_counter` int(11) NOT NULL,
  `status_visit` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `msg_pricing`
--

CREATE TABLE `msg_pricing` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `mobile_operation_id` bigint(20) UNSIGNED NOT NULL,
  `char_count` int(11) NOT NULL,
  `price_fa` int(11) NOT NULL,
  `price_en` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_pricing`
--

INSERT INTO `msg_pricing` (`id`, `mobile_operation_id`, `char_count`, `price_fa`, `price_en`, `status`, `deleted_at`) VALUES
(1, 1, 10, 10, 12, 0, NULL),
(2, 2, 10, 10, 12, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msg_prov`
--

CREATE TABLE `msg_prov` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `msg_title_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `char_count` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_prov`
--

INSERT INTO `msg_prov` (`id`, `msg_title_id`, `name`, `text`, `char_count`, `status`, `deleted_at`) VALUES
(1, 1, 'msg_prov1', 'متن پیام', 10, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msg_timing`
--

CREATE TABLE `msg_timing` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` datetime NOT NULL,
  `send_at` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `msg_title`
--

CREATE TABLE `msg_title` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `msg_title`
--

INSERT INTO `msg_title` (`id`, `name`, `status`) VALUES
(1, 'متن پیامک اول', 0),
(2, 'متن پیامک دوم', 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT 'sender name',
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `sms` tinyint(1) NOT NULL DEFAULT '0',
  `push_notification` tinyint(1) NOT NULL DEFAULT '0',
  `contact_type` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification_log`
--

CREATE TABLE `notification_log` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `notification_id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `viewed` int(11) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--

CREATE TABLE `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_auth_codes`
--

CREATE TABLE `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--

CREATE TABLE `oauth_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_personal_access_clients`
--

CREATE TABLE `oauth_personal_access_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--

CREATE TABLE `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products_name`
--

CREATE TABLE `products_name` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_group_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products_name`
--

INSERT INTO `products_name` (`id`, `product_group_id`, `name`, `image`, `status`, `deleted_at`) VALUES
(1, 1, 'روغن موتور سینولکس 1040', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 0, NULL),
(2, 1, 'روغن موتور لوبریفینت 1040', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 0, NULL),
(3, 1, 'روغن موتور لوبرینول 1040', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_groups`
--

CREATE TABLE `product_groups` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_usage` int(11) DEFAULT NULL,
  `send_msg` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_groups`
--

INSERT INTO `product_groups` (`id`, `job_category_id`, `title`, `km_usage`, `send_msg`, `status`, `deleted_at`) VALUES
(1, 1, 'روغن موتور', 5000, NULL, 1, NULL),
(2, 1, 'روغن ترمز', 1000, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'آذربايجان شرقي', NULL, NULL, NULL),
(2, 'آذربايجان غربي', NULL, NULL, NULL),
(3, 'اردبيل', NULL, NULL, NULL),
(4, 'اصفهان', NULL, NULL, NULL),
(5, 'البرز', NULL, NULL, NULL),
(6, 'ايلام', NULL, NULL, NULL),
(7, 'بوشهر', NULL, NULL, NULL),
(8, 'تهران', NULL, NULL, NULL),
(9, 'چهارمحال بختياري', NULL, NULL, NULL),
(10, 'خراسان جنوبي', NULL, NULL, NULL),
(11, 'خراسان رضوي', NULL, NULL, NULL),
(12, 'خراسان شمالي', NULL, NULL, NULL),
(13, 'خوزستان', NULL, NULL, NULL),
(14, 'زنجان', NULL, NULL, NULL),
(15, 'سمنان', NULL, NULL, NULL),
(16, 'سيستان و بلوچستان', NULL, NULL, NULL),
(17, 'فارس', NULL, NULL, NULL),
(18, 'قزوين', NULL, NULL, NULL),
(19, 'قم', NULL, NULL, NULL),
(20, 'كردستان', NULL, NULL, NULL),
(21, 'كرمان', NULL, NULL, NULL),
(22, 'كرمانشاه', NULL, NULL, NULL),
(23, 'كهكيلويه و بويراحمد', NULL, NULL, NULL),
(24, 'گلستان', NULL, NULL, NULL),
(25, 'گيلان', NULL, NULL, NULL),
(26, 'لرستان', NULL, NULL, NULL),
(27, 'مازندران', NULL, NULL, NULL),
(28, 'مركزي', NULL, NULL, NULL),
(29, 'هرمزگان', NULL, NULL, NULL),
(30, 'همدان', NULL, NULL, NULL),
(31, 'یزد', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quet`
--

CREATE TABLE `quet` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `title`, `created_at`, `updated_at`) VALUES
(1, 'ادمین', '2022-10-30 06:21:08', '2022-10-30 06:21:08'),
(2, 'کاربر', '2022-10-30 06:21:08', '2022-10-30 06:21:08');

-- --------------------------------------------------------

--
-- Table structure for table `score_service_center`
--

CREATE TABLE `score_service_center` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `service_id` bigint(20) UNSIGNED NOT NULL,
  `quet_id` bigint(20) UNSIGNED DEFAULT NULL,
  `score` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `customer_car_id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `km_now` int(11) NOT NULL,
  `km_next` int(11) NOT NULL,
  `service_date_time` datetime NOT NULL,
  `service_status` tinyint(1) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `avg_function` int(11) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0',
  `detail_service` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`id`, `customer_car_id`, `service_center_id`, `km_now`, `km_next`, `service_date_time`, `service_status`, `description`, `status`, `deleted_at`, `avg_function`, `price`, `detail_service`) VALUES
(1, 1, 2, 25000, 75000, '2022-11-09 13:04:28', 1, ' ', 1, NULL, 0, 785000, '[]'),
(2, 1, 2, 45000, 53000, '2022-11-10 00:00:00', 1, 'تست', 1, NULL, 25000, 0, '[]');

-- --------------------------------------------------------

--
-- Stand-in structure for view `servicess`
-- (See below for the actual view)
--
CREATE TABLE `servicess` (
`service_id` bigint(20) unsigned
,`customer_car_id` bigint(20) unsigned
,`service_center_id` bigint(20) unsigned
,`km_now` int(11)
,`km_next` int(11)
,`service_date_time` datetime
,`service_status` tinyint(1)
,`avg_function` int(11)
,`price` int(11)
,`status` tinyint(1)
,`deleted_at` timestamp
,`description` varchar(255)
,`detail_service` text
,`cust_id` bigint(20) unsigned
,`f_name` varchar(255)
,`l_name` varchar(255)
,`fullname` varchar(511)
,`mobile` varchar(255)
,`sex` varchar(255)
,`birthdate` date
,`national_code` varchar(255)
,`profile_photo` text
,`province_id` bigint(20) unsigned
,`city_id` bigint(20) unsigned
,`cust_created_at` datetime
,`cust_deleted_at` timestamp
,`car_id` bigint(20) unsigned
,`car_name_id` bigint(20) unsigned
,`car_name` varchar(255)
,`car_tip_id` bigint(20) unsigned
,`car_tip` varchar(255)
,`car_model_id` bigint(20) unsigned
,`car_model` bigint(11)
,`fuel_type_id` bigint(20) unsigned
,`fuel_type` varchar(255)
,`car_plate` varchar(255)
,`chassis_num` varchar(255)
,`car_created_at` date
,`car_service_center_id` bigint(20) unsigned
,`user_id` bigint(20) unsigned
,`center_id` bigint(20) unsigned
);

-- --------------------------------------------------------

--
-- Table structure for table `services_center`
--

CREATE TABLE `services_center` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `center_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` bigint(20) UNSIGNED DEFAULT NULL,
  `job_category_id` bigint(20) UNSIGNED NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_open` time NOT NULL,
  `f_close` time NOT NULL,
  `l_open` time NOT NULL,
  `l_close` time NOT NULL,
  `numOfBranch` int(11) NOT NULL DEFAULT '0',
  `waiting_place` tinyint(1) NOT NULL DEFAULT '0',
  `bar_serv` tinyint(1) NOT NULL DEFAULT '0',
  `holidays` tinyint(1) NOT NULL DEFAULT '0',
  `mobile_services` tinyint(1) NOT NULL DEFAULT '0',
  `checking` tinyint(1) NOT NULL DEFAULT '0',
  `app_type` tinyint(4) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `charge_total` int(11) NOT NULL DEFAULT '0',
  `charge_remain` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `services_center`
--

INSERT INTO `services_center` (`id`, `user_id`, `center_name`, `parent_id`, `job_category_id`, `phone`, `address`, `f_open`, `f_close`, `l_open`, `l_close`, `numOfBranch`, `waiting_place`, `bar_serv`, `holidays`, `mobile_services`, `checking`, `app_type`, `status`, `created_at`, `deleted_at`, `charge_total`, `charge_remain`) VALUES
(2, 2, 'آرنا', NULL, 1, '09398116292', 'میرزاده عشقی ', '00:00:08', '00:00:13', '00:00:16', '00:00:21', 2, 1, 1, 1, 1, 1, 1, 1, '2022-11-05 03:02:42', NULL, 50000, 49754);

-- --------------------------------------------------------

--
-- Table structure for table `services_detail`
--

CREATE TABLE `services_detail` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `services_id` bigint(20) UNSIGNED NOT NULL,
  `product_group_id` bigint(20) UNSIGNED NOT NULL,
  `product_name_id` bigint(20) UNSIGNED NOT NULL,
  `visited_change` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `services_pay_detail`
--

CREATE TABLE `services_pay_detail` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `services_id` bigint(20) UNSIGNED NOT NULL,
  `invoice_amount` int(11) NOT NULL COMMENT 'The price of the Factor',
  `discount` int(11) DEFAULT NULL,
  `copon_id` bigint(20) UNSIGNED DEFAULT NULL,
  `festival_id` bigint(20) UNSIGNED DEFAULT NULL,
  `pay_type` int(11) NOT NULL,
  `remaining` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `services_timing`
--

CREATE TABLE `services_timing` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `services_id` bigint(20) UNSIGNED NOT NULL,
  `services_detail_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` date NOT NULL,
  `due_date` date NOT NULL,
  `msg_reminder_date` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_available`
--

CREATE TABLE `service_available` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `service_center_id` bigint(20) UNSIGNED NOT NULL,
  `job_service_id` bigint(20) UNSIGNED NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `service_available`
--

INSERT INTO `service_available` (`id`, `service_center_id`, `job_service_id`, `status`) VALUES
(5, 2, 1, 1),
(6, 2, 2, 0),
(7, 2, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

CREATE TABLE `slider` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`id`, `title`, `image`, `url`, `status`, `deleted_at`) VALUES
(1, 'روغن موتور آیسین', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 'https://www.digikala.com/', 0, NULL),
(2, 'روغن موتور آیسین2 ', 'https://www.taminsanatapadana.com/wp-content/uploads/2020/03/OIL-20W50.png', 'https://www.digikala.com/', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `special_sale_request`
--

CREATE TABLE `special_sale_request` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `center_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `presentation_method` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tariff`
--

CREATE TABLE `tariff` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type` tinyint(4) NOT NULL,
  `price` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `profession` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `instagram` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `twitter` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `facebook` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_dir` text COLLATE utf8mb4_unicode_ci,
  `file_dir` text COLLATE utf8mb4_unicode_ci,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `update`
--

CREATE TABLE `update` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `version` decimal(8,2) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `date` date NOT NULL,
  `file_dir` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` tinyint(4) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `f_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `l_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `national_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_type` tinyint(4) NOT NULL,
  `role_id` bigint(20) UNSIGNED NOT NULL DEFAULT '1' COMMENT '0= admin, 1= normalUser',
  `profile_photo` text COLLATE utf8mb4_unicode_ci,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` date DEFAULT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` datetime NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `f_name`, `l_name`, `mobile`, `national_code`, `user_type`, `role_id`, `profile_photo`, `password`, `sex`, `birthdate`, `province_id`, `city_id`, `created_at`, `deleted_at`) VALUES
(1, 'admin', 'مدیر', 'مدیر', '09181230321', NULL, 0, 1, NULL, '$10$uMAtcVcfk2BkZKfi.Hfj3.abbHe1FF0QvoIiaI68WtfrYSIXrS8mG', 'm', NULL, 1, 1, '0000-00-00 00:00:00', NULL),
(2, NULL, 'عرفان ', 'سقاباشی', '09398116292', '', 1, 1, '', '', 'M', '1996-10-20', 30, 7, '2022-11-05 03:02:41', NULL),
(3, NULL, 'احسان', 'سقاباشی۳', '09398116292', '', 2, 2, '', '', 'F', '1996-10-20', 30, 7, '2022-11-10 03:57:15', NULL),
(4, NULL, 'الهه', 'سقاباشی', '09398116292', '', 2, 2, '', '', 'M', '1996-10-20', 30, 7, '2022-11-05 03:02:41', NULL),
(5, NULL, 'علی', 'سقاباشی ', '09398116292', '', 2, 2, '', '', 'M', '1996-04-01', 30, 7, '2022-11-10 03:37:51', NULL),
(6, NULL, 'حسین', 'شیری', '09398116292', '', 2, 2, '', '', 'M', '1996-06-21', 30, 7, '2022-11-10 03:49:46', NULL);

-- --------------------------------------------------------

--
-- Structure for view `cars`
--
DROP TABLE IF EXISTS `cars`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `cars`  AS SELECT `customers_car`.`id` AS `id`, `customers_car`.`car_name_id` AS `car_name_id`, (select `cars_name`.`name` from `cars_name` where (`cars_name`.`id` = `customers_car`.`car_name_id`)) AS `car_name`, `customers_car`.`car_tip_id` AS `car_tip_id`, (select `cars_tip`.`tip` from `cars_tip` where (`cars_tip`.`id` = `customers_car`.`car_tip_id`)) AS `car_tip`, `customers_car`.`car_model_id` AS `car_model_id`, (select `cars_model`.`year` from `cars_model` where (`cars_model`.`id` = `customers_car`.`car_model_id`)) AS `car_model`, `customers_car`.`fuel_type_id` AS `fuel_type_id`, (select `fuel_type`.`type` from `fuel_type` where (`fuel_type`.`id` = `customers_car`.`fuel_type_id`)) AS `fuel_type`, `customers_car`.`service_center_id` AS `service_center_id`, `customers_car`.`user_id` AS `user_id`, `customers_car`.`car_plate` AS `car_plate`, `customers_car`.`chassis_num` AS `chassis_num`, `customers_car`.`created_at` AS `created_at` FROM `customers_car` ;

-- --------------------------------------------------------

--
-- Structure for view `customers`
--
DROP TABLE IF EXISTS `customers`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `customers`  AS SELECT `users`.`id` AS `cust_id`, `users`.`f_name` AS `f_name`, `users`.`l_name` AS `l_name`, `users`.`mobile` AS `mobile`, `users`.`sex` AS `sex`, `users`.`birthdate` AS `birthdate`, `users`.`national_code` AS `national_code`, `users`.`profile_photo` AS `profile_photo`, `users`.`province_id` AS `province_id`, `users`.`city_id` AS `city_id`, `users`.`created_at` AS `cust_created_at`, `users`.`deleted_at` AS `cust_deleted_at`, `cars`.`id` AS `car_id`, `cars`.`car_name_id` AS `car_name_id`, `cars`.`car_name` AS `car_name`, `cars`.`car_tip_id` AS `car_tip_id`, `cars`.`car_tip` AS `car_tip`, `cars`.`car_plate` AS `car_plate`, `cars`.`car_model_id` AS `car_model_id`, `cars`.`car_model` AS `car_model`, `cars`.`fuel_type_id` AS `fuel_type_id`, `cars`.`fuel_type` AS `fuel_type`, `cars`.`chassis_num` AS `chassis_num`, `cars`.`service_center_id` AS `car_service_center_id`, `cars`.`created_at` AS `car_created_at`, `cars`.`user_id` AS `user_id`, (select `services`.`service_center_id` from `services` where (`services`.`customer_car_id` = `cars`.`id`) order by `services`.`id` desc limit 1) AS `center_id` FROM (`users` join `cars` on((`users`.`id` = `cars`.`user_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `msg_log_details`
--
DROP TABLE IF EXISTS `msg_log_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `msg_log_details`  AS SELECT `msg_log`.`id` AS `id`, `msg_log`.`service_center_id` AS `service_center_id`, `msg_log`.`user_id` AS `user_id`, `msg_log`.`content` AS `content`, `msg_log`.`create_at` AS `create_at`, `msg_log`.`send_at` AS `send_at`, `msg_log`.`char_count` AS `char_count`, `msg_log`.`total_price` AS `total_price`, (select concat(`users`.`f_name`,' ',`users`.`l_name`) from `users` where (`users`.`id` = `msg_log`.`user_id`)) AS `user_fullname`, (select `users`.`mobile` from `users` where (`users`.`id` = `msg_log`.`user_id`)) AS `user_mobile` FROM `msg_log` ;

-- --------------------------------------------------------

--
-- Structure for view `servicess`
--
DROP TABLE IF EXISTS `servicess`;

CREATE ALGORITHM=UNDEFINED DEFINER=`baadbaan`@`localhost` SQL SECURITY DEFINER VIEW `servicess`  AS  (select `services`.`id` AS `service_id`,`services`.`customer_car_id` AS `customer_car_id`,`services`.`service_center_id` AS `service_center_id`,`services`.`km_now` AS `km_now`,`services`.`km_next` AS `km_next`,`services`.`service_date_time` AS `service_date_time`,`services`.`service_status` AS `service_status`,`services`.`avg_function` AS `avg_function`,`services`.`price` AS `price`,`services`.`status` AS `status`,`services`.`deleted_at` AS `deleted_at`,`services`.`description` AS `description`,`services`.`detail_service` AS `detail_service`,`customers`.`cust_id` AS `cust_id`,`customers`.`f_name` AS `f_name`,`customers`.`l_name` AS `l_name`,concat(`customers`.`f_name`,' ',`customers`.`l_name`) AS `fullname`,`customers`.`mobile` AS `mobile`,`customers`.`sex` AS `sex`,`customers`.`birthdate` AS `birthdate`,`customers`.`national_code` AS `national_code`,`customers`.`profile_photo` AS `profile_photo`,`customers`.`province_id` AS `province_id`,`customers`.`city_id` AS `city_id`,`customers`.`cust_created_at` AS `cust_created_at`,`customers`.`cust_deleted_at` AS `cust_deleted_at`,`customers`.`car_id` AS `car_id`,`customers`.`car_name_id` AS `car_name_id`,`customers`.`car_name` AS `car_name`,`customers`.`car_tip_id` AS `car_tip_id`,`customers`.`car_tip` AS `car_tip`,`customers`.`car_model_id` AS `car_model_id`,`customers`.`car_model` AS `car_model`,`customers`.`fuel_type_id` AS `fuel_type_id`,`customers`.`fuel_type` AS `fuel_type`,`customers`.`car_plate` AS `car_plate`,`customers`.`chassis_num` AS `chassis_num`,`customers`.`car_created_at` AS `car_created_at`,`customers`.`car_service_center_id` AS `car_service_center_id`,`customers`.`user_id` AS `user_id`,`customers`.`center_id` AS `center_id` from (`services` join `customers` on((`services`.`customer_car_id` = `customers`.`car_id`)))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `about_us`
--
ALTER TABLE `about_us`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `access_routes`
--
ALTER TABLE `access_routes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `access_routes_role_id_foreign` (`role_id`);

--
-- Indexes for table `average_function`
--
ALTER TABLE `average_function`
  ADD PRIMARY KEY (`id`),
  ADD KEY `average_function_customer_car_id_foreign` (`customer_car_id`);

--
-- Indexes for table `backup`
--
ALTER TABLE `backup`
  ADD PRIMARY KEY (`id`),
  ADD KEY `backup_service_center_id_foreign` (`service_center_id`);

--
-- Indexes for table `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `blogs_user_id_foreign` (`user_id`),
  ADD KEY `blogs_blog_category_id_foreign` (`blog_category_id`);

--
-- Indexes for table `blog_categories`
--
ALTER TABLE `blog_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `blog_categories_slug_unique` (`slug`);

--
-- Indexes for table `branch_request`
--
ALTER TABLE `branch_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `branch_request_province_id_foreign` (`province_id`),
  ADD KEY `branch_request_city_id_foreign` (`city_id`);

--
-- Indexes for table `cars_company`
--
ALTER TABLE `cars_company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars_model`
--
ALTER TABLE `cars_model`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cars_name`
--
ALTER TABLE `cars_name`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_name_car_company_id_foreign` (`car_company_id`);

--
-- Indexes for table `cars_tip`
--
ALTER TABLE `cars_tip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_tip_car_name_id_foreign` (`car_name_id`);

--
-- Indexes for table `car_info`
--
ALTER TABLE `car_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `car_info_car_name_id_foreign` (`car_name_id`),
  ADD KEY `car_info_car_tip_id_foreign` (`car_tip_id`),
  ADD KEY `car_info_car_model_id_foreign` (`car_model_id`),
  ADD KEY `car_info_fuel_type_id_foreign` (`fuel_type_id`),
  ADD KEY `car_info_job_category_id_foreign` (`job_category_id`),
  ADD KEY `car_info_product_group_id_foreign` (`product_group_id`);

--
-- Indexes for table `charging_package`
--
ALTER TABLE `charging_package`
  ADD PRIMARY KEY (`id`),
  ADD KEY `charging_package_festival_id_foreign` (`festival_id`);

--
-- Indexes for table `charging_package_log`
--
ALTER TABLE `charging_package_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `charging_package_log_service_center_id_foreign` (`service_center_id`),
  ADD KEY `charging_package_log_charging_package_id_foreign` (`charging_package_id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cities_province_id_foreign` (`province_id`);

--
-- Indexes for table `comments_blog`
--
ALTER TABLE `comments_blog`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_blog_user_id_foreign` (`user_id`),
  ADD KEY `comments_blog_blog_id_foreign` (`blog_id`),
  ADD KEY `comments_blog_parent_id_foreign` (`parent_id`);

--
-- Indexes for table `comments_product`
--
ALTER TABLE `comments_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_product_user_id_foreign` (`user_id`),
  ADD KEY `comments_product_product_name_id_foreign` (`product_name_id`);

--
-- Indexes for table `comments_service_center`
--
ALTER TABLE `comments_service_center`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_service_center_user_id_foreign` (`user_id`),
  ADD KEY `comments_service_center_service_center_id_foreign` (`service_center_id`),
  ADD KEY `comments_service_center_parent_id_foreign` (`parent_id`);

--
-- Indexes for table `contact_us`
--
ALTER TABLE `contact_us`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `copon`
--
ALTER TABLE `copon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `copon_msg_prov_id_foreign` (`msg_prov_id`);

--
-- Indexes for table `customers_car`
--
ALTER TABLE `customers_car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customers_car_car_name_id_foreign` (`car_name_id`),
  ADD KEY `customers_car_car_tip_id_foreign` (`car_tip_id`),
  ADD KEY `customers_car_car_model_id_foreign` (`car_model_id`),
  ADD KEY `customers_car_fuel_type_id_foreign` (`fuel_type_id`),
  ADD KEY `customers_car_service_center_id_foreign` (`service_center_id`),
  ADD KEY `customers_car_user_id_foreign` (`user_id`);

--
-- Indexes for table `downloads`
--
ALTER TABLE `downloads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `festival`
--
ALTER TABLE `festival`
  ADD PRIMARY KEY (`id`),
  ADD KEY `festival_msg_prov_id_foreign` (`msg_prov_id`);

--
-- Indexes for table `fuel_type`
--
ALTER TABLE `fuel_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hero_sliders`
--
ALTER TABLE `hero_sliders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `introduce`
--
ALTER TABLE `introduce`
  ADD PRIMARY KEY (`id`),
  ADD KEY `introduce_user_id_foreign` (`user_id`);

--
-- Indexes for table `job_categories`
--
ALTER TABLE `job_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_services`
--
ALTER TABLE `job_services`
  ADD PRIMARY KEY (`id`),
  ADD KEY `job_services_job_category_id_foreign` (`job_category_id`);

--
-- Indexes for table `login_log`
--
ALTER TABLE `login_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `login_log_user_id_foreign` (`user_id`);

--
-- Indexes for table `meta_keyword`
--
ALTER TABLE `meta_keyword`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mobile_operation`
--
ALTER TABLE `mobile_operation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `msg_draft`
--
ALTER TABLE `msg_draft`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_draft_service_center_id_foreign` (`service_center_id`);

--
-- Indexes for table `msg_log`
--
ALTER TABLE `msg_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_log_user_id_foreign` (`user_id`),
  ADD KEY `msg_log_service_center_id_foreign` (`service_center_id`);

--
-- Indexes for table `msg_num_reminder`
--
ALTER TABLE `msg_num_reminder`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_num_reminder_service_timing_id_foreign` (`service_timing_id`);

--
-- Indexes for table `msg_pricing`
--
ALTER TABLE `msg_pricing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_pricing_mobile_operation_id_foreign` (`mobile_operation_id`);

--
-- Indexes for table `msg_prov`
--
ALTER TABLE `msg_prov`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_prov_msg_title_id_foreign` (`msg_title_id`);

--
-- Indexes for table `msg_timing`
--
ALTER TABLE `msg_timing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `msg_timing_service_center_id_foreign` (`service_center_id`),
  ADD KEY `msg_timing_user_id_foreign` (`user_id`);

--
-- Indexes for table `msg_title`
--
ALTER TABLE `msg_title`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notification_user_id_foreign` (`user_id`),
  ADD KEY `notification_job_category_id_foreign` (`job_category_id`),
  ADD KEY `notification_province_id_foreign` (`province_id`),
  ADD KEY `notification_city_id_foreign` (`city_id`);

--
-- Indexes for table `notification_log`
--
ALTER TABLE `notification_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notification_log_notification_id_foreign` (`notification_id`),
  ADD KEY `notification_log_service_center_id_foreign` (`service_center_id`);

--
-- Indexes for table `oauth_access_tokens`
--
ALTER TABLE `oauth_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_access_tokens_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_auth_codes`
--
ALTER TABLE `oauth_auth_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_auth_codes_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_clients_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_refresh_tokens`
--
ALTER TABLE `oauth_refresh_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`);

--
-- Indexes for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indexes for table `products_name`
--
ALTER TABLE `products_name`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_name_product_group_id_foreign` (`product_group_id`);

--
-- Indexes for table `product_groups`
--
ALTER TABLE `product_groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_groups_job_category_id_foreign` (`job_category_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provinces`
--
ALTER TABLE `provinces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quet`
--
ALTER TABLE `quet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `score_service_center`
--
ALTER TABLE `score_service_center`
  ADD PRIMARY KEY (`id`),
  ADD KEY `score_service_center_user_id_foreign` (`user_id`),
  ADD KEY `score_service_center_service_center_id_foreign` (`service_center_id`),
  ADD KEY `score_service_center_service_id_foreign` (`service_id`),
  ADD KEY `score_service_center_quet_id_foreign` (`quet_id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_customer_car_id_foreign` (`customer_car_id`),
  ADD KEY `services_service_center_id_foreign` (`service_center_id`);

--
-- Indexes for table `services_center`
--
ALTER TABLE `services_center`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_center_user_id_foreign` (`user_id`),
  ADD KEY `services_center_parent_id_foreign` (`parent_id`),
  ADD KEY `services_center_job_category_id_foreign` (`job_category_id`);

--
-- Indexes for table `services_detail`
--
ALTER TABLE `services_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_detail_services_id_foreign` (`services_id`),
  ADD KEY `services_detail_product_group_id_foreign` (`product_group_id`),
  ADD KEY `services_detail_product_name_id_foreign` (`product_name_id`);

--
-- Indexes for table `services_pay_detail`
--
ALTER TABLE `services_pay_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_pay_detail_services_id_foreign` (`services_id`),
  ADD KEY `services_pay_detail_copon_id_foreign` (`copon_id`),
  ADD KEY `services_pay_detail_festival_id_foreign` (`festival_id`);

--
-- Indexes for table `services_timing`
--
ALTER TABLE `services_timing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_timing_services_id_foreign` (`services_id`),
  ADD KEY `services_timing_services_detail_id_foreign` (`services_detail_id`);

--
-- Indexes for table `service_available`
--
ALTER TABLE `service_available`
  ADD PRIMARY KEY (`id`),
  ADD KEY `service_available_service_center_id_foreign` (`service_center_id`),
  ADD KEY `service_available_job_service_id_foreign` (`job_service_id`);

--
-- Indexes for table `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `special_sale_request`
--
ALTER TABLE `special_sale_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `special_sale_request_province_id_foreign` (`province_id`),
  ADD KEY `special_sale_request_city_id_foreign` (`city_id`);

--
-- Indexes for table `tariff`
--
ALTER TABLE `tariff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ticket_user_id_foreign` (`user_id`);

--
-- Indexes for table `update`
--
ALTER TABLE `update`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_username_unique` (`username`),
  ADD KEY `users_city_id_foreign` (`city_id`),
  ADD KEY `users_role_id_foreign` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `about_us`
--
ALTER TABLE `about_us`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `access_routes`
--
ALTER TABLE `access_routes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `average_function`
--
ALTER TABLE `average_function`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `backup`
--
ALTER TABLE `backup`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `blog_categories`
--
ALTER TABLE `blog_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `branch_request`
--
ALTER TABLE `branch_request`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_company`
--
ALTER TABLE `cars_company`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_model`
--
ALTER TABLE `cars_model`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_name`
--
ALTER TABLE `cars_name`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_tip`
--
ALTER TABLE `cars_tip`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `car_info`
--
ALTER TABLE `car_info`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `charging_package`
--
ALTER TABLE `charging_package`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `charging_package_log`
--
ALTER TABLE `charging_package_log`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=455;

--
-- AUTO_INCREMENT for table `comments_blog`
--
ALTER TABLE `comments_blog`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comments_product`
--
ALTER TABLE `comments_product`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comments_service_center`
--
ALTER TABLE `comments_service_center`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact_us`
--
ALTER TABLE `contact_us`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `copon`
--
ALTER TABLE `copon`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customers_car`
--
ALTER TABLE `customers_car`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `downloads`
--
ALTER TABLE `downloads`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `festival`
--
ALTER TABLE `festival`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fuel_type`
--
ALTER TABLE `fuel_type`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `hero_sliders`
--
ALTER TABLE `hero_sliders`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `introduce`
--
ALTER TABLE `introduce`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_categories`
--
ALTER TABLE `job_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `job_services`
--
ALTER TABLE `job_services`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `login_log`
--
ALTER TABLE `login_log`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `meta_keyword`
--
ALTER TABLE `meta_keyword`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `mobile_operation`
--
ALTER TABLE `mobile_operation`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `msg_draft`
--
ALTER TABLE `msg_draft`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `msg_log`
--
ALTER TABLE `msg_log`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `msg_num_reminder`
--
ALTER TABLE `msg_num_reminder`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `msg_pricing`
--
ALTER TABLE `msg_pricing`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `msg_prov`
--
ALTER TABLE `msg_prov`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `msg_timing`
--
ALTER TABLE `msg_timing`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `msg_title`
--
ALTER TABLE `msg_title`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification_log`
--
ALTER TABLE `notification_log`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products_name`
--
ALTER TABLE `products_name`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product_groups`
--
ALTER TABLE `product_groups`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `quet`
--
ALTER TABLE `quet`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `score_service_center`
--
ALTER TABLE `score_service_center`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `services_center`
--
ALTER TABLE `services_center`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `services_detail`
--
ALTER TABLE `services_detail`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services_pay_detail`
--
ALTER TABLE `services_pay_detail`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services_timing`
--
ALTER TABLE `services_timing`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service_available`
--
ALTER TABLE `service_available`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `slider`
--
ALTER TABLE `slider`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `special_sale_request`
--
ALTER TABLE `special_sale_request`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tariff`
--
ALTER TABLE `tariff`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `update`
--
ALTER TABLE `update`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `access_routes`
--
ALTER TABLE `access_routes`
  ADD CONSTRAINT `access_routes_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `average_function`
--
ALTER TABLE `average_function`
  ADD CONSTRAINT `average_function_customer_car_id_foreign` FOREIGN KEY (`customer_car_id`) REFERENCES `customers_car` (`id`);

--
-- Constraints for table `backup`
--
ALTER TABLE `backup`
  ADD CONSTRAINT `backup_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `blogs`
--
ALTER TABLE `blogs`
  ADD CONSTRAINT `blogs_blog_category_id_foreign` FOREIGN KEY (`blog_category_id`) REFERENCES `blog_categories` (`id`),
  ADD CONSTRAINT `blogs_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `branch_request`
--
ALTER TABLE `branch_request`
  ADD CONSTRAINT `branch_request_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `branch_request_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`);

--
-- Constraints for table `cars_name`
--
ALTER TABLE `cars_name`
  ADD CONSTRAINT `cars_name_car_company_id_foreign` FOREIGN KEY (`car_company_id`) REFERENCES `cars_company` (`id`);

--
-- Constraints for table `cars_tip`
--
ALTER TABLE `cars_tip`
  ADD CONSTRAINT `cars_tip_car_name_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`);

--
-- Constraints for table `car_info`
--
ALTER TABLE `car_info`
  ADD CONSTRAINT `car_info_car_model_id_foreign` FOREIGN KEY (`car_model_id`) REFERENCES `cars_model` (`id`),
  ADD CONSTRAINT `car_info_car_name_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`),
  ADD CONSTRAINT `car_info_car_tip_id_foreign` FOREIGN KEY (`car_tip_id`) REFERENCES `cars_tip` (`id`),
  ADD CONSTRAINT `car_info_fuel_type_id_foreign` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_type` (`id`),
  ADD CONSTRAINT `car_info_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`),
  ADD CONSTRAINT `car_info_product_group_id_foreign` FOREIGN KEY (`product_group_id`) REFERENCES `product_groups` (`id`);

--
-- Constraints for table `charging_package`
--
ALTER TABLE `charging_package`
  ADD CONSTRAINT `charging_package_festival_id_foreign` FOREIGN KEY (`festival_id`) REFERENCES `festival` (`id`);

--
-- Constraints for table `charging_package_log`
--
ALTER TABLE `charging_package_log`
  ADD CONSTRAINT `charging_package_log_charging_package_id_foreign` FOREIGN KEY (`charging_package_id`) REFERENCES `charging_package` (`id`),
  ADD CONSTRAINT `charging_package_log_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`);

--
-- Constraints for table `comments_blog`
--
ALTER TABLE `comments_blog`
  ADD CONSTRAINT `comments_blog_blog_id_foreign` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`),
  ADD CONSTRAINT `comments_blog_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `comments_blog` (`id`),
  ADD CONSTRAINT `comments_blog_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `comments_product`
--
ALTER TABLE `comments_product`
  ADD CONSTRAINT `comments_product_product_name_id_foreign` FOREIGN KEY (`product_name_id`) REFERENCES `products_name` (`id`),
  ADD CONSTRAINT `comments_product_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `comments_service_center`
--
ALTER TABLE `comments_service_center`
  ADD CONSTRAINT `comments_service_center_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `comments_service_center` (`id`),
  ADD CONSTRAINT `comments_service_center_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `comments_service_center_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `copon`
--
ALTER TABLE `copon`
  ADD CONSTRAINT `copon_msg_prov_id_foreign` FOREIGN KEY (`msg_prov_id`) REFERENCES `msg_prov` (`id`);

--
-- Constraints for table `customers_car`
--
ALTER TABLE `customers_car`
  ADD CONSTRAINT `customers_car_car_model_id_foreign` FOREIGN KEY (`car_model_id`) REFERENCES `cars_model` (`id`),
  ADD CONSTRAINT `customers_car_car_name_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`),
  ADD CONSTRAINT `customers_car_car_tip_id_foreign` FOREIGN KEY (`car_tip_id`) REFERENCES `cars_tip` (`id`),
  ADD CONSTRAINT `customers_car_fuel_type_id_foreign` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_type` (`id`),
  ADD CONSTRAINT `customers_car_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `customers_car_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `festival`
--
ALTER TABLE `festival`
  ADD CONSTRAINT `festival_msg_prov_id_foreign` FOREIGN KEY (`msg_prov_id`) REFERENCES `msg_prov` (`id`);

--
-- Constraints for table `introduce`
--
ALTER TABLE `introduce`
  ADD CONSTRAINT `introduce_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `job_services`
--
ALTER TABLE `job_services`
  ADD CONSTRAINT `job_services_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`);

--
-- Constraints for table `login_log`
--
ALTER TABLE `login_log`
  ADD CONSTRAINT `login_log_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `msg_draft`
--
ALTER TABLE `msg_draft`
  ADD CONSTRAINT `msg_draft_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `msg_log`
--
ALTER TABLE `msg_log`
  ADD CONSTRAINT `msg_log_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `msg_log_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `msg_num_reminder`
--
ALTER TABLE `msg_num_reminder`
  ADD CONSTRAINT `msg_num_reminder_service_timing_id_foreign` FOREIGN KEY (`service_timing_id`) REFERENCES `services_timing` (`id`);

--
-- Constraints for table `msg_pricing`
--
ALTER TABLE `msg_pricing`
  ADD CONSTRAINT `msg_pricing_mobile_operation_id_foreign` FOREIGN KEY (`mobile_operation_id`) REFERENCES `mobile_operation` (`id`);

--
-- Constraints for table `msg_prov`
--
ALTER TABLE `msg_prov`
  ADD CONSTRAINT `msg_prov_msg_title_id_foreign` FOREIGN KEY (`msg_title_id`) REFERENCES `msg_title` (`id`);

--
-- Constraints for table `msg_timing`
--
ALTER TABLE `msg_timing`
  ADD CONSTRAINT `msg_timing_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `msg_timing_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `notification_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`),
  ADD CONSTRAINT `notification_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`),
  ADD CONSTRAINT `notification_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `notification_log`
--
ALTER TABLE `notification_log`
  ADD CONSTRAINT `notification_log_notification_id_foreign` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`),
  ADD CONSTRAINT `notification_log_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `products_name`
--
ALTER TABLE `products_name`
  ADD CONSTRAINT `products_name_product_group_id_foreign` FOREIGN KEY (`product_group_id`) REFERENCES `product_groups` (`id`);

--
-- Constraints for table `product_groups`
--
ALTER TABLE `product_groups`
  ADD CONSTRAINT `product_groups_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`);

--
-- Constraints for table `score_service_center`
--
ALTER TABLE `score_service_center`
  ADD CONSTRAINT `score_service_center_quet_id_foreign` FOREIGN KEY (`quet_id`) REFERENCES `quet` (`id`),
  ADD CONSTRAINT `score_service_center_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `score_service_center_service_id_foreign` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`),
  ADD CONSTRAINT `score_service_center_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `services_customer_car_id_foreign` FOREIGN KEY (`customer_car_id`) REFERENCES `customers_car` (`id`),
  ADD CONSTRAINT `services_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `services_center`
--
ALTER TABLE `services_center`
  ADD CONSTRAINT `services_center_job_category_id_foreign` FOREIGN KEY (`job_category_id`) REFERENCES `job_categories` (`id`),
  ADD CONSTRAINT `services_center_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `services_center` (`id`),
  ADD CONSTRAINT `services_center_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `services_detail`
--
ALTER TABLE `services_detail`
  ADD CONSTRAINT `services_detail_product_group_id_foreign` FOREIGN KEY (`product_group_id`) REFERENCES `product_groups` (`id`),
  ADD CONSTRAINT `services_detail_product_name_id_foreign` FOREIGN KEY (`product_name_id`) REFERENCES `products_name` (`id`),
  ADD CONSTRAINT `services_detail_services_id_foreign` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`);

--
-- Constraints for table `services_pay_detail`
--
ALTER TABLE `services_pay_detail`
  ADD CONSTRAINT `services_pay_detail_copon_id_foreign` FOREIGN KEY (`copon_id`) REFERENCES `copon` (`id`),
  ADD CONSTRAINT `services_pay_detail_festival_id_foreign` FOREIGN KEY (`festival_id`) REFERENCES `festival` (`id`),
  ADD CONSTRAINT `services_pay_detail_services_id_foreign` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`);

--
-- Constraints for table `services_timing`
--
ALTER TABLE `services_timing`
  ADD CONSTRAINT `services_timing_services_detail_id_foreign` FOREIGN KEY (`services_detail_id`) REFERENCES `services_detail` (`id`),
  ADD CONSTRAINT `services_timing_services_id_foreign` FOREIGN KEY (`services_id`) REFERENCES `services` (`id`);

--
-- Constraints for table `service_available`
--
ALTER TABLE `service_available`
  ADD CONSTRAINT `service_available_job_service_id_foreign` FOREIGN KEY (`job_service_id`) REFERENCES `job_services` (`id`),
  ADD CONSTRAINT `service_available_service_center_id_foreign` FOREIGN KEY (`service_center_id`) REFERENCES `services_center` (`id`);

--
-- Constraints for table `special_sale_request`
--
ALTER TABLE `special_sale_request`
  ADD CONSTRAINT `special_sale_request_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `special_sale_request_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  ADD CONSTRAINT `users_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
--
-- Database: `baadbaan_ServiceA_db`
--
CREATE DATABASE IF NOT EXISTS `baadbaan_ServiceA_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `baadbaan_ServiceA_db`;

-- --------------------------------------------------------

--
-- Table structure for table `amazing_sales`
--

CREATE TABLE `amazing_sales` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `percentage` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `send_notification` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `amazing_sales`
--

INSERT INTO `amazing_sales` (`id`, `product_id`, `percentage`, `status`, `send_notification`, `start_date`, `end_date`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 50, 0, 0, '2022-07-10 21:12:09', '2022-07-08 21:12:09', '2022-07-07 06:15:05', '2022-07-10 05:23:19', NULL),
(2, 1, 10, 1, 0, '2022-06-26 09:42:22', '2022-07-07 09:42:22', '2022-07-07 09:42:33', '2022-07-07 09:42:33', NULL),
(3, 1, 20, 1, 0, '2022-07-17 09:44:25', '2022-07-20 09:44:25', '2022-07-07 09:44:34', '2022-07-07 10:15:38', NULL),
(4, 1, 70, 0, 0, '2022-07-07 09:45:53', '2022-07-07 09:45:53', '2022-07-07 09:45:58', '2022-07-07 09:45:58', NULL),
(5, 1, 20, 0, 0, '2022-07-07 09:46:24', '2022-07-07 09:46:24', '2022-07-07 09:46:28', '2022-07-07 09:46:28', NULL),
(6, 1, 20, 0, 0, '2022-07-07 09:46:57', '2022-07-07 09:46:57', '2022-07-07 09:47:01', '2022-07-07 09:47:01', NULL),
(7, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:03', '2022-07-07 09:48:03', NULL),
(8, 1, 20, 0, 0, '2022-07-07 09:47:21', '2022-07-07 09:47:21', '2022-07-07 09:48:13', '2022-07-07 09:48:13', NULL),
(9, 1, 70, 0, 0, '2022-07-07 09:48:49', '2022-07-07 09:48:49', '2022-07-07 09:48:57', '2022-07-07 09:48:57', NULL),
(10, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:38', '2022-07-07 09:52:38', NULL),
(11, 1, 10, 0, 0, '2022-07-07 09:52:33', '2022-07-07 09:52:33', '2022-07-07 09:52:59', '2022-07-07 09:52:59', NULL),
(12, 1, 20, 0, 0, '2022-07-07 10:07:31', '2022-07-07 10:07:31', '2022-07-07 10:07:42', '2022-07-07 10:07:42', NULL),
(13, 1, 20, 1, 0, '2022-07-07 10:07:57', '2022-07-07 10:07:57', '2022-07-07 10:08:02', '2022-07-07 10:08:02', NULL),
(14, 1, 10, 1, 0, '2022-07-07 10:09:59', '2022-07-07 10:09:59', '2022-07-07 10:10:06', '2022-07-07 10:10:06', NULL),
(15, 1, 10, 1, 0, '2022-07-07 10:18:26', '2022-07-07 10:18:26', '2022-07-07 10:18:30', '2022-07-07 10:18:30', NULL),
(16, 1, 20, 1, 0, '2022-07-07 18:04:03', '2022-07-07 18:04:04', '2022-07-07 18:04:13', '2022-07-07 18:04:13', NULL),
(17, 1, 20, 1, 0, '2022-07-07 19:09:38', '2022-07-07 19:09:38', '2022-07-07 19:09:45', '2022-07-07 19:09:45', NULL),
(18, 1, 10, 1, 1, '2022-07-07 19:11:13', '2022-07-07 19:11:13', '2022-07-07 19:11:21', '2022-07-07 19:11:21', NULL),
(19, 1, 10, 1, 1, '2022-07-07 21:21:04', '2022-07-07 21:21:04', '2022-07-07 21:21:10', '2022-07-07 21:21:10', NULL),
(20, 1, 20, 0, 1, '2022-07-07 21:21:55', '2022-07-07 21:21:55', '2022-07-07 21:22:01', '2022-07-07 21:22:01', NULL),
(21, 1, 10, 0, 1, '2022-07-08 19:58:05', '2022-07-08 19:58:05', '2022-07-08 19:58:12', '2022-07-08 19:58:12', NULL),
(22, 1, 10, 1, 1, '2022-07-10 05:18:22', '2022-07-10 05:18:22', '2022-07-10 05:18:43', '2022-07-10 05:18:43', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `back_up`
--

CREATE TABLE `back_up` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `department_id` bigint(20) UNSIGNED NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `file` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `banners`
--

CREATE TABLE `banners` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'developer explain 0 or 1 or ... in admin\\content\\banner model',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `buys`
--

CREATE TABLE `buys` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `service_center` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `tariffe_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `buys`
--

INSERT INTO `buys` (`id`, `name`, `email`, `mobile`, `type`, `service_center`, `address`, `tariffe_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'hffgfg', 'dasdasd@dadsa', '09997777656', 2, 'gjghjh', 'fdgfdgdgf', 3, '2022-07-16 10:52:57', '2022-07-16 10:52:57', NULL),
(2, 'hffgfg', 'salam@salam.com', '09398777656', 2, 'gjghjh', 'dfgdfdgfdfgfdg', 5, '2022-07-16 10:53:56', '2022-07-16 10:53:56', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `car_type` tinyint(4) NOT NULL DEFAULT '0',
  `fuel_type` tinyint(4) NOT NULL DEFAULT '0',
  `car_tag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `car_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `avg_function` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `chassei_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`id`, `car_name_id`, `user_id`, `slug`, `car_type`, `fuel_type`, `car_tag`, `car_created`, `avg_function`, `chassei_number`, `status`, `created_at`, `updated_at`, `deleted_at`, `province_id`, `city_id`) VALUES
(21, 1, 9, '92s57261', 0, 0, '92s57261', '2022-07-24 08:03:00', '333', '333', 1, '2022-07-24 08:02:23', '2022-07-24 08:03:00', NULL, 2, 2),
(22, 1, 9, '92p57261', 0, 0, '92p67261', '2022-07-24 08:05:19', '333', '333', 1, '2022-07-24 08:02:44', '2022-07-24 08:05:19', NULL, 2, 2),
(23, 1, 9, '09876543', 1, 0, '09876543', '2022-07-24 08:05:42', '333', '777', 1, '2022-07-24 08:05:42', '2022-07-24 08:05:42', NULL, 2, 2),
(24, 1, 9, '09776543', 1, 0, '09776543', '2022-07-24 08:06:44', '333', '777', 1, '2022-07-24 08:06:44', '2022-07-24 08:06:44', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cars_model`
--

CREATE TABLE `cars_model` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tip_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_model`
--

INSERT INTO `cars_model` (`id`, `model`, `tip_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, '2022-07-11 15:19:08', 1, '2022-07-11 10:49:11', '2022-07-11 10:49:11', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_name`
--

CREATE TABLE `cars_name` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_photo_path` text COLLATE utf8mb4_unicode_ci,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_type` tinyint(4) NOT NULL DEFAULT '0',
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_name`
--

INSERT INTO `cars_name` (`id`, `name`, `profile_photo_path`, `alt`, `car_type`, `slug`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'سمند', '{\"indexArray\":{\"large\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573974\\\\1656573974.large.jpg\",\"medium\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573974\\\\1656573974.medium.jpg\",\"small\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573974\\\\1656573974.small.jpg\"},\"directory\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573974\",\"currentImage\":\"medium\"}', '', 0, 'smnd', 1, '2022-06-30 02:56:14', '2022-06-30 02:56:14', NULL),
(2, 'پراید', '{\"indexArray\":{\"large\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573992\\\\1656573992.large.jpg\",\"medium\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573992\\\\1656573992.medium.jpg\",\"small\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573992\\\\1656573992.small.jpg\"},\"directory\":\"images\\\\carName\\\\22\\\\06\\\\30\\\\1656573992\",\"currentImage\":\"medium\"}', '', 0, 'r-d', 0, '2022-06-30 02:56:32', '2022-07-10 06:00:17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars_tip`
--

CREATE TABLE `cars_tip` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tip` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cars_tip`
--

INSERT INTO `cars_tip` (`id`, `tip`, `car_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'ef7', 1, '2022-07-11 10:49:00', '2022-07-11 10:49:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `car_galleries`
--

CREATE TABLE `car_galleries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `car_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chassei_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avg_function` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_tag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fuel_type` tinyint(4) NOT NULL DEFAULT '0',
  `car_type` tinyint(4) NOT NULL DEFAULT '0',
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `car_galleries`
--

INSERT INTO `car_galleries` (`id`, `car_name_id`, `car_create`, `chassei_number`, `avg_function`, `car_tag`, `fuel_type`, `car_type`, `car_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(22, 1, '2022-07-24 08:06:38', '777', '333', '09776543', 0, 1, 24, '2022-07-24 08:06:44', '2022-07-24 08:06:44', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `city`, `status`, `created_at`, `updated_at`, `deleted_at`, `province_id`) VALUES
(1, 'تهران', 1, '2022-06-30 02:57:32', '2022-06-30 05:04:54', NULL, 1),
(2, 'همدان', 1, '2022-06-30 04:51:06', '2022-06-30 07:01:39', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` bigint(20) UNSIGNED DEFAULT NULL,
  `author_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `commentable_id` bigint(20) UNSIGNED NOT NULL,
  `commentable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `seen` tinyint(4) NOT NULL DEFAULT '0' COMMENT ' 0 => unseen , 1 =>seen',
  `approved` tinyint(4) NOT NULL DEFAULT '0' COMMENT ' 0 => not approved , 1 => approved',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `body`, `parent_id`, `author_id`, `name`, `commentable_id`, `commentable_type`, `seen`, `approved`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'refgtrettret', NULL, NULL, NULL, 1, 'App\\Models\\Content\\SitePost', 1, 1, 0, '2022-07-16 07:41:24', '2022-07-16 07:41:49', NULL),
(2, 'vbfvbvbvbvbvbv', NULL, NULL, NULL, 1, 'App\\Models\\Content\\SitePost', 0, 0, 0, '2022-07-23 13:28:43', '2022-07-23 13:28:43', NULL),
(3, 'yfyufyfufuiogiug', NULL, NULL, NULL, 1, 'App\\Models\\Content\\SitePost', 0, 0, 0, '2022-07-23 13:30:09', '2022-07-23 13:30:09', NULL),
(4, 'yfyufyfufuiogiug', NULL, NULL, NULL, 1, 'App\\Models\\Content\\SitePost', 0, 0, 0, '2022-07-23 13:30:30', '2022-07-23 13:30:30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `common_discount`
--

CREATE TABLE `common_discount` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `percentage` int(11) NOT NULL,
  `discount_celling` bigint(20) UNSIGNED DEFAULT NULL,
  `minimal_order_amount` bigint(20) UNSIGNED DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `copans`
--

CREATE TABLE `copans` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => percentage , 1 => price unit',
  `discount_celling` bigint(20) UNSIGNED DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => common (each user can use one time) , 1 => private (one user can use one time)',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `downloads`
--

CREATE TABLE `downloads` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `service_center` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `downloads`
--

INSERT INTO `downloads` (`id`, `name`, `service_center`, `address`, `email`, `mobile`, `type`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'پرداخت', 'xccxvxcv', 'vbffcxcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-25 08:31:58', NULL),
(2, 'پرداخت', 'vcxvcvxcv', 'cxvcvxcvcxv', 'amir79077@gmail.com', '09398727657', 2, '2022-07-20 11:28:44', '2022-07-20 11:28:44', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `emails`
--

CREATE TABLE `emails` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `subject` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `emails`
--

INSERT INTO `emails` (`id`, `subject`, `body`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'fdgfdgfdg', '<p>fghgfhfghfggfh</p>', 1, '2022-07-06 05:24:56', '2022-07-06 05:24:56', NULL),
(2, 'dvcxvc', '<p>sdfsassadsf</p>', 1, '2022-07-06 05:44:43', '2022-07-06 05:44:43', NULL),
(3, 'dvcxvc', '<p>sdfsdfsfds</p>', 0, '2022-07-06 05:55:07', '2022-07-06 05:55:07', NULL),
(4, 'daada', '<p>ASSADASD</p>', 1, '2022-07-06 05:59:08', '2022-07-06 05:59:08', NULL),
(5, 'fdgfdgfdg', '<p>DRFSSDFSF</p>', 1, '2022-07-06 06:00:12', '2022-07-06 06:35:52', NULL),
(6, 'daada', '<p>sadewqsadadsa</p>', 1, '2022-07-06 06:03:55', '2022-07-06 06:29:37', NULL),
(7, 'fdgfdgfdg', '<p>asdsadasdsad</p>', 1, '2022-07-06 06:04:14', '2022-07-06 06:04:14', NULL),
(8, 'dvcxvc', '<p>sfsdfsdfdsassadfsd</p>', 1, '2022-07-06 06:11:06', '2022-07-06 06:11:06', NULL),
(9, 'dvcxvc', '<p>sdfsdsdfsdfdsf</p>', 1, '2022-07-06 06:14:53', '2022-07-06 06:14:53', NULL),
(10, 'daada', '<p>سلام خوب هستین</p>', 1, '2022-07-06 06:17:39', '2022-07-06 06:17:39', NULL),
(11, 'dvcxvc', '<p>zcxczxzxczxzxczxc</p>', 1, '2022-07-06 06:20:46', '2022-07-06 06:20:46', NULL),
(12, 'daada', '<p>sadsasadsadassadsadsadsa</p>', 1, '2022-07-06 06:23:03', '2022-07-06 06:23:03', NULL),
(13, 'fdgfdgfdg', '<p>zcxzxzxzxccxzvfghbfgb</p>', 1, '2022-07-06 06:24:57', '2022-07-06 06:24:57', NULL),
(14, 'fdgfdgfdg', '<p>gdhdffdfd</p>', 1, '2022-07-06 06:27:15', '2022-07-06 06:27:15', NULL),
(15, 'fdgfdgfdg', '<p>dsfdfdsfsddfsdfd</p>', 1, '2022-07-06 06:28:56', '2022-07-06 06:44:06', NULL),
(16, 'fdgfdgfdg', '<p>srtwerwewer</p>', 1, '2022-07-06 06:32:07', '2022-07-06 06:32:07', NULL),
(17, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:34:27', '2022-07-06 06:34:27', NULL),
(18, 'sdafasdfdsf', '<p>fsdfdsfdsfds</p>', 1, '2022-07-06 06:35:42', '2022-07-06 06:35:42', NULL),
(19, 'dvcxvc', '<p>fdsfddsfdsf</p>', 1, '2022-07-06 06:37:06', '2022-07-07 09:45:11', NULL),
(20, 'dfgfd', '<p>drdrgddfggfdgfdg</p>', 1, '2022-07-06 06:47:23', '2022-07-07 15:33:02', NULL),
(21, 'fhdhsfhf', '<p>ghjghjgjghj</p>', 1, '2022-07-10 05:05:18', '2022-07-10 05:05:18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `email_files`
--

CREATE TABLE `email_files` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `public_mail_id` bigint(20) UNSIGNED NOT NULL,
  `file_path` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `publish_type` tinyint(4) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `failed_jobs`
--

INSERT INTO `failed_jobs` (`id`, `uuid`, `connection`, `queue`, `payload`, `exception`, `failed_at`) VALUES
(54, 'bbae80e3-6fd2-42ab-a712-4e0343a87433', 'database', 'default', '{\"uuid\":\"bbae80e3-6fd2-42ab-a712-4e0343a87433\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:39:33'),
(55, '855da78c-52bf-4ec2-8add-8480c44fbac3', 'database', 'default', '{\"uuid\":\"855da78c-52bf-4ec2-8add-8480c44fbac3\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:43:42'),
(56, '426d81a6-a2d5-4073-b761-8950ff83b695', 'database', 'default', '{\"uuid\":\"426d81a6-a2d5-4073-b761-8950ff83b695\",\"displayName\":\"App\\\\Jobs\\\\timediscountjob\",\"job\":\"Illuminate\\\\Queue\\\\CallQueuedHandler@call\",\"maxTries\":null,\"maxExceptions\":null,\"failOnTimeout\":false,\"backoff\":null,\"timeout\":null,\"retryUntil\":null,\"data\":{\"commandName\":\"App\\\\Jobs\\\\timediscountjob\",\"command\":\"O:24:\\\"App\\\\Jobs\\\\timediscountjob\\\":2:{s:5:\\\"start\\\";i:2022;s:3:\\\"end\\\";i:2022;}\"}}', 'Illuminate\\Queue\\MaxAttemptsExceededException: App\\Jobs\\timediscountjob has been attempted too many times or run too long. The job may have previously timed out. in C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php:750\nStack trace:\n#0 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(504): Illuminate\\Queue\\Worker->maxAttemptsExceededException(Object(Illuminate\\Queue\\Jobs\\DatabaseJob))\n#1 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(418): Illuminate\\Queue\\Worker->markJobAsFailedIfAlreadyExceedsMaxAttempts(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), 1)\n#2 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(378): Illuminate\\Queue\\Worker->process(\'database\', Object(Illuminate\\Queue\\Jobs\\DatabaseJob), Object(Illuminate\\Queue\\WorkerOptions))\n#3 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Worker.php(329): Illuminate\\Queue\\Worker->runJob(Object(Illuminate\\Queue\\Jobs\\DatabaseJob), \'database\', Object(Illuminate\\Queue\\WorkerOptions))\n#4 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(130): Illuminate\\Queue\\Worker->runNextJob(\'database\', \'default\', Object(Illuminate\\Queue\\WorkerOptions))\n#5 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Queue\\Console\\WorkCommand.php(114): Illuminate\\Queue\\Console\\WorkCommand->runWorker(\'database\', \'default\')\n#6 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(36): Illuminate\\Queue\\Console\\WorkCommand->handle()\n#7 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Util.php(41): Illuminate\\Container\\BoundMethod::Illuminate\\Container\\{closure}()\n#8 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(93): Illuminate\\Container\\Util::unwrapIfClosure(Object(Closure))\n#9 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\BoundMethod.php(37): Illuminate\\Container\\BoundMethod::callBoundMethod(Object(Illuminate\\Foundation\\Application), Array, Object(Closure))\n#10 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Container\\Container.php(651): Illuminate\\Container\\BoundMethod::call(Object(Illuminate\\Foundation\\Application), Array, Array, NULL)\n#11 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(136): Illuminate\\Container\\Container->call(Array)\n#12 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Command\\Command.php(308): Illuminate\\Console\\Command->execute(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#13 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Command.php(121): Symfony\\Component\\Console\\Command\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Illuminate\\Console\\OutputStyle))\n#14 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(998): Illuminate\\Console\\Command->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#15 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(299): Symfony\\Component\\Console\\Application->doRunCommand(Object(Illuminate\\Queue\\Console\\WorkCommand), Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#16 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\symfony\\console\\Application.php(171): Symfony\\Component\\Console\\Application->doRun(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#17 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Console\\Application.php(102): Symfony\\Component\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#18 C:\\Users\\Administrator\\Desktop\\badbaan\\vendor\\laravel\\framework\\src\\Illuminate\\Foundation\\Console\\Kernel.php(129): Illuminate\\Console\\Application->run(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#19 C:\\Users\\Administrator\\Desktop\\badbaan\\artisan(37): Illuminate\\Foundation\\Console\\Kernel->handle(Object(Symfony\\Component\\Console\\Input\\ArgvInput), Object(Symfony\\Component\\Console\\Output\\ConsoleOutput))\n#20 {main}', '2022-07-11 09:45:00');

-- --------------------------------------------------------

--
-- Table structure for table `group_products`
--

CREATE TABLE `group_products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `heroes`
--

CREATE TABLE `heroes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image2` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `heroes`
--

INSERT INTO `heroes` (`id`, `image`, `alt`, `image2`, `description`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>dsfdsfdsdsfdsfd</p>', '2022-07-16 05:11:43', '2022-07-16 06:59:28', NULL),
(2, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\1657954821.png\"', '<p>dfsfddsfd</p>', '2022-07-16 07:00:21', '2022-07-16 07:01:35', '2022-07-16 07:01:35'),
(3, '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image.png\"', '', '\"images\\\\hero\\\\22\\\\07\\\\16\\\\image2.png\"', '<p>ddgsffsdgdfsgdfsgdf</p>', '2022-07-16 11:19:20', '2022-07-16 11:19:20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `infoes`
--

CREATE TABLE `infoes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `instagram` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `twitter` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `facebook` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pintress` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `infoes`
--

INSERT INTO `infoes` (`id`, `title`, `image`, `alt`, `description`, `instagram`, `twitter`, `facebook`, `pintress`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'hrerhrth', '{\"indexArray\":{\"large\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.large.jpg\",\"medium\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.medium.jpg\",\"small\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\\\\1657948423.small.jpg\"},\"directory\":\"images\\\\info\\\\22\\\\07\\\\16\\\\1657948423\",\"currentImage\":\"medium\"}', '', '<p>rthertrhthrth</p>', 'htrrtrth', 'rtyrtyeyrtey', 'rterthrhrt', 'rtthtrhrthrt', 1, '2022-07-16 05:13:43', '2022-07-16 05:13:43', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `queue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `attempts` tinyint(3) UNSIGNED NOT NULL,
  `reserved_at` int(10) UNSIGNED DEFAULT NULL,
  `available_at` int(10) UNSIGNED NOT NULL,
  `created_at` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_categories`
--

CREATE TABLE `job_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_categories`
--

INSERT INTO `job_categories` (`id`, `title`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'تعویض روغنی', 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `keywords`
--

CREATE TABLE `keywords` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keywords` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `keywords`
--

INSERT INTO `keywords` (`id`, `name`, `keywords`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'keyword', 'نرم افزار مدیریت اتوسرویس,حسابداری,تعویض روغنی,سرویسا,پلاکخوان,پلاک خوان,اتوسرویسا,مکانیکی,برق خودرو', '2022-07-23 08:28:51', '2022-07-31 06:46:19', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `master_auto`
--

CREATE TABLE `master_auto` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `master_id` bigint(20) UNSIGNED NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `master_auto_list`
--

CREATE TABLE `master_auto_list` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `master_of_car_id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `master_of_car`
--

CREATE TABLE `master_of_car` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `master_of_car`
--

INSERT INTO `master_of_car` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'پست', '2022-07-07 19:40:48', '2022-07-07 19:42:14', NULL),
(2, 'پرداخت', '2022-07-07 20:08:50', '2022-07-07 20:08:50', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `parent_id` bigint(20) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2013_06_20_064416_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2014_10_12_200000_add_two_factor_columns_to_users_table', 1),
(4, '2016_06_01_000001_create_oauth_auth_codes_table', 1),
(5, '2016_06_01_000002_create_oauth_access_tokens_table', 1),
(6, '2016_06_01_000003_create_oauth_refresh_tokens_table', 1),
(7, '2016_06_01_000004_create_oauth_clients_table', 1),
(8, '2016_06_01_000005_create_oauth_personal_access_clients_table', 1),
(9, '2019_08_19_000000_create_failed_jobs_table', 1),
(10, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(11, '2021_09_12_104123_create_copans_table', 1),
(12, '2021_09_12_104251_create_common_discount_table', 1),
(13, '2021_12_21_133131_create_settings_table', 1),
(14, '2022_06_19_060055_create-job-categories-table', 1),
(15, '2022_06_19_063907_create_sessions_table', 1),
(16, '2022_06_19_070123_create_otps_table', 1),
(17, '2022_06_19_083124_create-post_categories-table', 1),
(18, '2022_06_19_083256_create-posts-table', 1),
(19, '2022_06_19_083616_create-comments-table', 1),
(20, '2022_06_19_084628_create-sms-table', 1),
(21, '2022_06_19_084702_create-emails-table', 1),
(22, '2022_06_19_084725_create-email_files-table', 1),
(23, '2022_06_20_044614_create-infoes-table', 1),
(24, '2022_06_20_053743_create-banners-table', 1),
(25, '2022_06_20_060100_create-provinces-table', 1),
(26, '2022_06_20_060102_create-cities-center-table', 1),
(27, '2022_06_20_060412_create-services-center-table', 1),
(28, '2022_06_20_064343_create-cars-name-table', 1),
(29, '2022_06_20_064415_create-services-table', 1),
(30, '2022_06_20_064610_create-cars-table', 1),
(31, '2022_06_20_070815_add-column-users-table', 1),
(32, '2022_06_20_072912_create-group-products-table', 1),
(33, '2022_06_20_072921_create-products_group-table', 1),
(34, '2022_06_20_072937_create-services-detail-table', 1),
(35, '2022_06_20_090012_create-modules-table', 1),
(36, '2022_06_20_092008_create-menus-table', 1),
(37, '2022_06_20_092109_create-newsletter-table', 1),
(38, '2022_06_21_061023_create-sms_type-table', 1),
(39, '2022_06_21_061157_create-sms_draft_primary-table', 1),
(40, '2022_06_21_061822_create-sms_package-table', 1),
(41, '2022_06_21_062109_create-sms_draft-table', 1),
(42, '2022_06_21_062253_create-sms_timing-table', 1),
(43, '2022_06_21_063412_create-sms_buy_logs-table', 1),
(44, '2022_06_21_063549_create-sms_sending_logs-table', 1),
(45, '2022_06_21_064258_create-sms_saving_logs-table', 1),
(46, '2022_06_26_044811_add-status-to-job_category', 1),
(47, '2022_06_26_051826_add-status-to-posts', 1),
(48, '2022_06_26_075401_add-status-to-modules', 1),
(49, '2022_06_26_090727_add-status-to-newsletter', 1),
(50, '2022_06_26_094930_add-status-to-infoes', 1),
(51, '2022_06_27_073026_add-status-to-cars_name', 1),
(52, '2022_06_27_073112_add-status-to-cars', 1),
(53, '2022_06_27_074744_add-slug-to-cars', 1),
(54, '2022_06_27_074754_add-slug-to-cars_name', 1),
(55, '2022_06_27_101620_create-car-gallery-table', 1),
(56, '2022_06_27_103244_add-culomn-to-cars', 1),
(57, '2022_06_29_112750_add-province-id-to-cities-table', 1),
(58, '2022_06_29_113459_add-status-to-cities-table', 1),
(59, '2022_06_29_113513_add-status-to-provinces-table', 1),
(60, '2022_06_29_121430_add-status-to-services-table', 1),
(61, '2022_06_30_064927_add-car-create-to-car_galleries-table', 1),
(63, '2022_06_30_084600_add-culomn-to-car_galleries-table', 2),
(64, '2022_06_30_090730_change-car-tag-to-cars-table', 3),
(65, '2022_07_02_094018_cars-tip-table', 4),
(66, '2022_07_02_094136_cars-model-table', 4),
(67, '2022_07_02_094257_create-updating-table', 4),
(68, '2022_07_02_094319_create-back-up-table', 4),
(69, '2022_07_04_062823_add-status-to-sms-pakage', 4),
(70, '2022_07_04_073228_create_notifications_table', 4),
(71, '2022_07_04_100536_create_sms-price-table', 4),
(72, '2021_09_12_104154_create_amazing_sales_table', 5),
(74, '2022_07_06_100936_create_failed_jobs_table', 6),
(75, '2022_07_06_101038_create_jobs_table', 7),
(76, '2022_07_07_222432_add-send-notification-to-_discount', 8),
(77, '2022_07_07_234835_create-master-of-car-table', 9),
(79, '2022_07_08_020103_add-column-notificationst-to', 11),
(80, '2022_07_08_193718_add-user-type-notificationst-to', 12),
(81, '2022_07_08_195657_create-send-notificationst-to', 13),
(82, '2022_07_09_004514_create-technical-information-table', 14),
(87, '2022_07_10_112222_add-status-to-auto-master', 15),
(88, '2022_07_10_112354_add-image-to-auto-master', 15),
(89, '2022_07_10_122213_add-job-id-to-master-auto-list-create', 16),
(95, '2022_07_10_142912_add-dicounts-to-sms-pakage', 18),
(96, '2022_07_10_143612_add-dicounts-notification-to-sms-pakage', 19),
(97, '2022_07_07_235007_create-master-auto-table', 20),
(98, '2022_07_11_144555_add-image-to-technical-informations-table', 21),
(99, '2022_07_12_141239_create_properties_table', 22),
(100, '2022_07_12_151445_create_teams_table', 22),
(101, '2022_07_12_154542_create_possibilities_table', 22),
(102, '2022_07_12_194331_create_site_galleries_table', 22),
(103, '2022_07_12_200553_create_heroes_table', 22),
(104, '2022_07_13_190319_add-image2-hero-tabe', 22),
(105, '2022_07_13_193321_create-tariff-table', 22),
(106, '2022_07_13_194121_create_site_posts_table', 22),
(108, '2022_07_15_224332_create_buys_table', 22),
(109, '2022_07_15_232036_create-possibility-gallery-table', 22),
(110, '2022_07_15_232227_create-possibilities-table', 22),
(111, '2022_07_15_234510_create-possibility_galleries-table', 22),
(112, '2022_07_16_033625_add-name-tocomments', 22),
(113, '2022_07_16_122912_add-to-downloads', 23),
(114, '2022_07_16_131550_add-column-to-buys', 24),
(115, '2022_07_16_164045_add-column-to-possibilities', 25),
(117, '2022_07_17_120418_add-job-id-to-representations', 27),
(118, '2022_07_17_092641_create_representations_table', 28),
(119, '2022_07_15_173133_create_downloads_table', 29),
(120, '2022_07_23_123943_create_keywords_table', 30),
(121, '2022_07_23_130058_add-alt-to-banners-table', 31),
(122, '2022_07_23_130220_add-alt-to-cars_name-table', 32),
(123, '2022_07_23_130337_add-alt-to-heroes-table', 33),
(124, '2022_07_23_130418_add-alt-to-infoes-table', 34),
(125, '2022_07_23_130503_add-alt-to-master_auto-table', 35),
(126, '2022_07_23_130550_add-alt-to-modules-table', 36),
(127, '2022_07_23_130654_add-alt-to-possibilities-table', 37),
(128, '2022_07_23_130942_add-alt-to-posts-table', 38),
(129, '2022_07_23_131017_add-alt-to-post_categories-table', 39),
(130, '2022_07_23_131140_add-alt-to-sevices_center-table', 40),
(131, '2022_07_23_131227_add-alt-to-site_galleries-table', 41),
(132, '2022_07_23_131259_add-alt-to-site_posts-table', 42),
(133, '2022_07_23_131350_add-alt-to-teams-table', 43),
(134, '2022_07_23_131457_add-alt-to-technical_information-table', 44),
(136, '2022_07_23_131531_add-alt-to-users-table', 45),
(137, '2022_07_23_131740_add-alt-to-setting-table', 46),
(138, '2022_07_23_150609_add-alt-to-heroes-2-table', 47),
(139, '2022_07_25_121527_create_special_sales_table', 48),
(141, '2022_07_25_125920_add-service-center-to-download-table', 49);

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `newsletter`
--

CREATE TABLE `newsletter` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `newsletter`
--

INSERT INTO `newsletter` (`id`, `email`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'a970@gmail.com', 0, '2022-07-23 13:31:31', '2022-07-23 13:31:31', NULL),
(2, 'ass@gmail.com', 0, '2022-07-23 13:32:14', '2022-07-23 13:32:14', NULL),
(4, 'ass7@gmail.com', 0, '2022-07-23 13:32:55', '2022-07-23 13:32:55', NULL),
(5, 'amir79057@gmail.com', 0, '2022-07-23 13:34:17', '2022-07-23 13:34:17', NULL),
(6, 's7d@gmail.com', 0, '2022-07-23 13:39:04', '2022-07-23 13:39:04', NULL),
(7, 'amir797057@gmail.com', 0, '2022-07-23 13:39:48', '2022-07-23 13:39:48', NULL),
(9, 'sd@gmail.com', 0, '2022-07-23 13:41:18', '2022-07-23 13:41:18', NULL),
(11, 's77d@gmail.com', 0, '2022-07-23 13:42:46', '2022-07-23 13:42:46', NULL),
(12, 's79d@gmail.com', 0, '2022-07-23 13:43:19', '2022-07-23 13:43:19', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` char(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifiable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notifiable_id` bigint(20) UNSIGNED NOT NULL,
  `data` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `read_at` timestamp NULL DEFAULT NULL,
  `sms_draft_id` bigint(20) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `type`, `notifiable_type`, `notifiable_id`, `data`, `read_at`, `sms_draft_id`, `created_at`, `updated_at`) VALUES
('4f39184f-8be3-4b87-ba37-c01661919268', 'App\\Notifications\\SendNotification', 'App\\Models\\Content\\ServiceCenter', 5, '{\"user_id\":{\"id\":5,\"user_id\":1,\"first_name\":\"fdgggfd\",\"last_name\":\"dfgdggdfg\",\"place_name\":\"dfggfgdffdg\",\"mobile\":\"dfgdffdgfd\",\"phone_number\":null,\"birthday\":\"2022-07-10 14:26:29\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"fgdfdfdssdgsdfdfd\",\"status\":1,\"created_at\":null,\"updated_at\":\"2022-07-10T09:56:29.000000Z\",\"deleted_at\":null},\"sms_draft_title\":\"dfghfghfgdgf\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>gfdfgfgfggfh<\\/p>\"}', NULL, NULL, '2022-07-11 09:36:19', '2022-07-11 09:36:19'),
('50852310-13f3-493a-baf4-68106c98699a', 'App\\Notifications\\SendNotification', 'App\\Models\\Content\\ServiceCenter', 6, '{\"user_id\":{\"id\":6,\"user_id\":2,\"first_name\":\"fdgsgfd\",\"last_name\":\"fdfdsgfdsfd\",\"place_name\":\"dfdgdfgdfgdfgd\",\"mobile\":\"sdffdgdffdgfggdsg\",\"phone_number\":null,\"birthday\":\"2022-07-07 18:53:48\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"sdgfdfsgdfsgfdgdfg\",\"status\":1,\"created_at\":null,\"updated_at\":null,\"deleted_at\":null},\"sms_draft_title\":\"dfghfghfgdgf\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>gfdfgfgfggfh<\\/p>\"}', NULL, NULL, '2022-07-11 09:37:06', '2022-07-11 09:37:06'),
('9c94c1c8-0285-48ca-a292-a00477a0fedf', 'App\\Notifications\\SendPrivateNotification', 'App\\Models\\Content\\ServiceCenter', 6, '{\"user_id\":{\"id\":6,\"user_id\":2,\"first_name\":\"fdgsgfd\",\"last_name\":\"fdfdsgfdsfd\",\"place_name\":\"dfdgdfgdfgdfgd\",\"mobile\":\"sdffdgdffdgfggdsg\",\"phone_number\":null,\"birthday\":\"2022-07-07 18:53:48\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"sdgfdfsgdfsgfdgdfg\",\"status\":1,\"created_at\":null,\"updated_at\":null,\"deleted_at\":null},\"sms_draft_title\":\"yuuyfvuy\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>luycctgl<\\/p>\"}', NULL, NULL, '2022-07-11 09:37:42', '2022-07-11 09:37:42'),
('af104cf6-276e-4500-a5d1-499a4db18770', 'App\\Notifications\\SendNotification', 'App\\Models\\Content\\ServiceCenter', 5, '{\"user_id\":{\"id\":5,\"user_id\":1,\"first_name\":\"fdgggfd\",\"last_name\":\"dfgdggdfg\",\"place_name\":\"dfggfgdffdg\",\"mobile\":\"dfgdffdgfd\",\"phone_number\":null,\"birthday\":\"2022-07-10 14:26:29\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"fgdfdfdssdgsdfdfd\",\"status\":1,\"created_at\":null,\"updated_at\":\"2022-07-10T09:56:29.000000Z\",\"deleted_at\":null},\"sms_draft_title\":\"dfghfghfgdgf\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>gfdfgfgfggfh<\\/p>\"}', NULL, NULL, '2022-07-11 09:37:05', '2022-07-11 09:37:05'),
('c7ce487a-821c-4a36-a458-dcccf898c184', 'App\\Notifications\\SendPrivateNotification', 'App\\Models\\Content\\ServiceCenter', 5, '{\"user_id\":{\"id\":5,\"user_id\":1,\"first_name\":\"fdgggfd\",\"last_name\":\"dfgdggdfg\",\"place_name\":\"dfggfgdffdg\",\"mobile\":\"dfgdffdgfd\",\"phone_number\":null,\"birthday\":\"2022-07-10 14:26:29\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"fgdfdfdssdgsdfdfd\",\"status\":1,\"created_at\":null,\"updated_at\":\"2022-07-10T09:56:29.000000Z\",\"deleted_at\":null},\"sms_draft_title\":\"yuuyfvuy\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>luycctgl<\\/p>\"}', NULL, NULL, '2022-07-11 09:37:41', '2022-07-11 09:37:41'),
('c83ea408-29ed-4a13-b527-a86403953456', 'App\\Notifications\\DiscountNotification', 'App\\Models\\Content\\ServiceCenter', 6, '{\"d_id\":{\"id\":6,\"user_id\":2,\"first_name\":\"fdgsgfd\",\"last_name\":\"fdfdsgfdsfd\",\"place_name\":\"dfdgdfgdfgdfgd\",\"mobile\":\"sdffdgdffdgfggdsg\",\"phone_number\":null,\"birthday\":\"2022-07-07 18:53:48\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"sdgfdfsgdfsgfdgdfg\",\"status\":1,\"created_at\":null,\"updated_at\":null,\"deleted_at\":null},\"percentage\":50,\"sms_draft_title\":\"hffgfg\",\"start_date\":\"2022-07-11 11:33:20\",\"end_date\":\"2022-07-11 11:31:19\",\"message\":\"\\u06a9\\u062f \\u062a\\u062e\\u0641\\u06cc\\u0641 \\u0628\\u0631\\u0627\\u06cc \\u0627\\u06cc\\u0646 \\u0645\\u062d\\u0635\\u0648\\u0644 \\u0627\\u0631\\u0633\\u0627\\u0644 \\u06af\\u0631\\u062f\\u06cc\\u062f\"}', NULL, NULL, '2022-07-11 09:43:31', '2022-07-11 09:43:31'),
('df3eb51a-fa8d-4754-87dd-14307539d6ae', 'App\\Notifications\\DiscountNotification', 'App\\Models\\Content\\ServiceCenter', 5, '{\"d_id\":{\"id\":5,\"user_id\":1,\"first_name\":\"fdgggfd\",\"last_name\":\"dfgdggdfg\",\"place_name\":\"dfggfgdffdg\",\"mobile\":\"dfgdffdgfd\",\"phone_number\":null,\"birthday\":\"2022-07-10 14:26:29\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"fgdfdfdssdgsdfdfd\",\"status\":1,\"created_at\":null,\"updated_at\":\"2022-07-10T09:56:29.000000Z\",\"deleted_at\":null},\"percentage\":50,\"sms_draft_title\":\"hffgfg\",\"start_date\":\"2022-07-11 11:33:20\",\"end_date\":\"2022-07-11 11:31:19\",\"message\":\"\\u06a9\\u062f \\u062a\\u062e\\u0641\\u06cc\\u0641 \\u0628\\u0631\\u0627\\u06cc \\u0627\\u06cc\\u0646 \\u0645\\u062d\\u0635\\u0648\\u0644 \\u0627\\u0631\\u0633\\u0627\\u0644 \\u06af\\u0631\\u062f\\u06cc\\u062f\"}', NULL, NULL, '2022-07-11 09:43:31', '2022-07-11 09:43:31'),
('fe7a8c04-0d6c-4e13-b95c-8af4673465a2', 'App\\Notifications\\SendNotification', 'App\\Models\\Content\\ServiceCenter', 6, '{\"user_id\":{\"id\":6,\"user_id\":2,\"first_name\":\"fdgsgfd\",\"last_name\":\"fdfdsgfdsfd\",\"place_name\":\"dfdgdfgdfgdfgd\",\"mobile\":\"sdffdgdffdgfggdsg\",\"phone_number\":null,\"birthday\":\"2022-07-07 18:53:48\",\"profile_photo_path\":null,\"province_id\":1,\"city_id\":1,\"job_id\":1,\"address\":\"sdgfdfsgdfsgfdgdfg\",\"status\":1,\"created_at\":null,\"updated_at\":null,\"deleted_at\":null},\"sms_draft_title\":\"dfghfghfgdgf\",\"job_id\":\"1\",\"province_id\":\"1\",\"city_id\":\"1\",\"message\":\"<p>gfdfgfgfggfh<\\/p>\"}', NULL, NULL, '2022-07-11 09:36:19', '2022-07-11 09:36:19');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--

CREATE TABLE `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_auth_codes`
--

CREATE TABLE `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--

CREATE TABLE `oauth_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `provider`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Laravel Personal Access Client', 'GCHxgfWG2TXNGjQWaO5quLUOTLXlw3cM3ZfR4Wqu', NULL, 'http://localhost', 1, 0, 0, '2022-07-19 22:08:13', '2022-07-19 22:08:13'),
(2, NULL, 'Laravel Password Grant Client', 'x9oSVieMR7axCtautUgv4slHxyHYiBRN8dKSWrA8', 'users', 'http://localhost', 0, 1, 0, '2022-07-19 22:08:13', '2022-07-19 22:08:13');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_personal_access_clients`
--

CREATE TABLE `oauth_personal_access_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2022-07-19 22:08:13', '2022-07-19 22:08:13');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--

CREATE TABLE `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `otps`
--

CREATE TABLE `otps` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `otp_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'email address or mobile number',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => mobile number , 1 => email address',
  `used` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => not used , 1 => used',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `possibilities`
--

CREATE TABLE `possibilities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `file` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `possibilities`
--

INSERT INTO `possibilities` (`id`, `title`, `image`, `alt`, `file`, `file_size`, `file_type`, `description`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'پیکسل پرفکت', '\"images\\\\possibility\\\\22\\\\07\\\\24\\\\1658660103.png\"', 'تصویر سه', 'C:\\xampp\\tmp\\php3F7.tmp', 0, '', '<p>در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد</p>', '2022-07-16 09:40:09', '2022-07-24 10:55:03', NULL),
(2, 'آسان برای استفاده', '\"images\\\\possibility\\\\22\\\\07\\\\24\\\\1658660069.png\"', 'تصویر یک', 'C:\\xampp\\tmp\\php8196.tmp', 0, '', '<p>در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد</p>', '2022-07-16 11:47:47', '2022-07-24 10:54:29', NULL),
(3, 'طراحی تمیز', '\"images\\\\possibility\\\\22\\\\07\\\\24\\\\1658660028.png\"', 'تصویر یک', 'C:\\xampp\\tmp\\phpDF66.tmp', 56118, 'png', '<p>در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد</p>', '2022-07-16 12:12:29', '2022-07-24 10:53:48', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `possibility_gallery`
--

CREATE TABLE `possibility_gallery` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `file` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `possibility_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `possibility_gallery`
--

INSERT INTO `possibility_gallery` (`id`, `file`, `file_size`, `file_type`, `possibility_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, '\"files\\\\email-files\\\\22\\\\07\\\\16\\\\1657972681.png\"', 56118, 'png', 2, '2022-07-16 11:58:01', '2022-07-16 12:06:49', '2022-07-16 12:06:49'),
(2, '\"files\\\\email-files\\\\22\\\\07\\\\16\\\\1657972929.jpg\"', 61783, 'jpg', 2, '2022-07-16 12:02:09', '2022-07-16 12:06:46', '2022-07-16 12:06:46'),
(3, '\"files\\\\email-files\\\\22\\\\07\\\\16\\\\1657973218.jpg\"', 61783, 'jpg', 2, '2022-07-16 12:06:58', '2022-07-16 12:06:58', NULL),
(4, '\"files\\\\email-files\\\\22\\\\07\\\\16\\\\1657973225.jpg\"', 61783, 'jpg', 2, '2022-07-16 12:07:05', '2022-07-16 12:07:05', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `summary` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `tags` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post_categories`
--

CREATE TABLE `post_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `tags` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products_group`
--

CREATE TABLE `products_group` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `job_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products_group`
--

INSERT INTO `products_group` (`id`, `job_id`, `name`, `km`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 'روغن موتور', '5000', '2022-09-14 08:10:55', '2022-09-14 08:10:55', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

CREATE TABLE `properties` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `properties`
--

INSERT INTO `properties` (`id`, `title`, `description`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'vddfdsfds', '<p>dfdsfdsfdsf</p>', 1, '2022-07-28 08:52:02', '2022-09-15 14:54:01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `provinces`
--

CREATE TABLE `provinces` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `province` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `provinces`
--

INSERT INTO `provinces` (`id`, `province`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'تهران', 1, '2022-06-30 02:56:54', '2022-06-30 05:04:54', NULL),
(2, 'همدان', 1, '2022-06-30 04:50:45', '2022-06-30 04:50:45', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `representations`
--

CREATE TABLE `representations` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `servic_cecter` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `representations`
--

INSERT INTO `representations` (`id`, `name`, `email`, `mobile`, `category`, `province_id`, `city_id`, `address`, `servic_cecter`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'پرداخت', 'ass@gmail.com', '09398727656', 'vfdgdfgdfgfg', 1, 1, 'fdgdfgfdfdggfdgfgdfgdfg', NULL, '2022-07-20 11:24:10', '2022-07-20 11:24:10', NULL),
(2, 'نام اول', 'mail1@mail.com', '09184455585', 'دسته اول', 2, 2, 'ادرس اول درخواست نمایندگی', NULL, '2022-09-10 09:26:42', '2022-09-10 09:26:42', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `send_notifications`
--

CREATE TABLE `send_notifications` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `sms_draft_id` bigint(20) UNSIGNED DEFAULT NULL,
  `user_type` tinyint(4) NOT NULL,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `send_notifications`
--

INSERT INTO `send_notifications` (`id`, `sms_draft_id`, `user_type`, `message`, `created_at`, `updated_at`) VALUES
(68, 1, 1, '1', '2022-07-11 09:36:17', '2022-07-11 09:36:17'),
(69, NULL, 1, '<p>l;mubcrfujhx</p>', '2022-07-11 09:36:41', '2022-07-11 09:36:41'),
(70, 1, 1, '1', '2022-07-11 09:37:05', '2022-07-11 09:37:05'),
(71, NULL, 1, '<p>luycctgl</p>', '2022-07-11 09:37:40', '2022-07-11 09:37:40');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `department_id` bigint(20) UNSIGNED NOT NULL,
  `copan_id` bigint(20) UNSIGNED NOT NULL,
  `common_id` bigint(20) UNSIGNED NOT NULL,
  `discount_amount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `final_discount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reamining` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_now` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_next` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avg_function` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `final_amount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `services_detail`
--

CREATE TABLE `services_detail` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `serviec_id` bigint(20) UNSIGNED NOT NULL,
  `products_group_id` bigint(20) UNSIGNED NOT NULL,
  `product_name` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `visited` tinyint(4) NOT NULL DEFAULT '0',
  `changed` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `ip_address` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_agent` text COLLATE utf8mb4_unicode_ci,
  `payload` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_activity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`id`, `user_id`, `ip_address`, `user_agent`, `payload`, `last_activity`) VALUES
('cSsECoWmNbsYuzkBHbgohclHgC5H4rS9PWVCsWpX', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YToyOntzOjY6Il90b2tlbiI7czo0MDoianROWE10VHg1aTNydjc5aUhJa0hPYjZMWWo1V2lUUDM2QWc5Zm15dSI7czo2OiJfZmxhc2giO2E6Mjp7czozOiJvbGQiO2E6MDp7fXM6MzoibmV3IjthOjA6e319fQ==', 1657967931),
('e3HVKSvPiXmzazNnlsw49a7VH6AYbgWXmE610QL9', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YTozOntzOjY6Il90b2tlbiI7czo0MDoiRkRJRHJOVG5rZXVQT2FOdFVNTHAwb3llRDhxVUJLaXd2ZUlid0hGRyI7czo5OiJfcHJldmlvdXMiO2E6MTp7czozOiJ1cmwiO3M6NDQ6Imh0dHA6Ly8xMjcuMC4wLjE6OTAwL3Bvc3QvdGFncy1wb3N0L2ZnZmRkZmdkIjt9czo2OiJfZmxhc2giO2E6Mjp7czozOiJvbGQiO2E6MDp7fXM6MzoibmV3IjthOjA6e319fQ==', 1657974054),
('fxIiIYlZtQiKz8yVdyQT9b62QpNqUiDvGkZRRd4I', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YTozOntzOjY6Il90b2tlbiI7czo0MDoiS01NVzhzSnpxTFU2TVdDZlVXMzBFZ3pVNTAxMmM2QjFzVHdoVVJwZiI7czo5OiJfcHJldmlvdXMiO2E6MTp7czozOiJ1cmwiO3M6MjE6Imh0dHA6Ly8xMjcuMC4wLjE6ODAwMCI7fXM6NjoiX2ZsYXNoIjthOjI6e3M6Mzoib2xkIjthOjA6e31zOjM6Im5ldyI7YTowOnt9fX0=', 1658043671),
('IzAeAgyLV62qZWbJ6KRWBSQ4cLlXACy8cZGCB6Xt', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YToyOntzOjY6Il90b2tlbiI7czo0MDoiWEd6VU44ank5SUtCb3FLT1d2VnhGSm5tOXR0U2o5b3dlTkpFM2hKQyI7czo2OiJfZmxhc2giO2E6Mjp7czozOiJvbGQiO2E6MDp7fXM6MzoibmV3IjthOjA6e319fQ==', 1657967932),
('NATBO9Lr39BQWGj3pOZ3ip6hiVsz4cHhRWJ2HYrP', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YToyOntzOjY6Il90b2tlbiI7czo0MDoibk15S0N1QXJXSlU0YkFDZlRsdkhQUHFEb0ZESUVrdDNEclE1dXZXaiI7czo2OiJfZmxhc2giO2E6Mjp7czozOiJvbGQiO2E6MDp7fXM6MzoibmV3IjthOjA6e319fQ==', 1657967671),
('r5DnUNjOksosoX84JfhcbZDxWKiIKZNK3UPz216x', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'YToyOntzOjY6Il90b2tlbiI7czo0MDoidlVEdU1ZRlIyV2xXSEpORFduUDVPSURVbDJudW5vYXcyMFczbE9sZSI7czo2OiJfZmxhc2giO2E6Mjp7czozOiJvbGQiO2E6MDp7fXM6MzoibmV3IjthOjA6e319fQ==', 1657967671);

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keywords` text COLLATE utf8mb4_unicode_ci,
  `logo` text COLLATE utf8mb4_unicode_ci,
  `alt_logo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `icon` text COLLATE utf8mb4_unicode_ci,
  `alt_icon` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `title`, `keywords`, `logo`, `alt_logo`, `icon`, `alt_icon`, `created_at`, `updated_at`) VALUES
(1, 'سرویسا - نگبان سلامت خودروی شما', 'اتوسرویسا سرویسا', '\"images\\\\setting\\\\22\\\\07\\\\20\\\\logo.png\"', '', '\"images\\\\setting\\\\22\\\\07\\\\20\\\\icon.png\"', '', NULL, '2022-08-06 07:28:53');

-- --------------------------------------------------------

--
-- Table structure for table `sevices_center`
--

CREATE TABLE `sevices_center` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `place_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `profile_photo_path` text COLLATE utf8mb4_unicode_ci,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province_id` bigint(20) UNSIGNED NOT NULL,
  `city_id` bigint(20) UNSIGNED NOT NULL,
  `job_id` bigint(20) UNSIGNED NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `site_galleries`
--

CREATE TABLE `site_galleries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `site_galleries`
--

INSERT INTO `site_galleries` (`id`, `image`, `alt`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658659910.png\"', 'تصویر یک', '2022-07-23 11:05:35', '2022-07-24 10:51:51', NULL),
(2, '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658659902.png\"', 'تصویر سه', '2022-07-24 10:48:51', '2022-07-24 10:51:42', NULL),
(3, '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658659891.png\"', 'تصویر سه', '2022-07-24 10:49:01', '2022-07-24 10:51:31', NULL),
(4, '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658659882.png\"', 'تصویر یک', '2022-07-24 10:49:23', '2022-07-24 10:51:22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `site_posts`
--

CREATE TABLE `site_posts` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `summary` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `commentable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 =>uncommentable , 1 => commentable',
  `tags` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `author_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `site_posts`
--

INSERT INTO `site_posts` (`id`, `title`, `slug`, `summary`, `body`, `image`, `alt`, `status`, `commentable`, `tags`, `author_id`, `category_id`, `created_at`, `updated_at`, `deleted_at`) VALUES
(7, 'رشد هوش مصنوعی', NULL, '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.ر</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', '{\"indexArray\":{\"large\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660453\\\\1658660453.large.png\",\"medium\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660453\\\\1658660453.medium.png\",\"small\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660453\\\\1658660453.small.png\"},\"directory\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660453\",\"currentImage\":\"medium\"}', 'تصویر یک', 1, 0, 'رشد هوش مصنوعی,هوش مصنوعی', 9, 1, '2022-07-24 11:00:53', '2022-07-24 11:00:56', NULL),
(8, 'مشاوره تجاری رشد محصول', NULL, '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', '{\"indexArray\":{\"large\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660499\\\\1658660499.large.png\",\"medium\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660499\\\\1658660499.medium.png\",\"small\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660499\\\\1658660499.small.png\"},\"directory\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660499\",\"currentImage\":\"medium\"}', 'تصویر دو', 1, 0, 'مشاوره', 9, 1, '2022-07-24 11:01:40', '2022-07-24 11:01:40', NULL),
(9, 'رشد هوش مصنوعی', NULL, '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>&nbsp;</p>', '<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>\r\n\r\n<p>لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.</p>', '{\"indexArray\":{\"large\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660555\\\\1658660555.large.png\",\"medium\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660555\\\\1658660555.medium.png\",\"small\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660555\\\\1658660555.small.png\"},\"directory\":\"images\\\\sitePost\\\\22\\\\07\\\\24\\\\1658660555\",\"currentImage\":\"medium\"}', 'تصویر سه', 1, 0, 'رشد', 9, 1, '2022-07-24 11:02:35', '2022-07-24 11:02:48', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sms`
--

CREATE TABLE `sms` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_buy_logs`
--

CREATE TABLE `sms_buy_logs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `sms_package_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_draft`
--

CREATE TABLE `sms_draft` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sms_type_id` bigint(20) UNSIGNED NOT NULL,
  `msg_text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sms_draft`
--

INSERT INTO `sms_draft` (`id`, `title`, `sms_type_id`, `msg_text`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'dfghfghfgdgf', 1, '<p>gfdfgfgfggfh</p>', 1, '2022-07-07 21:39:02', '2022-07-07 21:43:41', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sms_draft_primary`
--

CREATE TABLE `sms_draft_primary` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '0',
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `plate` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_now` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `km_next` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `products_group_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_pakage`
--

CREATE TABLE `sms_pakage` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `count` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `send_notification` tinyint(4) NOT NULL,
  `percentage` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sms_pakage`
--

INSERT INTO `sms_pakage` (`id`, `name`, `count`, `price`, `send_notification`, `percentage`, `start_date`, `end_date`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'جی 7', '57', '70000', 0, 0, '2022-07-10 10:02:10', '2022-07-10 10:02:10', 0, '2022-07-07 06:11:54', '2022-09-14 08:03:44', '2022-09-14 08:03:44'),
(2, 'hffgfg79797', '7', '999999', 0, 90, '2022-06-10 10:30:06', '2022-07-11 10:30:06', 1, '2022-07-10 10:30:21', '2022-09-14 08:03:48', '2022-09-14 08:03:48'),
(3, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 06:55:22', '2022-09-14 08:03:53', '2022-09-14 08:03:53'),
(4, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 06:55:39', '2022-07-11 06:55:39', NULL),
(5, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 06:56:07', '2022-07-11 06:56:07', NULL),
(6, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 07:00:54', '2022-07-11 07:00:54', NULL),
(7, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 07:01:14', '2022-09-14 08:03:32', '2022-09-14 08:03:32'),
(8, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 07:01:30', '2022-09-14 08:03:28', '2022-09-14 08:03:28'),
(9, 'سمند', '7', '999999', 0, 10, '2022-07-11 06:55:12', '2022-07-11 06:55:12', 1, '2022-07-11 07:02:21', '2022-07-11 09:31:53', '2022-07-11 09:31:53'),
(10, 'hffgfg', '7', '999999', 1, 90, '2022-07-11 07:03:20', '2022-07-11 07:01:19', 1, '2022-07-11 07:03:42', '2022-09-14 08:03:24', '2022-09-14 08:03:24'),
(11, 'hffgfg', '7', '999999', 1, 50, '2022-07-16 07:03:20', '2022-07-11 07:01:19', 1, '2022-07-11 07:04:07', '2022-09-14 08:03:20', '2022-09-14 08:03:20');

-- --------------------------------------------------------

--
-- Table structure for table `sms_price`
--

CREATE TABLE `sms_price` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL,
  `type_operator` tinyint(4) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_saving`
--

CREATE TABLE `sms_saving` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `msg_text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_sending_logs`
--

CREATE TABLE `sms_sending_logs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `msg_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `car_id` bigint(20) UNSIGNED NOT NULL,
  `d_id` bigint(20) UNSIGNED NOT NULL,
  `msg_text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `reciver_number_id` bigint(20) UNSIGNED NOT NULL,
  `count` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_timing`
--

CREATE TABLE `sms_timing` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `reciver_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `draft_id` bigint(20) UNSIGNED NOT NULL,
  `send_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sms_type`
--

CREATE TABLE `sms_type` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sms_type`
--

INSERT INTO `sms_type` (`id`, `title`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'ddfghfghdfghgfhh', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `special_sales`
--

CREATE TABLE `special_sales` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(4) NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `service_center` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `special_sales`
--

INSERT INTO `special_sales` (`id`, `name`, `email`, `mobile`, `type`, `address`, `service_center`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'سیاسی', 'amir79077@gmail.com', '09398727656', 1, 'hjgjhhgjhgj', 'زرذزرذزر', '2022-07-25 08:17:23', '2022-07-25 08:17:23', NULL),
(2, 'سیاسی', 'amir79077@gmail.com', '09398727656', 1, 'hjgjhhgjhgj', 'زرذزرذزر', '2022-07-25 08:17:24', '2022-07-25 08:17:24', NULL),
(3, 'sdfsdsd', 'dfssdfss@gmail.com', '09398727656', 2, 'sdfdsfdsffdsdsffds', 'dsfdsfdsf', '2022-07-25 16:00:49', '2022-07-25 16:00:49', NULL),
(4, 'نام اول', 'mail1@mail.com', '09184455585', 1, 'آدرس اول درخواست فروش ویژه', 'اتوسرویس اول', '2022-09-10 09:28:17', '2022-09-10 09:28:17', NULL),
(5, 'نام اول', 'mail1@mail.com', '09184455585', 1, 'آدرس اول درخواست فروش ویژه', 'اتوسرویس اول', '2022-09-10 09:28:18', '2022-09-10 09:28:18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tariff`
--

CREATE TABLE `tariff` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `time_type` tinyint(4) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tariff`
--

INSERT INTO `tariff` (`id`, `time_type`, `type`, `price`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 0, 0, '999999', '2022-07-16 09:34:22', '2022-07-16 09:34:22', NULL),
(2, 1, 0, '999999', '2022-07-16 09:34:40', '2022-07-16 09:34:40', NULL),
(3, 0, 2, '999999', '2022-07-16 10:10:02', '2022-07-16 10:10:02', NULL),
(4, 0, 1, '999999', '2022-07-16 10:10:09', '2022-07-16 10:10:09', NULL),
(5, 1, 1, '777777', '2022-07-16 10:10:18', '2022-07-16 10:10:18', NULL),
(6, 1, 2, '777777', '2022-07-16 10:10:25', '2022-07-16 10:10:25', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profession` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `instagram` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `twitter` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `facebook` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`id`, `name`, `image`, `alt`, `profession`, `instagram`, `twitter`, `facebook`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'امیرحسین کریمی', '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658660227.png\"', 'تصویر 7', 'برنامه نویس laravel', 'dferdtgrtgretret', 'بللیبلب', 'vcxvcxvcvcxv', '2022-07-22 09:32:36', '2022-07-24 10:57:07', NULL),
(2, 'امیرحسین کریمی', '\"images\\\\team\\\\22\\\\07\\\\24\\\\1658660162.png\"', 'تصویر یک', 'برنامه نویس laravel', 'dferdtgrtgretret', 'بللیبلب', 'vcxvcxvcvcxv', '2022-07-23 15:36:42', '2022-07-24 10:56:32', NULL),
(3, 'مرتضی بهرامی', '\"images\\/team\\/22\\/07\\/31\\/1659277018.jpg\"', 'مرتضی بهرامی', 'برنامه نویس سی شارپ', 'morteza bahrami', 'morteza bahrami', 'morteza bahrami', '2022-07-24 10:57:51', '2022-07-31 14:16:58', NULL),
(4, 'محمود همدانی', '\"images\\/team\\/22\\/07\\/31\\/1659276948.jpg\"', 'محمود همدانی', 'تحلیل و مهندسی نرم افزار', 'mahmoud.hamedani', 'mahmoud.hamedani', 'mahmoud.hamedani', '2022-07-24 10:58:22', '2022-07-31 14:15:48', NULL),
(5, 'fdgdfgfdgdfg', '\"images\\/team\\/22\\/07\\/25\\/1658765091.jpg\"', 'fdsfsdfsdf', 'dfgdfgfdgdfg', 'fdgfdgdffd', 'bnbnbnvbnvbnnbvnbvn', 'fdgfdgfdg', '2022-07-25 16:04:54', '2022-07-25 16:04:58', '2022-07-25 16:04:58');

-- --------------------------------------------------------

--
-- Table structure for table `technical_information`
--

CREATE TABLE `technical_information` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `car_name_id` bigint(20) UNSIGNED NOT NULL,
  `car_tip_id` bigint(20) UNSIGNED NOT NULL,
  `car_model_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `technical_information`
--

INSERT INTO `technical_information` (`id`, `car_name_id`, `car_tip_id`, `car_model_id`, `name`, `description`, `image`, `alt`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 1, 1, 'کاپوت', '<p>sfasdfdsafs</p>', '{\"indexArray\":{\"large\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.large.jpg\",\"medium\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.medium.jpg\",\"small\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.small.jpg\"},\"directory\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\",\"currentImage\":\"medium\"}', '', '2022-07-11 10:49:46', '2022-07-11 10:52:24', '2022-07-11 10:52:24'),
(2, 1, 1, 1, 'آینه', '<p>sdfdssdsfsddsf</p>', '{\"indexArray\":{\"large\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.large.jpg\",\"medium\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.medium.jpg\",\"small\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\\\\1657536586.small.jpg\"},\"directory\":\"images\\\\technicalInfoe\\\\22\\\\07\\\\11\\\\1657536586\",\"currentImage\":\"small\"}', '', '2022-07-11 10:49:47', '2022-07-11 10:56:45', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `updating`
--

CREATE TABLE `updating` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `version` decimal(8,2) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `file_size` bigint(20) NOT NULL,
  `file` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `two_factor_secret` text COLLATE utf8mb4_unicode_ci,
  `two_factor_recovery_codes` text COLLATE utf8mb4_unicode_ci,
  `two_factor_confirmed_at` timestamp NULL DEFAULT NULL,
  `national_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profile_photo_path` text COLLATE utf8mb4_unicode_ci,
  `alt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `activation` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => inactive , 1 => active',
  `activation_date` timestamp NULL DEFAULT NULL,
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 => user , 1 => admin',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `current_team_id` bigint(20) UNSIGNED DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `service_id` bigint(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `mobile`, `password`, `two_factor_secret`, `two_factor_recovery_codes`, `two_factor_confirmed_at`, `national_code`, `first_name`, `last_name`, `slug`, `profile_photo_path`, `alt`, `email_verified_at`, `activation`, `activation_date`, `user_type`, `status`, `current_team_id`, `remember_token`, `created_at`, `updated_at`, `deleted_at`, `service_id`) VALUES
(8, 'baadbaan@gmail.com', '09398927656', '$2y$10$8r/GuRTRIjvcBjEoz6uoIOoGagV0FC7EtsFv/EVZ5h2FC2DNBVGEu', NULL, NULL, NULL, NULL, 'وحید', 'صالحی', NULL, 'images\\users\\22\\07\\23\\1658584413.png', 'تصویر چهار', NULL, 1, NULL, 0, 1, NULL, NULL, '2022-07-23 13:53:33', '2022-07-23 15:34:16', NULL, NULL),
(9, 'baadbaan7@gmail.com', '09398727656', '$2y$10$oclSpZ8PzKQoGMrNuBqYF.TeV73cKAOKRA1.UYJMe4CahH.g4zdg2', NULL, NULL, NULL, NULL, 'وحید', 'صالحی', NULL, NULL, 'تصویر سه', NULL, 1, NULL, 1, 1, NULL, NULL, '2022-07-23 13:54:44', '2022-07-23 15:34:17', NULL, NULL),
(10, 'baadbaan9@gmail.com', '03398727656', '$2y$10$AcHxDwimAd6lk2PKH8guj.lDXTS23Hg5HV4yBeqSPEl2gXyf0wJgu', NULL, NULL, NULL, NULL, 'وحید', 'صالحی', NULL, 'images\\users\\22\\07\\23\\1658584558.png', 'تصویر دو', NULL, 1, NULL, 2, 1, NULL, NULL, '2022-07-23 13:55:58', '2022-07-23 15:34:18', NULL, NULL),
(11, 'database@gmail.com', '09188777656', '$2y$10$ovVsbKWgH/aHaePhfnnSUexa.6C14bAOSXUHe1ZUptxkLR1iT968K', NULL, NULL, NULL, NULL, 'fghtffghgh', 'fghgfhfgh', NULL, NULL, 'dfgfdgdfggdf', NULL, 1, NULL, 0, 1, NULL, NULL, '2022-07-25 16:03:50', '2022-07-25 16:03:58', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amazing_sales`
--
ALTER TABLE `amazing_sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `amazing_sales_product_id_foreign` (`product_id`);

--
-- Indexes for table `back_up`
--
ALTER TABLE `back_up`
  ADD PRIMARY KEY (`id`),
  ADD KEY `back_up_department_id_foreign` (`department_id`);

--
-- Indexes for table `banners`
--
ALTER TABLE `banners`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buys`
--
ALTER TABLE `buys`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `buys_email_unique` (`email`),
  ADD UNIQUE KEY `buys_mobile_unique` (`mobile`),
  ADD KEY `buys_tariffe_id_foreign` (`tariffe_id`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cars_slug_unique` (`slug`),
  ADD UNIQUE KEY `cars_car_tag_unique` (`car_tag`),
  ADD KEY `cars_car_id_foreign` (`car_name_id`),
  ADD KEY `cars_user_id_foreign` (`user_id`),
  ADD KEY `cars_province_id_foreign` (`province_id`),
  ADD KEY `cars_city_id_foreign` (`city_id`);

--
-- Indexes for table `cars_model`
--
ALTER TABLE `cars_model`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_model_tip_id_foreign` (`tip_id`);

--
-- Indexes for table `cars_name`
--
ALTER TABLE `cars_name`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cars_name_slug_unique` (`slug`);

--
-- Indexes for table `cars_tip`
--
ALTER TABLE `cars_tip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_tip_car_id_foreign` (`car_id`);

--
-- Indexes for table `car_galleries`
--
ALTER TABLE `car_galleries`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `car_galleries_car_tag_unique` (`car_tag`),
  ADD KEY `car_galleries_car_name_id_foreign` (`car_name_id`),
  ADD KEY `car_galleries_car_id_foreign` (`car_id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cities_province_id_foreign` (`province_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_parent_id_foreign` (`parent_id`),
  ADD KEY `comments_author_id_foreign` (`author_id`);

--
-- Indexes for table `common_discount`
--
ALTER TABLE `common_discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `copans`
--
ALTER TABLE `copans`
  ADD PRIMARY KEY (`id`),
  ADD KEY `copans_user_id_foreign` (`user_id`);

--
-- Indexes for table `downloads`
--
ALTER TABLE `downloads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emails`
--
ALTER TABLE `emails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `email_files`
--
ALTER TABLE `email_files`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email_files_public_mail_id_foreign` (`public_mail_id`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `group_products`
--
ALTER TABLE `group_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `heroes`
--
ALTER TABLE `heroes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `infoes`
--
ALTER TABLE `infoes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `jobs_queue_index` (`queue`);

--
-- Indexes for table `job_categories`
--
ALTER TABLE `job_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keywords`
--
ALTER TABLE `keywords`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `master_auto`
--
ALTER TABLE `master_auto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `master_auto_d_id_foreign` (`d_id`),
  ADD KEY `master_auto_master_id_foreign` (`master_id`);

--
-- Indexes for table `master_auto_list`
--
ALTER TABLE `master_auto_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `master_auto_list_master_of_car_id_foreign` (`master_of_car_id`),
  ADD KEY `master_auto_list_d_id_foreign` (`d_id`);

--
-- Indexes for table `master_of_car`
--
ALTER TABLE `master_of_car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `menus_parent_id_foreign` (`parent_id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `newsletter`
--
ALTER TABLE `newsletter`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `newsletter_email_unique` (`email`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notifications_notifiable_type_notifiable_id_index` (`notifiable_type`,`notifiable_id`),
  ADD KEY `notifications_sms_draft_id_foreign` (`sms_draft_id`);

--
-- Indexes for table `oauth_access_tokens`
--
ALTER TABLE `oauth_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_access_tokens_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_auth_codes`
--
ALTER TABLE `oauth_auth_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_auth_codes_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_clients_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_refresh_tokens`
--
ALTER TABLE `oauth_refresh_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`);

--
-- Indexes for table `otps`
--
ALTER TABLE `otps`
  ADD PRIMARY KEY (`id`),
  ADD KEY `otps_user_id_foreign` (`user_id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indexes for table `possibilities`
--
ALTER TABLE `possibilities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `possibility_gallery`
--
ALTER TABLE `possibility_gallery`
  ADD PRIMARY KEY (`id`),
  ADD KEY `possibility_gallery_possibility_id_foreign` (`possibility_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `posts_slug_unique` (`slug`),
  ADD KEY `posts_author_id_foreign` (`author_id`),
  ADD KEY `posts_category_id_foreign` (`category_id`);

--
-- Indexes for table `post_categories`
--
ALTER TABLE `post_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `post_categories_slug_unique` (`slug`);

--
-- Indexes for table `products_group`
--
ALTER TABLE `products_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_group_job_id_foreign` (`job_id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provinces`
--
ALTER TABLE `provinces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `representations`
--
ALTER TABLE `representations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `representations_province_id_foreign` (`province_id`),
  ADD KEY `representations_city_id_foreign` (`city_id`);

--
-- Indexes for table `send_notifications`
--
ALTER TABLE `send_notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_car_id_foreign` (`car_id`),
  ADD KEY `services_department_id_foreign` (`department_id`),
  ADD KEY `services_copan_id_foreign` (`copan_id`),
  ADD KEY `services_common_id_foreign` (`common_id`);

--
-- Indexes for table `services_detail`
--
ALTER TABLE `services_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `services_detail_serviec_id_foreign` (`serviec_id`),
  ADD KEY `services_detail_products_group_id_foreign` (`products_group_id`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sessions_user_id_index` (`user_id`),
  ADD KEY `sessions_last_activity_index` (`last_activity`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sevices_center`
--
ALTER TABLE `sevices_center`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sevices_center_mobile_unique` (`mobile`),
  ADD UNIQUE KEY `sevices_center_phone_number_unique` (`phone_number`),
  ADD KEY `sevices_center_user_id_foreign` (`user_id`),
  ADD KEY `sevices_center_province_id_foreign` (`province_id`),
  ADD KEY `sevices_center_city_id_foreign` (`city_id`),
  ADD KEY `sevices_center_job_id_foreign` (`job_id`);

--
-- Indexes for table `site_galleries`
--
ALTER TABLE `site_galleries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `site_posts`
--
ALTER TABLE `site_posts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `site_posts_slug_unique` (`slug`),
  ADD KEY `site_posts_author_id_foreign` (`author_id`),
  ADD KEY `site_posts_category_id_foreign` (`category_id`);

--
-- Indexes for table `sms`
--
ALTER TABLE `sms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_buy_logs`
--
ALTER TABLE `sms_buy_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_buy_logs_d_id_foreign` (`d_id`),
  ADD KEY `sms_buy_logs_sms_package_id_foreign` (`sms_package_id`);

--
-- Indexes for table `sms_draft`
--
ALTER TABLE `sms_draft`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_draft_sms_type_id_foreign` (`sms_type_id`);

--
-- Indexes for table `sms_draft_primary`
--
ALTER TABLE `sms_draft_primary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_draft_primary_products_group_id_foreign` (`products_group_id`);

--
-- Indexes for table `sms_pakage`
--
ALTER TABLE `sms_pakage`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_price`
--
ALTER TABLE `sms_price`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sms_saving`
--
ALTER TABLE `sms_saving`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_saving_d_id_foreign` (`d_id`);

--
-- Indexes for table `sms_sending_logs`
--
ALTER TABLE `sms_sending_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_sending_logs_msg_id_foreign` (`msg_id`),
  ADD KEY `sms_sending_logs_user_id_foreign` (`user_id`),
  ADD KEY `sms_sending_logs_car_id_foreign` (`car_id`),
  ADD KEY `sms_sending_logs_d_id_foreign` (`d_id`),
  ADD KEY `sms_sending_logs_reciver_number_id_foreign` (`reciver_number_id`);

--
-- Indexes for table `sms_timing`
--
ALTER TABLE `sms_timing`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sms_timing_draft_id_foreign` (`draft_id`);

--
-- Indexes for table `sms_type`
--
ALTER TABLE `sms_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `special_sales`
--
ALTER TABLE `special_sales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tariff`
--
ALTER TABLE `tariff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `technical_information`
--
ALTER TABLE `technical_information`
  ADD PRIMARY KEY (`id`),
  ADD KEY `technical_information_car_name_id_foreign` (`car_name_id`),
  ADD KEY `technical_information_car_tip_id_foreign` (`car_tip_id`),
  ADD KEY `technical_information_car_model_id_foreign` (`car_model_id`);

--
-- Indexes for table `updating`
--
ALTER TABLE `updating`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`),
  ADD UNIQUE KEY `users_mobile_unique` (`mobile`),
  ADD UNIQUE KEY `users_national_code_unique` (`national_code`),
  ADD UNIQUE KEY `users_slug_unique` (`slug`),
  ADD KEY `users_service_id_foreign` (`service_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `amazing_sales`
--
ALTER TABLE `amazing_sales`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `back_up`
--
ALTER TABLE `back_up`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `banners`
--
ALTER TABLE `banners`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `buys`
--
ALTER TABLE `buys`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `cars_model`
--
ALTER TABLE `cars_model`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cars_name`
--
ALTER TABLE `cars_name`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cars_tip`
--
ALTER TABLE `cars_tip`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `car_galleries`
--
ALTER TABLE `car_galleries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `common_discount`
--
ALTER TABLE `common_discount`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `copans`
--
ALTER TABLE `copans`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `downloads`
--
ALTER TABLE `downloads`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `emails`
--
ALTER TABLE `emails`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `email_files`
--
ALTER TABLE `email_files`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `group_products`
--
ALTER TABLE `group_products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `heroes`
--
ALTER TABLE `heroes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `infoes`
--
ALTER TABLE `infoes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_categories`
--
ALTER TABLE `job_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `keywords`
--
ALTER TABLE `keywords`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `master_auto`
--
ALTER TABLE `master_auto`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `master_auto_list`
--
ALTER TABLE `master_auto_list`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `master_of_car`
--
ALTER TABLE `master_of_car`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

--
-- AUTO_INCREMENT for table `modules`
--
ALTER TABLE `modules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `newsletter`
--
ALTER TABLE `newsletter`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `otps`
--
ALTER TABLE `otps`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `possibilities`
--
ALTER TABLE `possibilities`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `possibility_gallery`
--
ALTER TABLE `possibility_gallery`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post_categories`
--
ALTER TABLE `post_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products_group`
--
ALTER TABLE `products_group`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `provinces`
--
ALTER TABLE `provinces`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `representations`
--
ALTER TABLE `representations`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `send_notifications`
--
ALTER TABLE `send_notifications`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services_detail`
--
ALTER TABLE `services_detail`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sevices_center`
--
ALTER TABLE `sevices_center`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `site_galleries`
--
ALTER TABLE `site_galleries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `site_posts`
--
ALTER TABLE `site_posts`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `sms`
--
ALTER TABLE `sms`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_buy_logs`
--
ALTER TABLE `sms_buy_logs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_draft`
--
ALTER TABLE `sms_draft`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sms_draft_primary`
--
ALTER TABLE `sms_draft_primary`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_pakage`
--
ALTER TABLE `sms_pakage`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sms_price`
--
ALTER TABLE `sms_price`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_saving`
--
ALTER TABLE `sms_saving`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_sending_logs`
--
ALTER TABLE `sms_sending_logs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_timing`
--
ALTER TABLE `sms_timing`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sms_type`
--
ALTER TABLE `sms_type`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `special_sales`
--
ALTER TABLE `special_sales`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tariff`
--
ALTER TABLE `tariff`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `technical_information`
--
ALTER TABLE `technical_information`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `updating`
--
ALTER TABLE `updating`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `amazing_sales`
--
ALTER TABLE `amazing_sales`
  ADD CONSTRAINT `amazing_sales_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `sms_pakage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `back_up`
--
ALTER TABLE `back_up`
  ADD CONSTRAINT `back_up_department_id_foreign` FOREIGN KEY (`department_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `buys`
--
ALTER TABLE `buys`
  ADD CONSTRAINT `buys_tariffe_id_foreign` FOREIGN KEY (`tariffe_id`) REFERENCES `tariff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_car_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cars_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cars_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cars_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cars_model`
--
ALTER TABLE `cars_model`
  ADD CONSTRAINT `cars_model_tip_id_foreign` FOREIGN KEY (`tip_id`) REFERENCES `cars_tip` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cars_tip`
--
ALTER TABLE `cars_tip`
  ADD CONSTRAINT `cars_tip_car_id_foreign` FOREIGN KEY (`car_id`) REFERENCES `cars_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `car_galleries`
--
ALTER TABLE `car_galleries`
  ADD CONSTRAINT `car_galleries_car_id_foreign` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `car_galleries_car_name_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_author_id_foreign` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `copans`
--
ALTER TABLE `copans`
  ADD CONSTRAINT `copans_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `email_files`
--
ALTER TABLE `email_files`
  ADD CONSTRAINT `email_files_public_mail_id_foreign` FOREIGN KEY (`public_mail_id`) REFERENCES `emails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `master_auto`
--
ALTER TABLE `master_auto`
  ADD CONSTRAINT `master_auto_d_id_foreign` FOREIGN KEY (`d_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `master_auto_master_id_foreign` FOREIGN KEY (`master_id`) REFERENCES `master_of_car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `master_auto_list`
--
ALTER TABLE `master_auto_list`
  ADD CONSTRAINT `master_auto_list_d_id_foreign` FOREIGN KEY (`d_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `master_auto_list_master_of_car_id_foreign` FOREIGN KEY (`master_of_car_id`) REFERENCES `master_of_car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `menus`
--
ALTER TABLE `menus`
  ADD CONSTRAINT `menus_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `menus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_sms_draft_id_foreign` FOREIGN KEY (`sms_draft_id`) REFERENCES `master_of_car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `otps`
--
ALTER TABLE `otps`
  ADD CONSTRAINT `otps_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `possibility_gallery`
--
ALTER TABLE `possibility_gallery`
  ADD CONSTRAINT `possibility_gallery_possibility_id_foreign` FOREIGN KEY (`possibility_id`) REFERENCES `possibilities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_author_id_foreign` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `posts_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `job_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products_group`
--
ALTER TABLE `products_group`
  ADD CONSTRAINT `products_group_job_id_foreign` FOREIGN KEY (`job_id`) REFERENCES `job_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `representations`
--
ALTER TABLE `representations`
  ADD CONSTRAINT `representations_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `representations_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `services_car_id_foreign` FOREIGN KEY (`car_id`) REFERENCES `cars_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `services_common_id_foreign` FOREIGN KEY (`common_id`) REFERENCES `common_discount` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `services_copan_id_foreign` FOREIGN KEY (`copan_id`) REFERENCES `copans` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `services_department_id_foreign` FOREIGN KEY (`department_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `services_detail`
--
ALTER TABLE `services_detail`
  ADD CONSTRAINT `services_detail_products_group_id_foreign` FOREIGN KEY (`products_group_id`) REFERENCES `products_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `services_detail_serviec_id_foreign` FOREIGN KEY (`serviec_id`) REFERENCES `services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sevices_center`
--
ALTER TABLE `sevices_center`
  ADD CONSTRAINT `sevices_center_city_id_foreign` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sevices_center_job_id_foreign` FOREIGN KEY (`job_id`) REFERENCES `job_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sevices_center_province_id_foreign` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sevices_center_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `site_posts`
--
ALTER TABLE `site_posts`
  ADD CONSTRAINT `site_posts_author_id_foreign` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `site_posts_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `job_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_buy_logs`
--
ALTER TABLE `sms_buy_logs`
  ADD CONSTRAINT `sms_buy_logs_d_id_foreign` FOREIGN KEY (`d_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_buy_logs_sms_package_id_foreign` FOREIGN KEY (`sms_package_id`) REFERENCES `sms_pakage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_draft`
--
ALTER TABLE `sms_draft`
  ADD CONSTRAINT `sms_draft_sms_type_id_foreign` FOREIGN KEY (`sms_type_id`) REFERENCES `sms_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_draft_primary`
--
ALTER TABLE `sms_draft_primary`
  ADD CONSTRAINT `sms_draft_primary_products_group_id_foreign` FOREIGN KEY (`products_group_id`) REFERENCES `products_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_saving`
--
ALTER TABLE `sms_saving`
  ADD CONSTRAINT `sms_saving_d_id_foreign` FOREIGN KEY (`d_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_sending_logs`
--
ALTER TABLE `sms_sending_logs`
  ADD CONSTRAINT `sms_sending_logs_car_id_foreign` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_sending_logs_d_id_foreign` FOREIGN KEY (`d_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_sending_logs_msg_id_foreign` FOREIGN KEY (`msg_id`) REFERENCES `sms_draft` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_sending_logs_reciver_number_id_foreign` FOREIGN KEY (`reciver_number_id`) REFERENCES `sevices_center` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sms_sending_logs_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sms_timing`
--
ALTER TABLE `sms_timing`
  ADD CONSTRAINT `sms_timing_draft_id_foreign` FOREIGN KEY (`draft_id`) REFERENCES `sms_draft` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `technical_information`
--
ALTER TABLE `technical_information`
  ADD CONSTRAINT `technical_information_car_model_id_foreign` FOREIGN KEY (`car_model_id`) REFERENCES `cars_model` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `technical_information_car_name_id_foreign` FOREIGN KEY (`car_name_id`) REFERENCES `cars_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `technical_information_car_tip_id_foreign` FOREIGN KEY (`car_tip_id`) REFERENCES `cars_tip` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_service_id_foreign` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
