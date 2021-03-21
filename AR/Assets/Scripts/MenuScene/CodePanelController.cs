using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CodePanelController : MonoBehaviour
{
    [SerializeField]private float speed = 30f;
    private Vector3 desiredScale ;
    private bool close = false;
    private void Awake()
    {
        transform.localScale = Vector3.zero;
    }

    void OnEnable()
    {
        desiredScale = Vector3.one;
    }

    public void Close()
    {
        desiredScale = Vector3.zero;
        close = true;
    }

    private void Update()
    {
        transform.localScale = Vector3.Lerp(transform.localScale, desiredScale, Time.deltaTime * speed);
        if (transform.localScale == Vector3.zero && close)
        {
            close = false;
            gameObject.SetActive(false);
        }
    }
}
