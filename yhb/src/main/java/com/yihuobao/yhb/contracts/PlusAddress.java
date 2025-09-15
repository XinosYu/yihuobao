package com.yihuobao.yhb.contracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class PlusAddress extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506108ab806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063517a68311461003b578063edb961ff1461006b575b600080fd5b610055600480360381019061005091906103e7565b61009b565b604051610062919061046a565b60405180910390f35b61008560048036038101906100809190610485565b610142565b604051610092919061054b565b60405180910390f35b60008083836040516020016100b192919061058e565b604051602081830303815290604052805190602001209050600086866100d684610142565b6040516020016100e893929190610676565b6040516020818303038152906040529050817fc10fe7bea388ab16afae494e4357599e240c85572f9b4702eb8773992abd357386868460405161012d939291906106a7565b60405180910390a28192505050949350505050565b606060006040518060400160405280601081526020017f303132333435363738396162636465660000000000000000000000000000000081525090506000604067ffffffffffffffff81111561019b5761019a6106e5565b5b6040519080825280601f01601f1916602001820160405280156101cd5781602001600182028036833780820191505090505b50905060005b6020811015610337578260048683602081106101f2576101f1610714565b5b1a60f81b7effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916901c60f81c60ff168151811061023157610230610714565b5b602001015160f81c60f81b8260028361024a919061077c565b8151811061025b5761025a610714565b5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535082600f60f81b8683602081106102a3576102a2610714565b5b1a60f81b1660f81c60ff16815181106102bf576102be610714565b5b602001015160f81c60f81b8260016002846102da919061077c565b6102e491906107d6565b815181106102f5576102f4610714565b5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350808061032f9061082c565b9150506101d3565b508092505050919050565b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b60008083601f8401126103715761037061034c565b5b8235905067ffffffffffffffff81111561038e5761038d610351565b5b6020830191508360018202830111156103aa576103a9610356565b5b9250929050565b6000819050919050565b6103c4816103b1565b81146103cf57600080fd5b50565b6000813590506103e1816103bb565b92915050565b6000806000806060858703121561040157610400610342565b5b600085013567ffffffffffffffff81111561041f5761041e610347565b5b61042b8782880161035b565b9450945050602061043e878288016103d2565b925050604061044f878288016103d2565b91505092959194509250565b610464816103b1565b82525050565b600060208201905061047f600083018461045b565b92915050565b60006020828403121561049b5761049a610342565b5b60006104a9848285016103d2565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156104ec5780820151818401526020810190506104d1565b838111156104fb576000848401525b50505050565b6000601f19601f8301169050919050565b600061051d826104b2565b61052781856104bd565b93506105378185602086016104ce565b61054081610501565b840191505092915050565b600060208201905081810360008301526105658184610512565b905092915050565b6000819050919050565b610588610583826103b1565b61056d565b82525050565b600061059a8285610577565b6020820191506105aa8284610577565b6020820191508190509392505050565b600081905092915050565b82818337600083830152505050565b60006105e083856105ba565b93506105ed8385846105c5565b82840190509392505050565b7f5f6d65726765645f000000000000000000000000000000000000000000000000600082015250565b600061062f6008836105ba565b915061063a826105f9565b600882019050919050565b6000610650826104b2565b61065a81856105ba565b935061066a8185602086016104ce565b80840191505092915050565b60006106838285876105d4565b915061068e82610622565b915061069a8284610645565b9150819050949350505050565b60006060820190506106bc600083018661045b565b6106c9602083018561045b565b81810360408301526106db8184610512565b9050949350505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061078782610743565b915061079283610743565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff04831182151516156107cb576107ca61074d565b5b828202905092915050565b60006107e182610743565b91506107ec83610743565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff038211156108215761082061074d565b5b828201905092915050565b600061083782610743565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561086a5761086961074d565b5b60018201905091905056fea2646970667358221220d346a54bc0a30a1f4bb7aff7fb85ec37f4ee585d82996c2ec93db10bde7ef90364736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"mergedHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"hashA\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"hashB\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"generatedTraceCode\",\"type\":\"string\"}],\"name\":\"HashMerged\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"baseTraceCode\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"hashA\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"hashB\",\"type\":\"bytes32\"}],\"name\":\"mergeHashes\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"data\",\"type\":\"bytes32\"}],\"name\":\"toHexString\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"pure\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_MERGEHASHES = "mergeHashes";

    public static final String FUNC_TOHEXSTRING = "toHexString";

    public static final Event HASHMERGED_EVENT = new Event("HashMerged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected PlusAddress(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<HashMergedEventResponse> getHashMergedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(HASHMERGED_EVENT, transactionReceipt);
        ArrayList<HashMergedEventResponse> responses = new ArrayList<HashMergedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            HashMergedEventResponse typedResponse = new HashMergedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.mergedHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.hashA = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.hashB = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.generatedTraceCode = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt mergeHashes(String baseTraceCode, byte[] hashA, byte[] hashB) {
        final Function function = new Function(
                FUNC_MERGEHASHES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(baseTraceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashA), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashB)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForMergeHashes(String baseTraceCode, byte[] hashA,
            byte[] hashB) {
        final Function function = new Function(
                FUNC_MERGEHASHES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(baseTraceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashA), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashB)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String mergeHashes(String baseTraceCode, byte[] hashA, byte[] hashB,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_MERGEHASHES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(baseTraceCode), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashA), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(hashB)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, byte[], byte[]> getMergeHashesInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MERGEHASHES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, byte[], byte[]>(

                (String) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue()
                );
    }

    public Tuple1<byte[]> getMergeHashesOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_MERGEHASHES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public TransactionReceipt toHexString(byte[] data) {
        final Function function = new Function(
                FUNC_TOHEXSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForToHexString(byte[] data) {
        final Function function = new Function(
                FUNC_TOHEXSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String toHexString(byte[] data, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TOHEXSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(data)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<byte[]> getToHexStringInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TOHEXSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple1<String> getToHexStringOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TOHEXSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public static PlusAddress load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new PlusAddress(contractAddress, client, credential);
    }

    public static PlusAddress deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(PlusAddress.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class HashMergedEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] mergedHash;

        public byte[] hashA;

        public byte[] hashB;

        public String generatedTraceCode;
    }
}
