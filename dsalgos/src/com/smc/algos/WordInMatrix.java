package com.smc.algos;

/**
 * https://docs.google.com/document/d/170koTJhA9ZSuwV_QR4L6HZXaW1mUwGgXCy5K72fAI8E/edit#
 * 
 * @author seshu
 */
public class WordInMatrix {

	private static final int ROW = 3;
	private static final int COL = 5;
	private static final char[][] MAT = { { 'B', 'N', 'E', 'Y', 'S' }, { 'H', 'E', 'D', 'E', 'S' },
			{ 'S', 'G', 'N', 'D', 'E' } };

	// private static final String word = "DES";
	private static final String word = "BNEGSHBN";

	private static int numResults = 0;

	public static final void main(String[] args) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				search(i, j, word, "");
			}
		}

		System.out.println("Num Results: " + numResults);
	}

	private static void search(int i, int j, String wordLeft, String wordSoFar) {
		// System.out.println("\ncalled search(" + i + ", " + j + ", wordLeft: '" + wordLeft + "', wordSoFar: '" + wordSoFar + "'");
		if (i < 0 || j < 0 || i >= ROW || j >= COL) {
			// System.out.println("IndexOutOfBounds. Aborted");
			return;
		}

		// char letter = MAT[i][j];
		// System.out.println("Letter here: " + letter);

		if (MAT[i][j] != wordLeft.charAt(0)) {
			// System.out.println("Mismatch. Aborted");
			return;
		}

		String wsf = wordSoFar + MAT[i][j] + "(" + i + ", " + j + ") ";
		String wl = wordLeft.substring(1, wordLeft.length());
		if (wl.isEmpty()) {
			System.out.println("Match found! " + wordSoFar);
			numResults++;
			return;
		}
		search(i, j + 1, wl, wsf);
		search(i + 1, j + 1, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i + 1, j, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i + 1, j - 1, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i, j - 1, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i - 1, j - 1, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i - 1, j, wordLeft.substring(1, wordLeft.length()), wsf);
		search(i - 1, j + 1, wordLeft.substring(1, wordLeft.length()), wsf);
	}

}
