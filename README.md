# CURD REST API Spring Boot 2.4.1 + JPA + MYSQL

### Dependencies Gradle
- Starter Web
- JPA
- Validation


### Structure
* Model
* Repository
* Service
* Controller
* Exception

### End Point
| Method | URI | Param Query | BODY JSON |
| ------ | ------ | ------ | ------ |
| CREATE | http://localhost:8080/api/customer | none | { "firstName" : "Aji", "lastName" : "Indra", "dlNumber" : "1234", "zipcode" : "123" }|
| READ ALL | http://localhost:8080/api/customers?query=aji&page=2&limit=5| query, page, limit | none |
| READ ONE | http://localhost:8080/api/customer/{id} | none | none |
| UPDATE | http://localhost:8080/api/customer | none | { "id" : 1, "firstName" : "Aji", "lastName" : "Indra", "dlNumber" : "1234", "zipcode" : "123" }|
| DELETE | http://localhost:8080/api/customer/{id} | none | none

### Contact
https://www.linkedin.com/in/aji-indra-jaya-3ab6b7135/

License
----

MIT
