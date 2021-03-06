package atcoder.beginner_100_199.beginner_154;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class E {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final char[] s = fs.next().toCharArray();
        final int k = fs.nextInt();
        final int[][][] dp = new int[s.length][4][2];
        for (int[][] r1 : dp) {
            for (int[] r2 : r1) {
                Arrays.fill(r2, -1);
            }
        }
        System.out.println(dfs(s, 0, k, 0, dp));
    }

    private static int dfs(char[] s, int idx, int k, int seenSmaller, int[][][] dp) {
        if (idx == s.length) {
            return k == 0 ? 1 : 0;
        }
        if (dp[idx][k][seenSmaller] != -1) {
            return dp[idx][k][seenSmaller];
        }
        int res = 0;
        final int curr = s[idx] - '0';
        int max = 9;
        if (seenSmaller == 0) {
            max = Math.min(max, curr);
        }
        for (int i = 0; i <= max; i++) {
            final int nextSmaller = i < curr ? 1 : 0;
            if (i > 0 && k > 0) {
                res += dfs(s, idx + 1, k - 1, nextSmaller | seenSmaller, dp);
            } else if (i == 0) {
                res += dfs(s, idx + 1, k, nextSmaller | seenSmaller, dp);
            }
        }
        return dp[idx][k][seenSmaller] = res;
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
