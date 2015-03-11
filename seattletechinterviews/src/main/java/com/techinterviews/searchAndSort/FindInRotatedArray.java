package com.techinterviews.searchAndSort;

/**
 * Created by xeniah on 3/7/15.
 */
public class FindInRotatedArray {

    public static int findInRotatedArray(int [] arr, int x)
    {
        int result = -1;
        // find the "inflection point"
        int inflectionPoint = -1;
        for(int i = 0; i < arr.length; i++)
        {
            if(i < arr.length-1)
            {
                if(arr[i] > arr[i+1])
                {
                    inflectionPoint = i;
                    break;
                }
            }
        }

        if(inflectionPoint == -1)
        {
            return binSearch(arr, 0, arr.length-1, x);
        }

        if(arr[0] <= x && arr[inflectionPoint] >=x)
        {
            return binSearch(arr, 0, inflectionPoint, x);
        }else
        {
            return binSearch(arr, inflectionPoint+1, arr.length-1, x);
        }
    }

    private static int binSearch(int[] arr, int start, int end, int x) {
        if(start > end) return -1;
        while(start <= end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid] == x) return mid;
            if(arr[mid] < x)
            {
                start = mid+1;
            }else
            {
                end = mid-1;
            }
        }
        return -1;
    }

    // NB: can find inflection in bin time:
    public static int findPivot(int arr[], int low, int high)
    {
        // base cases
        if (high < low)  return -1;
        if (high == low) return low;

        int mid = (low + high)/2;   /*low + (high - low)/2;*/
        if (mid < high && arr[mid] > arr[mid + 1])
            return mid;
        if (mid > low && arr[mid] < arr[mid - 1])
            return (mid-1);
        if (arr[low] >= arr[mid])
            return findPivot(arr, low, mid-1);
        else
            return findPivot(arr, mid + 1, high);
    }

    public static void main(String[] args) {
       // int [] array = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int [] array = {2, 2, 2, 3, 4, 2};

        //int [] array = {50, 5, 20, 30, 40};
        int index = findInRotatedArray(array, 5);
        System.out.print("Index is: " + index);
    }
}
