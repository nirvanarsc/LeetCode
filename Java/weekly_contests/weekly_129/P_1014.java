package weekly_contests.weekly_129;

public class P_1014 {

    public int maxScoreSightseeingPair(int[] A) {
        int res = 0, cur = 0;
        for (int a : A) {
            res = Math.max(res, cur + a);
            cur = Math.max(cur, a) - 1;
        }
        return res;
    }
}
