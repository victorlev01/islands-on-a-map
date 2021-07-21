FROM openjdk:8-jre-alpine
ADD build/libs/islands-on-a-map.jar /opt/app.jar
CMD java $JAVA_EXT -jar /opt/app.jar -Dfile.encoding=UTF-8
