using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{
    private Touch touch;
    private float speedModifier = 0.002f;
   
    // Start is called before the first frame update
    void Start()
    {

    }


    private void Update()
    {

        if (Input.touchCount > 0)
        {


            touch = Input.GetTouch(0);

            if (touch.phase == TouchPhase.Moved)
            {
                transform.position = new Vector3(
                    transform.position.x + touch.deltaPosition.x * speedModifier,
                    transform.position.y,
                    transform.position.z + touch.deltaPosition.y * speedModifier);


            }
        }
    }

}
