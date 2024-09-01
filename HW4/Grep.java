package HW4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    Given a word we have to find to which pattern classes the particular word belongs

    The application uses Pattern class to match the regex

 */

public class Grep {

    static String theWords;


    // These are list of possible regex classes in the given tables
    static String[] regularExpressions = {"^ab$",".a+b.",".ab.","^[ab]c$","^[ab]?c$", "^[ab]?|c?$", "^(.)(.)\\2\\1$"};

    /*
       This function is used to read words from the input file given in the command line

       It reads the line one by one and then calls the pattern matching function which finds to which
       class it belongs and returns that string and is printed
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));
        ) {
            while  ((theWords = input.readLine()) != null ){
                System.out.println(isPatternMatching(theWords));
            }

        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }


    /*
        This function is the one where we find the pattern matching of the words passed
        to this function as an argument

        This makes use of Pattern classes and loops through arrays of patterns and checks to which
        pattern it belongs and returns that regex string

        @params theWords: word that needs to be checked for pattern matching
     */
    public static String isPatternMatching(String theWords){
        String concatenateString = "";
        for(int i=0; i<regularExpressions.length; i++){
            if(Pattern.matches(regularExpressions[i],theWords))
                concatenateString=concatenateString + "Line: "+ regularExpressions[i]+ " " + theWords+"\n";
        }
        return concatenateString;
    }

    public static void main(String args[]){
        readWordsFromFile("/Users/kapilsharma/School Work/AOOP HW/src/HW4/words_with_patterns");
    }

}

