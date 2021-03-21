using MaticNetwork.Net;

public static class Settings
{
    public static string MATIC_PROVIDER = "https://testnet2.matic.network"; // This is the MATIC testnet RPC
    public static string PARENT_PROVIDER = "https://ropsten.infura.io/v3/70645f042c3a409599c60f96f6dd9fbc"; // This is the Ropsten testnet RPC
    //public static string PARENT_PROVIDER = "https://localhost:7545"; // This is the Ropsten testnet RPC
    public static string ROOTCHAIN_ADDRESS = "0x60e2b19b9a87a3f37827f2c8c8306be718a5f9b4"; // The address for the main Plasma contract in  Ropsten testnet
    public static string WITHDRAWMANAGER_ADDRESS = "0x4ef2b60cdd4611fa0bc815792acc14de4c158d22"; // An address for the WithdrawManager contract on Ropsten testnet
    public static string DEPOSITMANAGER_ADDRESS = "0x4072fab2a132bf98207cbfcd2c341adb904a67e9";  // An address for a DepositManager contract in Ropsten testnet
    public static string SYNCER_URL = "https://matic-syncer2.api.matic.network/api/v1"; // Backend service which syncs the Matic sidechain state to a MySQL database which we use for faster querying. This comes in handy especially for constructing withdrawal proofs while exiting assets from Plasma.
    public static string WATCHER_URL = "https://ropsten-watcher2.api.matic.network/api/v1"; // Backend service which syncs the Matic Plasma contract events on Ethereum mainchain to a MySQL database which we use for faster querying. This comes in handy especially for listening to asset deposits on the Plasma contract.
    public static string ROOTWETH_ADDRESS = "0x421dc9053cb4b51a7ec07b60c2bbb3ec3cfe050b";  // This is a wrapped ETH ERC20 contract address so that we can support ETH deposits to the sidechain
    public static string MATICWETH_ADDRESS = "0x31074c34a757a4b9FC45169C58068F43B717b2D0"; // The corresponding wrapped ETH ERC20 contract address on the Matic chain
    public static string PRIVATE_KEY = "0xdf67bacc27e705b5b23b0156d99f8f32cc62a83bd1dc39580fd19b236679fa28"; // A sample private key prefix with `0x`
    public static string FROM_ADDRESS = "0x4BBabBDc66761de5CFc810e0c667444001b07B70";// Your address
    public static string TO_ADDRESS = "0x8c1C9d0012B8E970193DBAA15d668361C187305b";// receipent address
    public static string ROPSTEN_TEST_TOKEN = "0x70459e550254b9d3520a56ee95b78ee4f2dbd846"; // Contract for ERC20 in Ropsten
    public static string MATIC_TEST_TOKEN = "0xc82c13004c06E4c627cF2518612A55CE7a3Db699"; // Contract for ERC20 in Matic testnet
    public static string ROPSTEN_ERC721_TOKEN = "0x07d799252cf13c01f602779b4dce24f4e5b08bbd"; // Contract for ERC721 in Ropsten testnet
    public static string MATIC_ERC721_TOKEN = "0x9f289a264b6db56d69ad53f363d06326b984e637"; // Contract for ERC721 in matic testnet

    public static IMatic matic = null;

    public static IMatic GetMatic()
    {
        if (matic == null) {
            matic = new Matic(Settings.MATIC_PROVIDER, Settings.PARENT_PROVIDER, Settings.SYNCER_URL, Settings.WATCHER_URL, Settings.ROOTCHAIN_ADDRESS, Settings.MATICWETH_ADDRESS, Settings.WITHDRAWMANAGER_ADDRESS, Settings.DEPOSITMANAGER_ADDRESS);
                    matic.SetPrivateKey(Settings.PRIVATE_KEY);
        }
        return matic;
    }

}

/*
 Available Accounts
==================
(0) 0xef15798b548e75AD7951644452D7929B84C395Fd (100 ETH)
(1) 0x2019Fb681FD3558CB8FcCA4671B3A6fab5502f84 (100 ETH)
(2) 0x1f59FD6B7DC5567e6826c43600C08bA621b45803 (100 ETH)
(3) 0x618096330dF6CA7cA3Ee621B3C3D9eAd655EbaBf (100 ETH)
(4) 0x617682e0fdb8D5Bc443ad506E9C7Cd916EbC3b85 (100 ETH)
(5) 0x5f6B1f59a255E86d084C97ad91DF849306B01d61 (100 ETH)
(6) 0xE20e22b48fbC25031F6ad9322C20410a28df3064 (100 ETH)
(7) 0x3204dDCAB39c2Ef3105bC8D46CEA0C4C256ab358 (100 ETH)
(8) 0x9bce9D9BE97c0B47008FA2E0ec1492fCb840017A (100 ETH)
(9) 0xd25660FA733AB557fd96454b85570Dd2052f6dD9 (100 ETH)

Private Keys
==================
(0) 0xbc811eece176e2e71e924f0e88363f9910ee3f549c296b87e5ed24b02eab1f48
(1) 0x2909d24463c12f9c0ed201f65bb09d2782a0945dcb3ef3dfdd8f4fea637af341
(2) 0xa19efdbb68375a0308f62317538e51544502106fe94f5efc0780bab3b542950c
(3) 0x00fe099ce2b4d144dc8f84bf64c8cd5c34a2a03d6a374b6abbd34d306cfec66e
(4) 0x32677159a24b059643ee3dbe9c3470452cba213c26c9cf486c22f4e1f91d08a4
(5) 0xbb2cb4465a931afefd2822b54d6a67ae0b188af50a157c1d89d39c53a4e131e0
(6) 0x08e923b73a2e3ebee6a89cf3938474f8e228f31169678883e10d2b61b675437d
(7) 0x2279f3a791d93d975db067d915c9abfa0b4128e8d998e0a549e4530fd4f65a27
(8) 0xb910d0817b6df55758c6d41e742fa383093b1774af905a7c793080160bc84c51
(9) 0xe8a7d739b0b9e82d7b66d5675aaba5e49e4f3925470b6fd040695e0e63e5aeae

HD Wallet
==================
Mnemonic:      artwork nature any equal rather rapid urban style require polar topic addict
Base HD Path:  m/44'/60'/0'/0/{account_index}

Gas Price
==================
20000000000

Gas Limit
==================
6721975

Call Gas Limit
==================
9007199254740991
     */

