#!/bin/bash
source ~/.bash_profile && \
cd /usr/local/Cellar/hadoop-2.9.0/sbin/ &&\
./stop-all.sh && \
./start-all.sh &&\
hadoop dfsadmin -safemode leave
