package main;

import structures.BinaryTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /** Main class that you are not allowed to modify. */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("quit")) break;

            BinaryTree tree = new BinaryTree(line);
            int value = tree.calculate();
            String traversal = tree.postorder();

            System.out.println(value + " : " + traversal);
        }
    }

    // Implement more functions HERE
}
