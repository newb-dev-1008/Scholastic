using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using IBM.Watson.Examples;
using UnityEngine.SceneManagement;


public class PhysicsSceneManager : MonoBehaviour
{
    //STATES
    // 1=put powerx
    // 2=put rheostatx
    // 3 =put ammeterx
    // 4 =connect Power supply to rheostat
    // 5 = connect rheostat to ammeter
    // 6 = connect ammeter to power supply;
    // 7 = Turn on power supply
    // 8 = Run;

    public int state;
    public GameObject rheostat, ammeter, power, c1, c2, c3;
    public bool isRheostat, isAmmeter, isPower;
    public Text powerText, ammeterText, instructions;
    public ExampleTextToSpeechV1 speechV1;
    public int wrongSteps = 0;
    public BlockchainHandler blockchain;
    // Start is called before the first frame update
    void Start()
    {
        state = 0;
        GoToNextState();
        blockchain.StartDepositTokens();

    }

    // Update is called once per frame
    void Update()
    {

    }

    public void MakeConnections(GameObject g)
    {
        Debug.Log(g.name);
        if (g.name == "rheostat")
        {
            if (state == 4 || state == 5)
            {
                isRheostat = true;
            }
            else
            {
                WrongStep();
            }

        }
        else if (g.name == "voltage_source")
        {
            if (state == 4 || state == 6 || state == 7)
            {
                isPower = true;
            }
            else
            {
                WrongStep();
            }
        }
        else if (g.name == "ammeter")
        {
            if (state == 6 || state == 5)
            {
                isAmmeter = true;
            }
            else
            {
                WrongStep();
            }
        }
        if ((state == 4 && isPower && isRheostat) ||
        (state == 5 && isAmmeter && isRheostat) ||
        (state == 6 && isPower && isAmmeter) ||
        (state == 7 && isPower))
        {
            isAmmeter = false;
            isPower = false;
            isRheostat = false;
            GoToNextState();
        }
    }
    public void instantiateObjects(Button button)
    {
        switch (button.name)
        {
            case "Power":
                if (state == 1)
                {
                    power.SetActive(true);
                    GoToNextState();
                }
                else
                {
                    WrongStep();
                }
                break;
            case "Ammeter":
                if (state == 3)
                {
                    ammeter.SetActive(true);
                    GoToNextState();
                }
                else
                {
                    WrongStep();
                }
                break;
            case "Rheostat":
                if (state == 2)
                {
                    rheostat.SetActive(true);
                    GoToNextState();
                }
                else
                {
                    WrongStep();
                }
                break;
            default:
                Debug.Log("NOBUTTON");
                break;
        }
    }
    public void GoToNextState()
    {
        state++;
        if (state == 1)
        {
            instructions.text = "Place Voltage Source";
        }
        if (state == 2)
        {
            instructions.text = "Place Rheostat";
        }
        if (state == 3)
        {
            instructions.text = "Place Ammeter";
        }
        if (state == 4)
        {
            instructions.text = "Connect Voltage Source to Rheostat";
        }
        if (state == 5)
        {
            c1.SetActive(true);
            instructions.text = "Connect Ammeter to Rheostat";

        }
        else if (state == 6)
        {
            c2.SetActive(true);
            instructions.text = "Connect Volatage Source to Ammeter";

        }
        else if (state == 7)
        {
            instructions.text = "Switch On Voltage Source";
            c3.SetActive(true);

        }
        else if (state == 8)
        {
            instructions.text = "Score Saved. Record Ammeter Readings";
            powerText.text = "ON";
            ammeterText.text = "2.8A";
            blockchain.StartDepositTokens();
        }
        else if (state == 9)
        {
            StartCoroutine(WaitForSceneLoad());

        }
        speechV1.Speak(instructions.text);
    }
    public void WrongStep()
    {
        speechV1.Speak("Incorrect");
        wrongSteps++;

    }
    private IEnumerator WaitForSceneLoad()
    {
        yield return new WaitForSeconds(3);
        SceneManager.LoadScene("QuizScene");

    }
}
