package leetcode.medium;

import java.util.Arrays;

public class P_300 {

    public int lengthOfLIS(int[] nums) {
        final int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            final int idx = lowerBound(dp, num, len);
            if (idx == len) {
                len++;
            }
            dp[idx] = num;
        }
        return len;
    }

    private static int lowerBound(int[] arr, int target, int to) {
        int lo = 0;
        int hi = to;
        while (lo < hi) {
            final int mid = lo + hi >>> 1;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public int lengthOfLISLibrary(int[] nums) {
        final int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static int lengthOfLISBottomUp(int[] nums) {
        final int[] dp = new int[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int lengthOfLISTopDown(int[] nums) {
        return recurse(nums, -1, 0, new Integer[nums.length]);
    }

    public static int recurse(int[] nums, int prev, int start, Integer[] dp) {
        if (start == nums.length) {
            return 0;
        }
        if (prev != -1 && dp[prev] != null) {
            return dp[prev];
        }
        int take = 0;
        if (prev < 0 || nums[start] > nums[prev]) {
            take = 1 + recurse(nums, start, start + 1, dp);
        }
        final int skip = recurse(nums, prev, start + 1, dp);
        if (prev != -1) {
            dp[prev] = Math.max(take, skip);
        }
        return Math.max(take, skip);
    }
}
