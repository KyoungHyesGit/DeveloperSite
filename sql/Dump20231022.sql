-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: developer_site
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
-- Table structure for table `advice_board`
--

DROP TABLE IF EXISTS `advice_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advice_board` (
  `advice_board_id` int NOT NULL,
  `user_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `kind` varchar(255) NOT NULL,
  PRIMARY KEY (`advice_board_id`,`user_id`),
  KEY `FK_GB_USER_TO_ADVICE_BOARD_1` (`user_id`),
  CONSTRAINT `FK_GB_USER_TO_ADVICE_BOARD_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice_board`
--

LOCK TABLES `advice_board` WRITE;
/*!40000 ALTER TABLE `advice_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `advice_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advice_board_reply`
--

DROP TABLE IF EXISTS `advice_board_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advice_board_reply` (
  `advice_board_id` int NOT NULL,
  `user_id` int NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `parent_reply_id` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`advice_board_id`,`user_id`),
  KEY `FK_ADVICE_BOARD_TO_ADVICE_BOARD_REPLY_2` (`user_id`),
  CONSTRAINT `FK_ADVICE_BOARD_TO_ADVICE_BOARD_REPLY_1` FOREIGN KEY (`advice_board_id`) REFERENCES `advice_board` (`advice_board_id`),
  CONSTRAINT `FK_ADVICE_BOARD_TO_ADVICE_BOARD_REPLY_2` FOREIGN KEY (`user_id`) REFERENCES `advice_board` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice_board_reply`
--

LOCK TABLES `advice_board_reply` WRITE;
/*!40000 ALTER TABLE `advice_board_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `advice_board_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_post`
--

DROP TABLE IF EXISTS `job_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_post` (
  `job_post_id` bigint NOT NULL AUTO_INCREMENT,
  `create_dt` datetime(6) DEFAULT NULL,
  `job_post_temp_detail` varchar(255) DEFAULT NULL,
  `job_post_temp_detail_addr` varchar(255) DEFAULT NULL,
  `job_post_temp_extra_add` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `job_post_temp_postdate` datetime(6) DEFAULT NULL,
  `job_post_temp_req` varchar(255) DEFAULT NULL,
  `job_post_temp_reqstate` varchar(255) DEFAULT NULL,
  `job_post_temp_salary` varchar(255) DEFAULT NULL,
  `job_post_temp_state` varchar(255) DEFAULT NULL,
  `job_post_temp_street_addr` varchar(255) DEFAULT NULL,
  `job_post_temp_title` varchar(255) DEFAULT NULL,
  `update_dt` datetime(6) DEFAULT NULL,
  `job_post_temp_work` varchar(255) DEFAULT NULL,
  `job_post_temp_zipcode` varchar(255) DEFAULT NULL,
  `job_post_temp_id` bigint DEFAULT NULL,
  `vender_id` bigint DEFAULT NULL,
  `job_post_temp_endtime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`job_post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_post`
--

LOCK TABLES `job_post` WRITE;
/*!40000 ALTER TABLE `job_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_post_temp`
--

DROP TABLE IF EXISTS `job_post_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_post_temp` (
  `job_post_temp_id` int NOT NULL AUTO_INCREMENT,
  `vender_id` int NOT NULL,
  `job_post_temp_title` varchar(100) NOT NULL,
  `job_post_temp_detail` varchar(3000) NOT NULL,
  `job_post_temp_salary` varchar(100) DEFAULT NULL,
  `job_post_temp_endtime` datetime(6) NOT NULL,
  `job_post_temp_postdate` datetime(6) DEFAULT NULL,
  `job_post_temp_state` varchar(20) NOT NULL,
  `job_post_temp_reqstate` varchar(20) DEFAULT NULL,
  `create_dt` datetime(6) DEFAULT NULL,
  `job_post_temp_detail_addr` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `job_post_temp_street_addr` varchar(255) DEFAULT NULL,
  `update_dt` datetime(6) DEFAULT NULL,
  `job_post_temp_zipcode` varchar(255) DEFAULT NULL,
  `job_post_temp_extra_add` varchar(255) DEFAULT NULL,
  `job_post_temp_req` varchar(255) DEFAULT NULL,
  `job_post_temp_work` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`job_post_temp_id`,`vender_id`),
  KEY `FK_VENDER_TO_JOB_POST_TEMP_1` (`vender_id`),
  CONSTRAINT `FK_VENDER_TO_JOB_POST_TEMP_1` FOREIGN KEY (`vender_id`) REFERENCES `vender` (`vender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_post_temp`
--

LOCK TABLES `job_post_temp` WRITE;
/*!40000 ALTER TABLE `job_post_temp` DISABLE KEYS */;
INSERT INTO `job_post_temp` VALUES (1,1,'tilte','detail ','3000','2023-10-27 01:09:00.000000',NULL,'등록',NULL,NULL,'','0:0:0:0:0:0:0:1','서울 동대문구 고산자로 534',NULL,'02467',' (제기동, 한신아파트)','B,E','work '),(2,1,'test1','detail ','3000','2023-11-07 00:00:00.000000',NULL,'등록',NULL,NULL,'','0:0:0:0:0:0:0:1','서울 강동구 가래여울길 71',NULL,'05200',' (고덕동)','B,D','work ');
/*!40000 ALTER TABLE `job_post_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jp_apply`
--

DROP TABLE IF EXISTS `jp_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jp_apply` (
  `Key` int NOT NULL,
  `user_id` int NOT NULL,
  `job_post_id` int NOT NULL,
  `job_post_temp_id` int NOT NULL,
  `vender_id` int NOT NULL,
  `like_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Key`,`user_id`,`job_post_id`,`job_post_temp_id`,`vender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jp_apply`
--

LOCK TABLES `jp_apply` WRITE;
/*!40000 ALTER TABLE `jp_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `jp_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jp_like`
--

DROP TABLE IF EXISTS `jp_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jp_like` (
  `Key` int NOT NULL,
  `user_id` int NOT NULL,
  `job_post_id` int NOT NULL,
  `job_post_temp_id` int NOT NULL,
  `vender_id` int NOT NULL,
  `like_date` varchar(255) NOT NULL,
  PRIMARY KEY (`Key`,`user_id`,`job_post_id`,`job_post_temp_id`,`vender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jp_like`
--

LOCK TABLES `jp_like` WRITE;
/*!40000 ALTER TABLE `jp_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `jp_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lov`
--

DROP TABLE IF EXISTS `lov`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lov` (
  `lov_id` int NOT NULL AUTO_INCREMENT,
  `label` varchar(20) NOT NULL,
  `kind` varchar(20) NOT NULL,
  `value` varchar(20) NOT NULL,
  `state` varchar(20) DEFAULT 'Y',
  PRIMARY KEY (`lov_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lov`
--

LOCK TABLES `lov` WRITE;
/*!40000 ALTER TABLE `lov` DISABLE KEYS */;
INSERT INTO `lov` VALUES (1,'중졸','post_req','A','Y'),(2,'고졸','post_req','B','Y'),(3,'초대졸','post_req','C','Y'),(4,'대졸','post_req','D','Y'),(5,'석사','post_req','E','Y'),(6,'박사','post_req','F','Y'),(7,'SI','post_work','A','Y'),(8,'SM','post_work','B','Y'),(9,'사내시스템운영','post_work','C','Y'),(10,'게임개발','post_work','D','Y'),(11,'게임운영','post_work','E','Y'),(12,'웹개발','post_work','F','Y');
/*!40000 ALTER TABLE `lov` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `birth` varchar(20) DEFAULT NULL,
  `privacy_state` varchar(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_resume`
--

DROP TABLE IF EXISTS `user_resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_resume` (
  `user_resume_id` int NOT NULL,
  `user_id` int NOT NULL,
  `addr_num` varchar(20) NOT NULL,
  `addr_basic` varchar(200) NOT NULL,
  `addr_detail` varchar(200) NOT NULL,
  `skill` varchar(1000) DEFAULT NULL,
  `final_education` varchar(200) NOT NULL,
  `state_resume` varchar(20) NOT NULL,
  `state_contact` varchar(20) NOT NULL,
  `certificattion` varchar(255) DEFAULT NULL,
  `work_exp` varchar(255) DEFAULT NULL,
  `training` varchar(255) DEFAULT NULL,
  `extracurricular _activities` varchar(255) DEFAULT NULL,
  `accomplishment` varchar(255) DEFAULT NULL,
  `overseas_exp` varchar(255) DEFAULT NULL,
  `language_skill` varchar(255) DEFAULT NULL,
  `portfolio` varchar(255) DEFAULT NULL,
  `git_addr` varchar(255) DEFAULT NULL,
  `military _service` varchar(255) DEFAULT NULL,
  `resume_title_1` varchar(1000) NOT NULL,
  `resume_content_1` varchar(3000) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_resume_id`,`user_id`),
  KEY `FK_GB_USER_TO_GB_USER_RESUME_1` (`user_id`),
  CONSTRAINT `FK_GB_USER_TO_GB_USER_RESUME_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_resume`
--

LOCK TABLES `user_resume` WRITE;
/*!40000 ALTER TABLE `user_resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vender`
--

DROP TABLE IF EXISTS `vender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vender` (
  `vender_id` int NOT NULL,
  `vender_email` varchar(255) NOT NULL,
  `vender_name` varchar(255) NOT NULL,
  `vender_passwd` varchar(255) NOT NULL,
  `create_dt` datetime(6) DEFAULT NULL,
  `update_dt` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`vender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vender`
--

LOCK TABLES `vender` WRITE;
/*!40000 ALTER TABLE `vender` DISABLE KEYS */;
INSERT INTO `vender` VALUES (1,'kimgyhye@naver.com','개발바닥','1234','2023-10-22 19:41:22.000000','2023-10-22 19:41:22.000000');
/*!40000 ALTER TABLE `vender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'developer_site'
--

--
-- Dumping routines for database 'developer_site'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-22 22:05:15
