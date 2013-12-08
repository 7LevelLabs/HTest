package com.ll7.htest.q8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

	public final static String MSG_WELCOME = "Please, input students scores as follows : nnnn-ss, or just hit Enter.";

	static Worker w;

	public static void main(String[] args) {
		w = new Worker();
	}

	private static class Worker {
		private boolean isInputActive;

		BufferedReader keyboard;

		private HashMap<String,Student> students;

		public Worker () {
			//initial settings
			isInputActive = true;
			this.keyboard = new BufferedReader(new InputStreamReader(System.in));
			students = new HashMap<String, Student>();

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
						collectInput(s);
					} else {
						System.out.println(MSG_WELCOME);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			action();
		}

		private void collectInput(final String input) {
			String n, s;
			StringTokenizer st = new StringTokenizer(input,"-");

			n = st.nextToken();
			s = st.nextToken();

			if (students.containsKey(n)) {
				students.get(n).addScore(s);
			} else {
				Student student = new Student(n);
				student.addScore(s);
				students.put(student.getNumber(),student);
			}
		}

		private boolean isValidInput(final String s) {
			return s.matches("[0-9]{4}[-][0-9]{1,2}");
		}

		private void action() {
			String s1 = "";

			StringBuilder sb = new StringBuilder();
			Iterator<String> iterator = students.keySet().iterator();

			Student student, tStudent;

			//build list to reverse
			ArrayList<Student> studentsToSort = new ArrayList<Student>();

			while (iterator.hasNext()) {
				student = students.get(iterator.next());
				tStudent = new Student(student.getNumber());
				tStudent.addScore(student.getBestScore());
				studentsToSort.add(tStudent);
			}

			//must be reverse
			Collections.sort(studentsToSort,Collections.reverseOrder(new Student("")));

			//output just reversed list
			Iterator<Student> si = studentsToSort.iterator();
			while (si.hasNext()) {
				tStudent = si.next();
				sb.append(tStudent.getNumber())
					.append("-")
					.append(tStudent.getBestScore())
					.append("\n");
			}

			s1 = sb.toString();
			System.out.println(s1);
		}

		private String readInput() throws IOException {
			return this.keyboard.readLine();
		}
	}

	private static class Student implements Comparator<Student> {
		private final String number;

		private ArrayList<String> scores;

		public Student(final String number) {
			this.number = number;
			scores = new ArrayList<String>();
		}

		public void addScore (final String score) {
			this.scores.add(score);
		}

		public String getBestScore() {
			String res = "";

			Collections.sort(scores);
			res = scores.get(scores.size()-1);

			return res;
		}

		private String getNumber() {
			return number;
		}

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getBestScore().compareTo(o2.getBestScore());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Student student = (Student) o;

			if (!number.equals(student.number)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return number.hashCode();
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Student{");
			sb.append("number='").append(number).append('\'');
			sb.append(", scores=").append(scores);
			sb.append('}');
			return sb.toString();
		}
	}
}
