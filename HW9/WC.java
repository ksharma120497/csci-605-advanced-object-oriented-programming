
/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application is use to mimic the wc command in the linux OS

    It shows three values in it number of lines, word count and byte count

 */
import java.io.*;

public class WC {
    static String[] words = new String[100];

    public static void main(String args[]) throws FileNotFoundException {
        String options = args.length > 1 ? args[0] : "file";
        String fileName = args.length > 1 ?  args[1] : args[0];
        try(
            FileReader file = new FileReader("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+fileName);
            BufferedReader br = new BufferedReader(file); ){
            File f = new File("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+fileName);
            int count = 0;
            int totalWords = 0;
            int characterCount = 0;
            while(( words[count] = br.readLine() ) != null){
                totalWords+=words[count].split(",").length;
                characterCount += words[count].toCharArray().length;
                count++;
            }
            long fileLength = f.length();
            int wordCount = count ;
            if(options.equalsIgnoreCase("-m") )
                System.out.println(totalWords + " " + characterCount + " " + fileName);
            else if (options.equalsIgnoreCase("-c"))
                System.out.println(totalWords+ " " + fileLength+ " "+ fileName);
            else if(options.equalsIgnoreCase("-l"))
                System.out.println(wordCount+ " " + fileLength+ " "+ fileName);
            else
                System.out.println(totalWords+ " " + wordCount+ " "+ fileLength+ " "+ fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
