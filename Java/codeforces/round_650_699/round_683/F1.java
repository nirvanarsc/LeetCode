package codeforces.round_650_699.round_683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class F1 {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int[] arr = fs.nextIntArray(n);
        final int[] count = new int[101];
        int mostFreq = -1;
        int currFreq = -1;
        for (int num : arr) {
            count[num]++;
            if (currFreq < count[num]) {
                currFreq = count[num];
                mostFreq = num;
            }
        }
        int res = 0;
        for (int other = 1; other <= 100; other++) {
            if (other == mostFreq) {
                continue;
            }
            final int[] iter = new int[n];
            for (int i = 0; i < n; i++) {
                if (arr[i] == mostFreq) {
                    iter[i] = 1;
                } else if (arr[i] == other) {
                    iter[i] = -1;
                }
            }
            final int diff = (int) 2e5;
            final int[] map = new int[(int) (4e5 + 5)];
            Arrays.fill(map, -5);
            int preSum = 0;
            map[diff + preSum] = -1;
            for (int i = 0; i < n; i++) {
                preSum += iter[i];
                res = Math.max(res, i - (map[diff + preSum] != -5 ? map[diff + preSum] : i));
                if (map[diff + preSum] == -5) {
                    map[diff + preSum] = i;
                }
            }
        }
        System.out.println(res);
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
