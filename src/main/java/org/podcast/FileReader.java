package org.podcast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader class reads a file from a specific location and returns an array list of podcasts
 */

public final class FileReader {

    /**
     * Reading a large file using Scanner class from specific location,
     * get each line and transform it into a JsonObject using JsonParser class
     * and returns an array list of podcasts
     * @param fileName as string - the file location in txt format
     * @return          listOfPodcasts - an array list of podcasts
     */
    public ArrayList readUsingScanner(String fileName) throws IOException {
        // define var for file location
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        JsonParser jsonParser = new JsonParser();

        //crete array list object to store podcasts read from file
        ArrayList<Object> listOfPodcasts = new ArrayList<>();

        //read line by line and store podcasts into an array list
        while(scanner.hasNextLine()){
            //process each line and store it in an array list
            String line = scanner.nextLine();
            JsonObject jo = (JsonObject)jsonParser.parse(line);
            listOfPodcasts.add(jo);
        }
        // close file scan
        scanner.close();

        return listOfPodcasts;
    }
}
