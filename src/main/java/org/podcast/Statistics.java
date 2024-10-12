package org.podcast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Statistics {

    public ArrayList readUsingScanner(String fileName) throws IOException {
        // define var for file location
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        ArrayList<Object> listOfPodcasts = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();

        //read line by line and store podcasts into an array list
        while(scanner.hasNextLine()){
            //process each line
            String line = scanner.nextLine();
            JsonObject jo = (JsonObject)jsonParser.parse(line);
            listOfPodcasts.add(jo);
        }
        // close file scan
        scanner.close();

        return listOfPodcasts;
    }


    public ArrayList getUniquePodcastIds(ArrayList listOfPodcasts) {
        ArrayList uniquePodcasts = new ArrayList();

        for(int i = 0; i < listOfPodcasts.toArray().length; i++){
            JsonObject podcast = (JsonObject) listOfPodcasts.get(i);
            JsonObject downloadIdentifier = (JsonObject) podcast.get("downloadIdentifier");
            JsonElement element = downloadIdentifier.get("podcastId");
            if(!uniquePodcasts.contains(element)) {
                uniquePodcasts.add(element);
            }
        }
        return uniquePodcasts;
    }
}
