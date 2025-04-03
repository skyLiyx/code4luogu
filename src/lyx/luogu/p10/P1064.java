package lyx.luogu.p10;

import java.io.*;
import java.util.Arrays;

/**
 * P1064 [NOIP 2006 提高组] 金明的预算方案
 *
 * @apiNote 有依赖的背包
 */
public class P1064 {
    private static final int MAXN = 32001;

    private static final int MAXM = 61;
    // 代价
    private static final int[] cost = new int[MAXM];
    // 价值
    private static final int[] val = new int[MAXM];
    // 是否主件
    private static final boolean[] king = new boolean[MAXM];
    // 附件数量
    private static final short[] fans = new short[MAXM];
    // 附件
    private static final int[][] follows = new int[MAXM][2];

    private static final int[] dp = new int[MAXN];

    private static int n, m, v, p, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken(); m = (int) in.nval;
            clear();
            for (int i = 1; i <= m; i++) {
                in.nextToken(); v = (int) in.nval;
                in.nextToken(); p = (int) in.nval;
                in.nextToken(); q = (int) in.nval;
                cost[i] = v;
                val[i] = v * p;
                king[i] = q == 0;
                if (q != 0) {
                    follows[q][fans[q]++] = i;
                }
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void clear() {
        for (int i = 1; i <= m; i++) {
            fans[i] = 0;
        }
    }

    private static int f() {
        // dp[i][j]: 前 i 件商品任选，代价为 j 时的最大收益
        Arrays.fill(dp, 0, n + 1, 0);
        for (int i = 1; i <= m; i++) {
            if (king[i]) {
                for (int j = n, fan1, fan2; j >= 0; j--) {
                    // 可能性1：不要这个主件
                    // dp[j] = dp[j];
                    // 可能性2：只要这一个主件
                    if (j - cost[i] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
                    }
                    fan1 = fans[i] > 0 ? follows[i][0] : -1;
                    fan2 = fans[i] > 1 ? follows[i][1] : -1;
                    // 可能性3：要这个主件，和附件1
                    if (fan1 != -1 && j - cost[i] - cost[fan1] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - cost[i] - cost[fan1]] + val[i] + val[fan1]);
                    }
                    // 可能性4：要这个主件，和附件2
                    if (fan2 != -1 && j - cost[i] - cost[fan2] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - cost[i] - cost[fan2]] + val[i] + val[fan2]);
                    }
                    // 可能性5：要这个主件，和两个附件
                    if (fan1 != -1 && fan2 != -1 && j - cost[i] - cost[fan1] - cost[fan2] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - cost[i] - cost[fan1] - cost[fan2]] + val[i] + val[fan1] + val[fan2]);
                    }
                }

            }
        }
        return dp[n];
    }
}
