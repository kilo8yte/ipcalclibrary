package de.b45h;

/**
 * Created by ben on 22.10.2015.
 */
public final class Binary {
    public static String byte2String(short b)
    {
        String bin="";
        int value = 128;
        byte shift = 7;
        while (shift >= 0)
        {
            bin += (int)((b & value)>>shift);
            value /= 2;
            --shift;
        }
        return bin;
    }
}
