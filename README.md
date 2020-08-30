# Intercorp
## Technical challenge 

>Desafío a resolver de microServicios:
>
>* Microservicio desarrollado en JAVA Spring boot
>* API Rest documentada en Swagger
>* Deployado en AWS o algún CLOUD + código subido en GITHUB
>* Endpoint de Entrada POST /creacliente: 
>  - Nombre
>  - Apellido
>  - Edad
>  - Fecha de nacimiento
>* Endpoint de salida GET  /kpideclientes:
>  - Promedio edad entre todos los clientes
>  - Desviación estándar entre las edades de todos los clientes
>* Endpoint de salida GET /listclientes:
>  - Lista de personas con todos los datos + fecha probable de muerte de cada una

##

This challenge is for a simple _CRUD_ service, I decided to not to focus so much on the logic and the details, but to
pay more attention to some design concepts and ideas; therefore on the implementation of the methods I asume,
and code to, the success path.

I use a database to show some spring-boot-data and JPA capabilities. I decide to use an in memory db for simplicity
and so anyone can run the code without having to configure it.

I changed the endpoints to be _REST compliant_  

# [App definition](src/main/java/com/intercorp/challenge/demoApp)
This is the solution to the requirements, it defines the interface for the application, so any implementation that
complies with these definitions could be integrated on other systems that requieres this service. For example a desktop
application could have a client service which is implemented with a module or library, or a web application could 
comunicate against a service like the one I did here as an example. Even further each module (client and metrics)
could be replaced and no user should ever have to update or change their own system. This is related with 
__interface segregation principle__, and a design style known as __code to the interface__ 
and allows systems to be loosely coupled.

## [Clients Module](src/main/java/com/intercorp/challenge/clients)

This module contains the functionalities to create and retrieve clients

#### create client

>POST /clients
>* Request body:
>`{
>    name: "client name",
>    lastName: "client last name",
>    birthDate: "client birth date with format YYYY-MM-DD"
>  }`
>* Response:
>   - code: 201
>   - body: the created client `{id: , name: , lastName: , birthDate: , age: , estimatedDateOfDeath: }`
>* Example: curl -H "Content-Type:application/json" -X "POST" 
>-d '{"name":"testName", "lastName":"testLast","birthDate":"1940-11-30"}' 'localhost:8080/clients'

#### retrieve all clients

>GET /clients
>* Response:
>   - code: 200,
>   - body: A list of clients
>* example: curl -H "Content-Type:application/json" localhost:8080/clients'
####Find client by id
>GET /clients/{id}
>* Response:
>   - code: 200
>   - body: A client or empty if no client found
>* example: curl -H "Content-Type:application/json" 'localhost:8080/clients/4'

## [Metrics Module](src/main/java/com/intercorp/challenge/metrics)

This module contains the metrics. I'm aware this is not the best way to implement this type of functionality as it has
potential to fail and to affect other services on the host because of resources usage.

#### KPI clients
>GET /metrics/clients
>* Response:
>   - code: 200
>   - body: Clients kpi `{averageAge: , standardDeviation: }`
>* Example: curl -H "Content-Type:application/json" 'localhost:8080/metrics/clients'
