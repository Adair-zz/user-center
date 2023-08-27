# database init
# @author <a href="https://github.com/Adair-zz">Zheng Zhang</a>

-- create database
create database if not exists zheng;

use zheng;

-- user table
create table if not exists user
(
    id           bigint                                 auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment 'account',
    userPassword varchar(512)                           not null comment 'password',
    userName     varchar(256)                           null comment 'user name',
    userAvatar   varchar(1024)                          null comment 'user avatar',
    userRole     varchar(256) default 'user'            not null comment 'user role: user/admin',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment 'create time',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'update time',
    isDelete     tinyint      default 0                 not null comment 'is delete',
    index idx_userAccount (userAccount)
) comment 'user' collate = utf8mb4_unicode_ci;