package org.podcast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Statistics {

    /**
     * Calculates the number of podcast downloads by city.
     *
     * @param listOfPodcasts as JsonObject - the list of podcasts
     * @param cityName       the name of the city
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

        return String.format("Most popular show is: %s\nNumber of downloads is: %s", mostPopularShow, maxDownloads);
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

        return String.format("Most popular device is: %s\nNumber of downloads is: %s", mostPopularDevice, maxDownloads);
    }


    /**
     * Determine the numbers of opportunities to insert a commercial advertisement
     *
     * @param listOfPodcasts as JsonObject - the list of podcasts
     * @return the number of opportunities to insert a commercial advertisement by show
     */

    public JsonObject opportunitiesToInsertCommercial(ArrayList listOfPodcasts) {
        JsonObject results = new JsonObject();

        for (int i = 0; i < listOfPodcasts.toArray().length; i++) {
            JsonObject podcast = (JsonObject) listOfPodcasts.get(i);
            JsonObject downloadIdentifier = podcast.get("downloadIdentifier").getAsJsonObject();
            String showId = downloadIdentifier.get("showId").getAsString();
            JsonArray opportunitiesJsonNode = (JsonArray) podcast.get("opportunities");

            for (int y = 0; y < opportunitiesJsonNode.size(); y++) {
                JsonObject opportunity = (JsonObject) opportunitiesJsonNode.get(y);
                JsonObject positionUrlSegments = opportunity.get("positionUrlSegments").getAsJsonObject();
                JsonArray adBreakIndex = positionUrlSegments.get("aw_0_ais.adBreakIndex").getAsJsonArray();

                if (adBreakIndex.contains(new JsonPrimitive("preroll"))) {
                    if (results.has(showId)) {
                        results.addProperty(
                                showId,
                                results.get(showId).getAsInt() + 1
                        );
                    } else {
                        results.addProperty(showId, 1);
                    }
                }
            }
        }
        return results;
    }

    /**
     * Arrange the opportunities to insert a commercial advertisement from high to low
     *
     * @param opportunitiesToInsertCommercial as JsonObject - the number of opportunities to insert a commercial advertisement by show
     * @return a JsonObject - of the shows sorted by the number of the opportunities to insert a commercial / advertisement
     */
    public JsonObject arrangeOpportunitiesToInsertCommercialHighToLow(JsonObject opportunitiesToInsertCommercial) {
        // TODO check for sorting maps by key and value https://www.baeldung.com/java-sorting
        // Convert JsonObject to a list of Map.Entry
        List<Map.Entry<String, JsonElement>> entries = new ArrayList<>(opportunitiesToInsertCommercial.entrySet());

        // Sort the list by values in descending order
        entries.sort((e1, e2) -> e2.getValue().getAsInt() - e1.getValue().getAsInt());

        // Create a new sorted JsonObject
        JsonObject sortedJsonObject = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : entries) {
            sortedJsonObject.add(entry.getKey(), entry.getValue());
        }

        return sortedJsonObject;
    }

    /**
     * Format the strings for the opportunities to insert a commercial / advertisement
     *
     * @param sortedOpportunitiesToInsertCommercial as JsonObject
     * @return a string of the shows sorted by the number of the opportunities - desired output
     */
    public String formatStringsForOpportunitiesToInsertCommercial(JsonObject sortedOpportunitiesToInsertCommercial) {
        StringBuilder result = new StringBuilder();
        for (String showId : sortedOpportunitiesToInsertCommercial.keySet()) {
            result.append("Show Id: ").append(showId).append(", Preroll Opportunity Number: ").append(sortedOpportunitiesToInsertCommercial.get(showId).getAsInt()).append("\n");
        }
        return result.toString();
    }

    /**
     * Determine the numbers of opportunities to insert a commercial advertisement
     *
     * @param listOfPodcasts as JsonObject - the list of podcasts
     * @return the number of opportunities to insert a commercial advertisement by show
     */

    public JsonObject opportunitiesToInsertCommercial(ArrayList listOfPodcasts) {
        JsonObject results = new JsonObject();

        for (int i = 0; i < listOfPodcasts.toArray().length; i++) {
            JsonObject podcast = (JsonObject) listOfPodcasts.get(i);
            JsonObject downloadIdentifier = podcast.get("downloadIdentifier").getAsJsonObject();
            String showId = downloadIdentifier.get("showId").getAsString();
            JsonArray opportunitiesJsonNode = (JsonArray) podcast.get("opportunities");

            for (int y = 0; y < opportunitiesJsonNode.size(); y++){
                JsonObject opportunity = (JsonObject) opportunitiesJsonNode.get(y);
                JsonObject positionUrlSegments = opportunity.get("positionUrlSegments").getAsJsonObject();
                JsonArray adBreakIndex = positionUrlSegments.get("aw_0_ais.adBreakIndex").getAsJsonArray();

                if (adBreakIndex.contains(new JsonPrimitive("preroll"))){
                    if (results.has(showId)) {
                        results.addProperty(
                                showId,
                                results.get(showId).getAsInt() + 1
                        );
                    } else {
                        results.addProperty(showId, 1);
                    }
                }
            }
        }
        return results;
    }

    /**
     * Arrange the opportunities to insert a commercial advertisement from high to low
     * @param - opportunitiesToInsertCommercial as JsonObject - the number of opportunities to insert a commercial advertisement by show
     * @return a JsonObject - of the shows sorted by the number of the opportunities to insert a commercial / advertisement
     */
    public JsonObject arrangeOpportunitiesToInsertCommercialHighToLow(JsonObject opportunitiesToInsertCommercial) {
        // TODO check for sorting maps by key and value https://www.baeldung.com/java-sorting
        // Convert JsonObject to a list of Map.Entry
        List<Map.Entry<String, JsonElement>> entries = new ArrayList<>(opportunitiesToInsertCommercial.entrySet());

        // Sort the list by values in descending order
        entries.sort((e1, e2) -> e2.getValue().getAsInt() - e1.getValue().getAsInt());

        // Create a new sorted JsonObject
        JsonObject sortedJsonObject = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : entries) {
            sortedJsonObject.add(entry.getKey(), entry.getValue());
        }

        return sortedJsonObject;
    }

    /**
     * Format the strings for the opportunities to insert a commercial / advertisement
     * @param sortedOpportunitiesToInsertCommercial as JsonObject
     * @return a string of the shows sorted by the number of the opportunities - desired output
     */
    public String formatStringsForOpportunitiesToInsertCommercial(JsonObject sortedOpportunitiesToInsertCommercial){
        StringBuilder result = new StringBuilder();
        for (String showId : sortedOpportunitiesToInsertCommercial.keySet()) {
            result.append("Show Id: ").append(showId).append(", Preroll Opportunity Number: ").append(sortedOpportunitiesToInsertCommercial.get(showId).getAsInt()).append("\n");
        }
        return result.toString();
    }
}
