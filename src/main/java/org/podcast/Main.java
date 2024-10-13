package org.podcast;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/test/resources/downloads.txt";
        FileReader fileReader = new FileReader();
        Statistics stats = new Statistics();

        ArrayList listOfPodcasts = fileReader.readUsingScanner(fileName);
        JsonObject listOfPodcastsByDownloads = stats.podcastsDownloadsByCity(listOfPodcasts, "San Francisco");
//        System.out.println(listOfPodcastsByDownloads);

        String mostPopularShow = stats.mostPopularShowByDownloads(listOfPodcastsByDownloads);
        System.out.println(mostPopularShow);

        assertEquals(mostPopularShow, "Most popular show is: Who Trolled Amber\nNumber of downloads is: 24");

        JsonObject downloadsByDevice = stats.podcastsDownloadsByDevice(listOfPodcasts);
//        System.out.println(downloadsByDevice);

        String mostPopularDevice = stats.mostPopularDeviceByDownloads(downloadsByDevice);
        System.out.println(mostPopularDevice);
        assertEquals(mostPopularDevice, "Most popular device is: mobiles & tablets\nNumber of downloads is: 60");
    }

}
