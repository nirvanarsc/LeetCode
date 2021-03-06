package atcoder.hhkb2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class E {

    private static final int MOD = (int) (1e9 + 7);

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int m = fs.nextInt();
        final char[][] grid = new char[n][m];
        int lamps = 0;
        for (int i = 0; i < n; i++) {
            grid[i] = fs.next().toCharArray();
            for (char c : grid[i]) {
                lamps += c == '.' ? 1 : 0;
            }
        }
        final int[][] row = getRow(n, m, grid);
        final int[][] col = getCol(n, m, grid);
        long res = 0;
        final long[] pow = new long[(int) (4e6 + 5)];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '#') {
                    final int x = row[i][j] + col[i][j] - 1;
                    final int y = lamps - x;
                    final long curr = ((pow[x] - 1) * pow[y]) % MOD;
                    res = (res + curr) % MOD;
                }
            }
        }
        System.out.println(res);
    }

    private static int[][] getRow(int n, int m, char[][] grid) {
        final int[][] row = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '#') {
                    row[i][j] = -1;
                } else {
                    int tt = j;
                    while (tt < m && grid[i][tt] == '.') {
                        tt++;
                    }
                    for (int k = j; k < tt; k++) {
                        row[i][k] = tt - j;
                    }
                    j = tt - 1;
                }
            }
        }
        return row;
    }

    private static int[][] getCol(int n, int m, char[][] grid) {
        final int[][] col = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[j][i] == '#') {
                    col[j][i] = -1;
                } else {
                    int tt = j;
                    while (tt < n && grid[tt][i] == '.') {
                        tt++;
                    }
                    for (int k = j; k < tt; k++) {
                        col[k][i] = tt - j;
                    }
                    j = tt - 1;
                }
            }
        }
        return col;
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
