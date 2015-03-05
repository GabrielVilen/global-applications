-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: iv1201gasakiproject
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `availability`
--

DROP TABLE IF EXISTS `availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `availability` (
  `availability_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`availability_id`),
  KEY `availability_person_id_idx` (`person_id`),
  CONSTRAINT `availability_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availability`
--

LOCK TABLES `availability` WRITE;
/*!40000 ALTER TABLE `availability` DISABLE KEYS */;
INSERT INTO `availability` VALUES (1,2,'2014-02-23','2014-05-25',1),(2,2,'2014-07-10','2014-08-10',1);
/*!40000 ALTER TABLE `availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence`
--

DROP TABLE IF EXISTS `competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence` (
  `competence_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`competence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence`
--

LOCK TABLES `competence` WRITE;
/*!40000 ALTER TABLE `competence` DISABLE KEYS */;
INSERT INTO `competence` VALUES (1,'Sausage grillning',1),(2,'Carousel operation',1);
/*!40000 ALTER TABLE `competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence_profile`
--

DROP TABLE IF EXISTS `competence_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence_profile` (
  `competence_profile_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `competence_id` bigint(20) DEFAULT NULL,
  `years_of_experience` decimal(4,2) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`competence_profile_id`),
  KEY `compprof_person_id_idx` (`person_id`),
  KEY `compprof_competence_id_idx` (`competence_id`),
  CONSTRAINT `compprof_competence_id` FOREIGN KEY (`competence_id`) REFERENCES `competence_sv` (`competence_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `compprof_person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_profile`
--

LOCK TABLES `competence_profile` WRITE;
/*!40000 ALTER TABLE `competence_profile` DISABLE KEYS */;
INSERT INTO `competence_profile` VALUES (1,2,1,3.50,1),(2,2,2,2.00,1);
/*!40000 ALTER TABLE `competence_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence_sv`
--

DROP TABLE IF EXISTS `competence_sv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence_sv` (
  `competence_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`competence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_sv`
--

LOCK TABLES `competence_sv` WRITE;
/*!40000 ALTER TABLE `competence_sv` DISABLE KEYS */;
INSERT INTO `competence_sv` VALUES (1,'Korvgrillning',1),(2,'Karuselldrift',1);
/*!40000 ALTER TABLE `competence_sv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `information` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `recruiter_person_id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `recruiter_FN_idx` (`recruiter_person_id`),
  CONSTRAINT `recruiter_FN` FOREIGN KEY (`recruiter_person_id`) REFERENCES `person` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Programmer','Develop an awesome video game','2014-04-23','2014-06-12',1,'Java Programmer',1),(2,'Designer','Design an awesome video game','2014-04-23','2014-06-12',1,'Photoshop Designer',1),(3,'Seller','Sell an awesome video game','2014-04-23','2014-06-12',1,'Market Analysis',1);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_sv`
--

DROP TABLE IF EXISTS `job_sv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_sv` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `information` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `recruiter_person_id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='						';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_sv`
--

LOCK TABLES `job_sv` WRITE;
/*!40000 ALTER TABLE `job_sv` DISABLE KEYS */;
INSERT INTO `job_sv` VALUES (1,'Programmerare','Utveckla ett grymt tvspel','2014-04-24','2014-06-12',1,'Java Programmerare',1),(2,'Designer','Deisgna ett grymt tvspel','2014-04-24','2014-06-12',1,'Photoshop Designare',1),(3,'Säljare','Sälj ett grymt tvspel','2014-04-24','2014-06-12',1,'Marknads Analytiker',1);
/*!40000 ALTER TABLE `job_sv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `ssn` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`person_id`),
  KEY `person_role_id_idx` (`role_id`),
  KEY `username_FN_idx` (`username`),
  CONSTRAINT `role_id_FN` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `username_FN` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Greta','Borg','19871212-1411','borg@kth.se',1,'borg',1),(2,'Per','Strand','19671212-1211','per@strand.kth.se',2,'per',1),(88,'TestRecruiter','Surname','1111','test@recruit.com',666,'username',1);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'recruit',1),(2,'applicant',1),(666,NULL,1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_sv`
--

DROP TABLE IF EXISTS `role_sv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_sv` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='					';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_sv`
--

LOCK TABLES `role_sv` WRITE;
/*!40000 ALTER TABLE `role_sv` DISABLE KEYS */;
INSERT INTO `role_sv` VALUES (1,'rekryterare',1),(2,'applikant',1);
/*!40000 ALTER TABLE `role_sv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `version` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('borg','kIIjHXDQfQS3/t6FpUMwPweWCaQUd1qn9jO7eXHo8uxnGtpAY4EGZQyrDIQG9gIN','',1),('per','pass','',1),('user','123','',1),('username','password','',1);
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

-- Dump completed on 2015-03-04 16:05:37
