-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 192.168.47.128    Database: car_demo
-- ------------------------------------------------------
-- Server version	8.0.20
use car_demo;
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
-- Table structure for table `car_category`
--

DROP TABLE IF EXISTS `car_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='汽车分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_category`
--

LOCK TABLES `car_category` WRITE;
/*!40000 ALTER TABLE `car_category` DISABLE KEYS */;
INSERT INTO `car_category` VALUES (1,'德系','2020-06-24 07:37:26','2020-06-24 07:37:26'),(2,'美系','2020-06-24 07:37:26','2020-06-24 07:37:26'),(3,'日系','2020-06-24 07:37:26','2020-06-24 07:37:26'),(4,'国产','2020-06-24 07:37:26','2020-06-24 07:37:26'),(5,'全部','2020-06-26 02:53:31','2020-06-26 02:53:31');
/*!40000 ALTER TABLE `car_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_menu`
--

DROP TABLE IF EXISTS `car_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_menu` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `car_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '汽车名字',
  `category_type` int NOT NULL COMMENT '汽车分类',
  `src` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小图路径',
  `src_list` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '大图路径',
  `car_factory` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出厂商',
  `displacement` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '排量',
  `kilometers` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公里数',
  `oli_consumption` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '油耗',
  `car_desc` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '汽车描述',
  `car_price` decimal(10,2) NOT NULL COMMENT '汽车价格',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `able` int DEFAULT '1' COMMENT '1显示,0不显示',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='汽车详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_menu`
--

LOCK TABLES `car_menu` WRITE;
/*!40000 ALTER TABLE `car_menu` DISABLE KEYS */;
INSERT INTO `car_menu` VALUES (1,'奥迪A4L',1,'http://192.168.47.128:8181/img/file/T_1594781234223837772.jpg','http://192.168.47.128:8181/img/file/T_1594781237484884101.jpg','August Horch 出厂','2.0T','2.2公里','9.0L/百公里','启动快,油耗适中,舒适',150.00,'2020-06-24 10:25:30','2020-07-15 14:48:09',1),(2,'凯迪拉克',2,'http://192.168.47.128:8181/img/file/T_1594777933979982848.jpg','http://192.168.47.128:8181/img/file/T_1594777940167345904.jpg','凯迪拉克汽车公司出厂','2.0T','2.5公里','10.2L/百公里','安全度高,油耗适中,舒适',220.00,'2020-06-24 10:32:39','2020-07-14 12:52:23',1),(3,'皇冠轿车',3,'http://192.168.47.128:8181/img/file/T_1594781285813882855.jpg','http://192.168.47.128:8181/img/file/T_1594781290222204030.jpg','日本丰田株式会社出厂','1.8T','3.2公里','8.0L/百公里','启动快,省油耗,舒适',100.00,'2020-06-24 10:32:39','2020-07-14 13:48:25',1),(4,'北京J40',4,'http://192.168.47.128:8181/img/file/T_1594781321376288231.jpg','http://192.168.47.128:8181/img/file/T_1594781334796977047.jpg','中国北京汽车出厂','3.0T','4.3公里','11.2L/百公里','空间大,油耗适中,舒适',200.00,'2020-06-24 10:32:39','2020-07-16 09:10:08',1),(5,'宝马530',1,'http://192.168.47.128:8181/img/file/T_1594781348029698232.jpg','http://192.168.47.128:8181/img/file/T_1594781351165915650.jpg','德国宝马出厂','2.0T','5.2公里','10.0L/百公里','启动快,油耗适中,舒适',450.00,'2020-06-24 10:38:14','2020-07-15 14:47:35',1),(6,'牧马人',2,'http://192.168.47.128:8181/img/file/T_1594781373791820055.jpg','http://192.168.47.128:8181/img/file/T_1594781380673994470.jpg','美国牧马人出厂','3.0T','3.8公里','12L/百公里','安全度高,油耗适中,舒适',400.00,'2020-06-24 10:38:14','2020-07-14 13:49:45',1),(7,'雷克萨斯',3,'http://192.168.47.128:8181/img/file/T_1594781414593269048.jpg','http://192.168.47.128:8181/img/file/T_1594781419475854366.jpg','日本凌志株式会社出厂','2.0T','3.0公里','9L/百公里','启动快,省油耗,舒适',150.00,'2020-06-24 10:38:14','2020-07-14 13:50:21',1),(8,'哈佛H9',4,'http://192.168.47.128:8181/img/file/T_1594781445516815026.jpg','http://192.168.47.128:8181/img/file/T_1594781449269142206.jpg','中国长安汽车出厂','3.0T','6.3公里','13L/百公里','空间大,油耗适中,舒适',200.00,'2020-06-24 10:38:14','2020-07-14 13:50:54',1),(9,'奔驰C系',1,'http://192.168.47.128:8181/img/file/T_1594781466564823251.jpg','http://192.168.47.128:8181/img/file/T_1594781469827179071.jpg','德国奔驰出厂','2.0T','6.2公里','9.2L/百公里','启动快,油耗适中,舒适',300.00,'2020-06-24 10:46:06','2020-07-15 14:46:55',1),(10,'君威2.0',2,'http://192.168.47.128:8181/img/file/T_1594781506555801770.jpg','http://192.168.47.128:8181/img/file/T_1594781511083670861.jpg','美国别克出厂','2.0T','4.5公里','11.2L/百公里','安全度高,油耗适中,舒适',200.00,'2020-06-24 10:46:06','2020-07-14 13:52:29',1),(11,'阿尔法',3,'http://192.168.47.128:8181/img/file/T_1594781565999915340.jpg','http://192.168.47.128:8181/img/file/T_1594781569916869109.jpg','日本丰田株式会社出厂','5.0T','2.8公里','12.2L/百公里','启动快,省油耗,舒适',500.00,'2020-06-24 10:46:06','2020-07-14 13:52:51',1),(12,'红旗SUV',4,'http://192.168.47.128:8181/img/file/T_1594781588464935533.jpg','http://192.168.47.128:8181/img/file/T_1594781592653827359.jpg','中国红旗汽车出厂','3.0T','3.9公里','10.5L/百公里','空间大,油耗适中,舒适',380.00,'2020-06-24 10:46:06','2020-07-14 13:53:17',1),(13,'大众CC',1,'http://192.168.47.128:8181/img/file/T_1594781614497976559.jpg','http://192.168.47.128:8181/img/file/T_1594781617503323367.jpg','德国一汽大众出厂','2.0T','5.5公里','10.2L/百公里','启动快,油耗适中,舒适',200.00,'2020-06-24 10:51:10','2020-07-14 13:53:39',1),(14,'特斯拉',2,'http://192.168.47.128:8181/img/file/T_1594781636646736451.jpg','http://192.168.47.128:8181/img/file/T_1594781644042132591.jpg','美国特斯拉出厂','2.0T','3.5公里','11.3L/百公里','安全度高,油耗适中,舒适',300.00,'2020-06-24 10:51:10','2020-07-14 13:54:06',1),(15,'马自达',3,'http://192.168.47.128:8181/img/file/T_1594781658510840297.jpg','http://192.168.47.128:8181/img/file/T_1594781662142920973.jpg','日本马自达株式会社出厂','1.8T','5.2公里','9.2L/百公里','启动快,省油耗,舒适',200.00,'2020-06-24 10:51:10','2020-07-14 13:54:24',1),(16,'领克SUV',4,'http://192.168.47.128:8181/img/file/T_1594781675791400640.jpg','http://192.168.47.128:8181/img/file/T_1594781679852393418.jpg','中国领克汽车出厂','2.0T','3.2公里','8.5L/百公里','空间大,油耗适中,舒适',100.00,'2020-06-24 10:51:10','2020-07-14 13:54:48',1),(17,'迈_腾',1,'http://192.168.47.128:8181/img/file/T_1594781699156222160.jpg','http://192.168.47.128:8181/img/file/T_1594781702888938495.jpg','德国一汽大众出厂','2.0T','6.2公里','10.0L/百公里','启动快,油耗适中,舒适',200.00,'2020-06-24 10:55:21','2020-07-14 13:55:15',1),(18,'雪佛兰',2,'http://192.168.47.128:8181/img/file/T_1594781727271648495.jpg','http://192.168.47.128:8181/img/file/T_1594781730446530463.jpg','美国雪佛兰出厂','3.0T','4.2公里','11.8L/百公里','安全度高,油耗适中,舒适',200.00,'2020-06-24 10:55:21','2020-07-14 13:55:32',1),(19,'讴歌SUV',3,'http://192.168.47.128:8181/img/file/T_1594781748284235380.jpg','http://192.168.47.128:8181/img/file/T_1594781751376709146.jpg','日本讴歌株式会社出厂','5.0T','3.8公里','12.8L/百公里','启动快,省油耗,舒适',400.00,'2020-06-24 10:55:21','2020-07-14 13:55:54',1),(20,'蔚来SUV',4,'http://192.168.47.128:8181/img/file/T_1594781764489441863.jpg','http://192.168.47.128:8181/img/file/T_1594781769166720398.jpg','中国蔚来汽车出厂','2.0T','2.6公里','11.0L/百公里','空间大,油耗适中,舒适',100.00,'2020-06-24 10:55:21','2020-07-14 13:56:14',1),(21,'迈巴赫',1,'http://192.168.47.128:8181/img/file/T_1594781784791891336.jpg','http://192.168.47.128:8181/img/file/T_1594781790280788298.jpg','德国迈巴赫出厂','2.0T','1.2公里','12.2L/百公里','启动快,油耗适中,舒适',500.00,'2020-06-26 02:59:50','2020-07-15 14:46:31',1),(29,'斯巴鲁',3,'http://192.168.47.128:8181/img/file/T_1594891421035432696.jpg','http://192.168.47.128:8181/img/file/T_1594696561927970074.jpg','日本斯巴鲁株式会社','2.0T','5.2公里','8.0L/百公里','起步快，省油，舒适',300.00,'2020-07-13 14:17:05','2020-07-15 20:23:51',1);
/*!40000 ALTER TABLE `car_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_order`
--

DROP TABLE IF EXISTS `car_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_order` (
  `order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `user_id` int NOT NULL COMMENT '用户id',
  `user_address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户地址',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户电话',
  `car_id` int NOT NULL COMMENT '汽车名称',
  `src` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小图路径',
  `car_price` decimal(10,2) NOT NULL COMMENT '汽车价格',
  `days` int NOT NULL COMMENT '租赁天数',
  `total_price` decimal(10,2) NOT NULL COMMENT '租赁总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `state` int DEFAULT '0' COMMENT '车辆状态',
  `admin_id` int DEFAULT NULL COMMENT '管理员id',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='汽车租赁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_order`
--

LOCK TABLES `car_order` WRITE;
/*!40000 ALTER TABLE `car_order` DISABLE KEYS */;
INSERT INTO `car_order` VALUES ('1593158877260327669',1,'北京市朝阳区花园小区','13784146219',2,'../../static/images/cars/m_kaidilake.jpg',220.00,3,660.00,'2020-06-26 08:07:57','2020-07-06 03:58:06',3,4),('1593161176082747498',1,'北京市朝阳区花园小区','13784146219',19,'http://192.168.47.128:8181/img/file/T_1594781751376709146.jpg',400.00,5,2000.00,'2020-06-26 08:46:16','2020-07-15 09:24:15',2,3),('1593161548418612865',1,'北京市朝阳区花园小区','13784146219',5,'../../static/images/cars/d_baoma530.jpg',450.00,4,1800.00,'2020-06-26 08:52:28','2020-07-06 03:57:24',2,3),('1593161773168998293',2,'北京市海淀区滨湖小区','12345678904',20,'../../static/images/cars/g_weilai.jpg',100.00,4,400.00,'2020-06-26 08:56:13','2020-07-06 03:57:24',3,3),('1593161831672999267',2,'北京市海淀区滨湖小区','12345678904',10,'../../static/images/cars/m_junwei.jpg',200.00,5,1000.00,'2020-06-26 08:57:11','2020-07-06 03:57:24',3,3),('1593161897505356965',2,'北京市海淀区滨湖小区','12345678904',4,'../../static/images/cars/g_beijing.jpg',200.00,5,1000.00,'2020-06-26 08:58:17','2020-07-06 03:57:24',2,3),('1593162003050768479',2,'北京市海淀区滨湖小区','12345678904',5,'../../static/images/cars/d_baoma530.jpg',450.00,1,450.00,'2020-06-26 09:00:03','2020-07-06 03:57:24',3,3),('1593162067040647250',2,'北京市海淀区滨湖小区','12345678904',13,'../../static/images/cars/d_dazhongcc.jpg',200.00,1,200.00,'2020-06-26 09:01:07','2020-07-06 03:57:24',3,3),('1593162250514212912',1,'北京市朝阳区花园小区','13784146219',9,'../../static/images/cars/d_benchiC260L.jpg',300.00,3,900.00,'2020-06-26 09:04:10','2020-07-06 03:57:24',3,3),('1593162369360353499',1,'北京市朝阳区花园小区','13784146219',10,'../../static/images/cars/m_junwei.jpg',200.00,1,200.00,'2020-06-26 09:06:09','2020-07-06 03:57:24',3,3),('1593162604575106245',1,'北京市朝阳区花园小区','13784146219',12,'../../static/images/cars/g_hongqi.jpg',380.00,4,1520.00,'2020-06-26 09:10:04','2020-07-06 03:57:24',2,3),('1593166460024336507',1,'北京市朝阳区花园小区','13784146219',14,'../../static/images/cars/m_tesila.jpg',300.00,4,1200.00,'2020-06-26 09:10:04','2020-07-06 03:57:24',3,3),('1593231153183679849',1,'北京市朝阳区花园小区','13784146219',1,'../../static/images/cars/d_aodiA4L.jpg',150.00,3,450.00,'2020-06-27 04:12:33','2020-07-06 03:57:24',3,4),('1593231429183898648',2,'北京市海淀区滨湖小区','12345678904',11,'../../static/images/cars/r_aierfa.jpg',500.00,1,500.00,'2020-06-27 04:17:09','2020-07-06 03:57:24',3,4),('1593231794087408215',2,'北京市海淀区滨湖小区','12345678904',2,'../../static/images/cars/m_kaidilake.jpg',220.00,3,660.00,'2020-06-27 04:23:14','2020-07-06 03:57:24',2,4),('1593341116556341240',1,'北京市朝阳区花园小区','13784146219',1,'../../static/images/cars/d_aodiA4L.jpg',150.00,1,150.00,'2020-06-28 10:45:17','2020-07-06 03:57:24',3,4),('1593401851423249955',1,'北京市朝阳区花园小区','13784146219',5,'../../static/images/cars/d_baoma530.jpg',450.00,3,1350.00,'2020-06-29 03:37:31','2020-07-06 03:57:24',3,4),('1593482884867349572',1,'北京市朝阳区花园小区','13784146219',6,'../../static/images/cars/m_mumaren.jpg',400.00,3,1200.00,'2020-06-30 02:08:05','2020-07-06 03:57:24',2,3),('1593483187897956669',1,'北京市朝阳区花园小区','13784146219',4,'../../static/images/cars/g_beijing.jpg',200.00,1,200.00,'2020-06-30 02:13:08','2020-07-06 03:57:24',2,4),('1593483290208938875',1,'北京市朝阳区花园小区','13784146219',3,'../../static/images/cars/r_huangguan.jpg',100.00,1,100.00,'2020-06-30 02:14:50','2020-07-06 03:57:24',2,4),('1593483578719244981',1,'北京市朝阳区花园小区','13784146219',2,'../../static/images/cars/m_kaidilake.jpg',220.00,1,220.00,'2020-06-30 02:19:39','2020-07-06 03:57:24',3,4),('1593483662471884244',1,'北京市朝阳区花园小区','13784146219',1,'../../static/images/cars/d_aodiA4L.jpg',150.00,1,150.00,'2020-06-30 02:21:02','2020-07-06 03:57:24',3,4),('1593485751537756346',1,'北京市朝阳区花园小区','13784146219',7,'../../static/images/cars/r_leikesasi.jpg',150.00,1,150.00,'2020-06-30 02:55:52','2020-07-06 03:57:24',2,4),('1593485868637256819',1,'北京市朝阳区花园小区','13784146219',8,'../../static/images/cars/g_hafo.jpg',200.00,1,200.00,'2020-06-30 02:57:49','2020-07-06 03:57:24',3,3),('1593487389906886766',1,'北京市朝阳区花园小区','13784146219',7,'../../static/images/cars/r_leikesasi.jpg',150.00,1,150.00,'2020-06-30 03:23:10','2020-07-06 03:57:24',1,3),('1593487666108991303',1,'北京市朝阳区花园小区','13784146219',7,'../../static/images/cars/r_leikesasi.jpg',150.00,1,150.00,'2020-06-30 03:27:46','2020-07-06 03:57:24',3,3),('1593505465972351284',1,'北京市朝阳区花园小区','13784146219',4,'../../static/images/cars/g_beijing.jpg',200.00,4,800.00,'2020-06-30 08:24:26','2020-07-06 03:57:24',3,3),('1593848044304411807',1,'北京市朝阳区花园小区','13784146219',19,'../../static/images/cars/r_ouge.jpg',400.00,4,1600.00,'2020-07-04 07:34:04','2020-07-06 03:57:24',3,3),('1593851389869376766',1,'北京市朝阳区花园小区','13784146219',11,'../../static/images/cars/r_aierfa.jpg',500.00,1,500.00,'2020-07-04 08:29:50','2020-07-06 03:57:24',3,3),('1593999045364303457',1,'北京市富强小区','12312312311',5,'../../static/images/cars/d_baoma530.jpg',450.00,3,1350.00,'2020-07-06 01:30:45','2020-07-06 03:15:02',3,3),('1594008079351827777',1,'北京市富强小区','12312312311',17,'../../static/images/cars/d_maiteng.jpg',200.00,3,600.00,'2020-07-06 04:01:19','2020-07-06 04:01:58',3,3),('1594008602170910198',1,'北京市富强小区','12312312311',15,'../../static/images/cars/r_mazida.jpg',200.00,3,600.00,'2020-07-06 04:10:02','2020-07-06 04:11:04',3,3),('1594016761626513998',1,'北京市富强小区','12312312311',10,'../../static/images/cars/m_junwei.jpg',200.00,3,600.00,'2020-07-06 06:26:02','2020-07-06 06:41:03',3,3),('1594017728207490094',2,'上海光明路18号','12332112311',16,'../../static/images/cars/g_lingke.jpg',100.00,3,300.00,'2020-07-06 06:42:08','2020-07-06 06:43:15',3,4),('1594824219824279609',5,'清河县','12333411223',4,'http://192.168.47.128:8181/img/file/T_1594781321376288231.jpg',200.00,2,400.00,'2020-07-15 01:43:40','2020-07-15 01:44:21',3,3),('1594824349630485359',5,'清河县','12333411223',5,'http://192.168.47.128:8181/img/file/T_1594781348029698232.jpg',450.00,1,450.00,'2020-07-15 01:45:50','2020-07-15 01:46:04',3,3),('1594824378199218748',5,'清河县','12333411223',21,'http://192.168.47.128:8181/img/file/T_1594781784791891336.jpg',500.00,1,500.00,'2020-07-15 01:46:18','2020-07-15 01:46:31',3,3),('1594824406189108519',5,'清河县','12333411223',9,'http://192.168.47.128:8181/img/file/T_1594781466564823251.jpg',300.00,3,900.00,'2020-07-15 01:46:46','2020-07-15 01:46:56',3,3),('1594824434645605255',5,'清河县','12333411223',5,'http://192.168.47.128:8181/img/file/T_1594781348029698232.jpg',450.00,3,1350.00,'2020-07-15 01:47:15','2020-07-15 01:47:35',3,3),('1594824478670326558',5,'清河县','12333411223',1,'http://192.168.47.128:8181/img/file/T_1594781234223837772.jpg',150.00,4,600.00,'2020-07-15 01:47:59','2020-07-15 01:48:10',3,3),('1594890522690658348',6,'hebei','11122312311',4,'http://192.168.47.128:8181/img/file/T_1594781321376288231.jpg',200.00,2,400.00,'2020-07-15 20:08:43','2020-07-15 20:10:09',3,3);
/*!40000 ALTER TABLE `car_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_return`
--

DROP TABLE IF EXISTS `car_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_return` (
  `return_id` int NOT NULL AUTO_INCREMENT COMMENT '还车编号',
  `order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号',
  `car_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '汽车名称',
  `car_price` decimal(10,2) NOT NULL COMMENT '汽车价格',
  `days` int NOT NULL COMMENT '租赁天数',
  `total_price` decimal(10,2) NOT NULL COMMENT '租赁总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '还车时间',
  `admin_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员id',
  `admin_id` int NOT NULL,
  `able` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`return_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='还书记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_return`
--

LOCK TABLES `car_return` WRITE;
/*!40000 ALTER TABLE `car_return` DISABLE KEYS */;
INSERT INTO `car_return` VALUES (3,'1593158877260327669','zhangsan','13784146219','凯迪拉克',220.00,3,660.00,'2020-06-29 08:59:43','2020-07-06 06:36:52','admin1',3,1),(4,'1593162003050768479','lisi','12345678904','宝马530',450.00,1,450.00,'2020-06-29 09:01:48','2020-07-06 06:36:52','admin1',3,1),(5,'1593161773168998293','lisi','12345678904','蔚来',100.00,4,400.00,'2020-06-29 09:05:15','2020-07-06 06:36:52','admin1',3,1),(6,'1593162250514212912','zhangsan','13784146219','奔驰C260L',300.00,3,900.00,'2020-06-29 09:05:35','2020-07-06 06:36:52','admin1',3,1),(7,'1593401851423249955','zhangsan','13784146219','宝马530',450.00,3,1350.00,'2020-06-29 09:06:02','2020-07-06 06:36:52','admin2',4,1),(8,'1593341116556341240','zhangsan','13784146219','奥迪A4L',150.00,1,150.00,'2020-06-29 09:07:36','2020-07-06 06:36:52','admin1',3,1),(9,'1593161831672999267','lisi','12345678904','君威',200.00,5,1000.00,'2020-06-30 02:03:14','2020-07-06 06:36:52','admin2',4,1),(10,'1593162067040647250','lisi','12345678904','大众CC',200.00,1,200.00,'2020-06-30 02:03:46','2020-07-06 06:36:52','admin1',3,1),(11,'1593162369360353499','zhangsan','13784146219','君威',200.00,1,200.00,'2020-06-30 02:03:47','2020-07-06 06:36:52','admin2',4,1),(12,'1593166460024336507','zhangsan','13784146219','特斯拉',300.00,4,1200.00,'2020-06-30 02:03:53','2020-07-06 06:36:52','admin1',3,1),(13,'1593231153183679849','zhangsan','13784146219','奥迪A4L',150.00,3,450.00,'2020-06-30 02:03:54','2020-07-06 06:36:52','admin2',4,1),(14,'1593231429183898648','lisi','12345678904','阿尔法',500.00,1,500.00,'2020-06-30 02:03:55','2020-07-06 06:36:52','admin2',4,1),(15,'1593483578719244981','zhangsan','13784146219','凯迪拉克',220.00,1,220.00,'2020-07-06 03:15:00','2020-07-06 06:36:52','admin1',3,1),(16,'1593483662471884244','zhangsan','13784146219','奥迪A4L',150.00,1,150.00,'2020-07-06 03:15:01','2020-07-06 06:36:52','admin1',3,1),(17,'1593485868637256819','zhangsan','13784146219','哈佛',200.00,1,200.00,'2020-06-30 02:57:56','2020-07-06 06:36:52','admin2',4,1),(18,'1593487666108991303','zhangsan','13784146219','雷克萨斯',150.00,1,150.00,'2020-06-30 03:27:54','2020-07-06 06:36:52','admin1',3,1),(19,'1593505465972351284','zhangsan','13784146219','北京',200.00,4,800.00,'2020-06-30 08:24:48','2020-07-06 06:36:52','admin2',4,1),(20,'1593848044304411807','zhangsan','13784146219','讴歌',400.00,4,1600.00,'2020-07-04 08:23:26','2020-07-06 06:36:52','admin1',3,1),(21,'1593851389869376766','zhangsan','13784146219','阿尔法',500.00,1,500.00,'2020-07-04 08:32:25','2020-07-06 06:36:52','admin1',3,1),(22,'1593999045364303457','zhangsan','12312312311','宝马530',450.00,3,1350.00,'2020-07-06 03:15:02','2020-07-06 06:36:52','admin1',3,1),(23,'1594008079351827777','zhangsan','12312312311','迈腾',200.00,3,600.00,'2020-07-06 04:01:59','2020-07-06 06:36:52','admin1',3,1),(24,'1594008602170910198','zhangsan','12312312311','马自达',200.00,3,600.00,'2020-07-06 04:11:04','2020-07-06 06:36:52','admin1',3,1),(25,'1594016761626513998','zhangsan','12312312311','君威',200.00,3,600.00,'2020-07-06 06:41:03','2020-07-06 06:41:03','admin1',3,1),(26,'1594017728207490094','lisi','12332112311','领克',100.00,3,300.00,'2020-07-06 06:43:15','2020-07-06 06:43:15','admin2',4,1),(27,'1594824219824279609','wangwu','12333411223','北京J40',200.00,2,400.00,'2020-07-15 01:44:21','2020-07-15 01:44:21','admin1',3,1),(28,'1594824349630485359','wangwu','12333411223','宝马530',450.00,1,450.00,'2020-07-15 01:46:04','2020-07-15 01:46:04','admin1',3,1),(29,'1594824378199218748','wangwu','12333411223','迈巴赫',500.00,1,500.00,'2020-07-15 01:46:31','2020-07-15 01:46:31','admin1',3,1),(30,'1594824406189108519','wangwu','12333411223','奔驰C系',300.00,3,900.00,'2020-07-15 01:46:56','2020-07-15 01:46:56','admin1',3,1),(31,'1594824434645605255','wangwu','12333411223','宝马530',450.00,3,1350.00,'2020-07-15 01:47:35','2020-07-15 01:47:35','admin1',3,1),(32,'1594824478670326558','wangwu','12333411223','奥迪A4L',150.00,4,600.00,'2020-07-15 01:48:10','2020-07-15 01:48:10','admin1',3,1),(33,'1594890522690658348','aaaa','11122312311','北京J40',200.00,2,400.00,'2020-07-15 20:10:09','2020-07-15 20:10:09','admin1',3,1);
/*!40000 ALTER TABLE `car_return` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(88) NOT NULL COMMENT '用户名',
  `password` varchar(88) NOT NULL COMMENT '密码',
  `salt` varchar(88) NOT NULL COMMENT '盐',
  `check_password` varchar(88) NOT NULL COMMENT '确认密码',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机号\n',
  `address` varchar(60) DEFAULT NULL COMMENT '地址',
  `like_model` varchar(30) DEFAULT NULL COMMENT '喜好\n',
  `src` varchar(80) DEFAULT NULL COMMENT '头像',
  `src_list` varchar(80) DEFAULT NULL COMMENT '大图',
  `role` varchar(20) NOT NULL,
  `perm` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'zhangsan','41a2c21f362563e903e7b719cf17ef55','yZm2LfJwwpV4jNAYUQTvBw==','41a2c21f362563e903e7b719cf17ef55',22,'1','12312312311','北京市富强小区','法拉利430','http://192.168.47.128:8181/img/file/T_1594432781034881643.jpg','http://192.168.47.128:8181/img/file/T_1594432781034881643.jpg','user','0'),(2,'lisi','22abc8a10916598c850dcce76c6f1222','l7aFNRjO5l71Ws9Yc6GIJg==','22abc8a10916598c850dcce76c6f1222',25,'0','12332112311','上海光明路18号','宝马','http://192.168.47.128:8181/img/file/T_1594695096002397939.jpg','http://192.168.47.128:8181/img/file/T_1594695096002397939.jpg','user','0'),(3,'admin1','b26384911c5e8345a0d9c809d4f4dce1','s7RBL8hGulx5MSh+Yt7YNQ==','b26384911c5e8345a0d9c809d4f4dce1',30,'1','13233233311','北京市富强小区','保时捷911','http://192.168.47.128:8181/img/file/T_1594891056341200714.jpg','http://192.168.47.128:8181/img/file/T_1594891056341200714.jpg','admin','1'),(4,'admin2','9a3ca91eea1fa4a42fbad756c34b4351','XsQdc37nOTsH+Bg3KIyXOg==','9a3ca91eea1fa4a42fbad756c34b4351',33,'0','13243212311','河北石家庄','野马','http://192.168.47.128:8181/img/file/T_1594865338868546697.jpg','http://192.168.47.128:8181/img/file/T_1594865338868546697.jpg','admin','1'),(5,'wangwu','05449e2f103352fb94c9c564120321d3','eJ5dssg0M/zAa5alCqeYEg==','05449e2f103352fb94c9c564120321d3',18,'1','12333411223','清河县','保时捷911','http://192.168.47.128:8181/img/file/T_1594695105668348461.jpg','http://192.168.47.128:8181/img/file/T_1594695105668348461.jpg','user','0');
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

-- Dump completed on 2020-07-19 19:12:57
