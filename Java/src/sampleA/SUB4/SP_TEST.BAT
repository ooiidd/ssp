@echo off&setlocal
set base=%~dp0..\..
set libdir=%~dp0..\..\..\..\..\lib
set classpath=%base%;%libdir%\*

start java -classpath %classpath% sampleA.SUB4.RunManager %base%\sampleA\SUB4\Proxy-1.json
start java -classpath %classpath% sampleA.SUB4.RunManager %base%\sampleA\SUB4\Proxy-2.json
start java -classpath %classpath% sampleA.SUB4.RunManager %base%\sampleA\SUB4\Proxy-3.json
