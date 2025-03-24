package lynx.luogu.p40;

import java.io.*;
import java.util.Arrays;

/**
 * P4017 最大食物链计数
 *
 * @apiNote 拓扑排序
 */
public class P4017 {
    private static final int MOD = 80112002;

    private static final int MAXN = 5001;
    private static final int MAXM = 500001;

    private static final int[] head = new int[MAXN];
    private static final int[] next = new int[MAXM];
    private static final int[] to = new int[MAXM];
    private static int cnt;

    private static final int[] indegree = new int[MAXN];

    private static final int[] queue = new int[MAXN];

    private static final int[] path = new int[MAXN];

    private static int n, m, a, b;

    private static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 0, n + 1, 0);
        Arrays.fill(indegree, 0, n + 1, 0);
        Arrays.fill(path, 0, n + 1, 0);
    }

    private static void addEdge(int a, int b) {
        next[cnt] = head[a];
        to[cnt] = b;
        head[a] = cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            build(n);
            in.nextToken();
            m = (int)in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                addEdge(a, b);
                indegree[b]++;
            }
            int l = 0, r = 0;
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    queue[r++] = i;
                    path[i] = 1;
                }
            }
            int ans = 0;
            while (l < r) {
                int cur = queue[l++];
                if (head[cur] == 0) {
                    // 这个点没有邻接，统计答案
                    ans = (ans + path[cur]) % MOD;
                }
                for (int e = head[cur]; e > 0; e = next[e]) {
                    path[to[e]] = (path[to[e]] + path[cur]) % MOD;
                    if (--indegree[to[e]] == 0) {
                        queue[r++] = to[e];
                    }
                }
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }
}
