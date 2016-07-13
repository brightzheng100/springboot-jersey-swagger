# springboot-jersey-swagger
https://travis-ci.org/itstarting/springboot-jersey-swagger.svg?branch=master

This is a proof-of-concept project to build RESTful web services by integrating below components:
 - [Spring Boot](http://projects.spring.io/spring-boot/) - The foundation of our web app
 - [Jersey](https://jersey.java.net/) - The JAX-RS reference implementation for building RESTful web services
 - [Swagger](https://github.com/swagger-api/swagger-ui) - The de-facto API documentation framework

For testing, we use below components:
 - Spring Boot Test Framework (spring-boot-starter-test) with Junit, Hamcrest
 - [Rest Assured](https://github.com/rest-assured/rest-assured)


# Takeaways
 - How To Integrate Spring Boot, Jersey and Swagger to build real world JSON-based RESTful web services
 - Unit Test plactices for RESTful API testing
 
# Start UP
Check out the code and execute below commands:
```
> mvn package
> java -jar target/springboot-jersey-swagger-1.0.0-SNAPSHOT.jar
```

# Play With the Web Services
```
> curl -X GET http://localhost:8000/api/v1/hello/Bright
{"msg":"Hello Bright - application/json"}

>curl -X GET http://localhost:8000/api/v1/hello/404
{"timestamp":1466473854650,"status":404,"error":"Not Found","message":"Not Found","path":"/api/v1/hello/404"}
```

# Check Out the Swagger UI
Open a browser and key in URL:
```
http://localhost:8000/
```
Note: we can play around the APIs within the UI.

# Blog
[From Code To Online Services: My experiments of DevOps - Development of RESTful Web Services by Spring Boot, Jersey, Swagger](http://bright-zheng.blogspot.com/2016/06/Development-of-RESTful-WebServices-by-SpringBoot-Jersey-Swagger.html)