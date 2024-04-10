package org.example;

public class DNSQuery {
    private final  String domainName;
    private final int queryType;
    public DNSQuery(String domainName , int queryType){
        this.domainName = domainName;
        this.queryType =queryType;
    }

    public String getDomainName() {
        return domainName;
    }

    public int getQueryType() {
        return queryType;
    }

    public static  DNSQuery fromByteArray(byte[] data){
        int index = 12;
        StringBuilder domainNameBuilder = new StringBuilder();
        while (data[index]!= 0){
            int labelLength = data[index];
            for (int i = 0 ; i < labelLength;i++){
                domainNameBuilder.append((char) data[index+i+1]);
            }
            domainNameBuilder.append(".");
            index+= labelLength + 1;
        }
        domainNameBuilder.deleteCharAt(domainNameBuilder.length() - 1);
        index++;
        int queryType = (data[index] & 0xFF) << 8 | (data[index + 1] & 0xFF);
        return new DNSQuery(domainNameBuilder.toString(),queryType);
    }
}
