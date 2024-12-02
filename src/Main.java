// Advent of Code 2024 Day 2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/input.txt");
            Scanner scanner = new Scanner(inputFile);

            // Each row is a "report".
            // A reports is safe if it is increasing or decreasing
            // A reports must only differ by at least 1 and at most 3
            // The "Problem Dampener" allows for one bad level

            int safeLevels = 0;

            ArrayList<ArrayList<Integer>> reports = new ArrayList<ArrayList<Integer>>();

            // Convert input to data structure
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineValuesStr = line.split(" ");

                ArrayList<Integer> lineValues = new ArrayList<>();
                for (String value : lineValuesStr) {
                    lineValues.add(Integer.valueOf(value));
                }
                reports.add(lineValues);
            }
            System.out.println("Input converted");

            // Check each row
            for (ArrayList<Integer> report : reports) {
                if (isSafe(report) || canBeMadeSafe(report)) {
                    safeLevels++;
                }
            }

            System.out.println(safeLevels);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // Check if a report is safe without removing any levels
    private static boolean isSafe (ArrayList<Integer> report) {
        boolean isIncreasing = report.get(1) > report.get(0);
        for (int i = 1; i < report.size(); i++) {
            int difference = Math.abs(report.get(i) - report.get(i - 1));
            if (difference < 1 || difference > 3
                || (isIncreasing && report.get(i) < report.get(i - 1))
                || (!isIncreasing && report.get(i) > report.get(i - 1))) {
                return false;
            }
        }
        return true;
    }


    // Check if a report can be made safe by removing each level until it is safe
    private static boolean canBeMadeSafe(ArrayList<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }
        return false;
    }

}
