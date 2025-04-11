#!/bin/bash
if [ "$1" == "local" ]; then
  cp .env.local .env
  echo "Usando configuração local"
elif [ "$1" == "container" ]; then
  cp .env.container .env
  echo "Usando configuração do container"
else
  echo "Uso: ./start.sh [local|container]"
fi