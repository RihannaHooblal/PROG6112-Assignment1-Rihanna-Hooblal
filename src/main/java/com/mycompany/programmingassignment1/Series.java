package com.mycompany.programmingassignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Series management class for Programming Assignment 1.
 * Contains the working methods required by Section A.
 */
public class Series {

    private final ArrayList<SeriesRecord> seriesList;
    private final Scanner scanner;

    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Constructor overload used by tests when an existing collection is supplied.
    public Series(ArrayList<SeriesRecord> initialSeries) {
        seriesList = initialSeries;
        scanner = new Scanner(System.in);
    }

    /**
     * 1.2 Captures a new series and stores it in memory.
     */
    public void CaptureSeries() {
        System.out.println("\n----------------------------------------");
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("----------------------------------------");

        String id = readNonEmptyString("Enter the series id: ");

        if (findSeries(id) != null) {
            System.out.println("A series with this Series ID already exists.");
            return;
        }

        String name = readNonEmptyString("Enter the series name: ");
        int ageRestriction = readAgeRestriction();
        int episodes = readPositiveInteger("Enter the number of episodes: ");

        SeriesRecord series = new SeriesRecord(id, name, ageRestriction, episodes);
        seriesList.add(series);

        System.out.println("\nSeries details have been successfully saved!");
    }

    /**
     * Console version of the search method.
     */
    public void SearchSeries() {
        System.out.print("\nEnter the series id to search: ");
        String id = scanner.nextLine().trim();
        SeriesRecord found = SearchSeries(id);

        if (found != null) {
            System.out.println("----------------------------------------");
            System.out.println(found);
            System.out.println("----------------------------------------");
        } else {
            System.out.println("----------------------------------------");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Testable search method. Returns the matching series or null.
     */
    public SeriesRecord SearchSeries(String seriesId) {
        return findSeries(seriesId);
    }

    /**
     * Console version of update.
     */
    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update: ");
        String id = scanner.nextLine().trim();
        SeriesRecord series = UpdateSeries(id, null, -1, -1);

        if (series == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }

        String name = readNonEmptyString("Enter the series name: ");
        int age = readAgeRestriction();
        int episodes = readPositiveInteger("Enter the number of episodes: ");

        series.setSeriesName(name);
        series.setAgeRestriction(age);
        series.setNumberOfEpisodes(episodes);

        System.out.println("Series with Series Id: " + id + " has been successfully updated!");
    }

    /**
     * Testable update method. Only non-null / positive supplied values are changed.
     */
    public SeriesRecord UpdateSeries(String seriesId, String name, int ageRestriction, int episodes) {
        SeriesRecord series = findSeries(seriesId);
        if (series == null) {
            return null;
        }

        if (name != null && !name.trim().isEmpty()) {
            series.setSeriesName(name.trim());
        }
        if (ageRestriction >= 0) {
            if (!isValidAgeRestriction(ageRestriction)) {
                return null;
            }
            series.setAgeRestriction(ageRestriction);
        }
        if (episodes >= 0) {
            if (episodes == 0) {
                return null;
            }
            series.setNumberOfEpisodes(episodes);
        }
        return series;
    }

    /**
     * Console version of delete with confirmation.
     */
    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        String id = scanner.nextLine().trim();

        SeriesRecord series = findSeries(id);
        if (series == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }

        System.out.print("Are you sure you want to delete series " + id
                + " from the system? Yes (y) to delete: ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("y")) {
            if (DeleteSeries(id)) {
                System.out.println("----------------------------------------");
                System.out.println("Series with Series Id: " + id + " WAS deleted!");
                System.out.println("----------------------------------------");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    /**
     * Testable delete method.
     */
    public boolean DeleteSeries(String seriesId) {
        SeriesRecord series = findSeries(seriesId);
        if (series == null) {
            return false;
        }
        return seriesList.remove(series);
    }

    /**
     * Prints a report generated from the in-memory collection.
     */
    public void SeriesReport() {
        System.out.println("\n========================================");
        System.out.println("          SERIES REPORT - 2025");
        System.out.println("========================================");

        if (seriesList.isEmpty()) {
            System.out.println("No series data is currently stored.");
            return;
        }

        int count = 1;
        for (SeriesRecord series : seriesList) {
            System.out.println("\nSeries " + count);
            System.out.println("----------------------------------------");
            System.out.println("SERIES ID: " + series.getSeriesId());
            System.out.println("SERIES NAME: " + series.getSeriesName());
            System.out.println("SERIES AGE RESTRICTION: " + series.getAgeRestriction());
            System.out.println("NUMBER OF EPISODES: " + series.getNumberOfEpisodes());
            count++;
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Menu option 6.
     */
    public void ExitSeriesApplication() {
        System.out.println("\nThank you for using the Latest Series Management Application.");
        System.out.println("Application closed.");
    }

    /**
     * Required validation rule: age must be a number from 2 to 18.
     */
    public boolean isValidAgeRestriction(int ageRestriction) {
        return ageRestriction >= 2 && ageRestriction <= 18;
    }

    public int getSeriesCount() {
        return seriesList.size();
    }

    public ArrayList<SeriesRecord> getSeriesList() {
        return seriesList;
    }

    private SeriesRecord findSeries(String seriesId) {
        for (SeriesRecord series : seriesList) {
            if (series.getSeriesId().equalsIgnoreCase(seriesId)) {
                return series;
            }
        }
        return null;
    }

    private int readAgeRestriction() {
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String input = scanner.nextLine().trim();

            try {
                int age = Integer.parseInt(input);
                if (isValidAgeRestriction(age)) {
                    return age;
                }
            } catch (NumberFormatException e) {
                // Invalid non-number input is handled below.
            }

            System.out.println("You have entered an incorrect series age!!!");
            System.out.println("Please re-enter the series age >>");
        }
    }

    private int readPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Prompt again.
            }
            System.out.println("Please enter a valid positive whole number.");
        }
    }

    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    /**
     * Data class used by the Series manager.
     * Fields are private to demonstrate information hiding.
     */
    public static class SeriesRecord {
        private String seriesId;
        private String seriesName;
        private int ageRestriction;
        private int numberOfEpisodes;

        public SeriesRecord(String seriesId, String seriesName,
                            int ageRestriction, int numberOfEpisodes) {
            this.seriesId = seriesId;
            this.seriesName = seriesName;
            this.ageRestriction = ageRestriction;
            this.numberOfEpisodes = numberOfEpisodes;
        }

        public String getSeriesId() {
            return seriesId;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public int getAgeRestriction() {
            return ageRestriction;
        }

        public int getNumberOfEpisodes() {
            return numberOfEpisodes;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
        }

        public void setAgeRestriction(int ageRestriction) {
            this.ageRestriction = ageRestriction;
        }

        public void setNumberOfEpisodes(int numberOfEpisodes) {
            this.numberOfEpisodes = numberOfEpisodes;
        }

        @Override
        public String toString() {
            return "SERIES ID: " + seriesId + "\n"
                    + "SERIES NAME: " + seriesName + "\n"
                    + "SERIES AGE RESTRICTION: " + ageRestriction + "\n"
                    + "NUMBER OF EPISODES: " + numberOfEpisodes;
        }
    }
}
