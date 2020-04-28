-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: citas
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citas` (
  `citNumero` int(11) NOT NULL AUTO_INCREMENT,
  `citFecha` date NOT NULL,
  `citHora` time NOT NULL,
  `citMedico` varchar(10) NOT NULL,
  `citConsultorio` int(11) NOT NULL,
  `citEstado` varchar(20) DEFAULT NULL,
  `citObservaciones` varchar(200) DEFAULT NULL,
  `paciente` varchar(10) NOT NULL,
  PRIMARY KEY (`citNumero`),
  KEY `citMedico` (`citMedico`),
  KEY `citConsultorio` (`citConsultorio`),
  KEY `paciente` (`paciente`),
  CONSTRAINT `citas_ibfk_1` FOREIGN KEY (`citMedico`) REFERENCES `medicos` (`medIdentificacion`),
  CONSTRAINT `citas_ibfk_2` FOREIGN KEY (`citConsultorio`) REFERENCES `consultorios` (`conNumero`),
  CONSTRAINT `citas_ibfk_3` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`pacIdentificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
INSERT INTO `citas` VALUES (2,'1999-08-05','09:30:00','111',1,'bien','ninguna','10000'),(3,'2020-04-13','05:25:00','1',1,'bien','nada','10000'),(4,'2020-04-13','12:25:00','1',1,'bien','nada','10000');
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-28 17:28:08
