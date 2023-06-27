# CRUD project with:

    - Java 8+
    - SpringBoot
    - MySQL
    - Docker-compose
    - Jpa
    - Actuator
    - Swagger

# To initialize and use this repository:

    1°: be suer to be in the directory: usersRest/usersAPI/docker, then initialize the         database with the command: docker-compose up.
    2°: one the database is up, go to the file: UserApiApplication.java and run it
    3°: If you are using postman:
        main URL: http://localhost:8080
        - GET Users: /api/v1/users/
        - POST Users: /api/v1/users/
        - GET User: /api/v1/users/{userId}
        - PUT Users: /api/v1/users/{id} + user information, add the field "userId" above,
            "name", and in the same way, "phoneId", above "number", in case you want to,
            update one or more phones.
        - DELETE Users: /api/v1/users/{userId}
    4°: To access to the Swagger-ui app view: http://localhost:8080/doc/swagger-ui.html
