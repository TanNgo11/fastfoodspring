-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.31-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for fastfoodspringmvc
CREATE DATABASE IF NOT EXISTS `fastfoodspringmvc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `fastfoodspringmvc`;

-- Dumping structure for table fastfoodspringmvc.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `oauth2_Id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.account: ~7 rows (approximately)
INSERT INTO `account` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `address`, `email`, `fullname`, `oauth2_Id`, `password`, `phonenumber`, `status`, `username`) VALUES
	(1, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, '$2a$12$7g8HCCNAsml./lt/p7zBQOCZbLNm2Vz0Uxx4vOHjAJkXlUK4dI7qS', NULL, 1, 'admin1'),
	(2, NULL, NULL, 'admin1', '2024-01-28 22:37:09', '', 'tan.ngo.cit20@eiu.edu.vn', 'thanh tan', NULL, '$2a$10$sqKhN9z.Lg/kU0/C43DZv.hKnhzpUg1GVbcUlsZiCuRF0XdiLJ0Eq', '', 1, 'user1'),
	(3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 1, 'FB001'),
	(4, 'anonymousUser', '2024-01-28 22:13:09', 'FB002', '2024-01-28 22:14:24', '', '', 'Ngô Thanh Tân', '3555822681411635', '$2a$10$MYvhYWwiTAPJXVanRMoBFO89dHule.snFLWgH87o21qjnwD1zm2QS', '', 1, 'FB002'),
	(5, 'anonymousUser', '2024-01-28 23:03:12', 'anonymousUser', '2024-01-28 23:03:12', NULL, NULL, 'nguyễn văn B', '0', '$2a$10$BCJfzTo0HskGAxeVh9t9..DrPvFcwflUY2vPqb/LqIu0/iHkweK7K', NULL, 1, 'user22'),
	(6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'GG001'),
	(7, 'anonymousUser', '2024-01-28 23:05:09', 'anonymousUser', '2024-01-28 23:05:09', NULL, 'tan.ngo.cit20@eiu.edu.vn', 'tan.ngo.cit20@eiu.edu.vn', '115333335345456023444', '$2a$10$KV8ubmePJkceFCN7aNu32eMdCwUNlmtxwhfIuHyN8pMuNC3KQLjsS', NULL, 1, 'GG002');

-- Dumping structure for table fastfoodspringmvc.account_role
CREATE TABLE IF NOT EXISTS `account_role` (
  `accountid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  KEY `FK_nssbcg9f81k63xa5me911x7dx` (`roleid`),
  KEY `FK_cjcq4lr37fq88rdhmhdn2eo7d` (`accountid`),
  CONSTRAINT `FK_cjcq4lr37fq88rdhmhdn2eo7d` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_nssbcg9f81k63xa5me911x7dx` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.account_role: ~5 rows (approximately)
INSERT INTO `account_role` (`accountid`, `roleid`) VALUES
	(1, 2),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(7, 1);

-- Dumping structure for table fastfoodspringmvc.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.category: ~3 rows (approximately)
INSERT INTO `category` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `type`, `status`) VALUES
	(1, NULL, NULL, 'anonymousUser', '2024-01-22 10:04:28', 'Food', '1'),
	(2, NULL, NULL, NULL, NULL, 'Drink', '1'),
	(3, 'anonymousUser', '2024-01-22 09:25:04', 'anonymousUser', '2024-01-22 09:25:04', 'combo', '1');

-- Dumping structure for table fastfoodspringmvc.image
CREATE TABLE IF NOT EXISTS `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mm4cmvteo84wq24upfvucdy08` (`product_id`),
  CONSTRAINT `FK_mm4cmvteo84wq24upfvucdy08` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.image: ~8 rows (approximately)
INSERT INTO `image` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `image_url`, `product_id`) VALUES
	(1, NULL, NULL, NULL, NULL, '/inc/Food/Burger-beef.png', 3),
	(2, NULL, NULL, NULL, NULL, '/inc/Food/burger-Shrimp.png', 3),
	(3, NULL, NULL, NULL, NULL, '/inc/Food/Cheese-Burger.png', 3),
	(4, NULL, NULL, NULL, NULL, '/inc/Food/Cheese-Burger.png', 10),
	(92, 'anonymousUser', '2024-01-22 11:18:20', 'anonymousUser', '2024-01-22 11:18:20', '/upload/product/Product43-1.png', 43),
	(93, 'admin1', '2024-01-28 22:22:55', 'admin1', '2024-01-28 22:22:55', '/upload/product/Product44-1.png', 44),
	(94, 'admin1', '2024-01-28 22:22:55', 'admin1', '2024-01-28 22:22:55', '/upload/product/Product44-2.png', 44),
	(95, 'admin1', '2024-01-28 22:22:55', 'admin1', '2024-01-28 22:22:55', '/upload/product/Product44-3.png', 44);

-- Dumping structure for table fastfoodspringmvc.item
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `totalpay` double DEFAULT NULL,
  `productid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kijx4rl7xtdaod3w2bb5t5cgr` (`productid`),
  CONSTRAINT `FK_kijx4rl7xtdaod3w2bb5t5cgr` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.item: ~0 rows (approximately)

-- Dumping structure for table fastfoodspringmvc.news
CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ajv99jnnrna0poym69pdusrlv` (`account_id`),
  CONSTRAINT `FK_ajv99jnnrna0poym69pdusrlv` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.news: ~8 rows (approximately)
INSERT INTO `news` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `description`, `title`, `account_id`, `status`, `slug`, `imageURL`) VALUES
	(4, NULL, NULL, NULL, NULL, 'sd', 'assd', NULL, '1', 'asd', 'asd'),
	(8, 'admin1', '2024-01-22 22:57:32', 'admin1', '2024-01-22 22:57:32', '<p>sd</p>\r\n', '', 1, '0', '-8', '/upload/news/News8-1.png'),
	(9, 'admin1', '2024-01-22 23:00:26', 'admin1', '2024-01-22 23:00:26', '<p>sdsd</p>\r\n', 'asd sdjk asdjjsd', 1, '0', 'asd-sdjk-asdjjsd-9', '/upload/news/News9-1.png'),
	(10, 'admin1', '2024-01-22 23:11:36', 'admin1', '2024-01-22 23:14:24', '<p><img alt="" src="http://localhost:8080/template/ckfinder/core/connector/java/connector.java?command=Thumbnail&amp;type=Images&amp;currentFolder=%2F&amp;langCode=vi&amp;hash=27095d05ab4f1de&amp;FileName=icon-admin.png" style="height:100px; width:100px" />love u so much 123</p>\r\n', 'ngay hom ay anh noi voi em 123', 1, '0', 'ngay-hom-ay-anh-noi-voi-em-123-10', '/upload/news/News10-1.png'),
	(11, 'admin1', '2024-01-22 23:18:20', 'admin1', '2024-01-22 23:18:20', '\r\nThời sựChính trịThứ tư, 24/1/2024, 17:22 (GMT+7)\r\nÔng Lương Nguyễn Minh Triết làm Bí thư tỉnh Quảng Nam\r\nPhó bí thư Thường trực Thành ủy Đà Nẵng Lương Nguyễn Minh Triết, 48 tuổi, được Bộ Chính trị điều động, chỉ định giữ chức Bí thư tỉnh Quảng Nam.\r\n\r\nChiều 24/1, tại trụ sở Tỉnh ủy Quảng Nam, Trưởng ban Tổ chức Trung ương Trương Thị Mai trao quyết định của Bộ Chính trị cho ông Triết. Ông sẽ tham gia Ban Chấp hành, Ban Thường vụ và giữ chức Bí thư Tỉnh ủy Quảng Nam nhiệm kỳ 2020-2025.\r\n\r\nÔng Triết sẽ thay ông Phan Việt Cường, được Bộ Chính trị cho nghỉ chờ hưu từ ngày 1/1, nghỉ chính thức từ 1/7.', 'anh yêu em 123', 1, '0', 'anh-yêu-em-123-11', '/upload/news/News11-1.png'),
	(12, 'admin1', '2024-01-25 14:12:16', 'admin1', '2024-01-25 14:12:16', '\r\nThời sựChính trịThứ tư, 24/1/2024, 17:22 (GMT+7)\r\nÔng Lương Nguyễn Minh Triết làm Bí thư tỉnh Quảng Nam\r\nPhó bí thư Thường trực Thành ủy Đà Nẵng Lương Nguyễn Minh Triết, 48 tuổi, được Bộ Chính trị điều động, chỉ định giữ chức Bí thư tỉnh Quảng Nam.\r\n\r\nChiều 24/1, tại trụ sở Tỉnh ủy Quảng Nam, Trưởng ban Tổ chức Trung ương Trương Thị Mai trao quyết định của Bộ Chính trị cho ông Triết. Ông sẽ tham gia Ban Chấp hành, Ban Thường vụ và giữ chức Bí thư Tỉnh ủy Quảng Nam nhiệm kỳ 2020-2025.\r\n\r\nÔng Triết sẽ thay ông Phan Việt Cường, được Bộ Chính trị cho nghỉ chờ hưu từ ngày 1/1, nghỉ chính thức từ 1/7.', 'testmoi ne bro', 1, '0', 'testmoi-ne-bro-12', '/upload/news/News12-1.png'),
	(13, 'admin1', '2024-01-25 21:14:06', 'admin1', '2024-01-25 21:14:06', '\r\n\r\n<p>A new high-street kebab offering has been described as a &lsquo;gamechanger&rsquo;. But can it challenge the popularity of a fried breakfast?</p>\r\n\r\n<address>&nbsp;</address>\r\n\r\n<p>Wed 17 Jan 2024 17.33 GMT</p>\r\n\r\n<ul>\r\n	<li>&nbsp;</li>\r\n	<li>&nbsp;</li>\r\n	<li>&nbsp;</li>\r\n</ul>\r\n\r\n<p><strong>Name:</strong>&nbsp;The breakfast kebab.</p>\r\n\r\n<p><strong>Age:</strong>&nbsp;Brand new.</p>\r\n\r\n<p><strong>Appearance:</strong>&nbsp;Like a kebab, but for breakfast.</p>\r\n\r\n<p><strong>Surely any kebab can be breakfast if you eat it at breakfast time</strong><strong>?&nbsp;</strong>Perhaps, but this is a purpose-built breakfast kebab &ndash; a wrap containing chicken or beef with egg, tomato and a side of hash brown &ldquo;bites&rdquo;, available from 7am.</p>\r\n\r\n<p><strong>Exactly where is it available?</strong>&nbsp;At the fast food outlet German Doner Kebab (GDK).</p>\r\n\r\n<p><strong>OK. And exactly why is it available?&nbsp;</strong>GDK&rsquo;s CEO, Simon Wallis, says the company is on a mission to &ldquo;reverse the stereotypical associations the great British public have with this product&rdquo;.</p>\r\n\r\n<p><strong>I&rsquo;m not sure they&rsquo;ve&nbsp;</strong><strong>succeeded there.</strong>&nbsp;Wallis also called the breakfast kebab a &ldquo;gamechanger&rdquo; when it was launched at GDK&rsquo;s new restaurant at Baldock services on&nbsp;the A1(M).</p>\r\n\r\n<p><strong>The last time I ate a kebab at 7</strong><strong>am, I was in no shape to drive.&nbsp;</strong>It&rsquo;s also available at the central London branch. If it&rsquo;s a success, it&nbsp;could be rolled out nationwide.</p>\r\n\r\n<p><img alt="" src="http://localhost:8080/template/ckfinder/core/connector/java/connector.java?command=Thumbnail&amp;type=Images&amp;currentFolder=%2F&amp;langCode=vi&amp;hash=27095d05ab4f1de&amp;FileName=2756%20(1).png" style="height:100px; width:80px" /></p>\r\n', 'The breakfast kebab: is this the morning meal we’ve all been waiting for?', 1, '0', 'the-breakfast-kebab-13', '/upload/news/News13-1.png'),
	(14, 'admin1', '2024-01-28 22:21:07', 'admin1', '2024-01-28 22:21:07', '<p>hehehehe</p>\r\n', 'bai viet moi ne bro', 1, '0', 'bai-viet-moi-ne-bro-14', '/upload/news/News14-1.png');

-- Dumping structure for table fastfoodspringmvc.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `orderStatus` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `totalpay` double DEFAULT NULL,
  `type` int(11) NOT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ovx1a77y1wro2ypuyiicu2toe` (`userid`),
  KEY `FK_haujdjk1ohmeixjhnhslchrp1` (`payment_id`),
  CONSTRAINT `FK_haujdjk1ohmeixjhnhslchrp1` FOREIGN KEY (`payment_id`) REFERENCES `payment_detail` (`id`),
  CONSTRAINT `FK_ovx1a77y1wro2ypuyiicu2toe` FOREIGN KEY (`userid`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.orders: ~0 rows (approximately)

-- Dumping structure for table fastfoodspringmvc.orders_item
CREATE TABLE IF NOT EXISTS `orders_item` (
  `orderid` bigint(20) NOT NULL,
  `itemid` bigint(20) NOT NULL,
  KEY `FK_81nnfnf0urfhnrqosaqixgv46` (`itemid`),
  KEY `FK_biv3anw3mii1q9366ukkya7p0` (`orderid`),
  CONSTRAINT `FK_81nnfnf0urfhnrqosaqixgv46` FOREIGN KEY (`itemid`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_biv3anw3mii1q9366ukkya7p0` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.orders_item: ~0 rows (approximately)

-- Dumping structure for table fastfoodspringmvc.passwordresettoken
CREATE TABLE IF NOT EXISTS `passwordresettoken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `expiryDateTime` tinyblob DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_odonwst8gvd0w1ikdrsjmh0vj` (`account_id`),
  CONSTRAINT `FK_odonwst8gvd0w1ikdrsjmh0vj` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.passwordresettoken: ~6 rows (approximately)
INSERT INTO `passwordresettoken` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `expiryDateTime`, `token`, `account_id`) VALUES
	(5, 'anonymousUser', '2024-01-20 23:22:21', 'anonymousUser', '2024-01-20 23:22:21', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e8011417341500b71b0078, '65d4c26e-3b7b-4e89-bc8e-f94c49fbb48e', 2),
	(6, 'anonymousUser', '2024-01-20 23:22:33', 'anonymousUser', '2024-01-20 23:22:33', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e80114173421125bb50078, '695755d7-2057-47ba-aef9-8663ef504d14', 2),
	(7, 'anonymousUser', '2024-01-20 23:37:31', 'anonymousUser', '2024-01-20 23:37:31', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e8011500071f3066230078, 'bfb10d03-712d-426c-926b-a2df6fd673d1', 2),
	(8, 'anonymousUser', '2024-01-20 23:42:44', 'anonymousUser', '2024-01-20 23:42:44', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e80115000c2c0ac9d74078, 'f5c1a477-1ba0-477a-b07d-f5aed3b3d805', 2),
	(9, 'anonymousUser', '2024-01-20 23:45:07', 'anonymousUser', '2024-01-20 23:45:07', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e80115000f0717d7840078, '4829407c-5517-45f2-b5bc-ce4a99288316', 2),
	(10, 'user1', '2024-01-20 23:55:24', 'user1', '2024-01-20 23:55:24', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e8011500191810642ac078, '5cd483ce-2309-491f-8464-ede59e509db4', 2),
	(11, 'anonymousUser', '2024-01-21 13:27:40', 'anonymousUser', '2024-01-21 13:27:40', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e801150d392815752a0078, '9915a399-76db-4d5f-bcdc-35efff71311f', 2),
	(12, 'anonymousUser', '2024-01-28 22:31:32', 'anonymousUser', '2024-01-28 22:31:32', _binary 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e8011c170120090013c078, '24be90cc-5100-49d6-9358-0cd2cd6c71e8', 2);

-- Dumping structure for table fastfoodspringmvc.payment_detail
CREATE TABLE IF NOT EXISTS `payment_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `orderInfo` varchar(255) DEFAULT NULL,
  `paymentTime` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `totalPrice` varchar(255) DEFAULT NULL,
  `transactionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.payment_detail: ~0 rows (approximately)

-- Dumping structure for table fastfoodspringmvc.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `in_stock` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `saleprice` double DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rlaghtegr0yx2c1q1s6nkqjlh` (`category_id`),
  CONSTRAINT `FK_rlaghtegr0yx2c1q1s6nkqjlh` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.product: ~4 rows (approximately)
INSERT INTO `product` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `description`, `in_stock`, `price`, `productname`, `saleprice`, `slug`, `status`, `category_id`) VALUES
	(3, NULL, NULL, NULL, NULL, 'hehe', 100, 5, 'Burger Beef', 5, 'burger-beef-3', 1, 1),
	(10, 'admin1', '2024-01-17 20:25:31', 'admin1', '2024-01-17 20:25:31', '<p><span style="color:hsl(30, 75%, 60%);">dsf</span></p>', 0, 5, 'ads', 5, 'ads', 1, 1),
	(43, 'admin1', '2024-01-19 18:21:23', 'anonymousUser', '2024-01-22 11:18:20', '<p><img alt="" src="http://localhost:8080/template/ckfinder/core/connector/java/connector.java?command=Thumbnail&amp;type=Images&amp;currentFolder=%2F&amp;langCode=vi&amp;hash=27095d05ab4f1de&amp;FileName=icon-admin.png" style="height:100px; width:100px" />asd</p>\r\n', 1, 1, 'haha', 1, 'haha-43', 1, 1),
	(44, 'admin1', '2024-01-28 22:22:55', 'admin1', '2024-01-28 22:22:55', '<p>coca cola ngon lam</p>\r\n', 100, 2, 'coca cola', 2, 'coca-cola-44', 1, 2);

-- Dumping structure for table fastfoodspringmvc.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.role: ~4 rows (approximately)
INSERT INTO `role` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `name`) VALUES
	(1, NULL, NULL, NULL, NULL, 'USER'),
	(2, NULL, NULL, NULL, NULL, 'ADMIN'),
	(3, NULL, NULL, NULL, NULL, 'STAFF'),
	(4, NULL, NULL, NULL, NULL, 'SALES');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
