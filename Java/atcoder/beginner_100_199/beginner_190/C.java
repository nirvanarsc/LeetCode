package atcoder.beginner_100_199.beginner_190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class C {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int m = fs.nextInt();
        final int[][] p = new int[m][2];
        for (int i = 0; i < m; i++) {
            p[i] = new int[] { fs.nextInt(), fs.nextInt() };
        }
        final int k = fs.nextInt();
        final int[][] kk = new int[k][2];
        for (int i = 0; i < k; i++) {
            kk[i] = new int[] { fs.nextInt(), fs.nextInt() };
        }
        int res = 0;
        for (int mask = 0; mask < 1 << k; mask++) {
            final int[] curr = new int[n + 5];
            for (int i = 0; i < k; i++) {
                if ((mask & (1 << i)) != 0) {
                    curr[kk[i][1]]++;
                } else {
                    curr[kk[i][0]]++;
                }
            }
            int tt = 0;
            for (int i = 0; i < m; i++) {
                if (curr[p[i][0]] > 0 && curr[p[i][1]] > 0) {
                    tt++;
                }
            }
            res = Math.max(res, tt);
        }
        System.out.println(res);
    }

    static final class Utils {
        public static void shuffleSort(int[] arr) {
            shuffle(arr);
            Arrays.sort(arr);
        }

        public static void shuffleSort(long[] arr) {
            shuffle(arr);
            Arrays.sort(arr);
        }

        public static void shuffle(int[] arr) {
            final Random r = new Random();

            for (int i = 0; i <= arr.length - 2; i++) {
                final int j = i + r.nextInt(arr.length - i);
                swap(arr, i, j);
            }
        }

        public static void shuffle(long[] arr) {
            final Random r = new Random();

            for (int i = 0; i <= arr.length - 2; i++) {
                final int j = i + r.nextInt(arr.length - i);
                swap(arr, i, j);
            }
        }

        public static void swap(int[] arr, int i, int j) {
            final int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }

        public static void swap(long[] arr, int i, int j) {
            final long t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
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
