# pocketmath
The pocketmath assignment demostrates the usage of Pocketmath RESTful API.

### Clone
`git clone https://github.com/dusan-eremic/pocketmath.git`

### Build
`mvn clean compile assembly:single`
  
### Run
`java -jar target/Pocketmath-0.1-jar-with-dependencies.jar api-key`

Where the api-key is an API authorization key (x-api-key) included with the assignment question.
Note that 429 (too many requests) HTTP error may sometimes occur, please re-run the app in that case.
