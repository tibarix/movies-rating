FROM java:8-jdk-alpine

COPY ./target/config-server-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch config-server-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar","--EUREKA_SERVER_URL=http://192.168.43.160:4000/eureka/"]
