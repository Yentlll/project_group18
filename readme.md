# Mobile Security project -  documentation

## Groupmember
1. Yentl Sinnaeve
2. Aykan Ridvanov
3. Lowie Callebaut

## Project summary
A brief summary about the application

## Requirements
### ℹ️ Legend
- ✔️ = Implemented
- ❌ = Not implemented
- ⌛ = Work in progress

 
| Status | Description                     | Details |     |
| --- | --------------------------------| ------- | --- |
|✅| **Application**                       |         |     |
|✔️| 2 UI screens                          |         |     |
|✔️| Secure API request<br>                |         |     |
|✔️| API request with IDOR                 |         |     |
|✔️| Connection to room database           |         |     |
|✔️| Secure storage                        |         |     |
| |                                         |         |     |
|✅ | **Security**                         |         |     |
|✔️| Unsafe storage                        |         |     |
|✔️| Malware                               |         |     |
|✔️| Frida functionality                   |         |     |
|⌛| Functionality change decompile compile|         |     |
|✔️| Detect root and block functionality   |         |     |
| |                                       |         |     |



## Overview app
Describe the implementation of the following topics.

### ![](ReadmeImages/Screenshot.png) Screenshots
#### First Screen: Login Screen
* This screen is meant to login to the app using the Room database
* ![](ReadmeImages/login_screen.png)
#### Second Screen: Timeline Screen
* This Screen is used to render the timeline off the app making a request to a file
* ![](ReadmeImages/timeline_screen.png)
#### Third Screen: Users Screen
* This Screen is used to request a random Twatter user thats pulled from a json file online
* ![](ReadmeImages/user_screen.png)

----

### ![](ReadmeImages/API.png) Secure API request
#### The app makes a request to a .json file in a gitlab repository linked at: 
```https://gitlab.ti.howest.be/aykan.ridvanov/api_project_mobile/-/raw/main/tweets.json?ref_type=heads```
* ![](ReadmeImages/tweets_json_file.png)
* This gets displayed at the TimelineScreen in the application
* It is possible to intercept the request using burpsuite but when you try to modify it it doesn't work

----

### ![](ReadmeImages/API.png) API request with IDOR
#### The app makes a request to another .json file stored in:
```https://jsonplaceholder.typicode.com/users/{id}```
* ![](ReadmeImages/users_json.png)
* This is displayed at the UserScreen
* ![](ReadmeImages/display_user.png)
* When intercepting this request using burp suite you can change the id and you will get another user instead of a random user   

----

### ![](ReadmeImages/Database.png) Room database

#### When a user succesfully logs in. You see this screen:
* ![](ReadmeImages/Screenshot_succesfull_login.png)

* This make a call to the local room database to check if the user exists

#### You can also register a user. This puts data inside of the local database.

* This is how the database looks:
![](ReadmeImages/Screenshot_database.png)

----

### ![](ReadmeImages/Database.png) Secure storage
* ![](ReadmeImages/Secrures_storage_Encryption.png)
* Encrypts the sensitive userdata in the local storage
----

### ![](ReadmeImages/Database.png) Unsecure storage
#### We stored password using Base64 in our room database.
* This is how it looks inside of the DB:
* ![](ReadmeImages/Screenshot_unsafe_encryption.png)

----

### ![](ReadmeImages/Malware.png) Malware
* ![](ReadmeImages/Malware_Locations.png)
* The malware shows the location of the device every minute.

----

### ![](ReadmeImages/Root.png) Root
#### Root gets detected when trying to use the register button on the login screen
* This is done using the imported module RootBeer
* ![](ReadmeImages/root_detection.png)

----

### ![](ReadmeImages/Frida.png) Frida
#### Using Frida we bypassed the root detection that's blocks a functionality
* The Following script was used in frida
```
Java.perform(function () {
    var VM = Java.use("com.example.project_group18.ui.login.LoginScreenViewModel");
    VM.isRooted.implementation = function () {
        console.log("[+] isRooted() called — returning false");
        return false;
    };
});
``` 
* The following command is used to attach the script to the application
```
frida -U -p 3110 -l .\change-isrooted.js
```
![](ReadmeImages/frida_bypass.png)

----

## Link to Panopto video
https://howest.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=63d0d0bb-e32b-47da-a4b1-b3d80140ec53

----

## Repositories
- Code
  - https://gitlab.ti.howest.be/ti/2025-2026/s3/mobilesecurity/students/group-18/project_group18/-/tree/5a716a75b04acb4e5842a663498e7f7e645eff14/
- APK
  - https://gitlab.ti.howest.be/ti/2025-2026/s3/mobilesecurity/students/group-18/project_group18/-/blob/5a716a75b04acb4e5842a663498e7f7e645eff14/APK/twatter.apk
