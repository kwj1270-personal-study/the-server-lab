CHANGE MASTER TO MASTER_HOST='master-node-1-rw', MASTER_USER='queryoffloading', MASTER_PASSWORD='password', MASTER_LOG_POS=0;

start slave;

show slave status\G;
