# tstzrange-bug
Shows how to recreate issue with zoned time ranges that cross a DST boundary

You will need to change the settings in resources/application.properties to connect to a Postgres instance

spring.datasource.url=jdbc:postgresql://localhost:5432/test<br>
spring.datasource.username=<your username><br>
spring.datasource.password=<your password>
