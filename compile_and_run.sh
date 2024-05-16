#!/usr/bin/env bash

clear;

mvn clean install || exit 1;

java -jar ./target/$(ls target | grep .jar) . || exit 1;
exit 0;

