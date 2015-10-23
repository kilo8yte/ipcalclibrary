package de.b45h;

/**
 * Created by ben on 22.10.2015.
 */
public class IPCalculation {
    byte cidr=0;
    long hostnumber = 0;
    InternetProtocolAddress ip;
    InternetProtocolAddress networkaddress;
    InternetProtocolAddress netmask;
    InternetProtocolAddress rnetmask;
    InternetProtocolAddress firstAddress;
    InternetProtocolAddress lastAddress;
    InternetProtocolAddress broadcastAddress;

    public IPCalculation(InternetProtocolAddress ip,byte cidr)
    {
        this.ip=ip;
        this.cidr=cidr;
        setNetmask();
        System.out.printf("Test\n");
    }

    private void setNetmask()
    {
        short octet=0;
        short value=128;
        short[] netmask=new short[4];
        for(int i=0;i<=32;i++) {
            if(i % 8 ==0 && i>0){
                netmask[(i/8)-1]=octet;
                octet=0;
                value=128;
            }
            if(i < cidr){
                octet+=value;
            }
            value/=2;
        }
        this.netmask=new InternetProtocolAddress(netmask[0],netmask[1],netmask[2],netmask[3]);
        rnetmask=new InternetProtocolAddress(this.netmask.IPAddress ^ 4294967295L);
    }

    private void setHostnumber()
    {
        byte hostbytes=(byte)(32-cidr);
        long hosts=1;
        for(int i=0;i < hostbytes;i++){
            hosts*=2;
        }
        hostnumber=hosts-2;
    }

    public InternetProtocolAddress getNetmask()
    {
        return netmask;
    }

    public long getHostnumber()
    {
        return hostnumber;
    }

    public byte getHostbits()
    {
        return (byte)(32-cidr);
    }

    public byte getNetworkbits()
    {
        return cidr;
    }

    public InternetProtocolAddress getfirstAddress()
    {
        firstAddress=new InternetProtocolAddress((ip.IPAddress & netmask.IPAddress)+1);
        return firstAddress;
    }

    public InternetProtocolAddress getNetworkAddress()
    {
        networkaddress=new InternetProtocolAddress(ip.IPAddress & netmask.IPAddress);
        return networkaddress;
    }

    public InternetProtocolAddress getlastAddress()
    {
        if(firstAddress==null)
            getfirstAddress();
        if(networkaddress==null)
            getNetworkAddress();
        lastAddress=new InternetProtocolAddress((networkaddress.IPAddress ^ rnetmask.IPAddress)-1);
        return lastAddress;
    }

    public InternetProtocolAddress getBroadcastAddress()
    {
        if(firstAddress==null)
            getfirstAddress();
        if(networkaddress==null)
            getNetworkAddress();
        broadcastAddress=new InternetProtocolAddress(networkaddress.IPAddress ^ rnetmask.IPAddress);
        return broadcastAddress;
    }
}
