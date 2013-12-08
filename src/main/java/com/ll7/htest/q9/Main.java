package com.ll7.htest.q9;

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
	public final static String MSG_WELCOME = "Please, input 4 number of hops or just hit Enter.";
	public final static String MSG_ONLY_DIGITS = "Only digits, pls.";

	public final static int VALUE_1HOP_DIST = 20;
	public final static int VALUE_2HOP_DIST = 10;
	public final static int VALUE_3HOP_DIST = 5;

	private static final int VALUE_ITERATIONS_NUMBER = 4;

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {

		private int[] results;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			results = new int[VALUE_ITERATIONS_NUMBER];

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
					if (isValidInput(s))
					{
						results[attempt_number] = getCalculationResult(Integer.parseInt(s));
						attempt_number++;
					} else {
						System.out.println(MSG_ONLY_DIGITS);
					}
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

		private int getCalculationResult(final int n) {
			int distRes = 0;

			int i = 0;

			while (i<n) {
				//first hop
				i++;
				distRes=distRes+VALUE_1HOP_DIST;
				if (i==n) {
					break;
				}
				//second hop
				i++;
				distRes=distRes+VALUE_2HOP_DIST;
				if (i==n) {
					break;
				}

				//third hop
				i++;
				distRes=distRes+VALUE_3HOP_DIST;

				//rest
				restForAWhile();
			}

			return distRes;
		}

		private boolean isValidInput(final String s) {
			return s.matches("[0-9]{1,}");
		}

		private void restForAWhile() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}

}
