# liferay-training

#### Verify jdk installation (Should be on JDK 17)
```
    java -version
```
#### Download node:
Download and install LTS version - https://nodejs.org
Run installer with default settings. Verify:
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
