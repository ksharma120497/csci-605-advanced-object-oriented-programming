package HW3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831\

    This application performs following function in steps
    1. all characters have to be converted to lowercase characters
    2. then all-white space has to be removed
    3. then the characters have to be sorted.
 */

public class SameNumberOfCharOnLine {

    static int numberOfWords=0;

    static final String[] theWords = new String[10231];
    /*
        This function reads file from a given location and reads the text line by line
        It stores the text in the array from where we can read the texts and
        consider it as our answerString

        @params fileName: takes file name in string from where we need to read the data
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));

        ) {
            int counter = 0;
            while  ( ( theWords[counter++] = input.readLine())  != null ){
                numberOfWords++;
            }
        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }


    /*
        This function prints the output string by taking two arrays: Sorted array and Unsorted array
        It compares the two arrays in a way that the elements in the unsorted array looks for the same
        string in the sorted array and it saves the index

        After saving the index it prints the original array with the same order

        @params sortedArray: it has the sorted array
        @params unsortedArray: it has the unsorted array

     */
    public static void printTheSortedArrayFromTheWords(String[] sortedArray, String[] unsortedArray){
        int[] sortingIndex = new int[sortedArray.length];
        for(int i=0; i< unsortedArray.length; i++){
            for(int j=0; j< unsortedArray.length;j++){
                if(sortedArray[i]==unsortedArray[j])
                    sortingIndex[i]=j;
            }
        }

        for(int i=0; i< sortingIndex.length; i++){
            System.out.println(theWords[sortingIndex[i]]);
        }
    }
    /*
          This function sorts the array when it has been
          sorted in characters

           @params unsortedArray: it takes unsorted array as inputs with characters are sorted

           @returns a string array with all the elements sorted
     */

    public static String[] sortArray(String[] unsortedArray){
        String temp;
        for(int i=0; i<unsortedArray.length;i++){
            for(int j=i+1;j<unsortedArray.length; j++){
                if(unsortedArray[j].compareTo(unsortedArray[i])<0){
                    temp = unsortedArray[j];
                    unsortedArray[j]=unsortedArray[i];
                    unsortedArray[i] = temp;
                }
            }
        }
        return unsortedArray;
    }

    /*
        This function sorts the characters in each words after removing spaces in them

        @params stringsToBeSorted: words with no spaces in them that are yet to be sorted

        @returns a string with characters sorted
     */

    public static String sortCharactersInWords(String stringsToBeSorted){
        char[] convertedCharArray = stringsToBeSorted.toCharArray();
        Arrays.sort(convertedCharArray);
        stringsToBeSorted = new String(convertedCharArray);
        return stringsToBeSorted;
    }

    /*
        This function formats the string in a way that it removes the extra spaces in them
        and it also converts all strings into lower cases

        @params arrayWithoutFormat: takes the input string array that needs to have its words sorted

        @returns a string array with words in its characters sorted
     */
    public static String[] formatStringsInArray(String[] arrayWithoutFormat){
        for(int i=0; i<arrayWithoutFormat.length; i++){
            arrayWithoutFormat[i] = sortCharactersInWords(arrayWithoutFormat[i].replaceAll(" ","").toLowerCase());
        }
        return arrayWithoutFormat;
    }

    public static void main(String args[]){
        readWordsFromFile("/Users/kapilsharma/School Work/AOOP HW/src/HW3/unordered_list"); //reads from input files
        String[] initializeArraysToBeSorted = new String[numberOfWords];
        String[] unsortedArray = new String[numberOfWords];
        for(int i=0; i< numberOfWords; i++)
            initializeArraysToBeSorted[i] = theWords[i];
        String[] formattedArray = formatStringsInArray(initializeArraysToBeSorted);
        for(int i=0; i< numberOfWords; i++)
            unsortedArray[i] = formattedArray[i];

        String[] sortedArray = sortArray(formattedArray);
        printTheSortedArrayFromTheWords(sortedArray, unsortedArray); // prints the output list of words
    }
}
