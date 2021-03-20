using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Toucher : MonoBehaviour
{
    public PhysicsSceneManager physicsSceneManager;
    // Start is called before the first frame update
    /// <summary>
    /// OnMouseDown is called when the user has pressed the mouse button while
    /// over the GUIElement or Collider.
    /// </summary>
    void OnMouseDown()
    {
        physicsSceneManager.MakeConnections(gameObject);
    }
}
