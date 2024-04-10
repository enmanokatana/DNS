package org.example;

import java.nio.ByteBuffer;

public class DNSResponse {
    private final DNSQuery query;

    public DNSResponse(DNSQuery query) {
        this.query = query;
    }

    public byte[] toByteArray() {
        byte[] domainNameBytes = query.getDomainName().getBytes();
        // Calculate total size needed for the response
        int totalSize = 12 + domainNameBytes.length + 24; // 24 bytes header + domain name length() + other fields
        System.out.println("DN length = "+domainNameBytes.length);
        ByteBuffer buffer = ByteBuffer.allocate(totalSize);

        // Header == 12
        buffer.putShort((short) 0xC00C); // Transaction ID (constant in this example)
        buffer.putShort((short) 0x0001); // Flags (response with recursion desired)
        buffer.putShort((short) 0x0001); // Questions count
        buffer.putShort((short) 0x0001); // Answer count
        buffer.putShort((short) 0x0000); // Authority count
        buffer.putShort((short) 0x0000); // Additional count
        System.out.println("Header = "+buffer.position());

        // Domain name pointer == 2
        buffer.put((byte) 0xC0); // Pointer to domain name
        buffer.put((byte) 0x0C);
        System.out.println("Domaine = "+buffer.position());

        // Query type and class == 4
        buffer.putShort((short) query.getQueryType());//4bits
        buffer.putShort((short) 0x0001); // IN class
        System.out.println("QT and class = "+buffer.position());

        // TTL and data length = 8
        buffer.putInt(0); // TTL
        buffer.putShort((short) (4 + domainNameBytes.length)); // Data length
        System.out.println("TTL and data length = "+buffer.position());

        // Address record== 12 + 11
        buffer.putShort((short) 0xC00C); // Domain name pointer
        buffer.putShort((short) 0x0001); // Type A record
        buffer.putShort((short) 0x0001); // Class IN
        buffer.putInt(0); // TTL
        buffer.putShort((short) domainNameBytes.length); // Data length
        buffer.put(domainNameBytes); // Domain name
        System.out.println("Total = "+buffer.position());
        return buffer.array();
    }
}
