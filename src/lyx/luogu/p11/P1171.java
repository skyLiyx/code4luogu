package lyx.luogu.p11;

import java.io.*;
import java.util.Arrays;

/**
 * P1171 售货员的难题
 *
 * @apiNote 状压DP
 */
public class P1171 {
    private static final int MAXN = 19;

    private static int n;
    // start[i]: 从起始到i号点的距离
    private static final int[] start = new int[MAXN];
    // back[i]: 从i号点回到起始的距离
    private static final int[] back = new int[MAXN];
    // graph[i][j]: 从i到j号点的距离
    private static final int[][] graph = new int[MAXN][MAXN];

    private static final int[][] dp = new int[1 << MAXN][MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval - 1; // 单独抛开起始点
            build();
            in.nextToken(); // 起始点到起始点的距离不管
            for (int i = 0; i < n; i++) {
                in.nextToken(); start[i] = (int)in.nval;
            }
            for (int i = 0; i < n; i++) {
                in.nextToken(); back[i] = (int)in.nval;
                for (int j = 0; j < n; j++) {
                    in.nextToken(); graph[i][j] = (int)in.nval;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void build() {
        for (int i = 0; i < 1 << n; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static int compute() {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, start[i] + f(1 << i, i));
        }
        return ans;
    }

    /**
     *
     * @param cur    当前所在点
     * @param status 点的可选状态
     */
    private static int f(int status, int cur) {
        if (status == (1 << n) - 1) {
            // 全部走完了，返回起点
            return back[cur];
        }
        if (dp[status][cur] != -1) {
            return dp[status][cur];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((status & (1 << i)) == 0) {
                ans = Math.min(ans, graph[cur][i] + f(status | (1 << i), i));
            }
        }
        dp[status][cur] = ans;
        return ans;
    }
}
