# NUTRITION-APP

//BACKEND

For backened part. I have built five micro services.
 1. Nutrition Server(port:8080)
    Nutrition Server is basically used for login and sign up part and also here we have implemented JWT           Authentication too. It is also connected with mysql database for storing user's sign up and login data.
    
 2. Nutrition Favourite(port:8585)
    Nutrition Favourite is used only for marking item favourite and deleting favourite item. All that logic is     applied in this micro service. It is connected with mongoDB database to store user's favourite food items     list.
    
 3. Nutrition API(port:8090)
    Nutrition API is basically used to deal with calling the external api.
 
 4. API GATEWAY(port:9000)
     As we can see above all three micro-services are running on different ports. So API Gateway will provide a single port to the client(user) i.e 9000. The user only have to hit on this port. After which our APUI Gateway automatically forward the request to 
 8. Nutrition Backened(port:8761)
