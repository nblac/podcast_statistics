package org.podcast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private static final String fileName = "src/test/resources/downloads.txt";
    private static Statistics stats;

    @BeforeEach
    void setUp() {
        stats = new Statistics();
    }


    @Test
    void podcastsDownloadsByCity() {
    }

    @Test
    void podcastsDownloadsByDevice() {
    }

    @Test
    void mostPopularShowByDownloads() {
    }

    @Test
    void mostPopularDeviceByDownloads() {
    }
}