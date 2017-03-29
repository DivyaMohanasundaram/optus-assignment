# Counter-API
  
 This Restful API is build using Java 1.8, SpringBoot and Maven 
 
 How To Run
 ----------
 1.Start the application
  
        mvn spring-boot:run
 
 2. Run below curl commands

        curl http://localhost:8080/counter-api/top/7 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" H"Accept: text/csv"
        curl http://localhost:8080/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" –X POST -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H"ContentType: application/json"
        curl -v -H "Accept: application/json" -H "Content-type: application/json" -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -X POST -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' http://localhost:8080/counter-api/search - For Mac

 Unit Test
 ---------
 
 Unit tests are available in /src/main/test