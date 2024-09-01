
import java.io.*;
import java.util.Scanner;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application is use to mimic the dd command in the linux OS

    It copies data from one file and stores it in another one with offset and length of the byte

 */

public class DiskDump {

    String outputFile = "";

    String inputFile = "";

    int skip;

    int bs;

    public static byte[] readFile(String file) throws IOException {
        File f = new File(file);
        byte[] buffer = new byte[(int) f.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(buffer);
        return buffer;
    }


    public static void main(String args[]){

        byte[] byteData;
        Scanner scanner = new Scanner(System.in);
        String inputFile = args[0].length()>7?args[0].substring(3,9): scanner.nextLine();
        String outputFile = args[0].length()>7?args[0].substring(3,9): scanner.nextLine();
        int skip = Integer.parseInt(args[2].substring(5,6));
        int bs = Integer.parseInt(args[3].substring(3,4));

        try(
            FileInputStream fileInputStream = new FileInputStream("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+ inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+outputFile);
            ) {
            byteData = readFile("/Users/kapilsharma/School Work/AOOP HW/src/HW9/"+inputFile);
            fileInputStream.skip(skip);
            long timeInSecondsBefore = System.currentTimeMillis()/1000;

            for (int i=skip; i< byteData.length - bs; i++){
                fileOutputStream.write((char)byteData[i]);
            }
            long timeInSecondsAfter = System.currentTimeMillis()/1000;
            System.out.println(byteData.length+" bytes transferred in " + (timeInSecondsAfter - timeInSecondsBefore)+ " secs  " + "(  )");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
