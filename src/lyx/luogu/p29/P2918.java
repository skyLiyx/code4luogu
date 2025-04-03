package lyx.luogu.p29;

import java.io.*;
import java.util.Arrays;

/**
 * P2918 [USACO08NOV] Buying Hay S
 *
 * @apiNote 完全背包变体
 */
public class P2918 {
    private static final int MAXN = 101;

    private static final int MAXM = 55001;

    private static final int[] dp = new int[MAXM];

    private static final int[] cost = new int[MAXN];
    private static final int[] val = new int[MAXN];
    private static int N, H, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            in.nextToken(); H = (int) in.nval;
            M = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                in.nextToken(); val[i] = (int) in.nval;
                in.nextToken(); cost[i] = (int) in.nval;
                M = Math.max(M, val[i] + H);
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int f() {
        // dp[i][j]: 前 i 个公司任意选择，价值为 j 的最小花费
        // 由于最终可能凑不出来刚好是 H ，所以需要扩充 j ，其扩充量应该是所有公司中的最大重量
        // 对于不可能的 j 数据，以 Integer.MAX_VALUE 表示
        // dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - val[i]] + cost[i])
        Arrays.fill(dp, 1, M + 1, Integer.MAX_VALUE);
        // dp[0][0] = 0
        for (int i = 1; i <= N; i++) {
            for (int j = val[i]; j <= M; j++) {
                if (dp[j - val[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - val[i]] + cost[i]);
                }
            }
        }
        // 最终需要在 H~M 之间寻找最小值
        int ans = dp[H];
        for (int j = H + 1; j <= M; j++) {
            ans = Math.min(ans, dp[j]);
        }
        return ans;
    }
}
