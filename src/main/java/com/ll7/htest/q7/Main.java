package com.ll7.htest.q7;

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
	public final static String MSG_WELCOME = "Please, input the employee ids participating in each event" +
		" (the first line relates to the first event and the second line relates to the second event).";
	private static final String MSG_EVENT1ISOK = "Event 1 - ok. Next input - event 2.";
	private static final String MSG_EVENT2ISOK = "Event 2 - ok. Calculating...";

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {
		private boolean isInputActive;

		private boolean isEventSet1, isEventSet2;

		BufferedReader keyboard;

		public Worker () {
			//initial settings
			isInputActive = true;

			isEventSet1 = false;
			isEventSet2 = false;

			this.keyboard = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(MSG_WELCOME);

			//start
			this.lifeCycle();
		}

		private void lifeCycle() {
			String s = "";

			String event1StringView = "";
			String event2StringView = "";

			while (isInputActive) {
				try {
					s = readInput();
					if (s.length()==0) {
						break;
					}

					if (this.isValidInput(s)) {

						if (!isEventSet1) {
							event1StringView = s;
							this.isEventSet1 = true;
							System.out.println(MSG_EVENT1ISOK);
						} else

						if (!isEventSet2) {
							event2StringView = s;
							this.isEventSet2 = true;
							System.out.println(MSG_EVENT2ISOK);

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
			action(event1StringView, event2StringView);
		}

		private boolean isValidInput(final String s) {
			//in this case - everything is valid
			return true;
		}

		private void action(final String event1StringView, final String event2StringView) {
			String[] idsEvent1, idsEvent2;

			idsEvent1 = getIDsToArrays(event1StringView);
			idsEvent2 = getIDsToArrays(event2StringView);

			int s1, s2, i1, i2;

			int dupsNumber = 0;

			s1 = idsEvent1.length;
			s2 = idsEvent2.length;

			for (i1 = 0; i1 < s1 ;i1++) {
				for (i2 = 0; i2 < s2 ;i2++) {
					if (idsEvent1[i1].equals(idsEvent2[i2])) {
						dupsNumber++;
					}
				}
			}

			System.out.println(dupsNumber);
		}

		private String[] getIDsToArrays(final String stringView) {
			StringTokenizer st = new StringTokenizer(stringView,",");
			int number = st.countTokens();
			int i = 0;

			String[] strings = new String[number];

			while (st.hasMoreTokens()) {
				strings[i] = st.nextToken();
				i++;
			}

			return strings;
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}
}
