package com.ll7.htest.q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	public final static String MSG_WELCOME = "Please, input 4 user names or just hit Enter.";
	public static final String MSG_FAIL = "FAIL";
	public static final String MSG_PASS = "PASS";

	private static final int VALUE_ITERATIONS_NUMBER = 4;

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {

		private String[] results;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			results = new String[VALUE_ITERATIONS_NUMBER];

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
					results[attempt_number] = getValidityResult(s);
					attempt_number++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (attempt_number<=VALUE_ITERATIONS_NUMBER-1);
			printResults();
		}

		private void printResults() {
			for (int i = 0;i<VALUE_ITERATIONS_NUMBER;i++) {
				System.out.println(results[i]);
			}
		}

		private String getValidityResult(final String s) {
			String res = "";
			boolean isValid = true;
			if ((s.length()<5) || (s.length()>10)) {
				return MSG_FAIL;
			}
			if (s.contains(" ")) {
				return MSG_FAIL;
			}
			if (!s.matches(".*[0-9].*")) {
				return MSG_FAIL;
			}
			if (!s.matches(".*[A-Z].*")) {
				return MSG_FAIL;
			}
			if (!s.matches(".*[@#*=].*")) {
				return MSG_FAIL;
			}
			if (isValid) {
				res = MSG_PASS;
			}
			else {
				res = MSG_FAIL;
			}
			return res;
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}

}
