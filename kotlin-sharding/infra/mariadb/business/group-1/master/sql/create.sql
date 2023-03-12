CREATE DATABASE businessdb;

##create masteruser and grant privileges
grant all privileges on businessdb.* to 'businessdb'@'%' identified by 'password';

#replication
grant replication slave on *.* to 'businessdb'@'%';

## flushj
flush privileges;

## DDL
use businessdb;

-- defaultdb.`user` definition

CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `account` varchar(255) UNIQUE DEFAULT NULL,
                        `nickname` varchar(255) UNIQUE DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- defaultdb.`board` definition
CREATE TABLE `board` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `content` varchar(255) DEFAULT NULL,
                         `title` varchar(255) DEFAULT NULL,
                         `writer` bigint(20) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
