FROM java:8
#
#VOLUME /tmp
#ARG JAR_FILE
# jar file is added to the container
# ADD ${JAR_FILE} target/Assessment-0.0.1-SNAPSHOT.jar
ADD target/Assessment-0.0.1-SNAPSHOT.jar /app.jar
# jar file is executed in the entrypoint:
# property pointing to urandom is to reduce tomcat startup time
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","Assessment-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]