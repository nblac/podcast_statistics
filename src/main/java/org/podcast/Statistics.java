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


    public JsonObject podcastsDownloadsByCity(ArrayList listOfPodcasts, String cityName) {

        JsonObject result = new JsonObject();

        for(int i = 0; i < listOfPodcasts.toArray().length; i++){
            JsonObject podcast = (JsonObject) listOfPodcasts.get(i);
            JsonObject downloadIdentifier = (JsonObject) podcast.get("downloadIdentifier");
            JsonElement readCity = podcast.get("city");

            if(cityName.toLowerCase().equals(readCity.getAsString())) {
                if(result.has(downloadIdentifier.get("showId").getAsString())) {
                    result.addProperty(
                            downloadIdentifier.get("showId").getAsString(),
                            result.get(downloadIdentifier.get("showId").getAsString()).getAsInt() + 1
                    );
                } else {
                    result.addProperty(downloadIdentifier.get("showId").getAsString(), 1);
                }
            }
        }
        return result;
    }




    public String mostPopularShowByDownloads(JsonObject listenedPodcasts){
        String mostPopularShow = "";
        int maxDownloads = 0;

        for(String showId : listenedPodcasts.keySet()){
            int downloads = listenedPodcasts.get(showId).getAsInt();
            if(downloads > maxDownloads){
                maxDownloads = listenedPodcasts.get(showId).getAsInt();
                mostPopularShow = showId;
            }
        }
        String popularShow = String.format("Most popular show is: %s\nNumber of downloads is: %s", mostPopularShow, maxDownloads);

        return popularShow;
    }


}
