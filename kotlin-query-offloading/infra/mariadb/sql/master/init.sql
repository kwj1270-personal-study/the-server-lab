CREATE DATABASE queryoffloading;

##create masteruser and grant privileges
grant all privileges on queryoffloading.* to 'queryoffloading'@'%' identified by 'password';

#replication
grant replication slave on *.* to 'queryoffloading'@'%';

## flushj
flush privileges;
