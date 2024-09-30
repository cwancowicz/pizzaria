## Usage
- [Endpoints](#endpoints)
  - [Create Customer Toppings Preference](#1-create-new-customer-toppings-preference)
  - [Update Customer Toppings Preference](#2-update-customer-toppings-preference)
  - [Get Customer Toppings Preference](#3-get-customer-toppings-preference)
  - [Get Popular Toppings](#4-get-all-customer-preference-counts)
  - [Get All Customers By Fields](#5-get-all-customers)

### Endpoints

The following is a list of endpoints available for use.

#### 1. Create new Customer Toppings Preference
- **Endpoint:** `POST /v1/api/customer`
- **Description:** Create a new customer with given email and selected toppings.
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "toppings": ["pepperoni", "mushrooms", "olives"]
  }
  ```
- **Example Call:**
`curl --location 'http://localhost:8080/v1/api/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@gmail.com",
    "toppings":["mushrooms","olives","cheese"],
    "receivePromos": true
}           '`

#### 2. Update Customer Toppings Preference
- **Endpoint:** `PUT/v1/api/customer`
- **Description:** Update your previously submitted customer toppings preferences.
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "toppings": ["jalapenos", "extra cheese"]
  }
  ```
- **Example Call:**
`curl --location --request PUT 'http://localhost:8080/v1/api/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@gmail.com",
    "toppings":["mushrooms","olives","cheese"],
    "receivePromos": false
}           '`

#### 3. Get Customer Toppings Preference
- **Endpoint:** `GET /v1/api/customer/{email}`
- **Description:** Retrieve your current toppings preferences for given email.
- **Query Parameters:**
    - `email`: Your email address
- **Response:**
  ```json
  {
    "email": "user@example.com",
    "toppings": ["pepperoni", "mushrooms", "olives"]
  }
  ```
 **Example Call:**
`curl --location --request GET 'http://localhost:8080/v1/api/customer/test@gmail.com' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@gmail.com",
    "toppings":["mushrooms", "onions"]
}           '`

#### 4. Get All Customer Preference Counts
- **Endpoint:** `GET /v1/api/customer/topping-count`
  - This endpoint is contained in a pageable response to control the number of results returned. Response can be controlled with the parameters:
    - size
    - pageNumber
    - order
- **Description:** Get a list of the most favored toppings based on customer preferences.
- **Example Call:**
`curl --location 'http://localhost:8080/v1/api/topping-count?size=16&sort=numberOfCustomers%2CDESC'` will return at most 16 items with the `numberOfCustomers` field sorted in DESCENDING order
- **Response:**
```json
{
  "content": [
    {
      "name": "mushrooms",
      "numberOfCustomers": 2
    },
    {
      "name": "olives",
      "numberOfCustomers": 2
    },
    {
      "name": "cheese",
      "numberOfCustomers": 1
    },
    {
      "name": "peppers",
      "numberOfCustomers": 1
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 16,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalElements": 4,
  "totalPages": 1,
  "first": true,
  "size": 16,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 4,
  "empty": false
}
```

#### 5. Get All Customers
- **Endpoint:** `GET /v1/api/customer/topping-count`
  - This endpoint is contained in a pageable response to control the number of results returned. Response can be controlled with the parameters:
    - size
    - pageNumber
    - order
    - qt: Query Term [receivePromos, delivery] (Optional)
    - qf: [true, false] (Optional)
- **Description:** Get all customers and optionally filter by receivePromos or delivery
- **Example Call:**
```
curl --location --request GET 'http://localhost:8080/v1/api/customer?qf=receivePromos&qv=true' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@gmail.com",
    "toppings":["mushrooms", "onions"]
}           '
```
- **Response:**
```json
{
    "content": [
        {
            "email": "test@gmail.com",
            "toppings": [
                "mushrooms",
                "olives",
                "cheese"
            ],
            "receivePromos": true,
            "delivery": false
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 1,
    "empty": false
}
```