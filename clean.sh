docker rm inventory-service-ctn
docker rm delivery-service-ctn
docker rm order-service-ctn

docker rmi tramhuuducvn/delivery-service-img    
docker rmi tramhuuducvn/inventory-service-img   
docker rmi tramhuuducvn/order-service-img       
docker rmi tramhuuducvn/order-service         