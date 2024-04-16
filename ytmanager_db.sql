-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ytmanager
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'To Watch'),(6,'Self Development'),(7,'Travel');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timestamps`
--

DROP TABLE IF EXISTS `timestamps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timestamps` (
  `video_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `timestamp_index` int(11) NOT NULL,
  PRIMARY KEY (`video_id`,`timestamp_index`),
  CONSTRAINT `FKisgihvshdobmt6x2a93e4jooc` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timestamps`
--

LOCK TABLES `timestamps` WRITE;
/*!40000 ALTER TABLE `timestamps` DISABLE KEYS */;
/*!40000 ALTER TABLE `timestamps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookmark` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `note` tinytext DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `watched` bit(1) NOT NULL,
  `yt_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (14,'303.466726','PT16M45S','https://www.youtube.com/watch?v=sCQ0VYNCmKw',NULL,'https://i.ytimg.com/vi/sCQ0VYNCmKw/default.jpg','How to Be Consistent: A Simple Secret to Personal Development',_binary '\0','sCQ0VYNCmKw'),(15,NULL,'PT1H20M52S','https://www.youtube.com/watch?v=ufQEqi4LUZ4',NULL,'https://i.ytimg.com/vi/ufQEqi4LUZ4/default.jpg','Mindset Reset: Take Control of Your Mental Habits | The Mel Robbins Podcast',_binary '','ufQEqi4LUZ4'),(16,NULL,'PT1H35M39S','https://www.youtube.com/watch?v=Q-zuTZuYeCg',NULL,'https://i.ytimg.com/vi/Q-zuTZuYeCg/default.jpg','Simon Sinek: The Number One Reason Why You’re Not Succeeding | E145',_binary '\0','Q-zuTZuYeCg'),(17,'2596.34421','PT1H4M11S','https://www.youtube.com/watch?v=3uLDin9A9pc',NULL,'https://i.ytimg.com/vi/3uLDin9A9pc/default.jpg','Jordan Peterson: How To Become The Person You’ve Always Wanted To Be | E113',_binary '\0','3uLDin9A9pc'),(18,NULL,'PT14M22S','https://www.youtube.com/watch?v=kidwSFte8-E',NULL,'https://i.ytimg.com/vi/kidwSFte8-E/default.jpg','Life Lessons from the Youngest Person to Travel to Every Country | Lexie Alford | TEDxKlagenfurt',_binary '','kidwSFte8-E'),(19,'735.53196','PT21M25S','https://www.youtube.com/watch?v=kYXiegTXsEs',NULL,'https://i.ytimg.com/vi/kYXiegTXsEs/default.jpg','The value of travel | Rick Steves | TEDxRainier',_binary '\0','kYXiegTXsEs');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_playlist`
--

DROP TABLE IF EXISTS `video_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_playlist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_index` int(11) NOT NULL,
  `playlist_id` bigint(20) DEFAULT NULL,
  `yt_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_playlist`
--

LOCK TABLES `video_playlist` WRITE;
/*!40000 ALTER TABLE `video_playlist` DISABLE KEYS */;
INSERT INTO `video_playlist` VALUES (21,1,6,'sCQ0VYNCmKw'),(22,1,1,'sCQ0VYNCmKw'),(23,2,6,'ufQEqi4LUZ4'),(24,2,1,'ufQEqi4LUZ4'),(25,3,6,'Q-zuTZuYeCg'),(26,3,1,'Q-zuTZuYeCg'),(27,4,6,'3uLDin9A9pc'),(28,4,1,'3uLDin9A9pc'),(29,5,1,'kidwSFte8-E'),(30,1,7,'kidwSFte8-E'),(32,2,7,'kYXiegTXsEs'),(33,2,1,'kYXiegTXsEs');
/*!40000 ALTER TABLE `video_playlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 19:16:38
