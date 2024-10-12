package org.podcast;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // define var for file location
        String fileName = "src/test/resources/downloads.txt";

        //using Scanner class for large files, to read line by line
        readUsingScanner(fileName);
    }

    private static void readUsingScanner(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        System.out.println("Read text file using Scanner");
        //read line by line
        while(scanner.hasNextLine()){
            //process each line
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
    }
}