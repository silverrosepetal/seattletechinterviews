package com.techinterviews.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xeniah on 3/10/15.
 */
public class ShortestDistanceBetweenWords {
    /*
    You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in
    the file.
    If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?
     */
    // public static int shortestDistance(File f)
    // read line by line  into an array of words
    public static int shortestDistance(List<String> words, String word1, String word2)
    {
        // compose a matching between a string an indices where it exists
        HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        int index = 0;
        for(String s : words)
        {
            if(map.get(s) == null)
            {
                List<Integer> l = new ArrayList<Integer>();
                l.add(index);
                map.put(s, l);
            }else
            {
                map.get(s).add(index);
            }
            index++;
        }

        List<Integer> word1indices = map.get(word1);
        List<Integer> word2indices = map.get(word2);
        if(word1indices == null || word2indices == null) return -1;

        int shortestDistance = Integer.MAX_VALUE;
        for(int w1 = 0; w1 < word1indices.size(); w1++)
        {
            for(int w2 = 0; w2 < word2indices.size(); w2++)
            {
                if(Math.abs(w1-w2) < shortestDistance)
                {
                    shortestDistance = Math.abs(w1-w2);
                }
            }
        }

        return shortestDistance;
    }

}
