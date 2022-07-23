-- 1.创建Schema
CREATE SCHEMA IF NOT EXISTS MODULE;
SET SCHEMA MODULE;

-- 2.创建用户信息表
CREATE TABLE IF NOT EXISTS MODULE.SYS_USER
(
    id          VARCHAR2(32) not null,
    username    VARCHAR2(32) not null,
    password    VARCHAR2(64) not null,
    create_user VARCHAR2(32) not null,
    create_time TIMESTAMP(6) not null,
    update_user VARCHAR2(32),
    update_time TIMESTAMP(6)
);

-- 3.创建机构信息表
CREATE TABLE IF NOT EXISTS MODULE.SYS_ORG
(
    id                VARCHAR2(32) not null,
    org_code          VARCHAR2(20) not null,
    org_name          VARCHAR2(100) not null,
    create_user       VARCHAR2(32) not null,
    create_time       TIMESTAMP(6) not null,
    update_user       VARCHAR2(32),
    update_time       TIMESTAMP(6)
);

-- 4.创建操作日志表
CREATE TABLE IF NOT EXISTS MODULE.SYS_OPERATE_LOG
(
    id           VARCHAR2(32) not null,
    log_type     VARCHAR2(64) not null,
    ip           VARCHAR2(64) not null,
    result       NUMBER(1) not null,
    create_user VARCHAR2(32) not null,
    create_time TIMESTAMP(6) not null
);