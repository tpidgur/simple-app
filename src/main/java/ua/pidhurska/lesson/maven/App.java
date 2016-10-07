package ua.pidhurska.lesson.maven;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Hello World!");
        } else {
            System.out.println("With arguments"+Arrays.toString(args));
        }
    }
}
