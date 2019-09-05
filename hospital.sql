/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 10.3.16-MariaDB : Database - hospital
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hospital` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `hospital`;

/*Table structure for table `center` */

DROP TABLE IF EXISTS `center`;

CREATE TABLE `center` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `name` char(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `center` */

insert  into `center`(`id`,`regDate`,`name`) values 
(1,'2019-09-05 20:03:38','소화기센터'),
(2,'2019-09-05 20:03:38','소아청소년센터'),
(3,'2019-09-05 20:03:38','뇌신경센터'),
(4,'2019-09-05 20:03:38','인공와우센터'),
(5,'2019-09-05 20:03:38','부정맥센터');

/*Table structure for table `counselreservation` */

DROP TABLE IF EXISTS `counselreservation`;

CREATE TABLE `counselreservation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `clientMemberId` int(10) unsigned NOT NULL,
  `staffScheduleId` int(10) unsigned NOT NULL,
  `body` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `counselreservation` */

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `name` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `centerId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dept` */

insert  into `dept`(`id`,`regDate`,`name`,`centerId`) values 
(1,'2019-09-05 20:03:38','소화기내과',1),
(2,'2019-09-05 20:03:38','소아외과',2),
(3,'2019-09-05 20:03:38','신경과',3),
(4,'2019-09-05 20:03:38','이비인후과',4),
(5,'2019-09-05 20:03:38','순환기내과',5);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `name` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `loginId` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `loginPw` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `emailAuthKey` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `emailAuthStatus` tinyint(1) unsigned NOT NULL DEFAULT 0,
  `delStatus` tinyint(1) unsigned NOT NULL DEFAULT 0,
  `permissionLevel` tinyint(1) unsigned NOT NULL DEFAULT 0,
  `staffId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`name`,`loginId`,`loginPw`,`email`,`emailAuthKey`,`emailAuthStatus`,`delStatus`,`permissionLevel`,`staffId`) values 
(1,'2019-09-05 20:07:30','엄홍길4','user4','user4','user1@naver.com','DWFREW4dewq',1,0,1,1),
(2,'2019-09-05 20:03:38','엄홍길','user1','user1','user1@naver.com','DWFREW4dewq',1,0,1,1),
(3,'2019-09-05 20:03:38','홍길동','user2','user2','user2@naver.com','DWFREW4deedd',1,0,0,0),
(4,'2019-09-05 20:32:29','관리자','aaaa','aaaa','admin@naver.com','DWFREW4addd',1,0,1,1),
(5,'2019-09-05 21:52:15','관리자2','aaaa2','aaaa2','admin@naver.com','DWFREW4addd',1,0,1,1);

/*Table structure for table `memberattr` */

DROP TABLE IF EXISTS `memberattr`;

CREATE TABLE `memberattr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL DEFAULT current_timestamp(),
  `memberId` int(10) unsigned NOT NULL,
  `attrName` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `attrVal` tinyblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `memberattr` */

insert  into `memberattr`(`id`,`regDate`,`memberId`,`attrName`,`attrVal`) values 
(1,'2019-09-05 20:32:45',4,'role','admin');

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `name` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `staffType` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `deptId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `staff` */

insert  into `staff`(`id`,`regDate`,`name`,`staffType`,`deptId`) values 
(1,'2019-09-05 20:03:38','백승운','의사',1),
(2,'2019-09-05 20:03:38','이석구','의사',2),
(3,'2019-09-05 20:03:38','정진상','의사',3),
(4,'2019-09-05 20:03:38','정원호','의사',4),
(5,'2019-09-05 20:03:38','김준수','의사',5);

/*Table structure for table `staffschedule` */

DROP TABLE IF EXISTS `staffschedule`;

CREATE TABLE `staffschedule` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `deptId` int(10) unsigned NOT NULL,
  `staffId` int(10) unsigned NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `scheduleType` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `relType` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `staffschedule` */

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `test` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
