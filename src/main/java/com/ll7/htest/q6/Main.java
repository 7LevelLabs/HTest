package com.ll7.htest.q6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	public final static String MSG_WELCOME = "Please, input the year and the month as follows : yyyy-mm.";
	public final static String VALUE_DAY = "28";

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
						this.isInputActive = false;
						break;
					}
					if (this.isValidInput(s)) {
						action(s);

					} else {
						System.out.println(MSG_WELCOME);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		private boolean isValidInput(final String s) {
			return s.matches("[0-9]{4}[-][0-9]{1,2}");
		}

		private void action(final String input) throws ParseException {
			String s1;
			s1 = calcWeekDay(input);
			System.out.println(s1);
		}

		private String calcWeekDay(final String input) throws ParseException {

			String res = "";

			String y,m,newInput;

			StringTokenizer st = new StringTokenizer(input,"-");
			y=st.nextToken();
			m=st.nextToken();

			newInput = new StringBuilder()
				.append(y)
				.append("-")
				.append(Integer.parseInt(m)+1).toString();

			Date date;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String sdfSource = new StringBuilder()
				.append(newInput)
				.append("-")
				.append(VALUE_DAY)
				.toString();

			date = sdf.parse(sdfSource);

			sdf.applyPattern("EEEE");
			res = sdf.format(date).toUpperCase();

			return res;
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}


}
