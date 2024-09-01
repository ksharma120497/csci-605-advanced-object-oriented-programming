package HW4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application checks if the words given in the text files are matching any pattern classes

    This application uses Pattern classes to execute regex operations and to return true or false if it
    matches any patterns

 */

public class GrepRegEx {

    static String theWords="";

    /*
       This function reads file from a given location and reads the text line by line
       It takes out one word at a time and perform regex operations on it

       @params fileName: takes file name in string from where we need to read the data
       @params delimiter: takes a parameter for the command line that acts as a delimeter
    */
    public static void readWordsFromFile(String fileName, String delimiter) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));
        ) {
            while  ((theWords = input.readLine()) != null ){
                System.out.println(isPatternMatching(theWords, delimiter));
            }

        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }

    /*

       This function is the one that performs pattern matching using Pattern classes
       It takes a regex of all the possible pattern classes given in the table and
       runs the word across all the patterns finds that if there is any match found

       We are using Pattern.matches method of Pattern classes which takes two parameters
       One is regex and the other is the word itself

     */

    public static boolean isPatternMatching(String inputString, String delimeter){
        String[] delimitedString =  inputString.split(delimeter);
        String allVowelsInOrder = "^a[^aeiou\\s+]*e[^aeiou\\s+]*i[^aeiou\\s+]*" +
                "o[^aeiou\\s+]*u[^aeiou\\s+]*";

        String dateRegex = "^(?:(0[1-3]|05|06|07|08|09|10|11|12)\\/(0[1-9]|1\\d|2[0-9]|3[0-1])\\/\\d{2}|(0[1-9]|1\\d|2[0-9]|3[0-1])\\/(0[1-3]|05|06|07|08|09|10|11|12)\\/\\d{2})$\n";

        String smallPalindrome = "^(.)(?:(.)(.)?\\\\2)\\\\1$";

        String largePalindrome = "";

        String textRegexSingleDigits = "\\[([0-9])+-+[0-9]\\]\\|\\(\\1[0-9]\\)";

        String concatDelimitedString = "";

        for(String eachWord: delimitedString){
            concatDelimitedString = concatDelimitedString + eachWord;
        }

        return  Pattern.matches(allVowelsInOrder,concatDelimitedString) || Pattern.matches(dateRegex,concatDelimitedString)
                || Pattern.matches(smallPalindrome,concatDelimitedString) || Pattern.matches(textRegexSingleDigits,concatDelimitedString) ||
                Pattern.matches(largePalindrome,concatDelimitedString);
    }

    public static void main(String args[]){
        readWordsFromFile("/Users/kapilsharma/School Work/AOOP HW/src/HW4/pattern_matching_words", "x");
    }

}
