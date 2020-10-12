package codeforces.grakn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class C {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int t = fs.nextInt();
        for (int test = 0; test < t; test++) {
            final int n = fs.nextInt();
            final int total = fs.nextInt();
            final int[] arr = fs.nextIntArray(n);
            int i = 0;
            int j = n - 1;
            double lSpeed = 1;
            double rSpeed = 1;
            double lPos = 0;
            double rPos = total;
            double res = 0;
            while (i <= j) {
                final double leftD = (arr[i] - lPos) / lSpeed;
                final double rightD = (rPos - arr[j]) / rSpeed;
                if (Double.compare(leftD, rightD) < 0) {
                    res += leftD;
                    lPos = arr[i];
                    rPos -= rSpeed * leftD;
                    lSpeed++;
                    i++;
                } else if (Double.compare(leftD, rightD) > 0) {
                    res += rightD;
                    rPos = arr[j];
                    lPos += lSpeed * rightD;
                    rSpeed++;
                    j--;
                } else {
                    res += rightD;
                    rPos = arr[j];
                    lPos = arr[i];
                    i++;
                    j--;
                    lSpeed++;
                    rSpeed++;
                }
            }
            if (rPos - lPos > 0) {
                res += (rPos - lPos) / (lSpeed + rSpeed);
            }
            System.out.println(res);
        }
    }

    static final class Utils {
        public static void shuffleSort(int[] x) {
            shuffle(x);
            Arrays.sort(x);
        }

        public static void shuffleSort(long[] x) {
            shuffle(x);
            Arrays.sort(x);
        }

        public static void shuffle(int[] x) {
            final Random r = new Random();

            for (int i = 0; i <= x.length - 2; i++) {
                final int j = i + r.nextInt(x.length - i);
                swap(x, i, j);
            }
        }

        public static void shuffle(long[] x) {
            final Random r = new Random();

            for (int i = 0; i <= x.length - 2; i++) {
                final int j = i + r.nextInt(x.length - i);
                swap(x, i, j);
            }
        }

        public static void swap(int[] x, int i, int j) {
            final int t = x[i];
            x[i] = x[j];
            x[j] = t;
        }

        public static void swap(long[] x, int i, int j) {
            final long t = x[i];
            x[i] = x[j];
            x[j] = t;
        }

        private Utils() {}
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        private String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int[] nextIntArray(int n) {
            final int[] a = new int[n];
            for (int i = 0; i < n; i++) { a[i] = nextInt(); }
            return a;
        }

        long[] nextLongArray(int n) {
            final long[] a = new long[n];
            for (int i = 0; i < n; i++) { a[i] = nextLong(); }
            return a;
        }
    }
}
