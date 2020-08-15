package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.*;
import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args) throws IOException {
//        Stopwatch stopwatch=new Stopwatch();
//        int n=160000;
//        int a = 0;
//        for(int i=0;i<n;i++){
//            for(int j=i+1;j<n;j++){
//                for(int k=j+1;k<n;k++){
//                    a++;
//                }
//            }
//        }
//        System.out.println(stopwatch.elapsedTime());
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("week1/assignment/data.txt")));
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }
    }

    public static class testXEAN {
        public static void main(String[] args) {
            int test=3;
            double time[]=new double[test];
            for(int i=0;i<test;i++){
                int[] ara=new int[16000];
                for(int j=0;j<ara.length;j++){
                    ara[j]= StdRandom.uniform(-100, 100);
                }
                Arrays.sort(ara);
                Stopwatch stopwatch=new Stopwatch();
                System.out.println(threeSum(ara));
                time[i]=stopwatch.elapsedTime();
            }
            System.out.println("Average time: "+ StdStats.mean(time));
        }

        private static int threeSum(int[] ara) {
            int count=0;
            for(int p=0;p<ara.length;p++){
                for(int q=p+1;q<ara.length;q++){
                    if(Arrays.binarySearch(ara, -(ara[p]+ara[q]))>=0)
                        count++;
                }
            }
            return count;
        }

    }
}
