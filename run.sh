! [ -e "target/log4j.properties" ] && cp src/main/resources/log4j.properties  target/

java -Dlog4j.configuration=file:target/log4j.properties -jar target/homework-0.0.1-SNAPSHOT.jar $1
