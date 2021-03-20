using UnityEngine;
using UnityEngine.UI;
using System.Threading.Tasks;


public class GetERC20Balance : MonoBehaviour
{
    public Text tokenBalanceText;

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Start");
        UpdateBalance();
    }

    // Update is called once per frame
    void Update()
    {

    }

    async void UpdateBalance() {
        var balance = await BalanceOfERC20();
        Debug.Log($"I have {balance} Wei TEST Tokens");
        tokenBalanceText = GetComponent<Text>();
        tokenBalanceText.text = balance.ToString() + " Wei";
    }

    public static async Task<System.Numerics.BigInteger> BalanceOfERC20()
    {
        var matic = Settings.GetMatic();
        return await matic.BalanceOfERC20(Settings.FROM_ADDRESS, Settings.MATIC_TEST_TOKEN);
    }
}
