package codeforces.round_650_699.round_688;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public final class D {

    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder();
        final FastScanner fs = new FastScanner();
        final int t = fs.nextInt();
        final long[] e = new long[62];
        long p = 2L;
        for (int i = 0; i < e.length; i++) {
            e[i] = p;
            p = p * 2 + 2;
        }
        outer:
        for (int test = 0; test < t; test++) {
            long k = fs.nextLong();
            final List<Integer> res = new ArrayList<>();
            int total = 0;
            while (k > 0) {
                boolean found = false;
                for (int i = e.length - 1; i >= 0; i--) {
                    if (e[i] <= k) {
                        k -= e[i];
                        res.add(i + 1);
                        total += i + 1;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    sb.append(-1);
                    sb.append('\n');
                    continue outer;
                }
            }
            if (total > 2000) {
                sb.append(-1);
                sb.append('\n');
                continue;
            }
            sb.append(total);
            sb.append('\n');
            for (int i = 0; i < res.size(); i++) {
                sb.append(1);
                sb.append(' ');
                for (int j = 1; j < res.get(i); j++) {
                    sb.append(0);
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
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
