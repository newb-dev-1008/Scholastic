using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Circuit : MonoBehaviour
{
    [SerializeField]Text text,text1,text2;
    public GameObject canvas1, canvas2, close_connection, stop_circuit; //buttons
    public GameObject planeDetection; //for detecting the ground
    public Canvas_control canvas_control; //for toggling buttons n canvases
    List<GameObject> comps; //contains all components present in a scene
    List<components> inCircuit; //contains all components present in circuit
    bool isCircuitConnected=false;
    int  isCurrentFlowing=0;
    float totalRes=0,current=0;
    [SerializeField]GameObject connections;
    GameObject VoltageSource = null;

    private void Start()
    {
        inCircuit = new List<components>();
    }

    public void setVoltageSource(GameObject vs)
    {
        VoltageSource = vs;
    }

    public void makeConnection()
    {
        connections.SetActive(true);
        canvas_control.toggle(canvas1,close_connection);
    }

    public void stopConnection()
    {
        connections.SetActive(false);
        canvas_control.toggle(close_connection,canvas1);
    }

    public bool traverse(GameObject iterator) //function that traverse from a voltage source and checks for all the components in a closed circuit with it
    {
        canvas_control.toggle(canvas1,stop_circuit);
        if(iterator==null)
            return false;
        do
        {
            Transform term=null;
            foreach(Transform child in iterator.transform) // to find the terminalin the gameobject
            {
                if(child.gameObject.tag == "Terminal" && !child.GetComponent<Terminal>().Traversed)
                {    
                    term = child;
                }
            }
            if(term==null)
                return false;
            term.gameObject.GetComponent<Terminal>().Traversed = true;
            iterator = term.GetComponent<Terminal>().connectedTo();
            if(iterator == null)
                return false;
            else
            {   text.text = "in else";
                text2.text = "" + iterator.tag;
                inCircuit.Add(new components { component = iterator, Type = iterator.GetComponent<typeComp>() });
                text1.text = "component added";
            }
        } while(iterator.tag != "VoltageSource");
        return true;
    }

    public void startCircuit()
    {
        if(traverse(VoltageSource))
        {
            isCircuitConnected = true;
            isCurrentFlowing =1;
            PlayerPrefs.SetInt("isCurrentFlowing",isCurrentFlowing);
            text.text = "" + isCurrentFlowing;
            calculateRes();
            setInCircuit(current);
        }
    }

    public void stopCircuit()
    {
        isCurrentFlowing = 0;
        PlayerPrefs.SetInt("isCurrentFlowing",isCurrentFlowing);
        canvas_control.toggle(stop_circuit,canvas1);
        setInCircuit(0);
    }

    void setInCircuit(float value)
    {
        text1.text = "loop error"; 
        foreach(components ele in inCircuit)
        {
            text1.text = "starting switch";
            //    all the types of functions performed when current flows by respective components are declared by this switch case
            int i = (int) ele.Type;
            switch(i)
            {
                case 0 : 
                    break;
                case 1 : 
                    ele.component.GetComponent<Ammeter>().Current = value;
                    text1.text = "moving";
                    break;
                case 2 : 
                    ele.component.GetComponent<Resistor>().Current = value;

                    break;
                case 3 : 
                    text1.text = "glowing";
                    ele.component.GetComponent<Bulb>().Current = value;
                    break;
                default : break;
            }
        }
    }

    void calculateRes()
    {
        
        text1.text = "loop error"; 
        foreach(components ele in inCircuit)
        {
            text1.text = "starting switch";
            //    all the types of functions performed when current flows by respective components are declared by this switch case
            int i = (int) ele.Type;
            switch(i)
            {
                case 0 : 
                    break;
                case 1 : 
                    ele.component.GetComponent<Ammeter>().getRes();
                    text1.text = "moving";
                    break;
                case 2 : 
                    ele.component.GetComponent<Resistor>().getRes();

                    break;
                case 3 : 
                    text1.text = "glowing";
                    ele.component.GetComponent<Bulb>().getRes();
                    break;
                default : break;
            }
        }
        current = (VoltageSource.GetComponent<Voltage_Source>().getVolt())/totalRes;
        PlayerPrefs.SetFloat("current",current);
    }

    public void startPlaceObjects()
    {
        planeDetection.SetActive(true);
        canvas_control.toggle(canvas1,canvas2);
    }

        public void stopPlaceObjects()
    {
        planeDetection.SetActive(false);
        canvas_control.toggle(canvas2,canvas1);
    }
    
    public class components
    {
        public GameObject component;
        public typeComp Type;
    }

    public enum typeComp //use this enumeration to declare all the types of commponents
    {
        Voltage_Source, Ammeter, Resistor, Bulb
    }
}
