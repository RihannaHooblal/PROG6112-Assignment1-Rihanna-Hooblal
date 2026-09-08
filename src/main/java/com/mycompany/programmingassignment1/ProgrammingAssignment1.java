package com.mycompany.programmingassignment1;

import java.util.Scanner;

/**
 * Main class for the TV Series Management Application.
 */
public class ProgrammingAssignment1 {

    public static void main(String[] args) {
        Series seriesManager = new Series();
        Scanner scanner = new Scanner(System.in);

        System.out.println("LATEST SERIES - 2025");
        System.out.println("******************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");

        String launch = scanner.nextLine().trim();

        if (!launch.equals("1")) {
            seriesManager.ExitSeriesApplication();
            return;
        }

        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> seriesManager.CaptureSeries();
                case "2" -> seriesManager.SearchSeries();
                case "3" -> seriesManager.UpdateSeries();
                case "4" -> seriesManager.DeleteSeries();
                case "5" -> seriesManager.SeriesReport();
                case "6" -> {
                    seriesManager.ExitSeriesApplication();
                    running = false;
                }
                default -> System.out.println("Invalid menu option. Please select 1 to 6.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select one of the following menu items.");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction.");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.print("Enter your choice: ");
    }
}
