package org.podcast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/test/resources/downloads.txt";
        FileReader fileReader = new FileReader();
        Statistics stats = new Statistics();

        ArrayList listOfPodcasts = fileReader.readUsingScanner(fileName);

        JsonObject listOfPodcastsByDownloads = stats.podcastsDownloadsByCity(listOfPodcasts, "San Francisco");
        String mostPopularShow = stats.mostPopularShowByDownloads(listOfPodcastsByDownloads);
        System.out.println(mostPopularShow);

        JsonObject downloadsByDevice = stats.podcastsDownloadsByDevice(listOfPodcasts);
        String mostPopularDevice = stats.mostPopularDeviceByDownloads(downloadsByDevice);
        System.out.println(mostPopularDevice);


        JsonObject opportunitiesToInsertCommercial = stats.opportunitiesToInsertCommercial(listOfPodcasts);
        System.out.println(stats.arrangeOpportunitiesToInsertCommercialHighToLow(opportunitiesToInsertCommercial));
        JsonObject ttt = stats.arrangeOpportunitiesToInsertCommercialHighToLow(opportunitiesToInsertCommercial);
        String see = stats.formatStringsForOpportunitiesToInsertCommercial(ttt);
        System.out.println(see);
    }

}
