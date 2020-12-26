FROM adoptopenjdk:14-jre-hotspot

#Create new directory inside fs
RUN mkdir /app

#Sets working directory
WORKDIR /app

#Adds target jar to working directory
ADD ./api/target/event-catalog-api-1.0.0-SNAPSHOT.jar /app

#Exposes specific port to the container interface
EXPOSE 8081

CMD ["java", "-jar", "event-catalog-api-1.0.0-SNAPSHOT.jar"]