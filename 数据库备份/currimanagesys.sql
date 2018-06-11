/*
Navicat MySQL Data Transfer

Source Server         : root_01
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : currimanagesys

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-11 00:17:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classId` varchar(36) NOT NULL,
  `className` varchar(16) DEFAULT NULL,
  `enrollYear` smallint(6) DEFAULT NULL,
  `majorId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('03071ef9c9be4c76b529d92633187843', '移动4班', '2016', 'd16de8e74a4048db95a8692e9bf7dd57');
INSERT INTO `classes` VALUES ('60a79b249f5c4c9387b6ca1f69d62998', '移动3班', '2016', 'd16de8e74a4048db95a8692e9bf7dd57');
INSERT INTO `classes` VALUES ('d9787b28775149f9b52cb4719e0c6876', '移动5班', '2016', 'd16de8e74a4048db95a8692e9bf7dd57');

-- ----------------------------
-- Table structure for class_student
-- ----------------------------
DROP TABLE IF EXISTS `class_student`;
CREATE TABLE `class_student` (
  `classStudentId` varchar(36) NOT NULL,
  `courseClassId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  `gpaScore` tinyint(4) DEFAULT NULL,
  `paperScore` tinyint(4) DEFAULT NULL,
  `practiceScore` tinyint(4) DEFAULT NULL,
  `score` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`classStudentId`),
  KEY `courseClassId_idx` (`courseClassId`),
  KEY `studentId_idx` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_student
-- ----------------------------
INSERT INTO `class_student` VALUES ('34310b67454549e4a4e98c04b11e45fd', '34310b67454549e4a4e98c04b11e45fd', '34310b67454549e4a4e98c04b11e45fd', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` varchar(36) NOT NULL,
  `courseNum` char(6) DEFAULT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `enName` varchar(100) DEFAULT NULL,
  `coursecategoryId` varchar(36) DEFAULT NULL,
  `score` tinyint(3) DEFAULT NULL,
  `cHour` tinyint(3) DEFAULT NULL,
  `lHour` tinyint(3) DEFAULT NULL,
  `tcHour` tinyint(3) DEFAULT NULL,
  `tlHour` smallint(5) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `test1` varchar(45) DEFAULT NULL,
  `test2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('c0f244dc192d46c9a98cb58ffa920a2a', '撒打算', '爱迪生', '撒', 'de452685728f4f5cac40ae6d9fc6156d', '2', '2', '2', '2', '2', '2018-06-10 01:52:50', null, null);

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `courseCategoryId` varchar(36) NOT NULL,
  `courseCategoryName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`courseCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_category
-- ----------------------------
INSERT INTO `course_category` VALUES ('6d23917f34654508966351a43c2bcf05', '公共选修课', '2018-06-10 01:14:07');
INSERT INTO `course_category` VALUES ('de452685728f4f5cac40ae6d9fc6156d', '专业课程', '2018-06-10 01:12:40');

-- ----------------------------
-- Table structure for course_class
-- ----------------------------
DROP TABLE IF EXISTS `course_class`;
CREATE TABLE `course_class` (
  `courseClassId` varchar(36) NOT NULL,
  `semesterId` varchar(36) DEFAULT NULL,
  `courseId` varchar(36) DEFAULT NULL,
  `maxClassSize` int(11) DEFAULT NULL,
  `teacherId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`courseClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_class
-- ----------------------------

-- ----------------------------
-- Table structure for curriculum
-- ----------------------------
DROP TABLE IF EXISTS `curriculum`;
CREATE TABLE `curriculum` (
  `curriculumId` varchar(36) NOT NULL,
  `educationProgramId` varchar(36) DEFAULT NULL,
  `courseId` varchar(36) DEFAULT NULL,
  `courseCategoryId` varchar(36) DEFAULT NULL,
  `semester` tinyint(4) DEFAULT NULL,
  `isDegree` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`curriculumId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of curriculum
-- ----------------------------

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `dictionaryId` varchar(36) NOT NULL,
  `dictypeId` varchar(36) DEFAULT NULL,
  `dictionaryName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dictionaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('29e6bf3b10c3419683b4f932e436b133', '9982b71132394749a1fbf235c66add5e', '发送到是');
INSERT INTO `dictionary` VALUES ('383bcf1a296a42b587ed807be2062867', '9982b71132394749a1fbf235c66add5e', '爱萨广东佛山');
INSERT INTO `dictionary` VALUES ('4de36843cf444726bffd3f345eb61693', 'b94a9bd81f8549e2b0d55dd25b800391', '程序');
INSERT INTO `dictionary` VALUES ('5043a19925f74011b4395c18ff200d45', '2908e7dec6d24d05adb2c0c4aed85e50', '萨达');
INSERT INTO `dictionary` VALUES ('50a3292bd276473ab8498399f32408b1', 'a43fe565963c4d93a2c274afe4768570', '发发呆');
INSERT INTO `dictionary` VALUES ('5d58679c192a46ffb0766e6682a09b36', '766848018db34e908d8a90c6dfad2792', '我去二无群');
INSERT INTO `dictionary` VALUES ('5ed6b8f5f9a7492cbf463139ac622603', 'b94a9bd81f8549e2b0d55dd25b800391', '认为');
INSERT INTO `dictionary` VALUES ('6fae5ff428aa4999a427e25452f9f1a7', '2908e7dec6d24d05adb2c0c4aed85e50', '2016级');
INSERT INTO `dictionary` VALUES ('770fe73099f24dd3ae5d75395ee747e4', 'a43fe565963c4d93a2c274afe4768570', '爱迪生');
INSERT INTO `dictionary` VALUES ('abca25fc28ef46898a72e69b8de53509', '9982b71132394749a1fbf235c66add5e', '121');
INSERT INTO `dictionary` VALUES ('be014c4065d24dfabe6754a79e7aeee2', '766848018db34e908d8a90c6dfad2792', '阿萨德');
INSERT INTO `dictionary` VALUES ('e2031629b2c9478cafabfdcf39fb4774', '2908e7dec6d24d05adb2c0c4aed85e50', '2017级');

-- ----------------------------
-- Table structure for dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_type`;
CREATE TABLE `dictionary_type` (
  `dictypeId` varchar(36) NOT NULL,
  `dictypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dictypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary_type
-- ----------------------------
INSERT INTO `dictionary_type` VALUES ('2908e7dec6d24d05adb2c0c4aed85e50', '年级');
INSERT INTO `dictionary_type` VALUES ('5bc2f18234c64a408b64f4e06384a339', '随便');
INSERT INTO `dictionary_type` VALUES ('766848018db34e908d8a90c6dfad2792', '傻逼');
INSERT INTO `dictionary_type` VALUES ('9982b71132394749a1fbf235c66add5e', '倪涵');
INSERT INTO `dictionary_type` VALUES ('a43fe565963c4d93a2c274afe4768570', '师生');
INSERT INTO `dictionary_type` VALUES ('b94a9bd81f8549e2b0d55dd25b800391', '班级');

-- ----------------------------
-- Table structure for education_program
-- ----------------------------
DROP TABLE IF EXISTS `education_program`;
CREATE TABLE `education_program` (
  `educationProgramId` varchar(36) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `objective` varchar(2000) DEFAULT NULL,
  `specification` varchar(5000) DEFAULT NULL,
  `duration` tinyint(4) DEFAULT NULL,
  `degree` varchar(20) DEFAULT NULL,
  `minCredit` int(11) DEFAULT NULL,
  `publishYear` smallint(6) DEFAULT NULL,
  `majorId` varchar(36) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`educationProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education_program
-- ----------------------------
INSERT INTO `education_program` VALUES ('5a2bf30e541c4f9791bd8d3730c2acbe', '软件学院2016年培养方案', '培养一堆程序yuan', '个的撒噶示范岗啥的故事dfsdfasddsdssadsaddssdsdsddsdsfsdsd', '4', '学士学位', '160', '2016', 'd16de8e74a4048db95a8692e9bf7dd57', '2018-06-10 14:43:45');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `majorId` varchar(36) NOT NULL,
  `majorName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`majorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('d16de8e74a4048db95a8692e9bf7dd57', '软件工程', '2018-06-10 11:21:20');
INSERT INTO `major` VALUES ('e77fc92d31e345f5b540b46de3815c9e', '文学院', '2018-06-10 11:21:30');

-- ----------------------------
-- Table structure for plan_study_course
-- ----------------------------
DROP TABLE IF EXISTS `plan_study_course`;
CREATE TABLE `plan_study_course` (
  `planStudyCourseId` varchar(36) NOT NULL,
  `courseId` varchar(36) DEFAULT NULL,
  `semesterId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`planStudyCourseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan_study_course
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resourceId` varchar(36) NOT NULL,
  `courseId` varchar(36) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `resourceUrl` varchar(500) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `test1` varchar(45) DEFAULT NULL,
  `test2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `roleId` varchar(36) NOT NULL,
  `roleName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleId_UNIQUE` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('5c9ac4be42bc40cab5690496cfe3c161', '教工', '2018-06-08 14:18:41');
INSERT INTO `roleinfo` VALUES ('7e53896234dd47479355969a31ee4398', '管理员', '2018-06-08 14:16:54');
INSERT INTO `roleinfo` VALUES ('c7f02d4b156b4aceb9e004361139bb1a', '学生', '2018-06-08 14:19:40');

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester` (
  `semesterId` varchar(36) NOT NULL,
  `semesterName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`semesterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of semester
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` varchar(36) NOT NULL,
  `studentScode` char(12) DEFAULT NULL,
  `studentName` varchar(45) DEFAULT NULL,
  `studentGender` char(2) DEFAULT NULL,
  `studentPhoto` varchar(100) DEFAULT NULL,
  `classId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` varchar(36) NOT NULL,
  `teacherTcode` char(6) DEFAULT NULL,
  `teacherName` varchar(45) DEFAULT NULL,
  `teacherGender` char(2) DEFAULT NULL,
  `teacherDegree` varchar(10) DEFAULT NULL,
  `teacherTitle` varchar(10) DEFAULT NULL,
  `teacherIntro` varchar(2000) DEFAULT NULL,
  `teacherPhoto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userInfoId` varchar(36) NOT NULL,
  `userRole` varchar(36) NOT NULL,
  `userID` varchar(45) NOT NULL,
  `userPwd` varchar(45) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('3631d4b856264a9aa918e963f7a0d544', '7e53896234dd47479355969a31ee4398', 'admin', '123456', '2018-06-08 13:33:24');
