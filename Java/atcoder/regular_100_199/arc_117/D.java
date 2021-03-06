package atcoder.regular_100_199.arc_117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;
import java.util.StringTokenizer;

public final class D {

    static int n;
    static int[][] edges;
    static int[][] g;
    static int time;
    static int[] res;
    static int[] mark;
    static int[] parent;

    public static void main(String[] args) {
        final FastScanner fs = new FastScanner();
        time = 1;
        n = fs.nextInt();
        edges = new int[n - 1][2];
        for (int i = 0; i < (n - 1); i++) {
            edges[i] = new int[] { fs.nextInt() - 1, fs.nextInt() - 1 };
        }
        g = packG();
        res = new int[n];
        parent = new int[n];
        mark = new int[n];
        final int[] ll = bfs(0);
        final int[] rr = bfs(ll[0]);
        final int u = ll[0];
        int v = rr[0];
        while (v != u) {
            mark[v] = 1;
            v = parent[v];
        }
        dfs(u, -1);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[i]);
            sb.append(' ');
        }
        System.out.println(sb);
    }

    private static void dfs(int u, int v) {
        res[u] = time;
        int last = -1;
        for (int next : g[u]) {
            if (next == v) {
                continue;
            }
            if (mark[next] == 1) {
                last = next;
                continue;
            }
            time++;
            dfs(next, u);
            time++;
        }
        if (last != -1) {
            time++;
            dfs(last, u);
            time++;
        }
    }

    private static int[] bfs(int u) {
        final Deque<int[]> dq = new ArrayDeque<>();
        final int[] res = { -1, -1 };
        dq.offerLast(new int[] { u, -1 });
        for (int level = 0; !dq.isEmpty(); level++) {
            for (int size = dq.size(); size > 0; size--) {
                final int[] curr = dq.removeFirst();
                final int node = curr[0];
                final int par = curr[1];
                parent[node] = par;
                res[0] = node;
                res[1] = level;
                for (int next : g[node]) {
                    if (next != par) {
                        dq.offerLast(new int[] { next, node });
                    }
                }
            }
        }
        return res;
    }

    private static int[][] packG() {
        final int[][] g = new int[n][];
        final int[] size = new int[n];
        for (int[] edge : edges) {
            ++size[edge[0]];
            ++size[edge[1]];
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[size[i]];
        }
        for (int[] edge : edges) {
            g[edge[0]][--size[edge[0]]] = edge[1];
            g[edge[1]][--size[edge[1]]] = edge[0];
        }
        return g;
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
