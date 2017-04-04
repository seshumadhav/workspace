package com.smc.thoughtworks.utils;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class Utils {
	
	public static final String[] tokenize(String line, String separator) {
		if (isNullOrEmpty(line) || isNullOrEmpty(separator)) {
			return new String[] {};
		}

		String[] tokens = line.split(separator);
		return trim(tokens);
	}
	
	public static final String[] trim(String[] source) {
		if (isNullOrEmpty(source)) {
			return new String[] {};
		}
		
		String[] destination = new String[source.length];
		int i = 0;
		for (String string : source) {
			destination[i++] = string.trim();
		}
		
		return destination;
	}

	public static boolean isNullOrEmpty(String[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
	public static final int toInt(String line) {
		if (isNullOrEmpty(line)) {
			return 0;
		}
		
		try {
			return new Integer(line);
		} catch (Exception e) {
			return 0;
		}
	}
	
}

