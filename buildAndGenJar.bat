@echo on

call "%~dp0\gradlew" assembleRelease --no-daemon

call "%~dp0\jar\genJar.bat" %1

