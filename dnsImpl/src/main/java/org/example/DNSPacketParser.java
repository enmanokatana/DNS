package org.example;

import java.util.Arrays;
// DNSPacketParser.java
import java.util.Arrays;

public class DNSPacketParser {

    public static String parseDNSPacket(byte[] data) {
        // Parse header section
        var Resp = "";
        byte[] headerBytes = Arrays.copyOfRange(data, 0, 12);
        DNSHeader header = parseHeader(headerBytes);
        System.out.println("Header: " + header);
        Resp += ("Header: " + header+"\n");

        // Parse question section
        byte[] questionBytes = Arrays.copyOfRange(data, 11, data.length);
        DNSQuestion question = parseQuestion(questionBytes);
        System.out.println("Question: " + question);
        Resp +=("Question: " + question);
        return Resp;
    }

    public static DNSHeader parseHeader(byte[] headerBytes) {
        // Parse fields from headerBytes and create DNSHeader object
        int id = (headerBytes[0] & 0xFF) << 8 | (headerBytes[1] & 0xFF);
        boolean qr = (headerBytes[2] & 0x80) != 0;
        int opcode = (headerBytes[2] >> 3) & 0x0F;
        boolean aa = (headerBytes[2] & 0x04) != 0;
        boolean tc = (headerBytes[2] & 0x02) != 0;
        boolean rd = (headerBytes[2] & 0x01) != 0;
        boolean ra = (headerBytes[3] & 0x80) != 0;
        int rcode = headerBytes[3] & 0x0F;
        int qdcount = (headerBytes[4] & 0xFF) << 8 | (headerBytes[5] & 0xFF);
        int ancount = (headerBytes[6] & 0xFF) << 8 | (headerBytes[7] & 0xFF);
        int nscount = (headerBytes[8] & 0xFF) << 8 | (headerBytes[9] & 0xFF);
        int arcount = (headerBytes[10] & 0xFF) << 8 | (headerBytes[11] & 0xFF);

        return new DNSHeader(id, qr, opcode, aa, tc, rd, ra, rcode, qdcount, ancount, nscount, arcount);
    }

    public static DNSQuestion parseQuestion(byte[] questionBytes) {
        int index = 0;
        StringBuilder qnameBuilder = new StringBuilder();
        while (index < questionBytes.length && questionBytes[index] != 0) {
            int length = questionBytes[index++] & 0xFF;
            if ((length & 0xC0) == 0xC0) {
                // Compression pointer encountered
                int pointer = ((length & 0x3F) << 8) | (questionBytes[index++] & 0xFF);
                // Follow compression pointer and parse domain name from there
                parseCompressedDomainName(questionBytes, pointer, qnameBuilder);
                break; // Domain name parsed, exit loop
            }
            if (qnameBuilder.length() > 0) {
                qnameBuilder.append(".");
            }
            for (int i = 0; i < length && index < questionBytes.length; i++) {
                qnameBuilder.append((char) (questionBytes[index++] & 0xFF));
            }
        }
        if (index + 4 <= questionBytes.length) {
            int qtype = (questionBytes[index] & 0xFF) << 8 | (questionBytes[index + 1] & 0xFF);
            int qclass = (questionBytes[index + 2] & 0xFF) << 8 | (questionBytes[index + 3] & 0xFF);

            return new DNSQuestion(qnameBuilder.toString(), qtype, qclass);
        } else {
            // Invalid questionBytes array
            return null;
        }
    }

    public static void parseCompressedDomainName(byte[] questionBytes, int pointer, StringBuilder qnameBuilder) {
        // Follow compression pointer and parse domain name from there
        while (pointer < questionBytes.length) {
            int length = questionBytes[pointer++] & 0xFF;
            if (length == 0) {
                break; // End of domain name
            }
            if (qnameBuilder.length() > 0) {
                qnameBuilder.append(".");
            }
            if ((length & 0xC0) == 0xC0) {
                // New compression pointer encountered, follow it recursively
                int newPointer = ((length & 0x3F) << 8) | (questionBytes[pointer++] & 0xFF);
                parseCompressedDomainName(questionBytes, newPointer, qnameBuilder);
                break; // Domain name parsed, exit loop
            }
            for (int i = 0; i < length && pointer < questionBytes.length; i++) {
                qnameBuilder.append((char) (questionBytes[pointer++] & 0xFF));
            }
        }
    }
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] byteArray = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return byteArray;
    }

}

