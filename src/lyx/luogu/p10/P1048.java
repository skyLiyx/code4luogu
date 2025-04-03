package lyx.luogu.p10;

import java.io.*;
import java.util.Arrays;

/**
 * P1048 [NOIP 2005 普及组] 采药
 *
 * @apiNote 01背包
 */
public class P1048 {
    private static final int MAXM = 1001;
    private static final int MAXN = 101;

    private static final int[] dp = new int[MAXM];

    private static int T, M;
    private static final int[] cost = new int[MAXN];
    private static final int[] value = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            T = (int) in.nval;
            in.nextToken(); M = (int) in.nval;
            for (int i = 1; i <= M; i++) {
                in.nextToken(); cost[i] = (int) in.nval;
                in.nextToken(); value[i] = (int) in.nval;
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int f() {
        // dp[i][j]: 前i个物品，代价为j的最大价值
        Arrays.fill(dp, 0, T + 1, 0);
        for (int i = 1; i <= M; i++) {
            for (int j = T; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i]);
            }
        }
        return dp[T];
    }
}
