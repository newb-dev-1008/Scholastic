using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Connections : MonoBehaviour
{
    public GameObject circuit;
    [SerializeField] GameObject wire;
    GameObject lr;
    [SerializeField] Text text,text2;
    bool waitingForSecondObject = false;
    GameObject firstObject,secondObject=null;

    //This gameobject will only be active when form connections button on canvas is pressed

    // Update is called once per frame
    void Update()
    {   
        if(Input.touchCount>0)
        {
            text2.text = "clicked "+Input.touchCount;
            if(!waitingForSecondObject)
            {
                RaycastHit hit;
                if(Physics.Raycast(Camera.main.ScreenPointToRay(Input.GetTouch(0).position), out hit) && hit.collider.gameObject.tag == "Terminal" && (secondObject == null || hit.collider != secondObject.GetComponent<Collider>()))
                {
                    if(hit.collider.transform.parent.tag == "VoltageSource")
                        circuit.GetComponent<Circuit>().setVoltageSource(hit.collider.transform.parent.gameObject);
                    firstObject = hit.collider.gameObject;
                    text.text = "firstTap";
                    waitingForSecondObject = true;
                }
            }
            else if(waitingForSecondObject)
            {
                RaycastHit hit;
                if(Physics.Raycast(Camera.main.ScreenPointToRay(Input.GetTouch(0).position), out hit) && hit.collider.gameObject.tag == "Terminal" && hit.collider != firstObject.GetComponent<Collider>())
                {
                    secondObject = hit.collider.gameObject;
                    if(hit.collider.transform.parent.tag == "VoltageSource")
                        circuit.GetComponent<Circuit>().setVoltageSource(hit.collider.transform.parent.gameObject);
                    firstObject.GetComponent<Terminal>().setConnectTo(hit.collider.gameObject);
                    hit.collider.gameObject.GetComponent<Terminal>().setConnectTo(firstObject);
                    lr = Object.Instantiate(wire,firstObject.transform.parent);
                    lr.GetComponent<LineRenderer>().SetPosition(0,firstObject.transform.position);
                    lr.GetComponent<LineRenderer>().SetPosition(1,hit.collider.transform.position);
                    text.text = "secondTap";
                    waitingForSecondObject = false;
                }  
            }
        }
    }
}

