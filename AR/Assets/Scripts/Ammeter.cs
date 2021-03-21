using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ammeter : MonoBehaviour
{
    float current,resistance;
    public Terminal tplus,tminus;

    public float Current
    {
        get { return current; }
        set { current = value; }
    }
    public float getRes()
    {
        return resistance;
    }

    public void Start()
    {
        tplus.setPol('+');
        tminus.setPol('-');
    }

    public int calculateCurrent()
    {
        return 0;
    }

    public void moveNeedle()
    {

    }

    private void Update()
    {

    }
}