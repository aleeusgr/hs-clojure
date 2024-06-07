FROM openjdk:8u181-alpine3.8 
WORKDIR / 
COPY target/hs-clojure-0.1.0-SNAPSHOT.jar hs-clojure-0.1.0-SNAPSHOT.jar 
EXPOSE 3000 
CMD java -jar hs-clojure-0.1.0-SNAPSHOT.jar
