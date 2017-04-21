#!/bin/sh
docker build -t superlogger-logstash .
docker run --net=host superlogger-logstash
