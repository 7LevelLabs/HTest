package com.ll7.htest.q2;

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

	public final static String MSG_WELCOME = "Print numbers in a predetermined range.";

	public final static int IFROM= 258;
	public final static int ITO= 393;

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {

		private int iFrom;
		private int iTo;

		public Worker () {
			//initial settings
			this.iFrom=IFROM;
			this.iTo=ITO;

			System.out.println(MSG_WELCOME);

			//start
			this.lifeCycle();
		}

		private void lifeCycle() {
			for (int i = this.iFrom;i<=this.iTo;i++) {
				if (isValidNumber(i)) {
					action(i);
				}
			}
		}

		private boolean isValidNumber(final int aNumber) {
			boolean res = false;
			return !(Integer.toString(aNumber).endsWith("5"));
		}

		private void action(final int aNumber) {
			System.out.println(aNumber);
		}

	}
}
