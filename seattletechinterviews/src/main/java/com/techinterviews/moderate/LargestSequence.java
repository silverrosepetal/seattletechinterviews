package com.techinterviews.moderate;

/**
 * Created by xeniah on 3/9/15.
 */
public class LargestSequence {
    public static int largestSequence(int [] numbers)
    {
        int sum = 0;
        int maxSoFar = 0;
        for(int i = 0; i < numbers.length; i++)
        {
            maxSoFar += numbers[i];
            maxSoFar = Math.max(maxSoFar, 0);
            sum = Math.max(maxSoFar, sum);

        }

        return sum;
    }

    public static void main(String[] args) {
        int [] numbers = {1, 3, 0, -9, -2, -8, -100, 44};
        System.out.print(largestSequence(numbers));
    }
}
