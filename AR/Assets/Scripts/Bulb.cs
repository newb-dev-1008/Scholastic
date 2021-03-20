using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bulb : MonoBehaviour
{
    float current,resistance;
    public Material mat;
    public Terminal t1,t2;

    public float Current
    {
        get { return current; }
        set { current = value; }
    }
    
    public float getRes()
    {
        return resistance;
    }

    public void glow(float intensity)
    {   
        mat.SetColor("_Emission",mat.color*intensity);
    }

    private void Update()
    {
        if(current != 0)
        {
            glow(5f);
        }
        else
        {
            glow(-5f);
        }
    }

}
