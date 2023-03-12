CREATE DATABASE defaultdb;

##create masteruser and grant privileges
grant all privileges on boarddb.* to 'defaultdb'@'%' identified by 'password';

#replication
grant replication slave on *.* to 'defaultdb'@'%';

## flushj
flush privileges;