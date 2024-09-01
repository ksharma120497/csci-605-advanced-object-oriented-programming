

import java.io.*;


/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application is does scrambling and descrambling of the words read from the file
    and it stores the data in another file

 */

public class MyScramble {

    public static void main(String args[]){
        int words;
        String options = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        try(
            FileInputStream fileInputStream = new FileInputStream("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+ inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/kapilsharma/School Work/AOOP HW/src/HW9/" + outputFile);
        ){
        if(options.equalsIgnoreCase("-scramble")){
            char[] word = new char[100];
            int count = 0;
            while((words = fileInputStream.read()) != -1) {
                    word[count] = (char) words;
                    count++;
            }

            for(int i=word.length-1; i>=0; i--){
                if((int)word[i] != 0)
                    fileOutputStream.write(word[i]);
            }

        }
        else if(options.equalsIgnoreCase("-descramble")){
            char[] word = new char[100];
            int count = 0;
            while((words = fileInputStream.read()) != -1) {
                word[count] = (char) words;
                count++;
            }

            for(int i=0; i<word.length; i++){
                if((int)word[i] != 0)
                    fileOutputStream.write(word[i]);
            }
        }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
