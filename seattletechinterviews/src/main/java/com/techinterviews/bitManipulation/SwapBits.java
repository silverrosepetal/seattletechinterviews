package com.techinterviews.bitManipulation;

/**
 * Created by xeniah on 3/7/15.
 */
public class SwapBits {

    public static int swapBits(int n)
    {
        int m = 0;
        for(int i = 0; i < 31; i += 2)
        {
            int bit1 = getNthBitOfNumber(n, i);
            int bit2 = getNthBitOfNumber(n, i+1);
            if( bit1 != bit2){
                m |= mask(i, 1, i+1, 1);
            }else
            {
                m |= mask(i, bit1, i+1, bit2);
            }
        }
        return n^m;
    }

    // this is Gayle's solution. Quite elegant
    public static int swapBits2(int n)
    {
        int maskOdd = 0xaaaaaaaa;
        int maskEven = 0x55555555;

        int shiftOddBitsRight = (n & maskOdd) >> 1;
        int shiftEvenBitsLeft =  (n & maskEven) << 1;
        return shiftOddBitsRight | shiftEvenBitsLeft;
    }


    public static int mask(int index1, int bit1, int index2, int bit2)
    {
        return (bit1 << index1) | (bit2 << index2);
    }

    public static int getNthBitOfNumber(int num, int index)
    {
        return (num >> index) & 0x1;
    }



    public static void main(String[] args) {
        int n = 585;
        int index = 0;

        System.out.println(Integer.toBinaryString(n));
        System.out.println("0" +Integer.toBinaryString(swapBits(n)));
        System.out.println("0" +Integer.toBinaryString(swapBits2(n)));


    }
}
