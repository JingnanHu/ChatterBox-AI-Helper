# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER JingnanHu
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
COPY /ChatterBox-AI-Helper-interfaces/target/ChatterBox-AI-Helper-interfaces-1.0-SNAPSHOT.jar /app/
# 执行镜像；docker run -e PARAMS=" --chatbot-api.groupId=你的星球ID --chatbot-api.openAiKey=自行申请 --chatbot-api.cookie=登录cookie信息" -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
CMD ["sh", "-c", "java -jar $JAVA_OPTS /app/ChatterBox-AI-Helper-interfaces-1.0-SNAPSHOT.jar $PARAMS"]
