FROM openjdk:8-jre-alpine
ARG jar
VOLUME /tmp
COPY $jar mocksvc.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /mocksvc.jar" ]
