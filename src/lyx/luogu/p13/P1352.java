package lyx.luogu.p13;

import java.io.*;
import java.util.Arrays;

/**
 * P1352 没有上司的舞会
 *
 * @apiNote 树形DP
 */
public class P1352 {

    private static final int MAXN = 6001;

    private static final int[] happy = new int[MAXN];

    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXN];
    private static final int[] to = new int[MAXN];
    private static int cnt;

    private static final boolean[] boss = new boolean[MAXN];

    // no[i]：i为根节点的整棵树，在i不来的情况下最大的快乐值
    private static final int[] no = new int[MAXN];
    // yes[i]：i为根节点的整棵树，在i来的情况下最大的快乐值
    private static final int[] yes = new int[MAXN];

    private static int n, high, low, root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build(n);
            for (int i = 1; i <= n; i++) {
                in.nextToken(); happy[i] = (int) in.nval;
            }
            for (int i = 1; i < n; i++) {
                in.nextToken(); low = (int) in.nval;
                in.nextToken(); high = (int) in.nval;
                addEdge(low, high);
                boss[low] = false;
            }
            for (int i = 1; i <= n; i++) {
                if (boss[i]) {
                    root = i;
                    break;
                }
            }
            f(root);
            out.println(Math.max(no[root], yes[root]));
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        Arrays.fill(boss, 0, n + 1, true);
    }

    private static void addEdge(int low, int high) {
        next[cnt] = head[high];
        to[cnt] = low;
        head[high] = cnt++;
    }

    private static void f(int u) {
        no[u] = 0;
        yes[u] = happy[u];
        for (int ei = head[u], v; ei > 0; ei = next[ei]) {
            v = to[ei];
            f(v);
            no[u] += Math.max(yes[v], no[v]);
            yes[u] += no[v];
        }
    }
}
