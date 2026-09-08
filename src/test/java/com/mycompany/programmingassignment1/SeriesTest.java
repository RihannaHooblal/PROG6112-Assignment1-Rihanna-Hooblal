package com.mycompany.programmingassignment1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests required by Section A.
 */
public class SeriesTest {

    private Series seriesManager;

    @BeforeEach
    void setUp() {
        ArrayList<Series.SeriesRecord> initialData = new ArrayList<>();
        initialData.add(new Series.SeriesRecord("101", "Extreme Sports", 12, 10));
        initialData.add(new Series.SeriesRecord("102", "Bargain Hunters", 10, 10));
        initialData.add(new Series.SeriesRecord("103", "Home Cooking", 10, 20));
        seriesManager = new Series(initialData);
    }

    @Test
    void TestSearchSeries() {
        Series.SeriesRecord result = seriesManager.SearchSeries("101");

        assertNotNull(result);
        assertEquals("101", result.getSeriesId());
        assertEquals("Extreme Sports", result.getSeriesName());
        assertEquals(12, result.getAgeRestriction());
        assertEquals(10, result.getNumberOfEpisodes());
    }

    @Test
    void TestSearchSeries_SeriesNotFound() {
        Series.SeriesRecord result = seriesManager.SearchSeries("999");

        assertNull(result);
    }

    @Test
    void TestUpdateSeries() {
        Series.SeriesRecord result =
                seriesManager.UpdateSeries("101", "Extreme Sports 2025", 10, 12);

        assertNotNull(result);
        assertEquals("Extreme Sports 2025", result.getSeriesName());
        assertEquals(10, result.getAgeRestriction());
        assertEquals(12, result.getNumberOfEpisodes());
    }

    @Test
    void TestDeleteSeries() {
        boolean deleted = seriesManager.DeleteSeries("101");

        assertTrue(deleted);
        assertNull(seriesManager.SearchSeries("101"));
    }

    @Test
    void TestDeleteSeries_SeriesNotFound() {
        boolean deleted = seriesManager.DeleteSeries("999");

        assertFalse(deleted);
        assertEquals(3, seriesManager.getSeriesCount());
    }

    @Test
    void TestSeriesAgeRestriction_AgeValid() {
        assertTrue(seriesManager.isValidAgeRestriction(2));
        assertTrue(seriesManager.isValidAgeRestriction(10));
        assertTrue(seriesManager.isValidAgeRestriction(18));
    }

    @Test
    void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(seriesManager.isValidAgeRestriction(1));
        assertFalse(seriesManager.isValidAgeRestriction(19));
        assertFalse(seriesManager.isValidAgeRestriction(25));
    }
}
