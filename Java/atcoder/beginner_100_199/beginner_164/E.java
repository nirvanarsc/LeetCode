package atcoder.beginner_100_199.beginner_164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public final class E {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int m = fs.nextInt();
        final int s = Math.min(3000, fs.nextInt());
        final List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            final int u = fs.nextInt() - 1;
            final int v = fs.nextInt() - 1;
            final int a = fs.nextInt();
            final int b = fs.nextInt();
            g.get(u).add(new int[] { v, a, b });
            g.get(v).add(new int[] { u, a, b });
        }
        final int[][] trade = new int[n][2];
        for (int i = 0; i < n; i++) {
            trade[i] = new int[] { fs.nextInt(), fs.nextInt() };
        }
        long[][] dp = new long[n][3000];
        for (long[] row : dp) {
            Arrays.fill(row, (long) 1e18);
        }
        dfs(g, trade, 0, s, dp);
        for (int i = 1; i < n; i++) {
            long res = (long) 1e18;
            for (int j = 0; j < 3000; j++) {
                res = Math.min(res, dp[i][j]);
            }
            System.out.println(res);
        }
    }

    private static long dfs(List<List<int[]>> g, int[][] trade, int u, int s, long[][] dp) {
        if (dp[u][s] != (long) 1e18) {
            return dp[u][s];
        }
        long res = (long) 1e18;
        if (s + trade[u][0] < 3000) {
            res = Math.min(res, trade[u][1] + dfs(g, trade, u, s + trade[u][0], dp));
        }
        if (s > 0) {
            for (int[] next : g.get(u)) {
                if (s >= next[1]) {
                    res = Math.min(res, next[2] + dfs(g, trade, next[0], s - next[1], dp));
                }
            }
        }
        return dp[u][s] = res;
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
