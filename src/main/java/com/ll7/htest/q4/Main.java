package com.ll7.htest.q4;

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
	public final static String MSG_WELCOME = "Please, input 2 time intervals (one time interval per line) or just hit Enter.";
	private static final String MSG_TIME1ISOK = "Time 1 - ok. Next input - time 2.";
	private static final String MSG_TIME2ISOK = "Time 2 - ok. Calculating...";

	private static final float VALUE_VELOCITY_INIT = 36F;
	private static final float VALUE_ACCELERATION_INIT = 5;


	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {
		private boolean isInputActive;

		private boolean isTimeSet1, isTimeSet2;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			isInputActive = true;

			isTimeSet1 = false;
			isTimeSet2 = false;

			this.keyboard = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(MSG_WELCOME);

			//start
			this.lifeCycle();
		}

		private void lifeCycle() {
			String s = "";

			String time1StringView = "";
			String time2StringView = "";

			while (isInputActive) {
				try {
					s = readInput();
					if (s.length()==0) {
						break;
					}
					if (this.isValidInput(s)) {

						if (!isTimeSet1) {
							time1StringView = s;
							this.isTimeSet1 = true;
							System.out.println(MSG_TIME1ISOK);
						} else

						if (!isTimeSet2) {
							time2StringView = s;
							this.isTimeSet2 = true;
							System.out.println(MSG_TIME2ISOK);

							this.isInputActive = false;
							break;
						}
					} else {
						System.out.println(MSG_WELCOME);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			action(time1StringView, time2StringView);
		}

		private boolean isValidInput(final String s) {
			return s.matches("[0-9]{1,}");
		}

		private void action(final String time1StringView, final String time2StringView) {
			float res1 = calcDistance(time1StringView);
			float res2 = calcDistance(time2StringView);

			System.out.println(res1);
			System.out.println(res2);
		}

		private float calcDistance(final String timeStringView) {
			float distance = 0F;

			float u = VALUE_VELOCITY_INIT;
			float a = VALUE_ACCELERATION_INIT;

			int t = Integer.valueOf(timeStringView);

			distance = u*1000/3600*t+((a*t*t)/2);

			return distance;
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}
}
