using UnityEngine;
using UnityEngine.SceneManagement;


public class SceneChangeHandler : MonoBehaviour
{
    public void GoToScene(string nameOfScene)
    {
    SceneManager.LoadScene(nameOfScene);
    }
}
