#!/bin/bash

# 停止脚本时抛出错误
set -e

# 构建 Docker 镜像
docker build -f Dockerfile -t chatterbox-api:1.0 .

echo "Docker 镜像构建完成：chatterbox-api:1.0"