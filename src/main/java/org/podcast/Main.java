package org.podcast;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        // define var for file location
        String fileName = "src/test/resources/downloads.txt";
        Statistics stats = new Statistics();

        //using Scanner class for large files, to read line by line
        ArrayList listOfPodcasts = stats.readUsingScanner(fileName);
        ArrayList podcastIds = stats.getUniquePodcastIds(listOfPodcasts);

        System.out.println(podcastIds);
    }

}
