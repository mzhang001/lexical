#!/bin/bash
#java bin/ExecuteMe > express.txt

javac -sourcepath src/ src/ExecuteMe.java 
jar cf src/lexical.jar src/*.class
java -classpath src/ ExecuteMe impress > impress.txt