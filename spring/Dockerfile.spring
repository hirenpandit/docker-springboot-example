#FROM java:8
#COPY spring-docker-0.0.1-SNAPSHOT.jar spring-docker.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "spring-docker.jar"]


FROM tomcat:alpine
COPY *.war /usr/local/tomcat/webapps/
COPY tomcat-users.xml /usr/local/tomcat/conf/
COPY manager.xml /usr/local/tomcat/conf/Catalina/localhost/
#EXPOSE 8080
