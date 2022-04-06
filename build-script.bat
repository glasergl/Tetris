@echo off

echo delete old output
del /s /q output\*.*

echo\
echo build modules
echo - Engine:
call mvn clean install -f source/TetrisEngine/
echo - UI:
call mvn package -f source/TetrisUI/

echo\
echo create .exe
call launch4jc source\TetrisUI\launch4j_configuration.xml

echo\
echo build finished.

pause