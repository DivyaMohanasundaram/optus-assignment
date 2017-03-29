# Counter-API
Initial Version


curl http://localhost:8080/counter-api/top/7 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" H"Accept: text/csv"

curl http://localhost:8080/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" –X POST -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H"ContentType: application/json"
  
 curl -v -H "Accept: application/json" -H "Content-type: application/json" -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -X POST -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' http://localhost:8080/counter-api/search - For Mac

