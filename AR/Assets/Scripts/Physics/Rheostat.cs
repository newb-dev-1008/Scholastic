using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rheostat : MonoBehaviour
{
    public PhysicsSceneManager physicsSceneManager;
    float minResistance = 100f;
    float maxResistance = 10000f;
    private bool isActive=false;
    private float voltage,current,resistance;
    public Terminal t1,t2,t3;
    private GameObject wiper;

    public float Current
    {
        get{return current;}
        set{current = value;}
    }
    void setVoltage(float voltage)
    {
        this.voltage = voltage;
    }
    float getVoltage()
    {
        return voltage;
    }
    void setResistance(float resistance)
    {
        this.resistance = resistance;
    }
    float getResistance()
    {
        return resistance;
    }
    void Start()
    {
        voltage = 0f;
        wiper = gameObject.transform.Find("wiper").gameObject;
        if(!t1 || !t2 || !t3 || !wiper)
        {
            Debug.LogError("RheostatComponent: One of the components are not linked!");
        }
        t1.setPol('n');
        t2.setPol('n');
        t3.setPol('n');
    }
   
    void Update()
    {
      
        
    }
    bool getActiveState()
    {
        return isActive;
    }
    void setActiveState(bool state)
    {
        if(!isActive && state)
        {
            isActive = true;
            updateRheostat();
        }
        else if(isActive && !state)
            {
            isActive = false;
            
            }  
    }

    void updateRheostat()
    {
        float lengthToMove = resistance*6f/(maxResistance-minResistance);
        Vector3 p = wiper.transform.localPosition;
        p.x = 3-lengthToMove;
        wiper.transform.localPosition= p;
    }

    private void OnMouseDown() {
        
    }
 
}
