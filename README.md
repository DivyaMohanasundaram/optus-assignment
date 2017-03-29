# Counter-API
Initial Version


curl http://localhost:8080/counter-api/top/7 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" H"Accept: text/csv"

curl –X POST http://localhost:8080/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d '{"searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}' -H"ContentType: application/json"  

