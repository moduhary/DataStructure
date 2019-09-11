package ds.test;

import java.util.Scanner;

import ds.queue.Queue;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Modify line below to initialize queue
		Queue<String> queue = null;

		while (sc.hasNext()) {
			String command = sc.next();

			if ("enqueue".equals(command)) {
				String item = sc.next();
				// Put your code to enqueue the item
			} else if ("dequeue".equals(command)) {
				// Put your code to dequeue and print the item
			} else if ("length".equals(command)) {
				// Put your code to print the length of the queue
			} else if ("isEmpty".equals(command)) {
				// Put your code to print whether the queue is empty or not
			} else if ("clear".equals(command)) {
				// Put your code to clear the queue
			} else if ("reverse".equals(command)) {
				// Put your code to reverse the queue
			}
		}

		sc.close();
	}
}
