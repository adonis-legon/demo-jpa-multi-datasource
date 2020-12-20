# Demo project for Spring JPA with multiple Datasources

## Test Locally

### GET from Basic User Datasource

`$ curl localhost:7777/api/v1/user/basic/user1@domain.com`

### GET from Premium User Datasource

`$ curl localhost:7777/api/v1/user/premium/user2@domain.com`

### POST to Basic User Datasource

`$ curl -X POST localhost:7777/api/v1/user --header 'Content-Type: application/json' --data '{"name": "user1", "email": "user1@domain.com", "membershipType": 0}'`

### POST to Premium User Datasource

`$ curl -X POST localhost:7777/api/v1/user --header 'Content-Type: application/json' --data '{"name": "user2", "email": "user2@domain.com", "membershipType": 1}'`
