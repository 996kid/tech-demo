#!/usr/bin/env bash
if [ $# != 1 ]; then
echo "./nacos-com.eastwood.config.sh nacosIp"
exit -1
fi

nacosIp=$1
echo "set nacosIp=$nacosIp"
error=0

for line in $(cat nacos-com.eastwood.config.txt)

do

key=${line%%=*}
value=${line##*=}
echo "\r\n set "${key}" = "${value}

result=`curl -X POST "http://$nacosIp:8848/nacos/v1/cs/configs?dataId=$key&group=SEATA_GROUP&content=$value"`

if [ "$result"x == "true"x ]; then

  echo "\033[42;37m $result \033[0m"

else

  echo "\033[41;37 $result \033[0m"
  let error++

fi

done


if [ $error -eq 0 ]; then

echo  "\r\n\033[42;37m init nacos com.eastwood.config finished, please start seata-server. \033[0m"

else

echo  "\r\n\033[41;33m init nacos com.eastwood.config fail. \033[0m"

fi