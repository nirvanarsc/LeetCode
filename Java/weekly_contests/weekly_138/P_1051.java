package weekly_contests.weekly_138;

import java.util.Arrays;

public class P_1051 {

    public int heightChecker(int[] heights) {
        final int[] copy = heights.clone();
        Arrays.sort(heights);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != copy[i]) {
                res++;
            }
        }
        return res;
    }
}