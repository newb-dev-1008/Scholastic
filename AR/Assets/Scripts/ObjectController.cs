using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ObjectController : MonoBehaviour
{


    [SerializeField] Text text;
    [SerializeField] GameObject planeDiscoveryObject;

    private bool currentBehaviour = true;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void ChangeCurrentbehaviour()
    {
        if (currentBehaviour)
        {
            text.text = "Resume Detection";
            planeDiscoveryObject.SetActive(false);
            currentBehaviour = false;
        }

        if (!currentBehaviour)
        {
            text.text = "Stop Detection";
            planeDiscoveryObject.SetActive(true);
            currentBehaviour = true;
        }
    }
}
