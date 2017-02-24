#### Switch to 'examplejavaagent' directory
$ mvn clean package

#### Switch to 'exampleappusingjavaagent' directory
$ mvn clean package

#### Verify everything was working correct so far
$ java -jar target/javaagent-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Content size: 10477
Content size: 290

#### Now attach agent to the app
$ java -javaagent:../examplejavaagent/target/javaagent-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar -jar target/javaagent-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
