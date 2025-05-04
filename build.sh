#!/bin/bash
mkdir -p build
javac -classpath $(hadoop classpath) -d build src/*.java src/util/*.java
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi
jar -cvf ALSDriver.jar -C build .
if [ $? -ne 0 ]; then
    echo "JAR creation failed."
    exit 1
fi
