DROP DATABASE IF EXISTS hospital;

CREATE DATABASE hospital;

USE `hospital`;

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`name` CHAR(100) NOT NULL,
	loginId CHAR(100) NOT NULL,
	loginPw CHAR(100) NOT NULL,
	email CHAR(100) NOT NULL,
	emailAuthKey CHAR(100) NOT NULL,
	emailAuthStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
	delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
	permissionLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
	staffId INT(10) UNSIGNED NOT NULL, # 일반회원은 0
	PRIMARY KEY (id)
);



INSERT INTO `member` SET
regDate = NOW(),
`name` = '엄홍길',
loginId = 'user1',
loginPw = 'user1',
email = 'user1@naver.com',
emailAuthKey = 'DWFREW4dewq',
emailAuthStatus = 1,
delStatus = 0,
permissionLevel = 1,
staffId = 1;

INSERT INTO `member` SET
regDate = NOW(),
`name` = '홍길동',
loginId = 'user2',
loginPw = 'user2',
email = 'user2@naver.com',
emailAuthKey = 'DWFREW4deedd',
emailAuthStatus = 1,
delStatus = 0,
permissionLevel = 0,
staffId = 0;

SELECT * FROM MEMBER


CREATE TABLE counselReservation (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	clientMemberId INT(10) UNSIGNED NOT NULL,
	staffScheduleId INT(10) UNSIGNED NOT NULL,
	`body` TEXT NOT NULL,
	PRIMARY KEY(id)
);


CREATE TABLE `staff` (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`name` CHAR(100) NOT NULL,
	staffType CHAR(100) NOT NULL,
	`deptId` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO `staff` (regDate, `name`, `staffType`, `deptId` ) VALUES
(NOW(), '백승운', '의사', 1),
(NOW(), '이석구', '의사', 2),
(NOW(), '정진상', '의사', 3),
(NOW(), '정원호', '의사', 4),
(NOW(), '김준수', '의사', 5);

SELECT * FROM `staff`;


CREATE TABLE `dept` (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`name` CHAR(100) NOT NULL,
	centerId INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO `dept` (regDate, `name`, `centerId`) 
VALUES (NOW(), '소화기내과', 1),
(NOW(), '소아외과', 2),
(NOW(), '신경과', 3),
(NOW(), '이비인후과', 4),
(NOW(), '순환기내과', 5);

SELECT * FROM `dept`;


CREATE TABLE `center` (
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`name` CHAR(100) NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO `center` (regDate, `name`) VALUES
(NOW(), '소화기센터'),
(NOW(), '소아청소년센터'),
(NOW(), '뇌신경센터'),
(NOW(), '인공와우센터'),
(NOW(), '부정맥센터');

SELECT * FROM `center`;


CREATE TABLE `staffSchedule`(
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, # 1
	`regDate` DATETIME NOT NULL, # 2019-08-08 12:56:00
	`deptId` INT(10) UNSIGNED NOT NULL, # 1
	`staffId` INT(10) UNSIGNED NOT NULL, # 23
	`startTime` DATETIME NOT NULL, # 2019-08-09 13:30:00
	`endTime` DATETIME NOT NULL, # 2019-08-09 15:30:00
	`scheduleType` CHAR(100) NOT NULL, # counsel
	`relType` CHAR(100) NOT NULL, # 'counselReservation'
	`relId` INT(10) UNSIGNED NOT NULL, # 99
	PRIMARY KEY(id)
);

SHOW TABLES;