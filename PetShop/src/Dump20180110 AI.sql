CREATE DATABASE  IF NOT EXISTS `pet_chops` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pet_chops`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pet_chops
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Alimentos Balanceados'),(2,'Anti Pulgas'),(3,'Limpieza e Higiene'),(4,'Placas de Identificacion'),(5,'Accesorios');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotas` (
  `idCuota` int(11) NOT NULL AUTO_INCREMENT,
  `idTarjeta` int(11) NOT NULL,
  `cantCuotas` int(11) DEFAULT NULL,
  `recargo` double DEFAULT NULL,
  PRIMARY KEY (`idCuota`),
  KEY `idTarjeta_idx` (`idTarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_venta`
--

DROP TABLE IF EXISTS `linea_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linea_venta` (
  `idLineaVenta` int(11) NOT NULL AUTO_INCREMENT,
  `idVenta` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `idTMascServ` int(11) DEFAULT NULL,
  `tipoLineaVenta` varchar(45) DEFAULT NULL,
  `precioUnitario` double DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLineaVenta`),
  KEY `idVenta_fk_idx` (`idVenta`),
  KEY `idProducto_fk_idx` (`idProducto`),
  KEY `idTMascServ_fk_idx` (`idTMascServ`),
  CONSTRAINT `idProducto_fk` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTMascServ_fk` FOREIGN KEY (`idTMascServ`) REFERENCES `tipo_mascota_servicio` (`idTMascServ`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idVenta_fk` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_venta`
--

LOCK TABLES `linea_venta` WRITE;
/*!40000 ALTER TABLE `linea_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mascota` (
  `idMascota` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `idTipoMascota` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idUsuario_idx` (`idUsuario`),
  KEY `idTipoMascota_idx` (`idTipoMascota`),
  CONSTRAINT `idTipoMascota` FOREIGN KEY (`idTipoMascota`) REFERENCES `tipo_mascota` (`idTipoMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medio_pago`
--

DROP TABLE IF EXISTS `medio_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medio_pago` (
  `idMedioPago` int(11) NOT NULL AUTO_INCREMENT,
  `idTarjeta` int(11) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMedioPago`),
  KEY `idTarjeta_fk_idx` (`idTarjeta`),
  CONSTRAINT `idTarjeta_fk` FOREIGN KEY (`idTarjeta`) REFERENCES `tarjeta` (`idTarjeta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medio_pago`
--

LOCK TABLES `medio_pago` WRITE;
/*!40000 ALTER TABLE `medio_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `medio_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL auto_increment,
  `idSubCategoria` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stockMinimo` int(11) DEFAULT NULL,
  `presentacion` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idSubCategoria_idx` (`idSubCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,1,'shampoo perrin',1,5,'500ml',120,NULL),(2,3,'1.5 metros',2,1,'Cuero',95,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senia`
--

DROP TABLE IF EXISTS `senia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `senia` (
  `idsenia` int(11) NOT NULL AUTO_INCREMENT,
  `idLineaVenta` int(11) DEFAULT NULL,
  `idVenta` int(11) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`idsenia`),
  KEY `idLineaVenta_fk_idx` (`idLineaVenta`),
  KEY `idVenta_fk1_idx` (`idVenta`),
  CONSTRAINT `idLineaVenta_fk` FOREIGN KEY (`idLineaVenta`) REFERENCES `linea_venta` (`idLineaVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idVenta_fk1` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senia`
--

LOCK TABLES `senia` WRITE;
/*!40000 ALTER TABLE `senia` DISABLE KEYS */;
/*!40000 ALTER TABLE `senia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicio` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategoria` (
  `idSubCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSubCategoria`),
  KEY `idCategoria_idx` (`idCategoria`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,3,'Shampoo'),(2,3,'Acondicionador'),(3,5,'Correa Extensible');
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarjeta` (
  `idTarjeta` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTarjeta`),
  UNIQUE KEY `idTarjeta_UNIQUE` (`idTarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_mascota`
--

DROP TABLE IF EXISTS `tipo_mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_mascota` (
  `idTipoMascota` int(11) NOT NULL AUTO_INCREMENT,
  `pelo` varchar(20) DEFAULT NULL,
  `tamanio` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTipoMascota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_mascota`
--

LOCK TABLES `tipo_mascota` WRITE;
/*!40000 ALTER TABLE `tipo_mascota` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_mascota_servicio`
--

DROP TABLE IF EXISTS `tipo_mascota_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_mascota_servicio` (
  `idTMascServ` int(11) NOT NULL AUTO_INCREMENT,
  `idServicio` int(11) DEFAULT NULL,
  `idTipoMascota` int(11) DEFAULT NULL,
  `tiempo` time DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`idTMascServ`),
  KEY `idServicio_fk_idx` (`idServicio`),
  KEY `idTipoMascota_idx` (`idTipoMascota`),
  KEY `idTipoMascota_fk_idx` (`idTipoMascota`),
  CONSTRAINT `idServicio_fk` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTipoMascota_fk` FOREIGN KEY (`idTipoMascota`) REFERENCES `tipo_mascota` (`idTipoMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_mascota_servicio`
--

LOCK TABLES `tipo_mascota_servicio` WRITE;
/*!40000 ALTER TABLE `tipo_mascota_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_mascota_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turno` (
  `idTurno` int(11) NOT NULL AUTO_INCREMENT,
  `idMascota` int(11) NOT NULL,
  `idServicio` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `repetir` varchar(45) DEFAULT NULL,
  `retiroDom` bit(1) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTurno`),
  KEY `idMascota_idx` (`idMascota`),
  KEY `idServicio_idx` (`idServicio`),
  CONSTRAINT `idMascota` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idServicio` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioLogin` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `tipoUsuario` varchar(30) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `telefono` int(20) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `legajo` int(11) DEFAULT NULL,
  `tipoEmpleado` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'1','1','Hugo','Santander',1,'1',1,'1',1,'1',1,'1'),(2,'2','2','Josefina','Ramirez',0,'2',2,'2',2,'2',2,'1');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `idMedioPago` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `datosOpcionales` varchar(45) DEFAULT NULL,
  `envioDom` bit(1) DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `idUsuario_fk_idx` (`idUsuario`),
  KEY `idMedioPago_fk_idx` (`idMedioPago`),
  CONSTRAINT `idMedioPago_fk` FOREIGN KEY (`idMedioPago`) REFERENCES `medio_pago` (`idMedioPago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsuario_fk` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pet_chops'
--

--
-- Dumping routines for database 'pet_chops'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-09 20:54:06
