package atcoder.beginner_100_199.beginner_162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public final class E {

    private static final int MOD = (int) (1e9 + 7);

    // https://codeforces.com/blog/entry/53925
    // Mobius Function
    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int n = fs.nextInt();
        final int k = fs.nextInt();
        final List<Integer> primes = new ArrayList<>();
        final boolean[] composite = new boolean[k + 5];
        final int[] mobius = new int[k + 5];
        Arrays.fill(mobius, 1);
        for (int i = 2; i < k + 5; i++) {
            if (!composite[i]) {
                mobius[i] = -1;
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < k + 5; j++) {
                composite[i * primes.get(j)] = true;
                mobius[i * primes.get(j)] = -mobius[i];
                if (i % primes.get(j) == 0) {
                    mobius[i * primes.get(j)] = 0;
                    break;
                }
            }
        }
        long res = 0;
        for (int i = 1; i <= k; i++) {
            long curr = 0;
            for (int d = 1; d <= k / i; d++) {
                curr = (curr + (mobius[d] * modPow(k / (i * d), n)) % MOD) % MOD;
            }
            curr = (i * curr) % MOD;
            res = (res + curr) % MOD;
        }
        System.out.println(res);
    }

    private static long modPow(long a, long n) {
        long res = 1;
        while (n > 0) {
            if (n % 2 != 0) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            n /= 2;
        }
        return res;
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
