# NUTRITION-APP

//BACKEND

For backened part. I have built five micro services.
 1. Nutrition Server(port:8080)
 2. Nutrition Favourite(port:8585)
 3. Nutrition API(port:8090)
 4. API GATEWAY(port:9000)
 5. Nutrition Backened(port:8761)
 
 -> Nutrition Server(port:8080)
    Nutrition Server is basically used for login and sign up part and also here we have implemented JWT           Authentication too. It is also connected with mysql database for storing user's sign up and login data.
    
 -> Nutrition Favourite(port:8585)
    Nutrition Favourite is used only for marking item favourite and deleting favourite item. All that logic is     applied in this micro service. It is connected with mongoDB database to store user's favourite food items     list.
    
 -> Nutrition API(port:8090)
    Nutrition API is basically used to deal with calling the external api.
 
 -> API GATEWAY(port:9000)
     As we can see above all three micro-services are running on different ports. So API Gateway will provide      a single port to the client(user) i.e 9000. The user only have to hit on this port. After which our API        Gateway automatically forward the request to the desriable ports with their micro-services. 
     
 -> Nutrition Backened(port:8761)
    Nutrition Backened is basically a Eureka netflix server which we use to deploy all are micro-services. It     itself is also a micro-services but we have deploy all are 4 mico services including API gateway.
