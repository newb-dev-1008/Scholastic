using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Voltage_Source : MonoBehaviour
{
    bool isOn;
    float volt=5;
    public Terminal tplus,tminus;

    public float getVolt()
    {
        return volt;
    }

    private void Start() {
        tplus.setPol('+');
        tminus.setPol('-');
    }

    public void turnOn()
    {
        isOn = true;
    }

    public void turnOff()
    {
        isOn = false;
    }
}
