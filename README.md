# zhiboapp
## Goal of branch zhibo_rsql_jpa
* Use https://github.com/tennaito/rsql-jpa to implement filter function.

## CI Status
* [![Build Status](https://travis-ci.org/LianWaiYuChanChan/zhiboapp.svg?branch=zhiboapp_rsql_jpa)](https://travis-ci.org/LianWaiYuChanChan/zhiboapp)
* [![codecov.io](https://codecov.io/github/LianWaiYuChanChan/zhiboapp/coverage.svg?branch=zhiboapp_rsql_jpa)](https://codecov.io/github/LianWaiYuChanChan/zhiboapp?branch=zhiboapp_rsql_jpa)

## Goal
* Expose REST Service based on Apache + Tomcat + Spring MVC + JPA + Hibernate + PostgreSQL

## How to build the code?
1. Get the code from Github
2. Make sure you have installed gradle 3.1
3. cd /path/to/zhiboapp
4. gradle war #This task will build the java code and generate war which can be deployed to servlet container.


## Artifact Version
* Gradle: 3.1
* Java: Java8
* Tomcat: 7.0.72 (https://tomcat.apache.org/download-70.cgi)
* Spring: 4.3.3  (http://docs.spring.io/spring/docs/4.3.3.RELEASE/)
* Hibernate: 4.3.5 (More details: build.gradle)
* Jackson: 2.8.3
* PostgreSQL: 9.3.14

## Test Environment configuration:

Download Tomcat and PostgreSQL to your local machine. Check the versions in above sections.

## How to test?
If you want to test zhiboapp, you have prepare PostgreSQL and Tomcat.
### Test way 1:  based on PostgreSQL and standalone Tomcat.
1. gradle war #This task wll generate war to /path/to/zhiboapp/build/libs/zhiboapp.war
2. Copy that war to <TomcatHome>/webapps/
3. Start tomcat: cd <TomcatHome>/bin, then ./startup.sh or .\startup.bat
4. The tomcat will listen on port 8080 of localhost (127.0.0.1).
5. Then you have to prepare a REST client
** If you are using Firefox, try : https://addons.mozilla.org/en-US/firefox/addon/restclient/
** If you are using Chrome, try DHC REST client, PostMan.
6. In REST client, fill in the HTTP method, URL, headers, and send your request.
** For querying data, use HTTP method -- GET, headers -- Accept:application/json.
** For create/modify operation, use HTTP method -- POST, headers -- Content-Type: application/json, Accept:application/json
** For delete operation, use HTTP method -- DELETE.

### Test way 2:  based on PostgreSQL and embedded Tomcat. (Without install tomcat)
1. This way is similar with way 1. But it doesn't require you to install tomcat.
2. Be sure your PostgreSQL service is up. Then run *gradle tomcatRun* in Terminal. Then the embedded tomcat will also start on 127.0.0.1:8080.
3. Note: must run *gradle tomcatRun* at Terminal. Run it in IntelliJ may not work.

### Some request and response example:
#### Collection query:
* Request:

```javascript
HTTP Method: GET
URL: http://127.0.0.1:8080/zhiboapp/api/account
```

* Response:
 + Response HTTP status: 200(OK)
 + Response Body:
```json
    {
       "resources":
       [
           {
               "phoneNumber": "123456789",
               "name": "jack",
               "id": 1
           }
       ]
    }
```

#### One instance query
* Request:
```javascript
HTTP Method: GET
URL: http://127.0.0.1:8080/zhiboapp/api/account/1
```
* Response:
 + Response HTTP status: 200(OK)
 + Response Body:
```json
    {
       "phoneNumber": "123456789",
       "name": "jack",
       "id": 1
    }
```

#### Create operation
   * Request:
    + Request method, url, headers
   ```javascript
   HTTP Method: POST
   URL: http://127.0.0.1:8080/zhiboapp/api/account
   Headers: Content-Type:application/json
   ```
    + Request body:
   ```json
   {
   "name":"Hello",
   "phoneNumber":"123"
   }
   ```
   * Response:
    + Response status: 200(OK)  (Need change to 201)
    + Response Body:
   ```json
       {
          "phoneNumber": "123",
          "name": "Hello",
          "id": 2
       }
   ```
#### Modify operation
* Request:
 + Request method, url, headers
```javascript
HTTP Method: POST
URL: http://127.0.0.1:8080/zhiboapp/api/account/2
Headers: Content-Type:application/json
```
 + Request body:
```json
{
"name":"World",
"phoneNumber":"246"
}
```
* Response:
 + Response status: 204(No Content)
 + Response Body: No response body.

#### Delete operation
* Request:
 + Request method, url, headers
```javascript
HTTP Method: DELETE
URL: http://127.0.0.1:8080/zhiboapp/api/account/2
Headers: Content-Type:application/json
```
 + Request body: No Need Request body.

* Response:
 + Response status: 204(No Content)
 + Response Body: No response body.

## Done features.
1. User can do collection query to fetch all.
2. User can do single instance query.
3. User can do create, modify, delete operation.
4. User can do collection with filter (Just like where clause of sql.)

## Request Examples:
1. http://127.0.0.1:8080/zhiboapp/api/account?filter=id>1 and id<4
2. http://127.0.0.1:8080/zhiboapp/api/account?filter=id=gt=1;id=lt=4

## Done infrastructure
1. Tomcat + Spring + JPA + Hibernate + PostgreSQL
2. CI based on Travis is done.
3. Integration test based on HSQL is done.

## Backlog
0. CI setup (0)
1. Basic Paging, Fields (always return all fields), Filter, OrderBy, Dot notation/reference/support?
2. Adanced query features: @count...Or not support, keep REST API simple.
2. Not reuturn null value.  to save the network data of customers phone.
2. Select expected fields. Maybe not a issue. First step, can be return all fields.
2. Embedded support.
3. HATEOAS.
4. Architecture: RQL + JPA&JPQL (based o hiberate)?
5. API spec finalize
6. User Login, session manage, security impl investigation and impl
7. Interact with Shipin Cloud? Should we?
8. Admin access, UI (WEB and App)?
9. Doc about Usage. For frontend developer.
10. JPA based on Hibernate. Can be final solution? Final solution always mixed solution, since no one solution is perfect.
