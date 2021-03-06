package atcoder.regular_100_199.arc_117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class C {

    private static final int MOD = 3;

    private static int[][] binomialCoeff(int n, int k) {
        final int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp;
    }

    private static int[][] binom;

    // https://www.youtube.com/watch?v=9JN5f7_3YmQ&ab_channel=Mathologer
    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        binom = binomialCoeff(3, 3);
        final int n = fs.nextInt();
        final char[] w = fs.next().toCharArray();
        final String word = "BWR";
        int res = 0;
        for (int i = 0; i < w.length; i++) {
            final int add = nCk(w.length - 1, i) * word.indexOf(w[i]) % MOD;
            res = (res + add) % MOD;
        }
        if (w.length % 2 == 0) {
            res = (-res + MOD) % MOD;
        }
        System.out.println(word.charAt(res));
    }

    // https://en.wikipedia.org/wiki/Lucas%27s_theorem
    private static int nCk(int n, int k) {
        int res = 1;
        while (n > 0) {
            final int u = n % MOD;
            final int v = k % MOD;
            res = (res * binom[u][v]) % MOD;
            n /= MOD;
            k /= MOD;
        }
        return res;
    }

    static final class Utils {
        private static class Shuffler {
            private static void shuffle(int[] x) {
                final Random r = new Random();

                for (int i = 0; i <= x.length - 2; i++) {
                    final int j = i + r.nextInt(x.length - i);
                    swap(x, i, j);
                }
            }

            private static void shuffle(long[] x) {
                final Random r = new Random();

                for (int i = 0; i <= x.length - 2; i++) {
                    final int j = i + r.nextInt(x.length - i);
                    swap(x, i, j);
                }
            }

            private static void swap(int[] x, int i, int j) {
                final int t = x[i];
                x[i] = x[j];
                x[j] = t;
            }

            private static void swap(long[] x, int i, int j) {
                final long t = x[i];
                x[i] = x[j];
                x[j] = t;
            }
        }

        public static void shuffleSort(int[] arr) {
            Shuffler.shuffle(arr);
            Arrays.sort(arr);
        }

        public static void shuffleSort(long[] arr) {
            Shuffler.shuffle(arr);
            Arrays.sort(arr);
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
