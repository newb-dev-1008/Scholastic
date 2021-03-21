using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Canvas_control : MonoBehaviour
{
    public void toggle(GameObject This, GameObject Next)
    {
        This.SetActive(false);
        Next.SetActive(true);
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
