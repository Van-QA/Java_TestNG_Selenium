Introduction:
---------------

This Test Automation Framework is created using Java + Selenium Web Driver + TestNG. Which can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly be used for another web application without spending any extra effort. 
With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.

Prerequisites:
---------------
*	Java jdk 17 or higher
*	Apache Maven 3 or higher
*	Please refer for any help in Maven. 
* 	http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* 	http://www.tutorialspoint.com/maven/maven_environment_setup.htm

Environment:
---------------
* There are 5 different environment configuration set up [dev, local, qa, stage, and prod]
* Use -P[environment_id] to copy the respective properties file to /resources directory
* From there, the script will use the config file / data file modified according to the specified env
* To run automation script on different browser based on below priority:
  1. Use ```-DBrowser=[browser_name] (chrome, edge, firefox...)``` 
  2. If #1 is not specified browser name, and the script is running from xml runner, it will use browser value from xml runner.
  3. If not #1 and #2, the script will use the default one from the config file.

Execution:
---------------
* Clone the repository.
* Open command prompt and go to web-test directory. Install pom.xml dependency package.
  * ```mvn clean install```
* To run on local environment use command
  * ```mvn clean test -Plocal -DBrowser=Chrome ```
* Please refer to the reporting session [Reporting](#Reporting) for details related to reporting.

Screenshot:
---------------
*	Capture Screenshot in WebDriver when some kind of error or exception surfaces while performing the test.
*	The screenshot will be attached to the Allure report with failed date time included

Reporting:
---------------
* Download Allure binary in keeping with your Allure version (you can find in POM.xml dependency as io.qameta.allure version)
  * a. If you use Windows, First move the downloaded allure file into your C directory or whatever you want to set path. Copy allure bin file path and set this path in ```Environment Variables > Path```
  * b. On Mac: ```brew install allure```
* The framework produce index.html report. It resides in the same 'target\surefire-reports' folder. This reports gives the link to all the different component of the TestNG reports like Groups & Reporter Output. On clicking these will display detailed descriptions of execution.
* You can find mailable-report.html from target\surefire-reports to email the test reports. As this is a html report you can open it with browser.
* For Allure report, use ```allure serve allure-results``` after test run to view result with screenshot
### <a id="Reporting"></a>

Enhancement:
---------------
* Allure report with recorded videos attached and adding other details into the report.
* Parallel test run with multiple threads.
* CI/CD integration.
* API calls with cookie / authentication.
* More test cases to increase coverage.
* Grouping tests to serve different purposes (regression, smoke test, ...).
* More annotations to help with organizing and improve the structure. 
