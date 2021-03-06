package atcoder.beginner_100_199.beginner_167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public final class F {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final List<int[]> left = new ArrayList<>();
        final List<int[]> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final char[] curr = fs.next().toCharArray();
            final Deque<Character> dq = new ArrayDeque<>();
            for (char c : curr) {
                if (!dq.isEmpty() && dq.getFirst() == '(' && c == ')') {
                    dq.removeFirst();
                } else {
                    dq.addFirst(c);
                }
            }
            int a = 0;
            int b = 0;
            while (!dq.isEmpty()) {
                final char c = dq.removeFirst();
                if (c == '(') {
                    a++;
                } else {
                    b++;
                }
            }
            if (a - b > 0) {
                left.add(new int[] { a, b });
            } else {
                right.add(new int[] { a, b });
            }
        }
        left.sort(Comparator.comparingInt(val -> val[1]));
        right.sort((a, b) -> Integer.compare(b[0], a[0]));
        int open = 0;
        for (int[] ll : left) {
            if (open < ll[1]) {
                System.out.println("No");
                return;
            }
            open -= ll[1];
            open += ll[0];
        }
        for (int[] rr : right) {
            if (open < rr[1]) {
                System.out.println("No");
                return;
            }
            open -= rr[1];
            open += rr[0];
        }
        System.out.println(open == 0 ? "Yes" : "No");
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
