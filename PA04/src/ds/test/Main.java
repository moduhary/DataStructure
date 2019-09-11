package ds.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import ds.sort.HybridSorter;
import ds.sort.Pair;
import ds.sort.Sorter;

public class Main {

	// The main method below is optimized for huge input and output.
	// Please do not change the main method for the performance of your program.
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		int current = 0;
		Pair<String, Integer>[] data = null;
		String line = null;
		
		// output
		final StringBuilder builder = new StringBuilder(512);
		
		// hybrid sorter
		final Sorter<String> sorter = new HybridSorter<String>();
		
		while ((line = reader.readLine()) != null) {
			final int index = line.indexOf(' ');
			String command = null;
			if (index == -1) {
				command = line;
			} else {
				command = line.substring(0, index);
			}
			if ("n".equals(command)) {
				final int n = Integer.parseInt(line.substring(index + 1));
				data = (Pair<String, Integer>[]) Array.newInstance(Pair.class, n);
			} else if ("append".equals(command)) {
				final int secondIndex = line.indexOf(' ', index + 1);
				final String key = line.substring(index + 1, secondIndex);
				final int value = Integer.parseInt(line.substring(secondIndex + 1));
				data[current] = new Pair<String, Integer>(key, value);
				++current;
			} else if ("sort".equals(command)) {
				sorter.sort(data, 0, current - 1);
			} else if ("print".equals(command)) {
				final int i = Integer.parseInt(line.substring(index + 1));
				builder.append("Print: ");
				builder.append(i);
				builder.append(' ');
				builder.append(data[i].getKey());
				builder.append(' ');
				builder.append(data[i].getValue());
				System.out.println(builder.toString());
				builder.setLength(0);
			}
		}
		reader.close();
	}

}
