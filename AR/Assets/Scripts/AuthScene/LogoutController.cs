using UnityEngine;
using Firebase.Auth;
using UnityEngine.SceneManagement;

public class LogoutController : MonoBehaviour
{
    public void Logout()
    {
        if(FirebaseAuth.DefaultInstance.CurrentUser != null)
        {
            FirebaseAuth.DefaultInstance.SignOut();
            print("User is Logged Out");
        }

        SceneManager.LoadScene("AuthScene");
    }
}
