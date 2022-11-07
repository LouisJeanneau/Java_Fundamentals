import java.io.FileWriter;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Exercise 5 of the Java Fundamentals class
 *
 * @author Louis
 * @version 1.1
 * @since 27/10/2022
 */
public class Exercise5 {
    /**
     * Main function
     *
     * @param args nothing expected
     */
    public static void main(String[] args) {
        Exercise5 instance = new Exercise5();
        System.out.println(instance.convertDate());
    }

    /**
     * @param s non-parsed and brut string that may or may not contain a date in one of the valid formats
     * @return a correctly created MyData object
     * @throws IllegalArgumentException with input in invalid format
     */
    protected MyData myDataFactory(String s) {
        MyData date;
        String[] splitString = s.split("(, )|[. _/]|-");

        if (MyData.patternFirst.matcher(s).find() && Arrays.stream(MyData.existingWeekdays).anyMatch(splitString[3]::equalsIgnoreCase)) {
            date = new MyData(splitString[0], splitString[1], splitString[2], splitString[3]);
        } else if (MyData.patternSecond.matcher(s).find() && Arrays.stream(MyData.existingWeekdays).anyMatch(splitString[3]::equalsIgnoreCase)) {
            date = new MyData(splitString[2], splitString[1], splitString[0], splitString[3]);
        } else if (MyData.patternThird.matcher(s).find() && Arrays.stream(MyData.existingWeekdays).anyMatch(splitString[0]::equalsIgnoreCase)) {
            date = new MyData(splitString[1], splitString[2], splitString[3], splitString[0]);
        } else {
            throw new IllegalArgumentException("Not in the valid format");
        }

        return date;
    }

    /**
     * Convert the dates from "InputData.txt" file to formatted date in "MyData.txt"
     *
     * @return The number of unique valid dates converted
     */
    public int convertDate() {
        // Array that will be populated by converted valid dates
        List<MyData> myDataList = new ArrayList<>();
        try {
            // Scanner for the input file
            Scanner reader = new Scanner(Path.of("InputData.txt").toAbsolutePath());

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                try {
                    // We try to create a MyData object, and we add it to the ArrayList
                    MyData date = myDataFactory(data);
                    myDataList.add(date.clone());
                    //System.out.println(data + " " + date);
                } catch (Exception e) {
                    // The format of the input line wasn't valid, nothing happens here
                }

            }
            // We close the reader
            reader.close();
        } catch (Exception e) {
            // Should normally not happen
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // We sort the list
        Collections.sort(myDataList);
        // We get rid of duplicates
        List<MyData> myDataListNoDuplicates = myDataList.stream().distinct().toList();

        // We write the output file
        try {
            FileWriter writer = new FileWriter("MyData.txt");
            for (MyData date : myDataListNoDuplicates) {
                writer.write(date.toString() + '\n');
            }
            writer.close();
        } catch (Exception e) {
            // Should normally not happen
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // We return the number of valid unique date
        return myDataListNoDuplicates.size();
    }

    /**
     * Class that contains a single date
     */
    protected class MyData implements Comparable<MyData> {
        // Pattern (using RegEx) used to verify that the input is in the correct format
        static protected Pattern patternFirst = Pattern.compile("\\d{2}/\\d{1,2}/\\d{4} \\w+");
        static protected Pattern patternSecond = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\w+");
        static protected Pattern patternThird = Pattern.compile("\\w+ \\d{2}\\.\\d{2}\\.\\d{4}");
        static String[] existingWeekdays = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };
        // (fields day - integer, month - integer, year - integer and day of the week - string)
        Integer day;
        Integer month;
        Integer year;
        String weekday;

        /**
         * Create a MyData object with correct input
         *
         * @param day day
         * @param month month
         * @param year year
         * @param weekday weekday
         */
        MyData(String day, String month, String year, String weekday) {
            this(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year), weekday);
        }

        MyData(Integer day, Integer month, Integer year, String weekday) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.weekday = weekday;
        }


        @Override
        public String toString() {
            return "day= " + day +
                    ", month= " + month +
                    ", year= " + year +
                    ", weekday= '" + weekday + '\'';
        }

        @Override
        protected MyData clone(){
            return new MyData(day, month, year, weekday);
        }

        /**
         * Implemented to compare dates for duplicate removal
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyData myData = (MyData) o;
            return day.equals(myData.day) && month.equals(myData.month) && year.equals(myData.year) && weekday.equals(myData.weekday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(day, month, year, weekday);
        }

        /**
         * Implemented to allow ordering of the dates list
         *
         * @param o the object to be compared.
         * @return
         */
        @Override
        public int compareTo(MyData o) {
            if (this.year > o.year) {
                // if current object is greater,then return 1
                return 1;
            } else if (this.year.equals(o.year) && this.month > o.month) {
                return 1;
            } else if (this.year.equals(o.year) && this.month.equals(o.month) && this.day > o.day) {
                return 1;
            } else if (this.year.equals(o.year) && this.month.equals(o.month) && this.day.equals(o.day)) {
                return 0;
            } else {
                return -1;
            }

        }
    }
}
