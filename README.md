# User Center

The user management system based on Spring Boot 3 realizes user registration, login, query management and other functions. You can use this as a template to quickly develop other projects.

[Github Link](https://github.com/Adair-zz/user-center)



## Requirements Analysis

- User login / logout / register
- User management
  - Add user
  - Delete user
  - Update user



## Tech Stack

- Java
- Spring, Spring MVC
- MyBatis, Mybatis Plus
- Spring Boot 3
- Junit
- MySQL Database



### User Table

```sql
-- user table
create table if not exists user
(
    id           bigint                                 auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment 'account',
    userPassword varchar(512)                           not null comment 'password',
    userName     varchar(256)                           null comment 'user name',
    userAvatar   varchar(1024)                          null comment 'user avatar',
    userRole     varchar(256) default 'user'            not null comment 'user role: user/admin',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment 'create time',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'update time',
    isDelete     tinyint      default 0                 not null comment 'is delete',
    index idx_userAccount (userAccount)
) comment 'user' collate = utf8mb4_unicode_ci;
```



## User Registration Logic Design

1. `User Input`: The user enters their accounts, password, and confirm password in the frontend.
2. `Validation of User Input`: Check if the account, password, and confirm password meet the requirements.
   - Non-empty fields
   - Account length is `at least` 4 characters
   - User account do not contain special characters
   - User account do not allow duplicate user account
   - Password is `at Least` 8 characters
   - Password is matching with confirm password
3. `Password Encryption`: Encrypt the password before storing it in the database. Avoid storing passwords in plain text.

4. `Inserting User Data in the Database`: Insert user data in database if all validation checks pass.



## User Login

1. `User Input`:
   - Not Empty
   - Account length is `at least` 4 characters
   - User account do not contain special characters
   - Password is `at Least` 8 characters
2. Before comparing user data with database, we can check if we can get the current user session info from redis.
3. Compare with database data to check if user account exists and password is correct.

3. `Managing User Session`: User login state needs to be maintained. It can be achieved by storing session information on the redis. The timeout is 24 hours, which is 86400.
4. `Return masked user info`: hiding sensitive details.



## User Management

1. User role should be `admin`.
2. Search user by user id.
3. Delete user by user id.



## Implementation

What we did in controller and service:

`Controller Level`: 

- **Validate the request parameters**  
- **Business Logic Delegation**
- **Error Handling**
- **Response Generation**
- **Content Negotiation**
- **Security** 
- **Session Management**
- **Integration**

`Service Level`:

- **Business Logic**
- **Code Reusability**
- **Decoupling**
- **Data Transformation**
- **Data Validation and Enrichment** 
- **Transaction Management** 
- **Error Handling** 
- **Integration** 
- **Complex Operations**
- **Unit Testing** 



## How to Identify Logged-In Users?

In the context of Java web development, particularly with Java Servlets and JSP, the process of identifying logged-in users involves managing user sessions and utilizing cookies. Here's how it works:

1. **Initial Connection and Anonymous Session** 

2. **Successful Login and Session Customization** 

3. **Cookie Setting on the Client** 

4. **Subsequent Requests with Cookies** 

5. **Server Identifies User Session** 

6. **Accessing Session Data**



## AOP

`Authentication` `Interceptor` and `Login Interceptor`



## Error Code

```
SUCCESS(0, "Success"),
PARAMS_ERROR(40000, "Parameters Error"),
NOT_LOGIN_ERROR(40100, "Not Login"),
NO_AUTH_ERROR(40101, "No Authentication"),
NOT_FOUND_ERROR(40400, "Not Found"),
FORBIDDEN_ERROR(40300, "No Access"),
SYSTEM_ERROR(50000, "System Internal Error"),
OPERATION_ERROR(50001, "Operation Fail"),
TOO_MANY_REQUEST(429, "Too Many Request");
```



## Global Exception Handling

#### Define Custom Business Exception Classes

1. more flexibility.
2. provide detailed information.
3. set these fields more easily and efficiently.

#### Create a Global Exception Handler

1. Write a global exception handler using Spring AOP (Aspect-Oriented Programming).
2. This handler intercepts method calls and performs additional processing before and after the method execution.
3. The handler will intercept method calls to catch exceptions and perform customized actions.

#### Purpose of Global Exception Handling:

1. **Capturing and Presenting Detailed Errors**
2. **Shielding Framework Specific Exceptions** 
3. **Centralized Handling** 
4. **Log Recording** 
