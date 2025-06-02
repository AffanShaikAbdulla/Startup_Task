#shipping services 
A spring boot application fro managing orders and caluclating shipping costs.
## features
-create and save order with shipping addresses
-Calucalte dynamic shipping cost based on country 
-RESTful API endpoints 

##Technologies Used 
-Java 17 
-JPA 
-MYSQL
-Lombok 

#API End points 
## create an order 


**Request Body:**
```json
{
  "shippingAddress": {
    "country": "India",
    "state": "Karnataka",
    "city": "Bangalore",
    "postalCode": "560001"
  }
}
GET /api/orders/{orderId}/shipping-cost
### Author - shaik Abdulla
