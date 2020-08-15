package extra;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {
    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if(a[i]+a[j]+a[k]==0) count++;
                }
            }
        }
        return count;
    }

    public static int efficientCount(int[] a){
        Arrays.sort(a);
        int n=a.length, count=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(Arrays.binarySearch(a, -(a[i]+a[j]))!=-1) count++;
            }
        }
        return count;
    }

    public static int[] generateArr(int n){
        int[] ara=new int[n];
        for(int i=0;i<n;i++){
            ara[i]= StdRandom.uniform(-100,101);
        }
        return ara;
    }
    public static void main(String[] args) {
        int[] testSize= new int[]{250,500,1000,2000,4000,8000, 16000, 32000, 64000};
        int repeatSize=5;
        double[] time=new double[repeatSize];
        for (int value : testSize) {
            for(int i=0;i<repeatSize;i++) {
                Stopwatch stopwatch = new Stopwatch();
                int count = efficientCount(generateArr(value));
                time[i] = stopwatch.elapsedTime();
                System.out.println("Input: " + value + "-----" + "Time: " +time[i]);
            }
            System.out.println("MeanTime: " + StdStats.mean(time));
        }
    }
}
