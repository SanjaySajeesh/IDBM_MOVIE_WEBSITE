-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: imbd
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'USER',
  `email` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `postal_code` int DEFAULT NULL,
  `phone_num` int DEFAULT NULL,
  `organisation` varchar(45) DEFAULT NULL,
  `account_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','ADMIN','admin@imbd.com','NA','NA','admin','admin',NULL,NULL,NULL,'APPROVED'),('caramel6','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','USER','caramelwilson@gmail.com','Australia','Female','Cara','Wilson',NULL,NULL,NULL,'APPROVED'),('critic','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','CRITIC','critic@mail.com','Australia','Female','critic','critic',1234,123456789,'RMIT','APPROVED'),('pco','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','PCO','pco@mail.com','Australia','Female','production','company',1234,123456789,'20th Century Fox','APPROVED');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credits_roll`
--

DROP TABLE IF EXISTS `credits_roll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credits_roll` (
  `person_id` int NOT NULL,
  `role` varchar(45) NOT NULL,
  `show_id` int NOT NULL,
  `start_year` int NOT NULL,
  `end_year` int DEFAULT '0',
  `character_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`show_id`,`person_id`),
  KEY `fk_person_idx` (`person_id`),
  CONSTRAINT `fk_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `fk_show` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credits_roll`
--

LOCK TABLES `credits_roll` WRITE;
/*!40000 ALTER TABLE `credits_roll` DISABLE KEYS */;
INSERT INTO `credits_roll` VALUES (6,'Actor',1,2019,0,'Kylo Ren'),(11,'Actress',1,2019,0,'Maz Kanata'),(13,'Actor',2,1988,0,'Vincent Benedict'),(14,'Actor',2,1988,0,'Julius Benedict'),(1,'Actor',3,2016,0,'Jackie Burke'),(13,'Actor',3,2016,0,'James Berkowitz');
/*!40000 ALTER TABLE `credits_roll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `bio` varchar(1000) NOT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Robert De Niro','Actor','1943-08-17','One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City, to artists Virginia (Admiral) and Robert De Niro Sr. His paternal grandfather was of Italian descent, and his other ancestry is Irish, English, Dutch, German, and French.'),(2,'Robert Pattinson','Actor','1986-05-13','Robert Douglas Thomas Pattinson was born May 13, 1986 in London, England, to Richard Pattinson, a car dealer importing vintage cars, and Clare Pattinson (née Charlton), who worked as a booker at a model agency. He grew up in Barnes, southwest London with two older sisters and attended Tower House'),(3,'Claire Foy','Actress','1984-04-16','Claire Elizabeth Foy (born 16 April 1984) is an English actress. She studied acting at the Liverpool John Moores University and the Oxford School of Drama and made her screen debut in the pilot of the supernatural comedy series Being Human, in 2008.'),(4,'Olivia Colman','Actress','1974-01-30','Olivia Colman was born on January 30, 1974 in Norwich, Norfolk, England as Sarah Caroline Olivia Colman. She is an actress, known for The Favourite (2018), Tyrannosaur (2011) and The Lobster (2015). She has been married to Ed Sinclair since August 2001. They have three children.'),(5,'Troy Baker','Actor','1976-04-01','Troy Baker was born on April 1, 1976 in Dallas, Texas, USA as Troy Edward Baker. He is known for his work on The Last of Us (2013), BioShock Infinite (2013) and Batman: Arkham Knight (2015). He has been married to Pamela Walworth since October 13, 2012. They have one child.'),(6,'Adam Driver','Actor','1983-11-03','Adam Douglas Driver was born in San Diego, California. His mother, Nancy (Needham) Wright, is a paralegal from Mishawaka, Indiana, and his father, Joe Douglas Driver, who has deep roots in the American South, is from Little Rock, Arkansas.'),(7,'Chadwick Boseman','Actor, Producer, Writer','1977-11-29','Chadwick Boseman is an American actor. He is known for his portrayal of T\'Challa / Black Panther in the Marvel Cinematic Universe (since 2016), particularly in Black Panther (2018), and for his starring roles in as Jackie Robinson in 42 (2013), James Brown in Get on Up (2014), and Thurgood Marshall in Marshall (2017). '),(8,'Gal Gadot','Actress, Producer','1985-04-30','Gal Gadot is an Israeli actress, singer, martial artist, and model. She was born in Rosh Ha\'ayin, Israel, to a Jewish family. Her parents are Irit, a teacher, and Michael, an engineer, who is a sixth-generation Israeli. She served in the IDF for two years, and won the Miss Israel title in 2004.'),(9,'Meryl Streep','Actress','1949-06-22','Considered by many critics to be the greatest living actress, Meryl Streep has been nominated for the Academy Award an astonishing 21 times, and has won it three times. Meryl was born Mary Louise Streep in 1949 in Summit, New Jersey, to Mary Wolf (Wilkinson), a commercial artist, and Harry William Streep, Jr., a pharmaceutical executive.'),(10,'Cate Blanchett','Actress','1969-05-14','Cate Blanchett was born on May 14, 1969 in Melbourne, Victoria, Australia, to June (Gamble), an Australian teacher and property developer, and Robert DeWitt Blanchett, Jr., an American advertising executive, originally from Texas.'),(11,'Lupita Nyongo','Actress, Producer','1983-03-01','Lupita Amondi Nyong\'o was born March 1, 1983 in Mexico City, Mexico, to Kenyan parents, Dorothy Ogada Buyu and Peter Anyang\' Nyong\'o. Her father, a senator, was then a visiting lecturer in political science. She was raised in Kenya. At age 16, her parents sent her back to Mexico for seven months to learn Spanish.'),(12,'Zoe Kravitz','Actress, Producer','1988-12-01','Zoe Isabella Kravitz, the daughter of singer/actor Lenny Kravitz and actress Lisa Bonet, was born on December 1, 1988 in Los Angeles, California. She is of half African-American (from her father\'s mother and her mother\'s father) and half Ashkenazi Jewish (from her father\'s father and her mother\'s mother) descent.'),(13,'Danny DeVito','Actor, Producer, Director','1944-11-17','Danny DeVito has amassed a formidable and versatile body of work as an actor, producer and director that spans the stage, television and film. Daniel Michael DeVito Jr. was born on November 17, 1944, in Neptune, New Jersey, to Italian-American parents. His mother, Julia (Moccello), was a homemaker. His father, Daniel, Sr., was a small business owner whose ventures included a dry cleaning shop, a dairy outlet, a diner, and a pool hall. While growing up in Asbury Park, his parents sent him to private schools. He attended Our Lady of Mount Carmel grammar school and Oratory Prep School. Following graduation in 1962, he took a job as a cosmetician at his sister\'s beauty salon. A year later, he enrolled at New York\'s American Academy of Dramatic Arts so he could learn more about cosmetology. While at the academy, he fell in love with acting and decided to further pursue an acting career.'),(14,'Arnold Schwarzenegger','Actor, Producer, Director','1947-07-30','With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood\'s biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and one day be the Governor of California!? The amazing story of megastar Arnold Schwarzenegger is a true \"rags to riches\" tale of a penniless immigrant making it in the land of opportunity, the United States of America. Arnold Alois Schwarzenegger was born July 30, 1947, in the town of Thal, Styria, Austria, to Aurelia Schwarzenegger (born Jadrny) and Gustav Schwarzenegger, the local police chief. From a young age, he took a keen interest in physical fitness and bodybuilding, going on to compete in several minor contests in Europe. However, it was when he emigrated to the United States in 1968 at the tender age of 21 that his star began to rise.');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_company`
--

DROP TABLE IF EXISTS `production_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_company` (
  `proco_id` int NOT NULL AUTO_INCREMENT,
  `proco_name` varchar(45) NOT NULL,
  PRIMARY KEY (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_company`
--

LOCK TABLES `production_company` WRITE;
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` VALUES (1,'Universal Pictures'),(2,'Paramount Pictures'),(3,'20th Century Fox'),(4,'Warner Bros.'),(5,'DreamWorks Pictures'),(6,'Metro-Goldwyn-Meyer'),(7,'Miramax'),(8,'Columbia Pictures'),(9,'Walt Disney Pictures'),(10,'Sony Pictures'),(11,'New Line Cinema'),(12,'Cinelou Films');
/*!40000 ALTER TABLE `production_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show`
--

DROP TABLE IF EXISTS `show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show` (
  `showid` int NOT NULL AUTO_INCREMENT,
  `show_title` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `length` decimal(3,2) NOT NULL,
  `movie` int NOT NULL DEFAULT '1',
  `series` int NOT NULL DEFAULT '0',
  `proco_id` int NOT NULL,
  `year` int NOT NULL DEFAULT '2020',
  `author` varchar(45) DEFAULT NULL,
  `entry_status` varchar(45) NOT NULL DEFAULT 'VISIBLE',
  `entry_status_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`showid`),
  KEY `fk_proco_idx` (`proco_id`),
  CONSTRAINT `fk_proco` FOREIGN KEY (`proco_id`) REFERENCES `production_company` (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show`
--

LOCK TABLES `show` WRITE;
/*!40000 ALTER TABLE `show` DISABLE KEYS */;
INSERT INTO `show` VALUES (1,'Star Wars: Episode IX - The Rise of Skywalker','Action',2.22,1,0,9,2019,'caramel6','VISIBLE','2020-05-03 09:02:22'),(2,'Twins','Comedy',1.78,1,0,1,1988,'caramel6','VISIBLE','2020-05-20 01:31:49'),(3,'The Comedian','Comedy',2.00,1,0,12,2016,'caramel6','VISIBLE','2020-05-03 13:01:07');
/*!40000 ALTER TABLE `show` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_review` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `rating` int DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `fk_showid_idx` (`show_id`),
  KEY `fk_username_idx` (`user_id`),
  CONSTRAINT `fk_showid` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`) ON DELETE CASCADE,
  CONSTRAINT `fk_username` FOREIGN KEY (`user_id`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` VALUES (1,1,'caramel6',4,'great','2020-05-20 00:00:00'),(4,1,'pco',5,'great','2020-05-20 00:00:00');
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20 14:29:18
