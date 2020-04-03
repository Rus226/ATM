-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accountnumberbank`
--

DROP TABLE IF EXISTS `accountnumberbank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountnumberbank` (
  `IdAN` int NOT NULL AUTO_INCREMENT,
  `AccountNumber` varchar(20) NOT NULL,
  `ClientId` int DEFAULT NULL,
  `CompanyId` int DEFAULT NULL,
  PRIMARY KEY (`IdAN`),
  UNIQUE KEY `AccountNumber` (`AccountNumber`),
  UNIQUE KEY `ClientId` (`ClientId`),
  UNIQUE KEY `CompanyId` (`CompanyId`),
  CONSTRAINT `accountnumberbank_ibfk_1` FOREIGN KEY (`ClientId`) REFERENCES `client` (`IdClient`) ON DELETE CASCADE,
  CONSTRAINT `accountnumberbank_ibfk_2` FOREIGN KEY (`CompanyId`) REFERENCES `company` (`IdCompany`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountnumberbank`
--

LOCK TABLES `accountnumberbank` WRITE;
/*!40000 ALTER TABLE `accountnumberbank` DISABLE KEYS */;
INSERT INTO `accountnumberbank` VALUES (1,'12461236842236548153',1,NULL),(2,'51879461531534635154',2,NULL),(3,'13548154643515315448',3,NULL),(4,'15489153446311684561',4,NULL),(5,'39485723957393458934',NULL,1),(6,'98123472394120935025',NULL,2),(7,'81273560213458235232',NULL,3);
/*!40000 ALTER TABLE `accountnumberbank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atm`
--

DROP TABLE IF EXISTS `atm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atm` (
  `IdATM` int NOT NULL AUTO_INCREMENT,
  `ATMnumber` varchar(12) NOT NULL,
  `Count100` int NOT NULL DEFAULT '0',
  `Count500` int NOT NULL DEFAULT '0',
  `Count1000` int NOT NULL DEFAULT '0',
  `Count2000` int NOT NULL DEFAULT '0',
  `Count5000` int NOT NULL DEFAULT '0',
  `BankNameId` int DEFAULT NULL,
  PRIMARY KEY (`IdATM`),
  UNIQUE KEY `ATMnumber` (`ATMnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atm`
--

LOCK TABLES `atm` WRITE;
/*!40000 ALTER TABLE `atm` DISABLE KEYS */;
INSERT INTO `atm` VALUES (1,'782783872784',24,37,40,36,50,1),(2,'568567521363',25,45,12,24,12,2),(3,'210237862778',28,21,45,35,26,3),(4,'542367782215',11,10,11,11,11,2),(5,'453398758675',7,27,62,44,75,3);
/*!40000 ALTER TABLE `atm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `IdBank` int NOT NULL AUTO_INCREMENT,
  `BankName` varchar(20) NOT NULL,
  `AccountNumber` varchar(20) DEFAULT NULL,
  `Balance` double(15,2) DEFAULT '0.00',
  PRIMARY KEY (`IdBank`),
  UNIQUE KEY `BankName` (`BankName`),
  UNIQUE KEY `AccountNumber` (`AccountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'Sberbank','57623453756805645334',51321432.74),(2,'Alfa','68468764654568494684',1580.30),(3,'VTB','58484651538463153546',46135132.64);
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `IdCard` int NOT NULL AUTO_INCREMENT,
  `CardNumber` varchar(16) NOT NULL,
  `CardBalance` decimal(9,2) NOT NULL DEFAULT '0.00',
  `Available` tinyint(1) NOT NULL DEFAULT '1',
  `Pincode` varchar(16) NOT NULL DEFAULT '0',
  `ClientId` int NOT NULL,
  PRIMARY KEY (`IdCard`),
  UNIQUE KEY `CardNumber` (`CardNumber`),
  KEY `ClientId` (`ClientId`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`ClientId`) REFERENCES `client` (`IdClient`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'1682687298756542',34525.34,1,'35163514',1),(2,'7682879272176286',15345.13,1,'04543005',2),(3,'9872421765619852',12551.35,0,'04543005',3),(4,'6479427873264717',7855.54,1,'15223741',4),(5,'6927835768527496',23415.44,0,'04543005',2),(6,'3574298727312687',23412.41,0,'04543005',2),(7,'6782598712318758',53453.25,1,'04543005',3),(8,'1678621676173364',5552.52,0,'04543005',1);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `IdClient` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `BankNameId` int NOT NULL,
  PRIMARY KEY (`IdClient`),
  KEY `BankNameId` (`BankNameId`),
  CONSTRAINT `client_ibfk_2` FOREIGN KEY (`BankNameId`) REFERENCES `bank` (`IdBank`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Ivan','Sidorov',1),(2,'Sergei','Petrenko',2),(3,'Pavel','Gusev',3),(4,'Nikolay','Radnoy',1);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `IdCompany` int NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(20) NOT NULL,
  `Balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `BankNameId` int NOT NULL,
  PRIMARY KEY (`IdCompany`),
  KEY `BankNameId` (`BankNameId`),
  CONSTRAINT `company_ibfk_2` FOREIGN KEY (`BankNameId`) REFERENCES `bank` (`IdBank`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Megafon',43182.56,1),(2,'MTS',36785.15,2),(3,'Rostelekom',67318.50,3);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-03 13:22:04
