using System.Collections;
using System.Collections.Generic;
using System.Numerics;
using UnityEngine;
using UnityEngine.UI;

public class TransferERC20Token : MonoBehaviour
{
	void Start () {
		Button transferButton = GetComponent<Button>();
		transferButton.onClick.AddListener(TransferTokens);
	}

    // Update is called once per frame
    void Update()
    {

    }

    async void TransferTokens() {
        var matic = Settings.GetMatic();
        var from = Settings.FROM_ADDRESS;
        var token = Settings.MATIC_TEST_TOKEN;

        Text transferAddress = GameObject.Find("Transfer Address Value").GetComponent<Text>();
        var to = transferAddress.text;
        Text transferAmout = GameObject.Find("Transfer Amount Value").GetComponent<Text>();
        var amount = BigInteger.Parse(transferAmout.text);

        var balance = await matic.BalanceOfERC20(from, token);
        Debug.Log($"Test Token Balance is {balance}");

        await matic.TransferTokens(from, token, to, amount);
        Debug.Log($"TransferTokens finished");

        balance = await matic.BalanceOfERC20(from, token);
        Debug.Log($"Test Token Balance is {balance}");
    }
}
