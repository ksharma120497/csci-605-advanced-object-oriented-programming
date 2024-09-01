package HW3;

import java.util.Arrays;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application is used to test our knowledge on strings

    It has certain variables that needs to show the output of the comparison between them

    It also has one function that replaces "L" with 'l' and then sorts them

 */

class StringIntegerArrays {
    static boolean firstTime = true;

    static String resturnsAstring(String arg)	{
        String rValue;

        if ( firstTime )
            rValue = "";
        else
            rValue = arg;
        firstTime = false;
        return rValue;
    }

    /*
        This function replaces 'L' with 'l' and then sorts the word according to the character
        It makes use of Arrays.sort function

        @params stringToBeSorted: it takes the string as input

        @returns a string that has its character sorted in order
     */

    public static String sortAndReplace(String stringToBeSorted){
        stringToBeSorted = stringToBeSorted.replace(" ","").replace("L", "l");
        char[] convertedCharArray = stringToBeSorted.toCharArray();
        Arrays.sort(convertedCharArray);
        stringToBeSorted = new String(convertedCharArray);
        return stringToBeSorted;
    }
    public static void main( String args[] ) {
        String a, b;
        String aString= null;
        String bString= null;
        String cString= null;
        String dString= null;
        String eString= null;
        String fString= null;
        String gString= null;
        String hString= null;
        String iString= null;

        if ( args.length == 1 ) {
            aString = "Ab" + "ba";
            bString = "Abba";
            cString = "A";
            dString = cString + "b" + "b" + aString.substring(aString.length() - 1);
            fString = "Pink FLoyd";
            gString = "Abba" + resturnsAstring("");
            hString = "Ab" + resturnsAstring("ba");
            System.out.println(aString == bString);
            // it will print true because we are comparing two same literals so it will store in  the same address
            System.out.println(aString.equals(bString));
            // it will print true because we are using equals to compare just two literals
            System.out.println(cString == dString);
            // it will print false because the literal itself is different and also the memory address
            System.out.println(cString.equals(dString));
            // it will print false because we are using equals to compare literals they are different
            System.out.println(gString == hString);
            // it will print false because it has a different literal because the static functions updates the variable
            // and the same variable is used for hstring
            System.out.println(gString.equals(hString));
            // it will print false because it has a different literal
            System.out.println(sortAndReplace(fString)); // this replaces letters and sorts the characters in the words
        } else {
            aString = "Ot" + "to";
            bString = "Otto";
            cString = "O";
            dString = cString + "t" + "t" + aString.substring(aString.length() - 1);
            fString = "Led ZeppeLin";
            gString = "Otto" + resturnsAstring("");
            hString = "Ot" + resturnsAstring("to");
            System.out.println(aString == bString);
            System.out.println(aString.equals(bString));
            System.out.println(cString == dString);
            System.out.println(cString.equals(dString));
            System.out.println(gString == hString);
            System.out.println(gString == hString);
            System.out.println(sortAndReplace(fString));
        }

    }
}