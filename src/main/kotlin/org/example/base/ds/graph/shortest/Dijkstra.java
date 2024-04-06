package org.example.base.ds.graph.shortest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 迪科斯特拉算法
public class Dijkstra {

    public List<Integer> findShortestPath(int[][] graph, int start, int end) {
        var len = graph.length;
        var distances = new int[len];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        // 记录前驱顶点
        var prev = new int[len];
        Arrays.fill(prev, -1);
        var minHeap = new PriorityQueue<int[]>((v1, v2) -> v1[1] - v2[1]);
        minHeap.offer(new int[]{start, 0});
        var visited = new boolean[len];
        while (!minHeap.isEmpty()) {
            var vertex = minHeap.poll();
            int from = vertex[0];
            int weight = vertex[1];
            if (visited[from]) continue;
            visited[from] = true;

            int[] adjs = graph[from];
            for (int to = 0; to < adjs.length; to++) {
                if (adjs[to] > 0 && !visited[to]
                    && weight+ adjs[to] < distances[to]) {
                    distances[to] = weight + adjs[to];
                    minHeap.offer(new int[]{to, distances[to]});
                    // 记录前驱顶点
                    prev[to] = from;
                }
            }
        }
        var path = new ArrayList<Integer>();
        var cur = end;
        path.add(cur);
        while (cur != start) {
            cur = prev[cur];
            path.add(cur);
        }
        return path.reversed();
    }

    public static void main(String[] args) {
        var graph = new int[][]{{0, 10, 3, -1}, {-1, 0, 1, 2}, {-1, 4, 0, 8}, {7, 6, -1, 0}};
        System.out.println(new Dijkstra().findShortestPath(graph, 0, 3));
    }
}
