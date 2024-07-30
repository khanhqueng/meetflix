# Beflix
## Description
* Betflix is a movie website built on Microservice Architecture that user can interact with now showing movies and book tickets like the websites of famous cinema such as CGV or Lotte
* I choose Springboot for developing services, Spring Cloud for Api-gateway, configuration server, server-registry
* ELK Stack for logging monitoring, RabbitMQ for async communication between services, Zipkin for tracing
* I use MySql for RDBMS and Redis for NoSql
  ![Screenshot 2024-07-30 124340](https://github.com/user-attachments/assets/024904c5-525c-4f2e-bb3d-9693c441cd69)
  _Application Architecture_
In addition, I utilize RabbitMQ for async communication between order services, email service and movie service, below is the general flow : 
![Screenshot 2024-07-30 132130](https://github.com/user-attachments/assets/e53b808a-16c2-421c-bcfd-658b3ca9df02)
## Technologies and Framework
* Java 17
* Spring boot 3: Authorization Server (Oauth 2), ...
* Spring Cloud: Netflix Eureka, Spring Cloud Gateway, Config Server, WebClient, ...
* Elastic stack: Elasticsearch, Logstash, Kibana
* Grafana stack: Prometheus, Grafana
* Zipkin
* Redis
* RabbitMQ
## How to use
Create network 
```
docker network create -d bridge betflix-network
```
Run infra
* You just need mysql, redis and rabbitmq for starting the application, if you don't want to start all of containers in infra, use the code below:
```
docker compose -f docker-compose.infra.yml up -d betflix-mysql

```
This code just run container betflix-mysql
* If you want to run all :
```
docker compose -f docker-compose.infra.yml up -d
```
Run services
* You need to run services in the following order: service-registry -> config-server -> user-service -> ...
```
docker compose -f docker-compose.yml up -d service-name
```
For run certain services and
```
docker compose -f docker-compose.yml up -d 
```
For run all services

Run prometheus and grafana
```
docker compose -f docker-compose.monitoring.yml up -d 
```


