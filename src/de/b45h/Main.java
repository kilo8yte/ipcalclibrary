package de.b45h;

public class Main {

    public static void main(String[] args) {
        System.out.printf("255: %s\n",Binary.byte2String((short)178));
        InternetProtocolAddress ip=new InternetProtocolAddress((short)192,(short)168,(short)178,(short)255);
        System.out.printf("IP: %d\n",ip.IPAddress);
        System.out.printf("IP: %d\n",ip.fourthbyte());
        System.out.printf("IP: %d\n",ip.thirdbyte());
        System.out.printf("IP: %d\n",ip.secondbyte());
        System.out.printf("IP: %d\n",ip.firstbyte());
        System.out.printf("IP: %s\n",ip.toString());
        System.out.printf("IP-BIN: %s\n",ip.toBinaryString());
        for(int i=0;i<4;i++){
            System.out.printf("Byte: %d\n",ip.getNextByte());
        }
        IPCalculation ipc=new IPCalculation(ip,(byte)24);

        System.out.printf("first: %s\n",ipc.getfirstAddress());
        System.out.printf("network: %s\n",ipc.getNetworkAddress());
        System.out.printf("last: %s\n",ipc.getlastAddress());
        System.out.printf("broadcast: %s\n",ipc.getBroadcastAddress());
    }
}
