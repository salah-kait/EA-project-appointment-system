#!/usr/bin/env bash
cd /home/ubuntu/server
sudo java -jar -Dspring.profiles.active=prod *.jar > /dev/null 2> /dev/null < /dev/null &