package org.example;

import java.net.InetAddress;

public class Ping {
    public static void main(String[] args) {
        if (args.length!=1){
            System.err.println("Usage : Ping 'HostName' ");
            return;
        }
        String hostname = args[0];
        try{
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("Pinging :" + hostname + "[" + address.getHostAddress()+"] with 32 bytes of data:");

            for (int i = 0 ; i< 4; i++){
                long startTime = System.currentTimeMillis();
                boolean isRecheable = address.isReachable(3000);
                long endTime = System.currentTimeMillis();
                long timeElapsed = endTime - startTime ;

                if (isRecheable){
                    System.out.println("Reply from " + address.getHostAddress() + ": bytes=32 time=" + timeElapsed + "ms");

                }else {
                    System.out.println("Request timed out.");

                }
                Thread.sleep(1000);
            }

        }catch (Exception e){
            System.err.println(e);
        }
    }
}
