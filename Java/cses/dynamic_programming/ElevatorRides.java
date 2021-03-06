package cses.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public final class ElevatorRides {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int x = fs.nextInt();
        final int[] arr = fs.nextIntArray(n);
        final int[] people = new int[1 << n];
        final int[] weight = new int[1 << n];
        Arrays.fill(people, n + 1);
        people[0] = 1;
        for (int mask = 0; mask < 1 << n; mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    final int nextP;
                    final int nextW;
                    if (weight[mask] + arr[i] <= x) {
                        nextP = people[mask];
                        nextW = weight[mask] + arr[i];
                    } else {
                        nextP = people[mask] + 1;
                        nextW = arr[i];
                    }
                    if (people[mask | (1 << i)] > nextP) {
                        people[mask | (1 << i)] = nextP;
                        weight[mask | (1 << i)] = nextW;
                    } else if (people[mask | (1 << i)] == nextP) {
                        weight[mask | (1 << i)] = Math.min(weight[mask | (1 << i)], nextW);
                    }
                }
            }
        }
        System.out.println(people[(1 << n) - 1]);
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
