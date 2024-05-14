FROM openjdk:8-alpine

COPY target/uberjar/hs-clojure.jar /hs-clojure/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/hs-clojure/app.jar"]
