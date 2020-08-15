package extra;

public class binSearch {
    public static int binarySearch(int[] a, int key) {
        int f = 0, l = a.length - 1;
        while (f <= l) {
            int mid = f + (l - f) / 2;
            if (a[mid] == key) return mid;
            else if (key < a[mid]) l = mid - 1;
            else f = mid + 1;

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ara =new int[]{1,2,3,4,5,6,9};
        System.out.println(binarySearch(ara, 5));
    }
}
