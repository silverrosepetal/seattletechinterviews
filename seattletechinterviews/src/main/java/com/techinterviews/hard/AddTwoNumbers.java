package com.techinterviews.hard;

/**
 * Created by xeniah on 3/9/15.
 */
public class AddTwoNumbers {
    public static int add(int x, int y)
    {
        while(y > 0)
        {
            int carryOver = x & y;
            x = x ^ y;
            y = carryOver << 1;
        }

        return x;
    }

    public static int addRecursive(int x, int y)
    {
        if(y == 0) return x;
        int sum = (x ^ y);
        int carry = (x & y) << 1;
        return addRecursive(sum, carry);
    }

    public static int add2(int x, int y)
    {
        while(y != 0){
            int carry = (x & y) << 1;
            x = x ^ y;
            y = carry;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.print(add2(-15, 10));
    }
}
