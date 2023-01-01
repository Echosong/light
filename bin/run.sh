#!/bin/bash

APP_NAME=Light-0.0.1-SNAPSHOT
JAR_NAME=$APP_NAME\.jar

java -jar -Xms2g -Xmx2g -XX:MetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps $JAR_NAME
