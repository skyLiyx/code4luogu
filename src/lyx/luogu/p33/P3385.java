package lyx.luogu.p33;

import java.io.*;
import java.util.Arrays;

/**
 * P3385 【模板】负环
 *
 * @apiNote SPFA
 */
public class P3385 {
    private static final int MAXN = 2001;
    private static final int MAXM = 3001 << 1;

    // 链式前向星
    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXM];
    private static final int[] to = new int[MAXM];
    private static final int[] weight = new int[MAXM];
    private static int cnt;

    // 最短距离
    private static final int[] distance = new int[MAXN];

    // 队列
    private static final int[] queue = new int[MAXN * MAXN];
    private static int l, r;
    private static final boolean[] enter = new boolean[MAXN];

    private static final int[] updateCnt = new int[MAXN];

    private static int T, n, m, u, v, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        in.nextToken(); T = (int) in.nval;
        for (int i = 0; i < T; i++) {
            in.nextToken(); n = (int) in.nval;
            build(n);
            in.nextToken(); m = (int) in.nval;
            for (int j = 0; j < m; j++) {
                in.nextToken(); u = (int) in.nval;
                in.nextToken(); v = (int) in.nval;
                in.nextToken(); w = (int) in.nval;
                addEdge(u, v, w);
                if (w >= 0) {
                    addEdge(v, u, w);
                }
            }
            out.println(spfa() ? "NO" : "YES");
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        Arrays.fill(distance, 0, n + 1, Integer.MAX_VALUE);
        Arrays.fill(enter, 0, n + 1, false);
        Arrays.fill(updateCnt, 0, n + 1, 0);
        l = r = 0;
    }

    private static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    private static boolean spfa() {
        queue[r++] = 1;
        distance[1] = 0;
        updateCnt[1]++;
        enter[1] = true;
        while (l < r) {
            int u = queue[l++];
            enter[u] = false;
            for (int ei = head[u], v, w; ei > 0; ei = next[ei]) {
                v = to[ei];
                w = weight[ei];
                if (distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    if (!enter[v]) {
                        if (updateCnt[v]++ == n) {
                            return false;
                        }
                        queue[r++] = v;
                        enter[v] = true;
                    }
                }
            }
        }
        return true;
    }
}
