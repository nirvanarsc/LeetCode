package atcoder.regular_100_199.keyence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public final class D {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        System.out.println((1 << n) - 1);
        for (String s : f(n)) {
            System.out.println(s);
        }
    }

    private static List<String> f(int n) {
        if (n == 1) {
            return new ArrayList<>(Collections.singletonList("AB"));
        }
        final List<String> prev = f(n - 1);
        final int size = prev.size();
        for (int i = 0; i < size; i++) {
            final String curr = prev.get(i);
            prev.set(i, curr + curr);
            final char[] shift = new char[curr.length()];
            for (int j = 0; j < curr.length(); j++) {
                final char c = curr.charAt(j);
                shift[j] = c == 'A' ? 'B' : 'A';
            }
            prev.add(curr + new String(shift));
        }
        final char[] last = new char[1 << n];
        int idx = 0;
        for (int i = 0; i < 1 << (n - 1); i++) {
            last[idx++] = 'A';
        }
        for (int i = 0; i < 1 << (n - 1); i++) {
            last[idx++] = 'B';
        }
        prev.add(new String(last));
        return prev;
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
