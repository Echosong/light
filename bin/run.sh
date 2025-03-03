#!/bin/bash

APP_NAME=Light.Admin-0.0.1-SNAPSHOT
JAR_NAME=$APP_NAME\.jar

java -jar -Xms500m -Xmx1g -XX:MetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps $JAR_NAME
