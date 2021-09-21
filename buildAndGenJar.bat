@echo off

call "%~dp0\gradlew" assembleRelease

del "%~dp0\custom_spider.jar"

call "%~dp0\jar\genJar.bat"

pause