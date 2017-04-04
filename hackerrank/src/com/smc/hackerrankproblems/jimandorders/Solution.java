package com.smc.hackerrankproblems.jimandorders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * https://www.hackerrank.com/challenges/jim-and-the-orders?h_r=next-challenge&h_v=zen
 * 
 * @author seshumadhav@gmail.com
 */
public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	String order = in.readLine();
        	int begin = Integer.valueOf(order.split(" ")[0]);
        	int duration = Integer.valueOf(order.split(" ")[1]);
        	Order o = new Order(i+1, begin, duration);
        	orders.add(o);
        }
        
        Collections.sort(orders);
        String s = new String();
        for (Order order : orders) {
			s += order.getId() + " ";
		}
        
        System.out.println(s);
    }
}

class Order implements Comparable<Order> {
	
	private int id;
	private int duration;
	private int begin;
	private Integer end;

	public Order(int id, int begin, int duration) {
		this.id = id;
		this.begin = begin;
		this.duration = duration;
		this.end = this.begin + this.duration;
	}
	
	public int getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public int getBegin() {
		return begin;
	}

	public Integer getEnd() {
		return end;
	}

	@Override
	public int compareTo(Order o) {
		if (o == null) {
			return 0;
		}
		
		return this.getEnd().compareTo(o.getEnd());
	}
	
}
