package atcoder.beginner_100_199.beginner_135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class D {

    private static final int MOD = (int) (1e9 + 7);

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final char[] str = fs.next().toCharArray();
        final int n = str.length;
        System.out.println(dfs(str, n - 1, 1, 0, new Integer[n][13][13]));
    }

    private static int dfs(char[] str, int idx, int mod10, int currMod, Integer[][][] dp) {
        if (idx == -1) {
            return currMod == 5 ? 1 : 0;
        }
        if (dp[idx][mod10][currMod] != null) {
            return dp[idx][mod10][currMod];
        }
        int res = 0;
        final int nextMod10 = (mod10 * 10) % 13;
        if (str[idx] == '?') {
            for (int i = 0; i < 10; i++) {
                final int nextMod = ((mod10 * i) + currMod) % 13;
                res = (res + dfs(str, idx - 1, nextMod10, nextMod, dp)) % MOD;
            }
        } else {
            final int num = str[idx] - '0';
            final int nextMod = ((mod10 * num) + currMod) % 13;
            res = (res + dfs(str, idx - 1, nextMod10, nextMod, dp)) % MOD;
        }
        return dp[idx][mod10][currMod] = res;
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
