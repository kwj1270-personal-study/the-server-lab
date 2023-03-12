CHANGE MASTER TO MASTER_HOST='db-default-master-1', MASTER_USER='defaultdb', MASTER_PASSWORD='password', MASTER_LOG_POS=0;

start slave;

show slave status\G;