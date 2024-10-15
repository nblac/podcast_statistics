package org.podcast;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsTest {

    static String fileName = "src/test/resources/downloads.txt";
    static FileReader fileReader = new FileReader();
    static ArrayList listOfPodcasts = null;
    Statistics stats = new Statistics();

    @BeforeAll
    public static void setUp() {

        try {
            listOfPodcasts = fileReader.readUsingScanner(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void podcastsDownloadsByCityTest() {

        String expectedString = "Most popular show is: Who Trolled Amber\nNumber of downloads is: 24";

        JsonObject listOfPodcastsByDownloads = stats.podcastsDownloadsByCity(listOfPodcasts, "San Francisco");
        String mostPopularShow = stats.mostPopularShowByDownloads(listOfPodcastsByDownloads);
        System.out.println(mostPopularShow);

        assertEquals(expectedString, mostPopularShow );
    }

    @Test
    void podcastsDownloadsByDeviceTest() {
        String expectedString = "Most popular device is: mobiles & tablets\nNumber of downloads is: 60";
        JsonObject downloadsByDevice = stats.podcastsDownloadsByDevice(listOfPodcasts);
        String mostPopularDevice = stats.mostPopularDeviceByDownloads(downloadsByDevice);
        System.out.println(mostPopularDevice);

        assertEquals(expectedString, mostPopularDevice);
    }


    @Test
    void listTheOpportunitiesToInsertCommercial(){
        String expectedString = """
                                    Show Id: Stuff You Should Know, Preroll Opportunity Number: 40
                                    Show Id: Who Trolled Amber, Preroll Opportunity Number: 40
                                    Show Id: Crime Junkie, Preroll Opportunity Number: 30
                                    Show Id: The Joe Rogan Experience, Preroll Opportunity Number: 10
                                """;

        JsonObject opportunitiesToInsertCommercial = stats.opportunitiesToInsertCommercial(listOfPodcasts);
        JsonObject sortedObject = stats.arrangeOpportunitiesToInsertCommercialHighToLow(opportunitiesToInsertCommercial);

        String returnedString =  stats.formatStringsForOpportunitiesToInsertCommercial(sortedObject);

        assertEquals(expectedString, returnedString);
    }
}