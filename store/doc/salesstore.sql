/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.29 : Database - salesstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`salesstore` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `salesstore`;

/*Table structure for table `achat` */

DROP TABLE IF EXISTS `achat`;

CREATE TABLE `achat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `support_id` varchar(255) DEFAULT NULL,
  `support_price` varchar(255) DEFAULT NULL,
  `produce_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `achat` */

insert  into `achat`(`id`,`category_id`,`content`,`create_date`,`create_user`,`title`,`state`,`support_id`,`support_price`,`produce_id`) values (1,'6','来一台外星人','2016-04-17','1','笔记本','5','8','10.00','1'),(2,'6','来一台外星人','2016-04-17','1','笔记本','5','9','99999.00','7'),(3,'13','反正就是要尽量牛逼','2016-04-17','1','我要一台蓝牙耳机','1','4',NULL,NULL),(5,'19','一根苹果数据线，不要太贵，谢谢。','2016-04-17','2','苹果数据线','5','8','200.00','1'),(6,'4','联想电脑','2016-04-20','1','我要买一台性价比不错的电脑','0',NULL,NULL,NULL);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL COMMENT '类别等级 共三级',
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`level`,`name`,`parent_id`,`user_id`) values (2,'1','电脑',-1,'8'),(3,'2','电脑整机',2,'8'),(4,'3','笔记本',3,'8'),(5,'3','超级本',3,'8'),(6,'3','游戏本',3,'8'),(7,'2','外设产品',2,'8'),(8,'3','鼠标',7,'8'),(9,'3','薄膜键盘',7,'8'),(10,'3','机械键盘',7,'8'),(11,'1','手机',-1,'8'),(12,'2','手机配件',11,'8'),(13,'3','蓝牙耳机',12,'8'),(17,'3','智能手环',15,'8'),(18,'3','智能手表',15,'8'),(19,'3','充电线，数据线',12,'8'),(20,'2','游戏设备',2,'8'),(21,'3','手柄',20,'8'),(22,'3','游戏机',20,'8'),(23,'2','网络产品',2,'8'),(24,'3','路由器',23,'8'),(25,'3','网卡',23,'8');

/*Table structure for table `function` */

DROP TABLE IF EXISTS `function`;

CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(255) DEFAULT NULL,
  `function_url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `function_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8;

/*Data for the table `function` */

insert  into `function`(`id`,`function_name`,`function_url`,`parent_id`,`remark`,`function_type`) values (12,'角色管理','',-1,'','1'),(13,'新增角色','role/create',12,'','2'),(1001,'用户管理','',-1,'','1'),(1002,'编辑用户','user/edit',1001,'编辑','2'),(1003,'删除用户','user/delete',1001,'删除用户','2'),(1005,'查询用户','user/getUserList',1001,'查询用户列表','2'),(1007,'新增角色','role/create',12,'','2'),(1008,'删除角色','role/delete',12,'','2');

/*Table structure for table `produce` */

DROP TABLE IF EXISTS `produce`;

CREATE TABLE `produce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` varchar(255) DEFAULT NULL,
  `kucun_number` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `produce` */

insert  into `produce`(`id`,`category_id`,`kucun_number`,`name`,`user_id`,`model`,`path`,`remark`) values (11,'4',1,'饿4','8','123234','upload/569e3007a775741f9c7c9ba3a1a3c877f7f.jpg',NULL),(12,'4',22,'小新出色版I2000 IRIS -草莓红','8','I2000 IRIS','upload/CmBZEFXkAAyAFXo2AADN6udKbC4087.jpg',NULL),(13,'5',20,'IdeaPad 700S-14ISK-6Y30','8','700S-14ISK','upload/CmBZD1ZMTvSAO4kbAABgZL1Gl7U142.jpg',NULL),(14,'6',34,'拯救者15-ISK i7悦动版','8','15-ISK i7','upload/CmBZD1ZbzDCAddBBAACgYoGkhAE633.jpg',NULL),(15,'5',49,'小新旗舰版超薄游戏本-I7','8','旗舰','upload/CmBZD1aCU4SAFsGGAADg4SOa9NE795.jpg',NULL),(16,'6',66,'Y700 I7-6700HQ（旗舰版）','8','旗舰版','upload/CmBZD1YnXfKAAmkxAACq-ez2O90092.jpg','很不错哦，很厉害哟，你要抓紧时间买哟。'),(17,'4',1,'thinkpad','8','carbon x1 2016','upload/pFTuWtjp1LLgaeM6fTaGgaDjm-7650.w544.jpg','处理器/操作系统\r\nCPU	第六代智能英特尔®酷睿™i5-6200U处理器(2.3GHz睿频至2.8GHz,3MB)\r\n操作系统	Windows 10 家庭版\r\nCPU类型	i5\r\nCPU型号	i5-6200U\r\nCPU主频	2.3GHz\r\n最高睿频	2.8GHz\r\n三级缓存	3MB\r\n硬盘\r\n硬盘容量	192GB\r\n固态硬盘	192GB M.2 固态硬盘\r\n硬盘类型	固态硬盘'),(18,'',1,'','3','','upload/11618807927aa2ef3do.jpg',''),(19,'19',4,'111','3','444','upload/8a98f00a883a01ffd596ed5c71e988e17a3.jpg','');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `all_function_name` varchar(255) DEFAULT NULL,
  `function_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`role_name`,`remark`,`all_function_name`,`function_id`) values (1,'管理员','所有权限',NULL,'12,13,1001,1002,1003,1005'),(2,'普通会员','普通角色',NULL,'12,13'),(3,'供应商','提供产品',NULL,NULL),(4,'客户经理','管理会员',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `parseDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`login_date`,`province`,`realname`,`register_date`,`role_id`,`role_name`,`parseDate`) values (1,'crossoverJie','abc123','2016-04-30 19:41:44','江苏省','变相1','2016-04-12 19:57:09','2',NULL,NULL),(2,'杰哥1','abc123','2016-04-20 19:41:49','重庆市','哈哈','2016-04-12 19:57:12','2',NULL,NULL),(3,'admin','abc123','2016-06-20 23:50:08','福建省','杰哥dsd',NULL,'1',NULL,NULL),(4,'nanjing','abc123','2016-04-20 19:44:54','江苏省','南京供应商1',NULL,'3',NULL,NULL),(7,'wangermazi','abc123','2016-04-17 10:48:39','河北省','河北客户经理','2016-04-11 22:09:15','4',NULL,NULL),(8,'供应商2','abc123','2016-05-03 11:40:03','江苏省','test2','2016-04-17 15:54:03','3',NULL,NULL),(9,'供应商3','abc123','2016-04-20 19:40:35','重庆市','test3','2016-04-17 15:55:06','3',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
