/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 8.0.26 : Database - library
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `title` varchar(30) DEFAULT NULL COMMENT '书名',
  `author` varchar(30) DEFAULT NULL COMMENT '作者',
  `type` varchar(30) DEFAULT NULL COMMENT '类型',
  `price` varchar(30) DEFAULT NULL COMMENT '价格',
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图书图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT '1' COMMENT '版本',
  `deleted` int DEFAULT '0' COMMENT '删除记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2055294979 DEFAULT CHARSET=utf8mb3;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`author`,`type`,`price`,`img`,`create_time`,`update_time`,`version`,`deleted`) values (-1484730367,'','','','','/img/books/2a2f5dd9-74ec-43e6-9b0f-a720922e098b.png','2022-03-02 16:41:33',NULL,1,1),(-1157525502,'广东发鬼地方','给对方','泽学','1.8','/img/books/2a2f5dd9-74ec-43e6-9b0f-a720922e098b.png','2022-03-02 16:21:20','2022-03-02 17:15:11',1,0),(-423571455,'','','','','/img/books/2a2f5dd9-74ec-43e6-9b0f-a720922e098b.png','2022-03-02 16:41:25',NULL,1,1),(1220591617,'assfsadf','fsadf ','修真','9.9','/img/books/a8a0830c-3ab6-430b-b677-c29a54f60fdc.png','2022-03-02 14:50:18','2022-03-02 15:08:16',1,1),(1235647825,'万物有数学','小台','战争','9.9','/img/books/img_1.png','2022-02-25 19:17:09','2022-03-02 17:08:32',1,0),(1236547915,'山海经','小杨','哲学','9.9','/img/books/img_3.png','2022-02-25 19:17:11','2022-03-02 16:57:37',1,0),(1238758647,'笑对人生','小田','爱情','9.9','/img/books/img_4.png','2022-02-25 23:32:11',NULL,1,0),(1300250626,'双方都三','阿斯蒂芬打算','哲学','发生的','/img/books/152c08d3-899d-4afc-bd33-61539872cd4d.png','2022-03-02 15:50:12',NULL,1,1),(1325478954,'岁月静好|不忘初心','小方','哲学','9.9','/img/books/img_7.png','2022-02-25 23:32:23',NULL,1,0),(1354784135,'平凡的世界','小刘','哲学','9.9','/img/books/img.png','2022-02-25 19:17:00',NULL,1,0),(1487325124,'格局','小田','哲学','9.9','/img/books/3d318d79-f1e7-4899-abb6-83154ef6e94d.png','2022-02-25 23:32:21','2022-03-02 12:02:54',1,1),(1548975634,'君子志道','小国','哲学','9.9','/img/books/img_2.png','2022-02-25 19:17:13',NULL,1,0),(1568673793,'格局要大','志坚','哲学','9.99','/img/books/345884b1-04de-4de9-93ce-a8814ab84aad.png','2022-03-02 17:18:12',NULL,1,0),(1602228225,'杀神风','是的发送到','泽学','1.8','/img/books/26624bd2-d94d-475a-bb70-1cafd6c65861.png','2022-03-02 16:22:37',NULL,1,0),(1754991106,'范德萨发生的','阿斯蒂芬打算','哲学','9.3','/img/books/0c069e4a-4a1a-4882-b2e2-fda8d44e3de4.png','2022-03-02 15:45:30',NULL,1,1),(1895462157,'国宝档案','小完','哲学','9.9','/img/books/img_5.png','2022-02-25 23:32:13',NULL,1,0),(1987432156,'教育情调','小白','哲学','9.9','/img/books/img_6.png','2022-02-25 23:32:14',NULL,1,0);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int NOT NULL COMMENT '订单号id',
  `create_day` date DEFAULT NULL COMMENT '订单创建日期',
  `total` double DEFAULT NULL COMMENT '订单小计',
  `state` int DEFAULT NULL COMMENT '订单状态 0未发货 1已发货 2已收货未付款 3已付款',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `tel` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `order` */

insert  into `order`(`id`,`create_day`,`total`,`state`,`name`,`address`,`tel`) values (1,'2022-03-02',9.9,1,'张三','武汉','13971216623');

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `persistent_logins` */

insert  into `persistent_logins`(`username`,`series`,`token`,`last_used`) values ('hehe','ArCzT4N3h5s7b44LSrh9Vw==','iRJh2LdGEnAo01bupDo27g==','2022-03-01 13:13:15');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `tel` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `level` int DEFAULT '1' COMMENT '用户级别',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '删除记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1400541039 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`,`tel`,`email`,`level`,`create_time`,`update_time`,`version`,`deleted`) values (-1979604991,'adminxxx','123456','打算','13547846654','2634068147@qq.com',1,'2022-02-28 21:33:24','2022-03-01 00:07:46',2,1),(-1837092862,'admin','123456','SaAS','13547846654','2634068147@qq.com',3,'2022-03-01 12:07:47','2022-03-02 16:41:14',1,0),(-1174347774,'adminqwe','123456','SaAS','13547846654','2634068147@qq.com',1,'2022-02-28 23:52:24','2022-03-01 00:04:13',2,0),(-239063038,'adminqwe','123456','SaAS','13547846654','2634068147@qq.com',1,'2022-03-01 12:08:19',NULL,1,1),(381689858,'hehe','123456','hehe','13547846654','hehe@qq.com',1,'2022-03-01 00:08:18','2022-03-01 23:35:54',1,0),(813719554,'13971216623','123456','小王','13547846654','2634068147@qq.com',1,'2022-02-28 22:01:00','2022-03-02 16:59:26',1,0),(1145139202,'admin张三','15615','SaAS','13971216623','2634068147@qq.com',1,'2022-03-01 12:54:33',NULL,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
