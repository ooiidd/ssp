@echo off&setlocal
set base=%~dp0..\..
set classpath=%base%

java -classpath %classpath% sampleA.SUB1.RunManager %1 %2 %3 %4 %5 %6 %7 %8 %9
