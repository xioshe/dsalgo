package org.example.base.ds.graph.mst;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Prim {

    public int[][] findMinimumSpanningTree(int[][] graph) {
        var len = graph.length;
        var result = new int[len - 1][3];
        var visited = new boolean[len];
        var minHeap = new PriorityQueue<int[]>((e1, e2) -> e1[2] - e2[2]);
        visited[0] = true;
        for (int i = 0; i < graph[0].length; i++) {
            if (graph[0][i] > 0) {
                minHeap.offer(new int[]{0, i, graph[0][i]});
            }
        }

        int need = result.length;
        while (need > 0 && !minHeap.isEmpty()) {
            int[] edge = minHeap.poll();
            if (visited[edge[1]]) continue;
            visited[edge[1]] = true;
            result[result.length - need] = edge;
            need--;
            for (int to = 0; to < graph[edge[1]].length; to++) {
                if (graph[edge[1]][to] > 0 && !visited[to]) {
                    minHeap.offer(new int[]{edge[1], to, graph[edge[1]][to]});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {{0, 10, 6, 5}, {10, 0, 15, 16}, {6, 15, 0, 4}, {5, 16, 4, 0}};
        int[][] mst = new Prim().findMinimumSpanningTree(adjacencyMatrix);
        for (int[] edge : mst) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
