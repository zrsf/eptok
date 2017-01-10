CREATE DATABASE TZPT
CHARACTER SET 'utf8'
COLLATE 'utf8_bin';

USE TZPT;

DROP TABLE IF EXISTS d_user_info; 
CREATE TABLE
    d_user_info
    (
        userCode VARCHAR(20) NOT NULL COMMENT '登陆用户名',
        userName VARCHAR(32) NOT NULL COMMENT '用户名称',
        loginPwd VARCHAR(50) COMMENT 'USERCODE + LOGINPWD MD5加密',
        state CHAR(2) NOT NULL COMMENT '用户状态，00正常，01冻结，04锁定',
        email VARCHAR(60) COMMENT '邮件地址',
        mobile VARCHAR(20) COMMENT '手机号码',
        officeTel VARCHAR(20) COMMENT '办公电话',
        lastLoginTime DATETIME COMMENT '上次登录时间',
        creatorId VARCHAR(20) COMMENT '创建人ID',
        createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        creatorName VARCHAR(50) COMMENT '创建人名称',
        pwdInvalid VARCHAR(8) COMMENT '最后一次修改密码的日期，日期为空时为第一次登录',
        pwdErrorNum int DEFAULT 0 COMMENT '密码错误次数，登录成功后置为0',
        pwdErrorTime DATETIME COMMENT '密码输入错误的时间',
        secondPwd VARCHAR(50) COMMENT '二级密码',
        parentUsercode VARCHAR(20) COMMENT '上级用户编码',
        loginIp VARCHAR(40) COMMENT '最后登录ip',
        loginTime VARCHAR(40) COMMENT '最后登录时间',
        loginMac VARCHAR(40) COMMENT '最后登录MAC地址',
        dayPwderrorNum int DEFAULT 0 COMMENT '当天内密码输入错误次数',
        CONSTRAINT PK_dUserInfo PRIMARY KEY (userCode)
    )comment='前台用户表';


DROP TABLE IF EXISTS Categorys;  
CREATE TABLE Categorys(
     typeNo varchar(20) NOT NULL PRIMARY KEY COMMENT '类型编号',
     typeName varchar(100) NOT NULL COMMENT '类型名称',
     district varchar(50) COMMENT '所属区域编码',
     descri varchar(200) COMMENT '备注信息',
     recordStatus varchar(10) NOT NULL DEFAULT '01' COMMENT '记录状态',
     createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     lastUpdateTime DATETIME COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS Applications;  
CREATE TABLE Applications(
     appNo varchar(20) NOT NULL PRIMARY KEY COMMENT '应用编号',
     appName varchar(100) NOT NULL COMMENT '应用名称',
     author varchar(100) COMMENT '开发商编号',
     descri varchar(200) COMMENT '应用备注信息',
     isAvailable int default 0 COMMENT '应用是否有效',
     createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     lastUpdateTime DATETIME COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS UploadRecords;  
CREATE TABLE UploadRecords(
     appNo varchar(20) NOT NULL COMMENT '应用编号',
     ver varchar(20) NOT NULL COMMENT '版本号',
     url varchar(300) NOT NULL COMMENT '上传到的URL地址',
     filePath varchar(300) NOT NULL COMMENT '物理路径',
     enabled int NOT NULL DEFAULT 0 COMMENT '是否启用',
     uploadDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
     uploadUser varchar(50) NOT NULL COMMENT '上传人',
     lastUpdateTime DATETIME COMMENT '最后更新时间',
     PRIMARY KEY(appNo,ver),
     FOREIGN KEY(appNo)	REFERENCES Applications(appNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS AppCategory;  
CREATE TABLE AppCategory(
     appNo varchar(20) NOT NULL COMMENT '应用编号',
     typeNo varchar(100) NOT NULL COMMENT '类型编号',
     recordStatus varchar(10) NOT NULL DEFAULT '01' COMMENT '记录状态',
     createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     lastUpdateTime DATETIME COMMENT '最后更新时间',
     PRIMARY KEY(appNo,typeNo),
     FOREIGN KEY(appNo)	REFERENCES Applications(appNo),
     FOREIGN KEY(typeNo) REFERENCES Categorys(typeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS SysUser;  
CREATE TABLE SysUser(
	 userID int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
     userName varchar(20) NOT NULL COMMENT '用户名',
     userPassWord varchar(20) NOT NULL COMMENT '用户密码',
     recordStatus varchar(10) NOT NULL DEFAULT '01' COMMENT '记录状态',
     createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     lastUpdateTime DATETIME COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into SysUser values(null,'admin','admin','01',null,null);

DROP TABLE IF EXISTS UpgradeLog;  
CREATE TABLE UpgradeLog(
	 logID int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
     appNo varchar(20) NOT NULL COMMENT '应用编号',
     curVer varchar(20) NOT NULL COMMENT '应用当前版本号',
     newVer varchar(20) NOT NULL COMMENT '升级的新版本号',
     taxNo varchar(30) NOT NULL COMMENT '纳税人标识',
     logLevel int NOT NULL default 2 COMMENT '日志级别[0：信息；1：警告；2：普通错误；3：数据库错误]',
     logs text COMMENT '日志详细信息',
     recordStatus varchar(10) NOT NULL DEFAULT '01' COMMENT '记录状态',
     createTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     lastUpdateTime DATETIME COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

