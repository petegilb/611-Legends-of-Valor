#!/bin/bash
cd ./
find . -name "*.class" -type f -delete
find . -name "*.java" > sources.txt
# if first running : mkdir classes
mkdir classes
javac -d classes -cp classes @sources.txt
java -cp classes lmh.Play