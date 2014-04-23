#!/bin/bash
if [ $# -ne "1" ]; then
   echo "./keep_run.sh 'command' "
   exit 0
fi
logfile="skr.log"
counter=0
while [ $counter -lt 100 ]; do
   time=$(date)
   echo "$time Started $counter" >> skr.log
   $1
   time=$(date)
   echo "$time Stopped $counter" >> skr.log
   sleep 5
   let counter++
done
