using System.Threading.Tasks;
using UnityEngine;

public class BlockchainHandler : MonoBehaviour
{
    async public void UpdateBalance()
    {
        var balance = await BalanceOfERC20();
        Debug.Log($"I have {balance} Wei TEST Tokens");
        //tokenBalanceText = GetComponent<Text>();
        //tokenBalanceText.text = balance.ToString() + " Wei";
    }

    public static async Task<System.Numerics.BigInteger> BalanceOfERC20()
    {
        var matic = Setting1.GetMatic();
        return await matic.BalanceOfERC20(Setting1.FROM_ADDRESS, Setting1.MATIC_TEST_TOKEN);
    }

    async public void StartDepositTokens()
    {
        System.Numerics.BigInteger amount = 100;
        //var message = $"You will desposit {amount} Wei TEST Token";
        //GUI.TextField(new Rect(400, 400, 300, 200), message, 50);
        //Debug.Log("GUI Enabled");

        Debug.Log($"Started DespositToken: {amount}");
        await this.DespositToken(amount);
        Debug.Log($"Finished DespositToken: {amount}");

        // update balance text after waiting for 10 seconds
        Invoke("UpdateBalance", 10);
    }

    async Task DespositToken(System.Numerics.BigInteger amount)
    {
        var matic = Setting1.GetMatic();
        var from = Setting1.FROM_ADDRESS;
        var token = Settings.ROPSTEN_TEST_TOKEN;
        await matic.ApproveERC20TokensForDeposit(from, token, amount);
        Debug.Log($"ApproveERC20TokensForDeposit finished");
        await matic.DepositERC20Tokens(from, from, token, amount);
        Debug.Log($"DepositERC20Tokens finished");
    }
}
