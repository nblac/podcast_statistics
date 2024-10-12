package org.podcast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;



public class Main {
    public static void main(String[] args) throws IOException {
        // define var for file location
        String fileName = "src/test/resources/downloads.txt";
        Statistics stats = new Statistics();

        //using Scanner class for large files, to read line by line
        ArrayList listOfPodcasts = stats.readUsingScanner(fileName);
        JsonObject listOfPodcastsByDownloads = stats.podcastsDownloadsByCity(listOfPodcasts, "San Francisco");

        String mostPopularShow = stats.mostPopularShowByDownloads(listOfPodcastsByDownloads);
        System.out.println(mostPopularShow);

        assertEquals(mostPopularShow, "Most popular show is: Who Trolled Amber\nNumber of downloads is: 24");




    }

}
