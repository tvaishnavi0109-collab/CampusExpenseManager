#!/bin/sh
set -e
mkdir -p out
javac -d out src/com/campus/expense/*.java
java -cp out com.campus.expense.Main
