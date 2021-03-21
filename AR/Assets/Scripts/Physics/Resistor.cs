using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Resistor : MonoBehaviour
{
    float current,resistance;
    Terminal t1,t2;

    public float Current
    {
        get { return current; }
        set { current = value; }
    }
    
    public float getRes()
    {
        return resistance;
    }

        public void setRes(float r)
    {
        resistance = r;
    }
}
