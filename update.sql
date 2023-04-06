DROP TABLE IF EXISTS likeit;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS user;



create table user(
userNo int auto_increment primary key,
username varchar(30) DEFAULT 'Anonymous',
nickname varchar(30) DEFAULT 'Anonymous',
email varchar(30) NOT NULL ,
password varchar(30) not null,
sido_code int Not null,
gugun_code int not null,
address varchar(30) not null

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table board(

articleNo int auto_increment primary key,
hit int default 1,
email varchar(30) not null,
regDate timestamp not null default current_timestamp,
title varchar(30) not null,
content varchar(3000) not null

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table likeit(
userNo int,
like_time timestamp not null default current_timestamp,
contend_id int

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;