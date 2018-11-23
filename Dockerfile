FROM openjdk:8-jre-alpine
ARG jar
VOLUME /tmp
COPY $jar jobsvc.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /jobsvc.jar" ]
