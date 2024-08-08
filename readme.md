## About
- Clothing Product API with Spring Boot MVC
## Environment:
- Java version: 17
- Maven version: 4.*
- Spring Boot version: 3.3.2.RELEASE

## Read-Only Files:
- src/test/*

## Data:
Example of a weather data JSON object:
```
{
   "barcode": "74002423",
   "item": "Shawl",
   "category": "Accessories",
   "price": 758,
   "discount": 12,
   "available": 1
}
```

## Requirements:
The `REST` service must expose the `/products` endpoint, which allows for managing the collection of weather records in the following way:


POST request to `/products`:

- creates a new list of product data records
- expects a product data object as its body payload, and verify before creating an object
- the response code is 201 and the response body is the created record and returns a list of the products


GET request to `/sort/items`:

- the response code is 200
- the response body is an array of matching records, ordered by their price in increasing order
- accepts an optional query string parameter, `item`, and when this parameter is present, only the records with the matching items are returned. The value of this parameter is case insensitive, so "jackets" and "Jackets" are equivalent. Moreover, it might contain several values, separated by commas (e.g. item=pants,jacket), meaning that records with the item matching any of these values must be returned.
- accepts an optional query string parameter, sort, that can take one of two values: either "asc" or "desc". If the value is "asc", then the ordering is by price in ascending order. If it is "desc", then the ordering is by price in descending order. If there are two records with the same date, the one with the cheaper id must come first.


GET request to `/filter/price/<initial_price}>/<final_price}>`:

- returns a list of records within given price range of initial_price and final_price
- if the matching record exists, the response code is 200 and the response body is the matching object list
- if there is no record in the collection with the given price range, the response code is 404


GET request to `/sort/price`:

- returns an array of records sorted by prices       
- if the matching record exists, the response code is 200 and the response body is the matching object list
- if there is no record in the collection with the response code is 404

## Commands
- run: 
```bash
mvn spring-boot:run
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```