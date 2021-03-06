package codeforces.round_650_699.round_667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class D {

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        final int t = fs.nextInt();
        for (int x = 0; x < t; x++) {
            final long n = fs.nextLong();
            final int s = fs.nextInt();
            if (f(n) <= s) {
                System.out.println(0);
                continue;
            }
            for (long mod = 10; true; mod *= 10) {
                if (f(n + (mod - n % mod)) <= s) {
                    System.out.println(mod - n % mod);
                    break;
                }
            }
        }
    }

    private static int f(long n) {
        long curr = n;
        int res = 0;
        while (curr > 0) {
            res += curr % 10;
            curr /= 10;
        }
        return res;
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
