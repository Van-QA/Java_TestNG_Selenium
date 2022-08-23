Introduction:
---------------

This Test Automation Framework is created using Java + Selenium Web Driver + TestNG. Which can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly be used for another web application without spending any extra effort. 
With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.

Prerequisites:
---------------
*	Java jdk-1.8 or higher
*	Apache Maven 3 or higher
*	Please refer for any help in Maven. 
* 	http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* 	http://www.tutorialspoint.com/maven/maven_environment_setup.htm

Environment:
---------------
* 	There are 5 different environment configuration set up [dev, local, qa, stage, and prod]
*	Use -P[environment_id] to copy the respective properties file to //config/env.properties 

Execution:
---------------
*	Clone the repository.
*	Open command prompt and go to web-test directory.
*	To run on local environment use command> mvn clean test -Pdev


*	log4j configured to capture the test execution logs
*	Configuration file is located at //config/log4j.xml
*	Execution log is captured in the //log/Automation_Execution.log

Screenshot:
---------------
*	Most of the time we think to Capture Screenshot in WebDriver when some kind of error or exception surfaces while practicing testing, to resolve the same the framework has a method. 
*	getScreenshot() is used to indicate driver to capture a screenshot and store it in //screenshot/packageName directory.

Reporting:
---------------
*  The framework produce index.html report. It resides in the same 'target\surefire-reports' folder. This reports gives the link to all the different component of the TestNG reports like Groups & Reporter Output. On clicking these will display detailed descriptions of execution.
*  You can find mailable-report.html from target\surefire-reports to email the test reports. As this is a html report you can open it with browser.

Testcases:
---------------

1. Subtraction - 2 integer
2. Subtraction - 2 decimal
3. Subtraction - decimal subtract integer
4. Subtraction - integer subtract decimal
5. Subtraction - minuend > subtrahend
6. Subtraction - minuend < subtrahend
7. Subtraction - minuend = subtrahend
8. Subtraction - negative minuend and negative subtrahend
9. Subtraction - positive minuend and negative subtrahend
10. Subtraction - negative minuend and positive subtrahend
11. Subtraction - max length of minuend, subtrahend  (9 numbers): test with 1->7, 8, 9, 10
12. Subtraction - overflow - min minuend and max subtrahend  (-999999999 and 999999999)


1. Addition - 2 integer
2. Addition - 2 decimal
3. Addition - decimal add integer
4. Addition - integer add decimal
5. Addition - 2 positive number
6. Addition - negative number add positive number
7. Addition - positive number add negative number
8. Addition - negative number add positive number = 0
9. Addition - max length of minuend, subtrahend  (9 numbers - not include - and .): test with 1->7, 8, 9, 10
10. Addition - overflow - add max number(999999999)


1. Division - 2 integer
2. Division - 2 decimal
3. Division - decimal divided by integer
4. Division - integer divided by decimal
5. Division - 2 positive number
6. Division - negative number divided by positive number
7. Division - positive number divided by negative number
8. Division - integer quotient
9. Division - decimal quotient
10. Division - max length of minuend, subtrahend  (9 numbers - not include - and .): test with 1->7, 8, 9, 10
11. Division - dividend is 0 -> 0
12. Division - divisor is 0 -> Error
