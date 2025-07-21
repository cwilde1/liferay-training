# liferay-training

#### Verify jdk installation (should be on JDK 17 at least)
```
    java -version
```
#### Download node:
https://nodejs.org
Download and install LTS version with default settings, then verify:
```
    node --version
    npm --version
```
#### install blade cli
```
    npm install -g blade-cli
```
#### navigate to your dev directory
```
    mkdir C:\devOpen command prompt and run:
    cd C:\dev
```
#### init workspace
```
    blade init --liferay-version 7.4 --java-version 11 liferay-training
    cd liferay-training 
```
#### download and start liferay
```
    blade server init
    blade server start
```
#### open liferay in browser
````
    http://localhost:8080
````
#### login with default admin user
```
    user: test
    password: test
```
