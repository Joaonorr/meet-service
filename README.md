## Project Architect
### Service 
```
📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂demo
 ┃ ┃ ┃ ┗ 📂meetup
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BeanConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RestExceptionHandle.java
 ┃ ┃ ┃ ┃ ┣ 📂core
 ┃ ┃ ┃ ┃ ┃ ┗ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MeetupException.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadUserEmailException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┗ 📜MeetupApplication.java
 ┗ 📂resources
 ┃ ┣ 📂db
 ┃ ┃ ┗ 📂migration
 ┃ ┃ ┃ ┣ 📜V100_202212192246__create_hibernate_sequence.sql
 ┃ ┃ ┃ ┗ 📜V100_202212192248__create_table_user.sql
 ┃ ┣ 📂messages
 ┃ ┃ ┣ 📜messages_en.properties
 ┃ ┃ ┣ 📜messages_es.properties
 ┃ ┃ ┗ 📜messages_pt.properties
 ┃ ┣ 📂static
 ┃ ┣ 📂templates
 ┃ ┗ 📜application.properties
```

### Tests
```
📦meetup
 ┣ 📂user
 ┃ ┣ 📂controller
 ┃ ┃ ┗ 📜UserControllerTest.java
 ┃ ┗ 📂service
 ┃ ┃ ┗ 📜UserServiceTest.java
 ┣ 📜GenericControllerTest.java
 ┗ 📜MeetupApplicationTests.java
```

---

## Routs 
### 📁Users 

### End-point: getByid
#### Method: GET
>```
>http://localhost:8080/api/v1/users/{id}
>```

---

### End-point: createUser
#### Method: POST
>```
>http://localhost:8080/api/v1/users
>```
#### Body (**raw**)

```json
{
    "name": "teste1",
    "email": "teste@teste.com"
    "password": "teste"
}
```

---

