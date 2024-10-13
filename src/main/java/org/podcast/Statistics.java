package org.podcast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;


public class Statistics {

    /**
     * Calculates the number of podcast downloads by city.
     *
     * @param listOfPodcasts the list of podcasts
     * @param cityName the name of the city
     * @return a JsonObject containing the number of downloads for each show in the specified city
     */
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


    /**
     * Calculates the number of podcast downloads by device type.
     *
     * @param listOfPodcasts the list of podcasts
     * @return a JsonObject containing the number of downloads for device type
     */
    public JsonObject podcastsDownloadsByDevice(ArrayList listOfPodcasts) {

        JsonObject result = new JsonObject();

        for(int i = 0; i < listOfPodcasts.toArray().length; i++){
            JsonObject podcast = (JsonObject) listOfPodcasts.get(i);
            JsonElement readDevice = podcast.get("deviceType");


            if(result.has(readDevice.getAsString())) {
                result.addProperty(
                        readDevice.getAsString(),
                        result.get(podcast.get("deviceType").getAsString()).getAsInt() + 1
                );
            } else {
                result.addProperty(podcast.get("deviceType").getAsString(), 1);
            }

        }
        return result;
    }


    /**
     * Determines the most popular show by the number of downloads.
     *
     * @param listenedPodcasts a JsonObject containing the number of downloads for each show
     * @return a JsonObject describing the most popular show and the number of downloads
     */
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

    /**
     * Determines the most popular device by the number of downloads.
     *
     * @param downloadsByDevice a JsonObject containing the number of downloads for each device type
     * @return a string describing the most popular device and its number of downloads
     */
    public String mostPopularDeviceByDownloads(JsonObject downloadsByDevice){
        String mostPopularDevice = "";
        int maxDownloads = 0;

        for(String device : downloadsByDevice.keySet()){
            int downloads = downloadsByDevice.get(device).getAsInt();
            if(downloads > maxDownloads){
                maxDownloads = downloadsByDevice.get(device).getAsInt();
                mostPopularDevice = device;
            }
        }
        String popularDevice = String.format("Most popular device is: %s\nNumber of downloads is: %s", mostPopularDevice, maxDownloads);

        return popularDevice;
    }

}
