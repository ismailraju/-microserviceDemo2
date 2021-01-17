

./consul.exe agent -dev



docker concul
==================
https://hub.docker.com/_/consul

docker pull consul

docker network create -d bridge consul-network
docker network create  --driver=bridge   --subnet=172.22.0.0/16  --ip-range=172.22.0.1/24    --gateway=172.22.0.254    consul-network

docker run -d --name consul-1 -p 8500:8500  -e CONSUL_BIND_INTERFACE=eth0 consul 


docker exec -t consul-1 consul members -detailed

docker run -d --name consul-2 -e CONSUL_BIND_INTERFACE=eth0 -p 8501:8500 consul agent -dev -join 172.17.0.2
docker run -d --name consul-3 -e CONSUL_BIND_INTERFACE=eth0 -p 8502:8500 consul agent -dev -join 172.17.0.2
-------------------------------------------------------------------
docker run -d --name consul-1 --dns 172.17.0.1 -p 8500:8500  -e CONSUL_BIND_INTERFACE=eth0 consul 


docker exec -t consul-1 consul members -detailed

docker run -d --name consul-2 --dns 172.17.0.1  -e CONSUL_BIND_INTERFACE=eth0 -p 8501:8500 consul agent -dev -join 172.17.0.2
docker run -d --name consul-3 --dns 172.17.0.1  -e CONSUL_BIND_INTERFACE=eth0 -p 8502:8500 consul agent -dev -join 172.17.0.2
docker run --name as   --dns=172.17.0.1 as
-------------------------------------------------------------------
docker run --name as as
docker run --name cs cs 
docker run --name gs -p 8080:8080 gs 
docker run --name os os 
docker run --name ps ps 
------------------------------------------------------
docker network connect consul-network consul-1
docker network connect consul-network consul-2 
docker network connect consul-network consul-3 
docker network connect consul-network as 
docker network connect consul-network cs 
docker network connect consul-network gs 
docker network connect consul-network os 
docker network connect consul-network ps 
docker exec -ti as ping consul-1


docker network connect consul-network gs-one 
--------------------------------------------------------------------

config/account-service,zone1/data
config/customer-service,zone1/data
config/order-service,zone1/data
config/product-service,zone1/data
config/gateway-service,zone1/data

config/gateway-service,zone2/data
config/account-service,zone2/data
config/customer-service,zone2/data
config/product-service,zone2/data
config/order-service,zone2/data
----------------------------------------------------------------

docker exec -t consul-1  consul kv put config/gateway-service,zone1/data 'spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0'

docker exec -t consul-1  consul kv put config/account-service,zone1/data  'spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0'

docker exec -t consul-1  consul kv put config/customer-service,zone1/data  'spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0'

docker exec -t consul-1  consul kv put config/product-service,zone1/data  'spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0'

docker exec -t consul-1  consul kv put config/order-service,zone1/data  'spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0'


----------------------------------------------------------------
spring:  
  cloud:
    consul:
      discovery:
        instanceId: "${spring.cloud.client.hostname}:${spring.application.name}:${random.int[1,999999]}"
        instanceZone: zone1      
server.port: 0
--------------------------------------------------------------------

docker run -d -e CONSUL_BIND_INTERFACE=eth0 consul agent -dev -join 172.17.0.2

docker exec -t consul-1 ping 172.17.0.2



docker exec -t consul-1  consul services deregister -id=account-service



docker exec -t consul-1 tail -20 /etc/hosts
docker exec -t consul-1 ping f10248fd4516




docker run --name as   --dns=172.17.0.1 as

docker run --name as   as
docker run --name as  --link consul:consul-1 as
docker run --name as   -p 9099:9099 as
docker run --name as   --net=bridge  as
docker run --name as -e  -p 9099:9099 as

docker exec -ti consul-1 ping ec841711d4e5
docker exec -ti consul-1 cat /etc/resolv.conf


docker network connect bridge consul-1 as

docker exec -t consul-1 curl http://172.17.0.2:8500/v1/catalog/nodes
docker exec -t consul-1 curl http://172.17.0.2:8500/v1/catalog/service/account-service
docker exec -t consul-1 curl http://172.17.0.2:8500/v1/catalog/service/account-service?passing





dig @127.0.0.1 -p 8600  account-service.service.consul


docker exec -t consul-1 consul kv get config



docker run -d --name consul-1 --network=consul-network -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0    consul 
docker exec -t consul-1 consul members -detailed
docker run -d --name consul-2  --network=consul-network   -e CONSUL_BIND_INTERFACE=eth0 -p 8501:8500 consul agent -dev -node machine  -join 172.22.0.1
docker run -d --name consul-3  --network=consul-network   -e CONSUL_BIND_INTERFACE=eth0 -p 8502:8500 consul agent -dev -node machine  -join 172.22.0.1


 
docker run -d --name consul-1 -e CONSUL_BIND_INTERFACE=eth0 -p 8501:8500  -p 8600:8600 consul agent -dev  -node machine   

docker run --name as  --network=consul-network as






-------------------------- zipkin server run ----------------------------------------
docker pull openzipkin/zipkin

docker run -d -p 9411:9411 openzipkin/zipkin












