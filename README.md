[![GitHub stars](https://img.shields.io/github/stars/ngoanh2n/selenide-testng-reportportal-example.svg?style=social&label=Star&maxAge=2592000)](https://github.com/ngoanh2n/selenide-testng-reportportal-example/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/ngoanh2n/selenide-testng-reportportal-example.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/ngoanh2n/selenide-testng-reportportal-example/watchers/)
[![GitHub forks](https://img.shields.io/github/forks/ngoanh2n/selenide-testng-reportportal-example.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/ngoanh2n/selenide-testng-reportportal-example/network/members/)

# Selenide, TestNG, ReportPotal Example
**Table of Contents**
- [**Environment**](#environment)
- [**Frameworks/Libraries**](#frameworkslibraries)
- [**Project Structure**](#project-structure)
- [**How To Use**](#how-to-use)
  - [**Clone Repository**](#clone-repository)
  - [**Configuration Files**](#configuration-files)
  - [**Deploy ReportPortal With Docker**](#deploy-reportportal-with-docker)
    - [**Install Docker, Docker Machine**](#install-docker-docker-machine)
    - [**Deploy ReportPortal**](#deploy-reportportal)
    - [**Setup ReportPortal UI**](#setup-reportportal-ui)
  - [**Run Tests With Gradle**](#run-tests-with-gradle)
    - [**Perform On Browsers**](#perform-on-browsers)
    - [**Filter Tests**](#filter-tests)
  - [**ReportPortal Results**](#reportportal-results)

## **Environment**
> **Platform**: <em>macOS Mojave</em><br/>
> **IDE**: <em>IntelliJ IDEA 2019.3.1 (Community Edition)</em><br/>
> **Java**: <em>1.8.0_211</em><br/>
> **Gradle**: <em>6.7.1</em><br/>
> **Docker**: <em>19.03.5</em><br/>

## **Frameworks/Libraries**
> **Selenide**: <em>5.17.0 - Web Driver</em><br/>
> **TestNG**: <em>7.1.0 - Testing Framework</em><br/>
> **ReportPortal UI**: <em>5.0 - Reporting Engine Service</em><br/>
> **ReportPortal Java Agent**: <em>5.0.8 - Reporting Engine Agent for TestNG</em><br/>

## **Project Structure**
```
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── images
│   └── **/*.png
├── src/test
│   ├── java/com/github/ngoanh2n
│   │   ├── common
│   │   │   ├── BasePage.java
│   │   │   └── BaseTest.java
│   │   ├── components
│   │   │   └── NavigationBar.java
│   │   ├── pages
│   │   │   ├── HomePage.java
│   │   │   └── LoginPage.java
│   │   └── scenarios
│   │       └── ExampleTest.java
│   └── resources
│       ├── log4j.properties
│       ├── reportportal.properties
│       └── selenide.properties
├── .gitignore
├── LICENSE
├── README.md
├── build.gradle
├── docker-compose.yml
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

## **How To Use**
### **Clone Repository**
> `$ git clone https://github.com/ngoanh2n/selenide-testng-reportportal-example.git`

### **Configuration Files**
You can change values for your case.

1. [log4j.properties](src/test/resources/log4j.properties)<br/>
    > Configuring log4j involves assigning the Level, defining Appender, and specifying Layout objects in a configuration file.
2. [selenide.properties](src/test/resources/selenide.properties)<br/>
    > Configuration settings for Selenide default browser. Settings can be set either via system property or programmatically.
3. [reportportal.properties](src/test/resources/reportportal.properties)<br/>
    > Store information for connecting to ReportPortal web service (Run with Docker in this case).

### **Deploy ReportPortal With Docker**
#### **Install Docker, Docker Machine**
<em>Bash completion is a bash function that allows you to auto complete commands or arguments by typing partially commands or arguments, then pressing the [Tab] key. This will help you when writing the bash command in terminal.</em><br/>
`$ brew install bash-completion`<br/>

<em>Docker for Mac is best installed with [Homebrew](https://brew.sh/) and [Homebrew Cask](https://github.com/Homebrew/homebrew-cask). For other ways to install on MacOS, see [Install Docker for Mac](https://docs.docker.com/docker-for-mac/install/) in Docker's docs.</em><br/>
`$ brew cask install docker`<br/>

<em>Download the [Docker Machine](https://docs.docker.com/machine/install-machine/) binary and extract it to your PATH.</em><br/>
`$ base=https://github.com/docker/machine/releases/download/v0.16.0 &&
  curl -L $base/docker-machine-$(uname -s)-$(uname -m) >/usr/local/bin/docker-machine &&
  chmod +x /usr/local/bin/docker-machine`<br/>

<em>To check the version type the following command.</em><br/>
> Mac:~ ngoanh2n`$ docker --version`<br/>
> Docker version 19.03.5, build 633a0ea<br/>
>
> Mac:~ ngoanh2n`$ docker-compose --version`<br/>
> docker-compose version 1.25.2, build 698e2846<br/>
>
> Mac:~ ngoanh2n`$ docker-machine --version`<br/>
> docker-machine version 0.16.0, build 702c267f<br/>

<em>Start Docker desktop community.</em><br/>
`$ open /Applications/Docker.app`<br/>

#### **Deploy ReportPortal**
By the following official instructions [here](https://reportportal.io/docs/Deploy-with-Docker), you can do that in shortly.

<em>Navigate repository root.</em><br/>
`$ cd selenide-testng-reportportal-example`<br/>

<em>Download the latest compose descriptor.</em><br/>
`$ curl https://raw.githubusercontent.com/reportportal/reportportal/master/docker-compose.yml -o docker-compose.yml`<br/>

<em>Start the application using the following command.</em><br/>
`$ docker-compose -p reportportal up -d --force-recreate`<br/>

#### **Setup ReportPortal UI**
<em>Get current IP on your mac machine (For ethernet in this case). </em><br/>
> Mac:~ ngoanh2n`$ ipconfig getifaddr en0`<br/>
> 192.168.1.5<br/>

Open your browser with an IP address of the deployed environment at port 8080. In my case: http://192.168.1.5:8080/ui/#login
![](images/login.png?raw=true)

Use below accounts to access.
> `default/1q2w3e`<br/>
> `superadmin/erebus`<br/>

Now, I use account `superadmin/erebus` to create a project named `selenide-testng-reportportal-example`

<em>`Note`: If nothing was occured when you logged in, please allocate RAM to 4GB and try again!</em>

Navigate to Projects Management in URL http://192.168.1.5:8080/ui/#administrate/projects
![](images/navigate-projects-management.png?raw=true)

Create a project.
![](images/create-project.png?raw=true)

Add a user as `ngoanh2n` to project `selenide-testng-reportportal-example`
![](images/add-user.png?raw=true)

Now I use account `ngoanh2n` to join project as role tester.
![](images/navigate-user-profile.png?raw=true)

Copy and replace personal properties into [reportportal.properties](src/test/resources/reportportal.properties) in resources folder.
![](images/user-profile.png?raw=true)

### **Run Tests With Gradle**
> `./gradlew clean test`<br/>

#### **Perform On Browsers**
- chrome
- firefox
- ie
- edge
- htmlunit
- phantomjs
- safari

> If run safari, you must enable the 'Allow Remote Automation' option in Safari's Develop menu to control Safari via WebDriver.

Able to select browser by passing system property `selenide.browser`<br/>
> `./gradlew clean test -Dselenide.browser=firefox`

#### **Filter Tests**
You can filter tests by using option `--tests`<br/>
Giving values can be `TestPackage`, `TestClass`, `TestMethod`
> `./gradlew clean test -Dselenide.browser=firefox --tests ExampleTest.homePageTest`

### **ReportPortal Results**
See test results as launches in URL http://192.168.1.5:8080/ui/#selenide-testng-reportportal-example/launches/all
![](images/launches.png?raw=true)
