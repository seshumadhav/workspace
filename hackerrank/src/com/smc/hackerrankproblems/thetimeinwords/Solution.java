package com.smc.hackerrankproblems.thetimeinwords;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	private HashMap<Integer, String> intToString;

	public String toString(int hours, int mins) {
		StringBuilder sb = new StringBuilder();

		int hoursToShow = getHourToShow(hours, mins);
		String minsToShow;
		String time = "";

		if (isExactHour(mins)) {
			minsToShow = " o' clock";
			time = hoursToString(hoursToShow) + minsToShow;
		} else {
			if (isQuarterHour(mins)) {
				minsToShow = processQuarterHours(mins);
			} else if (isInFirstHalf(mins)) {
				String suffix = (mins == 1) ? " minute " : " minutes";
				minsToShow = intToString.get(mins) + suffix + " past ";
			} else {
				int left = 60 - mins;
				String suffix = (left == 1) ? " minute " : " minutes";
				minsToShow = intToString.get(left) + suffix + " to ";
			}
			time = minsToShow + hoursToString(hoursToShow);
		}

		sb.append(time);
		return sb.toString();
	}

	private boolean isExactHour(int mins) {
		return mins == 0;
	}

	private boolean isQuarterHour(int mins) {
		return mins == 15 || mins == 30 || mins == 45;
	}

	private boolean isInFirstHalf(int mins) {
		return mins < 30;
	}

	private String processQuarterHours(int mins) {
		String minsToShow = "";

		if (mins == 15) {
			minsToShow = "quarter past ";
		} else if (mins == 30) {
			minsToShow = "half past ";
		} else if (mins == 45) {
			minsToShow = "quarter to ";
		}

		return minsToShow;
	}

	private int getHourToShow(int hours, int mins) {
		if (mins <= 30) {
			return hours;
		}

		int hoursToShow;
		hoursToShow = (hours + 1) % 13;
		if (hoursToShow == 0) { // if 12, mod becomes 0 => show it as 1
			hoursToShow++;
		}

		return hoursToShow;
	}

	private String hoursToString(int hours) {
		return intToString.get(hours);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int m = in.nextInt();

		// System.out.println("Hours: " + h + "\tMinutes: " + m);

		Solution s = new Solution();
		s.init();

		// System.out.println("Hours: " + s.toString(h, m));
		System.out.println(s.toString(h, m));
	}

	public Solution() {
		intToString = new HashMap<>();
	}

	private void init() {
		intToString.put(1, "one");
		intToString.put(2, "two");
		intToString.put(3, "three");
		intToString.put(4, "four");
		intToString.put(5, "five");
		intToString.put(6, "six");
		intToString.put(7, "seven");
		intToString.put(8, "eight");
		intToString.put(9, "nine");
		intToString.put(10, "ten");

		intToString.put(11, "eleven");
		intToString.put(12, "twelve");
		intToString.put(13, "thirteen");
		intToString.put(14, "fourteen");
		intToString.put(15, "fifteen");
		intToString.put(16, "sixteen");
		intToString.put(17, "seventeen");
		intToString.put(18, "eighteen");
		intToString.put(19, "nineteen");
		intToString.put(20, "twenty");

		for (int i = 21; i <= 29; i++) {
			intToString.put(i, twentyoneTotwentyNine(i));
		}
	}

	private String twentyoneTotwentyNine(int mins) {
		return "twenty " + intToString.get(mins - (mins / 10)*10);
	}
}
