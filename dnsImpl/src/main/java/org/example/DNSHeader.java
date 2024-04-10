package org.example;

// DNSHeader.java
public class DNSHeader {
    private int id;
    private boolean qr;
    private int opcode;
    private boolean aa;
    private boolean tc;
    private boolean rd;
    private boolean ra;
    private int rcode;
    private int qdcount;
    private int ancount;
    private int nscount;
    private int arcount;

    // Constructor
    public DNSHeader(int id, boolean qr, int opcode, boolean aa, boolean tc, boolean rd, boolean ra,
                     int rcode, int qdcount, int ancount, int nscount, int arcount) {
        this.id = id;
        this.qr = qr;
        this.opcode = opcode;
        this.aa = aa;
        this.tc = tc;
        this.rd = rd;
        this.ra = ra;
        this.rcode = rcode;
        this.qdcount = qdcount;
        this.ancount = ancount;
        this.nscount = nscount;
        this.arcount = arcount;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isQr() {
        return qr;
    }

    public void setQr(boolean qr) {
        this.qr = qr;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public boolean isAa() {
        return aa;
    }

    public void setAa(boolean aa) {
        this.aa = aa;
    }

    public boolean isTc() {
        return tc;
    }

    public void setTc(boolean tc) {
        this.tc = tc;
    }

    public boolean isRd() {
        return rd;
    }

    public void setRd(boolean rd) {
        this.rd = rd;
    }

    public boolean isRa() {
        return ra;
    }

    public void setRa(boolean ra) {
        this.ra = ra;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public int getQdcount() {
        return qdcount;
    }

    public void setQdcount(int qdcount) {
        this.qdcount = qdcount;
    }

    public int getAncount() {
        return ancount;
    }

    public void setAncount(int ancount) {
        this.ancount = ancount;
    }

    public int getNscount() {
        return nscount;
    }

    public void setNscount(int nscount) {
        this.nscount = nscount;
    }

    public int getArcount() {
        return arcount;
    }

    public void setArcount(int arcount) {
        this.arcount = arcount;
    }

    @Override
    public String toString() {
        return "DNSHeader{" +
                "id=" + id +
                ", qr=" + qr +
                ", opcode=" + opcode +
                ", aa=" + aa +
                ", tc=" + tc +
                ", rd=" + rd +
                ", ra=" + ra +
                ", rcode=" + rcode +
                ", qdcount=" + qdcount +
                ", ancount=" + ancount +
                ", nscount=" + nscount +
                ", arcount=" + arcount +
                '}';
    }
}
