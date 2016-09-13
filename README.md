# AI Assignment
This application is developed for AI assignment.
## Technology used
1. Spring boot
2. Spring data JPA (hibernate as provider)
3. Spring security
4. Spring MVC
5. Thymeleaf
6. PostgreSQL database

## Installation
1. Restore "assignment database"
```
psql -U postgres assignment < initial_data.sql
```
2. Open application.properties and set username, password, and database name with correct name.
3. run application
```
mvn spring-boot:run
```
