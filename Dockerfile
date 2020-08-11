FROM openjdk:14-alpine
COPY build/libs/micronaut-example-named-datasource-*-all.jar micronaut-example-named-datasource.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut-example-named-datasource.jar"]