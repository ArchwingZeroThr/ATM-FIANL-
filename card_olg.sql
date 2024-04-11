-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: card
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `olg`
--

DROP TABLE IF EXISTS `olg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `olg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `message1` varchar(45) DEFAULT NULL,
  `message2` varchar(45) DEFAULT NULL,
  `op` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `olg`
--

LOCK TABLES `olg` WRITE;
/*!40000 ALTER TABLE `olg` DISABLE KEYS */;
INSERT INTO `olg` VALUES (1,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(2,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(3,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(4,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(5,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(6,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(7,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(8,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(9,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(10,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(11,'10.234.114.108','19','AMNT:1000.00','Balance inquiry successful','BALA'),(12,'10.234.114.108','19','401 ERROR!','Withdrawal unsuccessful.Insufficient funds.','WDRA 1200.0'),(13,'10.234.114.108','19','525 OK!','Withdrawal successful. New balance: 100.0','WDRA 900.0'),(14,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(15,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(16,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(17,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(18,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(19,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(20,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(21,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(22,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(23,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(24,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(25,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(26,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(27,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(28,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(29,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(30,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(31,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(32,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(33,'10.234.114.108','19','AMNT:100.00','Balance inquiry successful','BALA'),(34,'10.234.114.108','19','525 OK!','Withdrawal successful. New balance: 90.0','WDRA 10.0'),(35,'10.234.114.108','19','AMNT:90.00','Balance inquiry successful','BALA'),(36,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(37,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(38,'10.234.114.108','19','AMNT:90.00','Balance inquiry successful','BALA'),(39,'10.234.114.108','19','525 OK!','Withdrawal successful. New balance: 80.0','WDRA 10.0'),(40,'10.234.114.108','19','AMNT:80.00','Balance inquiry successful','BALA'),(41,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19'),(42,'10.234.114.108','19','525 OK!','Password and account match','PASS 19'),(43,'10.234.114.108','19','AMNT:80.00','Balance inquiry successful','BALA'),(44,'10.234.114.108','19','525 OK!','Withdrawal successful. New balance: 69.8','WDRA 10.2'),(45,'10.234.114.108','19','AMNT:69.80','Balance inquiry successful','BALA'),(46,'10.234.114.108','19','500 AUTH REQUIRED!','HELO 19','record found for cid: 19');
/*!40000 ALTER TABLE `olg` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-11 14:30:31
