package com.ll7.htest.q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public final static String MSG_WELCOME = "Please, input a single pair of strings separated by a comma.";
	public final static String MSG_INACTION = "In action is : ";

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {
		private boolean isInputActive;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			isInputActive = true;
			this.keyboard = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(MSG_WELCOME);

			//start
			this.lifeCycle();
		}

		private void lifeCycle() {
			String s = "";

			while (isInputActive) {
				try {
					s = readInput();
					if (s.length()==0) {
						break;
					}
					if (this.isValidInput(s)) {
						this.isInputActive = false;
						break;
					} else {
						System.out.println(MSG_WELCOME);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			action(s);
		}

		/**
		 *
		 * Based on ASCII table, we have to exclude not standard ASCII Codes
		 * */
		private boolean isValidInput(final String s) {
			return s.matches("[!-+--~]+[,][!-+--~]+");
		}

		private void action(final String input) {
			System.out.println(MSG_INACTION+input);

			String s1, s2;
			StringTokenizer st = new StringTokenizer(input,",");

			s1 = st.nextToken();
			s2 = st.nextToken();

			int res = calcAsciiSum(s1)-calcAsciiSum(s2);

			System.out.println(res);
		}

		private int calcAsciiSum(final String s) {
			int res = 0;
			for (int i = 0;i < s.length();i++) {
				char c = s.charAt(i);
				res += (int) c;
			}
			return res;
		}


		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}

}
