package org.example.base.ds.graph;


// 并查集，判断图的连通性
public class UnionFind {

    // 返回子集的数量
    public int isConnected(int[][] graph) {
        int m = graph.length;
        int[] fathers = new int[m];
        for (int i = 0; i < m; i++) {
            fathers[i] = i;
        }

        // 根节点的数量
        int count = fathers.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                // 有一条边 -> 可能可以合并 -> union 检查合并
                if (graph[i][j] == 1 && union(fathers, i, j)) {
                    // 发生一次合并意味着根节点少了一个
                    count--;
                }
            }
        }
        return count;
    }

    // 判断 i 和 j 是否需要执行连接，如果需要，就将它们相连连接
    private boolean union(int[] fathers, int i, int j) {
        int fatherOfI = findFather(fathers, i);
        int fatherOfJ = findFather(fathers, j);
        if (fatherOfI != fatherOfJ) {
            fathers[fatherOfI] = fatherOfJ;
            return true;
        }
        return false;
    }

    // 在并查集上找到顶点 i 的根节点
    private int findFather(int[] fathers, int i) {
        if (fathers[i] != i) {
            fathers[i] = findFather(fathers, fathers[i]);
        }
        return fathers[i];
    }
}
