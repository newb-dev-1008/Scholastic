using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class Manager : MonoBehaviour
{
    public GameObject cards;

    public GameObject hydrogen;
    public GameObject oxygen;
    public GameObject carbon;

    public GameObject content;
    public GameObject waterCard;


    public GameObject plane;


    public void planeDestroyer()
    {

        plane.SetActive(false);
    }


    private void Update()
    {
        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

        RaycastHit hit;

        if(Physics.Raycast(ray.origin, ray.direction ,out hit))
        {
            if(hit.collider.tag == "hydrogen" || hit.collider.tag == "oxygen" || hit.collider.tag == "carbon")
            {
                GameObject thisGO = hit.collider.gameObject;

                newSelection();
                thisGO.GetComponent<Outline>().enabled = true;
                thisGO.GetComponent<Movement>().enabled = true;
            }
        }
    }

    public void newSelection()
    {
        for(int i = 0; i < content.transform.childCount; i++)
        {
            content.transform.GetChild(i).transform.GetComponent<Outline>().enabled = false;
            content.transform.GetChild(i).transform.GetComponent<Movement>().enabled = false;
        }
       
    }

    public void cardsClick()
    {
        LeanTween.moveLocalY(cards, -484, 1f);
    }


    public void hydrogenClick()
    {
        LeanTween.moveLocalY(cards, -917, 1f);

        Instantiate(hydrogen, content.transform);
    }

    public void oxygenClick()
    {
        LeanTween.moveLocalY(cards, -917, 1f);

        Instantiate(oxygen, content.transform);
    }

    public void carbonClick()
    {
        LeanTween.moveLocalY(cards, -917, 1f);

        Instantiate(carbon, content.transform);
    }

    public void joinMol()
    {
        GameObject[] Hydrogens = GameObject.FindGameObjectsWithTag("hydrogen");
        GameObject[] oxygens = GameObject.FindGameObjectsWithTag("oxygen");
        GameObject[] carbons = GameObject.FindGameObjectsWithTag("carbon");

        if(Hydrogens.Length == 2 && oxygens.Length == 1)
        {
            LeanTween.move(Hydrogens[0],new Vector3(-0.3f, -0.15f, 0), 1f);
            LeanTween.move(Hydrogens[1], new Vector3(0.3f, -0.15f, 0), 1f);
            LeanTween.move(oxygens[0], Vector3.zero, 1f);

            newSelection();

            LeanTween.scaleY(waterCard, 3.628176f, 0.5f);
            
        }

        if(carbons.Length> 0 && Hydrogens.Length > 3)
        {
            LeanTween.move(carbons[0], Vector3.zero, 1f);
            LeanTween.move(Hydrogens[0], new Vector3(0f, -0.284f, 0), 1f);
            LeanTween.move(Hydrogens[1], new Vector3(-0.194f,-0.305f,-0.123f), 1f);
            LeanTween.move(Hydrogens[2], new Vector3(0.188f, -0.242f, -0.2f), 1f);
            LeanTween.move(Hydrogens[3], new Vector3(0, -0.259f, 0.209f), 1f);

            newSelection();

            LeanTween.scaleY(ch4Card, 3.628176f, 0.5f);
        }

    }

    public GameObject ch4Card;
}
