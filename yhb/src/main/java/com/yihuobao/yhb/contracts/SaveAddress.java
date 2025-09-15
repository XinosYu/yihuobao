package com.yihuobao.yhb.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint64;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.CallCallback;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class SaveAddress extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3611440806101146000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c80639b2a88dd1161005b5780639b2a88dd14610101578063c3d0266a14610132578063d40fe8121461014e578063f2fde38b1461016a5761007d565b80630d392cd91461008257806311dd88451461009e5780633af32abf146100d1575b600080fd5b61009c60048036038101906100979190610c09565b610186565b005b6100b860048036038101906100b39190610cae565b6102bf565b6040516100c89493929190610d65565b60405180910390f35b6100eb60048036038101906100e69190610daa565b610352565b6040516100f89190610de6565b60405180910390f35b61011b60048036038101906101169190610e2d565b6103a8565b604051610129929190610e8d565b60405180910390f35b61014c60048036038101906101479190610e2d565b6103fb565b005b61016860048036038101906101639190610f62565b61062b565b005b610184600480360381019061017f9190610daa565b610951565b005b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610216576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020d90611040565b60405180910390fd5b80600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff167ff93f9a76c1bf3444d22400a00cb9fe990e6abe9dbb333fda48859cfee864543d826040516102b39190610de6565b60405180910390a25050565b60008060008060008087876040516102d892919061109f565b9081526020016040518091039020905080600001548160010160009054906101000a900467ffffffffffffffff168260010160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683600101601c9054906101000a900463ffffffff1694509450945094505092959194509250565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60008060008086866040516103be92919061109f565b90815260200160405180910390209050838160000154148160010160009054906101000a900467ffffffffffffffff169250925050935093915050565b600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16806104a05750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b6104df576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104d690611104565b60405180910390fd5b60008084846040516104f292919061109f565b908152602001604051809103902090506000429050828260000181905550808260010160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550338260010160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600182600101601c8282829054906101000a900463ffffffff166105a29190611153565b92506101000a81548163ffffffff021916908363ffffffff16021790555084846040516020016105d392919061109f565b604051602081830303815290604052805190602001207f155a4316031974f19f85b0776d53f6bde4363bc29f511003d9e347dce69532f684833360405161061c9392919061118d565b60405180910390a25050505050565b600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16806106d05750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b61070f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161070690611104565b60405180910390fd5b818190508484905014610757576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161074e90611210565b60405180910390fd5b606484849050111561079e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107959061127c565b60405180910390fd5b6000429050600085859050905060005b818110156108e5573660008888848181106107cc576107cb61129c565b5b90506020028101906107de91906112da565b9150915060008083836040516107f592919061109f565b908152602001604051809103902090508787858181106108185761081761129c565b5b905060200201358160000181905550858160010160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff160217905550338160010160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600181600101601c8282829054906101000a900463ffffffff166108b99190611153565b92506101000a81548163ffffffff021916908363ffffffff1602179055508360010193505050506107ae565b506040516020016108f590611389565b604051602081830303815290604052805190602001207f155a4316031974f19f85b0776d53f6bde4363bc29f511003d9e347dce69532f68260001b84336040516109419392919061118d565b60405180910390a2505050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146109e1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109d890611040565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610a51576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a48906113ea565b60405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610b9e82610b73565b9050919050565b610bae81610b93565b8114610bb957600080fd5b50565b600081359050610bcb81610ba5565b92915050565b60008115159050919050565b610be681610bd1565b8114610bf157600080fd5b50565b600081359050610c0381610bdd565b92915050565b60008060408385031215610c2057610c1f610b69565b5b6000610c2e85828601610bbc565b9250506020610c3f85828601610bf4565b9150509250929050565b600080fd5b600080fd5b600080fd5b60008083601f840112610c6e57610c6d610c49565b5b8235905067ffffffffffffffff811115610c8b57610c8a610c4e565b5b602083019150836001820283011115610ca757610ca6610c53565b5b9250929050565b60008060208385031215610cc557610cc4610b69565b5b600083013567ffffffffffffffff811115610ce357610ce2610b6e565b5b610cef85828601610c58565b92509250509250929050565b6000819050919050565b610d0e81610cfb565b82525050565b600067ffffffffffffffff82169050919050565b610d3181610d14565b82525050565b610d4081610b93565b82525050565b600063ffffffff82169050919050565b610d5f81610d46565b82525050565b6000608082019050610d7a6000830187610d05565b610d876020830186610d28565b610d946040830185610d37565b610da16060830184610d56565b95945050505050565b600060208284031215610dc057610dbf610b69565b5b6000610dce84828501610bbc565b91505092915050565b610de081610bd1565b82525050565b6000602082019050610dfb6000830184610dd7565b92915050565b610e0a81610cfb565b8114610e1557600080fd5b50565b600081359050610e2781610e01565b92915050565b600080600060408486031215610e4657610e45610b69565b5b600084013567ffffffffffffffff811115610e6457610e63610b6e565b5b610e7086828701610c58565b93509350506020610e8386828701610e18565b9150509250925092565b6000604082019050610ea26000830185610dd7565b610eaf6020830184610d28565b9392505050565b60008083601f840112610ecc57610ecb610c49565b5b8235905067ffffffffffffffff811115610ee957610ee8610c4e565b5b6020","83019150836020820283011115610f0557610f04610c53565b5b9250929050565b60008083601f840112610f2257610f21610c49565b5b8235905067ffffffffffffffff811115610f3f57610f3e610c4e565b5b602083019150836020820283011115610f5b57610f5a610c53565b5b9250929050565b60008060008060408587031215610f7c57610f7b610b69565b5b600085013567ffffffffffffffff811115610f9a57610f99610b6e565b5b610fa687828801610eb6565b9450945050602085013567ffffffffffffffff811115610fc957610fc8610b6e565b5b610fd587828801610f0c565b925092505092959194509250565b600082825260208201905092915050565b7f4f6e6c79206f776e657200000000000000000000000000000000000000000000600082015250565b600061102a600a83610fe3565b915061103582610ff4565b602082019050919050565b600060208201905081810360008301526110598161101d565b9050919050565b600081905092915050565b82818337600083830152505050565b60006110868385611060565b935061109383858461106b565b82840190509392505050565b60006110ac82848661107a565b91508190509392505050565b7f556e617574686f72697a65640000000000000000000000000000000000000000600082015250565b60006110ee600c83610fe3565b91506110f9826110b8565b602082019050919050565b6000602082019050818103600083015261111d816110e1565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061115e82610d46565b915061116983610d46565b92508263ffffffff0382111561118257611181611124565b5b828201905092915050565b60006060820190506111a26000830186610d05565b6111af6020830185610d28565b6111bc6040830184610d37565b949350505050565b7f4172726179206d69736d61746368000000000000000000000000000000000000600082015250565b60006111fa600e83610fe3565b9150611205826111c4565b602082019050919050565b60006020820190508181036000830152611229816111ed565b9050919050565b7f457863656564206261746368206c696d69740000000000000000000000000000600082015250565b6000611266601283610fe3565b915061127182611230565b602082019050919050565b6000602082019050818103600083015261129581611259565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b600080fd5b600080fd5b600080833560016020038436030381126112f7576112f66112cb565b5b80840192508235915067ffffffffffffffff821115611319576113186112d0565b5b602083019250600182023603831315611335576113346112d5565b5b509250929050565b7f62617463685f7570646174650000000000000000000000000000000000000000600082015250565b6000611373600c83611060565b915061137e8261133d565b600c82019050919050565b600061139482611366565b9150819050919050565b7f496e76616c696420616464726573730000000000000000000000000000000000600082015250565b60006113d4600f83610fe3565b91506113df8261139e565b602082019050919050565b60006020820190508181036000830152611403816113c7565b905091905056fea2646970667358221220b81ea4ca0c32d2439dae02b82a680315a9e4bad9cd058974c1931ecafddce10964736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"traceCodeHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"dataHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"uint64\",\"name\":\"timestamp\",\"type\":\"uint64\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"submitter\",\"type\":\"address\"}],\"name\":\"HashUpdated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"previousOwner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"status\",\"type\":\"bool\"}],\"name\":\"WhitelistUpdated\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"string[]\",\"name\":\"traceCodes\",\"type\":\"string[]\"},{\"internalType\":\"bytes32[]\",\"name\":\"dataHashes\",\"type\":\"bytes32[]\"}],\"name\":\"batchSubmit\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"traceCode\",\"type\":\"string\"}],\"name\":\"getRecord\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"dataHash\",\"type\":\"bytes32\"},{\"internalType\":\"uint64\",\"name\":\"timestamp\",\"type\":\"uint64\"},{\"internalType\":\"address\",\"name\":\"submitter\",\"type\":\"address\"},{\"internalType\":\"uint32\",\"name\":\"updateCount\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"isWhitelisted\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"traceCode\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"dataHash\",\"type\":\"bytes32\"}],\"name\":\"submitHash\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"},{\"internalType\":\"bool\",\"name\":\"status\",\"type\":\"bool\"}],\"name\":\"updateWhitelist\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"traceCode\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"dataHash\",\"type\":\"bytes32\"}],\"name\":\"verifyIntegrity\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"isValid\",\"type\":\"bool\"},{\"internalType\":\"uint64\",\"name\":\"timestamp\",\"type\":\"uint64\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_BATCHSUBMIT = "batchSubmit";

    public static final String FUNC_GETRECORD = "getRecord";

    public static final String FUNC_ISWHITELISTED = "isWhitelisted";

    public static final String FUNC_SUBMITHASH = "submitHash";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UPDATEWHITELIST = "updateWhitelist";

    public static final String FUNC_VERIFYINTEGRITY = "verifyIntegrity";

    public static final Event HASHUPDATED_EVENT = new Event("HashUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event WHITELISTUPDATED_EVENT = new Event("WhitelistUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    protected SaveAddress(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<HashUpdatedEventResponse> getHashUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(HASHUPDATED_EVENT, transactionReceipt);
        ArrayList<HashUpdatedEventResponse> responses = new ArrayList<HashUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            HashUpdatedEventResponse typedResponse = new HashUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.traceCodeHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.dataHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.submitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<WhitelistUpdatedEventResponse> getWhitelistUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WHITELISTUPDATED_EVENT, transactionReceipt);
        ArrayList<WhitelistUpdatedEventResponse> responses = new ArrayList<WhitelistUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WhitelistUpdatedEventResponse typedResponse = new WhitelistUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.status = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt batchSubmit(List<String> traceCodes, List<byte[]> dataHashes) {
        final Function function = new Function(
                FUNC_BATCHSUBMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(traceCodes, org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class)), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(dataHashes, org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForBatchSubmit(List<String> traceCodes,
            List<byte[]> dataHashes) {
        final Function function = new Function(
                FUNC_BATCHSUBMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(traceCodes, org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class)), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(dataHashes, org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String batchSubmit(List<String> traceCodes, List<byte[]> dataHashes,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_BATCHSUBMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(traceCodes, org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String.class)), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray<org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(dataHashes, org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<List<String>, List<byte[]>> getBatchSubmitInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_BATCHSUBMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<List<String>, List<byte[]>>(

                convertToNative((List<Utf8String>) results.get(0).getValue()), 
                convertToNative((List<Bytes32>) results.get(1).getValue())
                );
    }

    public Tuple4<byte[], BigInteger, String, BigInteger> getRecord(String traceCode) throws
            ContractException {
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<byte[], BigInteger, String, BigInteger>(
                (byte[]) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue());
    }

    public void getRecord(String traceCode, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
        asyncExecuteCall(function, callback);
    }

    public Boolean isWhitelisted(String account) throws ContractException {
        final Function function = new Function(FUNC_ISWHITELISTED, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public void isWhitelisted(String account, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_ISWHITELISTED, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        asyncExecuteCall(function, callback);
    }

    public TransactionReceipt submitHash(String traceCode, byte[] dataHash) {
        final Function function = new Function(
                FUNC_SUBMITHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(dataHash)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForSubmitHash(String traceCode, byte[] dataHash) {
        final Function function = new Function(
                FUNC_SUBMITHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(dataHash)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String submitHash(String traceCode, byte[] dataHash, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SUBMITHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(dataHash)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, byte[]> getSubmitHashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SUBMITHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, byte[]>(

                (String) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue()
                );
    }

    public TransactionReceipt transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForTransferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String transferOwnership(String newOwner, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getTransferOwnershipInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt updateWhitelist(String account, Boolean status) {
        final Function function = new Function(
                FUNC_UPDATEWHITELIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Bool(status)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForUpdateWhitelist(String account, Boolean status) {
        final Function function = new Function(
                FUNC_UPDATEWHITELIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Bool(status)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String updateWhitelist(String account, Boolean status, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPDATEWHITELIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Bool(status)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, Boolean> getUpdateWhitelistInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATEWHITELIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, Boolean>(

                (String) results.get(0).getValue(), 
                (Boolean) results.get(1).getValue()
                );
    }

    public Tuple2<Boolean, BigInteger> verifyIntegrity(String traceCode, byte[] dataHash) throws
            ContractException {
        final Function function = new Function(FUNC_VERIFYINTEGRITY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(dataHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint64>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, BigInteger>(
                (Boolean) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public void verifyIntegrity(String traceCode, byte[] dataHash, CallCallback callback) throws
            ContractException {
        final Function function = new Function(FUNC_VERIFYINTEGRITY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(traceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(dataHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint64>() {}));
        asyncExecuteCall(function, callback);
    }

    public static SaveAddress load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new SaveAddress(contractAddress, client, credential);
    }

    public static SaveAddress deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(SaveAddress.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class HashUpdatedEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] traceCodeHash;

        public byte[] dataHash;

        public BigInteger timestamp;

        public String submitter;
    }

    public static class OwnershipTransferredEventResponse {
        public TransactionReceipt.Logs log;

        public String previousOwner;

        public String newOwner;
    }

    public static class WhitelistUpdatedEventResponse {
        public TransactionReceipt.Logs log;

        public String account;

        public Boolean status;
    }
}
