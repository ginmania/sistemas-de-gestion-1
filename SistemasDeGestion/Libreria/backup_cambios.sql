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
  `OIDCatalogo` varchar(16) NOT NULL,
  `OIDProveedor` varchar(16) DEFAULT NULL,
  `OIDProducto` varchar(16) DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `Demora` int(11) DEFAULT NULL,
  `PrecioCompra` float DEFAULT NULL,
  PRIMARY KEY (`OIDCatalogo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Catalogo`
--

/*!40000 ALTER TABLE `Catalogo` DISABLE KEYS */;
LOCK TABLES `Catalogo` WRITE;
INSERT INTO `sg1`.`Catalogo` VALUES  ('1','3','42','2011-01-01',3,1.25),
 ('2','2','43','2011-01-01',3,0.25),
 ('3','1','44','2011-01-01',3,2.25),
 ('4','3','43','2011-10-01',3,0.2);
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
INSERT INTO `sg1`.`Demanda` VALUES  ('1','42','01012010','28012010',5000,0,4580,0,0,'2010',1,0,0,0,0,0),
 ('10','42','11092010','08102010',17250,0,15600,0,0,'2010',10,0,0,0,0,0),
 ('11','42','09102010','05112010',17250,0,16000,0,0,'2010',11,0,0,0,0,0),
 ('12','42','06112010','03112010',17250,0,16800,0,0,'2010',12,0,0,0,0,0),
 ('13','42','04122010','31122010',18000,0,17400,0,0,'2010',13,0,0,0,0,0),
 ('14','43','01012010','28012010',2000,0,0,0,0,'2010',1,0,0,0,0,0),
 ('15','43','29012010','25022010',1375,0,0,0,0,'2010',2,0,0,0,0,0),
 ('16','43','26022010','25032010',1250,0,0,0,0,'2010',3,0,0,0,0,0),
 ('17','43','26032010','23042010',2375,0,0,0,0,'2010',4,0,0,0,0,0),
 ('18','43','24042010','21052010',1625,0,0,0,0,'2010',5,0,0,0,0,0),
 ('19','43','22052010','18062010',1625,0,0,0,0,'2010',6,0,0,0,0,0),
 ('2','42','29012010','25022010',4750,0,4320,0,0,'2010',2,0,0,0,0,0),
 ('20','43','19062010','16072010',2500,0,0,0,0,'2010',7,0,0,0,0,0),
 ('21','43','17072010','13082010',1375,0,0,0,0,'2010',8,0,0,0,0,0),
 ('22','43','14082010','10092010',8800,0,0,0,0,'2010',9,0,0,0,0,0),
 ('23','43','11092010','08102010',9000,0,0,0,0,'2010',10,0,0,0,0,0),
 ('24','43','09102010','05112010',10000,0,0,0,0,'2010',11,0,0,0,0,0),
 ('25','43','06112010','03112010',8875,0,0,0,0,'2010',12,0,0,0,0,0),
 ('26','43','04122010','31122010',7625,0,0,0,0,'2010',13,0,0,0,0,0),
 ('27','44','01012010','28012010',13000,0,0,0,0,'2010',1,0,0,0,0,0),
 ('28','44','29012010','25022010',18000,0,0,0,0,'2010',2,0,0,0,0,0),
 ('29','44','26022010','25032010',13000,0,0,0,0,'2010',3,0,0,0,0,0),
 ('3','42','26022010','25032010',3000,0,3500,0,0,'2010',3,0,0,0,0,0),
 ('30','44','26032010','23042010',17000,0,0,0,0,'2010',4,0,0,0,0,0),
 ('31','44','24042010','21052010',14000,0,0,0,0,'2010',5,0,0,0,0,0),
 ('32','44','22052010','18062010',12000,0,0,0,0,'2010',6,0,0,0,0,0),
 ('33','44','19062010','16072010',16000,0,0,0,0,'2010',7,0,0,0,0,0),
 ('34','44','17072010','13082010',17000,0,0,0,0,'2010',8,0,0,0,0,0),
 ('35','44','14082010','10092010',72000,0,0,0,0,'2010',9,0,0,0,0,0),
 ('36','44','11092010','08102010',60000,0,0,0,0,'2010',10,0,0,0,0,0),
 ('37','44','09102010','05112010',74000,0,0,0,0,'2010',11,0,0,0,0,0),
 ('38','44','06112010','03112010',65000,0,0,0,0,'2010',12,0,0,0,0,0),
 ('39','44','04122010','31122010',20000,0,0,0,0,'2010',13,0,0,0,0,0),
 ('4','42','26032010','23042010',3750,0,3250,0,0,'2010',4,0,0,0,0,0),
 ('5','42','24042010','21052010',4750,0,3900,0,0,'2010',5,0,0,0,0,0),
 ('6','42','22052010','18062010',3000,0,3650,0,0,'2010',6,0,0,0,0,0),
 ('7','42','19062010','16072010',3500,0,3175,0,0,'2010',7,0,0,0,0,0),
 ('8','42','17072010','13082010',4500,0,3780,0,0,'2010',8,0,0,0,0,0),
 ('9','42','14082010','10092010',4750,0,4230,0,0,'2010',9,0,0,0,0,0);
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
  `baja` tinyint(1) NOT NULL,
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
INSERT INTO `sg1`.`DetallePedido` VALUES  ('1','43','1',15,0);
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
INSERT INTO `sg1`.`DetalleVenta` VALUES  ('6b1c4bfe-31f2-45b6-96e1-caafc06d7157','42','1872aa84-7456-455e-aeb5-ec4c00a5d5e2',3,54,18),
 ('85c8ec48-8238-43db-92f4-ad7c67577edf','43','a16401e0-c4fc-411e-9838-a1059a10824f',3,12,4),
 ('87029bc2-60e9-4cf0-b467-bc56f955d3da','42','9dfa86a6-69bc-41c9-9dca-fd9f0f7b5336',1,18,18),
 ('ba53da19-8b5d-4f45-889d-07a748d1c7f2','43','e7689090-d70c-438c-b632-a66ebce6b9ce',11,132,12),
 ('cbc2c0cc-67a4-4280-a2b7-779c8a3e9264','42','54501790-9fc0-452a-b3b0-5b986cd4c7b6',4,72,18),
 ('ee8abe53-1078-41fb-b3ae-630ece8ce3e1','42','a16401e0-c4fc-411e-9838-a1059a10824f',2,36,18);
UNLOCK TABLES;
/*!40000 ALTER TABLE `DetalleVenta` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Pedido`
--

DROP TABLE IF EXISTS `sg1`.`Pedido`;
CREATE TABLE  `sg1`.`Pedido` (
  `OIDPedido` varchar(50) NOT NULL,
  `OIDProveedor` varchar(50) NOT NULL,
  `fechaemision` varchar(50) NOT NULL,
  `fechaentrega` varchar(50) NOT NULL,
  `pendiente` tinyint(1) NOT NULL,
  `NroPedido` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`OIDPedido`),
  KEY `OIDProveedor` (`OIDProveedor`),
  CONSTRAINT `OIDProve1` FOREIGN KEY (`OIDProveedor`) REFERENCES `Proveedor` (`OIDProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg1`.`Pedido`
--

/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
LOCK TABLES `Pedido` WRITE;
INSERT INTO `sg1`.`Pedido` VALUES  ('1','3','2011-01-10','null',1,'0000000001');
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
INSERT INTO `sg1`.`Politica` VALUES  ('0','Politica S,Q'),
 ('1','Politica S,R');
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
INSERT INTO `sg1`.`Producto` VALUES  ('42',7,'Pan Dulce','500 grs',14,0,'45','C',0,'0',0),
 ('43',3,'Chocolate en Rama','Negro 250 grs',8,0,'123','C',0,'1',0),
 ('44',4,'Mani con Chocolate','Caja x 100',5.8,0,'122','C',0,'0',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='Tabla de Proveedores';

--
-- Dumping data for table `sg1`.`Proveedor`
--

/*!40000 ALTER TABLE `Proveedor` DISABLE KEYS */;
LOCK TABLES `Proveedor` WRITE;
INSERT INTO `sg1`.`Proveedor` VALUES  ('1','La Delicia Felipe Fort S.A.','Gascón 329 - Buenos Aires','46180000','01039672',1,'consumidor@felfort.com.ar',0,0,3,0),
 ('2','Aguila S.A','San Pedro 786 Las Heras','654321','654321',2,'chocolate@aguila.com',0,0,3,0),
 ('3','Pamela S.A.','San luis 328 Guaymallen','45888888','30-2000000-0',3,'pandulce@pamela.com.ar',0,0,3,0),
 ('6302324a-542a-47c8-8986-78ec045e473f','La Serenisima S.A.','Cordoba Av.Calle S/N','02354587','12-254879',4,'la@laserenisima.com.ar',0,0,0,0),
 ('74cc77fe-fe3e-47e0-b76c-acd993723975','La Serenisima S.A.','Cordoba Av.Calle S/N','02354587','12-254879',5,'la@laserenisima.com.ar',1,0,0,0);
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
  PRIMARY KEY (`OIDStock`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Es la tabla de Stock';

--
-- Dumping data for table `sg1`.`Stock`
--

/*!40000 ALTER TABLE `Stock` DISABLE KEYS */;
LOCK TABLES `Stock` WRITE;
INSERT INTO `sg1`.`Stock` VALUES  ('122',500,10000,0),
 ('123',500,11,0),
 ('2289e9e1-544a-41a1-bac6-10ca3287dc66',10,20,0),
 ('45',500,4,0),
 ('4e6dbe63-5e98-4cf0-9cff-8838247b7007',5,10,0),
 ('a9d02dee-6ff5-4f6d-9b0b-a9d873fe5c92',25,50,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Stock` ENABLE KEYS */;


--
-- Definition of table `sg1`.`Venta`
--

DROP TABLE IF EXISTS `sg1`.`Venta`;
CREATE TABLE  `sg1`.`Venta` (
  `OIDVenta` varchar(50) NOT NULL,
  `OIDCliente` varchar(50) NOT NULL,
  `fechaventa` varchar(50) NOT NULL,
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
INSERT INTO `sg1`.`Venta` VALUES  ('1872aa84-7456-455e-aeb5-ec4c00a5d5e2','1','26/09/2011',2,54),
 ('54501790-9fc0-452a-b3b0-5b986cd4c7b6','3','26/09/2011',12,72),
 ('9dfa86a6-69bc-41c9-9dca-fd9f0f7b5336','1','26/09/2011',2,18),
 ('a16401e0-c4fc-411e-9838-a1059a10824f','1','25/09/2011',0,48),
 ('e7689090-d70c-438c-b632-a66ebce6b9ce','5','26/09/2011',3,132);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Venta` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
