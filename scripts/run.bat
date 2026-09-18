@echo off
if not exist out mkdir out
javac -d out src\com\campus\expense\*.java
if errorlevel 1 exit /b 1
java -cp out com.campus.expense.Main
