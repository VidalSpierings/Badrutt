-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: test_schema
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `klantoptijdslotvooractiviteitsnaamoplocatie`
--

DROP TABLE IF EXISTS `klantoptijdslotvooractiviteitsnaamoplocatie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klantoptijdslotvooractiviteitsnaamoplocatie` (
  `start_time` timestamp NOT NULL,
  `customer` varchar(100) NOT NULL,
  `activity_name` varchar(150) NOT NULL,
  `location` varchar(125) NOT NULL,
  PRIMARY KEY (`start_time`),
  KEY `customer_fk_idx` (`customer`),
  KEY `activity_name_fk_idx` (`activity_name`),
  KEY `location_fk_idx` (`location`),
  CONSTRAINT `activity_name_fk` FOREIGN KEY (`activity_name`) REFERENCES `activiteitsnaamvanlocatietype` (`activity_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_fk` FOREIGN KEY (`customer`) REFERENCES `klant` (`customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `location_fk` FOREIGN KEY (`location`) REFERENCES `locatievanlocatietype` (`location`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klantoptijdslotvooractiviteitsnaamoplocatie`
--

LOCK TABLES `klantoptijdslotvooractiviteitsnaamoplocatie` WRITE;
/*!40000 ALTER TABLE `klantoptijdslotvooractiviteitsnaamoplocatie` DISABLE KEYS */;
INSERT INTO `klantoptijdslotvooractiviteitsnaamoplocatie` VALUES ('2024-06-18 10:00:00','Jean-Pierre','skiing and snowboarding','Piz Gügliet'),('2025-07-19 11:00:00','John','ice skating','Lej da San Murrezan');
/*!40000 ALTER TABLE `klantoptijdslotvooractiviteitsnaamoplocatie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-28 20:40:19
