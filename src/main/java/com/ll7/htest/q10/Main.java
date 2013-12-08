package com.ll7.htest.q10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * HTest
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 *
 * Attempted to implement as clean, well-factored, OO-code.
 * However, limitations in the problem statement are not allowed to use,
 * for example, Template method pattern, etc.
 */
public class Main {
	public final static String MSG_WELCOME = "Please, input 3 sentences (one sentence per line) with the words having Initial Caps or just hit Enter.";

	private static final int VALUE_ITERATIONS_NUMBER = 3;

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {

		private ArrayList<String> results;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			results = new ArrayList<String>();

			this.keyboard = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(MSG_WELCOME);

			//start
			this.lifeCycle();
		}

		private void lifeCycle() {
			String s = "";

			int attempt_number = 0;

			do {
				try {
					s = readInput();
					if (s.length()==0) {
						break;
					}
					action(s);
					attempt_number++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (attempt_number<=VALUE_ITERATIONS_NUMBER-1);
			printResults();
		}

		private void action(String s) {
			StringTokenizer st = new StringTokenizer(s," ");
			String tString;
			while (st.hasMoreTokens()) {
				tString = st.nextToken();
				if (isValidStringToAdd(tString)) {
					results.add(tString);
				}
			}
		}

		private boolean isValidStringToAdd(final String tString) {
			return tString.substring(0,1).matches("[A-Z]");
		}

		private void printResults() {
			for (String result : results) {
				System.out.println(result);
			}
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}


}
