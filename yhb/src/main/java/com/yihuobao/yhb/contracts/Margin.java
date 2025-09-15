package com.yihuobao.yhb.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
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
public class Margin extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506110ce806100606000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80639bdc61df1161005b5780639bdc61df14610154578063d051665014610184578063f84f89a2146101a0578063f851a440146101d057610088565b80630efe6a8b1461008d578063188d5557146100bf5780636d705ebb146100f1578063800c3d6114610121575b600080fd5b6100a760048036038101906100a29190610b00565b6101ee565b6040516100b693929190610bfb565b60405180910390f35b6100d960048036038101906100d49190610b00565b61039e565b6040516100e893929190610bfb565b60405180910390f35b61010b60048036038101906101069190610c39565b610595565b6040516101189190610c79565b60405180910390f35b61013b60048036038101906101369190610c9b565b6107b7565b60405161014b9493929190610ce7565b60405180910390f35b61016e60048036038101906101699190610c9b565b6107f7565b60405161017b9190610d2c565b60405180910390f35b61019e60048036038101906101999190610c9b565b610853565b005b6101ba60048036038101906101b59190610c9b565b6109f7565b6040516101c79190610d47565b60405180910390f35b6101d8610a43565b6040516101e59190610d71565b60405180910390f35b60606000806000600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060008160030160009054906101000a900463ffffffff1663ffffffff1614610293576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161028a90610dd8565b60405180910390fd5b600086116102d6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102cd90610e44565b60405180910390fd5b858160000160008282546102ea9190610e93565b925050819055508481600201819055508673ffffffffffffffffffffffffffffffffffffffff167f62ecb2a394867776d4797065f4c2c678a4d98de37e96d0a89642599b6e0c4f548260000154888860405161034893929190610ee9565b60405180910390a28060000154856040518060400160405280600f81526020017f73756363657373204465706f736974000000000000000000000000000000000081525091909350935093505093509350939050565b60606000806000600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060008160030160009054906101000a900463ffffffff1663ffffffff1614610443576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043a90610dd8565b60405180910390fd5b60008611610486576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161047d90610e44565b60405180910390fd5b85816000015410156104cd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104c490610f6c565b60405180910390fd5b858160000160008282546104e19190610f8c565b925050819055508481600201819055508673ffffffffffffffffffffffffffffffffffffffff167f0dd38134673685a83ef11521ef3040136016e1f68f8f127bb2f4c715b5cc40988260000154888860405161053f93929190610ee9565b60405180910390a28060000154856040518060400160405280600e81526020017f737563636573732044656475637400000000000000000000000000000000000081525091909350935093505093509350939050565b606060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610625576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161061c9061100c565b60405180910390fd5b6000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060018160030160009054906101000a900463ffffffff1663ffffffff1614806106ab575060038160030160009054906101000a900463ffffffff1663ffffffff16145b6106ea576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106e190611078565b60405180910390fd5b6000816000018190555082816001018190555082816002018190555060008160030160006101000a81548163ffffffff021916908363ffffffff1602179055508373ffffffffffffffffffffffffffffffffffffffff167f4d509eaf411f241400f13f5abf136c29e58fc43af0f27f87a1c1c9767795d03f846040516107709190610d47565b60405180910390a26040518060400160405280601081526020017f737563636573732072656769737465720000000000000000000000000000000081525091505092915050565b60016020528060005260406000206000915090508060000154908060010154908060020154908060030160009054906101000a900463ffffffff16905084565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900463ffffffff169050919050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108e1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108d89061100c565b60405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060008160030160009054906101000a900463ffffffff1663ffffffff1614610981576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161097890610dd8565b60405180910390fd5b60028160030160006101000a81548163ffffffff021916908363ffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff167faf522d93d5467fde3f4c7b4a5b98ba3bbd5df1e1504dd9edf9a7946a9caffbf0426040516109eb9190610d47565b60405180910390a25050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001549050919050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610a9782610a6c565b9050919050565b610aa781610a8c565b8114610ab257600080fd5b50565b600081359050610ac481610a9e565b92915050565b6000819050919050565b610add81610aca565b8114610ae857600080fd5b50565b600081359050610afa81610ad4565b92915050565b600080600060608486031215610b1957610b18610a67565b5b6000610b2786828701610ab5565b9350506020610b3886828701610aeb565b9250506040610b4986828701610aeb565b9150509250925092565b600081519050919050565b600082825260208201905092915050565b60005b83811015610b8d578082015181840152602081019050610b72565b83811115610b9c576000848401525b50505050565b6000601f19601f8301169050919050565b6000610bbe82610b53565b610bc88185610b5e565b9350610bd8818560208601610b6f565b610be181610ba2565b840191505092915050565b610bf581610aca565b82525050565b60006060820190508181036000830152610c158186610bb3565b9050610c246020830185610bec565b610c316040830184610bec565b949350505050565b60008060408385031215610c5057610c4f610a67565b5b6000610c5e85828601610ab5565b9250506020610c6f85828601610aeb565b9150509250929050565b60006020820190508181036000830152610c938184610bb3565b905092915050565b600060208284031215610cb157610cb0610a67565b5b6000610cbf84828501610ab5565b91505092915050565b600063ffffffff82169050919050565b610ce181610cc8565b82525050565b6000608082019050610cfc6000830187610bec565b610d096020830186610bec565b610d166040830185610bec565b610d236060830184610cd8565b95945050505050565b6000602082019050610d416000830184610cd8565b92915050565b6000602082019050610d5c6000830184610bec565b92915050565b610d6b81610a8c565b82525050565b6000602082019050610d866000830184610d62565b92915050565b7f4d65726368616e74206e6f742061637469766500000000000000000000000000600082015250565b6000610dc2601383610b5e565b9150610dcd82610d8c565b602082019050919050565b60006020820190508181036000830152610df181610db5565b9050919050565b7f416d6f756e74206d75737420626520706f736974697665000000000000000000600082015250565b6000610e2e601783610b5e565b9150610e3982610df8565b602082019050919050565b60006020820190508181036000830152610e5d81610e21565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610e9e82610aca565b9150610ea983610aca565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610ede57610edd610e64565b5b828201905092915050565b6000606082019050610efe6000830186610bec565b610f0b6020830185610bec565b610f186040830184610bec565b949350505050565b7f496e73756666696369656e74206d617267696e00000000000000000000000000600082015250565b6000610f56601383610b5e565b9150610f6182610f20565b602082019050919050565b60006020820190508181036000830152610f8581610f49565b9050919050565b6000610f9782610aca565b9150610fa283610a","ca565b925082821015610fb557610fb4610e64565b5b828203905092915050565b7f4e6f742061646d696e0000000000000000000000000000000000000000000000600082015250565b6000610ff6600983610b5e565b915061100182610fc0565b602082019050919050565b6000602082019050818103600083015261102581610fe9565b9050919050565b7f4d65726368616e7420616c726561647920726567697374657265640000000000600082015250565b6000611062601b83610b5e565b915061106d8261102c565b602082019050919050565b6000602082019050818103600083015261109181611055565b905091905056fea26469706673582212200767b41e51df7fe10c11acb3118e65805eb6fcd1b775cf4deafe792dbc3ad93b64736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"totalMargin\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"updateTime\",\"type\":\"uint256\"}],\"name\":\"DeductEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"totalMargin\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"updateTime\",\"type\":\"uint256\"}],\"name\":\"DepositEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"frozenTime\",\"type\":\"uint256\"}],\"name\":\"FrozenEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"registerTime\",\"type\":\"uint256\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"admin\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"updateTime\",\"type\":\"uint256\"}],\"name\":\"deduct\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"updateTime\",\"type\":\"uint256\"}],\"name\":\"deposit\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"}],\"name\":\"frozen\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"}],\"name\":\"getIsRegister\",\"outputs\":[{\"internalType\":\"uint32\",\"name\":\"\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"}],\"name\":\"getMargin\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"merchantAccount\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"totalMargin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"registerTime\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"updateTime\",\"type\":\"uint256\"},{\"internalType\":\"uint32\",\"name\":\"status\",\"type\":\"uint32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"merchantAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"registerTime\",\"type\":\"uint256\"}],\"name\":\"register\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_DEDUCT = "deduct";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_FROZEN = "frozen";

    public static final String FUNC_GETISREGISTER = "getIsRegister";

    public static final String FUNC_GETMARGIN = "getMargin";

    public static final String FUNC_MERCHANTACCOUNT = "merchantAccount";

    public static final String FUNC_REGISTER = "register";

    public static final Event DEDUCTEVENT_EVENT = new Event("DeductEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event DEPOSITEVENT_EVENT = new Event("DepositEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FROZENEVENT_EVENT = new Event("FrozenEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected Margin(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<DeductEventEventResponse> getDeductEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEDUCTEVENT_EVENT, transactionReceipt);
        ArrayList<DeductEventEventResponse> responses = new ArrayList<DeductEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeductEventEventResponse typedResponse = new DeductEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.merchantAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.totalMargin = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.updateTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<DepositEventEventResponse> getDepositEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEPOSITEVENT_EVENT, transactionReceipt);
        ArrayList<DepositEventEventResponse> responses = new ArrayList<DepositEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositEventEventResponse typedResponse = new DepositEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.merchantAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.totalMargin = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.updateTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<FrozenEventEventResponse> getFrozenEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FROZENEVENT_EVENT, transactionReceipt);
        ArrayList<FrozenEventEventResponse> responses = new ArrayList<FrozenEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FrozenEventEventResponse typedResponse = new FrozenEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.merchantAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.frozenTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.merchantAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.registerTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public String admin() throws ContractException {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public void admin(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        asyncExecuteCall(function, callback);
    }

    public TransactionReceipt deduct(String merchantAddress, BigInteger amount,
            BigInteger updateTime) {
        final Function function = new Function(
                FUNC_DEDUCT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForDeduct(String merchantAddress, BigInteger amount,
            BigInteger updateTime) {
        final Function function = new Function(
                FUNC_DEDUCT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String deduct(String merchantAddress, BigInteger amount, BigInteger updateTime,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DEDUCT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, BigInteger, BigInteger> getDeductInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DEDUCT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple3<String, BigInteger, BigInteger> getDeductOutput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DEDUCT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public TransactionReceipt deposit(String merchantAddress, BigInteger amount,
            BigInteger updateTime) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForDeposit(String merchantAddress, BigInteger amount,
            BigInteger updateTime) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String deposit(String merchantAddress, BigInteger amount, BigInteger updateTime,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(updateTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, BigInteger, BigInteger> getDepositInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple3<String, BigInteger, BigInteger> getDepositOutput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public TransactionReceipt frozen(String merchantAddress) {
        final Function function = new Function(
                FUNC_FROZEN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForFrozen(String merchantAddress) {
        final Function function = new Function(
                FUNC_FROZEN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String frozen(String merchantAddress, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_FROZEN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getFrozenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_FROZEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public BigInteger getIsRegister(String merchantAddress) throws ContractException {
        final Function function = new Function(FUNC_GETISREGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public void getIsRegister(String merchantAddress, CallCallback callback) throws
            ContractException {
        final Function function = new Function(FUNC_GETISREGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        asyncExecuteCall(function, callback);
    }

    public BigInteger getMargin(String merchantAddress) throws ContractException {
        final Function function = new Function(FUNC_GETMARGIN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public void getMargin(String merchantAddress, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETMARGIN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        asyncExecuteCall(function, callback);
    }

    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> merchantAccount(String param0)
            throws ContractException {
        final Function function = new Function(FUNC_MERCHANTACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue());
    }

    public void merchantAccount(String param0, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_MERCHANTACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}));
        asyncExecuteCall(function, callback);
    }

    public TransactionReceipt register(String merchantAddress, BigInteger registerTime) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(registerTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForRegister(String merchantAddress, BigInteger registerTime) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(registerTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String register(String merchantAddress, BigInteger registerTime,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(merchantAddress), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(registerTime)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<String> getRegisterOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public static Margin load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Margin(contractAddress, client, credential);
    }

    public static Margin deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Margin.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class DeductEventEventResponse {
        public TransactionReceipt.Logs log;

        public String merchantAddress;

        public BigInteger totalMargin;

        public BigInteger amount;

        public BigInteger updateTime;
    }

    public static class DepositEventEventResponse {
        public TransactionReceipt.Logs log;

        public String merchantAddress;

        public BigInteger totalMargin;

        public BigInteger amount;

        public BigInteger updateTime;
    }

    public static class FrozenEventEventResponse {
        public TransactionReceipt.Logs log;

        public String merchantAddress;

        public BigInteger frozenTime;
    }

    public static class RegisterEventEventResponse {
        public TransactionReceipt.Logs log;

        public String merchantAddress;

        public BigInteger registerTime;
    }
}
