-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: project2
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Nước uống thanh lọc'),(2,'Thức ăn thực dưỡng'),(3,'Cà phê hòa tan'),(4,'Nước ép, sinh tố'),(5,'Cà phê năng lượng');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `menu_name` varchar(150) NOT NULL,
  `price` int NOT NULL,
  `image` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_product_idx` (`category_id`),
  CONSTRAINT `category_id_menu` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,1,'Caramel Macchiato',60000,'1.jpg'),(2,1,'Ly Chanh Gừng Sả Hạt Chia',45000,'2.jpg'),(3,1,'Ly Trà Lá Nếp Sen Vàng',120000,'3.jpg'),(4,1,'Sữa Chua Thực Dưỡng',50000,'4.jpg'),(5,1,'Ly Trà Đào Cam Sả',70000,'5.jpg'),(6,1,'Trà Ngũ Bảo Milk Foam',65000,'6.jpg'),(7,2,'Bánh Mì Xíu Mại',50000,'7.jpg'),(8,2,'Bánh Mì Bít Tết Ốp La',55000,'8.jpg'),(9,2,'Bánh Mì Legend',30000,'9.jpg'),(10,2,'Bánh Croissant hạnh nhân',40000,'10.jpg'),(11,2,'Bánh Chuối Mật Ong',30000,'11.jpg'),(12,3,'Trung Nguyên Legend Classic - Hộp 21 gói',70000,'12.jpg'),(13,3,'Legend Special Edition 9 Sticks',50000,'13.jpg'),(14,3,'Legend Cappuccino Coconut - 12 Sticks',55000,'14.jpg'),(15,3,'Cà phê hòa tan G7 Gu Mạnh X2',60000,'15.jpg'),(16,3,'Cà Phê G7 3in1 - Bịch 50 Sachets',140000,'16.jpg'),(17,3,'Cà Phê Drip 1 - Culi Robusta',500000,'17.jpg'),(18,3,'Trung Nguyên Legend Cappuccino Hazelnut',70000,'18.jpg'),(19,3,'Legend Café Sữa Đá - 9 Sticks',40000,'19.jpg'),(20,4,'Nước ép cam',35000,'20.jpg'),(21,4,'Nước ép ổi',35000,'21.jpg'),(22,4,'Nước ép dứa',35000,'22.jpg'),(23,4,'Sinh tố bơ',50000,'23.jpg'),(24,4,'Sinh tố xoài',40000,'24.jpg'),(25,4,'Sinh tố mãng cầu',50000,'25.jpg'),(26,5,'Cà phê sữa',40000,'26.jpg'),(27,5,'Cà phê đen',40000,'27.jpg'),(28,5,'Cà Phê L’amour',70000,'28.jpg'),(29,5,'Cà Phê Mother Land (Nóng)',65000,'29.jpg'),(30,5,'Express Đá Viên',60000,'30.jpg'),(31,5,'Express',60000,'31.jpg'),(32,5,'Cà phê muối',40000,'32.jpg');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `menu_id` int NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` int NOT NULL,
  `into_money` int NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`id`),
  KEY `order_id_idx1` (`order_id`),
  KEY `menu_id_order_idx` (`menu_id`),
  CONSTRAINT `menu_id_order` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,26,2,40000,80000,'1 nhiều đá, 1 ít đá'),(2,1,27,1,40000,40000,''),(3,2,20,2,35000,70000,''),(4,2,22,1,35000,35000,''),(5,2,7,2,50000,100000,''),(6,2,9,1,30000,30000,''),(7,3,1,2,60000,120000,''),(8,3,4,1,50000,50000,''),(9,3,23,1,50000,50000,''),(10,3,19,1,40000,40000,'Mang về'),(11,4,26,1,40000,40000,''),(12,4,27,1,40000,40000,''),(13,4,30,1,60000,60000,''),(14,4,29,1,65000,65000,''),(15,5,17,1,500000,500000,'Mang về'),(16,4,13,1,50000,50000,'Mang về'),(17,6,1,1,60000,60000,''),(18,6,4,1,50000,50000,''),(19,7,1,3,60000,180000,''),(20,7,5,1,70000,70000,''),(21,8,30,1,60000,60000,''),(22,8,26,1,40000,40000,''),(23,9,6,1,65000,65000,''),(24,9,5,1,70000,70000,''),(25,10,10,1,40000,40000,''),(26,10,11,1,30000,30000,''),(27,10,20,3,35000,105000,''),(28,11,3,1,120000,120000,''),(29,11,2,1,45000,45000,''),(30,11,5,1,70000,70000,''),(31,12,26,2,40000,80000,''),(32,12,27,1,40000,40000,''),(33,13,26,1,40000,40000,''),(34,13,27,2,40000,80000,''),(35,14,1,2,60000,120000,''),(36,14,3,1,120000,120000,''),(37,14,4,3,50000,150000,''),(38,15,7,2,50000,100000,''),(39,15,8,1,55000,55000,''),(40,15,9,1,30000,30000,''),(41,15,20,2,35000,70000,''),(42,15,21,1,35000,35000,''),(43,15,22,1,35000,35000,''),(44,16,27,1,40000,40000,''),(45,16,28,1,70000,70000,''),(46,16,32,1,40000,40000,''),(47,17,1,6,60000,360000,''),(48,17,4,2,50000,100000,''),(49,17,7,2,50000,100000,''),(50,17,8,2,55000,110000,''),(51,17,9,2,30000,60000,''),(52,17,10,1,40000,40000,''),(53,17,11,1,30000,30000,''),(54,18,1,3,60000,180000,''),(55,19,26,2,40000,80000,''),(56,19,28,2,70000,140000,''),(57,19,17,1,500000,500000,'Mang về'),(58,19,8,4,55000,220000,''),(59,19,16,1,140000,140000,'Mang về');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `table_id` int NOT NULL,
  `status_id` int NOT NULL,
  `total` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `table_id_order_idx` (`table_id`),
  KEY `status_id_order_idx` (`status_id`),
  CONSTRAINT `status_id_order` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `table_id_order` FOREIGN KEY (`table_id`) REFERENCES `table` (`id`),
  CONSTRAINT `user_id_order` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,1,120000,'2022-04-01 20:21:20'),(2,1,9,1,235000,'2022-04-01 20:32:23'),(3,2,21,1,260000,'2022-07-01 21:01:11'),(4,2,1,1,255000,'2022-07-06 22:18:11'),(5,2,2,1,500000,'2022-07-06 21:23:56'),(6,2,8,1,110000,'2022-07-07 15:15:15'),(7,3,19,1,250000,'2022-07-07 20:52:51'),(8,3,15,1,100000,'2022-07-07 20:32:16'),(9,2,5,1,135000,'2022-08-15 15:23:51'),(10,2,8,1,175000,'2022-08-15 15:18:55'),(11,4,6,1,235000,'2022-08-23 16:39:02'),(12,3,1,1,120000,'2022-09-01 16:00:25'),(13,3,11,1,120000,'2022-11-01 15:33:33'),(14,2,21,1,390000,'2022-11-21 21:11:02'),(15,5,15,1,325000,'2023-06-16 07:10:21'),(16,5,1,1,150000,'2023-06-16 15:51:15'),(17,4,15,1,800000,'2023-08-10 23:15:20'),(18,4,11,1,180000,'2023-08-10 21:12:11'),(19,3,1,1,1080000,'2023-09-02 22:33:11');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Nhân viên');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Trống'),(2,'Đang phục vụ'),(3,'Đã thanh toán');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `table_name` varchar(10) NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_id_table_idx` (`status_id`),
  CONSTRAINT `status_id_table` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
INSERT INTO `table` VALUES (1,'B1',1),(2,'B2',1),(3,'B3',1),(4,'B4',1),(5,'B5',1),(6,'B6',1),(7,'B7',1),(8,'B8',1),(9,'B9',1),(10,'B10',1),(11,'B11',1),(12,'B12',1),(13,'B13',1),(14,'B14',1),(15,'B15',1),(16,'B16',1),(17,'B17',1),(18,'B18',1),(19,'B19',1),(20,'B20',1),(21,'B21',1);
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(150) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  `salary` int NOT NULL,
  `work` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id_user_idx` (`role_id`),
  CONSTRAINT `role_id_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'admin','88e924dc5932d79457f84b1f65824147','ADMIN','1995-01-01','Nam','admin@mail.com','0987654321','Admin',0,'2022-04-01'),(2,2,'nhanvien','727e3cffe747f51a3ea29c7c4e39f337','NHÂN VIÊN','2000-03-02','Nam','nhanvien@mail.com','0912345678','Việt Nam',5000000,'2022-07-01'),(3,2,'nhanvien1','727e3cffe747f51a3ea29c7c4e39f337','NHÂN VIÊN A','2003-03-23','Nữ','nhanvien1@mail.com','0987456123','Việt Nam',5000000,'2022-07-07'),(4,2,'nhanvien2','727e3cffe747f51a3ea29c7c4e39f337','NHÂN VIÊN B','2001-08-10','Nam','nhanvien2@mail.com','0961235488','Việt Nam',5000000,'2022-08-22'),(5,2,'nhanvien3','727e3cffe747f51a3ea29c7c4e39f337','NHÂN VIÊN C','2002-11-01','Nữ','nhanvien3@mail.com','0965683291','Việt Nam',5000000,'2023-06-15');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-10 13:59:07
