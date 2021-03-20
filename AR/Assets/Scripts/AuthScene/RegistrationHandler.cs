using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Firebase.Auth;
using Proyecto26;
public class RegistrationHandler : MonoBehaviour
{
    public GameObject registrationCanvas;
    public GameObject loginCanvas;
    
    public InputField RegEmail;
    public InputField password;
    public InputField reEnterPass;
    public InputField userName;
    public InputField nameField;

    public string firebaseAdd;
    public static string UserName;
    public static string EmailID;
    public static string Name;
    public static int Coins;
    
    public static bool RegistrationComplete = false;

    

    public void RegisterUser()
    {
        if(RegEmail.text.Equals("") && password.text.Equals(""))
        {
            print("Enter Email/Password");
            return;
        }
        if (password.text != reEnterPass.text)
        {
            
            print("Passwords don't match");
            return;
        }
        FirebaseAuth.DefaultInstance.CreateUserWithEmailAndPasswordAsync(RegEmail.text,
            password.text).ContinueWith((task =>
        {
            if (task.IsCanceled)
            {
                Firebase.FirebaseException e =
                    task.Exception.Flatten().InnerExceptions[0] as Firebase.FirebaseException;
                GetErrorMessage((AuthError)e.ErrorCode);
                return;
            }
            if (task.IsFaulted)
            {
                Firebase.FirebaseException e =
                    task.Exception.Flatten().InnerExceptions[0] as Firebase.FirebaseException;
                GetErrorMessage((AuthError)e.ErrorCode);
                return;
            }
            
            if (task.IsCompleted)
            {
                print("Registration COMPLETE");
                RegistrationComplete = true;
                
                
            }
        }));
        
        UserName = userName.text;
        EmailID = RegEmail.text;
        Name = nameField.text;
        Coins = 50;
        PostToDatabase();
        GoToLoginCanvas();
    }
    
    
    void GetErrorMessage(AuthError errorCode)
    {
        string msg = "";
        msg = errorCode.ToString(); 
        print(msg);
    }
    
    public void PostToDatabase()
    {
        Debug.Log("yes");
        RegistrationInfo user = new RegistrationInfo();
        RestClient.Put(firebaseAdd+user.UserName+".json",user);
        GoToLoginCanvas();
        
    }

    public void GoToLoginCanvas()
    {
        registrationCanvas.SetActive(false);
        loginCanvas.SetActive(true);
    }
    
    
}
