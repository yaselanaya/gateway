**Gateways**

_Application to manage the gateways and its peripheral devices._


**Technologies used to implement the application.**
- Spring Boot 2.3.2
- JVM 1.8
- MySQL 5.6 or higher
- Hibernate 5.4.18
- Flyway 5.2.4
- Swagger 2.9.2
- Apache Maven 

**Specifications to use the application.**

- Was used the flyway to create the tables of the gateways and the peripheral devices and insert the default data to use it, 
when the application runs, the three scripts in the **"db.migration"** path are executed creating the tables and inserting the data, 
is important note that the database has to be created before running the application, the database name must be equal to the database 
name set in the application.properties file, before running the application, the user must configure the data source parameters
with the custom configuration of its workspace.

- Was integrated swagger to document the API and to test all endpoints associated with the logic of the application, 
to access to the swagger the user has to put the URL **"http://localhost:8080/swagger-ui.html"** in the browser and 
the user will see the API documentation and it can test the application.

- Was used the Apache Maven to the automated build, to compile the application execute **"mvn clean package"** and 
in the **"target"** path execute the jar with the command "java **-jar gateway-0.0.1-SNAPSHOT.jar"**.

- In the **"postman_collection"** path of the project, there is a JSON file called **"Gateway Application.postman_collection.json"** 
with the collection of the requests to test in Postman, import the file, and executed in order of the appear.

- To list the data was created a JSON string to the parameter with the format:
{"search": "","filters":[{"field":"fieldName", "operator":"EQUALITY", "value":"fieldValue"}],"pageable":{"page":0,"size":200,"orders":[{"direction":"DESC","property":"propertyName"}]}}

Where: 
**search key**: In the case of the gateways the filter get all the gateways that its name or unique serial number contain the string value,
 and in the case of the devices the filter get all the devices that the vendor contain the string value.

**filter key**: This keys is an array of the objects that contain three keys each one, the field that represents the name of the field,
 the operator that represents the SQL operator that the query will be uses, and the value.

**e.g: {"field":"gatewayId", "operator":"EQUALITY", "value":1}**

This filter search all the devices that belong to the gateway with id.

**pageable key**: This key is an object used to set the limit of the rows listed and the order of the elements,
 it has three keys(page, size and the orders), the orders key is an array with the asc or desc order per field.  