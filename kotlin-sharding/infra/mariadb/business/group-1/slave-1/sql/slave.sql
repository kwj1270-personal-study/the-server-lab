CHANGE MASTER TO MASTER_HOST='db-business-master-1', MASTER_USER='businessdb', MASTER_PASSWORD='password', MASTER_LOG_POS=0;

start slave;

show slave status\G;
