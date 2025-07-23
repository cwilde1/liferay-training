# Liferay Training

## Installation Instructions
#### Verify jdk installation (should be on JDK 17 at least)
https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
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
    user: test@liferay.com
    password: test
`````

## Liferay Gogo Shell Commands

Liferay includes the Gogo shell, a command-line tool for interacting with the OSGi runtime. You can access it from the Liferay server console or by connecting via telnet (default port 11311).

### Common Gogo Shell Commands

- `lb`  
  Lists all OSGi bundles and their states (Installed, Active, etc).

- `services`  
  Lists all registered OSGi services.

- `help`  
  Shows available commands and their usage.

- `scr:list`  
  Lists all OSGi Declarative Services components.

- `scr:info <component name>`  
  Shows details about a specific OSGi component.

- `log:get`  
  Displays recent log entries from the OSGi log service.

- `log:tail`  
  Continuously displays new log entries as they occur.

- `dm wtf`  
  Shows diagnostic information about dependency management issues.

- `dm na`  
  Lists bundles that are not active and their dependency issues.

- `bundle:start <bundle-id>`  
  Starts a specific OSGi bundle by ID.

- `bundle:stop <bundle-id>`  
  Stops a specific OSGi bundle by ID.

- `bundle:refresh <bundle-id>`  
  Refreshes a specific OSGi bundle (useful after code changes).

- `bundle:update <bundle-id>`  
  Updates a specific OSGi bundle.

- `headers <bundle-id>`  
  Shows the manifest headers of a specific bundle.

- `packages <bundle-name>`  
  Lists packages exported/imported by a bundle.

- `inspect capability service <bundle-id>`  
  Shows services provided by a specific bundle.

- `inspect requirement service <bundle-id>`  
  Shows service dependencies of a specific bundle.

- `felix:bundlelevel <bundle-id>`  
  Shows the start level of a specific bundle.

- `system:property`  
  Lists all system properties.

- `system:property <property-name>`  
  Shows the value of a specific system property.

- `threads`  
  Displays information about running threads.

- `gc`  
  Forces garbage collection.

- `meminfo`  
  Shows memory usage information.

- `shutdown`  
  Shuts down the Liferay server.

### How to Access

- **From the Liferay console:**
  Just type the commands directly if you see the `g!` prompt.
- **Via telnet:**
  Connect using `telnet localhost 11311` and then enter commands at the `g!` prompt.

For more information, see the [Liferay Gogo Shell documentation](https://learn.liferay.com/dxp/latest/en/developing-applications/core-frameworks/gogo-shell.html).


