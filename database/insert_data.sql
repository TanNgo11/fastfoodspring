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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.account: ~1 rows (approximately)
INSERT INTO `account` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `address`, `email`, `fullname`, `oauth2_Id`, `password`, `phonenumber`, `status`, `username`) VALUES
	(1, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, '$2a$12$7g8HCCNAsml./lt/p7zBQOCZbLNm2Vz0Uxx4vOHjAJkXlUK4dI7qS', NULL, 1, 'admin1');

-- Dumping structure for table fastfoodspringmvc.account_role
CREATE TABLE IF NOT EXISTS `account_role` (
  `accountid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  KEY `FK_nssbcg9f81k63xa5me911x7dx` (`roleid`),
  KEY `FK_cjcq4lr37fq88rdhmhdn2eo7d` (`accountid`),
  CONSTRAINT `FK_cjcq4lr37fq88rdhmhdn2eo7d` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_nssbcg9f81k63xa5me911x7dx` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.account_role: ~1 rows (approximately)
INSERT INTO `account_role` (`accountid`, `roleid`) VALUES
	(1, 2);

-- Dumping structure for table fastfoodspringmvc.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.category: ~2 rows (approximately)
INSERT INTO `category` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `type`) VALUES
	(1, NULL, NULL, NULL, NULL, 'food'),
	(2, NULL, NULL, NULL, NULL, 'drink');

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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.image: ~6 rows (approximately)
INSERT INTO `image` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `image_url`, `product_id`) VALUES
	(1, NULL, NULL, NULL, NULL, '/inc/Food/Burger-beef.png', 3),
	(2, NULL, NULL, NULL, NULL, '/inc/Food/burger-Shrimp.png', 3),
	(3, NULL, NULL, NULL, NULL, '/inc/Food/Cheese-Burger.png', 3),
	(4, NULL, NULL, NULL, NULL, '/inc/Food/Cheese-Burger.png', 10),
	(90, 'admin1', '2024-01-20 12:32:38', 'admin1', '2024-01-20 12:32:38', '/upload/product/Product43-1.png', 43),
	(91, 'admin1', '2024-01-20 12:32:38', 'admin1', '2024-01-20 12:32:38', '/upload/product/Product43-2.png', 43);

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
  PRIMARY KEY (`id`),
  KEY `FK_ajv99jnnrna0poym69pdusrlv` (`account_id`),
  CONSTRAINT `FK_ajv99jnnrna0poym69pdusrlv` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.news: ~0 rows (approximately)

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.product: ~3 rows (approximately)
INSERT INTO `product` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `description`, `in_stock`, `price`, `productname`, `saleprice`, `slug`, `status`, `category_id`) VALUES
	(3, NULL, NULL, NULL, NULL, 'hehe', 100, 5, 'Burger Beef', 5, 'burger-beef-3', 1, 1),
	(10, 'admin1', '2024-01-17 20:25:31', 'admin1', '2024-01-17 20:25:31', '<p><span style="color:hsl(30, 75%, 60%);">dsf</span></p>', 0, 5, 'ads', 5, 'ads', 1, 1),
	(43, 'admin1', '2024-01-19 18:21:23', 'admin1', '2024-01-19 18:21:23', '<p>asd</p>\r\n', 1, 1, 'haha', 1, 'haha-43', 1, 1);

-- Dumping structure for table fastfoodspringmvc.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdBy` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table fastfoodspringmvc.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `createdBy`, `createddate`, `modifiedby`, `modifieddate`, `name`) VALUES
	(1, NULL, NULL, NULL, NULL, 'USER'),
	(2, NULL, NULL, NULL, NULL, 'ADMIN');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
