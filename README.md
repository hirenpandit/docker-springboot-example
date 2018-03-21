# docker-springboot-example
Springboot as backend service, MySQL as databse and Angular5 as UI 


Create springboot application with Mysql as databse which is being accessed with springdata JPA. Springboot services are accessible using RESTful API form Angular 5 UI Application. These application run on Docker as docker images. We need to create three docker images 

1). Springboot applciation running on tomcat 
2). MySQL image
3). Angular application image.
4). Run whole application on Docker using docker-compose !


1. Springboot Application

1.1

- Create springboot applciation using https://start.spring.io. Provide Artifact and Group (For this example I've given                 Artifact: spring-docker-service and Group: com.example). Add springboot-web depndency form depndency search textfield. 
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
    
    
1.2 
    Add following configuration properties in application.properties file of springboot application.

    server.contextPath=/spring-docker-service
    spring.datasource.platform=mysql 
    spring.jpa.hibernate.ddl-auto=create
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=root
    spring.datasource.password=root
    
 You can find springboot application under spring-docker-service folder of this repository. The application has required `controller`, `service` and `repository` to perform CRUD operation for `User` entity class.
 
 1.3
    Now, Create Dockerfile for our springboot application as below. Dockerfile for the springboot applciation is found in repository named `Dockerfile.spring`

        FROM tomcat:alpine
        COPY *.war /usr/local/tomcat/webapps/
        COPY tomcat-users.xml /usr/local/tomcat/conf/
        COPY manager.xml /usr/local/tomcat/conf/Catalina/localhost/
        EXPOSE 8080
 
This Dockerfile simply gets tomcat:alpine from dockerhub repository and copies our springboot .war file (spring-docker-service.war) to its webapp folder. Moreover to manage tomcat I've also enabled tomcat manager ui.

1.4
    Use below commands to build and run Dockerfile.spring. 
                                           
    docker build . -t spring-docker-service -f Dockerfile.spring
    docker run -p 8080:8080 spring-docker-service
    
2. Create MySQL Image:

2.1
    MySQL image you can direcly run using docker command
    
        docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
        
3. Cretae Angular application Image: 

3.1
    Angular application is create with angular-cli with angular 5 version. You can find guidline to create angular 5 application easily. I will skip the part how to create angular application and jump right into creating image for our angular application.

3.2
    Once we have created angular application we are now going to create Dockerile for this application. Let's call this `Dockerfile.angular`. this dockerfile can be found in repository.
    
    
        # Create image based on the official Node 6 image from dockerhub
        FROM node:6

        # Create a directory where our app will be placed
        RUN mkdir -p /usr/src/app

        # Change directory so that our commands run inside this new directory
        WORKDIR /usr/src/app

        # Copy dependency definitions
        COPY package.json /usr/src/app

        # Install dependecies
        RUN npm install

        # Get all the code needed to run the app
        COPY . /usr/src/app

        # Expose the port the app runs in
        EXPOSE 4200

        # Serve the app
        CMD ["npm", "start"]
    

4. Run whole application on Docker using docker-compose !
    
4.1
    To run the whole application on Docker using docker-compose we need to make a few changes in our application. In spring application we need to create new spring profile which we will use in docker-compose.yml file. I have created spring profile named container, for that I have created new application.properties file named `application-container.properties`. This file have configuration to use MySQL service (which I have named `docker-mysql`) runnning in docker.
    
    spring.datasource.url=jdbc:mysql://docker-mysql:3306/test
    
 4.2 
    Now we will create docker-compose.yml file which will creat all the mentioned docker images and run these images in docker.
    
    version: '2'
    services: 
      docker-mysql:
        image: mysql:latest
        environment:
          - MYSQL_ROOT_PASSWORD=root
          - MYSQL_DATABASE=test
          - MYSQL_PASSWORD=root
      spring-docker-service:
        image: spring-docker-service
        build:
          context: ./spring
          dockerfile: ./Dockerfile.spring
        depends_on:
          - docker-mysql
        ports:
          - 7070:8080
        environment:
          - CATALINA_OPTS=-Dspring.profiles.active=container
          - DATABASE_HOST=docker-mysql
          - DATABASE_USER=root
          - DATABASE_PASSWORD=root
          - DATABASE_NAME=test  
          - DATABASE_PORT=3306
      angular-docker-service:
        image: angular-docker-service
        build:
          context: ./angular
          dockerfile: ./Dockerfile.angular
        ports:
          - 4200:4200
    
    
4.2
    Run command `docker-compose up`
