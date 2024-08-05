FROM openjdk:11-jre-slim

ENV JAVA_OPTS="-Dcom.sun.management.jmxremote \
               -Dcom.sun.management.jmxremote.port=1099 \
               -Dcom.sun.management.jmxremote.authenticate=false \
               -Dcom.sun.management.jmxremote.ssl=false"

COPY fatApp.jar fatApp.jar

CMD ["sh", "-c", "java $JAVA_OPTS -jar fatApp.jar"]