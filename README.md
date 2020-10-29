# Planner App
An app to help you plan your week. Given a list of tasks defined by a due date, priority and estimated completition time, this app will generate an optimized schedule to get things done on top and help prevent procrastination. Customization options include:

* Unavailable times (work, class, sleep, free-time)
* Time between tasks
* Repeating tasks

## Design
This application consists of a frontend built in angular and a backend built in Java using Spring Boot API. The API is run on /api/v1/ where all request need to be authenticated unless /api/v1/login or /api/v1/register are called.

### Login
A request should be made to /api/v1/login with the body containing JSON formatted as seen below where "<GIVEN USERNAME>" and "<GIVEN PASSWORD>" are the given username and password for the login request. Upon a successful login request, a JWT token will be sent for all future authentications. An error message will be sent if the request is unsuccessful.

```JSON
{
  "username": "<GIVEN USERNAME>",
  "password": "<GIVEN PASSWORD>"
}
```
  
### Register
A request should be made to /api/v1/register with a body containing JSON formatted as seen below where the information contined within <> is replaced with the given information given to register the user. Everything listed is required to register a user except for role. If no role is supplied, then it is set to USER. All errors will be sent as a message response if the register is unable to handle the request made for any reason.

All possible roles are: USER, ADMIN

```JSON
{
  "username": "<GIVEN USERNAME>",
  "password": "<GIVEN PASSWORD>",
  "email": "<GIVEN EMAIL>"
}
```


## Starting the application
Clone the repository to the server and run:

```bash
./gradlew bootRun
```

The server will then be run on a http://localhost:8080
