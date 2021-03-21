using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Terminal : MonoBehaviour
{
    char polarity = 'n';
    bool traversed=false;
    GameObject connectTo;

    public void setPol(char p)
    {
        polarity = p;
    }

    public char getPol()
    {
        return polarity;
    }
    
    public bool Traversed
    {
        get { return traversed; }
        set { traversed = value; }
    }

    public void setConnectTo(GameObject game)
    {
        this.connectTo = game;
    }

    public GameObject connectedTo()
    {
        connectTo.GetComponent<Terminal>().traversed = true;
        return connectTo.transform.parent.gameObject;
    }

}
