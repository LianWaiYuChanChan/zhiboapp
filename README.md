# zhiboapp
## Goal of branch zhibo_rsql_jpa

* Use https://github.com/tennaito/rsql-jpa to implement filter function.

## Filter function can work now.

You can try : 
* http://127.0.0.1:8080/zhiboapp/api/account?filter=id>1 and id<4
* http://127.0.0.1:8080/zhiboapp/api/account?filter=id=gt=1;id=lt=4

## CI Status
* [![Build Status](https://travis-ci.org/LianWaiYuChanChan/zhiboapp.svg?branch=zhiboapp_rsql_jpa)](https://travis-ci.org/LianWaiYuChanChan/zhiboapp)
* [![codecov.io](https://codecov.io/github/LianWaiYuChanChan/zhiboapp/coverage.svg?branch=zhiboapp_rsql_jpa)](https://codecov.io/github/LianWaiYuChanChan/zhiboapp?branch=zhiboapp_rsql_jpa)

## Goal
	Expose REST Service based on Spring+Hibernate+PostgreSQL

## Architecture
	Apache + Tomcat + Spring + Hiberate + PostgreSQL
	
## Current status:
	1. The GET path of REST is OK. (Browser -> Spring -> Hibernate -> PostgreSQL). (2016/10/23)
	2. Featurs (CRUD) are supported: collection query, single resource query, create, modify, delete.

## Completed function list
* Note: these items are moved from Backlog

## Backlog
0. CI setup (0)
1. Basic Paging, Fields (always return all fields), Filter, OrderBy, Dot notation/reference/support?
2. Adanced query features: @count...Or not support, keep REST API simple.
2. Not reuturn null value.  to save the network data of customers' phone. 
2. Select expected fields. Maybe not a issue. First step, can be return all fields.
2. Embedded support.
3. HATEOAS.
4. Architecture: RQL + JPA&JPQL (based o hiberate)?
5. API spec finalize
6. Security Impl investigation and impl
7. Interact with Cloud? Should we?
8. Admin access, UI (WEB and App)?
9. JPA based on Hibernate. Can be final solution? Final solution always mixed solution, since no one solution is perfect.

## How to build the code?

1. Get the code from Github
2. Make sure you have installed gradle 3.1
3. cd /path/to/zhiboapp
4. gradle war #This task will build the java code and generate war which can be deployed to servlet container.

## Test Environment configuration:

Download Tomcat and PostgreSQL to your local machine. Check the versions in below sections.
	
## How to test?
1. gradle war #This task wll generate war to /path/to/zhiboapp/build/libs/zhiboapp.war
2. Copy that war to <TomcatHome>/webapps/
3. Start tomcat: cd <TomcatHome>/bin, then ./startup.sh or .\startup.bat
4. Try access: http://127.0.0.1:8080/zhiboapp/api/account/account_1 using Chrome.
5. Try SET operation (Note: must use REST client and specify header: Accept:application/json and Content-Type: application/json.). Request: POST http://127.0.0.1:8080/zhiboapp/api/account will return below message:
```json
    {
       "phoneNumber": null,
       "name": null,
       "id": 1
    }
```

## Try current implemented function

1. Run gradle tomcatRun  #This task will load the zhibo web app into a embedded tomcat
2. The embedded tomcat will start on port 8080
3. Open Browser, access URL: http://127.0.0.1:8080/zhiboapp/api/account/account_1, then you will get response like below:

```json
{"name":"Jack","phoneNumber":"12330940888","id":"account_1"}
```

4. If you try to access http://127.0.0.1:8080/zhiboapp/api/account/non_exist_account, it will report below error message:

```json
{"statusCode":404,"errorMessage":"Not found account: non_exist_account"}
```

## Version
* Gradle: 3.1
* Java: Java8
* Tomcat: 7.0.72 (https://tomcat.apache.org/download-70.cgi)
* Spring: 4.3.3  (http://docs.spring.io/spring/docs/4.3.3.RELEASE/)
* Hibenate: 4.3.5 (More details: build.gradle)
* Jackson: 2.8.3
* PostgreSQL: 9.3.14 

## References

### About REST HATEOAS
1. https://dzone.com/articles/building-hateoas-hypermedia
2. https://opencredo.com/hal-hypermedia-api-spring-hateoas/ (How to test HATEOAS)

### About test on docker
3. http://bencane.com/2016/01/11/using-travis-ci-to-test-docker-builds/

### About REST query filter and parser, ANTLR, JavaCC, AST
1. http://www.andymcm.com/?p=21
2. http://linqtoquerystring.net/index.html
3. http://tastefulcode.com/2014/04/04/building-a-query-parser-for-elasticsearch/
4. https://www.javacodegeeks.com/2012/01/simplifying-restful-search.html
5. http://specs.okfnlabs.org/research/data-query-protocol/
6. https://www.elastic.co/guide/en/elasticsearch/reference/current/search-uri-request.html
7. http://www.baeldung.com/rest-api-search-language-spring-data-querydsl
8. https://www.sitepen.com/blog/2010/11/02/resource-query-language-a-query-language-for-the-web-nosql/
9. https://github.com/jirutka/rsql-parser (Good)
10. https://github.com/tennaito/rsql-jpa (Query to JPA. Good)

### REST Best pratices
1. http://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api#advanced-queries

### Hibernate or JPA
1. http://cliffmeyers.com/blog/2011/7/18/why-i-finally-ditched-hibernate-native-apis-for-jpa.html

### README badge
* http://shields.io/

