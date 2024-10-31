#!/bin/bash

JAR_FILE="LifeSimilation.jar"

echo "Запуск приложения..."
java -jar "$JAR_FILE"

if [ $? -ne 0 ]; then
    echo "Ошибка при запуске JAR-файла."
    exit 1
fi

