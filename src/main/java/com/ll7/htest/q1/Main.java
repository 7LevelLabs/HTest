package com.ll7.htest.q1;

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

	public final static String MSG_WELCOME = "Please, input one digit or just hit Enter.";
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
			StringBuilder sb = new StringBuilder();

			while (isInputActive) {
				try {
					s = readInput();
					if (s.length()==0) {
						this.isInputActive = false;
						break;
					}
					if (this.isValidInput(s)) {
						sb.append(s);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			action(sb.toString());
		}

		private boolean isValidInput(final String s) {
			return s.matches("[1-9]{1}");
		}

		private void action(final String input) {
			System.out.println(MSG_INACTION+input);
			for (int i = 0;i<input.length();i++) {
				printADigit(input.substring(i,i+1));
			}
		}

		private void printADigit(final String aDigitView) {
			int theRealDigit = new Integer(aDigitView).intValue();
			int i;
			for (i = 1;i<=theRealDigit;i++) {
				System.out.println(this.viewTempDigit(i));
			}
		}

		private String viewTempDigit(final int td) {
			String s = "";
			StringBuilder sb = new StringBuilder();
			for (int i = 1;i<=td-1;i++) {
				sb.append(i).append(" ");
			}
			sb.append(td);
			return sb.toString();
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}
}
