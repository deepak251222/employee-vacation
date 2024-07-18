Employee Vacation Back-end App
Description:
This project aims to develop a back-end API using Java with Spring Boot and MySQL.
 The API will implement essential features outlined in the assignment requirements.

Features:
Built on the Spring MVC Model
Global Exception Handling
Search Operations


Please refer to the attached files for the code and Postman collection.


   Steps to Run :  
     
     Clone the repository or import it into an IDE:
     Ensure the application.properties file is properly configured for the database.
     Run the application:

    Note : - Refer to Postman for input data(postman collection import).
   
  1.Make clone for git  or import any IDE please chack application.property file for db configartion.

  2. After Run  (just i wrote url, please check post man for input data)
          
       For Employee 
        1. Create Employee  (localhost:8082/api/employees/create)  

        2. Apply for Vacation:  (localhost:8082/api/employees/1/vacation-requests)

        3. Check Own Vacation Status (localhost:8082/api/employees/1/vacation-details)


        for Manager 
         1. for Approve or Reject Vacation Requests (localhost:8082/api/managers/vacation-requests/1?status=APPROVED&managerId=2)
    
        2 . For checking employee details base serching (localhost:8082/api/managers/vacation-requests?status=APPROVED) 
              status=APPROVED parameter is optional for filtering based on the request status.

   
 Please check all URLs, such as:

localhost:8082/api/managers/vacation-requests/7?status=PENDING&managerId=2
In this example:
7 is the employeeId
2 is the managerId


If you encounter any issues while running the app or with the URLs,
 please let me know at deepak251222@gmail.com or call 8118818157.

Thanks for your consideration.
   
