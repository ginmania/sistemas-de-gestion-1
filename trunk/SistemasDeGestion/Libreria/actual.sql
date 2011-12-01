-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sg1
--

CREATE DATABASE IF NOT EXISTS sg1;
USE sg1;

--
-- Definition of table `sg1`.`Catalogo`
--

DROP TABLE IF EXISTS `sg1`.`Catalogo`;
CREATE TABLE  `sg1`.`Catalogo` (
  `OIDCatalogo` varchar(50) NOT NULL,
  `OIDProveedor` varchar(50) DEFAULT NULL,
  `OIDProducto` varchar(50) DEFAULT NULL,
  `Fecha` varchar(50) DEFAULT NULL,
  `Demora` int(11) DEFAULT NULL,
  `PrecioCompra` float DEFAULT NULL,
  `baja` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`OIDCatalogo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Catalogo`
--

/*!40000 ALTER TABLE `Catalogo` DISABLE KEYS */;
LOCK TABLES `Catalogo` WRITE;
INSERT INTO `sg1`.`Catalogo` VALUES  ('1','3','42','2011-01-01',3,1.25,0),
 ('2','2','43','2011-01-01',3,0.25,0),
 ('4','3','43','2011-10-01',3,0.2,0),
 ('5','1','44','2011-12-01',3,1.15,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Catalogo` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Cliente`
--

DROP TABLE IF EXISTS `sg1`.`Cliente`;
CREATE TABLE  `sg1`.`Cliente` (
  `OIDCliente` varchar(50) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `CUIT` varchar(15) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Telefono_Fijo` varchar(15) NOT NULL,
  `Celular` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `baja` tinyint(1) NOT NULL,
  PRIMARY KEY (`OIDCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de Clientes';

--
-- Dumping data for table `sg1`.`Cliente`
--

/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
LOCK TABLES `Cliente` WRITE;
INSERT INTO `sg1`.`Cliente` VALUES  ('0593f4f7-027c-4fd6-ad29-933a1e89b1dd','','','','','','','','',1),
 ('0dcb5a18-af42-4a20-8fe8-d30bfd8519e8','','','','','','','','',1),
 ('1','Diego','Gomez','29222236','20-29222236-0','Manuel A Saez 356','1111','1111','dggomez@mendoza.gov.ar',0),
 ('2','Natalia','Cortese','21','21','Chivilcoy ','Chivilcoy ','1000','taku246@hotmail.com',0),
 ('3','Leonardo','Gomez','30000000','30000000','San Juan 521','San Juan 521','14520','leogomez@hotmail.com',0),
 ('4','Rafael','Gomez','8154256','300000','San Luis 500','13214354','12123','pepe',0),
 ('5','Laura','Marzetti','3200000','320000','Chivilcoy 200','123456','321654','laura@hotmail.com',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Cliente_Proveedor`
--

DROP TABLE IF EXISTS `sg1`.`Cliente_Proveedor`;
CREATE TABLE  `sg1`.`Cliente_Proveedor` (
  `OIDClienteProveedor` varchar(50) NOT NULL,
  `OIDProveedor` varchar(50) NOT NULL,
  `OIDCliente` varchar(50) NOT NULL,
  `baja` tinyint(1) NOT NULL,
  PRIMARY KEY (`OIDClienteProveedor`),
  KEY `OIDProveedor` (`OIDProveedor`),
  KEY `OIDCliente` (`OIDCliente`),
  CONSTRAINT `OIDCli` FOREIGN KEY (`OIDCliente`) REFERENCES `Cliente` (`OIDCliente`),
  CONSTRAINT `OIDProv` FOREIGN KEY (`OIDProveedor`) REFERENCES `Proveedor` (`OIDProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Cliente_Proveedor`
--

/*!40000 ALTER TABLE `Cliente_Proveedor` DISABLE KEYS */;
LOCK TABLES `Cliente_Proveedor` WRITE;
INSERT INTO `sg1`.`Cliente_Proveedor` VALUES  ('1','1','1',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Cliente_Proveedor` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Demanda`
--

DROP TABLE IF EXISTS `sg1`.`Demanda`;
CREATE TABLE  `sg1`.`Demanda` (
  `OIDDemanda` varchar(50) NOT NULL,
  `OIDProducto` varchar(50) NOT NULL,
  `fechainicio` varchar(50) NOT NULL,
  `fechafin` varchar(50) NOT NULL,
  `demandareal` float NOT NULL,
  `demandaactualizada` float NOT NULL,
  `demandapronosticada` float NOT NULL,
  `factorestacional` float NOT NULL,
  `gananciaperiodo` float NOT NULL,
  `anio` varchar(50) NOT NULL,
  `periodo` int(10) NOT NULL,
  `tendencia` float NOT NULL,
  `errorpromedio` float NOT NULL,
  `mse` float NOT NULL,
  `senalrastreo` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`OIDDemanda`),
  KEY `OIDProducto` (`OIDProducto`),
  CONSTRAINT `OIDProdu4` FOREIGN KEY (`OIDProducto`) REFERENCES `Producto` (`OIDProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla de Demanda';

--
-- Dumping data for table `sg1`.`Demanda`
--

/*!40000 ALTER TABLE `Demanda` DISABLE KEYS */;
LOCK TABLES `Demanda` WRITE;
INSERT INTO `sg1`.`Demanda` VALUES  ('1','42','01012010','28012010',5000,4523,0,0,0,'2010',1,0,0,0,0,0),
 ('10','42','11092010','08102010',17250,13687,0,0,0,'2010',10,0,0,0,0,0),
 ('11','42','09102010','05112010',17250,15896,0,0,0,'2010',11,0,0,0,0,0),
 ('12','42','06112010','03112010',17250,17958,0,0,0,'2010',12,0,0,0,0,0),
 ('13','42','04122010','31122010',18000,21476,0,0,0,'2010',13,0,0,0,0,0),
 ('14','43','01012010','28012010',2000,3547,35,0,0,'2010',1,0,0,0,0,0),
 ('15','43','29012010','25022010',1375,1027,35,0,0,'2010',2,0,0,0,0,0),
 ('16','43','26022010','25032010',1250,954,35,0,0,'2010',3,0,0,0,0,0),
 ('17','43','26032010','23042010',2375,2147,35,0,0,'2010',4,0,0,0,0,0),
 ('18','43','24042010','21052010',1625,1323,35,0,0,'2010',5,0,0,0,0,0),
 ('19','43','22052010','18062010',1625,1478,35,0,0,'2010',6,0,0,0,0,0),
 ('2','42','29012010','25022010',4750,4588,0,0,0,'2010',2,0,0,0,0,0),
 ('20','43','19062010','16072010',2500,1965,35,0,0,'2010',7,0,0,0,0,0),
 ('21','43','17072010','13082010',1375,1687,35,0,0,'2010',8,0,0,0,0,0),
 ('22','43','14082010','10092010',8800,5364,35,0,0,'2010',9,0,0,0,0,0),
 ('23','43','11092010','08102010',9000,9704,35,0,0,'2010',10,0,0,0,0,0),
 ('24','43','09102010','05112010',10000,16581,35,0,0,'2010',11,0,0,0,0,0),
 ('25','43','06112010','03112010',8875,9015,35,0,0,'2010',12,0,0,0,0,0),
 ('26','43','04122010','31122010',7625,8023,35,0,0,'2010',13,0,0,0,0,0),
 ('27','44','01012010','28012010',13000,10900,10,0,0,'2010',1,0,0,0,0,0),
 ('28','44','29012010','25022010',18000,12300,10,0,0,'2010',2,0,0,0,0,0),
 ('29','44','26022010','25032010',13000,14866,10,0,0,'2010',3,0,0,0,0,0),
 ('3','42','26022010','25032010',3000,3210,0,0,0,'2010',3,0,0,0,0,0),
 ('30','44','26032010','23042010',17000,19841,10,0,0,'2010',4,0,0,0,0,0),
 ('31','44','24042010','21052010',14000,16852,10,0,0,'2010',5,0,0,0,0,0),
 ('32','44','22052010','18062010',12000,10236,10,0,0,'2010',6,0,0,0,0,0),
 ('33','44','19062010','16072010',16000,17488,10,0,0,'2010',7,0,0,0,0,0),
 ('34','44','17072010','13082010',17000,12477,10,0,0,'2010',8,0,0,0,0,0),
 ('35','44','14082010','10092010',72000,64258,10,0,0,'2010',9,0,0,0,0,0),
 ('36','44','11092010','08102010',60000,83698,10,0,0,'2010',10,0,0,0,0,0),
 ('37','44','09102010','05112010',74000,76855,10,0,0,'2010',11,0,0,0,0,0),
 ('38','44','06112010','03112010',65000,62100,10,0,0,'2010',12,0,0,0,0,0),
 ('39','44','04122010','31122010',20000,33147,10,0,0,'2010',13,0,0,0,0,0),
 ('4','42','26032010','23042010',3750,3756,0,0,0,'2010',4,0,0,0,0,0),
 ('5','42','24042010','21052010',4750,4258,0,0,0,'2010',5,0,0,0,0,0),
 ('6','42','22052010','18062010',3000,2971,0,0,0,'2010',6,0,0,0,0,0),
 ('7','42','19062010','16072010',3500,4025,0,0,0,'2010',7,0,0,0,0,0),
 ('8','42','17072010','13082010',4500,4269,0,0,0,'2010',8,0,0,0,0,0),
 ('9','42','14082010','10092010',4750,4555,0,0,0,'2010',9,0,0,0,0,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Demanda` ENABLE KEYS */;


--
-- Definition of table `sg1`.`DetallePedido`
--

DROP TABLE IF EXISTS `sg1`.`DetallePedido`;
CREATE TABLE  `sg1`.`DetallePedido` (
  `OIDDetallePedido` varchar(50) NOT NULL,
  `OIDProducto` varchar(50) NOT NULL,
  `OIDPedido` varchar(50) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `baja` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`OIDDetallePedido`),
  KEY `OIDProducto` (`OIDProducto`),
  KEY `OIDPedido` (`OIDPedido`),
  CONSTRAINT `OIDPed2` FOREIGN KEY (`OIDPedido`) REFERENCES `Pedido` (`OIDPedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OIDProdu1` FOREIGN KEY (`OIDProducto`) REFERENCES `Producto` (`OIDProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de DetallePedido';

--
-- Dumping data for table `sg1`.`DetallePedido`
--

/*!40000 ALTER TABLE `DetallePedido` DISABLE KEYS */;
LOCK TABLES `DetallePedido` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `DetallePedido` ENABLE KEYS */;


--
-- Definition of table `sg1`.`DetalleVenta`
--

DROP TABLE IF EXISTS `sg1`.`DetalleVenta`;
CREATE TABLE  `sg1`.`DetalleVenta` (
  `OIDDetalleVenta` varchar(50) NOT NULL,
  `OIDProducto` varchar(50) NOT NULL,
  `OIDVenta` varchar(50) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `Precio` double DEFAULT NULL,
  `PrecioUnitario` double DEFAULT NULL,
  PRIMARY KEY (`OIDDetalleVenta`),
  KEY `OIDProducto` (`OIDProducto`),
  KEY `OIDVenta` (`OIDVenta`),
  CONSTRAINT `OIDProdu3` FOREIGN KEY (`OIDProducto`) REFERENCES `Producto` (`OIDProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OIDVenta1` FOREIGN KEY (`OIDVenta`) REFERENCES `Venta` (`OIDVenta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de Detalle dce Ventas';

--
-- Dumping data for table `sg1`.`DetalleVenta`
--

/*!40000 ALTER TABLE `DetalleVenta` DISABLE KEYS */;
LOCK TABLES `DetalleVenta` WRITE;
INSERT INTO `sg1`.`DetalleVenta` VALUES  ('15965b4a-ac8e-4fa1-a1cc-a95caa706ece','44','5234109b-e9e9-491e-a9f2-b9b32c048d93',1,0.25,0.25),
 ('35010d17-8e73-4f1f-9dec-4d9805bc8255','44','bf6f7200-bc0e-4f95-b94e-2fe883990aa5',0,0,0.25),
 ('522f75c9-99b6-4a50-bb09-d4d02fe771fe','44','61ee2ceb-1cc0-4c58-a708-3c5aca5bf676',1,0.25,0.25),
 ('58caa24b-3af6-4749-aa8f-6ccf97a9776f','43','1a816101-a6c8-4317-8f93-db1339f7a4be',3,1.5,0.5),
 ('5e3a73f0-66a6-470c-9edb-f5faa70a65d1','43','1a816101-a6c8-4317-8f93-db1339f7a4be',300,150,0.5),
 ('84d5d8aa-a010-45bd-b4d7-552b8f9cf237','43','1ff62433-813e-4303-b780-562303dc5c8d',2,1,0.5),
 ('95218bb4-53a8-4025-9bd7-701bbdea0eeb','43','1ff62433-813e-4303-b780-562303dc5c8d',2,1,0.5),
 ('993dbcc5-b8ea-47eb-925c-ab42c699c33b','44','bcd21dfa-1562-4530-9b8c-e573faed5152',1,0.25,0.25),
 ('b987555a-5a6d-4825-8546-82caa935e902','43','4a73053b-f6fd-4161-9532-ad3bd24a1da6',3,1.5,0.5),
 ('bf69e061-a65d-4256-aea1-d0a49b3319fc','44','473c6a20-64a0-4be4-a564-a0c4cad69ec6',1,0.25,0.25),
 ('c4bc209d-7d20-439e-917b-5ae184dec42e','44','6062033c-03c3-467f-90ae-b4a280f9619e',1,0.25,0.25),
 ('e62cde20-0d50-48cb-9ae3-d128c7a21c54','44','6db72852-2147-4a2b-97eb-ab8c5e9ad016',0,0,0.25),
 ('faf2ce5e-94d4-4707-87aa-b1aa2c180bf8','44','e893842b-356d-4897-8ad1-e6a6855182ae',1,0.25,0.25);
UNLOCK TABLES;
/*!40000 ALTER TABLE `DetalleVenta` ENABLE KEYS */;


--
-- Definition of table `sg1`.`NivelServicio`
--

DROP TABLE IF EXISTS `sg1`.`NivelServicio`;
CREATE TABLE  `sg1`.`NivelServicio` (
  `items` varchar(15) DEFAULT NULL,
  `agotamiento` varchar(25) DEFAULT NULL,
  `agot_anual` float DEFAULT NULL,
  `nivel` varchar(25) DEFAULT NULL,
  `OIDNivel` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`NivelServicio`
--

/*!40000 ALTER TABLE `NivelServicio` DISABLE KEYS */;
LOCK TABLES `NivelServicio` WRITE;
INSERT INTO `sg1`.`NivelServicio` VALUES  ('Critico','1 cada 5',0.2,'1 - 0.2 / N','1'),
 ('Importante','1 cada 3',0.33,'1 - 0.33 / N','2'),
 ('Normal','1 cada 1',1,'1 - 1 / N','3'),
 ('Reemplazable','1 cada 0.5',2,'1 - 2 / N','4');
UNLOCK TABLES;
/*!40000 ALTER TABLE `NivelServicio` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Parametros`
--

DROP TABLE IF EXISTS `sg1`.`Parametros`;
CREATE TABLE  `sg1`.`Parametros` (
  `idParametros` varchar(50) NOT NULL,
  `Alfa` float NOT NULL,
  `Beta` float NOT NULL,
  `Gama` float NOT NULL,
  PRIMARY KEY (`idParametros`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Parametros`
--

/*!40000 ALTER TABLE `Parametros` DISABLE KEYS */;
LOCK TABLES `Parametros` WRITE;
INSERT INTO `sg1`.`Parametros` VALUES  ('1',0.1,0.05,0.2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Parametros` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Pedido`
--

DROP TABLE IF EXISTS `sg1`.`Pedido`;
CREATE TABLE  `sg1`.`Pedido` (
  `OIDPedido` varchar(50) NOT NULL,
  `OIDProveedor` varchar(50) NOT NULL,
  `fechaemision` date DEFAULT NULL,
  `fechaentrega` date DEFAULT NULL,
  `NroPedido` int(11) NOT NULL AUTO_INCREMENT,
  `pend` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`OIDPedido`),
  UNIQUE KEY `NroPedido` (`NroPedido`),
  KEY `OIDProveedor` (`OIDProveedor`),
  CONSTRAINT `OIDProve1` FOREIGN KEY (`OIDProveedor`) REFERENCES `Proveedor` (`OIDProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Pedido`
--

/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
LOCK TABLES `Pedido` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Pedido` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Politica`
--

DROP TABLE IF EXISTS `sg1`.`Politica`;
CREATE TABLE  `sg1`.`Politica` (
  `OIDPolitica` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`OIDPolitica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Politica`
--

/*!40000 ALTER TABLE `Politica` DISABLE KEYS */;
LOCK TABLES `Politica` WRITE;
INSERT INTO `sg1`.`Politica` VALUES  ('1','Politica S,R'),
 ('2','Politica S,Q');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Politica` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Producto`
--

DROP TABLE IF EXISTS `sg1`.`Producto`;
CREATE TABLE  `sg1`.`Producto` (
  `OIDProducto` varchar(50) NOT NULL,
  `codigo` int(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `preciocompra` float DEFAULT NULL,
  `precioventa` float DEFAULT NULL,
  `OIDStock` varchar(50) NOT NULL,
  `clasifABC` char(1) NOT NULL,
  `baja` tinyint(1) NOT NULL,
  `politica` varchar(16) NOT NULL,
  `nivelServicio` double NOT NULL,
  PRIMARY KEY (`OIDProducto`),
  KEY `OIDStock` (`OIDStock`),
  CONSTRAINT `OIDS` FOREIGN KEY (`OIDStock`) REFERENCES `Stock` (`OIDStock`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla de Productos';

--
-- Dumping data for table `sg1`.`Producto`
--

/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
LOCK TABLES `Producto` WRITE;
INSERT INTO `sg1`.`Producto` VALUES  ('42',7,'Pan Dulce','500 grs',1.4,0,'45','C',0,'2',0),
 ('43',3,'Chocolate en Rama','Negro 250 grs',8,1,'123','C',0,'1',1),
 ('44',4,'Mani con Chocolate','Caja x 100',5.8,2.25,'122','C',0,'2',0.25);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Producto` ENABLE KEYS */;


--
-- Definition of table `sg1`.`ProductoProveedor`
--

DROP TABLE IF EXISTS `sg1`.`ProductoProveedor`;
CREATE TABLE  `sg1`.`ProductoProveedor` (
  `OIDProductoProveedor` varchar(50) NOT NULL,
  `OIDProveedor` varchar(50) NOT NULL,
  `OIDProducto` varchar(50) NOT NULL,
  `baja` tinyint(1) NOT NULL,
  PRIMARY KEY (`OIDProductoProveedor`),
  KEY `OIDProveedor` (`OIDProveedor`),
  KEY `OIDProducto` (`OIDProducto`),
  CONSTRAINT `OIDProdu` FOREIGN KEY (`OIDProducto`) REFERENCES `Producto` (`OIDProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OIDProve` FOREIGN KEY (`OIDProveedor`) REFERENCES `Proveedor` (`OIDProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de Producto Proveedor';

--
-- Dumping data for table `sg1`.`ProductoProveedor`
--

/*!40000 ALTER TABLE `ProductoProveedor` DISABLE KEYS */;
LOCK TABLES `ProductoProveedor` WRITE;
INSERT INTO `sg1`.`ProductoProveedor` VALUES  ('1','3','42',0),
 ('2','2','43',0),
 ('3','1','44',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ProductoProveedor` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Proveedor`
--

DROP TABLE IF EXISTS `sg1`.`Proveedor`;
CREATE TABLE  `sg1`.`Proveedor` (
  `OIDProveedor` varchar(50) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `CUIT` varchar(15) NOT NULL,
  `Codigo_Proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `baja` tinyint(1) NOT NULL,
  `CostoEmision` float NOT NULL,
  `TiempoReposicion` int(11) NOT NULL,
  `PeriodoActual` int(11) NOT NULL,
  PRIMARY KEY (`OIDProveedor`),
  KEY `Codigo_Proveedor` (`Codigo_Proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='Tabla de Proveedores';

--
-- Dumping data for table `sg1`.`Proveedor`
--

/*!40000 ALTER TABLE `Proveedor` DISABLE KEYS */;
LOCK TABLES `Proveedor` WRITE;
INSERT INTO `sg1`.`Proveedor` VALUES  ('1','La Delicia Felipe Fort S.A.','Gascón 329 - Buenos Aires','46180000','01039672',1,'consumidor@felfort.com.ar',0,12,3,2011),
 ('2','Aguila S.A','San Pedro 786 Las Heras','654321','654321',2,'chocolate@aguila.com',0,12,3,2011),
 ('3','Pamela S.A.','San luis 328 Guaymallen','45888888','30-2000000-0',3,'pandulce@pamela.com.ar',0,12,3,2011);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Proveedor` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Stock`
--

DROP TABLE IF EXISTS `sg1`.`Stock`;
CREATE TABLE  `sg1`.`Stock` (
  `OIDStock` varchar(50) NOT NULL,
  `cantidadminima` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `stockPendiente` int(11) NOT NULL,
  `CostoAlmacen` double DEFAULT NULL,
  PRIMARY KEY (`OIDStock`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de Stock';

--
-- Dumping data for table `sg1`.`Stock`
--

/*!40000 ALTER TABLE `Stock` DISABLE KEYS */;
LOCK TABLES `Stock` WRITE;
INSERT INTO `sg1`.`Stock` VALUES  ('0a8e0234-4cc7-40e9-8eb6-e843bd3006c1',7,15,0,12),
 ('122',0,0,0,12),
 ('123',0,89,89,12),
 ('2289e9e1-544a-41a1-bac6-10ca3287dc66',10,20,0,12),
 ('45',0,124,0,12),
 ('4e6dbe63-5e98-4cf0-9cff-8838247b7007',5,10,0,12),
 ('53337258-8647-4caf-a693-552966321a89',5,10,0,12),
 ('6f3e8c87-31ac-4453-9ab3-e363a6f72ff8',15,30,0,12),
 ('a9d02dee-6ff5-4f6d-9b0b-a9d873fe5c92',25,50,0,12),
 ('d2aadf4a-d114-4d83-b208-717975e43a9c',15,30,0,12);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Stock` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Venta`
--

DROP TABLE IF EXISTS `sg1`.`Venta`;
CREATE TABLE  `sg1`.`Venta` (
  `OIDVenta` varchar(50) NOT NULL,
  `OIDCliente` varchar(50) NOT NULL,
  `fechaventa` date DEFAULT NULL,
  `numero` int(50) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`OIDVenta`),
  KEY `OIDCliente` (`OIDCliente`),
  CONSTRAINT `OIDCliente2` FOREIGN KEY (`OIDCliente`) REFERENCES `Cliente` (`OIDCliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Venta`
--

/*!40000 ALTER TABLE `Venta` DISABLE KEYS */;
LOCK TABLES `Venta` WRITE;
INSERT INTO `sg1`.`Venta` VALUES  ('1a816101-a6c8-4317-8f93-db1339f7a4be','2','2011-12-01',3,151.5),
 ('1ff62433-813e-4303-b780-562303dc5c8d','1','2011-12-01',1,1),
 ('473c6a20-64a0-4be4-a564-a0c4cad69ec6','1','2011-12-01',10,0.25),
 ('4a73053b-f6fd-4161-9532-ad3bd24a1da6','1','2011-12-01',2,1.5),
 ('5234109b-e9e9-491e-a9f2-b9b32c048d93','2','2011-12-01',6,0.25),
 ('6062033c-03c3-467f-90ae-b4a280f9619e','2','2011-12-01',10,0.25),
 ('61ee2ceb-1cc0-4c58-a708-3c5aca5bf676','3','2011-12-01',10,0.25),
 ('6db72852-2147-4a2b-97eb-ab8c5e9ad016','1','2011-12-01',10,0),
 ('bcd21dfa-1562-4530-9b8c-e573faed5152','1','2011-12-01',8,0.25),
 ('bf6f7200-bc0e-4f95-b94e-2fe883990aa5','1','2011-12-01',10,0),
 ('e893842b-356d-4897-8ad1-e6a6855182ae','1','2011-12-01',4,0.25);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Venta` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
