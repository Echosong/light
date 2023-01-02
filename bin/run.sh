#!/bin/bash

APP_NAME=light-0.0.1-SNAPSHOT
JAR_NAME=$APP_NAME\.jar

java -jar -Xms2g -Xmx2g -XX:MetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps $JAR_NAME
