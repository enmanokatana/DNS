package org.example;

// DNSQuestion.java
public class DNSQuestion {
    private String qname;
    private int qtype;
    private int qclass;

    // Constructor
    public DNSQuestion(String qname, int qtype, int qclass) {
        this.qname = qname;
        this.qtype = qtype;
        this.qclass = qclass;
    }

    // Getters and setters
    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public int getQtype() {
        return qtype;
    }

    public void setQtype(int qtype) {
        this.qtype = qtype;
    }

    public int getQclass() {
        return qclass;
    }

    public void setQclass(int qclass) {
        this.qclass = qclass;
    }

    @Override
    public String toString() {
        return "DNSQuestion{" +
                "qname='" + qname + '\'' +
                ", qtype=" + qtype +
                ", qclass=" + qclass +
                '}';
    }
}
