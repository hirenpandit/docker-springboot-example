# docker-springboot-example
Springboot as backend service, MySQL as databse and Angular5 as UI 


Create springboot application with Mysql as databse which is being accessed with springdata JPA. Springboot services are accessible using RESTful API form Angular 5 UI Application. These application run on Docker as docker images. We need to create three docker images 
1). Springboot applciation running on tomcat 
2). MySQL image
3). Angular application image.
4). Run application on Docker!


1. Springboot Application

1.1
- Create springboot applciation using https://start.spring.io. Provide Artifact and Group (For this example I've given Artifact: spring-docker-service and Group: com.example). Add springboot-web depndency form depndency search textfield. 
- Generate project and unzip. Import project in your favourite IDE.
- Add below additional depndencies.

since we are going to use MySQL add depndency for it.

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency> 
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>5.2.4.Final</version>
    </dependency>
        <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    
    
1.2 Add following configuration properties in application.properties file of springboot application.

    server.contextPath=/spring-docker-service
    spring.datasource.platform=mysql 
    spring.jpa.hibernate.ddl-auto=create
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=root
    spring.datasource.password=root
    
 You can find springboot application under spring-docker-service folder of this repository.
 
 

