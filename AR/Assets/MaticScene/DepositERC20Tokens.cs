using System.Threading.Tasks;
using UnityEngine;
using UnityEngine.UI;


public class DepositERC20Tokens : MonoBehaviour
{
    InputField despositeValue;

    void Start()
    {
        
    }

    void Update()
    {

    }

    public void OnGUI() {
        Event e = Event.current;
        if (e.type == EventType.KeyDown && e.keyCode == KeyCode.Return)
        {
            despositeValue = GetComponent<InputField>();
            var amount = System.Numerics.BigInteger.Parse(despositeValue.text);
            StartDepositTokens(amount);
        }
    }

    async void StartDepositTokens(System.Numerics.BigInteger amount)
    {
        var message = $"You will desposit {amount} Wei TEST Token";
        GUI.TextField(new Rect(400, 400, 300, 200), message, 50);
        //Debug.Log("GUI Enabled");

        Debug.Log($"Started DespositToken: {amount}");
        await this.DespositToken(amount);
        Debug.Log($"Finished DespositToken: {amount}");

        // update balance text after waiting for 10 seconds
        Invoke("UpdateBalance", 10);
    }

    async Task DespositToken(System.Numerics.BigInteger amount)
    {
        var matic = Settings.GetMatic();
        var from = Settings.FROM_ADDRESS;
        var token = Settings.ROPSTEN_TEST_TOKEN;
        await matic.ApproveERC20TokensForDeposit(from, token, 100);
        Debug.Log($"ApproveERC20TokensForDeposit finished");
        await matic.DepositERC20Tokens(from, from, token, amount);
        Debug.Log($"DepositERC20Tokens finished");
    }

    async Task UpdateBalance() {
        Text balanceValue = GameObject.Find("Balance Value").GetComponent<Text>();
        balanceValue.text = (await GetERC20Balance.BalanceOfERC20()).ToString() + " Wei";
        Debug.Log($"Update Balance: {balanceValue.text}");
    }

}
