package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class P_1057 {

    // Bucket Sort
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        final Map<Integer, List<int[]>> buckets = new HashMap<>();
        final boolean[] bikeVisited = new boolean[bikes.length];
        final int[] result = new int[workers.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                final int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                buckets.computeIfAbsent(dist, v -> new ArrayList<>()).add(new int[] { i, j });
            }
        }
        for (int k = 0; k <= 2000; k++) {
            for (int[] arr : buckets.getOrDefault(k, Collections.emptyList())) {
                final int w = arr[0];
                final int b = arr[1];
                if (result[w] == -1 && !bikeVisited[b]) {
                    result[w] = b;
                    bikeVisited[b] = true;
                }
            }
        }
        return result;
    }

    // Priority Queue
    public int[] assignBikesPQ(int[][] workers, int[][] bikes) {
        final boolean[] visited = new boolean[bikes.length];
        final int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        int count = 0;
        final PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0] && a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                final int dist = Math.abs(bikes[j][0] - workers[i][0]) + Math.abs(bikes[j][1] - workers[i][1]);
                q.add(new int[] { dist, i, j });
            }
        }

        while (count < workers.length) {
            final int[] curr = q.remove();
            if (res[curr[1]] == -1 && !visited[curr[2]]) {
                res[curr[1]] = curr[2];
                visited[curr[2]] = true;
                count++;
            }
        }

        return res;
    }

    // Lazy Priority Queue
    public int[] assignBikesPQLazy(int[][] workers, int[][] bikes) {
        final boolean[] visited = new boolean[bikes.length];
        final int[] res = new int[workers.length];
        int count = 0;
        final PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0] && a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int i = 0; i < workers.length; i++) {
            q.offer(helper(workers, i, bikes, visited));
        }

        while (!q.isEmpty() && count < workers.length) {
            final int[] curr = q.poll();
            if (visited[curr[2]]) {
                q.offer(helper(workers, curr[1], bikes, visited));
            } else {
                res[curr[1]] = curr[2];
                visited[curr[2]] = true;
                count++;
            }
        }
        return res;
    }

    private static int[] helper(int[][] workers, int i, int[][] bikes, boolean[] visited) {
        int min = Integer.MAX_VALUE, indexJ = -1;
        for (int j = 0; j < bikes.length; j++) {
            if (!visited[j]) {
                final int dist = Math.abs(bikes[j][0] - workers[i][0]) + Math.abs(bikes[j][1] - workers[i][1]);
                if (dist < min) {
                    min = dist;
                    indexJ = j;
                }
            }
        }
        return new int[] { min, i, indexJ };
    }
}
