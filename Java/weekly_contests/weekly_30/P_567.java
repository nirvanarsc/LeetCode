package weekly_contests.weekly_30;

import java.util.Arrays;

public class P_567 {

    public boolean checkInclusion(String s1, String s2) {
        final int[] map = new int[26];
        for (char c : s1.toCharArray()) {
            map[c - 'a']++;
        }
        final int[] map2 = new int[26];
        int j = 0;
        for (int i = 0; i < s2.length(); i++) {
            map2[s2.charAt(i) - 'a']++;
            while (j < s2.length() && map2[s2.charAt(j) - 'a'] > map[s2.charAt(j) - 'a']) {
                map2[s2.charAt(j++) - 'a']--;
            }
            if (i - j + 1 == s1.length() && Arrays.equals(map, map2)) {
                return true;
            }
        }
        return false;
    }
}
