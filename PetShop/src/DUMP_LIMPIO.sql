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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (1,3,3,0),(2,3,6,0),(3,3,12,0.25),(4,4,6,0.13),(5,4,12,0.31),(6,4,24,0.45);
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarios` (
  `horario` time(2) NOT NULL,
  PRIMARY KEY (`horario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES ('08:00:00.00'),('08:30:00.00'),('09:00:00.00'),('09:30:00.00'),('10:00:00.00'),('10:30:00.00'),('11:00:00.00'),('11:30:00.00'),('12:00:00.00'),('12:30:00.00'),('13:00:00.00'),('13:30:00.00'),('14:00:00.00'),('14:30:00.00'),('15:00:00.00'),('15:30:00.00'),('16:00:00.00'),('16:30:00.00'),('17:00:00.00'),('17:30:00.00'),('18:00:00.00'),('18:30:00.00'),('19:00:00.00'),('19:30:00.00'),('20:00:00.00'),('20:30:00.00');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  `estado` int(1) DEFAULT NULL,
  PRIMARY KEY (`idMascota`),
  KEY `idUsuario_idx` (`idUsuario`),
  KEY `idTipoMascota_idx` (`idTipoMascota`),
  CONSTRAINT `idTipoMascota` FOREIGN KEY (`idTipoMascota`) REFERENCES `tipo_mascota` (`idTipoMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medio_pago`
--

DROP TABLE IF EXISTS `medio_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medio_pago` (
  `idMedioPago` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMedioPago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medio_pago`
--

LOCK TABLES `medio_pago` WRITE;
/*!40000 ALTER TABLE `medio_pago` DISABLE KEYS */;
INSERT INTO `medio_pago` VALUES (1,'Efectivo'),(2,'Debito'),(3,'Credito');
/*!40000 ALTER TABLE `medio_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `idSubCategoria` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stockMinimo` int(11) DEFAULT NULL,
  `presentacion` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `imagen` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idSubCategoria_idx` (`idSubCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,4,'Sieger Criadores',14,5,'3 Kg',435,'imgs/productos/SiegerCriadores3Kg.jpg'),(2,10,'Sieger Cachorros',10,5,'5 Kg',1199.99,'imgs/productos/SiegerCachorros15Kg.jpg'),(3,5,'Power',3,5,'12 ml',65.49,'imgs/productos/PipetaPower.jpg'),(4,5,'Power',0,5,'20 ml',65.49,'imgs/productos/PipetaPower.jpg'),(5,5,'Power Plus',2,5,'23 ml',90,'imgs/productos/PipetaPower.jpg'),(6,1,'Perrin',6,5,'2 Litros',210,'imgs/productos/ShampooPerrin.jpg'),(7,1,'Perrin',15,5,'1 Litro',130,'imgs/productos/ShampooPerrin.jpg'),(8,2,'Perrin Soft',12,6,'1.5 Litros',237.99,'imgs/productos/ShampooPerrin.jpg'),(9,3,'Correa Cuero',2,3,'3 Metros',349,'imgs/productos/CorreaExtensibleNegra.jpg'),(10,7,'Colchon Animal Print',8,3,'0.6 x 0.6 Metros',420,'imgs/productos/ColchonAnimalPrint.jpg'),(11,7,'Colchon Animal Print',6,3,'0.6 x 0.9 Metros',470,'imgs/productos/ColchonAnimalPrint.jpg'),(12,8,'Cuero Masticable',10,3,'Pequeño',32,'imgs/productos/CueroMasticable.jpg'),(13,8,'Cuero Masticable',11,3,'Mediano',47,'imgs/productos/CueroMasticable.jpg'),(14,8,'Cuero Masticable',10,3,'Grande',60,'imgs/productos/CueroMasticable.jpg'),(15,4,'Sieger Adultos',10,5,'15 Kg',985,'imgs/productos/SiegerAdultos15Kg.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Ba&ntilde;o'),(2,'Corte'),(3,'Ba&ntilde;o con Corte'),(4,'Antipulgas');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,3,'Shampoo'),(2,3,'Acondicionador'),(3,5,'Correa Extensible'),(4,1,'Perro Adulto'),(5,2,'Pipeta'),(6,2,'Pastilla'),(7,5,'Colchon'),(8,5,'Juguete'),(9,5,'Abrigo'),(10,1,'Cachorro');
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
  `idMedioPago` int(11) NOT NULL,
  PRIMARY KEY (`idTarjeta`),
  KEY `idMedioPago_idx` (`idMedioPago`),
  CONSTRAINT `idMedioPago_idx` FOREIGN KEY (`idMedioPago`) REFERENCES `medio_pago` (`idMedioPago`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
INSERT INTO `tarjeta` VALUES (1,'Maestro / LINK',2),(2,'VISA Electron / Banelco',2),(3,'Nativa Mastercard',3),(4,'Naranja',3),(5,'CMR',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_mascota`
--

LOCK TABLES `tipo_mascota` WRITE;
/*!40000 ALTER TABLE `tipo_mascota` DISABLE KEYS */;
INSERT INTO `tipo_mascota` VALUES (1,'Corto','Chico'),(2,'Corto','Mediano'),(3,'Corto','Grande'),(4,'Largo','Chico'),(5,'Largo','Mediano'),(6,'Largo','Grande');
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_mascota_servicio`
--

LOCK TABLES `tipo_mascota_servicio` WRITE;
/*!40000 ALTER TABLE `tipo_mascota_servicio` DISABLE KEYS */;
INSERT INTO `tipo_mascota_servicio` VALUES (7,1,1,'00:30:00',150),(8,1,2,'00:30:00',150),(9,1,3,'01:00:00',300),(10,1,4,'00:30:00',150),(11,1,5,'01:00:00',300),(12,1,6,'01:30:00',450),(13,2,1,'00:30:00',150),(14,2,2,'01:00:00',150),(15,2,3,'01:30:00',300),(16,2,4,'01:00:00',150),(17,2,5,'01:00:00',300),(18,2,6,'01:30:00',450),(19,3,1,'01:00:00',300),(20,3,2,'01:30:00',450),(21,3,3,'01:30:00',450),(22,3,4,'01:30:00',450),(23,3,5,'02:00:00',600),(24,3,6,'02:00:00',600),(33,4,1,'00:30:00',150),(34,4,2,'00:30:00',150),(35,4,3,'01:00:00',300),(36,4,4,'00:30:00',150),(37,4,5,'01:00:00',300),(38,4,6,'01:00:00',450);
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
  `observaciones` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idTurno`),
  KEY `idMascota_idx` (`idMascota`),
  KEY `idServicio_idx` (`idServicio`),
  CONSTRAINT `idMascota` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idServicio` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioLogin` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `tipoUsuario` varchar(30) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `legajo` int(11) DEFAULT NULL,
  `tipoEmpleado` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `usuarioLogin_UNIQUE` (`usuarioLogin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'1','1','Hugo','Santander',1,'Administrador',1,'Cerrito 1487','15123456','caramanola@wert.pre',0,NULL),(2,'2','2','Josefina','Ramirez',1,'Online',31874556,'Mendoza 1233','456456',NULL,0,NULL);
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
  `idUsuario` int(11) DEFAULT NULL,
  `idMedioPago` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `datosOpcionales` varchar(45) DEFAULT NULL,
  `envioDom` bit(1) DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `idTarjeta` int(11) DEFAULT NULL,
  `idCuotas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `idUsuario_fk_idx` (`idUsuario`),
  KEY `idMedioPago_fk_idx` (`idMedioPago`),
  KEY `idTarjeta_fk_idx` (`idTarjeta`),
  KEY `idCuotas_fk_idx` (`idCuotas`),
  CONSTRAINT `idCuota_fk` FOREIGN KEY (`idCuotas`) REFERENCES `cuotas` (`idCuota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idMedioPago_fk` FOREIGN KEY (`idMedioPago`) REFERENCES `medio_pago` (`idMedioPago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTarjeta_fk` FOREIGN KEY (`idTarjeta`) REFERENCES `tarjeta` (`idTarjeta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsuario_fk` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'pet_chops'
--

--
-- Dumping routines for database 'pet_chops'
--
/*!50003 DROP PROCEDURE IF EXISTS `getHorariosDisponibles` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getHorariosDisponibles`(diaSeleccionado Date)
begin
    
	drop temporary table if exists horariosDisponibles;
	drop temporary table if exists turnosDia;

	/*Creo una tabla con todos los horarios*/
	create temporary table horariosDisponibles (primary key (horario)) as 
										SELECT 
										* 
										FROM horarios;
										
	/*Creo una tabla con los horarios del dia y su duracion*/                                    
	create temporary table turnosDia as 
										(SELECT 
											hora,addtime(hora,tiempo) duracion
										FROM
											turno t
												inner JOIN
											mascota m ON t.idMascota = m.idMascota
												inner JOIN
											tipo_mascota tm ON m.idTipoMascota = tm.idTipoMascota
											inner JOIN
											tipo_mascota_servicio tms ON tms.idTipoMascota = tm.idTipoMascota and tms.idServicio = t.idServicio
                                            where fecha = diaSeleccionado);    
    begin
		declare horaDesde Time default null;
		declare horaHasta Time default null;
    
		declare listo Tinyint default false;
		
        declare cursor1
			cursor for select * from turnosDia;
		
		declare continue handler for not found set listo = true;
		
		open cursor1;
		
		my_loop:
		LOOP
			fetch next from cursor1 into horaDesde, horaHasta;
			if listo then
				leave my_loop;
			else
				delete from horariosDisponibles where horario >= horaDesde and horario < horaHasta;
			end if;
		end Loop;
    end;
    
select * from horariosDisponibles;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-21 18:10:20
