package com.smc.hackerrankproblems.maxmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.


/**
 * https://www.hackerrank.com/challenges/angry-children?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=3-day-campaign
 * 
 * @author seshumadhav@gmail.com
 */
public class Solution {
    
   public static void main(String[] args) throws NumberFormatException, IOException {

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(in.readLine());
      int K = Integer.parseInt(in.readLine());
      int[] list = new int[N];

      for(int i = 0; i < N; i ++) {
         list[i] = Integer.parseInt(in.readLine());
      }
      
      int unfairness = Integer.MAX_VALUE;
      
      /*
       * Write your code here, to process numPackets N, numKids K, and the packets of candies
       * Compute the ideal value for unfairness over here
      */
      List<Integer> arrayAsList = toList(list);
      Collections.sort(arrayAsList); 

      for (int i = 0; i+K <= N; i++) {
    	  int delta = getDelta(arrayAsList.subList(i, i+K));
    	  if (delta < unfairness) {
    		  unfairness = delta;
    	  }
      }
      
      System.out.println(unfairness);
   }
   
   private static int getDelta(List<Integer> list) {
	   return list.get(list.size() - 1) - list.get(0);
   }
   
   private static List<Integer> toList(int[] array) {
	   List<Integer> list = new ArrayList<>();
	   
	   for (int i : array) {
		   list.add(i);
	   }
	   
	   return list;
   }
}