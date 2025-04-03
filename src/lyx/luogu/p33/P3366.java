package lyx.luogu.p33;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * P3366 【模板】最小生成树
 */
public class P3366 {
    private static final int[] father = new int[5001];

    private static final int[][] edges = new int[200001][3];

    private static int N, M;

    private static void build(int n) {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    private static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    private static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            father[fa] = fb;
            return true;
        } else {
            return false;
        }
    }

    private static int kruskal() {
        build(N);
        // 按权重从小到大排序
        Arrays.sort(edges, 0, M, (e1, e2) -> e1[2] - e2[2]);
        int ans = 0, edgeCount = 0;
        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                ans += edge[2];
                edgeCount++;
            }
        }
        return edgeCount == N - 1 ? ans : -1;
    }

    private static int prim() {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        boolean[] sets = new boolean[N + 1];
        int ans = 0;
        // 从1点开始
        sets[1] = true;
        int count = 1;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.addAll(graph.get(1));
        while (!heap.isEmpty()) {
            int[] next = heap.poll();
            if (!sets[next[0]]) {
                sets[next[0]] = true;
                count++;
                ans += next[1];
                heap.addAll(graph.get(next[0]));
            }
        }
        return count == N ? ans : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int)in.nval;
            in.nextToken();
            M = (int)in.nval;
            for (int i = 0; i < M; i++) {
                in.nextToken();
                edges[i][0] = (int)in.nval;
                in.nextToken();
                edges[i][1] = (int)in.nval;
                in.nextToken();
                edges[i][2] = (int)in.nval;
            }
             int ans = kruskal();
//            int ans = prim();
            out.println(ans != -1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
