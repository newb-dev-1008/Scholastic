using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class VoltageSource : MonoBehaviour
{
    //Apparatus Constants:
    float minVoltage = 0f;
    float maxVoltage = 440f;
    private bool isActive=false;
    private float voltage;
    public Terminal tplus,tminus;
    
    public Text voltageTextDisplay;
    public Material offLightMaterial,onLightMaterial;
    private GameObject switchButton, switchBulb,knob;
    
    // Start is called before the first frame update
    void Start()
    {
        switchButton = gameObject.transform.Find("switch").gameObject;
        switchBulb = gameObject.transform.Find("on_light_bulb").gameObject;
        knob = gameObject.transform.Find("knob_body").gameObject;
        voltage = 0f;
        tplus.setPol('+');
        tminus.setPol('-');
        if(!switchBulb || !switchButton || !knob || !voltageTextDisplay || !tplus || !tminus)
        {
            Debug.LogError("VoltageSourceComponent: One of the components are not linked!");
        }
        
    }
    float nextTime = 3f;


    // Update is called once per frame
    void Update()
    {
        //Test code, has nothing to do with actual code
        knob.transform.Rotate(Vector3.forward *2f *Time.deltaTime); //Rotates knob by small amount
        while(Time.time>nextTime)
        {
             Debug.LogError("Started");
            nextTime+=3f;
            voltage+=40f;//increments voltage
            updateVoltage();
            if(isActive){setActiveState(false);}//Switches it on/off
            else{setActiveState(true);}
        }

        
    }
    bool getActiveState()
    {
        return isActive;
    }
    void setActiveState(bool state)
    {
        if(!isActive && state)
        {
            //Machine is On;
            isActive = true;
            switchButton.transform.Rotate(Vector3.forward*38);
            switchBulb.GetComponent<Renderer>().sharedMaterial = onLightMaterial; 
            voltageTextDisplay.text = voltage.ToString();


        }
        else if(isActive && !state)
            {
            isActive = false;
            switchButton.transform.Rotate(Vector3.forward*-38);
            switchBulb.GetComponent<Renderer>().sharedMaterial = offLightMaterial;
            voltageTextDisplay.text = "OFF";
            }

        
    }
    void setVoltage(float voltage)
    {
        this.voltage = voltage;
    }
    void updateVoltage()
    {
        float degreeToRotate = voltage*240f/(maxVoltage-minVoltage);
        knob.transform.localRotation = Quaternion.Euler(0,0,degreeToRotate-120);
    }
}
