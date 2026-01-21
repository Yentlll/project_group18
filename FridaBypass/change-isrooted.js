Java.perform(function () { //this tells frida: run this code inside Android app's Java VM, when its ready
    var VM = Java.use("com.example.project_group18.ui.login.LoginScreenViewModel"); //loads java class reference from running app

    VM.isRooted.implementation = function () { //refers to isRooted value in code and replaces it with the function below
        console.log("[+] isRooted() called — returning false");// logs in the console when the isRooted is called, e.g. when clicking registration button
        return false; //replaces the original isRooted to be always false
    };
});