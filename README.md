# Pocketmath
The pocketmath assignment demonstrates the usage of Pocketmath RESTful API. The solution uses Jersey Client as an HTTP client, Jackson JSON for serialization purposes and Guice for dependency injection.

### Clone
`git clone https://github.com/dusan-eremic/pocketmath.git`

### Build
`mvn clean compile assembly:single`
  
### Run
`java -jar target/Pocketmath-0.1-jar-with-dependencies.jar api-key`

Where the api-key is an API authorization key (x-api-key) included with the assignment question.
