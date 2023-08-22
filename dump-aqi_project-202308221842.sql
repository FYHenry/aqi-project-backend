-- MariaDB dump 10.19  Distrib 10.6.12-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: aqi_project
-- ------------------------------------------------------
-- Server version	10.6.12-MariaDB-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city_insee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfyhv77yx7lqkf0fc2qinhvkvd` (`city_insee`),
  CONSTRAINT `FKfyhv77yx7lqkf0fc2qinhvkvd` FOREIGN KEY (`city_insee`) REFERENCES `city` (`insee`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'a','a','09001');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `air_quality_report`
--

DROP TABLE IF EXISTS `air_quality_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `air_quality_report` (
  `air_quality_station_id` int(11) DEFAULT NULL,
  `aqi` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no2` double DEFAULT NULL,
  `o3` double DEFAULT NULL,
  `pm10` int(11) NOT NULL,
  `pm25` int(11) NOT NULL,
  `report_date_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1oloxcejnwmci3k75tswip9bj` (`air_quality_station_id`),
  KEY `FK62dmc53o0sj6nvyin122bqscu` (`report_date_id`),
  CONSTRAINT `FK1oloxcejnwmci3k75tswip9bj` FOREIGN KEY (`air_quality_station_id`) REFERENCES `air_quality_station` (`id`),
  CONSTRAINT `FK62dmc53o0sj6nvyin122bqscu` FOREIGN KEY (`report_date_id`) REFERENCES `report_date` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_quality_report`
--

LOCK TABLES `air_quality_report` WRITE;
/*!40000 ALTER TABLE `air_quality_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `air_quality_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `air_quality_station`
--

DROP TABLE IF EXISTS `air_quality_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `air_quality_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `city_insee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKna2vs2d20lek57bp18whf49v8` (`city_insee`),
  CONSTRAINT `FKna2vs2d20lek57bp18whf49v8` FOREIGN KEY (`city_insee`) REFERENCES `city` (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_quality_station`
--

LOCK TABLES `air_quality_station` WRITE;
/*!40000 ALTER TABLE `air_quality_station` DISABLE KEYS */;
/*!40000 ALTER TABLE `air_quality_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `between_account_and_status`
--

DROP TABLE IF EXISTS `between_account_and_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `between_account_and_status` (
  `user_account_id` int(11) NOT NULL,
  `user_status_id` int(11) NOT NULL,
  KEY `FK93w7v4509cgfc83klboobcllj` (`user_status_id`),
  KEY `FK76dkl4agcjx0xa15keue4mhv1` (`user_account_id`),
  CONSTRAINT `FK76dkl4agcjx0xa15keue4mhv1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`user_account_id`),
  CONSTRAINT `FK93w7v4509cgfc83klboobcllj` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`user_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `between_account_and_status`
--

LOCK TABLES `between_account_and_status` WRITE;
/*!40000 ALTER TABLE `between_account_and_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `between_account_and_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark` (
  `forecast_type_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account_user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2n4f1mb3dspbiv7fyp018lmhw` (`forecast_type_id`),
  KEY `FKgiqgu6u1n6x6dtdwxtlkarj34` (`user_account_user_account_id`),
  CONSTRAINT `FK2n4f1mb3dspbiv7fyp018lmhw` FOREIGN KEY (`forecast_type_id`) REFERENCES `forecast_type` (`id`),
  CONSTRAINT `FKgiqgu6u1n6x6dtdwxtlkarj34` FOREIGN KEY (`user_account_user_account_id`) REFERENCES `user_account` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `department_insee` varchar(255) DEFAULT NULL,
  `insee` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`insee`),
  KEY `FKkcs5inr7mys6eou1rbusk3q32` (`department_insee`),
  CONSTRAINT `FKkcs5inr7mys6eou1rbusk3q32` FOREIGN KEY (`department_insee`) REFERENCES `department` (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (43.0502,1.471,'09','09001','Aigues-Juntes'),(42.9996,1.8781,'09','09002','Aigues-Vives'),(42.9102,1.9035,'09','09003','L\'Aiguillon'),(42.7539,1.6965,'09','09004','Albiès'),(42.8927,1.2683,'09','09005','Aleu'),(42.8181,1.5778,'09','09006','Alliat'),(43.0391,1.3631,'09','09007','Allières'),(42.9013,1.1318,'09','09008','Alos'),(42.9805,1.4539,'09','09009','Alzen'),(42.8713,0.9023,'09','09011','Antras'),(42.8032,1.7436,'09','09012','Appy');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city_postcodes`
--

DROP TABLE IF EXISTS `city_postcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_postcodes` (
  `postcodes` int(11) DEFAULT NULL,
  `city_insee` varchar(255) NOT NULL,
  KEY `FKkb66uadblbaj84sgr5nyny1py` (`city_insee`),
  CONSTRAINT `FKkb66uadblbaj84sgr5nyny1py` FOREIGN KEY (`city_insee`) REFERENCES `city` (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_postcodes`
--

LOCK TABLES `city_postcodes` WRITE;
/*!40000 ALTER TABLE `city_postcodes` DISABLE KEYS */;
INSERT INTO `city_postcodes` VALUES (9240,'09001'),(9600,'09002'),(9300,'09003'),(9310,'09004'),(9320,'09005'),(9400,'09006'),(9240,'09007'),(9200,'09008'),(9240,'09009'),(9800,'09011'),(9250,'09012');
/*!40000 ALTER TABLE `city_postcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `insee` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region_insee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`insee`),
  KEY `FK9i0shyfh12bmctqjad7x1n2c4` (`region_insee`),
  CONSTRAINT `FK9i0shyfh12bmctqjad7x1n2c4` FOREIGN KEY (`region_insee`) REFERENCES `region` (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('09','Ariège','76');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forecast_type`
--

DROP TABLE IF EXISTS `forecast_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forecast_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forecast_type`
--

LOCK TABLES `forecast_type` WRITE;
/*!40000 ALTER TABLE `forecast_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `forecast_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `user_account_user_account_id` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKng9f5tmplkvofk6x34knntjqs` (`thread_id`),
  KEY `FK6cpl7pmmjhpu5ui662u9te3yk` (`user_account_user_account_id`),
  CONSTRAINT `FK6cpl7pmmjhpu5ui662u9te3yk` FOREIGN KEY (`user_account_user_account_id`) REFERENCES `user_account` (`user_account_id`),
  CONSTRAINT `FKng9f5tmplkvofk6x34knntjqs` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `population`
--

DROP TABLE IF EXISTS `population`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `population` (
  `date` date DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `population_number` bigint(20) NOT NULL,
  `city_insee` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowg0nk90yes13po2p84svxvaf` (`city_insee`),
  CONSTRAINT `FKowg0nk90yes13po2p84svxvaf` FOREIGN KEY (`city_insee`) REFERENCES `city` (`insee`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `population`
--

LOCK TABLES `population` WRITE;
/*!40000 ALTER TABLE `population` DISABLE KEYS */;
INSERT INTO `population` VALUES ('2023-08-22',1,57,'09001',NULL),('2023-08-22',2,634,'09002',NULL),('2023-08-22',3,381,'09003',NULL),('2023-08-22',4,120,'09004',NULL),('2023-08-22',5,128,'09005',NULL),('2023-08-22',6,56,'09006',NULL),('2023-08-22',7,73,'09007',NULL),('2023-08-22',8,120,'09008',NULL),('2023-08-22',9,267,'09009',NULL),('2023-08-22',10,74,'09011',NULL),('2023-08-22',11,28,'09012',NULL);
/*!40000 ALTER TABLE `population` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `user_account_user_account_id` int(11) DEFAULT NULL,
  `reaction_type` enum('MINUS_ONE','PLUS_ONE','ZERO') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnnwfsi01kkhg9o9woxilp9dep` (`message_id`),
  KEY `FK1cj1cuvsoi8vhgcfujbeumdyd` (`user_account_user_account_id`),
  CONSTRAINT `FK1cj1cuvsoi8vhgcfujbeumdyd` FOREIGN KEY (`user_account_user_account_id`) REFERENCES `user_account` (`user_account_id`),
  CONSTRAINT `FKnnwfsi01kkhg9o9woxilp9dep` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `insee` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES ('1','abc'),('76','Occitanie');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_date`
--

DROP TABLE IF EXISTS `report_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_date`
--

LOCK TABLES `report_date` WRITE;
/*!40000 ALTER TABLE `report_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thread`
--

DROP TABLE IF EXISTS `thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `user_account_user_account_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkpxdvhiu73mdmtkrieddk3tpa` (`topic_id`),
  KEY `FKoad0k1jwmcgw37libe1e6fcrk` (`user_account_user_account_id`),
  CONSTRAINT `FKkpxdvhiu73mdmtkrieddk3tpa` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `FKoad0k1jwmcgw37libe1e6fcrk` FOREIGN KEY (`user_account_user_account_id`) REFERENCES `user_account` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thread`
--

LOCK TABLES `thread` WRITE;
/*!40000 ALTER TABLE `thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account_user_account_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0lniglwft2e0e8jgsuncnw80` (`user_account_user_account_id`),
  CONSTRAINT `FKc0lniglwft2e0e8jgsuncnw80` FOREIGN KEY (`user_account_user_account_id`) REFERENCES `user_account` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `address_id` int(11) NOT NULL,
  `user_account_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  PRIMARY KEY (`user_account_id`),
  UNIQUE KEY `UK_hl02wv5hym99ys465woijmfib` (`email`),
  KEY `FK9ds10517tjcuxwci9por8ym82` (`address_id`),
  CONSTRAINT `FK9ds10517tjcuxwci9por8ym82` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,'mm@gmail.com','monprenom','monnom','{bcrypt}$2a$10$lS5DY4QGawgojVDN8Z6ndud.IeJtIeZLqFkPjsWaJv68i4X.0w1X2','ADMIN');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_status` (
  `user_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_date` datetime(6) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `label` varchar(50) NOT NULL,
  `explanation` varchar(255) NOT NULL,
  `memo` varchar(255) NOT NULL,
  PRIMARY KEY (`user_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather_report`
--

DROP TABLE IF EXISTS `weather_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weather_report` (
  `humidity` double NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pressure` double NOT NULL,
  `report_date_id` int(11) DEFAULT NULL,
  `temperature` double NOT NULL,
  `weather_code` int(11) NOT NULL,
  `weather_station_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7t2jwhupiut54vdxix66nk6vi` (`report_date_id`),
  KEY `FKaojbt9wbnouyc9yqkefxkw3p6` (`weather_station_id`),
  CONSTRAINT `FK7t2jwhupiut54vdxix66nk6vi` FOREIGN KEY (`report_date_id`) REFERENCES `report_date` (`id`),
  CONSTRAINT `FKaojbt9wbnouyc9yqkefxkw3p6` FOREIGN KEY (`weather_station_id`) REFERENCES `weather_station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather_report`
--

LOCK TABLES `weather_report` WRITE;
/*!40000 ALTER TABLE `weather_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `weather_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather_station`
--

DROP TABLE IF EXISTS `weather_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weather_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `city_insee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcstos4x25o7xnkmeqibirn58f` (`city_insee`),
  CONSTRAINT `FKcstos4x25o7xnkmeqibirn58f` FOREIGN KEY (`city_insee`) REFERENCES `city` (`insee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather_station`
--

LOCK TABLES `weather_station` WRITE;
/*!40000 ALTER TABLE `weather_station` DISABLE KEYS */;
/*!40000 ALTER TABLE `weather_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'aqi_project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-22 18:42:37
