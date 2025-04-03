package lyx.luogu.p16;

import java.io.*;
import java.util.Arrays;

/**
 * P1616 疯狂的采药
 *
 * @apiNote 完全背包
 */
public class P1616 {
    private static final int MAXT = 10000001;
    private static final int MAXM = 10001;

    private static final long[] dp = new long[MAXT];

    private static int t, m;
    private static final int[] cost = new int[MAXM];
    private static final int[] value = new int[MAXM];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int) in.nval;
            in.nextToken(); m = (int) in.nval;
            for (int i = 1; i <= m; i++) {
                in.nextToken(); cost[i] = (int) in.nval;
                in.nextToken(); value[i] = (int) in.nval;
            }
            out.println(f());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static long f() {
        // dp[i][j]: 前i个物品，代价为j的最大价值
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= m; i++) {
            for (int j = cost[i]; j <= t; j++) {
                // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - cost[i]] + value[i]);
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i]);
            }
        }
        return dp[t];
    }
}
