# DADI Music Store

DADI Music Store is a Java Spring Web App.  
Users can:
* Browse an online product catalog
* Search the online product catalog
* Use a shopping cart and checkout
* Have an account  

Admins can:
* Add or remove genres, and update the details of existing genres
* View and manage the categories that belong to a genre
* Manage the list of products in a specific category, and edit product details
* Assign an existing product to an additional, or move it to another category
* Remove a product from a category or delete the product from the catalog
* Manage orders by updating their status

## Installation

Use [docker](https://docs.docker.com/get-docker/) for database.

```bash
docker-compose --file docker/compose.yaml up --build -d
```
Run in IDE by importing dadi-music-api and dadi-music-webapp 
or by using Maven
```bash
cd dadi-music-api/
mvn spring-boot:run
```
```bash
cd dadi-music-webapp/
mvn spring-boot:run
```

## Usage
API documentation: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Stopping

```bash
docker-compose --file docker/compose.yaml down
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
