package de.b45h;

/**
 * Created by ben on 22.10.2015.
 */
public class InternetProtocolAddress {
    public long IPAddress=0;


    final int byteone=-16777216;
    final int bytetwo=16711680;
    final int bytethree=65280;
    final int bytefour=255;

    byte count=0;

    public InternetProtocolAddress(long ip)
    {
        IPAddress=ip;
    }

    public InternetProtocolAddress(short firstoctet,short secondoctet,short thirdoctet,short fourthoctet)
    {
        IPAddress+=firstoctet*Math.pow(256,3);
        IPAddress+=secondoctet*Math.pow(256,2);
        IPAddress+=thirdoctet*256;
        IPAddress+=fourthoctet;
    }


    public short getNextByte()
    {
        short val=0;
        if(count>3)
            count=0;
        if(count==0)
            val=firstbyte();
        else if(count==1)
            val=secondbyte();
        else if(count==2)
            val=thirdbyte();
        else
            val=fourthbyte();
        ++count;
        return val;
    }



    public short firstbyte()
    {
        return (short)((byteone&IPAddress)>>24);
    }

    public short secondbyte()
    {
        return (short)((bytetwo&IPAddress)>>16);
    }

    public short thirdbyte()
    {
        return (short)((bytethree&IPAddress)>>8);
    }

    public short fourthbyte()
    {
        return (short)(bytefour&IPAddress);
    }


    @Override
    public String toString()
    {
        return firstbyte()+"."+secondbyte()+"."+thirdbyte()+"."+fourthbyte();
    }

    public String toBinaryString()
    {
        return Binary.byte2String(firstbyte())+"."+Binary.byte2String(secondbyte())+"."+Binary.byte2String(thirdbyte())+"."+Binary.byte2String(fourthbyte());
    }

}
