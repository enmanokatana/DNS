package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Dns {
    private  static  final  int DNS_PORT = 53;
    private static final  int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try{
            socket = new DatagramSocket(DNS_PORT);
            System.out.println("DNS server listening on port " + DNS_PORT + "...");
            while (true){
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer , buffer.length);
                socket.receive(packet);
                System.out.println("Received " + packet.getLength() + " bytes from " + packet.getAddress().getHostAddress() + ":" + packet.getPort());


                // process Dns Query
                //Print the query
                System.out.println("Received DNS query:");

                for (int i = 0; i < packet.getLength();i++){
                    if ((i+1)%16 == 0){
                        System.out.println();
                    }
                    System.out.println();


                }

                DNSQuery dnsQuery = DNSQuery.fromByteArray(packet.getData());
                System.out.println(dnsQuery.getDomainName());

                DNSResponse dnsResponse  = new DNSResponse(dnsQuery);
                byte[] responseDara = dnsResponse.toByteArray();
                DatagramPacket responsePacket = new DatagramPacket(responseDara , responseDara.length, packet.getAddress(), packet.getPort());
                var res = DNSPacketParser.parseDNSPacket(packet.getData());
                System.out.println(packet.getData());
                socket.send(responsePacket);
                System.out.println("Sent DNS response.");


            }

        }catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
    }
    }
}
