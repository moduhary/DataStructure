package ds.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ds.table.PriorityTable;

public class Main {
	/**
	 * Main method for the programming assignment 4.
	 * Please do the change this method for the performance of your program.
	 */
	public static void main(String[] args) throws IOException {
		final PriorityTable table = new PriorityTable();

		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			final int index = line.indexOf(' ');
			String command = line;
			if (index >= 0) command = line.substring(0, index);
			
			if ("insert".equals(command)) {
				final int secondIndex = line.indexOf(' ', index + 1);
				final String key = line.substring(index + 1, secondIndex);
				final double value = Double.parseDouble(line.substring(secondIndex + 1));
				table.insert(key, value);
			} else if ("delete".equals(command)) {
				final String key = line.substring(index + 1);
				table.delete(key);
			} else if ("search".equals(command)) {
				final String key = line.substring(index + 1);
				final double value = table.search(key);
				System.out.println(key + " " + value);
			} else if ("quit".equals(command)) {
				break;
			} else {
				System.out.println("Wrong command!");
			}
		}
		reader.close();
	}

}
