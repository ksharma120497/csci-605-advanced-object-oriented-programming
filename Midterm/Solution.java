package Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.

public class Solution {

    public static List<Integer> getMaxArray(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int maxMex=0;
        for(int i=0; i<arr.length; i++){
            int mex = getMex(result.subList(0,i+1));
            if(maxMex>mex){
                maxMex = i;
            }
        }

        return result;
    }

    public static int getMex(List<Integer> arr){
        return arr.get(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        List<Integer> result = getMaxArray(arr);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}