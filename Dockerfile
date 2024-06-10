FROM openjdk:8u181-alpine3.8 
WORKDIR / 
COPY target/hs-clojure-standalone.jar hs-clojure 
EXPOSE 3000 
CMD java -jar hs-clojure
