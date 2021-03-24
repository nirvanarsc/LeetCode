package codeforces.segment_trees.part8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public final class A {

    // Addition, Assignment and Sum
    private static class SegTree {
        int leftMost, rightMost;
        SegTree left, right;
        long set = Long.MAX_VALUE;
        long add;
        long sum;

        SegTree(int leftMost, int rightMost, int[] arr) {
            this.leftMost = leftMost;
            this.rightMost = rightMost;
            if (leftMost == rightMost) {
                sum = arr[leftMost];
            } else {
                final int mid = leftMost + rightMost >>> 1;
                left = new SegTree(leftMost, mid, arr);
                right = new SegTree(mid + 1, rightMost, arr);
                recalc();
            }
        }

        private void recalc() {
            sum = apply(left) + apply(right);
        }

        private void propagate() {
            left.compose(add, set);
            right.compose(add, set);
            set = Long.MAX_VALUE;
            add = 0;
        }

        private void compose(long add, long set) {
            if (set != Long.MAX_VALUE) {
                this.set = set;
                this.add = 0;
            }
            this.add += add;
        }

        private static long apply(SegTree st) {
            long res = st.sum;
            if (st.set != Long.MAX_VALUE) {
                res = (st.rightMost - st.leftMost + 1) * st.set;
            }
            res += (st.rightMost - st.leftMost + 1) * st.add;
            return res;
        }

        private long query(int l, int r) {
            if (l > rightMost || r < leftMost) {
                return 0;
            }
            if (l <= leftMost && rightMost <= r) {
                return apply(this);
            }
            propagate();
            recalc();
            return left.query(l, r) + right.query(l, r);
        }

        private void update(int l, int r, long add, long set) {
            if (l > rightMost || r < leftMost) {
                return;
            }
            if (l <= leftMost && rightMost <= r) {
                compose(add, set);
                return;
            }
            propagate();
            left.update(l, r, add, set);
            right.update(l, r, add, set);
            recalc();
        }
    }

    public static void main(String[] args) throws IOException {
        final FastReader fs = new FastReader();
        final StringBuilder sb = new StringBuilder();
        final int n = fs.nextInt();
        final int q = fs.nextInt();
        final SegTree st = new SegTree(0, n - 1, new int[n]);
        for (int i = 0; i < q; i++) {
            final int type = fs.nextInt();
            final int l = fs.nextInt();
            final int r = fs.nextInt();
            if (type == 1) {
                st.update(l, r - 1, 0, fs.nextInt());
            } else if (type == 2) {
                st.update(l, r - 1, fs.nextInt(), Long.MAX_VALUE);
            } else {
                sb.append(st.query(l, r - 1));
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    static class FastReader {
        private static final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            final byte[] buf = new byte[1024]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextSign() throws IOException {
            byte c = read();
            while ('+' != c && '-' != c) {
                c = read();
            }
            return '+' == c ? 0 : 1;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            final boolean neg = c == '-';
            if (neg) { c = read(); }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) { return -ret; }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') { c = read(); }
            final boolean neg = c == '-';
            if (neg) { c = read(); }
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg) { return -ret; }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') { c = read(); }
            final boolean neg = c == '-';
            if (neg) { c = read(); }

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) { return -ret; }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) { buffer[0] = -1; }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) { fillBuffer(); }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}
