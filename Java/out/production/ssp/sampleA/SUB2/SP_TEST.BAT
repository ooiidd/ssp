@echo off&setlocal
set base=%~dp0..\..\..\out\production\ssp
set classpath=%base%
echo %classpath%

java -classpath %classpath% sampleA.SUB2.RunManager %1 %2 %3 %4 %5 %6 %7 %8 %9
