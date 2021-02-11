package codeforces.sit_star;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public final class H {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final PrintWriter pw = new PrintWriter(System.out);
        final int n = fs.nextInt();
        final PriorityQueue<Integer> one = new PriorityQueue<>();
        final PriorityQueue<Integer> two = new PriorityQueue<>();
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < n; i++) {
            final int c = fs.nextInt();
            final int t = fs.nextInt();
            if (t == 1) {
                one.add(c);
            } else {
                two.add(c);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!one.isEmpty() && !two.isEmpty()) {
                if (one.element() < two.element()) {
                    if (ones < 2 * (twos + 1)) {
                        ones++;
                        res += one.remove();
                    } else {
                        twos++;
                        res += two.remove();
                    }
                } else {
                    if (twos < 2 * (ones + 1)) {
                        twos++;
                        res += two.remove();
                    } else {
                        ones++;
                        res += one.remove();
                    }
                }
            } else if (!one.isEmpty()) {
                if (ones < 2 * (twos + 1)) {
                    ones++;
                    res += one.remove();
                } else {
                    res = -1;
                }
            } else if (!two.isEmpty()) {
                if (twos < 2 * (ones + 1)) {
                    twos++;
                    res += two.remove();
                } else {
                    res = -1;
                }
            }
            pw.println(res);
        }
        pw.close();
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
