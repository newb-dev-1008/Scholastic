using MaticNetwork.Net;

public static class Setting1
{
    public static string MATIC_PROVIDER = "https://testnet2.matic.network"; // This is the MATIC testnet RPC
    public static string PARENT_PROVIDER = "https://127.0.0.1:7545"; // This is the Ropsten testnet RPC
    public static string PRIVATE_KEY = "0xdf67bacc27e705b5b23b0156d99f8f32cc62a83bd1dc39580fd19b236679fa28"; // A sample private key prefix with `0x`
    public static string MATIC_TEST_TOKEN = "0xc82c13004c06E4c627cF2518612A55CE7a3Db699";
    public static string ROPSTEN_TEST_TOKEN = "0x70459e550254b9d3520a56ee95b78ee4f2dbd846";
    public static string FROM_ADDRESS = "0x4BBabBDc66761de5CFc810e0c667444001b07B70";// Your address
    public static IMatic matic = null;

    public static IMatic GetMatic()
    {
        if (matic == null)
        {
            matic = new Matic(Setting1.MATIC_PROVIDER, Setting1.PARENT_PROVIDER);
            matic.SetPrivateKey(Setting1.PRIVATE_KEY);
        }
        return matic;
    }
}
