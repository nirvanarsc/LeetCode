package weekly_contests.weekly_204;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class P_1569 {

    private static class Combinations {
        long[] factorial;
        long[] facInverse;
        long[] inverse;

        Combinations(int n) {
            final int MAX = n + 2;
            factorial = new long[MAX];
            facInverse = new long[MAX];
            inverse = new long[MAX];
            factorial[0] = factorial[1] = 1;
            facInverse[0] = facInverse[1] = 1;
            inverse[1] = 1;
            for (int i = 2; i < MAX; i++) {
                factorial[i] = factorial[i - 1] * i % MOD;
                final long inv = inverse[i] = MOD - inverse[MOD % i] * (MOD / i) % MOD;
                facInverse[i] = facInverse[i - 1] * inv % MOD;
            }
        }

        long ncr(int n, int r) {
            if (n < r) { return 0; }
            if (n < 0 || r < 0) { return 0; }
            return factorial[n] * (facInverse[r] * facInverse[n - r] % MOD) % MOD;
        }

        long modpow(long a, long n) {
            long res = 1;
            while (n > 0) {
                if (n % 2 == 1) {
                    res = res * a % MOD;
                }
                a = a * a % MOD;
                n /= 2;
            }
            return res;
        }
    }

    private static final int MOD = (int) (1e9 + 7);

    public int numOfWays(int[] nums) {
        final List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        final Combinations combinations = new Combinations((int) 1e4);
        return (int) dfs(list, combinations) - 1;
    }

    private static long dfs(List<Integer> nums, Combinations comb) {
        if (nums.isEmpty()) {
            return 1;
        }
        final List<Integer> left = new ArrayList<>();
        final List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(0)) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        return (dfs(left, comb) * dfs(right, comb) % MOD) * comb.ncr(nums.size() - 1, left.size()) % MOD;
    }
}
