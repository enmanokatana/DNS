package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class DNSClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 53 ;
    private static final DNSPacketParser parser = new DNSPacketParser();
    private static Logger logger ;

    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket();

            byte[] queryData = createDNSQuery();
            // Sending DNS Query
            InetAddress serverAdress = InetAddress.getByName(SERVER_ADDRESS);
            DatagramPacket queryPacket = new DatagramPacket(queryData,queryData.length , serverAdress,SERVER_PORT);
            socket.send(queryPacket);

            //Recive DNS response
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData , responseData.length);
            socket.receive(responsePacket);

            System.out.println("Received DNS response from server : ");
            for (int i = 0 ;i<responsePacket.getLength(); i++ ){
                System.out.printf("%02x ",responseData[i]);
                if ((i+1) % 16== 0){
                    System.out.println();
                }
            }





            socket.close();


        }catch(Exception e){
            System.out.println(e);
            }
    }
    private static byte[] createDNSQuery() {
        // This is a very basic DNS query for demonstration purposes
        // You should replace it with a proper DNS query according to your needs
        // For simplicity, this example creates a query for example.com (type A record)
        byte[] queryData = {
                (byte) 0x00, (byte) 0x0F, // Transaction ID (2 bytes)
                (byte) 0x01, (byte) 0x00, // Flags (2 bytes)
                (byte) 0x00, (byte) 0x01, // Question count (2 bytes)
                (byte) 0x00, (byte) 0x00, // Answer count (2 bytes)
                (byte) 0x00, (byte) 0x00, // Authority count (2 bytes)
                (byte) 0x00, (byte) 0x00, // Additional count (2 bytes)
                // Question section
                (byte) 0x03, (byte) 0x77, (byte) 0x77, (byte) 0x77, // www (4 bytes)
                (byte) 0x06, (byte) 0x67, (byte) 0x6F, (byte) 0x6F, // .google (4 bytes)
                (byte) 0x67, (byte) 0x6C, (byte) 0x65, // .com (3 bytes)
                (byte) 0x00, // Null terminator (1 byte)
                (byte) 0x00, (byte) 0x01, // Type: A (2 bytes)
                (byte) 0x00, (byte) 0x01 // Class: IN (2 bytes)
                // Additional sections, if any, can be added similarly
        };
        return queryData;
    }
}
