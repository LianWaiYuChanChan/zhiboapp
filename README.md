# zhiboapp



## Goal
	Expose REST Service based on Spring+Hibernate+PostgreSQL

## Architecture
	Apache + Tomcat + Spring + Hiberate + PostgreSQL
## Current status:
	1. The GET path of REST is OK. (Browser -> Spring -> Hibernate -> PostgreSQL). (2016/10/23)
	
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
* Jackson: 2.8.3
* PostgreSQL: 9.3.14 