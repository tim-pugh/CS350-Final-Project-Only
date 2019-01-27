import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void populateArrayIncreasingByOne(int arrayLength, int intArray[]) {

        for (int i = 0; i < arrayLength; ++i)
            intArray[i] = i;
    }

    public static void populateArrayIncreasingBySeven(int arrayLength, int intArray[]) {

        for (int i = 0; i < arrayLength; ++i)
            intArray[i] = i + 7;
    }

    public static void populateArrayIncreasingRandomly(int arrayLength, int intArray[]) {

        Random randomNumberGenerator = new Random();

        for (int i = 0; i < arrayLength; ++i)
            intArray[i] = randomNumberGenerator.nextInt(arrayLength) + 1;

        Arrays.sort(intArray);
    }

    public static int generateRandomNumber(int arrayLength) {
        Random randomNumberGenerator = new Random();
        return randomNumberGenerator.nextInt(arrayLength) + 1;
    }

    public static int sequentialSearch(int searchedForValue, int arrayLength, int intArray[]) {
        int i = 0;
        while (i != arrayLength - 1) {
            if (intArray[i] == searchedForValue)
                return i;
            else
                ++i;
        }
        return -1;
    }

    public static int binarySearch(int searchedForValue, int arrayLength, int intArray[]) {

        int l = 0; //lowest position in current array partition
        int r = arrayLength - 1; //highest position in current array partition
        int m; //middle array index of current search partition

        while (l <= r) {
            m = (l + r) / 2;

            if (searchedForValue == intArray[m])
                return m;

            else if (searchedForValue < intArray[m])
                r = m - 1;

            else l = m + 1;
        }

        return -1;
    }

    public static int interpolationSearch(int searchedForValue, int arrayLength, int intArray[]) {

        int l = 0;//lowest position in current array partition (left side)
        int r = arrayLength - 1;//highest position in current array partition (right side)
        int m;//middle array index of current search partition (middle point

        while (l <= r && searchedForValue >= intArray[l] && searchedForValue <= intArray[r]) {
            m = l + ((searchedForValue - intArray[l]) * ((r - l) / (intArray[r] - intArray[l])));

            if (searchedForValue == intArray[m])
                return m;

            else if (searchedForValue > intArray[m])
                l = m + 1;

            else r = m - 1;
        }

        return -1;
    }

    public static void writeToFile(long stopwatchStart, long stopwatchEnd, String searchType) {

        try {
            PrintWriter writeToFile = new PrintWriter(new FileOutputStream(new File(searchType + "stats.txt"), true));

            writeToFile.println(((stopwatchEnd - stopwatchStart)));

            writeToFile.close();
        } catch (IOException e) {
            System.out.println("\nPlease make sure that the file stats.txt exists in the directory of the program!\n");
        }
    }

    public static void main(String[] args) {

        String FileName;

        int indexOfSearchedForValue;
        int arrayLength = 1000000;              //Creating an  int array of length arrayLength
        int[] intArray = new int[arrayLength];
        long stopwatchStart, stopwatchEnd;  //Creating values to time our algorithms. I declare them here to avoid declarations effecting timings later.

        populateArrayIncreasingByOne(arrayLength, intArray); //Populating array with int's from 0 to arrayLength-1 in sorted order
        int searchedForValue = generateRandomNumber(arrayLength); //Generating a random number between 0 to arrayLength-1
        System.out.println(
                "We have a sorted array from 1 to 1,000,000.\n" +
                        "We also have a randomly generated number which is: " + searchedForValue + "\t" +
                        "We will now commence our search algorithms\n");

       /* *****************************************************Binary Search - Array Increasing By One - 1000 Loops - Same Value Each Loop Iteration*******************************************/

        FileName = "Binary_Search_Array_Increasing_By_One_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {
            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search, the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array\n");
        }
       /* *****************************************************Sequential Search - Array Increasing By One - 1000 Loops - Same Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_By_One_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {
            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }
        /* *****************************************************Interpolation Search - Array Increasing By One - 1000 Loops - Same Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_By_One_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {
            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }
         /* *******************Binary Search -Array Increasing By One - 1000 Loops - New Random Searched Value Each Loop Iteration***********************/

        FileName = "Binary_Search_Array_Increasing_By_One_New_Random_Search_Value_Each_Loop";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = generateRandomNumber(arrayLength);

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search, the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array\n");
        }
         /* ******************Sequential Search - Array Increasing By One - 1000 Loops - New Random Searched Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_By_One_New_Random_Search_Value_Each_Loop";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = generateRandomNumber(arrayLength);

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }

        /* ***************************Interpolation Search - Array Increasing By One - 1000 Loops - New Random Searched Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_By_One_New_Random_Search_Value_Each_Loop";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = generateRandomNumber(arrayLength);

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }
         /* *****************************************************Binary Search - Array Increasing By Seven - 1000 Loops - Same Value Each Loop Iteration*************************************/

        populateArrayIncreasingBySeven(arrayLength, intArray);
        searchedForValue = intArray[generateRandomNumber(arrayLength)];

        FileName = "Binary_Search_Array_Increasing_By_Seven_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Sequential Search - Array Increasing By Seven - 1000 Loops - Same Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_By_Seven_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }

         /* *****************************************************Interpolation Search - Array Increasing By Seven - 1000 Loops - Same Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_By_Seven_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }

         /* *****************************************************Binary Search - Array Increasing By Seven - 1000 Loops - Random Value Each Loop Iteration*************************************/

        FileName = "Binary_Search_Array_Increasing_By_Seven_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Sequential Search - Array Increasing By Seven - 1000 Loops - Random Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_By_Seven_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }

         /* *****************************************************Interpolation Search - Array Increasing By Seven - 1000 Loops - Random Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_By_Seven_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Binary Search - Array Increasing Randomly - 1000 Loops - Same Value Each Loop Iteration*************************************/

        populateArrayIncreasingRandomly(arrayLength, intArray);
        searchedForValue = intArray[generateRandomNumber(arrayLength)];

        FileName = "Binary_Search_Array_Increasing_Randomly_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Sequential Search - Array Increasing Randomly - 1000 Loops - Same Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_Randomly_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Interpolation Search - Array Increasing Randomly - 1000 Loops - Same Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_Randomly_Same_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }

          /* *****************************************************Binary Search - Array Increasing Randomly - 1000 Loops - Random Value Each Loop Iteration*************************************/

        FileName = "Binary_Search_Array_Increasing_Randomly_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = binarySearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for binary search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via binary search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via binary search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Sequential Search - Array Increasing Randomly - 1000 Loops - Random Value Each Loop Iteration**********************************/

        FileName = "Sequential_Search_Array_Increasing_Randomly_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = sequentialSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for sequential search is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via sequential search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via sequential search, the value " + searchedForValue + " is not found in the array");
        }

        /* *****************************************************Interpolation Search - Array Increasing Randomly - 1000 Loops - Random Value Each Loop Iteration*************************************/

        FileName = "Interpolation_Search_Array_Increasing_Randomly_Random_Search_Value_Each_Loop_Iteration";
        for (int i = 0; i < 1000; ++i) {

            searchedForValue = intArray[generateRandomNumber(arrayLength)];

            System.out.println("The random value is\t" + searchedForValue + "\n\n");

            stopwatchStart = System.nanoTime(); //Beginning timing

            indexOfSearchedForValue = interpolationSearch(searchedForValue, arrayLength, intArray);

            stopwatchEnd = System.nanoTime(); //End timing

            writeToFile(stopwatchStart, stopwatchEnd, FileName);

            System.out.println("Our search time for interpolation is " + (stopwatchEnd - stopwatchStart) + " in nanoseconds \n");

            if (indexOfSearchedForValue != -1)
                System.out.println("Via interpolation search the value, " + searchedForValue + " was found in index " + indexOfSearchedForValue + "\n");

            else
                System.out.println("Via interpolation search, the value " + searchedForValue + " is not found in the array");
        }
    }
}

